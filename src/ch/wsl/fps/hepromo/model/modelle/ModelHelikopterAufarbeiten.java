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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.calc.CalculatorHelikopterAufarbeiten;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHelikopterAufarbeiten extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelHelikopterAufarbeiten() {
		super.arbeitsobjekt = new ArbeitsobjektHelikopterAufarbeiten();
		getArbeitsobjekt().setHolzmenge_m3(250);
		getArbeitsobjekt().setBaumartenGruppe(BaumartenGruppe.Fichte);
		getArbeitsobjekt().setArbeitsVerfahren(ArbeitsVerfahren.Sortimentsverfahren);
		
		super.arbeitssystem = new ArbeitssystemHelikopterAufarbeiten();
		getArbeitssystem().setAnzahlPersonal1(2);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setAnzahlMaschine1(1);
		getArbeitssystem().setKostensatzMaschine1_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setLabelMaschine1("Motorsäge");
		getArbeitssystem().setAnzahlMaschine2(1);
		getArbeitssystem().setKostensatzMaschine2_proH(80);
		getArbeitssystem().setLabelMaschine2("Kranfahrzeug");
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorHelikopterAufarbeiten(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelHelikopterAufarbeiten(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorHelikopterAufarbeiten(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektHelikopterAufarbeiten getArbeitsobjekt() {
		return (ArbeitsobjektHelikopterAufarbeiten) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemHelikopterAufarbeiten getArbeitssystem() {
		return (ArbeitssystemHelikopterAufarbeiten) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Helikopter: Aufarbeiten";
	}

}
