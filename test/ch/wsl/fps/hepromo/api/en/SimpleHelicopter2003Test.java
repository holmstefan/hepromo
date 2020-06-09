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

import ch.wsl.fps.hepromo.api.SimpleHelikopter2003Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHelicopter2003Test extends SimpleHelikopter2003Test {
	
	@Override
	protected String getCsvPath() {
		final String className = this.getClass().getSuperclass().getSimpleName().replace("Test", "");
		final String csvPath = "testcases/api/" + className + ".csv";
		return csvPath;
	}
	
	@Override
	protected double[] getErgebnis(double[] input) {
		// set input variables
		SimpleHelicopter2003 model = new SimpleHelicopter2003();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setTimberVolume_m3ob(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setWorkMethodAtLanding_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKindOfWood_category(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setClassOfTreeSpecies_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHorizontalDistance_m(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setVerticalDistanceDownwards_m(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsHelicopter_perMin(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setFlatRateReturnFlight_amount(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHelicopterClass_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCalculateLoadVolumeAutomatically_category(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setLoadVolume_m3(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setNumberOfStaffAtLogging_number(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsStaffPerPerson_perH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsChainsawsPerChainsaw_perH(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsVehicleWithCrane_perH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCalculationInclWorkAtLanding_category(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDailyWorkTime_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTravelAndBreakTimes_min(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTransfer_amount(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsFurtherWork_amount(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCorrectionFactor_factor(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRiskAdministrationBenefit_pct(input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setValueAddedTax_pct(			input[currentLineIndex]	);
		
		
		// get output variables
		double[] result = new double[12];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getTimeHelicopter_PMH15();
		result[++currentLineIndex] = model.getTimeStaffForestEnterprise_WPPH();
		result[++currentLineIndex] = model.getTimeChainsaw_PMH15();
		result[++currentLineIndex] = model.getTimeVehicleWithCrane_PMH15();
		result[++currentLineIndex] = model.getCostsHelicopter_perM3ub();
		result[++currentLineIndex] = model.getCostsStaffForestEnterprise_perM3ub();
		result[++currentLineIndex] = model.getCostsChainsaw_perM3ub();
		result[++currentLineIndex] = model.getCostsVehicleWithCrane_perM3ub();
		result[++currentLineIndex] = model.getCostsTransfer_perM3ub();
		result[++currentLineIndex] = model.getCostsFurtherWork_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_total();
		
		return result;
	}

}
