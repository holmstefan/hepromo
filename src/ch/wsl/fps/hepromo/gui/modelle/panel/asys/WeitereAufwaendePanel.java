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
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
public class WeitereAufwaendePanel extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblTitleWaehrung = new JLabel("SFr.", SwingConstants.CENTER); //$NON-NLS-1$
	private JSpinner txtUmsetzenBetrag = new JSpinner();
	private JSpinner txtUmsetzenZeit = new JSpinner();	
	private JSpinner txtWeitereAufwaendeBetrag = new JSpinner();
	private JSpinner txtWeitereAufwaendeZeit = new JSpinner();	

	
	public WeitereAufwaendePanel(HeProMoWindow parent, boolean umsetzenAusblenden) {
		this.parent = parent;
		initPanel(umsetzenAusblenden);
		initData();
	}
	
	
	public WeitereAufwaendePanel(HeProMoWindow parent) {
		this(parent, false);
	}




	private void initPanel(boolean umsetzenAusblenden) {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("WeitereAufwaendePanel.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Ueberschrift Waehrung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(lblTitleWaehrung, c);
		
		//Label Ueberschrift Zeiteinheit
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblTitleZeiteinheit = new JLabel(GuiStrings.getString("WeitereAufwaendePanel.Std"), SwingConstants.CENTER); //$NON-NLS-1$
		this.add(lblTitleZeiteinheit, c);
		
		

		if (umsetzenAusblenden == false) {
			//Label Umsetzen
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			JLabel lblUmsetzen = new JLabel(GuiStrings.getString("WeitereAufwaendePanel.Umsetzen")); //$NON-NLS-1$
			this.add(lblUmsetzen, c);

			//Text Umsetzen Betrag
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,0,0,5);
			this.add(txtUmsetzenBetrag, c);

			//Text Umsetzen Zeit
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			this.add(txtUmsetzenZeit, c);
			
			//Infobutton Umsetzen
	        c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 1;
			c.insets = new Insets(0,5,0,0);
			JLabel lblUmsetzen_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("WeitereAufwaendePanel.InfoButton")); //$NON-NLS-1$
			this.add(lblUmsetzen_Info, c);
		}

		
		
		//Label Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMotorsaege1 = new JLabel(GuiStrings.getString("WeitereAufwaendePanel.WeitereAufwaende")); //$NON-NLS-1$
		this.add(lblMotorsaege1, c);
		
		//Text Weitere Aufwaende Betrag
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtWeitereAufwaendeBetrag, c);
		
		//Text Weitere Aufwaende Zeit
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtWeitereAufwaendeZeit, c);
		
		//Infobutton WeitereAufwaende
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.insets = new Insets(0,5,0,0);
		JLabel lblWeitereAufwaende_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("WeitereAufwaendePanel.InfoButton")); //$NON-NLS-1$
		this.add(lblWeitereAufwaende_Info, c);
	}




	private void initData() {
		txtUmsetzenBetrag.setModel(new SpinnerNumberModel(0.0, 0, 100000, 1));
		txtUmsetzenBetrag.setEditor(new JSpinner.NumberEditor(txtUmsetzenBetrag, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtUmsetzenBetrag);

		txtUmsetzenZeit.setModel(new SpinnerNumberModel(0.0, 0, 1000, 1));
		txtUmsetzenZeit.setEditor(new JSpinner.NumberEditor(txtUmsetzenZeit, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtUmsetzenZeit);

		txtWeitereAufwaendeBetrag.setModel(new SpinnerNumberModel(0.0, 0, 100000, 1));
		txtWeitereAufwaendeBetrag.setEditor(new JSpinner.NumberEditor(txtWeitereAufwaendeBetrag, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtWeitereAufwaendeBetrag);

		txtWeitereAufwaendeZeit.setModel(new SpinnerNumberModel(0.0, 0, 1000, 1));
		txtWeitereAufwaendeZeit.setEditor(new JSpinner.NumberEditor(txtWeitereAufwaendeZeit, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtWeitereAufwaendeZeit);
	}
	
	
	public double getUmsetzenBetrag_CHF() {
		return (Double) txtUmsetzenBetrag.getValue();
	}
	
	
	public double getUmsetzenZeit_h() {
		return (Double) txtUmsetzenZeit.getValue();
	}
	
	
	public double getWeitereAufwaendeBetrag_CHF() {
		return (Double) txtWeitereAufwaendeBetrag.getValue();
	}
	
	
	public double getWeitereAufwaendeZeit_h() {
		return (Double) txtWeitereAufwaendeZeit.getValue();
	}

	

	public void setUmsetzenBetrag_CHF(double umsetzenBetrag_CHF) {
		txtUmsetzenBetrag.setValue(umsetzenBetrag_CHF);
	}


	public void setUmsetzenZeit_h(double umsetzenZeit_h) {
		txtUmsetzenZeit.setValue(umsetzenZeit_h);
	}


	public void setWeitereAufwaendeBetrag_CHF(double weitereAufwaendeBetrag_CHF) {
		txtWeitereAufwaendeBetrag.setValue(weitereAufwaendeBetrag_CHF);
	}


	public void setWeitereAufwaendeZeit_h(double weitereAufwaendeZeit_h) {
		txtWeitereAufwaendeZeit.setValue(weitereAufwaendeZeit_h);
	}

	
	
	
	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblTitleWaehrung.setText(newValue);
	}

}
