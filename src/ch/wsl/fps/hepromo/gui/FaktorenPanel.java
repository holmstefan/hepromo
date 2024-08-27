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
package ch.wsl.fps.hepromo.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.PersistentInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.calc.AbstractCalculatorSingleModel;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class FaktorenPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JToggleButton btnExpand;
	private JLabel lblRisikoVerwaltungGewinn;
	private JSpinner txtRisikoVerwaltungGewinn;
	private JLabel lblRisiko;
	private JSpinner txtRisiko;
	private JLabel lblVerwaltung;
	private JSpinner txtVerwaltung;
	private JLabel lblGewinn;
	private JSpinner txtGewinn;
	
	private JSpinner txtMehrwertsteuer;
	private JTextField txtWaehrungskuerzel;
	private JCheckBox chkRindenabzugAutomatisch;
	private JSpinner txtRindenabzug = new JSpinner(new SpinnerNumberModel(0.6, 0.5, 1.0, 0.01));
	private JPanel pnlWeitereFaktoren;
	private JSpinner txtKorrekturfaktor;
	private JLabel lblKorrekturfaktor_Info;
	private JLabel lblFaktorStoerungen;
	private JLabel lblFaktorVerlusteBis15Min;
	private JLabel lblFaktorIndirekteArbeitszeit;
	private JTextField txtFaktorStoerungen;
	private JTextField txtFaktorVerlusteBis15Min;
	private JTextField txtFaktorIndirekteArbeitszeit;
	private JTextField txtFaktorWegzeitenUndPausen;
	private JLabel lblFaktorWegzeitenUndPausen_Info;

	private String infoTextKF = 
			"<html>" +  //$NON-NLS-1$
			GuiStrings.getString("FaktorenPanel.InfoTextKorrekturFaktorZeile1") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			GuiStrings.getString("FaktorenPanel.InfoTextKorrekturFaktorZeile2") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			GuiStrings.getString("FaktorenPanel.InfoTextKorrekturFaktorZeile3") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			GuiStrings.getString("FaktorenPanel.InfoTextKorrekturFaktorZeile4") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			GuiStrings.getString("FaktorenPanel.InfoTextKorrekturFaktorZeile5") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
			GuiStrings.getString("FaktorenPanel.InfoTextKorrekturFaktorZeile6") + "</html>"; //$NON-NLS-1$ //$NON-NLS-2$
	
	
	public FaktorenPanel(HeProMoWindow parent) {
		this.parent = parent;
		initContent();
	}
	
	
	private void initContent() {

		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		
		//Erste Reihe
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,10,0,5);
		JPanel row1 = new JPanel();
		row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
		this.add(row1, c);
		
		//Button "+"
		btnExpand = new JToggleButton("+"); //$NON-NLS-1$
		btnExpand.setMargin(new Insets(1, 5, 1, 5));
		btnExpand.addActionListener(evt -> {
			changeExpandRisikoVerwaltungGewinn();
		});
		parent.addDefaultItemListener(btnExpand);
		row1.add(btnExpand);
		row1.add(Box.createHorizontalStrut(5));
		
		//Label Risiko, Verwaltung, Gewinn
		lblRisikoVerwaltungGewinn = new JLabel(GuiStrings.getString("FaktorenPanel.RisikoVerwaltungGewinn")); //$NON-NLS-1$
		row1.add(lblRisikoVerwaltungGewinn);
		row1.add(Box.createHorizontalStrut(5));
		
		//Text Risiko, Verwaltung, Gewinn
		txtRisikoVerwaltungGewinn = new JSpinner(new SpinnerNumberModel(0, 0, 200, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtRisikoVerwaltungGewinn);
		txtRisikoVerwaltungGewinn.setMaximumSize(new Dimension(60, 20));
		row1.add(txtRisikoVerwaltungGewinn);
		row1.add(Box.createHorizontalStrut(15));
		
		//Label Risiko
		lblRisiko = new JLabel(GuiStrings.getString("FaktorenPanel.Risiko")); //$NON-NLS-1$
		row1.add(lblRisiko);
		row1.add(Box.createHorizontalStrut(5));
		
		//Text Risiko
		txtRisiko = new JSpinner(new SpinnerNumberModel(0, 0, 200, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtRisiko);
		txtRisiko.setMaximumSize(new Dimension(60, 20));
		row1.add(txtRisiko);
		row1.add(Box.createHorizontalStrut(15));
		
		//Label Verwaltung
		lblVerwaltung = new JLabel(GuiStrings.getString("FaktorenPanel.Verwaltung")); //$NON-NLS-1$
		row1.add(lblVerwaltung);
		row1.add(Box.createHorizontalStrut(5));
		
		//Text Verwaltung
		txtVerwaltung = new JSpinner(new SpinnerNumberModel(0, 0, 200, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtVerwaltung);
		txtVerwaltung.setMaximumSize(new Dimension(60, 20));
		row1.add(txtVerwaltung);
		row1.add(Box.createHorizontalStrut(15));
		
		//Label Gewinn
		lblGewinn = new JLabel(GuiStrings.getString("FaktorenPanel.Gewinn")); //$NON-NLS-1$
		row1.add(lblGewinn);
		row1.add(Box.createHorizontalStrut(5));
		
		//Text Gewinn
		txtGewinn = new JSpinner(new SpinnerNumberModel(0, 0, 200, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtGewinn);
		txtGewinn.setMaximumSize(new Dimension(60, 20));
		row1.add(txtGewinn);
		row1.add(Box.createHorizontalStrut(15));
		
		//InfoButton Risiko, Verwaltung, Gewinn
		JLabel lblRisikoVerwaltungGewinn_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("FaktorenPanel.InfoButtonZuschlag")); //$NON-NLS-1$
		lblRisikoVerwaltungGewinn_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		row1.add(lblRisikoVerwaltungGewinn_Info);
		row1.add(Box.createHorizontalStrut(15));
;
		
		
		
		//Zweite Reihe
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,10,0,5);
		JPanel row2 = new JPanel();
		row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
		this.add(row2, c);
		
		//Label Mehrwertsteuer
		JLabel lblMehrwertsteuer = new JLabel(GuiStrings.getString("FaktorenPanel.Mehrwertsteuer")); //$NON-NLS-1$
		row2.add(lblMehrwertsteuer);
		row2.add(Box.createHorizontalStrut(5));
		
		//Text Mehrwertsteuer
		txtMehrwertsteuer = new JSpinner(new SpinnerNumberModel(0, 0, 200, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMehrwertsteuer);
		txtMehrwertsteuer.setMaximumSize(new Dimension(60, 20));
		row2.add(txtMehrwertsteuer);
		row2.add(Box.createHorizontalStrut(5));
		
		//InfoButton Mehrwertsteuer
		JLabel lblMehrwertsteuer_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("FaktorenPanel.InfoButtonZuschlag")); //$NON-NLS-1$
		lblMehrwertsteuer_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		row2.add(lblMehrwertsteuer_Info);
		row2.add(Box.createHorizontalStrut(15));
		
		//Label Waehrungskuerzel
		JLabel lblWaehrungsKuerzel = new JLabel(GuiStrings.getString("FaktorenPanel.Waehrungskuerzel")); //$NON-NLS-1$
		row2.add(lblWaehrungsKuerzel);
		row2.add(Box.createHorizontalStrut(5));
		
		//Text Waehrungskuerzel
		txtWaehrungskuerzel = new JTextField();
		txtWaehrungskuerzel.setMaximumSize(new Dimension(60, 20));
		parent.addDefaultDocumentListener(txtWaehrungskuerzel);
		row2.add(txtWaehrungskuerzel);
		
		
		
		//Dritte Reihe
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,0,5);
		JPanel row3 = new JPanel();
		row3.setLayout(new BoxLayout(row3, BoxLayout.X_AXIS));
		this.add(row3, c);
		
		//Checkbox Rindenabzug
		chkRindenabzugAutomatisch = new JCheckBox(GuiStrings.getString("FaktorenPanel.Umrechnungsfaktor_iR_oR_automatisch")); //$NON-NLS-1$
		parent.addDefaultItemListener(chkRindenabzugAutomatisch);
		chkRindenabzugAutomatisch.addItemListener(evt -> {
			txtRindenabzug.setEnabled( ! ((JCheckBox)evt.getSource()).isSelected() );
		});
		chkRindenabzugAutomatisch.setSelected(true);
		row3.add(chkRindenabzugAutomatisch);
		row3.add(Box.createHorizontalStrut(5));
		
		//Text Rindenabzug
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtRindenabzug);
		txtRindenabzug.setMaximumSize(new Dimension(60, 20));
		txtRindenabzug.setEnabled(false);
		row3.add(txtRindenabzug);
		

		
		
		//Panel Weitere Faktoren
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(20,0,0,0);
		pnlWeitereFaktoren = initPanelWeitereFaktoren();
		this.add(pnlWeitereFaktoren, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
		this.add(new JPanel(), c);
	}
	
	
	private JPanel initPanelWeitereFaktoren() {
		JPanel pnlWeitereFaktoren = new JPanel();
		pnlWeitereFaktoren.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("FaktorenPanel.TitleWeitereFaktoren"))); //$NON-NLS-1$
		

		//set layout
		pnlWeitereFaktoren.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//Label Betriebsspezifischer Korrekturfaktor
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,5);
		JLabel lblKorrekturfaktor = new JLabel(GuiStrings.getString("FaktorenPanel.BetriebsspezifischerKorrekturfaktor")); //$NON-NLS-1$
		pnlWeitereFaktoren.add(lblKorrekturfaktor, c);
		
		//Text Betriebsspezifischer Korrekturfaktor
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtKorrekturfaktor = new JSpinner(new SpinnerNumberModel(1, 0.1, 10, 0.01));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtKorrekturfaktor);
		pnlWeitereFaktoren.add(txtKorrekturfaktor, c);
		
		//Info Betriebsspezifischer Korrekturfaktor
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		lblKorrekturfaktor_Info = GuiStrings.getInfoButtonBlue(infoTextKF);
		pnlWeitereFaktoren.add(lblKorrekturfaktor_Info, c);
		
		
		
		
		//Label Stoerungen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		lblFaktorStoerungen = new JLabel(GuiStrings.getString("FaktorenPanel.Stoerungen")); //$NON-NLS-1$
		pnlWeitereFaktoren.add(lblFaktorStoerungen, c);
		
		//Text Stoerungen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFaktorStoerungen = new JTextField("--"); //$NON-NLS-1$
		txtFaktorStoerungen.setEditable(false);
		pnlWeitereFaktoren.add(txtFaktorStoerungen, c);
		
		
		
		
		//Label Verluste bis 15min
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		lblFaktorVerlusteBis15Min = new JLabel(GuiStrings.getString("FaktorenPanel.VerlusteBis15min")); //$NON-NLS-1$
		pnlWeitereFaktoren.add(lblFaktorVerlusteBis15Min, c);
		
		//Text Verluste bis 15min
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFaktorVerlusteBis15Min = new JTextField("--"); //$NON-NLS-1$
		txtFaktorVerlusteBis15Min.setEditable(false);
		pnlWeitereFaktoren.add(txtFaktorVerlusteBis15Min, c);
		
		
		
		
		//Label Indirekte Arbeitszeit
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		lblFaktorIndirekteArbeitszeit = new JLabel(GuiStrings.getString("FaktorenPanel.IndirekteArbeitszeit")); //$NON-NLS-1$
		pnlWeitereFaktoren.add(lblFaktorIndirekteArbeitszeit, c);
		
		//Text Indirekte Arbeitszeit
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtFaktorIndirekteArbeitszeit = new JTextField("--"); //$NON-NLS-1$
		txtFaktorIndirekteArbeitszeit.setEditable(false);
		pnlWeitereFaktoren.add(txtFaktorIndirekteArbeitszeit, c);
		
		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 2;
//		c.gridy = 0;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 10;
//		pnlWeitereFaktoren.add(new JPanel(), c);
		
		
		
		
		//Label Wegzeiten und Pausen
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblFaktorWegzeitenUndPausen = new JLabel(GuiStrings.getString("FaktorenPanel.WegzeitenUndPausen")); //$NON-NLS-1$
		pnlWeitereFaktoren.add(lblFaktorWegzeitenUndPausen, c);
		
		//Text Wegzeiten und Pausen
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,0,5);
		txtFaktorWegzeitenUndPausen = new JTextField("--"); //$NON-NLS-1$
		txtFaktorWegzeitenUndPausen.setEditable(false);
		pnlWeitereFaktoren.add(txtFaktorWegzeitenUndPausen, c);
		
		//Info Wegzeiten und Pausen
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.insets = new Insets(0,5,0,5);
		lblFaktorWegzeitenUndPausen_Info = GuiStrings.getInfoButtonBlue(
				"<html>" +  //$NON-NLS-1$
				GuiStrings.getString("FaktorenPanel.InfoTextWegzeitenUndPausenZeile1") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
				GuiStrings.getString("FaktorenPanel.InfoTextWegzeitenUndPausenZeile2") + "<br>" + //$NON-NLS-1$ //$NON-NLS-2$
				GuiStrings.getString("FaktorenPanel.InfoTextWegzeitenUndPausenZeile3") + "</html>" ); //$NON-NLS-1$ //$NON-NLS-2$
		pnlWeitereFaktoren.add(lblFaktorWegzeitenUndPausen_Info, c);		
		
		return pnlWeitereFaktoren;
	}
	
	
	/**
	 * Lädt die veränderbaren Faktoren ins GUI
	 */
	public void loadFaktoren(HeProMoInputData data) {
		btnExpand.setSelected(				data.getFaktoren().isRisikoVerwaltungGewinnMerged() == false);
		txtRisikoVerwaltungGewinn.setValue(	data.getFaktoren().getMargin()				);
		txtRisiko.setValue(					data.getFaktoren().getRisiko()				);
		txtVerwaltung.setValue(				data.getFaktoren().getVerwaltung()			);
		txtGewinn.setValue(					data.getFaktoren().getGewinn()				);
		txtMehrwertsteuer.setValue(			data.getFaktoren().getMehrwertsteuer()		);
		txtWaehrungskuerzel.setText( 		data.getFaktoren().getWaehrungskuerzel()	);
		txtKorrekturfaktor.setValue(		data.getFaktoren().getKorrekturFaktor()		);
		
		changeExpandRisikoVerwaltungGewinn();

		if (data instanceof AbstractModel2014 || data instanceof PersistentInputData && AbstractModel2014.class.isAssignableFrom(((PersistentInputData)data).getModelClass()) ) {
			Arbeitsobjekt ao = data.getArbeitsobjekt();
			chkRindenabzugAutomatisch.setSelected( ! ao.isRindenabzugsfaktorManuellGesetzt() );
			txtRindenabzug.setValue( ao.getRindenAbzugFaktor() );

			showRindenabzugFields(true);
		}
		else {
			showRindenabzugFields(false);
		}
	}
	
	
	/**
	 * Updated die nicht-veränderbaren Faktoren im GUI
	 */
	public void updateFaktorenPanel(AbstractModel model) {
		if (model.getCalculator() instanceof AbstractCalculatorSingleModel) { //Zusammengesetzte Modelle enthalten evt. verschiedene Faktoren in den Teilmodellen
			AbstractCalculatorSingleModel calculator = (AbstractCalculatorSingleModel) model.getCalculator();
			
			txtFaktorStoerungen.setText(			formatDouble( calculator.getFaktorStoerungen() )				);
			txtFaktorVerlusteBis15Min.setText(		formatDouble( calculator.getFaktorVerlusteBis15min() )			);
			txtFaktorIndirekteArbeitszeit.setText(	formatDouble( calculator.getFaktorIndirekteArbeitszeit() )		);
			
			lblFaktorStoerungen.setVisible(true);
			lblFaktorVerlusteBis15Min.setVisible(true);
			lblFaktorIndirekteArbeitszeit.setVisible(true);
			
			txtFaktorStoerungen.setVisible(true);
			txtFaktorVerlusteBis15Min.setVisible(true);
			txtFaktorIndirekteArbeitszeit.setVisible(true);
		}
		else {
			lblFaktorStoerungen.setVisible(false);
			lblFaktorVerlusteBis15Min.setVisible(false);
			lblFaktorIndirekteArbeitszeit.setVisible(false);
			
			txtFaktorStoerungen.setVisible(false);
			txtFaktorVerlusteBis15Min.setVisible(false);
			txtFaktorIndirekteArbeitszeit.setVisible(false);
		}
		
		txtFaktorWegzeitenUndPausen.setText(	formatDouble( model.getArbeitssystem().getFaktorWegzeitenUndPausen() )	);
		
		
		if (model instanceof AbstractModel2014) {
			lblFaktorVerlusteBis15Min.setVisible(false);
			txtFaktorVerlusteBis15Min.setVisible(false);
			
			if (chkRindenabzugAutomatisch.isSelected() == true) {
				txtRindenabzug.setValue( model.getArbeitsobjekt().getRindenAbzugFaktor() ); //Textfeld wieder auf Standardwert setzen
			}
		}
	}
	
	
	private static String formatDouble(double value) {
		DecimalFormat df = new DecimalFormat("0.0##"); //$NON-NLS-1$
		String result = df.format(value);
		return result;
	}
	
	
	/**
	 * Updated die veränderbaren Faktoren im Model entsprechend den aktuellen Eingaben im GUI
	 */
	public void loadGUIToModel(AbstractModel model) {
		model.getFaktoren().setRisikoVerwaltungGewinnMerged( btnExpand.isSelected() == false	);
		model.getFaktoren().setMargin(				(Double) txtRisikoVerwaltungGewinn.getValue() );
		model.getFaktoren().setRisiko(				(Double) txtRisiko.getValue() 			);
		model.getFaktoren().setVerwaltung(			(Double) txtVerwaltung.getValue() 		);
		model.getFaktoren().setGewinn(				(Double) txtGewinn.getValue() 			);
		model.getFaktoren().setMehrwertsteuer(		(Double) txtMehrwertsteuer.getValue()	);
		model.getFaktoren().setWaehrungskuerzel(	txtWaehrungskuerzel.getText()			);
		model.getFaktoren().setKorrekturFaktor(		(Double) txtKorrekturfaktor.getValue()	);
		
		if (model instanceof AbstractModel2014) {
			Arbeitsobjekt ao = model.getArbeitsobjekt();
			ao.setRindenabzugsfaktorManuellGesetzt( ! chkRindenabzugAutomatisch.isSelected() );
			
			if (chkRindenabzugAutomatisch.isSelected() == false) {
				ao.setRindenabzugsfaktorManuell( (Double) txtRindenabzug.getValue() );
			}
		}
	}
	
	
	public void showRindenabzugFields(boolean flag) {
		chkRindenabzugAutomatisch.setVisible(flag);
		txtRindenabzug.setVisible(flag);
	}
	
	
	public void showInfoButtonWegzeitenUndPausen(boolean flag) {
		lblFaktorWegzeitenUndPausen_Info.setVisible(flag);
	}
	
	
	private void changeExpandRisikoVerwaltungGewinn() {
		boolean isExpanded = btnExpand.isSelected();
		
		lblRisikoVerwaltungGewinn.setVisible(!isExpanded);
		txtRisikoVerwaltungGewinn.setVisible(!isExpanded);
		lblRisiko.setVisible(isExpanded);
		txtRisiko.setVisible(isExpanded);
		lblVerwaltung.setVisible(isExpanded);
		txtVerwaltung.setVisible(isExpanded);
		lblGewinn.setVisible(isExpanded);
		txtGewinn.setVisible(isExpanded);
	}
}
