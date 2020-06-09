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
public class SimpleKombiseilgeraet2018Test extends AbstractSimpleModelTest {

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitPersonal_WPPH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[0], expectedValues[0]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitKombiseilgeraet_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[1], expectedValues[1]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitMotorsaege_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[2], expectedValues[2]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitLagerplatzfahrzeugInklFahrer_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[3], expectedValues[3]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenPersonal_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[4], expectedValues[4]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenKombiseilgeraet_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[5], expectedValues[5]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenMotorsaege_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[6], expectedValues[6]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenLagerplatzfahrzeugInklFahrer_v(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[7], expectedValues[7]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenUmsetzen_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[8], expectedValues[8]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenWeitereAufwaende_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[9], expectedValues[9]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[10], expectedValues[10]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[11], expectedValues[11]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_m3iRproWSH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[12], expectedValues[12]);
	}


	@Override
	protected double[] getErgebnis(double[] input) {
		//Inputvariablen setzen
		SimpleKombiseilgeraet2018 model = new SimpleKombiseilgeraet2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setHolzmenge_m3iR(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlLinien_Anzahl(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMittlereLinienlaenge_m(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlStuetzenProLinie_Anzahl(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHangneigung_Prz(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnteilLaubholz_Prz(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setErschwernisse_Kategorie(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setEquipeAnzahlArbeiter_Anzahl(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnteilEinsatzzeitLagerplatzfahrzeug_Prz(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenPersonalProPerson_proH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenKombiseilgeraet_proH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenMotorsaege_proH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenLagerplatzfahrzeugInklFahrer_proH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTaeglicheArbeitszeit_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setWegzeitenUndPausen_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenUmsetzen_Betrag(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenWeitereAufwaende_Betrag(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKorrekturfaktor_Faktor(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRisikoVerwaltungGewinn_Prz(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMehrwertsteuer_Prz(				input[currentLineIndex]	);
		
		
		//Outputvariablen auslesen
		double[] result = new double[13];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getZeitPersonal_WPPH();
		result[++currentLineIndex] = model.getZeitKombiseilgeraet_PMH15();
		result[++currentLineIndex] = model.getZeitMotorsaege_PMH15();
		result[++currentLineIndex] = model.getZeitLagerplatzfahrzeugInklFahrer_PMH15();
		result[++currentLineIndex] = model.getKostenPersonal_proM3oR();
		result[++currentLineIndex] = model.getKostenKombiseilgeraet_proM3oR();
		result[++currentLineIndex] = model.getKostenMotorsaege_proM3oR();
		result[++currentLineIndex] = model.getKostenLagerplatzfahrzeugInklFahrer_proM3oR();
		result[++currentLineIndex] = model.getKostenUmsetzen_proM3oR();
		result[++currentLineIndex] = model.getKostenWeitereAufwaende_proM3oR();
		result[++currentLineIndex] = model.getKostenTotal_proM3oR();
		result[++currentLineIndex] = model.getKostenTotal_total();
		result[++currentLineIndex] = model.getProduktivitaet_m3iRproWSH();
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 20;
	}

	@Override
	protected int getLastOutputField() {
		return 32;
	}

}
