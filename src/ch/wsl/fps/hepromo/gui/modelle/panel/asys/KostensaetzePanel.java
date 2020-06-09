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
import ch.wsl.fps.hepromo.gui.GuiStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanel extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	protected final HeProMoWindow parent;
	
	private JSpinner txtAnsatzPersonal = new JSpinner();
	private JSpinner txtAnsatzMaschine = new JSpinner();

	protected JLabel lblMaschineA = new JLabel(GuiStrings.getString("KostensaetzePanel.Maschine")); //$NON-NLS-1$
	protected JLabel lblPersonalA = new JLabel(GuiStrings.getString("KostensaetzePanel.Personal")); //$NON-NLS-1$
	
	protected JLabel lblMaschineB = new JLabel(GuiStrings.getString("KostensaetzePanel.SFr_pro_BStd")); //$NON-NLS-1$
	protected JLabel lblPersonalB = new JLabel(GuiStrings.getString("KostensaetzePanel.SFr_pro_Std")); //$NON-NLS-1$
	
	
	public KostensaetzePanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	protected void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("KostensaetzePanel.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Personal A
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(lblPersonalA, c);
		
		//Text Personal
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzPersonal, c);
		
		//Label Personal B
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblPersonalB, c);
		
		
		
		//Label Maschine A
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(lblMaschineA, c);
		
		//Text Maschine
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzMaschine, c);
		
		//Label Maschine B
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblMaschineB, c);
	}



	protected void initData() {
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);
		
		txtAnsatzMaschine.setModel(new SpinnerNumberModel(14.0, 0, 1000, 1));
		txtAnsatzMaschine.setEditor(new JSpinner.NumberEditor(txtAnsatzMaschine, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMaschine);
	}
	
	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}
	
	
	public void setAnsatzPersonal(double ansatz) {
		txtAnsatzPersonal.setValue(ansatz);
	}
	
	
	public double getAnsatzMaschine() {
		return (Double) txtAnsatzMaschine.getValue();
	}
	
	
	public void setAnsatzMaschine(double ansatz) {
		txtAnsatzMaschine.setValue(ansatz);
	}
	
	
	public void setLabelPersonal(String name) {
		lblPersonalA.setText(name);
	}
	
	
	public void setLabelMaschine(String name) {
		lblMaschineA.setText(name);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblMaschineB.setText(newValue + GuiStrings.getString("KostensaetzePanel.Pro_BStd")); //$NON-NLS-1$
		lblPersonalB.setText(newValue + GuiStrings.getString("KostensaetzePanel.Pro_Std")); //$NON-NLS-1$
	}

}
