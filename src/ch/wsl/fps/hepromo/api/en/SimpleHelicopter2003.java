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

import ch.wsl.fps.hepromo.api.SimpleHelikopter2003;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHelicopter2003 extends AbstractSimpleModel<SimpleHelikopter2003> {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleHelikopter2003.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_WORK_METHOD_AT_LANDING_CATEGORY = SimpleHelikopter2003.DEFAULT_ARBEITSVERFAHREN_KATEGORIE;
	public static final double DEFAULT_KIND_OF_WOOD_CATEGORY = SimpleHelikopter2003.DEFAULT_HOLZTYP_KATEGORIE;
	public static final double DEFAULT_CLASS_OF_TREE_SPECIES_CATEGORY = SimpleHelikopter2003.DEFAULT_BAUMARTENGRUPPE_KATEGORIE;
	public static final double DEFAULT_HORIZONTAL_DISTANCE_M = SimpleHelikopter2003.DEFAULT_HORIZONTALDISTANZ_M;
	public static final double DEFAULT_VERTICAL_DISTANCE_DOWNWARDS_M = SimpleHelikopter2003.DEFAULT_VERTIKALDISTANZ_ABWAERTS_M;

	// Work system
	public static final double DEFAULT_COSTS_HELICOPTER_PER_MIN = SimpleHelikopter2003.DEFAULT_KOSTEN_HELIKOPTER_PRO_MIN;
	public static final double DEFAULT_FLAT_RATE_RETURN_FLIGHT_AMOUNT = SimpleHelikopter2003.DEFAULT_ANFLUGPAUSCHALE_BETRAG;
	public static final double DEFAULT_HELICOPTER_CLASS_CATEGORY = SimpleHelikopter2003.DEFAULT_HELIKOPTERKLASSE_KATEGORIE;
	public static final double DEFAULT_CALCULATE_LOAD_VOLUME_AUTOMATICALLY_CATEGORY = SimpleHelikopter2003.DEFAULT_LASTVOLUMEN_AUTOMATISCH_BERECHNEN_KATEGORIE;
	public static final double DEFAULT_LOAD_VOLUME_M3 = SimpleHelikopter2003.DEFAULT_LASTVOLUMEN_M3;
	public static final double DEFAULT_NUMBER_OF_STAFF_AT_LOGGING_NUMBER = SimpleHelikopter2003.DEFAULT_ANZAHL_PERSONAL_BEIM_HOLZ_FLIEGEN_ANZAHL;
	public static final double DEFAULT_COSTS_STAFF_PER_PERSON_PER_H = SimpleHelikopter2003.DEFAULT_KOSTEN_PERSONAL_PRO_PERSON_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAWS_PER_CHAINSAW_PER_H = SimpleHelikopter2003.DEFAULT_KOSTEN_MOTORSAEGEN_PRO_MOTORSAEGE_PRO_H;
	public static final double DEFAULT_COSTS_VEHICLE_WITH_CRANE_PER_H = SimpleHelikopter2003.DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H;
	public static final double DEFAULT_CALCULATION_INCL_WORK_AT_LANDING_CATEGORY = SimpleHelikopter2003.DEFAULT_KALKULATION_INKL_LAGERPLATZARBEIT_KATEGORIE;
	

	public SimpleHelicopter2003() {
		super(new SimpleHelikopter2003());
	}
	
	
	public void setTimberVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value cut-to-length method=1, full tree method=2
	 */
	public void setWorkMethodAtLanding_category(double value) {
		adaptee.setArbeitsverfahren_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value broadleaves (green)=1, broadleaves (dried)=2, conifers (green)=3, conifers (dried)=4
	 */
	public void setKindOfWood_category(double value) {
		adaptee.setHolztyp_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value norway spruce=1, silver fir=2, pine/larch=3
	 */
	public void setClassOfTreeSpecies_category(double value) {
		adaptee.setBaumartengruppe_Kategorie(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setHorizontalDistance_m(double value) {
		adaptee.setHorizontaldistanz_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setVerticalDistanceDownwards_m(double value) {
		adaptee.setVertikaldistanzAbwaerts_m(value);
	}
	
	public void setCostsHelicopter_perMin(double value) {
		adaptee.setKostenHelikopter_proMin(value);
	}
	
	public void setFlatRateReturnFlight_amount(double value) {
		adaptee.setAnflugpauschale_Betrag(value);
	}
	
	/**
	 * 
	 * @param value light=1, medium-weight=2, heavy=3
	 */
	public void setHelicopterClass_category(double value) {
		adaptee.setHelikopterklasse_Kategorie(value);
	}
	
	/**
	 * If set to true, the load volume is calculated automatically
	 * based on helicopter class and kind of wood.
	 * 
	 * @param value yes=1, no=2
	 */
	public void setCalculateLoadVolumeAutomatically_category(double value) {
		adaptee.setLastVolumenAutomatischBerechnen_Kategorie(value);
	}
	
	/**
	 * If the load volume needs to be set manually, it is necessary to set
	 * calculateLoadVolumeAutomatically to "no" first, otherwise this method throws an Exception.
	 */
	public void setLoadVolume_m3(double value) {
		adaptee.setLastvolumen_m3(value);
	}
	
	/**
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfStaffAtLogging_number(double value) {
		adaptee.setAnzahlPersonalBeimHolzFliegen_Anzahl(value);
	}

	public void setCostsStaffPerPerson_perH(double value) {
		adaptee.setKostenPersonalProPerson_proH(value);
	}
	
	public void setCostsChainsawsPerChainsaw_perH(double value) {
		adaptee.setKostenMotorsaegenProMotorsaege_proH(value);
	}
	
	public void setCostsVehicleWithCrane_perH(double value) {
		adaptee.setKostenKranfahrzeug_proH(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setCalculationInclWorkAtLanding_category(double value) {
		adaptee.setKalkulationInklLagerplatzarbeit_Kategorie(value);
	}
	
	
	public double getTimeHelicopter_PMH15() {
		return adaptee.getZeitHelikopter_PMH15();
	}

	public double getTimeStaffForestEnterprise_WPPH() {
		return adaptee.getZeitPersonalForstbetrieb_WPPH();
	}

	public double getTimeChainsaw_PMH15() {
		return adaptee.getZeitMotorsaege_PMH15();
	}

	public double getTimeVehicleWithCrane_PMH15() {
		return adaptee.getZeitKranfahrzeug_PMH15();
	}
	

	public double getCostsHelicopter_perM3ub() {
		return adaptee.getKostenHelikopter_proM3oR();
	}

	public double getCostsStaffForestEnterprise_perM3ub() {
		return adaptee.getKostenPersonal_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return adaptee.getKostenMotorsaege_proM3oR();
	}

	public double getCostsVehicleWithCrane_perM3ub() {
		return adaptee.getKostenKranfahrzeug_proM3oR();
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

	
	public double getCostsHelicopter_total() {
		return adaptee.getKostenHelikopter_total();
	}

	public double getCostsStaffForestEnterprise_total() {
		return adaptee.getKostenPersonal_total();
	}

	public double getCostsChainsaw_total() {
		return adaptee.getKostenMotorsaege_total();
	}

	public double getCostsVehicleWithCrane_total() {
		return adaptee.getKostenKranfahrzeug_total();
	}

	public double getCostsTransfer_total() {
		return adaptee.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return adaptee.getKostenWeitereAufwaende_total();
	}
	
}
