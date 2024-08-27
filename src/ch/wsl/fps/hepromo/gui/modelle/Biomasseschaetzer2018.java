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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.AbstractErgebnisPanel;
import ch.wsl.fps.hepromo.gui.DocumentationBroker;
import ch.wsl.fps.hepromo.gui.DocumentationBroker.Documentation;
import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoExceptionHandler;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.ErgebnisBiomasse;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.NadelLaub;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.Standort;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Gesamtwuchsleistung;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Tarifnummer;

/**
 * Biomasseschätzer ohne Berechnungen Energieholz
 * 
 * @author Stefan Holm
 *
 */
public class Biomasseschaetzer2018 extends JFrame {
	
	public static void main(String[] args) {
		new Biomasseschaetzer2018();
	}	
	
	private JComboBox<NadelLaub> cmbBaumart;
	private JComboBox<Standort> cmbStandort;
	private JComboBox<Gesamtwuchsleistung> cmbGesamtwuchsleistung;
	private JSpinner txtBhd_cm;
	private JSpinner txtAnzahlStaemme;

	private JTextField txtVolumenSchaftholz_m3iR;
	private JTextField txtVolumenSchaftholzInRinde;
	private JTextField txtVolumenAstderbholz;
	private JTextField txtVolumenReisig;
	private JTextField txtVolumenBiomasse;
	private JTextField txtMasseNadelnBlaetter;

	private JLabel lblStatus;
	
	//used for JSpinner
	private final ChangeListener defaultChangeListner = evt -> {
		onInputChanged();
	};
	
	//used for JComboBox
	private final ActionListener defaultActionListener = evt -> {
		Object selectedItem = ((JComboBox<?>)evt.getSource()).getSelectedItem();
		if (selectedItem != null) {
			onInputChanged();
		}
	};

	private static final DecimalFormat decimalFormat = new DecimalFormat(",##0.00"); //$NON-NLS-1$

	
	
	public Biomasseschaetzer2018() {
		SwingUtilities.invokeLater(() -> {
			//load look & feel
			try {
				// Set System L&F
				UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
				//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel" );
			} 
			catch (UnsupportedLookAndFeelException 
					| ClassNotFoundException
					| InstantiationException
					| IllegalAccessException e) {
				HeProMoExceptionHandler.handle(e);
			}

			//tooltip settings
			ToolTipManager.sharedInstance().setInitialDelay(0);
			ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

			//window properties
			Biomasseschaetzer2018.this.setTitle(GuiStrings.getString("Biomasseschaetzer2018.Title")); //$NON-NLS-1$
			Biomasseschaetzer2018.this.setSize((int) (480 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (370 * MainWindow.SIZE));
			Biomasseschaetzer2018.this.setLocationByPlatform(true);
			Biomasseschaetzer2018.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			//taskbar icon
			Biomasseschaetzer2018.this.setIconImage(MainWindow.getWslLogo().getImage());

			//content and data
			initContent();
			initData();

			//show window
			Biomasseschaetzer2018.this.setVisible(true);
		});
	}
	
	
	protected void initContent() {
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Panel Input
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(10,10,5,10);
		JPanel pnlInput = new JPanel();
		initPanelInput(pnlInput);
		this.add(pnlInput, c);
		
		//Panel Output
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(5,10,5,10);
		JPanel pnlOutput = new JPanel();
		initPanelOutput(pnlOutput);
		this.add(pnlOutput, c);
		
		//Label Status
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(10,30,0,0);
		lblStatus = new JLabel(" ", SwingConstants.CENTER); //$NON-NLS-1$
		this.add(lblStatus, c);
		
		//Button Dokumentation
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,0,0,10);
		final JButton btnDokumentation = new JButton(GuiStrings.getString("HeProMoWindow.btnGrundlagen")); //$NON-NLS-1$
		btnDokumentation.addActionListener(evt -> {
			DocumentationBroker.showDocumentation(Documentation.Biomasseschaetzer2018, Biomasseschaetzer2018.this, btnDokumentation);
		});
		this.add(btnDokumentation, c);
		
		
//		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 9;
//		c.gridy = 9;
//		c.weightx = 100;
//		c.weighty = 100;
//		this.add(new JPanel(), c);
	}
	
	
	private void initPanelInput(JPanel pnlInput) {
		pnlInput.setBorder(TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("Biomasseschaetzer2018.Eingaben"))); //$NON-NLS-1$
		
		//set layout
		pnlInput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label Baumtyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblBaumart = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.Baumart")); //$NON-NLS-1$
		pnlInput.add(lblBaumart, c);
		
		//Combo Baumtyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbBaumart = new JComboBox<>();
		pnlInput.add(cmbBaumart, c);
		
		

		//Label Standort
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblStandort = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.Standort")); //$NON-NLS-1$
		pnlInput.add(lblStandort, c);
		
		//Combo Standort
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbStandort = new JComboBox<>();
		cmbStandort.setMaximumRowCount(10);
		pnlInput.add(cmbStandort, c);
		
		

		//Label Gesamtwuchsleistung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,0,0,10);
		JLabel lblGesamtwuchsleistung = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.Gesamtwuchsleistung")); //$NON-NLS-1$
		pnlInput.add(lblGesamtwuchsleistung, c);
		
		//Combo Gesamtwuchsleistung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,0,10);
		cmbGesamtwuchsleistung = new JComboBox<>();
		pnlInput.add(cmbGesamtwuchsleistung, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblInfoButton3 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.GesamtwuchsleistungInfoButton")); //$NON-NLS-1$
		pnlInput.add(lblInfoButton3, c);
		
		

		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblBhd = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.MittlererBhd_cm")); //$NON-NLS-1$
		pnlInput.add(lblBhd, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtBhd_cm = new JSpinner();
		pnlInput.add(txtBhd_cm, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		JLabel lblBhd_cm_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Biomasseschaetzer2018.InfoButtonMittlererBhd")); //$NON-NLS-1$
		pnlInput.add(lblBhd_cm_Info, c);
		
		

		//Label Anzahl Stämme
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAnzahlStaemme = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.AnzahlStaemme")); //$NON-NLS-1$
		pnlInput.add(lblAnzahlStaemme, c);
		
		//Text Anzahl Stämme
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnzahlStaemme = new JSpinner();
		pnlInput.add(txtAnzahlStaemme, c);
		
		

		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 99;
//		c.gridy = 99;
//		c.weightx = 400;
//		c.weighty = 100;
//		pnlInput.add(new JPanel(), c);
	}

	
	
	private void initPanelOutput(JPanel pnlOutput) {
		pnlOutput.setBorder(TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("Biomasseschaetzer2018.Ergebnis"))); //$NON-NLS-1$
		pnlOutput.setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		
		//set layout
		pnlOutput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label Schaftholzvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 150000;
		c.insets = new Insets(0,0,0,5);
		JLabel lblSchaftholzvolumen = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.VolumenSchaftholzMittlererBarum_m3iR")); //$NON-NLS-1$
		pnlOutput.add(lblSchaftholzvolumen, c);
		
		//Text Schaftholzvolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenSchaftholz_m3iR = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenSchaftholz_m3iR, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		JLabel lblVolumenSchaftholz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Biomasseschaetzer2018.InfoButtonSchaftholz")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenSchaftholz_Info, c);
		
		

		//Label Volumen Schaftholz in Rinde
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenSchaftholzInRinde = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.VolumenSchaftholz_m3iR")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenSchaftholzInRinde, c);
		
		//Text Volumen Schaftholz in Rinde
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenSchaftholzInRinde = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenSchaftholzInRinde, c);
		
		

		//Label Volumen Astderbholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenAstderbholz = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.VolumenAstderbholz_m3iR")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenAstderbholz, c);
		
		//Text Volumen Astderbholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenAstderbholz = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenAstderbholz, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		JLabel lblAstderbholz_Info2 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Biomasseschaetzer2018.InfoButtonAstderbholz")); //$NON-NLS-1$
		pnlOutput.add(lblAstderbholz_Info2, c);
		
		

		//Label Volumen Reisig
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenReisig = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.VolumenReisig_m3iR")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenReisig, c);
		
		//Text Volumen Reisig
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenReisig = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenReisig, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		JLabel lblReisig_Info2 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Biomasseschaetzer2018.InfoButtonReisig")); //$NON-NLS-1$
		pnlOutput.add(lblReisig_Info2, c);
		
		

		//Label Volumen Biomasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenBiomasse = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.VolumenBiomasse")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenBiomasse, c);
		
		//Text Volumen Biomasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenBiomasse = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenBiomasse, c);
		
		

		//Label Masse Nadeln und Blätter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblMasseNadelnBlaetter = new JLabel(GuiStrings.getString("Biomasseschaetzer2018.MasseNadelnBlaetter")); //$NON-NLS-1$
		pnlOutput.add(lblMasseNadelnBlaetter, c);
		
		//Text Masse Nadeln und Blätter
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtMasseNadelnBlaetter = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtMasseNadelnBlaetter, c);
	}
	
	
	protected void initData() {
		for (NadelLaub value : NadelLaub.values()) {
			cmbBaumart.addItem(value);
		}
		addListener(cmbBaumart);
		cmbBaumart.addActionListener(evt -> {
			Object selectedItem = ((JComboBox<?>)evt.getSource()).getSelectedItem();
			if (selectedItem != null) {
				setDefaultValuesBasedOnNadelholzOrLaubholz();
			}
		});
		
		
//		for (Standort value : Standort.values()) {
//			cmbStandort.addItem(value);
//		}
		addListener(cmbStandort);
		

		for (Gesamtwuchsleistung value : Gesamtwuchsleistung.values()) {
			cmbGesamtwuchsleistung.addItem(value);
		}
		addListener(cmbGesamtwuchsleistung);
		

		txtBhd_cm.setModel(new SpinnerNumberModel(20, 10, 100, 1));
		addListener(txtBhd_cm);
		

		txtAnzahlStaemme.setModel(new SpinnerNumberModel(10, 1, 10000, 1));
		addListener(txtAnzahlStaemme);

		
		setDefaultValuesBasedOnNadelholzOrLaubholz();
	}
	
	
	private void addListener(JComponent comp) {
		if (comp instanceof JSpinner) {
			HeProMoWindow.adjustJSpinnerFormatter((JSpinner) comp, false);
			((JSpinner) comp).addChangeListener(defaultChangeListner);
		}
		else if (comp instanceof JComboBox) {
			((JComboBox<?>) comp).addActionListener(defaultActionListener);
		}
		else {
			throw new IllegalArgumentException(comp.toString());
		}
	}
	
	private boolean dependentValuesAreBeingSet = false;
	
	private void setDefaultValuesBasedOnNadelholzOrLaubholz() {
		dependentValuesAreBeingSet = true;
		if (cmbBaumart.getSelectedItem() == NadelLaub.Nadelholz) {
			//Nadelholz
			cmbStandort.removeAllItems();
			for (Standort value : Standort.getAllRelevantForReisigNadel()) {
				cmbStandort.addItem(value);
			}
		}
		else {
			//Laubholz
			cmbStandort.removeAllItems();
			for (Standort value : Standort.getAllRelevantForAstderbholzLaub()) {
				cmbStandort.addItem(value);
			}
		}
		dependentValuesAreBeingSet = false;
		onInputChanged();
	}
	
	
	private void onInputChanged() {
		if (dependentValuesAreBeingSet) {
			return;
		}
		
		try {
			double volumenSchaftholz_m3iR = BiomasseCalculatorSchaftholztarif2018.getSchaftholzVolumen_m3iR(
					Tarifnummer.values(cmbBaumart.getSelectedItem().equals(NadelLaub.Nadelholz), (Standort) cmbStandort.getSelectedItem())[0],
					(Integer) txtBhd_cm.getValue(),
					(Gesamtwuchsleistung) cmbGesamtwuchsleistung.getSelectedItem(),
					BiomasseschaetzerAbTarif2018.getEntwicklungsstufeFromBhd((Integer) txtBhd_cm.getValue()),
					((Standort) cmbStandort.getSelectedItem()).defaultHoehe
					);
			txtVolumenSchaftholz_m3iR.setText(decimalFormat.format(volumenSchaftholz_m3iR));
			
			ErgebnisBiomasse ergebnis = BiomasseCalculatorKompartimente2018.calc(
					volumenSchaftholz_m3iR,
					(NadelLaub) cmbBaumart.getSelectedItem(),
					(Standort)cmbStandort.getSelectedItem(),
					(Integer) txtBhd_cm.getValue(),
					(Integer) txtAnzahlStaemme.getValue()
					);
			
			txtVolumenSchaftholzInRinde.setText(			decimalFormat.format( ergebnis.getVolumenSchaftholz_m3iR() 				));
			txtVolumenAstderbholz.setText(					decimalFormat.format( ergebnis.getVolumenAstderbholz_m3iR() 				));
			txtVolumenReisig.setText(						decimalFormat.format( ergebnis.getVolumenReisig_m3iR()						));
			txtVolumenBiomasse.setText(						decimalFormat.format( ergebnis.getVolumenBiomasseOhneNadelnUndBlaetter()	));
			txtMasseNadelnBlaetter.setText(					decimalFormat.format( ergebnis.getMasseNadelnUndBlaetter_kg()				));
			lblStatus.setText(" "); //$NON-NLS-1$
		}  catch (IllegalArgumentException e) {
			//to catch exceptions in the calculations, as calculations
			// are done in EDT thread, which does not print those exceptions
//			e.printStackTrace();
			clearAllOutputFields();
			setErrorStatus(GuiStrings.getString("Biomasseschaetzer2018.UngueltigeZopfBhdKombination")); //$NON-NLS-1$
		} catch (Exception e) {
			//to catch exceptions in the calculations, as calculations
			// are done in EDT thread, which does not print those exceptions
			e.printStackTrace();
			clearAllOutputFields();
			setErrorStatus(GuiStrings.getString("Biomasseschaetzer2018.Fehler") + e.getMessage()); //$NON-NLS-1$
		}
	}
	
	
	private void clearAllOutputFields() {
		txtVolumenSchaftholzInRinde.setText(			"" 	); //$NON-NLS-1$
		txtVolumenAstderbholz.setText(					"" 	); //$NON-NLS-1$
		txtVolumenReisig.setText(						"" 	); //$NON-NLS-1$
		txtVolumenBiomasse.setText(						"" 	); //$NON-NLS-1$
		txtMasseNadelnBlaetter.setText(					"" 	); //$NON-NLS-1$
	}
	
	
	private void setErrorStatus(String msg) {
		lblStatus.setText("<html><font color=\"red\"><b>" + msg + "</b></font></html>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
