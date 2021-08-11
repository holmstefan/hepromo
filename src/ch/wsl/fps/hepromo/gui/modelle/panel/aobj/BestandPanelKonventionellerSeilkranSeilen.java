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
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BestandPanelKonventionellerSeilkranSeilen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtHolzmengeAnSeillinie_m3iR;
	private JLabel lblHolzmenge_m3oR_readOnly;
	private JSpinner txtMittleresStueckvolumen_m3;
	private JSpinner txtStuecklaenge_m;
	
	protected JComboBox<Eingriffsart> cmbEingriffsart;
	private JComboBox<HolzSeilOrt> cmbHolzSeilOrt;
	
	
	public BestandPanelKonventionellerSeilkranSeilen(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("BestandPanelKonventionellerSeilkranSeilen.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		
		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblHolzmengeAnSeillinie_m3 = new JLabel(GuiStrings.getString("BestandPanelKonventionellerSeilkranSeilen.HolzmengeAnSeillinie")); //$NON-NLS-1$
		this.add(lblHolzmengeAnSeillinie_m3, c);
		
		//Text Holzmenge iR
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHolzmengeAnSeillinie_m3iR = new JSpinner();
		this.add(txtHolzmengeAnSeillinie_m3iR, c);
		
		//Label Holzmenge oR
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0,10,0,0);
		lblHolzmenge_m3oR_readOnly = GuiStrings.getInfoButtonBlue(GuiStrings.getString("Allgemein.lblInfoButtonUmrechnung_iRoR")); //$NON-NLS-1$
		lblHolzmenge_m3oR_readOnly.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(lblHolzmenge_m3oR_readOnly, c);
		
		
		
		//Label Mittleres Stückvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMittleresStueckvolumen_m3 = new JLabel(GuiStrings.getString("BestandPanelKonventionellerSeilkranSeilen.MittleresStueckvolumen")); //$NON-NLS-1$
		this.add(lblMittleresStueckvolumen_m3, c);
		
		//Text Mittleres Stückvolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittleresStueckvolumen_m3 = new JSpinner();
		this.add(txtMittleresStueckvolumen_m3, c);
		
		
		
		//Label Stücklänge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblStuecklaenge = new JLabel(GuiStrings.getString("BestandPanelKonventionellerSeilkranSeilen.Stuecklaenge_m")); //$NON-NLS-1$
		this.add(lblStuecklaenge, c);
		
		//Text Stücklänge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtStuecklaenge_m = new JSpinner();
		this.add(txtStuecklaenge_m, c);

		

		
		//Label Eingriffsart
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblEingriffsart = new JLabel(GuiStrings.getString("BestandPanelKonventionellerSeilkranSeilen.Eingriffsart")); //$NON-NLS-1$
		this.add(lblEingriffsart, c);
		
		//Combo Eingriffsart
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbEingriffsart = new JComboBox<>();
		this.add(cmbEingriffsart, c);
		

		
		//Label HolzSeilOrt
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		JLabel lblHolzSeilOrt = new JLabel(GuiStrings.getString("BestandPanelKonventionellerSeilkranSeilen.HolzWirdGeseilt")); //$NON-NLS-1$
		this.add(lblHolzSeilOrt, c);
		
		//Combo HolzSeilOrt
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbHolzSeilOrt = new JComboBox<>();
		this.add(cmbHolzSeilOrt, c);
	}
	
	
	private void initData(){
		
		txtMittleresStueckvolumen_m3.setModel(new SpinnerNumberModel(0.4, 0.01, 10, 0.1));
		txtMittleresStueckvolumen_m3.setEditor(new JSpinner.NumberEditor(txtMittleresStueckvolumen_m3, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittleresStueckvolumen_m3);
		
		txtHolzmengeAnSeillinie_m3iR.setModel(new SpinnerNumberModel(800.0, 1, 10000, 100));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmengeAnSeillinie_m3iR);
		txtHolzmengeAnSeillinie_m3iR.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateLabelHolzmenge_m3iR();
			}
		});
		
		txtStuecklaenge_m.setModel(new SpinnerNumberModel(5.0, 1, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtStuecklaenge_m);

		for (Eingriffsart value : Eingriffsart.values()) {
			cmbEingriffsart.addItem(value);
		}
		parent.addDefaultActionListener(cmbEingriffsart);

		for (HolzSeilOrt value : HolzSeilOrt.values()) {
			cmbHolzSeilOrt.addItem(value);
		}
		parent.addDefaultActionListener(cmbHolzSeilOrt);
	}
	
	
	public void updateLabelHolzmengeMitRindenabzug() {
		updateLabelHolzmenge_m3iR();
	}
	
	
	private void updateLabelHolzmenge_m3iR() {
		DecimalFormat df = new DecimalFormat(",###.##"); //$NON-NLS-1$
		double value = (Double) txtHolzmengeAnSeillinie_m3iR.getValue();
		value *= parent.getModel().getArbeitsobjekt().getRindenAbzugFaktor();
		lblHolzmenge_m3oR_readOnly.setText( "<html><nobr>(" +  df.format(value) + " m<sup>3</sup> " + GuiStrings.getString("Allgemein.oR") + ")</nobr></html>" ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	
	
	public double getMittleresStueckvolumen_m3() {
		return (Double) txtMittleresStueckvolumen_m3.getValue();
	}
	
	
	public double getHolzmengeAnSeillinie_m3iR() {
		return (Double) txtHolzmengeAnSeillinie_m3iR.getValue();
	}
	
	
	public double getStuecklaenge_m() {
		return (Double) txtStuecklaenge_m.getValue();
	}
	
	
	public Eingriffsart getEingriffsart() {
		return (Eingriffsart) cmbEingriffsart.getSelectedItem();
	}
	
	
	public HolzSeilOrt getHolzSeilOrt() {
		return (HolzSeilOrt) cmbHolzSeilOrt.getSelectedItem();
	}



	public void setMittleresStueckvolumen_m3(double mittleresStueckvolumen_m3) {
		txtMittleresStueckvolumen_m3.setValue(mittleresStueckvolumen_m3);
	}


	public void setHolzmengeAnSeillinie_m3iR(double holzmenge) {
		txtHolzmengeAnSeillinie_m3iR.setValue(holzmenge);
		updateLabelHolzmenge_m3iR();
	}


	public void setStuecklaenge_m(double stuecklaenge_m) {
		txtStuecklaenge_m.setValue(stuecklaenge_m);
	}


	public void setEingriffsart(Eingriffsart eingriffsart) {
		cmbEingriffsart.setSelectedItem(eingriffsart);
	}


	public void setHolzSeilOrt(HolzSeilOrt holzSeilOrt) {
		cmbHolzSeilOrt.setSelectedItem(holzSeilOrt);
	}
}
