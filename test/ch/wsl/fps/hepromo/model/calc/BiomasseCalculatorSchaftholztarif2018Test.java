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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Entwicklungsstufe;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Gesamtwuchsleistung;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Tarifnummer;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseCalculatorSchaftholztarif2018Test extends AbstractBiomasseCalculator2018Test<Double> {

	private final double DEFAULT_DELTA = 0.0005d;
	
	private static Object[][] testData;

	
	@Test(dataProvider="csvData")
	public void checkVolumenSchaftholz(@SuppressWarnings("unused") String testcaseName, Double ergebnis, Double expectedValue) {
		assertEquals(ergebnis, expectedValue, DEFAULT_DELTA);
	}
	
	
	@DataProvider(name="csvData")
	public Object[][] getTestData() {
		return testData;
	}
	
	@Override
	protected void setTestData(Object[][] testData) {
		BiomasseCalculatorSchaftholztarif2018Test.testData = testData;
	}
	
	
	@Override
	protected String getCsvPath() {
		return "testcases/BiomasseCalculatorSchaftholzTarif2018.csv";
	}
	
	
	@Override
	protected Object[] parseInputData(String[] testcase) {
		Object[] inputData = new Object[testcase.length];
		
		inputData[0] = Tarifnummer.valueOf(	  "T" + testcase[0]);
		inputData[1] = Integer.valueOf(				testcase[1]);
		inputData[2] = Gesamtwuchsleistung.valueOf(	testcase[2]);
		inputData[3] = Entwicklungsstufe.valueOf(	testcase[3]);
		inputData[4] = Integer.valueOf(				testcase[4]);
		
		return inputData;
	}
	
	
	@Override
	protected Double parseExpectedValuesAsErgebnis(String[] testcase) {
		Double[] expectedValues = parseExpectedValues(testcase);
		Double result = expectedValues[0];
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 5;
	}


	@Override
	protected int getLastOutputField() {
		return 5;
	}
	
	
	@Override
	protected Double getErgebnis(Object[] inputData) {
		Tarifnummer tarif = 		(Tarifnummer) 			inputData[0];
		int bhd_cm = 				(Integer)				inputData[1];
		Gesamtwuchsleistung gwl =	(Gesamtwuchsleistung) 	inputData[2];
		Entwicklungsstufe es = 		(Entwicklungsstufe) 	inputData[3];
		int hoeheUeberMeer_m = 		(Integer) 				inputData[4];
		
		Double ergebnis = BiomasseCalculatorSchaftholztarif2018.getSchaftholzVolumen_m3iR(tarif, bhd_cm, gwl, es, hoeheUeberMeer_m);
		
		return ergebnis;
	}
}
