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
package ch.wsl.fps.hepromo.api;

import java.util.Random;

/**
 * 
 * @author Stefan Holm
 *
 */
class ApiTester {
	
	public static void main(String[] args) {
		testSimpleFaellenVorruecken2018();
		testSimpleSchlepper1992();
		testSimpleMotormanuell2014();
		testSimpleRadharvester2014();
		testSimpleSchlepper2014();
		testSimpleKombiseilgeraet2018();
		testSimpleHacker2018();
		testSimpleHelikopter2003();
		testSimpleFaellenMotormanuell1978();
		testSimpleKonventionellerSeilkran1999();
		testSimpleMobilseilkran1999();
		testSimpleHackschnitzeltransport2018();
		testSimpleForwarder1997();
		testSimpleForwarderRundholz2018();
		testSimpleForwarderEnergieholz2018();
		testPerformance();
	}

	private static void testSimpleForwarderRundholz2018() {
		System.out.println("SimpleForwarderRundholz2018");
		SimpleForwarderRundholz2018 modelForwarder18 = new SimpleForwarderRundholz2018();
		
		System.out.println(modelForwarder18.getZeitMaschinist_WPPH());
		System.out.println(modelForwarder18.getZeitForwarder_PMH15());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelForwarder18.setRundholzmenge_m3iR(1000);
//		modelForwarder18.setEnergieholzmenge_m3(500);
		System.out.println(modelForwarder18.getKostenTotal_total());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelForwarder18.setMittlererBhd_cm(23);
//		modelForwarder18.setEnergieholzanfall_m3ProHa(23);
		System.out.println(modelForwarder18.getKostenTotal_total());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleForwarderEnergieholz2018() {
		System.out.println("SimpleForwarderEnergieholz2018");
		SimpleForwarderEnergieholz2018 modelForwarder18 = new SimpleForwarderEnergieholz2018();
		
		System.out.println(modelForwarder18.getZeitMaschinist_WPPH());
		System.out.println(modelForwarder18.getZeitForwarder_PMH15());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelForwarder18.setEnergieholzmenge_m3iR(1000);
//		modelForwarder18.setEnergieholzmenge_m3(500);
		System.out.println(modelForwarder18.getKostenTotal_total());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelForwarder18.setMittlererBhd_cm(23);
//		modelForwarder18.setEnergieholzanfall_m3ProHa(23);
		System.out.println(modelForwarder18.getKostenTotal_total());
		System.out.println(modelForwarder18.getProduktivitaet_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleForwarder1997() {
		System.out.println("SimpleForwarder1997");
		SimpleForwarder1997 modelForwarder97 = new SimpleForwarder1997();
		
		System.out.println(modelForwarder97.getZeitMaschinist_WPPH());
		System.out.println(modelForwarder97.getZeitForwarder_PMH15());
		System.out.println(modelForwarder97.getKostenTotal_proM3iR());
		System.out.println(modelForwarder97.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelForwarder97.setHolzmenge_m3iR(1000);
		System.out.println(modelForwarder97.getKostenTotal_proM3iR());
		System.out.println(modelForwarder97.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelForwarder97.setLaubholzanteil_Prz(23);
		System.out.println(modelForwarder97.getKostenTotal_proM3iR());
		System.out.println(modelForwarder97.getProduktivitaet_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleHackschnitzeltransport2018() {
		System.out.println("SimpleHackschnitzeltransport2018");
		SimpleHackschnitzeltransport2018 modelTransport18 = new SimpleHackschnitzeltransport2018();
		
		System.out.println(modelTransport18.getZeitTransportfahrzeugInklFahrer_PMH15());
		System.out.println(modelTransport18.getKostenTotal_total());
		System.out.println(modelTransport18.getKostenTotal_proSrm());
		System.out.println();

		modelTransport18.setHackschnitzelmenge_Srm(1000);
		System.out.println(modelTransport18.getKostenTotal_total());
		System.out.println(modelTransport18.getKostenTotal_proSrm());
		System.out.println();

		modelTransport18.setFahrstreckeInnerortsUndAusserorts_km(23);
		System.out.println(modelTransport18.getKostenTotal_total());
		System.out.println(modelTransport18.getKostenTotal_proSrm());
		System.out.println();
	}

	private static void testSimpleMobilseilkran1999() {
		System.out.println("SimpleMobilseilkran1999");
		SimpleMobilseilkran1999 modelMobSeilkran = new SimpleMobilseilkran1999();
		
		System.out.println(modelMobSeilkran.getZeitPersonal_WPPH());
		System.out.println(modelMobSeilkran.getZeitMobilseilkran_PMH15());
		System.out.println(modelMobSeilkran.getZeitKranfahrzeug_PMH15());
		System.out.println(modelMobSeilkran.getKostenTotal_proM3oR());
		System.out.println(modelMobSeilkran.getProduktivitaetBeimSeilen_m3iRproPMH15());
		System.out.println();

		modelMobSeilkran.setHolzmenge_m3iR(1000);
		System.out.println(modelMobSeilkran.getKostenTotal_proM3oR());
		System.out.println(modelMobSeilkran.getProduktivitaetBeimSeilen_m3iRproPMH15());
		System.out.println();

		modelMobSeilkran.setMittlererStueckinhalt_m3iR(0.45);
		System.out.println(modelMobSeilkran.getKostenTotal_proM3oR());
		System.out.println(modelMobSeilkran.getProduktivitaetBeimSeilen_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleKonventionellerSeilkran1999() {
		System.out.println("SimpleKonventionellerSeilkran1999");
		SimpleKonventionellerSeilkran1999 modelKonvSeilkran = new SimpleKonventionellerSeilkran1999();
		
		System.out.println(modelKonvSeilkran.getZeitPersonal_WPPH());
		System.out.println(modelKonvSeilkran.getZeitSeilkrananlage_PMH15());
		System.out.println(modelKonvSeilkran.getZeitKranfahrzeug_PMH15());
		System.out.println(modelKonvSeilkran.getKostenTotal_proM3oR());
		System.out.println(modelKonvSeilkran.getProduktivitaetBeimSeilen_m3iRproPMH15());
		System.out.println();

		modelKonvSeilkran.setHolzmenge_m3iR(1000);
		System.out.println(modelKonvSeilkran.getKostenTotal_proM3oR());
		System.out.println(modelKonvSeilkran.getProduktivitaetBeimSeilen_m3iRproPMH15());
		System.out.println();

		modelKonvSeilkran.setMittlererStueckinhalt_m3iR(0.45);
		System.out.println(modelKonvSeilkran.getKostenTotal_proM3oR());
		System.out.println(modelKonvSeilkran.getProduktivitaetBeimSeilen_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleFaellenMotormanuell1978() {
		System.out.println("SimpleFaellenMotormanuell1978");
		SimpleFaellenMotormanuell1978 modelFaellenMoMa78 = new SimpleFaellenMotormanuell1978();
		
		System.out.println(modelFaellenMoMa78.getZeitPersonal_WPPH());
		System.out.println(modelFaellenMoMa78.getZeitMotorsaege_PMH15());
		System.out.println(modelFaellenMoMa78.getKostenTotal_proM3());
		System.out.println(modelFaellenMoMa78.getProduktivitaet_m3proPSH15());
		System.out.println();

		modelFaellenMoMa78.setHolzmenge_m3(1000);
		System.out.println(modelFaellenMoMa78.getKostenTotal_proM3());
		System.out.println(modelFaellenMoMa78.getProduktivitaet_m3proPSH15());
		System.out.println();

		modelFaellenMoMa78.setMassenmittelstamm_m3(0.45);
		System.out.println(modelFaellenMoMa78.getKostenTotal_proM3());
		System.out.println(modelFaellenMoMa78.getProduktivitaet_m3proPSH15());
		System.out.println();
	}

	private static void testSimpleHelikopter2003() {
		System.out.println("SimpleHelikopter2003");
		SimpleHelikopter2003 modelHeli = new SimpleHelikopter2003();
		
		System.out.println(modelHeli.getZeitHelikopter_PMH15());
		System.out.println(modelHeli.getZeitPersonalForstbetrieb_WPPH());
		System.out.println(modelHeli.getZeitMotorsaege_PMH15());
		System.out.println(modelHeli.getZeitKranfahrzeug_PMH15());
		System.out.println(modelHeli.getKostenTotal_proM3oR());
		System.out.println(modelHeli.getKostenKranfahrzeug_proM3oR());
		System.out.println();

		modelHeli.setHolzmenge_m3iR(1000);
		System.out.println(modelHeli.getKostenTotal_proM3oR());
		System.out.println(modelHeli.getKostenKranfahrzeug_proM3oR());
		System.out.println();

		modelHeli.setArbeitsverfahren_Kategorie(2);
		System.out.println(modelHeli.getKostenTotal_proM3oR());
		System.out.println(modelHeli.getKostenKranfahrzeug_proM3oR());
		System.out.println();
	}

	private static void testSimpleHacker2018() {
		System.out.println("SimpleHacker2018");
		SimpleHacker2018 modelHacker18 = new SimpleHacker2018();
		
		System.out.println(modelHacker18.getZeitMaschinist_WPPH());
		System.out.println(modelHacker18.getZeitHacker_PMH15());
		System.out.println(modelHacker18.getKostenTotal_total());
		System.out.println(modelHacker18.getProduktivitaet_SrmProPMH15());
		System.out.println();

		modelHacker18.setHackschnitzelmenge_Srm(1000);
		System.out.println(modelHacker18.getKostenTotal_total());
		System.out.println(modelHacker18.getProduktivitaet_SrmProPMH15());
		System.out.println();

		modelHacker18.setMotorleistungHacker_kW(230);
		System.out.println(modelHacker18.getKostenTotal_total());
		System.out.println(modelHacker18.getProduktivitaet_SrmProPMH15());
		System.out.println();
	}

	private static void testSimpleKombiseilgeraet2018() {
		System.out.println("SimpleKombiseilgeraet2018");
		SimpleKombiseilgeraet2018 modelKsg18 = new SimpleKombiseilgeraet2018();
		
		System.out.println(modelKsg18.getZeitPersonal_WPPH());
		System.out.println(modelKsg18.getZeitKombiseilgeraet_PMH15());
		System.out.println(modelKsg18.getZeitMotorsaege_PMH15());
		System.out.println(modelKsg18.getZeitLagerplatzfahrzeugInklFahrer_PMH15());
		System.out.println(modelKsg18.getKostenTotal_proM3oR());
		System.out.println(modelKsg18.getProduktivitaet_m3iRproWSH());
		System.out.println();

		modelKsg18.setHolzmenge_m3iR(1000);
		System.out.println(modelKsg18.getKostenTotal_proM3oR());
		System.out.println(modelKsg18.getProduktivitaet_m3iRproWSH());
		System.out.println();

		modelKsg18.setMittlereLinienlaenge_m(230);
		System.out.println(modelKsg18.getKostenTotal_proM3oR());
		System.out.println(modelKsg18.getProduktivitaet_m3iRproWSH());
		System.out.println();
	}

	private static void testSimpleSchlepper2014() {
		System.out.println("SimpleSchlepper2014");
		SimpleSchlepper2014 modelSchlepper14 = new SimpleSchlepper2014();
		
		System.out.println(modelSchlepper14.getZeitMaschinist_WPPH());
		System.out.println(modelSchlepper14.getZeitSchlepper_PMH15());
		System.out.println(modelSchlepper14.getKostenTotal_proM3oR());
		System.out.println(modelSchlepper14.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelSchlepper14.setHolzmenge_m3iR(1000);
		System.out.println(modelSchlepper14.getKostenTotal_proM3oR());
		System.out.println(modelSchlepper14.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelSchlepper14.setMittlereFahrentfernung_m(2300);
		System.out.println(modelSchlepper14.getKostenTotal_proM3oR());
		System.out.println(modelSchlepper14.getProduktivitaet_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleRadharvester2014() {
		System.out.println("SimpleRadharvester2014");
		SimpleRadharvester2014 modelRadharvester14 = new SimpleRadharvester2014();
		
		System.out.println(modelRadharvester14.getZeitMaschinist_WPPH());
		System.out.println(modelRadharvester14.getZeitRadharvester_PMH15());
		System.out.println(modelRadharvester14.getKostenTotal_proM3oR());
		System.out.println(modelRadharvester14.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelRadharvester14.setHolzmenge_m3iR(1000);
		System.out.println(modelRadharvester14.getKostenTotal_proM3oR());
		System.out.println(modelRadharvester14.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelRadharvester14.setMittlererBhd_cm(23);
		System.out.println(modelRadharvester14.getKostenTotal_proM3oR());
		System.out.println(modelRadharvester14.getProduktivitaet_m3iRproPMH15());
		System.out.println();
	}

	private static void testSimpleMotormanuell2014() {
		System.out.println("SimpleMotormanuell2014");
		SimpleMotormanuell2014 modelMoMa14 = new SimpleMotormanuell2014();
		
		System.out.println(modelMoMa14.getZeitPersonal_WPPH());
		System.out.println(modelMoMa14.getZeitMotorsaege_PMH15());
		System.out.println(modelMoMa14.getKostenTotal_proM3oR());
		System.out.println(modelMoMa14.getProduktivitaet_m3iRproWPSH());
		System.out.println();

		modelMoMa14.setHolzmenge_m3iR(1000);
		System.out.println(modelMoMa14.getKostenTotal_proM3oR());
		System.out.println(modelMoMa14.getProduktivitaet_m3iRproWPSH());
		System.out.println();

		modelMoMa14.setAnteilLaubholz_Prz(0);
		System.out.println(modelMoMa14.getKostenTotal_proM3oR());
		System.out.println(modelMoMa14.getProduktivitaet_m3iRproWPSH());
		System.out.println();
	}

	private static void testSimpleSchlepper1992() {
		System.out.println("SimpleSchlepper1992");
		SimpleSchlepper1992 modelSchl92 = new SimpleSchlepper1992();
		
		System.out.println(modelSchl92.getZeitMaschinist_WPPH());
		System.out.println(modelSchl92.getZeitSchlepper_PMH15());
		System.out.println(modelSchl92.getKostenTotal_proM3());
		System.out.println(modelSchl92.getProduktivitaet_m3proPSH15());
		System.out.println();

		modelSchl92.setHolzmenge_m3(1000);
		System.out.println(modelSchl92.getKostenTotal_proM3());
		System.out.println(modelSchl92.getProduktivitaet_m3proPSH15());
		System.out.println();

		modelSchl92.setRueckgehilfeEinsatzanteil_Prz(65);
		System.out.println(modelSchl92.getKostenTotal_proM3());
		System.out.println(modelSchl92.getProduktivitaet_m3proPSH15());
		System.out.println();
	}

	private static void testSimpleFaellenVorruecken2018() {
		System.out.println("SimpleFaellenVorruecken2018");
		SimpleFaellenVorruecken2018 modelFaellVo = new SimpleFaellenVorruecken2018();
		
		System.out.println(modelFaellVo.getZeitPersonal_WPPH());
		System.out.println(modelFaellVo.getZeitVorrueckegeraet_PMH15());
		System.out.println(modelFaellVo.getZeitMotorsaege_PMH15());
		System.out.println(modelFaellVo.getKostenTotal_proM3oR());
		System.out.println(modelFaellVo.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelFaellVo.setHolzmenge_m3iR(1000);
		System.out.println(modelFaellVo.getKostenTotal_proM3oR());
		System.out.println(modelFaellVo.getProduktivitaet_m3iRproPMH15());
		System.out.println();

		modelFaellVo.setBhd_cm(18);
		System.out.println(modelFaellVo.getKostenTotal_proM3oR());
		System.out.println(modelFaellVo.getProduktivitaet_m3iRproPMH15());
		System.out.println();
	}
	
	
	private static void testPerformance() {
		System.out.println("Starte Performance-Test...");
		SimpleFaellenMotormanuell1978 model = new SimpleFaellenMotormanuell1978();
		
		final int NUMBER_OF_TESTS = 1_000_000;
		final Random rnd = new Random(23);
		
		long start = System.currentTimeMillis();
		
		for (int i=0; i<NUMBER_OF_TESTS; i++) {
			// Arbeitsobjekt
			model.setHolzmenge_m3(rnd.nextDouble() * 10000);
			model.setMassenmittelstamm_m3(rnd.nextDouble() * 100);
			model.setBaumartengruppe_Kategorie(rnd.nextInt(4) + 1);
			model.setAnteilFaellenMitHandseilzug_Prz(rnd.nextInt(101));
			model.setHangneigung_Kategorie(rnd.nextInt(4) + 1);
			model.setHindernisse_Kategorie(rnd.nextInt(4) + 1);

			// Arbeitssystem
			model.setKostenPersonal_proH(rnd.nextDouble() * 100);
			model.setKostenMotorsaege_proH(rnd.nextDouble() * 100);
			model.setTaeglicheArbeitszeit_min(rnd.nextDouble() * 200 + 400);
			model.setWegzeitenUndPausen_min(rnd.nextDouble() * 100);
			model.setKostenUmsetzen_Betrag(rnd.nextDouble() * 1000);
			model.setKostenWeitereAufwaende_Betrag(rnd.nextDouble() * 1000);
			
			//Faktoren
			model.setKorrekturfaktor_Faktor(rnd.nextDouble() + 0.5);
			model.setRisikoVerwaltungGewinn_Prz(rnd.nextDouble() * 100);
			model.setMehrwertsteuer_Prz(rnd.nextDouble() * 20);
			
			//Ergebnis
			model.getZeitPersonal_WPPH();
			model.getZeitMotorsaege_PMH15();
			model.getKostenPersonal_proM3();
			model.getKostenMotorsaege_proM3();
			model.getKostenUmsetzen_proM3();
			model.getKostenWeitereAufwaende_proM3();
			model.getKostenTotal_proM3();
			model.getProduktivitaet_m3proPSH15();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		System.out.println("Performance-Test beendet.");
	}

}
