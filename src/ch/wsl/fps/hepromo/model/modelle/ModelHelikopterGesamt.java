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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterGesamt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;
import ch.wsl.fps.hepromo.model.calc.CalculatorHelikopterGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHelikopterGesamt extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelHelikopterGesamt() {
		super.arbeitsobjekt = new ArbeitsobjektHelikopterGesamt();
		getArbeitsobjekt().setHolzmenge_m3(250);
		getArbeitsobjekt().setArbeitsVerfahren(ArbeitsVerfahren.Sortimentsverfahren);
		getArbeitsobjekt().setHolztyp(Holztyp.Nadelholz_frisch);
		getArbeitsobjekt().setHorizontalDistanz_m(300);
		getArbeitsobjekt().setVertikalDistanz_m(100);
		
		super.arbeitssystem = new ArbeitssystemHelikopterGesamt();
		getArbeitssystem().setHelikopterKosten_proMin(60);
		getArbeitssystem().setAnflugPauschale(500);
		getArbeitssystem().setHelikopterKlasse(HelikopterKlasse.Mittel);
		getArbeitssystem().setLastVolumen(2.8571);
		getArbeitssystem().setLastVolumenAutomatischBerechnen(true);
		getArbeitssystem().setAnzahlPersonalBeimHolzFliegen(2);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1(PdfLabels.ModelHelikopterGesamt_LabelPersonalForstbetrieb.toString());
		getArbeitssystem().setAnzahlPersonalNachHolzFliegen(2);
		getArbeitssystem().setAnzahlMotorsaegen(1);
		getArbeitssystem().setKostensatzMaschine1_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelHelikopterGesamt_LabelMotorsaege.toString());
		getArbeitssystem().setAnzahlKranfahrzeuge(1);
		getArbeitssystem().setKostensatzMaschine2_proH(80);
		getArbeitssystem().setLabelMaschine2(PdfLabels.ModelHelikopterGesamt_LabelKranfahrzeug.toString());
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		getArbeitssystem().setKalkulationInklLagerplatzarbeit(true);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorHelikopterGesamt(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelHelikopterGesamt(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorHelikopterGesamt(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektHelikopterGesamt getArbeitsobjekt() {
		return (ArbeitsobjektHelikopterGesamt) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemHelikopterGesamt getArbeitssystem() {
		return (ArbeitssystemHelikopterGesamt) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return PdfLabels.ModelHelikopterGesamt_ModelName.toString();
	}

}
