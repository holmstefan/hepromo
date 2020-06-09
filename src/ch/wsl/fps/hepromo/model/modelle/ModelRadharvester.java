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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester.Baumartgruppe;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester.MaschinenKategorie;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester.MaschinenTyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorRadharvester;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelRadharvester extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelRadharvester() {
		super.arbeitsobjekt = new ArbeitsobjektRadharvester();
		getArbeitsobjekt().setBaumartGruppe(Baumartgruppe.Fichte);
		getArbeitsobjekt().setHolzmenge_m3(100);
		getArbeitsobjekt().setVolumenMittelstamm_m3(1.2);
		
		super.arbeitssystem = new ArbeitssystemRadharvester();
		getArbeitssystem().setMaschinenKategorie(MaschinenKategorie.Gross);
		getArbeitssystem().setMaschinenTyp(MaschinenTyp.Standard_gross);
		getArbeitssystem().setKostensatzPersonal1_proH(60);
		getArbeitssystem().setKostensatzMaschine1_proH(370);
		getArbeitssystem().setLabelPersonal1("Maschinist");
		getArbeitssystem().setLabelMaschine1("Radharvester");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorRadharvester(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelRadharvester(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorRadharvester(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektRadharvester getArbeitsobjekt() {
		return (ArbeitsobjektRadharvester) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemRadharvester getArbeitssystem() {
		return (ArbeitssystemRadharvester) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Radharvester";
	}
	
}
