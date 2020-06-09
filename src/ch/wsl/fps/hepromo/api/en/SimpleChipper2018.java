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

import ch.wsl.fps.hepromo.api.SimpleHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleChipper2018 extends AbstractSimpleModel<SimpleHacker2018> {

	// Work item
	public static final double DEFAULT_VOLUME_OF_WOODCHIPS_BCM = SimpleHacker2018.DEFAULT_HACKSCHNITZELMENGE_SRM;
	public static final double DEFAULT_KIND_OF_WOOD_ON_PILE_CATEGORY = SimpleHacker2018.DEFAULT_POLTERSORTIMENT_KATEGORIE;

	// Work system
	public static final double DEFAULT_ENGINE_POWER_CHIPPER_KW = SimpleHacker2018.DEFAULT_MOTORLEISTUNG_HACKER_KW;
	public static final double DEFAULT_COSTS_OPERATOR_PER_H = SimpleHacker2018.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_CHIPPER_PER_H = SimpleHacker2018.DEFAULT_KOSTEN_HACKER_PRO_H;
	

	public SimpleChipper2018() {
		super(new SimpleHacker2018());
	}
	
	
	public void setVolumeOfWoodchips_bcm(double value) {
		adaptee.setHackschnitzelmenge_Srm(value);
	}
	
	/**
	 * 
	 * @param value forest residues=1, energy round wood=2
	 */
	public void setKindOfWoodOnPile_category(double value) {
		adaptee.setPoltersortiment_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setEnginePowerChipper_kW(double value) {
		adaptee.setMotorleistungHacker_kW(value);
	}
	
	public void setCostsOperator_perH(double value) {
		adaptee.setKostenMaschinist_proH(value);
	}
	
	public void setCostsChipper_perH(double value) {
		adaptee.setKostenHacker_proH(value);
	}
	
	
	public double getTimeOperator_WPPH() {
		return adaptee.getZeitMaschinist_WPPH();
	}

	public double getTimeChipper_PMH15() {
		return adaptee.getZeitHacker_PMH15();
	}
	

	public double getCostsOperator_perBcm() {
		return adaptee.getKostenMaschinist_proSrm();
	}

	public double getCostsChipper_perBcm() {
		return adaptee.getKostenHacker_proSrm();
	}

	public double getCostsTransfer_perBcm() {
		return adaptee.getKostenUmsetzen_proSrm();
	}

	public double getCostsFurtherWork_perBcm() {
		return adaptee.getKostenWeitereAufwaende_proSrm();
	}

	public double getCostsTotal_perBcm() {
		return adaptee.getKostenTotal_proSrm();
	}
	

	public double getCostsOperator_total() {
		return adaptee.getKostenMaschinist_total();
	}

	public double getCostsChipper_total() {
		return adaptee.getKostenHacker_total();
	}

	public double getCostsTransfer_total() {
		return adaptee.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return adaptee.getKostenWeitereAufwaende_total();
	}


	public double getProductivity_bcmPerPMH15() {
		return adaptee.getProduktivitaet_SrmProPMH15();
	}
	
}
