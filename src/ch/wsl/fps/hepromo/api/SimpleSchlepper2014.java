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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.Beizugsdistanz;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.BeizugsdistanzArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.MittlereFahrentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014.Maschinenkategorie;
import ch.wsl.fps.hepromo.model.modelle.ModelSchlepper2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleSchlepper2014 extends AbstractSimpleModel<ModelSchlepper2014> {//TODO(enhancement): Setter für benutzerdefinierten Produktivitätszu-/abschlag.

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 100;
	public static final double DEFAULT_MITTLERER_STUECKINHALT_M3_IR_PRO_STK = 0.5;
	public static final double DEFAULT_MITTLERE_FAHRENTFERNUNG_M = 200;
	public static final double DEFAULT_BEIZUGSDISTANZ_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_SCHLEPPERTYP_KATEGORIE = 1;
	public static final double DEFAULT_KOSTEN_MASCHINIST_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_SCHLEPPER_PRO_H = 130;
	

	public SimpleSchlepper2014() {
		super(new ModelSchlepper2014());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setMittlererStueckinhalt_m3iRProStk(DEFAULT_MITTLERER_STUECKINHALT_M3_IR_PRO_STK);
		this.setMittlereFahrentfernung_m(DEFAULT_MITTLERE_FAHRENTFERNUNG_M);
		this.setBeizugsdistanz_Kategorie(DEFAULT_BEIZUGSDISTANZ_KATEGORIE);

		// Arbeitssystem
		this.setSchleppertyp_Kategorie(DEFAULT_SCHLEPPERTYP_KATEGORIE);
		this.setKostenMaschinist_proH(DEFAULT_KOSTEN_MASCHINIST_PRO_H);
		this.setKostenSchlepper_proH(DEFAULT_KOSTEN_SCHLEPPER_PRO_H);
	}
	
	
	public void setHolzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	public void setMittlererStueckinhalt_m3iRProStk(double value) {
		dirty = true;
		model.getArbeitsobjekt().setMittlererStueckinhalt(value);
	}
	
	/**
	 * 
	 * @param value Mittlere Fahrentfernung [m]
	 */
	public void setMittlereFahrentfernung_m(double value) {
		dirty = true;
		model.getArbeitsobjekt().setMittlereFahrentfernung( MittlereFahrentfernung.parseDouble(value) );
	}
	
	/**
	 * 
	 * @param value &lt;20m=1, 20-40m=2, &gt;40m=3
	 */
	public void setBeizugsdistanz_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		BeizugsdistanzArrayWithSelection arrayWithSelection = model.getArbeitsobjekt().getBeizugsdistanzArrayWithSelection();
		
		switch(intValue) {
		case 1:
			Beizugsdistanz beizugsdistanz = arrayWithSelection.allValues[0];
			arrayWithSelection.setSelection(beizugsdistanz);
			return;
		case 2:
			beizugsdistanz = arrayWithSelection.allValues[1];
			arrayWithSelection.setSelection(beizugsdistanz);
			return;
		case 3:
			beizugsdistanz = arrayWithSelection.allValues[2];
			arrayWithSelection.setSelection(beizugsdistanz);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value Kranschlepper=1, Seilschlepper=2, Klemmbankschlepper=3
	 */
	public void setSchleppertyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setMaschinenkategorie(Maschinenkategorie.Kranschlepper);
			return;
		case 2:
			model.getArbeitssystem().setMaschinenkategorie(Maschinenkategorie.Seilschlepper);
			return;
		case 3:
			model.getArbeitssystem().setMaschinenkategorie(Maschinenkategorie.Klemmbankschlepper);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	public void setKostenMaschinist_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenSchlepper_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	

	public double getZeitMaschinist_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitSchlepper_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenMaschinist_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenSchlepper_proM3oR() {
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
	

	public double getKostenMaschinist_total() {
		recalc();
		return ergebnis.getKostenPersonal_total();
	}

	public double getKostenSchlepper_total() {
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
	

	public double getProduktivitaet_m3iRproPMH15() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.M3_IR_PRO_PMH15);
	}

}
