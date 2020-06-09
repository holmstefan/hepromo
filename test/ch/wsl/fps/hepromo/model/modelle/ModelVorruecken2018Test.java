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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Standortguete;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018.Maschinentyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelVorruecken2018Test extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/Vorruecken2018.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		ModelVorruecken2018 model = new ModelVorruecken2018(inputData);
		return model;
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektVorruecken2018 ao = new ArbeitsobjektVorruecken2018();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setBhd_cm(							Integer.valueOf(testcase[3]));
		ao.setStandortguete(					Standortguete.valueOf(testcase[4]));
		ao.setHangneigung(						Hangneigung.valueOf(testcase[5]));
		ao.setAbzopfenDerKrone(					Boolean.valueOf(testcase[6]));
		
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemVorruecken2018 as = new ArbeitssystemVorruecken2018();
		as.setMaschinentyp(				Maschinentyp.valueOf(testcase[7]));
		as.setProduktivtaetssteigerungRueckeraupe(	Double.valueOf(testcase[8]));
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[ 9]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[10]));	
		as.setKostensatzMaschine2_proH(		Double.valueOf(testcase[11]));	
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[12]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[13]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[14]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[15]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[16]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[17]));
		
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 21;
	}
	
	@Override
	protected int getLastOutputField() {
		return 39;
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
		result.setKostenUmsetzen_total(			expectedValues[15] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[16] 	);
		result.setKostenTotal_total( 			expectedValues[17] 	);

		result.setProduktivitaet_m3ProPsh15(	expectedValues[18] 	);
		
		
		return result;
	}
	
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandMaschine2(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitMaschine2(), expectedValues.getZeitMaschine2());
	}
	
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Maschine2(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine2_proM3(), expectedValues.getKostenMaschine2_proM3());
	}
	
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalMaschine2(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine2_total(), expectedValues.getKostenMaschine2_total());
	}

}
