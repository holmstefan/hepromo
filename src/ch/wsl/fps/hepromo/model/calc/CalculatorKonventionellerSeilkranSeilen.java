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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorKonventionellerSeilkranSeilen extends AbstractCalculatorSingleModel {

	public CalculatorKonventionellerSeilkranSeilen(HeProMoInputData inputData) {
		super(inputData);
		
		f_indir = 1.0;
		
		//Bugfix: im alten HeProMo wurde im Teilmodell der falsche Faktor 1.020 verwendet.
//		f_stoer = 1.020; //in doku steht wert 1.022! -> 1.022 gilt nur bei gesamtmodell
		f_stoer = 1.022;
	}
	
	
	@Override
	protected ArbeitsobjektKonventionellerSeilkranSeilen getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranSeilen) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) super.getArbeitssystem();
	}
	
	

	@Override
	protected double calcPsh0_hProM3() {

		double psh0_Anhaengen 				 	 = getPsh0_Anhaengen();
		double psh0_SeitlicherZuzug 			 = getPsh0_SeitlicherZuzug();
		double psh0_VertikalbewegungImBestand 	 = getPsh0_VertikalbewegungImBestand();
		double psh0_FahrtAmTragseil		 	 	 = getPsh0_FahrtAmTragseil();
		double psh0_VertikalbewegungAmLagerplatz = getPsh0_VertikalbewegungAmLagerplatz();
		double psh0_Abhaengen 					 = getPsh0_Abhaengen();
		
		double psh0_SeilenLast =
				psh0_Anhaengen +
				psh0_SeitlicherZuzug +
				psh0_VertikalbewegungImBestand +
				psh0_FahrtAmTragseil +
				psh0_VertikalbewegungAmLagerplatz +
				psh0_Abhaengen;
		
		double lastVolumen = getLastVolumen();
		double psh0_Seilen = psh0_SeilenLast / lastVolumen;
		
		if (super.rindenAbzugBeruecksichtigen) {
			psh0_Seilen /= getArbeitsobjekt().getRindenAbzugFaktor();
		}
		
		
		return psh0_Seilen;
	}

	
	
	
	private double getLastVolumen() {
		double lastVolumen = 0;		
		double stueckVolumen = getArbeitsobjekt().getMittleresStueckvolumen_m3();
		
		if (getArbeitsobjekt().getHolzSeilOrt() == HolzSeilOrt.AusSchlagflaeche) {
			double stueckLaenge = getArbeitsobjekt().getStuecklaenge_m();
			double fd = getArbeitsobjekt().getMittlereFahrdistanz_m();
			lastVolumen = 0.825 - 0.017 * stueckLaenge + 0.395 * Math.log(stueckVolumen) + 0.150 * Math.log(fd);
		}
		else {
			lastVolumen = 1.843 + 0.216 * Math.log(stueckVolumen);
		}
		
		return lastVolumen;
	}
	
	
	
	
	
	private double getPsh0_Anhaengen() {
		double psh0 = 0;
		
		double lastVolumen = getLastVolumen();
		double stueckVolumen = getArbeitsobjekt().getMittleresStueckvolumen_m3();
		double hangneigung = getArbeitsobjekt().getHangneigung_Prz() / 100.0;
		double seitlicherZuzug = getArbeitsobjekt().getMittlereDistanzSeitlicherZuzug_m();
		int anzahlPersonenAnhaengen = getArbeitssystem().getAnzahlPersonal() - 1;
		if (anzahlPersonenAnhaengen < 1) {
			throw new RuntimeException("zuwenig Personen");
		}
		
		if (anzahlPersonenAnhaengen == 1) { // 1 person
			psh0 = (1.0/60.0) * ((0.01 * seitlicherZuzug + 0.45 * hangneigung) / stueckVolumen + 0.32) * lastVolumen;
		}
		else { // 2 oder mehr personen
			psh0 = (1.0/60.0) * ((0.45 * hangneigung) / stueckVolumen + 0.32) * lastVolumen;
		}
		
		
		return psh0;
	}

	
	

	private double getPsh0_SeitlicherZuzug() {
		double psh0 = 0;
		
		double seitlicherZuzug = getArbeitsobjekt().getMittlereDistanzSeitlicherZuzug_m();
		double hangneigung = getArbeitsobjekt().getHangneigung_Prz() / 100.0;
		int eingriffsArt = getEingriffsArtValue();
		int hindernisse = getHindernisseValue();
		
		psh0 = (1.0/60.0) * (0.081 * seitlicherZuzug + 0.061 * hangneigung - 0.034 * eingriffsArt + 0.041 * hindernisse);
		
		return psh0;
	}

	
	
	private int getEingriffsArtValue() {
		switch (getArbeitsobjekt().getEingriffsart()) {
		case Durchforstung:
			return 1;
		case Lichtung:
			return 2;
		case Raeumung:
			return 3;
		default:
			throw new RuntimeException();
		}
	}
	
	
	private int getHindernisseValue() {
		switch (getArbeitsobjekt().getHindernisse()) {
		case Normal:
			return 1;
		case Erschwert:
			return 2;
		case Extrem:
			return 3;
		default:
			throw new RuntimeException();
		}
	}

	

	private double getPsh0_VertikalbewegungImBestand() {
		double tragseilHoehe = getArbeitsobjekt().getTragseilHoeheBestand_m();
		double psh0 = (1.0/60.0) * (0.258 + 0.025 * tragseilHoehe);
		return psh0;
	}


	
	
	private double getPsh0_FahrtAmTragseil() {
		double psh0;
		
		double fahrdistanz = getArbeitsobjekt().getMittlereFahrdistanz_m();
		
		switch (getArbeitsobjekt().getFahrtrichtung() ) {
		case Bergauf:
			psh0 = (1.0/60.0) * (0.861 + 0.01151 * fahrdistanz);
			break;
		case Bergab:
			psh0 = (1.0/60.0) * (1.064 + 0.00792 * fahrdistanz);
			break;
		default:
			throw new RuntimeException();
		}

		return psh0;
	}

	
	

	private double getPsh0_VertikalbewegungAmLagerplatz() {
		double tragseilHoehe = getArbeitsobjekt().getTragseilHoeheLagerplatz_m();
		double psh0 = (1.0/60.0) * (0.370 + 0.036 * tragseilHoehe);
		return psh0;
	}


	
	
	private double getPsh0_Abhaengen() {
		double psh0 = 0;
		
		double lastVolumen = getLastVolumen();
		double stueckVolumen = getArbeitsobjekt().getMittleresStueckvolumen_m3();
		
		psh0 = (1.0/60.0) * (0.144/stueckVolumen + 0.30) * lastVolumen;
		
		
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
