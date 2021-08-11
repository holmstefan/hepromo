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

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.ErgebnisHelikopterGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisPanelHelikopterGesamt extends AbstractErgebnisPanel {

	private static final long serialVersionUID = 1L;
	

	private JTextField txtZeitDauerDerArbeitFliegen;
	private JTextField txtZeitDauerDerArbeitAbsenkplatz;
	private JTextField txtZeitHelifirma;
	private JTextField txtZeitPersonalForstbetrieb;
	private JTextField txtZeitMaschine1;
	private JTextField txtZeitMaschine2;
	private JTextField txtZeitUmsetzen;
	private JTextField txtZeitWeitereAufwaende;;

	private JTextField txtKostenHelifirma_proM3;
	private JTextField txtKostenPersonalForstbetrieb_proM3;
	private JTextField txtKostenMaschine1_proM3;
	private JTextField txtKostenMaschine2_proM3;
	private JTextField txtKostenUmsetzen_proM3;
	private JTextField txtKostenWeitereAufwaende_proM3;
	private JTextField txtKostenTotal_proM3;

	private JTextField txtKostenHelifirma_total;
	private JTextField txtKostenPersonalForstbetrieb_total;
	private JTextField txtKostenMaschine1_total;
	private JTextField txtKostenMaschine2_total;
	private JTextField txtKostenUmsetzen_total;
	private JTextField txtKostenWeitereAufwaende_total;
	private JTextField txtKostenTotal_total;

//	private JTextField txtProduktivitaet;
	private JTextField txtRotationszeit;
	
	
	
	public ErgebnisPanelHelikopterGesamt() {
		this.setBorder( TitledBorderFactory.createTitledBorderBold(GuiStrings.getString("ErgebnisPanel.Title"))); //$NON-NLS-1$
		this.setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);		
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
		c.insets = new Insets(35,0,2,0);
		JLabel lblDauerDerArbeit = new JLabel(GuiStrings.getString("ErgebnisPanel.DauerDerArbeit")); //$NON-NLS-1$
		panel.add(lblDauerDerArbeit, c);
		
		//label Helifirma
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(19,0,2,0);
		JLabel lblHelifirma = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.Helifirma")); //$NON-NLS-1$
		panel.add(lblHelifirma, c);
		
		//label Personal (Forstbetrieb)
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblPersonal = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.PersonalForstbetrieb")); //$NON-NLS-1$
		panel.add(lblPersonal, c);
		
		//label Maschine 1 (Motorsäge)
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblMaschine1 = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.Motorsaege")); //$NON-NLS-1$
		panel.add(lblMaschine1, c);
		
		//label Maschine 2 (Kranfahrzeug)
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblMaschine2 = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.Kranfahrzeug")); //$NON-NLS-1$
		panel.add(lblMaschine2, c);
		
		//label Umsetzen
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblUmsetzen = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.UmsetzenKranfahrzeug")); //$NON-NLS-1$
		panel.add(lblUmsetzen, c);
		
		//label Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = standardInsets;
		JLabel lblWeitereAufwaende = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.WeitereAufwaende")); //$NON-NLS-1$
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
		panel.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("ErgebnisPanel.ZeitaufwandStd"))); //$NON-NLS-1$
		panel.setOpaque(false);
		
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		

		
		//label Fliegen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblFliegen = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.Fliegen")); //$NON-NLS-1$
		lblFliegen.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblFliegen, c);
		
		//label Absenkplatz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		JLabel lblAbsenkplatz = new JLabel(GuiStrings.getString("ErgebnisPanelHelikopterGesamt.Absenkplatz")); //$NON-NLS-1$
		lblAbsenkplatz.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblAbsenkplatz, c);
		
		
		

		//text Dauer der Arbeit Fliegen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtZeitDauerDerArbeitFliegen = getNewLockedTextField();
		panel.add(txtZeitDauerDerArbeitFliegen, c);
		
		//text Dauer der Arbeit Absenkplatz
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
//		c.weightx = 50;
//		c.insets = new Insets(5,5,5,5);
		txtZeitDauerDerArbeitAbsenkplatz = getNewLockedTextField();
		panel.add(txtZeitDauerDerArbeitAbsenkplatz, c);
		
		
		

		
		//text Helifirma
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(15,0,0,0);
		txtZeitHelifirma = getNewLockedTextField();
		panel.add(txtZeitHelifirma, c);
		
		//label Helifirma
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(15,5,0,0);
		JLabel lblZeitHelifirma = new JLabel("PMH15"); //$NON-NLS-1$
		panel.add(lblZeitHelifirma, c);

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(15,15,0,5);
		JLabel lblZeitHelifirma_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonPMH15")); //$NON-NLS-1$
		panel.add(lblZeitHelifirma_Info, c);
		
		
		
		
		//text Personal Forstbetrieb
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtZeitPersonalForstbetrieb = getNewLockedTextField();
		panel.add(txtZeitPersonalForstbetrieb, c);
		
		//label Personal
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		JLabel lblZeitPersonalForstbetrieb = new JLabel("WPPH"); //$NON-NLS-1$
		panel.add(lblZeitPersonalForstbetrieb, c);

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(0,15,0,5);
		JLabel lblZeitPersonalForstbetrieb_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonWPPH")); //$NON-NLS-1$
		panel.add(lblZeitPersonalForstbetrieb_Info, c);



		//text Maschine1 (Motorsäge)
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtZeitMaschine1 = getNewLockedTextField();
		panel.add(txtZeitMaschine1, c);

		//label Maschine1
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		JLabel lblZeitMaschine1 = new JLabel("PMH15"); //$NON-NLS-1$
		panel.add(lblZeitMaschine1, c);

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(0,15,0,5);
		JLabel lblZeitMaschine1_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonPMH15")); //$NON-NLS-1$
		panel.add(lblZeitMaschine1_Info, c);


		//text Maschine2 (Kranfahrzeug)
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtZeitMaschine2 = getNewLockedTextField();
		panel.add(txtZeitMaschine2, c);

		//label Maschine2
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0,5,0,0);
		JLabel lblZeitMaschine2 = new JLabel("PMH15"); //$NON-NLS-1$
		panel.add(lblZeitMaschine2, c);

        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.insets = new Insets(0,15,0,5);
		JLabel lblZeitMaschine2_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("ErgebnisPanel.InfoButtonPMH15")); //$NON-NLS-1$
		panel.add(lblZeitMaschine2_Info, c);

		
		//text Umsetzen
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtZeitUmsetzen = getNewLockedTextField();
		panel.add(txtZeitUmsetzen, c);
		
		//text Weitere Aufwaende
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 7;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtZeitWeitereAufwaende = getNewLockedTextField();
		panel.add(txtZeitWeitereAufwaende, c);
		
		//text Total
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 8;
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
		panel.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("ErgebnisPanel.Kosten_SFr"))); //$NON-NLS-1$
		panel.setOpaque(false);
		
		
		//set layout
		panel.setLayout( new GridBagLayout() );
		GridBagConstraints c;

		
		//label 'pro m3'
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 50;
		c.insets = new Insets(20,0,3,0);
		JLabel lblproM3 = new JLabel(GuiStrings.getString("ErgebnisPanel.pro_m3_oR")); //$NON-NLS-1$
		lblproM3.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblproM3, c);
		
		//label 'total'
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.insets = new Insets(20,0,3,0);
		JLabel lblTotal = new JLabel(GuiStrings.getString("ErgebnisPanel.total")); //$NON-NLS-1$
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTotal, c);		
		
		
		


		//text Helifirma
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtKostenHelifirma_proM3 = getNewLockedTextField();
		panel.add(txtKostenHelifirma_proM3, c);

		//text Personal Forstbetrieb
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtKostenPersonalForstbetrieb_proM3 = getNewLockedTextField();
		panel.add(txtKostenPersonalForstbetrieb_proM3, c);

		//text Maschine1
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtKostenMaschine1_proM3 = getNewLockedTextField();
		panel.add(txtKostenMaschine1_proM3, c);

		//text Maschine2
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtKostenMaschine2_proM3 = getNewLockedTextField();
		panel.add(txtKostenMaschine2_proM3, c);

		//text Umsetzen
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//		c.insets = new Insets(5,5,5,5);
		txtKostenUmsetzen_proM3 = getNewLockedTextField();
		panel.add(txtKostenUmsetzen_proM3, c);

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

		

		
		//text Helifirma
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtKostenHelifirma_total = getNewLockedTextField();
		panel.add(txtKostenHelifirma_total, c);
		
		//text Personal Forstbetrieb
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.insets = new Insets(5,5,5,5);
		txtKostenPersonalForstbetrieb_total = getNewLockedTextField();
		panel.add(txtKostenPersonalForstbetrieb_total, c);
		
		//text Maschine1
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtKostenMaschine1_total = getNewLockedTextField();
		panel.add(txtKostenMaschine1_total, c);

		//text Maschine2
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtKostenMaschine2_total = getNewLockedTextField();
		panel.add(txtKostenMaschine2_total, c);

		//text Umsetzen
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//		c.insets = new Insets(5,5,5,5);
		txtKostenUmsetzen_total = getNewLockedTextField();
		panel.add(txtKostenUmsetzen_total, c);
		
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


		//label rotationszeit
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		JLabel lblRotationszeit = new JLabel(GuiStrings.getString("ErgebnisPanel.Rotationszeit_MinProRot")); //$NON-NLS-1$
		panel.add(lblRotationszeit, c);

		//text rotationszeit
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		//c.insets = new Insets(5,5,5,5);
		txtRotationszeit = getNewLockedTextField();
		panel.add(txtRotationszeit, c);
		
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 150;
//		c.insets = new Insets(5,5,5,5);
		JPanel pnlPlaceholder = new JPanel();
		pnlPlaceholder.setOpaque(false);
		panel.add(pnlPlaceholder, c);
		
		
		return panel;
	}
	
	
	@Override
	public void setErgebnis(Ergebnis ergebnis) {
		ErgebnisHelikopterGesamt eHeli = (ErgebnisHelikopterGesamt) ergebnis;
		
		txtZeitDauerDerArbeitFliegen		.setText(parseDouble( eHeli.getZeitTotalFliegen() 			));
		txtZeitDauerDerArbeitAbsenkplatz	.setText(parseDouble( eHeli.getZeitTotalAbsenkplatz()		));
		txtZeitHelifirma					.setText(parseDouble( eHeli.getZeitHelifirma()				));
		txtZeitPersonalForstbetrieb			.setText(parseDouble( eHeli.getZeitPersonal() 				));
		txtZeitMaschine1					.setText(parseDouble( eHeli.getZeitMaschine1() 				));
		txtZeitMaschine2					.setText(parseDouble( eHeli.getZeitMaschine2() 				));
		txtZeitUmsetzen						.setText(parseDouble( eHeli.getZeitUmsetzen() 				));
		txtZeitWeitereAufwaende				.setText(parseDouble( eHeli.getZeitWeitereAufwaende() 		));

		txtKostenHelifirma_proM3			.setText(parseDouble( eHeli.getKostenHeli_proM3()			));
		txtKostenPersonalForstbetrieb_proM3	.setText(parseDouble( eHeli.getKostenPersonal_proM3() 		));
		txtKostenMaschine1_proM3			.setText(parseDouble( eHeli.getKostenMaschine1_proM3() 		));
		txtKostenMaschine2_proM3			.setText(parseDouble( eHeli.getKostenMaschine2_proM3() 		));
		txtKostenUmsetzen_proM3				.setText(parseDouble( eHeli.getKostenUmsetzen_proM3() 		));
		txtKostenWeitereAufwaende_proM3		.setText(parseDouble( eHeli.getKostenWeitereAufwaende_proM3() ));
		txtKostenTotal_proM3				.setText(parseDouble( eHeli.getKostenTotal_proM3() 			));

		txtKostenHelifirma_total			.setText(parseDouble( eHeli.getKostenHeli_total() 			));
		txtKostenPersonalForstbetrieb_total	.setText(parseDouble( eHeli.getKostenPersonal_total() 		));
		txtKostenMaschine1_total			.setText(parseDouble( eHeli.getKostenMaschine1_total() 		));
		txtKostenMaschine2_total			.setText(parseDouble( eHeli.getKostenMaschine2_total() 		));
		txtKostenUmsetzen_total				.setText(parseDouble( eHeli.getKostenUmsetzen_total() 		));
		txtKostenWeitereAufwaende_total		.setText(parseDouble( eHeli.getKostenWeitereAufwaende_total() ));
		txtKostenTotal_total				.setText(parseDouble( eHeli.getKostenTotal_total() 			));	

//		txtProduktivitaet					.setText(parseDouble( eHeli.getProduktivitaet() 				));
		txtRotationszeit					.setText(parseDouble( eHeli.getRotationszeit() 				));
	}
	
	
}
