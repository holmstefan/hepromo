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

import java.text.DecimalFormat;

import org.testng.annotations.Test;

/**
 *
 * @author Stefan Holm
 *
 */
public class SimpleHarvesterForwarderTest extends AbstractSimpleChainTest {

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkTimeEngineDriver_WPPH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[0], expectedValues[0]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkTimeMachineOperator_WPPH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[1], expectedValues[1]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkTimeForwarder_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[2], expectedValues[2]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkTimeHarvester_PMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[3], expectedValues[3]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkTimeWinchAssistedHarvesting_ISH(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[4], expectedValues[4]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsEngineDriver_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[5], expectedValues[5]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsMachineOperator_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[6], expectedValues[6]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsForwarder_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[7], expectedValues[7]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsHarvester_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[8], expectedValues[8]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsWinchAssistedHarvesting_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[9], expectedValues[9]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsTransfer_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[10], expectedValues[10]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsFurtherWork_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[11], expectedValues[11]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsTotal_perM3ub(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[12], expectedValues[12]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsEngineDriver_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[13], expectedValues[13]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsMachineOperator_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[14], expectedValues[14]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsForwarder_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[15], expectedValues[15]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsHarvester_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[16], expectedValues[16]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsWinchAssistedHarvesting_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[17], expectedValues[17]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsTransfer_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[18], expectedValues[18]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsFurtherWork_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[19], expectedValues[19]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkCostsTotal_total(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[20], expectedValues[20]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProductivityHarvester_m3obPerPMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[21], expectedValues[21]);
	}

	@SuppressWarnings("unused")
	@Test(dataProvider="csvData")
	public void checkProductivityForwarder_m3obPerPMH15(String testcaseName, double[] ergebnis, double[] expectedValues) {
		assertEqualsDynamicDelta(ergebnis[22], expectedValues[22]);
	}


	@Override
	protected double[] getErgebnis(double[] input) {
		//Inputvariablen setzen
		SimpleHarvesterForwarder model = new SimpleHarvesterForwarder();
		int currentLineIndex = -1;
		if (input[++currentLineIndex] != -1) model.setTimberVolume_m3ob(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMeanDbh_cm(						input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setStemForm_category(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setPercentageOfBroadleaves_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setPrefelledTrees_category(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setMeanSlope_category(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setUseOfWinchAssistedHarvesting_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setNumberOfSkidTrailsWithWinchAssistedHarvesting_number(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setSkiddingDistance_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setSlope_category(					input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setNumberOfAssortments_category(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDifficulties_category(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDistanceBetweenSkidTrails_category(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setHarvesterType_category(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsEngineDriver_perH(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsHarvester_perH(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsWinchAssistedHarvesting_perH(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setForwarderType_category(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsMachineOperator_perH(		input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsForwarder_perH(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setDailyWorkTime_min(				input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setTravelAndBreakTimes_min(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsTransfer_amount(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCostsFurtherWork_amount(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setCorrectionFactor_factor(			input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setRiskAdministrationBenefit_pct(	input[currentLineIndex]	);
		if (input[++currentLineIndex] != -1) model.setValueAddedTax_pct(				input[currentLineIndex]	);


		//Outputvariablen auslesen
		double[] result = new double[23];
		currentLineIndex = -1;
		result[++currentLineIndex] = model.getTimeEngineDriver_WPPH();
		result[++currentLineIndex] = model.getTimeMachineOperator_WPPH();
		result[++currentLineIndex] = model.getTimeForwarder_PMH15();
		result[++currentLineIndex] = model.getTimeHarvester_PMH15();
		result[++currentLineIndex] = model.getTimeWinchAssistedHarvesting_ISH();
		result[++currentLineIndex] = model.getCostsEngineDriver_perM3ub();
		result[++currentLineIndex] = model.getCostsMachineOperator_perM3ub();
		result[++currentLineIndex] = model.getCostsForwarder_perM3ub();
		result[++currentLineIndex] = model.getCostsHarvester_perM3ub();
		result[++currentLineIndex] = model.getCostsWinchAssistedHarvesting_perM3ub();
		result[++currentLineIndex] = model.getCostsTransfer_perM3ub();
		result[++currentLineIndex] = model.getCostsFurtherWork_perM3ub();
		result[++currentLineIndex] = model.getCostsTotal_perM3ub();
		result[++currentLineIndex] = model.getCostsEngineDriver_total();
		result[++currentLineIndex] = model.getCostsMachineOperator_total();
		result[++currentLineIndex] = model.getCostsForwarder_total();
		result[++currentLineIndex] = model.getCostsHarvester_total();
		result[++currentLineIndex] = model.getCostsWinchAssistedHarvesting_total();
		result[++currentLineIndex] = model.getCostsTransfer_total();
		result[++currentLineIndex] = model.getCostsFurtherWork_total();
		result[++currentLineIndex] = model.getCostsTotal_total();
		result[++currentLineIndex] = model.getProductivityHarvester_m3obPerPMH15();
		result[++currentLineIndex] = model.getProductivityForwarder_m3obPerPMH15();
		
		//Dient zum Neuschreiben von Testdateien bei Änderungen im Calculator
		final boolean rewriteTests = false;
		if (rewriteTests) {
			DecimalFormat df = new DecimalFormat("0.00");
			for (int i=0; i<=currentLineIndex; i++) {
				System.err.print( df.format(result[i]) + "\t");
			}
			System.out.println();
		}

		return result;
	}


	@Override
	protected int getFirstOutputField() {
		return 27;
	}

	@Override
	protected int getLastOutputField() {
		return 49;
	}

}
