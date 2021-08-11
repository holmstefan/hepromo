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

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;

/**
 * 
 * @author Stefan Holm
 *
 */
@Deprecated //Klasse wird nur noch von deprecated-Klassen genutzt
public abstract class KonventionellerSeilkranMontageDemontagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JSpinner txtLinienLaenge_m;
	private JSpinner txtAnzahlStuetzen;
	private JSpinner txtAnzahlEndmasten;
	
	protected JCheckBox chkMontageDemontageIstSeiverlegung;
	private JComboBox<WindenTransport> cmbWindenTransport;
	private JComboBox<WindenStandort> cmbWindenStandort;
	private JSpinner txtDistanzWindenselbstfahrt_m;
	

	public KonventionellerSeilkranMontageDemontagePanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	protected abstract String getBorderTitle();
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder( getBorderTitle() ));	
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		
		//Label Linienlänge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLinienlaenge = new JLabel("Linienlänge (m)");
		this.add(lblLinienlaenge, c);
		
		//Text Linienlänge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtLinienLaenge_m = new JSpinner();
		this.add(txtLinienLaenge_m, c);
		
		
		
		//Label Anzahl Stützen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnzahlStuetzen = new JLabel("Anzahl Stützen");
		this.add(lblAnzahlStuetzen, c);
		
		//Text Anzahl Stützen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnzahlStuetzen = new JSpinner();
		this.add(txtAnzahlStuetzen, c);
		
		
		
		//Label Anzahl Endmasten
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnzahlEndmasten = new JLabel("Anzahl Endmasten");
		this.add(lblAnzahlEndmasten, c);
		
		//Text Anzahl Endmasten
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnzahlEndmasten = new JSpinner();
		this.add(txtAnzahlEndmasten, c);

		
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);
		
		

		

		
		
		//Checkbox Demontage ist Seilverlegung
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 3;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.anchor = GridBagConstraints.WEST;
//		c.insets = new Insets(5,5,5,5);
		chkMontageDemontageIstSeiverlegung = new JCheckBox("Die Montage ist eine Seilverlegung");
		chkMontageDemontageIstSeiverlegung.setHorizontalTextPosition(SwingConstants.LEFT);
		this.add(chkMontageDemontageIstSeiverlegung, c);

		
		
		//Label Winden-Transport
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblWindenTransport = new JLabel("Windentransport");
		this.add(lblWindenTransport, c);
		
		//Combo Winden-Transport
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbWindenTransport = new JComboBox<>();
		this.add(cmbWindenTransport, c);

		
		
		
		//Label Winden-Standort
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblWindenStandort = new JLabel("Windenstandort");
		this.add(lblWindenStandort, c);
		
		//Combo Winden-Standort
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbWindenStandort = new JComboBox<>();
		this.add(cmbWindenStandort, c);
		
		
		
		//Label Distanz Windenselbstfahrt
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblDistanzWindenselbstfahrt = new JLabel("<html>Distanz Windenselbstfahrt (m)</html>");
		this.add(lblDistanzWindenselbstfahrt, c);
		
		//Text Windenselbstfahrt
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtDistanzWindenselbstfahrt_m = new JSpinner();
		this.add(txtDistanzWindenselbstfahrt_m, c);
		
		//Info Windenselbstfahrt
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 3;
		c.insets = new Insets(0,5,0,5);
		JLabel lblDistanzWindenselbstfahrt_m_Info = GuiStrings.getInfoButtonBlue("Eingabe nur nötig falls Distanz > 150m");
		this.add(lblDistanzWindenselbstfahrt_m_Info, c);
	}
	
	
	
	private void initData(){

		for (WindenTransport value : WindenTransport.values()) {
			cmbWindenTransport.addItem(value);
		}
		parent.addDefaultActionListener(cmbWindenTransport);
		cmbWindenTransport.setSelectedItem(WindenTransport.KeinWindenTransport);

		
		for (WindenStandort value : WindenStandort.values()) {
			cmbWindenStandort.addItem(value);
		}
		parent.addDefaultActionListener(cmbWindenStandort);
		cmbWindenStandort.setSelectedItem(WindenStandort.Bleibt);
		
		

		txtLinienLaenge_m.setModel(new SpinnerNumberModel(800, 0, 10000, 100));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLinienLaenge_m);
		
		txtAnzahlStuetzen.setModel(new SpinnerNumberModel(2, 0, 20, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlStuetzen);
		
		txtAnzahlEndmasten.setModel(new SpinnerNumberModel(1, 0, 10, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlEndmasten);
		
		chkMontageDemontageIstSeiverlegung.setSelected(true);
		parent.addDefaultItemListener(chkMontageDemontageIstSeiverlegung);
		
		txtDistanzWindenselbstfahrt_m.setModel(new SpinnerNumberModel(0, 0, 10000, 50));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtDistanzWindenselbstfahrt_m);
		
		onInputChangedBeforeCalculation(null);
	}
	
	
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (chkMontageDemontageIstSeiverlegung.equals(eventSource) || eventSource == null) {
			if (chkMontageDemontageIstSeiverlegung.isSelected() == true) {
				cmbWindenTransport.setSelectedItem(WindenTransport.KeinWindenTransport);
				cmbWindenTransport.setEnabled(false);
				cmbWindenStandort.setEnabled(true);
				txtDistanzWindenselbstfahrt_m.setEnabled(false);
			}
			else {
				cmbWindenTransport.setEnabled(true);
				cmbWindenStandort.setEnabled(false);
				txtDistanzWindenselbstfahrt_m.setEnabled(false);
			}
		}
		
		if (cmbWindenTransport.equals(eventSource) || eventSource == null) {
			WindenTransport selection = (WindenTransport)cmbWindenTransport.getSelectedItem();
			if ( WindenTransport.SelbstfahrtBergab.equals(selection) || WindenTransport.SelbstfahrtBergauf.equals(selection) ) {
				txtDistanzWindenselbstfahrt_m.setEnabled(true);
			}
			else {
				txtDistanzWindenselbstfahrt_m.setEnabled(false);
			}
		}
	}
	
	

	
	
	public int getLinienLaenge_m() {
		return (Integer) txtLinienLaenge_m.getValue();
	}
	
	
	public int getAnzahlStuetzen() {
		return (Integer) txtAnzahlStuetzen.getValue();
	}
	
	
	public int getAnzahlEndmasten() {
		return (Integer) txtAnzahlEndmasten.getValue();
	}
	
	
	public WindenTransport getWindenTransport() {
		return (WindenTransport) cmbWindenTransport.getSelectedItem();
	}
	
	
	public WindenStandort getWindenStandort() {
		return (WindenStandort) cmbWindenStandort.getSelectedItem();
	}
	
	
	public int getDistanzWindenselbstfahrt_m() {
		return (Integer) txtDistanzWindenselbstfahrt_m.getValue();
	}



	public void setLinienLaenge_m(int linienLaenge_m) {
		txtLinienLaenge_m.setValue(linienLaenge_m);
	}

	public void setAnzahlStuetzen(int anzahlStuetzen) {
		txtAnzahlStuetzen.setValue(anzahlStuetzen);
	}

	public void setAnzahlEndmasten(int anzahlEndmasten) {
		txtAnzahlEndmasten.setValue(anzahlEndmasten);
	}

	public void setWindenTransport(WindenTransport windenTransport) {
		cmbWindenTransport.setSelectedItem(windenTransport);
	}

	public void setWindenStandort(WindenStandort windenStandort) {
		cmbWindenStandort.setSelectedItem(windenStandort);
	}

	public void setDistanzWindenselbstfahrt_m(int distanzWindenSelbstfahrt) {
		txtDistanzWindenselbstfahrt_m.setValue(distanzWindenSelbstfahrt);
	}

}

