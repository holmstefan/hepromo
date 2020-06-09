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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen.SchwierigkeitSeitlicherZuzug;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.calc.CalculatorMobilseilkranSeilen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMobilseilkranSeilen extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelMobilseilkranSeilen() {
		super.arbeitsobjekt = new ArbeitsobjektMobilseilkranSeilen();
		getArbeitsobjekt().setMittleresStueckvolumen_m3(0.4);
		getArbeitsobjekt().setHolzmenge_m3(400);
		getArbeitsobjekt().setMittlereFahrdistanz_m(300);
		getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(15);
		getArbeitsobjekt().setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug.Einfach);
		
		super.arbeitssystem = new ArbeitssystemSeilkran();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setAnzahlPersonal(2);
		getArbeitssystem().setKostensatzMaschine1_proH(150);
		getArbeitssystem().setLabelMaschine1("Mobilseilkran");
		getArbeitssystem().setAnteilSeilkranLaufzeit_Prz(90);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorMobilseilkranSeilen(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMobilseilkranSeilen(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorMobilseilkranSeilen(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektMobilseilkranSeilen getArbeitsobjekt() {
		return (ArbeitsobjektMobilseilkranSeilen) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Mobilseilkran: Seilen";
	}

}
