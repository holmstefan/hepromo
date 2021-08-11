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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester.MaschinenKategorie;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester.MaschinenTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class RadharvesterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<MaschinenKategorie> cmbMaschinenKategorie;
	private JComboBox<MaschinenTyp> cmbMaschinenTyp;
	private JTextField txtMaxFaellDrm;

	
	public RadharvesterPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder("Radharvester"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Maschinenkategorie
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		JLabel lblMaschinenKategorie = new JLabel("Maschinenkategorie"); 
		this.add(lblMaschinenKategorie, c);
		
		//Combo Maschinenkategorie
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		cmbMaschinenKategorie = new JComboBox<>();
		parent.addDefaultActionListener(cmbMaschinenKategorie);
		this.add(cmbMaschinenKategorie, c);
		
		//Label Maschinentyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,3,0);
		JLabel lblMaschinenTyp = new JLabel("Maschinentyp"); 
		this.add(lblMaschinenTyp, c);
		
		//Combo Maschinentyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,8,0);
		cmbMaschinenTyp = new JComboBox<>();
		cmbMaschinenTyp.setMaximumRowCount(15);
		parent.addDefaultActionListener(cmbMaschinenTyp);
		this.add(cmbMaschinenTyp, c);	
		
		//Label MaxFaellDrm
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		JLabel lblMaxFaellDrm = new JLabel("Maximaler Fälldurchmesser (cm)"); 
		this.add(lblMaxFaellDrm, c);
		
		//Text MaxFaellDrm
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		txtMaxFaellDrm = new JTextField();
		txtMaxFaellDrm.setEditable(false);
		this.add(txtMaxFaellDrm, c);	
		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 1;
//		c.gridy = 2;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
//		this.add(new JPanel(), c);	
		
	}
	
	
	

	private void initData() {		
		for (int i=0; i<MaschinenKategorie.values().length; i++) {
			cmbMaschinenKategorie.addItem(MaschinenKategorie.values()[i]);
		}
		setMaschinenKategorie(MaschinenKategorie.Gross);
	}	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {

		if (cmbMaschinenKategorie.equals(eventSource)) {
			//get relevant category
			MaschinenKategorie mKat = (MaschinenKategorie) cmbMaschinenKategorie.getSelectedItem();

			//clear and refill type-combo
			cmbMaschinenTyp.removeAllItems();
			for (MaschinenTyp mTyp : MaschinenTyp.values()) {
				if (mTyp.getMaschinenKategorie() == mKat) {
					cmbMaschinenTyp.addItem(mTyp);
				}
			}
		}
		
		
		if (cmbMaschinenKategorie.equals(eventSource) || cmbMaschinenTyp.equals(eventSource) ) {
			//get selected type
			MaschinenTyp mTyp = (MaschinenTyp) cmbMaschinenTyp.getSelectedItem();
			if (mTyp == null) {
				return;
			}
			
			//refresh max diameter
			int maxDrm = mTyp.getMaxFaellDrm_cm();
			txtMaxFaellDrm.setText("" + maxDrm); 
		}
		
	}
	
	
	public MaschinenKategorie getMaschinenKategorie() {
		return (MaschinenKategorie) cmbMaschinenKategorie.getSelectedItem();
	}
	
	
	public MaschinenTyp getMaschinenTyp() {
		return (MaschinenTyp) cmbMaschinenTyp.getSelectedItem();
	}



	public void setMaschinenKategorie(MaschinenKategorie maschinenKategorie) {
		cmbMaschinenKategorie.setSelectedItem(maschinenKategorie);
		onInputChangedBeforeCalculation(cmbMaschinenKategorie);
	}


	public void setMaschinenTyp(MaschinenTyp maschinenTyp) {
		cmbMaschinenTyp.setSelectedItem(maschinenTyp);
		onInputChangedBeforeCalculation(cmbMaschinenTyp);
	}

}
