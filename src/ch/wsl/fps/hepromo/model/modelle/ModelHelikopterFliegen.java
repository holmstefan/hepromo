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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;
import ch.wsl.fps.hepromo.model.calc.CalculatorHelikopterFliegen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHelikopterFliegen extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelHelikopterFliegen() {
		super.arbeitsobjekt = new ArbeitsobjektHelikopterFliegen();
		getArbeitsobjekt().setHolzmenge_m3(250);
		getArbeitsobjekt().setHolztyp(Holztyp.Nadelholz_frisch);
		getArbeitsobjekt().setHorizontalDistanz_m(300);
		getArbeitsobjekt().setVertikalDistanz_m(100);
		
		super.arbeitssystem = new ArbeitssystemHelikopterFliegen();
		getArbeitssystem().setKostensatzMaschine1_proMin(60);
		getArbeitssystem().setLabelMaschine1("Helifirma");
		getArbeitssystem().setAnflugPauschale(500);
		getArbeitssystem().setHelikopterKlasse(HelikopterKlasse.Mittel);
		getArbeitssystem().setLastVolumen(2.8571);
		getArbeitssystem().setLastVolumenAutomatischBerechnen(true);
		getArbeitssystem().setAnzahlPersonal(2);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal Forstbetrieb");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorHelikopterFliegen(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelHelikopterFliegen(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorHelikopterFliegen(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektHelikopterFliegen getArbeitsobjekt() {
		return (ArbeitsobjektHelikopterFliegen) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemHelikopterFliegen getArbeitssystem() {
		return (ArbeitssystemHelikopterFliegen) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Helikopter: Fliegen";
	}

}
