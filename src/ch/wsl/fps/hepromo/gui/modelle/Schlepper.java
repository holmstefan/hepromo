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
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSchlepper;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Fahrentfernung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckeart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckebedingungen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.RueckenImSaft;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Schlagordnung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Zuzugentfernung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper;
import ch.wsl.fps.hepromo.model.modelle.ModelSchlepper;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Schlepper extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtMittlererStueckinhalt_m3;
	private JSpinner txtHolzmenge_m3;
	private JComboBox<Schlagordnung> cmbSchlagordnung;
	private JComboBox<AnzahlSortimente> cmbAnzahlSortimente;
	private JComboBox<Fahrentfernung> cmbMittlereFahrentfernung;
	private JComboBox<Zuzugentfernung> cmbMittlereZuzugentfernung;
	private JComboBox<Rueckeart> cmbRueckeArt;
	private JComboBox<RueckenImSaft> cmbRueckenImSaft;
	private JComboBox<Rueckebedingungen> cmbRueckebedingungen;
	
	private KostensaetzePanelSchlepper panelKostensaetze;	
	private ArbeitswegePausenPanel panelArbeitswegePausen;	
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelSchlepper model = new ModelSchlepper(); 
	
	
	
	public Schlepper() {
		super.setTitle("Rücken mit Schlepper");
		super.setSize((int) (590 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (620 * MainWindow.SIZE));
		
		super.initalize();
	}
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblMittlererStueckInhalt = new JLabel("<html>" + "Mittlerer Stückinhalt" + " (m<sup>3</sup>)</html>");   
		panel.add(lblMittlererStueckInhalt, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlererStueckinhalt_m3 = new JSpinner();
		panel.add(txtMittlererStueckinhalt_m3, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblHolzmenge = new JLabel("<html>" + "Holzmenge" + " (m<sup>3</sup>)");   
		panel.add(lblHolzmenge, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHolzmenge_m3 = new JSpinner();
		panel.add(txtHolzmenge_m3, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblSchlagordnung = new JLabel("Schlagordnung bei Stammholz < 10m Länge bei:"); 
		panel.add(lblSchlagordnung, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,15,5,0);
		cmbSchlagordnung = new JComboBox<>();
		fillComboBoxWithEnum(cmbSchlagordnung, Schlagordnung.values());
		panel.add(cmbSchlagordnung, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblAnzahlSortimente = new JLabel("Anzahl Sortimente"); 
		panel.add(lblAnzahlSortimente, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbAnzahlSortimente = new JComboBox<>();
		fillComboBoxWithEnum(cmbAnzahlSortimente, AnzahlSortimente.values());
		panel.add(cmbAnzahlSortimente, c);
		
		

		
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
		
		
		
		

		

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblFahrentfernung = new JLabel("Fahrentfernung mittel"); 
		panel.add(lblFahrentfernung, c);
		

        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbMittlereFahrentfernung = new JComboBox<>();
		fillComboBoxWithEnum(cmbMittlereFahrentfernung, Fahrentfernung.values());
		panel.add(cmbMittlereFahrentfernung, c);
		

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblZuzugentfernung = new JLabel("Zuzugentfernung mittel"); 
		panel.add(lblZuzugentfernung, c);
		

        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbMittlereZuzugentfernung = new JComboBox<>();
		fillComboBoxWithEnum(cmbMittlereZuzugentfernung, Zuzugentfernung.values());
		panel.add(cmbMittlereZuzugentfernung, c);
		

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblRueckeart = new JLabel("Rückeart"); 
		panel.add(lblRueckeart, c);
		

        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbRueckeArt = new JComboBox<>();
		fillComboBoxWithEnum(cmbRueckeArt, Rueckeart.values());
		panel.add(cmbRueckeArt, c);
		

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblRueckenImSaft = new JLabel("Rücken im Saft"); 
		panel.add(lblRueckenImSaft, c);
		

        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbRueckenImSaft = new JComboBox<>();
		fillComboBoxWithEnum(cmbRueckenImSaft, RueckenImSaft.values());
		panel.add(cmbRueckenImSaft, c);
		

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblRueckebedingungen = new JLabel("Rückebedingungen"); 
		panel.add(lblRueckebedingungen, c);
		

        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbRueckebedingungen = new JComboBox<>();
		fillComboBoxWithEnum(cmbRueckebedingungen, Rueckebedingungen.values());
		panel.add(cmbRueckebedingungen, c);
		

        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 5;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblRueckebedingungen_Info = GuiStrings.getInfoButtonBlue("<html>Geländehindernisse, Erschliessung,<br>Witterung, Poltermöglichkeiten, ...)</html>"); 
		panel.add(lblRueckebedingungen_Info, c);
		
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}

	@Override
	protected void initPanelArbeitssystem(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(0,0,10,0);
		panelKostensaetze = new KostensaetzePanelSchlepper(this);
		panel.add(panelKostensaetze, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);
		
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setMittlererStueckInhalt(		(Double) txtMittlererStueckinhalt_m3.getValue()				);
		model.getArbeitsobjekt().setHolzmenge_m3(				(Double) txtHolzmenge_m3.getValue()							);
		model.getArbeitsobjekt().setSchlagordnung(				(Schlagordnung) 	cmbSchlagordnung.getSelectedItem()		);
		model.getArbeitsobjekt().setAnzahlSortimente(			(AnzahlSortimente) 	cmbAnzahlSortimente.getSelectedItem()	);
		model.getArbeitsobjekt().setFahrentfernung(				(Fahrentfernung) 	cmbMittlereFahrentfernung.getSelectedItem()		);
		model.getArbeitsobjekt().setZuzugentfernung(			(Zuzugentfernung) 	cmbMittlereZuzugentfernung.getSelectedItem()	);
		model.getArbeitsobjekt().setRueckeart(					(Rueckeart) 		cmbRueckeArt.getSelectedItem()			);
		model.getArbeitsobjekt().setRueckenImSaft(				(RueckenImSaft) 	cmbRueckenImSaft.getSelectedItem()		);
		model.getArbeitsobjekt().setRueckeBedingungen(			(Rueckebedingungen) cmbRueckebedingungen.getSelectedItem()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzMaschinist()				);
		model.getArbeitssystem().setRueckegehilfe(				panelKostensaetze.hasRueckegehilfe()				);
		model.getArbeitssystem().setKostensatzRueckegehilfe(	panelKostensaetze.getAnsatzRueckegehilfe()			);
		model.getArbeitssystem().setRueckgehilfeEinsatzanteil(	panelKostensaetze.getRueckegehilfeEinsatzanteil()	);
		model.getArbeitssystem().setKostensatzMaschine1_proH(		panelKostensaetze.getAnsatzSchlepper()				);
		model.getArbeitssystem().setSchlepperTyp(				panelKostensaetze.getSchlepperTyp()					);
		
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min(		panelArbeitswegePausen.getWegzeitenUndPausen_min()	);
		
		model.getArbeitssystem().setUmsetzenBetrag_CHF(			panelWeitereAufwaende.getUmsetzenBetrag_CHF()		);
		model.getArbeitssystem().setUmsetzenZeit_h(				panelWeitereAufwaende.getUmsetzenZeit_h()			);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF());
		model.getArbeitssystem().setWeitereAufwaendeZeit_h(		panelWeitereAufwaende.getWeitereAufwaendeZeit_h()	);
	}


	@Override
	public ModelSchlepper getModel() {
		return (ModelSchlepper) model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		txtMittlererStueckinhalt_m3.setValue(		((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getMittlererStueckInhalt()	);
		txtHolzmenge_m3.setValue(					((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getHolzmenge_m3()			);
		cmbSchlagordnung.setSelectedItem(			((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getSchlagordnung()			);
		cmbAnzahlSortimente.setSelectedItem(		((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getAnzahlSortimente()		);
		cmbMittlereFahrentfernung.setSelectedItem(	((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getFahrentfernung()		);
		cmbMittlereZuzugentfernung.setSelectedItem(	((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getZuzugentfernung()		);
		cmbRueckeArt.setSelectedItem(				((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getRueckeart()				);
		cmbRueckenImSaft.setSelectedItem(			((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getRueckenImSaft()			);
		cmbRueckebedingungen.setSelectedItem(		((ArbeitsobjektSchlepper) data.getArbeitsobjekt()).getRueckeBedingungen()		);	
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzMaschinist(				((ArbeitssystemSchlepper) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelKostensaetze.setRueckegehilfe(					((ArbeitssystemSchlepper) data.getArbeitssystem()).isRueckegehilfe()				);
		panelKostensaetze.setAnsatzRueckegehilfe(			((ArbeitssystemSchlepper) data.getArbeitssystem()).getKostensatzRueckegehilfe()	);
		panelKostensaetze.setRueckegehilfeEinsatzanteil(	((ArbeitssystemSchlepper) data.getArbeitssystem()).getRueckgehilfeEinsatzanteil()	);
		panelKostensaetze.setAnsatzSchlepper(				((ArbeitssystemSchlepper) data.getArbeitssystem()).getKostensatzMaschine1_proH()		);
		panelKostensaetze.setSchlepperTyp(					((ArbeitssystemSchlepper) data.getArbeitssystem()).getSchlepperTyp()				);
		
		panelArbeitswegePausen.setArbeitszeit_min(			data.getArbeitssystem().getTaeglicheArbeitszeit_Min()	);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	data.getArbeitssystem().getWegzeitenUndPausen_Min()		);
		
		panelWeitereAufwaende.setUmsetzenBetrag_CHF(		data.getArbeitssystem().getUmsetzenBetrag_CHF()			);
		panelWeitereAufwaende.setUmsetzenZeit_h(			data.getArbeitssystem().getUmsetzenZeit_h()				);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(data.getArbeitssystem().getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(	data.getArbeitssystem().getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}
	
	
	private <T extends Enum<T>>void fillComboBoxWithEnum(JComboBox<T> cmbTarget, T[] enumSource) {
		for (int i=0; i<enumSource.length; i++) {
			cmbTarget.addItem(enumSource[i]);
		}
		addDefaultActionListener(cmbTarget);
	}


	@Override
	protected void initData() {
		txtMittlererStueckinhalt_m3.setModel(new SpinnerNumberModel(1.0, 0, 100, 1));
		txtMittlererStueckinhalt_m3.setEditor(new JSpinner.NumberEditor(txtMittlererStueckinhalt_m3, "0.##")); 
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlererStueckinhalt_m3);
		
		txtHolzmenge_m3.setModel(new SpinnerNumberModel(200.0, 0, 100000, 1));
		txtHolzmenge_m3.setEditor(new JSpinner.NumberEditor(txtHolzmenge_m3, "0.##")); 
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3);
		
		((ErgebnisPanel) ergebnisPanel).setLabelUmsetzen("Schlepper Umsetzen"); 
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelKostensaetze.onInputChangedBeforeCalculation(eventSource);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}

}
