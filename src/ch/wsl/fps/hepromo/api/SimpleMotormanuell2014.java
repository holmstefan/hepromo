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

import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014.Region;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellGesamt2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMotormanuell2014 extends AbstractSimpleModel<ModelMotormanuellGesamt2014> { //TODO(enhancement): Setter für Kostensätze aller 4 Arbeitskräfte inkl. Einsatzzeitanteil.

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 150;
	public static final double DEFAULT_BHD_CM = 35;
	public static final double DEFAULT_ANTEIL_FOEHRE_PRZ = 0;
	public static final double DEFAULT_ANTEIL_LAUBHOLZ_PRZ = 40;
	public static final double DEFAULT_REGION_KATEGORIE = 2;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_MOTORSAEGE_PRO_H = 18;
	

	public SimpleMotormanuell2014() {
		super(new ModelMotormanuellGesamt2014());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setMittlererBhd_cm(DEFAULT_BHD_CM);
		this.setAnteilFoehre_Prz(DEFAULT_ANTEIL_FOEHRE_PRZ);
		this.setAnteilLaubholz_Prz(DEFAULT_ANTEIL_LAUBHOLZ_PRZ);
		this.setRegion_Kategorie(DEFAULT_REGION_KATEGORIE);

		// Arbeitssystem
		this.setKostenPersonal_proH(DEFAULT_KOSTEN_PERSONAL_PRO_H);
		this.setKostenMotorsaege_proH(DEFAULT_KOSTEN_MOTORSAEGE_PRO_H);
	}

	
	
	public void setHolzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMittlererBhd_cm(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setBhd_cm(intValue);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setAnteilFoehre_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnteilKiefer_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setAnteilLaubholz_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnteilLaubholz_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Gebirge=1, Flach- und Huegelland=2
	 */
	public void setRegion_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setRegion(Region.Gebirge);
			return;
		case 2:
			model.getArbeitsobjekt().setRegion(Region.Huegelland);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	public void setKostenPersonal_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenMotorsaege_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	
	public double getZeitPersonal_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitMotorsaege_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenPersonal_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenMotorsaege_proM3oR() {
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
	

	public double getKostenPersonal_total() {
		recalc();
		return ergebnis.getKostenPersonal_total();
	}

	public double getKostenMotorsaege_total() {
		recalc();
		return ergebnis.getKostenMaschine1_total();
	}

	public double getKostenUmsetzen_total() {
		recalc();
		return ergebnis.getKostenUmsetzen_total();
	}

	public double getKostenWeitereAufwaende_total() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_total();
	}
	

	public double getProduktivitaet_m3iRproWPSH() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.M3_IR_PRO_WPSH);
	}
}
