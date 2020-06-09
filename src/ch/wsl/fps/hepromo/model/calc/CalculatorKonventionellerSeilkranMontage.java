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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorKonventionellerSeilkranMontage extends CalculatorKonventionellerSeilkranMontageDemontage {

	public CalculatorKonventionellerSeilkranMontage(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	protected ArbeitsobjektKonventionellerSeilkranMontage getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranMontage) super.getArbeitsobjekt();
	}
	
	
	
	@Override
	protected double getPsh0_Grundzeit() {
		double l = getArbeitsobjekt().getLinienLaenge_m();
		double anzPers = getAnzahlPersonenTotal();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (1181 + 0.91 * l);
//		double psh0 = (1.0/60.0) * (1.0/4.0) * (1158 + 1.0 * l); //Diese formel wird in der doku verwendet. vgl hinweis im code X_D.vb toter-mann-anker!
		
		return psh0;
	}
	
	
	
	@Override
	protected double getPsh0_EndmastenStuetzen() {
		double anzStuetzen = getArbeitsobjekt().getAnzahlEndmasten() + getArbeitsobjekt().getAnzahlStuetzen();
		double anzPers = getAnzahlPersonenTotal();
//		anzStuetzen = 1.34 + 0.00148 * getArbeitsobjekt().getLinienLaenge_m();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (300 * anzStuetzen);
		
		return psh0;
	}
	
	
	
	
	@Override
	protected double getPsh0_Verlegung() {
		double psh0 = 0;
		double anzPers = getAnzahlPersonenTotal();
		
		if (getArbeitsobjekt().getWindenStandort() == WindenStandort.Bleibt) {
			psh0 = (1.0/60.0) * (1.0/anzPers) * (470.0);
		}
		
		
		return psh0;
	}


	@Override
	protected boolean isSeilverlegung() {
		return getArbeitsobjekt().isMontageIstSeilverlegung();
	}

}
