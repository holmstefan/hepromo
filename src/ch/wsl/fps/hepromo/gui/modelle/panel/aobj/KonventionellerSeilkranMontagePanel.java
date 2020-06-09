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

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public class KonventionellerSeilkranMontagePanel extends KonventionellerSeilkranMontageDemontagePanel {

	private static final long serialVersionUID = 1L;
	
	
	public KonventionellerSeilkranMontagePanel(HeProMoWindow parent) {
		super(parent);
		chkMontageDemontageIstSeiverlegung.setText("Montage ist eine Seilverlegung");
	}


	@Override
	protected String getBorderTitle() {
		return "Montage";
	}
	
	
	public boolean isMontageIstSeilverlegung() {
		return chkMontageDemontageIstSeiverlegung.isSelected();
	}

	
	public void setMontageIstSeilverlegung(boolean montageIstSeilverlegung) {
		chkMontageDemontageIstSeiverlegung.setSelected(montageIstSeilverlegung);
	}
}
