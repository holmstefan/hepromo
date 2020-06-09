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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorMobilseilkranSeilen extends AbstractCalculatorSingleModel {

	public CalculatorMobilseilkranSeilen(HeProMoInputData inputData) {
		super(inputData);
		
		f_indir = 1.0;
		
		//Bugfix: im alten HeProMo wurde im Teilmodell der falsche Faktor 1.050 verwendet.
//		f_stoer = 1.050; //sowohl in der doku als auch im alten code ist der wert 1.053 vermerkt. beim starten von hepromo wird jedoch aus der konfigurationsdatei der wert 1.050 eingelsen
		f_stoer = 1.053;
		
//		//f_verteilzeit = f_0bis15 * f_indir = 1.098
//		// -> da variable f_verteilzeit nicht vorhanden, wird
//		// f_0bis15 auf 1.098, und f_indir auf 1.0 gesetzt!
//		f_0bis15 = 1.098;
//		f_indir = 1.0;
	}
	
	
	@Override
	protected ArbeitsobjektMobilseilkranSeilen getArbeitsobjekt() {
		return (ArbeitsobjektMobilseilkranSeilen) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) super.getArbeitssystem();
	}
	
	

	@Override
	protected double calcPsh0_hProM3() {

		double psh0_Anhaengen 				 	 = getPsh0_Anhaengen();
		double psh0_SeitlicherZuzug 			 	 = getPsh0_SeitlicherZuzug();
		double psh0_VertikalbewegungImBestand 	 = getPsh0_VertikalbewegungImBestand();
		double psh0_FahrtAmTragseil		 	 	 = getPsh0_FahrtAmTragseil();
		double psh0_VertikalbewegungAmAbsenkplatz = getPsh0_VertikalbewegungAmAbsenkplatz();
		double psh0_Abhaengen 					 = getPsh0_Abhaengen();
		
		double psh0_SeilenLast =
				psh0_Anhaengen +
				psh0_SeitlicherZuzug +
				psh0_VertikalbewegungImBestand +
				psh0_FahrtAmTragseil +
				psh0_VertikalbewegungAmAbsenkplatz +
				psh0_Abhaengen;
		

		double stueckVolumen = getArbeitsobjekt().getMittleresStueckvolumen_m3();
		double fd = getArbeitsobjekt().getMittlereFahrdistanz_m();
		double lastVolumen = 0.691 + 0.668 * stueckVolumen + 0.0014 * fd;
		double psh0_Seilen = psh0_SeilenLast / lastVolumen;
		
		if (super.rindenAbzugBeruecksichtigen) {
			psh0_Seilen /= getArbeitsobjekt().getRindenAbzugFaktor();
		}
		
		
		return psh0_Seilen;
	}
	
	
	@Override
	public void setFaktorStoerzeiten(double value) {
		f_stoer = value;
	}
	
	
	
	
	private double getPsh0_Anhaengen() {
		double stueckVolumen = getArbeitsobjekt().getMittleresStueckvolumen_m3();
		double psh0 = (1.0/60.0) * (0.642 - 0.399 * Math.log(stueckVolumen)); 
		return psh0;
	}
	
	
	private double getPsh0_SeitlicherZuzug() {
		double a = -1;
		double b = -1;
		
		switch (getArbeitsobjekt().getSchwierigkeitSeitlicherZuzug()) {
		case Einfach:
			a = 0.883;
			b = 0.061;
			break;
			
		case Erschwert:
			a = 1.578;
			b = 0.068;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		double dz = getArbeitsobjekt().getMittlereDistanzSeitlicherZuzug_m();
		double psh0 = (1.0/60.0) * (a + b * dz);
		
		return psh0;
	}
	
	
	private double getPsh0_VertikalbewegungImBestand() {
		double psh0 = 0.0106;
		return psh0;
	}
	
	
	private double getPsh0_FahrtAmTragseil() {
		double fd = getArbeitsobjekt().getMittlereFahrdistanz_m();
		double psh0 = (1.0/60.0) * (1.0452 + 0.00934 * fd);
		
		return psh0;
	}
	
	
	private double getPsh0_VertikalbewegungAmAbsenkplatz() {
		double psh0 = 0.0151;
		
		//Wert ist unabhängig von Seilsystem -> Seilsystem entfernt
//		switch (getArbeitsobjekt().getSeilsystem()) {
//		case ZweiseilSystem:
//			psh0 = 0.0151;
//			break;
//			
//		case MehrseilSystem:
////			psh0 = 0.0054; //Variante Doku
//			psh0 = 0.0151; //Variante Code
//			break;
//			
//		default:
//			throw new RuntimeException();
//		}
		
		return psh0;
	}
	
	
	private double getPsh0_Abhaengen() {
		double stueckVolumen = getArbeitsobjekt().getMittleresStueckvolumen_m3();
		double psh0 = (1.0/60.0) * (0.266 * Math.pow(stueckVolumen, -0.69)); 
		return psh0;
	}	
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return getArbeitssystem().getAnzahlPersonal();
	}
	
	

	@Override
	protected double getLaufzeitAnteilMaschine() {
		return getArbeitssystem().getAnteilSeilkranLaufzeit_Prz() / 100.0;
	}

}
