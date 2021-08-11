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
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorAnteilEnergieholz2018.Zopfklasse;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.ErgebnisEnergieholz;
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
public class BiomasseschaetzerEnergie2018 extends JDialog {
	
	public static void main(String[] args) {
		new BiomasseschaetzerEnergie2018();
	}
	
	/**
	 * Falls grösser 0, ist die gesamte Schaftholzmenge fixiert, und die Anzahl Stämme 
	 * werden automatisch berechnet. Ansonsten sind die Anzahl Stämme eine Inputgrösse.
	 */
	private final double constSchaftholzmengeGesamt_m3iR;
	
	private JSpinner txtAnteilLaubholz;
	private JSpinner txtBhd_cm;
	private JLabel lblBhd_cm_Info;
	private JComboBox<Standort> cmbStandort;
	private JComboBox<Gesamtwuchsleistung> cmbGesamtwuchsleistung;
	private JLabel lblAnzahlStaemme;
	private JSpinner txtAnzahlStaemme;
	private JLabel lblZopf_cm;
	private JComboBox<Zopfklasse> cmbZopf_cm;
	private JLabel lblZopf_cm_Info;

	private JLabel lblAnteilVolumenRundholz;
	private JSpinner txtAnteilVolumenRundholz;
	private JLabel lblAnteilVolumenRundholz_Info;

	private JLabel lblAnteilVolumenAstderbholz;
	private JSpinner txtAnteilVolumenAstderbholz;
	private JLabel lblAnteilVolumenAstderbholz_Info;	
	
	private JLabel lblAnteilVolumenReisig;
	private JSpinner txtAnteilVolumenReisig;	
	private JLabel lblAnteilVolumenReisig_Info;
	
	private JLabel lblErnteverlustDerbholzNadel;
	private JSpinner txtErnteverlustDerbholzNadel;
	private JLabel lblErnteverlustDerbholzNadel_Info;
	
	private JLabel lblErnteverlustDerbholzLaub;
	private JSpinner txtErnteverlustDerbholzLaub;
	private JLabel lblErnteverlustDerbholzLaub_Info;
	
	private JLabel lblErnteverlustNichtderbholzNadel;
	private JSpinner txtErnteverlustNichtderbholzNadel;
	private JLabel lblErnteverlustNichtderbholzNadel_Info;

	private JTextField txtVolumenSchaftholz_m3iR;
	private JTextField txtVolumenEnergierundholzUnterhalbZopf;
	private JTextField txtVolumenEnergieholzAmSchaftOberhalbZopf;
	private JTextField txtVolumenAstderbholz;
	private JTextField txtVolumenReisig;
	private JLabel lblVolumenEnergieholz;
	private JTextField txtVolumenEnergieholz;

	private JLabel lblStatus;
	private JButton btnUebernehmen;
	
	private ErgebnisEnergieholz letztesErgebnis = null;
	
	
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

	private static final DecimalFormat decimalFormat = new DecimalFormat("0.00"); //$NON-NLS-1$
	
	
	public BiomasseschaetzerEnergie2018() {
		this(-1, -1, null, -1);
	}
	
	
	public BiomasseschaetzerEnergie2018(final int bhd_cm, final int zopf_cm, final Forwarder2018 parent, final double constSchaftholzmengeGesamt_m3iR) {
		this.constSchaftholzmengeGesamt_m3iR = constSchaftholzmengeGesamt_m3iR;
		
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
				BiomasseschaetzerEnergie2018.this.setTitle(GuiStrings.getString("BiomasseschaetzerEnergie2018.Title")); //$NON-NLS-1$
				int width = MainWindow.getCurrentLocale().getLanguage().equals("de")? 1040 : 1220;
				BiomasseschaetzerEnergie2018.this.setSize((int) (width * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (570 * MainWindow.SIZE));
				BiomasseschaetzerEnergie2018.this.setLocationByPlatform(true);
				BiomasseschaetzerEnergie2018.this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				BiomasseschaetzerEnergie2018.this.setModal(false);

				//taskbar icon
				BiomasseschaetzerEnergie2018.this.setIconImage(MainWindow.getWslLogo().getImage());

				//content and data
				initContent();
				initData();
				if (bhd_cm > 0) {
					setBhd_cm(bhd_cm);
				}
				if (zopf_cm > 0) {
					setZopf_cm(zopf_cm);
				}
				if (constSchaftholzmengeGesamt_m3iR > 0) {
					txtAnzahlStaemme.setEnabled(false);
					lblAnzahlStaemme.setVisible(false);
					txtAnzahlStaemme.setVisible(false);
					Dimension size = BiomasseschaetzerEnergie2018.this.getSize();
					size.height -= 20;
					BiomasseschaetzerEnergie2018.this.setSize(size);
				}
				if (parent != null) {
					BiomasseschaetzerEnergie2018.this.setModal(true);
					btnUebernehmen.addActionListener(new ActionListener() {	
						@Override
						public void actionPerformed(ActionEvent e) {
							parent.setZopf_cm(((Zopfklasse)cmbZopf_cm.getSelectedItem()).getValue());
							parent.setEnergieholzErgebnis(letztesErgebnis);
							BiomasseschaetzerEnergie2018.this.setVisible(false);
						}
					});
				}
				else {
					lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
					btnUebernehmen.setVisible(false);
				}

				//show window
				BiomasseschaetzerEnergie2018.this.setVisible(true);
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
		c.gridwidth = 3;
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
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(5,10,5,10);
		JPanel pnlOutput = new JPanel();
		initPanelOutput(pnlOutput);
		this.add(pnlOutput, c);
		
		//Skizze Baumteile
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 4;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.anchor = GridBagConstraints.EAST;
//		c.insets = new Insets(0,0,0,10);
		ImageIcon imageIconSkizzeBaumteile = getImageIcon(getSkizzeBaumteilePath());
		Image imageSkizzeBaumteile = imageIconSkizzeBaumteile.getImage().getScaledInstance(-1, 410, Image.SCALE_SMOOTH); //$NON-NLS-1$
		JLabel lblSkizzeBaumteile = new JLabel(new ImageIcon(imageSkizzeBaumteile));
		this.add(lblSkizzeBaumteile, c);
		
		
		//Button Dokumentation
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
//		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,10,0,0);
		final JButton btnDokumentation = new JButton(GuiStrings.getString("HeProMoWindow.btnGrundlagen")); //$NON-NLS-1$
		btnDokumentation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DocumentationBroker.showDocumentation(Documentation.Energieholzschaetzer2018, BiomasseschaetzerEnergie2018.this, btnDokumentation);
			}			
		});
		this.add(btnDokumentation, c);
		
		//Label Status
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,0,0,30);
		lblStatus = new JLabel(" ", SwingConstants.RIGHT); //$NON-NLS-1$
		this.add(lblStatus, c);
		
		//Button Übernehmen
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,40);
		btnUebernehmen = new JButton(GuiStrings.getString("BiomasseschaetzerEnergie2018.EnergieholzvolumenUebernehmen")); //$NON-NLS-1$
		this.add(btnUebernehmen, c);
		
		
//		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 9;
//		c.gridy = 9;
//		c.weightx = 100;
//		c.weighty = 100;
//		this.add(new JPanel(), c);
	}
	
	
	private static String getSkizzeBaumteilePath() {
		Locale locale = MainWindow.getCurrentLocale();
		String langCode = locale.getLanguage().toUpperCase();
		return "data/SkizzeBaumteile_" + langCode + ".png";
	}
	
	
	private static ImageIcon getImageIcon(String filePath) {
		//try to open from jar
		URL imgURL = MainWindow.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		
		//otherwise, try to open from file system
		return new ImageIcon(filePath);
	}
	
	
	private void initPanelInput(JPanel pnlInput) {
		pnlInput.setBorder(TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("BiomasseschaetzerEnergie2018.Eingaben"))); //$NON-NLS-1$
		
		
		//set layout
		pnlInput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAnteilLaubholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.AnteilLaubholz")); //$NON-NLS-1$
		pnlInput.add(lblAnteilLaubholz, c);
		
		//Combo Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnteilLaubholz = new JSpinner();
		pnlInput.add(txtAnteilLaubholz, c);
		
		

		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblBhd = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.MittlererBhd_cm")); //$NON-NLS-1$
		pnlInput.add(lblBhd, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtBhd_cm = new JSpinner();
		pnlInput.add(txtBhd_cm, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblBhd_cm_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButton_MittlererBhd")); //$NON-NLS-1$
		pnlInput.add(lblBhd_cm_Info, c);
		
		

		//Label Standort
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblStandort = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.Standort")); //$NON-NLS-1$
		pnlInput.add(lblStandort, c);
		
		//Combo Standort
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbStandort = new JComboBox<>();
		cmbStandort.setMaximumRowCount(10);
		pnlInput.add(cmbStandort, c);
		
		

		//Label Gesamtwuchsleistung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,0,0,10);
		JLabel lblGesamtwuchsleistung = new JLabel(GuiStrings.getString("BiomasseschaetzerAbTarif2018.Gesamtwuchsleistung")); //$NON-NLS-1$
		pnlInput.add(lblGesamtwuchsleistung, c);
		
		//Combo Gesamtwuchsleistung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,0,10);
		cmbGesamtwuchsleistung = new JComboBox<>();
		pnlInput.add(cmbGesamtwuchsleistung, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblInfoButton3 = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerAbTarif2018.GesamtwuchsleistungInfoButton")); //$NON-NLS-1$
		pnlInput.add(lblInfoButton3, c);
		
		

		//Label Anzahl Stämme
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblAnzahlStaemme = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.AnzahlStaemme")); //$NON-NLS-1$
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
		
		

		//Label Zopf
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
//		c.insets = new Insets(12,0,0,0);
		lblZopf_cm = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.Zopfdurchmesser")); //$NON-NLS-1$
		pnlInput.add(lblZopf_cm, c);
		
		//Text Zopf
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
//		c.insets = new Insets(0,0,0,0);
		cmbZopf_cm = new JComboBox<>();
		pnlInput.add(cmbZopf_cm, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblZopf_cm_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonZopfdurchmesser")); //$NON-NLS-1$
		pnlInput.add(lblZopf_cm_Info, c);
		
		

		//Label Anteil Volumen Rundholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblAnteilVolumenRundholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenanteilRundholz")); //$NON-NLS-1$
		pnlInput.add(lblAnteilVolumenRundholz, c);
		
		//Text Anteil Volumen Rundholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnteilVolumenRundholz = new JSpinner();
		pnlInput.add(txtAnteilVolumenRundholz, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblAnteilVolumenRundholz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonRundholz")); //$NON-NLS-1$
		pnlInput.add(lblAnteilVolumenRundholz_Info, c);
		
		
		
		//Label Nadelholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(8,0,2,0);
		JLabel lblNadelholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.TitleNadelholz")); //$NON-NLS-1$
		pnlInput.add(lblNadelholz, c);
		
		

		//Label Anteil Volumen Reisig
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,0);
		lblAnteilVolumenReisig = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenanteilReisig")); //$NON-NLS-1$
		pnlInput.add(lblAnteilVolumenReisig, c);
		
		//Text Anteil Volumen Reisig
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnteilVolumenReisig = new JSpinner();
		pnlInput.add(txtAnteilVolumenReisig, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblAnteilVolumenReisig_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonReisig")); //$NON-NLS-1$
		pnlInput.add(lblAnteilVolumenReisig_Info, c);
		
		
		
		//Label Ernteverlust Derbholz Nadel
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,0);
		lblErnteverlustDerbholzNadel = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.ErnteverlustNadelderbholz")); //$NON-NLS-1$
		pnlInput.add(lblErnteverlustDerbholzNadel, c);
		
		//Text Ernteverlust Derbholz Nadel
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtErnteverlustDerbholzNadel = new JSpinner();
		pnlInput.add(txtErnteverlustDerbholzNadel, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblErnteverlustDerbholzNadel_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonDerbholz")); //$NON-NLS-1$
		pnlInput.add(lblErnteverlustDerbholzNadel_Info, c);
		
		
		
		//Label Ernteverlust Nicht-Derbholz Nadel
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblErnteverlustNichtderbholzNadel = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.ErnteverlustNadelNichtDerbholz")); //$NON-NLS-1$
		pnlInput.add(lblErnteverlustNichtderbholzNadel, c);
		
		//Text Ernteverlust Nicht-Derbholz Nadel
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtErnteverlustNichtderbholzNadel = new JSpinner();
		pnlInput.add(txtErnteverlustNichtderbholzNadel, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblErnteverlustNichtderbholzNadel_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonNichtDerbholz")); //$NON-NLS-1$
		pnlInput.add(lblErnteverlustNichtderbholzNadel_Info, c);
		
		
		
		//Label Laubhholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 11;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(8,0,2,0);
		JLabel lblLaubhholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.TitleLaubholz")); //$NON-NLS-1$
		pnlInput.add(lblLaubhholz, c);
		
		

		//Label Anteil Volumen Astderbholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 12;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblAnteilVolumenAstderbholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenanteilAstderbholz")); //$NON-NLS-1$
		pnlInput.add(lblAnteilVolumenAstderbholz, c);
		
		//Text Anteil Volumen Astderbholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 12;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnteilVolumenAstderbholz = new JSpinner();
		pnlInput.add(txtAnteilVolumenAstderbholz, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 12;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblAnteilVolumenAstderbholz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonAstderbholz")); //$NON-NLS-1$
		pnlInput.add(lblAnteilVolumenAstderbholz_Info, c);
		
		

		//Label Ernteverlust Derbholz Laub
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 13;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,0);
		lblErnteverlustDerbholzLaub = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.ErnteverlustLaubderbholz")); //$NON-NLS-1$
		pnlInput.add(lblErnteverlustDerbholzLaub, c);
		
		//Text Ernteverlust Derbholz Laub
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 13;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtErnteverlustDerbholzLaub = new JSpinner();
		pnlInput.add(txtErnteverlustDerbholzLaub, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 13;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		lblErnteverlustDerbholzLaub_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonDerbholz")); //$NON-NLS-1$
		pnlInput.add(lblErnteverlustDerbholzLaub_Info, c);
		
		

		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 99;
//		c.gridy = 99;
//		c.weightx = 400;
//		c.weighty = 100;
//		pnlInput.add(new JPanel(), c);
	}

	
	
	private void initPanelOutput(JPanel pnlOutput) {
		pnlOutput.setBorder(TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("BiomasseschaetzerEnergie2018.Ergebnis"))); //$NON-NLS-1$
		pnlOutput.setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		
		//set layout
		pnlOutput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		

		//Label Schaftholzvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblSchaftholzvolumen = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenSchaftholzMittlererBaum_m3iR")); //$NON-NLS-1$
		pnlOutput.add(lblSchaftholzvolumen, c);
		
		//Text Schaftholzvolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenSchaftholz_m3iR = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenSchaftholz_m3iR, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		JLabel lblVolumenSchaftholz_m3iR = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenSchaftholz_m3iR")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenSchaftholz_m3iR, c);
		
		

		//Label Volumen Energierundholz unterhalb Zopf
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenEnergierundholzUnterhalbZopf = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenEnergierundholz")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenEnergierundholzUnterhalbZopf, c);
		
		//Text Volumen Energierundholz unterhalb Zopf
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenEnergierundholzUnterhalbZopf = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenEnergierundholzUnterhalbZopf, c);

		//InfoButton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,5,0,5);
		JLabel lblVolumenEnergieholzAmRundholzUnterhalbZopf_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BiomasseschaetzerEnergie2018.InfoButtonEnergierundholz")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenEnergieholzAmRundholzUnterhalbZopf_Info, c);
		
		

		//Label Volumen Energieholz am Schaft oberhalb Zopf
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenEnergieholzAmSchaftOberhalbZopf = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenEnergieholzAmSchaft")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenEnergieholzAmSchaftOberhalbZopf, c);
		
		//Text Volumen Energieholz am Schaft oberhalb Zopf
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenEnergieholzAmSchaftOberhalbZopf = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenEnergieholzAmSchaftOberhalbZopf, c);
		
		

		//Label Volumen Astderbholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenAstderbholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenAstderbholz")); //$NON-NLS-1$
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
		
		

		//Label Volumen Reisig
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblVolumenReisig = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenReisig")); //$NON-NLS-1$
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
		
		

		//Label Volumen Energieholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblVolumenEnergieholz = new JLabel(GuiStrings.getString("BiomasseschaetzerEnergie2018.VolumenEnergieholz")); //$NON-NLS-1$
		pnlOutput.add(lblVolumenEnergieholz, c);
		
		//Text Volumen Energieholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVolumenEnergieholz = AbstractErgebnisPanel.getNewLockedTextField();
		pnlOutput.add(txtVolumenEnergieholz, c);
	}
	
	
	private void initData() {
		txtAnteilLaubholz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		addListener(txtAnteilLaubholz);
		txtAnteilLaubholz.addChangeListener(new ChangeListener() {			
			@Override
			public void stateChanged(ChangeEvent e) {
				setDefaultValuesBasedOnAnteilLaubholz();
			}
		});
		
		
		for (Standort value : Standort.getAllNadelLaubGemischt()) {
			cmbStandort.addItem(value);
		}
		addListener(cmbStandort);
		

		for (Zopfklasse value : Zopfklasse.values()) {
			cmbZopf_cm.addItem(value);
		}
		cmbZopf_cm.setSelectedItem(Zopfklasse.Zopf15);
		addListener(cmbZopf_cm);
		

		txtBhd_cm.setModel(new SpinnerNumberModel(20, 15, 55, 1));
		addListener(txtBhd_cm);
		

		for (Gesamtwuchsleistung value : Gesamtwuchsleistung.values()) {
			cmbGesamtwuchsleistung.addItem(value);
		}
		addListener(cmbGesamtwuchsleistung);
		

		txtAnzahlStaemme.setModel(new SpinnerNumberModel(10.0, 1, 10000, 1));
		if (constSchaftholzmengeGesamt_m3iR < 0) {
			addListener(txtAnzahlStaemme);
		}
		

		txtAnteilVolumenRundholz.setModel(new SpinnerNumberModel(5, 0, 100, 1));
		addListener(txtAnteilVolumenRundholz);
		

		txtAnteilVolumenAstderbholz.setModel(new SpinnerNumberModel(100, 0, 100, 1));
		addListener(txtAnteilVolumenAstderbholz);
		

		txtAnteilVolumenReisig.setModel(new SpinnerNumberModel(100, 0, 100, 1));
		addListener(txtAnteilVolumenReisig);
		

		txtErnteverlustDerbholzNadel.setModel(new SpinnerNumberModel(8, 0, 100, 1));
		addListener(txtErnteverlustDerbholzNadel);
		

		txtErnteverlustDerbholzLaub.setModel(new SpinnerNumberModel(13, 0, 100, 1));
		addListener(txtErnteverlustDerbholzLaub);
		

		txtErnteverlustNichtderbholzNadel.setModel(new SpinnerNumberModel(58, 0, 100, 1));
		addListener(txtErnteverlustNichtderbholzNadel);

		
		setDefaultValuesBasedOnAnteilLaubholz();
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
	
	private void setDefaultValuesBasedOnAnteilLaubholz() {
		dependentValuesAreBeingSet = true;
		
		int anteilLaubholz_Prz = (Integer) txtAnteilLaubholz.getValue();
		if (anteilLaubholz_Prz == 0) {
			//Nadelholz
//			txtAnteilVolumenAstderbholz.setValue(0);
			txtAnteilVolumenAstderbholz.setEnabled(false);
//			txtAnteilVolumenReisig.setValue(100);
			txtAnteilVolumenReisig.setEnabled(true);
//			txtErnteverlustDerbholz.setValue(8);
//			txtErnteverlustNichtderbholz.setValue(58);
			txtErnteverlustDerbholzNadel.setEnabled(true);
			txtErnteverlustDerbholzLaub.setEnabled(false);
			txtErnteverlustNichtderbholzNadel.setEnabled(true);
		}
		else if (anteilLaubholz_Prz == 100) {
			//Laubholz
//			txtAnteilVolumenAstderbholz.setValue(100);
			txtAnteilVolumenAstderbholz.setEnabled(true);
//			txtAnteilVolumenReisig.setValue(0);
			txtAnteilVolumenReisig.setEnabled(false);
//			txtErnteverlustDerbholz.setValue(13);
//			txtErnteverlustNichtderbholz.setValue(50);
			txtErnteverlustDerbholzNadel.setEnabled(false);
			txtErnteverlustDerbholzLaub.setEnabled(true);
			txtErnteverlustNichtderbholzNadel.setEnabled(false);
		}
		else {
			//Nadelholz/Laubholz gemischt
//			txtAnteilVolumenAstderbholz.setValue(anteilLaubholz_Prz);
			txtAnteilVolumenAstderbholz.setEnabled(true);
//			txtAnteilVolumenReisig.setValue(100 - anteilLaubholz_Prz);
			txtAnteilVolumenReisig.setEnabled(true);
			txtErnteverlustDerbholzNadel.setEnabled(true);
			txtErnteverlustDerbholzLaub.setEnabled(true);
			txtErnteverlustNichtderbholzNadel.setEnabled(true);
		}
		dependentValuesAreBeingSet = false;
		onInputChanged();
	}
	
	
	private void onInputChanged() {
		if (dependentValuesAreBeingSet) {
			return;
		}
		
		txtVolumenSchaftholz_m3iR.setText(decimalFormat.format(calcVolumenSchaftholz_m3iR()));
		
		if (constSchaftholzmengeGesamt_m3iR > 0) {
			try {
				txtAnzahlStaemme.setValue(constSchaftholzmengeGesamt_m3iR / (Double)decimalFormat.parse(txtVolumenSchaftholz_m3iR.getText()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ErgebnisEnergieholz ergebnis = BiomasseCalculatorKompartimente2018.calc(
					(Double) decimalFormat.parse(txtVolumenSchaftholz_m3iR.getText()),
					(Integer) txtAnteilLaubholz.getValue(),
					(Standort)cmbStandort.getSelectedItem(),
					(Zopfklasse) cmbZopf_cm.getSelectedItem(),
					(Integer) txtBhd_cm.getValue(),
					(Integer) txtAnteilVolumenRundholz.getValue(),
					(Integer) txtAnteilVolumenAstderbholz.getValue(),
					(Integer) txtAnteilVolumenReisig.getValue(),
					(Integer) txtErnteverlustDerbholzNadel.getValue(),
					(Integer) txtErnteverlustDerbholzLaub.getValue(),
					(Integer) txtErnteverlustNichtderbholzNadel.getValue(),
					(Double) txtAnzahlStaemme.getValue()
					);
			
			letztesErgebnis = ergebnis;
			txtVolumenEnergieholzAmSchaftOberhalbZopf.setText(	decimalFormat.format( ergebnis.getVolumenEnergieholzAmSchaftOberhalbZopf_m3iR() ));
			txtVolumenEnergierundholzUnterhalbZopf.setText(		decimalFormat.format( ergebnis.getVolumenEnergierundholzUnterhalbZopf_m3iR()	));
			txtVolumenAstderbholz.setText(						decimalFormat.format( ergebnis.getVolumenAstderbholz_m3iR() 					));
			txtVolumenReisig.setText(							decimalFormat.format( ergebnis.getVolumenReisig_m3iR()							));
			txtVolumenEnergieholz.setText(						decimalFormat.format( ergebnis.getVolumenEnergieholz_m3iR()	 					));
			lblStatus.setText(" "); //$NON-NLS-1$
			btnUebernehmen.setEnabled(true);
		} catch (IllegalArgumentException e) {
			//to catch exceptions in the calculations, as calculations
			// are done in EDT thread, which does not print those exceptions
//			e.printStackTrace();
			clearAllOutputFields();
			btnUebernehmen.setEnabled(false);
			setErrorStatus(GuiStrings.getString("BiomasseschaetzerEnergie2018.UngueltigeZopfBhdKombination")); //$NON-NLS-1$
		} catch (Exception e) {
			//to catch exceptions in the calculations, as calculations
			// are done in EDT thread, which does not print those exceptions
			e.printStackTrace();
			clearAllOutputFields();
			btnUebernehmen.setEnabled(false);
			setErrorStatus(GuiStrings.getString("BiomasseschaetzerEnergie2018.Fehler") + e.getMessage()); //$NON-NLS-1$
		}
	}
	
	
	private double calcVolumenSchaftholz_m3iR() {
		// Schritt 1: Inputgrössen auslesen  bzw. berechnen
		int anteilLaubholz_Prz = (Integer) txtAnteilLaubholz.getValue();
		int bhd_cm = (Integer) txtBhd_cm.getValue();
		Standort standort = (Standort) cmbStandort.getSelectedItem();
	
		Entwicklungsstufe entwicklungsstufe = null;
		if (bhd_cm <= 30) {
			entwicklungsstufe = Entwicklungsstufe.Stangenholz_12bis30cm;
		}
		else if (bhd_cm <= 40) {
			entwicklungsstufe = Entwicklungsstufe.Baumholz1_31bis40cm;
		}
		else if (bhd_cm <= 50) {
			entwicklungsstufe = Entwicklungsstufe.Baumholz2_41bis50cm;
		}
		else {
			entwicklungsstufe = Entwicklungsstufe.Baumholz3_ueber50cm;
		}

		int hoeheUeberMeer_m = standort.defaultHoehe;
		
		Tarifnummer[] tarife = Tarifnummer.values( standort );
		if (tarife.length != 2) {
			throw new RuntimeException("this should never happen!"); //$NON-NLS-1$
		}
		Mischtarif mischtarif = new Mischtarif(tarife[0], tarife[1], anteilLaubholz_Prz);	


		// Schritt 2: Volumen Schaftholz berechnen
		double volumenSchaftholz_m3iR_TarifNadel = BiomasseCalculatorSchaftholztarif2018.getSchaftholzVolumen_m3iR(
				mischtarif.tarifNadel,
				(Integer) txtBhd_cm.getValue(),
				(Gesamtwuchsleistung) cmbGesamtwuchsleistung.getSelectedItem(),
				entwicklungsstufe,
				hoeheUeberMeer_m
				);		

		double volumenSchaftholz_m3iR_TarifLaub = BiomasseCalculatorSchaftholztarif2018.getSchaftholzVolumen_m3iR(
				mischtarif.tarifLaub,
				(Integer) txtBhd_cm.getValue(),
				(Gesamtwuchsleistung) cmbGesamtwuchsleistung.getSelectedItem(),
				entwicklungsstufe,
				hoeheUeberMeer_m
				);

		double anteilTarifLaub = mischtarif.anteilTarifLaub_Prz / 100d;
		double anteilTarifNadel = 1d - anteilTarifLaub;
		double volumenSchaftholz_m3iR = anteilTarifNadel * volumenSchaftholz_m3iR_TarifNadel + anteilTarifLaub * volumenSchaftholz_m3iR_TarifLaub;
		
			
		return volumenSchaftholz_m3iR;
	}
	
	
	private class Mischtarif {
		public final Tarifnummer tarifNadel;
		public final Tarifnummer tarifLaub;
		public final int anteilTarifLaub_Prz;
		
		private Mischtarif(Tarifnummer tarifNadel, Tarifnummer tarifLaub, int anteilTarifLaub_Prz) {
			this.tarifNadel = tarifNadel;
			this.tarifLaub = tarifLaub;
			this.anteilTarifLaub_Prz = anteilTarifLaub_Prz;
		}
		
		@Override
		public String toString() {
			return (100-anteilTarifLaub_Prz) + "% " + tarifNadel.nr() + ", " + anteilTarifLaub_Prz + "% " + tarifLaub.nr(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}
	
	
	private void clearAllOutputFields() {
		txtVolumenEnergieholzAmSchaftOberhalbZopf.setText(	"" 	); //$NON-NLS-1$
		txtVolumenEnergierundholzUnterhalbZopf.setText(		"" 	); //$NON-NLS-1$
		txtVolumenAstderbholz.setText(						"" 	); //$NON-NLS-1$
		txtVolumenReisig.setText(							"" 	); //$NON-NLS-1$
		txtVolumenEnergieholz.setText(						"" 	); //$NON-NLS-1$
	}
	
	
	private void setErrorStatus(String msg) {
		lblStatus.setText("<html><font color=\"red\"><b>" + msg + "</b></font></html>"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	
	protected void setBhd_cm(int bhd_cm) {
		txtBhd_cm.setValue(bhd_cm);
		txtBhd_cm.setEnabled(false);
	}
	
	
	protected void setZopf_cm(int zopf_cm) {
		cmbZopf_cm.setSelectedItem(Zopfklasse.getNaechstKleinere(zopf_cm));
//		cmbZopf_cm.setEnabled(false);
	}

}
