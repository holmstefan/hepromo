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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektFaellenMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorFaellenMotormanuell extends CalculatorMotormanuell {
	
	public CalculatorFaellenMotormanuell(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektFaellenMotormanuell getArbeitsobjekt() {
		return (ArbeitsobjektFaellenMotormanuell) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected double getM5() {
		switch (getArbeitsobjekt().getHangneigung()) {
		case Hangneigung_0bis30:
			return 1.00;
			
		case Hangneigung_31bis50:
			return 1.05;
			
		case Hangneigung_51bis70:
			return 1.10;
			
		case Hangneigung_71bis100:
			return 1.25;
		
		}
		throw new RuntimeException();
	}
	
	
	

	@Override
	protected double getA2() {
		double anteilFaellenMitHandseilzug = getArbeitsobjekt().getAnteilFaellenHandseilzug_Prz() / 100.0;	
		double massenmittelstamm_m3oR = getArbeitsobjekt().getMassenmittelstamm_m3iR() * super.getUmrechnungsfaktorVoR_ViR(this.getArbeitsobjekt().getBaumartenGruppe());		
		double result = -1;
		
		if (anteilFaellenMitHandseilzug < 0.1) { // 0 < x < 0.1
			result = (anteilFaellenMitHandseilzug / massenmittelstamm_m3oR) * 16;

		}
		else if (anteilFaellenMitHandseilzug < 0.5) { // 0.1 <= x < 0.5
			result = (anteilFaellenMitHandseilzug / massenmittelstamm_m3oR) * (16 - 0.2 * (anteilFaellenMitHandseilzug * 100 - 10));
			
		}
		else { // 0.5 <= x <= 1.0
			result = (anteilFaellenMitHandseilzug / massenmittelstamm_m3oR) * 8;
			
		}
		
		
		return result;
	}
	
	

	@Override
	protected double[] getKoeffizienten(Baumartgruppe baGruppe) {
		switch (baGruppe) {
		case Fichte:
		case Tanne:
			return new double[]{
					 2.0000,
					 2.4291,
					-0.1596,
					-0.9108
					};
			
		case Foehre_Laerche:
			return new double[]{
					 2.0000,
					 2.4236,
					-0.1602,
					-1.2105
					};
			
		case Laubholz:
			return new double[]{
					 0.00236,
					 6.9216,
					-0.0634,
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
			return 0.48;
			
		case Foehre_Laerche:
			return 0.54;
			
		case Laubholz:
			return 0.61;
		
		}
		throw new RuntimeException();
	}
	
}
