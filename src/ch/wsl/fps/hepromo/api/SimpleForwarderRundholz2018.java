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

import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018.ErgebnisAnzeige;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleForwarderRundholz2018 extends AbstractSimpleForwarder2018 {

	// Arbeitsobjekt
	public static final double DEFAULT_RUNDHOLZMENGE_M3_IR = 140;
	
	
	public SimpleForwarderRundholz2018() {
		super();
		model.getCalculator().setErgebnisAnzeige(ErgebnisAnzeige.Rundholz);
		
		// Arbeitsobjekt
		this.setRundholzmenge_m3iR(DEFAULT_RUNDHOLZMENGE_M3_IR);
	}
	
	
	public void setRundholzmenge_m3iR(double value) {
		double m3oR = value * model.getArbeitsobjekt().getRindenAbzugFaktor();
		
		dirty = true;
		model.getArbeitsobjekt().setVerkaufRundholz_m3oR(m3oR);
	}
	

	public double getKostenMaschinist_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenForwarder_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenUmsetzen_proM3oR() {
		recalc();
		return ergebnis.getKostenUmsetzen_proM3();
	}

	public double getKostenWeitereAufwaende_proM3oR() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_proM3();
	}
	
	public double getKostenTotal_proM3oR() {
		recalc();
		return ergebnis.getKostenTotal_proM3();
	}

}
