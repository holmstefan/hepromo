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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.AbstractErgebnisPanel;
import ch.wsl.fps.hepromo.gui.ErgebnisPanelHelikopterGesamt;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.HelikopterPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelHelikopterGesamtForstbetrieb;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterGesamt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class HelikopterGesamt extends HelikopterFliegenBase {

	private static final long serialVersionUID = 1L;

	private JLabel lblHolzmenge_m3oR_readOnly;
	private JComboBox<ArbeitsVerfahren> cmbArbeitsVerfahren;
	private JLabel lblBaumartenGruppe;
	private JComboBox<BaumartenGruppe> cmbBaumartenGruppe;
	
	private KostensaetzePanelHelikopterGesamtForstbetrieb panelKostensaetze;
	private JCheckBox chkKalkulationInklLagerplatzarbeit;
	
	private ModelHelikopterGesamt model = new ModelHelikopterGesamt();
	
	public HelikopterGesamt() {
		super.setTitle(GuiStrings.getString("HelikopterGesamt.Title")); //$NON-NLS-1$
		super.setSize((int) (710 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (730 * MainWindow.SIZE));
		
		super.initalize();
	}
	
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblHolzmenge = new JLabel(GuiStrings.getString("HelikopterGesamt.Holzmenge")); //$NON-NLS-1$
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtHolzmenge_m3iR = new JSpinner(new SpinnerNumberModel(250.0, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3iR);
		txtHolzmenge_m3iR.addChangeListener(evt -> {
			updateLabelHolzmenge_m3iR();
		});
		panel.add(txtHolzmenge_m3iR, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,5,0,5);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Allgemein.lblInfoButtonUmrechnung_iRoR")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblHolzmenge_m3oR_readOnly, c);
		
		
		//Label ArbeitsVerfahren
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblArbeitsVerfahren = new JLabel(GuiStrings.getString("HelikopterGesamt.Arbeitsverfahren")); //$NON-NLS-1$
		panel.add(lblArbeitsVerfahren, c);
		
		//Text ArbeitsVerfahren
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		cmbArbeitsVerfahren = new JComboBox<>();
		panel.add(cmbArbeitsVerfahren, c);
		
		
		
		//Label Holztyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblHolztyp = new JLabel(GuiStrings.getString("HelikopterGesamt.Holztyp")); //$NON-NLS-1$
		panel.add(lblHolztyp, c);
		
		//Combo Holztyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		cmbHolztyp = new JComboBox<>();
		panel.add(cmbHolztyp, c);
		
		
		//Label Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		lblBaumartenGruppe = new JLabel(GuiStrings.getString("HelikopterGesamt.Baumartengruppe")); //$NON-NLS-1$
		panel.add(lblBaumartenGruppe, c);
		
		//Combo Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		cmbBaumartenGruppe = new JComboBox<>();
		panel.add(cmbBaumartenGruppe, c);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 25;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);

		//***************** 2. Spalte
		
		
		//Label Horizontaldistanz
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblHorizontaldistanz = new JLabel(GuiStrings.getString("HelikopterGesamt.Horizontaldistanz_m")); //$NON-NLS-1$
		panel.add(lblHorizontaldistanz, c);
		
		//Text Horizontaldistanz
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtHorizontalDistanz_m = new JSpinner(new SpinnerNumberModel(300, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHorizontalDistanz_m);
		panel.add(txtHorizontalDistanz_m, c);
		
		
		//Label Vertikaldistanz
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblVertikaldistanz = new JLabel(GuiStrings.getString("HelikopterGesamt.Vertikaldistanz_m")); //$NON-NLS-1$
		panel.add(lblVertikaldistanz, c);
		
		//Text Vertikaldistanz
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		txtVertikalDistanz_m = new JSpinner(new SpinnerNumberModel(100, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtVertikalDistanz_m);
		panel.add(txtVertikalDistanz_m, c);

		//InfoButton Vertikaldistanz
        c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,0,0,10);
		JLabel lblVertikaldistanz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("HelikopterGesamt.InfoButtonVertikaldistanz")); //$NON-NLS-1$
		panel.add(lblVertikaldistanz_Info, c);

		
		
		//Label Modell-Info
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 7;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(30,10,0,10);
		JLabel lblModellInfo = new JLabel(GuiStrings.getString("HelikopterGesamt.ModellInfo")); //$NON-NLS-1$
		panel.add(lblModellInfo, c);
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder2 = new JPanel();
		panel.add(placeholder2, c);
	}

	
	@Override
	protected void initPanelArbeitssystem(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Helifirma
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.anchor = GridBagConstraints.NORTH;
//		c.insets = new Insets(5,5,5,5);
		panelHelikopter = new HelikopterPanel(this);
		panel.add(panelHelikopter, c);
		
		//panel Arbeitswege / Pausen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);	
		
		
		//panel Kostensätze
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelHelikopterGesamtForstbetrieb(this);
		panel.add(panelKostensaetze, c);
		
		//panel Weiteres
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);
		
		//Checkbox "Kalk. inkl. Lagerplatzarbeit"
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		chkKalkulationInklLagerplatzarbeit = new JCheckBox(GuiStrings.getString("HelikopterGesamt.KalkulationInklLagerplatzarbeit")); //$NON-NLS-1$
		addDefaultItemListener(chkKalkulationInklLagerplatzarbeit);
		chkKalkulationInklLagerplatzarbeit.setSelected(true);
		panel.add(chkKalkulationInklLagerplatzarbeit, c);

		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);	
	}


	
	@Override
	protected AbstractErgebnisPanel initErgebnisPanel() {
		return new ErgebnisPanelHelikopterGesamt();
	}
	
	
	@Override
	protected void initData() {
		for (ArbeitsVerfahren arbeitsVerfahren: ArbeitsVerfahren.values()) {
			cmbArbeitsVerfahren.addItem(arbeitsVerfahren);
		}
		addDefaultActionListener(cmbArbeitsVerfahren);
		cmbArbeitsVerfahren.setSelectedItem(ArbeitsVerfahren.Sortimentsverfahren);
		
		
		for (Holztyp holztyp: Holztyp.values()) {
			cmbHolztyp.addItem(holztyp);
		}
		addDefaultActionListener(cmbHolztyp);
		cmbHolztyp.setSelectedItem(Holztyp.Nadelholz_frisch);


		cmbBaumartenGruppe.addItem(BaumartenGruppe.Fichte);
		cmbBaumartenGruppe.addItem(BaumartenGruppe.Tanne);
		cmbBaumartenGruppe.addItem(BaumartenGruppe.Foehre_Laerche);
		addDefaultActionListener(cmbBaumartenGruppe);
		cmbBaumartenGruppe.setSelectedItem(BaumartenGruppe.Fichte);

		checkShowComboBaumartengruppe();
		adaptItemsInComboBaumartengruppe();
		adaptLastvolumen();
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmenge_m3iR.getValue();
		value *= getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html><nobr>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("Allgemein.oR") + ")</nobr></html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		updateLabelHolzmenge_m3iR(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(			(Double) 			txtHolzmenge_m3iR.getValue()			);
		model.getArbeitsobjekt().setHolztyp(				(Holztyp) 			cmbHolztyp.getSelectedItem()			);
		model.getArbeitsobjekt().setArbeitsVerfahren(		(ArbeitsVerfahren) 	cmbArbeitsVerfahren.getSelectedItem()	);
		model.getArbeitsobjekt().setHorizontalDistanz_m(	(Integer) 			txtHorizontalDistanz_m.getValue()		);
		model.getArbeitsobjekt().setVertikalDistanz_m(		(Integer) 			txtVertikalDistanz_m.getValue()			);
		model.getArbeitsobjekt().setBaumartenGruppe(		(BaumartenGruppe)	cmbBaumartenGruppe.getSelectedItem()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setHelikopterKosten_proMin(	panelHelikopter.getHelikopterKosten_proMin()			);
		model.getArbeitssystem().setAnflugPauschale(			panelHelikopter.getAnflugpauschale()					);
		model.getArbeitssystem().setHelikopterKlasse(			panelHelikopter.getHelikopterKlasse()					);
		model.getArbeitssystem().setLastVolumen(				panelHelikopter.getLastvolumen_m3()						);
		model.getArbeitssystem().setLastVolumenAutomatischBerechnen(panelHelikopter.isLastvolumenAutomatischBerechnen()	);

		
		model.getArbeitssystem().setAnzahlPersonalBeimHolzFliegen(	panelKostensaetze.getAnzahlPersonalFliegen()		);
		model.getArbeitssystem().setAnzahlPersonalNachHolzFliegen(	panelKostensaetze.getAnzahlPersonalLagerplatz()		);
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setAnzahlMotorsaegen(			panelKostensaetze.getAnzahlMotorsaegen()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(	panelKostensaetze.getAnsatzMotorsaegen()				);
		model.getArbeitssystem().setAnzahlKranfahrzeuge(		panelKostensaetze.getAnzahlKranfahrzeuge()				);
		model.getArbeitssystem().setKostensatzMaschine2_proH(	panelKostensaetze.getAnsatzKranfahrzeuge()				);
		

		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()				);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()		);
		
		model.getArbeitssystem().setUmsetzenBetrag_CHF(			panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h( 			panelWeitereAufwaende.getUmsetzenZeit_h()				);
		
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
		
		model.getArbeitssystem().setKalkulationInklLagerplatzarbeit(chkKalkulationInklLagerplatzarbeit.isSelected()		);
	}


	@Override
	public ModelHelikopterGesamt getModel() {
		return (ModelHelikopterGesamt) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		txtHolzmenge_m3iR.setValue(				  ((ArbeitsobjektHelikopterGesamt) data.getArbeitsobjekt()).getHolzmenge_m3()				);
		updateLabelHolzmenge_m3iR();
		cmbHolztyp.setSelectedItem(				  ((ArbeitsobjektHelikopterGesamt) data.getArbeitsobjekt()).getHolztyp()				);
		txtHorizontalDistanz_m.setValue(	(int) ((ArbeitsobjektHelikopterGesamt) data.getArbeitsobjekt()).getHorizontalDistanz_m()	);
		txtVertikalDistanz_m.setValue(		(int) ((ArbeitsobjektHelikopterGesamt) data.getArbeitsobjekt()).getVertikalDistanz_m()		);
		cmbBaumartenGruppe.setSelectedItem(		  ((ArbeitsobjektHelikopterGesamt) data.getArbeitsobjekt()).getBaumartenGruppe()		);
		cmbArbeitsVerfahren.setSelectedItem(	  ((ArbeitsobjektHelikopterGesamt) data.getArbeitsobjekt()).getArbeitsVerfahren()		);
		
		
		//Arbeitssystem
		panelHelikopter.setHelikopterKosten_proMin(	(int) ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getHelikopterKosten_proMin() 	);
		panelHelikopter.setAnflugpauschale(		(int) ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getAnflugPauschale()				);
		panelHelikopter.setHelikopterKlasse(		  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getHelikopterKlasse()				);
		panelHelikopter.setLastvolumen_m3(			  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getLastVolumen()					);
		panelHelikopter.setLastvolumenAutomatischBerechnen(	((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).isLastVolumenAutomatischBerechnen()	);
		adaptLastvolumen();

		panelKostensaetze.setAnzahlPersonalFliegen(		(int) ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getAnzahlPersonalBeimHolzFliegen()	); 
		panelKostensaetze.setAnzahlPersonalLagerplatz(	(int) ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getAnzahlPersonalNachHolzFliegen()	);
		panelKostensaetze.setAnsatzPersonal(				  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnzahlMotorsaegen(			(int) ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getAnzahlMotorsaegen()				);
		panelKostensaetze.setAnsatzMotorsaegen(				  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getKostensatzMaschine1_proH()		);
		panelKostensaetze.setAnzahlKranfahrzeuge(		(int) ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getAnzahlKranfahrzeuge()			);
		panelKostensaetze.setAnsatzKranfahrzeuge(			  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getKostensatzMaschine2_proH()		);
		
		
		panelArbeitswegePausen.setArbeitszeit_min(			((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()		);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getWegzeitenUndPausen_Min()		);
		
		panelWeitereAufwaende.setUmsetzenBetrag_CHF(	 	((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getUmsetzenBetrag_CHF()	);
		panelWeitereAufwaende.setUmsetzenZeit_h(		  	((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getUmsetzenZeit_h()		);
		
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()		);
		
		chkKalkulationInklLagerplatzarbeit.setSelected(	    	  ((ArbeitssystemHelikopterGesamt) data.getArbeitssystem()).isKalkulationInklLagerplatzarbeit()	);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}
	
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		
		if (cmbArbeitsVerfahren.equals(eventSource)) {
			//Wenn arbeitsverfahren geändert wird, müssen automatisch 
			// die anzahl personen / motorsägen / kranfz. angepasst werden!
			if (cmbArbeitsVerfahren.getSelectedItem() == ArbeitsVerfahren.Sortimentsverfahren) {
				panelKostensaetze.setAnzahlPersonalLagerplatz(2);
				panelKostensaetze.setAnzahlMotorsaegen(1);
				panelKostensaetze.setAnzahlKranfahrzeuge(1);
			}
			else {
				panelKostensaetze.setAnzahlPersonalLagerplatz(3);
				panelKostensaetze.setAnzahlMotorsaegen(2);
				panelKostensaetze.setAnzahlKranfahrzeuge(1);
			}

			checkShowComboBaumartengruppe();
			adaptItemsInComboBaumartengruppe();
			adaptLastvolumen();
			
		}
		else if (cmbHolztyp.equals(eventSource)) {
			checkShowComboBaumartengruppe();
			adaptItemsInComboBaumartengruppe();
			adaptLastvolumen();
		}
		
		panelHelikopter.onInputChangedBeforeCalculation(eventSource);
	}
	

	

	//Falls Vollbaumverfahren und Holztyp Nadelholz -> Combo Baumartengruppe anzeigen
	private void checkShowComboBaumartengruppe(){
		if (cmbArbeitsVerfahren.getSelectedItem() == ArbeitsVerfahren.Vollbaumverfahren) {
			if (
					cmbHolztyp.getSelectedItem() == Holztyp.Nadelholz_frisch ||
					cmbHolztyp.getSelectedItem() == Holztyp.Nadelholz_angetrocknet) {
				
				lblBaumartenGruppe.setVisible(true);
				cmbBaumartenGruppe.setVisible(true);
				return;
			}
		}
		
		lblBaumartenGruppe.setVisible(false);
		cmbBaumartenGruppe.setVisible(false);
	}


	private void adaptItemsInComboBaumartengruppe() {
		if (
				cmbHolztyp.getSelectedItem() == Holztyp.Laubholz_angetrocknet ||
				cmbHolztyp.getSelectedItem() == Holztyp.Laubholz_frisch ) {
			
			cmbBaumartenGruppe.removeAllItems();
			cmbBaumartenGruppe.addItem(BaumartenGruppe.Laubholz);
			cmbBaumartenGruppe.setSelectedItem(BaumartenGruppe.Laubholz);
		}
		else {
			cmbBaumartenGruppe.removeAllItems();
			cmbBaumartenGruppe.addItem(BaumartenGruppe.Fichte);
			cmbBaumartenGruppe.addItem(BaumartenGruppe.Tanne);
			cmbBaumartenGruppe.addItem(BaumartenGruppe.Foehre_Laerche);
			cmbBaumartenGruppe.setSelectedItem(BaumartenGruppe.Fichte);
		}
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
	}
	
	
}
