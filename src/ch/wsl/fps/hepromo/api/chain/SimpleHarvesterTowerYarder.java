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
import ch.wsl.fps.hepromo.api.SimpleRadharvester2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHarvesterTowerYarder extends AbstractSimpleChain {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleRadharvester2014.DEFAULT_HOLZMENGE_M3_IR; //this timber volume is used for both models in this process chain
	public static final double DEFAULT_MEAN_DBH_CM = SimpleRadharvester2014.DEFAULT_MITTLERER_BHD_CM;
	public static final double DEFAULT_STEM_FORM_CATEGORY = SimpleRadharvester2014.DEFAULT_FOERMIGKEIT_KATEGORIE;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_CATEGORY = SimpleRadharvester2014.DEFAULT_ANTEIL_LAUBHOLZ_KATEGORIE;
	public static final double DEFAULT_PREFELLED_TREES_CATEGORY = SimpleRadharvester2014.DEFAULT_LIEGENDES_HOLZ_KATEGORIE;

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
	public static final double DEFAULT_MEAN_SLOPE_CATEGORY = SimpleRadharvester2014.DEFAULT_MITTLERE_HANGNEIGUNG_KATEGORIE;
	public static final double DEFAULT_USE_OF_WINCH_ASSISTED_HARVESTING_CATEGORY = SimpleRadharvester2014.DEFAULT_EINSATZ_TRAKTIONSHILFSWINDE_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_SKID_TRAILS_WITH_WINCH_ASSISTED_HARVESTING_NUMBER = SimpleRadharvester2014.DEFAULT_ANZAHL_RUECKEGASSEN_MIT_THW_ANZAHL;
	public static final double DEFAULT_HARVESTER_TYPE_CATEGORY = SimpleRadharvester2014.DEFAULT_MASCHINENTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_ENGINE_DRIVER_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_HARVESTER_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_RADHARVESTER_PRO_H;
	public static final double DEFAULT_COSTS_WINCH_ASSISTED_HARVESTING_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_TRAKTIONSHILFSWINDE_PRO_H;

	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_TOWER_YARDER_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_MOBILSEILKRAN_PRO_H;
	public static final double DEFAULT_COSTS_VEHICLE_WITH_CRANE_PER_H = SimpleMobilseilkran1999.DEFAULT_KOSTEN_KRANFAHRZEUG_PRO_H;
	public static final double DEFAULT_OPERATING_TIME_VEHICLE_WITH_CRANE_PCT = SimpleMobilseilkran1999.DEFAULT_KRANFAHRZEUG_LAUFZEIT_PRZ;
	public static final double DEFAULT_NUMBER_OF_WORKERS_INSTALLTION_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_INSTALLATION_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LOGGING_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_SEILEN_ANZAHL;
	public static final double DEFAULT_NUMBER_OF_WORKERS_LANDING_NUMBER = SimpleMobilseilkran1999.DEFAULT_ANZAHL_PERSONEN_LAGERPLATZ_ANZAHL;

	private final SimpleRadharvester2014 harvester;
	private final SimpleMobilseilkran1999 towerYarder;



	public SimpleHarvesterTowerYarder() {
		this(new SimpleRadharvester2014(), new SimpleMobilseilkran1999());
	}

	private SimpleHarvesterTowerYarder(SimpleRadharvester2014 harvester, SimpleMobilseilkran1999 towerYarder) {
		super(harvester, towerYarder);
		this.harvester = harvester;
		this.towerYarder = towerYarder;
		this.setTimberVolume_m3ob(DEFAULT_TIMBER_VOLUME_M3_OB); // to ensure both models use the same default timber volume
	}

	
	
	public void setTimberVolume_m3ob(double value) {
		harvester.setHolzmenge_m3iR(value);
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
		harvester.setMittlererBhd_cm(value);
	}
	
	/**
	 * 
	 * @param value slightly tapering=1, normally tapering=2, tapering=3
	 */
	public void setStemForm_category(double value) {
		harvester.setFoermigkeit_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 0%=1, up to 25%=2, up to 50%=3, up to 75%=4, up to 100%=5
	 */
	public void setPercentageOfBroadleaves_category(double value) {
		harvester.setAnteilLaubholz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value none%=1, pre-delivered trees up to 25%=2, predelivered trees up to 50%=3, trees felled towards the skid trail up to 25%=4, trees felled towards the skid trails up to 50%=5
	 */
	public void setPrefelledTrees_category(double value) {
		harvester.setLiegendesHolz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value up to 30%=1, 30-39%=2, 40-49%=3, 50-60%=4
	 */
	public void setMeanSlope_category(double value) {
		harvester.setMittlereHangneigung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setUseOfWinchAssistedHarvesting_category(double value) {
		harvester.setEinsatzTraktionshilfswinde_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSkidTrailsWithWinchAssistedHarvesting_number(double value) {
		harvester.setAnzahlRueckegassenMitThw_Anzahl(value);
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
	 * 
	 * @param value medium=1, big=2
	 */
	public void setHarvesterType_category(double value) {
		harvester.setMaschinentyp_Kategorie(value);
	}
	
	public void setCostsEngineDriver_perH(double value) {
		harvester.setKostenMaschinist_proH(value);
	}
	
	public void setCostsHarvester_perH(double value) {
		harvester.setKostenRadharvester_proH(value);
	}
	
	public void setCostsWinchAssistedHarvesting_perH(double value) {
		harvester.setKostenTraktionshilfswinde_proH(value);
	}

	/**
	 * This method belongs to the tower yarder model
	 */
	public void setCostsStaff_perH(double value) {
		towerYarder.setKostenPersonal_proH(value);
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
	

	
	
	
	public double getTimeEngineDriver_WPPH() {
		return harvester.getZeitMaschinist_WPPH();
	}

	public double getTimeHarvester_PMH15() {
		return harvester.getZeitRadharvester_PMH15();
	}

	public double getTimeWinchAssistedHarvesting_ISH() {
		return harvester.getZeitTraktionshilfswinde_ISH();
	}
	

	/**
	 * This method belongs to the tower yarder model
	 */
	public double getTimeStaff_WPPH() {
		return towerYarder.getZeitPersonal_WPPH();
	}

	public double getTimeTowerYarder_PMH15() {
		return towerYarder.getZeitMobilseilkran_PMH15();
	}

	public double getTimeVehicleWithCrane_PMH15() {
		return towerYarder.getZeitKranfahrzeug_PMH15();
	}
	

	public double getCostsEngineDriver_perM3ub() {
		return harvester.getKostenMaschinist_proM3oR();
	}

	public double getCostsHarvester_perM3ub() {
		return harvester.getKostenRadharvester_proM3oR();
	}

	public double getCostsWinchAssistedHarvesting_perM3ub() {
		return harvester.getKostenTraktionshilfswinde_proM3oR();
	}

	/**
	 * This method belongs to the tower yarder model
	 */
	public double getCostsStaff_perM3ub() {
		return towerYarder.getKostenPersonal_proM3oR();
	}

	public double getCostsTowerYarder_perM3ub() {
		return towerYarder.getKostenMobilseilkran_proM3oR();
	}

	public double getCostsVehicleWithCrane_perM3ub() {
		return towerYarder.getKostenKranfahrzeug_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return harvester.getKostenUmsetzen_proM3oR()
				+ towerYarder.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return harvester.getKostenWeitereAufwaende_proM3oR()
				+ towerYarder.getKostenWeitereAufwaende_proM3oR();
	}

	public double getCostsTotal_perM3ub() {
		return harvester.getKostenTotal_proM3oR()
				+ towerYarder.getKostenTotal_proM3oR();
	}
	

	public double getCostsEngineDriver_total() {
		return harvester.getKostenMaschinist_total();
	}

	public double getCostsHarvester_total() {
		return harvester.getKostenRadharvester_total();
	}

	public double getCostsWinchAssistedHarvesting_total() {
		return harvester.getKostenTraktionshilfswinde_total();
	}
	

	/**
	 * This method belongs to the tower yarder model
	 */
	public double getCostsStaff_total() {
		return towerYarder.getKostenPersonal_total();
	}

	public double getCostsTowerYarder_total() {
		return towerYarder.getKostenMobilseilkran_total();
	}

	public double getCostsVehicleWithCrane_total() {
		return towerYarder.getKostenKranfahrzeug_total();
	}

	public double getCostsTransfer_total() {
		return harvester.getKostenUmsetzen_total()
				+ towerYarder.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return harvester.getKostenWeitereAufwaende_total()
				+ towerYarder.getKostenWeitereAufwaende_total();
	}
	
	public double getProductivityHarvester_m3obPerPMH15() {
		return harvester.getProduktivitaet_m3iRproPMH15();
	}

	public double getProductivityTowerYarderLogging_m3obPerPMH15() {
		return towerYarder.getProduktivitaetBeimSeilen_m3iRproPMH15();
	}

}
