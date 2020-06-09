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

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.RueckgehilfeEinsatzanteil;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.SchlepperTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanelSchlepper extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JLabel lblAnsatzMensch = new JLabel("SFr./Std."); 
	private JLabel lblAnsatzMaschine = new JLabel("SFr./BStd."); 
	
	private JSpinner txtAnsatzMaschinist;
	private JCheckBox chkRueckegehilfe;
	private JSpinner txtAnsatzRueckegehilfe;
	private JComboBox<RueckgehilfeEinsatzanteil> cmbRueckegehilfeEinsatzanteil;
	private JSpinner txtAnsatzSchlepper;
	private JComboBox<SchlepperTyp> cmbSchleppertyp;
	
	
	public KostensaetzePanelSchlepper(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}



	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Kostensätze"));		 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(lblAnsatzMensch, c);

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblMaschinist = new JLabel("Maschinist"); 
		this.add(lblMaschinist, c);

		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzMaschinist = new JSpinner();
		this.add(txtAnsatzMaschinist, c);

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblRueckegehilfe = new JLabel("Rückegehilfe"); 
		this.add(lblRueckegehilfe, c);

		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		chkRueckegehilfe = new JCheckBox();
		this.add(chkRueckegehilfe, c);

		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzRueckegehilfe = new JSpinner();
		this.add(txtAnsatzRueckegehilfe, c);

		
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.gridwidth = 2;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblEinsatzanteil = new JLabel("     Einsatzanteil"); 
		this.add(lblEinsatzanteil, c);

		
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbRueckegehilfeEinsatzanteil = new JComboBox<>();
		fillComboBoxWithEnum(cmbRueckegehilfeEinsatzanteil, RueckgehilfeEinsatzanteil.values());
		parent.addDefaultActionListener(cmbRueckegehilfeEinsatzanteil);
		this.add(cmbRueckegehilfeEinsatzanteil, c);
		
		
		
		

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 4;
		c.weightx = 10;
//		c.insets = new Insets(5,5,5,5);
		this.add(new JPanel(), c);
		
		
		


		
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		this.add(lblAnsatzMaschine, c);

		
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
//		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblSchlepper = new JLabel("Schlepper"); 
		this.add(lblSchlepper, c);

		
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnsatzSchlepper = new JSpinner();
		this.add(txtAnsatzSchlepper, c);

		
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.gridwidth = 2;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblSchleppertyp = new JLabel("Schleppertyp"); 
		this.add(lblSchleppertyp, c);

		
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbSchleppertyp = new JComboBox<>();
		fillComboBoxWithEnum(cmbSchleppertyp, SchlepperTyp.values());
		parent.addDefaultActionListener(cmbSchleppertyp);
		this.add(cmbSchleppertyp, c);
	}



	private void initData() {
		txtAnsatzMaschinist.setModel(new SpinnerNumberModel(60.0, 0, 1000, 1));
		txtAnsatzMaschinist.setEditor(new JSpinner.NumberEditor(txtAnsatzMaschinist, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMaschinist);
		
		chkRueckegehilfe.setSelected(false);
		parent.addDefaultItemListener(chkRueckegehilfe);
		
		txtAnsatzRueckegehilfe.setModel(new SpinnerNumberModel(50.0, 0, 1000, 1));
		txtAnsatzRueckegehilfe.setEditor(new JSpinner.NumberEditor(txtAnsatzRueckegehilfe, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzRueckegehilfe);
		
		cmbRueckegehilfeEinsatzanteil.setSelectedItem(RueckgehilfeEinsatzanteil.Unbekannt);
		
		txtAnsatzSchlepper.setModel(new SpinnerNumberModel(90.0, 0, 1000, 1));
		txtAnsatzSchlepper.setEditor(new JSpinner.NumberEditor(txtAnsatzSchlepper, "0.##")); 
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzSchlepper);
		
		cmbSchleppertyp.setSelectedItem(SchlepperTyp.AndereSchleppertypen);
	}
	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		
		if (chkRueckegehilfe.equals(eventSource)) {
			cmbRueckegehilfeEinsatzanteil.setEnabled( chkRueckegehilfe.isSelected() );
			
			if (chkRueckegehilfe.isSelected()) {
				cmbRueckegehilfeEinsatzanteil.setSelectedItem(RueckgehilfeEinsatzanteil.Prz_51bis75);
			}
			else {
				cmbRueckegehilfeEinsatzanteil.setSelectedItem(RueckgehilfeEinsatzanteil.Unbekannt);
			}
		}
	}
	
	
	public double getAnsatzMaschinist() {
		return (Double) txtAnsatzMaschinist.getValue();
	}
	
	
	public boolean hasRueckegehilfe() {
		return chkRueckegehilfe.isSelected();
	}
	
	
	public double getAnsatzRueckegehilfe() {
		return (Double) txtAnsatzRueckegehilfe.getValue();
	}
	
	
	public RueckgehilfeEinsatzanteil getRueckegehilfeEinsatzanteil() {
		return (RueckgehilfeEinsatzanteil) cmbRueckegehilfeEinsatzanteil.getSelectedItem();
	}
	
	
	public double getAnsatzSchlepper() {
		return (Double) txtAnsatzSchlepper.getValue();
	}
	
	
	public SchlepperTyp getSchlepperTyp() {
		return (SchlepperTyp) cmbSchleppertyp.getSelectedItem();
	}
	
	
	
	private <T extends Enum<T>>void fillComboBoxWithEnum(JComboBox<T> cmbTarget, T[] enumSource) {
		for (int i=0; i<enumSource.length; i++) {
			cmbTarget.addItem(enumSource[i]);
		}
	}



	public void setAnsatzMaschinist(double kostenansatzPersonal1) {
		txtAnsatzMaschinist.setValue(kostenansatzPersonal1);
	}

	public void setRueckegehilfe(boolean flag) {
		chkRueckegehilfe.setSelected(flag);
		onInputChangedBeforeCalculation(chkRueckegehilfe);
	}

	public void setAnsatzRueckegehilfe(double kostenansatzPersonal2) {
		txtAnsatzRueckegehilfe.setValue(kostenansatzPersonal2);
	}

	public void setRueckegehilfeEinsatzanteil(RueckgehilfeEinsatzanteil rueckgehilfeEinsatzanteil) {
		cmbRueckegehilfeEinsatzanteil.setSelectedItem(rueckgehilfeEinsatzanteil);
	}

	public void setAnsatzSchlepper(double kostenansatzMaschine1) {
		txtAnsatzSchlepper.setValue(kostenansatzMaschine1);
	}

	public void setSchlepperTyp(SchlepperTyp schlepperTyp) {
		cmbSchleppertyp.setSelectedItem(schlepperTyp);
	}



	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblAnsatzMensch.setText(newValue + "/Std."); 
		lblAnsatzMaschine.setText(newValue + "/BStd."); 
	}

}
