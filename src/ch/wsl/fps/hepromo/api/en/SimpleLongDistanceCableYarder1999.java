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

import ch.wsl.fps.hepromo.api.SimpleKonventionellerSeilkran1999;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleLongDistanceCableYarder1999 extends AbstractSimpleModel<SimpleKonventionellerSeilkran1999> {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleKonventionellerSeilkran1999.DEFAULT_HOLZMENGE_M3_IR;
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
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleKonventionellerSeilkran1999.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_CABLE_YARDER_PER_H = SimpleKonventionellerSeilkran1999.DEFAULT_KOSTEN_SEILKRANANLAGE_PRO_H;
	public static final double DEFAULT_COSTS_VEHICLE_WITH_CRANE_PER_H = SimpleKonventionellerSeilkran1999.DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H;
	public static final double DEFAULT_OPERATING_TIME_VEHICLE_WITH_CRANE_PCT = SimpleKonventionellerSeilkran1999.DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ;
	public static final double DEFAULT_NUMBER_OF_WORKERS_INSTALLATION_NUMBER = SimpleKonventionellerSeilkran1999.DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LOGGING_NUMBER = SimpleKonventionellerSeilkran1999.DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL;
	

	public SimpleLongDistanceCableYarder1999() {
		super(new SimpleKonventionellerSeilkran1999());
	}
	

	public void setTimberVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	public void setMeanLogVolume_m3ob(double value) {
		adaptee.setMittlererStueckinhalt_m3iR(value);
	}
	
	public void setStemLength_m(double value) {
		adaptee.setStuecklaenge_m(value);
	}
	
	/**
	 * 
	 * @param value thinning=1, opening up=2, final cutting=3
	 */
	public void setTypeOfTreatment_category(double value) {
		adaptee.setEingriffsart_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value of the cutting area=1, off-the-pile=2
	 */
	public void setLoggingArea_category(double value) {
		adaptee.setHolzseilort_Kategorie(value);
	}

	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setSlope_pct(double value) {
		adaptee.setHangneigung_Prz(value);
	}
	
	/**
	 * 
	 * @param value normal=1, hindered=2, extreme=3
	 */
	public void setObstacles_category(double value) {
		adaptee.setHindernisse_Kategorie(value);
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
	 * @param value downhill=1, uphill=2
	 */
	public void setLoggingDirection_category(double value) {
		adaptee.setFahrtrichtung_Kategorie(value);
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
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSupports_number(double value) {
		adaptee.setAnzahlStuetzen_Anzahl(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfTerminalSupports_number(double value) {
		adaptee.setAnzahlEndmasten_Anzahl(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSkylineHeightStand_m(double value) {
		adaptee.setTragseilhoeheBestand_m(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setSkylineHeightLanding_m(double value) {
		adaptee.setTragseilhoeheLagerplatz_m(value);
	}
	
	public void setCostsStaff_perH(double value) {
		adaptee.setKostenPersonal_proH(value);
	}
	
	public void setCostsCableYarder_perH(double value) {
		adaptee.setKostenSeilkrananlage_proH(value);
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

	public double getTimeCableYarder_PMH15() {
		return adaptee.getZeitSeilkrananlage_PMH15();
	}

	public double getTimeVehicleWithCrane_PMH15() {
		return adaptee.getZeitKranfahrzeug_PMH15();
	}
	

	public double getCostsStaff_perM3ub() {
		return adaptee.getKostenPersonal_proM3oR();
	}

	public double getCostsCableYarder_perM3ub() {
		return adaptee.getKostenSeilkrananlage_proM3oR();
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

	public double getCostsCableYarder_total() {
		return adaptee.getKostenSeilkrananlage_total();
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
