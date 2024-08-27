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

import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.ErschliessungForwarderPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.NutzungPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ForwarderPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Forwarder extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private NutzungPanel panelNutzung;
	private ErschliessungForwarderPanel panelErschliessung;
	
	
	private KostensaetzePanel panelKostensaetze;
	private ForwarderPanel panelForwarder;
	private ArbeitswegePausenPanel panelArbeitswegePausen;
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelForwarder model = new ModelForwarder();

	
	public Forwarder() {
		super.setTitle(GuiStrings.getString("Forwarder.Title")); //$NON-NLS-1$
		super.setSize((int) (690 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (768 * MainWindow.SIZE));
		
		super.initalize();
	}
	
	
	
	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Nutzung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelNutzung = new NutzungPanel(this);
		panel.add(panelNutzung, c);
		
		//panel Erschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelErschliessung = new ErschliessungForwarderPanel(this);
		panel.add(panelErschliessung, c);
		
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
		
		//panel Forwarder
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelForwarder = new ForwarderPanel(this);
		panel.add(panelForwarder, c);
		
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
		return new ErgebnisPanel.Builder()
				.enableRowPersonal()
				.enableRowMaschine1()
				.enableRowUmsetzen()
				.enableRowProduktivitaet()
				.enableColumnProM3()
				.showHintProduktivitaet()
				.showHintKostenProM3()
				.build();
	}

	
	@Override
	protected void initData() {
		panelKostensaetze.setLabelPersonal(GuiStrings.getString("Forwarder.Maschinist")); //$NON-NLS-1$
		panelKostensaetze.setAnsatzPersonal(60);
		panelKostensaetze.setLabelMaschine(GuiStrings.getString("Forwarder.Forwarder")); //$NON-NLS-1$
		panelKostensaetze.setAnsatzMaschine(110);

		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet(GuiStrings.getString("Forwarder.Produktivitaet_m3_pro_PMH15")); //$NON-NLS-1$
		((ErgebnisPanel) ergebnisPanel).setLabelUmsetzen(GuiStrings.getString("Forwarder.ForwarderUmsetzen")); //$NON-NLS-1$
		((ErgebnisPanel) ergebnisPanel).setLabelKostenProM3(GuiStrings.getString("ErgebnisPanel.pro_m3_iR")); //$NON-NLS-1$
	}

	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelNutzung.onInputChangedBeforeCalculation(eventSource);
		panelForwarder.onInputChangedBeforeCalculation(eventSource);
		panelErschliessung.onInputChangedBeforeCalculation(eventSource);
	}
	
	
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		//Nutzung
		model.getArbeitsobjekt().setLaubholzAnteil_Prz(					panelNutzung.getLaubholzAnteil()					);
		model.getArbeitsobjekt().setHolzmenge_m3(						panelNutzung.getHolzmenge_m3()						);
		model.getArbeitsobjekt().setDurchschnittlicherBhdAushieb_cm(	panelNutzung.getDurchschnittlicherBhdAushieb_cm()	);
		model.getArbeitsobjekt().setDurchschnittlicheHolzlaenge_m(		panelNutzung.getDurchschnittlicheHolzlaenge_m()		);
		model.getArbeitsobjekt().setDurchschnittlicheAnzahlSortimente(	panelNutzung.getDurchschnittlicheAnzahlSortimente()	);
		model.getArbeitsobjekt().setAnzahlVerschiedeneSortimente(		panelNutzung.getAnzahlVerschiedeneSortimente()		);
		model.getArbeitsobjekt().setAnteilKrumm_Prz(					panelNutzung.getAnteilKrumm()						);
		
		//Erschliessung
		model.getArbeitsobjekt().setFahrstreckeAufStrasse(				panelErschliessung.getFahrstreckeAufStrasse()			);
		model.getArbeitsobjekt().setFahrstreckenArtStrasse(				panelErschliessung.getFahrstreckenArtStrasse()			);
		model.getArbeitsobjekt().setFahrstreckeAufFeinerschliessung(	panelErschliessung.getFahrstreckeAufFeinerschliessung()	);
		model.getArbeitsobjekt().setFahrstreckenArtFeinerschliessung(	panelErschliessung.getFahrstreckenArtFeinerschliessung());
		
		//Erschliessung Details
		model.getArbeitsobjekt().setErschliessungsLaengeEinseitig_m(					panelErschliessung.getErschliessungsLaengeEinseitig_m()					);
		model.getArbeitsobjekt().setErschliessungsLaengeEinseitigAnteilStrasse_Prz(		panelErschliessung.getErschliessungsLaengeEinseitigAnteilStrasse_Prz()	);
		model.getArbeitsobjekt().setErschliessungsLaengeBeidseitig_m(					panelErschliessung.getErschliessungsLaengeBeidseitig_m()				);
		model.getArbeitsobjekt().setErschliessungsLaengeBeidseitigAnteilStrasse_Prz(	panelErschliessung.getErschliessungsLaengeBeidseitigAnteilStrasse_Prz()	);
		model.getArbeitsobjekt().setHindernisKategorieFeinerschliessung(				panelErschliessung.getHindernisKategorieFeinerschliessung()				);
		model.getArbeitsobjekt().setNeigungsKategorieFeinerschliessung(					panelErschliessung.getNeigungsKategorieFeinerschliessung()				);
		
		//Fahrstreckenanteile
		model.getArbeitsobjekt().setFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz(			panelErschliessung.getFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz(				panelErschliessung.getFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz(			panelErschliessung.getFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz(				panelErschliessung.getFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz(panelErschliessung.getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz());
		model.getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz( panelErschliessung.getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz(	panelErschliessung.getFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz(	panelErschliessung.getFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz(	panelErschliessung.getFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz()	);
		model.getArbeitsobjekt().setFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz(	panelErschliessung.getFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(		panelKostensaetze.getAnsatzMaschine()				);

		model.getArbeitssystem().setForwarderTyp(			 	panelForwarder.getForwarderTyp()					);
		model.getArbeitssystem().setLadeQuerschnittsFlaeche_m2(	panelForwarder.getLadeQuerschnittsFlaeche_m2()		);

		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()	);

		model.getArbeitssystem().setUmsetzenBetrag_CHF(	 		panelWeitereAufwaende.getUmsetzenBetrag_CHF()		);
		model.getArbeitssystem().setUmsetzenZeit_h(		 		panelWeitereAufwaende.getUmsetzenZeit_h()			);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF());
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()	);
	}


	@Override
	public ModelForwarder getModel() {
		return (ModelForwarder) model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		//Nutzung
		panelNutzung.setLaubholzAnteil(					((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getLaubholzAnteil_Prz()					);
		panelNutzung.setHolzmenge_m3(					((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getHolzmenge_m3()						);
		panelNutzung.setDurchschnittlicherBhdAushieb_cm(((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getDurchschnittlicherBhdAushieb_cm()		);
		panelNutzung.setDurchschnittlicheHolzlaenge_m(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getDurchschnittlicheHolzlaenge_m()		);
		panelNutzung.setDurchschnittlicheAnzahlSortimente(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getDurchschnittlicheAnzahlSortimente());
		panelNutzung.setAnzahlVerschiedeneSortimente(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getAnzahlVerschiedeneSortimente()		);
		panelNutzung.setAnteilKrumm(					((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getAnteilKrumm_Prz()						);
		
		//Erschliessung
		panelErschliessung.setFahrstreckeAufStrasse(			((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckeAufStrasse()				);
		panelErschliessung.setFahrstreckenArtStrasse(			((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenArtStrasse()				);
		panelErschliessung.setFahrstreckeAufFeinerschliessung(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckeAufFeinerschliessung()		);
		panelErschliessung.setFahrstreckenArtFeinerschliessung(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenArtFeinerschliessung()	);
		
		//Erschliessung Details
		panelErschliessung.setErschliessungsLaengeEinseitig_m(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getErschliessungsLaengeEinseitig_m()		);
		panelErschliessung.setErschliessungsLaengeEinseitigAnteilStrasse_Prz(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getErschliessungsLaengeEinseitigAnteilStrasse_Prz());
		panelErschliessung.setErschliessungsLaengeBeidseitig_m(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getErschliessungsLaengeBeidseitig_m()	);
		panelErschliessung.setErschliessungsLaengeBeidseitigAnteilStrasse_Prz(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getErschliessungsLaengeBeidseitigAnteilStrasse_Prz());
		panelErschliessung.setHindernisKategorieFeinerschliessung(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getHindernisKategorieFeinerschliessung()	);
		panelErschliessung.setNeigungsKategorieFeinerschliessung(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getNeigungsKategorieFeinerschliessung()	);
		
		//Fahrstreckenanteile
		panelErschliessung.setFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz()	);
		panelErschliessung.setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz(	((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz()	);
		panelErschliessung.setFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz(			((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz(		((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz()		);
		panelErschliessung.setFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz(			((ArbeitsobjektForwarder) data.getArbeitsobjekt()).getFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz()		);
		
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				((ArbeitssystemForwarder) data.getArbeitssystem()).getKostensatzPersonal1_proH());
		panelKostensaetze.setAnsatzMaschine(				((ArbeitssystemForwarder) data.getArbeitssystem()).getKostensatzMaschine1_proH());

		panelForwarder.setForwarderTyp(						((ArbeitssystemForwarder) data.getArbeitssystem()).getForwarderTyp());
		panelForwarder.setLadeQuerschnittsFlaeche_m2(		((ArbeitssystemForwarder) data.getArbeitssystem()).getLadeQuerschnittsFlaeche_m2());
		
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
