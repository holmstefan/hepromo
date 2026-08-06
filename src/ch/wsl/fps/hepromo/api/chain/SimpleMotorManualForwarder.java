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

import ch.wsl.fps.hepromo.api.SimpleForwarderRundholz2018;
import ch.wsl.fps.hepromo.api.SimpleMotormanuell2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMotorManualForwarder extends AbstractSimpleChain {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleMotormanuell2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleMotormanuell2014.DEFAULT_BHD_CM;
	public static final double DEFAULT_PERCENTAGE_OF_PINE_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_FOEHRE_PRZ;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_LAUBHOLZ_PRZ;
	public static final double DEFAULT_REGION_CATEGORY = SimpleMotormanuell2014.DEFAULT_REGION_KATEGORIE;

	@SuppressWarnings("unused")
	private static final double DEFAULT_ROUNDWOOD_VOLUME_M3_OB = SimpleForwarderRundholz2018.DEFAULT_RUNDHOLZMENGE_M3_IR; //this constant and the corresponding setter are not used in this process chain
//	public static final double DEFAULT_MEAN_DBH_CM = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_MITTLERER_BHD_CM; // we use the default dbh of the motor manual model here
	public static final double DEFAULT_SKIDDING_DISTANCE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_RUECKEENTFERNUNG_KATEGORIE;
	public static final double DEFAULT_SLOPE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_HANGNEIGUNG_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_ASSORTMENTS_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE;
	public static final double DEFAULT_DIFFICULTIES_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ERSCHWERNISSE_KATEGORIE;
	public static final double DEFAULT_DISTANCE_BETWEEN_SKID_TRAILS_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ABSTAND_RUECKEGASSEN_KATEGORIE;
	public static final double DEFAULT_USE_OF_WINCH_ASSISTED_HARVESTING_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_EINSATZ_TRAKTIONSHILFSWINDE_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_SKID_TRAILS_WITH_WINCH_ASSISTED_HARVESTING_NUMBER = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ANZAHL_RUECKEGASSEN_MIT_THW_ANZAHL;

	// Work system
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;
	
	public static final double DEFAULT_FORWARDER_TYPE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_FORWARDERTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_MACHINE_OPERATOR_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_FORWARDER_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_FORWARDER_PRO_H;
	public static final double DEFAULT_COSTS_WINCH_ASSISTED_HARVESTING_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_TRAKTIONSHILFSWINDE_PRO_H;


	private final SimpleMotormanuell2014 motorManual;
	private final SimpleForwarderRundholz2018 forwarder;



	public SimpleMotorManualForwarder() {
		this(new SimpleMotormanuell2014(), new SimpleForwarderRundholz2018());
	}

	private SimpleMotorManualForwarder(SimpleMotormanuell2014 motorManual, SimpleForwarderRundholz2018 forwarder) {
		super(motorManual, forwarder);
		this.motorManual = motorManual;
		this.forwarder = forwarder;
		this.setTimberVolume_m3ob(DEFAULT_TIMBER_VOLUME_M3_OB); // to ensure both models use the same default timber volume
		this.setMeanDbh_cm(DEFAULT_MEAN_DBH_CM); // to ensure both models use the same default dbh
	}

	
	
	public void setTimberVolume_m3ob(double value) {
		motorManual.setHolzmenge_m3iR(value);
		forwarder.setRundholzmenge_m3iR(value);
	}
	
	@SuppressWarnings("unused") // instead of this method, setTimberVolume_m3ob() should be used for both models 
	private void setRoundwoodVolume_m3ob(double value) {
		forwarder.setRundholzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDbh_cm(double value) {
		motorManual.setMittlererBhd_cm(value);
		forwarder.setMittlererBhd_cm(value);
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
	
	/**
	 * 
	 * @param value &lt;200m=1, 201-400m=2, 401-600m=3, 601-900m=4, 901-1200m=5, &gt;1200m=6
	 */
	public void setSkiddingDistance_category(double value) {
		forwarder.setRueckeentfernung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value &lt;15%=1, 15-25%=2, 25-35%=3, 35-45%=4, &gt;45%=5
	 */
	public void setSlope_category(double value) {
		forwarder.setHangneigung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 1-3=1, 4-6=2, &ge;7=3
	 */
	public void setNumberOfAssortments_category(double value) {
		forwarder.setAnzahlSortimente_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value none=1, few=2, many=3
	 */
	public void setDifficulties_category(double value) {
		forwarder.setErschwernisse_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 40m=1, 30m=2, 20m=3
	 */
	public void setDistanceBetweenSkidTrails_category(double value) {
		forwarder.setAbstandRueckegassen_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setUseOfWinchAssistedHarvesting_category(double value) {
		forwarder.setEinsatzTraktionshilfswinde_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSkidTrailsWithWinchAssistedHarvesting_number(double value) {
		forwarder.setAnzahlRueckegassenMitThw_Anzahl(value);
	}
	
	
	
	/**
	 * This method belongs to the motor manual model
	 */
	public void setCostsStaff_perH(double value) {
		motorManual.setKostenPersonal_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		motorManual.setKostenMotorsaege_proH(value);
	}
	
	/**
	 * 
	 * @param value medium=1, big=2
	 */
	public void setForwarderType_category(double value) {
		forwarder.setForwardertyp_Kategorie(value);
	}
	
	/**
	 * This method belongs to the forwarder model
	 */
	public void setCostsMachineOperator_perH(double value) {
		forwarder.setKostenMaschinist_proH(value);
	}
	
	public void setCostsForwarder_perH(double value) {
		forwarder.setKostenForwarder_proH(value);
	}
	
	public void setCostsWinchAssistedHarvesting_perH(double value) {
		forwarder.setKostenTraktionshilfswinde_proH(value);
	}
	
	
	
	/**
	 * This method belongs to the motor manual model
	 */
	public double getTimeStaff_WPPH() {
		return motorManual.getZeitPersonal_WPPH();
	}

	public double getTimeChainsaw_PMH15() {
		return motorManual.getZeitMotorsaege_PMH15();
	}
	
	/**
	 * This method belongs to the forwarder model
	 */
	public double getTimeMachineOperator_WPPH() {
		return forwarder.getZeitMaschinist_WPPH();
	}

	public double getTimeForwarder_PMH15() {
		return forwarder.getZeitForwarder_PMH15();
	}

	public double getTimeWinchAssistedHarvesting_ISH() {
		return forwarder.getZeitTraktionshilfswinde_ISH();
	}
	

	/**
	 * This method belongs to the motor manual model
	 */
	public double getCostsStaff_perM3ub() {
		return motorManual.getKostenPersonal_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return motorManual.getKostenMotorsaege_proM3oR();
	}

	/**
	 * This method belongs to the forwarder model
	 */
	public double getCostsMachineOperator_perM3ub() {
		return forwarder.getKostenMaschinist_proM3oR();
	}

	public double getCostsForwarder_perM3ub() {
		return forwarder.getKostenForwarder_proM3oR();
	}

	public double getCostsWinchAssistedHarvesting_perM3ub() {
		return forwarder.getKostenTraktionshilfswinde_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return motorManual.getKostenUmsetzen_proM3oR()
				+ forwarder.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return motorManual.getKostenWeitereAufwaende_proM3oR()
				+ forwarder.getKostenWeitereAufwaende_proM3oR();
	}
	
	public double getCostsTotal_perM3ub() {
		return motorManual.getKostenTotal_proM3oR()
				+ forwarder.getKostenTotal_proM3oR();
	}
	

	/**
	 * This method belongs to the motor manual model
	 */
	public double getCostsStaff_total() {
		return motorManual.getKostenPersonal_total();
	}

	public double getCostsChainsaw_total() {
		return motorManual.getKostenMotorsaege_total();
	}

	/**
	 * This method belongs to the forwarder model
	 */
	public double getCostsMachineOperator_total() {
		return forwarder.getKostenMaschinist_total();
	}

	public double getCostsForwarder_total() {
		return forwarder.getKostenForwarder_total();
	}

	public double getCostsWinchAssistedHarvesting_total() {
		return forwarder.getKostenTraktionshilfswinde_total();
	}

	public double getCostsTransfer_total() {
		return motorManual.getKostenUmsetzen_total()
				+ forwarder.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return motorManual.getKostenWeitereAufwaende_total()
				+ forwarder.getKostenWeitereAufwaende_total();
	}
	
	public double getProductivityMotorManual_m3obPerWPSH() {
		return motorManual.getProduktivitaet_m3iRproWPSH();
	}
	
	
	public double getProductivityForwarder_m3obPerPMH15() {
		return forwarder.getProduktivitaet_m3iRproPMH15();
	}

}
