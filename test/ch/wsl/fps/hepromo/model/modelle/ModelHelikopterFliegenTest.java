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
import ch.wsl.fps.hepromo.model.ErgebnisHelikopterFliegen;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelHelikopterFliegenTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/HelikopterFliegen.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelHelikopterFliegen(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektHelikopterFliegen ao = new ArbeitsobjektHelikopterFliegen();
		ao.setHolztyp(				Holztyp.valueOf(testcase[0]));
		ao.setHolzmenge_m3(			Double.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[2]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[3]));
		ao.setHorizontalDistanz_m(	Integer.valueOf(testcase[4]));
		ao.setVertikalDistanz_m(	Integer.valueOf(testcase[5]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemHelikopterFliegen as = new ArbeitssystemHelikopterFliegen();
		as.setKostensatzMaschine1_proMin(	Integer.valueOf(testcase[6])	);
		as.setAnflugPauschale(				Integer.valueOf(testcase[7])	);
		as.setHelikopterKlasse(				HelikopterKlasse.valueOf(testcase[8]));
		as.setLastVolumen(					Double.valueOf(testcase[9])		);
		as.setAnzahlPersonal(				Integer.valueOf(testcase[10])	);
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[11])	);
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[12])	);
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[13])	);
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[14]))	;
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[15]))	;
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 19;
	}
	
	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 13;
	}

	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[1]	);
		result.setZeitMaschine1( 				expectedValues[2]	);
//		result.setZeitUmsetzen( 				expectedValues[3]	);
		result.setZeitWeitereAufwaende(			expectedValues[3]	);
		result.setKostenPersonal_proM3( 		expectedValues[4]	);
		result.setKostenMaschine1_proM3( 		expectedValues[5]	);
//		result.setKostenUmsetzen_proM3(			expectedValues[7]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[6]	);
		result.setKostenTotal_proM3( 			expectedValues[7]	);
		result.setKostenPersonal_total(			expectedValues[8] 	);
		result.setKostenMaschine1_total( 		expectedValues[9] 	);
//		result.setKostenUmsetzen_total( 		expectedValues[12] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[10] 	);
		result.setKostenTotal_total( 			expectedValues[11] 	);
		result.setProduktivitaet_m3ProPsh15( 	expectedValues[12] 	);
		result.setRotationszeit( 				expectedValues[13] 	);
		
		
		return result;
	}
	
	
	

	

	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkZeitaufwandUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}

	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenProM3Umsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}

	
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenTotalUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
	
	@Test(dataProvider="csvData")
	public void checkRotationszeit(@SuppressWarnings("unused") String testcaseName, ErgebnisHelikopterFliegen ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getRotationszeit(), expectedValues.getRotationszeit());
	}
	

}
