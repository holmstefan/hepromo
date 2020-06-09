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
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.Hacker2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.calc.CalculatorHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanelHacker2018 extends KostensaetzePanel2014 {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblEinheit;
	private JRadioButton radPMH15;
	private JRadioButton radSrm;
	

	public KostensaetzePanelHacker2018(HeProMoWindow parent) {
		super(parent);

        GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(15,55,0,0);
		lblEinheit = new JLabel(GuiStrings.getString("KostensaetzePanelHacker2018.EinheitKostensatzHacker")); //$NON-NLS-1$
		this.add(lblEinheit, c);


        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,65,0,0);
		radPMH15 = new JRadioButton(GuiStrings.getString("KostensaetzePanelHacker2018.KostenProPMH15")); //$NON-NLS-1$
		radPMH15.setSelected(true);
		radPMH15.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					//aktuellen Wert umrechnen
					Zielsortiment zielsortiment = getHackerWindow().getZielsortiment();
					double motorleistungKw = getHackerWindow().getMotorleistungHacker_kW();
					double kostensatzAlt = KostensaetzePanelHacker2018.super.getAnsatzMaschine1();
					double kostensatzNeu = CalculatorHacker2018.convertFromCHFproSrmToCHFproPMH15(kostensatzAlt, zielsortiment, motorleistungKw);
					KostensaetzePanelHacker2018.super.setAnsatzMaschine1(kostensatzNeu);
					updateWaehrungskuerzel();
				}
			}
		});
		this.add(radPMH15, c);


		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,65,0,0);
		radSrm = new JRadioButton(GuiStrings.getString("KostensaetzePanelHacker2018.KostenProSrm")); //$NON-NLS-1$
		radSrm.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					//aktuellen Wert umrechnen
					Zielsortiment zielsortiment = getHackerWindow().getZielsortiment();
					double motorleistungKw = getHackerWindow().getMotorleistungHacker_kW();
					double kostensatzAlt = KostensaetzePanelHacker2018.super.getAnsatzMaschine1();
					double kostensatzNeu = CalculatorHacker2018.convertFromCHFproPMH15ToCHFproSrm(kostensatzAlt, zielsortiment, motorleistungKw);
					KostensaetzePanelHacker2018.super.setAnsatzMaschine1(kostensatzNeu);
					updateWaehrungskuerzel();
				}
			}
		});
		this.add(radSrm, c);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(radPMH15);
		group.add(radSrm);
	}

	

	@Override
	public double getAnsatzMaschine1() { //Diese Methode liefert den Ansatz immer in CHF/PMH15 zurück (so, wie er im Modell gespeichert wird)
		if (radPMH15.isSelected()) {
			return super.getAnsatzMaschine1();
		}
		else {
			Zielsortiment zielsortiment = getHackerWindow().getZielsortiment();
			double motorleistungKw = getHackerWindow().getMotorleistungHacker_kW();
			
			double ansatzCHFproSrm = super.getAnsatzMaschine1(); 
			double ansatzCHFproPMH15 = CalculatorHacker2018.convertFromCHFproSrmToCHFproPMH15(ansatzCHFproSrm, zielsortiment, motorleistungKw);
			
			return ansatzCHFproPMH15;
		}
	}
	
	
	@Override
	public void setAnsatzMaschine1(double ansatz) { //Diese Methode erwartet den Ansatz immer in CHF/PMH15 (so, wie er im Modell gespeichert wird)
		if (radPMH15.isSelected()) {
			super.setAnsatzMaschine1(ansatz);
		}
		else {
			// Ein neuer Ansatz wird immer in "proPMH15" angezeigt, also so, wie er im Modell gespeichert ist.
			// Da nach der Methode setAnsatzMaschine() jeweils die Methode setDisplayHackerKostenProPMH15() aufgerufen wird,
			// findet eine allfällige Umwandlung in "proSrm" erst dort statt.
			// Dies wurde so umgesetzt, da es sonst Probleme gab, wenn ein neuer Ansatz gesetzt wird, und die bisherige
			// Umwandlungsfunktion nicht mehr gültig war (z.B. beim Laden eines Modells ab File)
			radPMH15.setSelected(true);
			super.setAnsatzMaschine1(ansatz);
		}
	}
	
	
	public boolean isDisplayHackerKostenProPMH15() {
		return radPMH15.isSelected();
	}

	public void setDisplayHackerKostenProPMH15(boolean displayHackerKostenProPMH15) {
		if (displayHackerKostenProPMH15) {
			radPMH15.setSelected(true);
		}
		else {
			radSrm.setSelected(true);
		}
	}
	
	
	private void updateWaehrungskuerzel() {
		String einheitProPMH15 = GuiStrings.getString("KostensaetzePanel2014.ProPMH15"); //$NON-NLS-1$
		String einheitProSrm = GuiStrings.getString("KostensaetzePanelHacker2018.proSrm"); //$NON-NLS-1$
		String oldText = lblMaschine1B.getText();
		
		if (radPMH15.isSelected()) {
			lblMaschine1B.setText(oldText.replace(einheitProSrm, einheitProPMH15));
			lblMaschine1_Info.setVisible(true);
		}
		else {
			lblMaschine1B.setText(oldText.replace(einheitProPMH15, einheitProSrm));
			lblMaschine1_Info.setVisible(false);
		}
	}
	
	
	@Override
	public void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		updateWaehrungskuerzel();
	}
	
	
	private Hacker2018 getHackerWindow() {
		return (Hacker2018) parent;
	}
}
