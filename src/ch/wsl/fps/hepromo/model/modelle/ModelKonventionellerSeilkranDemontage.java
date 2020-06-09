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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranDemontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.calc.CalculatorKonventionellerSeilkranDemontage;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelKonventionellerSeilkranDemontage extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelKonventionellerSeilkranDemontage() {
		super.arbeitsobjekt = new ArbeitsobjektKonventionellerSeilkranDemontage();
		getArbeitsobjekt().setLinienLaenge_m(800);
		getArbeitsobjekt().setAnzahlStuetzen(2);
		getArbeitsobjekt().setAnzahlEndmasten(1);
		getArbeitsobjekt().setDemontageIstSeilverlegung(false);
		getArbeitsobjekt().setWindenTransport(WindenTransport.KeinWindenTransport);
		getArbeitsobjekt().setWindenStandort(WindenStandort.Bleibt);
		getArbeitsobjekt().setDistanzWindenSelbstfahrt(0);
		
		super.arbeitssystem = new ArbeitssystemSeilkran();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setAnzahlPersonal(3);
		getArbeitssystem().setKostensatzMaschine1_proH(80);
		getArbeitssystem().setLabelMaschine1("Seilkran");
		getArbeitssystem().setAnteilSeilkranLaufzeit_Prz(20);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorKonventionellerSeilkranDemontage(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelKonventionellerSeilkranDemontage(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorKonventionellerSeilkranDemontage(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektKonventionellerSeilkranDemontage getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranDemontage) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Konventioneller Seilkran: Demontage";
	}

}
