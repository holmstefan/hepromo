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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Standortguete;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018.Maschinentyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorVorruecken2018 extends AbstractCalculatorSingleModel2014 {

	public CalculatorVorruecken2018(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	public ArbeitsobjektVorruecken2018 getArbeitsobjekt() {
		return (ArbeitsobjektVorruecken2018) super.getArbeitsobjekt();
	}
	
	
	@Override
	public ArbeitssystemVorruecken2018 getArbeitssystem() {
		return (ArbeitssystemVorruecken2018) super.getArbeitssystem();
	}
	
	
	@Override
	public Ergebnis calculate() {
		Ergebnis ergebnis = super.calculate();
		
		double pmh15Motorsaege_hProM3 = 1d / calcProduktivitaetMotorsaege_m3oRproPMH15();
		double pmh15Motorsaege_total = pmh15Motorsaege_hProM3 * getRelevanteHolzmenge();
		pmh15Motorsaege_total *= super.faktoren.getKorrekturFaktor();
		
		ergebnis.setZeitMaschine2(pmh15Motorsaege_total);
		ergebnis.setKostenMaschine2_total(getArbeitssystem().getKostensatzMaschine2_proH() * pmh15Motorsaege_total);

		return ergebnis;
	}
	

	@Override
	protected double calcPsh0_hProM3() {
		double m3ProPMH15 = calcProduktivitaetSystem_m3oRproPMH15();
		double pmh15_hProM3 = 1.0 / m3ProPMH15;
		double psh0_hProM3 = pmh15_hProM3 / f_0bis15;
		return psh0_hProM3;
	}
	
	
	private double calcProduktivitaetSystem_m3oRproPMH15() {
		return convertZeitaufwandProBaumOhneZuschlaege_min_to_m3oRproPMH15(getZeitaufwandSystemProBaumOhneZuschlaege_min());
	}
	
	
	private double calcProduktivitaetMotorsaege_m3oRproPMH15() {
		return convertZeitaufwandProBaumOhneZuschlaege_min_to_m3oRproPMH15(getZeitaufwandMotorsaegeProBaumOhneZuschlaege_min());
	}
	
	
	private double convertZeitaufwandProBaumOhneZuschlaege_min_to_m3oRproPMH15(double zeitaufwandProBaum_min) {
		final double vMit = getVMit();
		final double mfHang = getMfHangneigung();
		final double f_iR_oR = rindenAbzugBeruecksichtigen ? getArbeitsobjekt().getRindenAbzugFaktor() : 1;
		final double effSteig = getArbeitssystem().getMaschinentyp() == Maschinentyp.Rueckeraupe ? getArbeitssystem().getProduktivtaetssteigerungRueckeraupe() : 1;	

		final double zeitaufwandProBaum_h = zeitaufwandProBaum_min / 60d;	
		final double zeitaufwandProM3_h = zeitaufwandProBaum_h / vMit;
		
		final double zeitaufwand_PMH15proM3iR = zeitaufwandProM3_h * mfHang;	
		final double zeitaufwand_PMH15proM3oR = zeitaufwand_PMH15proM3iR / f_iR_oR;
		
		final double produktivitaetSystem_m3oRproPMH15 = (1d / zeitaufwand_PMH15proM3oR) * effSteig;
		
		return produktivitaetSystem_m3oRproPMH15;
	}
	
	
	private double getZeitaufwandSystemProBaumOhneZuschlaege_min() {
		double bhd = getArbeitsobjekt().getBhd_cm();
		boolean isAbzopf = getArbeitsobjekt().isAbzopfenDerKrone();
		
		double result = 0.1786 * bhd + 1.275;
		if (isAbzopf == false) {
			result -= 0.25;
		}
		
		return result;
	}
	
	
	private double getZeitaufwandMotorsaegeProBaumOhneZuschlaege_min() {
		double bhd = getArbeitsobjekt().getBhd_cm();
		boolean isAbzopf = getArbeitsobjekt().isAbzopfenDerKrone();
		
		double result = 0.0918 * bhd + 0.3409;
		if (isAbzopf == false) {
			result -= 0.5;
		}
		
		return result;
	}
	
	
	/**
	 * Berechnet den mittleren Stückinhalt anhand mittlerem BHD und Standortgüte
	 */
	private double getVMit() {
		Standortguete standortguete = getArbeitsobjekt().getStandortguete();
		double bhd_cm = getArbeitsobjekt().getBhd_cm();
		
		double result = getVMit(standortguete, bhd_cm);
		return result;
	}
	
	
	public static double getVMit(Standortguete standortguete, double bhd_cm) {
		LuzernerTarif tarif = LuzernerTarif.getTarifForStandortguete(standortguete);
		double a = tarif.a;
		double b = tarif.b;
		
		double result = a * Math.pow(bhd_cm, b);
		return result;
	}
	
	
	private static enum LuzernerTarif {
		TARIF1(0.00090, 2.0478),
		TARIF2(0.00030, 2.3531),
		TARIF3(0.00020, 2.4200),
		TARIF4(0.00008, 2.5894),
		TARIF5(0.00002, 2.8915);
		
		private final double a;
		private final double b;
		
		private LuzernerTarif(double a, double b) {
			this.a = a;
			this.b = b;
		}
		
		private static LuzernerTarif getTarifForStandortguete(Standortguete standort) {
			switch(standort) {
			case SEHR_GUT:
				return TARIF1;
				
			case GUT:
				return TARIF2;
				
			case MITTEL:
				return TARIF3;
				
			case WENIGER_GUT:
				return TARIF4;
				
			case ARM:
				return TARIF5;
				
			default:
				throw new RuntimeException(standort.name());
			}
		}
	}
	
	
	private double getMfHangneigung() {
		Hangneigung hangneigung = getArbeitsobjekt().getHangneigung();
		return getMultiplikationsfaktorHangneigung(hangneigung);
	}
	
	
	private static double getMultiplikationsfaktorHangneigung(Hangneigung hangneigung) {
		switch(hangneigung) {
		case HN_BIS_15_PRZ:
			return 1d;
			
		case HN_GROESSER_15_BIS_25_PRZ:
			return 1.07;
			
		case HN_GROESSER_25_BIS_35_PRZ:
			return 1.18;
			
		case HN_GROESSER_35_BIS_45_PRZ:
			return 1.33;
			
		case HN_GROESSER_45_BIS_55_PRZ:
			return 1.53;
			
		case HN_GROESSER_55_PRZ:
			return 1.78;
			
		default:
			throw new RuntimeException(hangneigung.name());
			
		}
	}
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return 2;
	}

}
