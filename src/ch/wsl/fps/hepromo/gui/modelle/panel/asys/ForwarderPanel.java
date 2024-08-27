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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder.ForwarderTyp;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ForwarderPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<ForwarderTyp> cmbForwarderTyp;
	private JSpinner txtLadeQuerschnittsFlaeche_m2;

	
	public ForwarderPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("ForwarderPanel.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label ForwarderTyp
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.gridwidth = 2;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblForwarderTyp = new JLabel(GuiStrings.getString("ForwarderPanel.Typ")); //$NON-NLS-1$
		this.add(lblForwarderTyp, c);
		
		//Combo ForwarderTyp
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbForwarderTyp = new JComboBox<>();
		this.add(cmbForwarderTyp, c);
		
		//Label LadeQuerschnittsFlaeche_m2
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLadeQuerschnittsFlaeche_m2 = new JLabel("<html>" + GuiStrings.getString("ForwarderPanel.Ladequerschnittsflaeche_m2") + " m<sup>2</sup></html>");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		this.add(lblLadeQuerschnittsFlaeche_m2, c);
		
		//InfoButton LadeQuerschnittsFlaeche
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(2,5,0,5);
		JLabel lblLadeQuerschnittsFlaeche_Info = GuiStrings.getInfoButtonBlue( GuiStrings.getString("ForwarderPanel.Ladequerschnittsflaeche_Info") );  //$NON-NLS-1$
		lblLadeQuerschnittsFlaeche_Info.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lblLadeQuerschnittsFlaeche_Info, c);
		
		//Text LadeQuerschnittsFlaeche_m2
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtLadeQuerschnittsFlaeche_m2 = new JSpinner(new SpinnerNumberModel(4.1, 0.1, 100, 0.1));
		txtLadeQuerschnittsFlaeche_m2.setEditor(new JSpinner.NumberEditor(txtLadeQuerschnittsFlaeche_m2, "0.#")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLadeQuerschnittsFlaeche_m2);
		this.add(txtLadeQuerschnittsFlaeche_m2, c);
		
		
//		//placeholder
//        c = new GridBagConstraints();
//		c.gridx = 1;
//		c.gridy = 2;
//		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 100;
//		c.weighty = 100;
////		c.insets = new Insets(5,5,5,5);
//		this.add(new JPanel(), c);	
		
	}
	
	
	

	private void initData() {		
		for (int i=0; i<ForwarderTyp.values().length; i++) {
			cmbForwarderTyp.addItem(ForwarderTyp.values()[i]);
		}
		parent.addDefaultActionListener(cmbForwarderTyp);
		cmbForwarderTyp.setSelectedItem(ForwarderTyp.Mittel);
	}
	
	
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbForwarderTyp.equals(eventSource)) {
			SwingUtilities.invokeLater(() -> {
				String message = GuiStrings.getString("ForwarderPanel.DialogForwarderTypGeaendert"); //$NON-NLS-1$
				JOptionPane.showMessageDialog(ForwarderPanel.this, message, GuiStrings.getString("ForwarderPanel.DialogTitleWarnung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
			});
			
			//GUI ins Model laden
			parent.loadGUIToModel();
			ModelForwarder model = (ModelForwarder) parent.getModel();
			
			//Durch Aufruf dieser Methode wird die Ladequerschnittsfläche automatisch neu gesetzt
			model.getArbeitssystem().setForwarderTyp(getForwarderTyp());
			
			//Model zurück ins GUI laden
			parent.loadModelToGUI(model);
		}
	}
	
	
	
	
	public ForwarderTyp getForwarderTyp() {
		return (ForwarderTyp) cmbForwarderTyp.getSelectedItem();
	}
	
	
	public double getLadeQuerschnittsFlaeche_m2() {
		return (Double) txtLadeQuerschnittsFlaeche_m2.getValue();
	}


	public void setForwarderTyp(ForwarderTyp forwarderTyp) {
		cmbForwarderTyp.setSelectedItem(forwarderTyp);
	}

	public void setLadeQuerschnittsFlaeche_m2(double ladeQuerschnittsFlaeche_m2) {
		txtLadeQuerschnittsFlaeche_m2.setValue(ladeQuerschnittsFlaeche_m2);
	}


}
