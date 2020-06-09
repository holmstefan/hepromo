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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell.KronenLaengenKlasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten.IndustrieholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten.SchichtholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten.StammholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.calc.CalculatorMotormanuellGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMotormanuellGesamt extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelMotormanuellGesamt() {
		super.arbeitsobjekt = new ArbeitsobjektMotormanuellGesamt();
		getArbeitsobjekt().setHolzmenge_m3(150);
		getArbeitsobjekt().setMassenmittelstamm_m3iR(0.3);
		getArbeitsobjekt().setAnteilFaellenMitHandseilzug_Prz(0);
		getArbeitsobjekt().setAnteilEntrindenVonHand_Prz(0);
		getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Fichte);
		getArbeitsobjekt().setKronenLaengenKlasse(KronenLaengenKlasse.Klasse_0bis33);
		getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_0bis30);
		getArbeitsobjekt().setHindernisse(Hindernisse.Keine);
		getArbeitsobjekt().setAnteilIndustrieholz_Prz(0);
		getArbeitsobjekt().setAnteilSchichtholz_Prz(0);
		getArbeitsobjekt().setAnteilSpalten_Prz(0);
		getArbeitsobjekt().setStammholzStuecklaengen(StammholzStuecklaengen.Von6Bis10m);
		getArbeitsobjekt().setKantenBrechen(false);
		getArbeitsobjekt().setIndustrieholzStuecklaengen(IndustrieholzStuecklaengen.Ueber7m);
		getArbeitsobjekt().setSchichtholzStuecklaengen(SchichtholzStuecklaengen.Laenge2m);
		
		super.arbeitssystem = new Arbeitssystem();
		getArbeitssystem().setKostensatzPersonal1_proH(55);
		getArbeitssystem().setKostensatzMaschine1_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setKostensatzMaschine2_proH(0.5);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setLabelMaschine1("Motorsäge");
		getArbeitssystem().setLabelMaschine2("Schäleisen");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorMotormanuellGesamt(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMotormanuellGesamt(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorMotormanuellGesamt(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektMotormanuellGesamt getArbeitsobjekt() {
		return (ArbeitsobjektMotormanuellGesamt) arbeitsobjekt;
	}


	@Override
	protected String getModelName() {
		return "Motormanuell: Gesamtmodell";
	}

}
