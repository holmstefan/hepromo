/*******************************************************************************
 * Copyright 2020 Stefan Holm
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.wsl.fps.hepromo.model.calc;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorKombiseilgeraet2018 extends AbstractCalculatorSingleModel2014 {
	
	private ErgebnisAnzeige ergebnisAnzeige = ErgebnisAnzeige.AlleArbeitsschritte;
	
	private static final double FAKTOR_MASCHINENLAUFZEIT = 0.8;

	public CalculatorKombiseilgeraet2018(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	public ArbeitsobjektKombiseilgeraet2018 getArbeitsobjekt() {
		return (ArbeitsobjektKombiseilgeraet2018) super.getArbeitsobjekt();
	}
	
	
	@Override
	public ArbeitssystemKombiseilgeraet2018 getArbeitssystem() {
		return (ArbeitssystemKombiseilgeraet2018) super.getArbeitssystem();
	}
	
	
	@Override
	public Ergebnis calculate() {
		f_wegzeitenUndPausen = getArbeitssystem().getFaktorWegzeitenUndPausen();
		
		Ergebnis ergebnis = getNewErgebnisInstance();
		
		double holzmenge = getRelevanteHolzmenge();
		
//		if (holzmenge > 0) {
			//Berechnung der System- und Faktorzeiten
			//System:
			double psh15 = getZeitaufwandPersonal_PPH15()/* * holzmenge*/;
			double wsh = psh15 * f_indir;
			double wpsh = wsh * f_wegzeitenUndPausen * f_stoer;

			//Personal:
			double anzahlPersonen_fte = getAnzahlPersonalVollzeitAequivalente();
			double pph15 = anzahlPersonen_fte * getZeitaufwandPersonal_PPH15()/* * holzmenge*/;
			double wph = pph15 * f_indir;
			double wpph = wph * f_wegzeitenUndPausen * f_stoer;

			//Maschinen:
			double pmh15 = getMaschinenzeitKSG_PMH15();


			//Betriebsspezifischer Korrekturfaktor
			double kf = super.faktoren.getKorrekturFaktor();
			wpph   *= kf;
			pmh15  *= kf;
			psh15  *= kf;
			wpsh   *= kf;



			//set ergebnis
//			ergebnis.setPsh0_hProM3(psh0_hProM3);
//			ergebnis.setPsh0_total(psh0_total);

			ergebnis.setZeitPersonal(wpph);
			ergebnis.setZeitMaschine1(adaptZeitKSG(wpsh, pmh15));
			if (getErgebnisAnzeige() == ErgebnisAnzeige.NurFaellenRuecken) {
				ergebnis.setZeitMaschine2(getMaschinenzeitMotorsaege_PMH15());
			}
			if (getErgebnisAnzeige() == ErgebnisAnzeige.NurVerziehen) {
				ergebnis.setZeitMaschine3(getMaschinenzeitVerzugsfahrzeug_PMH15());
			}
			if (getErgebnisAnzeige() == ErgebnisAnzeige.AlleArbeitsschritte) {
				ergebnis.setZeitMaschine2(getMaschinenzeitMotorsaege_PMH15());
				ergebnis.setZeitMaschine3(getMaschinenzeitVerzugsfahrzeug_PMH15());
			}
			ergebnis.setZeitUmsetzen(getArbeitssystem().getUmsetzenZeit_h());
			ergebnis.setZeitWeitereAufwaende(getArbeitssystem().getWeitereAufwaendeZeit_h());
			if (getErgebnisAnzeige() != ErgebnisAnzeige.NurVerziehen) {
				ergebnis.setZeitTotal(wpsh);
			}
			else {
				ergebnis.setZeitTotal(getMaschinenzeitVerzugsfahrzeug_PMH15() * f_indir * f_wegzeitenUndPausen * f_stoer * kf);
			}

			ergebnis.setKostenPersonal_total( getArbeitssystem().getAnsatzPersonalKombiniertGewichtet() * wpph);
			ergebnis.setKostenMaschine1_total(getArbeitssystem().getKostensatzMaschine1_proH() * ergebnis.getZeitMaschine1());
			ergebnis.setKostenMaschine2_total(getArbeitssystem().getKostensatzMaschine2_proH() * ergebnis.getZeitMaschine2());
			ergebnis.setKostenMaschine3_total(getArbeitssystem().getKostensatzVerzugsfahrzeug_proH() * ergebnis.getZeitMaschine3());
			ergebnis.setKostenUmsetzen_total(getArbeitssystem().getUmsetzenBetrag_CHF());
			ergebnis.setKostenWeitereAufwaende_total(getArbeitssystem().getWeitereAufwaendeBetrag_CHF());

			setProduktivitaet(ergebnis, ProdEinheit.M3_OR_PRO_WSH, 	 holzmenge / wsh );
			setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_WSH, 	 (holzmenge / wsh) / getArbeitsobjekt().getRindenAbzugFaktor());
			setProduktivitaet(ergebnis, ProdEinheit.M3_PRO_PSH15, 	 holzmenge / psh15);
			setProduktivitaet(ergebnis, ProdEinheit.M3_OR_PRO_PSH15, holzmenge / psh15);
			setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_PSH15, (holzmenge / psh15) / getArbeitsobjekt().getRindenAbzugFaktor());
			setProduktivitaet(ergebnis, ProdEinheit.M3_PRO_PMH15, 	 holzmenge / (pmh15 + getMaschinenzeitVerzugsfahrzeug_PMH15()));
			setProduktivitaet(ergebnis, ProdEinheit.FM_OR_PRO_WPPH,	 holzmenge / wpph);
			
			if (getErgebnisAnzeige() == ErgebnisAnzeige.NurMontageDemontage) {
				setProduktivitaet(ergebnis, ProdEinheit.EMPTY, -1);
				ergebnis.hideProduktivitaet();
			}
			else if (getErgebnisAnzeige() == ErgebnisAnzeige.NurVerziehen) {
				setProduktivitaet(ergebnis, ProdEinheit.EMPTY, -1);
				ergebnis.hideProduktivitaet();
			}
			
			ergebnis.setAnzahl_m3(holzmenge);
//		}
		
		ergebnis.setRisikoVerwaltungGewinn_Prz(super.faktoren.getMargin());
		ergebnis.setMehrwertsteuer_Prz(super.faktoren.getMehrwertsteuer());

		ergebnis.setLabelPersonal1(getArbeitssystem().getLabelPersonal1());
		ergebnis.setLabelMaschine1(getArbeitssystem().getLabelMaschine1());
		ergebnis.setLabelMaschine2(getArbeitssystem().getLabelMaschine2());
		ergebnis.setLabelMaschine3(ModelStrings.getString("CalculatorKombiseilgeraet2018.VerzugsfahrzeugInklFahrer")); //$NON-NLS-1$
		
		//return ergebnis
		return ergebnis;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		if (getErgebnisAnzeige() == ErgebnisAnzeige.NurMontageDemontage) {
			return ProdEinheit.EMPTY;
		}
		else if (getErgebnisAnzeige() == ErgebnisAnzeige.NurFaellenRuecken) {
			return ProdEinheit.M3_IR_PRO_PSH15;
		}
		else if (getErgebnisAnzeige() == ErgebnisAnzeige.NurVerziehen) {
			return ProdEinheit.EMPTY;
		}
		else if (getErgebnisAnzeige() == ErgebnisAnzeige.AlleArbeitsschritte) {
			return ProdEinheit.M3_IR_PRO_WSH;
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit2() {
		if (getErgebnisAnzeige() == ErgebnisAnzeige.NurMontageDemontage) {
			return ProdEinheit.EMPTY;
		}
		else if (getErgebnisAnzeige() == ErgebnisAnzeige.NurFaellenRuecken) {
			return ProdEinheit.M3_OR_PRO_PSH15;
		}
		else if (getErgebnisAnzeige() == ErgebnisAnzeige.NurVerziehen) {
			return ProdEinheit.EMPTY;
		}
		else if (getErgebnisAnzeige() == ErgebnisAnzeige.AlleArbeitsschritte) {
			return ProdEinheit.M3_OR_PRO_WSH;
		}
		else {
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Passt die Zeit des Kombiseilgeräts an die Dauer der Arbeit an, damit 
	 * die Zeit des Kombiseilgerätes nicht grösser ist als die Dauer der Arbeit.
	 */
	private double adaptZeitKSG(double wpsh, double ksg_pmh15) {
		wpsh /= super.faktoren.getKorrekturFaktor();
		double ksg_max_pmh15 = wpsh / f_indir / f_wegzeitenUndPausen / f_stoer;
		return Math.min(ksg_pmh15, ksg_max_pmh15);
	}

	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return getArbeitssystem().getEquipeAnzahlPersonen().asDouble();
	}
	
	@Override
	protected double calcPsh0_hProM3() {
		throw new UnsupportedOperationException();
	}
	
	
	private double getZeitaufwandPersonal_PPH15() {
		int anzahlAufstellungen = getArbeitsobjekt().getAnzahlAufstellungen();
		double anzahlPersonen_fte = getAnzahlPersonalVollzeitAequivalente();
		
		double zeitaufwandPersonalTotalMontageDemontage_PPH15 = (anzahlAufstellungen * calcZeitaufwandPersonalProAufstellungMontageDemontage_PPH15()) / anzahlPersonen_fte;
		double zeitaufwandPersonalTotalFaellenRuecken_PPH15 = (getArbeitsobjekt().getHolzmenge_m3() / calcProduktivitaetPersonalFaellenRuecken_m3oRproPPH15()) / anzahlPersonen_fte;
		
		double zeitaufwandPersonalTotal_PPH15 = zeitaufwandPersonalTotalMontageDemontage_PPH15 + zeitaufwandPersonalTotalFaellenRuecken_PPH15;	
		
		switch(getErgebnisAnzeige()) {
		case NurMontageDemontage:
			return zeitaufwandPersonalTotalMontageDemontage_PPH15;

		case NurFaellenRuecken:
			return zeitaufwandPersonalTotalFaellenRuecken_PPH15;

		case NurVerziehen:
			return 0;

		case AlleArbeitsschritte:
			return zeitaufwandPersonalTotal_PPH15;

		default:
			throw new RuntimeException(getErgebnisAnzeige().toString());
		}
	}
	
	
	private double getMaschinenzeitMotorsaege_PMH15() {
		double anzahlPersonen_fte = getAnzahlPersonalVollzeitAequivalente();
		double maschinenzeitTotalFaellenRuecken_PMH15 = (getArbeitsobjekt().getHolzmenge_m3() / calcProduktivitaetPersonalFaellenRuecken_m3oRproPPH15()) / anzahlPersonen_fte;		
		return maschinenzeitTotalFaellenRuecken_PMH15 * 0.5;
	}
	
	
	private double getMaschinenzeitVerzugsfahrzeug_PMH15() {
		double anzahlPersonen_fte = getAnzahlPersonalVollzeitAequivalente();		
		double maschinenzeitTotalFaellenRuecken_PMH15 = (getArbeitsobjekt().getHolzmenge_m3() / calcProduktivitaetPersonalFaellenRuecken_m3oRproPPH15()) / anzahlPersonen_fte;
		maschinenzeitTotalFaellenRuecken_PMH15 *= FAKTOR_MASCHINENLAUFZEIT;	
		return maschinenzeitTotalFaellenRuecken_PMH15 * (getArbeitssystem().getAnteilEinsatzzeitVerzugsfahrzeug_Prz() / 100d);
	}
	
	
	private double getMaschinenzeitKSG_PMH15() {
		int anzahlAufstellungen = getArbeitsobjekt().getAnzahlAufstellungen();
		double anzahlPersonen_fte = getAnzahlPersonalVollzeitAequivalente();
		
		double maschinenzeitTotalMontageDemontage_PMH15 = (anzahlAufstellungen * calcMaschinenzeitProAufstellungMontageDemontage_PMH15());
		double maschinenzeitTotalFaellenRuecken_PMH15 = (getArbeitsobjekt().getHolzmenge_m3() / calcProduktivitaetPersonalFaellenRuecken_m3oRproPPH15()) / anzahlPersonen_fte;
		maschinenzeitTotalFaellenRuecken_PMH15 *= FAKTOR_MASCHINENLAUFZEIT;
		
		double maschinenzeitTotal_PMH15 = maschinenzeitTotalMontageDemontage_PMH15 + maschinenzeitTotalFaellenRuecken_PMH15;
		
		switch(getErgebnisAnzeige()) {
		case NurMontageDemontage:
			return maschinenzeitTotalMontageDemontage_PMH15;
			
		case NurFaellenRuecken:
			return maschinenzeitTotalFaellenRuecken_PMH15;

		case NurVerziehen:
			return 0;

		case AlleArbeitsschritte:
			return maschinenzeitTotal_PMH15;
			
		default:
			throw new RuntimeException(getErgebnisAnzeige().toString());
		}
	}
	
	
	private double calcZeitaufwandPersonalProAufstellungMontageDemontage_PPH15() {
		double a = -15.8938;
		double b =   0.0573;
		double c =   0.2645;
		double d =   9.2746;
		double e =  10.9707;
		
		double erschwernisValue = getArbeitsobjekt().getErschwernisse() == Erschwernisse.Keine ? 0 : 1;
		
		double pph = 
				a +
				b * getArbeitsobjekt().getLaengeProAufstellung_m() + 
				c * getArbeitsobjekt().getHangneigung_Prz() + 
				d * getArbeitsobjekt().getAnzahlStuetzenProAufstellung() + 
				e * erschwernisValue;
		
		return pph;
	}
	
	
	private double calcMaschinenzeitProAufstellungMontageDemontage_PMH15() {
		double a = 1.5166;
		double b = 0.0070;
		double d = 1.2922;
		
		double pmh15 = 
				a +
				b * getArbeitsobjekt().getLaengeProAufstellung_m() +
				d * getArbeitsobjekt().getAnzahlStuetzenProAufstellung();
		
		return pmh15;
	}
	
	
	private double calcProduktivitaetPersonalFaellenRuecken_m3oRproPPH15() {
		double a =  4.0613;
		double c = -0.0150;
		double e = -0.4276;
		double f = -0.0097;
		
		double erschwernisValue = getArbeitsobjekt().getErschwernisse() == Erschwernisse.Keine ? 0 : 1;
		
		double result = 
				a +
				c * getArbeitsobjekt().getHangneigung_Prz() + 
				e * erschwernisValue +
				f * getArbeitsobjekt().getAnteilLaubholz_Prz();
		
		return result;
	}
	
	
	
	public ErgebnisAnzeige getErgebnisAnzeige() {
		return ergebnisAnzeige;
	}

	public void setErgebnisAnzeige(ErgebnisAnzeige ergebnisAnzeige) {
		this.ergebnisAnzeige = ergebnisAnzeige;
	}

	public enum ErgebnisAnzeige {
		NurMontageDemontage("CalculatorKombiseilgeraet2018.enumNurMontageDemontage"), //$NON-NLS-1$
		NurFaellenRuecken("CalculatorKombiseilgeraet2018.enumFaellenRuecken"), //$NON-NLS-1$
		NurVerziehen("CalculatorKombiseilgeraet2018.enumNurVerziehen"), //$NON-NLS-1$
		AlleArbeitsschritte("CalculatorKombiseilgeraet2018.enumGesamteArbeit"); //$NON-NLS-1$
		
		private final String text;
		
		private ErgebnisAnzeige(String text) {
			this.text = text;
		}
		
		@Override
		public String toString() {
			return ModelStrings.getString(text);
		}
	}
	
}
