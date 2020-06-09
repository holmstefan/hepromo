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

import java.util.List;

import org.testng.annotations.BeforeClass;

import ch.wsl.fps.hepromo.AbstractHeProMoTest;
import ch.wsl.fps.hepromo.util.CsvReader;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractSimpleModelTest extends AbstractHeProMoTest {
	
	@Override
	protected String getCsvPath() {
		final String className = this.getClass().getSimpleName().replace("Test", "");
		final String csvPath = "testcases/api/" + className + ".csv";
		return csvPath;
	}
	
	@BeforeClass
	public void initTestData() {
		CsvReader csv = new CsvReader(";","#", true);
		String fileName = getCsvPath();
		List<String[]> testcases = csv.readFile(fileName);
		
		int nrOfTestcases = testcases.size();
		double[][] expectedValues = new double[nrOfTestcases][];
		double[][] ergebnisse = new double[nrOfTestcases][];
		
		for (int i=0; i<nrOfTestcases; i++) {
			int firstField = getFirstOutputField();
			int lastField = getLastOutputField();
			
			double[] input    = parseStringArrayToDoubleArray(testcases.get(i),  0, firstField-1);
			expectedValues[i] = parseStringArrayToDoubleArray(testcases.get(i), firstField, lastField);
			
			/*
			 * Falls das Ergebnis für einen Testcase nicht berechnet werden kann,
			 * soll dies erst beim Testcase selbst angezeigt werden, nicht schon
			 * beim Initialisieren der Testdaten.
			 */
			try { 
				ergebnisse[i] = getErgebnis(input);
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

	
	protected abstract double[] getErgebnis(double[] input);
	
	
	private double[] parseStringArrayToDoubleArray(String[] testcase, int first, int last) {
		int numberOfFields = last - first + 1;
		
		double[] result = new double[numberOfFields];
		
		for (int i=first; i<=last; i++) {
			result[i-first] = Double.valueOf(testcase[i]);
		}
		
		return result;
	}
}
