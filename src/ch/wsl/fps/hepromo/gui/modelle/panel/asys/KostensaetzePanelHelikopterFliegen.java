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
public class KostensaetzePanelHelikopterFliegen extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatz = new JLabel("SFr./Std.");
	
	private JSpinner txtAnzahlPersonal = new JSpinner();
	private JSpinner txtAnsatzPersonal = new JSpinner();
	
	
	public KostensaetzePanelHelikopterFliegen(HeProMoWindow parent) {
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
		this.add(lblAnsatz, c);
		
		
		
		//Label Personal
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		JLabel lblPersonal = new JLabel("Personal");
		this.add(lblPersonal, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,5);
		this.add(txtAnzahlPersonal, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		this.add(txtAnsatzPersonal, c);
	}



	protected void initData() {
		txtAnzahlPersonal.setModel(new SpinnerNumberModel(2, 1, 5, 1));
		txtAnzahlPersonal.setEditor(new JSpinner.NumberEditor(txtAnzahlPersonal, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlPersonal);
		
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);
	}
	
	
	public int getAnzahlPersonal() {
		return (Integer) txtAnzahlPersonal.getValue();
	}
	
	
	public void setAnzahlPersonal(int anzahl) {
		txtAnzahlPersonal.setValue(anzahl);
	}
	
	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}
	
	
	public void setAnsatzPersonal(double ansatz) {
		txtAnsatzPersonal.setValue(ansatz);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatz.setText(newValue + "/Std.");
	}

}
