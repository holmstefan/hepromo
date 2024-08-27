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
import java.text.DecimalFormat;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.AbstractErgebnisPanel;
import ch.wsl.fps.hepromo.gui.ErgebnisPanel;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.KostensaetzePanel2014;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektVorruecken2018.Standortguete;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemVorruecken2018.Maschinentyp;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.model.modelle.ModelVorruecken2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Vorruecken2018 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private JSpinner txtHolzmenge_m3;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JSpinner txtBhd_cm;
	private JComboBox<Standortguete> cmbStandortguete;
	private JSpinner txtMittelstammvolumen;
	private JComboBox<Hangneigung> cmbHangneigung_Prz;
	private JCheckBox chkAbzopfenDerKrone;
	
	private JComboBox<Maschinentyp> cmbMaschinentyp;
	private JComboBox<Double> cmbProduktivitaetssteigerung;
	
	private ModelVorruecken2018 model = new ModelVorruecken2018();
	

	public Vorruecken2018() {	
		super.setTitle(GuiStrings.getString("Vorruecken2018.Title")); //$NON-NLS-1$
		super.setSize((int) (645 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (650 * MainWindow.SIZE));
		
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
//		c.insets = new Insets(0,0,5,0);
		JLabel lblHolzmenge = new JLabel(GuiStrings.getString("Vorruecken2018.Holzmenge_m3iR")); //$NON-NLS-1$
		panel.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtHolzmenge_m3 = new JSpinner();
		panel.add(txtHolzmenge_m3, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,10,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("BestandPanelMotormanuellGesamt2014.lblInfoButtonUmrechnung_iRoR")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblHolzmenge_m3oR_readOnly, c);


		
		//Label BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblBhd_cm = new JLabel(GuiStrings.getString("Vorruecken2018.BHD_cm")); //$NON-NLS-1$
		panel.add(lblBhd_cm, c);
		
		//Text BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtBhd_cm = new JSpinner();
		panel.add(txtBhd_cm, c);

		

		//Label Standortgüte
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblStandortguete = new JLabel(GuiStrings.getString("Vorruecken2018.Standortguete")); //$NON-NLS-1$
		panel.add(lblStandortguete, c);
		
		//Combo Standortgüte
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		cmbStandortguete = new JComboBox<>();
		panel.add(cmbStandortguete, c);
		
		

		//Label Mittelstammvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblMittelstammvolumen = new JLabel(GuiStrings.getString("Vorruecken2018.Mittelstammvolumen_m3oR")); //$NON-NLS-1$
		panel.add(lblMittelstammvolumen, c);
		
		//Text Mittelstammvolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtMittelstammvolumen = new JSpinner();
		panel.add(txtMittelstammvolumen, c);

		

		//Label Hangneigung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblHangneigung_Prz = new JLabel(GuiStrings.getString("Vorruecken2018.Hangneigung_Prz")); //$NON-NLS-1$
		panel.add(lblHangneigung_Prz, c);
		
		//Combo Hangneigung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		cmbHangneigung_Prz = new JComboBox<>();
		panel.add(cmbHangneigung_Prz, c);
		
		//Info Hangneigung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,0,0);
		JLabel lblHangneigung_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Vorruecken2018.Hangneigung_Info")); //$NON-NLS-1$
		lblHangneigung_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		panel.add(lblHangneigung_Info, c);

		

		//Label Abzopfen der Krone
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblAbzopfenDerKrone = new JLabel(GuiStrings.getString("Vorruecken2018.AbzopfenDerKrone")); //$NON-NLS-1$
		panel.add(lblAbzopfenDerKrone, c);
		
		//CheckBox Abzopfen der Krone
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,-4,0,0);
		chkAbzopfenDerKrone = new JCheckBox();
		panel.add(chkAbzopfenDerKrone, c);
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 8;
		c.gridy = 8;
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

		
		//Label Maschinentyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(10,8,5,5);
		JLabel lblMaschinentyp = new JLabel(GuiStrings.getString("Vorruecken2018.Maschinentyp")); //$NON-NLS-1$
		panel.add(lblMaschinentyp, c);
		
		//Combo Maschinentyp
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,5,0);
		cmbMaschinentyp = new JComboBox<>();
		panel.add(cmbMaschinentyp, c);

		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 400;
		c.weighty = 100;
		panel.add(new JPanel(), c);	
		
		
		
		//Label Produktivitaetssteigerung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 20;
		c.insets = new Insets(0,8,10,5);
		JLabel lblProduktivitaetssteigerung = new JLabel(GuiStrings.getString("Vorruecken2018.Produktivitaetssteigerung")); //$NON-NLS-1$
		panel.add(lblProduktivitaetssteigerung, c);
		
		//Combo Produktivitaetssteigerung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,10,0);
		cmbProduktivitaetssteigerung = new JComboBox<>();
		panel.add(cmbProduktivitaetssteigerung, c);
		
		//Infobutton
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,10,0);
		JLabel lblInfoButton = GuiStrings.getInfoButtonBlue(HeProMoWindow.wrap(GuiStrings.getString("Vorruecken2018.InfoButtonProdSteigerung"), 60)); //$NON-NLS-1$
		panel.add(lblInfoButton, c);
		
		return panel;
	}
	
	
	@Override
	protected KostensaetzePanel2014 initKostensaetzePanel() {
		return new KostensaetzePanel2014.Builder(this)
				.showPersonal1()
				.showMaschine2()
				.build();
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
		
		panel.setLabelProduktivitaet(GuiStrings.getString("Vorruecken2018.ProduktivitaetEquipe_m3iR")); //$NON-NLS-1$
		panel.setLabelProduktivitaet2(GuiStrings.getString("HeProMoWindow2014.m3_oR_pro_PMH15")); //$NON-NLS-1$
		panel.setLabelKostenProM3(GuiStrings.getString("HeProMoWindow2014.ProM3oR")); //$NON-NLS-1$
		
		return panel;
	}

	
	@Override
	protected void initData() {
		String labelMaschine1 = getModel().getArbeitssystem().getLabelMaschine1();
		String labelMaschine2 = getModel().getArbeitssystem().getLabelMaschine2();
		
		panelKostensaetze.setLabelPersonal(GuiStrings.getString("Vorruecken2018.PersonalProPers")); //$NON-NLS-1$
		panelKostensaetze.setLabelMaschine1(labelMaschine1);
		panelKostensaetze.setLabelMaschine2(labelMaschine2);
		
		
		txtHolzmenge_m3.setModel(new SpinnerNumberModel(50.0, 0, 100000, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3);
		txtHolzmenge_m3.addChangeListener(evt -> {
			updateLabelHolzmenge_m3iR();
		});
		
		
		txtBhd_cm.setModel(new SpinnerNumberModel(20, 10, 30, 1));
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtBhd_cm);
		
		
		for (Standortguete guete : Standortguete.values()) {
			cmbStandortguete.addItem(guete);
		}
		addDefaultActionListener(cmbStandortguete);
		
		
		txtMittelstammvolumen.setModel(new SpinnerNumberModel(0.5, 0, 10, 0.1));
		txtMittelstammvolumen.setEnabled(false);
		
		
		for (Hangneigung hangneigung : Hangneigung.values()) {
			cmbHangneigung_Prz.addItem(hangneigung);
		}
		addDefaultActionListener(cmbHangneigung_Prz);
		
		
		addDefaultItemListener(chkAbzopfenDerKrone);
		
		
		for (Maschinentyp typ : Maschinentyp.values()) {
			cmbMaschinentyp.addItem(typ);
		}
		addDefaultActionListener(cmbMaschinentyp);
		cmbMaschinentyp.addActionListener(evt -> {
			boolean isRueckeraupeSelected = cmbMaschinentyp.getSelectedItem() == Maschinentyp.Rueckeraupe;
			cmbProduktivitaetssteigerung.setEnabled(isRueckeraupeSelected);
		});
		
		
		cmbProduktivitaetssteigerung.addItem(0.95);
		cmbProduktivitaetssteigerung.addItem(1.00);
		cmbProduktivitaetssteigerung.addItem(1.05);
		cmbProduktivitaetssteigerung.addItem(1.10);
		cmbProduktivitaetssteigerung.addItem(1.15);
		cmbProduktivitaetssteigerung.addItem(1.20);
		cmbProduktivitaetssteigerung.addItem(1.25);
		cmbProduktivitaetssteigerung.addItem(1.30);
		addDefaultActionListener(cmbProduktivitaetssteigerung);
	}
	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbMaschinentyp.equals(eventSource)) {
			SwingUtilities.invokeLater(() -> {
				String message = GuiStrings.getString("Vorruecken2018.WarnungKostensatz"); //$NON-NLS-1$
				JOptionPane.showMessageDialog(Vorruecken2018.this, message, GuiStrings.getString("Vorruecken2018.Achtung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
			});
		}
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmenge_m3.getValue();
		value *= getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("BestandPanelMotormanuellGesamt2014.oR") + ")</html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
	

	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		updateLabelHolzmenge_m3iR(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(        ((Double) txtHolzmenge_m3.getValue()) 			);
		model.getArbeitsobjekt().setBhd_cm(  			((Integer) txtBhd_cm.getValue()) 				);	
		model.getArbeitsobjekt().setStandortguete( (Standortguete) cmbStandortguete.getSelectedItem() 	);
		model.getArbeitsobjekt().setHangneigung(	 (Hangneigung) cmbHangneigung_Prz.getSelectedItem()	);
		model.getArbeitsobjekt().setAbzopfenDerKrone(			   chkAbzopfenDerKrone.isSelected()		);
		
		//Kann angezeigt werden, sobald BHD und Standortgüte im Modell
		txtMittelstammvolumen.setValue(model.getArbeitsobjekt().getMittelstammvolumen());
		
		
		//Arbeitssystem
		model.getArbeitssystem().setMaschinentyp( 				  ((Maschinentyp) cmbMaschinentyp.getSelectedItem()) 	  );
		model.getArbeitssystem().setProduktivtaetssteigerungRueckeraupe( (Double) cmbProduktivitaetssteigerung.getSelectedItem() );
		model.getArbeitssystem().setKostensatzMaschine2_proH( panelKostensaetze.getAnsatzMaschine2() );
	}

	
	@Override
	public AbstractModel getModel() {
		return model;
	}
	
	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);
		
		
		//Arbeitsobjekt
		txtHolzmenge_m3.setValue( 		  							 	  data.getArbeitsobjekt().getHolzmenge_m3() 		);
		updateLabelHolzmenge_m3iR();
		txtBhd_cm.setValue( 		  		((ArbeitsobjektVorruecken2018)data.getArbeitsobjekt()).getBhd_cm() 				);
		cmbStandortguete.setSelectedItem( 	((ArbeitsobjektVorruecken2018)data.getArbeitsobjekt()).getStandortguete() 		);
		txtMittelstammvolumen.setValue(   	((ArbeitsobjektVorruecken2018)data.getArbeitsobjekt()).getMittelstammvolumen()	);
		cmbHangneigung_Prz.setSelectedItem(	((ArbeitsobjektVorruecken2018)data.getArbeitsobjekt()).getHangneigung() 		);
		chkAbzopfenDerKrone.setSelected(  	((ArbeitsobjektVorruecken2018)data.getArbeitsobjekt()).isAbzopfenDerKrone() 	);
		
		
		//Arbeitssystem
		panelKostensaetze.setAnsatzMaschine2( 								 data.getArbeitssystem().getKostensatzMaschine2_proH()  );
		cmbMaschinentyp.setSelectedItem(  	   ((ArbeitssystemVorruecken2018)data.getArbeitssystem()).getMaschinentyp() 			);
		cmbProduktivitaetssteigerung.setSelectedItem( ((ArbeitssystemVorruecken2018)data.getArbeitssystem()).getProduktivtaetssteigerungRueckeraupe()	);
		
		super.setReactOnInputChange(true);
	}

}
