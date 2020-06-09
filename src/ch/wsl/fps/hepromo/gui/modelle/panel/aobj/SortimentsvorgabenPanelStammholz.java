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

import javax.swing.JCheckBox;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten.StammholzStuecklaengen;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class SortimentsvorgabenPanelStammholz extends SortimentsvorgabenPanel {

	private static final long serialVersionUID = 1L;

	private JCheckBox chkKantenBrechen;
	
	
	public SortimentsvorgabenPanelStammholz(HeProMoWindow parent) {
		super(parent);
	}
	
	@Override
	protected void addComponents() {
		GridBagConstraints c;

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		chkKantenBrechen = new JCheckBox("Kanten brechen");
		this.add(chkKantenBrechen, c);
	}
	
	
	@Override
	protected void initData(){
		for (int i=0; i<StammholzStuecklaengen.values().length; i++) {
			cmbStuecklaengen.addItem(StammholzStuecklaengen.values()[i]);
		}
		parent.addDefaultActionListener(cmbStuecklaengen);
		
		chkKantenBrechen.setSelected(false);
		parent.addDefaultItemListener(chkKantenBrechen);
	}

	
	
	public StammholzStuecklaengen getStuecklaengen() {
		return (StammholzStuecklaengen) cmbStuecklaengen.getSelectedItem();
	}
	
	
	public void setStuecklaengen(StammholzStuecklaengen stuecklaengen) {
		cmbStuecklaengen.setSelectedItem(stuecklaengen);
	}
	
	
	public boolean isKantenBrechen() {
		return chkKantenBrechen.isSelected();
	}


	public void setKantenBrechen(boolean kantenBrechen) {
		chkKantenBrechen.setSelected(kantenBrechen);
	}

}
