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
package ch.wsl.fps.hepromo.model.modelle;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.AbstractHeProMoTest;
import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.MockHeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.util.CsvReader;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractModelTest extends AbstractHeProMoTest {
	
	
	@BeforeClass
	public void initTestData() {
		CsvReader csv = new CsvReader(";","#", true);
		String fileName = getCsvPath();
		List<String[]> testcases = csv.readFile(fileName);
		
		int nrOfTestcases = testcases.size();
		MockErgebnis[] expectedValues = new MockErgebnis[nrOfTestcases];
		Ergebnis[] ergebnisse = new Ergebnis[nrOfTestcases];
		
		for (int i=0; i<nrOfTestcases; i++) {
			Arbeitsobjekt arbeitsobjekt = parseArbeitsobjektData(testcases.get(i));
			Arbeitssystem arbeitssystem = parseArbeitssystemData(testcases.get(i));
			Faktoren faktoren			= parseFaktoren(testcases.get(i));
			expectedValues[i] = parseExpectedValuesAsErgebnis(testcases.get(i));
			
			/*
			 * falls ergebnis für einen testcase nicht berechnet werden kann,
			 * soll dies erst beim testcase selbst angezeigt werden, nicht schon
			 * beim initialisieren der testdaten
			 */
			try { 
				ergebnisse[i] = getErgebnis(arbeitsobjekt, arbeitssystem, faktoren);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		Object[][] testData = new Object[nrOfTestcases][3];
		
		for (int i=0; i<nrOfTestcases; i++) {
			testData[i][0] = "Testcase " + (i + 1);
			testData[i][1] = ergebnisse[i];
			testData[i][2] = expectedValues[i];
		}
		
		setTestData(testData);
	}
	
	
	
	

	
	@Test(dataProvider="csvData")
	public void checkDauerDerArbeit(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitTotal(), expectedValues.getZeitTotal());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandPersonal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitPersonal(), expectedValues.getZeitPersonal());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandMaschine(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitMaschine1(), expectedValues.getZeitMaschine1());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitUmsetzen(), expectedValues.getZeitUmsetzen());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandWeitereAufwaende(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitWeitereAufwaende(), expectedValues.getZeitWeitereAufwaende());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Personal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenPersonal_proM3(), expectedValues.getKostenPersonal_proM3());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Maschine(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine1_proM3(), expectedValues.getKostenMaschine1_proM3());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Umsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenUmsetzen_proM3(), expectedValues.getKostenUmsetzen_proM3());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3WeitereAufwaende(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenWeitereAufwaende_proM3(), expectedValues.getKostenWeitereAufwaende_proM3());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Total(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenTotal_proM3(), expectedValues.getKostenTotal_proM3());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalPersonal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenPersonal_total(), expectedValues.getKostenPersonal_total());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalMaschine(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine1_total(), expectedValues.getKostenMaschine1_total());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenUmsetzen_total(), expectedValues.getKostenUmsetzen_total());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalWeitereAufwaende(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenWeitereAufwaende_total(), expectedValues.getKostenWeitereAufwaende_total());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalTotal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenTotal_total(), expectedValues.getKostenTotal_total());
	}
	
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_m3ProPsh15(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getProduktivitaet_m3ProPsh15(), expectedValues.getProduktivitaet_m3ProPsh15());
	}

	protected Ergebnis getErgebnis(Arbeitsobjekt ao, Arbeitssystem as, Faktoren faktoren){
		HeProMoInputData inputData = new MockHeProMoWindow(ao, as, faktoren);
		AbstractModel model = getModel(inputData);
		Ergebnis ergebnis = model.getErgebnis();
		
		//Dient zum Neuschreiben von Testdateien bei Änderungen im Calculator
//		DecimalFormat df = new DecimalFormat("0.00");
//		System.err.print( df.format(ergebnis.getZeitPersonal()) + "\t");
//		System.err.print( df.format(ergebnis.getZeitMaschine1()) + "\t");
//		System.err.print( df.format(ergebnis.getZeitUmsetzen()) + "\t");
//		System.err.print( df.format(ergebnis.getZeitWeitereAufwaende()) + "\t");
//
//		System.err.print( df.format(ergebnis.getKostenPersonal_proM3()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenMaschine1_proM3()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenUmsetzen_proM3()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenWeitereAufwaende_proM3()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenTotal_proM3()) + "\t");
//
//		System.err.print( df.format(ergebnis.getKostenPersonal_total()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenMaschine1_total()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenUmsetzen_total()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenWeitereAufwaende_total()) + "\t");
//		System.err.print( df.format(ergebnis.getKostenTotal_total()) + "\t");
//
//		System.err.println( df.format(ergebnis.getProduktivitaet()) + "\t");
		
		
		return ergebnis;
	}
	
	protected abstract AbstractModel getModel(HeProMoInputData inputData);
	
	protected abstract Arbeitsobjekt parseArbeitsobjektData(String[] testcase);	
	
	protected abstract Arbeitssystem parseArbeitssystemData(String[] testcase);
	
	
	protected Faktoren parseFaktoren(String[] testcase) {
		int firstFaktorField = getFirstOutputField() - 3;
		
		Faktoren faktoren = new Faktoren();
		faktoren.setMargin(				Double.valueOf(testcase[firstFaktorField + 0])	);
		faktoren.setWaehrungskuerzel( 	String.valueOf(testcase[firstFaktorField + 1])	);
		faktoren.setKorrekturFaktor(	Double.valueOf(testcase[firstFaktorField + 2])	);
		
		return faktoren;
	}

	
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[1]	);
		result.setZeitMaschine1( 				expectedValues[2]	);
		result.setZeitUmsetzen( 				expectedValues[3]	);
		result.setZeitWeitereAufwaende(			expectedValues[4]	);

		result.setKostenPersonal_proM3( 		expectedValues[5]	);
		result.setKostenMaschine1_proM3( 		expectedValues[6]	);
		result.setKostenUmsetzen_proM3(			expectedValues[7]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[8]	);
		result.setKostenTotal_proM3( 			expectedValues[9]	);

		result.setKostenPersonal_total(			expectedValues[10] 	);
		result.setKostenMaschine1_total( 		expectedValues[11] 	);
		result.setKostenUmsetzen_total(			expectedValues[12] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[13] 	);
		result.setKostenTotal_total( 			expectedValues[14] 	);
		
		result.setProduktivitaet_m3ProPsh15(	expectedValues[15] 	);
		
		
		return result;
	}

	
	protected final double[] parseExpectedValues(String[] testcase) {
		int firstField = getFirstOutputField();
		int lastField = getLastOutputField();
		
		int numberOfFields = lastField - firstField + 1;
		double[] result = new double[numberOfFields];
		
		for (int i=firstField; i<=lastField; i++) {
			result[i-firstField] = Double.valueOf( testcase[i] );
		}
		
		return result;
	}
	
	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 15;
	}
}
