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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorEntastenMotormanuell extends CalculatorMotormanuell {
	
	public CalculatorEntastenMotormanuell(HeProMoInputData inputData) {
		super(inputData);
	}


	@Override
	protected ArbeitsobjektEntastenMotormanuell getArbeitsobjekt() {
		return (ArbeitsobjektEntastenMotormanuell) super.getArbeitsobjekt();
	}
	
	

	@Override
	protected double getM1() {
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:
			switch (getArbeitsobjekt().getKronenLaengenKlasse()) {
			case Klasse_0bis33:
			case Klasse_33bis50:
			case Klasse_51bis66:
			case Klasse_67bis90:
				return 1.00;

			case Klasse_91bis100:
				return 1.25;

			}
			throw new RuntimeException();
			

		case Foehre_Laerche:
			switch (getArbeitsobjekt().getKronenLaengenKlasse()) {
			case Klasse_0bis33:
				return 0.87;

			case Klasse_33bis50:
				return 1.00;

			case Klasse_51bis66:
				return 1.17;

			case Klasse_67bis90:
			case Klasse_91bis100:
				return 1.62;

			}
			throw new RuntimeException();
			

		case Laubholz:
			switch (getArbeitsobjekt().getKronenLaengenKlasse()) {
			case Klasse_0bis33:
				return 0.73;

			case Klasse_33bis50:
				return 1.00;

			case Klasse_51bis66:
				return 1.25;

			case Klasse_67bis90:
			case Klasse_91bis100:
				return 1.53;

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
			return 1.05;
			
		case Hangneigung_51bis70:
			return 1.19;
			
		case Hangneigung_71bis100:
			return 1.52;
		
		}
		throw new RuntimeException();
	}
	
	
	

	@Override
	protected double getA1() {
		if (getArbeitsobjekt().getBaumartenGruppe() == Baumartgruppe.Tanne) {
			return 2.6;
		}
		return 0;
	}
	
	

	@Override
	protected double[] getKoeffizienten(Baumartgruppe baGruppe) {
		switch (baGruppe) {
		case Fichte:
		case Tanne:
			return new double[]{
					 4.0000,
					 4.8747,
					-0.0838,
					-3.7592
					};
			
		case Foehre_Laerche:
			return new double[]{
					 2.0000,
					26.2532,
					-0.0179,
					-25.2087
					};
			
		case Laubholz:
			return new double[]{
					 0.6871,
					 1.0303,
					-0.1878,
					 1.0000
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
			return 0.91;
			
		case Laubholz:
			return 0.93;
		
		}
		throw new RuntimeException();
	}

}
