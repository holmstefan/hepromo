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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelMotormanuellGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.GelaendePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.SortimentsvorgabenPanelMotormanuellGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelMotormanuellGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MotormanuellGesamt extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelMotormanuellGesamt panelBestand;
	private SortimentsvorgabenPanelMotormanuellGesamt panelSortimentsvorgaben;
	private GelaendePanel panelGelaende;
	
	protected KostensaetzePanelMotormanuellGesamt panelKostensaetze;
	protected ArbeitswegePausenPanel panelArbeitswegePausen;
	protected WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelMotormanuellGesamt model = new ModelMotormanuellGesamt();
	
	
	
	public MotormanuellGesamt() {		
		super.setTitle("Holzhauerei motormanuell");
		super.setSize((int) (570 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (645 * MainWindow.SIZE));
		
		super.initalize();
	}
	
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Bestand
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelMotormanuellGesamt(this);
		panel.add(panelBestand, c);
		
		//panel Sortimentsvorgaben
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.gridheight = 2;
//		c.insets = new Insets(5,5,5,5);
		panelSortimentsvorgaben = new SortimentsvorgabenPanelMotormanuellGesamt(this);
		panel.add(panelSortimentsvorgaben, c);
		
		//panel Gelände
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelGelaende = new GelaendePanel(this);
		panel.add(panelGelaende, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}

	@Override
	protected void initPanelArbeitssystem(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Kostensätze
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelMotormanuellGesamt(this);
		panel.add(panelKostensaetze, c);
		
		//panel Arbeitswege / Pausen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		
		//panel Weiteres
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.gridheight = 2;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}
	
	
	@Override
	protected ErgebnisPanel initErgebnisPanel() {
		ErgebnisPanel result = new ErgebnisPanel(true, true, true, false, false, true);
		result.hideDauerDerArbeit();
		return result;
	}

	
	@Override
	protected void initData() {
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		//Bestand
		model.getArbeitsobjekt().setHolzmenge_m3(					panelBestand.getHolzmenge_m3iR()				);
		model.getArbeitsobjekt().setMassenmittelstamm_m3iR(			panelBestand.getMassenmittelstamm_m3iR()		);
		model.getArbeitsobjekt().setAnteilFaellenMitHandseilzug_Prz(panelBestand.getAnteilFaellenMitHandseilzug_Prz());
		model.getArbeitsobjekt().setAnteilEntrindenVonHand_Prz(		panelBestand.getAnteilEntrindenVonHand_Prz()	);
		model.getArbeitsobjekt().setBaumartenGruppe(				panelBestand.getBaumartengruppe()				);
		model.getArbeitsobjekt().setKronenLaengenKlasse(			panelBestand.getKronenLaengenKlasse()			);
		
		//Gelände
		model.getArbeitsobjekt().setHangneigung(panelGelaende.getHangneigung());
		model.getArbeitsobjekt().setHindernisse(panelGelaende.getHindernisse());
		
		//Sortimentsvorgaben
//		model.getArbeitsobjekt().setAnteilStammholz(			panelSortimentsvorgaben.getAnteilStammholz_Prz()		);
		model.getArbeitsobjekt().setAnteilIndustrieholz_Prz(	panelSortimentsvorgaben.getAnteilIndustrieholz_Prz()	);
		model.getArbeitsobjekt().setAnteilSchichtholz_Prz(		panelSortimentsvorgaben.getAnteilSchichtholz_Prz()		);
		model.getArbeitsobjekt().setAnteilSpalten_Prz(			panelSortimentsvorgaben.getAnteilSpalten_Prz()			);
		model.getArbeitsobjekt().setStammholzStuecklaengen(		panelSortimentsvorgaben.getStuecklaengenStammholz()		);
		model.getArbeitsobjekt().setKantenBrechen(				panelSortimentsvorgaben.isKantenBrechen()				);
		model.getArbeitsobjekt().setIndustrieholzStuecklaengen(panelSortimentsvorgaben.getStuecklaengenIndustrieholz()	);
		model.getArbeitsobjekt().setSchichtholzStuecklaengen(	panelSortimentsvorgaben.getStuecklaengenSchichtholz()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH( panelKostensaetze.getAnsatzPersonal()					);
		model.getArbeitssystem().setKostensatzMaschine1_proH( panelKostensaetze.getAnsatzMotorsaegen()				);
		model.getArbeitssystem().setKostensatzMaschine2_proH( panelKostensaetze.getAnsatzSchaeleisen()				);
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min( panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min(   panelArbeitswegePausen.getWegzeitenUndPausen_min()	);
		model.getArbeitssystem().setUmsetzenBetrag_CHF(		  panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h(		 	  panelWeitereAufwaende.getUmsetzenZeit_h()				);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF( panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h(   panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}


	@Override
	public ModelMotormanuellGesamt getModel() {
		return (ModelMotormanuellGesamt) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		//Bestand
		panelBestand.setHolzmenge_m3iR(					((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getHolzmenge_m3()					);
		panelBestand.setMassenmittelstamm_m3iR(			((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getMassenmittelstamm_m3iR()			);
		panelBestand.setAnteilFaellenMitHandseilzug_Prz(((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getAnteilFaellenMitHandseilzug_Prz());
		panelBestand.setAnteilEntrindenVonHand_Prz(		((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getAnteilEntrindenVonHand_Prz()		);
		panelBestand.setBaumartengruppe(				((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getBaumartenGruppe()				);
		panelBestand.setKronenLaengenKlasse(			((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getKronenLaengenKlasse()			);
		
		//Gelände
		panelGelaende.setHangneigung(					((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getHangneigung()					);
		panelGelaende.setHindernisse(					((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getHindernisse()					);
		
		//Sorrimentsvorgaben
		panelSortimentsvorgaben.setAnteilIndustrieholz_Prz(		((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getAnteilIndustrieholz_Prz()	);
		panelSortimentsvorgaben.setAnteilSchichtholz_Prz(		((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getAnteilSchichtholz_Prz()		);
		panelSortimentsvorgaben.setAnteilSpalten_Prz(			((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getAnteilSpalten_Prz()			);
		panelSortimentsvorgaben.setStuecklaengenStammholz(		((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getStammholzStuecklaengen()		);
		panelSortimentsvorgaben.setKantenBrechen(				((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).isKantenBrechen()				);
		panelSortimentsvorgaben.setStuecklaengenIndustrieholz(	((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getIndustrieholzStuecklaengen()	);
		panelSortimentsvorgaben.setStuecklaengenSchichtholz(	((ArbeitsobjektMotormanuellGesamt) data.getArbeitsobjekt()).getSchichtholzStuecklaengen()	);
		
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				data.getArbeitssystem().getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnsatzMotorsaegen(				data.getArbeitssystem().getKostensatzMaschine1_proH()		);
		panelKostensaetze.setAnsatzSchaeleisen(				data.getArbeitssystem().getKostensatzMaschine2_proH()		);
		panelArbeitswegePausen.setArbeitszeit_min(			data.getArbeitssystem().getTaeglicheArbeitszeit_Min()	);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	data.getArbeitssystem().getWegzeitenUndPausen_Min()		);
		panelWeitereAufwaende.setUmsetzenBetrag_CHF(		data.getArbeitssystem().getUmsetzenBetrag_CHF()			);
		panelWeitereAufwaende.setUmsetzenZeit_h(			data.getArbeitssystem().getUmsetzenZeit_h()				);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(data.getArbeitssystem().getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(	data.getArbeitssystem().getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}

}
