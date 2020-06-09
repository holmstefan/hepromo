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
package ch.wsl.fps.hepromo.model.aobj;

import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektMobilerHacker extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;

	private double laubholzAnteil_Prz;
	private int anzahlBaeume;
//	private double hackgutMenge_m3;
	private Hackgutart hackgutart;
	private double mittlererBhdAushieb_m;
	private double mittlererZopfDrm_m;
	
	private int fahrstreckeStrasse_m;
	private int fahrstreckeFeinerschliessung_m;
	
	private int holzlagerLaenge_m;
	
	private HindernisKategorie hindernisKategorie;
	private NeigungsKategorie neigungsKategorie;	
	
	
	
	public double getLaubholzAnteil_Prz() {
		return laubholzAnteil_Prz;
	}


	public void setLaubholzAnteil_Prz(double laubholzAnteil_Prz) {
		this.laubholzAnteil_Prz = laubholzAnteil_Prz;
	}


	public int getAnzahlBaeume() {
		return anzahlBaeume;
	}


	public void setAnzahlBaeume(int anzahlBaeume) {
		this.anzahlBaeume = anzahlBaeume;
	}


//	public double getHackgutMenge_m3() {
//		return hackgutMenge_m3;
//	}
//
//
//	public void setHackgutMenge_m3(double hackgutMenge_m3) {
//		this.hackgutMenge_m3 = hackgutMenge_m3;
//	}


	public Hackgutart getHackgutart() {
		return hackgutart;
	}


	public void setHackgutart(Hackgutart hackgutart) {
		this.hackgutart = hackgutart;
	}


	public double getMittlererBhdAushieb_m() {
		return mittlererBhdAushieb_m;
	}


	public void setMittlererBhdAushieb_m(double mittlererBhdAushieb_m) {
		this.mittlererBhdAushieb_m = mittlererBhdAushieb_m;
	}


	public double getMittlererZopfDrm_m() {
		return mittlererZopfDrm_m;
	}


	public void setMittlererZopfDrm_m(double mittlererZopfDrm_m) {
		this.mittlererZopfDrm_m = mittlererZopfDrm_m;
	}


	public int getFahrstreckeStrasse_m() {
		return fahrstreckeStrasse_m;
	}


	public void setFahrstreckeStrasse_m(int fahrstreckeStrasse_m) {
		this.fahrstreckeStrasse_m = fahrstreckeStrasse_m;
	}


	public int getFahrstreckeFeinerschliessung_m() {
		return fahrstreckeFeinerschliessung_m;
	}


	public void setFahrstreckeFeinerschliessung_m(int fahrstreckeFeinerschliessung_m) {
		this.fahrstreckeFeinerschliessung_m = fahrstreckeFeinerschliessung_m;
	}
	
	
	public int getFahrstreckeTotal_m() {
		return getFahrstreckeStrasse_m() + getFahrstreckeFeinerschliessung_m();
	}


	public int getHolzlagerLaenge_m() {
		return holzlagerLaenge_m;
	}


	public void setHolzlagerLaenge_m(int holzlagerLaenge_m) {
		this.holzlagerLaenge_m = holzlagerLaenge_m;
	}


	public HindernisKategorie getHindernisKategorie() {
		return hindernisKategorie;
	}


	public void setHindernisKategorie(HindernisKategorie hindernisKategorie) {
		this.hindernisKategorie = hindernisKategorie;
	}


	public NeigungsKategorie getNeigungsKategorie() {
		return neigungsKategorie;
	}


	public void setNeigungsKategorie(NeigungsKategorie neigungsKategorie) {
		this.neigungsKategorie = neigungsKategorie;
	}


	

	public enum Hackgutart {
		Vollbaeume,
		Kronen,
		Rundholzabschnitte;
		
		@Override
		public String toString() {
			switch(this) {
			case Vollbaeume:
				return "Vollbäume"; 
				
			case Kronen:
				return "Kronen"; 
				
			case Rundholzabschnitte:
				return "Rundholzabschnitte"; 
			
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	

	/**
	 * Volumen pro Hackzyklus
	 * 
	 * @param spz Stückzahl pro Kranzyklus
	 * @param dRPV Durchschnittliches Rohpoltervolumen
	 * @return vpz
	 */
	public double getVpz(double spz, double dRPV) {

		double[] vvVkVn = getVvVkVn();
		
		//Volumen Vollbaum des Massenmittelstammes
		double vv = vvVkVn[0];
		
		//Volumen Kronenmaterial des Massenmittelstammes
		double vk = vvVkVn[1];
		
		//Volumen eines mittleren Rundholzabschnittes
		double vn = vvVkVn[2];
		
		
		//Volumen pro Hackzyklus
		double vz;
		switch (getHackgutart()) {
		case Vollbaeume:
			vz = vv * spz;
			break;
			
		case Kronen:
			vz = vk * spz;
			break;
			
		case Rundholzabschnitte:
			vz = vn * spz;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		
		//Volumen pro Hackzyklus
		double vpz;
		if (dRPV < vz) {
			vpz = dRPV;
		}
		else {
			vpz = vz;
		}
		
		
		return vpz;
	}
	
	
	
	
	
	public double getHackgutmenge_m3() {
		
		double[] vvVkVn = getVvVkVn();

		//Volumen Vollbaum des Massenmittelstammes
		double vv = vvVkVn[0];

		//Volumen Kronenmaterial des Massenmittelstammes
		double vk = vvVkVn[1];
		
		//Volumen eines mittleren Rundholzabschnittes
		double vn = vvVkVn[2];
		
		
		
		double anz = getAnzahlBaeume();
		
		double nt = -1;
		switch (getHackgutart()) {
		case Vollbaeume:
			nt = vv * anz;
			break;
			
		case Kronen:
			nt = vk * anz;
			break;
			
		case Rundholzabschnitte:
			nt = vn * anz;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		
		return nt;
	}
	
	
	
	/**
	 * This setter should not be used in this class, since the value is calculated automatically
	 */
	@Override
	public void setHolzmenge_m3(double holzmenge) {
		throw new RuntimeException("This setter should not be used in this class, since the value is calculated automatically"); //$NON-NLS-1$
	}
	
	
	@Override
	public double getHolzmenge_m3() {
		return getHackgutmenge_m3();
	}

	
	
	
	
	
	/**
	 * double[0] = Vv
	 * double[1] = Vk
	 * double[2] = Vn
	 */
	private double[] getVvVkVn() {
		double dBhd_cm = getMittlererBhdAushieb_m() * 100.0;
		
		
		//Volumen Derbholz des Massenmittelstammes
		double vd = 0.0002 * Math.pow(dBhd_cm, 2.3897);
		
		//Volumen Reisig des Massenmittelstammes
		double vrNadel = 10.009 * Math.pow(dBhd_cm, -1.1549) * vd; //Um Performance zu steigern könnte Aufteilung Nadel/Laubholz bereits hier passieren?
		double vrLaub  = 27.519 * Math.pow(dBhd_cm, -1.3600) * vd;
		
		//Volumen Vollbaum des Massenmittelstammes
		double vvNadel = vd + vrNadel;
		double vvLaub = vd + vrLaub;

		
		
		//Parameter für Bestimmung Schaftform 'Km'
		double kmNadel = 0.00004 * Math.pow(dBhd_cm, 2) - 0.0215 * dBhd_cm - 0.4238; //Um Performance zu steigern könnte Aufteilung Nadel/Laubholz bereits hier passieren?
		double kmLaub  = 0.00006 * Math.pow(dBhd_cm, 2) - 0.0264 * dBhd_cm - 0.3887;
		
		//Durchschnittlicher Zopfdurchmesser des mittleren Rundholzabschnittes
		double dZD_cm = getMittlererZopfDrm_m() * 100.0;

		//Länge des mittleren Rundholzabschnittes bis zum Zopfdurchmesser (ALzd)
		double alzdNadel = ((dZD_cm - dBhd_cm) / kmNadel) + 1.3;
		double alzdLaub  = ((dZD_cm - dBhd_cm) / kmLaub ) + 1.3;
		
		//Mittendurchmesser des mittleren Rundholzabschnittes
//		double dMD_Nadel = kmNadel * ((alzdNadel / 2) - 1.3) + dBhd_cm;
//		double dMD_Laub  = kmLaub  * ((alzdLaub  / 2) - 1.3) + dBhd_cm;
		
		//Volumen eines mittleren Rundholzabschnittes
//		double vnNadel = alzdNadel * Math.PI * Math.pow(dMD_Nadel/200, 2);
//		double vnLaub  = alzdLaub  * Math.PI * Math.pow(dMD_Laub /200, 2);
		
		
		//Code aus X_Vvbt.vb Zeile 115ff:
		//===============================
		
//		'        adVolRHAbschnitt(eha) = adxALzd(eha) _
//		'* dxPI _
//		'* (adxdMD(eha) / 200) ^ 2
//		
//		'Geändert: 05.09.2003,ve _
//		'Volumenermittlung für Rundholzabschnitt mittels Kegelformel. _
//		'Diese Änderung wurde nötig, weil bei der bisherigen Berechnung _
//		'das Kronenvolumen z. T. für abnehmende Zopfdurchmesser zunahm _
//		'und das Rundholzvolumen ab.
//		'neu:
//		dGesamtLaenge_m = (0 - dBHD_cm / adxKm(eha)) + dxBRUSTHOEHE_m
//		dBasisDurchm_cm = dBHD_cm - adxKm(eha) * dxBRUSTHOEHE_m
//		dVolumenKegelGesamt = (dxPI / 3) * (dBasisDurchm_cm / 200) ^ 2 * dGesamtLaenge_m
//		dSpitzLaenge_m = dGesamtLaenge_m - adxALzd(eha)
//		dVolumenKegelSpitze = (dxPI / 3) * (dZopfDrm_cm / 200) ^ 2 * dSpitzLaenge_m
//		dVolumenKegelBasis = dVolumenKegelGesamt - dVolumenKegelSpitze
//		'ende
//		
//		adVolRHAbschnitt(eha) = dVolumenKegelBasis
		

		double dGesamtLaengeNadel_m = (0 - dBhd_cm / kmNadel) + 1.3;
		double dGesamtLaengeLaub_m  = (0 - dBhd_cm / kmLaub ) + 1.3;
		
		double dBasisDurchmNadel_cm = dBhd_cm - kmNadel * 1.3;
		double dBasisDurchmLaub_cm  = dBhd_cm - kmLaub  * 1.3;

		double dVolumenKegelGesamtNadel = (Math.PI / 3) * Math.pow(dBasisDurchmNadel_cm / 200,  2) * dGesamtLaengeNadel_m;
		double dVolumenKegelGesamtLaub  = (Math.PI / 3) * Math.pow(dBasisDurchmLaub_cm  / 200,  2) * dGesamtLaengeLaub_m;

		double dSpitzLaengeNadel_m = dGesamtLaengeNadel_m - alzdNadel;
		double dSpitzLaengeLaub_m  = dGesamtLaengeLaub_m - alzdLaub;

		double dVolumenKegelSpitzeNadel = (Math.PI / 3) * Math.pow(dZD_cm / 200, 2) * dSpitzLaengeNadel_m;
		double dVolumenKegelSpitzeLaub  = (Math.PI / 3) * Math.pow(dZD_cm / 200, 2) * dSpitzLaengeLaub_m;

		double dVolumenKegelBasisNadel = dVolumenKegelGesamtNadel - dVolumenKegelSpitzeNadel;
		double dVolumenKegelBasisLaub  = dVolumenKegelGesamtLaub  - dVolumenKegelSpitzeLaub;

		//Volumen eines mittleren Rundholzabschnittes
		double vnNadel = dVolumenKegelBasisNadel;
		double vnLaub = dVolumenKegelBasisLaub;
		
		
		
		
		//Volumen Kronenmaterial des Massenmittelstammes
		double vkNadel = vvNadel - vnNadel;
		double vkLaub  = vvLaub  - vnLaub;
		
		
		
		//Aufteilung Nadel/Laub
		double anteilLaub = getLaubholzAnteil_Prz() / 100.0;
		
		double vv = vvNadel * (1 - anteilLaub) + vvLaub * anteilLaub;
		double vk = vkNadel * (1 - anteilLaub) + vkLaub * anteilLaub;
		double vn = vnNadel * (1 - anteilLaub) + vnLaub * anteilLaub;
		
		
		return new double[]{
				vv,
				vk,
				vn
		};
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add("Laubholzanteil (%)",  laubholzAnteil_Prz);
		list.add("Anzahl Bäume",  anzahlBaeume);
		list.add("Hackgutart",  hackgutart);
		list.add("Mittlerer BHD Aushieb (m)",  mittlererBhdAushieb_m);
		list.add("Mittlerer Zopfdurchmesser (m)",  mittlererZopfDrm_m);
		
		list.add("Fahrstrecke auf Strasse (m)",  fahrstreckeStrasse_m);
		list.add("Fahrstrecke auf Feinerschliessung (m)",  fahrstreckeFeinerschliessung_m);
		
		list.add("Länge Holzlager (m)",  holzlagerLaenge_m);
		
		list.add("Hinderniskategorie",  hindernisKategorie);
		list.add("Neigungskategorie",  neigungsKategorie);
		
		return list;
	}
	
}
