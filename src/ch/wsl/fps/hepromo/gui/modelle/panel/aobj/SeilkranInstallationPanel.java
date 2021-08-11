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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SeilkranInstallationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<Seilsystem> cmbSeilsystem;
	private JComboBox<MaschinenStandort> cmbMaschinenstandort;
	private JSpinner txtLinienLaenge_m;

	private List<Integer> listStuetzenTragseilHoehen = new ArrayList<Integer>();
	private JButton btnNeu;
	private JButton btnDel;
	private JSpinner txtAnzahlStuetzen;
	private JComboBox<Integer> cmbStuetzenNummer;
	private JSpinner txtStuetzenTragseilHoehe_m;
	
	private JCheckBox chkEndmast;
	private JSpinner txtEndmastTragseilHoehe_m;
	

	public SeilkranInstallationPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("SeilkranInstallationPanel.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Seilsystem
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblSeilsystem = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Seilsystem")); //$NON-NLS-1$
		this.add(lblSeilsystem, c);
		
		//Combo Seilsystem
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbSeilsystem = new JComboBox<>();
		this.add(cmbSeilsystem, c);

		
		
		//Label Maschinenstandort
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblMaschinenStandort = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Maschinenstandort")); //$NON-NLS-1$
		this.add(lblMaschinenStandort, c);
		
		//Combo MaschinenStandort
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbMaschinenstandort = new JComboBox<>();
		this.add(cmbMaschinenstandort, c);
		
		
		
		//Label Linienlänge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		JLabel lblLinienlaenge = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Linienlaenge_m")); //$NON-NLS-1$
		this.add(lblLinienlaenge, c);
		
		//Text Linienlänge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtLinienLaenge_m = new JSpinner();
		this.add(txtLinienLaenge_m, c);
		
		
		
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);
		
		


		
		//Label Stützen
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblStuetzen = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Stuetzen"), SwingConstants.CENTER); //$NON-NLS-1$
		this.add(lblStuetzen, c);
		
		//Label Anzahl
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAnzahl = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Anzahl_kurz"), SwingConstants.CENTER); //$NON-NLS-1$
		this.add(lblAnzahl, c);
		
		//Label Nr
        c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblNummer = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Nummer_kurz"), SwingConstants.CENTER); //$NON-NLS-1$
		this.add(lblNummer, c);
		
		//Label Tragseilhöhe
        c = new GridBagConstraints();
		c.gridx = 7;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblTragseilHoehe = new JLabel(GuiStrings.getString("SeilkranInstallationPanel.Tragseilhoehe_m"), SwingConstants.CENTER); //$NON-NLS-1$
		this.add(lblTragseilHoehe, c);
		
		

		
		
		//Button 'neu'
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		btnNeu = new JButton(GuiStrings.getString("SeilkranInstallationPanel.Neu_kurz")); //$NON-NLS-1$
		btnNeu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addStuetze();
			}
		});
		this.add(btnNeu, c);
		
		//Button 'entfernen'
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		btnDel = new JButton(GuiStrings.getString("SeilkranInstallationPanel.Entfernen_kurz")); //$NON-NLS-1$
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeStuetze();
			}
		});
		this.add(btnDel, c);
		
		//Text 'Anzahl'
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,2,0,0);
		txtAnzahlStuetzen = new JSpinner();
		txtAnzahlStuetzen.setEnabled(false);
		this.add(txtAnzahlStuetzen, c);
		
		//Combo 'Nr'
        c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,2,0,2);
		cmbStuetzenNummer = new JComboBox<>();
		cmbStuetzenNummer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selection = cmbStuetzenNummer.getSelectedIndex();
				
				if (selection != -1) {
					Integer value = listStuetzenTragseilHoehen.get(selection);
					txtStuetzenTragseilHoehe_m.setValue(value);
				}
			}
		});
		this.add(cmbStuetzenNummer, c);
		
		//Text Tragseilhöhe
        c = new GridBagConstraints();
		c.gridx = 7;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtStuetzenTragseilHoehe_m = new JSpinner();
		this.add(txtStuetzenTragseilHoehe_m, c);
		
		
		
		
		//Checkbox Endmast
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,5,0,0);
		chkEndmast = new JCheckBox(GuiStrings.getString("SeilkranInstallationPanel.Endmast")); //$NON-NLS-1$
		chkEndmast.setHorizontalTextPosition(SwingConstants.LEFT);
		chkEndmast.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				boolean flag = chkEndmast.isSelected();
				txtEndmastTragseilHoehe_m.setVisible(flag);
			}
		});
		this.add(chkEndmast, c);
		
		//Text Tragseilhöhe Endmast
        c = new GridBagConstraints();
		c.gridx = 7;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtEndmastTragseilHoehe_m = new JSpinner();
		this.add(txtEndmastTragseilHoehe_m, c);
	}
	
	
	private void initData(){

		for (Seilsystem value : Seilsystem.values()) {
			cmbSeilsystem.addItem(value);
		}
		parent.addDefaultActionListener(cmbSeilsystem);
		cmbSeilsystem.setSelectedItem(Seilsystem.ZweiseilSystem);

		
		for (MaschinenStandort value : MaschinenStandort.values()) {
			cmbMaschinenstandort.addItem(value);
		}
		parent.addDefaultActionListener(cmbMaschinenstandort);
		cmbMaschinenstandort.setSelectedItem(MaschinenStandort.Oben);


		txtLinienLaenge_m.setModel(new SpinnerNumberModel(600, 0, 10000, 100));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLinienLaenge_m);
		
		listStuetzenTragseilHoehen.add(12);
		txtAnzahlStuetzen.setValue(listStuetzenTragseilHoehen.size());
		parent.addDefaultActionListener(cmbStuetzenNummer);
		cmbStuetzenNummer.addItem(listStuetzenTragseilHoehen.size());
		
		txtStuetzenTragseilHoehe_m.setModel(new SpinnerNumberModel(12, 0, 100, 1));	
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtStuetzenTragseilHoehe_m);
		
		txtEndmastTragseilHoehe_m.setModel(new SpinnerNumberModel(8, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEndmastTragseilHoehe_m);
		
		chkEndmast.setSelected(true);
		parent.addDefaultItemListener(chkEndmast);
	}
	
	
	
	public void onInputChangeBeforeCalculation(Object eventSource) {
		
		if (cmbSeilsystem.equals(eventSource)) {
			if ( Seilsystem.ZweiseilSystem == cmbSeilsystem.getSelectedItem() ) {
				cmbMaschinenstandort.setSelectedItem(MaschinenStandort.Oben);
				cmbMaschinenstandort.setEnabled(false);
			}
			else {
				cmbMaschinenstandort.setEnabled(true);
			}
		}
		
		
		if (cmbMaschinenstandort.equals(eventSource)) {
			if ( MaschinenStandort.Unten == cmbMaschinenstandort.getSelectedItem() ) {
				cmbSeilsystem.setSelectedItem(Seilsystem.MehrseilSystem);
				cmbSeilsystem.setEnabled(false);
			}
			else {
				cmbSeilsystem.setEnabled(true);
			}
		}
		
		if (txtStuetzenTragseilHoehe_m.equals(eventSource)) {
			Integer value = (Integer) txtStuetzenTragseilHoehe_m.getValue();
			int selection = cmbStuetzenNummer.getSelectedIndex();
			
			listStuetzenTragseilHoehen.set(selection, value);
		}
	}

	
	
	
	private void addStuetze() {
		//Neue Stütze dem Model hinzufügen
		listStuetzenTragseilHoehen.add(12);
		
		//Feld 'Anzahl' erhöhen
		txtAnzahlStuetzen.setValue(listStuetzenTragseilHoehen.size());
		
		//Combo updaten
		cmbStuetzenNummer.addItem(listStuetzenTragseilHoehen.size());
		
		//Remove-Button etc. sichtbar machen
		if (listStuetzenTragseilHoehen.size() > 0) {
			btnDel.setVisible(true);
			cmbStuetzenNummer.setVisible(true);
			txtStuetzenTragseilHoehe_m.setVisible(true);
		}
		
		//Neue Stütze in Combo auswählen
		cmbStuetzenNummer.setSelectedIndex(listStuetzenTragseilHoehen.size() - 1);
	}
	
	
	private void removeStuetze() {
		//Gewählte Stütze aus Model entfernen
		Integer selectedItem = (Integer) cmbStuetzenNummer.getSelectedItem();
		listStuetzenTragseilHoehen.remove(selectedItem - 1);
		
		//Feld 'Anzahl' verringern
		txtAnzahlStuetzen.setValue(listStuetzenTragseilHoehen.size());
		
		//Combo updaten
		cmbStuetzenNummer.removeAllItems();
		for (int i=0; i<listStuetzenTragseilHoehen.size(); i++) {
			cmbStuetzenNummer.addItem(i+1);
		}
		
		//Falls alle Stützen entfernt, remove-Button unsichtbar machen
		if (listStuetzenTragseilHoehen.size() == 0) {
			btnDel.setVisible(false);
			cmbStuetzenNummer.setVisible(false);
			txtStuetzenTragseilHoehe_m.setVisible(false);
		}
	}
	
	
	
	
	
	
	
	public Seilsystem getSeilsystem() {
		return (Seilsystem) cmbSeilsystem.getSelectedItem();
	}
	
	public MaschinenStandort getMaschinenStandort() {
		return (MaschinenStandort) cmbMaschinenstandort.getSelectedItem();
	}
	
	public int getLinienLaenge_m() {
		return (Integer) txtLinienLaenge_m.getValue();
	}
	
	
	
	
	
//	public int getAnzahlStuetzen() {
//		return vStuetzenTragseilHoehen.size();
//	}
//	
//	public int getTragseilHoehe(int stuetzenNr) {
//		return vStuetzenTragseilHoehen.elementAt(stuetzenNr);
//	}
	
	public List<Integer> getStuetzenTragseilHoehen_m() {
		return listStuetzenTragseilHoehen;
	}
	
	


	
	public boolean isEndmast() {
		return chkEndmast.isSelected();
	}
	
	public int getEndmastTragseilHoehe_m() {
		return (Integer) txtEndmastTragseilHoehe_m.getValue();
	}


	
	

	public void setSeilsystem(Seilsystem seilsystem) {
		cmbSeilsystem.setSelectedItem(seilsystem);
		onInputChangeBeforeCalculation(cmbSeilsystem);
	}


	public void setMaschinenStandort(MaschinenStandort maschinenStandort) {
		cmbMaschinenstandort.setSelectedItem(maschinenStandort);
		onInputChangeBeforeCalculation(cmbMaschinenstandort);
	}


	public void setLinienLaenge_m(int linienLaenge_m) {
		txtLinienLaenge_m.setValue(linienLaenge_m);
	}


	public void setStuetzenTragseilHoehen_m(List<Integer> stuetzenTragseilHoehen) {
		this.listStuetzenTragseilHoehen = stuetzenTragseilHoehen;
		
		//Combo updaten
		cmbStuetzenNummer.removeAllItems();
		for (int i=0; i<listStuetzenTragseilHoehen.size(); i++) {
			cmbStuetzenNummer.addItem(i+1);
		}
		
		//Feld 'Anzahl' updaten
		txtAnzahlStuetzen.setValue(listStuetzenTragseilHoehen.size());
		
	}


	public void setEndmast(boolean endmast) {
		chkEndmast.setSelected(endmast);
	}


	public void setEndmastTragseilHoehe_m(int tragseilHoeheEndmast) {
		txtEndmastTragseilHoehe_m.setValue(tragseilHoeheEndmast);
	}

}

