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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester.Baumartgruppe;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester.MaschinenKategorie;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester.MaschinenTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelRadharvesterTest extends AbstractModelTest {
	
	public ModelRadharvesterTest() {
		super.DEFAULT_DELTA *= 10; //Altes HeProMo zeigt im Radharvester-Modul nur EINE Nachkommastelle an.
	}

	@Override
	protected String getCsvPath() {
		return "testcases/Radharvester.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelRadharvester(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektRadharvester ao = new ArbeitsobjektRadharvester();
		ao.setBaumartGruppe(Baumartgruppe.valueOf(testcase[0]));	
		ao.setHolzmenge_m3(Double.valueOf(testcase[1]));
		ao.setVolumenMittelstamm_m3(Double.valueOf(testcase[2]));	
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemRadharvester as = new ArbeitssystemRadharvester();
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[3]));
		as.setKostensatzMaschine1_proH(Double.valueOf(testcase[4]));
		as.setMaschinenKategorie(MaschinenKategorie.valueOf(testcase[5]));
		as.setMaschinenTyp(MaschinenTyp.valueOf(testcase[6]));		
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[7]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[8]));
		as.setUmsetzenBetrag_CHF(Double.valueOf(testcase[9]));
		as.setUmsetzenZeit_h(Double.valueOf(testcase[10]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[11]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[12]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 16;
	}
	
	@Override
	protected int getLastOutputField() {
		return 30;
	}

	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
//		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[0]	);
		result.setZeitMaschine1( 				expectedValues[1]	);
		result.setZeitUmsetzen( 				expectedValues[2]	);
		result.setZeitWeitereAufwaende(			expectedValues[3]	);
		result.setKostenPersonal_proM3( 		expectedValues[4]	);
		result.setKostenMaschine1_proM3( 		expectedValues[5]	);
		result.setKostenUmsetzen_proM3(			expectedValues[6]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[7]	);
		result.setKostenTotal_proM3( 			expectedValues[8]	);
		result.setKostenPersonal_total(			expectedValues[9] 	);
		result.setKostenMaschine1_total( 		expectedValues[10] 	);
		result.setKostenUmsetzen_total( 		expectedValues[11] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[12] 	);
		result.setKostenTotal_total( 			expectedValues[13] 	);
		result.setProduktivitaet_m3ProPsh15( 	expectedValues[14] 	);
		
		
		return result;
	}
	
	
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkDauerDerArbeit(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}

}
