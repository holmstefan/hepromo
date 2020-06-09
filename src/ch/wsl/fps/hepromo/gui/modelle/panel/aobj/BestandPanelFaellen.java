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
public class BestandPanelFaellen extends BestandPanel {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtAnteilFaellenMitHandseilzug;
	
	
	
	public BestandPanelFaellen(HeProMoWindow parent) {
		super(parent);
		addComponents();
		initAddedComponentsData();
	}
	
	
	private void addComponents() {
		GridBagConstraints c;
		
		//Label Anteil Fällen mit Handseilzug
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilFaellenMitHandseilzug = new JLabel("Anteil Fällen mit Handseilzug (%)"); 
		this.add(lblAnteilFaellenMitHandseilzug, c);
		
		//Text Anteil Fällen mit Handseilzug
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilFaellenMitHandseilzug = new JSpinner();
		this.add(txtAnteilFaellenMitHandseilzug, c);
	}
	
	
	private void initAddedComponentsData() {		
		txtAnteilFaellenMitHandseilzug.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilFaellenMitHandseilzug);
	}
	
	
	public int getAnteilFaellenMitHandseilzug_Prz() {
		return (Integer) txtAnteilFaellenMitHandseilzug.getValue();
	}


	public void setAnteilFaellenMitHandseilzug_Prz(int anteilFaellenHandseilzug_Prz) {
		txtAnteilFaellenMitHandseilzug.setValue(anteilFaellenHandseilzug_Prz);
	}


}
