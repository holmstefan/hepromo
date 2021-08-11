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
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterAufarbeiten;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelHelikopterAufarbeitenTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/HelikopterAufarbeiten.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelHelikopterAufarbeiten(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektHelikopterAufarbeiten ao = new ArbeitsobjektHelikopterAufarbeiten();
		ao.setBaumartenGruppe(	BaumartenGruppe.valueOf(testcase[0]));
		ao.setHolzmenge_m3(		Double.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[2]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[3]));
		ao.setArbeitsVerfahren(	ArbeitsVerfahren.valueOf(testcase[4]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemHelikopterAufarbeiten as = new ArbeitssystemHelikopterAufarbeiten();
		as.setAnzahlPersonal1(Integer.valueOf(testcase[5]));
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[6]));
		as.setAnzahlMaschine1(Integer.valueOf(testcase[7]));
		as.setKostensatzMaschine1_proH(Double.valueOf(testcase[8]));
		as.setAnzahlMaschine2(Integer.valueOf(testcase[9]));
		as.setKostensatzMaschine2_proH(Double.valueOf(testcase[10]));
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[11]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[12]));
		as.setUmsetzenBetrag_CHF(Double.valueOf(testcase[13]));
		as.setUmsetzenZeit_h(Double.valueOf(testcase[14]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[15]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[16]));
		return as;
	}


	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[1]	);
		result.setZeitMaschine1( 				expectedValues[2]	);
		result.setZeitMaschine2( 				expectedValues[3]	);
		result.setZeitUmsetzen( 				expectedValues[4]	);
		result.setZeitWeitereAufwaende(			expectedValues[5]	);
		result.setKostenPersonal_proM3( 		expectedValues[6]	);
		result.setKostenMaschine1_proM3( 		expectedValues[7]	);
		result.setKostenMaschine2_proM3( 		expectedValues[8]	);
		result.setKostenUmsetzen_proM3(			expectedValues[9]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[10]	);
		result.setKostenTotal_proM3( 			expectedValues[11]	);
		result.setKostenPersonal_total(			expectedValues[12] 	);
		result.setKostenMaschine1_total( 		expectedValues[13] 	);
		result.setKostenMaschine2_total( 		expectedValues[14] 	);
		result.setKostenUmsetzen_total( 		expectedValues[15] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[16] 	);
		result.setKostenTotal_total( 			expectedValues[17] 	);
		result.setProduktivitaet_m3ProPsh15( 	expectedValues[18] 	);
		
		return result;
	}
	
	
	@Override
	protected int getFirstOutputField() {
		return 20;
	}
	
	@Override
	protected int getLastOutputField() {
		return 38;
//		return getFirstOutputField() + 18;
	}
	
	
	
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandMaschine2(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitMaschine2(), expectedValues.getZeitMaschine2());
	}
	
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Maschine2(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine2_proM3(), expectedValues.getKostenMaschine2_proM3());
	}
	
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalMaschine2(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine2_total(), expectedValues.getKostenMaschine2_total());
	}

}
