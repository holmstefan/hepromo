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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelRadharvester extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	protected JComboBox<Baumartgruppe> cmbBaumartengruppe;
	private JSpinner txtVolumenmittelstamm;
	private JSpinner txtHolzmenge;
	

	public BestandPanelRadharvester(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder("Bestand"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblBaumartengruppe = new JLabel("Baumartengruppe"); 
		this.add(lblBaumartengruppe, c);
		
		//Combo Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbBaumartengruppe = new JComboBox<>();
		this.add(cmbBaumartengruppe, c);
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);
		
		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHolzmenge = new JLabel("<html>Holzmenge (m<sup>3</sup> i.R.)</html>"); 
		this.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHolzmenge = new JSpinner();
		this.add(txtHolzmenge, c);
		
		//Label Volumenmittelstamm
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblVolumenmittelstamm = new JLabel("<html>Volumen-Mittelstamm (m<sup>3</sup>)</html>"); 
		this.add(lblVolumenmittelstamm, c);
		
		//Label Volumenmittelstamm Hinweis 1
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblVolumenmittelstammH1 = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;Wertebereich: >= 0.07m<sup>3</sup> !</html>"); 
		this.add(lblVolumenmittelstammH1, c);
		
		//Label Volumenmittelstamm Hinweis 2
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.gridwidth = 4;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblVolumenmittelstammH2 = new JLabel("    Ermittlung aus BHD, Höhe und Tarif (Forstkalender 2003, S. 190)"); 
		this.add(lblVolumenmittelstammH2, c);
		
		//Text Volumenmittelstamm
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtVolumenmittelstamm = new JSpinner();
		this.add(txtVolumenmittelstamm, c);
	}
	
	
	private void initData(){
		for (int i=0; i<Baumartgruppe.values().length; i++) {
			cmbBaumartengruppe.addItem(Baumartgruppe.values()[i]);
		}
		parent.addDefaultActionListener(cmbBaumartengruppe);

		txtVolumenmittelstamm.setModel(new SpinnerNumberModel(1.2, 0, 100, 0.001));
		txtVolumenmittelstamm.setEditor(new JSpinner.NumberEditor(txtVolumenmittelstamm, "0.####")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtVolumenmittelstamm);
		
		txtHolzmenge.setModel(new SpinnerNumberModel(100.0, 0, 100000, 1));
		txtHolzmenge.setEditor(new JSpinner.NumberEditor(txtHolzmenge, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge);	
	}

	
	
	public Baumartgruppe getBaumartengruppe() {
		return (Baumartgruppe) cmbBaumartengruppe.getSelectedItem();
	}
	
	public double getVolumenmittelstamm_m3() {
		return (Double) txtVolumenmittelstamm.getValue();
	}
	
	public double getHolzmenge_m3iR() {
		return (Double) txtHolzmenge.getValue();
	}
	
	
	
	public void setHolzmenge_m3iR(double amount) {
		txtHolzmenge.setValue(amount);
	}

	public void setBaumartengruppe(Baumartgruppe baumartGruppe) {
		cmbBaumartengruppe.setSelectedItem(baumartGruppe);
	}

	public void setVolumenmittelstamm_m3(double volumenMittelstamm_m3) {
		txtVolumenmittelstamm.setValue(volumenMittelstamm_m3);
	}

}

