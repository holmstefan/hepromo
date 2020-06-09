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

import org.testng.annotations.Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleKonventionellerSeilkran1999Test extends AbstractSimpleModelTest {

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitPersonal_WPPH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[0], expectedValues[0]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitSeilkrananlage_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[1], expectedValues[1]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitKranfahrzeug_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[2], expectedValues[2]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenPersonal_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[3], expectedValues[3]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenSeilkrananlage_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[4], expectedValues[4]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenKranfahrzeug_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[5], expectedValues[5]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenUmsetzen_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[6], expectedValues[6]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenWeitereAufwaende_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[7], expectedValues[7]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[8], expectedValues[8]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[9], expectedValues[9]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProduktivitaetBeimSeilen_m3iRproPMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[10], expectedValues[10]);
	}


	@Override
	protected double[] getErgebnis(double[] input) {
		//Inputvariablen setzen
		SimpleKonventionellerSeilkran1999 model = new SimpleKonventionellerSeilkran1999();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setHolzmenge_m3iR(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMittlererStueckinhalt_m3iR(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setStuecklaenge_m(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setEingriffsart_Kategorie(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHolzseilort_Kategorie(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHangneigung_Prz(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHindernisse_Kategorie(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setLinienlaenge_m(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setFahrtrichtung_Kategorie(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMittlereFahrdistanz_m(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMittlereDistanzSeitlicherZuzug_m(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlStuetzen_Anzahl(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlEndmasten_Anzahl(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTragseilhoeheBestand_m(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTragseilhoeheLagerplatz_m(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenPersonal_proH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenSeilkrananlage_proH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenKranfahrzeug_proH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKranfahrzeugLaufzeit_Prz(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlPersonenInstallation_Anzahl(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlPersonenSeilen_Anzahl(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTaeglicheArbeitszeit_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setWegzeitenUndPausen_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenUmsetzen_Betrag(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenWeitereAufwaende_Betrag(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKorrekturfaktor_Faktor(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRisikoVerwaltungGewinn_Prz(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMehrwertsteuer_Prz(				input[currentLineIndex]	);
		
		
		//Outputvariablen auslesen
		double[] result = new double[11];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getZeitPersonal_WPPH();
		result[++currentLineIndex] = model.getZeitSeilkrananlage_PMH15();
		result[++currentLineIndex] = model.getZeitKranfahrzeug_PMH15();
		result[++currentLineIndex] = model.getKostenPersonal_proM3oR();
		result[++currentLineIndex] = model.getKostenSeilkrananlage_proM3oR();
		result[++currentLineIndex] = model.getKostenKranfahrzeug_proM3oR();
		result[++currentLineIndex] = model.getKostenUmsetzen_proM3oR();
		result[++currentLineIndex] = model.getKostenWeitereAufwaende_proM3oR();
		result[++currentLineIndex] = model.getKostenTotal_proM3oR();
		result[++currentLineIndex] = model.getKostenTotal_total();
		result[++currentLineIndex] = model.getProduktivitaetBeimSeilen_m3iRproPMH15();
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 28;
	}

	@Override
	protected int getLastOutputField() {
		return 38;
	}

}
