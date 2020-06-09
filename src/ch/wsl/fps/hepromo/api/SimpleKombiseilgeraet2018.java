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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018.EquipeAnzahlPersonen;
import ch.wsl.fps.hepromo.model.modelle.ModelKombiseilgeraet2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleKombiseilgeraet2018 extends AbstractSimpleModel<ModelKombiseilgeraet2018> {  //TODO(enhancement): Ergebnisanzeige

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 600;
	public static final double DEFAULT_ANZAHL_LINIEN_ANZAHL = 2;
	public static final double DEFAULT_MITTLERE_LINIENLAENGE_M = 350;
	public static final double DEFAULT_ANZAHL_STUETZEN_PRO_LINIE_ANZAHL = 2;
	public static final double DEFAULT_HANGNEIGUNG_PRZ = 50;
	public static final double DEFAULT_ANTEIL_LAUBHOLZ_PRZ = 20;
	public static final double DEFAULT_ERSCHWERNISSE_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_EQUIPE_ANZAHL_ARBEITER_ANZAHL = 3.5;
	public static final double DEFAULT_ANTEIL_EINSATZZEIT_LAGERPLATZFAHRZEUG_PRZ = 50;
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_PERSON_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_KOMBISEILGERAET_PRO_H = 280;
	public static final double DEFAULT_KOSTEN_MOTORSAEGE_PRO_H = 18;
	public static final double DEFAULT_KOSTEN_LAGERPLATZFAHRZEUG_INKL_FAHRER_PRO_H = 170;
	

	public SimpleKombiseilgeraet2018() {
		super(new ModelKombiseilgeraet2018());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setAnzahlLinien_Anzahl(DEFAULT_ANZAHL_LINIEN_ANZAHL);
		this.setMittlereLinienlaenge_m(DEFAULT_MITTLERE_LINIENLAENGE_M);
		this.setAnzahlStuetzenProLinie_Anzahl(DEFAULT_ANZAHL_STUETZEN_PRO_LINIE_ANZAHL);
		this.setHangneigung_Prz(DEFAULT_HANGNEIGUNG_PRZ);
		this.setAnteilLaubholz_Prz(DEFAULT_ANTEIL_LAUBHOLZ_PRZ);
		this.setErschwernisse_Kategorie(DEFAULT_ERSCHWERNISSE_KATEGORIE);

		// Arbeitssystem
		this.setEquipeAnzahlArbeiter_Anzahl(DEFAULT_EQUIPE_ANZAHL_ARBEITER_ANZAHL);
		this.setAnteilEinsatzzeitLagerplatzfahrzeug_Prz(DEFAULT_ANTEIL_EINSATZZEIT_LAGERPLATZFAHRZEUG_PRZ);
		this.setKostenPersonalProPerson_proH(DEFAULT_KOSTEN_PERSONAL_PRO_PERSON_PRO_H);
		this.setKostenKombiseilgeraet_proH(DEFAULT_KOSTEN_KOMBISEILGERAET_PRO_H);
		this.setKostenMotorsaege_proH(DEFAULT_KOSTEN_MOTORSAEGE_PRO_H);
		this.setKostenLagerplatzfahrzeugInklFahrer_proH(DEFAULT_KOSTEN_LAGERPLATZFAHRZEUG_INKL_FAHRER_PRO_H);
	}
	
	
	public void setHolzmenge_m3iR(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlLinien_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnzahlAufstellungen(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMittlereLinienlaenge_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setLaengeProAufstellung_m(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlStuetzenProLinie_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnzahlStuetzenProAufstellung(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setHangneigung_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setHangneigung_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnteilLaubholz_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setAnteilLaubholz_Prz(intValue);
	}
	
	/**
	 * 
	 * @param value Keine=1, Vorhanden=2
	 */
	public void setErschwernisse_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setErschwernisse(Erschwernisse.Keine);
			return;
		case 2:
			model.getArbeitsobjekt().setErschwernisse(Erschwernisse.Vorhanden);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}

	/**
	 * 
	 * @param value Valid values are 3, 3.5, and 4
	 */
	public void setEquipeAnzahlArbeiter_Anzahl(double value) {
		dirty = true;
		
		if (value > 2.9 && value < 3.1) {
			model.getArbeitssystem().setEquipeAnzahlPersonen(EquipeAnzahlPersonen.Anzahl3);
			return;
		}
		else if (value > 3.4 && value < 3.6) {
			model.getArbeitssystem().setEquipeAnzahlPersonen(EquipeAnzahlPersonen.Anzahl3einhalb);
			return;
		}
		else if (value > 3.9 && value < 4.1) {
			model.getArbeitssystem().setEquipeAnzahlPersonen(EquipeAnzahlPersonen.Anzahl4);
			return;
		}
		else {
			throw new IllegalArgumentException("value must be in [3, 3.5, 4]");
		}
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnteilEinsatzzeitLagerplatzfahrzeug_Prz(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setAnteilEinsatzzeitVerzugsfahrzeug_Prz(intValue);
	}
	
	public void setKostenPersonalProPerson_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenKombiseilgeraet_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	public void setKostenMotorsaege_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine2_proH(value);
	}
	
	public void setKostenLagerplatzfahrzeugInklFahrer_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzVerzugsfahrzeug_proH(value);
	}


	public double getZeitPersonal_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitKombiseilgeraet_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}

	public double getZeitMotorsaege_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine2();
	}

	public double getZeitLagerplatzfahrzeugInklFahrer_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine3();
	}

	
	public double getKostenPersonal_proM3oR() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenKombiseilgeraet_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenMotorsaege_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine2_proM3();
	}

	public double getKostenLagerplatzfahrzeugInklFahrer_proM3oR() {
		recalc();
		return ergebnis.getKostenMaschine3_proM3();
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

	public double getKostenKombiseilgeraet_total() {
		recalc();
		return ergebnis.getKostenMaschine1_total();
	}

	public double getKostenMotorsaege_total() {
		recalc();
		return ergebnis.getKostenMaschine2_total();
	}

	public double getKostenLagerplatzfahrzeugInklFahrer_total() {
		recalc();
		return ergebnis.getKostenMaschine3_total();
	}

	public double getKostenUmsetzen_total() {
		recalc();
		return ergebnis.getKostenUmsetzen_total();
	}

	public double getKostenWeitereAufwaende_total() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_total();
	}

	
	public double getProduktivitaet_m3iRproWSH() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.M3_IR_PRO_WSH);
	}

}
