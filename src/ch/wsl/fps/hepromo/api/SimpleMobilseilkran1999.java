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

import java.util.ArrayList;
import java.util.List;

import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen.SchwierigkeitSeitlicherZuzug;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMobilseilkran1999 extends AbstractSimpleModel<ModelMobilseilkranGesamt> { //TODO(enhancement): Setter für Tragseilhöhen für die einzelnen Stützen.

	// Arbeitsobjekt
	public static final double DEFAULT_HOLZMENGE_M3_IR = 400;
	public static final double DEFAULT_MITTLERER_STUECKINHALT_M3_IR = 0.4;
	public static final double DEFAULT_LINIENLAENGE_M = 600;
	public static final double DEFAULT_MITTLERE_FAHRDISTANZ_M = 300;
	public static final double DEFAULT_MITTLERE_DISTANZ_SEITLICHER_ZUZUG_M = 15;
	public static final double DEFAULT_SCHWIERIGKEIT_SEITLICHER_ZUZUG_KATEGORIE = 1;
	public static final double DEFAULT_SEILSYSTEM_KATEGORIE = 1;
	public static final double DEFAULT_MASCHINENSTANDORT_KATEGORIE = 2;
	public static final double DEFAULT_ANZAHL_STUETZEN_ANZAHL = 1;
	public static final double DEFAULT_TRAGSEILHOEHE_STUETZE_M = 12;
	public static final double DEFAULT_ENDMAST_VORHANDEN_KATEGORIE = 1;
	public static final double DEFAULT_TRAGSEILHOEHE_ENDMAST_M = 8;

	// Arbeitssystem
	public static final double DEFAULT_KOSTEN_PERSONAL_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_MOBILSEILKRAN_PRO_H = 140;
	public static final double DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H = 80;
	public static final double DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ = 75;
	public static final double DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL = 3;
	public static final double DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL = 2;

	public SimpleMobilseilkran1999() {
		super(new ModelMobilseilkranGesamt());
		
		// Arbeitsobjekt
		this.setHolzmenge_m3iR(DEFAULT_HOLZMENGE_M3_IR);
		this.setMittlererStueckinhalt_m3iR(DEFAULT_MITTLERER_STUECKINHALT_M3_IR);
		this.setLinienlaenge_m(DEFAULT_LINIENLAENGE_M);
		this.setMittlereFahrdistanz_m(DEFAULT_MITTLERE_FAHRDISTANZ_M);
		this.setMittlereDistanzSeitlicherZuzug_m(DEFAULT_MITTLERE_DISTANZ_SEITLICHER_ZUZUG_M);
		this.setSchwierigkeitSeitlicherZuzug_Kategorie(DEFAULT_SCHWIERIGKEIT_SEITLICHER_ZUZUG_KATEGORIE);
		this.setSeilsystem_Kategorie(DEFAULT_SEILSYSTEM_KATEGORIE);
		this.setMaschinenstandort_Kategorie(DEFAULT_MASCHINENSTANDORT_KATEGORIE);
		this.setAnzahlStuetzen_Anzahl(DEFAULT_ANZAHL_STUETZEN_ANZAHL);
		this.setEndmastVorhanden_Kategorie(DEFAULT_ENDMAST_VORHANDEN_KATEGORIE);
		this.setTragseilhoeheEndmast_m(DEFAULT_TRAGSEILHOEHE_ENDMAST_M);

		// Arbeitssystem
		this.setKostenPersonal_proH(DEFAULT_KOSTEN_PERSONAL_PRO_H);
		this.setKostenMobilseilkran_proH(DEFAULT_KOSTEN_MOBILSEILKRAN_PRO_H);
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
	 * @param value einfach=1, erschwert=2
	 */
	public void setSchwierigkeitSeitlicherZuzug_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug.Einfach);
			return;
		case 2:
			model.getArbeitsobjekt().setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug.Erschwert);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value Zweiseilsystem=1, Mehrseilsystem=2
	 */
	public void setSeilsystem_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setSeilsystem(Seilsystem.ZweiseilSystem);
			return;
		case 2:
			model.getArbeitsobjekt().setSeilsystem(Seilsystem.MehrseilSystem);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value unten=1, oben=2
	 */
	public void setMaschinenstandort_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setMaschinenStandort(MaschinenStandort.Unten);
			return;
		case 2:
			model.getArbeitsobjekt().setMaschinenStandort(MaschinenStandort.Oben);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setAnzahlStuetzen_Anzahl(double value) {
		int intValue = round(value);
		
		dirty = true;
		List<Integer> listStuetzenTragseilHoehen = new ArrayList<Integer>();
		for (int i=0; i<intValue; i++) {
			listStuetzenTragseilHoehen.add(round(DEFAULT_TRAGSEILHOEHE_STUETZE_M));
		}
		model.getArbeitsobjekt().setStuetzenTragseilHoehen(listStuetzenTragseilHoehen);
	}
	
	/**
	 * 
	 * @param value ja=1, nein=2
	 */
	public void setEndmastVorhanden_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setEndmast(true);
			return;
		case 2:
			model.getArbeitsobjekt().setEndmast(false);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTragseilhoeheEndmast_m(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setTragseilHoeheEndmast(intValue);
	}
	
	public void setKostenPersonal_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenMobilseilkran_proH(double value) {
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

	public double getZeitMobilseilkran_PMH15() {
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

	public double getKostenMobilseilkran_proM3oR() {
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

	public double getKostenMobilseilkran_total() {
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
