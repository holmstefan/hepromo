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
public class SimpleHacker2018Test extends AbstractSimpleModelTest {

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitMaschinist_WPPH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[0], expectedValues[0]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitHacker_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[1], expectedValues[1]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenMaschinist_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[2], expectedValues[2]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenHacker_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[3], expectedValues[3]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenUmsetzen_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[4], expectedValues[4]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenWeitereAufwaende_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[5], expectedValues[5]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[6], expectedValues[6]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[7], expectedValues[7]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_SrmProPMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[8], expectedValues[8]);
	}


	@Override
	protected double[] getErgebnis(double[] input) {
		//Inputvariablen setzen
		SimpleHacker2018 model = new SimpleHacker2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setHackschnitzelmenge_Srm(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setPoltersortiment_Kategorie(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMotorleistungHacker_kW(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenMaschinist_proH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenHacker_proH(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTaeglicheArbeitszeit_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setWegzeitenUndPausen_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenUmsetzen_Betrag(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenWeitereAufwaende_Betrag(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKorrekturfaktor_Faktor(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRisikoVerwaltungGewinn_Prz(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMehrwertsteuer_Prz(				input[currentLineIndex]	);
		
		
		//Outputvariablen auslesen
		double[] result = new double[9];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getZeitMaschinist_WPPH();
		result[++currentLineIndex] = model.getZeitHacker_PMH15();
		result[++currentLineIndex] = model.getKostenMaschinist_proSrm();
		result[++currentLineIndex] = model.getKostenHacker_proSrm();
		result[++currentLineIndex] = model.getKostenUmsetzen_proSrm();
		result[++currentLineIndex] = model.getKostenWeitereAufwaende_proSrm();
		result[++currentLineIndex] = model.getKostenTotal_proSrm();
		result[++currentLineIndex] = model.getKostenTotal_total();
		result[++currentLineIndex] = model.getProduktivitaet_SrmProPMH15();
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 12;
	}

	@Override
	protected int getLastOutputField() {
		return 20;
	}

}
