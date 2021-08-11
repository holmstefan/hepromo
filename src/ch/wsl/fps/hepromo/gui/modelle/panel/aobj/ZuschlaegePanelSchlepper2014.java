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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.Beizugsdistanz;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014.BeizugsdistanzArrayWithSelection;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ZuschlaegePanelSchlepper2014 extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;

	private JComboBox<Beizugsdistanz> cmbBeizugsdistanz;
	private JSpinner txtBeizugsdistanz;

	private BeizugsdistanzArrayWithSelection beizugsdistanzArrayWithSelection;
	
	
	public ZuschlaegePanelSchlepper2014(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("ZuschlaegePanelSchlepper2014.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;



		
		//Label Beizugsdistanz
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		JLabel lblBeizugsdistanz = new JLabel(GuiStrings.getString("ZuschlaegePanelSchlepper2014.Beizugsdistanz")); //$NON-NLS-1$
		this.add(lblBeizugsdistanz, c);
		
		//Combo Beizugsdistanz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		cmbBeizugsdistanz = new JComboBox<>();
		this.add(cmbBeizugsdistanz, c);
		
		//Text Beizugsdistanz
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 100;
		c.insets = new Insets(0,5,0,5);
		txtBeizugsdistanz = new JSpinner();
		this.add(txtBeizugsdistanz, c);
	}
	
	
	private void initData(){

		//Beizugsdistanz
		txtBeizugsdistanz.setModel(new SpinnerNumberModel(0.0, -0.5, 0.0, 0.01));
		txtBeizugsdistanz.setEditor(new JSpinner.NumberEditor(txtBeizugsdistanz, "0.## %")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtBeizugsdistanz, true);

		
		//init ComboBox
		setBeizugsdistanzArrayWithSelection( new BeizugsdistanzArrayWithSelection() );
	}
	

	
	
	public void onInputChangedBeforeCalculation(Object eventSource) {
		if (txtBeizugsdistanz.equals(eventSource)) {
			Beizugsdistanz selection = (Beizugsdistanz) cmbBeizugsdistanz.getSelectedItem();
			if ( selection.isBenutzerdefiniert() ) {
				selection.setWert( (Double) txtBeizugsdistanz.getValue() ) ;
				cmbBeizugsdistanz.repaint();
			}
		}
	}


	public BeizugsdistanzArrayWithSelection getBeizugsdistanzArrayWithSelection() {
		return beizugsdistanzArrayWithSelection;
	}


	public void setBeizugsdistanzArrayWithSelection(BeizugsdistanzArrayWithSelection beizugsdistanzArrayWithSelection) {
		this.beizugsdistanzArrayWithSelection = beizugsdistanzArrayWithSelection;
		
		//remove old action listeners
		for (ActionListener listener : cmbBeizugsdistanz.getActionListeners()) {
			cmbBeizugsdistanz.removeActionListener(listener);
		}
		
		//refill combobox
		cmbBeizugsdistanz.removeAllItems();
		for (Beizugsdistanz value : beizugsdistanzArrayWithSelection.allValues) {
			cmbBeizugsdistanz.addItem(value);
		}
		
		//add action listener
		cmbBeizugsdistanz.addActionListener(new ActionListener() { //braucht keinen DefaultActionListener -> Änderungen werden beim JSpinner abgefangen
			@Override
			public void actionPerformed(ActionEvent e) {
				Beizugsdistanz selectedItem = (Beizugsdistanz) ((JComboBox<?>)e.getSource()).getSelectedItem();
				if (selectedItem != null) {
					ZuschlaegePanelSchlepper2014.this.beizugsdistanzArrayWithSelection.setSelection(selectedItem);
					txtBeizugsdistanz.setEnabled(  selectedItem.isBenutzerdefiniert() );
					txtBeizugsdistanz.setValue( selectedItem.getWert() );
					
					if (selectedItem.isBenutzerdefiniert() && parent.isLoadingModelFromFile == false) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								String message = GuiStrings.getString("Schlepper2014.WarnungBeizugsdistanz"); //$NON-NLS-1$
								JOptionPane.showMessageDialog(ZuschlaegePanelSchlepper2014.this, message, GuiStrings.getString("Schlepper2014.Warnung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
							}
						});
					}
				}
			}
		});
		
		//select correct item
		cmbBeizugsdistanz.setSelectedItem( beizugsdistanzArrayWithSelection.getSelection() );
	}
}
