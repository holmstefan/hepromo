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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.Foermigkeit;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.FoermigkeitArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LaubholzAnteil;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LaubholzAnteilArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LiegendesHolz;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LiegendesHolzArrayWithSelection;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014.Maschinentyp;
import ch.wsl.fps.hepromo.model.modelle.ModelRadharvester2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleRadharvester2014 extends AbstractSimpleModel<ModelRadharvester2014> { //TODO(enhancement): Setter für benutzerdefinierte Produktivitätszu-/abschläge.

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 150;
	public static final double DEFAULT_MITTLERER_BHD_CM = 35;
	public static final double DEFAULT_FOERMIGKEIT_KATEGORIE = 2;
	public static final double DEFAULT_ANTEIL_LAUBHOLZ_KATEGORIE = 1;
	public static final double DEFAULT_LIEGENDES_HOLZ_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_MASCHINENTYP_KATEGORIE = 1;
	public static final double DEFAULT_KOSTEN_MASCHINIST_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_RADHARVESTER_PRO_H = 250;
	

	public SimpleRadharvester2014() {
		super(new ModelRadharvester2014());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setMittlererBhd_cm(DEFAULT_MITTLERER_BHD_CM);
		this.setFoermigkeit_Kategorie(DEFAULT_FOERMIGKEIT_KATEGORIE);
		this.setAnteilLaubholz_Kategorie(DEFAULT_ANTEIL_LAUBHOLZ_KATEGORIE);
		this.setLiegendesHolz_Kategorie(DEFAULT_LIEGENDES_HOLZ_KATEGORIE);

		// Arbeitssystem
		this.setMaschinentyp_Kategorie(DEFAULT_MASCHINENTYP_KATEGORIE);
		this.setKostenMaschinist_proH(DEFAULT_KOSTEN_MASCHINIST_PRO_H);
		this.setKostenRadharvester_proH(DEFAULT_KOSTEN_RADHARVESTER_PRO_H);
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
	 * @param value vollholzig=1, normalholzig=2, abholzig=3
	 */
	public void setFoermigkeit_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		FoermigkeitArrayWithSelection arrayWithSelection = model.getArbeitsobjekt().getFoermigkeitArrayWithSelection();
		
		switch(intValue) {
		case 1:
			Foermigkeit foermigkeit = arrayWithSelection.allValues[0];
			arrayWithSelection.setSelection(foermigkeit);
			return;
		case 2:
			foermigkeit = arrayWithSelection.allValues[1];
			arrayWithSelection.setSelection(foermigkeit);
			return;
		case 3:
			foermigkeit = arrayWithSelection.allValues[2];
			arrayWithSelection.setSelection(foermigkeit);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value 0%=1, bis 25%=2, bis 50%=3, bis 75%=4, bis 100%=5
	 */
	public void setAnteilLaubholz_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		LaubholzAnteilArrayWithSelection arrayWithSelection = model.getArbeitsobjekt().getLaubholzAnteilArrayWithSelection();
		
		switch(intValue) {
		case 1:
			LaubholzAnteil laubholzAnteil = arrayWithSelection.allValues[0];
			arrayWithSelection.setSelection(laubholzAnteil);
			return;
		case 2:
			laubholzAnteil = arrayWithSelection.allValues[1];
			arrayWithSelection.setSelection(laubholzAnteil);
			return;
		case 3:
			laubholzAnteil = arrayWithSelection.allValues[2];
			arrayWithSelection.setSelection(laubholzAnteil);
			return;
		case 4:
			laubholzAnteil = arrayWithSelection.allValues[3];
			arrayWithSelection.setSelection(laubholzAnteil);
			return;
		case 5:
			laubholzAnteil = arrayWithSelection.allValues[4];
			arrayWithSelection.setSelection(laubholzAnteil);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5]");
		}
	}
	
	/**
	 * 
	 * @param value keines%=1, vorgerücktes Holz bis 25%=2, vorgerücktes Holz bis 50%=3, zugefälltes Holz bis 25%=4, zugefälltes Holz bis 50%=5
	 */
	public void setLiegendesHolz_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		LiegendesHolzArrayWithSelection arrayWithSelection = model.getArbeitsobjekt().getLiegendesHolzArrayWithSelection();
		
		switch(intValue) {
		case 1:
			LiegendesHolz liegendesHolz = arrayWithSelection.allValues[0];
			arrayWithSelection.setSelection(liegendesHolz);
			return;
		case 2:
			liegendesHolz = arrayWithSelection.allValues[1];
			arrayWithSelection.setSelection(liegendesHolz);
			return;
		case 3:
			liegendesHolz = arrayWithSelection.allValues[2];
			arrayWithSelection.setSelection(liegendesHolz);
			return;
		case 4:
			liegendesHolz = arrayWithSelection.allValues[3];
			arrayWithSelection.setSelection(liegendesHolz);
			return;
		case 5:
			liegendesHolz = arrayWithSelection.allValues[4];
			arrayWithSelection.setSelection(liegendesHolz);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5]");
		}
	}
	
	/**
	 * 
	 * @param value Mittel=1, Gross=2
	 */
	public void setMaschinentyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setMaschinentyp(Maschinentyp.Mittel);
			return;
		case 2:
			model.getArbeitssystem().setMaschinentyp(Maschinentyp.Gross);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	public void setKostenMaschinist_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenRadharvester_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	
	public double getZeitMaschinist_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitRadharvester_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenMaschinist_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenRadharvester_proM3oR() {
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

	public double getKostenRadharvester_total() {
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
