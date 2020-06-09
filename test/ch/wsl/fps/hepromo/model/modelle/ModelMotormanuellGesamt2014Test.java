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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014.Region;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMotormanuell2014;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelMotormanuellGesamt2014Test extends AbstractModel2014Test {
	
	@Override
	protected void assertEqualsDynamicDelta(double actual, double expected) {
		if (expected < 10) {
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
		return "testcases/Motormanuell2014.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMotormanuellGesamt2014(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektMotormanuellGesamt2014 ao = new ArbeitsobjektMotormanuellGesamt2014();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setBhd_cm(							Integer.valueOf(testcase[3]));
		ao.setAnteilLaubholz_Prz(				Integer.valueOf(testcase[4]));
		ao.setAnteilKiefer_Prz(					Integer.valueOf(testcase[5]));
		ao.setRegion(							Region.valueOf(testcase[6]));
		return ao;
	}

	@Override
	protected ArbeitssystemMotormanuell2014 parseArbeitssystemData(String[] testcase) {
		ArbeitssystemMotormanuell2014 as = new ArbeitssystemMotormanuell2014();
		as.setKostensatzPersonal1_proH(			Double.valueOf(testcase[7])); 
		as.setEinsatzanteilPersonal1_Prz(	Integer.valueOf(testcase[8]));
		as.setKostensatzPersonal2_proH(			Double.valueOf(testcase[9]));
		as.setEinsatzanteilPersonal2_Prz(	Integer.valueOf(testcase[10]));
		as.setKostensatzPersonal3_proH(			Double.valueOf(testcase[11]));
		as.setEinsatzanteilPersonal3_Prz(	Integer.valueOf(testcase[12]));
		as.setKostensatzPersonal4_proH(			Double.valueOf(testcase[13]));
		as.setEinsatzanteilPersonal4_Prz(	Integer.valueOf(testcase[14]));
		
		as.setKostensatzMaschine1_proH(			Double.valueOf(testcase[15]));
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
		return 41;
	}
	
}
