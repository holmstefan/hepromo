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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen.SchwierigkeitSeitlicherZuzug;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelMobilseilkranGesamtTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/MobilseilkranGesamt.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMobilseilkranGesamt(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektMobilseilkranGesamt ao = new ArbeitsobjektMobilseilkranGesamt();
		
		ao.setHolzmenge_m3(					Double.valueOf(testcase[0]) );
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setMittleresStueckvolumen_m3(	Double.valueOf(testcase[3]) );
		ao.setLinieAbsteckenOhneProjekt(	Boolean.valueOf(testcase[4]) );
		ao.setLinienLaenge_m(				Integer.valueOf(testcase[5]) );
		ao.setMittlereFahrdistanz_m(		Integer.valueOf(testcase[6]) );
		ao.setMittlereDistanzSeitlicherZuzug_m(Integer.valueOf(testcase[7]) );
		ao.setSchwierigkeitSeitlicherZuzug(	SchwierigkeitSeitlicherZuzug.valueOf(testcase[8]) );		
		
		ao.setSeilsystem(			Seilsystem.valueOf(testcase[9]) );
		ao.setMaschinenStandort(	MaschinenStandort.valueOf(testcase[10]) );
	
		List<Integer> listTragseilHoehen = new ArrayList<Integer>();
		for (int i=11; i<=15; i++) {
			int hoehe = Integer.valueOf(testcase[i]);
			if (hoehe != -1) {
				listTragseilHoehen.add(hoehe);
			}
		}
		ao.setStuetzenTragseilHoehen(listTragseilHoehen);
		
		ao.setEndmast(				Boolean.valueOf(testcase[16]) );
		ao.setTragseilHoeheEndmast(	Integer.valueOf(testcase[17]) );
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSeilkranGesamt as = new ArbeitssystemSeilkranGesamt();
		
		as.setKostensatzPersonal1_proH(			Double.valueOf(testcase[18]));
		as.setKostensatzMaschine1_proH(			Double.valueOf(testcase[19]));
		as.setKostensatzMaschine2_proH(			Double.valueOf(testcase[20]));
		as.setLaufzeitKranfahrzeug_Prz(			Integer.valueOf(testcase[21]));
		
		as.setAnzahlPersonenInstallation(		Integer.valueOf(testcase[22]));
		as.setAnzahlPersonenSeilen(				Integer.valueOf(testcase[23]));
		as.setAnzahlPersonenLagerplatz(			Integer.valueOf(testcase[24]));
		as.setEinsatzzeitPersonenLagerplatz_Prz(Integer.valueOf(testcase[25]));
		
		as.setTaeglicheArbeitszeit_Min(			Integer.valueOf(testcase[26]));
		as.setWegzeitenUndPausen_Min(			Integer.valueOf(testcase[27]));
		
		as.setUmsetzenBetrag_CHF(				Integer.valueOf(testcase[28]));
		as.setUmsetzenZeit_h(					Integer.valueOf(testcase[29]));
		as.setWeitereAufwaendeBetrag_CHF(		Integer.valueOf(testcase[30]));
		as.setWeitereAufwaendeZeit_h(			Integer.valueOf(testcase[31]));
		
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 35;
	}
	
	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 18;
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
		result.setKostenUmsetzen_proM3( 		expectedValues[9]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[10]	);
		result.setKostenTotal_proM3( 			expectedValues[11]	);

		result.setKostenPersonal_total(			expectedValues[12] 	);
		result.setKostenMaschine1_total( 		expectedValues[13] 	);
		result.setKostenMaschine2_total( 		expectedValues[14] 	);
		result.setKostenUmsetzen_total( 		expectedValues[15] 	);
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
