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
public class SimpleHackschnitzeltransport2018Test extends AbstractSimpleModelTest {

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkZeitTransportfahrzeugInklFahrer_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[0], expectedValues[0]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTransportfahrzeugInklFahrer_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[1], expectedValues[1]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenWeitereAufwaende_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[2], expectedValues[2]);
	}
	
	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_proSrm(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[3], expectedValues[3]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkKostenTotal_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[4], expectedValues[4]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_SrmProPMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[5], expectedValues[5]);
	}


	@Override
	protected double[] getErgebnis(double[] input) {
		//Inputvariablen setzen
		SimpleHackschnitzeltransport2018 model = new SimpleHackschnitzeltransport2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setHackschnitzelmenge_Srm(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setFahrstreckeWaldstrasse_km(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setFahrstreckeInnerortsUndAusserorts_km(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setFahrstreckeAutobahn_km(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMuldeninhalt_Srm(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenTransportfahrzeugInklFahrer_proH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKostenWeitereAufwaende_Betrag(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKorrekturfaktor_Faktor(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRisikoVerwaltungGewinn_Prz(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMehrwertsteuer_Prz(				input[currentLineIndex]	);
		
		
		//Outputvariablen auslesen
		double[] result = new double[6];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getZeitTransportfahrzeugInklFahrer_PMH15();
		result[++currentLineIndex] = model.getKostenTransportfahrzeugInklFahrer_proSrm();
		result[++currentLineIndex] = model.getKostenWeitereAufwaende_proSrm();
		result[++currentLineIndex] = model.getKostenTotal_proSrm();
		result[++currentLineIndex] = model.getKostenTotal_total();
		result[++currentLineIndex] = model.getProduktivitaet_SrmProPMH15();
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 10;
	}

	@Override
	protected int getLastOutputField() {
		return 15;
	}

}
