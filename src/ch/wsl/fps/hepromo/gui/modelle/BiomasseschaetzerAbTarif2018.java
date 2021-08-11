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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.AbstractErgebnisPanel;
import ch.wsl.fps.hepromo.gui.DocumentationBroker;
import ch.wsl.fps.hepromo.gui.DocumentationBroker.Documentation;
import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.Standort;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Entwicklungsstufe;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Gesamtwuchsleistung;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorSchaftholztarif2018.Tarifnummer;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseschaetzerAbTarif2018 extends JFrame {
	
	public static void main(String[] args) {
		new BiomasseschaetzerAbTarif2018();
	}
	
	
	private JLabel lblTarifnummer;
	private JComboBox<Tarifnummer> cmbTarifnummer;
	private JSpinner txtBhd_cm;
	private JComboBox<Gesamtwuchsleistung> cmbGesamtwuchsleistung;
	private JComboBox<Entwicklungsstufe> cmbEntwicklungsstufe;
	private JSpinner txtHoeheUeberMeer_m;

	private JTextField txtVolumenSchaftholz;
	
	private JButton btnUebernehmen;

	private static final DecimalFormat decimalFormat = new DecimalFormat("0.000"); //$NON-NLS-1$
	
	
	//used for JSpinner
	private final ChangeListener defaultChangeListner = new ChangeListener(){
		@Override
		public void stateChanged(ChangeEvent e) {
			onInputChanged();
		}
	};
	
	//used for JComboBox
	private final ActionListener defaultActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object selectedItem = ((JComboBox<?>)e.getSource()).getSelectedItem();
			if (selectedItem != null) {
				onInputChanged();
			}
		}
	};
	
	
	public BiomasseschaetzerAbTarif2018() {
		this(-1, null, null, null);
	}
	
	
	public BiomasseschaetzerAbTarif2018(final int bhd_cm, final Boolean isNadelholz, final Standort standort, final JSpinner txtTargetSpinner) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {				
				
				//load look & feel
				try {
					// Set System L&F
					UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
					//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel" );
				} 
				catch (UnsupportedLookAndFeelException e) {
					// handle exception
				}
				catch (ClassNotFoundException e) {
					// handle exception
				}
				catch (InstantiationException e) {
					// handle exception
				}
				catch (IllegalAccessException e) {
					// handle exception
				}

				//tooltip settings
				ToolTipManager.sharedInstance().setInitialDelay(0);
				ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
				
				//window properties
				BiomasseschaetzerAbTarif2018.this.setTitle(GuiStrings.getString("BiomasseschaetzerAbTarif2018.TitleSchaftholzschaetzer")); //$NON-NLS-1$
				BiomasseschaetzerAbTarif2018.this.setSize((int) (500 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (275 * MainWindow.SIZE));
				BiomasseschaetzerAbTarif2018.this.setLocationByPlatform(true);
				BiomasseschaetzerAbTarif2018.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				//taskbar icon
				BiomasseschaetzerAbTarif2018.this.setIconImage(MainWindow.getWslLogo().getImage());

				//content and data
				initContent();
				initData(isNadelholz, standort);
				if (bhd_cm > 0) {
					txtBhd_cm.setValue(bhd_cm);
					txtBhd_cm.setEnabled(false);
				}
				if (txtTargetSpinner != null) {
					btnUebernehmen.addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								txtTargetSpinner.setValue(decimalFormat.parse(txtVolumenSchaftholz.getText()));
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							BiomasseschaetzerAbTarif2018.this.dispose();
						}
					});
				}
				else {
					btnUebernehmen.setVisible(false);
				}
				onInputChanged();

				//show window
				BiomasseschaetzerAbTarif2018.this.setVisible(true);
			}
		});
	}
	
	
	private void initContent() {
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
		c.insets = new Insets(10,30,5,30);
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
		c.insets = new Insets(5,30,10,30);
		JPanel pnlOutput = new JPanel();
		initPanelOutput(pnlOutput);
		this.add(pnlOutput, c);
		
		
		//Button Übernehmen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,20);
		btnUebernehmen = new JButton(GuiStrings.getString("BiomasseschaetzerAbTarif2018.SchaftholzvolumenUebernehmen")); //$NON-NLS-1$
		this.add(btnUebernehmen, c);
		
		
		//Button Dokumentation
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,0,0,10);
		final JButton btnDokumentation = new JButton(GuiStrings.getString("HeProMoWindow.btnGrundlagen")); //$NON-NLS-1$
		btnDokumentation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DocumentationBroker.showDocumentation(Documentation.Schaftholzschaetzer2018, BiomasseschaetzerAbTarif2018.this, btnDokumentation);
			}			
		});
		this.add(btnDokumentation, c);
		
		
//		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 9;
//		c.gridy = 9;
//		c.weightx = 50;
//		c.weighty = 50;
//		this.add(new JPanel(), c);
	}
	
	
	private void initPanelInput(JPanel pnlInput) {
		pnlInput.setBorder(TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("BiomasseschaetzerAbTarif2018.TitleEingaben"))); //$NON-NLS-1$
		
		//set layout
		pnlInput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label Tarifnummer
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblTarifnummer = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.Tarif")); //$NON-NLS-1$
		pnlInput.add(lblTarifnummer, c);
		
		//Combo Tarifnummer
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbTarifnummer = new JComboBox<>();
		pnlInput.add(cmbTarifnummer, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton1 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.TarifnummernGemaessLFI")); //$NON-NLS-1$
		pnlInput.add(lblInfoButton1, c);
		
		

		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblBhd_cm = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.Bhd_cm")); //$NON-NLS-1$
		pnlInput.add(lblBhd_cm, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtBhd_cm = new JSpinner();
		pnlInput.add(txtBhd_cm, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton2 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.BhdInfoButton")); //$NON-NLS-1$
		pnlInput.add(lblInfoButton2, c);
		
		

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
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton3 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.GesamtwuchsleistungInfoButton")); //$NON-NLS-1$
		pnlInput.add(lblInfoButton3, c);
		
		

		//Label Entwicklungsstufe
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblEntwicklungsstufe = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.Entwicklungsstufe")); //$NON-NLS-1$
		pnlInput.add(lblEntwicklungsstufe, c);
		
		//Combo Entwicklungsstufe
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbEntwicklungsstufe = new JComboBox<>();
		pnlInput.add(cmbEntwicklungsstufe, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton4 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.EntwicklungsstufeInfoButton")); //$NON-NLS-1$
		pnlInput.add(lblInfoButton4, c);
		
		

		//Label Höhe über Meer
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblHoeheUeberMeer_m = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.HoeheUeberMeer")); //$NON-NLS-1$
		pnlInput.add(lblHoeheUeberMeer_m, c);
		
		//Text Höhe über Meer
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtHoeheUeberMeer_m = new JSpinner();
		pnlInput.add(txtHoeheUeberMeer_m, c);
	}

	
	
	private void initPanelOutput(JPanel pnlOutput) {
		pnlOutput.setBorder(TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("BiomasseschaetzerAbTarif2018.TitleErgebnis"))); //$NON-NLS-1$
		pnlOutput.setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		//set layout
		pnlOutput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label Volumen Schaftholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenSchaftholz = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.VolumenSchaftholz")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenSchaftholz, c);
		
		//Text Volumen Schaftholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenSchaftholz = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenSchaftholz, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.SchaftholzInfoButton")); //$NON-NLS-1$
		pnlOutput.add(lblInfoButton, c);
	}
	
	
	
	private void initData(Boolean isNadelholz, Standort standort) {
		if (isNadelholz == null) {
			for (Tarifnummer value : Tarifnummer.values()) {
				cmbTarifnummer.addItem(value);
			}
		}
		else {
			for (Tarifnummer value : Tarifnummer.values(isNadelholz, standort)) {
				cmbTarifnummer.addItem(value);
			}
		}

		cmbTarifnummer.setMaximumRowCount(10);
		if (cmbTarifnummer.getItemCount() <= 1) {
			cmbTarifnummer.setEnabled(false);
		}
//		((JLabel)cmbTarifnummer.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		addListener(cmbTarifnummer);
		

		txtBhd_cm.setModel(new SpinnerNumberModel(20, 12, 100, 1));
		addListener(txtBhd_cm);
		txtBhd_cm.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				entwicklungsstufeAnBhdAnpassen((Integer) txtBhd_cm.getValue());
			}
		});
		

		for (Gesamtwuchsleistung value : Gesamtwuchsleistung.values()) {
			cmbGesamtwuchsleistung.addItem(value);
		}
		addListener(cmbGesamtwuchsleistung);
		

		for (Entwicklungsstufe value : Entwicklungsstufe.values()) {
			cmbEntwicklungsstufe.addItem(value);
		}
		addListener(cmbEntwicklungsstufe);
		cmbEntwicklungsstufe.setEnabled(false);
		

		txtHoeheUeberMeer_m.setModel(new SpinnerNumberModel(500, 0, 3000, 100));
		if (standort != null) {
			((SpinnerNumberModel)txtHoeheUeberMeer_m.getModel()).setMinimum(standort.minHoehe);
			((SpinnerNumberModel)txtHoeheUeberMeer_m.getModel()).setMaximum(standort.maxHoehe);
			((SpinnerNumberModel)txtHoeheUeberMeer_m.getModel()).setValue(standort.defaultHoehe);
		}
		addListener(txtHoeheUeberMeer_m);
	}
	
	
	private void entwicklungsstufeAnBhdAnpassen(int bhd) {
		Entwicklungsstufe entwicklungsstufe = getEntwicklungsstufeFromBhd(bhd);
		cmbEntwicklungsstufe.setSelectedItem(entwicklungsstufe);
	}
	
	
	public static Entwicklungsstufe getEntwicklungsstufeFromBhd(int bhd) {
		if (bhd <= 30) {
			return Entwicklungsstufe.Stangenholz_12bis30cm;
		}
		else if (bhd <= 40) {
			return Entwicklungsstufe.Baumholz1_31bis40cm;
		}
		else if (bhd <= 50) {
			return Entwicklungsstufe.Baumholz2_41bis50cm;
		}
		else {
			return Entwicklungsstufe.Baumholz3_ueber50cm;
		}
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
	
	
	private void onInputChanged() {
		double volumenSchaftholz_m3iR = BiomasseCalculatorSchaftholztarif2018.getSchaftholzVolumen_m3iR(
				(Tarifnummer) cmbTarifnummer.getSelectedItem(),
				(Integer) txtBhd_cm.getValue(),
				(Gesamtwuchsleistung) cmbGesamtwuchsleistung.getSelectedItem(),
				(Entwicklungsstufe) cmbEntwicklungsstufe.getSelectedItem(),
				(Integer) txtHoeheUeberMeer_m.getValue()
				);
		txtVolumenSchaftholz.setText(decimalFormat.format(volumenSchaftholz_m3iR));
	}

}
