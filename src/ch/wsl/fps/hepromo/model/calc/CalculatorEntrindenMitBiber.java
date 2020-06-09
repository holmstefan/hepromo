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

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorEntrindenMitBiber extends CalculatorEntrinden {

	public CalculatorEntrindenMitBiber(HeProMoInputData inputData) {
		super(inputData);
	}


	@Override
	protected double[] getKoeffizienten(Baumartgruppe baGruppe) {
		switch (baGruppe) {
		case Fichte:
		case Tanne:
			return new double[]{
					 5.0000,
					 8.8380,
					-0.0659,
					-7.8320
					};
			
		case Foehre_Laerche:
			return new double[]{
					 1.0000,
					 0.4196,
					-0.9319,
					 2.3259
					};
			
		case Laubholz:
			//not implemented
//			return new double[]{
//					0,
//					0,
//					0,
//					0
//					};
			throw new RuntimeException("not implemented");
		}
		
		throw new RuntimeException();
	}


	@Override
	protected double getLaufzeitAnteilMaschine() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
			return 0.90;
			
		case Foehre_Laerche:
			return 0.90;
			
		case Laubholz:
			//not implemented
			return 0;
		
		}
		throw new RuntimeException();
	}

}
