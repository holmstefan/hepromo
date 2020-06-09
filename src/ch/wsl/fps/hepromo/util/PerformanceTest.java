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
package ch.wsl.fps.hepromo.util;

import java.util.Random;

import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.model.modelle.ModelKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellGesamt2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PerformanceTest {
	
	private static final Random RND = new Random();
	private static final int NUM_ITERATIONS = 2 * 1000 * 1000;

	public static void main(String[] args) {
		AbstractModel model = new ModelKombiseilgeraet2018();
//		AbstractModel model = new ModelMotormanuellGesamt2014();
		
		long start = System.currentTimeMillis();
		
		for (int i=0; i<NUM_ITERATIONS; i++) {
			model.getArbeitsobjekt().setHolzmenge_m3(getRandomHolzmenge());
//			model.getArbeitsobjekt().setHolzmenge_m3(i);
			model.getErgebnis();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
	}
	
	
	private static double getRandomHolzmenge() {
		double holzmenge = RND.nextInt(100000) + RND.nextDouble();
		return holzmenge;
	}

}
