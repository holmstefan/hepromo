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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class SeilLiniePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<Fahrtrichtung> cmbFahrtrichtung;
	private JSpinner txtMittlereFahrdistanz_m;
	private JSpinner txtMittlereDistanzSeitlicherZuzug_m;
	private JSpinner txtTragseilHoeheLagerplatz_m;
	private JSpinner txtTragseilHoeheBestand_m;
	
	
	public SeilLiniePanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Seillinie"));		
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		

		
		//Label Fahrtrichtung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,0,0,0);
		JLabel lblFahrtrichtung = new JLabel("Fahrtrichtung");
		this.add(lblFahrtrichtung, c);
		
		//Combo Fahrtrichtung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbFahrtrichtung = new JComboBox<>();
		this.add(cmbFahrtrichtung, c);
		
		
		
		//Label Mittlere Fahrdistanz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMittlereFahrdistanz = new JLabel("Mittlere Fahrdistanz (m)");
		this.add(lblMittlereFahrdistanz, c);
		
		//Text Mittlere Fahrdistanz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlereFahrdistanz_m = new JSpinner();
		this.add(txtMittlereFahrdistanz_m, c);
		
		
		
		//Label Mittlere Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMittlereDistanzSeitlicherZuzug_m = new JLabel("Mittlere Distanz seitlicher Zuzug");
		this.add(lblMittlereDistanzSeitlicherZuzug_m, c);
		
		//Text Mittlere Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlereDistanzSeitlicherZuzug_m = new JSpinner();
		this.add(txtMittlereDistanzSeitlicherZuzug_m, c);
		
		
		
		//Label Tragseilhöhe Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblTragseilHoeheLagerplatz = new JLabel("Tragseilehöhe Lagerplatz (m)");
		this.add(lblTragseilHoeheLagerplatz, c);
		
		//Text Tragseilhöhe Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtTragseilHoeheLagerplatz_m = new JSpinner();
		this.add(txtTragseilHoeheLagerplatz_m, c);
		
		
		
		//Label Tragseilhöhe Bestand
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblTragseilHoeheBestand = new JLabel("Tragseilhöhe (m)");
		this.add(lblTragseilHoeheBestand, c);
		
		//Text Tragseilhöhe Bestand
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtTragseilHoeheBestand_m = new JSpinner();
		this.add(txtTragseilHoeheBestand_m, c);
	}
	
	
	private void initData(){
		for (Fahrtrichtung value : Fahrtrichtung.values()) {
			cmbFahrtrichtung.addItem(value);
		}
		parent.addDefaultActionListener(cmbFahrtrichtung);
		
		
		txtMittlereFahrdistanz_m.setModel(new SpinnerNumberModel(350, 1, 10000, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlereFahrdistanz_m);
		
		txtMittlereDistanzSeitlicherZuzug_m.setModel(new SpinnerNumberModel(20, 1, 10000, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlereDistanzSeitlicherZuzug_m);
		
		txtTragseilHoeheLagerplatz_m.setModel(new SpinnerNumberModel(10, 1, 200, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtTragseilHoeheLagerplatz_m);
		
		txtTragseilHoeheBestand_m.setModel(new SpinnerNumberModel(12, 1, 200, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtTragseilHoeheBestand_m);
	}

	
	
	public Fahrtrichtung getFahrtrichtung() {
		return (Fahrtrichtung) cmbFahrtrichtung.getSelectedItem();
	}
	
	
	public int getMittlereFahrdistanz_m() {
		return (Integer) txtMittlereFahrdistanz_m.getValue();
	}
	
	
	public int getMittlereDistanzSeitlicherZuzug() {
		return (Integer) txtMittlereDistanzSeitlicherZuzug_m.getValue();
	}
	
	
	public int getTragseilHoeheLagerplatz_m() {
		return (Integer) txtTragseilHoeheLagerplatz_m.getValue();
	}
	
	
	public int getTragseilHoeheBestand_m() {
		return (Integer) txtTragseilHoeheBestand_m.getValue();
	}



	public void setFahrtrichtung(Fahrtrichtung fahrtrichtung) {
		cmbFahrtrichtung.setSelectedItem(fahrtrichtung);
	}


	public void setMittlereFahrdistanz_m(int mittlereFahrdistanz_m) {
		txtMittlereFahrdistanz_m.setValue(mittlereFahrdistanz_m);
	}


	public void setMittlereDistanzSeitlicherZuzug(int mittlereDistanzSeitlicherZuzug_m) {
		txtMittlereDistanzSeitlicherZuzug_m.setValue(mittlereDistanzSeitlicherZuzug_m);
	}


	public void setTragseilHoeheLagerplatz_m(int tragseilHoeheLagerplatz_m) {
		txtTragseilHoeheLagerplatz_m.setValue(tragseilHoeheLagerplatz_m);
	}


	public void setTragseilHoeheBestand_m(int tragseilHoeheBestand_m) {
		txtTragseilHoeheBestand_m.setValue(tragseilHoeheBestand_m);
	}
}
