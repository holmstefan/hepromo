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
import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHacker2018 extends AbstractModel { //FIXME: AbstractModel2014?

	/**
	 * Constructor
	 */
	public ModelHacker2018() {
		super.arbeitsobjekt = new ArbeitsobjektHacker2018();
		getArbeitsobjekt().setHolzmenge_m3(50); //WICHTIG: m3 sind in diesem Modell immer Srm!
		getArbeitsobjekt().setZielsortiment(Zielsortiment.Waldrestholz);

		super.arbeitssystem = new ArbeitssystemHacker2018();
		getArbeitssystem().setHackerMotorleistung(ArbeitssystemHacker2018.HACKER_KW_300_BIS_399);
		getArbeitssystem().setDisplayHackerKostenProPMH15(true);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(320);
		getArbeitssystem().setLabelPersonal1(ModelStrings.getString("ModelHacker2018.Maschinist")); //$NON-NLS-1$
		getArbeitssystem().setLabelMaschine1(ModelStrings.getString("ModelHacker2018.Hacker")); //$NON-NLS-1$
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorHacker2018(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelHacker2018(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorHacker2018(this);
		}
	}
	
	
	@Override
	public ArbeitssystemHacker2018 getArbeitssystem() {
		return (ArbeitssystemHacker2018) arbeitssystem;
	}
	
	
	@Override
	public ArbeitsobjektHacker2018 getArbeitsobjekt() {
		return (ArbeitsobjektHacker2018) arbeitsobjekt;
	}
	
	

	@Override
	protected String getModelName() {
		return ModelStrings.getString("ModelHacker2018.ModelName"); //$NON-NLS-1$
	}

}
