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
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelKonventionellerSeilkranSeilen;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.GelaendePanelKonvSeilkranSeilen;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.SeilliniePanelKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.AnzahlPersonenPanelSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KonventionellerSeilkranGesamt extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelKonventionellerSeilkranSeilen panelBestand;
	private GelaendePanelKonvSeilkranSeilen panelGelaende;
	private SeilliniePanelKonventionellerSeilkranGesamt panelSeilLinie;
	
	private KostensaetzePanelSeilkranGesamt panelKostensaetze;
	private AnzahlPersonenPanelSeilkranGesamt panelAnzahlPersonen;	
	private ArbeitswegePausenPanel panelArbeitswegePausen;	
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelKonventionellerSeilkranGesamt model = new ModelKonventionellerSeilkranGesamt();
	
	
	
	public KonventionellerSeilkranGesamt() {
		super.setTitle(GuiStrings.getString("KonventionellerSeilkranGesamt.Title")); //$NON-NLS-1$
		super.setSize((int) (715 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (760 * MainWindow.SIZE));
		
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
		c.gridheight = 2;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelKonventionellerSeilkranSeilen(this);
		panel.add(panelBestand, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelGelaende = new GelaendePanelKonvSeilkranSeilen(this);
		panel.add(panelGelaende, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 400;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
		
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelSeilLinie = new SeilliniePanelKonventionellerSeilkranGesamt(this);
		panel.add(panelSeilLinie, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 400;
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
		
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelSeilkranGesamt(this);
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
		model.getArbeitsobjekt().setMittleresStueckvolumen_m3(	panelBestand.getMittleresStueckvolumen_m3()	);
		model.getArbeitsobjekt().setHolzmenge_m3(				panelBestand.getHolzmengeAnSeillinie_m3iR()	);
		model.getArbeitsobjekt().setStueckLaenge_m(				panelBestand.getStuecklaenge_m()			);
		model.getArbeitsobjekt().setEingriffsart(				panelBestand.getEingriffsart()				);
		model.getArbeitsobjekt().setHolzseilort(				panelBestand.getHolzSeilOrt()				);
		
		model.getArbeitsobjekt().setHangneigung_Prz(			panelGelaende.getHangneigung_Prz()			);
		model.getArbeitsobjekt().setHindernisse(				panelGelaende.getHindernisse()				);
		
		model.getArbeitsobjekt().setLinienLaenge_m(				panelSeilLinie.getLinienlaenge_m()				);
		model.getArbeitsobjekt().setFahrtrichtung(				panelSeilLinie.getFahrtrichtung()				);
		model.getArbeitsobjekt().setMittlereFahrdistanz_m(		panelSeilLinie.getMittlereFahrdistanz_m()		);
		model.getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(	panelSeilLinie.getMittlereDistanzSeitlicherZuzug()	);
		
		model.getArbeitsobjekt().setAnzahlStuetzen(				panelSeilLinie.getAnzahlStuetzen()				);
		model.getArbeitsobjekt().setAnzahlEndmasten(			panelSeilLinie.getAnzahlEndmasten()				);
		model.getArbeitsobjekt().setTragseilhoeheBestand_m(		panelSeilLinie.getTragseilHoeheBestand_m()		);
		model.getArbeitsobjekt().setTragseilhoeheLagerplatz_m(	panelSeilLinie.getTragseilHoeheLagerplatz_m()	);
		
		model.getArbeitsobjekt().setMontageIstSeilverlegung(				panelSeilLinie.isMontageSeilverlegung()					);
		model.getArbeitsobjekt().setMontageWindenTransportart(				panelSeilLinie.getMontageWindenTransport()				);
		model.getArbeitsobjekt().setMontageWindenStandort(					panelSeilLinie.getMontageWindenStandort()				);
		model.getArbeitsobjekt().setMontageDistanzWindenselbstfahrt_m(		panelSeilLinie.getMontageDistanzWindenselbstfahrt_m()	);
		model.getArbeitsobjekt().setDemontageIstSeilverlegung(				panelSeilLinie.isDemontageSeilverlegung()				);
		model.getArbeitsobjekt().setDemontageWindenTransportart(			panelSeilLinie.getDemontageWindenTransport()			);
		model.getArbeitsobjekt().setDemontageWindenStandort(				panelSeilLinie.getDemontageWindenStandort()				);
		model.getArbeitsobjekt().setDemontageDistanzWindenselbstfahrt_m(	panelSeilLinie.getDemontageDistanzWindenselbstfahrt_m()	);
		
		
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
	public ModelKonventionellerSeilkranGesamt getModel() {
		return (ModelKonventionellerSeilkranGesamt) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelBestand.setMittleresStueckvolumen_m3(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getMittleresStueckvolumen_m3()			);
		panelBestand.setHolzmengeAnSeillinie_m3iR(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getHolzmenge_m3()						);
		panelBestand.setStuecklaenge_m(					  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getStueckLaenge_m()					);
		panelBestand.setEingriffsart(					  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getEingriffsart()						);
		panelBestand.setHolzSeilOrt(					  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getHolzseilort()						);
		
		panelGelaende.setHangneigung_Prz(				  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getHangneigung_Prz()					);
		panelGelaende.setHindernisse(					  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getHindernisse()						);
		
		panelSeilLinie.setLinienlaenge_m(				  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getLinienLaenge_m()					);
		panelSeilLinie.setFahrtrichtung(				  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getFahrtrichtung()						);
		panelSeilLinie.setMittlereFahrdistanz_m(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getMittlereFahrdistanz_m()				);
		panelSeilLinie.setMittlereDistanzSeitlicherZuzug(((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getMittlereDistanzSeitlicherZuzug_m()	);
		
		panelSeilLinie.setAnzahlStuetzen(				  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getAnzahlStuezten()					);
		panelSeilLinie.setAnzahlEndmasten(				  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getAnzahlEndmasten()					);
		panelSeilLinie.setTragseilHoeheBestand_m(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getTragseilhoeheBestand_m()			);
		panelSeilLinie.setTragseilHoeheLagerplatz_m(	  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getTragseilhoeheLagerplatz_m()			);
		
		panelSeilLinie.setMontageSeilverlegung(			  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).isMontageIstSeilverlegung()			);
		panelSeilLinie.setMontageWindenTransport(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getMontageWindenTransportart()			);
		panelSeilLinie.setMontageWindenStandort(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getMontageWindenStandort()				);
		panelSeilLinie.setMontageDistanzWindenselbstfahrt_m(	((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getMontageDistanzWindenselbstfahrt_m());
		panelSeilLinie.setDemontageSeilverlegung(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).isDemontageIstSeilverlegung()			);
		panelSeilLinie.setDemontageWindenTransport(	  	  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getDemontageWindenTransportart()		);
		panelSeilLinie.setDemontageWindenStandort(		  ((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getDemontageWindenStandort()			);
		panelSeilLinie.setDemontageDistanzWindenselbstfahrt_m(	((ArbeitsobjektKonventionellerSeilkranGesamt) data.getArbeitsobjekt()).getDemontageDistanzWindenselbstfahrt_m());
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getKostensatzPersonal1_proH()				);
		panelKostensaetze.setAnsatzSeilkran(				((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getKostensatzMaschine1_proH()				);
		panelKostensaetze.setAnsatzKranfahrzeug(			((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getKostensatzMaschine2_proH()			);
		panelKostensaetze.setLaufzeitKranfahrzeug_Prz(		((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getLaufzeitKranfahrzeug_Prz()			);
		
		panelAnzahlPersonen.setAnzahlPersonenInstallation(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getAnzahlPersonenInstallation()			);
		panelAnzahlPersonen.setAnzahlPersonenSeilen(		((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getAnzahlPersonenSeilen()				);
		panelAnzahlPersonen.setAnzahlPersonenLagerplatz(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getAnzahlPersonenLagerplatz()			);
		panelAnzahlPersonen.setEinsatzzeitAnteilLagerplatz(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getEinsatzzeitPersonenLagerplatz_Prz()	);
		
		panelArbeitswegePausen.setArbeitszeit_min(			((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()			);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getWegzeitenUndPausen_Min()				);

		panelWeitereAufwaende.setUmsetzenBetrag_CHF(			((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getUmsetzenBetrag_CHF()					);
		panelWeitereAufwaende.setUmsetzenZeit_h(				((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getUmsetzenZeit_h()						);
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()			);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		((ArbeitssystemSeilkranGesamt) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()				);
		
		//Faktoren
		loadFaktoren(data);
		
		
		onInputChangedBeforeCalculation(null);
		
		
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
		panelSeilLinie.onInputChangedBeforeCalculation(eventSource);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
	
}
