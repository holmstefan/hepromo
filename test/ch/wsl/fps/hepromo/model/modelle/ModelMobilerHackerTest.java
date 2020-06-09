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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker.Hackgutart;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker.HackerTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelMobilerHackerTest extends AbstractModelTest {
	
	//Modell "Mobiler Hacker" konnte nicht exakt nachgebaut werden, deshalb wird
	// hier mit einer grösseren Toleranz getestet.
	@Override
	protected void assertEqualsDynamicDelta(double actual, double expected) {
		double relativeDelta = 1.006;
		double absoluteDelta = expected * (relativeDelta - 1.0);
		
		assertEquals(actual, expected, absoluteDelta);
	}

	@Override
	protected String getCsvPath() {
		return "testcases/MobilerHacker.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMobilerHacker(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektMobilerHacker ao = new ArbeitsobjektMobilerHacker();
		ao.setLaubholzAnteil_Prz(		Integer.valueOf(testcase[0]));
		ao.setAnzahlBaeume(				Integer.valueOf(testcase[1]));
		ao.setHackgutart(				Hackgutart.valueOf(testcase[2]));
		ao.setMittlererBhdAushieb_m(	Double.valueOf(testcase[3]));
		ao.setMittlererZopfDrm_m(		Double.valueOf(testcase[4]));
		ao.setFahrstreckeStrasse_m(		Integer.valueOf(testcase[5]));
		ao.setFahrstreckeFeinerschliessung_m(	Integer.valueOf(testcase[6]));
		ao.setHolzlagerLaenge_m(		Integer.valueOf(testcase[7]));
		ao.setHindernisKategorie(		HindernisKategorie.valueOf(testcase[8]));
		ao.setNeigungsKategorie(		NeigungsKategorie.valueOf(testcase[9]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemMobilerHacker as = new ArbeitssystemMobilerHacker();
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[10]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[11]));
		as.setHackerTyp(					HackerTyp.valueOf(testcase[12]));
		as.setKippContainerVolumen_m3(		Integer.valueOf(testcase[13]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[14]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[15]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[16]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[17]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[18]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[19]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 23;
	}
	
	@Override
	protected int getLastOutputField() {
		return 38;
	}
	
}
