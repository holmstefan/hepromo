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

import ch.wsl.fps.hepromo.api.SimpleSchlepper2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleSkidder2014 extends AbstractSimpleModel<SimpleSchlepper2014> {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleSchlepper2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_LOG_VOLUME_M3_OB_PER_PC = SimpleSchlepper2014.DEFAULT_MITTLERER_STUECKINHALT_M3_IR_PRO_STK;
	public static final double DEFAULT_MEAN_SKIDDING_DISTANCE_M = SimpleSchlepper2014.DEFAULT_MITTLERE_FAHRENTFERNUNG_M;
	public static final double DEFAULT_LATERAL_DRAGGING_DISTANCE_CATEGORY = SimpleSchlepper2014.DEFAULT_BEIZUGSDISTANZ_KATEGORIE;

	// Work system
	public static final double DEFAULT_SKIDDER_TYPE_CATEGORY = SimpleSchlepper2014.DEFAULT_SCHLEPPERTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_ENGINE_DRIVER_PER_H = SimpleSchlepper2014.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_SKIDDER_PER_H = SimpleSchlepper2014.DEFAULT_KOSTEN_SCHLEPPER_PRO_H;
	

	public SimpleSkidder2014() {
		super(new SimpleSchlepper2014());
	}
	
	
	public void setTimberVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	public void setMeanLogVolume_m3obPerPc(double value) {
		adaptee.setMittlererStueckinhalt_m3iRProStk(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanSkiddingDistance_m(double value) {
		adaptee.setMittlereFahrentfernung_m(value);
	}
	
	/**
	 * 
	 * @param value &lt;20m=1, 20-40m=2, &gt;40m=3
	 */
	public void setLateralDraggingDistance_category(double value) {
		adaptee.setBeizugsdistanz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value skidder with crane=1, skidder with winch=2, skidder with wood clamp=3
	 */
	public void setSkidderType_category(double value) {
		adaptee.setSchleppertyp_Kategorie(value);
	}
	
	public void setCostsEngineDriver_perH(double value) {
		adaptee.setKostenMaschinist_proH(value);
	}
	
	public void setCostsSkidder_perH(double value) {
		adaptee.setKostenSchlepper_proH(value);
	}
	

	public double getTimeEngineDriver_WPPH() {
		return adaptee.getZeitMaschinist_WPPH();
	}

	public double getTimeSkidder_PMH15() {
		return adaptee.getZeitSchlepper_PMH15();
	}
	

	public double getCostsEngineDriver_perM3ub() {
		return adaptee.getKostenMaschinist_proM3oR();
	}

	public double getCostsSkidder_perM3ub() {
		return adaptee.getKostenSchlepper_proM3oR();
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
	

	public double getCostsEngineDriver_total() {
		return adaptee.getKostenMaschinist_total();
	}

	public double getCostsSkidder_total() {
		return adaptee.getKostenSchlepper_total();
	}

	public double getCostsTransfer_total() {
		return adaptee.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return adaptee.getKostenWeitereAufwaende_total();
	}
	

	public double getProductivity_m3obPerPMH15() {
		return adaptee.getProduktivitaet_m3iRproPMH15();
	}

}
