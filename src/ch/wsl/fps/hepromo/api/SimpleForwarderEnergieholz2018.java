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
package ch.wsl.fps.hepromo.api;

import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Energieholzanfall;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018.ErgebnisAnzeige;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleForwarderEnergieholz2018 extends AbstractSimpleForwarder2018 {

	// Arbeitsobjekt
	public static final double DEFAULT_ENERGIEHOLZMENGE_M3_IR = 100;
	public static final double DEFAULT_ZOPFDURCHMESSER_RUNDHOLZ_CM = 10;
	public static final double DEFAULT_ENERGIEHOLZANFALL_M3_IR_PRO_HA = 70;
	
	
	public SimpleForwarderEnergieholz2018() {
		super();
		model.getCalculator().setErgebnisAnzeige(ErgebnisAnzeige.Energieholz);
		
		// Arbeitsobjekt
		this.setEnergieholzmenge_m3iR(DEFAULT_ENERGIEHOLZMENGE_M3_IR);
		this.setZopfdurchmesser_Rundholz_cm(DEFAULT_ZOPFDURCHMESSER_RUNDHOLZ_CM);
		this.setEnergieholzanfall_m3iRproHa(DEFAULT_ENERGIEHOLZANFALL_M3_IR_PRO_HA);
	}
	
	public void setEnergieholzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setEnergieholzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setZopfdurchmesser_Rundholz_cm(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setZopfdurchmesser_cm(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setEnergieholzanfall_m3iRproHa(double value) {
		int intValue = round(value);
		
		dirty = true;
		setEnergieholzanfallBenutzerdefiniert_m3iRproHa(intValue);
	}
	
	private void setEnergieholzanfallBenutzerdefiniert_m3iRproHa(int value) { //TODO: eine solche convenience-methode sollte im arbeitsobjekt vorhanden sein
		dirty = true;
		Energieholzanfall[] allEnergieholzanfall = model.getArbeitsobjekt().getAllEnergieholzanfall();
		for (Energieholzanfall energieholzanfall : allEnergieholzanfall) {
			if (energieholzanfall.isBenutzerdefiniert()) {
				energieholzanfall.setEnergieholzanfall_m3ProHa(value);
				model.getArbeitsobjekt().setEnergieholzanfall_m3iRproHa(energieholzanfall);
				break;
			}
		}
	}
	

	public double getKostenMaschinist_proM3iR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenForwarder_proM3iR() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenUmsetzen_proM3iR() {
		recalc();
		return ergebnis.getKostenUmsetzen_proM3();
	}

	public double getKostenWeitereAufwaende_proM3iR() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_proM3();
	}
	
	public double getKostenTotal_proM3iR() {
		recalc();
		return ergebnis.getKostenTotal_proM3();
	}

}
