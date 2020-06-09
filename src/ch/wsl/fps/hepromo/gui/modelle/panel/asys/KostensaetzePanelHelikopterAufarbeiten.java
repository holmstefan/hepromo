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
public class KostensaetzePanelHelikopterAufarbeiten extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzPersonal = new JLabel("SFr./Std.");
	private JLabel lblAnsatzMaschinen = new JLabel("SFr./BStd.");
	
	private JSpinner txtAnzahlPersonal = new JSpinner();
	private JSpinner txtAnsatzPersonal = new JSpinner();
	
	private JSpinner txtAnzahlMotorsaegen = new JSpinner();
	private JSpinner txtAnsatzMotorsaegen = new JSpinner();
	private JSpinner txtAnzahlKranfahrzeuge = new JSpinner();
	private JSpinner txtAnsatzKranfahrzeuge = new JSpinner();
	
	
	public KostensaetzePanelHelikopterAufarbeiten(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	protected void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Kostensätze"));		
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,1,5);
		JLabel lblAnzahl = new JLabel("Anzahl");
		this.add(lblAnzahl, c);
		
		//Label Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,1,0);
		this.add(lblAnsatzPersonal, c);
		
		
		
		//Label Personal
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblPersonal = new JLabel("Personal");
		this.add(lblPersonal, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtAnzahlPersonal, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzPersonal, c);
		
		
		
		
		

		
		//Label Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(20,0,0,0);
		this.add(lblAnsatzMaschinen, c);
		
		
		
		//Label Motorsägen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMotorsaegen = new JLabel("Motorsäge");
		this.add(lblMotorsaegen, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtAnzahlMotorsaegen, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzMotorsaegen, c);
		
		
		
		//Label Kranfahrzeuge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblKranfahrzeuge = new JLabel("Kranfahrzeug");
		this.add(lblKranfahrzeuge, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtAnzahlKranfahrzeuge, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzKranfahrzeuge, c);
	}



	protected void initData() {
		txtAnzahlPersonal.setModel(new SpinnerNumberModel(2, 1, 5, 1));
		txtAnzahlPersonal.setEditor(new JSpinner.NumberEditor(txtAnzahlPersonal, "0.##"));
		txtAnzahlPersonal.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlPersonal);
		
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);

		
		txtAnzahlMotorsaegen.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		txtAnzahlMotorsaegen.setEditor(new JSpinner.NumberEditor(txtAnzahlMotorsaegen, "0.##"));
		txtAnzahlMotorsaegen.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlMotorsaegen);
		
		txtAnsatzMotorsaegen.setModel(new SpinnerNumberModel(14.0, 0, 1000, 1));
		txtAnsatzMotorsaegen.setEditor(new JSpinner.NumberEditor(txtAnsatzMotorsaegen, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMotorsaegen);
		
		
		txtAnzahlKranfahrzeuge.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		txtAnzahlKranfahrzeuge.setEditor(new JSpinner.NumberEditor(txtAnzahlKranfahrzeuge, "0.##"));
		txtAnzahlKranfahrzeuge.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlKranfahrzeuge);
		
		txtAnsatzKranfahrzeuge.setModel(new SpinnerNumberModel(80.0, 0, 1000, 1));
		txtAnsatzKranfahrzeuge.setEditor(new JSpinner.NumberEditor(txtAnsatzKranfahrzeuge, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzKranfahrzeuge);
	}
	
	

	
	public void setAnzahlPersonal(int anzahl) {
		txtAnzahlPersonal.setValue(anzahl);
	}
	
	public void setAnzahlMotorsaegen(int anzahl) {
		txtAnzahlMotorsaegen.setValue(anzahl);
	}
	
	public void setAnzahlKranfahrzeuge(int anzahl) {
		txtAnzahlKranfahrzeuge.setValue(anzahl);
	}
	
	
	
	
	
	
	public int getAnzahlPersonal() {
		return (Integer) txtAnzahlPersonal.getValue();
	}
	
	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}
	
	
	public int getAnzahlMotorsaegen() {
		return (Integer) txtAnzahlMotorsaegen.getValue();
	}
	
	
	public double getAnsatzMotorsaegen() {
		return (Double) txtAnsatzMotorsaegen.getValue();
	}
	
	
	public int getAnzahlKranfahrzeuge() {
		return (Integer) txtAnzahlKranfahrzeuge.getValue();
	}
	
	
	public double getAnsatzKranfahrzeuge() {
		return (Double) txtAnsatzKranfahrzeuge.getValue();
	}



	public void setAnsatzPersonal(double kostenansatzPersonal1) {
		txtAnsatzPersonal.setValue(kostenansatzPersonal1);
	}


	public void setAnsatzMotorsaegen(double kostenansatzMaschine1) {
		txtAnsatzMotorsaegen.setValue(kostenansatzMaschine1);
	}


	public void setAnsatzKranfahrzeuge(double kostenansatzMaschine2) {
		txtAnsatzKranfahrzeuge.setValue(kostenansatzMaschine2);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzPersonal.setText(newValue + "/Std.");
		lblAnsatzMaschinen.setText(newValue + "/BStd.");
	}

}
