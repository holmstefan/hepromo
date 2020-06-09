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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014.Region;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMotormanuell2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorMotormanuellGesamt2014 extends AbstractCalculatorSingleModel2014 {
	
	
	public CalculatorMotormanuellGesamt2014(HeProMoInputData inputData) {
		super(inputData);
		f_indir = 1.4;
	}

	
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();

		if (ergebnis.getAnzahl_m3() > 0) {
			//Produktivität in Motormanuell2014 WPSH
			double prod_wpph = ergebnis.getProduktivitaet_fmORproWPPH();
			double prod_wpsh = prod_wpph * getAnzahlPersonalVollzeitAequivalente();
			if (rindenAbzugBeruecksichtigen) {
				setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_WPSH, prod_wpsh / getArbeitsobjekt().getRindenAbzugFaktor());
			}
			setProduktivitaet(ergebnis, ProdEinheit.M3_OR_PRO_WPSH, prod_wpsh);
		}
		
		ergebnis.setLabelPersonal1(PdfLabels.ModelMotormanuellGesamt2014_LabelPersonal.toString());
		
		return ergebnis;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		return ProdEinheit.M3_IR_PRO_WPSH;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit2() {
		return ProdEinheit.M3_OR_PRO_WPSH;
	}
		
		
	
	@Override
	protected ArbeitsobjektMotormanuellGesamt2014 getArbeitsobjekt() {
		return (ArbeitsobjektMotormanuellGesamt2014) super.getArbeitsobjekt();
	}
	
	
	@Override
	protected ArbeitssystemMotormanuell2014 getArbeitssystem() {
		return (ArbeitssystemMotormanuell2014) super.getArbeitssystem();
	}
	
	
	
	@Override
	protected double calcPsh0_hProM3() {
		
		double f_wegzeitenUndPausen_fix = 1.125; //Angenommener fixer Wert für Berechnung in calcM3oRProWPSH(). Der flexible Wert kann später wieder hineingerechnet werden.

		double m3ProWPSH = calcM3oRProWPSH();
		if (super.rindenAbzugBeruecksichtigen) {
			double rindenabzug_fix = 0.9;
			double rindenabzug_variabel = getArbeitsobjekt().getRindenAbzugFaktor();
			m3ProWPSH *= rindenabzug_variabel / rindenabzug_fix;
		}
		
		double WPSHproM3 = 1.0 / m3ProWPSH;
		double WSHproM3 = WPSHproM3 / (f_wegzeitenUndPausen_fix * f_stoer);
		double PSH15proM3 = WSHproM3 / (f_indir);
		double PSH0proM3 = PSH15proM3 / (f_0bis15);
		
		return PSH0proM3;
	}
		
		
		
		
	/**
	 * Berechnet die Produktivität in m3 o.R. pro WPSH
	 * 
	 * @return
	 */
	private double calcM3oRProWPSH() {
		
		//Koeffizienten
		double a = -8.8441;
		double b = getArbeitsobjekt().getRegion() == Region.Huegelland ?  1.5564  : 0;
		double c = 2.7332;
		double d = 2.5214;
		double e = -1.3273;
		double f = getArbeitsobjekt().getRegion() == Region.Huegelland ? -0.1601 : 0;
		double g = -0.3171;
		double h = 0.4712;
		
		
		
		//BHD
		final double bhd_original = getArbeitsobjekt().getBhd_cm();
		double bhd = bhd_original;
		
		if (bhd < 15) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("BHD muss mindestens 15cm sein!");
		}
		
		if (bhd > 100) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("BHD muss kleiner als 100cm sein!");
		}
		
		if (getArbeitsobjekt().getRegion() == Region.Huegelland) {
			bhd = Math.min(bhd, 90);
		}
		
		if (getArbeitsobjekt().getRegion() == Region.Gebirge) {
			bhd = Math.min(bhd, 75);
		}
		
		if (bhd < 35) {
			bhd = 35;
		}
		
		
		//Anteile Laubholz / Kiefer
		int anteilLaubholz_Prz = getArbeitsobjekt().getAnteilLaubholz_Prz();
		int anteilKiefern_Prz = getArbeitsobjekt().getAnteilKiefer_Prz();
		
		if ((anteilLaubholz_Prz + anteilKiefern_Prz) > 100) {
			//JSpinner scheint IllegalArgumentExeptions zu catchen?
			throw new RuntimeException("Kieferanteil und Laubholzanteil dürfen zusammen nicht mehr als 100% ergeben!");
		}
		
		double anteilLaubholz = anteilLaubholz_Prz / 100.0;
		double anteilKiefern = anteilKiefern_Prz / 100.0;
		
		
		
		//Berechnung
		double tap = 
				+ a
				+ b
				+ c * Math.log(bhd)
				+ d * anteilLaubholz
				+ e * anteilKiefern
				+ f * Math.log(bhd)
				+ g * Math.log(bhd) * anteilLaubholz
				+ h * Math.log(bhd) * anteilKiefern;
		tap *= getAnzahlPersonalVollzeitAequivalente();
		
		if (bhd_original < 35) {
			double kBaumart = calcKBaumartNachPfeiffer1978();
			kBaumart *= getAnzahlPersonalVollzeitAequivalente();
			tap = (tap - kBaumart) / (35-15) * (bhd_original - 15) + kBaumart;
		}
		
		
		return tap;
	}
	
	
	private double calcKBaumartNachPfeiffer1978() {
		double result = 0;
		
		double anteilLaub = getArbeitsobjekt().getAnteilLaubholz_Prz() / 100.0;
		double anteilKiefer = getArbeitsobjekt().getAnteilKiefer_Prz() / 100.0;

		if (getArbeitsobjekt().getRegion() == Region.Huegelland) {
			result += 0.86 * (1 - anteilKiefer - anteilLaub);
			result += 0.94 * anteilKiefer;
			result += 1.54 * anteilLaub;
		}
		else { //Gebirge
			result += 0.52 * (1 - anteilKiefer - anteilLaub);
			result += 0.63 * anteilKiefer;
			result += 1.18 * anteilLaub;
		}
		
		return result;
	}
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return getArbeitssystem().getVollzeitAequivalent();
	}
	
		
		
	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		double laufzeitAnteil = 0.7;
		return getAnzahlPersonalVollzeitAequivalente() * laufzeitAnteil; //eine Motorsäge pro Arbeitskraft
	}

}
