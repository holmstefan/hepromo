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

import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class Motormanuell extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	protected KostensaetzePanel panelKostensaetze;
	protected ArbeitswegePausenPanel panelArbeitswegePausen;
	protected WeitereAufwaendePanel panelWeitereAufwaende;
	
	


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
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanel(this);
		panel.add(panelKostensaetze, c);
		
		//panel Arbeitswege / Pausen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		
		//panel Weiteres
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(5,0,0,0);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);	
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 10;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);	
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}


	
	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		AbstractModel model = getModel();
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(	 	panelKostensaetze.getAnsatzPersonal()					);
		model.getArbeitssystem().setKostensatzMaschine1_proH(  		panelKostensaetze.getAnsatzMaschine()					);
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()				);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()		);
		model.getArbeitssystem().setUmsetzenBetrag_CHF(		 	panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h(		 		panelWeitereAufwaende.getUmsetzenZeit_h()				);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF( panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}
	
	
	
	@Override
	protected void initData() {
		panelKostensaetze.setLabelMaschine("Motorsäge");
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
}
