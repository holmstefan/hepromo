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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorHackschnitzelTransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHackschnitzelTransport2018 extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelHackschnitzelTransport2018() {
		super.arbeitsobjekt = new ArbeitsobjektHackschnitzelTransport2018();
		getArbeitsobjekt().setHolzmenge_m3(100); //WICHTIG: m3 sind in diesem Modell immer Srm!
		getArbeitsobjekt().setAufnehmenBeladenerMulde(true);
		getArbeitsobjekt().setZielsortiment(Zielsortiment.Waldrestholz);
		getArbeitsobjekt().setHackerMotorleistung(ArbeitssystemHacker2018.HACKER_KW_300_BIS_399);
		getArbeitsobjekt().setDistanzWaldstrasse_km(4);
		getArbeitsobjekt().setDistanzInnerAusserorts_km(8);
		getArbeitsobjekt().setDistanzAutobahn_km(0);

		super.arbeitssystem = new ArbeitssystemHackschnitzelTransport2018();
		getArbeitssystem().setMuldeninhalt_Srm(40);
		getArbeitssystem().setKostensatzMaschine1_proH(200);
		getArbeitssystem().setLabelMaschine1(ModelStrings.getString("ModelHackschnitzelTransport2018.LkwUndFahrer")); //$NON-NLS-1$
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(0); //Pausen sind in Kostensatz Fahrer eingerechnet!
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorHackschnitzelTransport2018(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelHackschnitzelTransport2018(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorHackschnitzelTransport2018(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektHackschnitzelTransport2018 getArbeitsobjekt() {
		return (ArbeitsobjektHackschnitzelTransport2018) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemHackschnitzelTransport2018 getArbeitssystem() {
		return (ArbeitssystemHackschnitzelTransport2018) arbeitssystem;
	}
	

	@Override
	protected String getModelName() {
		return ModelStrings.getString("ModelHackschnitzelTransport2018.ModelName"); //$NON-NLS-1$
	}

}
