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
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.AbstractErgebnisPanel;
import ch.wsl.fps.hepromo.gui.ErgebnisColorListCellRenderer;
import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel2014;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKombiseilgeraet2018.Erschwernisse;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemKombiseilgeraet2018.EquipeAnzahlPersonen;
import ch.wsl.fps.hepromo.model.calc.CalculatorKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorKombiseilgeraet2018.ErgebnisAnzeige;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.model.modelle.ModelKombiseilgeraet2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Kombiseilgeraet2018 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtHolzmenge_m3;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JComboBox<Erschwernisse> cmbErschwernisse;
	private JSpinner txtLaengeProAufstellung;
	private JSpinner txtAnzahlStuetzenProAufstellung;
	private JSpinner txtAnzahlAufstellungen;

	private JSpinner txtHangneigung_Prz;
	private JSpinner txtAnteilLaubholz_Prz;
	
	private JComboBox<ErgebnisAnzeige> cmbErgebnisanzeige;
	
	private JComboBox<EquipeAnzahlPersonen> cmbEquipe;
	private JSpinner txtAnteilEinsatzzeitVerzugsfahrzeug;
	
	private ModelKombiseilgeraet2018 model = new ModelKombiseilgeraet2018();
	

	public Kombiseilgeraet2018() {	
		super.setTitle(GuiStrings.getString("Kombiseilgeraet2018.Title")); //$NON-NLS-1$
		super.setSize((int) (745 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (670 * MainWindow.SIZE));
		
		super.initalize();
	}
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.setBorder(TitledBorderFactory.createTitledBorder("")); //$NON-NLS-1$
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblHolzmenge = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.Holzmenge")); //$NON-NLS-1$
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtHolzmenge_m3 = new JSpinner();
		panel.add(txtHolzmenge_m3, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,15,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblInfoButtonUmrechnung_iRoR")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblHolzmenge_m3oR_readOnly, c);

		
		

		//Label Anzahl Aufstellungen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAnzahlAufstellungen = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.AnzahlAufstellungen")); //$NON-NLS-1$
		panel.add(lblAnzahlAufstellungen, c);
		
		//Text Anzahl Aufstellungen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnzahlAufstellungen = new JSpinner();
		panel.add(txtAnzahlAufstellungen, c);

		
		

		//Label Länge pro Aufstellung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblLaengeProAufstellung = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.LaengeProAufstellung")); //$NON-NLS-1$
		panel.add(lblLaengeProAufstellung, c);
		
		//Text Länge pro Aufstellung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtLaengeProAufstellung = new JSpinner();
		panel.add(txtLaengeProAufstellung, c);

		
		

		//Label Anzahl Stützen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAnzahlStuetzen = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.AnzahlStuetzenProAufstellung")); //$NON-NLS-1$
		panel.add(lblAnzahlStuetzen, c);
		
		//Text Anzahl Stützen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnzahlStuetzenProAufstellung = new JSpinner();
		panel.add(txtAnzahlStuetzenProAufstellung, c);

		
		

		//Label Hangneigung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblHangneigung = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.Hangneigung")); //$NON-NLS-1$
		panel.add(lblHangneigung, c);
		
		//Text Hangneigung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtHangneigung_Prz = new JSpinner();
		panel.add(txtHangneigung_Prz, c);

		
		

		//Label Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblAnteilLaubholz = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.AnteilLaubholz")); //$NON-NLS-1$
		panel.add(lblAnteilLaubholz, c);
		
		//Text Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtAnteilLaubholz_Prz = new JSpinner();
		panel.add(txtAnteilLaubholz_Prz, c);

		
		

		//Label Erschwernisse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblErschwernisse = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.Erschwernisse")); //$NON-NLS-1$
		panel.add(lblErschwernisse, c);
		
		//Text Erschwernisse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbErschwernisse = new JComboBox<>();
		panel.add(cmbErschwernisse, c);
		
		//InfoButton Erschwernisse
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		JLabel lblErschwernisse_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Kombiseilgeraet2018.Erschwernisse_Info")); //$NON-NLS-1$
		lblErschwernisse_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblErschwernisse_Info, c);
		
		
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 7;
		c.gridy = 7;
		c.weightx = 400;
		c.weighty = 100;
		panel.add(new JPanel(), c);

		
		

		//Label Ergebnisanzeige
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 8;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 20;
//		c.insets = new Insets(10,8,20,5);
		JLabel lblErgebnisanzeige = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.ErgebnisAnzeige")); //$NON-NLS-1$
		lblErgebnisanzeige.setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
		lblErgebnisanzeige.setOpaque(true);
		panel.add(lblErgebnisanzeige, c);
		
		//Text Ergebnisanzeige
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,5,0);
		cmbErgebnisanzeige = new JComboBox<>();
		cmbErgebnisanzeige.setName("ergebnisanzeige");
		cmbErgebnisanzeige.setRenderer(new ErgebnisColorListCellRenderer());
		panel.add(cmbErgebnisanzeige, c);
		
		//InfoButton Ergebnisanzeige
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 8;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		JLabel lblErgebnisanzeige_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Kombiseilgeraet2018.ErgebnisAnzeige_Info")); //$NON-NLS-1$
		lblErgebnisanzeige_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblErgebnisanzeige_Info, c);
		
	}
	
	
	@Override
	protected JPanel initSpecialPanel() {
		JPanel panel = new JPanel();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Equipe
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,8,20,5);
		JLabel lblEquipe = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.EquipeAnzahlArbeiter")); //$NON-NLS-1$
		panel.add(lblEquipe, c);
		
		//Combo Equipe
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,5);
		cmbEquipe = new JComboBox<>();
		panel.add(cmbEquipe, c);

		
		
		//Label AnteilEinsatzzeitVerzugsfahrzeug
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,40,20,5);
		JLabel lblAnteilEinsatzzeitVerzugsfahrzeug = new JLabel(GuiStrings.getString("Kombiseilgeraet2018.AnteilEinsatzzeitVerzugsfahrzeugPrz")); //$NON-NLS-1$
		panel.add(lblAnteilEinsatzzeitVerzugsfahrzeug, c);
		
		//Combo AnteilEinsatzzeitVerzugsfahrzeug
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,0);
		txtAnteilEinsatzzeitVerzugsfahrzeug = new JSpinner();
		panel.add(txtAnteilEinsatzzeitVerzugsfahrzeug, c);
		
		//InfoButton AnteilEinsatzzeitVerzugsfahrzeug
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10,5,20,5);
		JLabel lblAnteilEinsatzzeitVerzugsfahrzeug_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Kombiseilgeraet2018.AnteilEinsatzzeitVerzugsfahrzeug_Info")); //$NON-NLS-1$
		lblAnteilEinsatzzeitVerzugsfahrzeug_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblAnteilEinsatzzeitVerzugsfahrzeug_Info, c);
		
		return panel;
	}
	
	
	@Override
	protected KostensaetzePanel2014 initKostensaetzePanel() {
		return new KostensaetzePanel2014.Builder(this)
				.showPersonal1()
				.showMaschine2()
				.showMaschine3()
				.build();
	}

	
	@Override
	protected AbstractErgebnisPanel initErgebnisPanel() {
		ErgebnisPanel panel = new ErgebnisPanel.Builder()
				.enableRowPersonal()
				.enableRowMaschine1()
				.enableRowMaschine2()
				.enableRowMaschine3()
				.enableRowUmsetzen()
				.enableRowProduktivitaet()
				.enableColumnProM3()
				.showHintProduktivitaet()
				.build();
		
//		panel.setLabelProduktivitaet(GuiStrings.getString("HeProMoWindow2014.Produktivitaet_m3_iR_pro_PMH15")); //$NON-NLS-1$
		panel.setLabelKostenProM3(GuiStrings.getString("HeProMoWindow2014.ProM3oR")); //$NON-NLS-1$
		panel.setHintProduktivitaet(GuiStrings.getString("Kombiseilgeraet2018.Produktivitaet_Info")); //$NON-NLS-1$
		
		return panel;
	}

	
	@Override
	protected void initData() {
//		String labelPersonal = getModel().getArbeitssystem().getLabelPersonal1();
		String labelMaschine1 = getModel().getArbeitssystem().getLabelMaschine1();
		String labelMaschine2 = getModel().getArbeitssystem().getLabelMaschine2();
		
		panelKostensaetze.setLabelPersonal(GuiStrings.getString("Kombiseilgeraet2018.PersonalProPerson")); //$NON-NLS-1$
		panelKostensaetze.setLabelMaschine1(labelMaschine1);
		panelKostensaetze.setLabelMaschine2(labelMaschine2);
		panelKostensaetze.setLabelMaschine3(GuiStrings.getString("Kombiseilgeraet2018.VerzugsfahrzeugInklFahrer")); //$NON-NLS-1$
		
		
		txtHolzmenge_m3.setModel(new SpinnerNumberModel(50.0, 0, 100000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3);
		txtHolzmenge_m3.addChangeListener(evt -> {
			updateLabelHolzmenge_m3iR();
		});
		
		for (Erschwernisse erschwernis : Erschwernisse.values()) {
			cmbErschwernisse.addItem(erschwernis);
		}
		addDefaultActionListener(cmbErschwernisse);
		
		
		txtLaengeProAufstellung.setModel(new SpinnerNumberModel(200, 0, 2000, 10));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaengeProAufstellung);
		
		
		txtAnzahlStuetzenProAufstellung.setModel(new SpinnerNumberModel(2, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlStuetzenProAufstellung);
		
		
		txtAnzahlAufstellungen.setModel(new SpinnerNumberModel(1, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlAufstellungen);
		
		
		txtHangneigung_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHangneigung_Prz);
		
		
		txtAnteilLaubholz_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilLaubholz_Prz);
		
		for (ErgebnisAnzeige value : ErgebnisAnzeige.values()) {
			cmbErgebnisanzeige.addItem(value);
		}
		addDefaultActionListener(cmbErgebnisanzeige);
		cmbErgebnisanzeige.addActionListener(evt -> {
			updateLabelProduktivitaet();
		});
		
		
		for (EquipeAnzahlPersonen value : EquipeAnzahlPersonen.values()) {
			cmbEquipe.addItem(value);
		}
		addDefaultActionListener(cmbEquipe);
		
		
		txtAnteilEinsatzzeitVerzugsfahrzeug.setModel(new SpinnerNumberModel(50, 0, 100, 5));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilEinsatzzeitVerzugsfahrzeug);
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmenge_m3.getValue();
		value *= getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("BestandPanelMotormanuellGesamt2014.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	
	
	private void updateLabelProduktivitaet() {
		ErgebnisAnzeige selectedItem = (ErgebnisAnzeige) cmbErgebnisanzeige.getSelectedItem();
		ErgebnisPanel ergebnisPnl = (ErgebnisPanel) ergebnisPanel;
		
		if (selectedItem == ErgebnisAnzeige.AlleArbeitsschritte) {
			ergebnisPnl.setLabelProduktivitaet(GuiStrings.getString("Kombiseilgeraet2018.ProduktivitaetEquipem3iRProWSH")); //$NON-NLS-1$
			ergebnisPnl.setLabelProduktivitaet2(GuiStrings.getString("Kombiseilgeraet2018.m3oRProWSH")); //$NON-NLS-1$

		}
		else if (selectedItem == ErgebnisAnzeige.NurFaellenRuecken) {
			ergebnisPnl.setLabelProduktivitaet(GuiStrings.getString("Kombiseilgeraet2018.ProduktivitaetEquipem3iRProPSH15")); //$NON-NLS-1$
			ergebnisPnl.setLabelProduktivitaet2(GuiStrings.getString("Kombiseilgeraet2018.m3oRProPSH15")); //$NON-NLS-1$

		}
		else {
			ergebnisPnl.setLabelProduktivitaet(GuiStrings.getString("Kombiseilgeraet2018.ProduktivitaetEquipe")); //$NON-NLS-1$
			ergebnisPnl.setLabelProduktivitaet2(null);
		}
	}
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		updateLabelHolzmenge_m3iR(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		((CalculatorKombiseilgeraet2018)model.getCalculator()).setErgebnisAnzeige(	(ErgebnisAnzeige) cmbErgebnisanzeige.getSelectedItem()	);
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(        			((Double) txtHolzmenge_m3.getValue()) 					);
		model.getArbeitsobjekt().setErschwernisse(        			((Erschwernisse) cmbErschwernisse.getSelectedItem()) 	);
		model.getArbeitsobjekt().setLaengeProAufstellung_m(        	((Integer) txtLaengeProAufstellung.getValue()) 			);
		model.getArbeitsobjekt().setAnzahlStuetzenProAufstellung(   ((Integer) txtAnzahlStuetzenProAufstellung.getValue())	);
		model.getArbeitsobjekt().setAnzahlAufstellungen(       		((Integer) txtAnzahlAufstellungen.getValue()) 			);
		model.getArbeitsobjekt().setHangneigung_Prz(	       		((Integer) txtHangneigung_Prz.getValue()) 				);
		model.getArbeitsobjekt().setAnteilLaubholz_Prz(      		((Integer) txtAnteilLaubholz_Prz.getValue()) 			);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzMaschine2_proH( 						panelKostensaetze.getAnsatzMaschine2() 	);
		model.getArbeitssystem().setKostensatzVerzugsfahrzeug_proH( 				panelKostensaetze.getAnsatzMaschine3() 	);
		model.getArbeitssystem().setEquipeAnzahlPersonen( 	(EquipeAnzahlPersonen) 	cmbEquipe.getSelectedItem() 			);
		model.getArbeitssystem().setAnteilEinsatzzeitVerzugsfahrzeug_Prz( (Integer) txtAnteilEinsatzzeitVerzugsfahrzeug.getValue() );
	}

	
	@Override
	public AbstractModel getModel() {
		return model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);
		
		if (data instanceof ModelKombiseilgeraet2018) {
			ModelKombiseilgeraet2018 modelKsg = (ModelKombiseilgeraet2018) data;
			cmbErgebnisanzeige.setSelectedItem( 	((CalculatorKombiseilgeraet2018)modelKsg.getCalculator()).getErgebnisAnzeige() ) ;
		}
		else {
			cmbErgebnisanzeige.setSelectedItem( 	ErgebnisAnzeige.AlleArbeitsschritte ) ;
		}
		
		
		//Arbeitsobjekt
		txtHolzmenge_m3.setValue( 		  							 					data.getArbeitsobjekt().getHolzmenge_m3() 				);
		updateLabelHolzmenge_m3iR();
		cmbErschwernisse.setSelectedItem(  			((ArbeitsobjektKombiseilgeraet2018)data.getArbeitsobjekt()).getErschwernisse() 				);
		txtLaengeProAufstellung.setValue( 		  	((ArbeitsobjektKombiseilgeraet2018)data.getArbeitsobjekt()).getLaengeProAufstellung_m() 	);
		txtAnzahlStuetzenProAufstellung.setValue( 	((ArbeitsobjektKombiseilgeraet2018)data.getArbeitsobjekt()).getAnzahlStuetzenProAufstellung());
		txtAnzahlAufstellungen.setValue( 		  	((ArbeitsobjektKombiseilgeraet2018)data.getArbeitsobjekt()).getAnzahlAufstellungen() 		);
		txtHangneigung_Prz.setValue( 			  	((ArbeitsobjektKombiseilgeraet2018)data.getArbeitsobjekt()).getHangneigung_Prz()		 	);
		txtAnteilLaubholz_Prz.setValue( 		  	((ArbeitsobjektKombiseilgeraet2018)data.getArbeitsobjekt()).getAnteilLaubholz_Prz()		 	);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzMaschine2( 											 data.getArbeitssystem().getKostensatzMaschine2_proH() 				);
		panelKostensaetze.setAnsatzMaschine3( 		  ((ArbeitssystemKombiseilgeraet2018)data.getArbeitssystem()).getKostensatzVerzugsfahrzeug_proH()		);
		cmbEquipe.setSelectedItem( 					  ((ArbeitssystemKombiseilgeraet2018)data.getArbeitssystem()).getEquipeAnzahlPersonen() 				);
		txtAnteilEinsatzzeitVerzugsfahrzeug.setValue( ((ArbeitssystemKombiseilgeraet2018)data.getArbeitssystem()).getAnteilEinsatzzeitVerzugsfahrzeug_Prz() );
		
		super.setReactOnInputChange(true);
	}

}
