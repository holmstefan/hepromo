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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckeart;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorSchlepper extends AbstractCalculatorSingleModel {

	double anzahlSchlepper = 1;
	
	
	public CalculatorSchlepper(HeProMoInputData inputData) {
		super(inputData);
	}

	
	
	@Override
	public ArbeitsobjektSchlepper getArbeitsobjekt() {
		return (ArbeitsobjektSchlepper) super.getArbeitsobjekt();
	}
	
	@Override
	public ArbeitssystemSchlepper getArbeitssystem() {
		return (ArbeitssystemSchlepper) super.getArbeitssystem();
	}
	
	

	
	@Override
	protected double calcPsh0_hProM3() {
		
		//Konsistenz-Check
		if (getArbeitssystem().isRueckegehilfe() == false && getArbeitssystem().getRueckgehilfeEinsatzanteil().getRueckgehilfeEinsatzZeitFaktor() > 0) {
			throw new RuntimeException("Ungültiger Rückegehilfe-Einsatzanteil");
		}
		
		
		double a1 = -1.06866;
		double a2 = 19.04929;
		double v0 =  0.704;
		double p  =  0.85735;
		
		double vMit = getArbeitsobjekt().getMittlererStueckInhalt(); //Achtung: bei motormanuell vMit = massenmittelstamm?
		
		
		double grundproduktivitaet = a2 + (a1-a2) / (1 + Math.pow(vMit / v0, p));
		

		
//		double grundeffizienz = 1 / grundproduktivitaet;
		
		double produktivitaetsZuschlag = getProductivityAdditionsSum();

		double pmh15_oR = 1 / ((1 + produktivitaetsZuschlag) * grundproduktivitaet);
		
//		System.out.println(pmh15_oR);
		
		double psh15_oR = pmh15_oR / (anzahlSchlepper * getLaufzeitAnteilMaschine());
		
		double psh0_oR = psh15_oR / f_0bis15;
		
		
//		System.out.println(psh0_oR);
		
		

		
		
		
		
		return psh0_oR;
	}
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		if (getArbeitssystem().isRueckegehilfe() == true) {
			return 1.0 + getArbeitssystem().getRueckgehilfeEinsatzanteil().getRueckgehilfeEinsatzZeitFaktor();
		}
		return 1;
	}
	
	
	
	private double getProductivityAdditionsSum() {
		double m0  = getM0();
		double m1  = getM1();
//		double m2  = getM2();
		double m3  = getM3();
		double m4  = getM4();
//		double m5  = getM5();
		double m6  = getM6();
		double m7  = getM7();
		double m8  = getM8();
		double m9  = getM9();
		double m10 = getM10();
		double sum = (m0 + m1 /*+ m2*/ + m3 + m4 /*+ m5*/ + m6 + m7 + m8 + m9 + m10 );
		return sum;
	}
	
	
	
	
	private double getM0() {
		switch (getArbeitsobjekt().getRueckeart()) {
		case NurVorruecken:
			return 0.7; //(double) Math.pow(0.7, 2);
			
		case NurFertigruecken:
			return 0.05;
			
		case GesamtesRuecken:
			return 0.0;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM1() {
		switch (getArbeitssystem().getSchlepperTyp()) {
		case Forstspezialschlepper:
			if (getArbeitsobjekt().getMittlererStueckInhalt() > 0.5) {
				return 0.1;
			} else {
				return 0.0;
			}
			
		case AndereSchleppertypen:
			return 0.0;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM3() {
		if (getArbeitsobjekt().getRueckeart() == Rueckeart.NurVorruecken) {
			return 0.0;
		}
		
		switch (getArbeitsobjekt().getFahrentfernung()) {
		case Bis200m:
			return 0.0;
			
		case Ab200bis400m:
			return -0.1;
			
		case Ueber400m:
			return -0.2;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM4() {
		if (getArbeitsobjekt().getRueckeart() == Rueckeart.NurFertigruecken) {
			return 0.0;
		}
		
		switch (getArbeitsobjekt().getZuzugentfernung()) {
		case Bis20m:
			return 0.0;
			
		case Ab20bis30m:
		case Ab30bis40m:
			return -0.1;
			
		case Ueber40m:
			return -0.15;
			
		}
		throw new RuntimeException();
	}

	
	
	
	private double getM6() {
		switch (getArbeitssystem().getRueckgehilfeEinsatzanteil()) {
		case Unbekannt:
		case Prz_0:
		case Prz_1bis25:
		case Prz_26bis50:
			return 0.0;
			
		case Prz_51bis75:
		case Prz_76bis100:
			return 0.15;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM7() {
		switch (getArbeitsobjekt().getRueckenImSaft()) {
		case Ja:
			return -0.1;
			
		case Nein:
			return 0.0;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM8() {
		switch (getArbeitsobjekt().getSchlagordnung()) {
		case MehrAlsEinViertelNichtEingehalten:
			return -0.1;
			
		case WenigerAlsEinViertelNichtEingehalten:
			return 0.0;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM9() {
		switch (getArbeitsobjekt().getAnzahlSortimente()) {
		case WenigerAlsVier:
			return 0.0;
			
		case VierBisSechs:
			return -0.05;
			
		case MehrAlsSechs:
			return -0.1;
			
		}
		throw new RuntimeException();
	}
	
	
	
	
	private double getM10() {
		switch (getArbeitsobjekt().getRueckeBedingungen()) {
		case SehrEinfach:
			return 0.1;
			
		case Einfach:
			return 0.05;
			
		case Mittel:
			return 0.0;
			
		case Schwierig:
			return -0.05;
			
		case SehrSchwierig:
			return -0.1;
			
		}
		throw new RuntimeException();
	}



	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 1;
	}
	
	

}
