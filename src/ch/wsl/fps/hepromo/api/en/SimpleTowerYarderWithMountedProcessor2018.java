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
package ch.wsl.fps.hepromo.api.en;

import ch.wsl.fps.hepromo.api.SimpleKombiseilgeraet2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleTowerYarderWithMountedProcessor2018 extends AbstractSimpleModel<SimpleKombiseilgeraet2018> {

	// Work item
	public static final double DEFAULT_WOOD_VOLUME_M3_OB = SimpleKombiseilgeraet2018.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_NUMBER_OF_LINES_NUMBER = SimpleKombiseilgeraet2018.DEFAULT_ANZAHL_LINIEN_ANZAHL;
	public static final double DEFAULT_MEAN_LINE_LENGTH_M = SimpleKombiseilgeraet2018.DEFAULT_MITTLERE_LINIENLAENGE_M;
	public static final double DEFAULT_NUMBER_OF_SUPPORTS_PER_LINE_NUMBER = SimpleKombiseilgeraet2018.DEFAULT_ANZAHL_STUETZEN_PRO_LINIE_ANZAHL;
	public static final double DEFAULT_SLOPE_PCT = SimpleKombiseilgeraet2018.DEFAULT_HANGNEIGUNG_PRZ;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_PCT = SimpleKombiseilgeraet2018.DEFAULT_ANTEIL_LAUBHOLZ_PRZ;
	public static final double DEFAULT_DIFFICULTIES_CATEGORY = SimpleKombiseilgeraet2018.DEFAULT_ERSCHWERNISSE_KATEGORIE;

	// Work system
	public static final double DEFAULT_CREW_NUMBER_OF_WORKERS_NUMBER = SimpleKombiseilgeraet2018.DEFAULT_EQUIPE_ANZAHL_ARBEITER_ANZAHL;
	public static final double DEFAULT_OPERATING_TIME_ENGINE_ON_LANDING_PLACE_PCT = SimpleKombiseilgeraet2018.DEFAULT_ANTEIL_EINSATZZEIT_LAGERPLATZFAHRZEUG_PRZ;
	public static final double DEFAULT_COSTS_STAFF_PER_PERSON_PER_H = SimpleKombiseilgeraet2018.DEFAULT_KOSTEN_PERSONAL_PRO_PERSON_PRO_H;
	public static final double DEFAULT_COSTS_TOWER_YARDER_WITH_MOUNTED_PROCESSOR_PER_H = SimpleKombiseilgeraet2018.DEFAULT_KOSTEN_KOMBISEILGERAET_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleKombiseilgeraet2018.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;
	public static final double DEFAULT_COSTS_ENGINE_ON_LANDING_PLACE_INCL_DRIVER_PER_H = SimpleKombiseilgeraet2018.DEFAULT_KOSTEN_LAGERPLATZFAHRZEUG_INKL_FAHRER_PRO_H;
	

	public SimpleTowerYarderWithMountedProcessor2018() {
		super(new SimpleKombiseilgeraet2018());
	}
	
	
	public void setWoodVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfLines_number(double value) {
		adaptee.setAnzahlLinien_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanLineLength_m(double value) {
		adaptee.setMittlereLinienlaenge_m(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSupportsPerLine_number(double value) {
		adaptee.setAnzahlStuetzenProLinie_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSlope_pct(double value) {
		adaptee.setHangneigung_Prz(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setPercentageOfBroadleaves_pct(double value) {
		adaptee.setAnteilLaubholz_Prz(value);
	}
	
	/**
	 * 
	 * @param value no difficulties=1, difficulties present=2
	 */
	public void setDifficulties_category(double value) {
		adaptee.setErschwernisse_Kategorie(value);
	}

	/**
	 * 
	 * @param value Valid values are 3, 3.5, and 4
	 */
	public void setCrewNumberOfWorkers_number(double value) {
		adaptee.setEquipeAnzahlArbeiter_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setOperatingTimeEngineOnLandingPlace_pct(double value) {
		adaptee.setAnteilEinsatzzeitLagerplatzfahrzeug_Prz(value);
	}
	
	public void setCostsStaffPerPerson_perH(double value) {
		adaptee.setKostenPersonalProPerson_proH(value);
	}
	
	public void setCostsTowerYarderWithMountedProcessor_perH(double value) {
		adaptee.setKostenKombiseilgeraet_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		adaptee.setKostenMotorsaege_proH(value);
	}
	
	public void setCostsEngineOnLandingPlaceInclDriver_perH(double value) {
		adaptee.setKostenLagerplatzfahrzeugInklFahrer_proH(value);
	}


	public double getTimeStaff_WPPH() {
		return adaptee.getZeitPersonal_WPPH();
	}

	public double getTimeTowerYarderWithMountedProcessor_PMH15() {
		return adaptee.getZeitKombiseilgeraet_PMH15();
	}

	public double getTimeChainsaw_PMH15() {
		return adaptee.getZeitMotorsaege_PMH15();
	}

	public double getTimeEngineOnLandingPlaceInclDriver_PMH15() {
		return adaptee.getZeitLagerplatzfahrzeugInklFahrer_PMH15();
	}

	
	public double getCostsStaff_perM3ub() {
		return adaptee.getKostenPersonal_proM3oR();
	}

	public double getCostsTowerYarderWithMountedProcessor_perM3ub() {
		return adaptee.getKostenKombiseilgeraet_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return adaptee.getKostenMotorsaege_proM3oR();
	}

	public double getCostsEngineOnLandingPlaceInclDriver_perM3ub() {
		return adaptee.getKostenLagerplatzfahrzeugInklFahrer_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return adaptee.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return adaptee.getKostenWeitereAufwaende_proM3oR();
	}

	public double getCostsTotal_perM3ub() {
		return adaptee.getKostenTotal_proM3oR();
	}

	
	public double getCostsStaff_total() {
		return adaptee.getKostenPersonal_total();
	}

	public double getCostsTowerYarderWithMountedProcessor_total() {
		return adaptee.getKostenKombiseilgeraet_total();
	}

	public double getCostsChainsaw_total() {
		return adaptee.getKostenMotorsaege_total();
	}

	public double getCostsEngineOnLandingPlaceInclDriver_total() {
		return adaptee.getKostenLagerplatzfahrzeugInklFahrer_total();
	}

	public double getCostsTransfer_total() {
		return adaptee.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return adaptee.getKostenWeitereAufwaende_total();
	}

	
	public double getProductivity_m3obPerWSH() {
		return adaptee.getProduktivitaet_m3iRproWSH();
	}

}
