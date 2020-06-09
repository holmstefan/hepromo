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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen.SchwierigkeitSeitlicherZuzug;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;
import ch.wsl.fps.hepromo.model.calc.CalculatorMobilseilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelMobilseilkranGesamt extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelMobilseilkranGesamt() {
		super.arbeitsobjekt = new ArbeitsobjektMobilseilkranGesamt();
		getArbeitsobjekt().setHolzmenge_m3(400);
		getArbeitsobjekt().setMittleresStueckvolumen_m3(0.4);
		getArbeitsobjekt().setLinienLaenge_m(600);
		getArbeitsobjekt().setLinieAbsteckenOhneProjekt(false);
		getArbeitsobjekt().setMittlereFahrdistanz_m(300);
		getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(15);
		getArbeitsobjekt().setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug.Einfach);
		getArbeitsobjekt().setSeilsystem(Seilsystem.ZweiseilSystem);
		getArbeitsobjekt().setMaschinenStandort(MaschinenStandort.Oben);
		
		List<Integer> listStuetzenTragseilHoehen = new ArrayList<Integer>();
		listStuetzenTragseilHoehen.add(12);
		getArbeitsobjekt().setStuetzenTragseilHoehen(listStuetzenTragseilHoehen);
		
		getArbeitsobjekt().setEndmast(true);
		getArbeitsobjekt().setTragseilHoeheEndmast(8);
		
		super.arbeitssystem = new ArbeitssystemSeilkranGesamt();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(140);
		getArbeitssystem().setKostensatzMaschine2_proH(80);
		getArbeitssystem().setLabelPersonal1(PdfLabels.ModelMobilseilkranGesamt_LabelPersonal.toString());
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelMobilseilkranGesamt_LabelMobilseilkran.toString());
		getArbeitssystem().setLabelMaschine2(PdfLabels.ModelMobilseilkranGesamt_LabelKranfahrzeug.toString());
		getArbeitssystem().setLaufzeitKranfahrzeug_Prz(75);
		getArbeitssystem().setAnzahlPersonenInstallation(3);
		getArbeitssystem().setAnzahlPersonenSeilen(2);
		getArbeitssystem().setAnzahlPersonenLagerplatz(1);
		getArbeitssystem().setEinsatzzeitPersonenLagerplatz_Prz(100);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorMobilseilkranGesamt(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelMobilseilkranGesamt(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorMobilseilkranGesamt(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektMobilseilkranGesamt getArbeitsobjekt() {
		return (ArbeitsobjektMobilseilkranGesamt) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkranGesamt getArbeitssystem() {
		return (ArbeitssystemSeilkranGesamt) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return PdfLabels.ModelMobilseilkranGesamt_ModelName.toString();
	}

}
