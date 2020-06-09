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

import ch.wsl.fps.hepromo.api.SimpleMobilseilkran1999;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleTowerYarder1999 extends AbstractSimpleModel<SimpleMobilseilkran1999> {

	// Work item
	public static final double DEFAULT_EXTRACTED_VOLUME_M3_OB = SimpleMobilseilkran1999.DEFAULT_HOLZMENGE_M3_IR;
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
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_TOWER_YARDER_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_MOBILSEILKRAN_PRO_H;
	public static final double DEFAULT_COSTS_VEHICLE_WITH_CRANE_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H;
	public static final double DEFAULT_OPERATING_TIME_VEHICLE_WITH_CRANE_PCT = SimpleMobilseilkran1999.DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ;
	public static final double DEFAULT_NUMBER_OF_WORKERS_INSTALLTION_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LOGGING_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL;

	public SimpleTowerYarder1999() {
		super(new SimpleMobilseilkran1999());
	}
	
	
	public void setExtractedVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	public void setMeanLogVolume_m3ob(double value) {
		adaptee.setMittlererStueckinhalt_m3iR(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setLineLength_m(double value) {
		adaptee.setLinienlaenge_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanLoggingDistance_m(double value) {
		adaptee.setMittlereFahrdistanz_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDistanceLateralDragging_m(double value) {
		adaptee.setMittlereDistanzSeitlicherZuzug_m(value);
	}
	
	/**
	 * 
	 * @param value easy=1, hindered=2
	 */
	public void setDifficultiesLateralDragging_category(double value) {
		adaptee.setSchwierigkeitSeitlicherZuzug_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value two-cable system=1, multiple-cable system=2
	 */
	public void setCableSystem_category(double value) {
		adaptee.setSeilsystem_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value down=1, up=2
	 */
	public void setEnginePosition_category(double value) {
		adaptee.setMaschinenstandort_Kategorie(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSupports_number(double value) {
		adaptee.setAnzahlStuetzen_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setTerminalSupportPresent_category(double value) {
		adaptee.setEndmastVorhanden_Kategorie(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSkylineHeightTerminalSupport_m(double value) {
		adaptee.setTragseilhoeheEndmast_m(value);
	}
	
	public void setCostsStaff_perH(double value) {
		adaptee.setKostenPersonal_proH(value);
	}
	
	public void setCostsTowerYarder_perH(double value) {
		adaptee.setKostenMobilseilkran_proH(value);
	}
	
	public void setCostsVehicleWithCrane_perH(double value) {
		adaptee.setKostenKranfahrzeug_proH(value);
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setOperatingTimeVehicleWithCrane_pct(double value) {
		adaptee.setKranfahrzeugLaufzeit_Prz(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersInstallation_number(double value) {
		adaptee.setAnzahlPersonenInstallation_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfWorkersLogging_number(double value) {
		adaptee.setAnzahlPersonenSeilen_Anzahl(value);
	}
	

	public double getTimeStaff_WPPH() {
		return adaptee.getZeitPersonal_WPPH();
	}

	public double getTimeTowerYarder_PMH15() {
		return adaptee.getZeitMobilseilkran_PMH15();
	}

	public double getTimeVehicleWithCrane_PMH15() {
		return adaptee.getZeitKranfahrzeug_PMH15();
	}
	

	public double getCostsStaff_perM3ub() {
		return adaptee.getKostenPersonal_proM3oR();
	}

	public double getCostsTowerYarder_perM3ub() {
		return adaptee.getKostenMobilseilkran_proM3oR();
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
	

	public double getCostsStaff_total() {
		return adaptee.getKostenPersonal_total();
	}

	public double getCostsTowerYarder_total() {
		return adaptee.getKostenMobilseilkran_total();
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
	

	public double getProductivityLogging_m3obPerPMH15() {
		return adaptee.getProduktivitaetBeimSeilen_m3iRproPMH15();
	}

}
