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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanelHacker2018;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018.HackerMotorleistung;
import ch.wsl.fps.hepromo.model.modelle.ModelHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Hacker2018 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtHolzmenge_Srm;
	private PoltervolumenSchaetzen pvs;
	private JComboBox<Zielsortiment> cmbZielsortiment;
	private JComboBox<HackerMotorleistung> cmbHackertyp;
	
	private ModelHacker2018 model = new ModelHacker2018();
	
	private final String begriffsErklaerung = "<html> " //$NON-NLS-1$
			+ GuiStrings.getString("Hacker2018.begriffserklaerungPolter") //$NON-NLS-1$
			+ GuiStrings.getString("Hacker2018.begriffserklaerungEnergierundholz") //$NON-NLS-1$
			+ GuiStrings.getString("Hacker2018.begriffserklaerungWaldrestholz") //$NON-NLS-1$
			+ "</html>"; //$NON-NLS-1$
	

	public Hacker2018() {	
		super.setTitle(GuiStrings.getString("Hacker2018.Title")); //$NON-NLS-1$
		super.setSize((int) (770 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (630 * MainWindow.SIZE));
		
		super.initalize();
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
		c.weightx = 20;
		c.insets = new Insets(0,0,0,8);
		JLabel lblHolzmenge = new JLabel(GuiStrings.getString("Hacker2018.Hackgutmenge_Srm")); //$NON-NLS-1$
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		txtHolzmenge_Srm = new JSpinner();
		panel.add(txtHolzmenge_Srm, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Hacker2018.InfoButton_Srm")); //$NON-NLS-1$
		panel.add(lblInfoButton, c);
		
		//Button Schätzung Poltervolumen
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
		c.insets = new Insets(0,30,0,0);
		JButton btnPoltervolumen = new JButton(GuiStrings.getString("Hacker2018.SchaetzungAnhandPoltervolumen")); //$NON-NLS-1$
		btnPoltervolumen.addActionListener(evt -> {
			if (pvs == null) {
				//Durch Wiederverwendung müssen die Daten nicht immer komplett neu eingegeben werden
				pvs = new PoltervolumenSchaetzen(getZielsortiment());
			}

			int result = JOptionPane.showConfirmDialog(
					Hacker2018.this,
					pvs,
					GuiStrings.getString("Hacker2018.SchaetzungHackschnitzelmenge"), //$NON-NLS-1$
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION && pvs.getHolzmenge_Srm() > 0) {
				txtHolzmenge_Srm.setValue( pvs.getHolzmenge_Srm() );
			}
		});
		panel.add(btnPoltervolumen, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton2 = GuiStrings.getInfoButtonBlue(begriffsErklaerung);
		panel.add(lblInfoButton2, c);
		


		
		//Label Zielsortiment
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,0,0,8);
		JLabel lblZielsortiment = new JLabel(GuiStrings.getString("Hacker2018.Poltersortiment")); //$NON-NLS-1$
		panel.add(lblZielsortiment, c);
		
		//Combo Zielsortiment
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(10,5,20,5);
		cmbZielsortiment = new JComboBox<>();
		panel.add(cmbZielsortiment, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,10,0,0);
		JLabel lblInfoButton3 = GuiStrings.getInfoButtonBlue(begriffsErklaerung);
		panel.add(lblInfoButton3, c);
		

		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 2;
		c.weightx = 400;
		c.weighty = 100;
		panel.add(new JPanel(), c);
		
	}
	
	
	@Override
	protected JPanel initSpecialPanel() {
		JPanel panel = new JPanel();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		//Label Hackertyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,8,20,5);
		JLabel lblHackertyp = new JLabel(GuiStrings.getString("Hacker2018.HackerMotorleistung")); //$NON-NLS-1$
		panel.add(lblHackertyp, c);
		
		//Combo Hackertyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,5);
		cmbHackertyp = new JComboBox<>();
		cmbHackertyp.setPreferredSize(new Dimension(180, 20));
		panel.add(cmbHackertyp, c);
		
		return panel;
	}
	
	
	@Override
	protected KostensaetzePanelHacker2018 initKostensaetzePanel() {
		return new KostensaetzePanelHacker2018(this);
	}
	
	

	@Override
	protected void initData() {
		String labelPersonal = getModel().getArbeitssystem().getLabelPersonal1();
		String labelMaschine = getModel().getArbeitssystem().getLabelMaschine1();
		
		panelKostensaetze.setLabelPersonal(labelPersonal);
		panelKostensaetze.setLabelMaschine1(labelMaschine);
		
		txtHolzmenge_Srm.setModel(new SpinnerNumberModel(50.0, 0, 100000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_Srm);

		for (HackerMotorleistung hackerMotorleistung : model.getArbeitssystem().getAllHackerMotorleistung()) {
			cmbHackertyp.addItem(hackerMotorleistung);
		}
		addDefaultActionListener(cmbHackertyp);

		for (Zielsortiment sortiment : Zielsortiment.values()) {
			cmbZielsortiment.addItem(sortiment);
		}
		addDefaultActionListener(cmbZielsortiment);
		cmbZielsortiment.addActionListener(evt -> {
			if (pvs != null) {
				pvs.setDefaultSchaetzmethode(getZielsortiment());
			}
		});

		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet(GuiStrings.getString("Hacker2018.ProduktivitaetSrmProPMH15")); //$NON-NLS-1$
		((ErgebnisPanel) ergebnisPanel).setLabelProduktivitaet2(null);
		((ErgebnisPanel) ergebnisPanel).setLabelKostenProM3(GuiStrings.getString("Hacker2018.proSrm")); //$NON-NLS-1$
	}

	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbHackertyp.equals(eventSource)) {
			final HackerMotorleistung hackertyp = (HackerMotorleistung) cmbHackertyp.getSelectedItem();
			if (hackertyp.isBenutzerdefiniert()) {
				SwingUtilities.invokeLater(() -> {
					String message = GuiStrings.getString("Hacker2018.MotorleistungEingeben"); //$NON-NLS-1$
					while (true) {
						//Inputdialog
						String result = JOptionPane.showInputDialog(Hacker2018.this, message, hackertyp.getMotorleistungCalc_Kw());
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
							JOptionPane.showMessageDialog(Hacker2018.this, GuiStrings.getString("Hacker2018.GueltigenWertEingeben"), GuiStrings.getString("Hacker2018.Fehler"), JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$

						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(Hacker2018.this, GuiStrings.getString("Hacker2018.GueltigenWertEingeben"), GuiStrings.getString("Hacker2018.Fehler"), JOptionPane.ERROR_MESSAGE ); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
					displayErgebnis();
				});
			}
		}
	}
	
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(        ((Double) txtHolzmenge_Srm.getValue()) 		);
		model.getArbeitsobjekt().setZielsortiment( (Zielsortiment) cmbZielsortiment.getSelectedItem()	);
		
		//Arbeitssystem
		model.getArbeitssystem().setHackerMotorleistung(		 (HackerMotorleistung) cmbHackertyp.getSelectedItem()  							  );
		model.getArbeitssystem().setAllHackerMotorleistung(       			 		   getAllHackerMotorleistungFromCombobox() 					  );
		model.getArbeitssystem().setDisplayHackerKostenProPMH15( ((KostensaetzePanelHacker2018)panelKostensaetze).isDisplayHackerKostenProPMH15() );
	}
	
	

	@Override
	public ModelHacker2018 getModel() {
		return model;
	}
	
	
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);

		//Combobox Hackertyp refillen, damit korrekter benutzerdefinierter Wert geladen wird
		cmbHackertyp.removeAllItems();
		for (HackerMotorleistung hackertyp : ((ArbeitssystemHacker2018)data.getArbeitssystem()).getAllHackerMotorleistung() ) {
			cmbHackertyp.addItem(hackertyp);
		}
		
		//Arbeitsobjekt
		txtHolzmenge_Srm.setValue( 		  							 data.getArbeitsobjekt().getHolzmenge_m3() 		);
		cmbZielsortiment.setSelectedItem( ((ArbeitsobjektHacker2018)data.getArbeitsobjekt()).getZielsortiment()		);
		
		//Arbeitssystem
		cmbHackertyp.setSelectedItem( (    (ArbeitssystemHacker2018) data.getArbeitssystem()).getHackerMotorleistung() );
		((KostensaetzePanelHacker2018)panelKostensaetze).setDisplayHackerKostenProPMH15( ((ArbeitssystemHacker2018) data.getArbeitssystem()).isDisplayHackerKostenProPMH15() 	); //diese Methode darf erst aufgerufen werden, nachdem Hackertyp und Zielsortiment im GUI gesetzt sind!
		
		super.setReactOnInputChange(true);
	}
	
	
	
	public Zielsortiment getZielsortiment() {
		return (Zielsortiment) cmbZielsortiment.getSelectedItem();
	}
	
	public int getMotorleistungHacker_kW() {
		return ((HackerMotorleistung) cmbHackertyp.getSelectedItem()).getMotorleistungCalc_Kw();
	}

	
	
	private HackerMotorleistung[] getAllHackerMotorleistungFromCombobox() {
		HackerMotorleistung[] result = new HackerMotorleistung[cmbHackertyp.getItemCount()];
		
		for (int i=0; i<cmbHackertyp.getItemCount(); i++) {
			result[i] = (HackerMotorleistung) cmbHackertyp.getItemAt(i);
		}
		
		return result;
	}
}
