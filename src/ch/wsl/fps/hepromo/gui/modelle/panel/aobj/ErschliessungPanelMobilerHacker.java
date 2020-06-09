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
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErschliessungPanelMobilerHacker extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;
	
	private JSpinner txtFahrstreckenAufStrasse;
	private JSpinner txtFahrstreckenFeinerschliessung;
	private JTextField txtFahrstreckenTotal;
	private JSpinner txtHolzlagerLaenge;
	private JComboBox<HindernisKategorie> cmbHindernisKategorie;
	private JComboBox<NeigungsKategorie> cmbNeigungskategorie;
	

	public ErschliessungPanelMobilerHacker(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Erschliessung"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Fahrstrecken
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblFahrstrecken = new JLabel("Fahrstrecken Container-Hackort"); 
		this.add(lblFahrstrecken, c);

		
		//Label Fahrstrecken auf Strasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		JLabel lblFahrstreckenAufStrasse = new JLabel("Auf Strasse (m)"); 
		this.add(lblFahrstreckenAufStrasse, c);
		
		//Text Fahrstrecken auf Strasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAufStrasse = new JSpinner(new SpinnerNumberModel(50, 0, 2000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAufStrasse);
		this.add(txtFahrstreckenAufStrasse, c);

		
		//Label Fahrstrecken Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		JLabel lblFahrstreckenFeinerschliessung = new JLabel("Auf Feinerschliessung (m)"); 
		this.add(lblFahrstreckenFeinerschliessung, c);
		
		//Text Fahrstrecken Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenFeinerschliessung = new JSpinner(new SpinnerNumberModel(0, 0, 2000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenFeinerschliessung);
		this.add(txtFahrstreckenFeinerschliessung, c);

		
		//Label Fahrstrecken Total
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		JLabel lblFahrstreckenTotal = new JLabel("Total (m)"); 
		this.add(lblFahrstreckenTotal, c);
		
		//Text Fahrstrecken Total
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenTotal = new JTextField();
		txtFahrstreckenTotal.setEditable(false);
		this.add(txtFahrstreckenTotal, c);

		
		//Label Länge an der Holz liegt
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblHolzlagerLaenge = new JLabel("Länge an der Holz liegt (m)"); 
		this.add(lblHolzlagerLaenge, c);
		
		//Text Laenge an der Holz liegt
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		txtHolzlagerLaenge = new JSpinner(new SpinnerNumberModel(200, 0, 2000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzlagerLaenge);
		this.add(txtHolzlagerLaenge, c);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);

		//***************** 2. Spalte


		
		//Label Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblFeinerschliessung = new JLabel("Feinerschliessung"); 
		this.add(lblFeinerschliessung, c);

		
		//Label Hindernis-Kategorie
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		JLabel lblHindernisKategorie = new JLabel("Hindernis-Kategorie"); 
		this.add(lblHindernisKategorie, c);
		
		//Combo Hindernis-Kategorie
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbHindernisKategorie = new JComboBox<>();
		this.add(cmbHindernisKategorie, c);

		
		//Label Neigungs-Kategorie
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		JLabel lblNeigungKategorie = new JLabel("Neigungs-Kategorie"); 
		this.add(lblNeigungKategorie, c);
		
		//Combo Neigungs-Kategorie
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbNeigungskategorie = new JComboBox<>();
		this.add(cmbNeigungskategorie, c);
		
	}
	
	
	private void initData(){
		for (int i=0; i<HindernisKategorie.values().length; i++) {
			cmbHindernisKategorie.addItem(HindernisKategorie.values()[i]);
		}
		parent.addDefaultActionListener(cmbHindernisKategorie);
		
		for (int i=0; i<NeigungsKategorie.values().length; i++) {
			cmbNeigungskategorie.addItem(NeigungsKategorie.values()[i]);
		}
		parent.addDefaultActionListener(cmbNeigungskategorie);
		
		cmbHindernisKategorie.setSelectedItem(HindernisKategorie.Wenige);
		cmbNeigungskategorie.setSelectedItem(NeigungsKategorie.NK_10bis20);
	}
	

	
	public int getFahrstreckenAufStrasse_m() {
		return (Integer) txtFahrstreckenAufStrasse.getValue();
	}
	
	public int getFahrstreckenFeinerschliessung_m() {
		return (Integer) txtFahrstreckenFeinerschliessung.getValue();
	}
	
	public int getHolzlagerLaenge_m() {
		return (Integer) txtHolzlagerLaenge.getValue();
	}
	
	public HindernisKategorie getHindernisKategorie() {
		return (HindernisKategorie) cmbHindernisKategorie.getSelectedItem();
	}
	
	public NeigungsKategorie getNeigungsKategorie() {
		return (NeigungsKategorie) cmbNeigungskategorie.getSelectedItem();
	}



	public void setFahrstreckenAufStrasse_m(int fahrstreckeStrasse_m) {
		txtFahrstreckenAufStrasse.setValue(fahrstreckeStrasse_m);
	}

	public void setFahrstreckenFeinerschliessung_m(int fahrstreckeFeinerschliessung_m) {
		txtFahrstreckenFeinerschliessung.setValue(fahrstreckeFeinerschliessung_m);
	}

	public void setHolzlagerLaenge_m(int holzlagerLaenge_m) {
		txtHolzlagerLaenge.setValue(holzlagerLaenge_m);
	}

	public void setHindernisKategorie(HindernisKategorie hindernisKategorie) {
		cmbHindernisKategorie.setSelectedItem(hindernisKategorie);
	}

	public void setNeigungsKategorie(NeigungsKategorie neigungsKategorie) {
		cmbNeigungskategorie.setSelectedItem(neigungsKategorie);
	}

}
