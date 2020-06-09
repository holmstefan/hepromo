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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.MittlereFahrentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014.Maschinenkategorie;
import ch.wsl.fps.hepromo.model.calc.CalculatorSchlepper2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelSchlepper2014 extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelSchlepper2014() {
		super.arbeitsobjekt = new ArbeitsobjektSchlepper2014();
		getArbeitsobjekt().setHolzmenge_m3(100);
		getArbeitsobjekt().setMittlererStueckinhalt(0.5);
		getArbeitsobjekt().setMittlereFahrentfernung(MittlereFahrentfernung.EntfBis300m);

		super.arbeitssystem = new ArbeitssystemSchlepper2014();
		getArbeitssystem().setMaschinenkategorie(Maschinenkategorie.Kranschlepper);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(130);
		getArbeitssystem().setLabelPersonal1(PdfLabels.ModelSchlepper2014_LabelMaschinist.toString());
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelSchlepper2014_LabelSchlepper.toString());
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorSchlepper2014(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelSchlepper2014(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorSchlepper2014(this);
		}
	}
	
	
	@Override
	public ArbeitssystemSchlepper2014 getArbeitssystem() {
		return (ArbeitssystemSchlepper2014) arbeitssystem;
	}
	
	
	@Override
	public ArbeitsobjektSchlepper2014 getArbeitsobjekt() {
		return (ArbeitsobjektSchlepper2014) arbeitsobjekt;
	}
	
	

	@Override
	protected String getModelName() {
		return PdfLabels.ModelSchlepper2014_ModelName.toString();
	}

}
