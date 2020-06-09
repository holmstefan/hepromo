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

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorRadharvester extends AbstractCalculatorSingleModel {
	

	public CalculatorRadharvester(HeProMoInputData inputData) {
		super(inputData);
	}
	
	

	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		ergebnis.hideDauerDerArbeit();
		return ergebnis;
	}
	
	
	@Override
	protected ArbeitsobjektRadharvester getArbeitsobjekt() {
		return (ArbeitsobjektRadharvester) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemRadharvester getArbeitssystem() {
		return (ArbeitssystemRadharvester) super.getArbeitssystem();
	}
	
	
	

	@Override
	protected double calcPsh0_hProM3() {
		
		//Konsistenz-Check
		if (getArbeitssystem().getMaschinenKategorie() != getArbeitssystem().getMaschinenTyp().getMaschinenKategorie() ) {
			throw new RuntimeException("Maschinenkategorie / Maschinentyp nicht kompatibel");
		}
		
		

		double[] hk1und2 = getHK1und2();
		double hk1 = hk1und2[0];
		double hk2 = hk1und2[1];
		
		double pv = getArbeitsobjekt().getVolumenMittelstamm_m3();
		
		double ba; //Faktor für Baumart
		switch (getArbeitsobjekt().getBaumartGruppe()) {
		case Fichte:
			ba = 0;
			break;
		case Foehre_Laerche:
			ba = 1;
			break;
		default:
			throw new RuntimeException();
		}
//		double r = 0.1; //Faktor für Rindenanteil
		
		
		double prod15 = (
				-  3.87
				+ 11.43 * Math.pow(pv, 0.25)
				-  3.5  * Math.pow(hk1, 0.4)
				+ 10.06 * Math.pow(pv, 0.25) * Math.pow(hk1, 0.4)
				+  0.52 * hk2
				+ 1.01  * ba);
//				* (1/(1-r)); //doku = falsch!
		
		double pmh15 = 1/prod15;


		switch (getArbeitssystem().getMaschinenKategorie()) {
		case Mittel:
			f_0bis15 = 1.1;
			break;
		case Gross:
			f_0bis15 = 1.3;
			break;
		default:
			throw new RuntimeException();
		}
		double psh0 = pmh15/f_0bis15;
		
		
		return psh0;
	}

	
	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 1;
	}
	
	
	
	private double[] getHK1und2() {
		double[] techParam = getArbeitssystem().getMaschinenTyp().getTechnologieFaktoren();
		double a = (techParam[0] - 111.75) / 29.07; //ML //variante doku
//		double a = (techParam[0] - 111.75) / 19.07; //ML //variante alter code
		double b = (techParam[1] -   9.43) /  0.98; //KR
		double c = (techParam[2] - 126.06) / 40.31; //HM
		double d = (techParam[3] -  30.69) /  6.63; //SM
		double e = (techParam[4] -  51.06) /  8.74; //FD
		double f = (techParam[5] -  20.69) /  2.91; //EK

		double hk1 = 0.44*a + 0.34*b + 0.45*c + 0.39*d + 0.41*e + 0.39*f + 4.1;
		double hk2 = 0.27*a - 0.65*b + 0.02*c - 0.49*d + 0.34*e + 0.38*f      ; //variante doku
//		double hk2 = 0.27*a - 0.65*b + 0.02*c - 0.49*d + 0.34*e + 0.30*f      ; //variante alter code
		
		return new double[]{hk1, hk2};
	}

}
