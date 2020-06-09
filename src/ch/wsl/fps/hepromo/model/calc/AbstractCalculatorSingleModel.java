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

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractCalculatorSingleModel extends AbstractCalculator {
	
	//Multiplikationsfaktoren:
	protected double f_stoer  = 1.00; //Störzeiten >15min
	protected double f_0bis15 = 1.10; //Unvermeidbare Verlustzeiten >15min
	protected double f_indir  = 1.10; //Indirekte Arbeitszeiten
	protected double f_wegzeitenUndPausen;
	
	
	public AbstractCalculatorSingleModel(HeProMoInputData inputData) {	
		super(inputData);
	}
	
	
	
	@Override
	public Ergebnis calculate() {
		f_wegzeitenUndPausen = getArbeitssystem().getFaktorWegzeitenUndPausen();
		
		Ergebnis ergebnis = getNewErgebnisInstance();
		
		double holzmenge = getRelevanteHolzmenge();
		
		if (holzmenge > 0) {
			//get psh0
			double psh0_hProM3 = calcPsh0_hProM3();
			double psh0_total = (psh0_hProM3 * holzmenge);	
			double anzahlPersonen_fte = getAnzahlPersonalVollzeitAequivalente();


			//Berechnung der System- und Faktorzeiten
			//System:
			double psh15 = psh0_total * f_0bis15;
			double wsh = psh15 * f_indir;
			double wpsh = wsh * f_wegzeitenUndPausen * f_stoer;
			if (anzahlPersonen_fte < 1) {
				wpsh *= anzahlPersonen_fte; //kommt vor z.B. in SeilkranLagerplatz
			}

			//Personal:
			double pph0 = anzahlPersonen_fte * psh0_total;
			double pph15 = pph0 * f_0bis15;
			double wph = pph15 * f_indir;
			double wpph = wph * f_wegzeitenUndPausen * f_stoer;

			//Maschinen:
			int anzahl_maschinen = getAnzahlMaschinen();
			double maschinen_laufzeitanteil = getLaufzeitAnteilMaschine();
			double pmh0 = anzahl_maschinen * psh0_total * maschinen_laufzeitanteil;
			double pmh15 = pmh0 * f_0bis15;
			//		double wmh = pmh15 * f_indir;
			//		double wpmh = wmh * f_stoer;



			//Betriebsspezifischer Korrekturfaktor
			double kf = super.faktoren.getKorrekturFaktor();
			wpph   *= kf;
			pmh15  *= kf;
			psh15  *= kf;
			wpsh   *= kf;



			//set ergebnis
			ergebnis.setPsh0_hProM3(psh0_hProM3);
			ergebnis.setPsh0_total(psh0_total);

			ergebnis.setZeitPersonal(wpph);
			ergebnis.setZeitMaschine1(pmh15);
			ergebnis.setZeitUmsetzen(getArbeitssystem().getUmsetzenZeit_h());
			ergebnis.setZeitWeitereAufwaende(getArbeitssystem().getWeitereAufwaendeZeit_h());
			ergebnis.setZeitTotal(wpsh);

			ergebnis.setKostenPersonal_total( getArbeitssystem().getAnsatzPersonalKombiniertGewichtet() * wpph);
			ergebnis.setKostenMaschine1_total(getArbeitssystem().getKostensatzMaschine1_proH() * ergebnis.getZeitMaschine1());
			ergebnis.setKostenUmsetzen_total(getArbeitssystem().getUmsetzenBetrag_CHF());
			ergebnis.setKostenWeitereAufwaende_total(getArbeitssystem().getWeitereAufwaendeBetrag_CHF());

			setProduktivitaet(ergebnis, ProdEinheit.M3_PRO_PSH15, holzmenge / psh15);
			setProduktivitaet(ergebnis, ProdEinheit.M3_PRO_PMH15, holzmenge / pmh15);
			setProduktivitaet(ergebnis, ProdEinheit.FM_OR_PRO_WPPH, holzmenge / wpph);
			ergebnis.setAnzahl_m3(holzmenge);
		}

		ergebnis.setRisikoVerwaltungGewinn_Prz(super.faktoren.getMargin());
		ergebnis.setMehrwertsteuer_Prz(super.faktoren.getMehrwertsteuer());

		ergebnis.setLabelPersonal1(getArbeitssystem().getLabelPersonal1());
		ergebnis.setLabelMaschine1(getArbeitssystem().getLabelMaschine1());
		ergebnis.setLabelMaschine2(getArbeitssystem().getLabelMaschine2());
		
		//return ergebnis
		return ergebnis;
	}

	
	protected Ergebnis getNewErgebnisInstance() {
		return new Ergebnis();
	}
	
	
	/**
	 * Gibt die Anzahl der Vollzeit-Aequivalente (FTE) des beschäftigten Personals zurück.
	 * Arbeitet eine zweite Person z.B. nur zu 50% der Zeit mit, wird 1.5 zurückgegeben.
	 * Arbeitet eine einzelne Person 100%, gibt die Methode 1.0 zurück.
	 */
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return 1;
	}
	
	
	
	/**
	 * Berechnet PSH0 [Stunden pro Kubikmeter] //TODO: je nach modell, wird psh0 iR oder oR erwartet!
	 * 
	 * @return
	 */
	protected abstract double calcPsh0_hProM3();
	
	
	
	/**
	 * Gibt den Laufzeitanteil der verwendeten Maschine zurück
	 * 
	 * @return
	 */
	protected abstract double getLaufzeitAnteilMaschine();
	
	
	/**
	 * Gibt die Anzahl der Maschinen(1) zurück
	 * 
	 * @return
	 */
	protected int getAnzahlMaschinen() {
		return 1;
	}
	
	
	/**
	 * Gibt die relevante Holzmenge zurück, falls sie von derjenigen im Arbeitsobjekt abweicht
	 * 
	 * @return
	 */
	protected double getRelevanteHolzmenge() {
		return super.rindenAbzugBeruecksichtigen? getArbeitsobjekt().getHolzmenge_m3() * getArbeitsobjekt().getRindenAbzugFaktor() : getArbeitsobjekt().getHolzmenge_m3();
	}
	
	

	public double getFaktorStoerungen() {
		return f_stoer;
	}
	
	

	public double getFaktorVerlusteBis15min() {
		return f_0bis15;
	}
	
	

	public double getFaktorIndirekteArbeitszeit() {
		return f_indir;
	}
	
	
	
	public void setFaktorStoerzeiten(double value) {
		f_stoer = value;
	}
	
	
	
	public void setFaktorIndirekteArbeitszeit(double value) {
		f_indir = value;
	}
	
}
