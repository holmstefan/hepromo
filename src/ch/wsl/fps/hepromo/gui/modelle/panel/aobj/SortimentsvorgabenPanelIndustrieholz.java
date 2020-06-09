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

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten.IndustrieholzStuecklaengen;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class SortimentsvorgabenPanelIndustrieholz extends SortimentsvorgabenPanel {

	private static final long serialVersionUID = 1L;
	
	
	public SortimentsvorgabenPanelIndustrieholz(HeProMoWindow parent) {
		super(parent);
	}
	
	
	
	@Override
	protected void addComponents() {
	}
	
	
	@Override
	protected void initData(){
		for (int i=0; i<IndustrieholzStuecklaengen.values().length; i++) {
			cmbStuecklaengen.addItem(IndustrieholzStuecklaengen.values()[i]);
		}
		parent.addDefaultActionListener(cmbStuecklaengen);
	}

	
	
	public IndustrieholzStuecklaengen getStuecklaengen() {
		return (IndustrieholzStuecklaengen) cmbStuecklaengen.getSelectedItem();
	}
	
	
	public void setStuecklaengen(IndustrieholzStuecklaengen stuecklaengen) {
		cmbStuecklaengen.setSelectedItem(stuecklaengen);
	}

}
