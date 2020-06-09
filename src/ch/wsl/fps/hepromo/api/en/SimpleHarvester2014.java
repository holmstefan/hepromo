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

import ch.wsl.fps.hepromo.api.SimpleRadharvester2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHarvester2014 extends AbstractSimpleModel<SimpleRadharvester2014> {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleRadharvester2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleRadharvester2014.DEFAULT_MITTLERER_BHD_CM;
	public static final double DEFAULT_STEM_FORM_CATEGORY = SimpleRadharvester2014.DEFAULT_FOERMIGKEIT_KATEGORIE;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_CATEGORY = SimpleRadharvester2014.DEFAULT_ANTEIL_LAUBHOLZ_KATEGORIE;
	public static final double DEFAULT_PREFELLED_TREES_CATEGORY = SimpleRadharvester2014.DEFAULT_LIEGENDES_HOLZ_KATEGORIE;

	// Work system
	public static final double DEFAULT_HARVESTER_TYPE_CATEGORY = SimpleRadharvester2014.DEFAULT_MASCHINENTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_ENGINE_DRIVER_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_HARVESTER_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_RADHARVESTER_PRO_H;
	

	public SimpleHarvester2014() {
		super(new SimpleRadharvester2014());
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
	 * @param value slightly tapering=1, normally tapering=2, tapering=3
	 */
	public void setStemForm_category(double value) {
		adaptee.setFoermigkeit_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 0%=1, up to 25%=2, up to 50%=3, up to 75%=4, up to 100%=5
	 */
	public void setPercentageOfBroadleaves_category(double value) {
		adaptee.setAnteilLaubholz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value none%=1, pre-delivered trees up to 25%=2, predelivered trees up to 50%=3, trees felled towards the skid trail up to 25%=4, trees felled towards the skid trails up to 50%=5
	 */
	public void setPrefelledTrees_category(double value) {
		adaptee.setLiegendesHolz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value medium=1, big=2
	 */
	public void setHarvesterType_category(double value) {
		adaptee.setMaschinentyp_Kategorie(value);
	}
	
	public void setCostsEngineDriver_perH(double value) {
		adaptee.setKostenMaschinist_proH(value);
	}
	
	public void setCostsHarvester_perH(double value) {
		adaptee.setKostenRadharvester_proH(value);
	}
	
	
	public double getTimeEngineDriver_WPPH() {
		return adaptee.getZeitMaschinist_WPPH();
	}

	public double getTimeHarvester_PMH15() {
		return adaptee.getZeitRadharvester_PMH15();
	}
	

	public double getCostsEngineDriver_perM3ub() {
		return adaptee.getKostenMaschinist_proM3oR();
	}

	public double getCostsHarvester_perM3ub() {
		return adaptee.getKostenRadharvester_proM3oR();
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

	public double getCostsHarvester_total() {
		return adaptee.getKostenRadharvester_total();
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
