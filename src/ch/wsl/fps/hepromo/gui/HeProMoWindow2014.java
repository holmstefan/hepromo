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
package ch.wsl.fps.hepromo.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel2014;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class HeProMoWindow2014 extends HeProMoWindow {
	
	private static final long serialVersionUID = 1L;
	
	protected KostensaetzePanel2014 panelKostensaetze;
	protected ArbeitswegePausenPanel panelArbeitswegePausen;
	protected WeitereAufwaendePanel panelWeitereAufwaende;

	
	@Override
	protected AbstractErgebnisPanel initErgebnisPanel() {
		ErgebnisPanel panel = (ErgebnisPanel) super.initErgebnisPanel();

		panel.setLabelProduktivitaet(GuiStrings.getString("HeProMoWindow2014.Produktivitaet_m3_iR_pro_PMH15")); //$NON-NLS-1$
		panel.setLabelProduktivitaet2(GuiStrings.getString("HeProMoWindow2014.m3_oR_pro_PMH15")); //$NON-NLS-1$
		panel.setLabelKostenProM3(GuiStrings.getString("HeProMoWindow2014.ProM3oR")); //$NON-NLS-1$
		
		return panel;
	}


	@Override
	protected void initPanelArbeitssystem(JPanel panel) {
		panel.removeAll();
		
		panelKostensaetze = initKostensaetzePanel();
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panelWeitereAufwaende = initWeitereAufwaendePanel();
		
		JPanel column1 = new JPanel();
		column1.setLayout(new BoxLayout(column1, BoxLayout.Y_AXIS));
		column1.add(panelKostensaetze);
		
		JPanel column2 = new JPanel();
		column2.setLayout(new BoxLayout(column2, BoxLayout.Y_AXIS));
		column2.add(panelArbeitswegePausen);
		column2.add(panelWeitereAufwaende);

		
		
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//specialpanel
		JPanel specialPanel = initSpecialPanel();
		if (specialPanel != null) {
	        c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = isSpecialPanelGridWidthSingleColumn() ? 1 : 2;
			c.anchor = GridBagConstraints.WEST;
			panel.add(specialPanel, c);
		}
		
		//column1
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.NORTH;
		panel.add(column1, c);
		
		//column2
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = isSpecialPanelGridWidthSingleColumn() ? 0 : 1;
		c.gridheight = isSpecialPanelGridWidthSingleColumn() ? 2 : 1;
		c.insets = new Insets(0,20,0,0);
		c.anchor = GridBagConstraints.NORTH;
		panel.add(column2, c);
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
		panel.add(new JPanel(), c);
	}
	
	
	protected JPanel initSpecialPanel() {
		return null;
	}
	
	protected boolean isSpecialPanelGridWidthSingleColumn() {
		return false;
	}
	
	
	protected KostensaetzePanel2014 initKostensaetzePanel() {
		return new KostensaetzePanel2014(this);
	}
	
	
	protected WeitereAufwaendePanel initWeitereAufwaendePanel() {
		return new WeitereAufwaendePanel(this);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitssystem
		Arbeitssystem arbeitssystem = getModel().getArbeitssystem();
		arbeitssystem.setKostensatzPersonal1_proH(	 		panelKostensaetze.getAnsatzPersonal1()					);
		arbeitssystem.setKostensatzMaschine1_proH( 			panelKostensaetze.getAnsatzMaschine1()					);
		arbeitssystem.setTaeglicheArbeitszeit_Min(		panelArbeitswegePausen.getArbeitszeit_min()				);
		arbeitssystem.setWegzeitenUndPausen_Min( 		panelArbeitswegePausen.getWegzeitenUndPausen_min()		);
		arbeitssystem.setUmsetzenBetrag_CHF(			panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		arbeitssystem.setUmsetzenZeit_h(		 		panelWeitereAufwaende.getUmsetzenZeit_h()				);
		arbeitssystem.setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		arbeitssystem.setWeitereAufwaendeZeit_h(		panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		
		//Arbeitssystem
		Arbeitssystem arbeitssystem = data.getArbeitssystem();
		panelKostensaetze.setAnsatzPersonal1(					arbeitssystem.getKostensatzPersonal1_proH()			);
		panelKostensaetze.setAnsatzMaschine1(					arbeitssystem.getKostensatzMaschine1_proH()			);
		panelArbeitswegePausen.setArbeitszeit_min(				arbeitssystem.getTaeglicheArbeitszeit_Min()		);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(		arbeitssystem.getWegzeitenUndPausen_Min()		);
		panelWeitereAufwaende.setUmsetzenBetrag_CHF(			arbeitssystem.getUmsetzenBetrag_CHF()			);
		panelWeitereAufwaende.setUmsetzenZeit_h(				arbeitssystem.getUmsetzenZeit_h()				);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	arbeitssystem.getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		arbeitssystem.getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}

}
