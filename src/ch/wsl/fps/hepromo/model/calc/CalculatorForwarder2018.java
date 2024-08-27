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
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018.Forwardertyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorForwarder2018 extends AbstractCalculatorSingleModel2014 {

	private ErgebnisAnzeige ergebnisAnzeige = ErgebnisAnzeige.Rundholz;
	
	
	public CalculatorForwarder2018(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		ergebnis.setUnitMaschine2ISH(true);
		
		if (ergebnis.getAnzahl_m3() > 0 && getErgebnisAnzeige() == ErgebnisAnzeige.Energieholz) {
			setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_PMH15, ergebnis.getProduktivitaet_m3ProPmh15());
			setProduktivitaet(ergebnis, ProdEinheit.M3_OR_PRO_PMH15, ergebnis.getProduktivitaet_m3ProPmh15() * getArbeitsobjekt().getRindenAbzugFaktor());
		}
		
		if (ergebnis.getAnzahl_m3() > 0) {
			SubcalculatorThw calcThw = SubcalculatorThw.getInstanceForwarder(
					getArbeitsobjekt().isEinsatzThw(), 
					getArbeitsobjekt().getAnzahlRueckegassen());
			
			// KEIN Korrekturfaktor für Energieholz.
//			double kfEnergieholz = getErgebnisAnzeige() == ErgebnisAnzeige.Energieholz ? getKfEnergieholz() / 0.89 : 1;	
			
			ergebnis.setZeitMaschine2(calcThw.calcISH_THW());
			ergebnis.setKostenMaschine2_total(getArbeitssystem().getKostensatzMaschine2_proH() * ergebnis.getZeitMaschine2());
		}
		
		return ergebnis;
	}
	
	
	@Override
	public ArbeitsobjektForwarder2018 getArbeitsobjekt() {
		return (ArbeitsobjektForwarder2018) super.getArbeitsobjekt();
	}
	
	@Override
	public ArbeitssystemForwarder2018 getArbeitssystem() {
		return (ArbeitssystemForwarder2018) super.getArbeitssystem();
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		if (getErgebnisAnzeige() == ErgebnisAnzeige.Rundholz) {
			return super.getProdEinheit1();
		}
		else {
			return ProdEinheit.M3_IR_PRO_PMH15;
		}
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit2() {
		if (getErgebnisAnzeige() == ErgebnisAnzeige.Rundholz) {
			return super.getProdEinheit2();
		}
		else {
			return ProdEinheit.EMPTY;
		}
	}
	


	@Override
	protected double calcPsh0_hProM3() {
		double m3ProPMH15 = getProduktivitaet_m3proPMH15();
		if (super.rindenAbzugBeruecksichtigen) {
			double rindenabzug_fix = 0.89;
			double rindenabzug_variabel = getArbeitsobjekt().getRindenAbzugFaktor();
			m3ProPMH15 *= rindenabzug_variabel / rindenabzug_fix;
		}
		
		double pmh15 = 1 / m3ProPMH15;
		double pmh0 = pmh15 / f_0bis15;
		
		return pmh0;
	}
	
	
	@Override
	protected double getRelevanteHolzmenge() {
		switch(getErgebnisAnzeige()) {
		case Rundholz:
			double holzmenge = getArbeitsobjekt().getVerkaufRundholz_m3oR();
			return holzmenge;
			
		case Energieholz:
			holzmenge = getArbeitsobjekt().getEnergieholzmenge_m3iR();
			return holzmenge;

		default:
			throw new RuntimeException(getErgebnisAnzeige().toString());
		}
	}
	
	
	private double getProduktivitaet_m3proPMH15() {
		switch(getErgebnisAnzeige()) {
		case Rundholz:
			return getProduktivitaetStammholz_m3oRproPMH15();
			
		case Energieholz:
			return getProduktivitaetStammholz_m3oRproPMH15() / getKfEnergieholz();

		default:
			throw new RuntimeException(getErgebnisAnzeige().toString());
		}
	}
	
	
	
	private double getProduktivitaetStammholz_m3oRproPMH15() {
		double bhdMit = getArbeitsobjekt().getBhdMit_cm();
		
		double result = (4.14 + 0.31 * bhdMit) * getReduktionGesamt();
		
		return result;
	}
	

	
	private double getReduktionGesamt() {	
		double result = 1
				- getReduktionRückeentfernung()
				- getReduktionHangneigung()
				- getReduktionAnzahlSortimente()
				- getReduktionErschwernisse()
				- getReduktionAbstandRückegassen()
				+ getReduktionForwarderTyp();
		
		return result;
	}
	
	
	
	private double getReduktionRückeentfernung() {
		switch(getArbeitsobjekt().getRueckeentfernung()) {
		case EntfKleiner200m:
			return 0;
			
		case Entf201bis400m:
			return 0.05;
			
		case Entf401bis600m:
			return 0.10;
			
		case Entf601bis900m:
			return 0.15;
			
		case Entf901bis1200m:
			return 0.175;
			
		case EntfGroesser1200m:
			return 0.20;
			
		default:
			throw new RuntimeException(getArbeitsobjekt().getRueckeentfernung().name());
		}
	}
	
	
	private double getReduktionHangneigung() {
		switch(getArbeitsobjekt().getHangneigung()) {
		case NeigungBis15Prozent:
			return 0;
			
		case Neigung15Bis25Prozent:
			return 0.025;
			
		case Neigung25Bis35Prozent:
			return 0.05;
			
		case Neigung35Bis45Prozent:
			return 0.10;
			
		case NeigungGroesser45Prozent:
			return 0.15;
			
		default:
			throw new RuntimeException(getArbeitsobjekt().getHangneigung().name());
		}
	}
	
	
	private double getReduktionAnzahlSortimente() {
		switch(getArbeitsobjekt().getAnzahlSortimente()) {
		case AnzahlBis3:
			return 0;
			
		case Anzahl4Bis6:
			return 0.025;
			
		case Anzahl7UndMehr:
			return 0.05;
			
		default:
			throw new RuntimeException(getArbeitsobjekt().getAnzahlSortimente().name());
		}
	}
	
	
	private double getReduktionErschwernisse() {
		switch(getArbeitsobjekt().getErschwernisse()) {
		case Keine:
			return 0;
			
		case Wenige:
			return 0.05;
			
		case Viele:
			return 0.10;
			
		default:
			throw new RuntimeException(getArbeitsobjekt().getErschwernisse().name());
		}
	}
	
	
	private double getReduktionAbstandRückegassen() {
		switch(getArbeitsobjekt().getAbstandRueckegasse()) {
		case Circa40m:
			return 0;
			
		case Circa30m:
			return 0.05;
			
		case Circa20m:
			return 0.15;
			
		default:
			throw new RuntimeException(getArbeitsobjekt().getAbstandRueckegasse().name());
		}
	}
	
	
	private double getReduktionForwarderTyp() {
		switch(getArbeitssystem().getForwardertyp()) {
		case Mittel:
			return 0;
			
		case Gross:
			return 0.20;
			
		default:
			throw new RuntimeException(getArbeitssystem().getForwardertyp().name());
		}
	}

	
	
	private double getKfEnergieholz() {
		double bhd = getArbeitsobjekt().getBhdMit_cm();
		double forwardertyp = getArbeitssystem().getForwardertyp() == Forwardertyp.Gross ? 1 : 0;
		
		double abstandRueckegasse = -1;
		switch(getArbeitsobjekt().getAbstandRueckegasse()) {
		case Circa40m:
			abstandRueckegasse = 1;
			break;
			
		case Circa30m:
			abstandRueckegasse = 0.5;
			break;
			
		case Circa20m:
			abstandRueckegasse = 0;
			break;
			
		default:
			throw new RuntimeException(getArbeitsobjekt().getAbstandRueckegasse().name());
		}
		
		
		double eholzanfall_proHa = getArbeitsobjekt().getEnergieholzanfall_m3iRproHa().getEnergieholzanfall_m3ProHa();
		double zopfdurchmesser = getArbeitsobjekt().getZopfdurchmesser_cm();
		
		double kf =
				(- 0.005 * Math.pow(bhd, 2)
				+ 0.469 * bhd
				+ 0.089 * bhd * forwardertyp
				+ 3.159 * abstandRueckegasse
				+ 2.975) 
				/ 
				( 6.171 * Math.log(bhd)
				+ 0.021 * eholzanfall_proHa
				+ 0.268 * zopfdurchmesser
				- 15.586);
		
		return kf;
	}
	
	
	
	public ErgebnisAnzeige getErgebnisAnzeige() {
		return ergebnisAnzeige;
	}

	public void setErgebnisAnzeige(ErgebnisAnzeige ergebnisAnzeige) {
		this.ergebnisAnzeige = ergebnisAnzeige;
		super.rindenAbzugBeruecksichtigen = ergebnisAnzeige == ErgebnisAnzeige.Rundholz;
	}

	public enum ErgebnisAnzeige {
		Rundholz("CalculatorForwarder2018.enumRundholz"), //$NON-NLS-1$
		Energieholz("CalculatorForwarder2018.enumEnergieholz"); //$NON-NLS-1$
		
		private final String text;
		
		private ErgebnisAnzeige(String text) {
			this.text = text;
		}
		
		@Override
		public String toString() {
			return ModelStrings.getString(text);
		}
	}
	
	@Override
	public void setRindenAbzugBeruecksichtigen(boolean flag) {
		//FIXME(prio1): dies scheint bei diesem Modell nicht zu funktionieren.
		// WORKAROUND: setRindenabzugsfaktorManuellGesetzt(true) und setRindenabzugsfaktorManuell(1) !
		super.setRindenAbzugBeruecksichtigen(flag);
	}
}
