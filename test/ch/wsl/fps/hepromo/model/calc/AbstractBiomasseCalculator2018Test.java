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

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;

import ch.wsl.fps.hepromo.util.CsvReader;

/**
 * 
 * @author Stefan Holm
 *
 * @param <T> Ergebnis-Klasse
 */
public abstract class AbstractBiomasseCalculator2018Test<T> {

	protected abstract void setTestData(Object[][] testData);
	
	protected abstract String getCsvPath();
	
	
	@BeforeClass
	public void initTestData() {
		CsvReader csv = new CsvReader(";","#", true);
		String fileName = getCsvPath();
		List<String[]> testcases = csv.readFile(fileName);
		
		int nrOfTestcases = testcases.size();
		List<T> expectedValues = new ArrayList<T>();
		List<T> actualValues = new ArrayList<T>();
		
		for (int i=0; i<nrOfTestcases; i++) {
			Object[] inputData = parseInputData(testcases.get(i));
			expectedValues.add( parseExpectedValuesAsErgebnis(testcases.get(i)) );
			
			/*
			 * Falls Ergebnis für einen Testcase nicht berechnet werden kann,
			 * soll dies erst beim Testcase selbst angezeigt werden, nicht schon
			 * beim Initialisieren der Testdaten
			 */
			T actualValue = null;
			try { 
				actualValue = getErgebnis(inputData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			actualValues.add(actualValue);
		}
		if (actualValues.size() != nrOfTestcases || expectedValues.size() != nrOfTestcases) {
			//sanity check
			throw new IllegalStateException(nrOfTestcases + "/" + actualValues.size() + "/" + expectedValues.size());
		}
		
		
		Object[][] testData = new Object[nrOfTestcases][3];		
		for (int i=0; i<nrOfTestcases; i++) {
			testData[i][0] = "Testcase " + (i + 1);
			testData[i][1] = actualValues.get(i);
			testData[i][2] = expectedValues.get(i);
		}
		
		setTestData(testData);
	}	
	
	
	protected abstract Object[] parseInputData(String[] testcase);	
	
	
	protected abstract T parseExpectedValuesAsErgebnis(String[] testcase);
	
	
	protected final Double[] parseExpectedValues(String[] testcase) {
		int firstField = getFirstOutputField();
		int lastField = getLastOutputField();
		
		int numberOfFields = lastField - firstField + 1;
		Double[] result = new Double[numberOfFields];
		
		for (int i=firstField; i<=lastField; i++) {
			result[i-firstField] = Double.valueOf( testcase[i] );
		}
		
		return result;
	}
	
	
	protected abstract int getFirstOutputField();


	protected abstract int getLastOutputField();
	
	
	protected abstract T getErgebnis(Object[] inputData);
}
