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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhundSchichtholzAufarbeiten;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class CalculatorIhUndSchichtholzAufarbeiten extends CalculatorMotormanuell {
	
	public CalculatorIhUndSchichtholzAufarbeiten(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektIhundSchichtholzAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektIhundSchichtholzAufarbeiten) super.getArbeitsobjekt();
	}



	@Override
	protected double getM2() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
		case Foehre_Laerche:
			switch (getArbeitsobjekt().getAnteilShUndIh()) {
			case Anteil_0bis10:
				return 1.20;
				
			case Anteil_11bis20:
				return 1.00;
				
			case Anteil_21bis35:
				return 0.86;
				
			case Anteil_36bis60:
				return 0.77;
				
			case Anteil_61bis100:
				return 0.71;
			}
			throw new RuntimeException();
			
		case Laubholz:
			switch (getArbeitsobjekt().getAnteilShUndIh()) {
			case Anteil_0bis10:
				return 1.22;
				
			case Anteil_11bis20:
				return 1.22;
				
			case Anteil_21bis35:
				return 1.11;
				
			case Anteil_36bis60:
				return 1.00;
				
			case Anteil_61bis100:
				return 0.90;
			}
			throw new RuntimeException();
		
		}
		throw new RuntimeException();
	}
	
	
	
	@Override
	protected double getM5() {
		switch (getArbeitsobjekt().getHangneigung()) {
		case Hangneigung_0bis30:
			return 1.00;

		case Hangneigung_31bis50:
		case Hangneigung_51bis70:
		case Hangneigung_71bis100:
			return 1.21;

		}
		throw new RuntimeException();
	}
	
	

	@Override
	protected double getLaufzeitAnteilMaschine() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
		case Foehre_Laerche:
			return 0.55;
			
		case Laubholz:
			return 0.63;
		
		}
		throw new RuntimeException();
	}
}
