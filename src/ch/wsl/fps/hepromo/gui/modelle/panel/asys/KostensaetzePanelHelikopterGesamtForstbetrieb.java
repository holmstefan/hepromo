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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanelHelikopterGesamtForstbetrieb extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzPersonal = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.SFr_pro_Std")); //$NON-NLS-1$
	private JLabel lblAnsatzMaschinen = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.SFr_pro_BStd")); //$NON-NLS-1$
	
	private JSpinner txtAnzahlPersonalFliegen = new JSpinner();
	private JSpinner txtAnsatzPersonalFliegen = new JSpinner();
	private JSpinner txtAnzahlPersonalLagerplatz = new JSpinner();
	private JSpinner txtAnsatzPersonalLagerplatz = new JSpinner();
	private JSpinner txtAnzahlMotorsaegen = new JSpinner();
	private JSpinner txtAnsatzMotorsaegen = new JSpinner();
	private JSpinner txtAnzahlKranfahrzeuge = new JSpinner();
	private JSpinner txtAnsatzKranfahrzeuge = new JSpinner();
	
	
	public KostensaetzePanelHelikopterGesamtForstbetrieb(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	protected void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.Forstbetrieb")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblAnzahl = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.Anzahl")); //$NON-NLS-1$
		this.add(lblAnzahl, c);
		
		//Label Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(lblAnsatzPersonal, c);

		
		
		//Label Personal Fliegen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblPersonalFliegen = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.PersonalBeimHolzFliegen")); //$NON-NLS-1$
		this.add(lblPersonalFliegen, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtAnzahlPersonalFliegen, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzPersonalFliegen, c);
		
		


		
		//Label Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(20,0,5,0);
		JLabel lblLagerplatz = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.LagerplatzarbeitNachHolzFliegen")); //$NON-NLS-1$
		this.add(lblLagerplatz, c);

		//InfoButton Lagerplatzarbeit
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(20,0,5,0);
		JLabel lblLagerplatzarbeit_Info = GuiStrings.getInfoButtonBlue(HeProMoWindow.wrap(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.InfoButtonLagerplatzarbeit"), 70)); //$NON-NLS-1$
		this.add(lblLagerplatzarbeit_Info, c);
		
		
		
		
		
		
		
		//Label Personal Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblPersonalLagerplatz = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.PersonalInklMaschinist")); //$NON-NLS-1$
		this.add(lblPersonalLagerplatz, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtAnzahlPersonalLagerplatz, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzPersonalLagerplatz, c);
		
		
		

		
		//Label Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		this.add(lblAnsatzMaschinen, c);
		
		
		
		
		//Label Motorsägen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblMotorsaegen = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.Motorsaege")); //$NON-NLS-1$
		this.add(lblMotorsaegen, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(txtAnzahlMotorsaegen, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(txtAnsatzMotorsaegen, c);
		
		
		
		//Label Kranfahrzeuge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,5,0);
		JLabel lblKranfahrzeuge = new JLabel(GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.Kranfahrzeug")); //$NON-NLS-1$
		this.add(lblKranfahrzeuge, c);
		
		//Text Anzahl
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,5);
		this.add(txtAnzahlKranfahrzeuge, c);
		
		//Text Ansatz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		this.add(txtAnsatzKranfahrzeuge, c);
	}



	protected void initData() {
		txtAnzahlPersonalFliegen.setModel(new SpinnerNumberModel(2, 1, 5, 1));
		txtAnzahlPersonalFliegen.setEditor(new JSpinner.NumberEditor(txtAnzahlPersonalFliegen, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlPersonalFliegen);
		
		txtAnsatzPersonalFliegen.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonalFliegen.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonalFliegen, "0.##")); //$NON-NLS-1$
		txtAnsatzPersonalFliegen.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				txtAnsatzPersonalLagerplatz.setValue(txtAnsatzPersonalFliegen.getValue());
			}
		});
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonalFliegen);
		
		
		txtAnzahlPersonalLagerplatz.setModel(new SpinnerNumberModel(2, 1, 5, 1));
		txtAnzahlPersonalLagerplatz.setEditor(new JSpinner.NumberEditor(txtAnzahlPersonalLagerplatz, "0.##")); //$NON-NLS-1$
		txtAnzahlPersonalLagerplatz.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlPersonalLagerplatz);
		
		txtAnsatzPersonalLagerplatz.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
		txtAnsatzPersonalLagerplatz.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonalLagerplatz, "0.##")); //$NON-NLS-1$
		txtAnsatzPersonalLagerplatz.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonalLagerplatz);

		
		txtAnzahlMotorsaegen.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		txtAnzahlMotorsaegen.setEditor(new JSpinner.NumberEditor(txtAnzahlMotorsaegen, "0.##")); //$NON-NLS-1$
		txtAnzahlMotorsaegen.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlMotorsaegen);
		
		txtAnsatzMotorsaegen.setModel(new SpinnerNumberModel(14.0, 0, 1000, 1));
		txtAnsatzMotorsaegen.setEditor(new JSpinner.NumberEditor(txtAnsatzMotorsaegen, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMotorsaegen);
		
		
		txtAnzahlKranfahrzeuge.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		txtAnzahlKranfahrzeuge.setEditor(new JSpinner.NumberEditor(txtAnzahlKranfahrzeuge, "0.##")); //$NON-NLS-1$
		txtAnzahlKranfahrzeuge.setEnabled(false);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlKranfahrzeuge);
		
		txtAnsatzKranfahrzeuge.setModel(new SpinnerNumberModel(80.0, 0, 1000, 1));
		txtAnsatzKranfahrzeuge.setEditor(new JSpinner.NumberEditor(txtAnsatzKranfahrzeuge, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzKranfahrzeuge);
	}
	
	

	
	public void setAnzahlPersonalLagerplatz(int anzahl) {
		txtAnzahlPersonalLagerplatz.setValue(anzahl);
	}
	
	public void setAnzahlMotorsaegen(int anzahl) {
		txtAnzahlMotorsaegen.setValue(anzahl);
	}
	
	public void setAnzahlKranfahrzeuge(int anzahl) {
		txtAnzahlKranfahrzeuge.setValue(anzahl);
	}
	
	
	
	
	
	
	public int getAnzahlPersonalFliegen() {
		return (Integer) txtAnzahlPersonalFliegen.getValue();
	}
	
	
	public double getAnsatzPersonalFliegen() {
		return (Double) txtAnsatzPersonalFliegen.getValue();
	}
	
	
	public int getAnzahlPersonalLagerplatz() {
		return (Integer) txtAnzahlPersonalLagerplatz.getValue();
	}
	
	
	public double getAnsatzPersonal() {
		return (Double) txtAnsatzPersonalLagerplatz.getValue();
	}
	
	
	public int getAnzahlMotorsaegen() {
		return (Integer) txtAnzahlMotorsaegen.getValue();
	}
	
	
	public double getAnsatzMotorsaegen() {
		return (Double) txtAnsatzMotorsaegen.getValue();
	}
	
	
	public int getAnzahlKranfahrzeuge() {
		return (Integer) txtAnzahlKranfahrzeuge.getValue();
	}
	
	
	public double getAnsatzKranfahrzeuge() {
		return (Double) txtAnsatzKranfahrzeuge.getValue();
	}



	public void setAnzahlPersonalFliegen(int anzahlPersonalBeimHolzFliegen) {
		txtAnzahlPersonalFliegen.setValue(anzahlPersonalBeimHolzFliegen);
	}


	public void setAnsatzPersonal(double kostenansatzPersonal1) {
		txtAnsatzPersonalLagerplatz.setValue(kostenansatzPersonal1);
		txtAnsatzPersonalFliegen.setValue(kostenansatzPersonal1);
	}


	public void setAnsatzMotorsaegen(double kostenansatzMaschine1) {
		txtAnsatzMotorsaegen.setValue(kostenansatzMaschine1);
	}


	public void setAnsatzKranfahrzeuge(double kostenansatzMaschine2) {
		txtAnsatzKranfahrzeuge.setValue(kostenansatzMaschine2);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzPersonal.setText(newValue + GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.ProStd")); //$NON-NLS-1$
		lblAnsatzMaschinen.setText(newValue + GuiStrings.getString("KostensaetzePanelHelikopterGesamtForstbetrieb.ProBStd")); //$NON-NLS-1$
	}

}
