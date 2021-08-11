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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.ErgebnisColorListCellRenderer;
import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AbstandRueckegasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Energieholzanfall;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder2018.Rueckeentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder2018.Forwardertyp;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.ErgebnisEnergieholz;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018.ErgebnisAnzeige;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class Forwarder2018 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private BiomasseschaetzerEnergie2018 energieholzschaetzer = null;
	
	private boolean holzmengenUnveraendertSeitBerechnungEnergieholzmengen = false;
	private boolean hinweisAenderungenBeimNachestenChangeZeigen = false;
	
	protected final DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
	protected double verkaufsmenge_m3oR;

	protected JLabel lblSchaftholz_m3iR;
	protected JSpinner txtSchaftholz_m3iR;
	protected JLabel lblSchaftholz_Info;
	protected JLabel lblEnergieholzmenge_m3iR;
	protected JSpinner txtEnergieholzmenge_m3iR;
	protected JLabel lblEnergieholz_Info;
	protected JLabel lblEnergieholzAusSchaftholz_m3iR;
	protected JSpinner txtEnergieholzAusSchaftholz_m3iR;
	protected JLabel lblEnergieholzAusSchaftholz_Info;
	protected JLabel lblEnergieholzAusAstderbholzUndReisig_m3iR;
	protected JSpinner txtEnergieholzAusAstderbholzUndReisig_m3iR;
	private JLabel lblVerkaufRundholz_m3iR;
	protected JSpinner txtVerkaufRundholz_m3iR;
	protected JLabel lblVerkaufRundholz_m3oR_Info;
	protected JButton btnEnergieholzvolumenBerechnen;
	private JSpinner txtBhdMit_cm;
	private JComboBox<Rueckeentfernung> cmbRueckeentfernung;
	private JComboBox<Hangneigung> cmbHangneigung;
	private JComboBox<AnzahlSortimente> cmbAnzahlSortimente;
	private JComboBox<Erschwernisse> cmbErschwernisse;
	private JComboBox<AbstandRueckegasse> cmbAbstandRueckegasse;
	
	protected JLabel lblZopfdurchmesser_cm;
	protected JSpinner txtZopfdurchmesser_cm;
	protected JLabel lblEnergieholzanfall_m3iRproHa;
	protected JComboBox<Energieholzanfall> cmbEnergieholzanfall_m3iRproHa;
	protected JLabel lblEnergieholzanfall_Info;
	
	private JComboBox<Forwardertyp> cmbForwardertyp;

	protected JLabel lblErgebnisanzeige;
	protected JComboBox<ErgebnisAnzeige> cmbErgebnisanzeige;
	
	private static final HashMap<ImgCode, ImageIcon> imageCache = new HashMap<ImgCode, ImageIcon>();
	protected JLabel lblSkizzeBaumteile;
	
	private JLabel lblMouseSensitiveArea;
	
	private ModelForwarder2018 model = new ModelForwarder2018();
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.setBorder(TitledBorderFactory.createTitledBorder("")); //$NON-NLS-1$
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Label Schaftholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblSchaftholz_m3iR = new JLabel(GuiStrings.getString("Forwarder2018.Schaftholz_m3iR")); //$NON-NLS-1$
		panel.add(lblSchaftholz_m3iR, c);
		
		//Text Schaftholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtSchaftholz_m3iR = new JSpinner();
		panel.add(txtSchaftholz_m3iR, c);

		//Info Schaftholz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		lblSchaftholz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Forwarder2018.InfoButtonSchaftholz")); //$NON-NLS-1$
		panel.add(lblSchaftholz_Info, c);


		
		//Label Energieholzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,15,0,0);
		lblEnergieholzmenge_m3iR = new JLabel(GuiStrings.getString("Forwarder2018.Energieholz_m3iR")); //$NON-NLS-1$
		panel.add(lblEnergieholzmenge_m3iR, c);
		
		//Text Energieholzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtEnergieholzmenge_m3iR = new JSpinner();
		panel.add(txtEnergieholzmenge_m3iR, c);

		//Info Energieholzmenge
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		lblEnergieholz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Forwarder2018.InfoButtonEnergieholz")); //$NON-NLS-1$
		panel.add(lblEnergieholz_Info, c);
		
		//Button Energieholzanteil berechnen
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 30;
		c.insets = new Insets(0,5,0,0);
		btnEnergieholzvolumenBerechnen = new JButton(GuiStrings.getString("Forwarder2018.Berechnen")); //$NON-NLS-1$
		btnEnergieholzvolumenBerechnen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int bhd_cm = (Integer) txtBhdMit_cm.getValue();
				int zopf_cm = (Integer) txtZopfdurchmesser_cm.getValue();
				if (energieholzschaetzer == null) {
					energieholzschaetzer = new BiomasseschaetzerEnergie2018(bhd_cm, zopf_cm, Forwarder2018.this, (Double) txtSchaftholz_m3iR.getValue());
				}
				else {
					energieholzschaetzer.setBhd_cm(bhd_cm);
					energieholzschaetzer.setZopf_cm(zopf_cm);
					energieholzschaetzer.setVisible(true);
				}
				
			}
		});
		panel.add(btnEnergieholzvolumenBerechnen, c);


		
		
		//Label Energieholz aus Schaftholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,10,0,0);
		lblEnergieholzAusSchaftholz_m3iR = new JLabel(GuiStrings.getString("Forwarder2018.EnergieholzAusSchaftholz_m3iR")); //$NON-NLS-1$
		panel.add(lblEnergieholzAusSchaftholz_m3iR, c);
		
		//Text Energieholz aus Schaftholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtEnergieholzAusSchaftholz_m3iR = new JSpinner();
		panel.add(txtEnergieholzAusSchaftholz_m3iR, c);

		//Info Energieholz aus Schaftholz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		lblEnergieholzAusSchaftholz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Forwarder2018.InfoButtonEnergieholzAusSchaftholz")); //$NON-NLS-1$
		panel.add(lblEnergieholzAusSchaftholz_Info, c);


		
		
		//Label Energieholz aus Astderbholz und Reisig
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,10,0,8);
		lblEnergieholzAusAstderbholzUndReisig_m3iR = new JLabel(GuiStrings.getString("Forwarder2018.EnergieholzAusAstderbholzUndReisig_m3iR")); //$NON-NLS-1$
		panel.add(lblEnergieholzAusAstderbholzUndReisig_m3iR, c);
		
		//Text Energieholz aus Astderbholz und Reisig
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtEnergieholzAusAstderbholzUndReisig_m3iR = new JSpinner();
		panel.add(txtEnergieholzAusAstderbholzUndReisig_m3iR, c);


		
		
		//Label Verkauf Rundholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblVerkaufRundholz_m3iR = new JLabel(GuiStrings.getString("Forwarder2018.VerkaufRundholz_m3iR")); //$NON-NLS-1$
		panel.add(lblVerkaufRundholz_m3iR, c);
		
		//Text Verkauf Rundholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtVerkaufRundholz_m3iR = new JSpinner();
		panel.add(txtVerkaufRundholz_m3iR, c);

		//Info Verkauf Rundholz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		lblVerkaufRundholz_m3oR_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Forwarder2018.InfoButtonVerkaufRundholz")); //$NON-NLS-1$
		lblVerkaufRundholz_m3oR_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		lblVerkaufRundholz_m3oR_Info.setMinimumSize(new Dimension(120, 12));
		panel.add(lblVerkaufRundholz_m3oR_Info, c);


		
		
		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(10,0,0,0);
		JLabel lblBhdMit_cm = new JLabel(GuiStrings.getString("Forwarder2018.MittlererBhd_cm")); //$NON-NLS-1$
		panel.add(lblBhdMit_cm, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		txtBhdMit_cm = new JSpinner();
		panel.add(txtBhdMit_cm, c);

		
		

		//Label Rueckeentfernung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblRueckeentfernung = new JLabel(GuiStrings.getString("Forwarder2018.Rueckeentfernung")); //$NON-NLS-1$
		panel.add(lblRueckeentfernung, c);
		
		//Text Rueckeentfernung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbRueckeentfernung = new JComboBox<>();
		panel.add(cmbRueckeentfernung, c);

		
		

		//Label Hangneigung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblHangneigung = new JLabel(GuiStrings.getString("Forwarder2018.Hangneigung")); //$NON-NLS-1$
		panel.add(lblHangneigung, c);
		
		//Text Hangneigung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbHangneigung = new JComboBox<>();
		panel.add(cmbHangneigung, c);

		
		

		//Label Anzahl Sortimente
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAnzahlSortimente = new JLabel(GuiStrings.getString("Forwarder2018.AnzahlSortimente")); //$NON-NLS-1$
		panel.add(lblAnzahlSortimente, c);
		
		//Text Anzahl Sortimente
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbAnzahlSortimente = new JComboBox<>();
		panel.add(cmbAnzahlSortimente, c);

		
		

		//Label Erschwernisse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblErschwernisse = new JLabel(GuiStrings.getString("Forwarder2018.Erschwernisse")); //$NON-NLS-1$
		panel.add(lblErschwernisse, c);
		
		//Text Erschwernisse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbErschwernisse = new JComboBox<>();
		panel.add(cmbErschwernisse, c);

		
		

		//Label Abstand Rückegasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAbstandRueckegasse = new JLabel(GuiStrings.getString("Forwarder2018.AbstandRueckegassen")); //$NON-NLS-1$
		panel.add(lblAbstandRueckegasse, c);
		
		//Text Abstand Rückegasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbAbstandRueckegasse = new JComboBox<>();
		panel.add(cmbAbstandRueckegasse, c);

		
		

		//Label Zopfdurchmesser
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 11;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblZopfdurchmesser_cm = new JLabel(GuiStrings.getString("Forwarder2018.Zopfdurchmesser_cm")); //$NON-NLS-1$
		panel.add(lblZopfdurchmesser_cm, c);
		
		//Text Zopfdurchmesser
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 11;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtZopfdurchmesser_cm = new JSpinner();
		panel.add(txtZopfdurchmesser_cm, c);

		
		

		//Label Energieholzanfall
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 12;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,0,0,5);
		lblEnergieholzanfall_m3iRproHa = new JLabel(GuiStrings.getString("Forwarder2018.Energieholzanfall_m3iRproHa")); //$NON-NLS-1$
		panel.add(lblEnergieholzanfall_m3iRproHa, c);
		
		//Text Energieholzanfall
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 12;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbEnergieholzanfall_m3iRproHa = new JComboBox<>();
		panel.add(cmbEnergieholzanfall_m3iRproHa, c);

		//Info Energieholzanfall
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 12;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		lblEnergieholzanfall_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Forwarder2018.InfoButtonEnergieholzanfall")); //$NON-NLS-1$
		panel.add(lblEnergieholzanfall_Info, c);

		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 13;
//		c.weightx = 200;
		c.weighty = 100;
		panel.add(new JPanel(), c);

		
		

		//Label Ergebnisanzeige
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 14;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		lblErgebnisanzeige = new JLabel(GuiStrings.getString("Forwarder2018.Ergebnisanzeige")); //$NON-NLS-1$
		lblErgebnisanzeige.setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		lblErgebnisanzeige.setOpaque(true);
		lblErgebnisanzeige.setFont(lblErgebnisanzeige.getFont().deriveFont(Font.BOLD));
		panel.add(lblErgebnisanzeige, c);
		
		//Text Ergebnisanzeige
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 14;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbErgebnisanzeige = new JComboBox<>();
		cmbErgebnisanzeige.setName("ergebnisanzeige");
		cmbErgebnisanzeige.setRenderer(new ErgebnisColorListCellRenderer());
		cmbErgebnisanzeige.setFont(cmbErgebnisanzeige.getFont().deriveFont(Font.BOLD));
		panel.add(cmbErgebnisanzeige, c);
		
		
		
		//Skizze Baumteile
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 2;
		c.gridheight = 10;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,10,0,0);
		lblSkizzeBaumteile = new JLabel(getScaledImageIcon(ImgCode.ALLES));
		panel.add(lblSkizzeBaumteile, c);
		

		
		
		//Mouse-sensitive area
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 5;
		c.fill = GridBagConstraints.BOTH;
		lblMouseSensitiveArea = new JLabel();
		lblMouseSensitiveArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				if (isMouseWithinSensitiveArea() == false) {
					lblSkizzeBaumteile.setIcon(getScaledImageIcon(ImgCode.ALLES));
				}
			}
		});
		panel.add(lblMouseSensitiveArea, c);
	}
	
	
	private enum ImgCode {
		ALLES("Alles"),
		SCHAFTHOLZ("Schaftholz"),
		ENERGIEHOLZ("Energieholz"),
		ENERGIEHOLZ_AUS_SCHAFTHOLZ("SchaftholzEnergie"),
		ASTDERBHOLZREISIG("AstderbholzReisig"),
		RUNDHOLZ("Rundholz"),
		ZOPFDURCHMESSER("Zopfdurchmesser");

		private final String fileSuffix;
		
		private ImgCode(String fileSuffix) {
			this.fileSuffix = fileSuffix;
		}
	}
	
	
	private static ImageIcon getScaledImageIcon(ImgCode imgCode) {
		if (imageCache.containsKey(imgCode)) {
			return imageCache.get(imgCode);
		}
		else {
			ImageIcon imageIconSkizzeBaumteile = getImageIcon("data/SkizzeBaumteile_" + imgCode.fileSuffix + ".png");
			Image imageSkizzeBaumteile = imageIconSkizzeBaumteile.getImage().getScaledInstance(-1, 230, Image.SCALE_SMOOTH);
			ImageIcon result = new ImageIcon(imageSkizzeBaumteile);
			imageCache.put(imgCode, result);
			return result;
		}
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
	
	
	@Override
	protected JPanel initSpecialPanel() {
		JPanel panel = new JPanel();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Forwardertyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,8,20,5);
		JLabel lblForwardertyp = new JLabel(GuiStrings.getString("Forwarder2018.Forwardertyp")); //$NON-NLS-1$
		panel.add(lblForwardertyp, c);
		
		//Combo Forwardertyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,5);
		cmbForwardertyp = new JComboBox<>();
		panel.add(cmbForwardertyp, c);
		
		return panel;
	}

	
	@Override
	protected void initData() {
		String labelPersonal = getModel().getArbeitssystem().getLabelPersonal1();
		String labelMaschine1 = getModel().getArbeitssystem().getLabelMaschine1();
		
		panelKostensaetze.setLabelPersonal(labelPersonal);
		panelKostensaetze.setLabelMaschine1(labelMaschine1);
		
		txtSchaftholz_m3iR.setModel(new SpinnerNumberModel(100.0, 0, 100000, 10));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtSchaftholz_m3iR);
		txtSchaftholz_m3iR.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				onHolzmengenChange();
			}
		});
		
		txtEnergieholzmenge_m3iR.setModel(new SpinnerNumberModel(100.0, 0, 100000, 10));
		setSpinnerMaximumFractionDigits(txtEnergieholzmenge_m3iR);
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEnergieholzmenge_m3iR);
		txtEnergieholzmenge_m3iR.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				onHolzmengenChange();
			}
		});
		
		txtEnergieholzAusSchaftholz_m3iR.setModel(new SpinnerNumberModel());
		setSpinnerMaximumFractionDigits(txtEnergieholzAusSchaftholz_m3iR);
		txtEnergieholzAusSchaftholz_m3iR.setEnabled(false);

		txtEnergieholzAusAstderbholzUndReisig_m3iR.setModel(new SpinnerNumberModel());
		setSpinnerMaximumFractionDigits(txtEnergieholzAusAstderbholzUndReisig_m3iR);
		txtEnergieholzAusAstderbholzUndReisig_m3iR.setEnabled(false);

		txtVerkaufRundholz_m3iR.setModel(new SpinnerNumberModel(100.0, 0, 100000, 10));
		setSpinnerMaximumFractionDigits(txtVerkaufRundholz_m3iR);
		txtVerkaufRundholz_m3iR.setEnabled(false);
		
		
		txtBhdMit_cm.setModel(new SpinnerNumberModel(30, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtBhdMit_cm);
		
		
		for (Rueckeentfernung value : Rueckeentfernung.values()) {
			cmbRueckeentfernung.addItem(value);
		}
		addDefaultActionListener(cmbRueckeentfernung);
		
		
		for (Hangneigung value : Hangneigung.values()) {
			cmbHangneigung.addItem(value);
		}
		addDefaultActionListener(cmbHangneigung);
		
		
		for (AnzahlSortimente value : AnzahlSortimente.values()) {
			cmbAnzahlSortimente.addItem(value);
		}
		addDefaultActionListener(cmbAnzahlSortimente);
		
		
		for (Erschwernisse value : Erschwernisse.values()) {
			cmbErschwernisse.addItem(value);
		}
		addDefaultActionListener(cmbErschwernisse);
		
		
		for (AbstandRueckegasse value : AbstandRueckegasse.values()) {
			cmbAbstandRueckegasse.addItem(value);
		}
		addDefaultActionListener(cmbAbstandRueckegasse);
		
		
		txtZopfdurchmesser_cm.setModel(new SpinnerNumberModel(10, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtZopfdurchmesser_cm);
		
		
		for (Energieholzanfall energieholzanfall : model.getArbeitsobjekt().getAllEnergieholzanfall()) {
			cmbEnergieholzanfall_m3iRproHa.addItem(energieholzanfall);
		}
		addDefaultActionListener(cmbEnergieholzanfall_m3iRproHa);
		
		
		for (Forwardertyp value : Forwardertyp.values()) {
			cmbForwardertyp.addItem(value);
		}
		addDefaultActionListener(cmbForwardertyp);

		
		for (ErgebnisAnzeige value : ErgebnisAnzeige.values()) {
			cmbErgebnisanzeige.addItem(value);
		}
		addDefaultActionListener(cmbErgebnisanzeige);
//		cmbErgebnisanzeige.setEnabled(false);		
		cmbErgebnisanzeige.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (((ErgebnisAnzeige)cmbErgebnisanzeige.getSelectedItem()) == ErgebnisAnzeige.Rundholz) {
					((ErgebnisPanel) ergebnisPanel).setTitle(GuiStrings.getString("Forwarder2018.ErgebnistitelRundholz")); //$NON-NLS-1$
					((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet(GuiStrings.getString("HeProMoWindow2014.Produktivitaet_m3_iR_pro_PMH15")); //$NON-NLS-1$
					((ErgebnisPanel) ergebnisPanel).setLabelKostenProM3(GuiStrings.getString("HeProMoWindow2014.ProM3oR")); //$NON-NLS-1$
				}
				else if (((ErgebnisAnzeige)cmbErgebnisanzeige.getSelectedItem()) == ErgebnisAnzeige.Energieholz) {
					((ErgebnisPanel) ergebnisPanel).setTitle(GuiStrings.getString("Forwarder2018.ErgebnistitelEnergieholz")); //$NON-NLS-1$
					((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet(GuiStrings.getString("Forwarder2018.Produktivitaet_m3iRproPmh15")); //$NON-NLS-1$
					((ErgebnisPanel) ergebnisPanel).setLabelKostenProM3(GuiStrings.getString("Forwarder2018.proM3iR")); //$NON-NLS-1$
				}
			}
		});
		
		initHoverActions();
	}
	
	
	private void initHoverActions() {
		MouseAdapter mouseAdapterSchaftholz = createMouseAdapter(ImgCode.SCHAFTHOLZ);
		addMouseListenerToLabelAndSpinner(lblSchaftholz_m3iR, txtSchaftholz_m3iR, mouseAdapterSchaftholz);
		
		MouseAdapter mouseAdapterEnergieholz = createMouseAdapter(ImgCode.ENERGIEHOLZ);
		addMouseListenerToLabelAndSpinner(lblEnergieholzmenge_m3iR, txtEnergieholzmenge_m3iR, mouseAdapterEnergieholz);
		
		MouseAdapter mouseAdapterSchaftholzEnergie = createMouseAdapter(ImgCode.ENERGIEHOLZ_AUS_SCHAFTHOLZ);
		addMouseListenerToLabelAndSpinner(lblEnergieholzAusSchaftholz_m3iR, txtEnergieholzAusSchaftholz_m3iR, mouseAdapterSchaftholzEnergie);
		
		MouseAdapter mouseAdapterAstderbholzReisig = createMouseAdapter(ImgCode.ASTDERBHOLZREISIG);
		addMouseListenerToLabelAndSpinner(lblEnergieholzAusAstderbholzUndReisig_m3iR, txtEnergieholzAusAstderbholzUndReisig_m3iR, mouseAdapterAstderbholzReisig);
		
		MouseAdapter mouseAdapterRundholz = createMouseAdapter(ImgCode.RUNDHOLZ);
		addMouseListenerToLabelAndSpinner(lblVerkaufRundholz_m3iR, txtVerkaufRundholz_m3iR, mouseAdapterRundholz);
		
		MouseAdapter mouseAdapterZopfdurchmesser = createMouseAdapter(ImgCode.ZOPFDURCHMESSER);
		addMouseListenerToLabelAndSpinner(lblZopfdurchmesser_cm, txtZopfdurchmesser_cm, mouseAdapterZopfdurchmesser);
	}
	
	
	private MouseAdapter createMouseAdapter(final ImgCode imgCode) {
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSkizzeBaumteile.setIcon(getScaledImageIcon(imgCode));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (isMouseWithinSensitiveArea() == false) {
					lblSkizzeBaumteile.setIcon(getScaledImageIcon(ImgCode.ALLES));
				}
			}
		};
		return mouseAdapter;
	}
	
	public boolean isMouseWithinSensitiveArea() {
	    Point mousePos = MouseInfo.getPointerInfo().getLocation();
	    Rectangle bounds = lblMouseSensitiveArea.getBounds();
	    bounds.setLocation(lblMouseSensitiveArea.getLocationOnScreen());
	    return bounds.contains(mousePos);
	}
	
	
	private static void addMouseListenerToLabelAndSpinner(JLabel label, JSpinner spinner, MouseListener listener) {
		// add listener to label
		label.addMouseListener(listener);
		
		// add listener to spinner-buttons
		for (Component c : spinner.getComponents()) {
			c.addMouseListener(listener);
		}
		
		// add listener to spinner-textfield
		((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().addMouseListener(listener);
	}
	
	
	private static void setSpinnerMaximumFractionDigits(JSpinner spinner) {
		NumberEditor editor = ((JSpinner.NumberEditor) spinner.getEditor());
		DecimalFormat decimalFormat = editor.getFormat();
		decimalFormat.setMaximumFractionDigits(2);
	}
	
	
	private void onHolzmengenChange() {
		if (super.isReactOnInputChange()) {
			SwingUtilities.invokeLater(new Runnable() {	
				// Somit wird die gemachte Änderung im JSpinner dem Benutzer bereits angezeigt, wenn der Warnhinweis erscheint.
				@Override
				public void run() {
					if (holzmengenUnveraendertSeitBerechnungEnergieholzmengen) {
						holzmengenUnveraendertSeitBerechnungEnergieholzmengen = false;
						highlightMengenFields(true);
					}
					showWarnmeldungHolzmengeIfNotYetShown();
				}
			});
		}
	}
	
	
	protected void showWarnmeldungHolzmengeIfNotYetShown() {
		if (hinweisAenderungenBeimNachestenChangeZeigen) {
			hinweisAenderungenBeimNachestenChangeZeigen = false;
			String msg = wrap(GuiStrings.getString("Forwarder2018.WarnmeldungHolzmenge"), 60); //$NON-NLS-1$
			JOptionPane.showMessageDialog(Forwarder2018.this, msg, GuiStrings.getString("Forwarder2018.Achtung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
		}
	}
	
	
	protected void highlightMengenFields(boolean flag) {
		highlightSpinner(txtSchaftholz_m3iR, flag);
		highlightSpinner(txtEnergieholzmenge_m3iR, flag);
		highlightSpinner(txtEnergieholzAusSchaftholz_m3iR, flag);
		highlightSpinner(txtEnergieholzAusAstderbholzUndReisig_m3iR, flag);
		highlightSpinner(txtVerkaufRundholz_m3iR, flag);
	}
	
	
	private static final Color HIGHLIGHT_COLOR = new Color(0xff, 0xdd, 0xbb);
	
	private static void highlightSpinner(JSpinner spinner, boolean flag) {
		Color bgColor = flag ? HIGHLIGHT_COLOR : spinner.isEnabled() ? Color.WHITE : null;
		((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setBackground(bgColor);
	}
	
	
	protected void setEnergieholzErgebnis(ErgebnisEnergieholz energieholzErgebnis) {
		holzmengenUnveraendertSeitBerechnungEnergieholzmengen = false;
		hinweisAenderungenBeimNachestenChangeZeigen = false;
		
		txtEnergieholzmenge_m3iR.setValue(energieholzErgebnis.getVolumenEnergieholz_m3iR());
		
		double energieHolzSchaft_m3iR = energieholzErgebnis.getVolumenEnergieholzAmSchaftOberhalbZopf_m3iR() + energieholzErgebnis.getVolumenEnergierundholzUnterhalbZopf_m3iR();
		txtEnergieholzAusSchaftholz_m3iR.setValue(energieHolzSchaft_m3iR);
		
		double energieHolzAstderbReisig_m3iR = energieholzErgebnis.getVolumenAstderbholz_m3iR() + energieholzErgebnis.getVolumenReisig_m3iR();
		txtEnergieholzAusAstderbholzUndReisig_m3iR.setValue(energieHolzAstderbReisig_m3iR);
		
		updateVerkaufRundholz();
		displayErgebnis();
		
		// Weil in onHolzmengenChange() mit invokeLater gearbeitet wird, muss das auch hier getan werden, ansonsten wird
		// die Änderung der Energieholzmenge in setEnergieholzErgebnis() bereits als nachträgliche Änderung gewertet!
		// Das ganze hat den Vorteil, dass die gemachte Änderung im JSpinner dem Benutzer bereits angezeigt wird, wenn
		// der Warnhinweis erscheint.
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				holzmengenUnveraendertSeitBerechnungEnergieholzmengen = true;
				hinweisAenderungenBeimNachestenChangeZeigen = true;
				highlightMengenFields(false);
			}
		});
	}
	
	
	protected void setZopf_cm(int value) {
		txtZopfdurchmesser_cm.setValue(value);
	}
	
	
	protected void updateVerkaufRundholz() {
		double schaftholz_m3iR = (Double) txtSchaftholz_m3iR.getValue();
		double energieHolzSchaft_m3iR = (Double) txtEnergieholzAusSchaftholz_m3iR.getValue();
		double verkaufsmenge_m3iR = schaftholz_m3iR - energieHolzSchaft_m3iR;
		txtVerkaufRundholz_m3iR.setValue(verkaufsmenge_m3iR);
		
		double rindenAbzugFaktor = getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		verkaufsmenge_m3oR = verkaufsmenge_m3iR * rindenAbzugFaktor;
		updateLabelVerkaufRundholz_m3oR_Info();
	}
	
	protected final void updateLabelVerkaufRundholz_m3oR_Info() {
		lblVerkaufRundholz_m3oR_Info.setText( "<html>(" +  df.format(verkaufsmenge_m3oR) + " m<sup>3</sup> " + GuiStrings.getString("Forwarder2018.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$			
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		updateVerkaufRundholz();
		
		if (cmbForwardertyp.equals(eventSource)) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run(){
					String message = GuiStrings.getString("Forwarder2018.WarnungKostensatz"); //$NON-NLS-1$
					JOptionPane.showMessageDialog(Forwarder2018.this, message, GuiStrings.getString("Forwarder2018.Achtung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
				}
			});
		}
		
		if (cmbEnergieholzanfall_m3iRproHa.equals(eventSource)) {
			final Energieholzanfall energieholzanfall = (Energieholzanfall) cmbEnergieholzanfall_m3iRproHa.getSelectedItem();
			if (energieholzanfall.isBenutzerdefiniert()) {
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						String message = GuiStrings.getString("Forwarder2018.EnergieholzanfallEingeben"); //$NON-NLS-1$
						while (true) {
							//Inputdialog
							String result = JOptionPane.showInputDialog(Forwarder2018.this, message, energieholzanfall.getEnergieholzanfall_m3ProHa());
							if (result == null) {
								break; //"Abbrechen" gedrückt
							}
							
							try {
								//Ergebnis parsen
								int wertNeu = (int) Double.parseDouble(result);
								
								//Ungültige Werte abfangen bzw. neuer Wert setzen
								if (wertNeu < 0 || wertNeu > 2000) {
									throw new IllegalArgumentException();
								}
								else {
									energieholzanfall.setEnergieholzanfall_m3ProHa(wertNeu);
									break; //Änderung erfolgreich
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(Forwarder2018.this, GuiStrings.getString("Forwarder2018.GueltigenWertEingeben"), GuiStrings.getString("Forwarder2018.Fehler"), JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
								
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(Forwarder2018.this, GuiStrings.getString("Forwarder2018.GueltigenWertEingeben"), GuiStrings.getString("Forwarder2018.Fehler"), JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
							}
						}
						displayErgebnis();
					}
				});
			}
		}
	}
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		ErgebnisAnzeige ergebnisAnzeige = (ErgebnisAnzeige) cmbErgebnisanzeige.getSelectedItem();
		model.getCalculator().setErgebnisAnzeige(	ergebnisAnzeige 	);
		
		updateVerkaufRundholz();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3( 					(Double) txtSchaftholz_m3iR.getValue() 						);
		model.getArbeitsobjekt().setEnergieholzmenge_m3iR( 			(Double) txtEnergieholzmenge_m3iR.getValue() 				);
		model.getArbeitsobjekt().setEnergieholzAusSchaftholz_m3iR(	(Double) txtEnergieholzAusSchaftholz_m3iR.getValue() 		);
		model.getArbeitsobjekt().setEnergieholzAusAstderbholzUndReisig_m3iR(	(Double) txtEnergieholzAusAstderbholzUndReisig_m3iR.getValue() );
		model.getArbeitsobjekt().setVerkaufRundholz_m3oR(			verkaufsmenge_m3oR											);	
		model.getArbeitsobjekt().setHolzmengenUnveraendertSeitBerechnungEnergieholzmengen(holzmengenUnveraendertSeitBerechnungEnergieholzmengen);
		model.getArbeitsobjekt().setBhdMit_cm(        				((Integer) txtBhdMit_cm.getValue()) 						);
		model.getArbeitsobjekt().setRueckeentfernung(      			((Rueckeentfernung) cmbRueckeentfernung.getSelectedItem()) 	);
		model.getArbeitsobjekt().setHangneigung(      				((Hangneigung) cmbHangneigung.getSelectedItem()) 			);
		model.getArbeitsobjekt().setAnzahlSortimente(      			((AnzahlSortimente) cmbAnzahlSortimente.getSelectedItem()) 	);
		model.getArbeitsobjekt().setErschwernisse(      			((Erschwernisse) cmbErschwernisse.getSelectedItem()) 		);
		model.getArbeitsobjekt().setAbstandRueckegasse(      		((AbstandRueckegasse) cmbAbstandRueckegasse.getSelectedItem()) 	);
		model.getArbeitsobjekt().setZopfdurchmesser_cm(		       	((Integer) txtZopfdurchmesser_cm.getValue()) 				);
		model.getArbeitsobjekt().setEnergieholzanfall_m3iRproHa(	(Energieholzanfall) cmbEnergieholzanfall_m3iRproHa.getSelectedItem()  );
		model.getArbeitsobjekt().setAllEnergieholzanfall(       	 getAllEnergieholzanfallFromCombobox() 						);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setForwardertyp(	(Forwardertyp) cmbForwardertyp.getSelectedItem() );
	}

	
	@Override
	public AbstractModel getModel() {
		return model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);
		
		if (data instanceof ModelForwarder2018) {
			ModelForwarder2018 modelFwd = (ModelForwarder2018) data;
			cmbErgebnisanzeige.setSelectedItem( 	modelFwd.getCalculator().getErgebnisAnzeige() ) ;
		}
		else {
			cmbErgebnisanzeige.setSelectedItem( 	ErgebnisAnzeige.Rundholz ) ;
		}

		//Combobox Energieholzanfall refillen, damit korrekter benutzerdefinierter Wert geladen wird
		cmbEnergieholzanfall_m3iRproHa.removeAllItems();
		for (Energieholzanfall energieholzanfall : ((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getAllEnergieholzanfall() ) {
			cmbEnergieholzanfall_m3iRproHa.addItem(energieholzanfall);
		}
		
		
		//Arbeitsobjekt
		txtSchaftholz_m3iR.setValue( 		  						 				 data.getArbeitsobjekt().getHolzmenge_m3() 					);
		txtEnergieholzmenge_m3iR.setValue( 					((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getEnergieholzmenge_m3iR()		);
		txtEnergieholzAusSchaftholz_m3iR.setValue(			((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getEnergieholzAusSchaftholz_m3iR());
		txtEnergieholzAusAstderbholzUndReisig_m3iR.setValue(((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getEnergieholzAusAstderbholzUndReisig_m3iR());
		verkaufsmenge_m3oR =							((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getVerkaufRundholz_m3oR();	
		txtBhdMit_cm.setValue( 		  					((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getBhdMit_cm() 					);
		holzmengenUnveraendertSeitBerechnungEnergieholzmengen = ((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).isHolzmengenUnveraendertSeitBerechnungEnergieholzmengen();
		cmbRueckeentfernung.setSelectedItem(  			((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getRueckeentfernung()				);
		cmbHangneigung.setSelectedItem(  				((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getHangneigung()					);
		cmbAnzahlSortimente.setSelectedItem(  			((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getAnzahlSortimente()				);
		cmbErschwernisse.setSelectedItem(  				((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getErschwernisse() 				);
		cmbAbstandRueckegasse.setSelectedItem(  		((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getAbstandRueckegasse()			);
		txtZopfdurchmesser_cm.setValue( 		  		((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getZopfdurchmesser_cm() 			);
		cmbEnergieholzanfall_m3iRproHa.setSelectedItem( ((ArbeitsobjektForwarder2018)data.getArbeitsobjekt()).getEnergieholzanfall_m3iRproHa()	);
		
		
		//Arbeitssystem
		cmbForwardertyp.setSelectedItem( 	((ArbeitssystemForwarder2018)data.getArbeitssystem()).getForwardertyp()		 );
		
		updateVerkaufRundholz();
		
		super.setReactOnInputChange(true);
	}
	
	
	@Override
	protected void onSuccessfullyLoaded() {
		// Weil in onHolzmengenChange() mit invokeLater gearbeitet wird, muss das auch hier getan werden.
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				highlightMengenFields(false);
				
				boolean isEnergieholzBerechnet = ((Double)txtEnergieholzAusSchaftholz_m3iR.getValue()) > 0 || ((Double)txtEnergieholzAusAstderbholzUndReisig_m3iR.getValue()) > 0;
				if (isEnergieholzBerechnet) {
					hinweisAenderungenBeimNachestenChangeZeigen = true;
					
					if (holzmengenUnveraendertSeitBerechnungEnergieholzmengen == false) {
						highlightMengenFields(true);
						showWarnmeldungHolzmengeIfNotYetShown();
					}
				}
				else {
					hinweisAenderungenBeimNachestenChangeZeigen = false;
				}
			}
		});
	}

	
	
	private Energieholzanfall[] getAllEnergieholzanfallFromCombobox() {
		Energieholzanfall[] result = new Energieholzanfall[cmbEnergieholzanfall_m3iRproHa.getItemCount()];
		
		for (int i=0; i<cmbEnergieholzanfall_m3iRproHa.getItemCount(); i++) {
			result[i] = (Energieholzanfall) cmbEnergieholzanfall_m3iRproHa.getItemAt(i);
		}
		
		return result;
	}

}
