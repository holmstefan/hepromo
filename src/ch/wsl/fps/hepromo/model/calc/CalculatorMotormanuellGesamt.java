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
package ch.wsl.fps.hepromo.model.calc;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektFaellenMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorMotormanuellGesamt extends AbstractCalculatorComposedModel {

	public CalculatorMotormanuellGesamt(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	protected ArbeitsobjektMotormanuellGesamt getArbeitsobjekt() {
		return (ArbeitsobjektMotormanuellGesamt) super.getArbeitsobjekt();
	}
	
	
	
	@Override
	public Ergebnis calculate() {
		
		//Teilprozess Stammholz: 		Fällen, Entasten, Entrinden, Stammholz aufarbeiten
		//Teilprozess Industrieholz: 	Fällen, Entasten, 			 Industrieholz aufarbeiten
		//Teilprozess Schichtholz: 		Fällen, Entasten, 			 Schichtholz aufarbeiten
		

		//get teilergebnisse
		Ergebnis eFaellen 					= getErgebnisFaellen();
		Ergebnis eEntasten 					= getErgebnisEntasten();
		Ergebnis eEntrindenVonHand 			= getErgebnisEntrindenVonHand();
//		Ergebnis eEntrindenMitBiber 		= getErgebnisEntrindenMitBiber();
//		Ergebnis eIndustrieholzAufarbeiten 	= getErgebnisIndustrieholzAufarbeiten();
//		Ergebnis eSchichtholzAufarbeiten 	= getErgebnisSchichtholzAufarbeiten();
//		Ergebnis eStammholzAufarbeiten 		= getErgebnisStammholzAufarbeiten();
		Ergebnis eAufarbeiten				= getSummierteTeilergebnisseAufarbeiten();
		

		//create neues ergebnis
		Ergebnis ergebnis = new Ergebnis();
		
		ergebnis.setAnzahl_m3(				eFaellen.getAnzahl_m3()		);
		
		ergebnis.setZeitPersonal(			eFaellen.getZeitPersonal() 			+ eEntasten.getZeitPersonal() 		   + eEntrindenVonHand.getZeitPersonal() 		 + eAufarbeiten.getZeitPersonal()			);
		ergebnis.setZeitMaschine1(			eFaellen.getZeitMaschine1() 		+ eEntasten.getZeitMaschine1() 		   +               	0                   		 + eAufarbeiten.getZeitMaschine1()			);
		ergebnis.setZeitMaschine2(																						 eEntrindenVonHand.getZeitMaschine1()													);
		ergebnis.setZeitUmsetzen(				getArbeitssystem().getUmsetzenZeit_h()				);
		ergebnis.setZeitWeitereAufwaende(		getArbeitssystem().getWeitereAufwaendeZeit_h()		);
		
		ergebnis.setKostenPersonal_total(	eFaellen.getKostenPersonal_total()  + eEntasten.getKostenPersonal_total()  + eEntrindenVonHand.getKostenPersonal_total() + eAufarbeiten.getKostenPersonal_total() 	);
		ergebnis.setKostenMaschine1_total(	eFaellen.getKostenMaschine1_total() + eEntasten.getKostenMaschine1_total()                                               + eAufarbeiten.getKostenMaschine1_total()  );
		ergebnis.setKostenMaschine2_total(																			 	 eEntrindenVonHand.getKostenMaschine1_total() 											);
		ergebnis.setKostenUmsetzen_total(			getArbeitssystem().getUmsetzenBetrag_CHF()			);
		ergebnis.setKostenWeitereAufwaende_total(	getArbeitssystem().getWeitereAufwaendeBetrag_CHF()	);
		
		ergebnis.setRisikoVerwaltungGewinn_Prz( eFaellen.getRisikoVerwaltungGewinn_Prz());
		ergebnis.setMehrwertsteuer_Prz( eFaellen.getMehrwertsteuer_Prz());

		ergebnis.setLabelPersonal1(getArbeitssystem().getLabelPersonal1());
		ergebnis.setLabelMaschine1(getArbeitssystem().getLabelMaschine1());
		ergebnis.setLabelMaschine2(getArbeitssystem().getLabelMaschine2());
		
		ergebnis.hideDauerDerArbeit();
		ergebnis.hideProduktivitaet();
		
		
		//return ergebnis
		return ergebnis;
	}
	
	
	
	private Ergebnis getSummierteTeilergebnisseAufarbeiten() {
		
		//get teilergebnisse
		Ergebnis eStammholzAufarbeiten 		= getErgebnisStammholzAufarbeiten();
		Ergebnis eIndustrieholzAufarbeiten 	= getErgebnisIndustrieholzAufarbeiten();
		Ergebnis eSchichtholzAufarbeiten 	= getErgebnisSchichtholzAufarbeiten();
		
//		//get anteile
//		double anteilStammholzAufarbeiten 		= getArbeitsobjekt().getAnteilStammholz_Prz() / 100.0;
//		double anteilIndustrieholzAufarbeiten 	= getArbeitsobjekt().getAnteilIndustrieholz_Prz() / 100.0;
//		double anteilSchichtholzAufarbeiten 	= getArbeitsobjekt().getAnteilSchichtholz_Prz() / 100.0;
		
		
		//create neues ergebnis
		Ergebnis ergebnis = new Ergebnis();

		ergebnis.setZeitPersonal(
				eStammholzAufarbeiten.getZeitPersonal() 	+
				eIndustrieholzAufarbeiten.getZeitPersonal() +
				eSchichtholzAufarbeiten.getZeitPersonal()
				);
		
		ergebnis.setZeitMaschine1(
				eStammholzAufarbeiten.getZeitMaschine1() 	 +
				eIndustrieholzAufarbeiten.getZeitMaschine1() +
				eSchichtholzAufarbeiten.getZeitMaschine1()
				);

		ergebnis.setKostenPersonal_total(
				eStammholzAufarbeiten.getKostenPersonal_total() 	+
				eIndustrieholzAufarbeiten.getKostenPersonal_total() +
				eSchichtholzAufarbeiten.getKostenPersonal_total()
				);

		ergebnis.setKostenMaschine1_total(
				eStammholzAufarbeiten.getKostenMaschine1_total() 	+
				eIndustrieholzAufarbeiten.getKostenMaschine1_total()+
				eSchichtholzAufarbeiten.getKostenMaschine1_total() 
				);


		//return ergebnis
		return ergebnis;
	}

	
	
	
	
	private HeProMoInputData getInputDataMotormanuell(final Arbeitsobjekt ao) {
		final Faktoren faktoren = super.faktoren;
		final Arbeitssystem as = super.getArbeitssystem();
		HeProMoInputData inputData = createHeProMoInputData(ao, as, faktoren);
		
		return inputData;
	}
	
	
	

	
	private Ergebnis getErgebnisFaellen() {
		//get arbeitsobjekt
		ArbeitsobjektFaellenMotormanuell ao = getArbeitsobjekt().getTeilobjektFaellen();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//get calculator
		CalculatorFaellenMotormanuell calc = new CalculatorFaellenMotormanuell(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisEntasten() {
		//get arbeitsobjekt
		ArbeitsobjektEntastenMotormanuell ao = getArbeitsobjekt().getTeilobjektEntasten();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//get calculator
		CalculatorEntastenMotormanuell calc = new CalculatorEntastenMotormanuell(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisEntrindenVonHand() {
		//get arbeitsobjekt
		ArbeitsobjektEntrinden ao = getArbeitsobjekt().getTeilobjektEntrinden();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//IMPORTANT: stundenansatz auf maschine2 kopieren! TODO: sollte schöner gelöst werden (maschine1, maschine2 etc)
		double kostenansatzMaschine1 = inputData.getArbeitssystem().getKostensatzMaschine1_proH();
		double kostenansatzMaschine2 = inputData.getArbeitssystem().getKostensatzMaschine2_proH();
//		System.out.println(kostenansatzMaschine1 + " " + kostenansatzMaschine2);
		inputData.getArbeitssystem().setKostensatzMaschine1_proH(kostenansatzMaschine2);
		
		//get calculator
		CalculatorEntrindenVonHand calc = new CalculatorEntrindenVonHand(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		
		//WICHTIG: stundenansatz wieder auf den alten wert setzen, da keine deep-copy TODO: schöner lösen
		inputData.getArbeitssystem().setKostensatzMaschine1_proH(kostenansatzMaschine1); //TODO: mögliche lösung: schäleisen hat im gesamten motormanuell-bereich immer maschine2!
		
		return ergebnis;
	}
	
	
	
	@SuppressWarnings("unused")
	private Ergebnis getErgebnisEntrindenMitBiber() {
		//get arbeitsobjekt
		ArbeitsobjektEntrinden ao = getArbeitsobjekt().getTeilobjektEntrinden();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//get calculator
		CalculatorEntrindenMitBiber calc = new CalculatorEntrindenMitBiber(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisIndustrieholzAufarbeiten() {
		//get arbeitsobjekt
		ArbeitsobjektIhLangAufarbeiten ao = getArbeitsobjekt().getTeilobjektIndustrieholzAufarbeiten();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//get calculator
		CalculatorIhLangAufarbeiten calc = new CalculatorIhLangAufarbeiten(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisSchichtholzAufarbeiten() {
		//get arbeitsobjekt
		ArbeitsobjektSchichtholzAufarbeiten ao = getArbeitsobjekt().getTeilobjektSchichtholzAufarbeiten();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//get calculator
		CalculatorSchichtholzAufarbeiten calc = new CalculatorSchichtholzAufarbeiten(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisStammholzAufarbeiten() {
		//get arbeitsobjekt
		ArbeitsobjektStammholzAufarbeiten ao = getArbeitsobjekt().getTeilobjektStammholzAufarbeiten();
		
		//get input data
		HeProMoInputData inputData = getInputDataMotormanuell(ao);
		
		//get calculator
		CalculatorStammholzAufarbeiten calc = new CalculatorStammholzAufarbeiten(inputData);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	

}
