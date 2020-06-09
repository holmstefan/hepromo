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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell.KronenLaengenKlasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelMotormanuellEntastenTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/EntastenMotormanuell.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMotormanuellEntasten(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektEntastenMotormanuell ao = new ArbeitsobjektEntastenMotormanuell();
		ao.setBaumartenGruppe(Baumartgruppe.valueOf(testcase[0]));
		ao.setMassenmittelstamm_m3iR(Double.valueOf(testcase[1]));		
		ao.setHolzmenge_m3(Double.valueOf(testcase[2]));
		ao.setKronenLaengenKlasse(KronenLaengenKlasse.valueOf(testcase[3]));
		ao.setHangneigung(Hangneigung.valueOf(testcase[4]));
		ao.setHindernisse(Hindernisse.valueOf(testcase[5]));
		return ao;
	}

	
	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		Arbeitssystem as = new Arbeitssystem();
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[6]));
		as.setKostensatzMaschine1_proH(Double.valueOf(testcase[7]));
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[8]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[9]));
		as.setUmsetzenBetrag_CHF(Double.valueOf(testcase[10]));
		as.setUmsetzenZeit_h(Double.valueOf(testcase[11]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[12]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[13]));
		return as;
	}

	
	@Override
	protected int getFirstOutputField() {
		return 17;
	}

}
