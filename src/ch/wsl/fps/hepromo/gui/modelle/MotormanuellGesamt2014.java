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
import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel2014;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMotormanuell2014;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellGesamt2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MotormanuellGesamt2014 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelMotormanuellGesamt2014 panelBestand;
	
	private ModelMotormanuellGesamt2014 model = new ModelMotormanuellGesamt2014();
	
	
	
	public MotormanuellGesamt2014() {		
		super.setTitle(GuiStrings.getString("MotormanuellGesamt2014.Title")); //$NON-NLS-1$
		super.setSize((int) (720 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (630 * MainWindow.SIZE));
		
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
		c.weightx = 12;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelMotormanuellGesamt2014(this);
		panel.add(panelBestand, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}
	
	
	@Override
	protected KostensaetzePanel2014 initKostensaetzePanel() {
		return new KostensaetzePanel2014(this, true);
	}

	
	@Override
	protected void initData() {
		panelKostensaetze.setLabelMaschine1(GuiStrings.getString("MotormanuellGesamt2014.lblMotorsaege")); //$NON-NLS-1$

		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet(GuiStrings.getString("MotormanuellGesamt2014.lblProduktivitaet")); //$NON-NLS-1$
		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet2(GuiStrings.getString("MotormanuellGesamt2014.lblProduktivitaet2")); //$NON-NLS-1$
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		panelBestand.updateLabelHolzmengeMitRindenabzug(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		//Arbeitsobjekt
		//Bestand
		model.getArbeitsobjekt().setHolzmenge_m3(			panelBestand.getHolzmenge_m3()			);
		model.getArbeitsobjekt().setBhd_cm(					panelBestand.getBhd_cm()				);
		model.getArbeitsobjekt().setAnteilLaubholz_Prz(		panelBestand.getAnteilLaubholz_Prz()	);
		model.getArbeitsobjekt().setAnteilKiefer_Prz(		panelBestand.getAnteilKiefer_Prz()		);
		model.getArbeitsobjekt().setRegion(					panelBestand.getRegion()				);
		
		
		//Arbeitssystem
		ArbeitssystemMotormanuell2014 arbeitssystemMotormanuell2014 = (ArbeitssystemMotormanuell2014) getModel().getArbeitssystem();
		arbeitssystemMotormanuell2014.setKostensatzPersonal2_proH( panelKostensaetze.getAnsatzPersonal2() );
		arbeitssystemMotormanuell2014.setKostensatzPersonal3_proH( panelKostensaetze.getAnsatzPersonal3() );
		arbeitssystemMotormanuell2014.setKostensatzPersonal4_proH( panelKostensaetze.getAnsatzPersonal4() );
		arbeitssystemMotormanuell2014.setEinsatzanteilPersonal1_Prz( panelKostensaetze.getEinsatzanteilPersonal1_Prz() );
		arbeitssystemMotormanuell2014.setEinsatzanteilPersonal2_Prz( panelKostensaetze.getEinsatzanteilPersonal2_Prz() );
		arbeitssystemMotormanuell2014.setEinsatzanteilPersonal3_Prz( panelKostensaetze.getEinsatzanteilPersonal3_Prz() );
		arbeitssystemMotormanuell2014.setEinsatzanteilPersonal4_Prz( panelKostensaetze.getEinsatzanteilPersonal4_Prz() );
	}


	@Override
	public ModelMotormanuellGesamt2014 getModel() {
		return (ModelMotormanuellGesamt2014) model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);
		
		//Arbeitsobjekt
		//Bestand
		panelBestand.setHolzmenge_m3(										 	   data.getArbeitsobjekt().getHolzmenge_m3()		);
		panelBestand.setBhd_cm(				((ArbeitsobjektMotormanuellGesamt2014) data.getArbeitsobjekt()).getBhd_cm()				);
		panelBestand.setAnteilLaubholz_Prz(	((ArbeitsobjektMotormanuellGesamt2014) data.getArbeitsobjekt()).getAnteilLaubholz_Prz()	);
		panelBestand.setAnteilKiefer_Prz(	((ArbeitsobjektMotormanuellGesamt2014) data.getArbeitsobjekt()).getAnteilKiefer_Prz()	);
		panelBestand.setRegion(				((ArbeitsobjektMotormanuellGesamt2014) data.getArbeitsobjekt()).getRegion()				);
		
		
		//Arbeitssystem
		ArbeitssystemMotormanuell2014 arbeitssystemMotormanuell2014 = (ArbeitssystemMotormanuell2014) data.getArbeitssystem();
		panelKostensaetze.setAnsatzPersonal2( arbeitssystemMotormanuell2014.getKostensatzPersonal2_proH() );
		panelKostensaetze.setAnsatzPersonal3( arbeitssystemMotormanuell2014.getKostensatzPersonal3_proH() );
		panelKostensaetze.setAnsatzPersonal4( arbeitssystemMotormanuell2014.getKostensatzPersonal4_proH() );
		panelKostensaetze.setEinsatzzeitanteilPersonal1_Prz( arbeitssystemMotormanuell2014.getEinsatzanteilPersonal1_Prz() );
		panelKostensaetze.setEinsatzzeitanteilPersonal2_Prz( arbeitssystemMotormanuell2014.getEinsatzanteilPersonal2_Prz() );
		panelKostensaetze.setEinsatzzeitanteilPersonal3_Prz( arbeitssystemMotormanuell2014.getEinsatzanteilPersonal3_Prz() );
		panelKostensaetze.setEinsatzzeitanteilPersonal4_Prz( arbeitssystemMotormanuell2014.getEinsatzanteilPersonal4_Prz() );
		
		
		super.setReactOnInputChange(true);
	}
	

	@Override
	public void onInputChangedBeforeCalculation(Object eventSource) {
		panelBestand.onInputChangedBeforeCalculation(eventSource);
		panelKostensaetze.onInputChangedBeforeCalculation(eventSource);
	}

}
