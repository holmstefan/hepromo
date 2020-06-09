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

import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Standortguete;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018.Maschinentyp;
import ch.wsl.fps.hepromo.model.modelle.ModelVorruecken2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleFaellenVorruecken2018 extends AbstractSimpleModel<ModelVorruecken2018> {

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 100;
	public static final double DEFAULT_BHD_CM = 25;
	public static final double DEFAULT_BONITAET_KATEGORIE = 3;
	public static final double DEFAULT_HANGNEIGUNG_KATEGORIE = 1;
	public static final double DEFAULT_ABZOPFEN_DER_KRONE_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_MASCHINENTYP_KATEGORIE = 2;
	public static final double DEFAULT_PRODUKTIVITAETSSTEIGERUNG_RUECKERAUPTE_FAKTOR = 1.1;
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_VORRUECKEGERAET_PRO_H = 60;
	public static final double DEFAULT_KOSTEN_MOTORSAEGE_PRO_H = 18;
	
	
	public SimpleFaellenVorruecken2018() {
		super(new ModelVorruecken2018());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setBhd_cm(DEFAULT_BHD_CM);
		this.setBonitaet_Kategorie(DEFAULT_BONITAET_KATEGORIE);
		this.setHangneigung_Kategorie(DEFAULT_HANGNEIGUNG_KATEGORIE);
		this.setAbzopfenDerKrone_Kategorie(DEFAULT_ABZOPFEN_DER_KRONE_KATEGORIE);

		// Arbeitssystem
		this.setMaschinentyp_Kategorie(DEFAULT_MASCHINENTYP_KATEGORIE);
		this.setProduktivtaetssteigerungRueckeraupe_Faktor(DEFAULT_PRODUKTIVITAETSSTEIGERUNG_RUECKERAUPTE_FAKTOR);
		this.setKostenPersonal_proH(DEFAULT_KOSTEN_PERSONAL_PRO_H);
		this.setKostenVorrueckegeraet_proH(DEFAULT_KOSTEN_VORRUECKEGERAET_PRO_H);
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
	public void setBhd_cm(double value) {
		dirty = true;
		model.getArbeitsobjekt().setBhd_cm(round(value));
	}
	
	/**
	 * 
	 * @param value sehr gering=1, gering=2, mittel=3, gut=4, sehr gut=5
	 */
	public void setBonitaet_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setStandortguete(Standortguete.ARM);
			return;
		case 2:
			model.getArbeitsobjekt().setStandortguete(Standortguete.WENIGER_GUT);
			return;
		case 3:
			model.getArbeitsobjekt().setStandortguete(Standortguete.MITTEL);
			return;
		case 4:
			model.getArbeitsobjekt().setStandortguete(Standortguete.GUT);
			return;
		case 5:
			model.getArbeitsobjekt().setStandortguete(Standortguete.SEHR_GUT);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5]");
		}
	}
	
	/**
	 * 
	 * @param value 0-15%=1, 16-25%=2, 26-35%=3, 36-45%=4, 46-55%=5, &gt;55%=6
	 */
	public void setHangneigung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.HN_BIS_15_PRZ);
			return;
		case 2:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.HN_GROESSER_15_BIS_25_PRZ);
			return;
		case 3:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.HN_GROESSER_25_BIS_35_PRZ);
			return;
		case 4:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.HN_GROESSER_35_BIS_45_PRZ);
			return;
		case 5:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.HN_GROESSER_45_BIS_55_PRZ);
			return;
		case 6:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.HN_GROESSER_55_PRZ);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5,6]");
		}
	}
	
	/**
	 * 
	 * @param value ja=1, nein=2
	 */
	public void setAbzopfenDerKrone_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setAbzopfenDerKrone(true);
			return;
		case 2:
			model.getArbeitsobjekt().setAbzopfenDerKrone(false);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}

	/**
	 * 
	 * @param value Schlepper=1, Rückeraupe=2
	 */
	public void setMaschinentyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setMaschinentyp(Maschinentyp.Schlepper);
			return;
		case 2:
			model.getArbeitssystem().setMaschinentyp(Maschinentyp.Rueckeraupe);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	public void setProduktivtaetssteigerungRueckeraupe_Faktor(double value) {
		dirty = true;
		model.getArbeitssystem().setProduktivtaetssteigerungRueckeraupe(value);
	}
	
	public void setKostenPersonal_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenVorrueckegeraet_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	public void setKostenMotorsaege_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine2_proH(value);
	}
	
	
	public double getZeitPersonal_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitVorrueckegeraet_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}

	public double getZeitMotorsaege_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine2();
	}
	

	public double getKostenPersonal_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenVorrueckegeraet_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenMotorsaege_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine2_proM3();
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

	public double getKostenVorrueckegeraet_total() {
		recalc();
		return ergebnis.getKostenMaschine1_total();
	}

	public double getKostenMotorsaege_total() {
		recalc();
		return ergebnis.getKostenMaschine2_total();
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
		return ergebnis.getProduktivitaet();
	}

}
