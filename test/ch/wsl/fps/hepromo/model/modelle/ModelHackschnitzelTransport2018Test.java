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
import ch.wsl.fps.hepromo.model.ErgebnisHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.MockErgebnisHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHackschnitzelTransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHackschnitzelTransport2018Test extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/HackschnitzelTransport2018.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelHackschnitzelTransport2018(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektHackschnitzelTransport2018 ao = new ArbeitsobjektHackschnitzelTransport2018();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setAufnehmenBeladenerMulde(			Boolean.valueOf(testcase[1]) == false);
		ao.setZielsortiment(					Zielsortiment.valueOf(testcase[2]));
		ao.setHackerMotorleistung(ArbeitsobjektHackschnitzelTransport2018.getBenutzerdefinierteHackerMotorleistung(Integer.valueOf(testcase[3])));
		ao.setDistanzWaldstrasse_km(			Double.valueOf(testcase[4]) );
		ao.setDistanzInnerAusserorts_km(		Double.valueOf(testcase[5]) );
		ao.setDistanzAutobahn_km(				Double.valueOf(testcase[6]) );
		
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemHackschnitzelTransport2018 as = new ArbeitssystemHackschnitzelTransport2018();
		
		as.setTaeglicheArbeitszeit_Min(100); //Wert spielt keine Rolle hier, darf aber nicht 0 sein!
		as.setWegzeitenUndPausen_Min(0); //Pausen sind in Kostensatz Fahrer eingerechnet!
		
		as.setMuldeninhalt_Srm(				Double.valueOf(testcase[7]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[8]));	
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[9]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[10]));
		
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 14;
	}
	
	@Override
	protected int getLastOutputField() {
		return 29;
	}
	
	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnisHackschnitzelTransport2018 result = new MockErgebnisHackschnitzelTransport2018();
		result.setZeitTotal( 					expectedValues[ 0]	);
		result.setZeitMaschine1( 				expectedValues[ 1]	);
		result.setZeitWeitereAufwaende(			expectedValues[ 2]	);

		result.setKostenMaschine1_proM3( 		expectedValues[ 3]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[ 4]	);
		result.setKostenTotal_proM3( 			expectedValues[ 5]	);

		result.setKostenMaschine1_total(		expectedValues[ 6] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[ 7] 	);
		result.setKostenTotal_total( 			expectedValues[ 8] 	);

		result.setProduktivitaet_SrmProPsh15(	expectedValues[ 9] 	);

		result.setAnzahlZyklen(			  (int) expectedValues[10] 	);

		result.setZeitLaden_h(					expectedValues[11] 	);
		result.setZeitLastfahrt_h(				expectedValues[12] 	);
		result.setZeitEntladen_h(				expectedValues[13] 	);
		result.setZeitLeerfahrt_h(				expectedValues[14] 	);
		result.setZeitProZyklus_h(				expectedValues[15] 	);
		
		return result;
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkZeitaufwandPersonal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkZeitaufwandUmsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenProM3Personal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenProM3Umsetzen(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkKostenTotalPersonal(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
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
	
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_SrmProPsh15(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getProduktivitaet(), expectedValues.getProduktivitaet_SrmProPsh15());
	}
	
	@Test(dataProvider="csvData")
	public void checkAnzahlZyklen(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getAnzahlZyklen(), expectedValues.getAnzahlZyklen());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitLaden_h(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitLaden_h(), expectedValues.getZeitLaden_h());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitLastfahrt_h(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitLastfahrt_h(), expectedValues.getZeitLastfahrt_h());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitEntladen_h(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitEntladen_h(), expectedValues.getZeitEntladen_h());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitLeerfahrt_h(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitLeerfahrt_h(), expectedValues.getZeitLeerfahrt_h());
	}
	
	@Test(dataProvider="csvData")
	public void checkZeitProZyklus_h(@SuppressWarnings("unused") String testcaseName, ErgebnisHackschnitzelTransport2018 ergebnis, MockErgebnisHackschnitzelTransport2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitProZyklus_h(), expectedValues.getZeitProZyklus_h());
	}
	
}
