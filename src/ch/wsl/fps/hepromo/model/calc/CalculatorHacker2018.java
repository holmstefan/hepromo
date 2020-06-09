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
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorHacker2018 extends AbstractCalculatorSingleModel2014 {
	
//	public static final double m3toSrm = 2.8;

	public CalculatorHacker2018(HeProMoInputData inputData) {
		super(inputData);	
		f_indir = 1.5;
	}
	
	
	@Override
	public ArbeitsobjektHacker2018 getArbeitsobjekt() {
		return (ArbeitsobjektHacker2018) super.getArbeitsobjekt();
	}
	
	
	@Override
	public ArbeitssystemHacker2018 getArbeitssystem() {
		return (ArbeitssystemHacker2018) super.getArbeitssystem();
	}
	
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		
		setProduktivitaet(ergebnis, ProdEinheit.SRM_PRO_PMH15, ergebnis.getProduktivitaet(ProdEinheit.M3_PRO_PSH15));
		
		return ergebnis;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		return ProdEinheit.SRM_PRO_PMH15;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit2() {
		return ProdEinheit.EMPTY;
	}

	
	@Override
	protected double calcPsh0_hProM3() {
		
		double produktivitaet_SrmProMAS = getProduktivitaet_SrmProMAS();
		
		double pmh15_Hacken_hProSrm = 1.0 / produktivitaet_SrmProMAS;
		
//		double wartezeit_hProSrm = 0.5 * pmh15_Hacken_hProSrm; //ENTFERNT: enstpricht f_indir!
		
		double pmh15_Gesamt_hProSrm = pmh15_Hacken_hProSrm /*+ wartezeit_hProSrm*/;
		
		double pmh0_Gesamt_hProSrm = pmh15_Gesamt_hProSrm / f_0bis15;

		return pmh0_Gesamt_hProSrm;
	}

	
	private double getProduktivitaet_SrmProMAS() {
		Zielsortiment zielsortiment = getArbeitsobjekt().getZielsortiment();
		int motorleistungKw = getArbeitssystem().getHackerMotorleistung().getMotorleistungCalc_Kw();
		
		double result = getProduktivitaet_SrmProMAS(zielsortiment, motorleistungKw);
		return result;
	}
	
	
	public static final double getProduktivitaet_SrmProMAS(Zielsortiment zielsortiment, double motorleistungKw) {
		switch(zielsortiment) {
		case Energierundholz:
			return 0.2848 * Math.pow(motorleistungKw, 1.0276);
			
		case Waldrestholz:
			return 0.5177 * Math.pow(motorleistungKw, 0.8486);
			
		default:
			throw new RuntimeException();
		}
	}
	
	
	public static final double convertFromCHFproSrmToCHFproPMH15(double CHFproSrm, Zielsortiment zielsortiment, double motorleistungKw) {
		double produktivitaet_SrmProPMH15 = getProduktivitaet_SrmProMAS(zielsortiment, motorleistungKw);
		double CHFproPMH15 = CHFproSrm * produktivitaet_SrmProPMH15;
		return CHFproPMH15;
	}
	
	public static final double convertFromCHFproPMH15ToCHFproSrm(double CHFproPMH15, Zielsortiment zielsortiment, double motorleistungKw) {
		double produktivitaet_SrmProPMH15 = getProduktivitaet_SrmProMAS(zielsortiment, motorleistungKw);
		double CHFproSrm = CHFproPMH15 / produktivitaet_SrmProPMH15;
		return CHFproSrm;
	}
}
