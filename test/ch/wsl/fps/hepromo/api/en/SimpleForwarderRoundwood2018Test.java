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

import ch.wsl.fps.hepromo.api.SimpleForwarderRundholz2018Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleForwarderRoundwood2018Test extends SimpleForwarderRundholz2018Test {
	
	@Override
	protected String getCsvPath() {
		final String className = this.getClass().getSuperclass().getSimpleName().replace("Test", "");
		final String csvPath = "testcases/api/" + className + ".csv";
		return csvPath;
	}

	@Override
	protected double[] getErgebnis(double[] input) {
		// set input variables
		SimpleForwarderRoundwood2018 model = new SimpleForwarderRoundwood2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setRoundwoodVolume_m3ob(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMeanDbh_cm(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setSkiddingDistance_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setSlope_category(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setNumberOfAssortments_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDifficulties_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDistanceBetweenSkidTrails_category(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setForwarderType_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsMachineOperator_perH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsForwarder_perH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDailyWorkTime_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTravelAndBreakTimes_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTransfer_amount(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsFurtherWork_amount(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCorrectionFactor_factor(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRiskAdministrationBenefit_pct(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setValueAddedTax_pct(			input[currentLineIndex]	);
		
		
		// get output variables
		double[] result = new double[9];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getTimeMachineOperator_WPPH();
		result[++currentLineIndex] = model.getTimeForwarder_PMH15();
		result[++currentLineIndex] = model.getCostsMachineOperator_perM3ub();
		result[++currentLineIndex] = model.getCostsForwarder_perM3ub();
		result[++currentLineIndex] = model.getCostsTransfer_perM3ub();
		result[++currentLineIndex] = model.getCostsFurtherWork_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_total();
		result[++currentLineIndex] = model.getProductivity_m3obPerPMH15();
		
		return result;
	}

}
