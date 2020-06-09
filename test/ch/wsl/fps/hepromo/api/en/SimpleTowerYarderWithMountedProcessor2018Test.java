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

import ch.wsl.fps.hepromo.api.SimpleKombiseilgeraet2018Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleTowerYarderWithMountedProcessor2018Test extends SimpleKombiseilgeraet2018Test {
	
	@Override
	protected String getCsvPath() {
		final String className = this.getClass().getSuperclass().getSimpleName().replace("Test", "");
		final String csvPath = "testcases/api/" + className + ".csv";
		return csvPath;
	}
	
	@Override
	protected double[] getErgebnis(double[] input) {
		// set input variables
		SimpleTowerYarderWithMountedProcessor2018 model = new SimpleTowerYarderWithMountedProcessor2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setWoodVolume_m3ob(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setNumberOfLines_number(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMeanLineLength_m(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setNumberOfSupportsPerLine_number(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setSlope_pct(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setPercentageOfBroadleaves_pct(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDifficulties_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCrewNumberOfWorkers_number(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setOperatingTimeEngineOnLandingPlace_pct(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsStaffPerPerson_perH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTowerYarderWithMountedProcessor_perH(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsChainsaw_perH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsEngineOnLandingPlaceInclDriver_perH(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDailyWorkTime_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTravelAndBreakTimes_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTransfer_amount(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsFurtherWork_amount(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCorrectionFactor_factor(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRiskAdministrationBenefit_pct(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setValueAddedTax_pct(			input[currentLineIndex]	);
		
		
		// get output variables
		double[] result = new double[13];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getTimeStaff_WPPH();
		result[++currentLineIndex] = model.getTimeTowerYarderWithMountedProcessor_PMH15();
		result[++currentLineIndex] = model.getTimeChainsaw_PMH15();
		result[++currentLineIndex] = model.getTimeEngineOnLandingPlaceInclDriver_PMH15();
		result[++currentLineIndex] = model.getCostsStaff_perM3ub();
		result[++currentLineIndex] = model.getCostsTowerYarderWithMountedProcessor_perM3ub();
		result[++currentLineIndex] = model.getCostsChainsaw_perM3ub();
		result[++currentLineIndex] = model.getCostsEngineOnLandingPlaceInclDriver_perM3ub();
		result[++currentLineIndex] = model.getCostsTransfer_perM3ub();
		result[++currentLineIndex] = model.getCostsFurtherWork_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_total();
		result[++currentLineIndex] = model.getProductivity_m3obPerWSH();
		
		return result;
	}

}
