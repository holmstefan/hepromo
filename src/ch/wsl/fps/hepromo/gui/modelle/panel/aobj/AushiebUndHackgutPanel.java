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

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilerHacker.Hackgutart;

/**
 * 
 * @author Stefan Holm
 *
 */
public class AushiebUndHackgutPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final HeProMoWindow parent;
	
	private JSpinner txtLaubholzanteil;
	private JSpinner txtAnzahlBaeume;
	private JTextField txtHackgutMenge;
	private JComboBox<Hackgutart> cmbHackgutArt;
	private JSpinner txtMittlererBhdAushieb;
	private JSpinner txtMittlererZopfDurchmesser;
	

	public AushiebUndHackgutPanel(HeProMoWindow parent) {
		this.parent = parent;
		initPanel();
		initData();
	}
	
	
	
	private void initPanel() {
		this.setBorder(BorderFactory.createTitledBorder("Aushieb und Hackgut")); 
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//Label Laubholzanteil
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblLaubholzanteil = new JLabel("Laubholzanteil (%)"); 
		this.add(lblLaubholzanteil, c);
		
		//Text Laubholzanteil
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtLaubholzanteil = new JSpinner(new SpinnerNumberModel(0.0, 0, 100, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtLaubholzanteil);
		this.add(txtLaubholzanteil, c);

		
		//Label Anzahl Bäume
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,5,0,0);
		JLabel lblAnzahlBaeume = new JLabel("Anzahl Bäume beim Aushieb"); 
		this.add(lblAnzahlBaeume, c);
		
		//Text Anzahl Bäume
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtAnzahlBaeume = new JSpinner(new SpinnerNumberModel(550, 0, 2000, 1));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnzahlBaeume);
		this.add(txtAnzahlBaeume, c);

		
		//Label Hackgutmenge
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,5,0,0);
		JLabel lblHackgutmenge = new JLabel("<html>" + "Hackgutmenge" + " (m<sup>3</sup>)</html>");   
		this.add(lblHackgutmenge, c);
		
		//Text Hackgutmenge
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtHackgutMenge = new JTextField();
		txtHackgutMenge.setEditable(false);
		this.add(txtHackgutMenge, c);

		
		//Label Hackgutart
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,0,0,0);
		JLabel lblHackgutart = new JLabel("Hackgutart"); 
		this.add(lblHackgutart, c);
		
		//Combo Hackgutart
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,0,0,0);
		cmbHackgutArt = new JComboBox<>();
		this.add(cmbHackgutArt, c);
		

		//***************** 2. Spalte
		
		//Platzhalter
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 15;
//		c.insets = new Insets(5,5,5,5);
		JPanel placeholder = new JPanel();
		this.add(placeholder, c);

		//***************** 2. Spalte

		
		//Label Mittlerer BHD Aushieb
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,5,0,0);
		JLabel lblMittlererBhdAushieb = new JLabel("Mittlerer BHD Aushieb (m)"); 
		this.add(lblMittlererBhdAushieb, c);
		
		//Text Mittlerer BHD Aushieb
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlererBhdAushieb = new JSpinner(new SpinnerNumberModel(0.3, 0, 1, 0.01));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlererBhdAushieb);
		this.add(txtMittlererBhdAushieb, c);

		
		//Label Mittlerer ZopfDrm
        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(0,5,0,0);
		JLabel lblMittlererZopfDrm = new JLabel("<html>" + "Mittlerer Zopfdurchmesser, resp. Basisdurchmesser des Kronenmaterials (m)" + "</html>");   
		this.add(lblMittlererZopfDrm, c);
		
		//Text Mittlerer ZopfDrm
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtMittlererZopfDurchmesser = new JSpinner(new SpinnerNumberModel(0.08, 0, 1, 0.01));
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtMittlererZopfDurchmesser);
		this.add(txtMittlererZopfDurchmesser, c);
	}
	
	
	private void initData(){
		for (int i=0; i<Hackgutart.values().length; i++) {
			cmbHackgutArt.addItem(Hackgutart.values()[i]);
		}
		parent.addDefaultActionListener(cmbHackgutArt);
		cmbHackgutArt.setSelectedItem(Hackgutart.Kronen);
	}
	

	
	public double getLaubholzAnteil_Prz() {
		return (Double) txtLaubholzanteil.getValue();
	}
	
	public int getAnzahlBaeume() {
		return (Integer) txtAnzahlBaeume.getValue();
	}
	
	public Hackgutart getHackgutart() {
		return (Hackgutart) cmbHackgutArt.getSelectedItem();
	}
	
	public double getMittlererBhdAushieb_m() {
		return (Double) txtMittlererBhdAushieb.getValue();
	}
	
	public double getMittlererZopfDurchmesser_m() {
		return (Double) txtMittlererZopfDurchmesser.getValue();
	}



	public void setLaubholzAnteil_Prz(double laubholzAnteil_Prz) {
		txtLaubholzanteil.setValue(laubholzAnteil_Prz);
	}

	public void setAnzahlBaeume(int anzahlBaeume) {
		txtAnzahlBaeume.setValue(anzahlBaeume);
	}
	
	public void setHackgutMenge_m3(double hackgutMenge_m3) {
		txtHackgutMenge.setText(parseDouble(hackgutMenge_m3, 2));
	}

	public void setHackgutart(Hackgutart hackgutart) {
		cmbHackgutArt.setSelectedItem(hackgutart);
	}
	
	public void setMittlererBhdAushieb_m(double mittlererBhdAushieb_m) {
		txtMittlererBhdAushieb.setValue(mittlererBhdAushieb_m);
	}

	public void setMittlererZopfDurchmesser_m(double mittlererZopfDrm_m) {
		txtMittlererZopfDurchmesser.setValue(mittlererZopfDrm_m);
	}


	
	protected final String parseDouble(double value, int anzahlNachkommastellen) { //TODO(deprecated model): DecimalFormat verwenden
		//round
		int factor = (int) Math.pow(10, anzahlNachkommastellen);
		double roundedValue =  Math.round(value * factor) / (0.0 + factor);
		
		//overflow check
		if (Math.abs(roundedValue - value) > 1) {
//			throw new ArithmeticException("overflow");
			throw new ArithmeticException("overflow (" + value + "/" + roundedValue + ")");   
		}
		
		//convert to string
		String result = String.valueOf(roundedValue);
		
		//if 0 digits after comma, cut string
		if (anzahlNachkommastellen == 0) {
			result = result.replace(".0", "");  
		}
		
		//done
		return result;
	}
}
