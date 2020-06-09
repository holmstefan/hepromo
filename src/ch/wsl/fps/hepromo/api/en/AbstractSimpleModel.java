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
abstract class AbstractSimpleModel<T extends ch.wsl.fps.hepromo.api.AbstractSimpleModel<?>> {
	
	// Work system
	public static final double DEFAULT_DAILY_WORK_TIME_MIN = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_TAEGLICHE_ARBEITSZEIT_MIN;
	public static final double DEFAULT_TRAVEL_AND_BREAK_TIMES_MIN = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_WEGZEITEN_UND_PAUSEN_MIN;
	public static final double DEFAULT_COSTS_TRANSFER_AMOUNT = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_KOSTEN_UMSETZEN_BETRAG;
	public static final double DEFAULT_COSTS_FURTHER_WORK_AMOUNT = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_KOSTEN_WEITERE_AUFWAENDE_BETRAG;

	// Factors
	public static final double DEFAULT_CORRECTION_FACTOR_FACTOR = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_KORREKTURFAKTOR_FAKTOR;
	public static final double DEFAULT_RISK_ADMINISTRATION_BENEFIT_PCT = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_RISIKO_VERWALTUNG_GEWINN_PRZ;
	public static final double DEFAULT_VALUE_ADDED_TAX_PCT = ch.wsl.fps.hepromo.api.AbstractSimpleModel.DEFAULT_MEHRWERTSTEUER_PRZ;
	
	protected final T adaptee;
	
	public AbstractSimpleModel(T adaptee) {
		this.adaptee = adaptee;
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setDailyWorkTime_min(double value) {
		adaptee.setTaeglicheArbeitszeit_min(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTravelAndBreakTimes_min(double value) {
		adaptee.setWegzeitenUndPausen_min(value);
	}

	public void setCostsTransfer_amount(double value) {
		adaptee.setKostenUmsetzen_Betrag(value);
	}
	
	public void setCostsFurtherWork_amount(double value) {
		adaptee.setKostenWeitereAufwaende_Betrag(value);
	}
	
	public void setCorrectionFactor_factor(double value) {
		adaptee.setKorrekturfaktor_Faktor(value);
	}
	
	public void setRiskAdministrationBenefit_pct(double value) {
		adaptee.setRisikoVerwaltungGewinn_Prz(value);
	}
	
	public void setValueAddedTax_pct(double value) {
		adaptee.setMehrwertsteuer_Prz(value);
	}
	
	public double getCostsTotal_total() {
		return adaptee.getKostenTotal_total();
	}

}
