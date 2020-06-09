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
public class SimpleForwarderRundholz2018Test extends AbstractSimpleModelTest {

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitMaschinist_WPPH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[0], expectedValues[0]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitForwarder_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[1], expectedValues[1]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenMaschinist_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[2], expectedValues[2]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenForwarder_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[3], expectedValues[3]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenUmsetzen_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[4], expectedValues[4]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenWeitereAufwaende_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[5], expectedValues[5]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_proM3oR(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[6], expectedValues[6]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[7], expectedValues[7]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_m3iRproPMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[8], expectedValues[8]);
	}


	@Override
	protected double[] getErgebnis(double[] input) {
		//Inputvariablen setzen
		SimpleForwarderRundholz2018 model = new SimpleForwarderRundholz2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setRundholzmenge_m3iR(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMittlererBhd_cm(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRueckeentfernung_Kategorie(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHangneigung_Kategorie(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAnzahlSortimente_Kategorie(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setErschwernisse_Kategorie(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setAbstandRueckegassen_Kategorie(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setForwardertyp_Kategorie(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenMaschinist_proH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenForwarder_proH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTaeglicheArbeitszeit_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setWegzeitenUndPausen_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenUmsetzen_Betrag(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenWeitereAufwaende_Betrag(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKorrekturfaktor_Faktor(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRisikoVerwaltungGewinn_Prz(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMehrwertsteuer_Prz(			input[currentLineIndex]	);
		
		
		//Outputvariablen auslesen
		double[] result = new double[9];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getZeitMaschinist_WPPH();
		result[++currentLineIndex] = model.getZeitForwarder_PMH15();
		result[++currentLineIndex] = model.getKostenMaschinist_proM3oR();
		result[++currentLineIndex] = model.getKostenForwarder_proM3oR();
		result[++currentLineIndex] = model.getKostenUmsetzen_proM3oR();
		result[++currentLineIndex] = model.getKostenWeitereAufwaende_proM3oR();
		result[++currentLineIndex] = model.getKostenTotal_proM3oR();
		result[++currentLineIndex] = model.getKostenTotal_total();
		result[++currentLineIndex] = model.getProduktivitaet_m3iRproPMH15();
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 17;
	}

	@Override
	protected int getLastOutputField() {
		return 25;
	}

}
