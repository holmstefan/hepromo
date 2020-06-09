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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014.Region;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMotormanuell2014;
import ch.wsl.fps.hepromo.model.calc.CalculatorMotormanuellGesamt2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMotormanuellGesamt2014 extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelMotormanuellGesamt2014() {
		super.arbeitsobjekt = new ArbeitsobjektMotormanuellGesamt2014();
		getArbeitsobjekt().setHolzmenge_m3(150);
		getArbeitsobjekt().setBhd_cm(35);
		getArbeitsobjekt().setAnteilLaubholz_Prz(40);
		getArbeitsobjekt().setAnteilKiefer_Prz(0);
		getArbeitsobjekt().setRegion(Region.Huegelland);
		
		super.arbeitssystem = new ArbeitssystemMotormanuell2014();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzPersonal2_proH(0);
		getArbeitssystem().setKostensatzPersonal3_proH(0);
		getArbeitssystem().setKostensatzPersonal4_proH(0);
		getArbeitssystem().setEinsatzanteilPersonal1_Prz(100);
		getArbeitssystem().setEinsatzanteilPersonal2_Prz(0);
		getArbeitssystem().setEinsatzanteilPersonal3_Prz(0);
		getArbeitssystem().setEinsatzanteilPersonal4_Prz(0);
		getArbeitssystem().setKostensatzMaschine1_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setLabelPersonal1("Arbeitskraft 1");
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelMotormanuellGesamt2014_LabelMotorsaege.toString());
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorMotormanuellGesamt2014(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMotormanuellGesamt2014(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorMotormanuellGesamt2014(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektMotormanuellGesamt2014 getArbeitsobjekt() {
		return (ArbeitsobjektMotormanuellGesamt2014) arbeitsobjekt;
	}

	
	@Override
	public ArbeitssystemMotormanuell2014 getArbeitssystem() {
		return (ArbeitssystemMotormanuell2014) arbeitssystem;
	}
	

	@Override
	protected String getModelName() {
		return PdfLabels.ModelMotormanuellGesamt2014_ModelName.toString();
	}

}
