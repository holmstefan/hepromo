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
package ch.wsl.fps.hepromo.gui.modelle.panel.aobj;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell.KronenLaengenKlasse;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class BestandPanelEntasten extends BestandPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<KronenLaengenKlasse> cmbKronenLaengenKlasse;
	
	
	public BestandPanelEntasten(HeProMoWindow parent) {
		super(parent);
		addComponents();
		initAddedComponentsData();
	}
	
	
	private void addComponents() {
		GridBagConstraints c;
		
		//Label Kronenlängenklasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblKronenLaengenKlasse = new JLabel("Kronenlängen-Klasse");
		this.add(lblKronenLaengenKlasse, c);
		
		//Text Kronenlängenklasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbKronenLaengenKlasse = new JComboBox<>();
		this.add(cmbKronenLaengenKlasse, c);
	}
	
	
	private void initAddedComponentsData() {
		for (int i=0; i<KronenLaengenKlasse.values().length; i++) {
			cmbKronenLaengenKlasse.addItem(KronenLaengenKlasse.values()[i]);
		}
		parent.addDefaultActionListener(cmbKronenLaengenKlasse);
	}
	
	
	public KronenLaengenKlasse getKronenLaengenKlasse() {
		return (KronenLaengenKlasse) cmbKronenLaengenKlasse.getSelectedItem();
	}


	public void setKronenLaengenKlasse(KronenLaengenKlasse kronenLaengenKlasse) {
		cmbKronenLaengenKlasse.setSelectedItem(kronenLaengenKlasse);
	}
}
