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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker.HackerTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MobilerHackerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<HackerTyp> cmbHackerTyp;
	private JSpinner txtKippContainerVolumen_m3;

	
	public MobilerHackerPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder("Hacker"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label HackerTyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHackerTyp = new JLabel("Typ"); 
		this.add(lblHackerTyp, c);
		
		//Combo HackerTyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbHackerTyp = new JComboBox<>();
		this.add(cmbHackerTyp, c);
		
		//Label KippContainerVolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblKippContainerVolumen_m3 = new JLabel("<html>" + "Kippcontainervolumen" + " (m<sup>3</sup>)</html>");   
		this.add(lblKippContainerVolumen_m3, c);
		
		//Text KippContainerVolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtKippContainerVolumen_m3 = new JSpinner(new SpinnerNumberModel(15d, 1d, 100d, 0.1d));
		txtKippContainerVolumen_m3.setEditor(new JSpinner.NumberEditor(txtKippContainerVolumen_m3, "0.#")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtKippContainerVolumen_m3);
		this.add(txtKippContainerVolumen_m3, c);	
		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 1;
//		c.gridy = 2;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
//		this.add(new JPanel(), c);	
		
	}
	
	
	

	private void initData() {		
		for (int i=0; i<HackerTyp.values().length; i++) {
			cmbHackerTyp.addItem(HackerTyp.values()[i]);
		}
		parent.addDefaultActionListener(cmbHackerTyp);
		
//		txtKippContainerVolumen_m3.setValue(30);
	}
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbHackerTyp.equals(eventSource)) {
			
			if (cmbHackerTyp.getSelectedItem() == HackerTyp.AufForwarder) {
				txtKippContainerVolumen_m3.setValue(15.0);
			}
			else if (cmbHackerTyp.getSelectedItem() == HackerTyp.AufLastwagen) {
				txtKippContainerVolumen_m3.setValue(30.0);
			}
			
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run(){
					String message = "Der Kostensatz im Tab 'Arbeitssystem' muss möglicherweise angepasst werden."; 
					JOptionPane.showMessageDialog(parent, message, "Warnung", JOptionPane.WARNING_MESSAGE); 
				}
			});
		}
	}
	
	
	
	public HackerTyp getHackerTyp() {
		return (HackerTyp) cmbHackerTyp.getSelectedItem();
	}
	
	
	public double getKippContainerVolumen_m3() {
		return (Double) txtKippContainerVolumen_m3.getValue();
	}



	public void setHackerTyp(HackerTyp hackerTyp) {
		cmbHackerTyp.setSelectedItem(hackerTyp);
	}

	public void setKippContainerVolumen_m3(double kippContainerVolumen_m3) {
		txtKippContainerVolumen_m3.setValue(kippContainerVolumen_m3);
	}

}
