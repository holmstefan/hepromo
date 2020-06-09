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

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker.Hackgutart;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker.HackerTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorMobilerHacker extends AbstractCalculatorSingleModel {

	public CalculatorMobilerHacker(HeProMoInputData inputData) {
		super(inputData);
	}

	
	
	@Override
	protected ArbeitsobjektMobilerHacker getArbeitsobjekt() {
		return (ArbeitsobjektMobilerHacker) super.getArbeitsobjekt();
	}
	
	
	@Override 
	protected ArbeitssystemMobilerHacker getArbeitssystem() {
		return (ArbeitssystemMobilerHacker) super.getArbeitssystem();
	}
	
	
	
	
	
	
	@Override
	protected double calcPsh0_hProM3() {
		
		double psh15_Fahren = getPsh15_Fahren();
		double psh15_Hacken = getPsh15_Hacken();
		double psh15_Entladen = getPsh15_Entladen();
		
		double psh15_Hackschnitzel =
				psh15_Fahren +
				psh15_Hacken +
				psh15_Entladen;
		
		
		double psh0 = psh15_Hackschnitzel / f_0bis15;
		
		return psh0;
	}
	
	
	
	
	private double getPsh15_Fahren() {
		
		double tF = getTF();
		double kf = 1.2; //Korrekturfaktor zur Anpassung der Modellergebnisse an die Feldversuche
		double dLV = getDLV();
		
		double psh15 = (tF / 60.0) * (1/dLV) * kf;
		
		return psh15;
	}
	
	
	/**
	 * Berechnet das durschnittliche Lastvolumen pro Rückezyklus
	 */
	private double getDLV() {
		double c = getArbeitssystem().getKippContainerVolumen_m3();
		double dBH = 0.95; //Durchschnittliche Beladehöhe
		double dUF = 0.4; //Durschnittlicher Schichtigkeitsfaktor von Hackschnitzeln (Umrechnung von srm in m3)
		
		double dLV = c * dBH * dUF;
		
		return dLV;
	}
	
	
	
	/**
	 * Berechnet den Zeitbedarf für alle Fahrbewegungen des mittleren Rückezyklus (tF)
	 */
	private double getTF() {
		
		double ale = 0.5; //Anteil Leerfahrt pro mittlerer Rückezyklus
		double rmsLELA = 2 * getArbeitsobjekt().getFahrstreckeFeinerschliessung_m(); //Leer- und Lastfahrten pro mittlerer Rückezyklus auf Rückegasse und Maschinenweg
		double stsLELA = 2 * getArbeitsobjekt().getFahrstreckeStrasse_m(); //Leer- und Lastfahrten pro mittlerer Rückezyklus auf Strasse
		
		boolean gh = getArbeitssystem().getHackerTyp() == HackerTyp.AufLastwagen ? true : false; //Grosshacker
		
		
		//Geschwindigkeiten und Fahrstrecken
		double vrm = getVrm();
		double[] sTsFLandRMsFL = getSTsFLandRMsFL();
		
		
		//Zeitbedarf Rückegassen und Maschinenwege
		double rmsFL = sTsFLandRMsFL[1];
		double rmsLE = gh ? 0 : ale * rmsLELA;
		double rmsLA = gh ? 0 : (1.0 - ale) * rmsLELA;
		
		double vRMFL = vrm * 0.5;
		if (vRMFL < 15) {
			vRMFL = 15;
		}
		double vRMLE = vrm * 1.0;
		if (vRMLE < 15) {
			vRMLE = 15;
		}
		double vRMLA = vrm * 0.64;
		if (vRMLA < 15) {
			vRMLA = 15;
		}
		
		double tRMFL = rmsFL / vRMFL;
		double tRMLE = rmsLE / vRMLE;
		double tRMLA = rmsLA / vRMLA;
		
		double tRM = tRMFL + tRMLE + tRMLA;
		
		
		//Zeitbedarf Strassen
		double stsFL = sTsFLandRMsFL[0];
		double stsLE = ale * stsLELA;
		double stsLA = (1.0 - ale) * stsLELA;
		
		double vSTFL = 60;
		double vSTLE = 126.45 + 0.314 * stsLE;
		if (vSTLE > 200) {
			vSTLE = 200;
		}
		double vSTLA = 69.1 + 0.8 * stsLA;
		if (vSTLA > 200) {
			vSTLA = 200;
		}
		
		double tSTFL = stsFL / vSTFL;
		double tSTLE = stsLE / vSTLE;
		double tSTLA = stsLA / vSTLA;
		
		double tST = tSTFL + tSTLE + tSTLA;
		
		
		//Zeitbedarf alle Fahrbewegungen
		double tF = tRM + tST;
		
		
		return tF;
	}
	
	
	
	/**
	 * Geschwindigkeit auf Rückegasse und Maschinenweg
	 */
	private double getVrm() {
		
		double bg = 89; //Basisgeschwindigkeit beim Fahren mit dem Hacker
		double cgX = 22; //Geschwindigkeitsveränderung gegenüber der Basisgeschwindigkeit beim Fahren auf Rückegassen und Maschinenwegen: eben
		double cgY = 11; //Geschwindigkeitsveränderung gegenüber der Basisgeschwindigkeit beim Fahren auf Rückegassen und Maschinenwegen: aufwärts
		double cgZ = 7.5; //Geschwindigkeitsveränderung gegenüber der Basisgeschwindigkeit beim Fahren auf Rückegassen und Maschinenwegen: abwärts
		double hk = getArbeitsobjekt().getHindernisKategorie().value(); //Hindernisklasse
		double gk = getArbeitsobjekt().getNeigungsKategorie().value(); //Gefällsklasse
		
		double vrmEben = bg - cgX * (hk -1.0);


		double vrmAbwaerts  = vrmEben - cgZ * (gk - 1.0); //im code -cgZ, in der doku +cgZ (addition macht jedoch keinen sinn, da sonst bergab die geschwindigkeit höher wäre als geradeaus);
		double vrmAufwaerts = vrmEben - cgY * (gk - 1.0);
		
		double vrm = (vrmEben + vrmAbwaerts + vrmAufwaerts) / 3; //Kommentar alter Code X_Tsf.vb:94: "Auf Feinerschliessung: Fahrrichtung wird zu je 1/3 berücksichtigt"
		
		
		return vrm;
	}
	
	
	
	
	
	
	/**
	 * double[0] = Fahrstrecke Fahren beim Hacken auf Strasse im mittleren Rückezyklus
	 * double[1] = Fahrstrecke Fahren beim Hacken auf Rückegasse, Maschinenweg im mittleren Rückezyklus
	 * @return
	 */
	private double[] getSTsFLandRMsFL() {
		ArbeitsobjektMobilerHacker ao = getArbeitsobjekt();
		
		double ast = (0.0 + ao.getFahrstreckeStrasse_m()) / ao.getFahrstreckeTotal_m(); //Anteil Strasse an Fahrstrecke pro Rohpolter
		double sfl = getSFL(); //Fahrstrecke beim Hacken im Rückezyklus
		
		
		double stsFL;
		double rmsFL;
		if (getArbeitssystem().getHackerTyp() == HackerTyp.AufForwarder) {
			stsFL = ast * sfl;
			rmsFL = (1-ast) * sfl;
		}
		else {
			stsFL = sfl;
			rmsFL = 0;
		}
		
		return new double[]{stsFL, rmsFL};
	}
	
	
	
	
	/**
	 * Fahrstrecke pro Rohpolter
	 */
	private double getSRP() {
		double nt = getArbeitsobjekt().getHackgutmenge_m3(); //Hackgutmenge
		double dRPV = getDRPV(); //Durchschnittliches Rohpoltervolumen
		double nrp = nt / dRPV; //Anzahl Rohpolter
		
		double e = getArbeitsobjekt().getHolzlagerLaenge_m();
		double srp = e / nrp; //Fahrstrecke pro Rohpolter
		
		return srp;
	}
	
	
	
	/**
	 * Fahrstrecke beim Hacken im Rückezyklus
	 */
	private double getSFL() {
		double dLV = getDLV(); //Durchschnittliches Lastvolumen pro Rückezyklus
		double srp = getSRP(); //Fahrstrecke pro Rohpolter
		double dRPV = getDRPV(); //Durchschnittliches Rohpoltervolumen
		
		double sfl = dLV * srp / dRPV; //Fahrstrecke beim Hacken im Rückezyklus //dRPV kürzt sich hier raus?!
		
		return sfl;
	}
	
	
	
	
	
	
	
	private double getPsh15_Hacken() {
		
		double vpz = getVpz(); //Volumen pro Hackzyklus
		double ghf = getArbeitssystem().getHackerTyp() == HackerTyp.AufLastwagen ? 2.5 : 1.0; //Grosshackerfaktor
		double duf = 0.4; //Durchschnittlicher Schichtigkeitsfaktor von Hackschnitzeln (Umrechnung von srm in m3)
		double vb  = getArbeitsobjekt().getHackgutart() == Hackgutart.Rundholzabschnitte ? 1 : 0; //Art des Hackgutes
		
		double psh15_Hacken = Math.pow( (17.55 + 165.48 * vpz - 74.33 * Math.pow(vpz, 2) - 10.48 * vb) * ghf * duf, -1);

		psh15_Hacken *= 0.917795; //Workaround. Dies ist die genauste Näherung zum alten Modell. -> in Diff-Doku vermerkt
		
		
		return psh15_Hacken;
	}
	
	
	
	/**
	 * Volumen pro Hackzyklus
	 */
	private double getVpz() {
		
		//Stückzahl pro Kranzyklus
		double spz = getSpZ();
		
		//Durchschnittliches Rohpoltervolumen
		double dRPV = getDRPV();
		
		//Volumen pro Hackzyklus
		double vpz = getArbeitsobjekt().getVpz(spz, dRPV);
		
		return vpz;
	}
	
	
	
	
	
	/**
	 * Stückzahl pro Kranzyklus
	 */
	private double getSpZ() {
		
		double var_cm;
		if (getArbeitsobjekt().getHackgutart() == Hackgutart.Kronen) {
			var_cm = getArbeitsobjekt().getMittlererZopfDrm_m() * 100.0;
		}
		else { //Vollbaum oder Rundholzabschnitte
			var_cm = getArbeitsobjekt().getMittlererBhdAushieb_m() * 100.0;
		}
		
		
		double spz;
		if (var_cm <= 20) {
			spz = 11.494 * Math.pow(var_cm, -0.7854);
		}
		else {
			spz = 1.5357 * Math.pow(var_cm, -0.1176);
		}
		
		
		if (spz < 1) {
			spz = 1;
		}
		
		
		return spz;
	}
	
	
	
	
	
	
	
	/**
	 * Durchschnittliches Rohpoltervolumen
	 */
	private double getDRPV() {
		double dBHD_cm = getArbeitsobjekt().getMittlererBhdAushieb_m() * 100.0;
		
		double e = getArbeitsobjekt().getHolzlagerLaenge_m();
		double nt = getArbeitsobjekt().getHackgutmenge_m3(); //Hackgutmenge
		double ev = 1.18*nt/e; //Erschliessunglänge mit Holz im Rohpoltermodell
		
		double dRPV = (0.0247 * dBHD_cm + 1.0644 * ev - 0.31973);
		
		return dRPV;
	}
	
	
	
	
	
	private double getPsh15_Entladen() {
		
		double psh15_Hacken = getPsh15_Hacken();
		double fe = 0.2; //Faktor Entladezeit
		
		double psh15_Entladen = psh15_Hacken * fe;
		
		return psh15_Entladen;
	}
	
	

	
	
	@Override
	protected double getRelevanteHolzmenge() {
		return getArbeitsobjekt().getHackgutmenge_m3();
	}
	
	

	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 1;
	}

}
