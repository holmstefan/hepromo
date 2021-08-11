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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SeilliniePanelKonventionellerSeilkranGesamt extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtLinienlaenge_m;
	private JComboBox<Fahrtrichtung> cmbFahrtrichtung;
	private JSpinner txtMittlereFahrdistanz_m;
	private JSpinner txtMittlereDistanzSeitlicherZuzug_m;
	
	private JSpinner txtAnzahlStuetzen;
	private JSpinner txtAnzahlEndmasten;
	private JSpinner txtTragseilHoeheLagerplatz_m;
	private JSpinner txtTragseilHoeheBestand_m;
	
	private JCheckBox chkMontageIstSeiverlegung;
	private JComboBox<WindenTransport> cmbMontageWindenTransport;
	private JComboBox<WindenStandort> cmbMontageWindenStandort;
	private JSpinner txtMontageDistanzWindenselbstfahrt_m;
	
	private JCheckBox chkDemontageIstSeiverlegung;
	private JComboBox<WindenTransport> cmbDemontageWindenTransport;
	private JComboBox<WindenStandort> cmbDemontageWindenStandort;
	private JSpinner txtDemontageDistanzWindenselbstfahrt_m;
	
	
	public SeilliniePanelKonventionellerSeilkranGesamt(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		
		
		//Label Linienlänge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblLinienlaenge = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Linienlaenge_m")); //$NON-NLS-1$
		this.add(lblLinienlaenge, c);
		
		//Text Linienlänge
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtLinienlaenge_m = new JSpinner();
		this.add(txtLinienlaenge_m, c);
		

		
		//Label Fahrtrichtung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblFahrtrichtung = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Fahrtrichtung")); //$NON-NLS-1$
		this.add(lblFahrtrichtung, c);
		
		//Combo Fahrtrichtung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbFahrtrichtung = new JComboBox<>();
		this.add(cmbFahrtrichtung, c);
		
		
		
		//Label Mittlere Fahrdistanz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMittlereFahrdistanz = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.MittlereFahrdistanz_m")); //$NON-NLS-1$
		this.add(lblMittlereFahrdistanz, c);
		
		//Text Mittlere Fahrdistanz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlereFahrdistanz_m = new JSpinner();
		this.add(txtMittlereFahrdistanz_m, c);
		
		
		
		//Label Mittlere Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMittlereDistanzSeitlicherZuzug_m = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.MittlereDistanzSeitlicherZuzug")); //$NON-NLS-1$
		this.add(lblMittlereDistanzSeitlicherZuzug_m, c);
		
		//Text Mittlere Distanz seitlicher Zuzug
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlereDistanzSeitlicherZuzug_m = new JSpinner();
		this.add(txtMittlereDistanzSeitlicherZuzug_m, c);
		
		
		


		
		//Label Anzahl
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblAnzahl = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Anzahl")); //$NON-NLS-1$
		this.add(lblAnzahl, c);
		
		//Label Tragseilhöhe
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblTragseilhoehe = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Tragseilhoehe")); //$NON-NLS-1$
		this.add(lblTragseilhoehe, c);
		
		
		
		
		
		//Label Anzahl Stützen
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,20,0,0);
		JLabel lblAnzahlStuetzen = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Stuetzen")); //$NON-NLS-1$
		this.add(lblAnzahlStuetzen, c);
		
		//Text Anzahl Stützen
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnzahlStuetzen = new JSpinner();
		this.add(txtAnzahlStuetzen, c);
		
		
		
		//Label Anzahl Endmasten
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,20,0,0);
		JLabel lblAnzahlEndmasten = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Endmasten")); //$NON-NLS-1$
		this.add(lblAnzahlEndmasten, c);
		
		//Text Anzahl Endmasten
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnzahlEndmasten = new JSpinner();
		this.add(txtAnzahlEndmasten, c);
		
		
		

		
		
		
		//Label Tragseilhöhe Bestand
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,20,0,0);
		JLabel lblTragseilHoeheBestand = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Bestand")); //$NON-NLS-1$
		this.add(lblTragseilHoeheBestand, c);
		
		//Text Tragseilhöhe Bestand
        c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtTragseilHoeheBestand_m = new JSpinner();
		this.add(txtTragseilHoeheBestand_m, c);
		
		
		
		
		//Label Tragseilhöhe Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,20,0,0);
		JLabel lblTragseilHoeheLagerplatz = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Lagerplatz")); //$NON-NLS-1$
		this.add(lblTragseilHoeheLagerplatz, c);
		
		//Text Tragseilhöhe Lagerplatz
        c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtTragseilHoeheLagerplatz_m = new JSpinner();
		this.add(txtTragseilHoeheLagerplatz_m, c);
		
		
		

		
		//Windenoptionen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 7;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(initPanelWindenoptionen(), c);
	}
	
	
	private void initData(){
		txtLinienlaenge_m.setModel(new SpinnerNumberModel(800, 0, 10000, 100));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLinienlaenge_m);
		
		for (Fahrtrichtung value : Fahrtrichtung.values()) {
			cmbFahrtrichtung.addItem(value);
		}
		parent.addDefaultActionListener(cmbFahrtrichtung);
		cmbFahrtrichtung.setSelectedItem(Fahrtrichtung.Bergab);
		
		txtMittlereFahrdistanz_m.setModel(new SpinnerNumberModel(350, 1, 10000, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlereFahrdistanz_m);
		txtMittlereDistanzSeitlicherZuzug_m.setModel(new SpinnerNumberModel(20, 1, 10000, 10));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlereDistanzSeitlicherZuzug_m);

		
		
		txtAnzahlStuetzen.setModel(new SpinnerNumberModel(2, 0, 20, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlStuetzen);
		
		txtAnzahlEndmasten.setModel(new SpinnerNumberModel(1, 0, 10, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlEndmasten);
		
		txtTragseilHoeheLagerplatz_m.setModel(new SpinnerNumberModel(10, 1, 200, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtTragseilHoeheLagerplatz_m);
		
		txtTragseilHoeheBestand_m.setModel(new SpinnerNumberModel(12, 1, 200, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtTragseilHoeheBestand_m);
		
		
		
		chkMontageIstSeiverlegung.setSelected(false);
		parent.addDefaultItemListener(chkMontageIstSeiverlegung);
		
		for (WindenTransport value : WindenTransport.values()) {
			cmbMontageWindenTransport.addItem(value);
		}
		parent.addDefaultActionListener(cmbMontageWindenTransport);
		cmbMontageWindenTransport.setSelectedItem(WindenTransport.KeinWindenTransport);
		
		for (WindenStandort value : WindenStandort.values()) {
			cmbMontageWindenStandort.addItem(value);
		}
		parent.addDefaultActionListener(cmbMontageWindenStandort);
		cmbMontageWindenStandort.setSelectedItem(WindenStandort.Bleibt);
		
		txtMontageDistanzWindenselbstfahrt_m.setModel(new SpinnerNumberModel(200, 0, 10000, 50));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMontageDistanzWindenselbstfahrt_m);
		
		
		
		chkDemontageIstSeiverlegung.setSelected(false);
		parent.addDefaultItemListener(chkDemontageIstSeiverlegung);
		
		for (WindenTransport value : WindenTransport.values()) {
			cmbDemontageWindenTransport.addItem(value);
		}
		parent.addDefaultActionListener(cmbDemontageWindenTransport);
		cmbDemontageWindenTransport.setSelectedItem(WindenTransport.KeinWindenTransport);
		
		for (WindenStandort value : WindenStandort.values()) {
			cmbDemontageWindenStandort.addItem(value);
		}
		parent.addDefaultActionListener(cmbDemontageWindenStandort);
		cmbDemontageWindenStandort.setSelectedItem(WindenStandort.Bleibt);
		
		txtDemontageDistanzWindenselbstfahrt_m.setModel(new SpinnerNumberModel(200, 0, 10000, 50));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDemontageDistanzWindenselbstfahrt_m);
		
		onInputChangedBeforeCalculation(null);
	}
	
	
	
	
	private JPanel initPanelWindenoptionen() {
		
		JPanel result = new JPanel(new GridBagLayout());
		GridBagConstraints c;

		
		//Label Winden-Transport
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(15,0,0,0);
		JLabel lblWindenTransport = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Windentransport"), SwingConstants.CENTER); //$NON-NLS-1$
		result.add(lblWindenTransport, c);
		
		//Label Winden-Standort
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(15,5,0,5);
		JLabel lblWindenStandort = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Windenstandort"), SwingConstants.CENTER); //$NON-NLS-1$
		result.add(lblWindenStandort, c);
		
		//Label Distanz Windenselbstfahrt
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(15,5,0,0);
		JLabel lblDistanzWindenselbstfahrt = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.DistanzWindenselbstfahrt_m"), SwingConstants.CENTER); //$NON-NLS-1$
		result.add(lblDistanzWindenselbstfahrt, c);
		
		
		
		
		
	
		//Label Montage
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMontage = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Montage")); //$NON-NLS-1$
		result.add(lblMontage, c);
		
		//Checkbox Montage ist Seilverlegung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		chkMontageIstSeiverlegung = new JCheckBox(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Seilverlegung")); //$NON-NLS-1$
		chkMontageIstSeiverlegung.setHorizontalTextPosition(SwingConstants.RIGHT);
		result.add(chkMontageIstSeiverlegung, c);
		
		//Combo Winden-Transport
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbMontageWindenTransport = new JComboBox<>();
		result.add(cmbMontageWindenTransport, c);
		
		//Combo Winden-Standort
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbMontageWindenStandort = new JComboBox<>();
		result.add(cmbMontageWindenStandort, c);
		
		//Text Windenselbstfahrt
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMontageDistanzWindenselbstfahrt_m = new JSpinner();
		result.add(txtMontageDistanzWindenselbstfahrt_m, c);
		
		
		
		
		
	
		//Label Demontage
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblDemontage = new JLabel(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Demontage")); //$NON-NLS-1$
		result.add(lblDemontage, c);
		
		//Checkbox Demontage ist Seilverlegung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		chkDemontageIstSeiverlegung = new JCheckBox(GuiStrings.getString("SeilliniePanelKonventionellerSeilkranGesamt.Seilverlegung")); //$NON-NLS-1$
		chkDemontageIstSeiverlegung.setHorizontalTextPosition(SwingConstants.RIGHT);
		result.add(chkDemontageIstSeiverlegung, c);
		
		//Combo Winden-Transport
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbDemontageWindenTransport = new JComboBox<>();
		result.add(cmbDemontageWindenTransport, c);
		
		//Combo Winden-Standort
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbDemontageWindenStandort = new JComboBox<>();
		result.add(cmbDemontageWindenStandort, c);
		
		//Text Windenselbstfahrt
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtDemontageDistanzWindenselbstfahrt_m = new JSpinner();
		result.add(txtDemontageDistanzWindenselbstfahrt_m, c);
		
		
		return result;
	}
	
	
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		
		if (chkMontageIstSeiverlegung.equals(eventSource) || eventSource == null) {
			if (chkMontageIstSeiverlegung.isSelected() == true) {
				cmbMontageWindenTransport.setSelectedItem(WindenTransport.KeinWindenTransport);
				cmbMontageWindenTransport.setEnabled(false);
				cmbMontageWindenStandort.setEnabled(true);
				txtMontageDistanzWindenselbstfahrt_m.setEnabled(false);
			}
			else {
				cmbMontageWindenTransport.setEnabled(true);
				cmbMontageWindenStandort.setEnabled(false);
				txtMontageDistanzWindenselbstfahrt_m.setEnabled(false);
			}
		}
		
		if (chkDemontageIstSeiverlegung.equals(eventSource) || eventSource == null) {
			if (chkDemontageIstSeiverlegung.isSelected() == true) {
				cmbDemontageWindenTransport.setSelectedItem(WindenTransport.KeinWindenTransport);
				cmbDemontageWindenTransport.setEnabled(false);
				cmbDemontageWindenStandort.setEnabled(true);
				txtDemontageDistanzWindenselbstfahrt_m.setEnabled(false);
			}
			else {
				cmbDemontageWindenTransport.setEnabled(true);
				cmbDemontageWindenStandort.setEnabled(false);
				txtDemontageDistanzWindenselbstfahrt_m.setEnabled(false);
			}
		}
		
		if (cmbMontageWindenTransport.equals(eventSource) || eventSource == null) {
			WindenTransport selection = (WindenTransport)cmbMontageWindenTransport.getSelectedItem();
			if ( WindenTransport.SelbstfahrtBergab.equals(selection) || WindenTransport.SelbstfahrtBergauf.equals(selection) ) {
				txtMontageDistanzWindenselbstfahrt_m.setEnabled(true);
			}
			else {
				txtMontageDistanzWindenselbstfahrt_m.setEnabled(false);
			}
		}
		
		if (cmbDemontageWindenTransport.equals(eventSource) || eventSource == null) {
			WindenTransport selection = (WindenTransport)cmbDemontageWindenTransport.getSelectedItem();
			if ( WindenTransport.SelbstfahrtBergab.equals(selection) || WindenTransport.SelbstfahrtBergauf.equals(selection) ) {
				txtDemontageDistanzWindenselbstfahrt_m.setEnabled(true);
			}
			else {
				txtDemontageDistanzWindenselbstfahrt_m.setEnabled(false);
			}
		}
	}
	
	
	
	
	
	
	public int getLinienlaenge_m() {
		return (Integer) txtLinienlaenge_m.getValue();
	}
	
	
	public Fahrtrichtung getFahrtrichtung() {
		return (Fahrtrichtung) cmbFahrtrichtung.getSelectedItem();
	}
	
	
	public int getMittlereFahrdistanz_m() {
		return (Integer) txtMittlereFahrdistanz_m.getValue();
	}
	
	
	public int getMittlereDistanzSeitlicherZuzug() {
		return (Integer) txtMittlereDistanzSeitlicherZuzug_m.getValue();
	}
	
	
	public int getAnzahlStuetzen() {
		return (Integer) txtAnzahlStuetzen.getValue();
	}
	
	
	public int getAnzahlEndmasten() {
		return (Integer) txtAnzahlEndmasten.getValue();
	}
	
	
	public int getTragseilHoeheLagerplatz_m() {
		return (Integer) txtTragseilHoeheLagerplatz_m.getValue();
	}
	
	
	public int getTragseilHoeheBestand_m() {
		return (Integer) txtTragseilHoeheBestand_m.getValue();
	}
	
	

	
	
	

	public boolean isMontageSeilverlegung() {
		return chkMontageIstSeiverlegung.isSelected();
	}
	
	
	public WindenTransport getMontageWindenTransport() {
		return (WindenTransport) cmbMontageWindenTransport.getSelectedItem();
	}
	
	
	public WindenStandort getMontageWindenStandort() {
		return (WindenStandort) cmbMontageWindenStandort.getSelectedItem();
	}
	
	
	public int getMontageDistanzWindenselbstfahrt_m() {
		return (Integer) txtMontageDistanzWindenselbstfahrt_m.getValue();
	}
	
	
	
	
	

	public boolean isDemontageSeilverlegung() {
		return chkDemontageIstSeiverlegung.isSelected();
	}
	
	
	public WindenTransport getDemontageWindenTransport() {
		return (WindenTransport) cmbDemontageWindenTransport.getSelectedItem();
	}
	
	
	public WindenStandort getDemontageWindenStandort() {
		return (WindenStandort) cmbDemontageWindenStandort.getSelectedItem();
	}
	
	
	public int getDemontageDistanzWindenselbstfahrt_m() {
		return (Integer) txtDemontageDistanzWindenselbstfahrt_m.getValue();
	}



	public void setLinienlaenge_m(int linienLaenge_m) {
		txtLinienlaenge_m.setValue(linienLaenge_m);
	}

	public void setFahrtrichtung(Fahrtrichtung fahrtrichtung) {
		cmbFahrtrichtung.setSelectedItem(fahrtrichtung);
	}

	public void setMittlereFahrdistanz_m(int mittlereFahrdistanz_m) {
		txtMittlereFahrdistanz_m.setValue(mittlereFahrdistanz_m);
	}

	public void setMittlereDistanzSeitlicherZuzug(int mittlereDistanzSeitlicherZuzug_m) {
		txtMittlereDistanzSeitlicherZuzug_m.setValue(mittlereDistanzSeitlicherZuzug_m);
	}

	public void setAnzahlStuetzen(int anzahlStuezten) {
		txtAnzahlStuetzen.setValue(anzahlStuezten);
	}

	public void setAnzahlEndmasten(int anzahlEndmasten) {
		txtAnzahlEndmasten.setValue(anzahlEndmasten);
	}

	public void setTragseilHoeheBestand_m(int tragseilhoeheBestand_m) {
		txtTragseilHoeheBestand_m.setValue(tragseilhoeheBestand_m);
	}

	public void setTragseilHoeheLagerplatz_m(int tragseilhoeheLagerplatz_m) {
		txtTragseilHoeheLagerplatz_m.setValue(tragseilhoeheLagerplatz_m);
	}

	public void setMontageSeilverlegung(boolean montageIstSeilverlegung) {
		chkMontageIstSeiverlegung.setSelected(montageIstSeilverlegung);
	}

	public void setMontageWindenTransport(WindenTransport montageWindenTransportart) {
		cmbMontageWindenTransport.setSelectedItem(montageWindenTransportart);
	}

	public void setMontageWindenStandort(WindenStandort montageWindenStandort) {
		cmbMontageWindenStandort.setSelectedItem(montageWindenStandort);
	}

	public void setMontageDistanzWindenselbstfahrt_m(int montageDistanzWindenselbstfahrt_m) {
		txtMontageDistanzWindenselbstfahrt_m.setValue(montageDistanzWindenselbstfahrt_m);
	}

	public void setDemontageSeilverlegung(boolean demontageIstSeilverlegung) {
		chkDemontageIstSeiverlegung.setSelected(demontageIstSeilverlegung);
	}

	public void setDemontageWindenTransport(WindenTransport demontageWindenTransportart) {
		cmbDemontageWindenTransport.setSelectedItem(demontageWindenTransportart);
	}

	public void setDemontageWindenStandort(WindenStandort demontageWindenStandort) {
		cmbDemontageWindenStandort.setSelectedItem(demontageWindenStandort);
	}

	public void setDemontageDistanzWindenselbstfahrt_m(int demontageDistanzWindenselbstfahrt_m) {
		txtDemontageDistanzWindenselbstfahrt_m.setValue(demontageDistanzWindenselbstfahrt_m);
	}
	
}
