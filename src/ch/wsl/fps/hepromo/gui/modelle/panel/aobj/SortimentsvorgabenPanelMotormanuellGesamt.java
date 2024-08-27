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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten.IndustrieholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten.SchichtholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten.StammholzStuecklaengen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SortimentsvorgabenPanelMotormanuellGesamt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtAnteilStammholz;
	private JSpinner txtAnteilIndustrieholz;
	private JSpinner txtAnteilSchichtholz;
	private JSpinner txtAnteilSpalten;
	private JComboBox<StammholzStuecklaengen> cmbStuecklaengenStammholz;
	private JCheckBox chkKantenBrechen;
	private JComboBox<IndustrieholzStuecklaengen> cmbStuecklaengenIndustrieholz;
	private JComboBox<SchichtholzStuecklaengen> cmbStuecklaengenSchichtholz;
	

	public SortimentsvorgabenPanelMotormanuellGesamt(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder("Sortimentsvorgaben"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMengenanteile = new JLabel("Mengenanteile (%)"); 
		this.add(lblMengenanteile, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblAnteilStammholz = new JLabel("Stammholz"); 
		this.add(lblAnteilStammholz, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilStammholz = new JSpinner();
		txtAnteilStammholz.setEnabled(false);
		this.add(txtAnteilStammholz, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblAnteilIndustrieholz = new JLabel("Industrieholz lang"); 
		this.add(lblAnteilIndustrieholz, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilIndustrieholz = new JSpinner();
		txtAnteilIndustrieholz.addChangeListener(evt -> {
			int anteilIh = (Integer) txtAnteilIndustrieholz.getValue();
			int anteilSchH = (Integer) txtAnteilSchichtholz.getValue();
			txtAnteilStammholz.setValue(100 - anteilIh - anteilSchH);
		});
		this.add(txtAnteilIndustrieholz, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblAnteilSchichtholz = new JLabel("Schichtholz"); 
		this.add(lblAnteilSchichtholz, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilSchichtholz = new JSpinner();
		txtAnteilSchichtholz.addChangeListener(evt -> {
			int anteilIh = (Integer) txtAnteilIndustrieholz.getValue();
			int anteilSchH = (Integer) txtAnteilSchichtholz.getValue();
			txtAnteilStammholz.setValue(100 - anteilIh - anteilSchH);
		});
		this.add(txtAnteilSchichtholz, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,20,0,0);
		JLabel lblAnteilSpalten = new JLabel("Anteil Spalten"); 
		this.add(lblAnteilSpalten, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilSpalten = new JSpinner();
		this.add(txtAnteilSpalten, c);




		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblStuecklaengen = new JLabel("Stücklängen"); 
		this.add(lblStuecklaengen, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblStuecklaengenStammholz = new JLabel("Stammholz"); 
		this.add(lblStuecklaengenStammholz, c);

		//Combo
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbStuecklaengenStammholz = new JComboBox<>();
		this.add(cmbStuecklaengenStammholz, c);

		//Checkbox
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		chkKantenBrechen = new JCheckBox("Kanten brechen"); 
		this.add(chkKantenBrechen, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblStuecklaengenIndustrieholz = new JLabel("Industrieholz lang"); 
		this.add(lblStuecklaengenIndustrieholz, c);

		//Combo
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbStuecklaengenIndustrieholz = new JComboBox<>();
		this.add(cmbStuecklaengenIndustrieholz, c);


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblStuecklaengenSchichtholz = new JLabel("Schichtholz"); 
		this.add(lblStuecklaengenSchichtholz, c);

		//Combo
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbStuecklaengenSchichtholz = new JComboBox<>();
		this.add(cmbStuecklaengenSchichtholz, c);
	}


	
	private void initData(){

		//Mengenanteile
		txtAnteilStammholz.		setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilStammholz);
		txtAnteilStammholz.setValue(100);
		
		txtAnteilIndustrieholz.	setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilIndustrieholz);
		
		txtAnteilSchichtholz.	setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilSchichtholz);
		
		txtAnteilSpalten.		setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilSpalten);
		
		
		
		//Stücklängen
		for (StammholzStuecklaengen stuecklaenge : StammholzStuecklaengen.values()) {
			cmbStuecklaengenStammholz.addItem(stuecklaenge);
		}
		parent.addDefaultActionListener(cmbStuecklaengenStammholz);
		cmbStuecklaengenStammholz.setSelectedItem(StammholzStuecklaengen.Von6Bis10m);
		
		chkKantenBrechen.setSelected(false);
		parent.addDefaultItemListener(chkKantenBrechen);

		for (IndustrieholzStuecklaengen stuecklaenge : IndustrieholzStuecklaengen.values()) {
			cmbStuecklaengenIndustrieholz.addItem(stuecklaenge);
		}
		parent.addDefaultActionListener(cmbStuecklaengenIndustrieholz);
		cmbStuecklaengenIndustrieholz.setSelectedItem(IndustrieholzStuecklaengen.Ueber7m);

		for (SchichtholzStuecklaengen stuecklaenge : SchichtholzStuecklaengen.values()) {
			cmbStuecklaengenSchichtholz.addItem(stuecklaenge);
		}
		parent.addDefaultActionListener(cmbStuecklaengenSchichtholz);
		cmbStuecklaengenSchichtholz.setSelectedItem(SchichtholzStuecklaengen.Laenge2m);
	}

	
	

	public int getAnteilStammholz_Prz() {
		return (Integer) txtAnteilStammholz.getValue();
	}
	
	public int getAnteilIndustrieholz_Prz() {
		return (Integer) txtAnteilIndustrieholz.getValue();
	}
	
	public int getAnteilSchichtholz_Prz() {
		return (Integer) txtAnteilSchichtholz.getValue();
	}
	
	public int getAnteilSpalten_Prz() {
		return (Integer) txtAnteilSpalten.getValue();
	}
	
	

	public StammholzStuecklaengen getStuecklaengenStammholz() {
		return (StammholzStuecklaengen) cmbStuecklaengenStammholz.getSelectedItem();
	}
	
	public boolean isKantenBrechen() {
		return chkKantenBrechen.isSelected();
	}
	
	public IndustrieholzStuecklaengen getStuecklaengenIndustrieholz() {
		return (IndustrieholzStuecklaengen) cmbStuecklaengenIndustrieholz.getSelectedItem();
	}
	
	public SchichtholzStuecklaengen getStuecklaengenSchichtholz() {
		return (SchichtholzStuecklaengen) cmbStuecklaengenSchichtholz.getSelectedItem();
	}



	public void setAnteilIndustrieholz_Prz(int anteilIndustrieholz_Prz) {
		txtAnteilIndustrieholz.setValue(anteilIndustrieholz_Prz);
	}


	public void setAnteilSchichtholz_Prz(int anteilSchichtholz_Prz) {
		txtAnteilSchichtholz.setValue(anteilSchichtholz_Prz);
	}


	public void setAnteilSpalten_Prz(int anteilSpalten_Prz) {
		txtAnteilSpalten.setValue(anteilSpalten_Prz);
	}


	public void setStuecklaengenStammholz(StammholzStuecklaengen stammholzStuecklaengen) {
		cmbStuecklaengenStammholz.setSelectedItem(stammholzStuecklaengen);
	}


	public void setKantenBrechen(boolean kantenBrechen) {
		chkKantenBrechen.setSelected(kantenBrechen);
	}


	public void setStuecklaengenIndustrieholz(IndustrieholzStuecklaengen industrieholzStuecklaengen) {
		cmbStuecklaengenIndustrieholz.setSelectedItem(industrieholzStuecklaengen);
	}


	public void setStuecklaengenSchichtholz(SchichtholzStuecklaengen schichtholzStuecklaengen) {
		cmbStuecklaengenSchichtholz.setSelectedItem(schichtholzStuecklaengen);
	}
	

}
