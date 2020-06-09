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

import ch.wsl.fps.hepromo.api.SimpleMotormanuell2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleMotorManualFellingAndProcessing2014 extends AbstractSimpleModel<SimpleMotormanuell2014> {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleMotormanuell2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleMotormanuell2014.DEFAULT_BHD_CM;
	public static final double DEFAULT_PERCENTAGE_OF_PINE_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_FOEHRE_PRZ;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_PCT = SimpleMotormanuell2014.DEFAULT_ANTEIL_LAUBHOLZ_PRZ;
	public static final double DEFAULT_REGION_CATEGORY = SimpleMotormanuell2014.DEFAULT_REGION_KATEGORIE;

	// Work system
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleMotormanuell2014.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;
	

	public SimpleMotorManualFellingAndProcessing2014() {
		super(new SimpleMotormanuell2014());
	}

	
	
	public void setTimberVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDbh_cm(double value) {
		adaptee.setMittlererBhd_cm(value);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setPercentageOfPine_pct(double value) {
		adaptee.setAnteilFoehre_Prz(value);
	}
	
	/**
	 * 
	 * @param value 0=0%, 100=100%. Value is rounded to the closest <code>int</code>.
	 */
	public void setPercentageOfBroadleaves_pct(double value) {
		adaptee.setAnteilLaubholz_Prz(value);
	}
	
	/**
	 * 
	 * @param value mountainous=1, flat or hilly=2
	 */
	public void setRegion_category(double value) {
		adaptee.setRegion_Kategorie(value);
	}
	
	public void setCostsStaff_perH(double value) {
		adaptee.setKostenPersonal_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		adaptee.setKostenMotorsaege_proH(value);
	}
	
	
	public double getTimeStaff_WPPH() {
		return adaptee.getZeitPersonal_WPPH();
	}

	public double getTimeChainsaw_PMH15() {
		return adaptee.getZeitMotorsaege_PMH15();
	}
	

	public double getCostsStaff_perM3ub() {
		return adaptee.getKostenPersonal_proM3oR();
	}

	public double getCostsChainsaw_perM3ub() {
		return adaptee.getKostenMotorsaege_proM3oR();
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

	public double getCostsChainsaw_total() {
		return adaptee.getKostenMotorsaege_total();
	}

	public double getCostsTransfer_total() {
		return adaptee.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return adaptee.getKostenWeitereAufwaende_total();
	}
	

	public double getProductivity_m3obPerWPSH() {
		return adaptee.getProduktivitaet_m3iRproWPSH();
	}
}
