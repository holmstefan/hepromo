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
package ch.wsl.fps.hepromo.model.calc;

import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractCalculatorComposedModel extends AbstractCalculator {

	public AbstractCalculatorComposedModel(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	
	protected final HeProMoInputData createHeProMoInputData(final Arbeitsobjekt arbeitsObjekt, final Arbeitssystem arbeitsSystem, final Faktoren faktoren) {
		HeProMoInputData inputData = new HeProMoInputData() {
			@Override
			public Faktoren getFaktoren() {
				return faktoren;
			}
			
			@Override
			public Arbeitssystem getArbeitssystem() {
				return arbeitsSystem;
			}
			
			@Override
			public Arbeitsobjekt getArbeitsobjekt() {
				return arbeitsObjekt;
			}
			
			@Override
			public String getArbeitsort() {
				return null;
			}
		};
		
		return inputData;
	}

}
