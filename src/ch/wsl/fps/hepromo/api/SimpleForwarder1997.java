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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder.FahrstreckenArt;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder.ForwarderTyp;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder;

/**
 * 
 * In diesem Modell findet keine Umrechnung von m3 in Rinde nach m3 ohne Rinde
 * statt; sowohl die Eingangsgrössen wie auch die Ausgangsgrössen sind in Rinde.
 * 
 * @author Stefan Holm
 *
 */
public class SimpleForwarder1997 extends AbstractSimpleModel<ModelForwarder> {

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 200;
	public static final double DEFAULT_LAUBHOLZANTEIL_PRZ = 50;
	public static final double DEFAULT_DURCHSCHNITTLICHER_BHD_AUSHIEB_CM = 30;
	public static final double DEFAULT_DURCHSCHNITTLICHE_HOLZLAENGE_M = 4;
	public static final double DEFAULT_DURCHSCHNITTLICHE_ANZAHL_SORTIMENTE_PRO_FAHRZYKLUS_ANZAHL = 2;
	public static final double DEFAULT_ANZAHL_VERSCHIEDEN_ZU_LAGERNDE_SORTIMENTE_ANZAHL = 4;
	public static final double DEFAULT_ANTEIL_KRUMM_PRZ = 40;
	public static final double DEFAULT_FAHRSTRECKE_AUF_STRASSE_M = 100;
	public static final double DEFAULT_FAHRSTRECKENANTEILE_STRASSE_KATEGORIE = 4;
	public static final double DEFAULT_FAHRSTRECKE_AUF_FEINERSCHLIESSUNG_M = 250;
	public static final double DEFAULT_FAHRSTRECKENANTEILE_FEINERSCHLIESSUNG_KATEGORIE = 4;
	public static final double DEFAULT_STRECKENLAENGE_HOLZ_EINSEITIG_M = 0;
	public static final double DEFAULT_STRECKENLAENGE_HOLZ_BEIDSEITIG_M = 150;
	public static final double DEFAULT_HINDERNISSE_FEINERSCHLIESSUNG_KATEGORIE = 2;
	public static final double DEFAULT_NEIGUNG_FEINERSCHLIESSUNG_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_MASCHINIST_PRO_H = 80;
	public static final double DEFAULT_KOSTEN_FORWARDER_PRO_H = 120;
	public static final double DEFAULT_FORWARDERTYP_KATEGORIE = 2;
	public static final double DEFAULT_LADEQUERSCHNITTSFLAECHE_M2 = 4.1;
	
	
	public SimpleForwarder1997() {
		super(new ModelForwarder());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setLaubholzanteil_Prz(DEFAULT_LAUBHOLZANTEIL_PRZ);
		this.setDurchschnittlicherBhdAushieb_cm(DEFAULT_DURCHSCHNITTLICHER_BHD_AUSHIEB_CM);
		this.setDurchschnittlicheHolzlaenge_m(DEFAULT_DURCHSCHNITTLICHE_HOLZLAENGE_M);
		this.setDurchschnittlicheAnzahlSortimenteProFahrzyklus_Anzahl(DEFAULT_DURCHSCHNITTLICHE_ANZAHL_SORTIMENTE_PRO_FAHRZYKLUS_ANZAHL);
		this.setAnzahlVerschiedenZuLagerndeSortimente_Anzahl(DEFAULT_ANZAHL_VERSCHIEDEN_ZU_LAGERNDE_SORTIMENTE_ANZAHL);
		this.setAnteilKrumm_Prz(DEFAULT_ANTEIL_KRUMM_PRZ);
		this.setFahrstreckeAufStrasse_m(DEFAULT_FAHRSTRECKE_AUF_STRASSE_M);
		this.setFahrstreckenanteileStrasse_Kategorie(DEFAULT_FAHRSTRECKENANTEILE_STRASSE_KATEGORIE);
		this.setFahrstreckeAufFeinerschliessung_m(DEFAULT_FAHRSTRECKE_AUF_FEINERSCHLIESSUNG_M);
		this.setFahrstreckenanteileFeinerschliessung_Kategorie(DEFAULT_FAHRSTRECKENANTEILE_FEINERSCHLIESSUNG_KATEGORIE);
		this.setStreckenlaengeHolzEinseitig_m(DEFAULT_STRECKENLAENGE_HOLZ_EINSEITIG_M);
		this.setStreckenlaengeHolzBeidseitig_m(DEFAULT_STRECKENLAENGE_HOLZ_BEIDSEITIG_M);
		this.setHindernisseFeinerschliessung_Kategorie(DEFAULT_HINDERNISSE_FEINERSCHLIESSUNG_KATEGORIE);
		this.setNeigungFeinerschliessung_Kategorie(DEFAULT_NEIGUNG_FEINERSCHLIESSUNG_KATEGORIE);

		// Arbeitssystem
		this.setKostenMaschinist_proH(DEFAULT_KOSTEN_MASCHINIST_PRO_H);
		this.setKostenForwarder_proH(DEFAULT_KOSTEN_FORWARDER_PRO_H);
		this.setForwardertyp_Kategorie(DEFAULT_FORWARDERTYP_KATEGORIE);
		this.setLadequerschnittsflaeche_m2(DEFAULT_LADEQUERSCHNITTSFLAECHE_M2);
	}
	

	public void setHolzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setLaubholzanteil_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setLaubholzAnteil_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setDurchschnittlicherBhdAushieb_cm(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setDurchschnittlicherBhdAushieb_cm(intValue);
	}
	
	public void setDurchschnittlicheHolzlaenge_m(double value) {
		dirty = true;
		model.getArbeitsobjekt().setDurchschnittlicheHolzlaenge_m(value);
	}
	
	public void setDurchschnittlicheAnzahlSortimenteProFahrzyklus_Anzahl(double value) {
		dirty = true;
		model.getArbeitsobjekt().setDurchschnittlicheAnzahlSortimente(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlVerschiedenZuLagerndeSortimente_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnzahlVerschiedeneSortimente(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnteilKrumm_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnteilKrumm_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setFahrstreckeAufStrasse_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setFahrstreckeAufStrasse(intValue);
	}
	
	/**
	 * 
	 * @param value Lastfahrt vorwärts bergab=1, Lastfahrt vorwärts bergauf=2, Lastfahrt vorwärts eben=3, gleichmässig verteilt=4
	 */
	public void setFahrstreckenanteileStrasse_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setFahrstreckenArtStrasse(FahrstreckenArt.LastfahrtVorwaertsBergab);
			return;
		case 2:
			model.getArbeitsobjekt().setFahrstreckenArtStrasse(FahrstreckenArt.LastfahrtVorwaertsBergauf);
			return;
		case 3:
			model.getArbeitsobjekt().setFahrstreckenArtStrasse(FahrstreckenArt.LastfahrtVorwaertsEben);
			return;
		case 4:
			model.getArbeitsobjekt().setFahrstreckenArtStrasse(FahrstreckenArt.GleichmaessigVerteilt);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setFahrstreckeAufFeinerschliessung_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setFahrstreckeAufFeinerschliessung(intValue);
	}

	/**
	 * 
	 * @param value Lastfahrt vorwärts bergab=1, Lastfahrt vorwärts bergauf=2, Lastfahrt vorwärts eben=3, gleichmässig verteilt=4
	 */
	public void setFahrstreckenanteileFeinerschliessung_Kategorie(double value) { //FIXME: Methode hat keinen Einfluss aufs Ergebnis! Auch im GUI entfernen!
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(FahrstreckenArt.LastfahrtVorwaertsBergab);
			return;
		case 2:
			model.getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(FahrstreckenArt.LastfahrtVorwaertsBergauf);
			return;
		case 3:
			model.getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(FahrstreckenArt.LastfahrtVorwaertsEben);
			return;
		case 4:
			model.getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(FahrstreckenArt.GleichmaessigVerteilt);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setStreckenlaengeHolzEinseitig_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setErschliessungsLaengeEinseitig_m(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setStreckenlaengeHolzBeidseitig_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setErschliessungsLaengeBeidseitig_m(intValue);
	}
	
	/**
	 * 
	 * @param value keine=1, wenige=2, viele=3, sehr viele=4
	 */
	public void setHindernisseFeinerschliessung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHindernisKategorieFeinerschliessung(HindernisKategorie.Keine);
			return;
		case 2:
			model.getArbeitsobjekt().setHindernisKategorieFeinerschliessung(HindernisKategorie.Wenige);
			return;
		case 3:
			model.getArbeitsobjekt().setHindernisKategorieFeinerschliessung(HindernisKategorie.Viele);
			return;
		case 4:
			model.getArbeitsobjekt().setHindernisKategorieFeinerschliessung(HindernisKategorie.SehrViele);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
	}
	
	/**
	 * 
	 * @param value &lt;10%=1, 10-20%=2, &gt;20%=3
	 */
	public void setNeigungFeinerschliessung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setNeigungsKategorieFeinerschliessung(NeigungsKategorie.NK_bis10);
			return;
		case 2:
			model.getArbeitsobjekt().setNeigungsKategorieFeinerschliessung(NeigungsKategorie.NK_10bis20);
			return;
		case 3:
			model.getArbeitsobjekt().setNeigungsKategorieFeinerschliessung(NeigungsKategorie.NK_ueber20);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
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

	/**
	 * Setzt den Forwardertyp.
	 * 
	 * <b>Achtung:</b> beim Aufruf dieser Methode wird die 
	 * Ladequerschnittsfläche automatisch neu gesetzt. Der 
	 * gesetzte Wert entspricht dem Standardwert des gewählten 
	 * Forwardertyps und ist in der Dokumentation ersichtlich.
	 * 
	 * @param value klein=1, mittel=2
	 */
	public void setForwardertyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setForwarderTyp(ForwarderTyp.Klein);
			return;
		case 2:
			model.getArbeitssystem().setForwarderTyp(ForwarderTyp.Mittel);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	public void setLadequerschnittsflaeche_m2(double value) {
		dirty = true;
		model.getArbeitssystem().setLadeQuerschnittsFlaeche_m2(value);
	}


	public double getZeitMaschinist_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitForwarder_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenPersonal_proM3iR() {
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
	

	public double getKostenPersonal_total() {
		recalc();
		return ergebnis.getKostenPersonal_total();
	}

	public double getKostenForwarder_total() {
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
