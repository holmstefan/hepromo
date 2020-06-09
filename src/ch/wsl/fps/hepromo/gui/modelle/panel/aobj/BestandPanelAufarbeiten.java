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

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class BestandPanelAufarbeiten extends BestandPanel {

	private static final long serialVersionUID = 1L;

	private String bezeichnungHauptsortiment;
	private String bezeichnungNebensortiment;
	private JSpinner txtAnteilHauptsortiment_Prz;
	private JSpinner txtAnteilNebensortiment_Prz;
	private JSpinner txtAnteilHauptsortiment_m3iR;
	
	
	
	public BestandPanelAufarbeiten(HeProMoWindow parent, String bezeichnungHauptsortiment, String bezeichnungNebensortiment) {
		super(parent);
		this.bezeichnungHauptsortiment = bezeichnungHauptsortiment;
		this.bezeichnungNebensortiment = bezeichnungNebensortiment;
		addComponents();
		initAddedComponentsData();
	}
	
	
	private void addComponents() {
		GridBagConstraints c;
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilHauptsortiment_Prz = new JLabel("Anteil " + bezeichnungHauptsortiment + " (%)");
		this.add(lblAnteilHauptsortiment_Prz, c);


        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilHauptsortiment_Prz = new JSpinner();
		this.add(txtAnteilHauptsortiment_Prz, c);
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilNebensortiment_Prz = new JLabel("Anteil " + bezeichnungNebensortiment + " (%)");
		this.add(lblAnteilNebensortiment_Prz, c);


        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilNebensortiment_Prz = new JSpinner();
		this.add(txtAnteilNebensortiment_Prz, c);
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilSortiment_m3iR = new JLabel("<html>Menge " + bezeichnungHauptsortiment + " (m<sup>3</sup> i.R.)</html>");
		this.add(lblAnteilSortiment_m3iR, c);


        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilHauptsortiment_m3iR = new JSpinner();
		txtAnteilHauptsortiment_m3iR.setEnabled(false);
		this.add(txtAnteilHauptsortiment_m3iR, c);
	}
	
	
	private void initAddedComponentsData() {		
		txtAnteilHauptsortiment_Prz.setModel(new SpinnerNumberModel(100, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilHauptsortiment_Prz);
		
		txtAnteilNebensortiment_Prz.setModel(new SpinnerNumberModel(0, 0, 0, 1)); //Maximum muss zu Beginn 0 sein, da der Hauptsortiment-Spinner den Wert 100 hat!
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilNebensortiment_Prz);
		
		txtAnteilHauptsortiment_m3iR.setModel(new SpinnerNumberModel(0.0, 0, 100000, 0.01));
//		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilHauptsortiment_m3iR); //Feld nicht editierbar -> darf keinen Listener haben!
		updateMengeHauptsortiment_m3iR();

	}
	
	
	public int getAnteilHauptsortiment() {
		return (Integer) txtAnteilHauptsortiment_Prz.getValue();
	}
	
	
	public int getAnteilNebensortiment() {
		return (Integer) txtAnteilNebensortiment_Prz.getValue();
	}
	
	
	public void setAnteilHauptsortiment_Prz(int anteil) {
		txtAnteilHauptsortiment_Prz.setValue(anteil);
		onInputChangedBeforeCalculation();
	}
	
	
	public void setAnteilNebensortiment_Prz(int anteil) {
		txtAnteilNebensortiment_Prz.setValue(anteil);
		onInputChangedBeforeCalculation();
	}
	
	
	
	public void onInputChangedBeforeCalculation() {
		SpinnerNumberModel modelNebensortiment = (SpinnerNumberModel) txtAnteilNebensortiment_Prz.getModel();
		modelNebensortiment.setMaximum(100 - (Integer) txtAnteilHauptsortiment_Prz.getValue());
		
		SpinnerNumberModel modelHauptsortiment = (SpinnerNumberModel) txtAnteilHauptsortiment_Prz.getModel();
		modelHauptsortiment.setMaximum(100 - (Integer) txtAnteilNebensortiment_Prz.getValue());
		
		updateMengeHauptsortiment_m3iR();
	}
	
	
	
	private void updateMengeHauptsortiment_m3iR() {
		int prz = (Integer) txtAnteilHauptsortiment_Prz.getValue();
		double menge = super.getHolzmenge_m3iR() * (prz / 100.0);
		txtAnteilHauptsortiment_m3iR.setValue(menge);
	}


}
