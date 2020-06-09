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

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014.Maschinenkategorie;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorSchlepper2014 extends AbstractCalculatorSingleModel2014 {
	
	public CalculatorSchlepper2014(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	protected ArbeitsobjektSchlepper2014 getArbeitsobjekt() {
		return (ArbeitsobjektSchlepper2014) super.getArbeitsobjekt();
	}
	
	
	
	@Override
	protected double calcPsh0_hProM3() {

		double m3ProPsh15 = calcProduktivitaet_m3oRProPsh15();
		if (super.rindenAbzugBeruecksichtigen) {
			double rindenabzug_fix = 0.9;
			double rindenabzug_variabel = getArbeitsobjekt().getRindenAbzugFaktor();
			m3ProPsh15 *= rindenabzug_variabel / rindenabzug_fix;
		}
		
		double psh15ProM3 = 1.0 / m3ProPsh15;
		double psh0ProM3 = psh15ProM3 / f_0bis15;
		
		return psh0ProM3;
	}
	
	
	
	
	/**
	 * Berechnet die Produktivität in m3 o.R. pro PSH15
	 * 
	 * @return
	 */
	private double calcProduktivitaet_m3oRProPsh15() {
		
		//Koeffizienten
		double a = 15.8005;
		double b = -1.7838;
		double c = -1;
		double d = -1;
		
		switch(getArbeitssystem().getMaschinenkategorie()) {
		case Seilschlepper:
			c = -6.4945;
			d = 1.1923;
			break;
			
		case Kranschlepper:
			c = -3.5725;
			d = 3.0337;
			break;
			
		case Klemmbankschlepper:
			c = 0;
			d = 8.0771;
			break;
			
		default: 
			throw new RuntimeException();
		}
		
		
		
		//Mittlerer Stueckinhalt
		double vMit = getArbeitsobjekt().getMittlererStueckinhalt();

		if (vMit < 0.05) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("Mittlerer Stückinhalt muss mindestens 0.05m3 sein!");
		}

		if (getArbeitssystem().getMaschinenkategorie() == Maschinenkategorie.Klemmbankschlepper && vMit < 0.36) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("Beim Klemmbankschlepper muss der mittlerer Stückinhalt mindestens 0.36m3 sein!");
		}
		
		double lowVMitFactor = 1;
		if (vMit < 0.14) {
			lowVMitFactor = vMit / 0.14;
			vMit = 0.14;
		}
		
		
		
		//Berechnung
		double tap = 
				a
				+ b * Math.log(getArbeitsobjekt().getMittlereFahrentfernung().value())
				+ c
				+ d * Math.log(vMit);		
		tap *= lowVMitFactor;
		
		//Zu-/Abschläge beachten
		tap *= getArbeitsobjekt().getGesamtfaktorAllerZuschlaege();

		
		return tap;
	}
	
	
	
	@Override
	protected ArbeitssystemSchlepper2014 getArbeitssystem() {
		return (ArbeitssystemSchlepper2014) super.getArbeitssystem();
		
	}

}
