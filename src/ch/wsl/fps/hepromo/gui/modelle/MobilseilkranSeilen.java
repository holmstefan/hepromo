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

import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelMobilseilkranSeilen;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSeilkran;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkran;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranSeilen;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public class MobilseilkranSeilen extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelMobilseilkranSeilen panelBestand;
	
	private KostensaetzePanelSeilkran panelKostensaetze;	
	private ArbeitswegePausenPanel panelArbeitswegePausen;	
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelMobilseilkranSeilen model = new ModelMobilseilkranSeilen();
	
	
	
	public MobilseilkranSeilen() {
		super.setTitle("Mobilseilkran Seilen");
		super.setSize(600, 585);
		
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
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelMobilseilkranSeilen(this);
		panel.add(panelBestand, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
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
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,0,0,0);
		panelKostensaetze = new KostensaetzePanelSeilkran(this);
		panel.add(panelKostensaetze, c);
		

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 2;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this, true);
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
		return new ErgebnisPanel(true, false, false, true, false, true);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		panelBestand.updateLabelHolzmengeMitRindenabzug(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setMittleresStueckvolumen_m3(			panelBestand.getMittleresStueckvolumen_m3()			);
		model.getArbeitsobjekt().setHolzmenge_m3(						panelBestand.getNutzungsmenge_m3iR()					);
		model.getArbeitsobjekt().setMittlereFahrdistanz_m(				panelBestand.getMittlereFahrdistanz_m()				);
		model.getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(	panelBestand.getMittlereDistanzSeitlicherZuzug_m()	);
		model.getArbeitsobjekt().setSchwierigkeitSeitlicherZuzug(		panelBestand.getSchwierigkeitSeitlicherZuzug()		);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setAnzahlPersonal(				panelKostensaetze.getAnzahlPersonal()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(		panelKostensaetze.getAnsatzSeilkran()				);
		model.getArbeitssystem().setAnteilSeilkranLaufzeit_Prz(	panelKostensaetze.getLaufzeitSeilkran_Prz()			);
		
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min(		panelArbeitswegePausen.getWegzeitenUndPausen_min()	);
		
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF());
		model.getArbeitssystem().setWeitereAufwaendeZeit_h(		panelWeitereAufwaende.getWeitereAufwaendeZeit_h()	);
	}


	@Override
	public ModelMobilseilkranSeilen getModel() {
		return (ModelMobilseilkranSeilen) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelBestand.setMittleresStueckvolumen_m3(				  ((ArbeitsobjektMobilseilkranSeilen) data.getArbeitsobjekt()).getMittleresStueckvolumen_m3()			);
		panelBestand.setNutzungsmenge_m3iR(						  ((ArbeitsobjektMobilseilkranSeilen) data.getArbeitsobjekt()).getHolzmenge_m3()						);
		panelBestand.setMittlereFahrdistanz_m(					  ((ArbeitsobjektMobilseilkranSeilen) data.getArbeitsobjekt()).getMittlereFahrdistanz_m()				);
		panelBestand.setMittlereDistanzSeitlicherZuzug_m(	      ((ArbeitsobjektMobilseilkranSeilen) data.getArbeitsobjekt()).getMittlereDistanzSeitlicherZuzug_m()	);
		panelBestand.setSchwierigkeitSeitlicherZuzug(			  ((ArbeitsobjektMobilseilkranSeilen) data.getArbeitsobjekt()).getSchwierigkeitSeitlicherZuzug()		);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(					  ((ArbeitssystemSeilkran) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnzahlPersonal(				(int) ((ArbeitssystemSeilkran) data.getArbeitssystem()).getAnzahlPersonal()				);
		panelKostensaetze.setAnsatzSeilkran(					  ((ArbeitssystemSeilkran) data.getArbeitssystem()).getKostensatzMaschine1_proH()		);
		panelKostensaetze.setLaufzeitSeilkran_Prz(			(int) ((ArbeitssystemSeilkran) data.getArbeitssystem()).getAnteilSeilkranLaufzeit_Prz()	);
		
		panelArbeitswegePausen.setArbeitszeit_min(				  ((ArbeitssystemSeilkran) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()		);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(		  ((ArbeitssystemSeilkran) data.getArbeitssystem()).getWegzeitenUndPausen_Min()		);
		
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	  ((ArbeitssystemSeilkran) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF()	);
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		  ((ArbeitssystemSeilkran) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()		);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}


	@Override
	protected void initData() {
		//nothing to initialize
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
	
}
