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
import ch.wsl.fps.hepromo.model.ErgebnisHelikopterFliegen;
import ch.wsl.fps.hepromo.model.ErgebnisHelikopterGesamt;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterGesamt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorHelikopterGesamt extends AbstractCalculatorComposedModel {

	public CalculatorHelikopterGesamt(HeProMoInputData inputData) {
		super(inputData);
	}
	
	
	@Override
	public ArbeitsobjektHelikopterGesamt getArbeitsobjekt() {
		return (ArbeitsobjektHelikopterGesamt) super.getArbeitsobjekt();
	}
	
	
	@Override
	public ArbeitssystemHelikopterGesamt getArbeitssystem() {
		return (ArbeitssystemHelikopterGesamt) super.getArbeitssystem();
	}
	

	
	@Override
	public ErgebnisHelikopterGesamt calculate() {
		
		//Konsistenz Baumart/Holztyp überprüfen
		BaumartenGruppe ba = getArbeitsobjekt().getTeilobjektAufarbeiten().getBaumartenGruppe();
		Holztyp ht = getArbeitsobjekt().getTeilobjektFliegen().getHolztyp();		
		if (ba == BaumartenGruppe.Laubholz && (ht == Holztyp.Nadelholz_frisch || ht == Holztyp.Nadelholz_angetrocknet) ) {
			throw new RuntimeException("Baumarten nicht konsistent!");
		}
		
		
		
		//get teilergebnisse
		ErgebnisHelikopterFliegen eFliegen = getErgebnisFliegen();
		Ergebnis eAufarbeiten = getErgebnisAufarbeiten();

		//create neues ergebnis
		ErgebnisHelikopterGesamt ergebnis = new ErgebnisHelikopterGesamt();
		
		if (getArbeitssystem().isKalkulationInklLagerplatzarbeit() == true) {
			ergebnis.setAnzahl_m3(					eFliegen.getAnzahl_m3()						);
			
			ergebnis.setZeitTotalFliegen(			eFliegen.getZeitTotal()					);
			ergebnis.setZeitTotalAbsenkplatz(		eAufarbeiten.getZeitTotal()				);
			
			ergebnis.setZeitHelifirma(				eFliegen.getZeitMaschine1()				);
			ergebnis.setZeitPersonal(				eFliegen.getZeitPersonal() + eAufarbeiten.getZeitPersonal()	);
			ergebnis.setZeitMaschine1(				eAufarbeiten.getZeitMaschine1()			);
			ergebnis.setZeitMaschine2(				eAufarbeiten.getZeitMaschine2()			);
			ergebnis.setZeitUmsetzen(				eAufarbeiten.getZeitUmsetzen()			);
			ergebnis.setZeitWeitereAufwaende(		eFliegen.getZeitWeitereAufwaende()	);
			
			ergebnis.setKostenHeli_total(			eFliegen.getKostenMaschine1_total()		);
			ergebnis.setKostenPersonal_total(		eFliegen.getKostenPersonal_total() + eAufarbeiten.getKostenPersonal_total()	);
			ergebnis.setKostenMaschine1_total(		eAufarbeiten.getKostenMaschine1_total()	);
			ergebnis.setKostenMaschine2_total(		eAufarbeiten.getKostenMaschine2_total()	);
			ergebnis.setKostenUmsetzen_total(		eAufarbeiten.getKostenUmsetzen_total()	);
			ergebnis.setKostenWeitereAufwaende_total(eFliegen.getKostenWeitereAufwaende_total()	);
			
			ergebnis.setRotationszeit(				eFliegen.getRotationszeit()				);
			
			ergebnis.setRisikoVerwaltungGewinn_Prz( eFliegen.getRisikoVerwaltungGewinn_Prz());
			ergebnis.setMehrwertsteuer_Prz( 		eFliegen.getMehrwertsteuer_Prz());
		}
		
		else {	
			ergebnis.setAnzahl_m3(					eFliegen.getAnzahl_m3()					);
			
			ergebnis.setZeitTotalFliegen(			eFliegen.getZeitTotal()					);
			ergebnis.setZeitTotalAbsenkplatz(		0										);
			
			ergebnis.setZeitHelifirma(				eFliegen.getZeitMaschine1()				);
			ergebnis.setZeitPersonal(				eFliegen.getZeitPersonal()				);
			ergebnis.setZeitMaschine1(				0										);
			ergebnis.setZeitMaschine2(				0										);
			ergebnis.setZeitUmsetzen(				eAufarbeiten.getZeitUmsetzen()			);
			ergebnis.setZeitWeitereAufwaende(		eFliegen.getZeitWeitereAufwaende()	);
			
			ergebnis.setKostenHeli_total(			eFliegen.getKostenMaschine1_total()		);
			ergebnis.setKostenPersonal_total(		eFliegen.getKostenPersonal_total()		);
			ergebnis.setKostenMaschine1_total(		0										);
			ergebnis.setKostenMaschine2_total(		0										);
			ergebnis.setKostenUmsetzen_total(		eAufarbeiten.getKostenUmsetzen_total()	);
			ergebnis.setKostenWeitereAufwaende_total(eFliegen.getKostenWeitereAufwaende_total()	);
			
			ergebnis.setRotationszeit(				eFliegen.getRotationszeit()				);
			
			ergebnis.setRisikoVerwaltungGewinn_Prz( eFliegen.getRisikoVerwaltungGewinn_Prz());
			ergebnis.setMehrwertsteuer_Prz( 		eFliegen.getMehrwertsteuer_Prz());
		}

		ergebnis.setLabelPersonal1(getArbeitssystem().getLabelPersonal1());
		ergebnis.setLabelMaschine1(getArbeitssystem().getLabelMaschine1());
		ergebnis.setLabelMaschine2(getArbeitssystem().getLabelMaschine2());
		
		return ergebnis;
	}

	

	
	private ErgebnisHelikopterFliegen getErgebnisFliegen() {
		final ArbeitsobjektHelikopterFliegen arbeitsObjekt = getArbeitsobjekt().getTeilobjektFliegen();
		final ArbeitssystemHelikopterFliegen arbeitsSystem = getArbeitssystem().getTeilsystemFliegen();
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		CalculatorHelikopterFliegen calc = new CalculatorHelikopterFliegen(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		ErgebnisHelikopterFliegen ergebnis = calc.calculate();
		
		return ergebnis;
	}
	
	
	
	private Ergebnis getErgebnisAufarbeiten() {
		final ArbeitsobjektHelikopterAufarbeiten arbeitsObjekt = getArbeitsobjekt().getTeilobjektAufarbeiten();
		final ArbeitssystemHelikopterAufarbeiten arbeitsSystem = getArbeitssystem().getTeilsystemAufarbeiten();
		HeProMoInputData inputData = createHeProMoInputData(arbeitsObjekt, arbeitsSystem, faktoren);
		
		CalculatorHelikopterAufarbeiten calc = new CalculatorHelikopterAufarbeiten(inputData);
		calc.setRindenAbzugBeruecksichtigen(this.rindenAbzugBeruecksichtigen);
		Ergebnis ergebnis = calc.calculate();
		
		return ergebnis;
	}
	

}
