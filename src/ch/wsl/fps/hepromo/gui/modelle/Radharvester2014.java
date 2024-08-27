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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.AbstractErgebnisPanel;
import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelRadharvester2014;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.TraktionshilfswindePanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.ZuschlaegePanelRadharvester2014;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel2014;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014.Maschinentyp;
import ch.wsl.fps.hepromo.model.modelle.ModelRadharvester2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Radharvester2014 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelRadharvester2014 panelBestand;
	private ZuschlaegePanelRadharvester2014 panelZuschlaege;
	private TraktionshilfswindePanel panelThw;
	private JComboBox<Maschinentyp> cmbMaschinentyp;
	
	private ModelRadharvester2014 model = new ModelRadharvester2014();
	
	
	
	public Radharvester2014() {		
		super.setTitle(GuiStrings.getString("Radharvester2014.Title")); //$NON-NLS-1$
		super.setSize((int) (645 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (720 * MainWindow.SIZE));
		
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
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelRadharvester2014(this);
		panel.add(panelBestand, c);
		
		//panel Zuschläge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(10,0,0,0);
		panelZuschlaege = new ZuschlaegePanelRadharvester2014(this);
		panel.add(panelZuschlaege, c);
		
		//panel THW
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(10,0,0,0);
		panelThw = new TraktionshilfswindePanel(this);
		panel.add(panelThw, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}

	
	@Override
	protected AbstractErgebnisPanel initErgebnisPanel() {
		ErgebnisPanel panel = new ErgebnisPanel.Builder()
				.enableRowPersonal()
				.enableRowMaschine1()
				.enableRowMaschine2()
				.enableRowUmsetzen()
				.enableRowProduktivitaet()
				.enableColumnProM3()
				.build();

		panel.setZeitMaschine2Unit("ISH"); //$NON-NLS-1$
		panel.setZeitMaschine2UnitInfo(GuiStrings.getString("Thw.IndirectSystemHours")); //$NON-NLS-1$
		panel.setLabelProduktivitaet(GuiStrings.getString("HeProMoWindow2014.Produktivitaet_m3_iR_pro_PMH15")); //$NON-NLS-1$
		panel.setLabelProduktivitaet2(GuiStrings.getString("HeProMoWindow2014.m3_oR_pro_PMH15")); //$NON-NLS-1$
		panel.setLabelKostenProM3(GuiStrings.getString("HeProMoWindow2014.ProM3oR")); //$NON-NLS-1$
		
		return panel;
	}
	
	
	@Override
	protected JPanel initSpecialPanel() {
		JPanel panel = new JPanel();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Maschinentyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,8,20,5);
		JLabel lblMaschinentyp = new JLabel(GuiStrings.getString("Radharvester2014.Maschinentyp")); //$NON-NLS-1$
		panel.add(lblMaschinentyp, c);
		
		//Combo Maschinentyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,5);
		cmbMaschinentyp = new JComboBox<>();
		panel.add(cmbMaschinentyp, c);
		
		return panel;
	}
	
	
	@Override
	protected KostensaetzePanel2014 initKostensaetzePanel() {
		KostensaetzePanel2014 panel = new KostensaetzePanel2014.Builder(this)
				.showPersonal1()
				.showMaschine2()
				.setUnitMaschine2ToISH()
				.build();
		
		panel.setLabelMaschine2(GuiStrings.getString("Thw.TraktionshilfswindeMaschine")); //$NON-NLS-1$
		return panel;
	}

	
	@Override
	protected void initData() {
		String labelPersonal = getModel().getArbeitssystem().getLabelPersonal1();
		String labelMaschine = getModel().getArbeitssystem().getLabelMaschine1();
		
		panelKostensaetze.setLabelPersonal(labelPersonal);
		panelKostensaetze.setLabelMaschine1(labelMaschine);

		for (int i=0; i<Maschinentyp.values().length; i++) {
			cmbMaschinentyp.addItem(Maschinentyp.values()[i]);
		}
		addDefaultActionListener(cmbMaschinentyp);
	}

	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbMaschinentyp.equals(eventSource)) {
			Maschinentyp selection = (Maschinentyp) cmbMaschinentyp.getSelectedItem();
			switch (selection) {
			case Mittel:
				panelBestand.setMinBhd_cm(12);
				
				SwingUtilities.invokeLater(() -> {
					String message = GuiStrings.getString("Radharvester2014.WarnungKostensatz"); //$NON-NLS-1$
					JOptionPane.showMessageDialog(Radharvester2014.this, message, GuiStrings.getString("Radharvester2014.Warnung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
				});
				break;
				
			case Gross:
				if (panelBestand.getBhd_cm() < 15) {
					panelBestand.setBhd_cm(15);
				}
				panelBestand.setMinBhd_cm(15);
				
				SwingUtilities.invokeLater(() -> {
					String message = "<html><li>" //$NON-NLS-1$
							+ GuiStrings.getString("Radharvester2014.WarnungKostensatzZeile1") //$NON-NLS-1$
							+ GuiStrings.getString("Radharvester2014.WarnungKostensatzZeile2") //$NON-NLS-1$
							+ "<li>" //$NON-NLS-1$
							+ GuiStrings.getString("Radharvester2014.WarnungKostensatz") //$NON-NLS-1$
							+ "</html>"; //$NON-NLS-1$
					JOptionPane.showMessageDialog(Radharvester2014.this, message, GuiStrings.getString("Radharvester2014.Warnung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
				});
				break;
				
			default:
				throw new RuntimeException();
			}
		}
		
		panelZuschlaege.onInputChangedBeforeCalculation(eventSource);
	}
	
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		panelBestand.updateLabelHolzmengeMitRindenabzug(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(	panelBestand.getHolzmenge_m3()		);
		model.getArbeitsobjekt().setBhd_cm(			panelBestand.getBhd_cm()			);
		
		model.getArbeitsobjekt().setFoermigkeitArrayWithSelection( 		panelZuschlaege.getFoermigkeitArrayWithSelection() 		);
		model.getArbeitsobjekt().setLaubholzAnteilArrayWithSelection(	panelZuschlaege.getLaubholzAnteilArrayWithSelection() 	);
		model.getArbeitsobjekt().setLiegendesHolzArrayWithSelection(	panelZuschlaege.getLiegendesHolzArrayWithSelection()	);
		model.getArbeitsobjekt().setHangneigungArrayWithSelection(		panelZuschlaege.getHangneigungArrayWithSelection()		);
		
		model.getArbeitsobjekt().setEinsatzThw(			panelThw.isEinsatzThw()				);
		model.getArbeitsobjekt().setAnzahlRueckegassen(	panelThw.getAnzahlRueckegassen()	);
		
		//Arbeitssystem
		model.getArbeitssystem().setMaschinentyp(	this.getMaschinentyp()		);
		model.getArbeitssystem().setKostensatzMaschine2_proH(panelKostensaetze.getAnsatzMaschine2());
	}


	@Override
	public ModelRadharvester2014 getModel() {
		return (ModelRadharvester2014) model;
	}
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);

		//Arbeitssystem
		this.setMaschinentyp(				((ArbeitssystemRadharvester2014) data.getArbeitssystem()).getMaschinentyp()		);
		
		//Arbeitsobjekt
		panelBestand.setHolzmenge_m3(										 data.getArbeitsobjekt().getHolzmenge_m3()		);
		panelBestand.setBhd_cm(				((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).getBhd_cm()			);

		panelZuschlaege.setFoermigkeitArrayWithSelection(		((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).getFoermigkeitArrayWithSelection()		);
		panelZuschlaege.setLaubholzAnteilArrayWithSelection(	((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).getLaubholzAnteilArrayWithSelection()		);
		panelZuschlaege.setLiegendesHolzArrayWithSelection(		((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).getLiegendesHolzArrayWithSelection()		);
		panelZuschlaege.setHangneigungArrayWithSelection(		((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).getHangneigungArrayWithSelection()		);
		
		panelThw.setEinsatzThw(				((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).isEinsatzThw()				);
		panelThw.setAnzahlRueckegassen(		((ArbeitsobjektRadharvester2014) data.getArbeitsobjekt()).getAnzahlRueckegassen()		);

		//Arbeitssystem
		panelKostensaetze.setAnsatzMaschine2( 								data.getArbeitssystem().getKostensatzMaschine2_proH() 	);
		
		super.setReactOnInputChange(true);
	}



	public Maschinentyp getMaschinentyp() {
		return (Maschinentyp) cmbMaschinentyp.getSelectedItem();
	}

	public void setMaschinentyp(Maschinentyp maschinentyp) {
		cmbMaschinentyp.setSelectedItem(maschinentyp);

		switch (maschinentyp) {
		case Mittel:
			panelBestand.setMinBhd_cm(12);
			break;
			
		case Gross:
			if (panelBestand.getBhd_cm() < 15) {
				panelBestand.setBhd_cm(15);
			}
			panelBestand.setMinBhd_cm(15);
			break;
			
		default:
			throw new RuntimeException();
		}
	}
	
}
