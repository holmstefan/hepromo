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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class KostensaetzePanelSeilkranLagerplatz extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzEinheit1 = new JLabel("SFr./Std.");
	private JLabel lblAnsatzEinheit2 = new JLabel("SFr./BStd.");
	
	private JSpinner txtAnsatzPersonal;
	private JSpinner txtAnzahlPersonal;
	private JSpinner txtEinsatzzeitPersonal_Prz;
	private JSpinner txtAnsatzKranfahrzeug;
	private JSpinner txtLaufzeitKranfahrzeug_Prz;
	
	
	public KostensaetzePanelSeilkranLagerplatz(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Kostensätze"));		
		
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
		c.weightx = 100;
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
		c.insets = new Insets(0,10,0,0);
		JLabel lblEinsatzzeit = new JLabel("Einsatzzeit (%)");
		this.add(lblEinsatzzeit, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtEinsatzzeitPersonal_Prz = new JSpinner();
		this.add(txtEinsatzzeitPersonal_Prz, c);


		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblKranfahrzeug = new JLabel("Kranfahrzeug");
		this.add(lblKranfahrzeug, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzKranfahrzeug = new JSpinner();
		this.add(txtAnsatzKranfahrzeug, c);
		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzEinheit2, c);
		
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,5,0);
		JLabel lblLaufzeit = new JLabel("Laufzeit (%)");
		this.add(lblLaufzeit, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		txtLaufzeitKranfahrzeug_Prz = new JSpinner();
		this.add(txtLaufzeitKranfahrzeug_Prz, c);
	}



	private void initData() {
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);

		txtAnzahlPersonal.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlPersonal);

		txtEinsatzzeitPersonal_Prz.setModel(new SpinnerNumberModel(100, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEinsatzzeitPersonal_Prz);

		txtAnsatzKranfahrzeug.setModel(new SpinnerNumberModel(80.0, 0, 1000, 1));
		txtAnsatzKranfahrzeug.setEditor(new JSpinner.NumberEditor(txtAnsatzKranfahrzeug, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzKranfahrzeug);

		txtLaufzeitKranfahrzeug_Prz.setModel(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaufzeitKranfahrzeug_Prz);
	}
	

	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}
	
	
	public int getAnzahlPersonal() {
		return (Integer) txtAnzahlPersonal.getValue();
	}
	
	
	public int getEinsatzzeitPersonal_Prz() {
		return (Integer) txtEinsatzzeitPersonal_Prz.getValue();
	}
	
	
	public double getAnsatzKranfahrzeug() {
		return (Double) txtAnsatzKranfahrzeug.getValue();
	}
	
	
	public int getLaufzeitKranfahrzeug_Prz() {
		return (Integer) txtLaufzeitKranfahrzeug_Prz.getValue();
	}
	
	
	
	public void setAnsatzPersonal(double ansatz) {
		txtAnsatzPersonal.setValue(ansatz);
	}

	
	public void setAnzahlPersonal(int anzahl) {
		txtAnzahlPersonal.setValue(anzahl);
	}
	
	
	public void setEinsatzzeitPersonal_Prz(int einsatzzeit) {
		txtEinsatzzeitPersonal_Prz.setValue(einsatzzeit);
	}
	
	
	public void setAnsatzKranfahrzeug(double ansatz) {
		txtAnsatzKranfahrzeug.setValue(ansatz);
	}

	
	public void setLaufzeitKranfahrzeug_Prz(int laufzeit) {
		txtLaufzeitKranfahrzeug_Prz.setValue(laufzeit);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzEinheit1.setText(newValue + "/Std.");
		lblAnsatzEinheit2.setText(newValue + "/BStd.");
	}
	
}
