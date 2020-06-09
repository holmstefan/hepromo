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
import ch.wsl.fps.hepromo.model.ErgebnisHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHackschnitzelTransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorHackschnitzelTransport2018 extends AbstractCalculatorSingleModel2014 {

	public CalculatorHackschnitzelTransport2018(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	protected Ergebnis getNewErgebnisInstance() {
		return new ErgebnisHackschnitzelTransport2018();
	}
	
	
	@Override
	public ArbeitsobjektHackschnitzelTransport2018 getArbeitsobjekt() {
		return (ArbeitsobjektHackschnitzelTransport2018) super.getArbeitsobjekt();
	}
	
	
	@Override
	public ArbeitssystemHackschnitzelTransport2018 getArbeitssystem() {
		return (ArbeitssystemHackschnitzelTransport2018) super.getArbeitssystem();
	}
	

	@Override
	public Ergebnis calculate() {
		ErgebnisHackschnitzelTransport2018 ergebnis = (ErgebnisHackschnitzelTransport2018) super.calculate();

		ergebnis.setZeitLaden_h(	 calcZeitLaden_min()	 / 60 );
		ergebnis.setZeitLastfahrt_h( calcZeitLastfahrt_min() / 60 );
		ergebnis.setZeitEntladen_h(	 calcZeitEntladen_min()	 / 60 );
		ergebnis.setZeitLeerfahrt_h( calcZeitLeerfahrt_min() / 60 );
		
		ergebnis.setAnzahlZyklen( calcAnzahlZyklen() );
		
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
		
		double zeitLaden_min = calcZeitLaden_min();
		double zeitLastfahrt_min = calcZeitLastfahrt_min();
		double zeitEntladen_min = calcZeitEntladen_min();
		double zeitLeerfahrt_min = calcZeitLeerfahrt_min();
		
		double zeitTotal_min = zeitLaden_min + zeitLastfahrt_min + zeitEntladen_min + zeitLeerfahrt_min;
		double zeitTotal_h = zeitTotal_min / 60;
		
		int anzahlZyklen = calcAnzahlZyklen();
		
		double avgSrmProFahrt = getArbeitsobjekt().getHolzmenge_m3() / anzahlZyklen;
		double produktivitaetProFahrt_SrmProPMH15 = avgSrmProFahrt / zeitTotal_h;

		double pmh15 = 1 / produktivitaetProFahrt_SrmProPMH15;
		double pmh0 = pmh15 / f_0bis15;
		
		return pmh0;
	}
	
	
	private int calcAnzahlZyklen() {
		double muldeninhalt_Srm = getArbeitssystem().getMuldeninhalt_Srm();
		return (int) Math.ceil(getArbeitsobjekt().getHolzmenge_m3() / muldeninhalt_Srm);
	}
	
	
	
	private  double calcZeitLaden_min() {
		if (getArbeitsobjekt().isAufnehmenBeladenerMulde() == true) {
			return 10;
		}
		else {
			if (getArbeitsobjekt().getHolzmenge_m3() > 0) {
				Zielsortiment zielsortiment = getArbeitsobjekt().getZielsortiment();
				int motorleistungHacker_kW = getArbeitsobjekt().getHackerMotorleistung().getMotorleistungCalc_Kw();

				double produktivitaet_SrmProPMH15 = CalculatorHacker2018.getProduktivitaet_SrmProMAS(zielsortiment, motorleistungHacker_kW);

				double zeit_min = (getArbeitsobjekt().getHolzmenge_m3() / produktivitaet_SrmProPMH15) * 60;

				zeit_min /= calcAnzahlZyklen();

				return zeit_min;
			}
			else {
				return 0;
			}
		}
	}
	
	private double calcZeitLastfahrt_min() {
		double fahrzeitWaldstrasse_min = getArbeitsobjekt().getDistanzWaldstrasse_km() * (60.0 / 20);
		double fahrzeitInnerAusserorts_min = getArbeitsobjekt().getDistanzInnerAusserorts_km() * (60.0 / 40);
		double fahrzeitAutobahn_min = getArbeitsobjekt().getDistanzAutobahn_km() * 0.86 /*(60.0 / 70)*/; //in Testdaten wird der gerundete Faktor 0.86 verwendet
		
		double sum_min = fahrzeitWaldstrasse_min + fahrzeitInnerAusserorts_min + fahrzeitAutobahn_min;
		
		return sum_min;
	}
	
	
	private double calcZeitEntladen_min() {
		return 15;
	}
	
	
	private double calcZeitLeerfahrt_min() {
		return calcZeitLastfahrt_min() * 0.95;
	}

}
