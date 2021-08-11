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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018.EquipeAnzahlPersonen;
import ch.wsl.fps.hepromo.model.calc.CalculatorKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorKombiseilgeraet2018.ErgebnisAnzeige;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelKombiseilgeraet2018 extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelKombiseilgeraet2018() {
		super.arbeitsobjekt = new ArbeitsobjektKombiseilgeraet2018();
		getArbeitsobjekt().setHolzmenge_m3(600);
		getArbeitsobjekt().setErschwernisse(Erschwernisse.Keine);
		getArbeitsobjekt().setLaengeProAufstellung_m(350);
		getArbeitsobjekt().setAnzahlStuetzenProAufstellung(2);
		getArbeitsobjekt().setAnzahlAufstellungen(2);
		getArbeitsobjekt().setHangneigung_Prz(50);
		getArbeitsobjekt().setAnteilLaubholz_Prz(20);

		super.arbeitssystem = new ArbeitssystemKombiseilgeraet2018();
		getArbeitssystem().setEquipeAnzahlPersonen(EquipeAnzahlPersonen.Anzahl3einhalb);
		getArbeitssystem().setAnteilEinsatzzeitVerzugsfahrzeug_Prz(50);
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(280);
		getArbeitssystem().setKostensatzMaschine2_proH(KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15);
		getArbeitssystem().setKostensatzVerzugsfahrzeug_proH(170);
		getArbeitssystem().setLabelPersonal1(ModelStrings.getString("ModelKombiseilgeraet2018.PersonalInsgesamt")); //$NON-NLS-1$
		getArbeitssystem().setLabelMaschine1(ModelStrings.getString("ModelKombiseilgeraet2018.Kombiseilgeraet")); //$NON-NLS-1$
		getArbeitssystem().setLabelMaschine2(ModelStrings.getString("ModelKombiseilgeraet2018.Motorsaege")); //$NON-NLS-1$
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorKombiseilgeraet2018(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelKombiseilgeraet2018(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorKombiseilgeraet2018(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektKombiseilgeraet2018 getArbeitsobjekt() {
		return (ArbeitsobjektKombiseilgeraet2018) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemKombiseilgeraet2018 getArbeitssystem() {
		return (ArbeitssystemKombiseilgeraet2018) arbeitssystem;
	}
	
	
	@Override
	public CalculatorKombiseilgeraet2018 getCalculator() {
		return (CalculatorKombiseilgeraet2018) calculator;
	}
	
	
	
	@Override
	protected String getModelName() {
		return ModelStrings.getString("ModelKombiseilgeraet2018.ModelName"); //$NON-NLS-1$
	}
	
	
	@Override
	protected String getErgebnisTitleSuffix() {	
		ErgebnisAnzeige ergebnisAnzeige = getCalculator().getErgebnisAnzeige();	
		String suffix = " (" + ergebnisAnzeige + ")";	
		return suffix;
	}

}
