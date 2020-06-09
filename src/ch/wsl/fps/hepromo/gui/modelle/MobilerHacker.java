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
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.AushiebUndHackgutPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.ErschliessungPanelMobilerHacker;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.MobilerHackerPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemMobilerHacker;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilerHacker;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MobilerHacker extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private AushiebUndHackgutPanel panelAushiebUndHackgut;
	private ErschliessungPanelMobilerHacker panelErschliessung;
	
	
	private KostensaetzePanel panelKostensaetze;
	private MobilerHackerPanel panelHacker;
	private ArbeitswegePausenPanel panelArbeitswegePausen;
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private ModelMobilerHacker model = new ModelMobilerHacker();
	
	

	
	public MobilerHacker() {		
		super.setTitle("Mobiler Hacker");
		super.setSize((int) (620 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (665 * MainWindow.SIZE));
		
		super.initalize();
	}
	
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Aushieb und Hackgut
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelAushiebUndHackgut = new AushiebUndHackgutPanel(this);
		panel.add(panelAushiebUndHackgut, c);
		
		//panel Erschliessung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelErschliessung = new ErschliessungPanelMobilerHacker(this);
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
		
		//panel Hacker
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelHacker = new MobilerHackerPanel(this);
		panel.add(panelHacker, c);
		
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
	protected void initData() {
		panelKostensaetze.setLabelPersonal("Maschinist"); //TODO(deprecated model): Labels können direkt aus Arbeitssystem ausgelesen werden 
		panelKostensaetze.setAnsatzPersonal(60);
		panelKostensaetze.setLabelMaschine("Mobiler Hacker"); 
		panelKostensaetze.setAnsatzMaschine(250);
		
		((ErgebnisPanel) ergebnisPanel).setLabelUmsetzen("Hacker Umsetzen"); 
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelHacker.onInputChangedBeforeCalculation(eventSource);
	}

	
	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setLaubholzAnteil_Prz(	panelAushiebUndHackgut.getLaubholzAnteil_Prz()		);
		model.getArbeitsobjekt().setAnzahlBaeume(		panelAushiebUndHackgut.getAnzahlBaeume()			);
		model.getArbeitsobjekt().setHackgutart(			panelAushiebUndHackgut.getHackgutart()				);
		
		model.getArbeitsobjekt().setMittlererBhdAushieb_m(	panelAushiebUndHackgut.getMittlererBhdAushieb_m()	);
		model.getArbeitsobjekt().setMittlererZopfDrm_m(		panelAushiebUndHackgut.getMittlererZopfDurchmesser_m());
		
		model.getArbeitsobjekt().setFahrstreckeStrasse_m(	panelErschliessung.getFahrstreckenAufStrasse_m()	);
		model.getArbeitsobjekt().setFahrstreckeFeinerschliessung_m(panelErschliessung.getFahrstreckenFeinerschliessung_m());
		model.getArbeitsobjekt().setHolzlagerLaenge_m(		panelErschliessung.getHolzlagerLaenge_m()			);
		
		model.getArbeitsobjekt().setHindernisKategorie(	panelErschliessung.getHindernisKategorie()			);
		model.getArbeitsobjekt().setNeigungsKategorie(	panelErschliessung.getNeigungsKategorie()			);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzPersonal()				);
		model.getArbeitssystem().setKostensatzMaschine1_proH(		panelKostensaetze.getAnsatzMaschine()				);

		model.getArbeitssystem().setHackerTyp(					panelHacker.getHackerTyp()							);
		model.getArbeitssystem().setKippContainerVolumen_m3(	panelHacker.getKippContainerVolumen_m3()			);

		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()			);
		model.getArbeitssystem().setWegzeitenUndPausen_Min( 	panelArbeitswegePausen.getWegzeitenUndPausen_min()	);

		model.getArbeitssystem().setUmsetzenBetrag_CHF(			panelWeitereAufwaende.getUmsetzenBetrag_CHF()			);
		model.getArbeitssystem().setUmsetzenZeit_h(		 		panelWeitereAufwaende.getUmsetzenZeit_h()				);
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF( panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h( 	panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}


	@Override
	public ModelMobilerHacker getModel() {
		return (ModelMobilerHacker) model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelAushiebUndHackgut.setLaubholzAnteil_Prz(			((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getLaubholzAnteil_Prz());
		panelAushiebUndHackgut.setAnzahlBaeume(					((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getAnzahlBaeume());
		panelAushiebUndHackgut.setHackgutart(					((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getHackgutart());
		
		panelAushiebUndHackgut.setMittlererBhdAushieb_m(		((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getMittlererBhdAushieb_m());
		panelAushiebUndHackgut.setMittlererZopfDurchmesser_m(	((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getMittlererZopfDrm_m());
		
		panelErschliessung.setFahrstreckenAufStrasse_m(			((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getFahrstreckeStrasse_m());
		panelErschliessung.setFahrstreckenFeinerschliessung_m(	((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getFahrstreckeFeinerschliessung_m());
		panelErschliessung.setHolzlagerLaenge_m(				((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getHolzlagerLaenge_m());
		
		panelErschliessung.setHindernisKategorie(				((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getHindernisKategorie());
		panelErschliessung.setNeigungsKategorie(				((ArbeitsobjektMobilerHacker) data.getArbeitsobjekt()).getNeigungsKategorie());
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				data.getArbeitssystem().getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnsatzMaschine(				data.getArbeitssystem().getKostensatzMaschine1_proH()		);

		panelHacker.setHackerTyp(							((ArbeitssystemMobilerHacker) data.getArbeitssystem()).getHackerTyp());
		panelHacker.setKippContainerVolumen_m3(				((ArbeitssystemMobilerHacker) data.getArbeitssystem()).getKippContainerVolumen_m3());
		
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
	protected final void displayErgebnis() {
		super.displayErgebnis();
		
		double hackgutMenge_m3 = getModel().getArbeitsobjekt().getHackgutmenge_m3();
		panelAushiebUndHackgut.setHackgutMenge_m3(hackgutMenge_m3);
		
		this.validate();
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}

}
