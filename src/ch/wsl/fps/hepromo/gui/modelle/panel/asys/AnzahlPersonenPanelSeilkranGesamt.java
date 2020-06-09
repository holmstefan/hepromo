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
package ch.wsl.fps.hepromo.gui.modelle.panel.asys;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class AnzahlPersonenPanelSeilkranGesamt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;
	
	private JSpinner txtInstallation = new JSpinner();
	private JSpinner txtSeilen = new JSpinner();
	private JSpinner txtLagerplatz = new JSpinner();
	private JSpinner txtEinsatzzeit = new JSpinner();
	
	
	
	public AnzahlPersonenPanelSeilkranGesamt(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}

	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Planung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblPlanung = new JLabel(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.Planung")); //$NON-NLS-1$
		this.add(lblPlanung, c);
		
		//InfoButton Planung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,0,2,0);
		JLabel lblPlanung_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.PlanungInfoButton")); //$NON-NLS-1$
		this.add(lblPlanung_Info, c);
		
		
		
		//Label Installation
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblInstallation = new JLabel(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.Installation")); //$NON-NLS-1$
		this.add(lblInstallation, c);
		
		//Text Installation
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtInstallation, c);
		
		
		
		//Label Seilen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblSeilen = new JLabel(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.Seilen")); //$NON-NLS-1$
		this.add(lblSeilen, c);
		
		//Text Seilen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtSeilen, c);
		
		
		
		//Label Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLagerplatz = new JLabel(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.Lagerplatz")); //$NON-NLS-1$
		this.add(lblLagerplatz, c);
		
		//Text Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtLagerplatz, c);
		
		
		
		//Label Einsatzzeit
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblEinsatzzeit = new JLabel(GuiStrings.getString("AnzahlPersonenPanelSeilkranGesamt.Einsatzzeit_Prz")); //$NON-NLS-1$
		this.add(lblEinsatzzeit, c);
		
		//Text Einsatzzeit
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtEinsatzzeit, c);
	}

	
	
	private void initData() {
		txtInstallation.setModel(	new SpinnerNumberModel(3, 1, 5, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtInstallation);
		
		txtSeilen.setModel(			new SpinnerNumberModel(3, 2, 5, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtSeilen);
		
		txtLagerplatz.setModel(		new SpinnerNumberModel(1, 1, 5, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLagerplatz);
		
		txtEinsatzzeit.setModel(	new SpinnerNumberModel(100, 0, 100, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEinsatzzeit);
	}
	
	
	public int getAnzahlPersonenInstallation(){
		return (Integer) txtInstallation.getValue();
	}
	
	
	public int getAnzahlPersonenSeilen(){
		return (Integer) txtSeilen.getValue();
	}
	
	
	public int getAnzahlPersonenLagerplatz(){
		return (Integer) txtLagerplatz.getValue();
	}
	
	
	public int getEinsatzzeitAnteilLagerplatz(){
		return (Integer) txtEinsatzzeit.getValue();
	}

	public void setAnzahlPersonenSeilen(int anzahlPersonenSeilen) {
		txtSeilen.setValue(anzahlPersonenSeilen);
	}

	public void setAnzahlPersonenInstallation(int anzahlPersonenInstallation) {
		txtInstallation.setValue(anzahlPersonenInstallation);
	}

	public void setAnzahlPersonenLagerplatz(int anzahlPersonenLagerplatz) {
		txtLagerplatz.setValue(anzahlPersonenLagerplatz);
	}

	public void setEinsatzzeitAnteilLagerplatz(int einsatzzeitPersonenLagerplatz_Prz) {
		txtEinsatzzeit.setValue(einsatzzeitPersonenLagerplatz_Prz);
	}
	
}
