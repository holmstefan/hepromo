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

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractSimpleForwarder2018<T extends ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018> extends AbstractSimpleModel<T> {

	// Work item
	public static final double DEFAULT_MEAN_DBH_CM = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_MITTLERER_BHD_CM;
	public static final double DEFAULT_SKIDDING_DISTANCE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_RUECKEENTFERNUNG_KATEGORIE;
	public static final double DEFAULT_SLOPE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_HANGNEIGUNG_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_ASSORTMENTS_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE;
	public static final double DEFAULT_DIFFICULTIES_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ERSCHWERNISSE_KATEGORIE;
	public static final double DEFAULT_DISTANCE_BETWEEN_SKID_TRAILS_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ABSTAND_RUECKEGASSEN_KATEGORIE;

	// Work system
	public static final double DEFAULT_FORWARDER_TYPE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_FORWARDERTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_MACHINE_OPERATOR_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_FORWARDER_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_FORWARDER_PRO_H;
	

	public AbstractSimpleForwarder2018(T adaptee) {
		super(adaptee);
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
	 * @param value &lt;200m=1, 201-400m=2, 401-600m=3, 601-900m=4, 901-1200m=5, &gt;1200m=6
	 */
	public void setSkiddingDistance_category(double value) {
		adaptee.setRueckeentfernung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value &lt;15%=1, 15-25%=2, &gt;25%=3
	 */
	public void setSlope_category(double value) {
		adaptee.setHangneigung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 1-3=1, 4-6=2, &ge;7=3
	 */
	public void setNumberOfAssortments_category(double value) {
		adaptee.setAnzahlSortimente_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value none=1, few=2, many=3
	 */
	public void setDifficulties_category(double value) {
		adaptee.setErschwernisse_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 40m=1, 30m=2, 20m=3
	 */
	public void setDistanceBetweenSkidTrails_category(double value) {
		adaptee.setAbstandRueckegassen_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value medium=1, big=2
	 */
	public void setForwarderType_category(double value) {
		adaptee.setForwardertyp_Kategorie(value);
	}
	
	public void setCostsMachineOperator_perH(double value) {
		adaptee.setKostenMaschinist_proH(value);
	}
	
	public void setCostsForwarder_perH(double value) {
		adaptee.setKostenForwarder_proH(value);
	}

	
	public double getTimeMachineOperator_WPPH() {
		return adaptee.getZeitMaschinist_WPPH();
	}

	public double getTimeForwarder_PMH15() {
		return adaptee.getZeitForwarder_PMH15();
	}
	

	public double getCostsMachineOperator_total() {
		return adaptee.getKostenMaschinist_total();
	}

	public double getCostsForwarder_total() {
		return adaptee.getKostenForwarder_total();
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
