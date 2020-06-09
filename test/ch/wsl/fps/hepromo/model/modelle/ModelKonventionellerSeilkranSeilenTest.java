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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelKonventionellerSeilkranSeilenTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/KonventionellerSeilkranSeilen.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelKonventionellerSeilkranSeilen(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektKonventionellerSeilkranSeilen ao = new ArbeitsobjektKonventionellerSeilkranSeilen();
		ao.setMittleresStueckvolumen_m3(	Double.valueOf(testcase[0]));
		ao.setHolzmenge_m3(					Double.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[2]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[3]));
		ao.setStuecklaenge_m(				Integer.valueOf(testcase[4]));
		ao.setEingriffsart(					Eingriffsart.valueOf(testcase[5]));
		ao.setHolzSeilOrt(					HolzSeilOrt.valueOf(testcase[6]));
		ao.setHangneigung_Prz(				Integer.valueOf(testcase[7]));
		ao.setHindernisse(					Hindernisse.valueOf(testcase[8]));
		ao.setFahrtrichtung(					Fahrtrichtung.valueOf(testcase[9]));
		ao.setMittlereFahrdistanz_m(			Integer.valueOf(testcase[10]));
		ao.setMittlereDistanzSeitlicherZuzug_m(	Integer.valueOf(testcase[11]));
		ao.setTragseilHoeheLagerplatz_m(		Integer.valueOf(testcase[12]));
		ao.setTragseilHoeheBestand_m(			Integer.valueOf(testcase[13]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSeilkran as = new ArbeitssystemSeilkran();
		as.setKostensatzPersonal1_proH(				Double.valueOf(testcase[14]));
		as.setAnzahlPersonal(					Integer.valueOf(testcase[15]));
		as.setKostensatzMaschine1_proH(				Double.valueOf(testcase[16]));
		as.setAnteilSeilkranLaufzeit_Prz(		Integer.valueOf(testcase[17]));
		as.setTaeglicheArbeitszeit_Min(			Integer.valueOf(testcase[18]));
		as.setWegzeitenUndPausen_Min(			Integer.valueOf(testcase[19]));
		as.setWeitereAufwaendeBetrag_CHF(		Double.valueOf(testcase[20]));
		as.setWeitereAufwaendeZeit_h(			Double.valueOf(testcase[21]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 25;
	}
	
	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 12;
	}

	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[1]	);
		result.setZeitMaschine1( 				expectedValues[2]	);
		result.setZeitWeitereAufwaende(			expectedValues[3]	);

		result.setKostenPersonal_proM3( 		expectedValues[4]	);
		result.setKostenMaschine1_proM3( 		expectedValues[5]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[6]	);
		result.setKostenTotal_proM3( 			expectedValues[7]	);

		result.setKostenPersonal_total(			expectedValues[8] 	);
		result.setKostenMaschine1_total( 		expectedValues[9] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[10] 	);
		result.setKostenTotal_total( 			expectedValues[11] 	);
		
		result.setProduktivitaet_m3ProPsh15(	expectedValues[12] 	);
		
		
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
	
}
