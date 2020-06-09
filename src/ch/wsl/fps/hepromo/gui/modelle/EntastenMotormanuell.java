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

import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelEntasten;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.GelaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellEntasten;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public class EntastenMotormanuell extends Motormanuell {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelEntasten panelBestand;
	private GelaendePanel panelGelaende;
	
	private ModelMotormanuellEntasten model = new ModelMotormanuellEntasten();
	
	
	
	public EntastenMotormanuell() {		
		super.setTitle("Entasten motormanuell");
		super.setSize(550, 585);
		
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
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelEntasten(this);
		panel.add(panelBestand, c);
		
		//panel Gelände
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelGelaende = new GelaendePanel(this);
		panel.add(panelGelaende, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}
	
	
	
	/*
	 * GETTERS AND SETTERS
	 */

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setBaumartenGruppe(		panelBestand.getBaumartengruppe()		);
		model.getArbeitsobjekt().setHangneigung(			panelGelaende.getHangneigung()			);
		model.getArbeitsobjekt().setHindernisse(			panelGelaende.getHindernisse()			);
		model.getArbeitsobjekt().setMassenmittelstamm_m3iR(	panelBestand.getMassenmittelstamm_m3iR());
		model.getArbeitsobjekt().setHolzmenge_m3(			panelBestand.getHolzmenge_m3iR()		);
		model.getArbeitsobjekt().setKronenLaengenKlasse(	panelBestand.getKronenLaengenKlasse()	);	
	}


	@Override
	public ModelMotormanuellEntasten getModel() {
		return (ModelMotormanuellEntasten) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelBestand.setBaumartengruppe(		((ArbeitsobjektMotormanuell) data.getArbeitsobjekt()).getBaumartenGruppe()		);
		panelGelaende.setHangneigung(			((ArbeitsobjektMotormanuell) data.getArbeitsobjekt()).getHangneigung()			);
		panelGelaende.setHindernisse(			((ArbeitsobjektMotormanuell) data.getArbeitsobjekt()).getHindernisse()			);
		panelBestand.setMassenmittelstamm_m3iR(	((ArbeitsobjektMotormanuell) data.getArbeitsobjekt()).getMassenmittelstamm_m3iR());
		panelBestand.setHolzmenge_m3iR(										 data.getArbeitsobjekt().getHolzmenge_m3()			);
		panelBestand.setKronenLaengenKlasse(	((ArbeitsobjektEntastenMotormanuell) data.getArbeitsobjekt()).getKronenLaengenKlasse()	);	
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				data.getArbeitssystem().getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnsatzMaschine(				data.getArbeitssystem().getKostensatzMaschine1_proH()		);
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

}
