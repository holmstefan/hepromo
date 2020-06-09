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
package ch.wsl.fps.hepromo;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractHeProMoTest {

	protected double DEFAULT_DELTA = 0.0051;
	protected static final boolean ADAPT_DELTA = true;
	
	private Object[][] testData;

	@DataProvider(name="csvData")
	public final Object[][] getTestData() {
		return testData;
	}

	protected final void setTestData(Object[][] testData) {
		this.testData = testData;
	}
	
	protected abstract String getCsvPath();
	
	protected void assertEqualsDynamicDelta(double actual, double expected) {
		if (expected < 10000) {
			assertEqualsDynamicAbsoluteDelta(actual, expected);
		}
		else {
			assertEqualsDynamicRelativeDelta(actual, expected);
		}
	}
	
	private final void assertEqualsDynamicAbsoluteDelta(double actual, double expected) {
		double delta = DEFAULT_DELTA;
		if (ADAPT_DELTA && expected > 1000) {
			delta *= 2;
		}
		
		assertEquals(actual, expected, delta);
	}
	
	private final void assertEqualsDynamicRelativeDelta(double actual, double expected) {
		double relativeDelta = 1.00005;
		double absoluteDelta = expected * (relativeDelta - 1.0);
		
		assertEquals(actual, expected, absoluteDelta);
	}
	
	protected abstract int getFirstOutputField();
	
	protected abstract int getLastOutputField();
}
