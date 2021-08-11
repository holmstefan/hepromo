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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanelSeilkranGesamt extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzEinheit1 = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.SFr_pro_Std")); //$NON-NLS-1$
	private JLabel lblAnsatzEinheit2 = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.SFr_pro_BStd")); //$NON-NLS-1$
	private JLabel lblAnsatzEinheit3 = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.SFr_pro_BStd")); //$NON-NLS-1$
	
	private JSpinner txtAnsatzPersonal;
	private JLabel lblSeilkran;
	private JSpinner txtAnsatzSeilkran;
	private JSpinner txtAnsatzKranfahrzeug;
	private JSpinner txtLaufzeitKranfahrzeug_Prz;
	
	
	public KostensaetzePanelSeilkranGesamt(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblPersonal = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.Personal")); //$NON-NLS-1$
		this.add(lblPersonal, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzPersonal = new JSpinner();
		this.add(txtAnsatzPersonal, c);
		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzEinheit1, c);
		


		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		lblSeilkran = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.Seilkran")); //$NON-NLS-1$
		this.add(lblSeilkran, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzSeilkran = new JSpinner();
		this.add(txtAnsatzSeilkran, c);
		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzEinheit2, c);
		


		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblKranfahrzeug = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.Kranfahrzeug")); //$NON-NLS-1$
		this.add(lblKranfahrzeug, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzKranfahrzeug = new JSpinner();
		this.add(txtAnsatzKranfahrzeug, c);
		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzEinheit3, c);
		
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,5,0);
		JLabel lblLaufzeit = new JLabel(GuiStrings.getString("KostensaetzePanelSeilkranGesamt.Laufzeit_Prz")); //$NON-NLS-1$
		this.add(lblLaufzeit, c);
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		txtLaufzeitKranfahrzeug_Prz = new JSpinner();
		this.add(txtLaufzeitKranfahrzeug_Prz, c);
	}



	private void initData() {
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);

		txtAnsatzSeilkran.setModel(new SpinnerNumberModel(120.0, 0, 1000, 1));
		txtAnsatzSeilkran.setEditor(new JSpinner.NumberEditor(txtAnsatzSeilkran, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzSeilkran);

		txtAnsatzKranfahrzeug.setModel(new SpinnerNumberModel(80.0, 0, 1000, 1));
		txtAnsatzKranfahrzeug.setEditor(new JSpinner.NumberEditor(txtAnsatzKranfahrzeug, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzKranfahrzeug);

		txtLaufzeitKranfahrzeug_Prz.setModel(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaufzeitKranfahrzeug_Prz);
	}


	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}

	
	public double getAnsatzSeilkran() {
		return (Double) txtAnsatzSeilkran.getValue();
	}

	
	public double getAnsatzKranfahrzeug() {
		return (Double) txtAnsatzKranfahrzeug.getValue();
	}
	
	
	public int getLaufzeitKranfahrzeug_Prz() {
		return (Integer) txtLaufzeitKranfahrzeug_Prz.getValue();
	}



	public void setAnsatzPersonal(double kostenansatzPersonal1) {
		txtAnsatzPersonal.setValue(kostenansatzPersonal1);
	}
	
	
	public void setLabelSeilkran(String label) {
		lblSeilkran.setText(label);
	}


	public void setAnsatzSeilkran(double kostenansatzMaschine1) {
		txtAnsatzSeilkran.setValue(kostenansatzMaschine1);
	}


	public void setAnsatzKranfahrzeug(double kostenansatzMaschine2) {
		txtAnsatzKranfahrzeug.setValue(kostenansatzMaschine2);
	}


	public void setLaufzeitKranfahrzeug_Prz(int kranfahrzeugLaufzeit_Prz) {
		txtLaufzeitKranfahrzeug_Prz.setValue(kranfahrzeugLaufzeit_Prz);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzEinheit1.setText(newValue + GuiStrings.getString("KostensaetzePanelSeilkranGesamt.ProStd")); //$NON-NLS-1$
		lblAnsatzEinheit2.setText(newValue + GuiStrings.getString("KostensaetzePanelSeilkranGesamt.ProBStd")); //$NON-NLS-1$
		lblAnsatzEinheit3.setText(newValue + GuiStrings.getString("KostensaetzePanelSeilkranGesamt.ProBStd")); //$NON-NLS-1$
	}

}
