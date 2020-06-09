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
import ch.wsl.fps.hepromo.model.Ergebnis.Purpose;
import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractCalculator {
	
	private final Arbeitsobjekt arbeitsobjekt;
	private final Arbeitssystem arbeitssystem;
	protected final Faktoren faktoren;
	
	protected boolean rindenAbzugBeruecksichtigen = true;
	
	
	
	public AbstractCalculator(HeProMoInputData inputData) {
		//get input data
		arbeitsobjekt = inputData.getArbeitsobjekt();
		arbeitssystem = inputData.getArbeitssystem();
		faktoren = inputData.getFaktoren();
	}
	
	
	
	/**
	 * Berechnet das Ergebnis und gibt es zurück
	 */
	public abstract Ergebnis calculate();
	
	
	
	/**
	 * Gibt das Arbeitsobjekt zurück
	 */
	protected Arbeitsobjekt getArbeitsobjekt() {
		return arbeitsobjekt;
	}	
	
	
	
	/**
	 * Gibt das Arbeitssystem zurück
	 */
	protected Arbeitssystem getArbeitssystem() {
		return arbeitssystem;
	}
	
	
	
	/**
	 *  Einige Modelle erlauben als Input die Eingabe 
	 *  von m3 i.R., während der Output in m3 o.R. erfolgt.  
	 *  Dies kann hier deaktiviert werden, falls in diesen
	 *  Modellen keine Umrechnung i.R. -> o.R. erfolgen soll.
	 */
	public void setRindenAbzugBeruecksichtigen(boolean flag) {
		this.rindenAbzugBeruecksichtigen = flag;
	}



	protected ProdEinheit getProdEinheit1() {
		return ProdEinheit.M3_PRO_PSH15;
	}



	protected ProdEinheit getProdEinheit2() {
		return ProdEinheit.EMPTY;
	}



	protected final void setProduktivitaet(Ergebnis ergebnis, ProdEinheit prodEinheit, double produktivitaet ) {
		if (prodEinheit == getProdEinheit1()) {
			ergebnis.setProduktivitaet(Purpose.FIRST, prodEinheit, produktivitaet);
		}
		if (prodEinheit == getProdEinheit2()) {
			ergebnis.setProduktivitaet(Purpose.SECOND, prodEinheit, produktivitaet);
		}
		if (prodEinheit != getProdEinheit1() && prodEinheit != getProdEinheit2()) {
			ergebnis.setProduktivitaet(Purpose.ADDITIONAL, prodEinheit, produktivitaet);
		}
	}

}
