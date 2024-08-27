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
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.KonventionellerSeilkranMontageDemontagePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSeilkran;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public abstract class KonventionellerSeilkranMontageDemontage extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	protected KonventionellerSeilkranMontageDemontagePanel panelSeilkranMontageDemontage;
	
	protected KostensaetzePanelSeilkran panelKostensaetze;	
	protected ArbeitswegePausenPanel panelArbeitswegePausen;	
	protected WeitereAufwaendePanel panelWeitereAufwaende;
	
	
	
	public KonventionellerSeilkranMontageDemontage() {
		super.setSize(600, 565);
	}
	
	
	protected abstract KonventionellerSeilkranMontageDemontagePanel createMontageDemontagePanel();
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelSeilkranMontageDemontage = createMontageDemontagePanel();
		panel.add(panelSeilkranMontageDemontage, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 400;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
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
//		c.gridwidth = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelSeilkran(this);
		panel.add(panelKostensaetze, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this, true);
		panel.add(panelWeitereAufwaende, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 400;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
	}
	
	
	@Override
	protected ErgebnisPanel initErgebnisPanel() {
		return new ErgebnisPanel.Builder()
				.enableRowPersonal()
				.enableRowMaschine1()
				.build();
	}
	
	
	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		ArbeitssystemSeilkran arbeitssystem = (ArbeitssystemSeilkran) getModel().getArbeitssystem();
		
		arbeitssystem.setKostensatzPersonal1_proH(			panelKostensaetze.getAnsatzPersonal()				);
		arbeitssystem.setAnzahlPersonal(				panelKostensaetze.getAnzahlPersonal()				);
		arbeitssystem.setKostensatzMaschine1_proH(			panelKostensaetze.getAnsatzSeilkran()				);
		arbeitssystem.setAnteilSeilkranLaufzeit_Prz(	panelKostensaetze.getLaufzeitSeilkran_Prz()			);
		
		arbeitssystem.setTaeglicheArbeitszeit_Min(		panelArbeitswegePausen.getArbeitszeit_min()			);
		arbeitssystem.setWegzeitenUndPausen_Min(		panelArbeitswegePausen.getWegzeitenUndPausen_min()	);
		
		arbeitssystem.setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF());
		arbeitssystem.setWeitereAufwaendeZeit_h(		panelWeitereAufwaende.getWeitereAufwaendeZeit_h()	);
	}


	@Override
	protected void initData() { 
		panelKostensaetze.setAnsatzSeilkran(70);
		panelKostensaetze.setLaufzeitSeilkran_Prz(20);
	}
	

	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelSeilkranMontageDemontage.onInputChangedBeforeCalculation(eventSource);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
}
