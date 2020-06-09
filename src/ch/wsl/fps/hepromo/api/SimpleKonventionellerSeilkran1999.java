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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleKonventionellerSeilkran1999 extends AbstractSimpleModel<ModelKonventionellerSeilkranGesamt> { //TODO(enhancement): Setter für Seilverlegung/Windentransport/Windenstandort/Distanz Windenselbstfahrt/Anzahl Pers. Lagerplatz/Einsatzzeitanteil.

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 600;
	public static final double DEFAULT_MITTLERER_STUECKINHALT_M3_IR = 0.4;
	public static final double DEFAULT_STUECKLAENGE_M = 5;
	public static final double DEFAULT_EINGRIFFSART_KATEGORIE = 1;
	public static final double DEFAULT_HOLZSEILORT_KATEGORIE = 1;
	public static final double DEFAULT_HANGNEIGUNG_PRZ = 50;
	public static final double DEFAULT_HINDERNISSE_KATEGORIE = 1;
	public static final double DEFAULT_LINIENLAENGE_M = 800;
	public static final double DEFAULT_FAHRTRICHTUNG_KATEGORIE = 1;
	public static final double DEFAULT_MITTLERE_FAHRDISTANZ_M = 350;
	public static final double DEFAULT_MITTLERE_DISTANZ_SEITLICHER_ZUZUG_M = 20;
	public static final double DEFAULT_ANZAHL_STUETZEN_ANZAHL = 2;
	public static final double DEFAULT_ANZAHL_ENDMASTEN_ANZAHL = 1;
	public static final double DEFAULT_TRAGSEILHOEHE_BESTAND_M = 12;
	public static final double DEFAULT_TRAGSEILHOEHE_LAGERPLATZ_M = 10;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_SEILKRANANLAGE_PRO_H = 110;
	public static final double DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H = 80;
	public static final double DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ = 75;
	public static final double DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL = 3;
	public static final double DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL = 2;
	

	public SimpleKonventionellerSeilkran1999() {
		super(new ModelKonventionellerSeilkranGesamt());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setMittlererStueckinhalt_m3iR(DEFAULT_MITTLERER_STUECKINHALT_M3_IR);
		this.setStuecklaenge_m(DEFAULT_STUECKLAENGE_M);
		this.setEingriffsart_Kategorie(DEFAULT_EINGRIFFSART_KATEGORIE);
		this.setHolzseilort_Kategorie(DEFAULT_HOLZSEILORT_KATEGORIE);
		this.setHangneigung_Prz(DEFAULT_HANGNEIGUNG_PRZ);
		this.setHindernisse_Kategorie(DEFAULT_HINDERNISSE_KATEGORIE);
		this.setLinienlaenge_m(DEFAULT_LINIENLAENGE_M);
		this.setFahrtrichtung_Kategorie(DEFAULT_FAHRTRICHTUNG_KATEGORIE);
		this.setMittlereFahrdistanz_m(DEFAULT_MITTLERE_FAHRDISTANZ_M);
		this.setMittlereDistanzSeitlicherZuzug_m(DEFAULT_MITTLERE_DISTANZ_SEITLICHER_ZUZUG_M);
		this.setAnzahlStuetzen_Anzahl(DEFAULT_ANZAHL_STUETZEN_ANZAHL);
		this.setAnzahlEndmasten_Anzahl(DEFAULT_ANZAHL_ENDMASTEN_ANZAHL);
		this.setTragseilhoeheBestand_m(DEFAULT_TRAGSEILHOEHE_BESTAND_M);
		this.setTragseilhoeheLagerplatz_m(DEFAULT_TRAGSEILHOEHE_LAGERPLATZ_M);

		// Arbeitssystem
		this.setKostenPersonal_proH(DEFAULT_KOSTEN_PERSONAL_PRO_H);
		this.setKostenSeilkrananlage_proH(DEFAULT_KOSTEN_SEILKRANANLAGE_PRO_H);
		this.setKostenKranfahrzeug_proH(DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H);
		this.setKranfahrzeugLaufzeit_Prz(DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ);
		this.setAnzahlPersonenInstallation_Anzahl(DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL);
		this.setAnzahlPersonenSeilen_Anzahl(DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL);
	}
	

	public void setHolzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	public void setMittlererStueckinhalt_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setMittleresStueckvolumen_m3(value);
	}
	
	public void setStuecklaenge_m(double value) {
		dirty = true;
		model.getArbeitsobjekt().setStueckLaenge_m(value);
	}
	
	/**
	 * 
	 * @param value Durchforstung=1, Lichtung=2, Räumung=3
	 */
	public void setEingriffsart_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setEingriffsart(Eingriffsart.Durchforstung);
			return;
		case 2:
			model.getArbeitsobjekt().setEingriffsart(Eingriffsart.Lichtung);
			return;
		case 3:
			model.getArbeitsobjekt().setEingriffsart(Eingriffsart.Raeumung);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value Schlagfläche=1, Haufen=2
	 */
	public void setHolzseilort_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHolzseilort(HolzSeilOrt.AusSchlagflaeche);
			return;
		case 2:
			model.getArbeitsobjekt().setHolzseilort(HolzSeilOrt.AbHaufen);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setHangneigung_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setHangneigung_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Normal=1, Erschwert=2, Extrem=3
	 */
	public void setHindernisse_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Normal);
			return;
		case 2:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Erschwert);
			return;
		case 3:
			model.getArbeitsobjekt().setHindernisse(Hindernisse.Extrem);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setLinienlaenge_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setLinienLaenge_m(intValue);
	}
	
	/**
	 * 
	 * @param value bergab=1, bergauf=2
	 */
	public void setFahrtrichtung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setFahrtrichtung(Fahrtrichtung.Bergab);
			return;
		case 2:
			model.getArbeitsobjekt().setFahrtrichtung(Fahrtrichtung.Bergauf);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMittlereFahrdistanz_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setMittlereFahrdistanz_m(intValue);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMittlereDistanzSeitlicherZuzug_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(intValue);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlStuetzen_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnzahlStuetzen(intValue);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlEndmasten_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnzahlEndmasten(intValue);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTragseilhoeheBestand_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setTragseilhoeheBestand_m(intValue);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTragseilhoeheLagerplatz_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setTragseilhoeheLagerplatz_m(intValue);
	}
	
	public void setKostenPersonal_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenSeilkrananlage_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	public void setKostenKranfahrzeug_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine2_proH(value);
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setKranfahrzeugLaufzeit_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setLaufzeitKranfahrzeug_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlPersonenInstallation_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setAnzahlPersonenInstallation(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlPersonenSeilen_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setAnzahlPersonenSeilen(intValue);
	}
	

	public double getZeitPersonal_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitSeilkrananlage_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}

	public double getZeitKranfahrzeug_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine2();
	}
	

	public double getKostenPersonal_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenSeilkrananlage_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenKranfahrzeug_proM3oR() {
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

	public double getKostenSeilkrananlage_total() {
		recalc();
		return ergebnis.getKostenMaschine1_total();
	}

	public double getKostenKranfahrzeug_total() {
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
	

	public double getProduktivitaetBeimSeilen_m3iRproPMH15() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.M3_IR_PRO_PMH15_BEIM_SEILEN);
	}
	
}
