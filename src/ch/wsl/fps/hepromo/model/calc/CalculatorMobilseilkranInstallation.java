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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorMobilseilkranInstallation extends AbstractCalculatorSingleModel {

	public CalculatorMobilseilkranInstallation(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	protected ArbeitsobjektMobilseilkranInstallation getArbeitsobjekt() {
		return (ArbeitsobjektMobilseilkranInstallation) super.getArbeitsobjekt();
	}

	
	@Override
	protected ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) super.getArbeitssystem();
	}
	
	
	
	@Override
	protected double calcPsh0_hProM3() {

		double psh0_Grundzeit 			= getPsh0_Grundzeit();
		double psh0_Endmasten 			= getPsh0_Endmasten();
		double psh0_Stuetzen 			= getPsh0_Stuetzen();
		double psh0_Mehrbaumanker 		= getPsh0_Mehrbaumanker();
		double psh0_MaschinenStandortOben= getPsh0_MaschinenStandortOben();
		
		double psh0_Installation =
				psh0_Grundzeit +
				psh0_Endmasten +
				psh0_Stuetzen +
				psh0_Mehrbaumanker -
				psh0_MaschinenStandortOben;
		
		
//		f_stoer = 1.021; //Variante doku
		f_stoer = 1; //Variante Code
		f_0bis15 = 1.1;
		f_indir = 1.5; // -> f_verteilzeit (siehe doku) = f_0bis15 * f_indir = 1.65
		
		
		psh0_Installation /= getRelevanteHolzmenge(); //Spezialfall, da Gesamtzeit unabhängig von Holzmenge ist!
		
		
		return psh0_Installation;
	}
	
	
	
	
	
	private double getPsh0_Grundzeit() {
		double a = -1;
		double b = -1;
		
		switch (getArbeitsobjekt().getSeilsystem()) {
		case ZweiseilSystem:
			a = 497;
			b = 0.577;
			break;
			
		case MehrseilSystem:
			a = 281;
			b = 2.332;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		double linienLaenge = getArbeitsobjekt().getLinienLaenge_m();
		double anzPers = getArbeitssystem().getAnzahlPersonal();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (a + b * linienLaenge);
		
		return psh0;
	}
	
	
	private double getPsh0_Endmasten() {
		if (getArbeitsobjekt().isEndmast() == false) {
			return 0;
		}
		
		double tragseilHoehe = getArbeitsobjekt().getTragseilHoeheEndmast();
		double anzPers = getArbeitssystem().getAnzahlPersonal();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (-1.0 + 19.57 * tragseilHoehe);
		
		return psh0;
	}
	
	
	private double getPsh0_Stuetzen() {
		double psh0 = 0;
		double anzPers = getArbeitssystem().getAnzahlPersonal();
		
		double anzahlStuetzen = getArbeitsobjekt().getStuetzenTragseilHoehen().size();
		for (int i=0; i<anzahlStuetzen; i++) {
			double tragseilHoehe = getArbeitsobjekt().getStuetzenTragseilHoehen().get(i);
			psh0 += (1.0/60.0) * (1.0/anzPers) * (177.0 + 10.5 * tragseilHoehe);
		}
		
		return psh0;
	}
	
	
	private double getPsh0_Mehrbaumanker() {
		double linienLaenge = getArbeitsobjekt().getLinienLaenge_m();
		if (linienLaenge < 150) {
			linienLaenge = 150;
		}

		double anzPers = getArbeitssystem().getAnzahlPersonal();
		double psh0 = (1.0/60.0) * (1.0/anzPers) * (-17.0 + 0.149 * linienLaenge);
		
		return psh0;
	}
	
	
	private double getPsh0_MaschinenStandortOben() {
		double psh0 = 0;
		
//		if (getArbeitsobjekt().getSeilsystem() == Seilsystem.ZweiseilSystem ||
//				getArbeitsobjekt().getMaschinenStandort() == MaschinenStandort.Unten) {
//			return 0;
//		}
		
		if (getArbeitsobjekt().getSeilsystem() == Seilsystem.MehrseilSystem &&
				getArbeitsobjekt().getMaschinenStandort() == MaschinenStandort.Oben) {
			
			double linienLaenge = getArbeitsobjekt().getLinienLaenge_m();
			double anzPers = getArbeitssystem().getAnzahlPersonal();
			psh0 = (1.0/60.0) * (1.0/anzPers) * (8.0 + 0.08 * linienLaenge);
		}

		
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
