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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten.SchichtholzStuecklaengen;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class SortimentsvorgabenPanelSchichtholz extends SortimentsvorgabenPanel {

	private static final long serialVersionUID = 1L;

	private JSpinner txtAnteilSpalten_Prz;
	
	
	public SortimentsvorgabenPanelSchichtholz(HeProMoWindow parent) {
		super(parent);
	}
	
	
	@Override
	protected void addComponents() {
		GridBagConstraints c;

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnteilSpalten_Prz = new JLabel("Anteil Spalten (%)");
		this.add(lblAnteilSpalten_Prz, c);

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilSpalten_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilSpalten_Prz);
		this.add(txtAnteilSpalten_Prz, c);
	}
	
	
	@Override
	protected void initData(){
		for (int i=0; i<SchichtholzStuecklaengen.values().length; i++) {
			cmbStuecklaengen.addItem(SchichtholzStuecklaengen.values()[i]);
		}
		parent.addDefaultActionListener(cmbStuecklaengen);
		
	}

	
	
	public SchichtholzStuecklaengen getStuecklaengen() {
		return (SchichtholzStuecklaengen) cmbStuecklaengen.getSelectedItem();
	}
	
	
	public void setStuecklaengen(SchichtholzStuecklaengen stuecklaengen) {
		cmbStuecklaengen.setSelectedItem(stuecklaengen);
	}
	
	
	public int getAnteilSpalten_Prz() {
		return (Integer) txtAnteilSpalten_Prz.getValue();
	}
	
	
	public void setAnteilSpalten_Prz(int value) {
		txtAnteilSpalten_Prz.setValue(value);
	}

}
