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

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class GelaendePanelKonvSeilkranSeilen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtHangneigung;
	private JComboBox<Hindernisse> cmbHindernisse;
	

	public GelaendePanelKonvSeilkranSeilen(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("GelaendePanelKonvSeilkranSeilen.Gelaende")));		 //$NON-NLS-1$
		
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
		JLabel lblHangneigung = new JLabel(GuiStrings.getString("GelaendePanelKonvSeilkranSeilen.Hangneigung_Prz")); //$NON-NLS-1$
		this.add(lblHangneigung, c);
		
		//Text Hangneigung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHangneigung = new JSpinner();
		this.add(txtHangneigung, c);
		
		//Label Hindernisse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHindernisse = new JLabel(GuiStrings.getString("GelaendePanelKonvSeilkranSeilen.Hindernisse")); //$NON-NLS-1$
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
		txtHangneigung.setModel(new SpinnerNumberModel(50, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHangneigung);
		
		for (int i=0; i<Hindernisse.values().length; i++) {
			cmbHindernisse.addItem(Hindernisse.values()[i]);
		}
		parent.addDefaultActionListener(cmbHindernisse);
	}
	
	
	public int getHangneigung_Prz() {
		return (Integer) txtHangneigung.getValue();
	}
	
	
	public Hindernisse getHindernisse() {
		return (Hindernisse) cmbHindernisse.getSelectedItem();
	}



	public void setHangneigung_Prz(int hangneigung_Prz) {
		txtHangneigung.setValue(hangneigung_Prz);
	}

	public void setHindernisse(Hindernisse hindernisse) {
		cmbHindernisse.setSelectedItem(hindernisse);
	}
	
	
}
