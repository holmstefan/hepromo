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
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelMobilseilkranSeilen;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.SeilkranInstallationPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.AnzahlPersonenPanelSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranGesamt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MobilseilkranGesamt extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelMobilseilkranSeilen panelBestand;
	private SeilkranInstallationPanel panelSeilLinie;
	
	private KostensaetzePanelSeilkranGesamt panelKostensaetze;
	private AnzahlPersonenPanelSeilkranGesamt panelAnzahlPersonen;	
	private ArbeitswegePausenPanel panelArbeitswegePausen;	
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelMobilseilkranGesamt model = new ModelMobilseilkranGesamt();
	
	
	
	public MobilseilkranGesamt() {
		super.setTitle(GuiStrings.getString("MobilseilkranGesamt.Title")); //$NON-NLS-1$
		super.setSize((int) (730 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (645 * MainWindow.SIZE));
		
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
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelMobilseilkranSeilen(this);
		panel.add(panelBestand, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelSeilLinie = new SeilkranInstallationPanel(this);
		panel.add(panelSeilLinie, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 400;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
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
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelSeilkranGesamt(this);
		panelKostensaetze.setLabelSeilkran(GuiStrings.getString("MobilseilkranGesamt.LabelKostensatz")); //$NON-NLS-1$);
		panel.add(panelKostensaetze, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelAnzahlPersonen = new AnzahlPersonenPanelSeilkranGesamt(this);
		panel.add(panelAnzahlPersonen, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this);
		panel.add(panelWeitereAufwaende, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 400;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
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
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		panelBestand.updateLabelHolzmengeMitRindenabzug(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setMittleresStueckvolumen_m3(			panelBestand.getMittleresStueckvolumen_m3()			);
		model.getArbeitsobjekt().setHolzmenge_m3(						panelBestand.getNutzungsmenge_m3iR()				);
		model.getArbeitsobjekt().setMittlereFahrdistanz_m(				panelBestand.getMittlereFahrdistanz_m()				);
		model.getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(	panelBestand.getMittlereDistanzSeitlicherZuzug_m()	);
		model.getArbeitsobjekt().setSchwierigkeitSeitlicherZuzug(		panelBestand.getSchwierigkeitSeitlicherZuzug()		);
		
		model.getArbeitsobjekt().setSeilsystem(					panelSeilLinie.getSeilsystem()				);
		model.getArbeitsobjekt().setMaschinenStandort(			panelSeilLinie.getMaschinenStandort()		);
		model.getArbeitsobjekt().setLinienLaenge_m(				panelSeilLinie.getLinienLaenge_m()			);
		model.getArbeitsobjekt().setStuetzenTragseilHoehen(		panelSeilLinie.getStuetzenTragseilHoehen_m());
		model.getArbeitsobjekt().setEndmast(					panelSeilLinie.isEndmast()					);
		model.getArbeitsobjekt().setTragseilHoeheEndmast(		panelSeilLinie.getEndmastTragseilHoehe_m()	);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(	panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(	panelKostensaetze.getAnsatzSeilkran()				);
		model.getArbeitssystem().setKostensatzMaschine2_proH(	panelKostensaetze.getAnsatzKranfahrzeug()			);
		model.getArbeitssystem().setLaufzeitKranfahrzeug_Prz(	panelKostensaetze.getLaufzeitKranfahrzeug_Prz()		);
		
		model.getArbeitssystem().setAnzahlPersonenInstallation(	panelAnzahlPersonen.getAnzahlPersonenInstallation()	);
		model.getArbeitssystem().setAnzahlPersonenSeilen(		panelAnzahlPersonen.getAnzahlPersonenSeilen()		);
		model.getArbeitssystem().setAnzahlPersonenLagerplatz(	panelAnzahlPersonen.getAnzahlPersonenLagerplatz()	);
		model.getArbeitssystem().setEinsatzzeitPersonenLagerplatz_Prz(panelAnzahlPersonen.getEinsatzzeitAnteilLagerplatz());
		
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min(		panelArbeitswegePausen.getWegzeitenUndPausen_min()	);

		model.getArbeitssystem().setUmsetzenBetrag_CHF(			panelWeitereAufwaende.getUmsetzenBetrag_CHF()		);
		model.getArbeitssystem().setUmsetzenZeit_h(				panelWeitereAufwaende.getUmsetzenZeit_h()			);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF());
		model.getArbeitssystem().setWeitereAufwaendeZeit_h(		panelWeitereAufwaende.getWeitereAufwaendeZeit_h()	);
	}


	@Override
	public ModelMobilseilkranGesamt getModel() {
		return (ModelMobilseilkranGesamt) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelBestand.setMittleresStueckvolumen_m3(			((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getMittleresStueckvolumen_m3()	);
		panelBestand.setNutzungsmenge_m3iR(					((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getHolzmenge_m3()				);
		panelBestand.setMittlereFahrdistanz_m(				((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getMittlereFahrdistanz_m()		);
		panelBestand.setMittlereDistanzSeitlicherZuzug_m(	((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getMittlereDistanzSeitlicherZuzug_m());
		panelBestand.setSchwierigkeitSeitlicherZuzug(		((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getSchwierigkeitSeitlicherZuzug());
		
		panelSeilLinie.setSeilsystem(						((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getSeilsystem()				);
		panelSeilLinie.setMaschinenStandort(				((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getMaschinenStandort()			);
		panelSeilLinie.setLinienLaenge_m(					((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getLinienLaenge_m()			);
		panelSeilLinie.setStuetzenTragseilHoehen_m(			((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getStuetzenTragseilHoehen()	);
		panelSeilLinie.setEndmast(							((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).isEndmast()					);
		panelSeilLinie.setEndmastTragseilHoehe_m(			((ArbeitsobjektMobilseilkranGesamt) data.getArbeitsobjekt()).getTragseilHoeheEndmast()		);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnsatzSeilkran(				((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getKostensatzMaschine1_proH()		);
		panelKostensaetze.setAnsatzKranfahrzeug(			((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getKostensatzMaschine2_proH()		);
		panelKostensaetze.setLaufzeitKranfahrzeug_Prz(		((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getLaufzeitKranfahrzeug_Prz()	);
		
		panelAnzahlPersonen.setAnzahlPersonenInstallation(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getAnzahlPersonenInstallation());
		panelAnzahlPersonen.setAnzahlPersonenSeilen(		((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getAnzahlPersonenSeilen()		);
		panelAnzahlPersonen.setAnzahlPersonenLagerplatz(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getAnzahlPersonenLagerplatz()	);
		panelAnzahlPersonen.setEinsatzzeitAnteilLagerplatz(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getEinsatzzeitPersonenLagerplatz_Prz());
		
		panelArbeitswegePausen.setArbeitszeit_min(			((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()	);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getWegzeitenUndPausen_Min()	);

		panelWeitereAufwaende.setUmsetzenBetrag_CHF(			((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getUmsetzenBetrag_CHF()		);
		panelWeitereAufwaende.setUmsetzenZeit_h(				((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getUmsetzenZeit_h()			);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF());
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()	);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}


	@Override
	protected void initData() {
		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet(GuiStrings.getString("SeilkranGesamt.ProduktivitaetBeimSeilen_m3iRProPMH15")); //$NON-NLS-1$
		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet2(GuiStrings.getString("SeilkranGesamt.m3oRProPMH15")); //$NON-NLS-1$
		((ErgebnisPanel) ergebnisPanel).setLabelKostenProM3(GuiStrings.getString("ErgebnisPanel.pro_m3_oR")); //$NON-NLS-1$
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelSeilLinie.onInputChangeBeforeCalculation(eventSource);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
	
}
