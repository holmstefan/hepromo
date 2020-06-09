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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorSchichtholzAufarbeiten extends CalculatorIhUndSchichtholzAufarbeiten {
	
	public CalculatorSchichtholzAufarbeiten(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektSchichtholzAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektSchichtholzAufarbeiten) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected double getA3() {
		double faktor = -1;
		
		switch (getArbeitsobjekt().getBaumartenGruppe()) {
		case Fichte:
		case Tanne:		
			faktor = 34.3;
			break;
			
		case Foehre_Laerche:
			faktor = 27.4;
			break;
			
		case Laubholz:
			faktor = 21.4;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		
		
		double a3 = faktor * getArbeitsobjekt().getAnteilSpalten_Prz() / 100.0;
		
		
		return a3;
	}
	
	

	@Override
	protected double getM3() {
		switch (getArbeitsobjekt().getStuecklaengen()) {
		case Laenge1m:
			return 1.0;
			
		case Laenge2m:
			return 0.64;
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
					 -0.0166,
					-18.4610
					 };
			
		case Laubholz:
			return new double[]{
					  1.4504,
					  0.1998,
					 -0.5495,
					  2.1618
					 };
		}
		
		throw new RuntimeException();
	}
	
	
	
	@Override
	protected double getRelevanteHolzmenge() {
		return getArbeitsobjekt().getMengeSchichtholz_m3iR();
	}
}
