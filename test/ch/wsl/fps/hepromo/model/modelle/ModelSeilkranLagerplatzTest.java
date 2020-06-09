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

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranLagerplatz;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelSeilkranLagerplatzTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/SeilkranLagerplatz.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelSeilkranLagerplatz(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektSeilkranLagerplatz ao = new ArbeitsobjektSeilkranLagerplatz();
		ao.setSystemzeitSeilen_psh0proM(Double.valueOf(testcase[0]));
		ao.setHolzmenge_m3(Double.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[2]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[3]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSeilkranLagerplatz as = new ArbeitssystemSeilkranLagerplatz();
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[4]));
		as.setAnzahlPersonal(Integer.valueOf(testcase[5]));
		as.setEinsatzzeitPersonal_Prz(Integer.valueOf(testcase[6]));
		as.setKostensatzMaschine1_proH(Double.valueOf(testcase[7]));
		as.setLaufzeitKranfahrzeug_Prz(Integer.valueOf(testcase[8]));
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[9]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[10]));
		as.setUmsetzenBetrag_CHF(Double.valueOf(testcase[11]));
		as.setUmsetzenZeit_h(Double.valueOf(testcase[12]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[13]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[14]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 18;
	}

	@Override
	protected int getLastOutputField() {
		return getFirstOutputField() + 15;
	}

}
