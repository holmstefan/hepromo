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

import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten.StammholzStuecklaengen;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.calc.CalculatorStammholzAufarbeiten;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMotormanuellStammholzAufarbeiten extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelMotormanuellStammholzAufarbeiten() {
		super.arbeitsobjekt = new ArbeitsobjektStammholzAufarbeiten();
		getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Fichte);
		getArbeitsobjekt().setMassenmittelstamm_m3iR(0.3);
		getArbeitsobjekt().setHolzmenge_m3(100);
		getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_0bis30);
		getArbeitsobjekt().setHindernisse(Hindernisse.Keine);
		getArbeitsobjekt().setStuecklaengen(StammholzStuecklaengen.Von6Bis10m);
		getArbeitsobjekt().setKantenBrechen(false);
		
		super.arbeitssystem = new Arbeitssystem();
		getArbeitssystem().setKostensatzPersonal1_proH(55);
		getArbeitssystem().setKostensatzMaschine1_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setLabelMaschine1("Motorsäge");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorStammholzAufarbeiten(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMotormanuellStammholzAufarbeiten(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorStammholzAufarbeiten(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektStammholzAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektStammholzAufarbeiten) arbeitsobjekt;
	}


	@Override
	protected String getModelName() {
		return "Motormanuell: Stammholz aufarbeiten";
	}

}
