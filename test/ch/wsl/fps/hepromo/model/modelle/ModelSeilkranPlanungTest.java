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
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranPlanung;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelSeilkranPlanungTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/SeilkranPlanung.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelSeilkranPlanung(inputData);
	}

	@Override
	protected ArbeitsobjektSeilkranPlanung parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektSeilkranPlanung ao = new ArbeitsobjektSeilkranPlanung();
		ao.setHolzmenge_m3(Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setLinienlaenge_m(Integer.valueOf(testcase[3]));
		ao.setLinieAbsteckenOhneProjekt(Boolean.valueOf(testcase[4]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		Arbeitssystem as = new Arbeitssystem();
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[5]));
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[6]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[7]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[8]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[9]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 13;
	}

	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 8;
	}
	
	
	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitTotal( 					expectedValues[0] );
		result.setZeitPersonal( 				expectedValues[1] );
		result.setZeitWeitereAufwaende( 		expectedValues[2] );
		result.setKostenPersonal_proM3( 		expectedValues[3] );
		result.setKostenWeitereAufwaende_proM3( expectedValues[4] );
		result.setKostenTotal_proM3( 			expectedValues[5] );
		result.setKostenPersonal_total( 		expectedValues[6] );
		result.setKostenWeitereAufwaende_total( expectedValues[7] );
		result.setKostenTotal_total( 			expectedValues[8] );
		
		return result;
	}
	
	
	

	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkZeitaufwandMaschine(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkZeitaufwandUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenProM3Maschine(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenProM3Umsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenTotalMaschine(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenTotalUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkProduktivitaet_m3ProPsh15(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
	}

}
