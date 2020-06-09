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
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Hindernisse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class GelaendePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<Hangneigung> cmbHangneigung;
	private JComboBox<Hindernisse> cmbHindernisse;
	

	public GelaendePanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Gelände"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Hangneigung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHangneigung = new JLabel("Hangneigung"); 
		this.add(lblHangneigung, c);
		
		//Combo Hangneigung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbHangneigung = new JComboBox<>();
		this.add(cmbHangneigung, c);
		
		//Label Hindernisse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHindernisse = new JLabel("Hindernisse"); 
		this.add(lblHindernisse, c);
		
		//Combo Hindernisse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbHindernisse = new JComboBox<>();
		this.add(cmbHindernisse, c);	
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(new JPanel(), c);	
		
	}
	
	
	

	private void initData() {		
		for (int i=0; i<Hangneigung.values().length; i++) {
			cmbHangneigung.addItem(Hangneigung.values()[i]);
		}
		parent.addDefaultActionListener(cmbHangneigung);
		
		for (int i=0; i<Hindernisse.values().length; i++) {
			cmbHindernisse.addItem(Hindernisse.values()[i]);
		}
		parent.addDefaultActionListener(cmbHindernisse);
	}
	
	
	public Hangneigung getHangneigung() {
		return (Hangneigung) cmbHangneigung.getSelectedItem();
	}
	
	
	public Hindernisse getHindernisse() {
		return (Hindernisse) cmbHindernisse.getSelectedItem();
	}


	public void setHangneigung(Hangneigung hangneigung) {
		cmbHangneigung.setSelectedItem(hangneigung);
	}


	public void setHindernisse(Hindernisse hindernisse) {
		cmbHindernisse.setSelectedItem(hindernisse);
	}
	
}
