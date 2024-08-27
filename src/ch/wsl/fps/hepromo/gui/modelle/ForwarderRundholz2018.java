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
package ch.wsl.fps.hepromo.gui.modelle;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.model.HeProMoInputData;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ForwarderRundholz2018 extends Forwarder2018 {
	
	private boolean isLoadingGUIToModel = false;
	private boolean isLoadingModelToGUI = false;
	
	
	public ForwarderRundholz2018() {
		super.setTitle(GuiStrings.getString("ForwarderRundholz2018.Title")); //$NON-NLS-1$
		super.setSize((int) (600 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (680 * MainWindow.SIZE));
		
		super.initalize();
	}
	
	
	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		super.initPanelArbeitsobjekt(panel);
		
		lblSchaftholz_m3iR.setVisible(false);
		txtSchaftholz_m3iR.setVisible(false);
		lblSchaftholz_Info.setVisible(false);
		
		lblEnergieholzmenge_m3iR.setVisible(false);
		txtEnergieholzmenge_m3iR.setVisible(false);
		lblEnergieholz_Info.setVisible(false);
		btnEnergieholzvolumenBerechnen.setVisible(false);
		
		lblEnergieholzAusSchaftholz_m3iR.setVisible(false);
		txtEnergieholzAusSchaftholz_m3iR.setVisible(false);
		lblEnergieholzAusSchaftholz_Info.setVisible(false);
		
		lblEnergieholzAusAstderbholzUndReisig_m3iR.setVisible(false);
		txtEnergieholzAusAstderbholzUndReisig_m3iR.setVisible(false);
		
		
		lblZopfdurchmesser_cm.setVisible(false);
		txtZopfdurchmesser_cm.setVisible(false);
		
		lblEnergieholzanfall_m3iRproHa.setVisible(false);
		cmbEnergieholzanfall_m3iRproHa.setVisible(false);
		lblEnergieholzanfall_Info.setVisible(false);
		
		lblSkizzeBaumteile.setVisible(false);
		
		lblErgebnisanzeige.setVisible(false);
		cmbErgebnisanzeige.setVisible(false);
		
		
		//Placeholder
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 150;
		JPanel pnlPlaceholder = new JPanel();
		pnlPlaceholder.setPreferredSize(new Dimension(125, 5));
		panel.add(pnlPlaceholder, c);
	}
	
	
	@Override
	protected void initData() {
		super.initData();
		
		txtVerkaufRundholz_m3iR.setEnabled(true);
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtVerkaufRundholz_m3iR);
	}
	
	@Override
	protected void showWarnmeldungHolzmengeIfNotYetShown() {
		// do nothing
	}
	
	@Override
	protected void highlightMengenFields(boolean flag) {
		// do nothing
	}
	
	
	@Override
	protected void updateVerkaufRundholz() {
		final double rindenAbzugFaktor = getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		
		if (isLoadingModelToGUI) {
			final double verkaufsmenge_m3iR = verkaufsmenge_m3oR / rindenAbzugFaktor;
			txtVerkaufRundholz_m3iR.setValue(verkaufsmenge_m3iR);
		}
		else if (isLoadingGUIToModel) {
			verkaufsmenge_m3oR = ((Double)txtVerkaufRundholz_m3iR.getValue()) * rindenAbzugFaktor;
		}
		else { //onInputChangedBeforeCalculation -> könnte ein Update des Rindenabzugsfaktors sein, aber auch alles andere.
			verkaufsmenge_m3oR = ((Double)txtVerkaufRundholz_m3iR.getValue()) * rindenAbzugFaktor;
		}

		updateLabelVerkaufRundholz_m3oR_Info();
	}
	
	
	@Override
	public void loadGUIToModel() {
		isLoadingGUIToModel = true;
		super.loadGUIToModel();
		isLoadingGUIToModel = false;
	}
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		isLoadingModelToGUI = true;
		super.loadModelToGUI(data);
		isLoadingModelToGUI = false;
	}

}
