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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker.Hackgutart;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker.HackerTyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorMobilerHacker;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMobilerHacker extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelMobilerHacker() {
		super.arbeitsobjekt = new ArbeitsobjektMobilerHacker();
		getArbeitsobjekt().setLaubholzAnteil_Prz(0);
		getArbeitsobjekt().setAnzahlBaeume(550);
		getArbeitsobjekt().setHackgutart(Hackgutart.Kronen);
		getArbeitsobjekt().setMittlererBhdAushieb_m(0.3);
		getArbeitsobjekt().setMittlererZopfDrm_m(0.08);
		getArbeitsobjekt().setFahrstreckeStrasse_m(50);
		getArbeitsobjekt().setFahrstreckeFeinerschliessung_m(100);
		getArbeitsobjekt().setHolzlagerLaenge_m(100);
		getArbeitsobjekt().setHindernisKategorie(HindernisKategorie.Wenige);
		getArbeitsobjekt().setNeigungsKategorie(NeigungsKategorie.NK_10bis20);
		
		super.arbeitssystem = new ArbeitssystemMobilerHacker();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Maschinist");
		getArbeitssystem().setKostensatzMaschine1_proH(280);
		getArbeitssystem().setLabelMaschine1("Mobiler Hacker");
		getArbeitssystem().setHackerTyp(HackerTyp.AufForwarder);
		getArbeitssystem().setKippContainerVolumen_m3(15);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorMobilerHacker(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMobilerHacker(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorMobilerHacker(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektMobilerHacker getArbeitsobjekt() {
		return (ArbeitsobjektMobilerHacker) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemMobilerHacker getArbeitssystem() {
		return (ArbeitssystemMobilerHacker) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Mobiler Hacker";
	}

}
