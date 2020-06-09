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

import ch.wsl.fps.hepromo.api.SimpleFaellenVorruecken2018Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleFellingPreskidding2018Test extends SimpleFaellenVorruecken2018Test {
	
	@Override
	protected String getCsvPath() {
		final String className = this.getClass().getSuperclass().getSimpleName().replace("Test", "");
		final String csvPath = "testcases/api/" + className + ".csv";
		return csvPath;
	}

	@Override
	protected double[] getErgebnis(double[] input) {
		// set input variables
		SimpleFellingPreskidding2018 model = new SimpleFellingPreskidding2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setWoodVolume_m3ob(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMeanDbh_cm(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setFertility_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setSlope_category(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCutTheTop_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMachineType_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setProductivityIncreaseSmallTrackedSkidder_factor(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsStaff_perH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsPreskiddingEngine_perH(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsChainsaw_perH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDailyWorkTime_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTravelAndBreakTimes_min(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTransfer_amount(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsFurtherWork_amount(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCorrectionFactor_factor(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRiskAdministrationBenefit_pct(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setValueAddedTax_pct(		input[currentLineIndex]	);
		
		
		// get output variables
		double[] result = new double[11];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getTimeStaff_WPPH();
		result[++currentLineIndex] = model.getTimePreskiddingEngine_PMH15();
		result[++currentLineIndex] = model.getTimeChainsaw_PMH15();
		result[++currentLineIndex] = model.getCostsStaff_perM3ub();
		result[++currentLineIndex] = model.getCostsPreskiddingEngine_perM3ub();
		result[++currentLineIndex] = model.getCostsChainsaw_perM3ub();
		result[++currentLineIndex] = model.getCostsTransfer_perM3ub();
		result[++currentLineIndex] = model.getCostsFurtherWork_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_total();
		result[++currentLineIndex] = model.getProductivity_m3obperPMH15();
		
		return result;
	}

}
