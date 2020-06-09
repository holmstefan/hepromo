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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class CalculatorKonventionellerSeilkranMontageDemontage extends AbstractCalculatorSingleModel {

	public CalculatorKonventionellerSeilkranMontageDemontage(HeProMoInputData inputData) {
		super(inputData);
		f_indir = 1.39; //gemäss altem code. laut doku (f_0bis15 * f_indir) = 1.53 -> stimmt, falls f_0bis15 == 0.1
	}
	
	
	@Override
	protected ArbeitsobjektKonventionellerSeilkranMontageDemontage getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranMontageDemontage) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) super.getArbeitssystem();
	}
	
	
	
	protected abstract boolean isSeilverlegung();
	
	

	@Override
	protected double calcPsh0_hProM3() {
		
		double psh0_Grundzeit 			= getPsh0_Grundzeit();
		double psh0_EndmastenStützen 	= getPsh0_EndmastenStuetzen();
		double psh0_Windenselbstfahrt 	= getPsh0_Windenselbstfahrt();
		double psh0_WindenTransport_Heli = getPsh0_WindenTransport_Heli();
		double psh0_Verlegung			= getPsh0_Verlegung();
		
		
		double psh0_MontageDemontage = -1;
		
		if (isSeilverlegung() == true) {
			psh0_MontageDemontage = 
					psh0_Grundzeit +
					psh0_EndmastenStützen -
					psh0_Verlegung;
		}
		else {
			psh0_MontageDemontage = 
					psh0_Grundzeit +
					psh0_EndmastenStützen +
					psh0_Windenselbstfahrt +
					psh0_WindenTransport_Heli;
		}
		
		
		psh0_MontageDemontage /= getRelevanteHolzmenge(); //Spezialfall, da Gesamtzeit unabhängig von Holzmenge ist!
		
		
		return psh0_MontageDemontage;
	}

	
	
	
	
	protected abstract double getPsh0_Grundzeit();
	
	
	protected abstract double getPsh0_EndmastenStuetzen();
	

	
	protected double getPsh0_Windenselbstfahrt() {
		
		double wsd = 0;
		if (getArbeitsobjekt().getDistanzWindenSelbstfahrt() >= 150) {
			wsd = getArbeitsobjekt().getDistanzWindenSelbstfahrt() - 150;
		}
		
		double psh0 = 0;
		double anzPers = getAnzahlPersonenTotal();
		
		if (getArbeitsobjekt().getWindenTransport() == WindenTransport.SelbstfahrtBergauf) {
			psh0 = (1.0/60.0) * (1.0/anzPers) * (177 + 0.76 * wsd);
		}
		else if (getArbeitsobjekt().getWindenTransport() == WindenTransport.SelbstfahrtBergab) {
			psh0 = (1.0/60.0) * (1.0/anzPers) * (179 + 1.21 * wsd);
		}
		
		
		return psh0;
	}
	
	
	
	
	protected double getPsh0_WindenTransport_Heli() {
		double psh0 = 0;
		double anzPers = getAnzahlPersonenTotal();
		
		if (getArbeitsobjekt().getWindenTransport() == WindenTransport.MitHeli) {
			psh0 = (1.0/60.0) * (1.0/anzPers) * (328.0);
		}
		
		
		return psh0;
	}
	
	
	
	
	protected abstract double getPsh0_Verlegung();
	

	protected double getAnzahlPersonenTotal() {
		return getArbeitssystem().getAnzahlPersonal();
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
