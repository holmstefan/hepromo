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

import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorAnteilEnergieholz2018.Zopfklasse;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.ErgebnisEnergieholz;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.Standort;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseCalculatorAnteilEnergieholz2018Test extends AbstractBiomasseCalculator2018Test<ErgebnisEnergieholz> {

	private final double DEFAULT_DELTA = 0.005d;
	
	private static Object[][] testData;
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenEnergieholzAmSchaftOberhalbZopf_m3iR(String testcaseName, ErgebnisEnergieholz ergebnis, ErgebnisEnergieholz expectedValue) {
		assertEquals(ergebnis.getVolumenEnergieholzAmSchaftOberhalbZopf_m3iR(), expectedValue.getVolumenEnergieholzAmSchaftOberhalbZopf_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenEnergierundholzUnterhalbZopf_m3iR(String testcaseName, ErgebnisEnergieholz ergebnis, ErgebnisEnergieholz expectedValue) {
		assertEquals(ergebnis.getVolumenEnergierundholzUnterhalbZopf_m3iR(), expectedValue.getVolumenEnergierundholzUnterhalbZopf_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenAstderbholz_m3iR(String testcaseName, ErgebnisEnergieholz ergebnis, ErgebnisEnergieholz expectedValue) {
		assertEquals(ergebnis.getVolumenAstderbholz_m3iR(), expectedValue.getVolumenAstderbholz_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenReisig_m3iR(String testcaseName, ErgebnisEnergieholz ergebnis, ErgebnisEnergieholz expectedValue) {
		assertEquals(ergebnis.getVolumenReisig_m3iR(), expectedValue.getVolumenReisig_m3iR(), DEFAULT_DELTA);
	}
	
	
	@Test(dataProvider="csvData")
	public void checkVolumenEnergieholz_m3iR(String testcaseName, ErgebnisEnergieholz ergebnis, ErgebnisEnergieholz expectedValue) {
		assertEquals(ergebnis.getVolumenEnergieholz_m3iR(), expectedValue.getVolumenEnergieholz_m3iR(), DEFAULT_DELTA);
	}
	
	
	@DataProvider(name="csvData")
	public Object[][] getTestData() {
		return testData;
	}
	
	@Override
	protected void setTestData(Object[][] testData) {
		BiomasseCalculatorAnteilEnergieholz2018Test.testData = testData;
	}
	
	
	@Override
	protected String getCsvPath() {
		return "testcases/BiomasseCalculatorAnteilEnergieholz2018.csv";
	}
	
	
	@Override
	protected Object[] parseInputData(String[] testcase) {
		Object[] inputData = new Object[testcase.length];
		
		inputData[ 0] = Double.valueOf(	 	testcase[ 0]);
		inputData[ 1] = Integer.valueOf(	testcase[ 1]);
		inputData[ 2] = Integer.valueOf(	testcase[ 2]);
		inputData[ 3] = Standort.valueOf(	testcase[ 3]);
		inputData[ 4] = Integer.valueOf(	testcase[ 4]);
		inputData[ 5] = Zopfklasse.valueOf(	testcase[ 5]);
		inputData[ 6] = Integer.valueOf(	testcase[ 6]);
		inputData[ 7] = Integer.valueOf(	testcase[ 7]);
		inputData[ 8] = Integer.valueOf(	testcase[ 8]);
		inputData[ 9] = Integer.valueOf(	testcase[ 9]);
		inputData[10] = Integer.valueOf(	testcase[10]);
		inputData[11] = Integer.valueOf(	testcase[11]);
		
		return inputData;
	}
	
	
	@Override
	protected ErgebnisEnergieholz parseExpectedValuesAsErgebnis(String[] testcase) {
		Double[] expectedValues = parseExpectedValues(testcase);
		
		ErgebnisEnergieholz result = new ErgebnisEnergieholz();			
		result.volumenEnergieholzAmSchaftOberhalbZopf_m3iR = expectedValues[0];
		result.volumenEnergierundholzUnterhalbZopf_m3iR = 	 expectedValues[1];
		result.volumenAstderbholz_m3iR = 					 expectedValues[2];
		result.volumenReisig_m3iR = 						 expectedValues[3];
		result.volumenEnergieholz_m3iR = 					 expectedValues[4];
		
		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 12;
	}


	@Override
	protected int getLastOutputField() {
		return 16;
	}
	
	
	@Override
	protected ErgebnisEnergieholz getErgebnis(Object[] inputData) {
		Double vshEinzelnerBaum = 	(Double) 	inputData[ 0];
		Integer anzahlStaemme = 	(Integer) 	inputData[ 1];
		Integer lh_Prz = 		  	(Integer)	inputData[ 2];
		Standort standort = 		(Standort) 	inputData[ 3];
		Integer bhd_cm = 			(Integer) 	inputData[ 4];
		Zopfklasse zopf_cm = 		(Zopfklasse)inputData[ 5];
		Integer ae_Prz = 			(Integer) 	inputData[ 6];
		Integer ar_Prz = 			(Integer) 	inputData[ 7];
		Integer aad_Prz = 			(Integer) 	inputData[ 8];
		Integer evdh_Nadel_Prz = 	(Integer) 	inputData[ 9];
		Integer evdh_Laub_Prz = 	(Integer) 	inputData[10];
		Integer evndh_Nadel_Prz = 	(Integer) 	inputData[11];
		
		
		ErgebnisEnergieholz ergebnis = BiomasseCalculatorKompartimente2018.calc(vshEinzelnerBaum, lh_Prz, standort, zopf_cm, bhd_cm, ae_Prz, aad_Prz, ar_Prz, evdh_Nadel_Prz, evdh_Laub_Prz, evndh_Nadel_Prz, anzahlStaemme);
		
//		//Dient zum Neuschreiben von Testdateien bei Änderungen im Calculator
//		DecimalFormat df = new DecimalFormat("0.00");
//		System.err.print( df.format(ergebnis.getVolumenEnergieholzAmSchaftOberhalbZopf_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenEnergierundholzUnterhalbZopf_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenAstderbholz_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenReisig_m3iR()) + "\t");
//		System.err.print( df.format(ergebnis.getVolumenEnergieholz_m3iR()) + "\t\n");
		
		return ergebnis;
	}
	
}
