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

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractSimpleModel<T extends AbstractModel> {
	
	// Arbeitssystem
	public static final double DEFAULT_TAEGLICHE_ARBEITSZEIT_MIN = 540;
	public static final double DEFAULT_WEGZEITEN_UND_PAUSEN_MIN = 60;
	public static final double DEFAULT_KOSTEN_UMSETZEN_BETRAG = 0;
	public static final double DEFAULT_KOSTEN_WEITERE_AUFWAENDE_BETRAG = 0;

	//Faktoren
	public static final double DEFAULT_KORREKTURFAKTOR_FAKTOR = 1;
	public static final double DEFAULT_RISIKO_VERWALTUNG_GEWINN_PRZ = 0;
	public static final double DEFAULT_MEHRWERTSTEUER_PRZ = 0;
	
	protected final T model;
	
	protected boolean dirty = true;
	protected Ergebnis ergebnis = null;
	
	
	public AbstractSimpleModel(T model) {
		this.model = model;
		
		// Arbeitssystem
		this.setTaeglicheArbeitszeit_min(DEFAULT_TAEGLICHE_ARBEITSZEIT_MIN);
		this.setWegzeitenUndPausen_min(DEFAULT_WEGZEITEN_UND_PAUSEN_MIN);
		this.setKostenUmsetzen_Betrag(DEFAULT_KOSTEN_UMSETZEN_BETRAG);
		this.setKostenWeitereAufwaende_Betrag(DEFAULT_KOSTEN_WEITERE_AUFWAENDE_BETRAG);
		
		//Faktoren
		this.setKorrekturfaktor_Faktor(DEFAULT_KORREKTURFAKTOR_FAKTOR);
		this.setRisikoVerwaltungGewinn_Prz(DEFAULT_RISIKO_VERWALTUNG_GEWINN_PRZ);
		this.setMehrwertsteuer_Prz(DEFAULT_MEHRWERTSTEUER_PRZ);
	}
	
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTaeglicheArbeitszeit_min(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setWegzeitenUndPausen_min(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setWegzeitenUndPausen_Min(intValue);
	}

	public void setKostenUmsetzen_Betrag(double value) {
		dirty = true;
		model.getArbeitssystem().setUmsetzenBetrag_CHF(value);
	}
	
	public void setKostenWeitereAufwaende_Betrag(double value) {
		dirty = true;
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(value);
	}
	
	public void setKorrekturfaktor_Faktor(double value) {
		dirty = true;
		model.getFaktoren().setKorrekturFaktor(value);
	}
	
	public void setRisikoVerwaltungGewinn_Prz(double value) {
		dirty = true;
		model.getFaktoren().setMargin(value);
	}
	
	public void setMehrwertsteuer_Prz(double value) {
		dirty = true;
		model.getFaktoren().setMehrwertsteuer(value);
	}
	
	
	public double getKostenTotal_total() {
		recalc();
		return ergebnis.getKostenTotal_total();
	}
	
	
	protected void recalc() {
		if (dirty == true) {
			ergebnis = model.getErgebnis();
			dirty = false;
		}
	}
	
	
	protected static int round(double value) {
		long longValue = Math.round(value);
	    
        if ((int)longValue != longValue) {
            throw new ArithmeticException("integer overflow: " + longValue);
        }
        return (int)longValue;
	}

}
