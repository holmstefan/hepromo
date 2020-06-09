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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorStammholzAufarbeiten extends CalculatorMotormanuell{
	
	public CalculatorStammholzAufarbeiten(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektStammholzAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektStammholzAufarbeiten) super.getArbeitsobjekt();
	}
	
	

	@Override
	protected double getM3() {
		switch (getArbeitsobjekt().getStuecklaengen()) {
		case WenigerAls4m:
			switch (getArbeitsobjekt().getBaumartenGruppe()) {
			case Fichte:
			case Tanne:
			case Foehre_Laerche:
				return 1.5;
			case Laubholz:
				return 1.21;
			}
			throw new RuntimeException();

		case Von4Bis6m:
			switch (getArbeitsobjekt().getBaumartenGruppe()) {
			case Fichte:
			case Tanne:
			case Foehre_Laerche:
				return 1.5;
			case Laubholz:
				return 1.0;
			}
			throw new RuntimeException();

		case Von6Bis10m:
			switch (getArbeitsobjekt().getBaumartenGruppe()) {
			case Fichte:
			case Tanne:
			case Foehre_Laerche:
				return 1.24;
			case Laubholz:
				return 0.89;
			}
			throw new RuntimeException();

		case MehrAls10m:
			switch (getArbeitsobjekt().getBaumartenGruppe()) {
			case Fichte:
			case Tanne:
			case Foehre_Laerche:
				return 1.00;
			case Laubholz:
				return 0.89;
			}
			throw new RuntimeException();

		}
		throw new RuntimeException();
	}
	
	
	

	@Override
	protected double getM4() {
		if (getArbeitsobjekt().isKantenBrechen() == true) {		
			switch (getArbeitsobjekt().getStuecklaengen()) {
			case WenigerAls4m:
			case Von4Bis6m:
				return 1.35;

			case Von6Bis10m:
				switch (getArbeitsobjekt().getBaumartenGruppe()) {
				case Fichte:
				case Tanne:
					return 1.25;
				case Foehre_Laerche:
				case Laubholz:
					return 1.26;
				}
				throw new RuntimeException();

			case MehrAls10m:
				switch (getArbeitsobjekt().getBaumartenGruppe()) {
				case Fichte:
				case Tanne:
					return 1.15;
				case Foehre_Laerche:
				case Laubholz:
					return 1.17;
				}
				throw new RuntimeException();

			}
			throw new RuntimeException();	
		}
		else {		
			return 1.0;	
		}
	}
	
	
	

	@Override
	protected double getM5() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
		case Foehre_Laerche:
			switch (getArbeitsobjekt().getHangneigung()) {
			case Hangneigung_0bis30:
				return 1.00;

			case Hangneigung_31bis50:
				return 1.11;

			case Hangneigung_51bis70:
				return 1.31;

			case Hangneigung_71bis100:
				return 1.77;

			}
			throw new RuntimeException();
		case Laubholz:
			switch (getArbeitsobjekt().getHangneigung()) {
			case Hangneigung_0bis30:
				return 1.00;

			case Hangneigung_31bis50:
				return 1.07;

			case Hangneigung_51bis70:
				return 1.32;

			case Hangneigung_71bis100:
				return 1.77;

			}
			throw new RuntimeException();
		}
		throw new RuntimeException();
	}

	
	
	@Override
	protected double[] getKoeffizienten(Baumartgruppe baGruppe) {
		switch (baGruppe) {
		case Fichte:
		case Tanne:			
		case Foehre_Laerche:
			return new double[]{
					  2.0000,
					 25.9148,
					 -0.0136,
					-24.9783
					 };
			
		case Laubholz:
			return new double[]{
					  4.0000,
					 -0.2769,
					  0.7009,
					  0.4403
					 };
		}
		
		throw new RuntimeException();
	}

	
	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
		case Foehre_Laerche:
			return 0.18;
			
		case Laubholz:
			return 0.34;
		
		}
		throw new RuntimeException();
	}

}
