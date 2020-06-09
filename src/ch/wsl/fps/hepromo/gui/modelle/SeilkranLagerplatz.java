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
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSeilkranLagerplatz;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.modelle.ModelSeilkranLagerplatz;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public abstract class SeilkranLagerplatz extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtSystemzeitSeilen_Psh0proM3;
	private JSpinner txtHolzmenge_m3;
	
	private KostensaetzePanelSeilkranLagerplatz panelKostensaetze;	
	private ArbeitswegePausenPanel panelArbeitswegePausen;	
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private final ModelSeilkranLagerplatz model;
	
	
	
	public SeilkranLagerplatz(ModelSeilkranLagerplatz model) {
		super.setTitle("Seilkran Lagerplatz");
		super.setSize(550, 585);
		
		this.model = model;
		
		super.initalize();
	}
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblSystemzeitSeilen = new JLabel("<html>Systemzeit Seilen (PSH0 pro m<sup>3</sup>)</html>");
		panel.add(lblSystemzeitSeilen, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtSystemzeitSeilen_Psh0proM3 = new JSpinner();
		panel.add(txtSystemzeitSeilen_Psh0proM3, c);
		

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblHolzmenge = new JLabel("<html>Holzmenge (m<sup>3</sup>)");
		panel.add(lblHolzmenge, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		txtHolzmenge_m3 = new JSpinner();
		panel.add(txtHolzmenge_m3, c);
		
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder2 = new JPanel();
		panel.add(placeholder2, c);
	}

	
	
	@Override
	protected void initPanelArbeitssystem(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelSeilkranLagerplatz(this);
		panel.add(panelKostensaetze, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 2;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this, false);
		panel.add(panelWeitereAufwaende, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 400;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setSystemzeitSeilen_psh0proM(	(Double) txtSystemzeitSeilen_Psh0proM3.getValue()	);
		model.getArbeitsobjekt().setHolzmenge_m3( 				(Double) txtHolzmenge_m3.getValue() 				);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setAnzahlPersonal(				panelKostensaetze.getAnzahlPersonal()				);
		model.getArbeitssystem().setEinsatzzeitPersonal_Prz(	panelKostensaetze.getEinsatzzeitPersonal_Prz()		);
		model.getArbeitssystem().setKostensatzMaschine1_proH(		panelKostensaetze.getAnsatzKranfahrzeug()			);
		model.getArbeitssystem().setLaufzeitKranfahrzeug_Prz(	panelKostensaetze.getLaufzeitKranfahrzeug_Prz()		);
		
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min(		panelArbeitswegePausen.getWegzeitenUndPausen_min()	);

		model.getArbeitssystem().setUmsetzenBetrag_CHF(	 	panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h(		 	panelWeitereAufwaende.getUmsetzenZeit_h()				);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h(	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}


	@Override
	public ModelSeilkranLagerplatz getModel() {
		return (ModelSeilkranLagerplatz) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		txtHolzmenge_m3.setValue(				((ArbeitsobjektSeilkranLagerplatz) data.getArbeitsobjekt()).getHolzmenge_m3()				);
		txtSystemzeitSeilen_Psh0proM3.setValue(	 ((ArbeitsobjektSeilkranLagerplatz) data.getArbeitsobjekt()).getSystemzeitSeilen_psh0proM()	);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(					  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnzahlPersonal(				(int) ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getAnzahlPersonal()				);
		panelKostensaetze.setEinsatzzeitPersonal_Prz(			  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getEinsatzzeitPersonal_Prz()		);
		panelKostensaetze.setAnsatzKranfahrzeug(				  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getKostensatzMaschine1_proH()		);
		panelKostensaetze.setLaufzeitKranfahrzeug_Prz(		 	  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getLaufzeitKranfahrzeug_Prz()		);
		
		panelArbeitswegePausen.setArbeitszeit_min(				  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()		);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(		  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getWegzeitenUndPausen_Min()		);
		
		panelWeitereAufwaende.setUmsetzenBetrag_CHF(	 		  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getUmsetzenBetrag_CHF()			);
		panelWeitereAufwaende.setUmsetzenZeit_h(		 		  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getUmsetzenZeit_h()				);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		  ((ArbeitssystemSeilkranLagerplatz) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}
	


	@Override
	protected void initData() {
		txtSystemzeitSeilen_Psh0proM3.setModel(new SpinnerNumberModel(0.13, 0.01, 10, 0.01));
		txtSystemzeitSeilen_Psh0proM3.setEditor(new JSpinner.NumberEditor(txtSystemzeitSeilen_Psh0proM3, "0.##"));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtSystemzeitSeilen_Psh0proM3);
		
		txtHolzmenge_m3.setModel(new SpinnerNumberModel(400.0, 0, 50000, 50));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3);

		((ErgebnisPanel) ergebnisPanel).setLabelUmsetzen("Kranfahrzeug umsetzen");
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
	
}
