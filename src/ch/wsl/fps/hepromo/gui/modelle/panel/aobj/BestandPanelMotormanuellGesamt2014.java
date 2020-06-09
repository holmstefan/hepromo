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

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuellGesamt2014.Region;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelMotormanuellGesamt2014 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtHolzmenge_m3iR;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JSpinner txtBhd_cm;
	private JSpinner txtAnteilNadelholz_Prz;
	private JSpinner txtAnteilLaubholz_Prz;
	private JSpinner txtAnteilKiefer_Prz;
	private JComboBox<Region> cmbRegion;
	
	
	public BestandPanelMotormanuellGesamt2014(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblAusscheidenderBestand")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		
		//Label Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHolzmenge_iR = new JLabel(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblHolzmenge")); //$NON-NLS-1$
		this.add(lblHolzmenge_iR, c);
		
		//Text Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHolzmenge_m3iR = new JSpinner();
		this.add(txtHolzmenge_m3iR, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,10,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblInfoButtonUmrechnung_iRoR")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblHolzmenge_m3oR_readOnly, c);
		
		
		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblBhd = new JLabel(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblBHD")); //$NON-NLS-1$
		this.add(lblBhd, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtBhd_cm = new JSpinner();
		this.add(txtBhd_cm, c);
		
		
		//Label Anteil Nadelholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilNadelholz = new JLabel(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.AnteilNadelholzOhneFoehre")); //$NON-NLS-1$
		this.add(lblAnteilNadelholz, c);
		
		//Text Anteil Nadelholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilNadelholz_Prz = new JSpinner();
		this.add(txtAnteilNadelholz_Prz, c);
		
		
		//Label Anteil Kiefer
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilKiefer = new JLabel(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblAnteilKiefer")); //$NON-NLS-1$
		this.add(lblAnteilKiefer, c);
		
		//Text Anteil Kiefer
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilKiefer_Prz = new JSpinner();
		this.add(txtAnteilKiefer_Prz, c);
		
		//Label Anteil Kiefer
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		JLabel lblAnteilKiefer_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.AnteilKieferInfoButton")); //$NON-NLS-1$
		lblAnteilKiefer_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblAnteilKiefer_Info, c);
		
		
		//Label Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilLaubholz = new JLabel(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblAnteilLaubholz")); //$NON-NLS-1$
		this.add(lblAnteilLaubholz, c);
		
		//Text Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilLaubholz_Prz = new JSpinner();
		this.add(txtAnteilLaubholz_Prz, c);
		

		
		//Label Region
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblRegion = new JLabel(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblRegion")); //$NON-NLS-1$
		this.add(lblRegion, c);
		
		//Combo Region
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbRegion = new JComboBox<>();
		this.add(cmbRegion, c);
		

		
		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 2;
//		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 500;
//		this.add(new JPanel(), c);
	}
	
	
	private void initData(){
		txtHolzmenge_m3iR.setModel(new SpinnerNumberModel(150.0, 0, 100000, 50));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3iR);
		txtHolzmenge_m3iR.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateLabelHolzmenge_m3iR();
			}
		});
		
		
		txtBhd_cm.setModel(new SpinnerNumberModel(35, 15, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtBhd_cm);
		
		txtAnteilNadelholz_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		txtAnteilNadelholz_Prz.setEnabled(false);
		
		txtAnteilLaubholz_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilLaubholz_Prz);
		txtAnteilLaubholz_Prz.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				updateAnteilNadelholz();
			}
		});
		
		txtAnteilKiefer_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilKiefer_Prz);
		txtAnteilKiefer_Prz.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				updateAnteilNadelholz();
			}
		});

		for (int i=0; i<Region.values().length; i++) {
			cmbRegion.addItem(Region.values()[i]);
		}
		parent.addDefaultActionListener(cmbRegion);
		
		updateAnteilNadelholz();
	}
	
	
	public void updateLabelHolzmengeMitRindenabzug() {
		updateLabelHolzmenge_m3iR();
	}
	
	
	private void updateAnteilNadelholz() {
		int anteilLaub = (Integer) txtAnteilLaubholz_Prz.getValue();
		int anteilKiefer = (Integer) txtAnteilKiefer_Prz.getValue();
		txtAnteilNadelholz_Prz.setValue(100 - anteilLaub - anteilKiefer);
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmenge_m3iR.getValue();
		value *= parent.getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("BestandPanelMotormanuellGesamt2014.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	
	
	public double getHolzmenge_m3() {
		return (Double) txtHolzmenge_m3iR.getValue();
	}
	
	
	public int getBhd_cm() {
		return (Integer) txtBhd_cm.getValue();
	}
	
	
	public int getAnteilLaubholz_Prz() {
		return (Integer) txtAnteilLaubholz_Prz.getValue();
	}
	
	
	public int getAnteilKiefer_Prz() {
		return (Integer) txtAnteilKiefer_Prz.getValue();
	}
	
	
	public Region getRegion() {
		return (Region) cmbRegion.getSelectedItem();
	}



	public void setHolzmenge_m3(double holzmenge) {
		txtHolzmenge_m3iR.setValue(holzmenge);
		updateLabelHolzmenge_m3iR();
	}


	public void setBhd_cm(int bhd_cm) {
		txtBhd_cm.setValue(bhd_cm);
	}


	public void setAnteilLaubholz_Prz(int anteilLaubholz_Prz) {
		txtAnteilLaubholz_Prz.setValue(anteilLaubholz_Prz);
	}


	public void setAnteilKiefer_Prz(int anteilKiefer_Prz) {
		txtAnteilKiefer_Prz.setValue(anteilKiefer_Prz);
	}


	public void setRegion(Region region) {
		cmbRegion.setSelectedItem(region);
	}

	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (txtAnteilLaubholz_Prz.equals(eventSource)) {
			int myVal = (Integer) txtAnteilLaubholz_Prz.getValue();
			int otherMax = 100 - myVal;
			((SpinnerNumberModel)txtAnteilKiefer_Prz.getModel()).setMaximum(otherMax);
		}
		if (txtAnteilKiefer_Prz.equals(eventSource)) {
			int myVal = (Integer) txtAnteilKiefer_Prz.getValue();
			int otherMax = 100 - myVal;
			((SpinnerNumberModel)txtAnteilLaubholz_Prz.getModel()).setMaximum(otherMax);
		}
	}
}
