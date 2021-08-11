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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.ErgebnisPanelHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel2014;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018.HackerMotorleistung;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.modelle.ModelHackschnitzelTransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class HackschnitzelTransport2018 extends HeProMoWindow2014 {
	
	private static final long serialVersionUID = 1L;
	
	private JSpinner txtHolzmenge_Srm;
	private JRadioButton radAufnehmenBeladenerMulde;
	private JRadioButton radHackenInMulde;
	private JLabel lblZielsortiment;
	private JComboBox<Zielsortiment> cmbZielsortiment;
	private JLabel lblHackertyp;
	private JComboBox<HackerMotorleistung> cmbHackertyp;
	private JSpinner txtDistanzWaldstrasse_km;
	private JSpinner txtDistanzInnerAusserorts_km;
	private JSpinner txtDistanzAutobahn_km;
	
	private JSpinner txtMuldeninhalt_Srm;
	
	private ModelHackschnitzelTransport2018 model = new ModelHackschnitzelTransport2018();

	
	public HackschnitzelTransport2018() {
		super.setTitle(GuiStrings.getString("HackschnitzelTransport2018.Title")); //$NON-NLS-1$
		super.setSize((int) (645 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (650 * MainWindow.SIZE));
		
		super.initalize();
		super.panelArbeitswegePausen.setVisible(false);
	}
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.setBorder(TitledBorderFactory.createTitledBorder("")); //$NON-NLS-1$
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		JLabel lblHolzmenge = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.Hackgutmenge_Srm")); //$NON-NLS-1$
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		txtHolzmenge_Srm = new JSpinner();
		panel.add(txtHolzmenge_Srm, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton = GuiStrings.getInfoButtonBlue(GuiStrings.getString("HackschnitzelTransport2018.InfoButtonSrm")); //$NON-NLS-1$
		panel.add(lblInfoButton, c);
		

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		radAufnehmenBeladenerMulde = new JRadioButton(GuiStrings.getString("HackschnitzelTransport2018.Variante1")); //$NON-NLS-1$
		radAufnehmenBeladenerMulde.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblZielsortiment.setEnabled(false);
					cmbZielsortiment.setEnabled(false);
					lblHackertyp.setEnabled(false);
					cmbHackertyp.setEnabled(false);
				}
			}
		});
		panel.add(radAufnehmenBeladenerMulde, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		JLabel lblAufnehmenBeladenerMulde_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("HackschnitzelTransport2018.InfoButtonVariante1")); //$NON-NLS-1$
		panel.add(lblAufnehmenBeladenerMulde_Info, c);
		
		
		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		radHackenInMulde = new JRadioButton(GuiStrings.getString("HackschnitzelTransport2018.Variante2")); //$NON-NLS-1$
		radHackenInMulde.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					lblZielsortiment.setEnabled(true);
					cmbZielsortiment.setEnabled(true);
					lblHackertyp.setEnabled(true);
					cmbHackertyp.setEnabled(true);
				}
			}
		});
		panel.add(radHackenInMulde, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		JLabel lblHackenInMulde_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("HackschnitzelTransport2018.InfoButtonVariante2")); //$NON-NLS-1$
		panel.add(lblHackenInMulde_Info, c);
		
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(radAufnehmenBeladenerMulde);
		buttonGroup.add(radHackenInMulde);


		
		//Label Zielsortiment
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(0,50,0,0);
		lblZielsortiment = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.Poltersortiment")); //$NON-NLS-1$
		panel.add(lblZielsortiment, c);
		
		//Combo Zielsortiment
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		cmbZielsortiment = new JComboBox<>();
		panel.add(cmbZielsortiment, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton2 = GuiStrings.getInfoButtonBlue("<html> " //$NON-NLS-1$
				+ GuiStrings.getString("HackschnitzelTransport2018.begriffserklaerungPolter") //$NON-NLS-1$
				+ GuiStrings.getString("HackschnitzelTransport2018.begriffserklaerungEnergierundholz") //$NON-NLS-1$
				+ GuiStrings.getString("HackschnitzelTransport2018.begriffserklaerungWaldrestholz") //$NON-NLS-1$
				+ "</html>"); //$NON-NLS-1$
		panel.add(lblInfoButton2, c);
		


		//Label Hackertyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,50,0,0);
		lblHackertyp = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.HackerMotorleistung")); //$NON-NLS-1$
		panel.add(lblHackertyp, c);
		
		//Combo Hackertyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		cmbHackertyp = new JComboBox<>();
		panel.add(cmbHackertyp, c);
		
		
		//Methode darf erst aufgerufen werden, nachdem die Combos Zielsortiment/Hackertyp initialisiert sind
		radAufnehmenBeladenerMulde.setSelected(true);
		
		
		
		//Panel Distanzen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		panel.add(createPanelDistanzen(), c);
		
		
		
		//Plathalter
        c = new GridBagConstraints();
		c.gridx = 9;
		c.gridy = 9;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 400;
		c.weighty = 100;
		panel.add(new JPanel(), c);
		
	}
	
	
	@Override
	protected JPanel initSpecialPanel() {
		JPanel panel = new JPanel();
		
		panel.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("HackschnitzelTransport2018.Transportfahrzeug"))); //$NON-NLS-1$
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Label Muldeninhalt
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,10,5);
		JLabel lblMuldeninhalt = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.MuldeninhaltSrm")); //$NON-NLS-1$
		panel.add(lblMuldeninhalt, c);
		
		//Text Muldeninhalt
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,10,5);
		txtMuldeninhalt_Srm = new JSpinner();
		panel.add(txtMuldeninhalt_Srm, c);
		
		
		return panel;
	}
	
	@Override
	protected boolean isSpecialPanelGridWidthSingleColumn() {
		return true;
	}
	
	
	private JPanel createPanelDistanzen() {
		JPanel pnlDistanzen = new JPanel();
		pnlDistanzen.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("HackschnitzelTransport2018.Distanzen"))); //$NON-NLS-1$
		
		//set layout
		pnlDistanzen.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		JLabel lblDistanzWaldstrasse_km = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.DistanzWaldstrasse_km")); //$NON-NLS-1$
		pnlDistanzen.add(lblDistanzWaldstrasse_km, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		txtDistanzWaldstrasse_km = new JSpinner();
		pnlDistanzen.add(txtDistanzWaldstrasse_km, c);


		
		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		JLabel lblDistanzInnerAusserorts_km = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.DistanzInnerAusserorts_km")); //$NON-NLS-1$
		pnlDistanzen.add(lblDistanzInnerAusserorts_km, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		txtDistanzInnerAusserorts_km = new JSpinner();
		pnlDistanzen.add(txtDistanzInnerAusserorts_km, c);


		
		//Label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		JLabel lblDistanzAutobahn_km = new JLabel(GuiStrings.getString("HackschnitzelTransport2018.DistanzAutobahn_km")); //$NON-NLS-1$
		pnlDistanzen.add(lblDistanzAutobahn_km, c);
		
		//Text
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		txtDistanzAutobahn_km = new JSpinner();
		pnlDistanzen.add(txtDistanzAutobahn_km, c);
		
		
		return pnlDistanzen; 
	}

	
	@Override
	protected ErgebnisPanel initErgebnisPanel() {
		ErgebnisPanel panel = new ErgebnisPanelHackschnitzelTransport2018();
		
		panel.setLabelProduktivitaet(GuiStrings.getString("HackschnitzelTransport2018.ProduktivitaetSrmProPMH15")); //$NON-NLS-1$
		panel.setLabelKostenProM3(GuiStrings.getString("HackschnitzelTransport2018.proSrm")); //$NON-NLS-1$
		
		return panel;
	}
	
	@Override
	protected KostensaetzePanel2014 initKostensaetzePanel() {
		return new KostensaetzePanel2014(this, false, false);
	}
	
	
	@Override
	protected WeitereAufwaendePanel initWeitereAufwaendePanel() {
		return new WeitereAufwaendePanel(this, true);
	}
	

	@Override
	protected void initData() {
		String labelMaschine = getModel().getArbeitssystem().getLabelMaschine1();
		panelKostensaetze.setLabelMaschine1(labelMaschine);
		
		
		txtHolzmenge_Srm.setModel(new SpinnerNumberModel(1.0, 0, 100000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_Srm);
		
		addDefaultItemListener(radAufnehmenBeladenerMulde);
		addDefaultItemListener(radHackenInMulde);

		for (Zielsortiment sortiment : Zielsortiment.values()) {
			cmbZielsortiment.addItem(sortiment);
		}
		addDefaultActionListener(cmbZielsortiment);

		for (HackerMotorleistung hackerMotorleistung : model.getArbeitsobjekt().getAllHackerMotorleistung()) {
			cmbHackertyp.addItem(hackerMotorleistung);
		}
		addDefaultActionListener(cmbHackertyp);

		
		
		txtDistanzWaldstrasse_km.setModel(new SpinnerNumberModel(1.0, 0, 100, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDistanzWaldstrasse_km);
		
		txtDistanzInnerAusserorts_km.setModel(new SpinnerNumberModel(1.0, 0, 500, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDistanzInnerAusserorts_km);
		
		txtDistanzAutobahn_km.setModel(new SpinnerNumberModel(1.0, 0, 500, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDistanzAutobahn_km);
		
		
		txtMuldeninhalt_Srm.setModel(new SpinnerNumberModel(1.0, 1, 200, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMuldeninhalt_Srm);
		
		
		getFaktorenPanel().showInfoButtonWegzeitenUndPausen(false);
	}

	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		if (radAufnehmenBeladenerMulde.equals(eventSource)) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run(){
					String message = GuiStrings.getString("HackschnitzelTransport2018.WarnungKostensatz"); //$NON-NLS-1$
					JOptionPane.showMessageDialog(HackschnitzelTransport2018.this, message, GuiStrings.getString("HackschnitzelTransport2018.Warnung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
				}
			});
		}
		else if (cmbHackertyp.equals(eventSource)) {
			final HackerMotorleistung hackertyp = (HackerMotorleistung) cmbHackertyp.getSelectedItem();
			if (hackertyp.isBenutzerdefiniert()) {
				SwingUtilities.invokeLater(new Runnable(){
					@Override
					public void run(){
						String message = GuiStrings.getString("HackschnitzelTransport2018.MotorleistungEingeben"); //$NON-NLS-1$
						while (true) {
							//Inputdialog
							String result = JOptionPane.showInputDialog(HackschnitzelTransport2018.this, message, hackertyp.getMotorleistungCalc_Kw());
							if (result == null) {
								break; //"Abbrechen" gedrückt
							}
							
							try {
								//Ergebnis parsen
								int motorleistungNeu = (int) Double.parseDouble(result);
								
								//Ungültige Werte abfangen bzw. neuer Wert setzen
								if (motorleistungNeu < 10 || motorleistungNeu > 2000) {
									throw new IllegalArgumentException();
								}
								else {
									hackertyp.setMotorleistungCalc_Kw(motorleistungNeu);
									break; //Änderung erfolgreich
								}
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(HackschnitzelTransport2018.this, GuiStrings.getString("HackschnitzelTransport2018.gueltigenWertEingeben"), GuiStrings.getString("HackschnitzelTransport2018.Fehler"), JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
								
							} catch (IllegalArgumentException e) {
								JOptionPane.showMessageDialog(HackschnitzelTransport2018.this, GuiStrings.getString("HackschnitzelTransport2018.gueltigenWertEingeben"), GuiStrings.getString("HackschnitzelTransport2018.Fehler"), JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
							}
						}
						displayErgebnis();
					}
				});
			}
		}
	}

	
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3( 				  ((Double) txtHolzmenge_Srm.getValue()) 			);
		model.getArbeitsobjekt().setAufnehmenBeladenerMulde(			  	radAufnehmenBeladenerMulde.isSelected() );
		model.getArbeitsobjekt().setZielsortiment( 			(Zielsortiment) cmbZielsortiment.getSelectedItem()		);
		model.getArbeitsobjekt().setHackerMotorleistung((HackerMotorleistung) cmbHackertyp.getSelectedItem()  		);
		model.getArbeitsobjekt().setAllHackerMotorleistung(			 	 	getAllHackerMotorleistungFromCombobox() );
		model.getArbeitsobjekt().setDistanzWaldstrasse_km(		  ((Double) txtDistanzWaldstrasse_km.getValue()) 	);
		model.getArbeitsobjekt().setDistanzInnerAusserorts_km(	  ((Double) txtDistanzInnerAusserorts_km.getValue()));
		model.getArbeitsobjekt().setDistanzAutobahn_km(			  ((Double) txtDistanzAutobahn_km.getValue()) 		);
		
		//Arbeitssystem
		model.getArbeitssystem().setMuldeninhalt_Srm(			((Double) txtMuldeninhalt_Srm.getValue()) );
	}
	

	@Override
	public ModelHackschnitzelTransport2018 getModel() {
		return model;
	}
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);

		//Combobox Hackertyp refillen, damit korrekter benutzerdefinierter Wert geladen wird
		cmbHackertyp.removeAllItems();
		for (HackerMotorleistung hackertyp : ((ArbeitsobjektHackschnitzelTransport2018)data.getArbeitsobjekt()).getAllHackerMotorleistung() ) {
			cmbHackertyp.addItem(hackertyp);
		}
		
		//Arbeitsobjekt
		txtHolzmenge_Srm.setValue( 														   data.getArbeitsobjekt().getHolzmenge_m3() );
		radAufnehmenBeladenerMulde.setSelected( ((ArbeitsobjektHackschnitzelTransport2018) data.getArbeitsobjekt()).isAufnehmenBeladenerMulde()		);
		radHackenInMulde.setSelected( 			((ArbeitsobjektHackschnitzelTransport2018) data.getArbeitsobjekt()).isAufnehmenBeladenerMulde() == false  );
		cmbZielsortiment.setSelectedItem( 		((ArbeitsobjektHackschnitzelTransport2018) data.getArbeitsobjekt()).getZielsortiment()		);
		cmbHackertyp.setSelectedItem( (    		(ArbeitsobjektHackschnitzelTransport2018)  data.getArbeitsobjekt()).getHackerMotorleistung() );
		txtDistanzWaldstrasse_km.setValue(  	((ArbeitsobjektHackschnitzelTransport2018) data.getArbeitsobjekt()).getDistanzWaldstrasse_km() );
		txtDistanzInnerAusserorts_km.setValue( 	((ArbeitsobjektHackschnitzelTransport2018) data.getArbeitsobjekt()).getDistanzInnerAusserorts_km() );
		txtDistanzAutobahn_km.setValue( 		((ArbeitsobjektHackschnitzelTransport2018) data.getArbeitsobjekt()).getDistanzAutobahn_km() );
		
		//Arbeitssystem
		txtMuldeninhalt_Srm.setValue(			((ArbeitssystemHackschnitzelTransport2018) data.getArbeitssystem()).getMuldeninhalt_Srm() );
		
		
		super.setReactOnInputChange(true);
	}
	
	
	private HackerMotorleistung[] getAllHackerMotorleistungFromCombobox() {
		HackerMotorleistung[] result = new HackerMotorleistung[cmbHackertyp.getItemCount()];
		
		for (int i=0; i<cmbHackertyp.getItemCount(); i++) {
			result[i] = (HackerMotorleistung) cmbHackertyp.getItemAt(i);
		}
		
		return result;
	}

}
