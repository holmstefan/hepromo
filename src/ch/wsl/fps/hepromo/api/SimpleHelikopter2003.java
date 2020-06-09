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

import ch.wsl.fps.hepromo.model.ErgebnisHelikopterGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHelikopter2003 extends AbstractSimpleModel<ModelHelikopterGesamt> {

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 250;
	public static final double DEFAULT_ARBEITSVERFAHREN_KATEGORIE = 1;
	public static final double DEFAULT_HOLZTYP_KATEGORIE = 3;
	public static final double DEFAULT_BAUMARTENGRUPPE_KATEGORIE = 1;
	public static final double DEFAULT_HORIZONTALDISTANZ_M = 300;
	public static final double DEFAULT_VERTIKALDISTANZ_ABWAERTS_M = 100;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_HELIKOPTER_PRO_MIN = 60;
	public static final double DEFAULT_ANFLUGPAUSCHALE_BETRAG = 500;
	public static final double DEFAULT_HELIKOPTERKLASSE_KATEGORIE = 2;
	public static final double DEFAULT_LASTVOLUMEN_AUTOMATISCH_BERECHNEN_KATEGORIE = 1;
	public static final double DEFAULT_LASTVOLUMEN_M3 = 2.8571;
	public static final double DEFAULT_ANZAHL_PERSONAL_BEIM_HOLZ_FLIEGEN_ANZAHL = 2;
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_PERSON_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_MOTORSAEGEN_PRO_MOTORSAEGE_PRO_H = 18;
	public static final double DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H = 80;
	public static final double DEFAULT_KALKULATION_INKL_LAGERPLATZARBEIT_KATEGORIE = 1;
	

	public SimpleHelikopter2003() {
		super(new ModelHelikopterGesamt());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setArbeitsverfahren_Kategorie(DEFAULT_ARBEITSVERFAHREN_KATEGORIE);
		this.setHolztyp_Kategorie(DEFAULT_HOLZTYP_KATEGORIE);
		this.setBaumartengruppe_Kategorie(DEFAULT_BAUMARTENGRUPPE_KATEGORIE);
		this.setHorizontaldistanz_m(DEFAULT_HORIZONTALDISTANZ_M);
		this.setVertikaldistanzAbwaerts_m(DEFAULT_VERTIKALDISTANZ_ABWAERTS_M);

		// Arbeitssystem
		this.setKostenHelikopter_proMin(DEFAULT_KOSTEN_HELIKOPTER_PRO_MIN);
		this.setAnflugpauschale_Betrag(DEFAULT_ANFLUGPAUSCHALE_BETRAG);
		this.setHelikopterklasse_Kategorie(DEFAULT_HELIKOPTERKLASSE_KATEGORIE);
		this.setLastVolumenAutomatischBerechnen_Kategorie(DEFAULT_LASTVOLUMEN_AUTOMATISCH_BERECHNEN_KATEGORIE);
		if (DEFAULT_LASTVOLUMEN_AUTOMATISCH_BERECHNEN_KATEGORIE != 1) {
			this.setLastvolumen_m3(DEFAULT_LASTVOLUMEN_M3);
		}
		this.setAnzahlPersonalBeimHolzFliegen_Anzahl(DEFAULT_ANZAHL_PERSONAL_BEIM_HOLZ_FLIEGEN_ANZAHL);
		this.setKostenPersonalProPerson_proH(DEFAULT_KOSTEN_PERSONAL_PRO_PERSON_PRO_H);
		this.setKostenMotorsaegenProMotorsaege_proH(DEFAULT_KOSTEN_MOTORSAEGEN_PRO_MOTORSAEGE_PRO_H);
		this.setKostenKranfahrzeug_proH(DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H);
		this.setKalkulationInklLagerplatzarbeit_Kategorie(DEFAULT_KALKULATION_INKL_LAGERPLATZARBEIT_KATEGORIE);
	}
	
	
	public void setHolzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	/**
	 * 
	 * @param value Sortimentsverfahren=1, Vollbaumverfahren=2
	 */
	public void setArbeitsverfahren_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setArbeitsVerfahren(ArbeitsVerfahren.Sortimentsverfahren);
			model.getArbeitssystem().setAnzahlPersonalNachHolzFliegen(2);
			model.getArbeitssystem().setAnzahlMotorsaegen(1);
			model.getArbeitssystem().setAnzahlKranfahrzeuge(1);
			return;
		case 2:
			model.getArbeitsobjekt().setArbeitsVerfahren(ArbeitsVerfahren.Vollbaumverfahren);
			model.getArbeitssystem().setAnzahlPersonalNachHolzFliegen(3);
			model.getArbeitssystem().setAnzahlMotorsaegen(2);
			model.getArbeitssystem().setAnzahlKranfahrzeuge(1);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value Laubholz frisch=1, Laubholz angetrocknet=2, Nadelholz frisch=3, Nadelholz angetrocknet=4
	 */
	public void setHolztyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setHolztyp(Holztyp.Laubholz_frisch);
			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Laubholz);
			break;
		case 2:
			model.getArbeitsobjekt().setHolztyp(Holztyp.Laubholz_angetrocknet);
			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Laubholz);
			break;
		case 3:
			model.getArbeitsobjekt().setHolztyp(Holztyp.Nadelholz_frisch);
//			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Fichte);
			break;
		case 4:
			model.getArbeitsobjekt().setHolztyp(Holztyp.Nadelholz_angetrocknet);
//			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Fichte);
			break;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
		
		lastvolumenAnpassen();
	}
	
	/**
	 * 
	 * @param value Fichte=1, Tanne=2, Föhre/Lärche=3
	 */
	public void setBaumartengruppe_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Fichte);
			return;
		case 2:
			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Tanne);
			return;
		case 3:
			model.getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Foehre_Laerche);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setHorizontaldistanz_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setHorizontalDistanz_m(intValue);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setVertikaldistanzAbwaerts_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setVertikalDistanz_m(intValue);
	}
	
	public void setKostenHelikopter_proMin(double value) {
		dirty = true;
		model.getArbeitssystem().setHelikopterKosten_proMin(value);
	}
	
	public void setAnflugpauschale_Betrag(double value) {
		dirty = true;
		model.getArbeitssystem().setAnflugPauschale(value);
	}
	
	/**
	 * 
	 * @param value leicht=1, mittel=2, schwer=3
	 */
	public void setHelikopterklasse_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setHelikopterKlasse(HelikopterKlasse.Leicht);
			break;
		case 2:
			model.getArbeitssystem().setHelikopterKlasse(HelikopterKlasse.Mittel);
			break;
		case 3:
			model.getArbeitssystem().setHelikopterKlasse(HelikopterKlasse.Schwer);
			break;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
		
		lastvolumenAnpassen();
	}
	
	/**
	 * If set to true, the lastvolumen is calculated automatically
	 * based on Helikopterklasse and Holztyp.
	 * 
	 * @param value ja=1, nein=2
	 */
	public void setLastVolumenAutomatischBerechnen_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setLastVolumenAutomatischBerechnen(true);
			lastvolumenAnpassen();
			return;
		case 2:
			model.getArbeitssystem().setLastVolumenAutomatischBerechnen(false);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * If the lastvolumen needs to be set manually, it is necessary to set
	 * lastVolumenAutomatischBerechnen to "no" first, otherwise this method throws an Exception.
	 */
	public void setLastvolumen_m3(double value) {
		if (model.getArbeitssystem().isLastVolumenAutomatischBerechnen()) {
			throw new IllegalStateException("Lastvolumen wird automatisch berechnet");
		}
		dirty = true;
		model.getArbeitssystem().setLastVolumen(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlPersonalBeimHolzFliegen_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setAnzahlPersonalBeimHolzFliegen(intValue);
	}

	public void setKostenPersonalProPerson_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenMotorsaegenProMotorsaege_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	public void setKostenKranfahrzeug_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine2_proH(value);
	}
	
	/**
	 * 
	 * @param value ja=1, nein=2
	 */
	public void setKalkulationInklLagerplatzarbeit_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setKalkulationInklLagerplatzarbeit(true);
			return;
		case 2:
			model.getArbeitssystem().setKalkulationInklLagerplatzarbeit(false);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	

	private void lastvolumenAnpassen() {
		if (model.getArbeitssystem().isLastVolumenAutomatischBerechnen()) {
			//Lastkapazität ermitteln abhängig von Helikopterklasse
			double lastKapazitaet = -1;
			switch (model.getArbeitssystem().getHelikopterKlasse()) {
			case Leicht:
				lastKapazitaet = 11;
				break;

			case Mittel:
				lastKapazitaet = 30;
				break;

			case Schwer:
				lastKapazitaet = 49;
				break;

			default:
				throw new RuntimeException();
			}


			//Spezifisches Gewicht ermitteln abhängig von Holztyp
			double spezGewicht = -1;
			switch(model.getArbeitsobjekt().getHolztyp()) {
			case Laubholz_angetrocknet:
				spezGewicht = 9;
				break;

			case Laubholz_frisch:
				spezGewicht = 10;
				break;

			case Nadelholz_angetrocknet:
				spezGewicht = 7.5;
				break;

			case Nadelholz_frisch:
				spezGewicht = 8.4;
				break;

			default:
				throw new RuntimeException();
			}


			//Lastvolumen berechnen und setzen
			double lastvolumen = lastKapazitaet * 0.8 / spezGewicht;
			model.getArbeitssystem().setLastVolumen(lastvolumen);
		}
	}
	
	
	public double getZeitHelikopter_PMH15() {
		recalc();
		return ((ErgebnisHelikopterGesamt) ergebnis).getZeitHelifirma();
	}

	public double getZeitPersonalForstbetrieb_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitMotorsaege_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}

	public double getZeitKranfahrzeug_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine2();
	}
	

	public double getKostenHelikopter_proM3oR() {
		recalc();
		return ((ErgebnisHelikopterGesamt) ergebnis).getKostenHeli_proM3();
	}

	public double getKostenPersonal_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenMotorsaege_proM3oR() {
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

	
	public double getKostenHelikopter_total() {
		recalc();
		return ((ErgebnisHelikopterGesamt) ergebnis).getKostenHeli_total();
	}

	public double getKostenPersonal_total() {
		recalc();
		return ergebnis.getKostenPersonal_total();
	}

	public double getKostenMotorsaege_total() {
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
	
}
