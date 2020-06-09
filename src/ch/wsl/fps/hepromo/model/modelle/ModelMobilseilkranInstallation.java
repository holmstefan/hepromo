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

import java.util.ArrayList;
import java.util.List;

import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.calc.CalculatorMobilseilkranInstallation;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMobilseilkranInstallation extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelMobilseilkranInstallation() {
		super.arbeitsobjekt = new ArbeitsobjektMobilseilkranInstallation();
		getArbeitsobjekt().setSeilsystem(Seilsystem.ZweiseilSystem);
		getArbeitsobjekt().setMaschinenStandort(MaschinenStandort.Oben);
		getArbeitsobjekt().setLinienLaenge_m(600);
		
		List<Integer> listStuetzenTragseilHoehen = new ArrayList<Integer>();
		listStuetzenTragseilHoehen.add(12);
		getArbeitsobjekt().setStuetzenTragseilHoehen(listStuetzenTragseilHoehen);
		
		getArbeitsobjekt().setEndmast(true);
		getArbeitsobjekt().setTragseilHoeheEndmast(8);
		
		super.arbeitssystem = new ArbeitssystemSeilkran();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setLabelPersonal1("Personal");
		getArbeitssystem().setAnzahlPersonal(3);
		getArbeitssystem().setKostensatzMaschine1_proH(150);
		getArbeitssystem().setLabelMaschine1("Mobilseilkran");
		getArbeitssystem().setAnteilSeilkranLaufzeit_Prz(20);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorMobilseilkranInstallation(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMobilseilkranInstallation(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorMobilseilkranInstallation(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektMobilseilkranInstallation getArbeitsobjekt() {
		return (ArbeitsobjektMobilseilkranInstallation) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkran getArbeitssystem() {
		return (ArbeitssystemSeilkran) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return "Mobilseilkran: Installation";
	}

}
