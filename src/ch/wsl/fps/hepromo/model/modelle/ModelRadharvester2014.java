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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014.Maschinentyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorRadharvester2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelRadharvester2014 extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelRadharvester2014() {
		super.arbeitsobjekt = new ArbeitsobjektRadharvester2014();
		getArbeitsobjekt().setHolzmenge_m3(150);
		getArbeitsobjekt().setBhd_cm(35);

		super.arbeitssystem = new ArbeitssystemRadharvester2014();
		getArbeitssystem().setMaschinentyp(Maschinentyp.Mittel);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(250);
		getArbeitssystem().setLabelPersonal1(PdfLabels.ModelRadharvester2014_LabelMaschinist.toString());
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelRadharvester2014_LabelRadharvester.toString());
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorRadharvester2014(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelRadharvester2014(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorRadharvester2014(this);
		}
	}
	
	
	@Override
	public ArbeitssystemRadharvester2014 getArbeitssystem() {
		return (ArbeitssystemRadharvester2014) arbeitssystem;
	}
	
	
	@Override
	public ArbeitsobjektRadharvester2014 getArbeitsobjekt() {
		return (ArbeitsobjektRadharvester2014) arbeitsobjekt;
	}


	@Override
	protected String getModelName() {
		return PdfLabels.ModelRadharvester2014_ModelName.toString();
	}

}
