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
import ch.wsl.fps.hepromo.model.ErgebnisHelikopterGesamt;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.MockErgebnisHelikopterGesamt;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterGesamt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelHelikopterGesamtTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/HelikopterGesamt.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelHelikopterGesamt(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektHelikopterGesamt ao = new ArbeitsobjektHelikopterGesamt();
		ao.setHolzmenge_m3(			Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setArbeitsVerfahren(		ArbeitsVerfahren.valueOf(testcase[3]));
		ao.setHolztyp(				Holztyp.valueOf(testcase[4]));
		ao.setBaumartenGruppe(		BaumartenGruppe.valueOf(testcase[5]));
		ao.setHorizontalDistanz_m(	Integer.valueOf(testcase[6]));
		ao.setVertikalDistanz_m(	Integer.valueOf(testcase[7]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemHelikopterGesamt as = new ArbeitssystemHelikopterGesamt();
		as.setHelikopterKosten_proMin(		Double.valueOf(testcase[8]));
		as.setAnflugPauschale(				Integer.valueOf(testcase[9]));
		as.setHelikopterKlasse(				HelikopterKlasse.valueOf(testcase[10]));
		as.setLastVolumen(					Double.valueOf(testcase[11]));
		as.setAnzahlPersonalBeimHolzFliegen(Integer.valueOf(testcase[12]));
		as.setAnzahlPersonalNachHolzFliegen(Integer.valueOf(testcase[13]));
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[14]));
		as.setAnzahlMotorsaegen(			Integer.valueOf(testcase[15]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[16]));
		as.setAnzahlKranfahrzeuge(			Integer.valueOf(testcase[17]));
		as.setKostensatzMaschine2_proH(		Double.valueOf(testcase[18]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[19]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[20]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[21]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[22]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[23]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[24]));
		as.setKalkulationInklLagerplatzarbeit(Boolean.valueOf(testcase[25]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 29;
	}
	
	@Override
	protected int getLastOutputField() {
		return 51;
	}

	
	
	@Override
	protected MockErgebnisHelikopterGesamt parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnisHelikopterGesamt result = new MockErgebnisHelikopterGesamt();
		result.setZeitTotalFliegen(				expectedValues[0]	);
		result.setZeitTotalAbsenkplatz(			expectedValues[1]	);
		result.setZeitHelifirma(				expectedValues[2]	);
		result.setZeitPersonal( 				expectedValues[3]	);
		result.setZeitMaschine1( 				expectedValues[4]	);
		result.setZeitMaschine2( 				expectedValues[5]	);
		result.setZeitUmsetzen( 				expectedValues[6]	);
		result.setZeitWeitereAufwaende(			expectedValues[7]	);

		result.setKostenHeli_proM3(				expectedValues[8]	);
		result.setKostenPersonal_proM3( 		expectedValues[9]	);
		result.setKostenMaschine1_proM3( 		expectedValues[10]	);
		result.setKostenMaschine2_proM3( 		expectedValues[11]	);
		result.setKostenUmsetzen_proM3(			expectedValues[12]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[13]	);
		result.setKostenTotal_proM3( 			expectedValues[14]	);

		result.setKostenHeli_total(				expectedValues[15] 	);
		result.setKostenPersonal_total(			expectedValues[16] 	);
		result.setKostenMaschine1_total( 		expectedValues[17] 	);
		result.setKostenMaschine2_total( 		expectedValues[18] 	);
		result.setKostenUmsetzen_total( 		expectedValues[19] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[20] 	);
		result.setKostenTotal_total( 			expectedValues[21] 	);
		
		result.setRotationszeit( 				expectedValues[22] 	);
		
		
		return result;
	}




	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkDauerDerArbeit(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}

	@Test(dataProvider="csvData")
	public void checkDauerDerArbeitFliegen(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta( ((ErgebnisHelikopterGesamt)ergebnis).getZeitTotalFliegen(), ((MockErgebnisHelikopterGesamt)expectedValues).getZeitTotalFliegen());
	}

	@Test(dataProvider="csvData")
	public void checkDauerDerArbeitAbsenkplatz(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta( ((ErgebnisHelikopterGesamt)ergebnis).getZeitTotalAbsenkplatz(), ((MockErgebnisHelikopterGesamt)expectedValues).getZeitTotalAbsenkplatz());
	}

	@Test(dataProvider="csvData")
	public void checkZeitHelifirma(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta( ((ErgebnisHelikopterGesamt)ergebnis).getZeitHelifirma(), ((MockErgebnisHelikopterGesamt)expectedValues).getZeitHelifirma());
	}	
	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandMaschine2(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitMaschine2(), expectedValues.getZeitMaschine2());
	}
	
	

	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Heli(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta( ((ErgebnisHelikopterGesamt)ergebnis).getKostenHeli_proM3(), ((MockErgebnisHelikopterGesamt)expectedValues).getKostenHeli_proM3());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Maschine2(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine2_proM3(), expectedValues.getKostenMaschine2_proM3());
	}
	
	
	
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalHeli(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta( ((ErgebnisHelikopterGesamt)ergebnis).getKostenHeli_total(), ((MockErgebnisHelikopterGesamt)expectedValues).getKostenHeli_total());
	}
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalMaschine2(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine2_total(), expectedValues.getKostenMaschine2_total());
	}
	
	
	
	@Test(dataProvider="csvData")
	public void checkRotationszeit(@SuppressWarnings("unused") String testcaseName, ErgebnisHelikopterGesamt ergebnis, MockErgebnis expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getRotationszeit(), expectedValues.getRotationszeit());
	}
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkProduktivitaet_m3ProPsh15(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
}
