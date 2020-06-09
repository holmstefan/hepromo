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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellFaellen;

/**
 * 
 * In diesem Modell findet keine Umrechnung von m3 in Rinde nach m3 ohne Rinde
 * statt, d.h. wenn als Eingangsgrösse die Holzmenge in Rinde gegeben wird, sind
 * die Ausgangsgrössen in Rinde, wenn die Eingangsgrösse als Holzmenge ohne 
 * Rinde gegeben wird, sind die Ausgangsgrössen ohne Rinde. 
 * 
 * @author Stefan Holm
 *
 */
public class SimpleFaellenMotormanuell1978 extends AbstractSimpleModel<ModelMotormanuellFaellen> {

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3 = 200;
	public static final double DEFAULT_MASSENMITTELSTAMM_M3 = 0.3;
	public static final double DEFAULT_BAUMARTENGRUPPE_KATEGORIE = 1;
	public static final double DEFAULT_ANTEIL_FAELLEN_MIT_HANDSEILZUG_PRZ = 0;
	public static final double DEFAULT_HANGNEIGUNG_KATEGORIE = 1;
	public static final double DEFAULT_HINDERNISSE_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_H = 55;
	public static final double DEFAULT_KOSTEN_MOTORSAEGE_PRO_H = 18;
	

	public SimpleFaellenMotormanuell1978() {
		super(new ModelMotormanuellFaellen());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3(DEFAULT_HOLZMENGE_M3);
		this.setMassenmittelstamm_m3(DEFAULT_MASSENMITTELSTAMM_M3);
		this.setBaumartengruppe_Kategorie(DEFAULT_BAUMARTENGRUPPE_KATEGORIE);
		this.setAnteilFaellenMitHandseilzug_Prz(DEFAULT_ANTEIL_FAELLEN_MIT_HANDSEILZUG_PRZ);
		this.setHangneigung_Kategorie(DEFAULT_HANGNEIGUNG_KATEGORIE);
		this.setHindernisse_Kategorie(DEFAULT_HINDERNISSE_KATEGORIE);

		// Arbeitssystem
		this.setKostenPersonal_proH(DEFAULT_KOSTEN_PERSONAL_PRO_H);
		this.setKostenMotorsaege_proH(DEFAULT_KOSTEN_MOTORSAEGE_PRO_H);
	}
	

	public void setHolzmenge_m3(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	public void setMassenmittelstamm_m3(double value) {
		dirty = true;
		model.getArbeitsobjekt().setMassenmittelstamm_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Fichte=1, Tanne=2, Föhre/Lärche=3, Laubholz=4
	 */
	public void setBaumartengruppe_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Fichte);
			return;
		case 2:
			model.getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Tanne);
			return;
		case 3:
			model.getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Foehre_Laerche);
			return;
		case 4:
			model.getArbeitsobjekt().setBaumartenGruppe(Baumartgruppe.Laubholz);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setAnteilFaellenMitHandseilzug_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnteilFaellenHandseilzug_Prz(intValue);
	}	
	
	/**
	 * 
	 * @param value 0-30%=1, 31-50%=2, 51-70%=3, &gt;70%=4
	 */
	public void setHangneigung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_0bis30);
			return;
		case 2:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_31bis50);
			return;
		case 3:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_51bis70);
			return;
		case 4:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Hangneigung_71bis100);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
	}
	
	/**
	 * 
	 * @param value keine=1, gering=2, mässig=3, stark=4
	 */
	public void setHindernisse_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Keine);
			return;
		case 2:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Gering);
			return;
		case 3:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Maessig);
			return;
		case 4:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Stark);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
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

	public double getKostenPersonal_proM3() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenMotorsaege_proM3() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenUmsetzen_proM3() {
		recalc();
		return ergebnis.getKostenUmsetzen_proM3();
	}

	public double getKostenWeitereAufwaende_proM3() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_proM3();
	}

	public double getKostenTotal_proM3() {
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
	

	public double getProduktivitaet_m3proPSH15() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.M3_PRO_PSH15);
	}
	
}
