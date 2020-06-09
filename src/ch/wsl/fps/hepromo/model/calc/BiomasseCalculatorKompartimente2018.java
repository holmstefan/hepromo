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

import java.util.HashMap;

import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorAnteilEnergieholz2018.Baumtyp;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorAnteilEnergieholz2018.Zopfklasse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseCalculatorKompartimente2018 {
	

	private static final HashMap<Standort, Koeffizienten> koeffizientenLaubAstderbholz;
	private static final HashMap<Standort, Koeffizienten> koeffizientenNadelReisig;
	
	static {
		koeffizientenLaubAstderbholz = new HashMap<Standort, Koeffizienten>();
//		koeffizientenAstderbholz.put(Standort.Laub_Jura_Hoehe601bis1250m,	new Koeffizienten(-4.8322966, 0.05631471, 0, 0, 1, 0));
//		koeffizientenAstderbholz.put(Standort.Laub_Jura_Hoehe1251bis3000m, 	new Koeffizienten(-4.8322966, 0.05631471, 0, 0, 0, 1));
//		koeffizientenAstderbholz.put(Standort.Laub_Jura_Hoehe0bis600m, 		new Koeffizienten(-4.8322966, 0.05631471, 0, 0, 0, 0));
		koeffizientenLaubAstderbholz.put(Standort.Laub_Jura_Hoehe0bis3000m, 			new Koeffizienten(-4.8322966, 0.05631471, 0, 0, 0, 0));
//		koeffizientenAstderbholz.put(Standort.Laub_Mittelland_Hoehe601bis1250m, 	new Koeffizienten(-5.9903924, 0.10188909, 0, 0, 1, 0));
//		koeffizientenAstderbholz.put(Standort.Laub_Mittelland_Hoehe1251bis3000m, 	new Koeffizienten(-5.9903924, 0.10188909, 0, 0, 0, 1));
//		koeffizientenAstderbholz.put(Standort.Laub_Mittelland_Hoehe0bis600m, 		new Koeffizienten(-5.9903924, 0.10188909, 0, 0, 0, 0));
		koeffizientenLaubAstderbholz.put(Standort.Laub_Mittelland_Hoehe0bis3000m, 		new Koeffizienten(-5.9903924, 0.10188909, 0, 0, 0, 0));
//		koeffizientenAstderbholz.put(Standort.Laub_VoralpenAlpen_Hoehe601bis1250m, 	new Koeffizienten(-4.9853383, 0.07394173, -0.7056977, 0, 0, 1));
		koeffizientenLaubAstderbholz.put(Standort.Laub_Voralpen_Hoehe0bis1250m, 	new Koeffizienten(-4.9853383, 0.07394173, -0.7056977, 0, 0, 0));
		koeffizientenLaubAstderbholz.put(Standort.Laub_Voralpen_Hoehe1251bis3000m, new Koeffizienten(-4.9853383, 0.07394173, -0.7056977, 0, 1, 0));
//		koeffizientenAstderbholz.put(Standort.Laub_VoralpenAlpen_Hoehe0bis600m, 	new Koeffizienten(-4.9853383, 0.07394173, -0.7056977, 0, 0, 0));
		koeffizientenLaubAstderbholz.put(Standort.Laub_AlpenSuedseite_Hoehe0bis1250m, 	new Koeffizienten(-4.9853383, 0.07394173, -0.7056977, 0, 0, 0));
		koeffizientenLaubAstderbholz.put(Standort.Laub_AlpenSuedseite_Hoehe1251bis3000m, new Koeffizienten(-4.9853383, 0.07394173, -0.7056977, 0, 1, 0));
	
		koeffizientenNadelReisig = new HashMap<Standort, Koeffizienten>();
		koeffizientenNadelReisig.put(Standort.Nadel_Alpen_Hoehe1000bis1500m,	 new Koeffizienten(-1.20641326, -0.01918645, 0, 0.44296676, 1, 0));
		koeffizientenNadelReisig.put(Standort.Nadel_Alpen_Hoehe1501bis3000m,  new Koeffizienten(-1.20641326, -0.01918645, 0, 0.44296676, 0, 1));
//		koeffizientenReisig.put(Standort.Nadel_Andere_Hoehe601bis1250m,  new Koeffizienten(-1.20641326, -0.01918645, 0, 0.44296676, 1, 0));
		koeffizientenNadelReisig.put(Standort.Nadel_Jura_Hoehe0bis1250m,  	 new Koeffizienten(-1.20641326, -0.01918645, 0, 0.44296676, 0, 0));
		koeffizientenNadelReisig.put(Standort.Nadel_Jura_Hoehe1251bis3000m,   new Koeffizienten(-1.20641326, -0.01918645, 0, 0.44296676, 0, 1));
//		koeffizientenReisig.put(Standort.Nadel_Andere_Hoehe0bis600m, 	 new Koeffizienten(-1.20641326, -0.01918645, 0, 0.44296676, 0, 0));
		koeffizientenNadelReisig.put(Standort.Nadel_Mittelland_Hoehe0bis1250m,		  koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Nadel_Mittelland_Hoehe1251bis3000m,  	  koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe1251bis3000m));
		koeffizientenNadelReisig.put(Standort.Nadel_Voralpen_Hoehe0bis1250m,  		  koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Nadel_Voralpen_Hoehe1251bis3000m,  	  koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe1251bis3000m));
		koeffizientenNadelReisig.put(Standort.Nadel_AlpenSuedseite_Hoehe0bis1250m,    koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Nadel_AlpenSuedseite_Hoehe1251bis3000m, koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe1251bis3000m));
		
		
		koeffizientenLaubAstderbholz.put(Standort.Allg_Alpen_Hoehe1000bis1400m,	 		koeffizientenLaubAstderbholz.get(Standort.Laub_Voralpen_Hoehe0bis1250m)); //Höhe Laub stimmt nicht genau mit Höhe Nadel überein, deshalb 1400m als Kompromiss
		koeffizientenLaubAstderbholz.put(Standort.Allg_Alpen_Hoehe1401bis3000m,  		koeffizientenLaubAstderbholz.get(Standort.Laub_Voralpen_Hoehe1251bis3000m)); //Höhe Laub stimmt nicht genau mit Höhe Nadel überein, deshalb 1400m als Kompromiss
		koeffizientenLaubAstderbholz.put(Standort.Allg_Jura_Hoehe0bis1250m,  	 		koeffizientenLaubAstderbholz.get(Standort.Laub_Jura_Hoehe0bis3000m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_Jura_Hoehe1251bis3000m,  	 	koeffizientenLaubAstderbholz.get(Standort.Laub_Jura_Hoehe0bis3000m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_Mittelland_Hoehe0bis1250m,  		koeffizientenLaubAstderbholz.get(Standort.Laub_Mittelland_Hoehe0bis3000m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_Mittelland_Hoehe1251bis3000m,  	koeffizientenLaubAstderbholz.get(Standort.Laub_Mittelland_Hoehe0bis3000m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_Voralpen_Hoehe0bis1250m,  		koeffizientenLaubAstderbholz.get(Standort.Laub_Voralpen_Hoehe0bis1250m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_Voralpen_Hoehe1251bis3000m,		koeffizientenLaubAstderbholz.get(Standort.Laub_Voralpen_Hoehe1251bis3000m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_AlpenSuedseite_Hoehe0bis1250m,  	koeffizientenLaubAstderbholz.get(Standort.Laub_AlpenSuedseite_Hoehe0bis1250m));
		koeffizientenLaubAstderbholz.put(Standort.Allg_AlpenSuedseite_Hoehe1251bis3000m,koeffizientenLaubAstderbholz.get(Standort.Laub_AlpenSuedseite_Hoehe1251bis3000m));
		
		
		koeffizientenNadelReisig.put(Standort.Allg_Alpen_Hoehe1000bis1400m,	 		koeffizientenNadelReisig.get(Standort.Nadel_Alpen_Hoehe1000bis1500m)); //Höhe Laub stimmt nicht genau mit Höhe Nadel überein, deshalb 1400m als Kompromiss
		koeffizientenNadelReisig.put(Standort.Allg_Alpen_Hoehe1401bis3000m,  		koeffizientenNadelReisig.get(Standort.Nadel_Alpen_Hoehe1501bis3000m)); //Höhe Laub stimmt nicht genau mit Höhe Nadel überein, deshalb 1400m als Kompromiss
		koeffizientenNadelReisig.put(Standort.Allg_Jura_Hoehe0bis1250m,  	 		koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Allg_Jura_Hoehe1251bis3000m,  	 	koeffizientenNadelReisig.get(Standort.Nadel_Jura_Hoehe1251bis3000m));
		koeffizientenNadelReisig.put(Standort.Allg_Mittelland_Hoehe0bis1250m,  		koeffizientenNadelReisig.get(Standort.Nadel_Mittelland_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Allg_Mittelland_Hoehe1251bis3000m,  	koeffizientenNadelReisig.get(Standort.Nadel_Mittelland_Hoehe1251bis3000m));
		koeffizientenNadelReisig.put(Standort.Allg_Voralpen_Hoehe0bis1250m,  		koeffizientenNadelReisig.get(Standort.Nadel_Voralpen_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Allg_Voralpen_Hoehe1251bis3000m,		koeffizientenNadelReisig.get(Standort.Nadel_Voralpen_Hoehe1251bis3000m));
		koeffizientenNadelReisig.put(Standort.Allg_AlpenSuedseite_Hoehe0bis1250m,  	koeffizientenNadelReisig.get(Standort.Nadel_AlpenSuedseite_Hoehe0bis1250m));
		koeffizientenNadelReisig.put(Standort.Allg_AlpenSuedseite_Hoehe1251bis3000m,koeffizientenNadelReisig.get(Standort.Nadel_AlpenSuedseite_Hoehe1251bis3000m));
	}
	
	
	
	/**
	 * 
	 * @param vshEinzelnerBaum Volumen Schaftholz in Rinde von einem einzelnen Baum
	 * @param lh_Prz Anteil Laubholz (in Prozent)
	 * @param standort
	 * @param zopf_cm Zopfklasse
	 * @param bhd_cm
	 * @param ae_Prz Anteil des Rundholzes, der als Energieholz verwendet wird (in Prozent)
	 * @param aad_Prz Anteil des Astderbholzes, der als Energieholz verwendet wird (in Prozent)
	 * @param ar_Prz Anteil des Reisig, der als Energieholz verwendet wird (in Prozent)
	 * @param evdh_Nadel_Prz Ernteverlust beim Nadel-Derbholz (in Prozent)
	 * @param evdh_Laub_Prz Ernteverlust beim Laub-Derbholz (in Prozent)
	 * @param evndh_Nadel_Prz Ernteverlust beim Nadel-Nicht-Derbholz (in Prozent)
	 */
	public static ErgebnisEnergieholz calc(double vshEinzelnerBaum, int lh_Prz, Standort standort, Zopfklasse zopf_cm, int bhd_cm, int ae_Prz, int aad_Prz, int ar_Prz, int evdh_Nadel_Prz, int evdh_Laub_Prz, int evndh_Nadel_Prz, double anzahlStaemme) {
		double nh = (100.0 - lh_Prz) / 100d;
		double lh = lh_Prz / 100d;

		ErgebnisEnergieholz ergebnis_Nh = calc(vshEinzelnerBaum, Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, standort, zopf_cm, bhd_cm, ae_Prz, aad_Prz, ar_Prz, evdh_Nadel_Prz, evndh_Nadel_Prz, anzahlStaemme);
		ErgebnisEnergieholz ergebnis_Lh = calc(vshEinzelnerBaum, Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, standort, zopf_cm, bhd_cm, ae_Prz, aad_Prz, ar_Prz, evdh_Laub_Prz, 0, anzahlStaemme);
		
		ErgebnisEnergieholz ergebnis = new ErgebnisEnergieholz();
		ergebnis.anteilEnergieholzAmSchaftholz_Prz = nh * ergebnis_Nh.anteilEnergieholzAmSchaftholz_Prz + lh * ergebnis_Lh.anteilEnergieholzAmSchaftholz_Prz;
		ergebnis.volumenEnergieholzAmSchaftOberhalbZopf_m3iR = nh * ergebnis_Nh.volumenEnergieholzAmSchaftOberhalbZopf_m3iR + lh * ergebnis_Lh.volumenEnergieholzAmSchaftOberhalbZopf_m3iR;
		ergebnis.volumenEnergierundholzUnterhalbZopf_m3iR = nh * ergebnis_Nh.volumenEnergierundholzUnterhalbZopf_m3iR + lh * ergebnis_Lh.volumenEnergierundholzUnterhalbZopf_m3iR;
		ergebnis.volumenAstderbholz_m3iR = nh * ergebnis_Nh.volumenAstderbholz_m3iR + lh * ergebnis_Lh.volumenAstderbholz_m3iR;
		ergebnis.volumenReisig_m3iR = nh * ergebnis_Nh.volumenReisig_m3iR + lh * ergebnis_Lh.volumenReisig_m3iR;
		ergebnis.volumenEnergieholz_m3iR = nh * ergebnis_Nh.volumenEnergieholz_m3iR + lh * ergebnis_Lh.volumenEnergieholz_m3iR;
		
		return ergebnis;
	}
	
	
	
	/**
	 * 
	 * @param vshEinzelnerBaum Volumen Schaftholz in Rinde von einem einzelnen Baum
	 * @param baumtyp
	 * @param standort
	 * @param zopf_cm Zopfklasse
	 * @param bhd_cm
	 * @param ae_Prz Anteil des Rundholzes, der als Energieholz verwendet wird (in Prozent)
	 * @param aad_Prz Anteil des Astderbholzes, der als Energieholz verwendet wird (in Prozent)
	 * @param ar_Prz Anteil des Reisig, der als Energieholz verwendet wird (in Prozent)
	 * @param evdh_Prz Ernteverlust beim Derbholz (in Prozent)
	 * @param evndh_Prz Ernteverlust beim Nicht-Derbholz (in Prozent)
	 */
	private static ErgebnisEnergieholz calc(double vshEinzelnerBaum, Baumtyp baumtyp, Standort standort, Zopfklasse zopf_cm, int bhd_cm, int ae_Prz, int aad_Prz, int ar_Prz, int evdh_Prz, int evndh_Prz, double anzahlStaemme) {
		double vsh = vshEinzelnerBaum * anzahlStaemme;
		double ae = ae_Prz / 100.0;
		double aad = aad_Prz / 100.0;
		double ar = ar_Prz / 100.0;
		double evdh = evdh_Prz / 100.0;
		double evndh = evndh_Prz / 100.0;

		boolean isNadelholz = baumtyp == Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe;
		double logit = getLogit(standort, bhd_cm, isNadelholz);
		
		/** Verhältnis Astderbholz zu Schaftholz **/
//		double verhaeltnisAstderbholzZuSchaftholz = Math.pow(1 - 0.062 * Math.exp(-bhd_cm), bhd_cm); //Vereinfachung
		double verhaeltnisAstderbholzZuSchaftholz = isNadelholz ? 0 : Math.exp(logit) / (1.0 + Math.exp(logit));
		
		/** Verhältnis Reisig zu Schaftholz **/
//		double verhaeltnisReisigZuSchaftholz = 0.286 - 0.00239 * bhd_cm; //Vereinfachung
		double verhaeltnisReisigZuSchaftholz = isNadelholz ? Math.exp(logit) / (1.0 + Math.exp(logit)) : 0;
		
				
		/** Volumen Ast-Derbholz in Rinde **/
		double vadh = verhaeltnisAstderbholzZuSchaftholz * vsh;
		
		/** Volumen Reisig in Rinde **/
		double vr = verhaeltnisReisigZuSchaftholz * vsh;
		
		/** Volumen Stock in Rinde **/
		double vStock = 0.03 * vsh;

		
		/** Beitrag des Schaftholzes zum Energieholz (oberhalb Zopf) **/
		double volumenEnergieholzAmSchaftOberhalbZopf = BiomasseCalculatorAnteilEnergieholz2018.calculateAnteilEnergieholz(baumtyp, zopf_cm, bhd_cm) * vsh * (1.0 - evdh);
		
		/** Beitrag des Schaftholzes zum Energierundholz (unterhalb Zopf) **/
		double volumenEnergieRundholzUnterhalbZopf = ae * (vsh - (volumenEnergieholzAmSchaftOberhalbZopf / (1.0 - evdh)) - vStock) * (1.0 - evdh);
		
		/** Beitrag des Ast-Derbholzes zum Energieholz **/
		double volumenEnergieAstderbholz = aad * vadh * (1.0 - evdh);
		
		/** Beitrag des Reisig zum Energieholz **/
		double volumenEnergieReisig = ar * vr * (1.0 - evndh);
		
		
		/** Volumen Energieholz in Rinde **/
		double veh = volumenEnergieholzAmSchaftOberhalbZopf + volumenEnergieRundholzUnterhalbZopf + volumenEnergieAstderbholz + volumenEnergieReisig;

		
		ErgebnisEnergieholz ergebnis = new ErgebnisEnergieholz();
		ergebnis.anteilEnergieholzAmSchaftholz_Prz = 100 * BiomasseCalculatorAnteilEnergieholz2018.calculateAnteilEnergieholz(baumtyp, zopf_cm, bhd_cm);
		ergebnis.volumenEnergieholzAmSchaftOberhalbZopf_m3iR = volumenEnergieholzAmSchaftOberhalbZopf;
		ergebnis.volumenEnergierundholzUnterhalbZopf_m3iR = Math.max(0, volumenEnergieRundholzUnterhalbZopf);
		ergebnis.volumenAstderbholz_m3iR = volumenEnergieAstderbholz;
		ergebnis.volumenReisig_m3iR = volumenEnergieReisig;
		ergebnis.volumenEnergieholz_m3iR = veh;
		
		return ergebnis;
	}
	
	
	
	public static ErgebnisBiomasse calc(double vshEinzelnerBaum, NadelLaub baumart, Standort standort, int bhd_cm, int anzahlStaemme) {
		double vsh = vshEinzelnerBaum * anzahlStaemme;

		boolean isNadelholz = baumart == NadelLaub.Nadelholz;
		double logit = getLogit(standort, bhd_cm, isNadelholz);
		
		/** Verhältnis Astderbholz zu Schaftholz **/
//		double verhaeltnisAstderbholzZuSchaftholz = Math.pow(1 - 0.062 * Math.exp(-bhd_cm), bhd_cm); //Vereinfachung
		double verhaeltnisAstderbholzZuSchaftholz = isNadelholz ? 0 : Math.exp(logit) / (1.0 + Math.exp(logit));
		
		/** Verhältnis Reisig zu Schaftholz **/
//		double verhaeltnisReisigZuSchaftholz = 0.286 - 0.00239 * bhd_cm; //Vereinfachung
		double verhaeltnisReisigZuSchaftholz = isNadelholz ? Math.exp(logit) / (1.0 + Math.exp(logit)) : 0;
		
				
		/** Volumen Ast-Derbholz in Rinde **/
		double vadh = verhaeltnisAstderbholzZuSchaftholz * vsh;
		
		/** Volumen Reisig in Rinde **/
		double vr = verhaeltnisReisigZuSchaftholz * vsh;
		
		/** Volumen Biomasse **/
		double vBiom = vsh + vadh + vr;
		
		
		double masseNadelnUndBlaetterProBaum_kg = -1;
		if (isNadelholz) {
			double b0 = 1.413701;
			double b1 = 0.024182;
			double b2 = -0.000001067;
			masseNadelnUndBlaetterProBaum_kg = b0 + b1 * Math.pow(bhd_cm, 2) + b2 * Math.pow(bhd_cm, 4);
		}
		else {
			double b0 = 0.372238;
			double b1 = 0.006653;
			double b2 = 0.000000978;
			masseNadelnUndBlaetterProBaum_kg = b0 + b1 * Math.pow(bhd_cm, 2) + b2 * Math.pow(bhd_cm, 4);
		}
		
		
		ErgebnisBiomasse ergebnis = new ErgebnisBiomasse();
		ergebnis.verhaeltnisAstderbholzZuSchaftholz_Prz = 100 * verhaeltnisAstderbholzZuSchaftholz;
		ergebnis.verhaeltnisReisigZuSchaftholz_Prz = 100 * verhaeltnisReisigZuSchaftholz;	
		ergebnis.volumenSchaftholz_m3iR = vsh;
		ergebnis.volumenAstderbholz_m3iR = vadh;
		ergebnis.volumenReisig_m3iR = vr;
		ergebnis.volumenBiomasseOhneNadelnUndBlaetter = vBiom;
		ergebnis.masseNadelnUndBlaetter_kg = masseNadelnUndBlaetterProBaum_kg * anzahlStaemme;
		
		return ergebnis; 
	}
	
	
	
	private static double getLogit(Standort region, int bhd_cm, boolean isNadelholz) {
		Koeffizienten k = isNadelholz ? koeffizientenNadelReisig.get(region) : koeffizientenLaubAstderbholz.get(region);

		double result = 
				k.b0 + 
				k.b1 * bhd_cm + 
				k.b2 * k.h2 + 
				k.b3 * k.h3;
		
		return result;
	}
	
	
	public static class ErgebnisEnergieholz {
		double anteilEnergieholzAmSchaftholz_Prz;
		double volumenEnergieholzAmSchaftOberhalbZopf_m3iR;
		double volumenEnergierundholzUnterhalbZopf_m3iR;
		double volumenAstderbholz_m3iR;
		double volumenReisig_m3iR;
		double volumenEnergieholz_m3iR;

		
//		public double getAnteilEnergieholzAmSchaftholz_Prz() {  //FIXME: wird nicht gebraucht?
//			return anteilEnergieholzAmSchaftholz_Prz;
//		}

		public double getVolumenEnergieholzAmSchaftOberhalbZopf_m3iR() {
			return volumenEnergieholzAmSchaftOberhalbZopf_m3iR;
		}

		public double getVolumenEnergierundholzUnterhalbZopf_m3iR() {
			return volumenEnergierundholzUnterhalbZopf_m3iR;
		}

		public double getVolumenAstderbholz_m3iR() {
			return volumenAstderbholz_m3iR;
		}

		public double getVolumenReisig_m3iR() {
			return volumenReisig_m3iR;
		}

		public double getVolumenEnergieholz_m3iR() {
			return volumenEnergieholz_m3iR;
		}
	}
	
	
	public static class ErgebnisBiomasse {
		double verhaeltnisAstderbholzZuSchaftholz_Prz;
		double verhaeltnisReisigZuSchaftholz_Prz;	
		double volumenSchaftholz_m3iR;
		double volumenAstderbholz_m3iR;
		double volumenReisig_m3iR;
		double volumenBiomasseOhneNadelnUndBlaetter;
		double masseNadelnUndBlaetter_kg;

		public double getVerhaeltnisAstderbholzZuSchaftholz_Prz() {
			return verhaeltnisAstderbholzZuSchaftholz_Prz;
		}

		public double getVerhaeltnisReisigZuSchaftholz_Prz() {
			return verhaeltnisReisigZuSchaftholz_Prz;
		}

		public double getVolumenSchaftholz_m3iR() {
			return volumenSchaftholz_m3iR;
		}

		public double getVolumenAstderbholz_m3iR() {
			return volumenAstderbholz_m3iR;
		}

		public double getVolumenReisig_m3iR() {
			return volumenReisig_m3iR;
		}

		public double getVolumenBiomasseOhneNadelnUndBlaetter() {
			return volumenBiomasseOhneNadelnUndBlaetter;
		}

		public double getMasseNadelnUndBlaetter_kg() {
			return masseNadelnUndBlaetter_kg;
		}
	}
	
	
	public static enum Standort {
		
		Allg_Alpen_Hoehe1000bis1400m(1000, 1200, 1400),
		Allg_Alpen_Hoehe1401bis3000m(1401, 1600, 3000),
		
		Allg_Jura_Hoehe0bis1250m(0, 800, 1250),
		Allg_Jura_Hoehe1251bis3000m(1251, 1500, 3000),

		Allg_Mittelland_Hoehe0bis1250m(0, 800, 1250),
		Allg_Mittelland_Hoehe1251bis3000m(1251, 1500, 3000),

		Allg_Voralpen_Hoehe0bis1250m(0, 800, 1250),
		Allg_Voralpen_Hoehe1251bis3000m(1251, 1500, 3000),

		Allg_AlpenSuedseite_Hoehe0bis1250m(0, 800, 1250),
		Allg_AlpenSuedseite_Hoehe1251bis3000m(1251, 1500, 3000),
		
		
		
		
//		Laub_Jura_Hoehe0bis600m,
//		Laub_Jura_Hoehe601bis1250m,
//		Laub_Jura_Hoehe1251bis3000m,
		Laub_Jura_Hoehe0bis3000m(0, 800, 3000),

//		Laub_Mittelland_Hoehe0bis600m,
//		Laub_Mittelland_Hoehe601bis1250m,
//		Laub_Mittelland_Hoehe1251bis3000m,
		Laub_Mittelland_Hoehe0bis3000m(0, 800, 3000),

//		Laub_VoralpenAlpen_Hoehe0bis600m,
//		Laub_VoralpenAlpen_Hoehe601bis1250m,
		Laub_Voralpen_Hoehe0bis1250m(0, 1000, 1250),
		Laub_Voralpen_Hoehe1251bis3000m(1251, 1500, 3000),
		
		Laub_AlpenSuedseite_Hoehe0bis1250m(0, 1000, 1250),
		Laub_AlpenSuedseite_Hoehe1251bis3000m(1251, 1500, 3000),

		Nadel_Alpen_Hoehe1000bis1500m(1000, 1200, 1500),
		Nadel_Alpen_Hoehe1501bis3000m(1501, 1800, 3000),
		
//		Nadel_Andere_Hoehe0bis600m,
//		Nadel_Andere_Hoehe601bis1250m,
		Nadel_Jura_Hoehe0bis1250m(0, 800, 1250),
		Nadel_Jura_Hoehe1251bis3000m(1251, 1500, 3000),

		Nadel_Mittelland_Hoehe0bis1250m(0, 800, 1250),
		Nadel_Mittelland_Hoehe1251bis3000m(1251, 1500, 3000),

		Nadel_Voralpen_Hoehe0bis1250m(0, 800, 1250),
		Nadel_Voralpen_Hoehe1251bis3000m(1251, 1500, 3000),

		Nadel_AlpenSuedseite_Hoehe0bis1250m(0, 800, 1250),
		Nadel_AlpenSuedseite_Hoehe1251bis3000m(1251, 1500, 3000);
		
		public final int minHoehe;
		public final int defaultHoehe;
		public final int maxHoehe;
		
		private Standort(int minHoehe, int defaultHoehe, int maxHoehe) {
			this.minHoehe = minHoehe;
			this.defaultHoehe = defaultHoehe;
			this.maxHoehe = maxHoehe;
		}
		
		public static Standort[] getAllRelevantForAstderbholzLaub() {
			return new Standort[] {
//					Laub_Jura_Hoehe0bis600m,
//					Laub_Jura_Hoehe601bis1250m,
//					Laub_Jura_Hoehe1251bis3000m,
					Laub_Jura_Hoehe0bis3000m,
//					Laub_Mittelland_Hoehe0bis600m,
//					Laub_Mittelland_Hoehe601bis1250m,
//					Laub_Mittelland_Hoehe1251bis3000m,
					Laub_Mittelland_Hoehe0bis3000m,
//					Laub_VoralpenAlpen_Hoehe0bis600m,
//					Laub_VoralpenAlpen_Hoehe601bis1250m,
					Laub_Voralpen_Hoehe0bis1250m,
					Laub_Voralpen_Hoehe1251bis3000m,
					Laub_AlpenSuedseite_Hoehe0bis1250m,
					Laub_AlpenSuedseite_Hoehe1251bis3000m
			};
		}
		
		public static Standort[] getAllRelevantForReisigNadel() {
			return new Standort[] {
					Nadel_Alpen_Hoehe1000bis1500m,
					Nadel_Alpen_Hoehe1501bis3000m,
//					Nadel_Andere_Hoehe0bis600m,
//					Nadel_Andere_Hoehe601bis1250m,
					Nadel_Jura_Hoehe0bis1250m,
					Nadel_Jura_Hoehe1251bis3000m,
					Nadel_Mittelland_Hoehe0bis1250m,
					Nadel_Mittelland_Hoehe1251bis3000m,
					Nadel_Voralpen_Hoehe0bis1250m,
					Nadel_Voralpen_Hoehe1251bis3000m,
					Nadel_AlpenSuedseite_Hoehe0bis1250m,
					Nadel_AlpenSuedseite_Hoehe1251bis3000m
			};
		}
		
		public static Standort[] getAllNadelLaubGemischt() {
			return new Standort[] {
					Allg_Alpen_Hoehe1000bis1400m,
					Allg_Alpen_Hoehe1401bis3000m,
					Allg_Jura_Hoehe0bis1250m,
					Allg_Jura_Hoehe1251bis3000m,
					Allg_Mittelland_Hoehe0bis1250m,
					Allg_Mittelland_Hoehe1251bis3000m,
					Allg_Voralpen_Hoehe0bis1250m,
					Allg_Voralpen_Hoehe1251bis3000m,
					Allg_AlpenSuedseite_Hoehe0bis1250m,
					Allg_AlpenSuedseite_Hoehe1251bis3000m
			};
		}
		
		@Override
		public String toString() {  //FIXME: min-/max nicht aus properties-file auslesen, sondern generisch lösen!
			switch(this) {
			case Allg_Alpen_Hoehe1000bis1400m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Alpen1000bis1400"); //$NON-NLS-1$

			case Allg_Alpen_Hoehe1401bis3000m:		
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Alpen1401bis3000"); //$NON-NLS-1$

				

			case Nadel_Alpen_Hoehe1000bis1500m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Alpen1000bis1500"); //$NON-NLS-1$

			case Nadel_Alpen_Hoehe1501bis3000m:		
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Alpen1501bis3000"); //$NON-NLS-1$

				

			case Allg_Jura_Hoehe0bis1250m:
			case Nadel_Jura_Hoehe0bis1250m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Jura0bis1250"); //$NON-NLS-1$

			case Allg_Jura_Hoehe1251bis3000m:
			case Nadel_Jura_Hoehe1251bis3000m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Jura1251bis3000"); //$NON-NLS-1$
				
			case Laub_Jura_Hoehe0bis3000m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Jura0bis3000"); //$NON-NLS-1$

				

			case Allg_Mittelland_Hoehe0bis1250m:
			case Nadel_Mittelland_Hoehe0bis1250m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Mittelland0bis1250"); //$NON-NLS-1$

			case Allg_Mittelland_Hoehe1251bis3000m:
			case Nadel_Mittelland_Hoehe1251bis3000m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Mittelland1251bis3000"); //$NON-NLS-1$
				
			case Laub_Mittelland_Hoehe0bis3000m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Mittelland0bis3000");			 //$NON-NLS-1$
				
				

			case Allg_Voralpen_Hoehe0bis1250m:
			case Nadel_Voralpen_Hoehe0bis1250m:
			case Laub_Voralpen_Hoehe0bis1250m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Voralpen0bis1250"); //$NON-NLS-1$

			case Allg_Voralpen_Hoehe1251bis3000m:
			case Nadel_Voralpen_Hoehe1251bis3000m:
			case Laub_Voralpen_Hoehe1251bis3000m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.Voralpen1251bis3000");	 //$NON-NLS-1$

				

			case Allg_AlpenSuedseite_Hoehe0bis1250m:
			case Nadel_AlpenSuedseite_Hoehe0bis1250m:
			case Laub_AlpenSuedseite_Hoehe0bis1250m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.AlpenSuedseite0bis1250"); //$NON-NLS-1$

			case Allg_AlpenSuedseite_Hoehe1251bis3000m:
			case Nadel_AlpenSuedseite_Hoehe1251bis3000m:
			case Laub_AlpenSuedseite_Hoehe1251bis3000m:
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.AlpenSuedseite1251bis3000");	 //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());		
			}
		}
	}
	
	
	public static enum NadelLaub {
		Nadelholz,
		Laubholz;
		
		@Override
		public String toString() {
			if (this == Nadelholz) {
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.enumNadelholz"); //$NON-NLS-1$
			}
			else if (this == Laubholz) {
				return ModelStrings.getString("BiomasseCalculatorKompartimente2018.enumLaubholz"); //$NON-NLS-1$
			}
			else {
				throw new IllegalStateException();
			}
		}
	}
	
	
	private static class Koeffizienten {
		private final double b0;
		private final double b1;
		private final double b2;
		private final double b3;
		private final double h2;
		private final double h3;
		
		private Koeffizienten(double b0, double b1, double b2, double b3, double h2, double h3) {
			this.b0 = b0;
			this.b1 = b1;
			this.b2 = b2;
			this.b3 = b3;
			this.h2 = h2;
			this.h3 = h3;
		}
	}

}
