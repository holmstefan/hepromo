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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelKonventionellerSeilkranGesamtTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/KonventionellerSeilkranGesamt.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelKonventionellerSeilkranGesamt(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektKonventionellerSeilkranGesamt ao = new ArbeitsobjektKonventionellerSeilkranGesamt();
		ao.setMittleresStueckvolumen_m3(		Double.valueOf(testcase[0]));
		ao.setHolzmenge_m3(						Double.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[2]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[3]));
		ao.setStueckLaenge_m(					Integer.valueOf(testcase[4]));
		ao.setEingriffsart(						Eingriffsart.valueOf(testcase[5]));
		ao.setHolzseilort(						HolzSeilOrt.valueOf(testcase[6]));
		ao.setHangneigung_Prz(					Integer.valueOf(testcase[7]));
		ao.setHindernisse(						Hindernisse.valueOf(testcase[8]));
		ao.setLinieAbsteckenOhneProjekt(		Boolean.valueOf(testcase[9]));
		ao.setLinienLaenge_m(					Integer.valueOf(testcase[10]));
		ao.setFahrtrichtung(					Fahrtrichtung.valueOf(testcase[11]));
		ao.setMittlereFahrdistanz_m(			Integer.valueOf(testcase[12]));
		ao.setMittlereDistanzSeitlicherZuzug_m(	Integer.valueOf(testcase[13]));
		ao.setAnzahlStuetzen(					Integer.valueOf(testcase[14]));
		ao.setAnzahlEndmasten(					Integer.valueOf(testcase[15]));
		ao.setTragseilhoeheBestand_m(			Integer.valueOf(testcase[16]));
		ao.setTragseilhoeheLagerplatz_m(		Integer.valueOf(testcase[17]));
		ao.setMontageIstSeilverlegung(			Boolean.valueOf(testcase[18]));
		ao.setMontageWindenTransportart(		WindenTransport.valueOf(testcase[19]));
		ao.setMontageWindenStandort(			WindenStandort.valueOf(testcase[20]));
		ao.setMontageDistanzWindenselbstfahrt_m(Integer.valueOf(testcase[21]));
		ao.setDemontageIstSeilverlegung(		Boolean.valueOf(testcase[22]));
		ao.setDemontageWindenTransportart(		WindenTransport.valueOf(testcase[23]));
		ao.setDemontageWindenStandort(			WindenStandort.valueOf(testcase[24]));
		ao.setDemontageDistanzWindenselbstfahrt_m(Integer.valueOf(testcase[25]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSeilkranGesamt as = new ArbeitssystemSeilkranGesamt();
		as.setKostensatzPersonal1_proH(			Double.valueOf(testcase[26]));
		as.setKostensatzMaschine1_proH(			Double.valueOf(testcase[27]));
		as.setKostensatzMaschine2_proH(			Double.valueOf(testcase[28]));
		as.setLaufzeitKranfahrzeug_Prz(			Integer.valueOf(testcase[29]));
		
		as.setAnzahlPersonenInstallation(		Integer.valueOf(testcase[30]));
		as.setAnzahlPersonenSeilen(				Integer.valueOf(testcase[31]));
		as.setAnzahlPersonenLagerplatz(			Integer.valueOf(testcase[32]));
		as.setEinsatzzeitPersonenLagerplatz_Prz(Integer.valueOf(testcase[33]));

		as.setTaeglicheArbeitszeit_Min(			Integer.valueOf(testcase[34]));
		as.setWegzeitenUndPausen_Min(			Integer.valueOf(testcase[35]));
		
		as.setUmsetzenBetrag_CHF(				Integer.valueOf(testcase[36]));
		as.setUmsetzenZeit_h(					Integer.valueOf(testcase[37]));
		as.setWeitereAufwaendeBetrag_CHF(		Integer.valueOf(testcase[38]));
		as.setWeitereAufwaendeZeit_h(			Integer.valueOf(testcase[39]));
		
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 43;
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
