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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.modelle.ModelHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHacker2018 extends AbstractSimpleModel<ModelHacker2018> {

	// Arbeitsobjekt
	public static final double DEFAULT_HACKSCHNITZELMENGE_SRM = 50;
	public static final double DEFAULT_POLTERSORTIMENT_KATEGORIE = 1;

	// Arbeitssystem
	public static final double DEFAULT_MOTORLEISTUNG_HACKER_KW = 350;
	public static final double DEFAULT_KOSTEN_MASCHINIST_PRO_H = 70;
	public static final double DEFAULT_KOSTEN_HACKER_PRO_H = 320;
	

	public SimpleHacker2018() {
		super(new ModelHacker2018());
		
		// Arbeitsobjekt
		this.setHackschnitzelmenge_Srm(DEFAULT_HACKSCHNITZELMENGE_SRM);
		this.setPoltersortiment_Kategorie(DEFAULT_POLTERSORTIMENT_KATEGORIE);

		// Arbeitssystem
		this.setMotorleistungHacker_kW(DEFAULT_MOTORLEISTUNG_HACKER_KW);
		this.setKostenMaschinist_proH(DEFAULT_KOSTEN_MASCHINIST_PRO_H);
		this.setKostenHacker_proH(DEFAULT_KOSTEN_HACKER_PRO_H);
	}
	
	
	public void setHackschnitzelmenge_Srm(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	/**
	 * 
	 * @param value Waldrestholz=1, Energierundholz=2
	 */
	public void setPoltersortiment_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setZielsortiment(Zielsortiment.Waldrestholz);
			return;
		case 2:
			model.getArbeitsobjekt().setZielsortiment(Zielsortiment.Energierundholz);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMotorleistungHacker_kW(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitssystem().setHackerMotorleistung(ArbeitssystemHacker2018.getBenutzerdefinierteHackerMotorleistung(intValue));
	}
	
	public void setKostenMaschinist_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzPersonal1_proH(value);
	}
	
	public void setKostenHacker_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	
	public double getZeitMaschinist_WPPH() {
		recalc();
		return ergebnis.getZeitPersonal();
	}

	public double getZeitHacker_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenMaschinist_proSrm() {
		recalc();
		return ergebnis.getKostenPersonal_proM3();
	}

	public double getKostenHacker_proSrm() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenUmsetzen_proSrm() {
		recalc();
		return ergebnis.getKostenUmsetzen_proM3();
	}

	public double getKostenWeitereAufwaende_proSrm() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_proM3();
	}

	public double getKostenTotal_proSrm() {
		recalc();
		return ergebnis.getKostenTotal_proM3();
	}
	

	public double getKostenMaschinist_total() {
		recalc();
		return ergebnis.getKostenPersonal_total();
	}

	public double getKostenHacker_total() {
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


	public double getProduktivitaet_SrmProPMH15() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.SRM_PRO_PMH15);
	}
	
}
