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

import ch.wsl.fps.hepromo.api.SimpleMotormanuell2014;
import ch.wsl.fps.hepromo.api.SimpleSchlepper2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMotorManualSkidder extends AbstractSimpleChain {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleMotormanuell2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleMotormanuell2014.DEFAULT_BHD_CM;
	public static final double DEFAULT_PERCENTAGE_OF_PINE_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_FOEHRE_PRZ;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_LAUBHOLZ_PRZ;
	public static final double DEFAULT_REGION_CATEGORY = SimpleMotormanuell2014.DEFAULT_REGION_KATEGORIE;

//	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleSchlepper2014.DEFAULT_HOLZMENGE_M3_IR; // we use the default of the motor manual model for this constant
	public static final double DEFAULT_MEAN_LOG_VOLUME_M3_OB_PER_PC = SimpleSchlepper2014.DEFAULT_MITTLERER_STUECKINHALT_M3_IR_PRO_STK;
	public static final double DEFAULT_MEAN_SKIDDING_DISTANCE_M = SimpleSchlepper2014.DEFAULT_MITTLERE_FAHRENTFERNUNG_M;
	public static final double DEFAULT_LATERAL_DRAGGING_DISTANCE_CATEGORY = SimpleSchlepper2014.DEFAULT_BEIZUGSDISTANZ_KATEGORIE;

	// Work system
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;

	public static final double DEFAULT_SKIDDER_TYPE_CATEGORY = SimpleSchlepper2014.DEFAULT_SCHLEPPERTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_ENGINE_DRIVER_PER_H = SimpleSchlepper2014.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_SKIDDER_PER_H = SimpleSchlepper2014.DEFAULT_KOSTEN_SCHLEPPER_PRO_H;
	
	private final SimpleMotormanuell2014 motorManual;
	private final SimpleSchlepper2014 skidder;



	public SimpleMotorManualSkidder() {
		this(new SimpleMotormanuell2014(), new SimpleSchlepper2014());
	}

	private SimpleMotorManualSkidder(SimpleMotormanuell2014 motorManual, SimpleSchlepper2014 skidder) {
		super(motorManual, skidder);
		this.motorManual = motorManual;
		this.skidder = skidder;
		this.setTimberVolume_m3ob(DEFAULT_TIMBER_VOLUME_M3_OB); // to ensure both models use the same default timber volume
	}



	public void setTimberVolume_m3ob(double value) {
		motorManual.setHolzmenge_m3iR(value);
		skidder.setHolzmenge_m3iR(value);
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
	
	public void setMeanLogVolume_m3obPerPc(double value) {
		skidder.setMittlererStueckinhalt_m3iRProStk(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanSkiddingDistance_m(double value) {
		skidder.setMittlereFahrentfernung_m(value);
	}
	
	/**
	 * 
	 * @param value &lt;20m=1, 20-40m=2, &gt;40m=3
	 */
	public void setLateralDraggingDistance_category(double value) {
		skidder.setBeizugsdistanz_Kategorie(value);
	}
	
	
	public void setCostsStaff_perH(double value) {
		motorManual.setKostenPersonal_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		motorManual.setKostenMotorsaege_proH(value);
	}
	
	/**
	 * 
	 * @param value skidder with crane=1, skidder with winch=2, skidder with wood clamp=3
	 */
	public void setSkidderType_category(double value) {
		skidder.setSchleppertyp_Kategorie(value);
	}
	
	public void setCostsEngineDriver_perH(double value) {
		skidder.setKostenMaschinist_proH(value);
	}
	
	public void setCostsSkidder_perH(double value) {
		skidder.setKostenSchlepper_proH(value);
	}
	
	
	
	public double getTimeStaff_WPPH() {
		return motorManual.getZeitPersonal_WPPH();
	}

	public double getTimeChainsaw_PMH15() {
		return motorManual.getZeitMotorsaege_PMH15();
	}	

	public double getTimeEngineDriver_WPPH() {
		return skidder.getZeitMaschinist_WPPH();
	}

	public double getTimeSkidder_PMH15() {
		return skidder.getZeitSchlepper_PMH15();
	}
	

	public double getCostsStaff_perM3ub() {
		return motorManual.getKostenPersonal_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return motorManual.getKostenMotorsaege_proM3oR();
	}	

	public double getCostsEngineDriver_perM3ub() {
		return skidder.getKostenMaschinist_proM3oR();
	}

	public double getCostsSkidder_perM3ub() {
		return skidder.getKostenSchlepper_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return motorManual.getKostenUmsetzen_proM3oR() 
				+ skidder.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return motorManual.getKostenWeitereAufwaende_proM3oR()
				+ skidder.getKostenWeitereAufwaende_proM3oR();
	}

	public double getCostsTotal_perM3ub() {
		return motorManual.getKostenTotal_proM3oR()
				+ skidder.getKostenTotal_proM3oR();
	}
	

	public double getCostsStaff_total() {
		return motorManual.getKostenPersonal_total();
	}

	public double getCostsChainsaw_total() {
		return motorManual.getKostenMotorsaege_total();
	}	

	public double getCostsEngineDriver_total() {
		return skidder.getKostenMaschinist_total();
	}

	public double getCostsSkidder_total() {
		return skidder.getKostenSchlepper_total();
	}

	public double getCostsTransfer_total() {
		return motorManual.getKostenUmsetzen_total() + skidder.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return motorManual.getKostenWeitereAufwaende_total() + skidder.getKostenWeitereAufwaende_total();
	}
	
	public double getProductivityMotorManual_m3obPerWPSH() {
		return motorManual.getProduktivitaet_m3iRproWPSH();
	}

	public double getProductivitySkidder_m3obPerPMH15() {
		return skidder.getProduktivitaet_m3iRproPMH15();
	}

}
