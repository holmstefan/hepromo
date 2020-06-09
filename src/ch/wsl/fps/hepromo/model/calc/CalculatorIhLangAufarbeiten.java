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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorIhLangAufarbeiten extends CalculatorIhUndSchichtholzAufarbeiten {
	
	public CalculatorIhLangAufarbeiten(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektIhLangAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektIhLangAufarbeiten) super.getArbeitsobjekt();
	}
	
	

	@Override
	protected double getM3() {
		switch (getArbeitsobjekt().getStuecklaengen()) {
		case Bis7m:
			return 1.0;
			
		case Ueber7m:
			return 0.77;
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
					  4.0000,
					 20.0000,
					 -0.0167,
					-19.6650
					 };
			
		case Laubholz:
			return new double[]{
					  1.0856,
					  0.1848,
					 -0.5688,
					  1.2685
					 };
		}
		
		throw new RuntimeException();
	}
	
	
	@Override
	protected double getRelevanteHolzmenge() {
		return getArbeitsobjekt().getMengeIndustrieholzLang_m3iR();
	}

}
