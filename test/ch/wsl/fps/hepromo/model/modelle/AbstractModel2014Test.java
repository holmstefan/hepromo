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

import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.MockErgebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractModel2014Test extends AbstractModelTest {

	
	
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_fmORproWPPH(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getProduktivitaet_fmORproWPPH(), expectedValues.getProduktivitaet_fmORproWPPH());
	}
	
	
	
	@Override
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
		result.setProduktivitaet_fmORproWPPH(	expectedValues[16] 	); 
		
		
		return result;
	}
}
