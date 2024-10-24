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
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.MockErgebnis;
import ch.wsl.fps.hepromo.model.MockErgebnisKombiseilgeraet2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelKombiseilgeraet2018GesamtTest extends ModelKombiseilgeraet2018AbstractTest {

	@Override
	protected String getCsvPath() {
		return "testcases/Kombiseilgeraet2018Gesamt.csv";
	}
	
	
	@Override
	protected void setProduktivitaetInResult(MockErgebnisKombiseilgeraet2018 result, double expectedValue) {
		result.setProduktivitaet_m3oRproWSH(expectedValue);
	}

	
	@Override
	@Test(dataProvider="csvData", enabled=false)
	public void checkProduktivitaet_m3ProPsh15(String testcaseName, Ergebnis ergebnis, MockErgebnis expectedValues) {
		//disabled test
	}
	
	@Test(dataProvider="csvData")
	public void checkProduktivitaet_m3ProWSH(@SuppressWarnings("unused") String testcaseName, Ergebnis ergebnis, MockErgebnisKombiseilgeraet2018 expectedValues) {
		assertEqualsDynamicDelta(ergebnis.getProduktivitaet(ProdEinheit.M3_OR_PRO_WSH), expectedValues.getProduktivitaet_m3oRproWSH());
	}
}
