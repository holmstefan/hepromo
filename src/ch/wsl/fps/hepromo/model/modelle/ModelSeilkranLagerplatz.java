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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.calc.CalculatorSeilkranLagerplatz;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelSeilkranLagerplatz extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelSeilkranLagerplatz() {
		super.arbeitsobjekt = new ArbeitsobjektSeilkranLagerplatz();
		getArbeitsobjekt().setSystemzeitSeilen_psh0proM(0.13);
		getArbeitsobjekt().setHolzmenge_m3(400);
		
		super.arbeitssystem = new ArbeitssystemSeilkranLagerplatz();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setAnzahlPersonal(1);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setEinsatzzeitPersonal_Prz(100);
		getArbeitssystem().setKostensatzMaschine1_proH(80);
		getArbeitssystem().setLabelMaschine1("Kranfahrzeug");
		getArbeitssystem().setLaufzeitKranfahrzeug_Prz(75);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorSeilkranLagerplatz(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelSeilkranLagerplatz(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorSeilkranLagerplatz(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektSeilkranLagerplatz getArbeitsobjekt() {
		return (ArbeitsobjektSeilkranLagerplatz) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkranLagerplatz getArbeitssystem() {
		return (ArbeitssystemSeilkranLagerplatz) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Seilkran: Lagerplatzarbeiten";
	}

}
