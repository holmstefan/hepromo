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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder.FahrstreckenArt;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder.ForwarderTyp;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelForwarder extends AbstractModel {

	/**
	 * Constructor
	 */
	public ModelForwarder() {
		super.arbeitsobjekt = new ArbeitsobjektForwarder();
		getArbeitsobjekt().setLaubholzAnteil_Prz(50);
		getArbeitsobjekt().setHolzmenge_m3(200);
		getArbeitsobjekt().setDurchschnittlicherBhdAushieb_cm(30);
		getArbeitsobjekt().setDurchschnittlicheHolzlaenge_m(4);
		getArbeitsobjekt().setDurchschnittlicheAnzahlSortimente(2);
		getArbeitsobjekt().setAnzahlVerschiedeneSortimente(4);
		getArbeitsobjekt().setAnteilKrumm_Prz(40);
		getArbeitsobjekt().setFahrstreckeAufStrasse(100);
		getArbeitsobjekt().setFahrstreckenArtStrasse(FahrstreckenArt.GleichmaessigVerteilt);
		getArbeitsobjekt().setFahrstreckeAufFeinerschliessung(250);
		getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(FahrstreckenArt.GleichmaessigVerteilt);
		getArbeitsobjekt().setErschliessungsLaengeEinseitig_m(0);
		getArbeitsobjekt().setErschliessungsLaengeEinseitigAnteilStrasse_Prz(0);
		getArbeitsobjekt().setErschliessungsLaengeBeidseitig_m(150);
		getArbeitsobjekt().setErschliessungsLaengeBeidseitigAnteilStrasse_Prz(0);
		getArbeitsobjekt().setHindernisKategorieFeinerschliessung(HindernisKategorie.Wenige);
		getArbeitsobjekt().setNeigungsKategorieFeinerschliessung(NeigungsKategorie.NK_bis10);
		getArbeitsobjekt().setFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz(33);
		getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz(33);
		
		super.arbeitssystem = new ArbeitssystemForwarder();
		getArbeitssystem().setKostensatzPersonal1_proH(80);
		getArbeitssystem().setLabelPersonal1(PdfLabels.ModelForwarder_LabelPersonal.toString());
		getArbeitssystem().setKostensatzMaschine1_proH(120);
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelForwarder_LabelForwarder.toString());
		getArbeitssystem().setForwarderTyp(ForwarderTyp.Mittel);
		getArbeitssystem().setLadeQuerschnittsFlaeche_m2(4.1);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorForwarder(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelForwarder(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorForwarder(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektForwarder getArbeitsobjekt() {
		return (ArbeitsobjektForwarder) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemForwarder getArbeitssystem() {
		return (ArbeitssystemForwarder) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return PdfLabels.ModelForwarder_ModelName.toString();
	}

}
