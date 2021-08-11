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
package ch.wsl.fps.hepromo.gui.modelle.panel.asys;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.gui.modelle.HelikopterFliegenBase;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class HelikopterPanel extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblHelikopterkosten = new JLabel(GuiStrings.getString("HelikopterPanel.Helikopterkosten_SFr_pro_BMin")); //$NON-NLS-1$
	private JLabel lblAnflugpauschale = new JLabel(GuiStrings.getString("HelikopterPanel.Anflugpauschale_SFr")); //$NON-NLS-1$
	
	private JSpinner txtHelikopterKosten_proMin;
	private JSpinner txtAnflugpauschale;
	private JComboBox<HelikopterKlasse> cmbHeliklasse;
	private JSpinner txtLastvolumen_m3;
	private JCheckBox chkLastvolumenAutomatischBerechnen;

	
	public HelikopterPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("HelikopterPanel.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Helikopterkosten
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,0,5);
		this.add(lblHelikopterkosten, c);
		
		//Text Helikopterkosten
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtHelikopterKosten_proMin = new JSpinner(new SpinnerNumberModel(60, 0, 500, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtHelikopterKosten_proMin);
		this.add(txtHelikopterKosten_proMin, c);

		
		//Label Anflugpauschale
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		this.add(lblAnflugpauschale, c);
		
		//Text Anflugpauschale
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtAnflugpauschale = new JSpinner(new SpinnerNumberModel(500, 0, 5000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnflugpauschale);
		this.add(txtAnflugpauschale, c);

		
		//Label Heliklasse
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblHeliklasse = new JLabel(GuiStrings.getString("HelikopterPanel.Heliklasse")); //$NON-NLS-1$
		this.add(lblHeliklasse, c);
		
		//Combo Heliklasse
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		cmbHeliklasse = new JComboBox<>();
		this.add(cmbHeliklasse, c);

		
		//Label Lastvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		JLabel lblLastvolumen = new JLabel("<html>" + GuiStrings.getString("HelikopterPanel.Lastvolumen") + " (m<sup>3</sup>)</html>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.add(lblLastvolumen, c);
		
		//Text Lastvolumen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,0,5,0);
		txtLastvolumen_m3 = new JSpinner(new SpinnerNumberModel(1, 0.5, 20, 0.1));
		txtLastvolumen_m3.setEditor(new JSpinner.NumberEditor(txtLastvolumen_m3, "0.####")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLastvolumen_m3);
		txtLastvolumen_m3.setEnabled(false);
		this.add(txtLastvolumen_m3, c);
		
		//Checkbox Lastvolumen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,15,0,0);
		chkLastvolumenAutomatischBerechnen = new JCheckBox(GuiStrings.getString("HelikopterPanel.LastvolumenAutomatischBerechnen")); //$NON-NLS-1$
		chkLastvolumenAutomatischBerechnen.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				boolean auto = chkLastvolumenAutomatischBerechnen.isSelected();
				txtLastvolumen_m3.setEnabled( ! auto );
				if (auto) {
					((HelikopterFliegenBase)parent).adaptLastvolumen();
				}
			}
		});
		chkLastvolumenAutomatischBerechnen.setSelected(true);
		this.add(chkLastvolumenAutomatischBerechnen, c);
		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 0;
//		c.gridy = 4;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
//		this.add(new JPanel(), c);	
		
	}
	
	private void initData() {
		for (HelikopterKlasse heliKlasse : HelikopterKlasse.values()) {
			cmbHeliklasse.addItem(heliKlasse);
		}
		parent.addDefaultActionListener(cmbHeliklasse);
		cmbHeliklasse.setSelectedItem(HelikopterKlasse.Mittel);
	}
	
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbHeliklasse.equals(eventSource)) {
			((HelikopterFliegenBase)parent).adaptLastvolumen();
			
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run(){
					String message = GuiStrings.getString("HelikopterPanel.WarnungKostensatzMessage"); //$NON-NLS-1$
					JOptionPane.showMessageDialog(parent, message, GuiStrings.getString("HelikopterPanel.WarnungKostensatzTitle"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
				}
			});
		}
	}
	

	
	public int getHelikopterKosten_proMin() {
		return (Integer) txtHelikopterKosten_proMin.getValue();
	}
	
	public int getAnflugpauschale() {
		return (Integer) txtAnflugpauschale.getValue();
	}
	
	public HelikopterKlasse getHelikopterKlasse() {
		return (HelikopterKlasse) cmbHeliklasse.getSelectedItem();
	}
	
	public double getLastvolumen_m3() {
		return (Double) txtLastvolumen_m3.getValue();
	}
	
	public boolean isLastvolumenAutomatischBerechnen() {
		return chkLastvolumenAutomatischBerechnen.isSelected();
	}


	public void setHelikopterKosten_proMin(int value) {
		txtHelikopterKosten_proMin.setValue(value);
	}


	public void setAnflugpauschale(int anflugPauschale) {
		txtAnflugpauschale.setValue(anflugPauschale);
	}


	public void setHelikopterKlasse(HelikopterKlasse helikopterKlasse) {
		cmbHeliklasse.setSelectedItem(helikopterKlasse);
	}


	public void setLastvolumen_m3(double lastVolumen) {
		txtLastvolumen_m3.setValue(lastVolumen);
	}
	
	public void setLastvolumenAutomatischBerechnen(boolean flag) {
		chkLastvolumenAutomatischBerechnen.setSelected(flag);
	}


	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblHelikopterkosten.setText(GuiStrings.getString("HelikopterPanel.Helikopterkosten") + " (" + newValue + GuiStrings.getString("HelikopterPanel.Pro_BMin") + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		lblAnflugpauschale.setText(GuiStrings.getString("HelikopterPanel.Anflugpauschale") + " (" + newValue + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
