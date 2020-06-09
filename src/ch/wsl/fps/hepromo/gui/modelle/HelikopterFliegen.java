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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.HelikopterPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelHelikopterFliegen;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterFliegen;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public class HelikopterFliegen extends HelikopterFliegenBase {

	private static final long serialVersionUID = 1L;
	
	private KostensaetzePanelHelikopterFliegen panelKostensaetze;
	
	private ModelHelikopterFliegen model = new ModelHelikopterFliegen();
	
	
	public HelikopterFliegen() {
		super.setTitle("Holz fliegen mit Helikopter");
		super.setSize(560, 605);
		
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
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblHolzmenge = new JLabel("<html>Holzmenge (m<sup>3</sup>)");
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtHolzmenge_m3iR = new JSpinner(new SpinnerNumberModel(250.0, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3iR);
		panel.add(txtHolzmenge_m3iR, c);
		
		
		//Label Holztyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblHolztyp = new JLabel("Holztyp");
		panel.add(lblHolztyp, c);
		
		//Combo Holztyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 50;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		cmbHolztyp = new JComboBox<>();
		panel.add(cmbHolztyp, c);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);

		//***************** 2. Spalte
		
		
		//Label Horizontaldistanz
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblHorizontaldistanz = new JLabel("Horizontaldistanz (m)");
		panel.add(lblHorizontaldistanz, c);
		
		//Text Horizontaldistanz
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtHorizontalDistanz_m = new JSpinner(new SpinnerNumberModel(300, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHorizontalDistanz_m);
		panel.add(txtHorizontalDistanz_m, c);
		
		
		//Label Vertikaldistanz
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblVertikaldistanz = new JLabel("Vertikaldistanz (m)");
		panel.add(lblVertikaldistanz, c);
		
		//Text Vertikaldistanz
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		txtVertikalDistanz_m = new JSpinner(new SpinnerNumberModel(100, 0, 5000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtVertikalDistanz_m);
		panel.add(txtVertikalDistanz_m, c);
		
//
//		//***************** 5. Spalte
//		
//		//Platzhalter
//        c = new GridBagConstraints();
//		c.gridx = 5;
//		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
////		c.insets = new Insets(5,5,5,5);
//		JPanel placeholder2 = new JPanel();
//		panel.add(placeholder2, c);
//
//		//***************** 5. Spalte
//		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.VERTICAL;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder3 = new JPanel();
		panel.add(placeholder3, c);
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
		c.gridheight = 2;
//		c.insets = new Insets(5,5,5,5);
		panelHelikopter = new HelikopterPanel(this);
		panel.add(panelHelikopter, c);
		
		
		//panel Kostensätze
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelHelikopterFliegen(this);
		panel.add(panelKostensaetze, c);
		
		//panel Arbeitswege / Pausen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);	
		
		//panel Weiteres
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this, true);
		panel.add(panelWeitereAufwaende, c);

		
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
	protected ErgebnisPanel initErgebnisPanel() {
		return new ErgebnisPanel(true, false, false, true, true, true);
	}
	
	
	@Override
	protected void initData() {
		for (Holztyp holztyp: Holztyp.values()) {
			cmbHolztyp.addItem(holztyp);
		}
		addDefaultActionListener(cmbHolztyp);
		cmbHolztyp.setSelectedItem(Holztyp.Nadelholz_frisch);
	}
	
	
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbHolztyp.equals(eventSource)) {
			adaptLastvolumen();
		}
		
		panelHelikopter.onInputChangedBeforeCalculation(eventSource);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(			(Double) txtHolzmenge_m3iR.getValue()		);
		model.getArbeitsobjekt().setHolztyp(				(Holztyp) cmbHolztyp.getSelectedItem()		);
		model.getArbeitsobjekt().setHorizontalDistanz_m(	(Integer) txtHorizontalDistanz_m.getValue()	);
		model.getArbeitsobjekt().setVertikalDistanz_m(		(Integer) txtVertikalDistanz_m.getValue()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzMaschine1_proMin(	panelHelikopter.getHelikopterKosten_proMin()			);
		model.getArbeitssystem().setAnflugPauschale(			panelHelikopter.getAnflugpauschale()					);
		model.getArbeitssystem().setHelikopterKlasse(			panelHelikopter.getHelikopterKlasse()					);
		model.getArbeitssystem().setLastVolumen(				panelHelikopter.getLastvolumen_m3()						);
		model.getArbeitssystem().setLastVolumenAutomatischBerechnen(panelHelikopter.isLastvolumenAutomatischBerechnen()	);
		
		model.getArbeitssystem().setAnzahlPersonal(				panelKostensaetze.getAnzahlPersonal()					);
		model.getArbeitssystem().setKostensatzPersonal1_proH(	panelKostensaetze.getAnsatzPersonal()					);

		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()				);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()		);
		
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}


	@Override
	public ModelHelikopterFliegen getModel() {
		return (ModelHelikopterFliegen) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		txtHolzmenge_m3iR.setValue(			((ArbeitsobjektHelikopterFliegen) data.getArbeitsobjekt()).getHolzmenge_m3()				);
		cmbHolztyp.setSelectedItem(			((ArbeitsobjektHelikopterFliegen) data.getArbeitsobjekt()).getHolztyp()						);
		txtHorizontalDistanz_m.setValue(	(int) ((ArbeitsobjektHelikopterFliegen) data.getArbeitsobjekt()).getHorizontalDistanz_m()	);
		txtVertikalDistanz_m.setValue(		(int) ((ArbeitsobjektHelikopterFliegen) data.getArbeitsobjekt()).getVertikalDistanz_m()		);
		
		
		//Arbeitssystem
		panelHelikopter.setHelikopterKosten_proMin(	(int) ((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getKostensatzMaschine1_proMin() );
		panelHelikopter.setAnflugpauschale(		(int) ((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getAnflugPauschale()		);
		panelHelikopter.setHelikopterKlasse(	((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getHelikopterKlasse()			);
		panelHelikopter.setLastvolumen_m3(		((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getLastVolumen()					);
		panelHelikopter.setLastvolumenAutomatischBerechnen(	((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).isLastVolumenAutomatischBerechnen()	);
		
		panelKostensaetze.setAnzahlPersonal(				(int) ((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getAnzahlPersonal()	);
		panelKostensaetze.setAnsatzPersonal(				((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelArbeitswegePausen.setArbeitszeit_min(			((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()	);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getWegzeitenUndPausen_Min()		);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(	((ArbeitssystemHelikopterFliegen) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
	}

}
