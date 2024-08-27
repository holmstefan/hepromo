/*******************************************************************************
 * Copyright 2024 Stefan Holm
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

import javax.swing.JCheckBox;
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
public class TraktionshilfswindePanel extends JPanel {
	
	private final HeProMoWindow parent;
	
	private JCheckBox chkEinsatzThw;
	private JSpinner txtAnzahlRueckegassen;

	
	public TraktionshilfswindePanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("Thw.TraktionshilfswindeProzess"))); //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		

		//Label Einsatz THW
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblEinsatzThw = new JLabel(GuiStrings.getString("Thw.EinsatzTraktionshilfswinde")); //$NON-NLS-1$
		this.add(lblEinsatzThw, c);
		
		//CheckBox Einsatz THW
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,-4,0,0);
		chkEinsatzThw = new JCheckBox();
		this.add(chkEinsatzThw, c);

		
		//Label Anzahl Rückegassen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblAnzahlRueckegassen = new JLabel(GuiStrings.getString("Thw.AnzahlRueckegassen")); //$NON-NLS-1$
		this.add(lblAnzahlRueckegassen, c);
		
		//Text Anzahl Rückegassen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtAnzahlRueckegassen = new JSpinner();
		this.add(txtAnzahlRueckegassen, c);
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.weightx = 400;
		c.weighty = 100;
		this.add(new JPanel(), c);	
	}
	
	
	private void initData(){
		parent.addDefaultItemListener(chkEinsatzThw);
		chkEinsatzThw.addItemListener(evt -> {
			txtAnzahlRueckegassen.setEnabled(chkEinsatzThw.isSelected());
		});
		txtAnzahlRueckegassen.setEnabled(chkEinsatzThw.isSelected());
		
		txtAnzahlRueckegassen.setModel(new SpinnerNumberModel(1, 0, 20, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlRueckegassen);
	}
	
	
	public void setEinsatzThw(boolean flag) {
		chkEinsatzThw.setSelected(flag);
	}
	
	public boolean isEinsatzThw() {
		return chkEinsatzThw.isSelected();
	}
	
	public void setAnzahlRueckegassen(int nr) {
		txtAnzahlRueckegassen.setValue(nr);
	}
	
	public int getAnzahlRueckegassen() {
		return (Integer) txtAnzahlRueckegassen.getValue();
	}
}
