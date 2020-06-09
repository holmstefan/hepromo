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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranDemontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelKonventionellerSeilkranDemontageTest extends ModelKonventionellerSeilkranMontageDemontageTest {

	@Override
	protected String getCsvPath() {
		return "testcases/KonventionellerSeilkranDemontage.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelKonventionellerSeilkranDemontage(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektKonventionellerSeilkranDemontage ao = new ArbeitsobjektKonventionellerSeilkranDemontage();
		ao.setHolzmenge_m3(Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setLinienLaenge_m(			Integer.valueOf(testcase[3]));
		ao.setAnzahlStuetzen(			Integer.valueOf(testcase[4]));
		ao.setAnzahlEndmasten(			Integer.valueOf(testcase[5]));
		ao.setDemontageIstSeilverlegung(Boolean.valueOf(testcase[6]));
		ao.setWindenTransport(			WindenTransport.valueOf(testcase[7]));
		ao.setWindenStandort(			WindenStandort.valueOf(testcase[8]));
		ao.setDistanzWindenSelbstfahrt(	Integer.valueOf(testcase[9]));
		return ao;
	}
	
}
