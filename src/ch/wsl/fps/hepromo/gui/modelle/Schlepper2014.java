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
package ch.wsl.fps.hepromo.gui.modelle;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ch.wsl.fps.hepromo.gui.HeProMoWindow2014;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.BestandPanelSchlepper2014;
import ch.wsl.fps.hepromo.gui.modelle.panel.aobj.ZuschlaegePanelSchlepper2014;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper2014.Maschinenkategorie;
import ch.wsl.fps.hepromo.model.modelle.ModelSchlepper2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Schlepper2014 extends HeProMoWindow2014 {

	private static final long serialVersionUID = 1L;
	
	private BestandPanelSchlepper2014 panelBestand;
	private ZuschlaegePanelSchlepper2014 panelZuschlaege;
	private JComboBox<Maschinenkategorie> cmbMaschinenkategorie;
	
	private ModelSchlepper2014 model = new ModelSchlepper2014();
	
	
	
	public Schlepper2014() {
		super.setTitle(GuiStrings.getString("Schlepper2014.Title")); //$NON-NLS-1$
		super.setSize((int) (645 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (630 * MainWindow.SIZE));
		
		super.initalize();
	}
	
	

	@Override
	protected void initPanelArbeitsobjekt(JPanel panel) {
		panel.removeAll();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//panel Bestand
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panelBestand = new BestandPanelSchlepper2014(this);
		panel.add(panelBestand, c);
		
		//panel Zuschläge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(15,0,0,0);
		panelZuschlaege = new ZuschlaegePanelSchlepper2014(this);
		panel.add(panelZuschlaege, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(5,5,5,5);
		panel.add(new JPanel(), c);
	}
	
	
	@Override
	protected JPanel initSpecialPanel() {
		JPanel panel = new JPanel();
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Maschinenkategorie
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,8,20,5);
		JLabel lblMaschinenkategorie = new JLabel(GuiStrings.getString("Schlepper2014.Schlepperart")); //$NON-NLS-1$
		panel.add(lblMaschinenkategorie, c);
		
		//Combo Maschinenkategorie
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,5,20,5);
		cmbMaschinenkategorie = new JComboBox<>();
		panel.add(cmbMaschinenkategorie, c);
		
		return panel;
	}

	
	@Override
	protected void initData() {
		String labelPersonal = getModel().getArbeitssystem().getLabelPersonal1();
		String labelMaschine = getModel().getArbeitssystem().getLabelMaschine1();
		
		panelKostensaetze.setLabelPersonal(labelPersonal);
		panelKostensaetze.setLabelMaschine1(labelMaschine);

		
		for (Maschinenkategorie value : Maschinenkategorie.values()) {
			cmbMaschinenkategorie.addItem(value);
		}
		addDefaultActionListener(cmbMaschinenkategorie);
	}

	
	
	@Override
	protected void onInputChangedBeforeCalculation(Object eventSource) {
		if (cmbMaschinenkategorie.equals(eventSource)) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run(){
					String message = GuiStrings.getString("Schlepper2014.WarnungKostensatz"); //$NON-NLS-1$
					JOptionPane.showMessageDialog(Schlepper2014.this, message, GuiStrings.getString("Schlepper2014.Warnung"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
				}
			});
		}
		
		panelZuschlaege.onInputChangedBeforeCalculation(eventSource);
	}


	@Override
	public void loadGUIToModel() {
		super.loadGUIToModel();
		
		panelBestand.updateLabelHolzmengeMitRindenabzug(); //kann erst aktualisiert werden, wenn die Faktoren ins Modell geladen worden sind

		//Arbeitssystem
		model.getArbeitssystem().setMaschinenkategorie(			this.getMaschinenkategorie()				);
		
		//Arbeitsobjekt
		model.getArbeitsobjekt().setHolzmenge_m3(				panelBestand.getHolzmenge_m3()				);
		model.getArbeitsobjekt().setMittlererStueckinhalt(		panelBestand.getMittlererStueckinhalt()		);
		model.getArbeitsobjekt().setMittlereFahrentfernung(		panelBestand.getMittlereFahrentfernung()	);
		
		model.getArbeitsobjekt().setBeizugsdistanzArrayWithSelection( panelZuschlaege.getBeizugsdistanzArrayWithSelection() );
	}

	
	@Override
	public ModelSchlepper2014 getModel() {
		return model;
	}

	
	@Override
	public void loadModelToGUI(HeProMoInputData data) {
		super.setReactOnInputChange(false); //verhindert unnötige Berechnungen während dem Laden der Daten
		
		super.loadModelToGUI(data);
		
		//Arbeitssystem
		this.setMaschinenkategorie(					((ArbeitssystemSchlepper2014) data.getArbeitssystem()).getMaschinenkategorie()		);
		
		//Arbeitsobjekt
		panelBestand.setHolzmenge_m3(										 	  data.getArbeitsobjekt().getHolzmenge_m3()				);
		panelBestand.setMittlererStueckinhalt(		((ArbeitsobjektSchlepper2014) data.getArbeitsobjekt()).getMittlererStueckinhalt()	);
		panelBestand.setMittlereFahrentfernung(		((ArbeitsobjektSchlepper2014) data.getArbeitsobjekt()).getMittlereFahrentfernung()	);
		
		panelZuschlaege.setBeizugsdistanzArrayWithSelection(	((ArbeitsobjektSchlepper2014) data.getArbeitsobjekt()).getBeizugsdistanzArrayWithSelection()	);
		
		super.setReactOnInputChange(true);
	}
	

	public Maschinenkategorie getMaschinenkategorie() {
		return (Maschinenkategorie) cmbMaschinenkategorie.getSelectedItem();
	}
	

	public void setMaschinenkategorie(Maschinenkategorie maschinenkategorie) {
		this.cmbMaschinenkategorie.setSelectedItem(maschinenkategorie);;
	}
	
}
