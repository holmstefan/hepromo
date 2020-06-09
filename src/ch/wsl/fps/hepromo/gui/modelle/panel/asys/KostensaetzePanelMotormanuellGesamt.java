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
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanelMotormanuellGesamt extends KostensaetzePanel {

	private static final long serialVersionUID = 1L;

	protected JLabel lblMaschine2A;
	private JSpinner txtAnsatzMaschine2;
	protected JLabel lblMaschine2B;

	public KostensaetzePanelMotormanuellGesamt(HeProMoWindow parent) {
		super(parent);
		
		super.setLabelMaschine("Motorsägen"); 
	}
	

	@Override
	protected void initPanel() {
		super.initPanel();
		

		GridBagConstraints c;		
		
		
		//Label Maschine A
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		lblMaschine2A = new JLabel("Schäleisen"); 
		this.add(lblMaschine2A, c);
		
		
		//Text Maschine
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzMaschine2 = new JSpinner();
		this.add(txtAnsatzMaschine2, c);
		
		//Label Maschine B
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		lblMaschine2B = new JLabel("SFr./B'Std."); 
		this.add(lblMaschine2B, c);
	}	
	
	
	
	@Override
	protected void initData() {
		super.initData();
		
		txtAnsatzMaschine2.setModel(new SpinnerNumberModel(0.50, 0, 100, 0.01));
		txtAnsatzMaschine2.setEditor(new JSpinner.NumberEditor(txtAnsatzMaschine2, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMaschine2);
	}
	

	/**
	 * Should not be used in this subclass, use getAnsatzMotorsaegen() or getAnsatzSchaeleisen() instead
	 */
	@Override
	public double getAnsatzMaschine() {
		throw new RuntimeException(); 
	}
	

	/**
	 * Should not be used in this subclass, use setAnsatzMotorsaegen() or setAnsatzSchaeleisen() instead
	 */
	@Override
	public void setAnsatzMaschine(double ansatz) {
		throw new RuntimeException(); 
	}
	
	
	public double getAnsatzMotorsaegen() {
		return super.getAnsatzMaschine();
	}
	
	
	public void setAnsatzMotorsaegen(double ansatz) {
		super.setAnsatzMaschine(ansatz);
	}
	
	
	public double getAnsatzSchaeleisen() {
		return (Double) txtAnsatzMaschine2.getValue();
	}
	
	
	public void setAnsatzSchaeleisen(double ansatz) {
		txtAnsatzMaschine2.setValue(ansatz);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		lblMaschine2B.setText(newValue + "/B'Std."); 
	}

}
