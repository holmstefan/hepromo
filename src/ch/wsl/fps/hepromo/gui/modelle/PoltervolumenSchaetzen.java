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

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.modelle.PoltervolumenSchaetzmethode.SchaetzmethodeKuptz;
import ch.wsl.fps.hepromo.gui.modelle.PoltervolumenSchaetzmethode.SchaetzmethodeKuptzWaldrestholz;
import ch.wsl.fps.hepromo.gui.modelle.PoltervolumenSchaetzmethode.SchaetzmethodeRauricaDetailliert;
import ch.wsl.fps.hepromo.gui.modelle.PoltervolumenSchaetzmethode.SchaetzmethodeRauricaGross;
import ch.wsl.fps.hepromo.gui.modelle.PoltervolumenSchaetzmethode.SchaetzmethodeRauricaKlein;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PoltervolumenSchaetzen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<PoltervolumenSchaetzmethode> cmbSchaetzmethode;
	private JPanel pnlSchaetzmethode = new JPanel();
	
	
	public PoltervolumenSchaetzen(Zielsortiment zielsortiment) {
		initContent();
		setDefaultSchaetzmethode(zielsortiment);
	}
	
	
	protected void setDefaultSchaetzmethode(Zielsortiment zielsortiment) {
		switch(zielsortiment) {
		case Energierundholz:
			cmbSchaetzmethode.setSelectedItem(cmbSchaetzmethode.getItemAt(0));
			break;
			
		case Waldrestholz:
			cmbSchaetzmethode.setSelectedItem(cmbSchaetzmethode.getItemAt(4));
			break;
			
		default:
			throw new RuntimeException(zielsortiment.toString());
		}
	}
	
	
	
	private void initContent() {
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Schätzmethode
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,10);
		JLabel lblSchaetzmethode = new JLabel(GuiStrings.getString("PoltervolumenSchaetzen.Schaetzmethode")); //$NON-NLS-1$
		this.add(lblSchaetzmethode, c);
		
		//Combo Schätzmethode
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		cmbSchaetzmethode = new JComboBox<>();
		cmbSchaetzmethode.addItem(new SchaetzmethodeRauricaKlein());
		cmbSchaetzmethode.addItem(new SchaetzmethodeRauricaGross());
		cmbSchaetzmethode.addItem(new SchaetzmethodeRauricaDetailliert());
		cmbSchaetzmethode.addItem(new SchaetzmethodeKuptz());
		cmbSchaetzmethode.addItem(new SchaetzmethodeKuptzWaldrestholz());
		cmbSchaetzmethode.setSelectedIndex(-1);
		cmbSchaetzmethode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selection = cmbSchaetzmethode.getSelectedItem();
				if (selection instanceof PoltervolumenSchaetzmethode) {
					pnlSchaetzmethode.removeAll();
					pnlSchaetzmethode.setLayout( new GridBagLayout() );
					GridBagConstraints c = new GridBagConstraints();
					c.gridx = 0;
					c.gridy = 0;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.weightx = 100;
					pnlSchaetzmethode.add((PoltervolumenSchaetzmethode) selection, c);
					PoltervolumenSchaetzen.this.repaint();
					PoltervolumenSchaetzen.this.validate();
					Window window = SwingUtilities.getWindowAncestor(PoltervolumenSchaetzen.this);
					if (window != null) {
						window.pack();
					}
				}
				else {
					pnlSchaetzmethode.removeAll();
					PoltervolumenSchaetzen.this.validate();
					Window window = SwingUtilities.getWindowAncestor(PoltervolumenSchaetzen.this);
					if (window != null) {
						window.pack();
					}
				}
			}
		});
		cmbSchaetzmethode.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (isSelected == false) {
					PoltervolumenSchaetzmethode schaetzmethode = (PoltervolumenSchaetzmethode) value;
					if (schaetzmethode instanceof SchaetzmethodeKuptzWaldrestholz) {
						component.setBackground(new Color(184, 134, 11));
					} else {
						component.setBackground(new Color(144, 238, 144));
					}
				}
				return component;
			}
		});
		this.add(cmbSchaetzmethode, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton = GuiStrings.getInfoButtonBlue("<html> " //$NON-NLS-1$
				+ GuiStrings.getString("PoltervolumenSchaetzen.begriffserklaerungPolter") //$NON-NLS-1$
				+ GuiStrings.getString("PoltervolumenSchaetzen.begriffserklaerungEnergierundholz") //$NON-NLS-1$
				+ GuiStrings.getString("PoltervolumenSchaetzen.begriffserklaerungWaldrestholz") //$NON-NLS-1$
				+ "</html>"); //$NON-NLS-1$
		this.add(lblInfoButton, c);
		
		
		//Container für Schätzmethode
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		this.add(pnlSchaetzmethode, c);
	}
	
	
	
	public double getHolzmenge_Srm() {
		Object selection = cmbSchaetzmethode.getSelectedItem();
		if (selection instanceof PoltervolumenSchaetzmethode) {
			double result = ((PoltervolumenSchaetzmethode) selection).getResult();
			result = Math.round(result * 10) / 10f;
			return result;
		}
		else {
			return -1;
		}
	}

}
