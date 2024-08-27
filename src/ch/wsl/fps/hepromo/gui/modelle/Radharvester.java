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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelRadharvester;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.RadharvesterPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester;
import ch.wsl.fps.hepromo.model.modelle.ModelRadharvester;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Radharvester extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelRadharvester panelBestand;
	private JPanel panelHinweis;
	private JTextField txtMaxFaellDrm;
	
	private KostensaetzePanel panelKostensaetze;
	private RadharvesterPanel panelRadharvester;
	private ArbeitswegePausenPanel panelArbeitswegePausen;
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelRadharvester model = new ModelRadharvester();
	
	
	
	public Radharvester() {		
		super.setTitle("Radharvester");
		super.setSize((int) (600 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (605 * MainWindow.SIZE));
		
		super.initalize();
	}

	
	@Override
	public void validate() {
		if (txtMaxFaellDrm != null) {
			txtMaxFaellDrm.setText("" + panelRadharvester.getMaschinenTyp().getMaxFaellDrm_cm());			 
		}
		super.validate();
	}

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Bestand
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelRadharvester(this);
		panel.add(panelBestand, c);
		
		//panel Hinweis
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelHinweis = initPanelHinweis();
		panel.add(panelHinweis, c);
		
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
	protected ErgebnisPanel initErgebnisPanel() {
		ErgebnisPanel result = new ErgebnisPanel.Builder()
				.enableRowPersonal()
				.enableRowMaschine1()
				.enableRowUmsetzen()
				.enableRowProduktivitaet()
				.enableColumnProM3()
				.build();
		result.hideDauerDerArbeit();
		return result;
	}
	
	
	
	private JPanel initPanelHinweis() {
		JPanel panel = new JPanel();
		panel.setBorder(TitledBorderFactory.createTitledBorder("Hinweis"));		 
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Hinweistext
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel txtHinweis = new JLabel();
		txtHinweis.setText("<html>Achtung: Maximaler Fälldurchmesser (cm) abhängig vom gewählten Maschinentyp (siehe unter 'Arbeitssystem')</html>"); 
		panel.add(txtHinweis, c);
		
		//Max. Fälldurchmesser
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMaxFaellDrm = new JTextField();
		txtMaxFaellDrm.setEditable(false);
		panel.add(txtMaxFaellDrm, c);	
		
		
		return panel;
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
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanel(this);
		panel.add(panelKostensaetze, c);
		
		//panel Radharvester
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelRadharvester = new RadharvesterPanel(this);
		panel.add(panelRadharvester, c);
		
		//panel Arbeitswege / Pausen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
//		panel.add(new JPanel(), c);
		
		//panel Weiteres
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);
	}

	@Override
	protected void initData() {
		panelKostensaetze.setLabelPersonal("Maschinist"); 
		panelKostensaetze.setAnsatzPersonal(60);
		panelKostensaetze.setLabelMaschine("Radharvester"); 
		panelKostensaetze.setAnsatzMaschine(370);
	}	
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelRadharvester.onInputChangedBeforeCalculation(eventSource);
	}
	
	
	
	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setBaumartGruppe(			panelBestand.getBaumartengruppe()		);
		model.getArbeitsobjekt().setHolzmenge_m3(			panelBestand.getHolzmenge_m3iR()		);
		model.getArbeitsobjekt().setVolumenMittelstamm_m3(	panelBestand.getVolumenmittelstamm_m3()	);	
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(	 	panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(  		panelKostensaetze.getAnsatzMaschine()				);

		model.getArbeitssystem().setMaschinenKategorie( 		panelRadharvester.getMaschinenKategorie()			);
		model.getArbeitssystem().setMaschinenTyp( 				panelRadharvester.getMaschinenTyp() 				);
		
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()	);
		
		model.getArbeitssystem().setUmsetzenBetrag_CHF(	 		panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h(			 	panelWeitereAufwaende.getUmsetzenZeit_h()				);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF( panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}

	
	
	@Override
	public ModelRadharvester getModel() {
		return (ModelRadharvester) model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelBestand.setBaumartengruppe(		((ArbeitsobjektRadharvester) data.getArbeitsobjekt()).getBaumartGruppe()		);
		panelBestand.setHolzmenge_m3iR(			((ArbeitsobjektRadharvester) data.getArbeitsobjekt()).getHolzmenge_m3()			);
		panelBestand.setVolumenmittelstamm_m3(	((ArbeitsobjektRadharvester) data.getArbeitsobjekt()).getVolumenMittelstamm_m3());	
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				data.getArbeitssystem().getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnsatzMaschine(				data.getArbeitssystem().getKostensatzMaschine1_proH()		);

		panelRadharvester.setMaschinenKategorie(			((ArbeitssystemRadharvester) data.getArbeitssystem()).getMaschinenKategorie());
		panelRadharvester.setMaschinenTyp(					((ArbeitssystemRadharvester) data.getArbeitssystem()).getMaschinenTyp()		);
		
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
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}

}
