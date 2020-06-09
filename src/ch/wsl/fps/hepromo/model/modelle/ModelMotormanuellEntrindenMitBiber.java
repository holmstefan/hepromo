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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden.AnteilShUndIh;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.calc.CalculatorEntrindenMitBiber;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMotormanuellEntrindenMitBiber extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelMotormanuellEntrindenMitBiber() {
		super.arbeitsobjekt = new ArbeitsobjektEntrinden();
		getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Fichte);
		getArbeitsobjekt().setMassenmittelstamm_m3iR(0.3);
		getArbeitsobjekt().setHolzmenge_m3(200);
		getArbeitsobjekt().setAnteilShUndIhLang(AnteilShUndIh.Anteil_0bis10);
		getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_0bis30);
		getArbeitsobjekt().setHindernisse(Hindernisse.Keine);
		
		super.arbeitssystem = new Arbeitssystem();
		getArbeitssystem().setKostensatzPersonal1_proH(55);
		getArbeitssystem().setKostensatzMaschine1_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setLabelMaschine1("Biber");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		getArbeitssystem().setUmsetzenBetrag_CHF(100);
		getArbeitssystem().setWeitereAufwaendeBetrag_CHF(500);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorEntrindenMitBiber(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMotormanuellEntrindenMitBiber(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorEntrindenMitBiber(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektEntrinden getArbeitsobjekt() {
		return (ArbeitsobjektEntrinden) arbeitsobjekt;
	}


	@Override
	protected String getModelName() {
		return "Motormanuell: Entrinden mit Biber";
	}

}
