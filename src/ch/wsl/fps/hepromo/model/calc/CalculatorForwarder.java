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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder.ForwarderTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorForwarder extends AbstractCalculatorSingleModel {

	public CalculatorForwarder(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		
		setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_PMH15, ergebnis.getProduktivitaet_m3ProPmh15());
		
		return ergebnis;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		//Produktivität als Menge/PMH15 angezeigen
		return ProdEinheit.M3_PRO_PMH15;
	}
	
	
	@Override
	protected ArbeitsobjektForwarder getArbeitsobjekt() {
		return (ArbeitsobjektForwarder) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemForwarder getArbeitssystem() {
		return (ArbeitssystemForwarder) super.getArbeitssystem();
	}
	
	
	/**
	 * Vereinfachter Zugriff auf getArbeitsobjekt() (weniger Text -> übersichtlicher)
	 */
	private ArbeitsobjektForwarder ao() {
		return getArbeitsobjekt();
	}
	
	
	
	@Override
	protected double calcPsh0_hProM3() {
		
		//Korrekturfaktor ForwarderTyp
		double kf = getArbeitssystem().getForwarderTyp().getKF();
		
		//Zeitbedarf für die Arbeitsausführung pro mittleren Rückezyklus für kleine und mittlere Forwarder
		double tR = getTR_min();
		
		//Durschnittliches Lastvolumen
		double dLV = getDurchschnittlichesLastvolumen();
		
		//PSH0
		double psh0 = (tR * kf) / (dLV * 60 * f_0bis15);
		
		return psh0;
	}
	
	
	
	/**
	 * Zeitbedarf für die Arbeitsausführung pro mittleren Rückezyklus für kleine und mittlere Forwarder
	 * 
	 * @return
	 */
	private double getTR_min() {

		double rpq = getRPQ();
		double dLV = getDurchschnittlichesLastvolumen();
		double dRPV = getDRPV();
		double dSR = ao().getDurchschnittlicheAnzahlSortimente(); //Durchschnittliche Anzahl transportierte Sortimente pro Rückezyklus
				
		double d;
		double e;
		double f;
		if (getArbeitssystem().getForwarderTyp() == ForwarderTyp.Klein) {
			d = 0.35;
			e = 1.00;
			f = 0.5;
		}
		else if (getArbeitssystem().getForwarderTyp() == ForwarderTyp.Mittel) {
			d = 0.2;
			e = 0.35;
			f = 0.4;
		}
		else {
			throw new RuntimeException();
		}
		
		
		//Zeitbedarf Laden
		double tTL = (  (  (d + (0.2 * rpq)) / dRPV) + e) * dLV;
		
		//Zeitbedarf Entladen
		double tTE = ( (f - (0.1 * dSR))  +  (0.1 * Math.pow(dSR, 2)) ) * dLV;
		
		
		
		
		
		
		
		

		
		double nt = ao().getHolzmenge_m3(); //Nutzungsmenge
		double nrp = nt / dRPV; //Anzahl Rohpolter pro Kalkulationsobjekt
		
		double ste = ao().getErschliessungsLaengeEinseitig_m() * (ao().getErschliessungsLaengeEinseitigAnteilStrasse_Prz() / 100.0); //Strasse vorgeliefert einseitig
		double stb = ao().getErschliessungsLaengeBeidseitig_m() * (ao().getErschliessungsLaengeBeidseitigAnteilStrasse_Prz() / 100.0); //Strasse vorgeiiefert beidseitig
		double ee  = ao().getErschliessungsLaengeEinseitig_m();  //Erschliessungslinie vorgeliefert einseitig
		double eb  = ao().getErschliessungsLaengeBeidseitig_m(); //Erschliessungslinie vorgeliefert beidseitig
		
		
		double so = ao().getAnzahlVerschiedeneSortimente(); //Anzahl verschieden zu lagernde Sortimente
		double srp = (ee + eb) / nrp; //Fahrstrecke pro Rohpolter
		
		double sFL = (dLV * so * srp) / (dRPV * dSR); //Fahrstrecke beim Laden
		double stPFL = (ste + stb) / (ee + eb); //Anteil Strasse der Strecke Fahre beim Laden
		
		
		
		
		
		
		
		
		double sstLEaf = ao().getFahrstreckeAufStrasse() * ao().getFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz() / 100.0;
		double sstLEab = ao().getFahrstreckeAufStrasse() * ao().getFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz() / 100.0;
		double sstLEeb = ao().getFahrstreckeAufStrasse() - sstLEaf - sstLEab;
		
		double sstLAaf = ao().getFahrstreckeAufStrasse() * ao().getFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz() / 100.0;
		double sstLAab = ao().getFahrstreckeAufStrasse() * ao().getFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz() / 100.0;
		double sstLAeb = ao().getFahrstreckeAufStrasse() - sstLAaf - sstLAab;
		
		double sstFL = sFL * stPFL;
		
		double srmLEaf = ao().getFahrstreckeAufFeinerschliessung() * ao().getFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz() / 100.0;
		double srmLEab = ao().getFahrstreckeAufFeinerschliessung() * ao().getFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz() / 100.0;
		double srmLEeb = ao().getFahrstreckeAufFeinerschliessung() - srmLEaf - srmLEab;
		
		double srmLAaf = ao().getFahrstreckeAufFeinerschliessung() * ao().getFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz() / 100.0;
		double srmLAab = ao().getFahrstreckeAufFeinerschliessung() * ao().getFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz() / 100.0;
		double srmLAeb = ao().getFahrstreckeAufFeinerschliessung() - srmLAaf - srmLAab;

		double srmFL = sFL - sstFL;
		double srmFLaf = srmFL * ao().getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz() / 100.0;
		double srmFLab = srmFL * ao().getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz() / 100.0;
		double srmFLeb = srmFL - srmFLaf - srmFLab;
		
		

		double vStLEaf = 126.45 + 0.314 * sstLEaf;
		vStLEaf = vStLEaf > 200 ? 200 : vStLEaf;
		
		double vStLEab = 126.45 + 0.314 * sstLEab;
		vStLEab = vStLEab > 200 ? 200 : vStLEab;
		
		double vStLEeb = 126.45 + 0.314 * sstLEeb;
		vStLEeb = vStLEeb > 200 ? 200 : vStLEeb;
		

		double dtStLEaf = sstLEaf / vStLEaf;
		double dtStLEab = sstLEab / vStLEab;
		double dtStLEeb = sstLEeb / vStLEeb;
		
		

		double vStLAab = 69.1 +0.80 * sstLAab;
		vStLAab = vStLAab > 200 ? 200 : vStLAab;
		
		double vStLAeb = 69.1 +0.80 * sstLAeb;
		vStLAeb = vStLAeb > 200 ? 200 : vStLAeb;

		double dtStLAaf = sstLAaf / 62.0;
		double dtStLAab = sstLAab / vStLAab;
		double dtStLAeb = sstLAeb / vStLAeb;
		
		double tStFL = sstFL / 60.0;
		double tStLE = dtStLEaf + dtStLEab + dtStLEeb;
		double tStLA = dtStLAaf + dtStLAab + dtStLAeb;
		
		
		

		
		
		double cGy = 11;
		double cGx = 22;
		double cGz = 7.5;
		double bg = 89;
		
		double hk = ao().getHindernisKategorieFeinerschliessung().value();
		double gk = ao().getNeigungsKategorieFeinerschliessung().value();
		
		double vRMaf = bg - cGx * (hk -1) - cGy * (gk -1);
		double vRMab = bg - cGx * (hk -1) + cGz * (gk -1);
		double vRMeb = bg - cGx * (hk -1);
		
		
		

		double vRMLEaf = vRMaf * 1.0;
		vRMLEaf = vRMLEaf < 15 ? 15 : vRMLEaf;
		double vRMLEab = vRMab * 1.0;
		vRMLEab = vRMLEab < 15 ? 15 : vRMLEab;
		double vRMLEeb = vRMeb * 1.0;
		vRMLEeb = vRMLEeb < 15 ? 15 : vRMLEeb;

		double vRMFLaf = vRMaf * 0.5;
		vRMFLaf = vRMFLaf < 15 ? 15 : vRMFLaf;
		double vRMFLab = vRMab * 0.5;
		vRMFLab = vRMFLab < 15 ? 15 : vRMFLab;
		double vRMFLeb = vRMeb * 0.5;
		vRMFLeb = vRMFLeb < 15 ? 15 : vRMFLeb;

		double vRMLAaf = vRMaf * 0.64;
		vRMLAaf = vRMLAaf < 15 ? 15 : vRMLAaf;
		double vRMLAab = vRMab * 0.64;
		vRMLAab = vRMLAab < 15 ? 15 : vRMLAab;
		double vRMLAeb = vRMeb * 0.64;
		vRMLAeb = vRMLAeb < 15 ? 15 : vRMLAeb;
		
		
		

		double dtRMFLaf = srmFLaf / vRMFLaf;
		double dtRMFLab = srmFLab / vRMFLab;
		double dtRMFLeb = srmFLeb / vRMFLeb;
		
		double dtRMLEaf = srmLEaf / vRMLEaf;
		double dtRMLEab = srmLEab / vRMLEab;
		double dtRMLEeb = srmLEeb / vRMLEeb;
		
		double dtRMLAaf = srmLAaf / vRMLAaf;
		double dtRMLAab = srmLAab / vRMLAab;
		double dtRMLAeb = srmLAeb / vRMLAeb;
		
		double tRMFL = dtRMFLaf + dtRMFLab + dtRMFLeb;
		double tRMLE = dtRMLEaf + dtRMLEab + dtRMLEeb;
		double tRMLA = dtRMLAaf + dtRMLAab + dtRMLAeb;
		
		
		
		//Zeitbedarf im mittleren Rückezyklus auf Rückegasse, Maschinenweg
		double tRM = tRMFL + tRMLE + tRMLA; 
		
		//Zeitbedarf im mittleren Rückezyklus auf Waldstrasse
		double tSt = tStFL + tStLE + tStLA;
		
		//Zeitbedarf im mittleren Rückezyklus auf Rückegasse, Maschinenweg, Strasse aller Fahrbewegungen
		double tF = tRM + tSt;
		
		
		//Zeitbedarf für die Arbeitsausführung pro mittleren Rückezyklus für kleine und mittlere Forwarder
		double tR = tTL + tTE + tF;
		
		return tR;
	}
	
	
	
	
	
	
	
	/**
	 * Rohpolterqualität
	 * 
	 * @return
	 */
	private double getRPQ() {
		
		double nhp = ao().getNadelholzAnteil_Prz() / 100.0;
		double lhp = ao().getLaubholzAnteil_Prz() / 100.0;
		
		//Holzanfall pro Laufmeter Erschliessungslinie beidseitig vorgeliefert
		double ev = getEv();
		
		//Durchschnittlicher BHD des Aushiebs
		double dBHD_cm = ao().getDurchschnittlicherBhdAushieb_cm();
		
		
		//Rohpolterqualität
		double rpq = (-0.01237798 * dBHD_cm) + (0.349602 * ev) + (0.999837 * nhp) + (1.12327 * lhp);
		if (rpq > 1) {
			rpq = 1;
		}
		
		return rpq;
	}
	
	
	
	
	/**
	 * Durschnittliches Rohpoltervolumen
	 * 
	 * @return
	 */
	private double getDRPV() {
		
		//Holzanfall pro Laufmeter Erschliessungslinie beidseitig vorgeliefert
		double ev = getEv();
		
		//Durchschnittlicher BHD des Aushiebs
		double dBHD_cm = ao().getDurchschnittlicherBhdAushieb_cm();
		
		//Durschnittliches Rohpoltervolumen
		double dRPV = (0.024657 * dBHD_cm) + (1.0644 * ev) - 0.319737; //in dokumentation -0.31973! (ohne 7 am Schluss wie im Code)
		
		return dRPV;
	}
	
	
	
	/**
	 * Holzanfall pro Laufmeter Erschliessungslinie beidseitig vorgeliefert
	 * 
	 * @return
	 */
	private double getEv() {
		//Erschliessungslinienlänge total
//		double eTot = ao().getErschliessungsLaengeEinseitig_m() + ao().getErschliessungsLaengeBeidseitig_m();

		//Berechnung Erschliessungslinie vorgeliefert einseitig
//		double ste = eTot * 0.20;
//		double mWe = eTot * 0.05; 
//		double rGe = eTot * 0.05; 
//		double ee = ste + mWe + rGe;
		double ee = ao().getErschliessungsLaengeEinseitig_m();

		//Berechnung Erschliessungslinie vorgeliefert beidseitig
//		double stb = eTot * 0.10;
//		double mWb = eTot * 0.30; 
//		double rGb = eTot * 0.30; 
//		double eb = stb + mWb + rGb;
		double eb = ao().getErschliessungsLaengeBeidseitig_m();

		//Nutzungsmenge
		double nt = ao().getHolzmenge_m3();

		//Holzanfall pro Laufmeter Erschliessungslinie beidseitig vorgeliefert
		double ev = (2 * nt) / (ee + 2*eb);

		return ev;
	}
	
	
	
	
	private double getDurchschnittlichesLastvolumen() {
		
		//Ladequerschnittsfläche
		double lq = getArbeitssystem().getLadeQuerschnittsFlaeche_m2();
		
		
		
		//Durchschnittliche Beladehöhe des Forwarders
		double dBH;
		double s = 2 *(ao().getFahrstreckeAufStrasse() + ao().getFahrstreckeAufFeinerschliessung()); //Faktor zwei für je hin und rückfahrt
		if (s < 200) {
			dBH = 0.85;
		}
		else if (s <= 800) {
			dBH = 0.9;
		}
		else {
			dBH = 0.95;
		}
		
		
		
		//Durschnittliche Holzlänge im Kalkulationsobjekt
		double dHL;
		//Nutzungsmenge
		double nt = ao().getHolzmenge_m3();
		//Nutzungsmenge Anteil Volumen
//		double[] nav = new double[9]; //convenience, gebraucht werden nur [2]-[8] //dHL stammt direkt aus den Eingaben des Benutzers!
//		nav[2] = nt * 0.01;
//		nav[3] = nt * 0.05;
//		nav[4] = nt * 0.25;
//		nav[5] = nt * 0.63;
//		nav[6] = nt * 0.04;
//		nav[7] = nt * 0.01;
//		nav[8] = nt * 0.01;
//		//Holzlänge der Sortimente
//		double[] hl = new double[9]; //convenience, gebraucht werden nur [2]-[8]
//		hl[2] = 2;
//		hl[3] = 3;
//		hl[4] = 4;
//		hl[5] = 5;
//		hl[6] = 6;
//		hl[7] = 7;
//		hl[8] = 8;
//		double zaehler = 0.0;
//		for (int i=2; i<=8; i++) {
//			zaehler += nav[i] * hl[i];
//		}
//		double nenner = nt - (nav[2] / 2);
//		dHL = zaehler / nenner;
		dHL = ao().getDurchschnittlicheHolzlaenge_m();
		
		
		//Durschnittlicher Schichtigkeitsfaktor im Kalkulationsobjekt
		double dUF;
		//Laubholz krumm
		double lhk = nt * (ao().getLaubholzAnteil_Prz() / 100.0) * (ao().getAnteilKrumm_Prz() / 100.0);
		//Laubholz gerade
		double lhg = nt * (ao().getLaubholzAnteil_Prz() / 100.0) - lhk;
		//Nadelholz krumm
		double nhk = nt * (ao().getNadelholzAnteil_Prz() / 100.0) * (ao().getAnteilKrumm_Prz() / 100.0);
		//Nadelholz gerade
		double nhg = nt * (ao().getNadelholzAnteil_Prz() / 100.0) - nhk;
		dUF = (0.53 * lhk + 0.59 * nhg + 0.57 *(lhg + nhk)) / nt;
		
		
		//Durschnittliches Lastvolumen
		double dLV = lq * dBH * dHL * dUF;
		
		
		return dLV;
	}
	
	
	
	
	
	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 1.0;
	}

	
	
}
