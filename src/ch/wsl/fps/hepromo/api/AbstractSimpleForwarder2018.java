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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AbstandRueckegasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Rueckeentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018.Forwardertyp;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractSimpleForwarder2018 extends AbstractSimpleModel<ModelForwarder2018> {

	// Arbeitsobjekt
	public static final double DEFAULT_MITTLERER_BHD_CM = 30;
	public static final double DEFAULT_RUECKEENTFERNUNG_KATEGORIE = 1;
	public static final double DEFAULT_HANGNEIGUNG_KATEGORIE = 1;
	public static final double DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE = 1;
	public static final double DEFAULT_ERSCHWERNISSE_KATEGORIE = 1;
	public static final double DEFAULT_ABSTAND_RUECKEGASSEN_KATEGORIE = 2;

	// Arbeitssystem
	public static final double DEFAULT_FORWARDERTYP_KATEGORIE = 1;
	public static final double DEFAULT_KOSTEN_MASCHINIST_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_FORWARDER_PRO_H = 110;
	

	public AbstractSimpleForwarder2018() {
		super(new ModelForwarder2018());
		
		// Arbeitsobjekt
		this.setMittlererBhd_cm(DEFAULT_MITTLERER_BHD_CM);
		this.setRueckeentfernung_Kategorie(DEFAULT_RUECKEENTFERNUNG_KATEGORIE);
		this.setHangneigung_Kategorie(DEFAULT_HANGNEIGUNG_KATEGORIE);
		this.setAnzahlSortimente_Kategorie(DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE);
		this.setErschwernisse_Kategorie(DEFAULT_ERSCHWERNISSE_KATEGORIE);
		this.setAbstandRueckegassen_Kategorie(DEFAULT_ABSTAND_RUECKEGASSEN_KATEGORIE);

		// Arbeitssystem
		this.setForwardertyp_Kategorie(DEFAULT_FORWARDERTYP_KATEGORIE);
		this.setKostenMaschinist_proH(DEFAULT_KOSTEN_MASCHINIST_PRO_H);
		this.setKostenForwarder_proH(DEFAULT_KOSTEN_FORWARDER_PRO_H);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMittlererBhd_cm(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setBhdMit_cm(intValue);
	}
	
	/**
	 * 
	 * @param value bis 200m=1, 201-400m=2, 401-600m=3, 601-900m=4, 901-1200m=5, mehr als 1200m=6
	 */
	public void setRueckeentfernung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.EntfKleiner200m);
			return;
		case 2:
			model.getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.Entf201bis400m);
			return;
		case 3:
			model.getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.Entf401bis600m);
			return;
		case 4:
			model.getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.Entf601bis900m);
			return;
		case 5:
			model.getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.Entf901bis1200m);
			return;
		case 6:
			model.getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.EntfGroesser1200m);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5,6]");
		}
	}
	
	/**
	 * 
	 * @param value bis 15%=1, 15-25%=2, 25-35%=3, 35-45%=4, mehr als 45%=5
	 */
	public void setHangneigung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.NeigungBis15Prozent);
			return;
		case 2:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Neigung15Bis25Prozent);
			return;
		case 3:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Neigung25Bis35Prozent);
			return;
		case 4:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.Neigung35Bis45Prozent);
			return;
		case 5:
			model.getArbeitsobjekt().setHangneigung(Hangneigung.NeigungGroesser45Prozent);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5]");
		}
	}
	
	/**
	 * 
	 * @param value 1-3=1, 4-6=2, 7 und mehr=3
	 */
	public void setAnzahlSortimente_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.AnzahlBis3);
			return;
		case 2:
			model.getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.Anzahl4Bis6);
			return;
		case 3:
			model.getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.Anzahl7UndMehr);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value Keine=1, Wenige=2, Viele=3
	 */
	public void setErschwernisse_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setErschwernisse(Erschwernisse.Keine);
			return;
		case 2:
			model.getArbeitsobjekt().setErschwernisse(Erschwernisse.Wenige);
			return;
		case 3:
			model.getArbeitsobjekt().setErschwernisse(Erschwernisse.Viele);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value 40m=1, 30m=2, 20m=3
	 */
	public void setAbstandRueckegassen_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setAbstandRueckegasse(AbstandRueckegasse.Circa40m);
			return;
		case 2:
			model.getArbeitsobjekt().setAbstandRueckegasse(AbstandRueckegasse.Circa30m);
			return;
		case 3:
			model.getArbeitsobjekt().setAbstandRueckegasse(AbstandRueckegasse.Circa20m);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value Ja=1, Nein=2
	 */
	public void setEinsatzTraktionshilfswinde_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setEinsatzThw(true);
			return;
		case 2:
			model.getArbeitsobjekt().setEinsatzThw(false);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlRueckegassenMitThw_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnzahlRueckegassen(intValue);
	}
	
	/**
	 * 
	 * @param value Mittel=1, Gross=2
	 */
	public void setForwardertyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setForwardertyp(Forwardertyp.Mittel);
			return;
		case 2:
			model.getArbeitssystem().setForwardertyp(Forwardertyp.Gross);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	public void setKostenMaschinist_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenForwarder_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	public void setKostenTraktionshilfswinde_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine2_proH(value);
	}

	
	public double getZeitMaschinist_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitForwarder_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}

	public double getZeitTraktionshilfswinde_ISH() {
		recalc();
		return ergebnis.getZeitMaschine2();
	}
	

	public double getKostenMaschinist_total() {
		recalc();
		return ergebnis.getKostenPersonal_total();
	}

	public double getKostenForwarder_total() {
		recalc();
		return ergebnis.getKostenMaschine1_total();
	}

	public double getKostenTraktionshilfswinde_total() {
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
		return ergebnis.getProduktivitaet(ProdEinheit.M3_IR_PRO_PMH15);
	}

}
