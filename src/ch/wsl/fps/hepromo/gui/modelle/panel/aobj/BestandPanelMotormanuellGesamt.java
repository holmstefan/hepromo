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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell.KronenLaengenKlasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Baumartgruppe;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelMotormanuellGesamt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtHolzmenge;
	private JSpinner txtMassenmittelstamm;

	private JSpinner txtAnteilFaellenMitHandseilzug;
	private JSpinner txtAnteilEntrindenVonHand;
	
	protected JComboBox<Baumartgruppe> cmbBaumartengruppe;
	private JComboBox<KronenLaengenKlasse> cmbKronenLaengenKlasse;
	
	
	public BestandPanelMotormanuellGesamt(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder("Bestand"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		
		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHolzmenge = new JLabel("<html>Holzmenge (m<sup>3</sup> i.R.)</html>"); 
		this.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHolzmenge = new JSpinner();
		this.add(txtHolzmenge, c);
		
		
		//Label Massenmittelstamm
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMassenmittelstamm = new JLabel("<html>Massenmittelstamm (m<sup>3</sup> i.R.)</html>"); 
		this.add(lblMassenmittelstamm, c);
		
		//Text Massenmittelstamm
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMassenmittelstamm = new JSpinner();
		this.add(txtMassenmittelstamm, c);
		
		
		//Label Anteil Fällen mit Handseilzug
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblAnteilFaellenMitHandseilzug = new JLabel("Anteil Fällen mit Handseilzug (%)"); 
		this.add(lblAnteilFaellenMitHandseilzug, c);
		
		//Text Anteil Fällen mit Handseilzug
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilFaellenMitHandseilzug = new JSpinner();
		this.add(txtAnteilFaellenMitHandseilzug, c);
		
		
		//Label Anteil Entrinden von Hand
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilEntrindenVonHand = new JLabel("Anteil Entrinden von Hand (%)"); 
		this.add(lblAnteilEntrindenVonHand, c);
		
		//Text Anteil Entrinden von Hand
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilEntrindenVonHand = new JSpinner();
		this.add(txtAnteilEntrindenVonHand, c);

		
		//Label Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblBaumartengruppe = new JLabel("Baumartengruppe"); 
		this.add(lblBaumartengruppe, c);
		
		//Combo Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbBaumartengruppe = new JComboBox<>();
		this.add(cmbBaumartengruppe, c);
		
		
		//Label Kronenlängenklasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblKronenLaengenKlasse = new JLabel("Kronenlängen-Klasse"); 
		this.add(lblKronenLaengenKlasse, c);
		
		//Text Kronenlängenklasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbKronenLaengenKlasse = new JComboBox<>();
		this.add(cmbKronenLaengenKlasse, c);
	}
	
	
	private void initData(){
		
		txtHolzmenge.setModel(new SpinnerNumberModel(150.0, 0, 100000, 1));
		txtHolzmenge.setEditor(new JSpinner.NumberEditor(txtHolzmenge, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge);
		
		txtMassenmittelstamm.setModel(new SpinnerNumberModel(0.3, 0, 100, 0.1));
		txtMassenmittelstamm.setEditor(new JSpinner.NumberEditor(txtMassenmittelstamm, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMassenmittelstamm);

		txtAnteilFaellenMitHandseilzug.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilFaellenMitHandseilzug);
		
		txtAnteilEntrindenVonHand.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilEntrindenVonHand);

		for (int i=0; i<Baumartgruppe.values().length; i++) {
			cmbBaumartengruppe.addItem(Baumartgruppe.values()[i]);
		}
		parent.addDefaultActionListener(cmbBaumartengruppe);
		
		for (int i=0; i<KronenLaengenKlasse.values().length; i++) {
			cmbKronenLaengenKlasse.addItem(KronenLaengenKlasse.values()[i]);
		}
		parent.addDefaultActionListener(cmbKronenLaengenKlasse);
	}

	
	
	public double getHolzmenge_m3iR() {
		return (Double) txtHolzmenge.getValue();
	}
	
	
	public void setHolzmenge_m3iR(double amount) {
		txtHolzmenge.setValue(amount);
	}
	
	
	public double getMassenmittelstamm_m3iR() {
		return (Double) txtMassenmittelstamm.getValue();
	}
	
	
	public int getAnteilFaellenMitHandseilzug_Prz() {
		return (Integer) txtAnteilFaellenMitHandseilzug.getValue();
	}
	
	
	public int getAnteilEntrindenVonHand_Prz() {
		return (Integer) txtAnteilEntrindenVonHand.getValue();
	}
	
	
	public Baumartgruppe getBaumartengruppe() {
		return (Baumartgruppe) cmbBaumartengruppe.getSelectedItem();
	}

	
	public KronenLaengenKlasse getKronenLaengenKlasse() {
		return (KronenLaengenKlasse) cmbKronenLaengenKlasse.getSelectedItem();
	}


	public void setMassenmittelstamm_m3iR(double massenmittelstamm_m3iR) {
		txtMassenmittelstamm.setValue(massenmittelstamm_m3iR);		
	}


	public void setAnteilFaellenMitHandseilzug_Prz(int anteilFaellenMitHandseilzug_Prz) {
		txtAnteilFaellenMitHandseilzug.setValue(anteilFaellenMitHandseilzug_Prz);
	}


	public void setAnteilEntrindenVonHand_Prz(int anteilEntrindenVonHand_Prz) {
		txtAnteilEntrindenVonHand.setValue(anteilEntrindenVonHand_Prz);
	}


	public void setBaumartengruppe(Baumartgruppe baumartenGruppe) {
		cmbBaumartengruppe.setSelectedItem(baumartenGruppe);
	}


	public void setKronenLaengenKlasse(KronenLaengenKlasse kronenLaengenKlasse) {
		cmbKronenLaengenKlasse.setSelectedItem(kronenLaengenKlasse);
	}
}
