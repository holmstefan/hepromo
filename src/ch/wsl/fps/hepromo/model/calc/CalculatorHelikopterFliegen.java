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

import ch.wsl.fps.hepromo.model.ErgebnisHelikopterFliegen;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorHelikopterFliegen extends AbstractCalculatorSingleModel {

	public CalculatorHelikopterFliegen(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	
	@Override
	public ErgebnisHelikopterFliegen calculate() {		
		ErgebnisHelikopterFliegen ergebnis = (ErgebnisHelikopterFliegen) super.calculate();
		
		ergebnis.setKostenMaschine1_total(
				getArbeitssystem().getKostensatzMaschine1_proH() * ergebnis.getZeitMaschine1() +
				getArbeitssystem().getAnflugPauschale()
				);

		ergebnis.setRotationszeit(getPsh0Rot() * 60.0);
		
		return ergebnis;
	}

	
	@Override
	protected ErgebnisHelikopterFliegen getNewErgebnisInstance() {
		return new ErgebnisHelikopterFliegen();
	}
	
	

	@Override
	protected double calcPsh0_hProM3() {
		
		double psh0_rot = getPsh0Rot();
		double psh0_heli_hProM3 = psh0_rot / getArbeitssystem().getLastVolumen();
		
		
		double UNBEKANNTER_KORREKTURFAKTOR = 1.25;
		psh0_heli_hProM3 *= UNBEKANNTER_KORREKTURFAKTOR; //In Diff-Doku vermerkt, evt. mit Auslastungsgrad beim Lastvolumen verbunden?
		
		if (super.rindenAbzugBeruecksichtigen) {
			psh0_heli_hProM3 /= getArbeitsobjekt().getRindenAbzugFaktor();
		}
		
		return psh0_heli_hProM3;
	}

	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 1;
	}
	
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return getArbeitssystem().getAnzahlPersonal();
	}
	
	
	
	private double getPsh0Rot() {
		double koeff1 = 0;
		double koeff2 = 0;
		switch (getArbeitssystem().getHelikopterKlasse()) {
		case Leicht:
			koeff1 = 1.87;
			koeff2 = 0.0004;
			break;

		case Mittel:
			koeff1 = 1.87;
			koeff2 = 0.0027;
			break;

		case Schwer:
			koeff1 = 2.36;
			koeff2 = 0.0010;
			break;
			
		}
		
		double result = (1.0 / 60) * (koeff1 + 0.00032 * getArbeitsobjekt().getHorizontalDistanz_m() + koeff2 * getArbeitsobjekt().getVertikalDistanz_m());
		
		
		return result;
	}

	
	
	@Override
	protected ArbeitsobjektHelikopterFliegen getArbeitsobjekt() {
		return (ArbeitsobjektHelikopterFliegen) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemHelikopterFliegen getArbeitssystem() {
		return (ArbeitssystemHelikopterFliegen) super.getArbeitssystem();
	}

}
