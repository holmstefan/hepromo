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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen;
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
public class CalculatorMobilseilkranGesamt extends AbstractCalculatorComposedModel {

	public CalculatorMobilseilkranGesamt(HeProMoInputData inputData) {
		super(inputData);
	}

	
	@Override
	protected ArbeitsobjektMobilseilkranGesamt getArbeitsobjekt() {
		return (ArbeitsobjektMobilseilkranGesamt) super.getArbeitsobjekt();
	}

	
	@Override
	protected ArbeitssystemSeilkranGesamt getArbeitssystem() {
		return (ArbeitssystemSeilkranGesamt) super.getArbeitssystem();
	}
	
	
	@Override
	public Ergebnis calculate() {
		
		//get teilergebnisse
		Ergebnis ePlanung 		= getErgebnisPlanung();
		Ergebnis eInstallation 	= getErgebnisInstallation();
		Ergebnis eSeilen 		= getErgebnisSeilen();
		double psh0ProM3_Seilen = eSeilen.getPsh0_hProM3();
		Ergebnis eLagerplatz 	= getErgebnisLagerplatzarbeiten(psh0ProM3_Seilen);
		
		//create neues ergebnis
		Ergebnis ergebnis = new Ergebnis();
		
		ergebnis.setAnzahl_m3(					eSeilen.getAnzahl_m3()		);

		//Bugfix: im alten HeProMo wurde für die Gesamtzeit die Summe der psh0-Werte multipliziert mit f_wegzeitenUndPausen.
		// Somit gingen die in den Teilmodellen jeweils unterschiedlichen Faktoren f_0bis15, f_indir und f_stoer im Gesamtmodell verloren.
		// Ausserdem floss die Lagerplatzzeit ebenfalls in die Gesamtzeit ein, obwohl die Lagerplatzarbeit parallel zum Seilen verläuft.
//		ergebnis.setZeitTotal(					(ePlanung.getPsh0_hProM3() + eInstallation.getPsh0_hProM3() + eSeilen.getPsh0_hProM3()*eSeilen.getAnzahl_m3() + eLagerplatz.getPsh0_hProM3()*eSeilen.getAnzahl_m3()) * 1.125);
		ergebnis.setZeitTotal(					ePlanung.getZeitTotal() + eInstallation.getZeitTotal() + eSeilen.getZeitTotal() ); //Lagerplatzarbeiten laufen parallel zum Seilen, deshalb wird Lagerplatzzeit hier nicht mitgerechnet
		
		ergebnis.setZeitPersonal(				ePlanung.getZeitPersonal() + eInstallation.getZeitPersonal() + eSeilen.getZeitPersonal() + eLagerplatz.getZeitPersonal()		);
		ergebnis.setZeitMaschine1(				eInstallation.getZeitMaschine1() + eSeilen.getZeitMaschine1() ); //Maschine1 = Seilkran
		ergebnis.setZeitMaschine2(				eLagerplatz.getZeitMaschine1()  ); //Maschine2 = Kranfahrzeug
		ergebnis.setZeitUmsetzen(				getArbeitssystem().getUmsetzenZeit_h()				);
		ergebnis.setZeitWeitereAufwaende(		getArbeitssystem().getWeitereAufwaendeZeit_h()		);
		
		ergebnis.setKostenPersonal_total(		ePlanung.getKostenPersonal_total() + eInstallation.getKostenPersonal_total() + eSeilen.getKostenPersonal_total() + eLagerplatz.getKostenPersonal_total() 	);
		ergebnis.setKostenMaschine1_total(		eInstallation.getKostenMaschine1_total() + eSeilen.getKostenMaschine1_total() ); //Maschine1 = Seilkran
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
		final ArbeitsobjektSeilkranPlanung arbeitsObjekt = getArbeitsobjekt().getTeilobjektPlanung();
		final Arbeitssystem arbeitsSystem = getArbeitssystem().getTeilsystemPlanung();

		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		CalculatorSeilkranPlanung calc = new CalculatorSeilkranPlanung(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		Ergebnis ergebnis = calc.calculate();
		
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisInstallation() {
		final ArbeitsobjektMobilseilkranInstallation arbeitsObjekt = getArbeitsobjekt().getTeilobjektInstallation();
		final ArbeitssystemSeilkran arbeitsSystem = getArbeitssystem().getTeilsystemInstallation();

		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		CalculatorMobilseilkranInstallation calc = new CalculatorMobilseilkranInstallation(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		Ergebnis ergebnis = calc.calculate();
		
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisSeilen() {
		final ArbeitsobjektMobilseilkranSeilen arbeitsObjekt = getArbeitsobjekt().getTeilobjektSeilen();
		final ArbeitssystemSeilkran arbeitsSystem = getArbeitssystem().getTeilsystemSeilen();

		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		CalculatorMobilseilkranSeilen calc = new CalculatorMobilseilkranSeilen(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		calc.setFaktorStoerzeiten(1.053); //Im Gesamtmodell wird mit einem anderen Wert gerechnet als im Einzelmodell (Bug im alten Modell; nun wird auch im Teilmodell mit 1.053 gerechnet).
		Ergebnis ergebnis = calc.calculate();
		
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisLagerplatzarbeiten(double systemzeitSeilen_psh0proM) {
		final ArbeitsobjektSeilkranLagerplatz arbeitsObjekt = getArbeitsobjekt().getTeilobjektLagerplatz(systemzeitSeilen_psh0proM);
		final ArbeitssystemSeilkranLagerplatz arbeitsSystem = getArbeitssystem().getTeilsystemLagerplatz();

		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		CalculatorSeilkranLagerplatz calc = new CalculatorSeilkranLagerplatz(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		Ergebnis ergebnis = calc.calculate();
		
		return ergebnis;
	}

}
