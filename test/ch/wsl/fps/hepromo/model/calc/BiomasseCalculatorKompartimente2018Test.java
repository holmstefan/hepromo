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

import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.ErgebnisBiomasse;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.NadelLaub;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.Standort;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseCalculatorKompartimente2018Test extends AbstractBiomasseCalculator2018Test<ErgebnisBiomasse> {

	private final double DEFAULT_DELTA = 0.005d;
	
	private static Object[][] testData;
	
	
	@Test(dataProvider="csvData")
	public void checkVerhaeltnisAstderbholzZuSchaftholz_Prz(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getVerhaeltnisAstderbholzZuSchaftholz_Prz(), expectedValue.getVerhaeltnisAstderbholzZuSchaftholz_Prz(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVerhaeltnisReisigZuSchaftholz_Prz(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getVerhaeltnisReisigZuSchaftholz_Prz(), expectedValue.getVerhaeltnisReisigZuSchaftholz_Prz(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenSchaftholz_m3iR(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getVolumenSchaftholz_m3iR(), expectedValue.getVolumenSchaftholz_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenAstderbholz_m3iR(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getVolumenAstderbholz_m3iR(), expectedValue.getVolumenAstderbholz_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenReisig_m3iR(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getVolumenReisig_m3iR(), expectedValue.getVolumenReisig_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenBiomasseOhneNadelnUndBlaetter(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getVolumenBiomasseOhneNadelnUndBlaetter(), expectedValue.getVolumenBiomasseOhneNadelnUndBlaetter(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkMasseNadelnUndBlaetter_kg(@SuppressWarnings("unused") String testcaseName, ErgebnisBiomasse ergebnis, ErgebnisBiomasse expectedValue) {
		assertEquals(ergebnis.getMasseNadelnUndBlaetter_kg(), expectedValue.getMasseNadelnUndBlaetter_kg(), DEFAULT_DELTA);
	}
	
	
	@DataProvider(name="csvData")
	public Object[][] getTestData() {
		return testData;
	}
	
	
	@Override
	protected void setTestData(Object[][] testData) {
		BiomasseCalculatorKompartimente2018Test.testData = testData;
	}
	
	
	@Override
	protected String getCsvPath() {
		return "testcases/BiomasseCalculatorKompartimente2018.csv";
	}	
	
	
	@Override
	protected Object[] parseInputData(String[] testcase) {
		Object[] inputData = new Object[testcase.length];
		
		inputData[0] = Double.valueOf(	 	testcase[0]);
		inputData[1] = NadelLaub.valueOf(	testcase[1]);
		inputData[2] = Standort.valueOf(	testcase[2]);
		inputData[3] = Integer.valueOf(		testcase[3]);
		inputData[4] = Integer.valueOf(		testcase[4]);
		
		return inputData;
	}
	
	
	@Override
	protected ErgebnisBiomasse parseExpectedValuesAsErgebnis(String[] testcase) {
		Double[] expectedValues = parseExpectedValues(testcase);
		
		ErgebnisBiomasse result = new ErgebnisBiomasse();			
		result.verhaeltnisAstderbholzZuSchaftholz_Prz = expectedValues[0];
		result.verhaeltnisReisigZuSchaftholz_Prz = 		expectedValues[1];
		result.volumenSchaftholz_m3iR = 				expectedValues[2];
		result.volumenAstderbholz_m3iR = 				expectedValues[3];
		result.volumenReisig_m3iR = 					expectedValues[4];
		result.volumenBiomasseOhneNadelnUndBlaetter = 	expectedValues[5];
		result.masseNadelnUndBlaetter_kg = 				expectedValues[6];
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 5;
	}


	@Override
	protected int getLastOutputField() {
		return 11;
	}
	
	
	@Override
	protected ErgebnisBiomasse getErgebnis(Object[] inputData) {
		Double vshEinzelnerBaum = 	(Double) 	inputData[0];
		NadelLaub baumart = 		(NadelLaub)	inputData[1];
		Standort standort =			(Standort) 	inputData[2];
		Integer bhd_cm = 			(Integer) 	inputData[3];
		Integer anzahlStaemme = 	(Integer) 	inputData[4];
		
		ErgebnisBiomasse ergebnis = BiomasseCalculatorKompartimente2018.calc(vshEinzelnerBaum, baumart, standort, bhd_cm, anzahlStaemme);
		
//		//Dient zum Neuschreiben von Testdateien bei Änderungen im Calculator
//		DecimalFormat df = new DecimalFormat("0.00");
//		System.err.print( df.format(ergebnis.getVerhaeltnisAstderbholzZuSchaftholz_Prz()) + "\t");
//		System.err.print( df.format(ergebnis.getVerhaeltnisReisigZuSchaftholz_Prz()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenSchaftholz_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenAstderbholz_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenReisig_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenBiomasseOhneNadelnUndBlaetter()) + "\t");
//		System.err.print( df.format(ergebnis.getMasseNadelnUndBlaetter_kg()) + "\t\n");
		
		return ergebnis;
	}
	
}
