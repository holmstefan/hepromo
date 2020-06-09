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

import ch.wsl.fps.hepromo.api.SimpleFaellenVorruecken2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleFellingPreskidding2018 extends AbstractSimpleModel<SimpleFaellenVorruecken2018> {

	// Work item
	public static final double DEFAULT_WOOD_VOLUME_M3_OB = SimpleFaellenVorruecken2018.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleFaellenVorruecken2018.DEFAULT_BHD_CM;
	public static final double DEFAULT_FERTILITY_CATEGORY = SimpleFaellenVorruecken2018.DEFAULT_BONITAET_KATEGORIE;
	public static final double DEFAULT_SLOPE_CATEGORY = SimpleFaellenVorruecken2018.DEFAULT_HANGNEIGUNG_KATEGORIE;
	public static final double DEFAULT_CUT_THE_TOP_CATEGORY = SimpleFaellenVorruecken2018.DEFAULT_ABZOPFEN_DER_KRONE_KATEGORIE;

	// Work system
	public static final double DEFAULT_MACHINE_TYPE_CATEGORY = SimpleFaellenVorruecken2018.DEFAULT_MASCHINENTYP_KATEGORIE;
	public static final double DEFAULT_PRODUCTIVITY_INCREASE_SMALL_TRACKED_SKIDDER_FACTOR = SimpleFaellenVorruecken2018.DEFAULT_PRODUKTIVITAETSSTEIGERUNG_RUECKERAUPTE_FAKTOR;
	public static final double DEFAULT_COSTS_STAFF_PER_H = SimpleFaellenVorruecken2018.DEFAULT_KOSTEN_PERSONAL_PRO_H;
	public static final double DEFAULT_COSTS_PRESKIDDING_ENGINE_PER_H = SimpleFaellenVorruecken2018.DEFAULT_KOSTEN_VORRUECKEGERAET_PRO_H;
	public static final double DEFAULT_COSTS_CHAINSAW_PER_H = SimpleFaellenVorruecken2018.DEFAULT_KOSTEN_MOTORSAEGE_PRO_H;
	
	
	public SimpleFellingPreskidding2018() {
		super(new SimpleFaellenVorruecken2018());
	}

	
	public void setWoodVolume_m3ob(double value) {
		adaptee.setHolzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDbh_cm(double value) {
		adaptee.setBhd_cm(value);
	}
	
	/**
	 * 
	 * @param value very low=1, low=2, medium=3, high=4, very high=5
	 */
	public void setFertility_category(double value) {
		adaptee.setBonitaet_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 0-15%=1, 16-25%=2, 26-35%=3, 36-45%=4, 46-55%=5, &gt;55%=6
	 */
	public void setSlope_category(double value) {
		adaptee.setHangneigung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setCutTheTop_category(double value) {
		adaptee.setAbzopfenDerKrone_Kategorie(value);
	}

	/**
	 * 
	 * @param value skidder=1, small tracked skidder=2
	 */
	public void setMachineType_category(double value) {
		adaptee.setMaschinentyp_Kategorie(value);
	}
	
	public void setProductivityIncreaseSmallTrackedSkidder_factor(double value) {
		adaptee.setProduktivtaetssteigerungRueckeraupe_Faktor(value);
	}
	
	public void setCostsStaff_perH(double value) {
		adaptee.setKostenPersonal_proH(value);
	}
	
	public void setCostsPreskiddingEngine_perH(double value) {
		adaptee.setKostenVorrueckegeraet_proH(value);
	}
	
	public void setCostsChainsaw_perH(double value) {
		adaptee.setKostenMotorsaege_proH(value);
	}
	
	
	public double getTimeStaff_WPPH() {
		return adaptee.getZeitPersonal_WPPH();
	}

	public double getTimePreskiddingEngine_PMH15() {
		return adaptee.getZeitVorrueckegeraet_PMH15();
	}

	public double getTimeChainsaw_PMH15() {
		return adaptee.getZeitMotorsaege_PMH15();
	}
	

	public double getCostsStaff_perM3ub() {
		return adaptee.getKostenPersonal_proM3oR();
	}

	public double getCostsPreskiddingEngine_perM3ub() {
		return adaptee.getKostenVorrueckegeraet_proM3oR();
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

	public double getCostsPreskiddingEngine_total() {
		return adaptee.getKostenVorrueckegeraet_total();
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
	

	public double getProductivity_m3obperPMH15() {
		return adaptee.getProduktivitaet_m3iRproPMH15();
	}

}
