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
package ch.wsl.fps.hepromo.model.modelle;

import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.calc.CalculatorKonventionellerSeilkranSeilen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelKonventionellerSeilkranSeilen extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelKonventionellerSeilkranSeilen() {
		super.arbeitsobjekt = new ArbeitsobjektKonventionellerSeilkranSeilen();
		getArbeitsobjekt().setMittleresStueckvolumen_m3(0.4);
		getArbeitsobjekt().setHolzmenge_m3(600);
		getArbeitsobjekt().setStuecklaenge_m(5);
		getArbeitsobjekt().setEingriffsart(Eingriffsart.Durchforstung);
		getArbeitsobjekt().setHolzSeilOrt(HolzSeilOrt.AusSchlagflaeche);
		getArbeitsobjekt().setHangneigung_Prz(50);
		getArbeitsobjekt().setHindernisse(Hindernisse.Normal);
		getArbeitsobjekt().setFahrtrichtung(Fahrtrichtung.Bergab);
		getArbeitsobjekt().setMittlereFahrdistanz_m(350);
		getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(20);
		getArbeitsobjekt().setTragseilHoeheLagerplatz_m(10);
		getArbeitsobjekt().setTragseilHoeheBestand_m(12);
		
		super.arbeitssystem = new ArbeitssystemSeilkran();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setAnzahlPersonal(3);
		getArbeitssystem().setKostensatzMaschine1_proH(80);
		getArbeitssystem().setLabelMaschine1("Seilkran");
		getArbeitssystem().setAnteilSeilkranLaufzeit_Prz(90);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorKonventionellerSeilkranSeilen(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelKonventionellerSeilkranSeilen(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorKonventionellerSeilkranSeilen(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektKonventionellerSeilkranSeilen getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranSeilen) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Konventioneller Seilkran: Seilen";
	}

}
