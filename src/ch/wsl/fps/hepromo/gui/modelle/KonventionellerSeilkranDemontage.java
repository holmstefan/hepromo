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
package ch.wsl.fps.hepromo.gui.modelle;

import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.KonventionellerSeilkranDemontagePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranDemontage;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranDemontage;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public class KonventionellerSeilkranDemontage extends KonventionellerSeilkranMontageDemontage {

	private static final long serialVersionUID = 1L;
	
	private ModelKonventionellerSeilkranDemontage model = new ModelKonventionellerSeilkranDemontage();
	
	
	
	public KonventionellerSeilkranDemontage() {
		super.setTitle("Konventioneller Seilkran Demontage");
		
		super.initalize();
	}
	


	@Override
	protected KonventionellerSeilkranDemontagePanel createMontageDemontagePanel() {
		return new KonventionellerSeilkranDemontagePanel(this);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setLinienLaenge_m(				panelSeilkranMontageDemontage.getLinienLaenge_m()				);
		model.getArbeitsobjekt().setAnzahlStuetzen(				panelSeilkranMontageDemontage.getAnzahlStuetzen()				);
		model.getArbeitsobjekt().setAnzahlEndmasten(			panelSeilkranMontageDemontage.getAnzahlEndmasten()				);
		model.getArbeitsobjekt().setDemontageIstSeilverlegung(	((KonventionellerSeilkranDemontagePanel) panelSeilkranMontageDemontage).isDemontageIstSeilverlegung()	);
		model.getArbeitsobjekt().setWindenTransport(			panelSeilkranMontageDemontage.getWindenTransport()				);
		model.getArbeitsobjekt().setWindenStandort(				panelSeilkranMontageDemontage.getWindenStandort()				);
		model.getArbeitsobjekt().setDistanzWindenSelbstfahrt(	panelSeilkranMontageDemontage.getDistanzWindenselbstfahrt_m()	);
	}


	@Override
	public ModelKonventionellerSeilkranDemontage getModel() {
		return (ModelKonventionellerSeilkranDemontage) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelSeilkranMontageDemontage.setLinienLaenge_m(			((ArbeitsobjektKonventionellerSeilkranMontageDemontage) data.getArbeitsobjekt()).getLinienLaenge_m()	);
		panelSeilkranMontageDemontage.setAnzahlStuetzen(			((ArbeitsobjektKonventionellerSeilkranMontageDemontage) data.getArbeitsobjekt()).getAnzahlStuetzen()	);
		panelSeilkranMontageDemontage.setAnzahlEndmasten(			((ArbeitsobjektKonventionellerSeilkranMontageDemontage) data.getArbeitsobjekt()).getAnzahlEndmasten()	);
		((KonventionellerSeilkranDemontagePanel) panelSeilkranMontageDemontage).setDemontageIstSeilverlegung(	((ArbeitsobjektKonventionellerSeilkranDemontage) 			data.getArbeitsobjekt()).isDemontageIstSeilverlegung()	);
		panelSeilkranMontageDemontage.setWindenTransport(			((ArbeitsobjektKonventionellerSeilkranMontageDemontage) data.getArbeitsobjekt()).getWindenTransport()	);
		panelSeilkranMontageDemontage.setWindenStandort(			((ArbeitsobjektKonventionellerSeilkranMontageDemontage) data.getArbeitsobjekt()).getWindenStandort()	);
		panelSeilkranMontageDemontage.setDistanzWindenselbstfahrt_m(((ArbeitsobjektKonventionellerSeilkranMontageDemontage) data.getArbeitsobjekt()).getDistanzWindenSelbstfahrt());
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				((ArbeitssystemSeilkran) data.getArbeitssystem()).getKostensatzPersonal1_proH()			);
		panelKostensaetze.setAnzahlPersonal(				((ArbeitssystemSeilkran) data.getArbeitssystem()).getAnzahlPersonal()				);
		panelKostensaetze.setAnsatzSeilkran(				((ArbeitssystemSeilkran) data.getArbeitssystem()).getKostensatzMaschine1_proH()			);
		panelKostensaetze.setLaufzeitSeilkran_Prz(			((ArbeitssystemSeilkran) data.getArbeitssystem()).getAnteilSeilkranLaufzeit_Prz()	);
		
		panelArbeitswegePausen.setArbeitszeit_min(			((ArbeitssystemSeilkran) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()		);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	((ArbeitssystemSeilkran) data.getArbeitssystem()).getWegzeitenUndPausen_Min()		);
		
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	((ArbeitssystemSeilkran) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		((ArbeitssystemSeilkran) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		
		onInputChangedBeforeCalculation(null);
		
		
		super.setReactOnInputChange(true);
	}

}
