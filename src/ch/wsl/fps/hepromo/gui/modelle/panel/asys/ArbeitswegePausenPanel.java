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
public class ArbeitswegePausenPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;
	
	private JSpinner txtArbeitszeit = new JSpinner();
	private JSpinner txtPausen = new JSpinner();
	
	
	
	public ArbeitswegePausenPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}

	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ArbeitswegePausenPanel.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Arbeitszeit
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblArbeitszeit = new JLabel(GuiStrings.getString("ArbeitswegePausenPanel.TaeglicheArbeitszeit_min")); //$NON-NLS-1$
		this.add(lblArbeitszeit, c);
		
		//Text Arbeitszeit
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtArbeitszeit, c);
		
		
		
		//Label Pausen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblPausen = new JLabel(GuiStrings.getString("ArbeitswegePausenPanel.WegzeitenUndPausenMin")); //$NON-NLS-1$
		this.add(lblPausen, c);
		
		//Text Pausen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtPausen, c);
	}

	
	
	private void initData() {
		txtArbeitszeit.setModel(new SpinnerNumberModel(540, 0, 1000, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtArbeitszeit);
		
		txtPausen.setModel(new SpinnerNumberModel(60, 0, 1000, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtPausen);
	}
	
	
	public int getArbeitszeit_min(){
		return (Integer) txtArbeitszeit.getValue();
	}
	
	
	public int getWegzeitenUndPausen_min(){
		return (Integer) txtPausen.getValue();
	}

	
	public void setArbeitszeit_min(int taeglicheArbeitszeit_Min) {
		txtArbeitszeit.setValue(taeglicheArbeitszeit_Min);
	}

	
	public void setWegzeitenUndPausen_min(int wegzeitenUndPausen_Min) {
		txtPausen.setValue(wegzeitenUndPausen_Min);
	}
	
}
