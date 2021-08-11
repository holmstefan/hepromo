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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelRadharvester2014 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;
	
	private JSpinner txtHolzmenge_m3iR;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JSpinner txtBhd_cm;
	
	
	public BestandPanelRadharvester2014(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("BestandPanelRadharvester2014.AusscheidenderBestand")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblHolzmenge_iR = new JLabel(GuiStrings.getString("BestandPanelRadharvester2014.1")); //$NON-NLS-1$
		this.add(lblHolzmenge_iR, c);
		
		//Text Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,5,0,5);
		txtHolzmenge_m3iR = new JSpinner();
		txtHolzmenge_m3iR.setName("txtHolzmenge_m3iR");
		this.add(txtHolzmenge_m3iR, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,15,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BestandPanelRadharvester2014.2")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblHolzmenge_m3oR_readOnly, c);
		
		
		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,5,5);
		JLabel lblBhd = new JLabel(GuiStrings.getString("BestandPanelRadharvester2014.BHD_cm_iR")); //$NON-NLS-1$
		this.add(lblBhd, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,5,5);
		txtBhd_cm = new JSpinner();
		txtBhd_cm.setName("txtBhd_cm");
		this.add(txtBhd_cm, c);
		
		
		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 2;
//		c.gridy = 3;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 300;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
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
		
		
		txtBhd_cm.setModel(new SpinnerNumberModel(35, 12, 75, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtBhd_cm);
	}
	
	
	public void updateLabelHolzmengeMitRindenabzug() {
		updateLabelHolzmenge_m3iR();
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmenge_m3iR.getValue();
		value *= parent.getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("BestandPanelRadharvester2014.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	

	public double getHolzmenge_m3() {
		return (Double) txtHolzmenge_m3iR.getValue();
	}

	public void setHolzmenge_m3(double holzmenge_m3) {
		txtHolzmenge_m3iR.setValue(holzmenge_m3);
		updateLabelHolzmenge_m3iR();
	}



	public int getBhd_cm() {
		return (Integer) txtBhd_cm.getValue();
	}

	public void setBhd_cm(int bhd_cm) {
		txtBhd_cm.setValue(bhd_cm);
	}
	
	
	public void setMinBhd_cm(int value) {
		((SpinnerNumberModel)txtBhd_cm.getModel()).setMinimum(value);
	}
	

}
