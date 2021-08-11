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
import ch.wsl.fps.hepromo.model.MockErgebnisKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018.EquipeAnzahlPersonen;
import ch.wsl.fps.hepromo.model.calc.CalculatorKombiseilgeraet2018.ErgebnisAnzeige;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class ModelKombiseilgeraet2018AbstractTest extends AbstractModelTest {
	
	private ErgebnisAnzeige ergebnisAnzeige = null;


	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		if (ergebnisAnzeige == null) {
			throw new IllegalStateException("ErgebnisAnzeige noch nicht gesetzt!");
		}
		ModelKombiseilgeraet2018 model = new ModelKombiseilgeraet2018(inputData);
		model.getCalculator().setErgebnisAnzeige(ergebnisAnzeige);
		return model;
	}
	
	
	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektKombiseilgeraet2018 ao = new ArbeitsobjektKombiseilgeraet2018();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setErschwernisse(					Erschwernisse.valueOf(testcase[3]));
		ao.setLaengeProAufstellung_m(			Integer.valueOf(testcase[4]));
		ao.setAnzahlStuetzenProAufstellung(		Integer.valueOf(testcase[5]));
		ao.setAnzahlAufstellungen(				Integer.valueOf(testcase[6]));
		ao.setHangneigung_Prz(					Integer.valueOf(testcase[7]));
		ao.setAnteilLaubholz_Prz(				Integer.valueOf(testcase[8]));
		
		if (ergebnisAnzeige == null) {
			//beim ersten Durchgang setzen
			ergebnisAnzeige = ErgebnisAnzeige.valueOf(testcase[9]);
		}
		else {
			//ErgebnisAnzeige darf nach dem ersten Setzen nicht mehr verändert werden!
			if (ergebnisAnzeige != ErgebnisAnzeige.valueOf(testcase[9])) {
				throw new RuntimeException("Die Ergebnisanzeige darf innerhalb einer csv-Datei nicht ändern!");
			}
		}
		
		return ao;
	}
	

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemKombiseilgeraet2018 as = new ArbeitssystemKombiseilgeraet2018();
		as.setEquipeAnzahlPersonen(		EquipeAnzahlPersonen.parseFromDouble(Double.valueOf(testcase[10])));
		as.setAnteilEinsatzzeitVerzugsfahrzeug_Prz(	Integer.valueOf(testcase[11]));	
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[12]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[13]));	
		as.setKostensatzMaschine2_proH(		Double.valueOf(testcase[14]));
		as.setKostensatzVerzugsfahrzeug_proH(Double.valueOf(testcase[15]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[16]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[17]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[18]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[19]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[20]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[21]));
		
		return as;
	}
	

	@Override
	protected int getFirstOutputField() {
		return 25;
	}
	
	
	@Override
	protected int getLastOutputField() {
		return 46;
	}
	
	
	@Override
	protected MockErgebnis parseExpectedValuesAsErgebnis(String[] testcase) {
		double[] expectedValues = parseExpectedValues(testcase);
		
		MockErgebnisKombiseilgeraet2018 result = new MockErgebnisKombiseilgeraet2018();
		result.setZeitTotal( 					expectedValues[0]	);
		result.setZeitPersonal( 				expectedValues[1]	);
		result.setZeitMaschine1( 				expectedValues[2]	);
		result.setZeitMaschine2( 				expectedValues[3]	);
		result.setZeitVerzugsfahrzeug(			expectedValues[4]	);
		result.setZeitUmsetzen( 				expectedValues[5]	);
		result.setZeitWeitereAufwaende(			expectedValues[6]	);

		result.setKostenPersonal_proM3( 		expectedValues[7]	);
		result.setKostenMaschine1_proM3( 		expectedValues[8]	);
		result.setKostenMaschine2_proM3( 		expectedValues[9]	);
		result.setKostenVerzugsfahrzeug_proM3(	expectedValues[10]	);
		result.setKostenUmsetzen_proM3(			expectedValues[11]	);
		result.setKostenWeitereAufwaende_proM3( expectedValues[12]	);
		result.setKostenTotal_proM3( 			expectedValues[13]	);

		result.setKostenPersonal_total(			expectedValues[14] 	);
		result.setKostenMaschine1_total( 		expectedValues[15] 	);
		result.setKostenMaschine2_total( 		expectedValues[16] 	);
		result.setKostenVerzugsfahrzeug_total(	expectedValues[17] 	);
		result.setKostenUmsetzen_total(			expectedValues[18] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[19] 	);
		result.setKostenTotal_total( 			expectedValues[20] 	);

		setProduktivitaetInResult(result, expectedValues[21]);
		
		
		return result;
	}
	
	
	/**
	 * Die Einheit der Produktivität unterscheidet sich je nach Arbeitsschritt,
	 * deshalb muss in dieser Methode die korrekte Methode aufgerufen werden.
	 * 
	 * @param result MockErgebnis mit den erwarteten Ergebniswerten
	 * 
	 * @param expectedValue Erwarteter Wert für Produktivität, in der Einheit 
	 * des entsprechenden Arbeitsschrittes. Dieser Wert muss in dieser Methode 
	 * in die MockErgebnis-Instanz übertragen werden.
	 */
	protected void setProduktivitaetInResult(MockErgebnisKombiseilgeraet2018 result, double expectedValue) {
		result.setProduktivitaet_m3ProPsh15(expectedValue);
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


	
	@Test(dataProvider="csvData")
	public void checkZeitaufwandVerzugsfahrzeug(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnisKombiseilgeraet2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getZeitMaschine3(), expectedValues.getZeitVerzugsfahrzeug());
	}
	
	
	@Test(dataProvider="csvData")
	public void checkKostenProM3Verzugsfahrzeug(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnisKombiseilgeraet2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine3_proM3(), expectedValues.getKostenVerzugsfahrzeug_proM3());
	}
	
	
	@Test(dataProvider="csvData")
	public void checkKostenTotalVerzugsfahrzeug(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnisKombiseilgeraet2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getKostenMaschine3_total(), expectedValues.getKostenVerzugsfahrzeug_total());
	}
	
}
