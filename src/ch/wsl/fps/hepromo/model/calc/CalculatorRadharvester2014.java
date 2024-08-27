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
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014.Maschinentyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorRadharvester2014 extends AbstractCalculatorSingleModel2014 {
	
	public CalculatorRadharvester2014(HeProMoInputData inputData) {
		super(inputData);
	}
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		ergebnis.setUnitMaschine2ISH(true);
		
		if (ergebnis.getAnzahl_m3() > 0) {
			SubcalculatorThw calcThw = SubcalculatorThw.getInstanceForwarder(
					getArbeitsobjekt().isEinsatzThw(), 
					getArbeitsobjekt().getAnzahlRueckegassen());
			
			ergebnis.setZeitMaschine2(calcThw.calcISH_THW());
			ergebnis.setKostenMaschine2_total(getArbeitssystem().getKostensatzMaschine2_proH() * ergebnis.getZeitMaschine2());
		}
		
		return ergebnis;
	}

	
	@Override
	protected ArbeitsobjektRadharvester2014 getArbeitsobjekt() {
		return (ArbeitsobjektRadharvester2014) super.getArbeitsobjekt();
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
		double a = -34.4624;
		double b = getArbeitssystem().getMaschinentyp() == Maschinentyp.Mittel ?  0  : -24.9268;
		double c = 14.7029;
		double d = getArbeitssystem().getMaschinentyp() == Maschinentyp.Mittel ?  0  : 9.5333;
		
		
		
		//BHD
		double bhd = getArbeitsobjekt().getBhd_cm();
		
		if (getArbeitssystem().getMaschinentyp() == Maschinentyp.Mittel && bhd < 12) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("Beim Maschinentyp 'Mittel' darf der BHD nicht kleiner als 12cm sein!");
		}
		
		if (getArbeitssystem().getMaschinentyp() == Maschinentyp.Gross && bhd < 15) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("Beim Maschinentyp 'Gross' darf der BHD nicht kleiner als 15cm sein!");
		}
		
		if (bhd > 75) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("BHD darf nicht grösser als 75cm sein!");
		}
		
		if (getArbeitssystem().getMaschinentyp() == Maschinentyp.Mittel && bhd > 50) {
			bhd = 50;
		}
		
		if (getArbeitssystem().getMaschinentyp() == Maschinentyp.Gross && bhd > 60) {
			bhd = 60;
		}
		
		
		
		//Berechnung
		double tap = 
				a
				+ b
				+ c * Math.log(bhd)
				+ d * Math.log(bhd);
		
		//Zu-/Abschläge beachten
		tap *= getArbeitsobjekt().getGesamtfaktorAllerZuschlaege();
		
		return tap;
	}
	
	
	
	@Override
	protected ArbeitssystemRadharvester2014 getArbeitssystem() {
		return (ArbeitssystemRadharvester2014) super.getArbeitssystem();
		
	}

}
