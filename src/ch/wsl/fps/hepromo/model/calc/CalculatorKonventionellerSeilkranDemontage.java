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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranDemontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorKonventionellerSeilkranDemontage extends CalculatorKonventionellerSeilkranMontageDemontage {

	public CalculatorKonventionellerSeilkranDemontage(HeProMoInputData inputData) {
		super(inputData);
	}
	

	@Override
	protected ArbeitsobjektKonventionellerSeilkranDemontage getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranDemontage) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected double getPsh0_Grundzeit() {
		double l = getArbeitsobjekt().getLinienLaenge_m();
		double anzPers = getAnzahlPersonenTotal();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (493 + 0.49 * l);
		
		return psh0;
	}
	

	@Override
	protected double getPsh0_EndmastenStuetzen() {
		double anzStuetzen = getArbeitsobjekt().getAnzahlEndmasten() + getArbeitsobjekt().getAnzahlStuetzen();
		double anzPers = getAnzahlPersonenTotal();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (100 * anzStuetzen);
		
		return psh0;
	}
	
	
	

	@Override
	protected double getPsh0_Verlegung() {
		double l = getArbeitsobjekt().getLinienLaenge_m();
		double psh0 = 0;
		double anzPers = getAnzahlPersonenTotal();
		
		if (getArbeitsobjekt().getWindenStandort() == WindenStandort.Bleibt) {
			psh0 = (1.0/60.0) * (1.0/anzPers) * (191.0 + 0.19 * l);
		}
		else if (getArbeitsobjekt().getWindenStandort() == WindenStandort.Wechselt) {
			psh0 = (1.0/60.0) * (1.0/anzPers) * (61.0 + 0.19 * l);
		}
		
		
		return psh0;
	}



	@Override
	protected boolean isSeilverlegung() {
		return getArbeitsobjekt().isDemontageIstSeilverlegung();
	}

}
