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

import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Fahrentfernung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckeart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckebedingungen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.RueckenImSaft;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Schlagordnung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Zuzugentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.RueckgehilfeEinsatzanteil;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.SchlepperTyp;
import ch.wsl.fps.hepromo.model.modelle.ModelSchlepper;

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
public class SimpleSchlepper1992 extends AbstractSimpleModel<ModelSchlepper> {

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3 = 200;
	public static final double DEFAULT_MITTLERER_STUECKINHALT_FM_PRO_STK = 1;
	public static final double DEFAULT_SCHLAGORDNUNG_KATEGORIE = 1;
	public static final double DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE = 1;
	public static final double DEFAULT_FAHRENTFERNUNG_KATEGORIE = 1;
	public static final double DEFAULT_ZUZUGENTFERNUNG_KATEGORIE = 3;
	public static final double DEFAULT_RUECKEART_KATEGORIE = 3;
	public static final double DEFAULT_RUECKEN_IM_SAFT_KATEGORIE = 2;
	public static final double DEFAULT_RUECKEBEDINGUNGEN_KATEGORIE = 3;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_MASCHINIST_PRO_H = 60;
	public static final double DEFAULT_KOSTEN_RUECKEGEHILFE_PRO_H = 50;
	public static final double DEFAULT_RUECKEGEHILFE_EINSATZANTEIL_PRZ = 0;
	public static final double DEFAULT_KOSTEN_SCHLEPPER_PRO_H = 90;
	public static final double DEFAULT_SCHLEPPERTYP_KATEGORIE = 2;
	
	
	public SimpleSchlepper1992() {
		super(new ModelSchlepper());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3(DEFAULT_HOLZMENGE_M3);
		this.setMittlererStueckinhalt_FmProStk(DEFAULT_MITTLERER_STUECKINHALT_FM_PRO_STK);
		this.setSchlagordnung_Kategorie(DEFAULT_SCHLAGORDNUNG_KATEGORIE);
		this.setAnzahlSortimente_Kategorie(DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE);
		this.setFahrentfernung_Kategorie(DEFAULT_FAHRENTFERNUNG_KATEGORIE);
		this.setZuzugentfernung_Kategorie(DEFAULT_ZUZUGENTFERNUNG_KATEGORIE);
		this.setRueckeart_Kategorie(DEFAULT_RUECKEART_KATEGORIE);
		this.setRueckenImSaft_Kategorie(DEFAULT_RUECKEN_IM_SAFT_KATEGORIE);
		this.setRueckebedingungen_Kategorie(DEFAULT_RUECKEBEDINGUNGEN_KATEGORIE);

		// Arbeitssystem
		this.setKostenMaschinist_proH(DEFAULT_KOSTEN_MASCHINIST_PRO_H);
		this.setKostenRueckegehilfe_proH(DEFAULT_KOSTEN_RUECKEGEHILFE_PRO_H);
		this.setRueckgehilfeEinsatzanteil_Prz(DEFAULT_RUECKEGEHILFE_EINSATZANTEIL_PRZ);
		this.setKostenSchlepper_proH(DEFAULT_KOSTEN_SCHLEPPER_PRO_H);
		this.setSchleppertyp_Kategorie(DEFAULT_SCHLEPPERTYP_KATEGORIE);
	}
	
	
	
	public void setHolzmenge_m3(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	public void setMittlererStueckinhalt_FmProStk(double value) {
		dirty = true;
		model.getArbeitsobjekt().setMittlererStueckInhalt(value);
	}

	/**
	 * 
	 * @param value "weniger als 1/4 der Stücke nicht eingehalten"=1, "mehr als 1/4 der Stücke nicht eingehalten"=2
	 */
	public void setSchlagordnung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setSchlagordnung(Schlagordnung.WenigerAlsEinViertelNichtEingehalten);
			return;
		case 2:
			model.getArbeitsobjekt().setSchlagordnung(Schlagordnung.MehrAlsEinViertelNichtEingehalten);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value &lt;4=1, 4-6=2, &gt;6=3
	 */
	public void setAnzahlSortimente_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.WenigerAlsVier);
			return;
		case 2:
			model.getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.VierBisSechs);
			return;
		case 3:
			model.getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.MehrAlsSechs);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value &lt;200m=1, 200-400m=2, &gt;400m=3
	 */
	public void setFahrentfernung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setFahrentfernung(Fahrentfernung.Bis200m);
			return;
		case 2:
			model.getArbeitsobjekt().setFahrentfernung(Fahrentfernung.Ab200bis400m);
			return;
		case 3:
			model.getArbeitsobjekt().setFahrentfernung(Fahrentfernung.Ueber400m);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value &lt;20m=1, 20-30m=2, 30-40m=3, &gt;40m=4
	 */
	public void setZuzugentfernung_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setZuzugentfernung(Zuzugentfernung.Bis20m);
			return;
		case 2:
			model.getArbeitsobjekt().setZuzugentfernung(Zuzugentfernung.Ab20bis30m);
			return;
		case 3:
			model.getArbeitsobjekt().setZuzugentfernung(Zuzugentfernung.Ab30bis40m);
			return;
		case 4:
			model.getArbeitsobjekt().setZuzugentfernung(Zuzugentfernung.Ueber40m);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4]");
		}
	}
	
	/**
	 * 
	 * @param value Nur Vorrücken=1, Nur Fertigrücken=2, Gesamtes Rücken=3
	 */
	public void setRueckeart_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setRueckeart(Rueckeart.NurVorruecken);
			return;
		case 2:
			model.getArbeitsobjekt().setRueckeart(Rueckeart.NurFertigruecken);
			return;
		case 3:
			model.getArbeitsobjekt().setRueckeart(Rueckeart.GesamtesRuecken);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3]");
		}
	}
	
	/**
	 * 
	 * @param value ja=1, nein=2
	 */
	public void setRueckenImSaft_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setRueckenImSaft(RueckenImSaft.Ja);
			return;
		case 2:
			model.getArbeitsobjekt().setRueckenImSaft(RueckenImSaft.Nein);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value sehr einfach=1, einfach=2, mittel=3, schwierig=4, sehr schwierig=5
	 */
	public void setRueckebedingungen_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setRueckeBedingungen(Rueckebedingungen.SehrEinfach);
			return;
		case 2:
			model.getArbeitsobjekt().setRueckeBedingungen(Rueckebedingungen.Einfach);
			return;
		case 3:
			model.getArbeitsobjekt().setRueckeBedingungen(Rueckebedingungen.Mittel);
			return;
		case 4:
			model.getArbeitsobjekt().setRueckeBedingungen(Rueckebedingungen.Schwierig);
			return;
		case 5:
			model.getArbeitsobjekt().setRueckeBedingungen(Rueckebedingungen.SehrSchwierig);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2,3,4,5]");
		}
	}
	
	public void setKostenMaschinist_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenRueckegehilfe_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzRueckegehilfe(value);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%.
	 */
	public void setRueckgehilfeEinsatzanteil_Prz(double value) {
		dirty = true;
		model.getArbeitssystem().setRueckegehilfe(value > 0);
		model.getArbeitssystem().setRueckgehilfeEinsatzanteil(RueckgehilfeEinsatzanteil.parseDouble_Prz(value));
	}
	
	
	public void setKostenSchlepper_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	/**
	 * 
	 * @param value Forstspezialschlepper=1, andere Schleppertypen=2
	 */
	public void setSchleppertyp_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitssystem().setSchlepperTyp(SchlepperTyp.Forstspezialschlepper);
			return;
		case 2:
			model.getArbeitssystem().setSchlepperTyp(SchlepperTyp.AndereSchleppertypen);
			return;
		default:
			throw new IllegalArgumentException("value must be [1,2]");
		}
	}

	
	public double getZeitMaschinist_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitSchlepper_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenMaschinist_proM3() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenSchlepper_proM3() {
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
	

	public double getProduktivitaet_m3proPSH15() {
		recalc();
		return ergebnis.getProduktivitaet();
	}

}
