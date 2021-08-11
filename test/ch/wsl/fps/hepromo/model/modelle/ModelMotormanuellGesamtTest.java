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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell.KronenLaengenKlasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten.IndustrieholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten.SchichtholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten.StammholzStuecklaengen;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelMotormanuellGesamtTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/MotormanuellGesamt.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMotormanuellGesamt(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektMotormanuellGesamt ao = new ArbeitsobjektMotormanuellGesamt();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setMassenmittelstamm_m3iR(			Double.valueOf(testcase[1]));
		ao.setAnteilFaellenMitHandseilzug_Prz(	Integer.valueOf(testcase[2]));
		ao.setAnteilEntrindenVonHand_Prz(		Integer.valueOf(testcase[3]));
		ao.setBaumartenGruppe(					Baumartgruppe.valueOf(testcase[4]));
		ao.setKronenLaengenKlasse(				KronenLaengenKlasse.valueOf(testcase[5]));
		ao.setHangneigung(						Hangneigung.valueOf(testcase[6]));
		ao.setHindernisse(						Hindernisse.valueOf(testcase[7]));
		ao.setAnteilIndustrieholz_Prz(			Integer.valueOf(testcase[8]));
		ao.setAnteilSchichtholz_Prz(			Integer.valueOf(testcase[9]));
		ao.setAnteilSpalten_Prz(				Integer.valueOf(testcase[10]));
		ao.setStammholzStuecklaengen(			StammholzStuecklaengen.valueOf(testcase[11]));
		ao.setKantenBrechen(					Boolean.valueOf(testcase[12]));
		ao.setIndustrieholzStuecklaengen(		IndustrieholzStuecklaengen.valueOf(testcase[13]));
		ao.setSchichtholzStuecklaengen(			SchichtholzStuecklaengen.valueOf(testcase[14]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		Arbeitssystem as = new Arbeitssystem();
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[15]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[16]));
		as.setKostensatzMaschine2_proH(		Double.valueOf(testcase[17]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[18]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[19]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[20]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[21]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[22]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[23]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 27;
	}
	
	@Override
	protected int getLastOutputField() {
		return 43;
	}

	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnis result = new MockErgebnis();
		result.setZeitPersonal( 				expectedValues[0]	);
		result.setZeitMaschine1( 				expectedValues[1]	);
		result.setZeitMaschine2( 				expectedValues[2]	);
		result.setZeitUmsetzen( 				expectedValues[3]	);
		result.setZeitWeitereAufwaende(			expectedValues[4]	);

		result.setKostenPersonal_proM3( 		expectedValues[5]	);
		result.setKostenMaschine1_proM3( 		expectedValues[6]	);
		result.setKostenMaschine2_proM3( 		expectedValues[7]	);
		result.setKostenUmsetzen_proM3(			expectedValues[8]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[9]	);
		result.setKostenTotal_proM3( 			expectedValues[10]	);

		result.setKostenPersonal_total(			expectedValues[11] 	);
		result.setKostenMaschine1_total( 		expectedValues[12] 	);
		result.setKostenMaschine2_total( 		expectedValues[13] 	);
		result.setKostenUmsetzen_total( 		expectedValues[14] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[15] 	);
		result.setKostenTotal_total( 			expectedValues[16] 	);
		
		
		return result;
	}

	
	
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkDauerDerArbeit(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {	
		//disabled test
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
	
	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkProduktivitaet_m3ProPsh15(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
}
