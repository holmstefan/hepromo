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
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen.SchwierigkeitSeitlicherZuzug;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelMobilseilkranSeilen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtNutzungsmenge_m3iR;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JSpinner txtMittleresStueckvolumen_m3;
	private JSpinner txtMittlereFahrdistanz_m;
	private JSpinner txtMittlereDistanzSeitlicherZuzug_m;
	protected JComboBox<SchwierigkeitSeitlicherZuzug> cmbSchwierigkeitSeitlicherZuzug;
	

	public BestandPanelMobilseilkranSeilen(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("BestandPanelMobilseilkranSeilen.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		//Label Nutzungsmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblNutzungsmenge = new JLabel(GuiStrings.getString("BestandPanelMobilseilkranSeilen.Nutzungsmenge")); //$NON-NLS-1$
		this.add(lblNutzungsmenge, c);
		
		//Text Nutzungsmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
//		c.insets = new Insets(5,5,5,5);
		txtNutzungsmenge_m3iR = new JSpinner();
		this.add(txtNutzungsmenge_m3iR, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,10,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Allgemein.lblInfoButtonUmrechnung_iRoR")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblHolzmenge_m3oR_readOnly, c);

		
		//Label Mittleres Stückvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMittleresStueckvolumen = new JLabel(GuiStrings.getString("BestandPanelMobilseilkranSeilen.MittleresStueckvolumen")); //$NON-NLS-1$
		this.add(lblMittleresStueckvolumen, c);
		
		//Text Mittleres Stückvolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
//		c.insets = new Insets(5,5,5,5);
		txtMittleresStueckvolumen_m3 = new JSpinner();
		this.add(txtMittleresStueckvolumen_m3, c);
		
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);
		
		

		
		
		//Label Mittlere Fahrdistanz
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMittlereFahrdistanz = new JLabel(GuiStrings.getString("BestandPanelMobilseilkranSeilen.MittlereFahrdistanz_m")); //$NON-NLS-1$
		this.add(lblMittlereFahrdistanz, c);
		
		//Text Mittlere Fahrdistanz
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(5,5,5,5);
		txtMittlereFahrdistanz_m = new JSpinner();
		this.add(txtMittlereFahrdistanz_m, c);
		
		
		
		//Label Mittlere Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMittlereDistanzSeitlicherZuzug = new JLabel(GuiStrings.getString("BestandPanelMobilseilkranSeilen.SeitlicherZuzug_m")); //$NON-NLS-1$
		this.add(lblMittlereDistanzSeitlicherZuzug, c);
		
		//Text Mittlere Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(5,5,5,5);
		txtMittlereDistanzSeitlicherZuzug_m = new JSpinner();
		this.add(txtMittlereDistanzSeitlicherZuzug_m, c);
		
		
		
		//Label Schwierigkeit seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblSchwierigkeitSeitlicherZuzug = new JLabel(GuiStrings.getString("BestandPanelMobilseilkranSeilen.SchwierigkeitZuzug")); //$NON-NLS-1$
		this.add(lblSchwierigkeitSeitlicherZuzug, c);
		
		//Combo Schwierigkeit Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(5,5,5,5);
		cmbSchwierigkeitSeitlicherZuzug = new JComboBox<>();
		this.add(cmbSchwierigkeitSeitlicherZuzug, c);
	}
	
	
	private void initData(){

		for (SchwierigkeitSeitlicherZuzug value : SchwierigkeitSeitlicherZuzug.values()) {
			cmbSchwierigkeitSeitlicherZuzug.addItem(value);
		}
		parent.addDefaultActionListener(cmbSchwierigkeitSeitlicherZuzug);
		cmbSchwierigkeitSeitlicherZuzug.setSelectedItem(SchwierigkeitSeitlicherZuzug.Einfach);

		txtMittleresStueckvolumen_m3.setModel(new SpinnerNumberModel(0.4, 0.01, 10, 0.1));
		txtMittleresStueckvolumen_m3.setEditor(new JSpinner.NumberEditor(txtMittleresStueckvolumen_m3, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittleresStueckvolumen_m3);
		
		txtNutzungsmenge_m3iR.setModel(new SpinnerNumberModel(400.0, 1, 100000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtNutzungsmenge_m3iR);
		txtNutzungsmenge_m3iR.addChangeListener(evt -> {
			updateLabelHolzmenge_m3iR();
		});

		txtMittlereFahrdistanz_m.setModel(new SpinnerNumberModel(300, 0, 10000, 100));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlereFahrdistanz_m);
		
		txtMittlereDistanzSeitlicherZuzug_m.setModel(new SpinnerNumberModel(15, 0, 1000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlereDistanzSeitlicherZuzug_m);	
	}
	
	
	public void updateLabelHolzmengeMitRindenabzug() {
		updateLabelHolzmenge_m3iR();
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtNutzungsmenge_m3iR.getValue();
		value *= parent.getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("Allgemein.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
	
	
	public double getMittleresStueckvolumen_m3() {
		return (Double) txtMittleresStueckvolumen_m3.getValue();
	}
	
	public double getNutzungsmenge_m3iR() {
		return (Double) txtNutzungsmenge_m3iR.getValue();
	}
	
	public int getMittlereFahrdistanz_m() {
		return (Integer) txtMittlereFahrdistanz_m.getValue();
	}
	
	public int getMittlereDistanzSeitlicherZuzug_m() {
		return (Integer) txtMittlereDistanzSeitlicherZuzug_m.getValue();
	}
	
	public SchwierigkeitSeitlicherZuzug getSchwierigkeitSeitlicherZuzug() {
		return (SchwierigkeitSeitlicherZuzug) cmbSchwierigkeitSeitlicherZuzug.getSelectedItem();
	}



	public void setMittleresStueckvolumen_m3(double mittleresStueckvolumen_m3) {
		txtMittleresStueckvolumen_m3.setValue(mittleresStueckvolumen_m3);
	}

	public void setNutzungsmenge_m3iR(double holzmenge) {
		txtNutzungsmenge_m3iR.setValue(holzmenge);
		updateLabelHolzmenge_m3iR();
	}

	public void setMittlereFahrdistanz_m(int mittlereFahrdistanz_m) {
		txtMittlereFahrdistanz_m.setValue(mittlereFahrdistanz_m);
	}

	public void setMittlereDistanzSeitlicherZuzug_m(int mittlereDistanzSeitlicherZuzug_m) {
		txtMittlereDistanzSeitlicherZuzug_m.setValue(mittlereDistanzSeitlicherZuzug_m);
	}

	public void setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug schwierigkeitSeitlicherZuzug) {
		cmbSchwierigkeitSeitlicherZuzug.setSelectedItem(schwierigkeitSeitlicherZuzug);
	}

}

