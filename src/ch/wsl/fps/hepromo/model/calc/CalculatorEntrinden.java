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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class CalculatorEntrinden extends CalculatorMotormanuell {
	
	public CalculatorEntrinden(HeProMoInputData inputData) {
		super(inputData);
	}
	

	@Override
	protected ArbeitsobjektEntrinden getArbeitsobjekt() {
		return (ArbeitsobjektEntrinden) super.getArbeitsobjekt();
	}



	@Override
	protected double getM2() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
			switch (getArbeitsobjekt().getAnteilShUndIhLang()) {
			case Anteil_0bis10:
				return 1.08;
				
			case Anteil_11bis20:
				return 1.00;
				
			case Anteil_21bis35:
				return 0.93;
				
			case Anteil_36bis60:
				return 0.87;
				
			case Anteil_61bis100:
				return 0.87;
			}
			throw new RuntimeException();
			
		case Foehre_Laerche:
			switch (getArbeitsobjekt().getAnteilShUndIhLang()) {
			case Anteil_0bis10:
				return 1.06;
				
			case Anteil_11bis20:
				return 1.00;
				
			case Anteil_21bis35:
				return 0.93;
				
			case Anteil_36bis60:
				return 0.87;
				
			case Anteil_61bis100:
				return 0.87;
			}
			throw new RuntimeException();
			
		case Laubholz:
			//not implemented;
			return 1;
		
		}
		throw new RuntimeException();
	}

	
	
	
	@Override
	protected double getM5() {
		switch (getArbeitsobjekt().getHangneigung()) {
		case Hangneigung_0bis30:
			return 1.00;
			
		case Hangneigung_31bis50:
			return 1.08;
			
		case Hangneigung_51bis70:
			return 1.19;
			
		case Hangneigung_71bis100:
			return 1.40;
		
		}
		throw new RuntimeException();
	}
}
