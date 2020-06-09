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
import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AbstandRueckegasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Rueckeentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018.Forwardertyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018.ErgebnisAnzeige;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelForwarder2018 extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelForwarder2018() {
		super.arbeitsobjekt = new ArbeitsobjektForwarder2018();
		getArbeitsobjekt().setHolzmenge_m3(140);
		getArbeitsobjekt().setEnergieholzmenge_m3iR(0);
		getArbeitsobjekt().setVerkaufRundholz_m3oR(140 * getArbeitsobjekt().getRindenAbzugFaktor());
		getArbeitsobjekt().setBhdMit_cm(30);
		getArbeitsobjekt().setRueckeentfernung(Rueckeentfernung.EntfKleiner200m);
		getArbeitsobjekt().setHangneigung(Hangneigung.NeigungBis15Prozent);
		getArbeitsobjekt().setAnzahlSortimente(AnzahlSortimente.AnzahlBis3);
		getArbeitsobjekt().setErschwernisse(Erschwernisse.Keine);
		getArbeitsobjekt().setAbstandRueckegasse(AbstandRueckegasse.Circa30m);
		getArbeitsobjekt().setZopfdurchmesser_cm(10);
		getArbeitsobjekt().setEnergieholzanfall_m3iRproHa(getArbeitsobjekt().getAllEnergieholzanfall()[1]);

		super.arbeitssystem = new ArbeitssystemForwarder2018();
		getArbeitssystem().setForwardertyp(Forwardertyp.Mittel);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(110);
		getArbeitssystem().setLabelPersonal1(ModelStrings.getString("ModelForwarder2018.Maschinist")); //$NON-NLS-1$
		getArbeitssystem().setLabelMaschine1(ModelStrings.getString("ModelForwarder2018.Forwarder")); //$NON-NLS-1$
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorForwarder2018(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelForwarder2018(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorForwarder2018(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektForwarder2018 getArbeitsobjekt() {
		return (ArbeitsobjektForwarder2018) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemForwarder2018 getArbeitssystem() {
		return (ArbeitssystemForwarder2018) arbeitssystem;
	}
	
	
	@Override
	public CalculatorForwarder2018 getCalculator() {
		return (CalculatorForwarder2018) calculator;
	}
	
	
	
	@Override
	protected String getModelName() {
		return ModelStrings.getString("ModelForwarder2018.ModelName"); //$NON-NLS-1$
	}
	
	
	@Override
	protected String getErgebnisTitleSuffix() {	
		ErgebnisAnzeige ergebnisAnzeige = getCalculator().getErgebnisAnzeige();	
		String suffix = "(" + ergebnisAnzeige + ")";	
		return suffix;
	}

}
