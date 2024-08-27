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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterAufarbeiten;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public class HelikopterAufarbeiten extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtHolzmenge;
	private JComboBox<BaumartenGruppe> cmbBaumartenGruppe;
	private JComboBox<ArbeitsVerfahren> cmbArbeitsVerfahren;
	
	private KostensaetzePanelHelikopterAufarbeiten panelKostensaetze;
	private ArbeitswegePausenPanel panelArbeitswegePausen;
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelHelikopterAufarbeiten model = new ModelHelikopterAufarbeiten();
	
	public HelikopterAufarbeiten() {
		super.setTitle("Holz aufarbeiten, sortieren, lagern (Helikopter)");
		super.setSize(600, 605);
		
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
		JLabel lblHolzmenge = new JLabel("<html>Holzmenge (m<sup>3</sup>)</html>");
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtHolzmenge = new JSpinner(new SpinnerNumberModel(250.0, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge);
		panel.add(txtHolzmenge, c);
		
		
		//Label Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblBaumartengruppe = new JLabel("Baumartengruppe");
		panel.add(lblBaumartengruppe, c);
		
		//Combo Baumartengruppe
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		cmbBaumartenGruppe = new JComboBox<>();
		panel.add(cmbBaumartenGruppe, c);
		
		
		//Label Arbeitsverfahren
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblArbeitsverfahren = new JLabel("Arbeitsverfahren");
		panel.add(lblArbeitsverfahren, c);
		
		//Combo Arbeitsverfahren
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		cmbArbeitsVerfahren = new JComboBox<>();
		cmbArbeitsVerfahren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Wenn arbeitsverfahren geändert wird, müssen automatisch 
				// die anzahl personen / motorsägen / kranfz. angepasst werden!
				if (cmbArbeitsVerfahren.getSelectedItem() == ArbeitsVerfahren.Sortimentsverfahren) {
					panelKostensaetze.setAnzahlPersonal(2);
					panelKostensaetze.setAnzahlMotorsaegen(1);
					panelKostensaetze.setAnzahlKranfahrzeuge(1);
				}
				else {
					panelKostensaetze.setAnzahlPersonal(3);
					panelKostensaetze.setAnzahlMotorsaegen(2);
					panelKostensaetze.setAnzahlKranfahrzeuge(1);
				}
			}
		});
		panel.add(cmbArbeitsVerfahren, c);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 200;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);

		//***************** 2. Spalte
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
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
		
		
		//panel Kostensätze
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelHelikopterAufarbeiten(this);
		panel.add(panelKostensaetze, c);
		
		//panel Arbeitswege / Pausen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);	
		
		//panel Weiteres
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);

		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);	
	}


	
	@Override
	protected ErgebnisPanel initErgebnisPanel() {
		return new ErgebnisPanel.Builder()
				.enableRowPersonal()
				.enableRowMaschine1()
				.enableRowMaschine2()
				.enableRowUmsetzen()
				.enableRowProduktivitaet()
				.enableColumnProM3()
				.build();
	}

	
	@Override
	protected void initData() {
		for (BaumartenGruppe baumartenGruppe: BaumartenGruppe.values()) {
			cmbBaumartenGruppe.addItem(baumartenGruppe);
		}
		addDefaultActionListener(cmbBaumartenGruppe);
		cmbBaumartenGruppe.setSelectedItem(BaumartenGruppe.Fichte);
		
		
		for (ArbeitsVerfahren arbeitsVerfahren: ArbeitsVerfahren.values()) {
			cmbArbeitsVerfahren.addItem(arbeitsVerfahren);
		}
		addDefaultActionListener(cmbArbeitsVerfahren);
		cmbArbeitsVerfahren.setSelectedItem(ArbeitsVerfahren.Sortimentsverfahren);
		
		((ErgebnisPanel) ergebnisPanel).setLabelUmsetzen("Kranfahrzeug umsetzen");
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(		(Double) txtHolzmenge.getValue()							);
		model.getArbeitsobjekt().setBaumartenGruppe(	(BaumartenGruppe) cmbBaumartenGruppe.getSelectedItem()		);
		model.getArbeitsobjekt().setArbeitsVerfahren(	(ArbeitsVerfahren) cmbArbeitsVerfahren.getSelectedItem()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setAnzahlPersonal1(			panelKostensaetze.getAnzahlPersonal()					);
		model.getArbeitssystem().setKostensatzPersonal1_proH(	panelKostensaetze.getAnsatzPersonal()					);
		model.getArbeitssystem().setAnzahlMaschine1(			panelKostensaetze.getAnzahlMotorsaegen()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(	panelKostensaetze.getAnsatzMotorsaegen()				);
		model.getArbeitssystem().setAnzahlMaschine2(			panelKostensaetze.getAnzahlKranfahrzeuge()				);
		model.getArbeitssystem().setKostensatzMaschine2_proH(	panelKostensaetze.getAnsatzKranfahrzeuge()				);

		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()				);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()		);

		model.getArbeitssystem().setUmsetzenBetrag_CHF(			panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h( 			panelWeitereAufwaende.getUmsetzenZeit_h()				);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}


	@Override
	public ModelHelikopterAufarbeiten getModel() {
		return (ModelHelikopterAufarbeiten) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		txtHolzmenge.setValue(					((ArbeitsobjektHelikopterAufarbeiten) data.getArbeitsobjekt()).getHolzmenge_m3()		);
		cmbBaumartenGruppe.setSelectedItem(		((ArbeitsobjektHelikopterAufarbeiten) data.getArbeitsobjekt()).getBaumartenGruppe()		);
		cmbArbeitsVerfahren.setSelectedItem(	((ArbeitsobjektHelikopterAufarbeiten) data.getArbeitsobjekt()).getArbeitsVerfahren()	);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnzahlPersonal(				(int) ((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getAnzahlPersonal1()			);
		panelKostensaetze.setAnsatzPersonal(					  ((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnzahlMotorsaegen(				(int) ((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getAnzahlMaschine1()			);
		panelKostensaetze.setAnsatzMotorsaegen(				 	  ((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getKostensatzMaschine1_proH()		);
		panelKostensaetze.setAnzahlKranfahrzeuge(			(int) ((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getAnzahlMaschine2()			);
		panelKostensaetze.setAnsatzKranfahrzeuge(				  ((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getKostensatzMaschine2_proH()		);
		
		panelArbeitswegePausen.setArbeitszeit_min(			((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()	);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getWegzeitenUndPausen_Min()		);
		
		panelWeitereAufwaende.setUmsetzenBetrag_CHF(		((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getUmsetzenBetrag_CHF()			);
		panelWeitereAufwaende.setUmsetzenZeit_h(			((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getUmsetzenZeit_h()				);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(	((ArbeitssystemHelikopterAufarbeiten) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}

}
