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
public class KostensaetzePanelSeilkranPlanung extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzPersonal = new JLabel("SFr./Std.");
	private JSpinner txtAnsatzPersonal;
	
	
	public KostensaetzePanelSeilkranPlanung(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Kostensätze"));		
		
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
		JLabel lblPersonal = new JLabel("Personal");
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
		c.weightx = 100;
		c.insets = new Insets(0,5,0,0);
		this.add(lblAnsatzPersonal, c);
	}



	private void initData() {
		txtAnsatzPersonal.setModel(new SpinnerNumberModel(60.0, 0, 1000, 1));
		txtAnsatzPersonal.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal, "0.##"));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal);
	}
	
	
	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonal.getValue();
	}



	public void setAnsatzPersonal(double kostenansatzPersonal1) {
		txtAnsatzPersonal.setValue(kostenansatzPersonal1);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzPersonal.setText(newValue + "/Std.");
	}

}
