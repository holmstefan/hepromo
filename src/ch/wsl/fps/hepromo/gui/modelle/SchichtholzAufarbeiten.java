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

import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelAufarbeiten;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.GelaendePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.SortimentsvorgabenPanelSchichtholz;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten.SchichtholzStuecklaengen;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellSchichtholzAufarbeiten;
import ch.wsl.fps.hepromo.model.HeProMoInputData;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public class SchichtholzAufarbeiten extends Motormanuell {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelAufarbeiten panelBestand;
	private GelaendePanel panelGelaende;
	private SortimentsvorgabenPanelSchichtholz panelSortimentsvorgaben;
	
	private ModelMotormanuellSchichtholzAufarbeiten model = new ModelMotormanuellSchichtholzAufarbeiten();
	
	
	
	public SchichtholzAufarbeiten() {
		super.setTitle("Schichtholz abfertigen");
		super.setSize(550, 585);
		
		super.initalize();
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
		c.gridheight = 2;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelAufarbeiten(this, "Schichtholz", "Industrieholz lang");
		panel.add(panelBestand, c);
		
		//panel Gelände
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelGelaende = new GelaendePanel(this);
		panel.add(panelGelaende, c);
		
		//panel Sortimentsvorgaben
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelSortimentsvorgaben = new SortimentsvorgabenPanelSchichtholz(this);
		panel.add(panelSortimentsvorgaben, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}
	
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		panelBestand.onInputChangedBeforeCalculation();
	}
	


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setBaumartenGruppe(		panelBestand.getBaumartengruppe()			);
		model.getArbeitsobjekt().setMassenmittelstamm_m3iR(	panelBestand.getMassenmittelstamm_m3iR()	);
		model.getArbeitsobjekt().setHolzmenge_m3(			panelBestand.getHolzmenge_m3iR()			);
		model.getArbeitsobjekt().setAnteilSchichtholz_Prz(	panelBestand.getAnteilHauptsortiment()		);
		model.getArbeitsobjekt().setAnteilIndustrieholz_Prz(panelBestand.getAnteilNebensortiment()		);
		
		model.getArbeitsobjekt().setHangneigung(			panelGelaende.getHangneigung()				);
		model.getArbeitsobjekt().setHindernisse(			panelGelaende.getHindernisse()				);
		
		model.getArbeitsobjekt().setStuecklaengen(			panelSortimentsvorgaben.getStuecklaengen()		);
		model.getArbeitsobjekt().setAnteilSpalten_Prz(		panelSortimentsvorgaben.getAnteilSpalten_Prz()	);
	}


	@Override
	public ModelMotormanuellSchichtholzAufarbeiten getModel() {
		return (ModelMotormanuellSchichtholzAufarbeiten) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		panelBestand.setBaumartengruppe(			((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getBaumartenGruppe()				);
		panelBestand.setMassenmittelstamm_m3iR(		((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getMassenmittelstamm_m3iR()		);
		panelBestand.setHolzmenge_m3iR(				((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getHolzmenge_m3()				);
		panelBestand.setAnteilHauptsortiment_Prz(	((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getAnteilSchichtholz_Prz()		);
		panelBestand.setAnteilNebensortiment_Prz(	((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getAnteilIndustrieholz_Prz()		);
		panelGelaende.setHangneigung(				((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getHangneigung()					);
		panelGelaende.setHindernisse(				((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getHindernisse()					);
		panelSortimentsvorgaben.setStuecklaengen(	((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getStuecklaengen()				);
		panelSortimentsvorgaben.setAnteilSpalten_Prz(((ArbeitsobjektSchichtholzAufarbeiten)data.getArbeitsobjekt()).getAnteilSpalten_Prz()		);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(				data.getArbeitssystem().getKostensatzPersonal1_proH()		);
		panelKostensaetze.setAnsatzMaschine(				data.getArbeitssystem().getKostensatzMaschine1_proH()		);
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
	protected void initData() {
		super.initData();
		panelSortimentsvorgaben.setStuecklaengen(SchichtholzStuecklaengen.Laenge2m);
		panelSortimentsvorgaben.setAnteilSpalten_Prz(0);
	}

}
