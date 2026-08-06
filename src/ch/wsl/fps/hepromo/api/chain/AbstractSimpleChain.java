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

import ch.wsl.fps.hepromo.api.AbstractSimpleModel;

/**
 *
 * @author Stefan Holm
 *
 */
public abstract class AbstractSimpleChain {

	// Work system
	public static final double DEFAULT_DAILY_WORK_TIME_MIN = AbstractSimpleModel.DEFAULT_TAEGLICHE_ARBEITSZEIT_MIN;
	public static final double DEFAULT_TRAVEL_AND_BREAK_TIMES_MIN = AbstractSimpleModel.DEFAULT_WEGZEITEN_UND_PAUSEN_MIN;
	public static final double DEFAULT_COSTS_TRANSFER_AMOUNT = AbstractSimpleModel.DEFAULT_KOSTEN_UMSETZEN_BETRAG;
	public static final double DEFAULT_COSTS_FURTHER_WORK_AMOUNT = AbstractSimpleModel.DEFAULT_KOSTEN_WEITERE_AUFWAENDE_BETRAG;

	// Factors
	public static final double DEFAULT_CORRECTION_FACTOR_FACTOR = AbstractSimpleModel.DEFAULT_KORREKTURFAKTOR_FAKTOR;
	public static final double DEFAULT_RISK_ADMINISTRATION_BENEFIT_PCT = AbstractSimpleModel.DEFAULT_RISIKO_VERWALTUNG_GEWINN_PRZ;
	public static final double DEFAULT_VALUE_ADDED_TAX_PCT = AbstractSimpleModel.DEFAULT_MEHRWERTSTEUER_PRZ;


	protected final AbstractSimpleModel<?> fellingModel;
	protected final AbstractSimpleModel<?> extractionModel;


	protected AbstractSimpleChain(AbstractSimpleModel<?> fellingModel, AbstractSimpleModel<?> extractionModel) {
		this.fellingModel = fellingModel;
		this.extractionModel = extractionModel;
	}


	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setDailyWorkTime_min(double value) {
		fellingModel.setTaeglicheArbeitszeit_min(value);
		extractionModel.setTaeglicheArbeitszeit_min(value);
	}

	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTravelAndBreakTimes_min(double value) {
		fellingModel.setWegzeitenUndPausen_min(value);
		extractionModel.setWegzeitenUndPausen_min(value);
	}

	public void setCostsTransfer_amount(double value) {
		fellingModel.setKostenUmsetzen_Betrag(value / 2); // given value is the sum for the whole process chain, therefore value needs to be divided by number of processes in the chain.
		extractionModel.setKostenUmsetzen_Betrag(value / 2); // given value is the sum for the whole process chain, therefore value needs to be divided by number of processes in the chain.
	}

	public void setCostsFurtherWork_amount(double value) {
		fellingModel.setKostenWeitereAufwaende_Betrag(value / 2); // given value is the sum for the whole process chain, therefore value needs to be divided by number of processes in the chain.
		extractionModel.setKostenWeitereAufwaende_Betrag(value / 2); // given value is the sum for the whole process chain, therefore value needs to be divided by number of processes in the chain.
	}

	public void setCorrectionFactor_factor(double value) {
		fellingModel.setKorrekturfaktor_Faktor(value);
		extractionModel.setKorrekturfaktor_Faktor(value);
	}

	public void setRiskAdministrationBenefit_pct(double value) {
		fellingModel.setRisikoVerwaltungGewinn_Prz(value);
		extractionModel.setRisikoVerwaltungGewinn_Prz(value);
	}

	public void setValueAddedTax_pct(double value) {
		fellingModel.setMehrwertsteuer_Prz(value);
		extractionModel.setMehrwertsteuer_Prz(value);
	}


	public double getCostsTotal_total() {
		return fellingModel.getKostenTotal_total() + extractionModel.getKostenTotal_total();
	}

}
