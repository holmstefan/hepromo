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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden.AnteilShUndIh;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class BestandPanelEntrinden extends BestandPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<AnteilShUndIh> cmbAnteilShUndIhLang;
	
	
	
	public BestandPanelEntrinden(HeProMoWindow parent) {
		super(parent);
		addComponents();
		initAddedComponentsData();
		
		super.cmbBaumartengruppe.removeItem(Baumartgruppe.Laubholz);
	}
	
	
	private void addComponents() {
		GridBagConstraints c;
		
		//Label Anteil Schichtholz und Langholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilSchichtholzUndLangholz = new JLabel("Anteil SH und IH-lang");
		this.add(lblAnteilSchichtholzUndLangholz, c);
		
		//Text Anteil Schichtholz und Langholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbAnteilShUndIhLang = new JComboBox<>();
		this.add(cmbAnteilShUndIhLang, c);
	}
	
	
	private void initAddedComponentsData() {
		for (int i=0; i<AnteilShUndIh.values().length; i++) {
			cmbAnteilShUndIhLang.addItem(AnteilShUndIh.values()[i]);
		}
		parent.addDefaultActionListener(cmbAnteilShUndIhLang);
	}
	
	
	public AnteilShUndIh getAnteilShUndIhLang() {
		return (AnteilShUndIh) cmbAnteilShUndIhLang.getSelectedItem();
	}


	public void setAnteilShUndIhLang(AnteilShUndIh anteilShUndIhLang) {
		cmbAnteilShUndIhLang.setSelectedItem(anteilShUndIhLang);
	}

}
