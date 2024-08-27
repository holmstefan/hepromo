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

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelSeilkranPlanung;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranPlanung;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.modelle.ModelSeilkranPlanung;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //GUIs von Teilmodellen werden ab Version 2.1 nicht mehr gewartet
public abstract class SeilkranPlanung extends HeProMoWindow {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtLinienlaenge_m;
	private JCheckBox chkLinieAbsteckenOhneProjekt;
	
	private KostensaetzePanelSeilkranPlanung panelKostensaetze;	
	private ArbeitswegePausenPanel panelArbeitswegePausen;	
	private WeitereAufwaendePanel panelWeitereAufwaende;
	
	private final ModelSeilkranPlanung model;
	
	
	
	public SeilkranPlanung(ModelSeilkranPlanung model) {
		super.setTitle("Seilkran Planung");
		super.setSize(550, 540);
		
		this.model = model;
		
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
		c.insets = new Insets(5,5,0,5);
		JLabel lblLinienlaenge_m = new JLabel("Linienlänge (m)");
		panel.add(lblLinienlaenge_m, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(5,5,0,5);
		txtLinienlaenge_m = new JSpinner();
		panel.add(txtLinienlaenge_m, c);

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,2,0,0);
		chkLinieAbsteckenOhneProjekt = new JCheckBox("Linie abstecken ohne Projekt");
		chkLinieAbsteckenOhneProjekt.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(chkLinieAbsteckenOhneProjekt, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder1 = new JPanel();
		panel.add(placeholder1, c);
		

		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
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
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelKostensaetze = new KostensaetzePanelSeilkranPlanung(this);
		panel.add(panelKostensaetze, c);
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelArbeitswegePausen = new ArbeitswegePausenPanel(this);
		panel.add(panelArbeitswegePausen, c);
		

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		panelWeitereAufwaende = new WeitereAufwaendePanel(this, true);
		panel.add(panelWeitereAufwaende, c);
		

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
				.build();
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setLinienlaenge_m(				(Integer) txtLinienlaenge_m.getValue()			);
		model.getArbeitsobjekt().setLinieAbsteckenOhneProjekt(	chkLinieAbsteckenOhneProjekt.isSelected()		);
		
		
		//Arbeitssystem
		model.getArbeitssystem().setKostensatzPersonal1_proH(		panelKostensaetze.getAnsatzPersonal()					);
		
		model.getArbeitssystem().setTaeglicheArbeitszeit_Min(	panelArbeitswegePausen.getArbeitszeit_min()				);
		model.getArbeitssystem().setWegzeitenUndPausen_Min(		panelArbeitswegePausen.getWegzeitenUndPausen_min()		);
		
		model.getArbeitssystem().setWeitereAufwaendeBetrag_CHF(	panelWeitereAufwaende.getWeitereAufwaendeBetrag_CHF()	);
		model.getArbeitssystem().setWeitereAufwaendeZeit_h(		panelWeitereAufwaende.getWeitereAufwaendeZeit_h()		);
	}


	@Override
	public ModelSeilkranPlanung getModel() {
		return (ModelSeilkranPlanung) model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		//Arbeitsobjekt
		txtLinienlaenge_m.setValue(								((ArbeitsobjektSeilkranPlanung) data.getArbeitsobjekt()).getLinienlaenge_m()			);
		chkLinieAbsteckenOhneProjekt.setSelected(				((ArbeitsobjektSeilkranPlanung) data.getArbeitsobjekt()).isLinieAbsteckenOhneProjekt()	);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzPersonal(					((Arbeitssystem) data.getArbeitssystem()).getKostensatzPersonal1_proH()		);
		
		panelArbeitswegePausen.setArbeitszeit_min(			 	((Arbeitssystem) data.getArbeitssystem()).getTaeglicheArbeitszeit_Min()	);
		panelArbeitswegePausen.setWegzeitenUndPausen_min(		((Arbeitssystem) data.getArbeitssystem()).getWegzeitenUndPausen_Min()	);
		
		panelWeitereAufwaende.setWeitereAufwaendeBetrag_CHF(	((Arbeitssystem) data.getArbeitssystem()).getWeitereAufwaendeBetrag_CHF());
		panelWeitereAufwaende.setWeitereAufwaendeZeit_h(		((Arbeitssystem) data.getArbeitssystem()).getWeitereAufwaendeZeit_h()	);
		
		//Faktoren
		loadFaktoren(data);
		
		super.setReactOnInputChange(true);
	}


	@Override
	protected void initData() {
		txtLinienlaenge_m.setModel(new SpinnerNumberModel(600, 0, 10000, 50));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLinienlaenge_m);
		
		chkLinieAbsteckenOhneProjekt.setSelected(true);
		addDefaultItemListener(chkLinieAbsteckenOhneProjekt);
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelKostensaetze.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
	
}
