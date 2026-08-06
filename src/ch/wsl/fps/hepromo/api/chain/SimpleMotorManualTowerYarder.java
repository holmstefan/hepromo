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

import ch.wsl.fps.hepromo.api.SimpleMobilseilkran1999;
import ch.wsl.fps.hepromo.api.SimpleMotormanuell2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMotorManualTowerYarder extends AbstractSimpleChain {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleMotormanuell2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleMotormanuell2014.DEFAULT_BHD_CM;
	public static final double DEFAULT_PERCENTAGE_OF_PINE_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_FOEHRE_PRZ;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_LAUBHOLZ_PRZ;
	public static final double DEFAULT_REGION_CATEGORY = SimpleMotormanuell2014.DEFAULT_REGION_KATEGORIE;

	@SuppressWarnings("unused")
	private static final double DEFAULT_EXTRACTED_VOLUME_M3_OB = SimpleMobilseilkran1999.DEFAULT_HOLZMENGE_M3_IR; //this constant and the corresponding setter are not used in this process chain
	public static final double DEFAULT_MEAN_LOG_VOLUME_M3_OB = SimpleMobilseilkran1999.DEFAULT_MITTLERER_STUECKINHALT_M3_IR;
	public static final double DEFAULT_LINE_LENGTH_M = SimpleMobilseilkran1999.DEFAULT_LINIENLAENGE_M;
	public static final double DEFAULT_MEAN_LOGGING_DISTANCE_M = SimpleMobilseilkran1999.DEFAULT_MITTLERE_FAHRDISTANZ_M;
	public static final double DEFAULT_MEAN_DISTANCE_LATERAL_DRAGGING_M = SimpleMobilseilkran1999.DEFAULT_MITTLERE_DISTANZ_SEITLICHER_ZUZUG_M;
	public static final double DEFAULT_DIFFICULTIES_LATERAL_DRAGGING_CATEGORY = SimpleMobilseilkran1999.DEFAULT_SCHWIERIGKEIT_SEITLICHER_ZUZUG_KATEGORIE;
	public static final double DEFAULT_CABLE_SYSTEM_CATEGORY = SimpleMobilseilkran1999.DEFAULT_SEILSYSTEM_KATEGORIE;
	public static final double DEFAULT_ENGINE_POSITION_CATEGORY = SimpleMobilseilkran1999.DEFAULT_MASCHINENSTANDORT_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_SUPPORTS_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_STUETZEN_ANZAHL;
	public static final double DEFAULT_SKYLINGE_HEIGHT_SUPPORT_M = SimpleMobilseilkran1999.DEFAULT_TRAGSEILHOEHE_STUETZE_M;
	public static final double DEFAULT_TERMINAL_SUPPORT_PRESENT_CATEGORY = SimpleMobilseilkran1999.DEFAULT_ENDMAST_VORHANDEN_KATEGORIE;
	public static final double DEFAULT_SKYLINE_HEIGHT_TERMINAL_SUPPORT = SimpleMobilseilkran1999.DEFAULT_TRAGSEILHOEHE_ENDMAST_M;

	// Work system
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;

//	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_PERSONAL_PRO_H; // we use the default of the motor manual model for this constant
	public static final double DEFAULT_COSTS_TOWER_YARDER_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_MOBILSEILKRAN_PRO_H;
	public static final double DEFAULT_COSTS_VEHICLE_WITH_CRANE_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H;
	public static final double DEFAULT_OPERATING_TIME_VEHICLE_WITH_CRANE_PCT = SimpleMobilseilkran1999.DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ;
	public static final double DEFAULT_NUMBER_OF_WORKERS_INSTALLTION_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LOGGING_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LANDING_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_LAGERPLATZ_ANZAHL;


	private final SimpleMotormanuell2014 motorManual;
	private final SimpleMobilseilkran1999 towerYarder;


	public SimpleMotorManualTowerYarder() {
		this(new SimpleMotormanuell2014(), new SimpleMobilseilkran1999());
	}

	private SimpleMotorManualTowerYarder(SimpleMotormanuell2014 motorManual, SimpleMobilseilkran1999 towerYarder) {
		super(motorManual, towerYarder);
		this.motorManual = motorManual;
		this.towerYarder = towerYarder;
		this.setTimberVolume_m3ob(DEFAULT_TIMBER_VOLUME_M3_OB); // to ensure both models use the same default timber volume
		this.setCostsStaff_perH(DEFAULT_COSTS_STAFF_PER_H); // to ensure both models use the same default staff costs
	}

	
	
	public void setTimberVolume_m3ob(double value) {
		motorManual.setHolzmenge_m3iR(value);
		towerYarder.setHolzmenge_m3iR(value);
	}
	
	@SuppressWarnings("unused") // instead of this method, setTimberVolume_m3ob() should be used for both models 
	private void setExtractedVolume_m3ob(double value) {
		towerYarder.setHolzmenge_m3iR(value);
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
		towerYarder.setMittlererStueckinhalt_m3iR(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setLineLength_m(double value) {
		towerYarder.setLinienlaenge_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanLoggingDistance_m(double value) {
		towerYarder.setMittlereFahrdistanz_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDistanceLateralDragging_m(double value) {
		towerYarder.setMittlereDistanzSeitlicherZuzug_m(value);
	}
	
	/**
	 * 
	 * @param value easy=1, hindered=2
	 */
	public void setDifficultiesLateralDragging_category(double value) {
		towerYarder.setSchwierigkeitSeitlicherZuzug_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value two-cable system=1, multiple-cable system=2
	 */
	public void setCableSystem_category(double value) {
		towerYarder.setSeilsystem_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value down=1, up=2
	 */
	public void setEnginePosition_category(double value) {
		towerYarder.setMaschinenstandort_Kategorie(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSupports_number(double value) {
		towerYarder.setAnzahlStuetzen_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setTerminalSupportPresent_category(double value) {
		towerYarder.setEndmastVorhanden_Kategorie(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSkylineHeightTerminalSupport_m(double value) {
		towerYarder.setTragseilhoeheEndmast_m(value);
	}
	
	
	/**
	 * This method belongs to both models
	 */
	public void setCostsStaff_perH(double value) {
		motorManual.setKostenPersonal_proH(value);
		towerYarder.setKostenPersonal_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		motorManual.setKostenMotorsaege_proH(value);
	}
	
	public void setCostsTowerYarder_perH(double value) {
		towerYarder.setKostenMobilseilkran_proH(value);
	}
	
	public void setCostsVehicleWithCrane_perH(double value) {
		towerYarder.setKostenKranfahrzeug_proH(value);
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setOperatingTimeVehicleWithCrane_pct(double value) {
		towerYarder.setKranfahrzeugLaufzeit_Prz(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersInstallation_number(double value) {
		towerYarder.setAnzahlPersonenInstallation_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersLogging_number(double value) {
		towerYarder.setAnzahlPersonenSeilen_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersLanding_number(double value) {
		towerYarder.setAnzahlPersonenLagerplatz_Anzahl(value);
	}
	
	
	
	/**
	 * This method belongs to both models
	 */
	public double getTimeStaff_WPPH() {
		return motorManual.getZeitPersonal_WPPH() 
				+ towerYarder.getZeitPersonal_WPPH();
	}

	public double getTimeChainsaw_PMH15() {
		return motorManual.getZeitMotorsaege_PMH15();
	}

	public double getTimeTowerYarder_PMH15() {
		return towerYarder.getZeitMobilseilkran_PMH15();
	}

	public double getTimeVehicleWithCrane_PMH15() {
		return towerYarder.getZeitKranfahrzeug_PMH15();
	}
	

	/**
	 * This method belongs to both models
	 */
	public double getCostsStaff_perM3ub() {
		return motorManual.getKostenPersonal_proM3oR()
				+ towerYarder.getKostenPersonal_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return motorManual.getKostenMotorsaege_proM3oR();
	}

	public double getCostsTowerYarder_perM3ub() {
		return towerYarder.getKostenMobilseilkran_proM3oR();
	}

	public double getCostsVehicleWithCrane_perM3ub() {
		return towerYarder.getKostenKranfahrzeug_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return motorManual.getKostenUmsetzen_proM3oR()
				+ towerYarder.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return motorManual.getKostenWeitereAufwaende_proM3oR()
				+ towerYarder.getKostenWeitereAufwaende_proM3oR();
	}

	public double getCostsTotal_perM3ub() {
		return motorManual.getKostenTotal_proM3oR()
				+ towerYarder.getKostenTotal_proM3oR();
	}
	

	/**
	 * This method belongs to both models
	 */
	public double getCostsStaff_total() {
		return motorManual.getKostenPersonal_total() 
				+ towerYarder.getKostenPersonal_total();
	}

	public double getCostsChainsaw_total() {
		return motorManual.getKostenMotorsaege_total();
	}

	public double getCostsTowerYarder_total() {
		return towerYarder.getKostenMobilseilkran_total();
	}

	public double getCostsVehicleWithCrane_total() {
		return towerYarder.getKostenKranfahrzeug_total();
	}

	public double getCostsTransfer_total() {
		return motorManual.getKostenUmsetzen_total()
				+ towerYarder.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return motorManual.getKostenWeitereAufwaende_total()
				+ towerYarder.getKostenWeitereAufwaende_total();
	}
	
	public double getProductivityMotorManual_m3obPerWPSH() {
		return motorManual.getProduktivitaet_m3iRproWPSH();
	}

	public double getProductivityLoggingTowerYarder_m3obPerPMH15() {
		return towerYarder.getProduktivitaetBeimSeilen_m3iRproPMH15();
	}

}
