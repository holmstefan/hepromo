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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterAufarbeiten;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorHelikopterAufarbeiten extends AbstractCalculatorSingleModel {

	public CalculatorHelikopterAufarbeiten(HeProMoInputData inputData) {
		super(inputData);
	}


	
	@Override
	protected ArbeitsobjektHelikopterAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektHelikopterAufarbeiten) super.getArbeitsobjekt();
	}

	
	@Override
	protected ArbeitssystemHelikopterAufarbeiten getArbeitssystem() {
		return (ArbeitssystemHelikopterAufarbeiten) super.getArbeitssystem();
	}
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return getArbeitssystem().getAnzahlPersonal1();
	}
	

	
	
	@Override
	public Ergebnis calculate() {

		Ergebnis e = super.calculate();
		
		//setze ergebnis für 2.Maschine (Kranfahrzeug) // 1. Maschine == Motorsäge
		double psh0_total = e.getPsh0_total();
		int anzahl_maschinen = getArbeitssystem().getAnzahlMaschine2();
		double maschinen_laufzeitanteil = 1.0;
		double pmh0 = anzahl_maschinen * psh0_total * maschinen_laufzeitanteil;
		double pmh15 = pmh0 * f_0bis15;
		
		//Betriebsspezifischer Korrekturfaktor
		double kf = super.faktoren.getKorrekturFaktor();
		pmh15  *= kf;
		
		e.setZeitMaschine2(pmh15);
		e.setKostenMaschine2_total(getArbeitssystem().getKostensatzMaschine2_proH() * e.getZeitMaschine2());

		
		return e;
	}
	
	
	
	

	@Override
	protected double calcPsh0_hProM3() {

		double psh0_lagerplatz = getPsh0Lagerplatz();

		double UNBEKANNTER_KORREKTURFAKTOR = 1.0/1.1;
		psh0_lagerplatz *= UNBEKANNTER_KORREKTURFAKTOR; //In Diff-Doku vermerkt, möglicherweise Rindenabzugsfaktor

		if (super.rindenAbzugBeruecksichtigen) {
			psh0_lagerplatz /= getArbeitsobjekt().getRindenAbzugFaktor();
		}
		
		return psh0_lagerplatz;
	}

	
	
	@Override
	protected double getLaufzeitAnteilMaschine() { //Laufzeitanteil Maschine1 == Motorsäge
		if (getArbeitsobjekt().getArbeitsVerfahren() == ArbeitsVerfahren.Sortimentsverfahren) {
			return 0.5; 
		}
		else {
			return 1.0;
		}
	}
	

	@Override
	protected int getAnzahlMaschinen() {
		return getArbeitssystem().getAnzahlMaschine1();
	}
	
	
	
	private double getPsh0Lagerplatz() {
		double result = 0;
		
		switch (getArbeitsobjekt().getArbeitsVerfahren()) {
		case Sortimentsverfahren:
			result = 0.05;
			break;
			
		case Vollbaumverfahren:
			switch (getArbeitsobjekt().getBaumartenGruppe()) {
			
			case Fichte:
			case Tanne:
//				result = 0.059;
				result = 1 / 17.0;
				break;
				
			case Foehre_Laerche:
//				result = 0.05;
				result = 1 / 20.0;
				break;
				
			case Laubholz:
//				result = 0.045;
				result = 1 / 22.0;
				break;
			}
		}
		
		
		return result;
	}

	

}
