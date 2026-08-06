/*******************************************************************************
 * Copyright 2026 Stefan Holm
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
package ch.wsl.fps.hepromo.api.chain;

import ch.wsl.fps.hepromo.api.SimpleKonventionellerSeilkran1999;
import ch.wsl.fps.hepromo.api.SimpleMotormanuell2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMotorManualCableYarder extends AbstractSimpleChain { //TODO: setter for mounting/removal are completely missing.

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleMotormanuell2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleMotormanuell2014.DEFAULT_BHD_CM;
	public static final double DEFAULT_PERCENTAGE_OF_PINE_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_FOEHRE_PRZ;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_LAUBHOLZ_PRZ;
	public static final double DEFAULT_REGION_CATEGORY = SimpleMotormanuell2014.DEFAULT_REGION_KATEGORIE;

//	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleKonventionellerSeilkran1999.DEFAULT_HOLZMENGE_M3_IR; // we use the default of the motor manual model for this constant
	public static final double DEFAULT_MEAN_LOG_VOLUME_M3_OB = SimpleKonventionellerSeilkran1999.DEFAULT_MITTLERER_STUECKINHALT_M3_IR;
	public static final double DEFAULT_STEM_LENGTH_M = SimpleKonventionellerSeilkran1999.DEFAULT_STUECKLAENGE_M;
	public static final double DEFAULT_TYPE_OF_TREATMENT_CATEGORY = SimpleKonventionellerSeilkran1999.DEFAULT_EINGRIFFSART_KATEGORIE;
	public static final double DEFAULT_LOGGING_AREA_CATEGORY = SimpleKonventionellerSeilkran1999.DEFAULT_HOLZSEILORT_KATEGORIE;
	public static final double DEFAULT_SLOPE_PCT = SimpleKonventionellerSeilkran1999.DEFAULT_HANGNEIGUNG_PRZ;
	public static final double DEFAULT_OBSTACLES_CATEGORY = SimpleKonventionellerSeilkran1999.DEFAULT_HINDERNISSE_KATEGORIE;
	public static final double DEFAULT_LINE_LENGHT_M = SimpleKonventionellerSeilkran1999.DEFAULT_LINIENLAENGE_M;
	public static final double DEFAULT_LOGGING_DIRECTION_CATEGORY = SimpleKonventionellerSeilkran1999.DEFAULT_FAHRTRICHTUNG_KATEGORIE;
	public static final double DEFAULT_MEAN_LOGGING_DISTANCE_M = SimpleKonventionellerSeilkran1999.DEFAULT_MITTLERE_FAHRDISTANZ_M;
	public static final double DEFAULT_MEAN_DISTANCE_LATERAL_DRAGGING_M = SimpleKonventionellerSeilkran1999.DEFAULT_MITTLERE_DISTANZ_SEITLICHER_ZUZUG_M;
	public static final double DEFAULT_NUMBER_OF_SUPPORTS_NUMBER = SimpleKonventionellerSeilkran1999.DEFAULT_ANZAHL_STUETZEN_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_TERMINAL_SUPPORTS_NUMBER = SimpleKonventionellerSeilkran1999.DEFAULT_ANZAHL_ENDMASTEN_ANZAHL;
	public static final double DEFAULT_SKYLINE_HEIGHT_STAND_M = SimpleKonventionellerSeilkran1999.DEFAULT_TRAGSEILHOEHE_BESTAND_M;
	public static final double DEFAULT_SKYLINE_HEIGHT_LANDING_M = SimpleKonventionellerSeilkran1999.DEFAULT_TRAGSEILHOEHE_LAGERPLATZ_M;


	// Work system
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;

//	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleKonventionellerSeilkran1999.DEFAULT_KOSTEN_PERSONAL_PRO_H; // we use the default of the motor manual model for this constant
	public static final double DEFAULT_COSTS_CABLE_YARDER_PER_H = SimpleKonventionellerSeilkran1999.DEFAULT_KOSTEN_SEILKRANANLAGE_PRO_H;
	public static final double DEFAULT_COSTS_VEHICLE_WITH_CRANE_PER_H = SimpleKonventionellerSeilkran1999.DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H;
	public static final double DEFAULT_OPERATING_TIME_VEHICLE_WITH_CRANE_PCT = SimpleKonventionellerSeilkran1999.DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ;
	public static final double DEFAULT_NUMBER_OF_WORKERS_INSTALLATION_NUMBER = SimpleKonventionellerSeilkran1999.DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LOGGING_NUMBER = SimpleKonventionellerSeilkran1999.DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL;


	private final SimpleMotormanuell2014 motorManual;
	private final SimpleKonventionellerSeilkran1999 cableYarder;



	public SimpleMotorManualCableYarder() {
		this(new SimpleMotormanuell2014(), new SimpleKonventionellerSeilkran1999());
	}

	private SimpleMotorManualCableYarder(SimpleMotormanuell2014 motorManual, SimpleKonventionellerSeilkran1999 cableYarder) {
		super(motorManual, cableYarder);
		this.motorManual = motorManual;
		this.cableYarder = cableYarder;
		this.setTimberVolume_m3ob(DEFAULT_TIMBER_VOLUME_M3_OB); // to ensure both models use the same default timber volume
		this.setCostsStaff_perH(DEFAULT_COSTS_STAFF_PER_H); // to ensure both models use the same default staff costs
	}

	
	
	public void setTimberVolume_m3ob(double value) {
		motorManual.setHolzmenge_m3iR(value);
		cableYarder.setHolzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDbh_cm(double value) {
		motorManual.setMittlererBhd_cm(value);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setPercentageOfPine_pct(double value) {
		motorManual.setAnteilFoehre_Prz(value);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setPercentageOfBroadleaves_pct(double value) {
		motorManual.setAnteilLaubholz_Prz(value);
	}
	
	/**
	 * 
	 * @param value mountainous=1, flat or hilly=2
	 */
	public void setRegion_category(double value) {
		motorManual.setRegion_Kategorie(value);
	}
	

	public void setMeanLogVolume_m3ob(double value) {
		cableYarder.setMittlererStueckinhalt_m3iR(value);
	}
	
	public void setStemLength_m(double value) {
		cableYarder.setStuecklaenge_m(value);
	}
	
	/**
	 * 
	 * @param value thinning=1, opening up=2, final cutting=3
	 */
	public void setTypeOfTreatment_category(double value) {
		cableYarder.setEingriffsart_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value of the cutting area=1, off-the-pile=2
	 */
	public void setLoggingArea_category(double value) {
		cableYarder.setHolzseilort_Kategorie(value);
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setSlope_pct(double value) {
		cableYarder.setHangneigung_Prz(value);
	}
	
	/**
	 * 
	 * @param value normal=1, hindered=2, extreme=3
	 */
	public void setObstacles_category(double value) {
		cableYarder.setHindernisse_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setLineLength_m(double value) {
		cableYarder.setLinienlaenge_m(value);
	}
	
	/**
	 * 
	 * @param value downhill=1, uphill=2
	 */
	public void setLoggingDirection_category(double value) {
		cableYarder.setFahrtrichtung_Kategorie(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanLoggingDistance_m(double value) {
		cableYarder.setMittlereFahrdistanz_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDistanceLateralDragging_m(double value) {
		cableYarder.setMittlereDistanzSeitlicherZuzug_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSupports_number(double value) {
		cableYarder.setAnzahlStuetzen_Anzahl(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfTerminalSupports_number(double value) {
		cableYarder.setAnzahlEndmasten_Anzahl(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSkylineHeightStand_m(double value) {
		cableYarder.setTragseilhoeheBestand_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSkylineHeightLanding_m(double value) {
		cableYarder.setTragseilhoeheLagerplatz_m(value);
	}
	
	
	/**
	 * This method belongs to both models
	 */
	public void setCostsStaff_perH(double value) {
		motorManual.setKostenPersonal_proH(value);
		cableYarder.setKostenPersonal_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		motorManual.setKostenMotorsaege_proH(value);
	}
	
	public void setCostsCableYarder_perH(double value) {
		cableYarder.setKostenSeilkrananlage_proH(value);
	}
	
	public void setCostsVehicleWithCrane_perH(double value) {
		cableYarder.setKostenKranfahrzeug_proH(value);
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setOperatingTimeVehicleWithCrane_pct(double value) {
		cableYarder.setKranfahrzeugLaufzeit_Prz(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersInstallation_number(double value) {
		cableYarder.setAnzahlPersonenInstallation_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersLogging_number(double value) {
		cableYarder.setAnzahlPersonenSeilen_Anzahl(value);
	}
	
	
	
	/**
	 * This method belongs to both models
	 */
	public double getTimeStaff_WPPH() {
		return motorManual.getZeitPersonal_WPPH()
				+ cableYarder.getZeitPersonal_WPPH();
	}

	public double getTimeChainsaw_PMH15() {
		return motorManual.getZeitMotorsaege_PMH15();
	}

	public double getTimeCableYarder_PMH15() {
		return cableYarder.getZeitSeilkrananlage_PMH15();
	}

	public double getTimeVehicleWithCrane_PMH15() {
		return cableYarder.getZeitKranfahrzeug_PMH15();
	}
	

	/**
	 * This method belongs to both models
	 */
	public double getCostsStaff_perM3ub() {
		return motorManual.getKostenPersonal_proM3oR()
				+ cableYarder.getKostenPersonal_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return motorManual.getKostenMotorsaege_proM3oR();
	}

	public double getCostsCableYarder_perM3ub() {
		return cableYarder.getKostenSeilkrananlage_proM3oR();
	}

	public double getCostsVehicleWithCrane_perM3ub() {
		return cableYarder.getKostenKranfahrzeug_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return motorManual.getKostenUmsetzen_proM3oR()
				+ cableYarder.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return motorManual.getKostenWeitereAufwaende_proM3oR()
				+ cableYarder.getKostenWeitereAufwaende_proM3oR();
	}

	public double getCostsTotal_perM3ub() {
		return motorManual.getKostenTotal_proM3oR()
				+ cableYarder.getKostenTotal_proM3oR();
	}
	

	/**
	 * This method belongs to both models
	 */
	public double getCostsStaff_total() {
		return motorManual.getKostenPersonal_total()
				+ cableYarder.getKostenPersonal_total();
	}

	public double getCostsChainsaw_total() {
		return motorManual.getKostenMotorsaege_total();
	}

	public double getCostsCableYarder_total() {
		return cableYarder.getKostenSeilkrananlage_total();
	}

	public double getCostsVehicleWithCrane_total() {
		return cableYarder.getKostenKranfahrzeug_total();
	}

	public double getCostsTransfer_total() {
		return motorManual.getKostenUmsetzen_total() + cableYarder.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return motorManual.getKostenWeitereAufwaende_total() + cableYarder.getKostenWeitereAufwaende_total();
	}
	
	public double getProductivityMotorManual_m3obPerWPSH() {
		return motorManual.getProduktivitaet_m3iRproWPSH();
	}
	

	public double getProductivityLoggingCableYarder_m3obPerPMH15() {
		return cableYarder.getProduktivitaetBeimSeilen_m3iRproPMH15();
	}

}
