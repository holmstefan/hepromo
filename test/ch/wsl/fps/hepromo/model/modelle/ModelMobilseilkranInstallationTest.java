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

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelMobilseilkranInstallationTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/MobilseilkranInstallation.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMobilseilkranInstallation(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektMobilseilkranInstallation ao = new ArbeitsobjektMobilseilkranInstallation();
		ao.setHolzmenge_m3(Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setSeilsystem(			Seilsystem.valueOf(testcase[3]) );
		ao.setMaschinenStandort(	MaschinenStandort.valueOf(testcase[4]) );
		ao.setLinienLaenge_m(		Integer.valueOf(testcase[5]) );
	
		List<Integer> listTragseilHoehen = new ArrayList<>();
		for (int i=6; i<=10; i++) {
			int hoehe = Integer.valueOf(testcase[i]);
			if (hoehe != -1) {
				listTragseilHoehen.add(hoehe);
			}
		}
		ao.setStuetzenTragseilHoehen(listTragseilHoehen);
		
		ao.setEndmast(				Boolean.valueOf(testcase[11]) );
		ao.setTragseilHoeheEndmast(	Integer.valueOf(testcase[12]) );
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSeilkran as = new ArbeitssystemSeilkran();
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[13]));
		as.setAnzahlPersonal(				Integer.valueOf(testcase[14]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[15]));
		as.setAnteilSeilkranLaufzeit_Prz(	Integer.valueOf(testcase[16]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[17]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[18]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[19]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[20]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 24;
	}
	
	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 11;
	}

	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[1]	);
		result.setZeitMaschine1( 				expectedValues[2]	);
		result.setZeitWeitereAufwaende(			expectedValues[3]	);

		result.setKostenPersonal_proM3(			expectedValues[4] 	);
		result.setKostenMaschine1_proM3( 		expectedValues[5] 	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[6] 	);
		result.setKostenTotal_proM3( 			expectedValues[7] 	);

		result.setKostenPersonal_total(			expectedValues[8] 	);
		result.setKostenMaschine1_total( 		expectedValues[9] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[10] 	);
		result.setKostenTotal_total( 			expectedValues[11] 	);
		
		
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
	
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkProduktivitaet_m3ProPsh15(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
}
