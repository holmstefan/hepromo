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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.ErgebnisHelikopterFliegen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisPanel extends AbstractErgebnisPanel {

	private static final long serialVersionUID = 1L;
	
	public static final Color ERGEBNIS_PANEL_BACKGROUND_COLOR = new Color(255,255,225);
	
	private JLabel lblPersonal = new JLabel(GuiStrings.getString("ErgebnisPanel.Personal")); //$NON-NLS-1$
	private JLabel lblMaschine1 = new JLabel(GuiStrings.getString("ErgebnisPanel.Maschine1")); //$NON-NLS-1$
	private JLabel lblMaschine2 = new JLabel(GuiStrings.getString("ErgebnisPanel.Maschine2")); //$NON-NLS-1$
	private JLabel lblMaschine3 = new JLabel(GuiStrings.getString("ErgebnisPanel.Maschine3")); //$NON-NLS-1$
	private JLabel lblUmsetzen= new JLabel(GuiStrings.getString("ErgebnisPanel.Umsetzen")); //$NON-NLS-1$
	
	private JLabel lblDauerDerArbeit;
	private JTextField txtZeitDauerDerArbeit;
	private JLabel lblZeitDauerDerArbeit;
	private JLabel lblZeitDauerDerArbeit_Info;
	
	private JTextField txtZeitPersonal = new JTextField(); //avoids NullPointerException
	private JTextField txtZeitMaschine1 = new JTextField(); //avoids NullPointerException
	private JTextField txtZeitMaschine2 = new JTextField(); //avoids NullPointerException
	private JTextField txtZeitMaschine3 = new JTextField(); //avoids NullPointerException
	private JTextField txtZeitUmsetzen = new JTextField(); //avoids NullPointerException
	private JTextField txtZeitWeitereAufwaende;;
	
	private JTextField txtKostenPersonal_proM3 = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenMaschine1_proM3 = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenMaschine2_proM3 = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenMaschine3_proM3 = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenUmsetzen_proM3 = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenWeitereAufwaende_proM3 = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenTotal_proM3 = new JTextField(); //avoids NullPointerException

	private JTextField txtKostenPersonal_total = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenMaschine1_total = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenMaschine2_total = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenMaschine3_total = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenUmsetzen_total = new JTextField(); //avoids NullPointerException
	private JTextField txtKostenWeitereAufwaende_total;
	private JTextField txtKostenTotal_total;

	private JTextField txtProduktivitaet = new JTextField(); //avoids NullPointerException
	protected JLabel lblRotationszeit;
	protected JTextField txtRotationszeit = new JTextField(); //avoids NullPointerException
	
	private JLabel lblKostenproM3 = new JLabel(GuiStrings.getString("ErgebnisPanel.pro_m3")); //$NON-NLS-1$
	private JLabel lblProduktivitaet = new JLabel(GuiStrings.getString("ErgebnisPanel.Produktivitaet_m3_pro_PSH15")); //$NON-NLS-1$
	private JLabel lblProduktivitaet_Info;
	private final ValueAndUnitLabel lblProduktivitaet2 = new ValueAndUnitLabel();

	private boolean enableRowPersonal = true;
	private boolean enableRowMaschine1 = true;
	private boolean enableRowMaschine2 = false;
	private boolean enableRowMaschine3 = false;
	private boolean enableRowUmsetzen = true;
	private boolean enableRowProduktivitaet = true;
	private boolean enableRowRotationszeit = false;
	private boolean enableColumnProM3 = false;
	
	private boolean showHintKostenProM3 = false;
	private boolean showHintProduktivitaet = false;
	
	
	
	public ErgebnisPanel() {
		this(true, false, true, true, false, true);
	}
	
	
	public ErgebnisPanel(boolean enableRowMaschine1, boolean enableRowMaschine2, boolean enableRowUmsetzen, boolean enableRowProduktivitaet, boolean enableRowRotationszeit, boolean enableColumnProM3 ) {
		this(true, enableRowMaschine1, enableRowMaschine2, enableRowUmsetzen, enableRowProduktivitaet, enableRowRotationszeit, enableColumnProM3, false, false);
	}
	
	
	
	public ErgebnisPanel(boolean enableRowPersonal, boolean enableRowMaschine1, boolean enableRowMaschine2, boolean enableRowUmsetzen, boolean enableRowProduktivitaet, boolean enableRowRotationszeit, boolean enableColumnProM3, boolean showHintKostenProM3, boolean showHintProduktivitaet) {
		this(enableRowPersonal, enableRowMaschine1, enableRowMaschine2, enableRowUmsetzen, enableRowProduktivitaet, enableRowRotationszeit, enableColumnProM3, showHintKostenProM3, showHintProduktivitaet, false);
	}
	
	
	
	public ErgebnisPanel(boolean enableRowPersonal, boolean enableRowMaschine1, boolean enableRowMaschine2, boolean enableRowUmsetzen, boolean enableRowProduktivitaet, boolean enableRowRotationszeit, boolean enableColumnProM3, boolean showHintKostenProM3, boolean showHintProduktivitaet, boolean enableRowMaschine3) {
		this.enableRowPersonal = enableRowPersonal;
		this.enableRowMaschine1 = enableRowMaschine1;
		this.enableRowMaschine2 = enableRowMaschine2;
		this.enableRowUmsetzen = enableRowUmsetzen;
		this.enableRowProduktivitaet = enableRowProduktivitaet;
		this.enableRowRotationszeit = enableRowRotationszeit;
		this.enableColumnProM3 = enableColumnProM3;	
		this.showHintKostenProM3 = showHintKostenProM3;
		this.showHintProduktivitaet = showHintProduktivitaet;
		this.enableRowMaschine3 = enableRowMaschine3;	
		
		//set title font to bold
		this.setBorder( BorderFactory.createTitledBorder(GuiStrings.getString("ErgebnisPanel.Title"))); //$NON-NLS-1$
		TitledBorder border = (TitledBorder) this.getBorder();
		Font titleFont = border.getTitleFont();
		if (titleFont == null) { //needed since java 7, see http://bugs.java.com/view_bug.do?bug_id=7022041
//			titleFont = UIManager.getDefaults().getFont("TitledBorder.font");
			titleFont = UIManager.getFont("TitledBorder.font"); //$NON-NLS-1$
		}
		border.setTitleFont( titleFont.deriveFont(Font.BOLD) );
		
		this.setBackground(ERGEBNIS_PANEL_BACKGROUND_COLOR);	
		
		initContent();
	}
	
	
	
	@Override
	protected JPanel initPanelTitles() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		Insets standardInsets = new Insets(4,0,2,0);
		
		//label Dauer der Arbeit
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(19,0,2,0);
		lblDauerDerArbeit = new JLabel(GuiStrings.getString("ErgebnisPanel.DauerDerArbeit")); //$NON-NLS-1$
		panel.add(lblDauerDerArbeit, c);
		
		//label Personal
		if (enableRowPersonal == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			c.insets = standardInsets;
			panel.add(lblPersonal, c);
		}
		
		//label Maschine 1
		if (enableRowMaschine1 == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			c.insets = standardInsets;
			panel.add(lblMaschine1, c);
		}
		
		//label Maschine 2
		if (enableRowMaschine2 == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			c.insets = standardInsets;
			panel.add(lblMaschine2, c);
		}
		
		//label Maschine 3
		if (enableRowMaschine3 == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 4;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			c.insets = standardInsets;
			panel.add(lblMaschine3, c);
		}
		
		//label Umsetzen
		if (enableRowUmsetzen == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 5;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			c.insets = standardInsets;
			panel.add(lblUmsetzen, c);
		}
		
		//label Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblWeitereAufwaende = new JLabel(GuiStrings.getString("ErgebnisPanel.WeitereAufwaende")); //$NON-NLS-1$
		panel.add(lblWeitereAufwaende, c);
		
		//label Total
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblTotal = new JLabel(GuiStrings.getString("ErgebnisPanel.Total")); //$NON-NLS-1$
		panel.add(lblTotal, c);
		
		
		return panel;
	}
	
	
	
	@Override
	protected JPanel initPanelZeitaufwand() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ErgebnisPanel.ZeitaufwandStd"))); //$NON-NLS-1$
		panel.setOpaque(false);
		
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//text Dauer der Arbeit
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtZeitDauerDerArbeit = getNewLockedTextField();
		panel.add(txtZeitDauerDerArbeit, c);
		
		//label Dauer der Arbeit
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		lblZeitDauerDerArbeit = new JLabel("WPSH"); //$NON-NLS-1$
		panel.add(lblZeitDauerDerArbeit, c);

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0,15,0,5);
		lblZeitDauerDerArbeit_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonWPSH")); //$NON-NLS-1$
		panel.add(lblZeitDauerDerArbeit_Info, c);
		

		if (enableRowPersonal == true) {

			//text Personal
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtZeitPersonal = getNewLockedTextField();
			panel.add(txtZeitPersonal, c);

			//label Personal
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 1;
			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(0,5,0,0);
			JLabel lblZeitPersonal = new JLabel("WPPH"); //$NON-NLS-1$
			panel.add(lblZeitPersonal, c);

			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 1;
			c.insets = new Insets(0,15,0,5);
			JLabel lblZeitPersonal_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonWPPH")); //$NON-NLS-1$
			panel.add(lblZeitPersonal_Info, c);
		}

		if (enableRowMaschine1 == true) {
			
			//text Maschine1
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			txtZeitMaschine1 = getNewLockedTextField();
			panel.add(txtZeitMaschine1, c);

			//label Maschine1
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 2;
			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(0,5,0,0);
			JLabel lblZeitMaschine1 = new JLabel("PMH15"); //$NON-NLS-1$
			panel.add(lblZeitMaschine1, c);

	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 2;
			c.insets = new Insets(0,15,0,5);
			JLabel lblZeitMaschine1_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonPMH15")); //$NON-NLS-1$
			panel.add(lblZeitMaschine1_Info, c);
		}

		if (enableRowMaschine2 == true) {
			
			//text Maschine2
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			txtZeitMaschine2 = getNewLockedTextField();
			panel.add(txtZeitMaschine2, c);

			//label Maschine1
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 3;
			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(0,5,0,0);
			JLabel lblZeitMaschine2 = new JLabel("PMH15"); //$NON-NLS-1$
			panel.add(lblZeitMaschine2, c);

	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 3;
			c.insets = new Insets(0,15,0,5);
			JLabel lblZeitMaschine2_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonPMH15")); //$NON-NLS-1$
			panel.add(lblZeitMaschine2_Info, c);
		}

		if (enableRowMaschine3 == true) {
			
			//text Maschine3
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 4;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			txtZeitMaschine3 = getNewLockedTextField();
			panel.add(txtZeitMaschine3, c);

			//label Maschine3
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 4;
			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(0,5,0,0);
			JLabel lblZeitMaschine3 = new JLabel("PMH15"); //$NON-NLS-1$
			panel.add(lblZeitMaschine3, c);

	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 4;
			c.insets = new Insets(0,15,0,5);
			JLabel lblZeitMaschine3_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonPMH15")); //$NON-NLS-1$
			panel.add(lblZeitMaschine3_Info, c);
		}
		
		//text Umsetzen
		if (enableRowUmsetzen == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 5;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtZeitUmsetzen = getNewLockedTextField();
			panel.add(txtZeitUmsetzen, c);
			
			//label Umsetzen
	        c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 5;
			c.anchor = GridBagConstraints.WEST;
			c.insets = new Insets(0,5,0,0);
			JLabel lblZeitUmsetzen = new JLabel("WPPH"); //$NON-NLS-1$
			panel.add(lblZeitUmsetzen, c);

	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 5;
			c.insets = new Insets(0,15,0,5);
			JLabel lblZeitUmsetzen_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonWPPH")); //$NON-NLS-1$
			panel.add(lblZeitUmsetzen_Info, c);
		}
		
		//text Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtZeitWeitereAufwaende = getNewLockedTextField();
		panel.add(txtZeitWeitereAufwaende, c);
		
		//label Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 6;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		JLabel lblZeitWeitereAufwaende = new JLabel("WPPH"); //$NON-NLS-1$
		panel.add(lblZeitWeitereAufwaende, c);

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
		c.insets = new Insets(0,15,0,5);
		JLabel lblZeitWeitereAufwaende_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonWPPH")); //$NON-NLS-1$
		panel.add(lblZeitWeitereAufwaende_Info, c);
		
		
		//text Total
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblZeitTotal = new JLabel(" "); //$NON-NLS-1$
		panel.add(lblZeitTotal, c);
		
		
		
		return panel;
	}
	
	
	
	@Override
	protected JPanel initPanelKosten() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(GuiStrings.getString("ErgebnisPanel.Kosten_SFr"))); //$NON-NLS-1$
		panel.setOpaque(false);
		
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//label 'pro m3'
		if (enableColumnProM3 == true) {
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
//			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 50;
			c.insets = new Insets(7,0,3,0);
			lblKostenproM3.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblKostenproM3, c);
			
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 50;
			c.insets = new Insets(2,5,0,5);
			JLabel lblKostenProM3_Info = showHintKostenProM3 ? GuiStrings.getInfoButtonRed(GuiStrings.getString("ErgebnisPanel.InfoButtonKostenProM3")) : new JLabel(); //$NON-NLS-1$
			lblKostenProM3_Info.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(lblKostenProM3_Info, c);
		}
		
		//label 'total'
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(7,0,3,0);
		JLabel lblTotal = new JLabel(GuiStrings.getString("ErgebnisPanel.total")); //$NON-NLS-1$
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTotal, c);		
		
		
		

		if (enableColumnProM3 == true) {

			//text Personal
			if (enableRowPersonal == true) {
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 1;
				c.gridwidth = 2;
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 100;
				//		c.insets = new Insets(5,5,5,5);
				txtKostenPersonal_proM3 = getNewLockedTextField();
				panel.add(txtKostenPersonal_proM3, c);
			}

			//text Maschine1
			if (enableRowMaschine1 == true) {
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 2;
				c.gridwidth = 2;
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 100;
				//c.insets = new Insets(5,5,5,5);
				txtKostenMaschine1_proM3 = getNewLockedTextField();
				panel.add(txtKostenMaschine1_proM3, c);
			}

			//text Maschine2
			if (enableRowMaschine2 == true) {
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 100;
				//c.insets = new Insets(5,5,5,5);
				txtKostenMaschine2_proM3 = getNewLockedTextField();
				panel.add(txtKostenMaschine2_proM3, c);
			}

			//text Maschine3
			if (enableRowMaschine3 == true) {
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 4;
				c.gridwidth = 2;
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 100;
				//c.insets = new Insets(5,5,5,5);
				txtKostenMaschine3_proM3 = getNewLockedTextField();
				panel.add(txtKostenMaschine3_proM3, c);
			}

			//text Umsetzen
			if (enableRowUmsetzen == true) {
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 5;
				c.gridwidth = 2;
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 100;
				//		c.insets = new Insets(5,5,5,5);
				txtKostenUmsetzen_proM3 = getNewLockedTextField();
				panel.add(txtKostenUmsetzen_proM3, c);
			}

			//text Weitere Aufwaende
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 6;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtKostenWeitereAufwaende_proM3 = getNewLockedTextField();
			panel.add(txtKostenWeitereAufwaende_proM3, c);

			//text Total
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 7;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtKostenTotal_proM3 = getNewLockedTextField();
			txtKostenTotal_proM3.setFont( txtKostenTotal_proM3.getFont().deriveFont(Font.BOLD) );
			panel.add(txtKostenTotal_proM3, c);

		}
		
		
		
		//text Personal
		if (enableRowPersonal == true) {
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 1;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtKostenPersonal_total = getNewLockedTextField();
			panel.add(txtKostenPersonal_total, c);
		}
		
		//text Maschine1
		if (enableRowMaschine1 == true) {
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 2;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			txtKostenMaschine1_total = getNewLockedTextField();
			panel.add(txtKostenMaschine1_total, c);
		}
		
		//text Maschine2
		if (enableRowMaschine2 == true) {
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 3;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			txtKostenMaschine2_total = getNewLockedTextField();
			panel.add(txtKostenMaschine2_total, c);
		}
		
		//text Maschine2
		if (enableRowMaschine3 == true) {
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 4;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//c.insets = new Insets(5,5,5,5);
			txtKostenMaschine3_total = getNewLockedTextField();
			panel.add(txtKostenMaschine3_total, c);
		}
		
		//text Umsetzen
		if (enableRowUmsetzen == true) {
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 5;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtKostenUmsetzen_total = getNewLockedTextField();
			panel.add(txtKostenUmsetzen_total, c);
		}
		
		//text Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtKostenWeitereAufwaende_total = getNewLockedTextField();
		panel.add(txtKostenWeitereAufwaende_total, c);
		
		//text Total
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtKostenTotal_total = getNewLockedTextField();
		panel.add(txtKostenTotal_total, c);
		
		return panel;
	}
	
	
	
	@Override
	protected JPanel initPanelProduktivitaetUndRotationszeit() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;


		if (enableRowProduktivitaet == true) {
			//label produktivitaet
	        c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			if (showHintProduktivitaet == false) {
				c.weightx = 50;
			}
//			c.insets = new Insets(5,5,5,5);
			lblProduktivitaet.setName("lblProduktivitaet");
			panel.add(lblProduktivitaet, c);
			
			if (showHintProduktivitaet == true) {
		        c = new GridBagConstraints();
				c.gridx = 1;
				c.gridy = 0;
				c.fill = GridBagConstraints.BOTH;
//				c.anchor = GridBagConstraints.WEST;
				c.weightx = 50;
				c.insets = new Insets(2,5,0,5);
				lblProduktivitaet_Info = GuiStrings.getInfoButtonRed(GuiStrings.getString("ErgebnisPanel.InfoButtonProduktivitaet")); //$NON-NLS-1$
				lblProduktivitaet_Info.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblProduktivitaet_Info, c);
			}
			
			//text produktivitaet
	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 50;
//			c.insets = new Insets(5,5,5,5);
			txtProduktivitaet = getNewLockedTextField();
			txtProduktivitaet.setName("txtProduktivitaet");
			panel.add(txtProduktivitaet, c);
			
			//label produktivität2
	        c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 0;
			c.insets = new Insets(0,15,0,0);
			c.anchor = GridBagConstraints.WEST;
			lblProduktivitaet2.setName("lblProduktivitaet2");
			panel.add(lblProduktivitaet2, c);
		}


		if (enableRowRotationszeit == false) {
			//placeholder
	        c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 200;
//			c.insets = new Insets(5,5,5,5);
			JPanel pnlPlaceholder = new JPanel();
			pnlPlaceholder.setOpaque(false);
			panel.add(pnlPlaceholder, c);
		}


		if (enableRowProduktivitaet && enableRowRotationszeit) {
			//placeholder
	        c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 250;
//			c.insets = new Insets(5,5,5,5);
			JPanel pnlPlaceholder = new JPanel();
			pnlPlaceholder.setOpaque(false);
			panel.add(pnlPlaceholder, c);
		}


		if (enableRowRotationszeit == true) {
			//label rotationszeit
	        c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
//			c.insets = new Insets(5,5,5,5);
			lblRotationszeit = new JLabel(GuiStrings.getString("ErgebnisPanel.Rotationszeit_MinProRot")); //$NON-NLS-1$
			panel.add(lblRotationszeit, c);
			
			//text rotationszeit
	        c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = 0;
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 100;
//			c.insets = new Insets(5,5,5,5);
			txtRotationszeit = getNewLockedTextField();
			panel.add(txtRotationszeit, c);
		}
		
		
		return panel;
	}
	
	
	public void setTitle(String title) {
		TitledBorder border = (TitledBorder) this.getBorder();
		border.setTitle(title);
	}
	
	
	@Override
	public void setErgebnis(Ergebnis ergebnis) {
		
		setLabels(ergebnis);
		
		txtZeitDauerDerArbeit			.setText(parseDouble( ergebnis.getZeitTotal() 					));
		txtZeitPersonal					.setText(parseDouble( ergebnis.getZeitPersonal() 				));
		txtZeitMaschine1				.setText(parseDouble( ergebnis.getZeitMaschine1() 				));
		txtZeitMaschine2				.setText(parseDouble( ergebnis.getZeitMaschine2() 				));
		txtZeitMaschine3				.setText(parseDouble( ergebnis.getZeitMaschine3() 				));
		txtZeitUmsetzen					.setText(parseDouble( ergebnis.getZeitUmsetzen() 				));
		txtZeitWeitereAufwaende			.setText(parseDouble( ergebnis.getZeitWeitereAufwaende() 		));
		
		txtKostenPersonal_proM3			.setText(parseDouble( ergebnis.getKostenPersonal_proM3() 		));
		txtKostenMaschine1_proM3		.setText(parseDouble( ergebnis.getKostenMaschine1_proM3() 		));
		txtKostenMaschine2_proM3		.setText(parseDouble( ergebnis.getKostenMaschine2_proM3() 		));
		txtKostenMaschine3_proM3		.setText(parseDouble( ergebnis.getKostenMaschine3_proM3() 		));
		txtKostenUmsetzen_proM3			.setText(parseDouble( ergebnis.getKostenUmsetzen_proM3() 		));
		txtKostenWeitereAufwaende_proM3	.setText(parseDouble( ergebnis.getKostenWeitereAufwaende_proM3() ));
		txtKostenTotal_proM3			.setText(parseDouble( ergebnis.getKostenTotal_proM3() 			));

		txtKostenPersonal_total			.setText(parseDouble( ergebnis.getKostenPersonal_total() 		));
		txtKostenMaschine1_total		.setText(parseDouble( ergebnis.getKostenMaschine1_total() 		));
		txtKostenMaschine2_total		.setText(parseDouble( ergebnis.getKostenMaschine2_total() 		));
		txtKostenMaschine3_total		.setText(parseDouble( ergebnis.getKostenMaschine3_total() 		));
		txtKostenUmsetzen_total			.setText(parseDouble( ergebnis.getKostenUmsetzen_total() 		));
		txtKostenWeitereAufwaende_total	.setText(parseDouble( ergebnis.getKostenWeitereAufwaende_total() ));
		txtKostenTotal_total			.setText(parseDouble( ergebnis.getKostenTotal_total() 			));	

		txtProduktivitaet				.setText(parseDouble( ergebnis.getProduktivitaet() 				));
		if (ergebnis.getProduktivitaet() == -1) {
			txtProduktivitaet.setText("-"); //$NON-NLS-1$
		}
		
		if (ergebnis.getProduktivitaet2() > 0) {
			lblProduktivitaet2.setValue(ergebnis.getProduktivitaet2());
		}
		else {
			lblProduktivitaet2.setValue(null);
		}

		
		if (ergebnis instanceof ErgebnisHelikopterFliegen) {
			txtRotationszeit.setText(parseDouble( ((ErgebnisHelikopterFliegen)ergebnis).getRotationszeit() ));
		}
	}
	
	
	private void setLabels(Ergebnis ergebnis) {
		lblPersonal.setText( 	setFirstLetterOfStringToUpperCase(ergebnis.getLabelPersonal1()) );
		lblMaschine1.setText(	setFirstLetterOfStringToUpperCase(ergebnis.getLabelMaschine1()) );
		lblMaschine2.setText( 	setFirstLetterOfStringToUpperCase(ergebnis.getLabelMaschine2()) );
		lblMaschine3.setText( 	setFirstLetterOfStringToUpperCase(ergebnis.getLabelMaschine3()) );
	}
	
	private String setFirstLetterOfStringToUpperCase(String inputString) {
		if (inputString == null || inputString.isEmpty()) {
			return inputString;
		}
		String result = inputString.substring(0, 1).toUpperCase() + inputString.substring(1, inputString.length());
		return result;
	}
	
	
	@Override
	public void setDecimalFormat(DecimalFormat decimalFormat) {
		super.setDecimalFormat(decimalFormat);
		lblProduktivitaet2.setDecimalFormat(decimalFormat);
	}
	
	
	public void setLabelUmsetzen(String label) {
		lblUmsetzen.setText(label);
	}
	
	
	public void setLabelKostenProM3(String label) {
		 lblKostenproM3.setText(label);
	}
	
	
	public void setLabelProduktivitaet(String label) {
		lblProduktivitaet.setText(label);
	}
	
	
	public void setLabelProduktivitaet2(String label) {
		lblProduktivitaet2.setUnit(label);
	}
	
	
	public void setHintProduktivitaet(String text) {
		lblProduktivitaet_Info.setToolTipText(text);
	}
	
	
	public void hideDauerDerArbeit() {
		//Wenn die Felder nur auf visible=false gesetzt werden,
		// stimmen die Abstände nicht mehr. Deshalb werden hier 
		// einzelne Felder unsichtbar gemacht, indem sie dieselbe
		// Farbe wie der Hintergrund erhalten.
		lblDauerDerArbeit.setForeground(getBackground());
		
		txtZeitDauerDerArbeit.setEnabled(false);
		txtZeitDauerDerArbeit.setForeground(getBackground());
		txtZeitDauerDerArbeit.setBackground(getBackground());
		txtZeitDauerDerArbeit.setBorder(BorderFactory.createEmptyBorder());
		txtZeitDauerDerArbeit.setDisabledTextColor(getBackground());

		lblZeitDauerDerArbeit.setVisible(false);
		lblZeitDauerDerArbeit_Info.setVisible(false);
	}
	
	
}
