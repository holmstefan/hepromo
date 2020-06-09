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
package ch.wsl.fps.hepromo.model.calc;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.HeProMoInputData;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractCalculatorSingleModel2014 extends AbstractCalculatorSingleModel {

	public AbstractCalculatorSingleModel2014(HeProMoInputData inputData) {
		super(inputData);
	}

	
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		
		if (ergebnis.getAnzahl_m3() > 0) {
			//Produktivität wird bei 2014er Modellen als Menge/PMH15 angezeigt
			setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_PMH15, ergebnis.getProduktivitaet_m3ProPmh15() / getArbeitsobjekt().getRindenAbzugFaktor());
			setProduktivitaet(ergebnis, ProdEinheit.M3_OR_PRO_PMH15, ergebnis.getProduktivitaet_m3ProPmh15());
		}
		
		return ergebnis;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		return ProdEinheit.M3_IR_PRO_PMH15;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit2() {
		return ProdEinheit.M3_OR_PRO_PMH15;
	}
		
		
	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 1.0;
	}

}
