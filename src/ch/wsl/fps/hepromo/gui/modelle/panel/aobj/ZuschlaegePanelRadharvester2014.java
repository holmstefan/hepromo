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
package ch.wsl.fps.hepromo.gui.modelle.panel.aobj;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.Foermigkeit;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.FoermigkeitArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.Hangneigung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.HangneigungArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LaubholzAnteil;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LaubholzAnteilArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LiegendesHolz;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LiegendesHolzArrayWithSelection;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZuschlaegePanelRadharvester2014 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<Foermigkeit> cmbFoermigkeit;
	private JComboBox<LaubholzAnteil> cmbLaubholzAnteil;
	private JComboBox<LiegendesHolz> cmbLiegendesHolz;
	private JComboBox<Hangneigung> cmbHangneigung;

	private JSpinner txtFoermigkeitWert;
	private JSpinner txtLaubholzAnteil;
	private JSpinner txtLiegendesHolz;
	private JSpinner txtHangneigung;
	
	private JSpinner txtTotal;

	private FoermigkeitArrayWithSelection foermigkeitArrayWithSelection;
	private LaubholzAnteilArrayWithSelection laubholzAnteilArrayWithSelection;
	private LiegendesHolzArrayWithSelection liegendesHolzArrayWithSelection;
	private HangneigungArrayWithSelection hangneigungArrayWithSelection;
	
	
	public ZuschlaegePanelRadharvester2014(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("ZuschlaegePanelRadharvester2014.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		
		//Label Förmigkeit
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblFoermigkeit = new JLabel(GuiStrings.getString("ZuschlaegePanelRadharvester2014.Foermigkeit")); //$NON-NLS-1$
		this.add(lblFoermigkeit, c);
		
		//Combo Förmigkeit
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbFoermigkeit = new JComboBox<>();
		this.add(cmbFoermigkeit, c);
		
		//Text Förmigkeit
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		txtFoermigkeitWert = new JSpinner();
		this.add(txtFoermigkeitWert, c);


		
		//Label Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblAnteilLaubholz = new JLabel(GuiStrings.getString("ZuschlaegePanelRadharvester2014.AnteilLaubholz")); //$NON-NLS-1$
		this.add(lblAnteilLaubholz, c);
		
		//Combo Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbLaubholzAnteil = new JComboBox<>();
		this.add(cmbLaubholzAnteil, c);
		
		//Text Anteil Laubholz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		txtLaubholzAnteil = new JSpinner();
		this.add(txtLaubholzAnteil, c);


		
		//Label Liegendes Holz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblLiegendesHolz = new JLabel(GuiStrings.getString("ZuschlaegePanelRadharvester2014.LiegendesHolz")); //$NON-NLS-1$
		this.add(lblLiegendesHolz, c);
		
		//Combo Liegendes Holz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbLiegendesHolz = new JComboBox<>();
		this.add(cmbLiegendesHolz, c);
		
		//Text Liegendes Holz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		txtLiegendesHolz = new JSpinner();
		this.add(txtLiegendesHolz, c);
		
		//InfoButton Liegendes Holz
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,2,0,7);
		JLabel lblLiegendesHolz_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ZuschlaegePanelRadharvester2014.InfoButtonLiegendesHolz")); //$NON-NLS-1$
		lblLiegendesHolz_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblLiegendesHolz_Info, c);


		
		//Label Hangneigung
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblHangneigung = new JLabel(GuiStrings.getString("ZuschlaegePanelRadharvester2014.Hangneigung")); //$NON-NLS-1$
		this.add(lblHangneigung, c);
		
		//Combo Hangneigung
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbHangneigung = new JComboBox<>();
		this.add(cmbHangneigung, c);
		
		//Text Hangneigung
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		txtHangneigung = new JSpinner();
		this.add(txtHangneigung, c);
		
		//InfoButton Hangneigung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,2,0,7);
		JLabel lblHangneigung_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Thw.InfoButtonHangneigung")); //$NON-NLS-1$
		lblHangneigung_Info.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblHangneigung_Info, c);


		
		//Label Total
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,5,0,5);
		JLabel lblTotal = new JLabel(GuiStrings.getString("ZuschlaegePanelRadharvester2014.TotalZuAbschlaege"), SwingConstants.RIGHT); //$NON-NLS-1$
		this.add(lblTotal, c);
		
		//Text Total
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		txtTotal = new JSpinner();
		this.add(txtTotal, c);
	}
	
	
	private void initData(){

		//Förmigkeit
		txtFoermigkeitWert.setModel(new SpinnerNumberModel(0.0, -0.1, 0.1, 0.01));
		txtFoermigkeitWert.setEditor(new JSpinner.NumberEditor(txtFoermigkeitWert, "0.## %")); //$NON-NLS-1$
		setColNr(txtFoermigkeitWert);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtFoermigkeitWert, true);
		
		
		//Anteil Laubholz
		txtLaubholzAnteil.setModel(new SpinnerNumberModel(0.0, -0.15001, 0.0, 0.01));
		txtLaubholzAnteil.setEditor(new JSpinner.NumberEditor(txtLaubholzAnteil, "0.## %")); //$NON-NLS-1$
		setColNr(txtLaubholzAnteil);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaubholzAnteil, true);
		
		
		//Liegendes Holz
		txtLiegendesHolz.setModel(new SpinnerNumberModel(0.0, -0.4, 0.4, 0.01));
		txtLiegendesHolz.setEditor(new JSpinner.NumberEditor(txtLiegendesHolz, "0.## %")); //$NON-NLS-1$
		setColNr(txtLiegendesHolz);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLiegendesHolz, true);
		
		
		//Hangneigung
		txtHangneigung.setModel(new SpinnerNumberModel(0.0, -0.2, 0.0, 0.01));
		txtHangneigung.setEditor(new JSpinner.NumberEditor(txtHangneigung, "0.## %")); //$NON-NLS-1$
		setColNr(txtHangneigung);
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHangneigung, true);

		
		//init ComboBoxes
		setFoermigkeitArrayWithSelection( new FoermigkeitArrayWithSelection() );
		setLaubholzAnteilArrayWithSelection( new LaubholzAnteilArrayWithSelection() );
		setLiegendesHolzArrayWithSelection( new LiegendesHolzArrayWithSelection() );
		setHangneigungArrayWithSelection( new HangneigungArrayWithSelection() );
		
		
		
		
		//Total
		txtTotal.setEnabled(false);
		txtTotal.setModel(new SpinnerNumberModel(0.0, -1, 1, 1));
		txtTotal.setEditor(new JSpinner.NumberEditor(txtTotal, "0.## %")); //$NON-NLS-1$
		setColNr(txtTotal);
		calculateAndShowTotal();
	}
	
	
	private void setColNr(JSpinner spinner) {
		 int nrOfCols = 6;
		((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setColumns(nrOfCols);
	}

	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {

		if (txtFoermigkeitWert.equals(eventSource)) {
			Foermigkeit selection = (Foermigkeit) cmbFoermigkeit.getSelectedItem();
			if ( selection.isBenutzerdefiniert() ) {
				selection.setWert( (Double) txtFoermigkeitWert.getValue() ) ;
				cmbFoermigkeit.repaint();
			}
			calculateAndShowTotal();
		}
		
		
		if (txtLaubholzAnteil.equals(eventSource)) {
			LaubholzAnteil selection = (LaubholzAnteil) cmbLaubholzAnteil.getSelectedItem();
			if ( selection.isBenutzerdefiniert() ) {
				selection.setWert( (Double) txtLaubholzAnteil.getValue() ) ;
				cmbLaubholzAnteil.repaint();
			}
			calculateAndShowTotal();
		}
		
		
		if (txtLiegendesHolz.equals(eventSource)) {
			LiegendesHolz selection = (LiegendesHolz) cmbLiegendesHolz.getSelectedItem();
			if ( selection.isBenutzerdefiniert() ) {
				selection.setWert( (Double) txtLiegendesHolz.getValue() ) ;
				cmbLiegendesHolz.repaint();
			}
			calculateAndShowTotal();
		}
		
		
		if (txtHangneigung.equals(eventSource)) {
			Hangneigung selection = (Hangneigung) cmbHangneigung.getSelectedItem();
			if ( selection.isBenutzerdefiniert() ) {
				selection.setWert( (Double) txtHangneigung.getValue() ) ;
				cmbHangneigung.repaint();
			}
			calculateAndShowTotal();
		}
	}
	
	
	
	private void calculateAndShowTotal() {
		double foermigkeit 	  = (Double) txtFoermigkeitWert.getValue();
		double anteilLaubholz = (Double) txtLaubholzAnteil.getValue();
		double liegendesHolz  = (Double) txtLiegendesHolz.getValue();
		double hangneigung  = (Double) txtHangneigung.getValue();
		
		double total =
				foermigkeit +
				anteilLaubholz +
				liegendesHolz +
				hangneigung;
		
		txtTotal.setValue(total);
	}
	
	


	public FoermigkeitArrayWithSelection getFoermigkeitArrayWithSelection() {
		return foermigkeitArrayWithSelection;
	}

	public void setFoermigkeitArrayWithSelection(FoermigkeitArrayWithSelection foermigkeitArrayWithSelection) {
		this.foermigkeitArrayWithSelection = foermigkeitArrayWithSelection;
		
		//remove old action listeners
		for (ActionListener listener : cmbFoermigkeit.getActionListeners()) {
			cmbFoermigkeit.removeActionListener(listener);
		}
		
		//refill combobox
		cmbFoermigkeit.removeAllItems();
		for (Foermigkeit value : foermigkeitArrayWithSelection.allValues) {
			cmbFoermigkeit.addItem(value);
		}
		
		//add action listener
		cmbFoermigkeit.addActionListener(evt -> { //braucht keinen DefaultActionListener -> Änderungen werden beim JSpinner abgefangen
			Foermigkeit selectedItem = (Foermigkeit) ((JComboBox<?>)evt.getSource()).getSelectedItem();
			if (selectedItem != null) {
				ZuschlaegePanelRadharvester2014.this.foermigkeitArrayWithSelection.setSelection(selectedItem);
				txtFoermigkeitWert.setEnabled(  selectedItem.isBenutzerdefiniert() );
				txtFoermigkeitWert.setValue( selectedItem.getWert() );
			}
		});
		
		//select correct item
		cmbFoermigkeit.setSelectedItem( foermigkeitArrayWithSelection.getSelection() );
		calculateAndShowTotal();
	}
	
	
	public LaubholzAnteilArrayWithSelection getLaubholzAnteilArrayWithSelection() {
		return laubholzAnteilArrayWithSelection;
	}

	public void setLaubholzAnteilArrayWithSelection(LaubholzAnteilArrayWithSelection laubholzAnteilArrayWithSelection) {
		this.laubholzAnteilArrayWithSelection = laubholzAnteilArrayWithSelection;
		
		//remove old action listeners
		for (ActionListener listener : cmbLaubholzAnteil.getActionListeners()) {
			cmbLaubholzAnteil.removeActionListener(listener);
		}
		
		//refill combobox
		cmbLaubholzAnteil.removeAllItems();
		for (LaubholzAnteil value : laubholzAnteilArrayWithSelection.allValues) {
			cmbLaubholzAnteil.addItem(value);
		}
		
		//add action listener
		cmbLaubholzAnteil.addActionListener(evt -> { //braucht keinen DefaultActionListener -> Änderungen werden beim JSpinner abgefangen
			LaubholzAnteil selectedItem = (LaubholzAnteil) ((JComboBox<?>)evt.getSource()).getSelectedItem();
			if (selectedItem != null) {
				ZuschlaegePanelRadharvester2014.this.laubholzAnteilArrayWithSelection.setSelection(selectedItem);
				txtLaubholzAnteil.setEnabled(  selectedItem.isBenutzerdefiniert() );
				txtLaubholzAnteil.setValue( selectedItem.getWert() );
			}
		});
		
		//select correct item
		cmbLaubholzAnteil.setSelectedItem( laubholzAnteilArrayWithSelection.getSelection() );
		calculateAndShowTotal();
	}


	public LiegendesHolzArrayWithSelection getLiegendesHolzArrayWithSelection() {
		return liegendesHolzArrayWithSelection;
	}

	public void setLiegendesHolzArrayWithSelection(LiegendesHolzArrayWithSelection liegendesHolzArrayWithSelection) {
		this.liegendesHolzArrayWithSelection = liegendesHolzArrayWithSelection;
		
		//remove old action listeners
		for (ActionListener listener : cmbLiegendesHolz.getActionListeners()) {
			cmbLiegendesHolz.removeActionListener(listener);
		}
		
		//refill combobox
		cmbLiegendesHolz.removeAllItems();
		for (LiegendesHolz value : liegendesHolzArrayWithSelection.allValues) {
			cmbLiegendesHolz.addItem(value);
		}
		
		//add action listener
		cmbLiegendesHolz.addActionListener(evt -> { //braucht keinen DefaultActionListener -> Änderungen werden beim JSpinner abgefangen
			LiegendesHolz selectedItem = (LiegendesHolz) ((JComboBox<?>)evt.getSource()).getSelectedItem();
			if (selectedItem != null) {
				ZuschlaegePanelRadharvester2014.this.liegendesHolzArrayWithSelection.setSelection(selectedItem);
				txtLiegendesHolz.setEnabled(  selectedItem.isBenutzerdefiniert() );
				txtLiegendesHolz.setValue( selectedItem.getWert() );
			}
		});
		
		//select correct item
		cmbLiegendesHolz.setSelectedItem( liegendesHolzArrayWithSelection.getSelection() );
		calculateAndShowTotal();
	}


	public HangneigungArrayWithSelection getHangneigungArrayWithSelection() {
		return hangneigungArrayWithSelection;
	}

	public void setHangneigungArrayWithSelection(HangneigungArrayWithSelection hangneigungArrayWithSelection) {
		this.hangneigungArrayWithSelection = hangneigungArrayWithSelection;
		
		//remove old action listeners
		for (ActionListener listener : cmbHangneigung.getActionListeners()) {
			cmbHangneigung.removeActionListener(listener);
		}
		
		//refill combobox
		cmbHangneigung.removeAllItems();
		for (Hangneigung value : hangneigungArrayWithSelection.allValues) {
			cmbHangneigung.addItem(value);
		}
		
		//add action listener
		cmbHangneigung.addActionListener(evt -> { //braucht keinen DefaultActionListener -> Änderungen werden beim JSpinner abgefangen
			Hangneigung selectedItem = (Hangneigung) ((JComboBox<?>)evt.getSource()).getSelectedItem();
			if (selectedItem != null) {
				ZuschlaegePanelRadharvester2014.this.hangneigungArrayWithSelection.setSelection(selectedItem);
				txtHangneigung.setEnabled(  selectedItem.isBenutzerdefiniert() );
				txtHangneigung.setValue( selectedItem.getWert() );
			}
		});
		
		//select correct item
		cmbHangneigung.setSelectedItem( hangneigungArrayWithSelection.getSelection() );
		calculateAndShowTotal();
	}

}
