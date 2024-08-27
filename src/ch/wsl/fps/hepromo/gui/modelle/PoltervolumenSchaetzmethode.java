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
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.MainWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class PoltervolumenSchaetzmethode extends JPanel { //FIXME: testing der schätzmethoden!
	private static final long serialVersionUID = 1L;
	
	protected static final Icon iconQuader = getIcon("data/quader.png"); //$NON-NLS-1$
	protected static final Icon iconHalbzylinder = getIcon("data/zylinder.png"); //$NON-NLS-1$
	
	private final JLabel lblIcon = new JLabel();

	protected final JLabel lblHoehe = new JLabel(GuiStrings.getString("PoltervolumenSchaetzmethode.MittlereHoehe_m"));  //$NON-NLS-1$
	protected final JLabel lblBreite = new JLabel(GuiStrings.getString("PoltervolumenSchaetzmethode.MittlereBreite_m")); //$NON-NLS-1$
	protected final JLabel lblTiefe = new JLabel(GuiStrings.getString("PoltervolumenSchaetzmethode.MittlereTiefe_m")); //$NON-NLS-1$
	protected final JLabel lblOptional1 = new JLabel();
	protected final JLabel lblOptional2 = new JLabel();
	protected final JLabel lblOptional3 = new JLabel();
	
	protected final JSpinner txtHoehe = new JSpinner(new SpinnerNumberModel(4, 0.5, 50, 0.2));
	protected final JSpinner txtBreite = new JSpinner(new SpinnerNumberModel(12, 0.5, 50, 0.2));
	protected final JSpinner txtTiefe = new JSpinner(new SpinnerNumberModel(5, 0.5, 50, 0.2));
	protected final JSpinner txtOptional1 = new JSpinner();
	protected final JSpinner txtOptional2 = new JSpinner();
	protected final JComboBox<Integer> cmbOptional3 = new JComboBox<>();
	
	private final JLabel lblResult = new JLabel(GuiStrings.getString("PoltervolumenSchaetzmethode.Hackschnitzelmenge_Srm")); //$NON-NLS-1$
	private final JSpinner txtResult = new JSpinner();

	
	public PoltervolumenSchaetzmethode(Icon icon, boolean showOptional1, boolean showOptional2, boolean showOptional3) {
		lblIcon.setIcon(icon);
		init(showOptional1, showOptional2, showOptional3);
	}
	
	
	
	private void init(boolean showOptional1, boolean showOptional2, boolean showOptional3) {
		
		//set spinners to correct format
		HeProMoWindow.adjustJSpinnerFormatter(txtHoehe, false);
		HeProMoWindow.adjustJSpinnerFormatter(txtBreite, false);
		HeProMoWindow.adjustJSpinnerFormatter(txtTiefe, false);
		HeProMoWindow.adjustJSpinnerFormatter(txtOptional1, false);
		HeProMoWindow.adjustJSpinnerFormatter(txtOptional2, false);
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;

        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 99;
		c.insets = new Insets(0,0,0,10);
		JPanel panelIcon = new JPanel();
		panelIcon.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("PoltervolumenSchaetzmethode.Polterform"))); //$NON-NLS-1$
		panelIcon.add(lblIcon);
		this.add(panelIcon, c);
		
		

        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,0,10);
		this.add(lblHoehe, c);

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		txtHoehe.addChangeListener(evt -> {
			updateResult();
		});
		this.add(txtHoehe, c);

		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,10);
		this.add(lblBreite, c);

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		txtBreite.addChangeListener(evt -> {
			updateResult();
		});
		this.add(txtBreite, c);

		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,10);
		this.add(lblTiefe, c);

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		txtTiefe.addChangeListener(evt -> {
			updateResult();
		});
		this.add(txtTiefe, c);

		
		if (showOptional1) {
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 3;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,0,0,10);
			this.add(lblOptional1, c);

			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			txtOptional1.addChangeListener(evt -> {
				updateResult();
			});
			this.add(txtOptional1, c);
		}

		
		if (showOptional2) {
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 4;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,0,0,10);
			this.add(lblOptional2, c);

			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			txtOptional2.addChangeListener(evt -> {
				updateResult();
			});
			this.add(txtOptional2, c);
		}

		
		if (showOptional3) {
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 5;
//			c.gridwidth = 2;
//			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,0,0,10);
			this.add(lblOptional3, c);

			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 5;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			((JLabel)cmbOptional3.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
			cmbOptional3.addActionListener(evt -> {
				updateResult();
			});
			this.add(cmbOptional3, c);

			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 5;
//			c.fill = GridBagConstraints.HORIZONTAL;
//			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(0,10,0,0);
			JLabel lblInfoButton = GuiStrings.getInfoButtonBlue(GuiStrings.getString("PoltervolumenSchaetzmethode.InfoButtonKronenfussflaeche")); //FIXME: infotext kronenfussfläche -> flexibler text! //$NON-NLS-1$
			this.add(lblInfoButton, c);
		}
		else {
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 5;
			c.insets = new Insets(0,26,0,0);
			this.add(new JLabel(), c); //placeholder
		}
		
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 100;
		this.add(new JPanel(), c); //placeholder
		
		
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,10,10);
		this.add(lblResult, c);

        c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 7;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(0,0,10,0);
		txtResult.setEnabled(false);
		this.add(txtResult, c);

		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 7;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,10,10,0);
		JLabel lblInfoButton = GuiStrings.getInfoButtonBlue(GuiStrings.getString("PoltervolumenSchaetzmethode.InfoButtonSrm")); //$NON-NLS-1$
		this.add(lblInfoButton, c);
	}
	
	
	@Override
	public abstract String toString(); //force subclasses to override method
	
	
	protected final void updateResult() {
		double result = Math.round(getResult() * 10) / 10f;
		txtResult.setValue(result);
	}
	
	
	public abstract double getResult();
	
	
	private static Icon getIcon(String filePath) {
		//try to open from jar
		URL imgURL = MainWindow.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		
		//otherwise, try to open from file system
		return new ImageIcon(filePath);
	}
	
	
	
	
	
	
	public static class SchaetzmethodeRauricaKlein extends PoltervolumenSchaetzmethode {
		private static final long serialVersionUID = 1L;

		public SchaetzmethodeRauricaKlein() {
			super(iconQuader, false, false, false);
			updateResult();
		}

		@Override
		public String toString() {
			return GuiStrings.getString("PoltervolumenSchaetzmethode.SchaetzmethodeRauricaKlein"); //$NON-NLS-1$
		}

		@Override
		public double getResult() {
			double hoehe_m = (Double) super.txtHoehe.getValue();
			double breite_m = (Double) super.txtBreite.getValue();
			double tiefe_m = (Double) super.txtTiefe.getValue();
			
			double result = hoehe_m * breite_m * tiefe_m * 0.6;
			return result;
		}
	}
	
	
	public static class SchaetzmethodeRauricaGross extends PoltervolumenSchaetzmethode {
		private static final long serialVersionUID = 1L;

		public SchaetzmethodeRauricaGross() {
			super(iconQuader, false, false, false);
			updateResult();
		}

		@Override
		public String toString() {
			return GuiStrings.getString("PoltervolumenSchaetzmethode.SchaetzmethodeRauricaGross"); //FIXME: siehe originalartikel: http://www.waldwissen.net/waldwirtschaft/holz/energie/wsl_polter/wsl_polter_originalartikel.pdf //$NON-NLS-1$
		}

		@Override
		public double getResult() {
			double hoehe_m = (Double) super.txtHoehe.getValue();
			double breite_m = (Double) super.txtBreite.getValue();
			double tiefe_m = (Double) super.txtTiefe.getValue();
			
			double result = hoehe_m * breite_m * tiefe_m * 0.4;
			return result;
		}
	}
	
	
	public static class SchaetzmethodeRauricaDetailliert extends PoltervolumenSchaetzmethode {
		private static final long serialVersionUID = 1L;

		public SchaetzmethodeRauricaDetailliert() {
			super(iconQuader, true, true, false);
			super.lblOptional1.setText(GuiStrings.getString("PoltervolumenSchaetzmethode.VolumenanteilNadelholz_Prz")); //$NON-NLS-1$
			super.lblOptional2.setText(GuiStrings.getString("PoltervolumenSchaetzmethode.VolumenAnteilLaubholz_Prz")); //$NON-NLS-1$
			super.txtOptional1.setModel(new SpinnerNumberModel(50, 0, 100, 1));
			super.txtOptional2.setModel(new SpinnerNumberModel(50, 0, 100, 1));
			HeProMoWindow.adjustJSpinnerFormatter(txtOptional1, false);
			HeProMoWindow.adjustJSpinnerFormatter(txtOptional2, false);
			super.updateResult();
		}

		@Override
		public String toString() {
			return GuiStrings.getString("PoltervolumenSchaetzmethode.SchaetzmethodeRauricaDetailliert"); //$NON-NLS-1$
		}

		@Override
		public double getResult() {
			double hoehe_m = (Double) super.txtHoehe.getValue();
			double breite_m = (Double) super.txtBreite.getValue();
			double tiefe_m = (Double) super.txtTiefe.getValue();

			double anteilNadel = ((Integer) txtOptional1.getValue()) / 100.0;
			double anteilDerbholz = ((Integer) txtOptional2.getValue()) / 100.0;
			
			double result = 2.8 * hoehe_m * breite_m * tiefe_m * 0.5 * (anteilNadel * 0.8 + (1-anteilNadel) * 0.6) * (anteilDerbholz * 0.7 + (1-anteilDerbholz) * 0.5);
			return result;
		}
	}
	
	
	public static class SchaetzmethodeKuptz extends PoltervolumenSchaetzmethode {
		private static final long serialVersionUID = 1L;

		public SchaetzmethodeKuptz() {
			super(iconQuader, false, false, true);
			super.lblOptional3.setText(GuiStrings.getString("PoltervolumenSchaetzmethode.Kronenfussflaeche_Prz")); //$NON-NLS-1$
			super.cmbOptional3.addItem(40);
			super.cmbOptional3.addItem(45);
			super.cmbOptional3.addItem(50);
			super.cmbOptional3.addItem(55);
			super.cmbOptional3.addItem(60);
			super.cmbOptional3.addItem(65);
			super.updateResult();
		}

		@Override
		public String toString() {
			return GuiStrings.getString("PoltervolumenSchaetzmethode.SchaetzmethodeKuptz"); //$NON-NLS-1$
		}

		@Override
		public double getResult() {
			double hoehe_m = (Double) super.txtHoehe.getValue();
			double breite_m = (Double) super.txtBreite.getValue();
			double tiefe_m = (Double) super.txtTiefe.getValue();

			int kronenfussflaeche_Prz = (Integer) cmbOptional3.getSelectedItem();
			double umrechnungsfaktor = -1;
			switch(kronenfussflaeche_Prz) {
			case 40:
				umrechnungsfaktor = 1.0;
				break;
				
			case 45:
				umrechnungsfaktor = 1.1;
				break;
				
			case 50:
				umrechnungsfaktor = 1.2;
				break;
				
			case 55:
				umrechnungsfaktor = 1.4;
				break;
				
			case 60:
				umrechnungsfaktor = 1.5;
				break;
				
			case 65:
				umrechnungsfaktor = 1.6;
				break;
				
			default:
				JOptionPane.showMessageDialog(this, GuiStrings.getString("PoltervolumenSchaetzmethode.FehlerUngueltigeKronenfussflaeche") + String.valueOf(kronenfussflaeche_Prz), GuiStrings.getString("PoltervolumenSchaetzmethode.TitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			double result = hoehe_m * breite_m * tiefe_m * umrechnungsfaktor;
			return result;
		}
	}
	
	
	public static class SchaetzmethodeKuptzWaldrestholz extends PoltervolumenSchaetzmethode {
		private static final long serialVersionUID = 1L;

		public SchaetzmethodeKuptzWaldrestholz() {
			super(iconHalbzylinder, false, false, true);
			super.lblHoehe.setText(GuiStrings.getString("PoltervolumenSchaetzmethode.HoeheStirnseite")); //$NON-NLS-1$
			super.lblOptional3.setText(GuiStrings.getString("PoltervolumenSchaetzmethode.Kronenfussflaeche_Prz")); //$NON-NLS-1$
			super.cmbOptional3.addItem(20);
			super.cmbOptional3.addItem(25);
			super.cmbOptional3.addItem(30);
			super.cmbOptional3.addItem(35);
			super.updateResult();
		}

		@Override
		public String toString() {
			return GuiStrings.getString("PoltervolumenSchaetzmethode.SchaetzmethodeKuptzWaldrestholz"); //$NON-NLS-1$
		}

		@Override
		public double getResult() {
			double hoehe_m = (Double) super.txtHoehe.getValue();
			double breite_m = (Double) super.txtBreite.getValue();
			double tiefe_m = (Double) super.txtTiefe.getValue();
			
			int kronenfussflaeche_Prz = (Integer) cmbOptional3.getSelectedItem();
			double umrechnungsfaktor = -1;
			switch(kronenfussflaeche_Prz) {
			case 20:
				umrechnungsfaktor = 0.4;
				break;
				
			case 25:
				umrechnungsfaktor = 0.6;
				break;
				
			case 30:
				umrechnungsfaktor = 0.7;
				break;
				
			case 35:
				umrechnungsfaktor = 0.8;
				break;
				
			default:
				JOptionPane.showMessageDialog(this, GuiStrings.getString("PoltervolumenSchaetzmethode.FehlerUngueltigeKronenfussflaeche") + String.valueOf(kronenfussflaeche_Prz), GuiStrings.getString("PoltervolumenSchaetzmethode.TitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			double result = Math.PI / 4 * hoehe_m * breite_m * tiefe_m * umrechnungsfaktor;
			return result;
		}
	}
}
