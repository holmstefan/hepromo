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
package ch.wsl.fps.hepromo.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ch.wsl.fps.hepromo.model.Ergebnis;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractErgebnisPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat(",##0.00"); //$NON-NLS-1$
	private DecimalFormat decimalFormat = DEFAULT_DECIMAL_FORMAT;
	
	protected JPanel pnlKosten;
	
	
	protected final void initContent(){
		//remove all
		this.removeAll();		

		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		//panel titles
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 10;
//		c.insets = new Insets(5,5,5,5);
		this.add(initPanelTitles(), c);
		
		//panel zeitaufwand
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 30;
//		c.insets = new Insets(5,5,5,5);
		this.add(initPanelZeitaufwand(), c);
		
		//panel kosten
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 40;
//		c.weighty = 100;
//		c.insets = new Insets(0,0,5,0);
		pnlKosten = initPanelKosten();
		this.add(pnlKosten, c);
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.insets = new Insets(10,0,10,0);
		JPanel pnlPlaceholder = new JPanel();
		pnlPlaceholder.setOpaque(false);
		this.add(pnlPlaceholder, c);

		//panel produktivität
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.weighty = 100;
		//c.insets = new Insets(10,0,10,0);
		this.add(initPanelProduktivitaetUndRotationszeit(), c);
	}


	protected abstract JPanel initPanelProduktivitaetUndRotationszeit();

	protected abstract JPanel initPanelKosten();

	protected abstract JPanel initPanelZeitaufwand();

	protected abstract JPanel initPanelTitles();
	
	public abstract void setErgebnis(Ergebnis ergebnis);
	
	
	protected final String parseDouble(double value) {
		return decimalFormat.format(value);
	}
	
	
	
	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}
	
	
	public void updateWaehrung(String waehrung) {
		((TitledBorder) pnlKosten.getBorder()).setTitle(GuiStrings.getString("AbstractErgebnisPanel.TitleKosten") + " (" + waehrung + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		pnlKosten.repaint();
	}
	
	
	public static final JTextField getNewLockedTextField() {
		JTextField textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(Color.WHITE);
		textField.setHorizontalAlignment(JTextField.RIGHT);
//		textField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		return textField;
	}
}
