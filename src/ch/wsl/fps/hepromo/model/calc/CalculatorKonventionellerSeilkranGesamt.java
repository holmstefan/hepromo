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
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranDemontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranPlanung;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranLagerplatz;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorKonventionellerSeilkranGesamt extends AbstractCalculatorComposedModel {

	public CalculatorKonventionellerSeilkranGesamt(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	protected ArbeitsobjektKonventionellerSeilkranGesamt getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranGesamt) super.getArbeitsobjekt();
	}

	
	@Override
	protected ArbeitssystemSeilkranGesamt getArbeitssystem() {
		return (ArbeitssystemSeilkranGesamt) super.getArbeitssystem();
	}
	
	
	
	@Override
	public Ergebnis calculate() {
		
		//get teilergebnisse
		Ergebnis ePlanung 		= getErgebnisPlanung();
		Ergebnis eMontage	 	= getErgebnisMontage();
		Ergebnis eSeilen 		= getErgebnisSeilen();
		Ergebnis eDemontage		= getErgebnisDemontage();
		double psh0ProM_Seilen = eSeilen.getPsh0_hProM3();
		Ergebnis eLagerplatz 	= getErgebnisLagerplatz(psh0ProM_Seilen);

		
		//create neues ergebnis
		Ergebnis ergebnis = new Ergebnis();
		
		ergebnis.setAnzahl_m3(					eSeilen.getAnzahl_m3()		);

		//Bugfix: im alten HeProMo wurde für die Planung der psh0-Wert verwendet, und die Montagezeit durch die Anzahl Personen _Planung_ statt _Montage_ geteilt.
//		ergebnis.setZeitTotal(					ePlanung.getPsh0_total()*1.1 + eMontage.getZeitTotal()/3*2 + eSeilen.getZeitTotal() + eDemontage.getZeitTotal()		);
		ergebnis.setZeitTotal(					ePlanung.getZeitTotal() + eMontage.getZeitTotal() + eSeilen.getZeitTotal() + eDemontage.getZeitTotal()		); //Lagerplatzarbeiten laufen parallel zum Seilen, deshalb wird Lagerplatzzeit hier nicht mitgerechnet
		
		ergebnis.setZeitPersonal(				ePlanung.getZeitPersonal() + eMontage.getZeitPersonal() + eSeilen.getZeitPersonal() + eDemontage.getZeitPersonal() + eLagerplatz.getZeitPersonal()		);
		ergebnis.setZeitMaschine1(				eMontage.getZeitMaschine1() + eSeilen.getZeitMaschine1() + eDemontage.getZeitMaschine1() ); //Maschine1 = Seilkran
		ergebnis.setZeitMaschine2(				eLagerplatz.getZeitMaschine1()  ); //Maschine2 = Kranfahrzeug
		ergebnis.setZeitUmsetzen(				getArbeitssystem().getUmsetzenZeit_h()				);
		ergebnis.setZeitWeitereAufwaende(		getArbeitssystem().getWeitereAufwaendeZeit_h()		);
		
		ergebnis.setKostenPersonal_total(		ePlanung.getKostenPersonal_total() + eMontage.getKostenPersonal_total() + eSeilen.getKostenPersonal_total() + eDemontage.getKostenPersonal_total() + eLagerplatz.getKostenPersonal_total() 	);
		ergebnis.setKostenMaschine1_total(		eMontage.getKostenMaschine1_total() + eSeilen.getKostenMaschine1_total() + eDemontage.getKostenMaschine1_total() ); //Maschine1 = Seilkran
		ergebnis.setKostenMaschine2_total(		eLagerplatz.getKostenMaschine1_total() ); //Maschine2 = Kranfahrzeug
		ergebnis.setKostenUmsetzen_total(			getArbeitssystem().getUmsetzenBetrag_CHF()				);
		ergebnis.setKostenWeitereAufwaende_total(	getArbeitssystem().getWeitereAufwaendeBetrag_CHF()		);

		setProduktivitaet(ergebnis, ProdEinheit.M3_OR_PRO_PMH15_BEIM_SEILEN, ergebnis.getAnzahl_m3() / eSeilen.getZeitMaschine1()); //gemäss FF/5.5.15: nur Seilzeit berücksichtigen, ohne Montage/Demontage
		setProduktivitaet(ergebnis, ProdEinheit.M3_IR_PRO_PMH15_BEIM_SEILEN, (ergebnis.getAnzahl_m3() / eSeilen.getZeitMaschine1()) / getArbeitsobjekt().getRindenAbzugFaktor());
		setProduktivitaet(ergebnis, ProdEinheit.M3_PRO_PSH15, ergebnis.getAnzahl_m3() / ergebnis.getZeitMaschine1());

		ergebnis.setRisikoVerwaltungGewinn_Prz( ePlanung.getRisikoVerwaltungGewinn_Prz());
		ergebnis.setMehrwertsteuer_Prz( ePlanung.getMehrwertsteuer_Prz());

		ergebnis.setLabelPersonal1(getArbeitssystem().getLabelPersonal1());
		ergebnis.setLabelMaschine1(getArbeitssystem().getLabelMaschine1());
		ergebnis.setLabelMaschine2(getArbeitssystem().getLabelMaschine2());
		
		
		//return ergebnis
		return ergebnis;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit1() {
		return ProdEinheit.M3_IR_PRO_PMH15_BEIM_SEILEN;
	}
	
	
	@Override
	protected ProdEinheit getProdEinheit2() {
		return ProdEinheit.M3_OR_PRO_PMH15_BEIM_SEILEN;
	}

	
	
	private Ergebnis getErgebnisPlanung() {
		//get arbeitsobjekt / arbeitssystem
		ArbeitsobjektSeilkranPlanung arbeitsObjekt = getArbeitsobjekt().getTeilobjektPlanung();
		Arbeitssystem arbeitsSystem = getArbeitssystem().getTeilsystemPlanung();
		
		//get input data
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		//get calculator
		CalculatorSeilkranPlanung calc = new CalculatorSeilkranPlanung(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	private Ergebnis getErgebnisMontage() {
		//get arbeitsobjekt / arbeitssystem
		final ArbeitsobjektKonventionellerSeilkranMontage arbeitsObjekt = getArbeitsobjekt().getTeilobjektMontage();
		final ArbeitssystemSeilkran arbeitsSystem = getArbeitssystem().getTeilsystemInstallation();
		
		//get input data
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		//get calculator
		CalculatorKonventionellerSeilkranMontage calc = new CalculatorKonventionellerSeilkranMontage(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	private Ergebnis getErgebnisSeilen() {
		//get arbeitsobjekt / arbeitssystem
		final ArbeitsobjektKonventionellerSeilkranSeilen arbeitsObjekt = getArbeitsobjekt().getTeilobjektSeilen();
		final ArbeitssystemSeilkran arbeitsSystem = getArbeitssystem().getTeilsystemSeilen();
		
		//get input data
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		//get calculator
		CalculatorKonventionellerSeilkranSeilen calc = new CalculatorKonventionellerSeilkranSeilen(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		calc.setFaktorIndirekteArbeitszeit(1.0); //Im Gesamtmodell wird mit einem anderen Wert gerechnet als im Teilmodell
		calc.setFaktorStoerzeiten(1.022); //Im Gesamtmodell wird mit einem anderen Wert gerechnet als im Teilmodell (Bug im alten Modell; nun wird auch im Teilmodell mit 1.022 gerechnet).
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	private Ergebnis getErgebnisDemontage() {
		//get arbeitsobjekt / arbeitssystem
		final ArbeitsobjektKonventionellerSeilkranDemontage arbeitsObjekt = getArbeitsobjekt().getTeilobjektDemontage();
		final ArbeitssystemSeilkran arbeitsSystem = getArbeitssystem().getTeilsystemInstallation();
		
		//get input data
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		//get calculator
		CalculatorKonventionellerSeilkranDemontage calc = new CalculatorKonventionellerSeilkranDemontage(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}
	
	
	private Ergebnis getErgebnisLagerplatz(double systemzeitSeilen_psh0proM) {
		//get arbeitsobjekt / arbeitssystem
		final ArbeitsobjektSeilkranLagerplatz arbeitsObjekt = getArbeitsobjekt().getTeilobjektLagerplatz(systemzeitSeilen_psh0proM);
		final ArbeitssystemSeilkranLagerplatz arbeitsSystem = getArbeitssystem().getTeilsystemLagerplatz();
		
		//get input data
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		//get calculator
		CalculatorSeilkranLagerplatz calc = new CalculatorSeilkranLagerplatz(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		calc.setFaktorIndirekteArbeitszeit(1.0); //Im Gesamtmodell wird mit einem anderen Wert gerechnet als im Teilmodell
		calc.setFaktorStoerzeiten(1.022); //Im Gesamtmodell wird mit einem anderen Wert gerechnet als im Teilmodell
		
		//calculate and return
		Ergebnis ergebnis = calc.calculate();
		return ergebnis;
	}

}
