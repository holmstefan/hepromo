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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class KostensaetzePanelSeilkran extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzEinheit1 = new JLabel("SFr./Std.");
	private JLabel lblAnsatzEinheit2 = new JLabel("SFr./BStd.");
	
	private JSpinner txtAnsatzPersonal;
	private JSpinner txtAnzahlPersonal;
	private JSpinner txtAnsatzSeilkran;
	private JSpinner txtLaufzeitSeilkran_Prz;
	
	
	public KostensaetzePanelSeilkran(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder("Kostensätze"));		
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblPersonal = new JLabel("Personal");
		this.add(lblPersonal, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzPersonal = new JSpinner();
		this.add(txtAnsatzPersonal, c);
		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzEinheit1, c);

		
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblAnzahl = new JLabel("Anzahl");
		this.add(lblAnzahl, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnzahlPersonal = new JSpinner();
		this.add(txtAnzahlPersonal, c);
		


		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblSeilkran = new JLabel("Seilkran");
		this.add(lblSeilkran, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzSeilkran = new JSpinner();
		this.add(txtAnsatzSeilkran, c);
		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzEinheit2, c);
		
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,5,5);
		JLabel lblLaufzeit = new JLabel("Anteil Seilkran-Laufzeit (%)");
		this.add(lblLaufzeit, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		txtLaufzeitSeilkran_Prz = new JSpinner();
		this.add(txtLaufzeitSeilkran_Prz, c);
	}



	private void initData() {
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);

		txtAnzahlPersonal.setModel(new SpinnerNumberModel(3, 2, 5, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlPersonal);
		
		txtAnsatzSeilkran.setModel(new SpinnerNumberModel(120.0, 0, 1000, 1));
		txtAnsatzSeilkran.setEditor(new JSpinner.NumberEditor(txtAnsatzSeilkran, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzSeilkran);

		txtLaufzeitSeilkran_Prz.setModel(new SpinnerNumberModel(90, 0, 100, 1));
		txtLaufzeitSeilkran_Prz.setEditor(new JSpinner.NumberEditor(txtLaufzeitSeilkran_Prz, "0.#"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaufzeitSeilkran_Prz);
	}
	

	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}
	
	
	public int getAnzahlPersonal() {
		return (Integer) txtAnzahlPersonal.getValue();
	}
	
	
	public double getAnsatzSeilkran() {
		return (Double) txtAnsatzSeilkran.getValue();
	}
	
	
	public int getLaufzeitSeilkran_Prz() {
		return (Integer) txtLaufzeitSeilkran_Prz.getValue();
	}
	
	public void setAnsatzSeilkran(double value) {
		txtAnsatzSeilkran.setValue(value);
	}
	
	public void setLaufzeitSeilkran_Prz(int value) {
		txtLaufzeitSeilkran_Prz.setValue(value);
	}
	
	public void setLaufzeitSeilkranEnabled(boolean flag) {
		txtLaufzeitSeilkran_Prz.setEnabled(flag);
	}


	public void setAnsatzPersonal(double kostenansatzPersonal1) {
		txtAnsatzPersonal.setValue(kostenansatzPersonal1);
	}

	public void setAnzahlPersonal(int anzahlPersonal) {
		txtAnzahlPersonal.setValue(anzahlPersonal);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzEinheit1.setText(newValue + "/Std.");
		lblAnsatzEinheit2.setText(newValue + "/BStd.");
	}

}
