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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Standortguete;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018.Maschinentyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorVorruecken2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelVorruecken2018 extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelVorruecken2018() {
		super.arbeitsobjekt = new ArbeitsobjektVorruecken2018();
		getArbeitsobjekt().setHolzmenge_m3(100);
		getArbeitsobjekt().setBhd_cm(25);
		getArbeitsobjekt().setStandortguete(Standortguete.MITTEL);
		getArbeitsobjekt().setHangneigung(Hangneigung.HN_BIS_15_PRZ);
		getArbeitsobjekt().setAbzopfenDerKrone(true);

		super.arbeitssystem = new ArbeitssystemVorruecken2018();
		getArbeitssystem().setMaschinentyp(Maschinentyp.Rueckeraupe);
		getArbeitssystem().setProduktivtaetssteigerungRueckeraupe(1.1);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(60);
		getArbeitssystem().setKostensatzMaschine2_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setLabelPersonal1(ModelStrings.getString("ModelVorruecken2018.Personal2Pers")); //$NON-NLS-1$
		getArbeitssystem().setLabelMaschine1(ModelStrings.getString("ModelVorruecken2018.VorrueckeGeraet")); //$NON-NLS-1$
		getArbeitssystem().setLabelMaschine2(ModelStrings.getString("ModelVorruecken2018.Motorsaege")); //$NON-NLS-1$
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorVorruecken2018(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelVorruecken2018(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorVorruecken2018(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektVorruecken2018 getArbeitsobjekt() {
		return (ArbeitsobjektVorruecken2018) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemVorruecken2018 getArbeitssystem() {
		return (ArbeitssystemVorruecken2018) arbeitssystem;
	}	
	
	
	@Override
	protected String getModelName() {
		return ModelStrings.getString("ModelVorruecken2018.ModelName"); //$NON-NLS-1$
	}

}
