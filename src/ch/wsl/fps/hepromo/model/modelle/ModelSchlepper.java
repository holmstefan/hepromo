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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Fahrentfernung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckeart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckebedingungen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.RueckenImSaft;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Schlagordnung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Zuzugentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.RueckgehilfeEinsatzanteil;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.SchlepperTyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorSchlepper;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelSchlepper extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelSchlepper() {
		super.arbeitsobjekt = new ArbeitsobjektSchlepper();
		getArbeitsobjekt().setMittlererStueckInhalt(1);
		getArbeitsobjekt().setHolzmenge_m3(200);
		getArbeitsobjekt().setSchlagordnung(Schlagordnung.WenigerAlsEinViertelNichtEingehalten);
		getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.WenigerAlsVier);
		getArbeitsobjekt().setFahrentfernung(Fahrentfernung.Bis200m);
		getArbeitsobjekt().setZuzugentfernung(Zuzugentfernung.Ab30bis40m);
		getArbeitsobjekt().setRueckeart(Rueckeart.GesamtesRuecken);
		getArbeitsobjekt().setRueckenImSaft(RueckenImSaft.Nein);
		getArbeitsobjekt().setRueckeBedingungen(Rueckebedingungen.Mittel);
		
		super.arbeitssystem = new ArbeitssystemSchlepper();
		getArbeitssystem().setKostensatzPersonal1_proH(60);
		getArbeitssystem().setKostensatzRueckegehilfe(50);
		getArbeitssystem().setLabelPersonal1("Maschinist");
		getArbeitssystem().setRueckegehilfe(false);
		getArbeitssystem().setRueckgehilfeEinsatzanteil(RueckgehilfeEinsatzanteil.Unbekannt);
		getArbeitssystem().setKostensatzMaschine1_proH(90);
		getArbeitssystem().setLabelMaschine1("Schlepper");
		getArbeitssystem().setSchlepperTyp(SchlepperTyp.AndereSchleppertypen);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorSchlepper(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelSchlepper(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorSchlepper(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektSchlepper getArbeitsobjekt() {
		return (ArbeitsobjektSchlepper) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSchlepper getArbeitssystem() {
		return (ArbeitssystemSchlepper) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Schlepper";
	}

}
