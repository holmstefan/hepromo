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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten.IndustrieholzStuecklaengen;
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
public class ModelMotormanuellIhLangAufarbeitenTest extends AbstractModelTest {
	
	@Override
	protected String getCsvPath() {
		return "testcases/IndustrieholzAufarbeiten.csv";
	}

	
	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelMotormanuellIhLangAufarbeiten(inputData);
	}

	
	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektIhLangAufarbeiten ao = new ArbeitsobjektIhLangAufarbeiten();
		ao.setBaumartenGruppe(Baumartgruppe.valueOf(testcase[0]));
		ao.setMassenmittelstamm_m3iR(Double.valueOf(testcase[1]));		
		ao.setHolzmenge_m3(Double.valueOf(testcase[2]));
		ao.setAnteilIndustrieholz_Prz(Integer.valueOf(testcase[3]));
		ao.setAnteilSchichtholz_Prz(Integer.valueOf(testcase[4]));
		ao.setStuecklaengen(IndustrieholzStuecklaengen.valueOf(testcase[5]));
		ao.setHangneigung(Hangneigung.valueOf(testcase[6]));
		ao.setHindernisse(Hindernisse.valueOf(testcase[7]));
		return ao;
	}

	
	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		Arbeitssystem as = new Arbeitssystem();
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[8]));
		as.setKostensatzMaschine1_proH(Double.valueOf(testcase[9]));
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[10]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[11]));
		as.setUmsetzenBetrag_CHF(Double.valueOf(testcase[12]));
		as.setUmsetzenZeit_h(Double.valueOf(testcase[13]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[14]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[15]));
		return as;
	}

	
	@Override
	protected int getFirstOutputField() {
		return 19;
	}

}
