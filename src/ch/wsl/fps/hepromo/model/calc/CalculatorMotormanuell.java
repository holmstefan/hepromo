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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class CalculatorMotormanuell extends AbstractCalculatorSingleModel {
	
	public CalculatorMotormanuell(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektMotormanuell getArbeitsobjekt() {
		return (ArbeitsobjektMotormanuell) super.getArbeitsobjekt();
	}
	
	
		
	
	/**
	 * Berechnet PSH0 [Stunden pro Kubikmeter in Rinde]
	 * 
	 * @return
	 */
	@Override
	protected double calcPsh0_hProM3() {
		double raz = calcRAZ_minProM3oR(getArbeitsobjekt().getBaumartenGruppe());
		double kBA = getUmrechnungsfaktorVoR_ViR(getArbeitsobjekt().getBaumartenGruppe());
		double m = getMulitplicationFactorsProduct();
		double a = getAdditionsSum();
		
		double psh0 = kBA * (1.0/60.0) * (m * raz + a);
		
		return psh0;
	}

	
	
	
	/**
	 * Berechnet RAZ (Grundzeit)
	 * 
	 * @return
	 */
	protected double calcRAZ_minProM3oR(Baumartgruppe baGruppe) {
		
		double[] c = getKoeffizienten(baGruppe);		
		double kBA = getUmrechnungsfaktorVoR_ViR(getArbeitsobjekt().getBaumartenGruppe());
		double vMit = getArbeitsobjekt().getMassenmittelstamm_m3iR() * kBA; //vMit = Massenmittelstamm [m3 ohne Rinde]
		
		double result = c[0] * Math.exp(c[1] * Math.pow(vMit, c[2]) + c[3]);
		
		return result;
	}	
	
	
	
	/**
	 * Gibt das Produkt aller relevaten Multiplikationsfaktoren zur�ck
	 * 
	 * @return
	 */
	protected double getMulitplicationFactorsProduct() {
		double m1  = getM1();
		double m2  = getM2();
		double m3  = getM3();
		double m4  = getM4();
		double m5  = getM5();
		double m6  = getM6();
		double product = (m1 * m2 * m3 * m4 * m5 * m6);
		return product;
	}
	
	
	
	/**
	 * Gibt die Summe aller relevanten Additionszuschl�ge zur�ck
	 * 
	 * @return
	 */
	protected double getAdditionsSum() {
		double a1  = getA1();
		double a2  = getA2();
		double a3  = getA3();
		double sum = (a1 + a2 + a3);
		return sum;
	}
	
	
	
	
	/**
	 * Gibt den Multiplikationsfaktor f�r die Kronenl�nge zur�ck
	 * 
	 * @return
	 */
	protected double getM1() {
		return 1;
	}



	/**
	 * Gibt den Multiplikationsfaktor f�r den Anteil Schichtholz u. Industrieholz zur�ck
	 * 
	 * @return
	 */
	protected double getM2(){
		return 1;
	}
	
	
	
	
	/**
	 * Gibt den Multiplikationsfaktor f�r die St�ckl�nge zur�ck
	 * 
	 * @return
	 */
	protected double getM3() {
		return 1;
	}
	
	
	
	/**
	 * Gibt den Multiplikationsfaktor f�rs Kantenbrechen zur�ck
	 * 
	 * @return
	 */
	protected double getM4() {
		return 1;
	}
	
	
	
	/**
	 * Gibt den Multiplikationsfaktor f�r die Hangneigung zur�ck
	 * 
	 * @return
	 */
	protected double getM5() {
		return 1;
	}
	
	
	
	/**
	 * Gibt den Multiplikationsfaktor f�r Hindernisse zur�ck
	 * 
	 * @return
	 */
	protected double getM6() {
		switch (getArbeitsobjekt().getHindernisse()) {
		case Keine:
			return 1.00;
			
		case Gering:
			return 1.05;
			
		case Maessig:
			return 1.10;
			
		case Stark:
			return 1.20;
		
		}
		throw new RuntimeException();
	}
	
	
	
	/**
	 * Gibt den Additionszuschlag f�r Tannen zur�ck
	 * 
	 * @return
	 */
	protected double getA1() {
		return 0;
	}
	
	
	
	/**
	 * Gibt den Additionszuschlag f�rs F�llen mit Seilzug zur�ck
	 * 
	 * @return
	 */
	protected double getA2() {
		return 0;
	}
	
	
	
	/**
	 * Gibt den Additionszuschlag f�rs Spalten von durschnittlich z�hem Holz zur�ck
	 * 
	 * @return
	 */
	protected double getA3() {
		return 0;
	}
	
	
	
	
	
	/**
	 * Gibt den Umrechnungsfaktor Vol(ohne Rinde) -> Vol(in Rinde) 
	 * f�r die entsprechende Baumart zur�ck
	 * 
	 * @param baGruppe
	 * @return
	 */
	protected double getUmrechnungsfaktorVoR_ViR(Baumartgruppe baGruppe) {
		switch (baGruppe) {
		case Fichte:
		case Tanne:
			return 0.89;
			
		case Foehre_Laerche:
			return 0.92;
			
		case Laubholz:
			return 0.93;
		
		}
		throw new RuntimeException();
	}
	
	
	
	
	/**
	 * Gibt Koeffizienten [{c1,c2,c3,c4}] zur�ck
	 * 
	 * @param baGruppe
	 * @return
	 */
	protected abstract double[] getKoeffizienten(Baumartgruppe baGruppe);
	
	
}
