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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranPlanung;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.calc.CalculatorSeilkranPlanung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelSeilkranPlanung extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelSeilkranPlanung() {
		super.arbeitsobjekt = new ArbeitsobjektSeilkranPlanung();
		getArbeitsobjekt().setLinienlaenge_m(600);
		getArbeitsobjekt().setLinieAbsteckenOhneProjekt(true);
		
		super.arbeitssystem = new Arbeitssystem();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorSeilkranPlanung(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelSeilkranPlanung(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorSeilkranPlanung(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektSeilkranPlanung getArbeitsobjekt() {
		return (ArbeitsobjektSeilkranPlanung) arbeitsobjekt;
	}


	@Override
	protected String getModelName() {
		return "Seilkran: Planung";
	}

}
