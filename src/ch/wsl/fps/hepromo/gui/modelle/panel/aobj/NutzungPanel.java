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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
public class NutzungPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;
	
	private JSpinner txtNadelholzanteil_Prz;
	private JSpinner txtLaubholzanteil_Prz;
	private JSpinner txtHolzmenge_m3;
	private JSpinner txtDurchschnBhdAushieb_cm;
	private JSpinner txtDurchschnHolzlaenge_m;
	private JSpinner txtDurchschnAnzSortimente;
	private JComboBox<Integer> cmbAnzahlSortimente;
	private JSpinner txtAnteilKrumm_Prz;
	
	
	public NutzungPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("NutzungPanel.Title"))); //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Nadelholzanteil
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.insets = new Insets(0,0,0,5);
		JLabel lblNadelholzanteil = new JLabel(GuiStrings.getString("NutzungPanel.Nadelholzanteil_Prz")); //$NON-NLS-1$
		this.add(lblNadelholzanteil, c);
		
		//Text Nadelholzanteil
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
//		c.insets = new Insets(5,5,5,5);
		txtNadelholzanteil_Prz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); 
		txtNadelholzanteil_Prz.setEnabled(false);
		this.add(txtNadelholzanteil_Prz, c);

		
		//Label Laubholzanteil
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblLaubholzanteil = new JLabel(GuiStrings.getString("NutzungPanel.Laubholanteil_Prz")); //$NON-NLS-1$
		this.add(lblLaubholzanteil, c);
		
		//Text Laubholzanteil
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		txtLaubholzanteil_Prz = new JSpinner(new SpinnerNumberModel(50, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaubholzanteil_Prz);
		this.add(txtLaubholzanteil_Prz, c);

		
		//Label Holzmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblHolzmenge = new JLabel(GuiStrings.getString("NutzungPanel.Holzmenge_m3")); //$NON-NLS-1$
		this.add(lblHolzmenge, c);
		
		//Text Holzmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		txtHolzmenge_m3 = new JSpinner();
		this.add(txtHolzmenge_m3, c);

		
		//Label Durchschn. BHD
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblDurchschnBhd = new JLabel(GuiStrings.getString("NutzungPanel.BhdAushieb")); //$NON-NLS-1$
		this.add(lblDurchschnBhd, c);
		
		//Text Durchschn. BHD
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		txtDurchschnBhdAushieb_cm = new JSpinner(new SpinnerNumberModel(30, 1, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDurchschnBhdAushieb_cm);
		this.add(txtDurchschnBhdAushieb_cm, c);

		
		//Label Durchschn. Holzlänge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblDurchschnHolzlaenge = new JLabel(GuiStrings.getString("NutzungPanel.Holzlaenge")); //$NON-NLS-1$
		this.add(lblDurchschnHolzlaenge, c);
		
		//Text Durchschn. Holzlänge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		txtDurchschnHolzlaenge_m = new JSpinner(new SpinnerNumberModel(4, 3, 7, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDurchschnHolzlaenge_m);
		this.add(txtDurchschnHolzlaenge_m, c);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);

		//***************** 2. Spalte

		
		//Label Durchschn. Anzahl Sortimente
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridheight = 2;
		c.insets = new Insets(0,0,0,5);
		JLabel lblDurchschnAnzahlSortimente = new JLabel(GuiStrings.getString("NutzungPanel.SortimenteProFahrzyklus")); //$NON-NLS-1$
		this.add(lblDurchschnAnzahlSortimente, c);
		
		//Text Durchschn. Anzahl Sortimente
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.gridheight = 2;
//		c.insets = new Insets(5,5,5,5);
		txtDurchschnAnzSortimente = new JSpinner(new SpinnerNumberModel(1.6, 1.0, 20, 0.1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDurchschnAnzSortimente);
		this.add(txtDurchschnAnzSortimente, c);

		
		//Label Anzahl zu lagernde Sortimente
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.gridheight = 2;
		c.insets = new Insets(0,0,0,5);
		JLabel lblAnzahlZuLagerndeSortimente = new JLabel(GuiStrings.getString("NutzungPanel.ZuLagerndeSortimente")); //$NON-NLS-1$
		this.add(lblAnzahlZuLagerndeSortimente, c);
		
		//Combo Anzahl zu lagernde Sortimente
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.gridheight = 2;
//		c.insets = new Insets(5,5,5,5);
		cmbAnzahlSortimente = new JComboBox<>();
		this.add(cmbAnzahlSortimente, c);

		
		//Label Anteil krumm
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblAnteilKrumm = new JLabel(GuiStrings.getString("NutzungPanel.AnteilKrumm_Prz")); //$NON-NLS-1$
		this.add(lblAnteilKrumm, c);
		
		//Text Anteil krumm
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnteilKrumm_Prz = new JSpinner(new SpinnerNumberModel(40, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnteilKrumm_Prz);
		this.add(txtAnteilKrumm_Prz, c);
		
	}
	
	
	private void initData() {
		txtHolzmenge_m3.setModel(new SpinnerNumberModel(200.0, 0, 10000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHolzmenge_m3);
		
		
		for (int i=1; i<=4; i++) {
			cmbAnzahlSortimente.addItem(i);
		}
		parent.addDefaultActionListener(cmbAnzahlSortimente);
		cmbAnzahlSortimente.setSelectedItem(4);
	}
	
	
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (this.txtLaubholzanteil_Prz.equals(eventSource)) {
			txtNadelholzanteil_Prz.setValue(100 - getLaubholzAnteil());
		}
	}

	
	
	public int getLaubholzAnteil() {
		return (Integer) txtLaubholzanteil_Prz.getValue();
	}

	
	public double getHolzmenge_m3() {
		return (Double) txtHolzmenge_m3.getValue();
	}


	public int getDurchschnittlicherBhdAushieb_cm() {
		return (Integer) txtDurchschnBhdAushieb_cm.getValue();
	}


	public double getDurchschnittlicheHolzlaenge_m() {
		return (Double) txtDurchschnHolzlaenge_m.getValue();
	}

	public double getDurchschnittlicheAnzahlSortimente() {
		return (Double) txtDurchschnAnzSortimente.getValue();
	}


	public int getAnzahlVerschiedeneSortimente() {
		return (Integer) cmbAnzahlSortimente.getSelectedItem();
	}


	public int getAnteilKrumm() {
		return (Integer) txtAnteilKrumm_Prz.getValue();
	}


	public void setLaubholzAnteil(int laubholzAnteil_Prz) {
		txtLaubholzanteil_Prz.setValue(laubholzAnteil_Prz);
		onInputChangedBeforeCalculation(txtLaubholzanteil_Prz);
	}

	public void setHolzmenge_m3(double holzmenge) {
		txtHolzmenge_m3.setValue(holzmenge);
	}

	public void setDurchschnittlicherBhdAushieb_cm(int durchschnittlicherBhdAushieb_cm) {
		txtDurchschnBhdAushieb_cm.setValue(durchschnittlicherBhdAushieb_cm);
	}

	public void setDurchschnittlicheHolzlaenge_m(double durchschnittlicheHolzlaenge_m) {
		txtDurchschnHolzlaenge_m.setValue(durchschnittlicheHolzlaenge_m);
	}

	public void setDurchschnittlicheAnzahlSortimente(double durchschnittlicheAnzahlSortimente) {
		txtDurchschnAnzSortimente.setValue(durchschnittlicheAnzahlSortimente);
	}

	public void setAnzahlVerschiedeneSortimente(int anzahlVerschiedeneSortimente) {
		cmbAnzahlSortimente.setSelectedItem(anzahlVerschiedeneSortimente);
	}

	public void setAnteilKrumm(int anteilKrumm_Prz) {
		txtAnteilKrumm_Prz.setValue(anteilKrumm_Prz);
	}
}
