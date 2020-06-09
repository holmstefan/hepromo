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

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMotormanuell.Stuecklaengen;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class SortimentsvorgabenPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	protected final HeProMoWindow parent;

	protected JComboBox<Stuecklaengen> cmbStuecklaengen;
	

	public SortimentsvorgabenPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		addComponents();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Sortimentsvorgaben"));		
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;



        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblStuecklaengen = new JLabel("Stücklängen");
		this.add(lblStuecklaengen, c);


        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		cmbStuecklaengen = new JComboBox<>();
		this.add(cmbStuecklaengen, c);
	}
	

	protected abstract void addComponents();
	
	protected abstract void initData();

}
