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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AbstandRueckegasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Energieholzanfall;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Rueckeentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018.Forwardertyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018.ErgebnisAnzeige;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelForwarder2018EnergieholzTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/Forwarder2018Energieholz.csv";
	}

	@Override
	protected ModelForwarder2018 getModel(HeProMoInputData inputData) {
		ModelForwarder2018 model = new ModelForwarder2018(inputData);
		model.getCalculator().setErgebnisAnzeige(ErgebnisAnzeige.Energieholz);
		return model;
	}

	@Override
	protected ArbeitsobjektForwarder2018 parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektForwarder2018 ao = new ArbeitsobjektForwarder2018();
		ao.setHolzmenge_m3(						Double.valueOf(				testcase[0]) );
		ao.setEnergieholzmenge_m3iR(			Double.valueOf(				testcase[0]) );
		ao.setBhdMit_cm(						Integer.valueOf(			testcase[1]) );
		ao.setRueckeentfernung(					Rueckeentfernung.valueOf(	testcase[2]) );
		ao.setHangneigung(						Hangneigung.valueOf(		testcase[3]) );
		ao.setAnzahlSortimente(					AnzahlSortimente.valueOf(	testcase[4]) );
		ao.setErschwernisse(					Erschwernisse.valueOf(		testcase[5]) );
		ao.setAbstandRueckegasse(				AbstandRueckegasse.valueOf(	testcase[6]) );
		ao.setZopfdurchmesser_cm(				Integer.valueOf(			testcase[7]) );
		
		Energieholzanfall[] allEnergieholzanfall = ao.getAllEnergieholzanfall();
		for (Energieholzanfall e : allEnergieholzanfall) {
			if (e.name.equals(testcase[8])) {
				ao.setEnergieholzanfall_m3iRproHa(e);
				break;
			}		
			//"benutzerdefiniert" ist der letzte Eintrag im Array -> Wenn also bisher der
			//Energieholzanfall noch nicht gesetzt wurde, kann man davon ausgehen, dass 
			//eine Zahl im Testfile steht, der dem benutzerdefinierten Wert entspricht.
			if (e.name.equals("benutzerdefiniert")) {
				e.setEnergieholzanfall_m3ProHa( Integer.valueOf(testcase[8]));
				ao.setEnergieholzanfall_m3iRproHa(e);
				break;
			}
		}
		
		ao.setEinsatzThw(						Boolean.valueOf(			testcase[9]) );
		ao.setAnzahlRueckegassen(				Integer.valueOf(			testcase[10]) );
		
		return ao;
	}

	@Override
	protected ArbeitssystemForwarder2018 parseArbeitssystemData(String[] testcase) {
		ArbeitssystemForwarder2018 as = new ArbeitssystemForwarder2018();
		as.setForwardertyp(  				Forwardertyp.valueOf(	testcase[11]) );
		as.setKostensatzPersonal1_proH(		Double.valueOf(			testcase[12]) );
		as.setKostensatzMaschine1_proH(		Double.valueOf(			testcase[13]) );
		as.setKostensatzMaschine2_proH(		Double.valueOf(			testcase[14]) );
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(		testcase[15]) );
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(		testcase[16]) );
		as.setUmsetzenBetrag_CHF(			Double.valueOf(			testcase[17]) );
		as.setUmsetzenZeit_h(				Double.valueOf(			testcase[18]) );
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(			testcase[19]) );
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(			testcase[20]) );
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
		result.setKostenUmsetzen_total(			expectedValues[15] 	);
		result.setKostenWeitereAufwaende_total( expectedValues[16] 	);
		result.setKostenTotal_total( 			expectedValues[17] 	);

		result.setProduktivitaet_m3ProPsh15(	expectedValues[18] 	);
		
		
		return result;
	}

	@Override
	protected int getFirstOutputField() {
		return 24;
	}
	
	@Override
	protected int getLastOutputField() {
		return 42;
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
