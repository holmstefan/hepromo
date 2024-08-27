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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.TitledBorderFactory;

/**
 * 
 * @author Stefan Holm
 *
 */
public class KostensaetzePanel2014 extends JPanel implements IWaehrungsanzeige {

	private static final long serialVersionUID = 1L;
	
	protected final HeProMoWindow parent;
	
	private final boolean showZusaetzlichePersonen;
	private final boolean showPersonal1;
	private final boolean showMaschine2;
	private final boolean showMaschine3;

	private JSpinner txtAnsatzPersonal1;
	private JSpinner txtAnsatzPersonal2;
	private JSpinner txtAnsatzPersonal3;
	private JSpinner txtAnsatzPersonal4;
	private JSpinner txtEinsatzanteilPersonal1_Prz;
	private JSpinner txtEinsatzanteilPersonal2_Prz;
	private JSpinner txtEinsatzanteilPersonal3_Prz;
	private JSpinner txtEinsatzanteilPersonal4_Prz;
	private JSpinner txtAnteilPersonalKosten1_Prz;
	private JSpinner txtAnteilPersonalKosten2_Prz;
	private JSpinner txtAnteilPersonalKosten3_Prz;
	private JSpinner txtAnteilPersonalKosten4_Prz;
	private JSpinner txtAnsatzMaschine1;
	private JSpinner txtAnsatzMaschine2;
	private JSpinner txtAnsatzMaschine3;

	protected JLabel lblPersonal1A;
	protected JLabel lblPersonal2A;
	protected JLabel lblPersonal3A;
	protected JLabel lblPersonal4A;
	protected JLabel lblPersonal1B = new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProStd")); //$NON-NLS-1$
	protected JLabel lblPersonal2B = new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProStd")); //$NON-NLS-1$
	protected JLabel lblPersonal3B = new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProStd")); //$NON-NLS-1$
	protected JLabel lblPersonal4B = new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProStd")); //$NON-NLS-1$

	protected JLabel lblMaschine1A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Maschine")); //$NON-NLS-1$
	protected JLabel lblMaschine1B = new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProPMH15")); //$NON-NLS-1$
	protected JLabel lblMaschine1_Info;

	private final boolean isUnitMaschine2ISH;
	protected JLabel lblMaschine2A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Maschine")); //$NON-NLS-1$
	protected JLabel lblMaschine2B = new JLabel();
	protected JLabel lblMaschine2_Info;

	protected JLabel lblMaschine3A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Maschine")); //$NON-NLS-1$
	protected JLabel lblMaschine3B = new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProPMH15")); //$NON-NLS-1$
	protected JLabel lblMaschine3_Info;


	
	public KostensaetzePanel2014(Builder builder) {
		this.parent = builder.parent;
		this.showZusaetzlichePersonen = builder.showZusaetzlichePersonen;
		this.showPersonal1 = builder.showPersonal1;
		this.showMaschine2 = builder.showMaschine2;
		this.showMaschine3 = builder.showMaschine3;
		this.isUnitMaschine2ISH = builder.isUnitMaschine2ISH;
		initPanel();
		initData();
	}


	protected void initPanel() {
		this.setBorder(TitledBorderFactory.createTitledBorder(GuiStrings.getString("KostensaetzePanel2014.Title")));		 //$NON-NLS-1$
		
		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		 
		if (showZusaetzlichePersonen) {
			//Label Einsatzanteil
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 0;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			JLabel lblEinsatzanteil_Prz = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Einsatzzeitanteil_Prz")); //$NON-NLS-1$
			this.add(lblEinsatzanteil_Prz, c);

			//Label Anteil Personalkosten
			c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = 0;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			JLabel lblAnteilPersonalkosten_Prz = new JLabel(GuiStrings.getString("KostensaetzePanel2014.AnteilPersonalkosten_Prz")); //$NON-NLS-1$
			this.add(lblAnteilPersonalkosten_Prz, c);
		}



		if (showPersonal1) {
			//Label Personal A
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,0,0,5);
			lblPersonal1A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Arbeitskraft1")); //$NON-NLS-1$
			this.add(lblPersonal1A, c);

			//Text Personal
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			//		c.insets = new Insets(5,5,5,5);
			txtAnsatzPersonal1 = new JSpinner();
			this.add(txtAnsatzPersonal1, c);

			//Label Personal B
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,5,0,0);
			this.add(lblPersonal1B, c);
		}
		

		if (showZusaetzlichePersonen) {
			//Text Einsatzzeitanteil
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			txtEinsatzanteilPersonal1_Prz = new JSpinner();
			this.add(txtEinsatzanteilPersonal1_Prz, c);

			//Text Anteil Personalkosten
			c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = 1;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			txtAnteilPersonalKosten1_Prz = new JSpinner();
			txtAnteilPersonalKosten1_Prz.setEnabled(false);
			this.add(txtAnteilPersonalKosten1_Prz, c);





			//Label Personal A
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,0,0,5);
			lblPersonal2A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Arbeitskraft2")); //$NON-NLS-1$
			this.add(lblPersonal2A, c);

			//Text Personal
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			// c.insets = new Insets(5,5,5,5);
			txtAnsatzPersonal2 = new JSpinner();
			this.add(txtAnsatzPersonal2, c);

			//Label Personal B
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,5,0,0);
			this.add(lblPersonal2B, c);

			//Text Einsatzzeitanteil
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			txtEinsatzanteilPersonal2_Prz = new JSpinner();
			this.add(txtEinsatzanteilPersonal2_Prz, c);

			//Text Anteil Personalkosten
			c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = 2;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			txtAnteilPersonalKosten2_Prz = new JSpinner();
			txtAnteilPersonalKosten2_Prz.setEnabled(false);
			this.add(txtAnteilPersonalKosten2_Prz, c);





			//Label Personal A
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,0,0,5);
			lblPersonal3A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Arbeitskraft3")); //$NON-NLS-1$
			this.add(lblPersonal3A, c);

			//Text Personal
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			// c.insets = new Insets(5,5,5,5);
			txtAnsatzPersonal3 = new JSpinner();
			this.add(txtAnsatzPersonal3, c);

			//Label Personal B
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,5,0,0);
			this.add(lblPersonal3B, c);

			//Text Einsatzzeitanteil
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 150;
			c.insets = new Insets(0,10,0,0);
			txtEinsatzanteilPersonal3_Prz = new JSpinner();
			this.add(txtEinsatzanteilPersonal3_Prz, c);

			//Text Anteil Personalkosten
			c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = 3;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 150;
			c.insets = new Insets(0,10,0,0);
			txtAnteilPersonalKosten3_Prz = new JSpinner();
			txtAnteilPersonalKosten3_Prz.setEnabled(false);
			this.add(txtAnteilPersonalKosten3_Prz, c);







			//Label Personal A
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,0,0,5);
			lblPersonal4A = new JLabel(GuiStrings.getString("KostensaetzePanel2014.Arbeitskraft4")); //$NON-NLS-1$
			this.add(lblPersonal4A, c);

			//Text Personal
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			// c.insets = new Insets(5,5,5,5);
			txtAnsatzPersonal4 = new JSpinner();
			this.add(txtAnsatzPersonal4, c);

			//Label Personal B
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(0,5,0,0);
			this.add(lblPersonal4B, c);

			//Text Einsatzzeitanteil
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			txtEinsatzanteilPersonal4_Prz = new JSpinner();
			this.add(txtEinsatzanteilPersonal4_Prz, c);

			//Text Anteil Personalkosten
			c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = 4;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(0,10,0,0);
			txtAnteilPersonalKosten4_Prz = new JSpinner();
			txtAnteilPersonalKosten4_Prz.setEnabled(false);
			this.add(txtAnteilPersonalKosten4_Prz, c);
		}
		
		
		
		
		
		
		//Label Maschine 1 A
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,5);
		this.add(lblMaschine1A, c);
		
		//Text Maschine 1
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(10,0,0,0);
		txtAnsatzMaschine1 = new JSpinner();
		this.add(txtAnsatzMaschine1, c);
		
		//Label Maschine 1 B
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,5,0,0);
		this.add(lblMaschine1B, c);
		
		//Tooltip Maschine 1
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,5,0,0);
		lblMaschine1_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("KostensaetzePanel2014.InfoButtonKostenProBetriebsstundePMH15")); //$NON-NLS-1$
		this.add(lblMaschine1_Info, c);
		
		
		if (showMaschine2) {
			//Label Maschine 2 A
	        c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 6;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(10,0,0,5);
			this.add(lblMaschine2A, c);
			
			//Text Maschine 2
	        c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 6;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(10,0,0,0);
			txtAnsatzMaschine2 = new JSpinner();
			this.add(txtAnsatzMaschine2, c);
			
			//Label Maschine 2 B
	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 6;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,5,0,0);
			lblMaschine2B = isUnitMaschine2ISH ? 
					new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProISH")) : //$NON-NLS-1$ 
						new JLabel(GuiStrings.getString("KostensaetzePanel2014.SFrProPMH15")); //$NON-NLS-1$
			this.add(lblMaschine2B, c);
			
			//Tooltip Maschine 2
			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 6;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,5,0,0);
			lblMaschine2_Info = isUnitMaschine2ISH ? 
					GuiStrings.getInfoButtonBlue(GuiStrings.getString("KostensaetzePanel2014.InfoButtonKostenProBetriebsstundeISH")) :  //$NON-NLS-1$
						GuiStrings.getInfoButtonBlue(GuiStrings.getString("KostensaetzePanel2014.InfoButtonKostenProBetriebsstundePMH15")); //$NON-NLS-1$
			this.add(lblMaschine2_Info, c);
		}
		
		
		if (showMaschine3) {
			//Label Maschine 3 A
	        c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 7;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(10,0,0,5);
			this.add(lblMaschine3A, c);
			
			//Text Maschine 3
	        c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 7;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 100;
			c.insets = new Insets(10,0,0,0);
			txtAnsatzMaschine3 = new JSpinner();
			this.add(txtAnsatzMaschine3, c);
			
			//Label Maschine 3 B
	        c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = 7;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,5,0,0);
			this.add(lblMaschine3B, c);
			
			//Tooltip Maschine 2
			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 7;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,5,0,0);
			lblMaschine3_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("KostensaetzePanel2014.InfoButtonKostenProBetriebsstundePMH15")); //$NON-NLS-1$
			this.add(lblMaschine3_Info, c);
		}
		
		
		
		//Placeholder
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 9;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 100;
		this.add(new JPanel(), c);
	}



	protected void initData() {
		if (showPersonal1) {
			txtAnsatzPersonal1.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
			txtAnsatzPersonal1.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal1, "0.##")); //$NON-NLS-1$
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal1);
		}

		if (showZusaetzlichePersonen) {
			txtEinsatzanteilPersonal1_Prz.setModel(new SpinnerNumberModel(100, 100, 100, 1));
			txtEinsatzanteilPersonal1_Prz.setEnabled(false);
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEinsatzanteilPersonal1_Prz);

			txtAnteilPersonalKosten1_Prz.setModel(new SpinnerNumberModel(00, 0, 100, 1));
			txtAnteilPersonalKosten1_Prz.setEditor(new JSpinner.NumberEditor(txtAnteilPersonalKosten1_Prz, "0.##")); //$NON-NLS-1$




			txtAnsatzPersonal2.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
			txtAnsatzPersonal2.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal2, "0.##")); //$NON-NLS-1$
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal2);

			txtEinsatzanteilPersonal2_Prz.setModel(new SpinnerNumberModel(100, 0, 100, 1));
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEinsatzanteilPersonal2_Prz);

			txtAnteilPersonalKosten2_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			txtAnteilPersonalKosten2_Prz.setEditor(new JSpinner.NumberEditor(txtAnteilPersonalKosten2_Prz, "0.##")); //$NON-NLS-1$




			txtAnsatzPersonal3.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
			txtAnsatzPersonal3.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal3, "0.##")); //$NON-NLS-1$
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal3);

			txtEinsatzanteilPersonal3_Prz.setModel(new SpinnerNumberModel(100, 0, 100, 1));
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEinsatzanteilPersonal3_Prz);

			txtAnteilPersonalKosten3_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			txtAnteilPersonalKosten3_Prz.setEditor(new JSpinner.NumberEditor(txtAnteilPersonalKosten3_Prz, "0.##")); //$NON-NLS-1$




			txtAnsatzPersonal4.setModel(new SpinnerNumberModel(55.0, 0, 1000, 1));
			txtAnsatzPersonal4.setEditor(new JSpinner.NumberEditor(txtAnsatzPersonal4, "0.##")); //$NON-NLS-1$
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzPersonal4);

			txtEinsatzanteilPersonal4_Prz.setModel(new SpinnerNumberModel(100, 0, 100, 1));
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtEinsatzanteilPersonal4_Prz);

			txtAnteilPersonalKosten4_Prz.setModel(new SpinnerNumberModel(0, 0, 100, 1));
			txtAnteilPersonalKosten4_Prz.setEditor(new JSpinner.NumberEditor(txtAnteilPersonalKosten4_Prz, "0.##")); //$NON-NLS-1$
		}
		
		
		
		
		txtAnsatzMaschine1.setModel(new SpinnerNumberModel(14.0, 0, 1000, 1));
		txtAnsatzMaschine1.setEditor(new JSpinner.NumberEditor(txtAnsatzMaschine1, "0.##")); //$NON-NLS-1$
		parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMaschine1);
		
		
		if (showMaschine2) {
			txtAnsatzMaschine2.setModel(new SpinnerNumberModel(14.0, 0, 1000, 1));
			txtAnsatzMaschine2.setEditor(new JSpinner.NumberEditor(txtAnsatzMaschine2, "0.##")); //$NON-NLS-1$
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMaschine2);
		}
		
		
		if (showMaschine3) {
			txtAnsatzMaschine3.setModel(new SpinnerNumberModel(14.0, 0, 1000, 1));
			txtAnsatzMaschine3.setEditor(new JSpinner.NumberEditor(txtAnsatzMaschine3, "0.##")); //$NON-NLS-1$
			parent.addDefaultChangeListenerAndAdjustJSpinnerFormatter(txtAnsatzMaschine3);
		}
		

		if (showZusaetzlichePersonen) {
			onInputChangedBeforeCalculation(this);
		}
	}
	
	
	
	
	
	public double getAnsatzPersonal1() {
		if (showPersonal1) {
			return (Double) txtAnsatzPersonal1.getValue();
		}
		return 0;
	}
	
	public void setAnsatzPersonal1(double ansatz) {
		if (showPersonal1) {
			txtAnsatzPersonal1.setValue(ansatz);
		}
	}
	
	public void setLabelPersonal(String name) {
		if (showPersonal1) {
			lblPersonal1A.setText(setFirstLetterOfStringToUpperCase(name));
		}
	}

	public int getEinsatzanteilPersonal1_Prz() {
		return (Integer) txtEinsatzanteilPersonal1_Prz.getValue();
	}

	public void setEinsatzzeitanteilPersonal1_Prz(int value) {
		txtEinsatzanteilPersonal1_Prz.setValue(value);
		onInputChangedBeforeCalculation(this);
	}
	
	
	


	public double getAnsatzPersonal2() {
		return (Double) txtAnsatzPersonal2.getValue();
	}
	
	public void setAnsatzPersonal2(double ansatz) {
		txtAnsatzPersonal2.setValue(ansatz);
		onInputChangedBeforeCalculation(this);
	}
	
	public int getEinsatzanteilPersonal2_Prz() {
		return (Integer) txtEinsatzanteilPersonal2_Prz.getValue();
	}
	
	public void setEinsatzzeitanteilPersonal2_Prz(int value) {
		txtEinsatzanteilPersonal2_Prz.setValue(value);
		onInputChangedBeforeCalculation(this);
	}
	
	
	
	
	
	public double getAnsatzPersonal3() {
		return (Double) txtAnsatzPersonal3.getValue();
	}
	
	public void setAnsatzPersonal3(double ansatz) {
		txtAnsatzPersonal3.setValue(ansatz);
		onInputChangedBeforeCalculation(this);
	}
	
	public int getEinsatzanteilPersonal3_Prz() {
		return (Integer) txtEinsatzanteilPersonal3_Prz.getValue();
	}
	
	public void setEinsatzzeitanteilPersonal3_Prz(int value) {
		txtEinsatzanteilPersonal3_Prz.setValue(value);
		onInputChangedBeforeCalculation(this);
	}
	
	
	
	
	
	public double getAnsatzPersonal4() {
		return (Double) txtAnsatzPersonal4.getValue();
	}
	
	public void setAnsatzPersonal4(double ansatz) {
		txtAnsatzPersonal4.setValue(ansatz);
		onInputChangedBeforeCalculation(this);
	}
	
	public int getEinsatzanteilPersonal4_Prz() {
		return (Integer) txtEinsatzanteilPersonal4_Prz.getValue();
	}
	
	public void setEinsatzzeitanteilPersonal4_Prz(int value) {
		txtEinsatzanteilPersonal4_Prz.setValue(value);
		onInputChangedBeforeCalculation(this);
	}
	
	
	

	
	public double getAnsatzMaschine1() {
		return (Double) txtAnsatzMaschine1.getValue();
	}
	
	
	public void setAnsatzMaschine1(double ansatz) {
		txtAnsatzMaschine1.setValue(ansatz);
	}
	
	
	public void setLabelMaschine1(String name) {
		lblMaschine1A.setText(setFirstLetterOfStringToUpperCase(name));
	}
	
	
	
	
	public double getAnsatzMaschine2() {
		return (Double) txtAnsatzMaschine2.getValue();
	}
	
	
	public void setAnsatzMaschine2(double ansatz) {
		txtAnsatzMaschine2.setValue(ansatz);
	}
	
	
	public void setLabelMaschine2(String name) {
		lblMaschine2A.setText(setFirstLetterOfStringToUpperCase(name));
	}
	
	
	
	
	public double getAnsatzMaschine3() {
		return (Double) txtAnsatzMaschine3.getValue();
	}
	
	
	public void setAnsatzMaschine3(double ansatz) {
		txtAnsatzMaschine3.setValue(ansatz);
	}
	
	
	public void setLabelMaschine3(String name) {
		lblMaschine3A.setText(setFirstLetterOfStringToUpperCase(name));
	}
	
	
	private String setFirstLetterOfStringToUpperCase(String inputString) {
		if (inputString == null) {
			return null;
		}
		String result = inputString.substring(0, 1).toUpperCase() + inputString.substring(1, inputString.length());
		return result;
	}
	
	
	
	

	/**
	 * @param eventSource  
	 */
	public void onInputChangedBeforeCalculation(Object eventSource) {

		double anteil1 = (getEinsatzanteilPersonal1_Prz() / 100.0) * getAnsatzPersonal1();
		double anteil2 = (getEinsatzanteilPersonal2_Prz() / 100.0) * getAnsatzPersonal2();
		double anteil3 = (getEinsatzanteilPersonal3_Prz() / 100.0) * getAnsatzPersonal3();
		double anteil4 = (getEinsatzanteilPersonal4_Prz() / 100.0) * getAnsatzPersonal4();
		double gesamtkostensatz = anteil1 + anteil2 + anteil3 + anteil4;

		txtAnteilPersonalKosten1_Prz.setValue( (int) (100 * anteil1 / gesamtkostensatz) );
		txtAnteilPersonalKosten2_Prz.setValue( (int) (100 * anteil2 / gesamtkostensatz) );
		txtAnteilPersonalKosten3_Prz.setValue( (int) (100 * anteil3 / gesamtkostensatz) );
		txtAnteilPersonalKosten4_Prz.setValue( (int) (100 * anteil4 / gesamtkostensatz) );
	}

	
	
	
	@Override
	public void updateWaehrungskuerzel(String newValue) {
		lblPersonal1B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProStd")); //$NON-NLS-1$
		lblPersonal2B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProStd")); //$NON-NLS-1$
		lblPersonal3B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProStd")); //$NON-NLS-1$
		lblPersonal4B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProStd")); //$NON-NLS-1$
		lblMaschine1B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProPMH15")); //$NON-NLS-1$
		if (isUnitMaschine2ISH) {
			lblMaschine2B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProISH")); //$NON-NLS-1$
		}
		else {
			lblMaschine2B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProPMH15")); //$NON-NLS-1$
		}
		lblMaschine3B.setText(newValue + GuiStrings.getString("KostensaetzePanel2014.ProPMH15")); //$NON-NLS-1$
	}
	
	
	public static class Builder {
		private final HeProMoWindow parent;
		private boolean showZusaetzlichePersonen;
		private boolean showPersonal1;
		private boolean showMaschine2;
		private boolean showMaschine3;
		private boolean isUnitMaschine2ISH;
		
		
		public Builder(HeProMoWindow parent) {
			this.parent = parent;
		}
		
		public Builder showZusaetzlichePersonen() {
			this.showZusaetzlichePersonen = true;
			return this;
		}
		
		public Builder showPersonal1() {
			this.showPersonal1 = true;
			return this;
		}
		
		public Builder showMaschine2() {
			this.showMaschine2 = true;
			return this;
		}
		
		public Builder showMaschine3() {
			this.showMaschine3 = true;
			return this;
		}
		
		public Builder setUnitMaschine2ToISH() {
			this.isUnitMaschine2ISH = true;
			return this;
		}
		
		public KostensaetzePanel2014 build() {
			return new KostensaetzePanel2014(this);
		}
	}
	
}
