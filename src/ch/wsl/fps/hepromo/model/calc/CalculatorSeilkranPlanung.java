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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranPlanung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorSeilkranPlanung extends AbstractCalculatorSingleModel {

	
	
	public CalculatorSeilkranPlanung(HeProMoInputData inputData) {
		super(inputData);
	}

	

	@Override
	public ArbeitsobjektSeilkranPlanung getArbeitsobjekt() {
		return (ArbeitsobjektSeilkranPlanung) super.getArbeitsobjekt();
	}
	
	
	
	@Override
	protected double calcPsh0_hProM3() {
		
		double psh15 = getPsh15_Projektierung();
		double psh0 = psh15 / f_0bis15;
		
		psh0 /= getRelevanteHolzmenge(); //Spezialfall, da Gesamtzeit unabhängig von Holzmenge ist!

		return psh0;
	}

	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		if (getArbeitsobjekt().isLinieAbsteckenOhneProjekt()) {
			return 2;
		}
		else {
			/* Hier wird eine Mischrechnung gemacht, da für Abstecken und Profilaufnahme zwei Personen 
			 * im Einsatz sind, für das Ausarbeiten des Projekts jedoch nur eine.
			 *
			 * Dadurch, dass beim Ausarbeiten nur noch eine Person im Einsatz ist (anstatt zwei), ist die 
			 * Dauer der Arbeit etwas länger, der Zeitaufwand Personal sowie Kosten Personal bleiben jedoch gleich. 
			 */
			
			double psh15_Abstecken = getPsh15_Abstecken(); // 2 Personen
			double psh15_ProfilAufnehmen = getPsh15_ProfilAufnehmen(); // 2 Personen
			double psh15_ProjektAusarbeiten = getPsh15_ProjektAusarbeiten(); // 1 Person
			
			double psh15_Gesamt = psh15_Abstecken + psh15_ProfilAufnehmen + psh15_ProjektAusarbeiten;	
			
			double anzPers_Mischrechnung = ((psh15_Abstecken + psh15_ProfilAufnehmen) / psh15_Gesamt) * 2 + ((psh15_ProjektAusarbeiten) / psh15_Gesamt) * 1;
			return anzPers_Mischrechnung;
		}
	}

	
	
	private double getPsh15_Projektierung() {
		double psh15 = getPsh15_Abstecken();
		
		if (getArbeitsobjekt().isLinieAbsteckenOhneProjekt() == false) {
			psh15 += getPsh15_ProfilAufnehmen() + getPsh15_ProjektAusarbeiten();
		}
		
		return psh15;
	}
	
	
	
	
	private double getPsh15_Abstecken() {
		double l = getArbeitsobjekt().getLinienlaenge_m();
		double psh15 = (1.0/60.0) * (1.0/2.0) * (50.0 + 0.6 * l);
		return psh15;
	}
	
	
	
	private double getPsh15_ProfilAufnehmen() {
		double l = getArbeitsobjekt().getLinienlaenge_m();
		double psh15 = (1.0/60.0) * (1.0/2.0) * (0.4 * l);
		return psh15;
	}
	
	
	
	private double getPsh15_ProjektAusarbeiten() {
		double l = getArbeitsobjekt().getLinienlaenge_m();
		double psh15 = (1.0/60.0) /* * (1.0/2.0)*/ * (60.0 + 0.2 * l); //wird von einer einzelnen Person erledigt
		return psh15;
	}
	
	

	@Override
	protected double getLaufzeitAnteilMaschine() {
		return 0;
	}

}
