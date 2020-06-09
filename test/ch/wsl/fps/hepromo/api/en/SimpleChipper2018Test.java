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

import ch.wsl.fps.hepromo.api.SimpleHacker2018Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleChipper2018Test extends SimpleHacker2018Test {
	
	@Override
	protected String getCsvPath() {
		final String className = this.getClass().getSuperclass().getSimpleName().replace("Test", "");
		final String csvPath = "testcases/api/" + className + ".csv";
		return csvPath;
	}
	
	@Override
	protected double[] getErgebnis(double[] input) {
		// set input variables
		SimpleChipper2018 model = new SimpleChipper2018();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setVolumeOfWoodchips_bcm(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setKindOfWoodOnPile_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setEnginePowerChipper_kW(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsOperator_perH(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsChipper_perH(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDailyWorkTime_min(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTravelAndBreakTimes_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTransfer_amount(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsFurtherWork_amount(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCorrectionFactor_factor(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRiskAdministrationBenefit_pct(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setValueAddedTax_pct(				input[currentLineIndex]	);
		
		
		// get output variables
		double[] result = new double[9];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getTimeOperator_WPPH();
		result[++currentLineIndex] = model.getTimeChipper_PMH15();
		result[++currentLineIndex] = model.getCostsOperator_perBcm();
		result[++currentLineIndex] = model.getCostsChipper_perBcm();
		result[++currentLineIndex] = model.getCostsTransfer_perBcm();
		result[++currentLineIndex] = model.getCostsFurtherWork_perBcm();
		result[++currentLineIndex] = model.getCostsTotal_perBcm();
		result[++currentLineIndex] = model.getCostsTotal_total();
		result[++currentLineIndex] = model.getProductivity_bcmPerPMH15();
		
		return result;
	}

}
