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
package ch.wsl.fps.hepromo.gui.modelle.panel.aobj;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder.FahrstreckenArt;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErschliessungForwarderPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtFahrstreckeAufStrasse;
	private JComboBox<FahrstreckenArt> cmbFahrstreckenArtStrasse;
	private JSpinner txtFahrstreckeAufFeinerschliessung;
	private JComboBox<FahrstreckenArt> cmbFahrstreckenArtFeinerschliessung;
	private JTextField txtFahrstreckeTotal;
	
	private JButton btnAnsichtWechseln;
	private JPanel pnlWechselPanel;
	private boolean erschliessungsPanelSelected = true;
	
	private JPanel pnlErschliessungsdetails;
	private JSpinner txtErschliessungsLaengeEinseitig_m;
	private JSpinner txtErschliessungsLaengeEinseitigAnteilStrasse_Prz;
	private JSpinner txtErschliessungsLaengeEinseitigAnteilFeinerschliessung_Prz;
	private JSpinner txtErschliessungsLaengeBeidseitig_m;
	private JSpinner txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz;
	private JSpinner txtErschliessungsLaengeBeidseitigAnteilFeinerschliessung_Prz;
	private JComboBox<HindernisKategorie> cmbHindernisKategorieFeinerschliessung;
	private JComboBox<NeigungsKategorie> cmbNeigungsKategorieFeinerschliessung;
	
	private JPanel pnlFahrstreckenanteile;
	private JSpinner txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilStrasseLastfahrtEben_Prz;
	private JSpinner txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilStrasseLeerfahrtEben_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenEben_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungLastfahrtEben_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz;
	private JSpinner txtFahrstreckenAnteilFeinerschliessungLeerfahrtEben_Prz;
	
	
	public ErschliessungForwarderPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanelErschliessungsdetails();
		initPanelFahrstreckenAnteile();
		initPanel();
		initData();
	}
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ErschliessungForwarderPanel.Title"))); //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Fahrstrecke
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.gridwidth = 2;
		c.insets = new Insets(0,0,10,0);
		JLabel lblFahrstrecke = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.FahrstreckeLadeortLagerplatz")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(lblFahrstrecke, c);

		
		

		
		//Fahrstrecke Auf Strasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		JPanel row1 = new JPanel();
		row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
		this.add(row1, c);
		
		//Label Fahrstrecke Auf Strasse
		JLabel lblFahrstreckeAufStrasse = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.AufStrasse_m")); //$NON-NLS-1$
		row1.add(lblFahrstreckeAufStrasse);
		row1.add(Box.createHorizontalGlue());
		
		//Text Fahrstrecke Auf Strasse
		txtFahrstreckeAufStrasse = new JSpinner(new SpinnerNumberModel(100, 0, 5000, 1));
		txtFahrstreckeAufStrasse.setMaximumSize(new Dimension(60, 20));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckeAufStrasse);
		row1.add(txtFahrstreckeAufStrasse);
		
		//Combo Fahrstrecke Auf Strasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,10,0);
		cmbFahrstreckenArtStrasse = new JComboBox<>();
		this.add(cmbFahrstreckenArtStrasse, c);

		
		
		
		
		//Fahrstrecke Auf Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		JPanel row2 = new JPanel();
		row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
		this.add(row2, c);
		
		//Label Fahrstrecke Auf Feinerschliessung
		JLabel lblFahrstreckeAufFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.AufFeinerschliessung_m")); //$NON-NLS-1$
		row2.add(lblFahrstreckeAufFeinerschliessung);
		row2.add(Box.createHorizontalGlue());
		
		//Text Fahrstrecke Auf Feinerschliessung
		txtFahrstreckeAufFeinerschliessung = new JSpinner(new SpinnerNumberModel(250, 0, 5000, 1));
		txtFahrstreckeAufFeinerschliessung.setMaximumSize(new Dimension(60, 20));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckeAufFeinerschliessung);
		row2.add(txtFahrstreckeAufFeinerschliessung);
		
		//Combo Fahrstrecke Auf Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbFahrstreckenArtFeinerschliessung = new JComboBox<>();
		this.add(cmbFahrstreckenArtFeinerschliessung, c);

		

		//Fahrstrecke total
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		JPanel row3 = new JPanel();
		row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
		this.add(row3, c);
		
		//Label Fahrstrecke total
		JLabel lblFahrstreckeTotal = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.total_m")); //$NON-NLS-1$
		row3.add(lblFahrstreckeTotal);
		row3.add(Box.createHorizontalGlue());
		
		//Text Fahrstrecke total
		txtFahrstreckeTotal = new JTextField(6);
		txtFahrstreckeTotal.setHorizontalAlignment(JTextField.RIGHT);
		txtFahrstreckeTotal.setEditable(false);
		txtFahrstreckeTotal.setMinimumSize(new Dimension(80, 20));
		txtFahrstreckeTotal.setMaximumSize(new Dimension(80, 20));
		row3.add(txtFahrstreckeTotal);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		this.add(placeholder1, c);

		//***************** 2. Spalte

		
		//Button Ansicht wechslen
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		btnAnsichtWechseln = new JButton("Ansicht wechseln"); //$NON-NLS-1$
		btnAnsichtWechseln.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (erschliessungsPanelSelected == true) { //change to Fahrstreckenanteile
					pnlWechselPanel.removeAll();
					pnlWechselPanel.add(pnlFahrstreckenanteile);
					btnAnsichtWechseln.setText(GuiStrings.getString("ErschliessungForwarderPanel.ErschliessungsdetailsZeigen")); //$NON-NLS-1$
					erschliessungsPanelSelected = false;
					return;
				}
				else { //change to Erschliessungsdetails
					pnlWechselPanel.removeAll();
					pnlWechselPanel.add(pnlErschliessungsdetails);
					btnAnsichtWechseln.setText(GuiStrings.getString("ErschliessungForwarderPanel.FahrstreckenanteileZeigen")); //$NON-NLS-1$
					erschliessungsPanelSelected = true;
					return;
				}
			}
		});
		this.add(btnAnsichtWechseln, c);
		
		
		//Wechsel-Panel
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		pnlWechselPanel = new JPanel();
		this.add(pnlWechselPanel, c);
	}
	
	
	
	private void initPanelErschliessungsdetails() {
		pnlErschliessungsdetails = new JPanel();
		pnlErschliessungsdetails.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ErschliessungForwarderPanel.Erschliessungsdetails"))); //$NON-NLS-1$
		
		//set layout
		pnlErschliessungsdetails.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Erschliessungslänge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblErschliessungslaenge = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.ErschliessungslaengeAnDer")); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblErschliessungslaenge, c);
		
		//Label Aufteilung auf
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,0,5,0);
		JLabel lblAufteilungAuf = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.AufteilungAuf"), SwingConstants.CENTER); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblAufteilungAuf, c);
		
		
		//Label Aufteilung auf Strasse
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblAufteilungAufStrasse = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Strasse_Prz"), SwingConstants.CENTER); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblAufteilungAufStrasse, c);
		
		//Label Aufteilung auf Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblAufteilungAufFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Feinerschliessung_Prz"), SwingConstants.CENTER); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblAufteilungAufFeinerschliessung, c);

		
		//Label (m)
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMeter = new JLabel("(m)", SwingConstants.CENTER); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblMeter, c);

		
		//Label einseitig Holz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHolzEinseitig = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.einseitigHolzLiegt")); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblHolzEinseitig, c);
		
		//Text einseitig Holz 
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtErschliessungsLaengeEinseitig_m = new JSpinner(new SpinnerNumberModel(50, 0, 5000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtErschliessungsLaengeEinseitig_m);
		pnlErschliessungsdetails.add(txtErschliessungsLaengeEinseitig_m, c);
		
		//Text einseitig Holz 
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtErschliessungsLaengeEinseitigAnteilStrasse_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtErschliessungsLaengeEinseitigAnteilStrasse_Prz);
		pnlErschliessungsdetails.add(txtErschliessungsLaengeEinseitigAnteilStrasse_Prz, c);
		
		//Text einseitig Holz 
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtErschliessungsLaengeEinseitigAnteilFeinerschliessung_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); //SpinnerNumberModel wird hier gesetzt, weil damit das Feld die gleiche Breite hat wie die anderen!
		txtErschliessungsLaengeEinseitigAnteilFeinerschliessung_Prz.setEnabled(false);
		pnlErschliessungsdetails.add(txtErschliessungsLaengeEinseitigAnteilFeinerschliessung_Prz, c);

		
		//Label beidseitig Holz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHolzBeidseitig = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.BeidseitigHolzLiegt")); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblHolzBeidseitig, c);
		
		//Text beidseitig Holz 
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtErschliessungsLaengeBeidseitig_m = new JSpinner(new SpinnerNumberModel(150, 0, 5000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtErschliessungsLaengeBeidseitig_m);
		pnlErschliessungsdetails.add(txtErschliessungsLaengeBeidseitig_m, c);
		
		//Text beidseitig Holz 
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz);
		pnlErschliessungsdetails.add(txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz, c);
		
		//Text beidseitig Holz 
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtErschliessungsLaengeBeidseitigAnteilFeinerschliessung_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		txtErschliessungsLaengeBeidseitigAnteilFeinerschliessung_Prz.setEnabled(false);
		pnlErschliessungsdetails.add(txtErschliessungsLaengeBeidseitigAnteilFeinerschliessung_Prz, c);

		
		//Label Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,5,0);
		JLabel lblFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.FeinerschliessungTitle")); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblFeinerschliessung, c);
		
		
		//Label Hindernisse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 100;
		c.insets = new Insets(0,3,0,0);
		JLabel lblHindernisse = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.HindernisseAufFeinerschliessung")); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblHindernisse, c);
		
		//Combo Hindernisse
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbHindernisKategorieFeinerschliessung = new JComboBox<>();
		pnlErschliessungsdetails.add(cmbHindernisKategorieFeinerschliessung, c);
		
		
		//Label Neigung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 100;
		c.insets = new Insets(0,3,0,0);
		JLabel lblNeigung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.NeigungFeinerschliessung")); //$NON-NLS-1$
		pnlErschliessungsdetails.add(lblNeigung, c);
		
		//Combo Neigung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbNeigungsKategorieFeinerschliessung = new JComboBox<>();
		pnlErschliessungsdetails.add(cmbNeigungsKategorieFeinerschliessung, c);
	}
	
	
	private void initPanelFahrstreckenAnteile() {
		pnlFahrstreckenanteile = new JPanel();
		pnlFahrstreckenanteile.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ErschliessungForwarderPanel.Fahrstreckenanteile_Prz"))); //$NON-NLS-1$
		
		//set layout
		pnlFahrstreckenanteile.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label auf Strasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAufStrasse = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.AufStrasse")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblAufStrasse, c);
		
		//Label aufwärts
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAufwaerts = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Aufwaerts"), SwingConstants.CENTER); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblAufwaerts, c);
		
		//Label abwärts
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAbwaerts = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Abwaerts"), SwingConstants.CENTER); //$NON-NLS-1$
		
		pnlFahrstreckenanteile.add(lblAbwaerts, c);
		
		//Label eben
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblEben = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Eben"), SwingConstants.CENTER); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblEben, c);

		
		//Label Lastfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLastfahrtStrasse = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Lastfahrt")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblLastfahrtStrasse, c);
		
		//Text Lastfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz, c);
		
		//Text Lastfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz, c);
		
		//Text Lastfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilStrasseLastfahrtEben_Prz = new JSpinner();
		txtFahrstreckenAnteilStrasseLastfahrtEben_Prz.setEnabled(false);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilStrasseLastfahrtEben_Prz, c);

		
		//Label Leerfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLeerfahrtStrasse = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Leerfahrt")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblLeerfahrtStrasse, c);
		
		//Text Leerfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz, c);
		
		//Text Leerfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz, c);
		
		//Text Leerfahrt Strasse
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilStrasseLeerfahrtEben_Prz = new JSpinner();
		txtFahrstreckenAnteilStrasseLeerfahrtEben_Prz.setEnabled(false);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilStrasseLeerfahrtEben_Prz, c);

		
		
		
		//Label auf Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAufFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.AufFeinerschliessung")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblAufFeinerschliessung, c);

		
		//Label FahrenBeimLaden Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblFahrenBeimLadenFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.FahrenBeimLaden")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblFahrenBeimLadenFeinerschliessung, c);
		
		//Text FahrenBeimLaden Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz, c);
		
		//Text FahrenBeimLaden Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz, c);
		
		//Text FahrenBeimLaden Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenEben_Prz = new JSpinner();
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenEben_Prz.setEnabled(false);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenEben_Prz, c);

		
		//Label Lastfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLastfahrtFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Lastfahrt")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblLastfahrtFeinerschliessung, c);
		
		//Text Lastfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz, c);
		
		//Text Lastfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz, c);
		
		//Text Lastfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungLastfahrtEben_Prz = new JSpinner();
		txtFahrstreckenAnteilFeinerschliessungLastfahrtEben_Prz.setEnabled(false);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungLastfahrtEben_Prz, c);

		
		//Label Leerfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLeerfahrtFeinerschliessung = new JLabel(GuiStrings.getString("ErschliessungForwarderPanel.Leerfahrt")); //$NON-NLS-1$
		pnlFahrstreckenanteile.add(lblLeerfahrtFeinerschliessung, c);
		
		//Text Leerfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz, c);
		
		//Text Leerfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz, c);
		
		//Text Leerfahrt Feinerschliessung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtEben_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); //SpinnerNumberModel wird hier gesetzt, weil damit das Feld die gleiche Breite hat wie die anderen!
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtEben_Prz.setEnabled(false);
		pnlFahrstreckenanteile.add(txtFahrstreckenAnteilFeinerschliessungLeerfahrtEben_Prz, c);
	}
	
	
	
	
	private void initData() {
		for (int i=0; i<FahrstreckenArt.values().length; i++) {
			cmbFahrstreckenArtStrasse.addItem(			FahrstreckenArt.values()[i]);
			cmbFahrstreckenArtFeinerschliessung.addItem(FahrstreckenArt.values()[i]);
		}
		parent.addDefaultActionListener(cmbFahrstreckenArtStrasse);
		parent.addDefaultActionListener(cmbFahrstreckenArtFeinerschliessung);

		for (int i=0; i<HindernisKategorie.values().length; i++) {
			cmbHindernisKategorieFeinerschliessung.addItem(HindernisKategorie.values()[i]);
		}
		parent.addDefaultActionListener(cmbHindernisKategorieFeinerschliessung);
		
		for (int i=0; i<NeigungsKategorie.values().length; i++) {
			cmbNeigungsKategorieFeinerschliessung.addItem(NeigungsKategorie.values()[i]);
		}
		parent.addDefaultActionListener(cmbNeigungsKategorieFeinerschliessung);

		cmbFahrstreckenArtStrasse.				setSelectedItem(FahrstreckenArt.LastfahrtVorwaertsBergab);
		cmbFahrstreckenArtFeinerschliessung.	setSelectedItem(FahrstreckenArt.LastfahrtVorwaertsBergab);
		cmbHindernisKategorieFeinerschliessung.	setSelectedItem(HindernisKategorie.Wenige				);
		cmbNeigungsKategorieFeinerschliessung.	setSelectedItem(NeigungsKategorie.NK_10bis20			);
		
		
		
		//Auf WechselPanel Erschliessungsdetails anzeigen
		pnlWechselPanel.removeAll();
		pnlWechselPanel.add(pnlErschliessungsdetails);
		btnAnsichtWechseln.setText(GuiStrings.getString("ErschliessungForwarderPanel.FahrstreckenanteileZeigen")); //$NON-NLS-1$
		erschliessungsPanelSelected = true;
	}
	
	
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbFahrstreckenArtStrasse.equals(eventSource)) {
			//GUI ins Model laden
			parent.loadGUIToModel();
			ModelForwarder model = (ModelForwarder) parent.getModel();
			
			//Durch Aufruf dieser Methode werden die prozentualen Fahrstreckenanteile automatisch neu gesetzt
			model.getArbeitsobjekt().setFahrstreckenArtStrasse(getFahrstreckenArtStrasse());
			
			//Model zurück ins GUI laden
			parent.loadModelToGUI(model);
		}
		else if (cmbFahrstreckenArtFeinerschliessung.equals(eventSource)) {
			//GUI ins Model laden
			parent.loadGUIToModel();
			ModelForwarder model = (ModelForwarder) parent.getModel();
			
			//Durch Aufruf dieser Methode werden die prozentualen Fahrstreckenanteile automatisch neu gesetzt
			model.getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(getFahrstreckenArtFeinerschliessung());
			
			//Model zurück ins GUI laden
			parent.loadModelToGUI(model);
		}

		
		updateCalculatedValues();
	}
	
	
	
	private void updateCalculatedValues() {
		
		//Fahrstrecke total
		txtFahrstreckeTotal.setText(String.valueOf(  ((Integer)txtFahrstreckeAufStrasse.getValue()) + ((Integer)txtFahrstreckeAufFeinerschliessung.getValue())  ));
		
		
		//Erschliessungslänge Holz einseitig Anteil Feinerschliessung
		txtErschliessungsLaengeEinseitigAnteilFeinerschliessung_Prz.setValue( 100 - ((Integer)txtErschliessungsLaengeEinseitigAnteilStrasse_Prz.getValue()) );
		
		
		//Erschliessungslänge Holz beidseitig Anteil Feinerschliessung
		txtErschliessungsLaengeBeidseitigAnteilFeinerschliessung_Prz.setValue( 100 - ((Integer)txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz.getValue()) );
		
		
		
		//Strasse / Lastfahrt
		SpinnerNumberModel modelAuf = (SpinnerNumberModel) txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz.getModel();
		Integer valueAuf = (Integer) txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz.getValue();
		
		SpinnerNumberModel modelAb = (SpinnerNumberModel) txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz.getModel();
		Integer valueAb = (Integer) txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz.getValue();

		modelAuf.setMaximum(100 - valueAb);
		modelAb.setMaximum(100 - valueAuf);
		txtFahrstreckenAnteilStrasseLastfahrtEben_Prz.setValue(100 - (valueAuf + valueAb));
		

		
		//Strasse / Leerfahrt
		modelAuf = (SpinnerNumberModel) txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz.getModel();
		valueAuf = (Integer) txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz.getValue();
		
		modelAb = (SpinnerNumberModel) txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz.getModel();
		valueAb = (Integer) txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz.getValue();

		modelAuf.setMaximum(100 - valueAb);
		modelAb.setMaximum(100 - valueAuf);
		txtFahrstreckenAnteilStrasseLeerfahrtEben_Prz.setValue(100 - (valueAuf + valueAb));
		

		
		//Feinerschliessung / Fahren beim Laden
		modelAuf = (SpinnerNumberModel) txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz.getModel();
		valueAuf = (Integer) txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz.getValue();
		
		modelAb = (SpinnerNumberModel) txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz.getModel();
		valueAb = (Integer) txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz.getValue();

		modelAuf.setMaximum(100 - valueAb);
		modelAb.setMaximum(100 - valueAuf);
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenEben_Prz.setValue(100 - (valueAuf + valueAb));
		
		

		//Feinerschliessung / Lastfahrt
		modelAuf = (SpinnerNumberModel) txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz.getModel();
		valueAuf = (Integer) txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz.getValue();
		
		modelAb = (SpinnerNumberModel) txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz.getModel();
		valueAb = (Integer) txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz.getValue();

		modelAuf.setMaximum(100 - valueAb);
		modelAb.setMaximum(100 - valueAuf);
		txtFahrstreckenAnteilFeinerschliessungLastfahrtEben_Prz.setValue(100 - (valueAuf + valueAb));
		
		

		//Feinerschliessung / Leerfahrt
		modelAuf = (SpinnerNumberModel) txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz.getModel();
		valueAuf = (Integer) txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz.getValue();
		
		modelAb = (SpinnerNumberModel) txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz.getModel();
		valueAb = (Integer) txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz.getValue();

		modelAuf.setMaximum(100 - valueAb);
		modelAb.setMaximum(100 - valueAuf);
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtEben_Prz.setValue(100 - (valueAuf + valueAb));
	}

	
	

	public int getFahrstreckeAufStrasse() {
		return (Integer) txtFahrstreckeAufStrasse.getValue();
	}


	public FahrstreckenArt getFahrstreckenArtStrasse() {
		return (FahrstreckenArt) cmbFahrstreckenArtStrasse.getSelectedItem();
	}


	public int getFahrstreckeAufFeinerschliessung() {
		return (Integer) txtFahrstreckeAufFeinerschliessung.getValue();
	}


	public FahrstreckenArt getFahrstreckenArtFeinerschliessung() {
		return (FahrstreckenArt) cmbFahrstreckenArtFeinerschliessung.getSelectedItem();
	}


	public int getErschliessungsLaengeEinseitig_m() {
		return (Integer) txtErschliessungsLaengeEinseitig_m.getValue();
	}


	public int getErschliessungsLaengeEinseitigAnteilStrasse_Prz() {
		return (Integer) txtErschliessungsLaengeEinseitigAnteilStrasse_Prz.getValue();
	}


	public int getErschliessungsLaengeBeidseitig_m() {
		return (Integer) txtErschliessungsLaengeBeidseitig_m.getValue();
	}


	public int getErschliessungsLaengeBeidseitigAnteilStrasse_Prz() {
		return (Integer) txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz.getValue();
	}


	public HindernisKategorie getHindernisKategorieFeinerschliessung() {
		return (HindernisKategorie) cmbHindernisKategorieFeinerschliessung.getSelectedItem();
	}


	public NeigungsKategorie getNeigungsKategorieFeinerschliessung() {
		return (NeigungsKategorie) cmbNeigungsKategorieFeinerschliessung.getSelectedItem();
	}


	public int getFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz.getValue();
	}


	public int getFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz() {
		return (Integer) txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz.getValue();
	}

	
	

	public void setFahrstreckeAufStrasse(int fahrstreckeAufStrasse) {
		txtFahrstreckeAufStrasse.setValue(fahrstreckeAufStrasse);
		updateCalculatedValues();
	}

	public void setFahrstreckenArtStrasse(FahrstreckenArt fahrstreckenArtStrasse) {
		cmbFahrstreckenArtStrasse.setSelectedItem(fahrstreckenArtStrasse);
	}

	public void setFahrstreckeAufFeinerschliessung(int fahrstreckeAufFeinerschliessung) {
		txtFahrstreckeAufFeinerschliessung.setValue(fahrstreckeAufFeinerschliessung);
		updateCalculatedValues();
	}

	public void setFahrstreckenArtFeinerschliessung(FahrstreckenArt fahrstreckenArtFeinerschliessung) {
		cmbFahrstreckenArtFeinerschliessung.setSelectedItem(fahrstreckenArtFeinerschliessung);
	}

	public void setErschliessungsLaengeEinseitig_m(int erschliessungsLaengeEinseitig_m) {
		txtErschliessungsLaengeEinseitig_m.setValue(erschliessungsLaengeEinseitig_m);
	}

	public void setErschliessungsLaengeEinseitigAnteilStrasse_Prz(int erschliessungsLaengeEinseitigAnteilStrasse_Prz) {
		txtErschliessungsLaengeEinseitigAnteilStrasse_Prz.setValue(erschliessungsLaengeEinseitigAnteilStrasse_Prz);
		updateCalculatedValues();
	}

	public void setErschliessungsLaengeBeidseitig_m(int erschliessungsLaengeBeidseitig_m) {
		txtErschliessungsLaengeBeidseitig_m.setValue(erschliessungsLaengeBeidseitig_m);
	}

	public void setErschliessungsLaengeBeidseitigAnteilStrasse_Prz(int erschliessungsLaengeBeidseitigAnteilStrasse_Prz) {
		txtErschliessungsLaengeBeidseitigAnteilStrasse_Prz.setValue(erschliessungsLaengeBeidseitigAnteilStrasse_Prz);
		updateCalculatedValues();
	}

	public void setHindernisKategorieFeinerschliessung(HindernisKategorie hindernisKategorieFeinerschliessung) {
		cmbHindernisKategorieFeinerschliessung.setSelectedItem(hindernisKategorieFeinerschliessung);
	}

	public void setNeigungsKategorieFeinerschliessung(NeigungsKategorie neigungsKategorieFeinerschliessung) {
		cmbNeigungsKategorieFeinerschliessung.setSelectedItem(neigungsKategorieFeinerschliessung);
	}

	public void setFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz(int fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz) {
		txtFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz.setValue(fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz(int fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz) {
		txtFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz.setValue(fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz(int fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz) {
		txtFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz.setValue(fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz(int fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz) {
		txtFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz.setValue(fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz(int fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz) {
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz.setValue(fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz(int fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz) {
		txtFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz.setValue(fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz) {
		txtFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz.setValue(fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz) {
		txtFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz.setValue(fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz) {
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz.setValue(fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz);
		updateCalculatedValues();
	}

	public void setFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz) {
		txtFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz.setValue(fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz);
		updateCalculatedValues();
	}
}
