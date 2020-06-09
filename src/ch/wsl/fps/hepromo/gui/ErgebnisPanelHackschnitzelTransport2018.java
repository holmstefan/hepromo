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
package ch.wsl.fps.hepromo.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.ErgebnisHackschnitzelTransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisPanelHackschnitzelTransport2018 extends ErgebnisPanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblLaden = new JLabel(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.Laden")); //$NON-NLS-1$
	private JLabel lblLastfahrt = new JLabel(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.Lastfahrt")); //$NON-NLS-1$
	private JLabel lblEntladen = new JLabel(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.Entladen")); //$NON-NLS-1$
	private JLabel lblLeerfahrt = new JLabel(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.Leerfahrt")); //$NON-NLS-1$
	private JLabel lblGesamt = new JLabel(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.Gesamt")); //$NON-NLS-1$

	private JTextField txtLaden = super.getNewLockedTextField();
	private JTextField txtLastfahrt = super.getNewLockedTextField();
	private JTextField txtEntladen = super.getNewLockedTextField();
	private JTextField txtLeerfahrt = super.getNewLockedTextField();
	private JTextField txtGesamt = super.getNewLockedTextField();

	public ErgebnisPanelHackschnitzelTransport2018() {
		super(false, true, false, false, true, true, true, false, false);
		
		super.lblRotationszeit.setText(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.AnzahlZyklen")); //$NON-NLS-1$
		
		//Panel Zeiten
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(15,0,0,0);
		this.add(initZeitenPanel(), c);
	}
	
	
	private JPanel initZeitenPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ErgebnisPanelHackschnitzelTransport2018.ZeitenProZyklusPMH15"))); //$NON-NLS-1$
		panel.setOpaque(false);
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Laden
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,5);
		panel.add(lblLaden, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		panel.add(txtLaden, c);
		
		
		//Lastfahrt
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5,30,0,5);
		panel.add(lblLastfahrt, c);
		
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		panel.add(txtLastfahrt, c);
		
		
		//Entladen
		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(5,30,0,5);
		panel.add(lblEntladen, c);
		
		c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		panel.add(txtEntladen, c);
		
		
		//Leerfahrt
		c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 0;
		c.insets = new Insets(5,30,0,5);
		panel.add(lblLeerfahrt, c);
		
		c = new GridBagConstraints();
		c.gridx = 7;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		panel.add(txtLeerfahrt, c);
		
		
		//Gesamt
		c = new GridBagConstraints();
		c.gridx = 8;
		c.gridy = 0;
		c.insets = new Insets(5,30,0,5);
		panel.add(lblGesamt, c);
		
		c = new GridBagConstraints();
		c.gridx = 9;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		panel.add(txtGesamt, c);
		
		
		return panel;
	}
	
	
	@Override
	public void setErgebnis(Ergebnis ergebnis) {
		super.setErgebnis(ergebnis);
		
		ErgebnisHackschnitzelTransport2018 erg = (ErgebnisHackschnitzelTransport2018) ergebnis;

		txtLaden	.setText(parseDouble( erg.getZeitLaden_h() 		));
		txtLastfahrt.setText(parseDouble( erg.getZeitLastfahrt_h() 	));
		txtEntladen	.setText(parseDouble( erg.getZeitEntladen_h() 	));
		txtLeerfahrt.setText(parseDouble( erg.getZeitLeerfahrt_h() 	));
		txtGesamt.setText(parseDouble( erg.getZeitProZyklus_h() ));
		
		txtRotationszeit.setText( "" + erg.getAnzahlZyklen() ); //$NON-NLS-1$
	}

}
