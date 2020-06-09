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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.MittlereFahrentfernung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelSchlepper2014 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtHolzmenge_m3iR;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JSpinner txtMittlererStueckinhalt;
	private JComboBox<MittlereFahrentfernung> cmbMittlereFahrentfernung;
	
	
	public BestandPanelSchlepper2014(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("BestandPanelSchlepper2014.AusscheidenderBestand")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,0,5);
		JLabel lblHolzmenge_iR = new JLabel(GuiStrings.getString("BestandPanelSchlepper2014.Holzmenge_m3_iR")); //$NON-NLS-1$
		this.add(lblHolzmenge_iR, c);
		
		//Text Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,0,5);
		txtHolzmenge_m3iR = new JSpinner();
		this.add(txtHolzmenge_m3iR, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,15,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BestandPanelSchlepper2014.TooltipUmrechnungsfaktor")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblHolzmenge_m3oR_readOnly, c);
		
		
		//Label Mittlerer Stückinhalt
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,5,0,5);
		JLabel lblMittlererStueckinhalt = new JLabel(GuiStrings.getString("BestandPanelSchlepper2014.MittlererStueckinhalt_m3_iR_pro_Stk")); //$NON-NLS-1$
		this.add(lblMittlererStueckinhalt, c);
		
		//Text Mittlerer Stückinhalt
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,5,0,5);
		txtMittlererStueckinhalt = new JSpinner();
		this.add(txtMittlererStueckinhalt, c);
		

		
		//Label Mittlere Fahrentfernung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		JLabel lblMittlereFahrentfernung = new JLabel(GuiStrings.getString("BestandPanelSchlepper2014.MittlereFahrentfernung")); //$NON-NLS-1$
		this.add(lblMittlereFahrentfernung, c);
		
		//Combo Mittlere Fahrentfernung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		cmbMittlereFahrentfernung = new JComboBox<>();
		this.add(cmbMittlereFahrentfernung, c);
		
		
		
		
		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 2;
//		c.gridy = 4;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 500;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
//		this.add(new JPanel(), c);
	}
	
	
	
	
	private void initData(){
		txtHolzmenge_m3iR.setModel(new SpinnerNumberModel(100.0, 0, 100000, 50));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3iR);
		txtHolzmenge_m3iR.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateLabelHolzmenge_m3iR();
			}
		});
		
		
		txtMittlererStueckinhalt.setModel(new SpinnerNumberModel(0.5, 0.05, 3.0, 0.01));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlererStueckinhalt);

		
		for (MittlereFahrentfernung value : MittlereFahrentfernung.values()) {
			cmbMittlereFahrentfernung.addItem(value);
		}
		parent.addDefaultActionListener(cmbMittlereFahrentfernung);
	}
	
	
	public void updateLabelHolzmengeMitRindenabzug() {
		updateLabelHolzmenge_m3iR();
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmenge_m3iR.getValue();
		value *= parent.getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("BestandPanelSchlepper2014.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	
	public double getHolzmenge_m3() {
		return (Double) txtHolzmenge_m3iR.getValue();
	}


	public void setHolzmenge_m3(double holzmenge_m3) {
		this.txtHolzmenge_m3iR.setValue(holzmenge_m3);
		updateLabelHolzmenge_m3iR();
	}

	public double getMittlererStueckinhalt() {
		return (Double) txtMittlererStueckinhalt.getValue();
	}
	

	public void setMittlererStueckinhalt(double mittlererStueckinhalt) {
		this.txtMittlererStueckinhalt.setValue(mittlererStueckinhalt);
	}
	

	public MittlereFahrentfernung getMittlereFahrentfernung() {
		return (MittlereFahrentfernung) cmbMittlereFahrentfernung.getSelectedItem();
	}
	

	public void setMittlereFahrentfernung(MittlereFahrentfernung mittlereFahrentfernung) {
		this.cmbMittlereFahrentfernung.setSelectedItem(mittlereFahrentfernung);
	}

}
