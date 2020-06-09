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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.BeizugsdistanzArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.MittlereFahrentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014.Maschinenkategorie;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelSchlepper2014Test extends AbstractModel2014Test {
	
	@Override
	protected void assertEqualsDynamicDelta(double actual, double expected) {
		if (expected < 20) {
			assertEquals(actual, expected, 0.0051);
		}
		else {
			double relativeDelta = 1.00025;
			double absoluteDelta = expected * (relativeDelta - 1.0);
			assertEquals(actual, expected, absoluteDelta);
		}
	}

	@Override
	protected String getCsvPath() {
		return "testcases/Schlepper2014.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelSchlepper2014(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektSchlepper2014 ao = new ArbeitsobjektSchlepper2014();
		ao.setHolzmenge_m3(							Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(		Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(			Double.valueOf(testcase[2]));
		ao.setMittlererStueckinhalt(				Double.valueOf(testcase[3]));
		ao.setMittlereFahrentfernung(				MittlereFahrentfernung.valueOf(testcase[4]));
		
		BeizugsdistanzArrayWithSelection beizugsdistanz = ao.getBeizugsdistanzArrayWithSelection();
		beizugsdistanz.setSelection( beizugsdistanz.allValues[	Integer.valueOf(testcase[5])	]);
		beizugsdistanz.allValues[3].setWert(					Double.valueOf(testcase[6])		);
		
		return ao;
	}

	@Override
	protected ArbeitssystemSchlepper2014 parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSchlepper2014 as = new ArbeitssystemSchlepper2014();
		as.setMaschinenkategorie(			Maschinenkategorie.valueOf(testcase[7]));
		as.setKostensatzPersonal1_proH(			Double.valueOf(testcase[8]));
		as.setKostensatzMaschine1_proH(			Double.valueOf(testcase[9]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[10]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[11]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[12]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[13]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[14]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[15]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 19;
	}
	
	@Override
	protected int getLastOutputField() {
		return 35;
	}
	
}
