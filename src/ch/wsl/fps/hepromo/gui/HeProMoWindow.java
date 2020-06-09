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
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.PersistentInputData;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.util.DatenblattCreator;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class HeProMoWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblArbeitsort;
	private JTextField txtArbeitsort;
	private JPanel pnlArbeitsObjekt = new JPanel();
	private JPanel pnlArbeitsSystem = new JPanel();
	private FaktorenPanel pnlFaktoren;
	
	protected AbstractErgebnisPanel ergebnisPanel;
	private JSpinner txtAnzahlNachkommastellen;
	private JButton btnDatenblatt;
	

	protected Faktoren faktoren = new Faktoren();
	
	private boolean reactOnInputChange = false;
	
	private final String defaultDir = isStartedFromJar() ? "." : "./data"; //muss für deployment "." sein, damit Standardordner für das Speichern der .hpm-Dateien wählbar ist ("Start in..")
	
	private static boolean isStartedFromJar() {
		return MainWindow.class.getResource("MainWindow.class").toString().startsWith("rsrc");
	}
	
	
	public HeProMoWindow() {
		//window properties
		this.setSize(550, 590);
//		this.setMinimumSize(new Dimension(0, 0));
		this.setLocationByPlatform(true);
//		this.setTitle("HeProMo");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//taskbar icon
		this.setIconImage(MainWindow.getWslLogo().getImage());	
	}

	
	//Methode nur für Testzwecke
//	public static void main(String[] args) {
//		
//		//load look & feel
//		try {
//			// Set System L&F
//			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
//			//UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel" );
//		} 
//		catch (UnsupportedLookAndFeelException e) {
//			// handle exception
//		}
//		catch (ClassNotFoundException e) {
//			// handle exception
//		}
//		catch (InstantiationException e) {
//			// handle exception
//		}
//		catch (IllegalAccessException e) {
//			// handle exception
//		}
//		
//		
//		//create window
//		new HeProMoWindow(){
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			protected void initPanelArbeitsobjekt(JPanel panel) {				
//			}
//
//			@Override
//			protected void initPanelArbeitssystem(JPanel panel) {
//			}
//
//			@Override
//			protected void initData() {
//			}
//
//			@Override
//			public void loadModelToGUI(HeProMoInputData data) {
//			}
//
//			@Override
//			public void loadGUIToModel() {
//			}
//
//			@Override
//			public AbstractModel getModel() {
//				return null;
//			}
//		};
//	}
	
	
	
	protected final void setReactOnInputChange(boolean flag) {
		this.reactOnInputChange = flag;
		if (flag == true) {
			displayErgebnis();
		}
	}
	
	protected final boolean isReactOnInputChange() {
		return reactOnInputChange;
	}
	
	
	// used for JSpinner
	private final ChangeListener defaultChangeListner = new ChangeListener(){
		@Override
		public void stateChanged(ChangeEvent e) {
//			System.out.println("spn value changed: " + ((JSpinner)e.getSource()).getValue());
			onInputChanged(e.getSource());
		}
	};
	
	//used for JComboBox
	private final ActionListener defaultActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object selectedItem = ((JComboBox<?>)e.getSource()).getSelectedItem();
			if (selectedItem != null) {
//				System.out.println("cmb value changed: " + selectedItem);
				onInputChanged(e.getSource());
			}
		}
	};
	
	//used for JCheckBox / JRadioButton
	private final ItemListener defaultItemListener = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
//			System.out.println( "chk value changed: " + ((JCheckBox)e.getSource()).isSelected() );
			onInputChanged(e.getSource());
		}
	};
	
	//used fo JTextField
	private final DocumentListener defaultDocumentListener = new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
//			try {
//				AbstractDocument doc = (AbstractDocument) e.getDocument();
//				System.out.println("txt value removeUpdate: " + doc.getText(0, doc.getLength()) );
//			} catch (BadLocationException e1) {
//				e1.printStackTrace();
//			}
			onInputChanged(e.getDocument());
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
//			try {
//				AbstractDocument doc = (AbstractDocument) e.getDocument();
//				System.out.println("txt value insertUpdate: " + doc.getText(0, doc.getLength()) );
//			} catch (BadLocationException e1) {
//				e1.printStackTrace();
//			}
			onInputChanged(e.getDocument());
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
//			try {
//				AbstractDocument doc = (AbstractDocument) e.getDocument();
//				System.out.println("txt value changedUpdate: " + doc.getText(0, doc.getLength()) );
//			} catch (BadLocationException e1) {
//				e1.printStackTrace();
//			}
			onInputChanged(e.getDocument());
		}
	};
	
	
	/**
	 * Methode wird aufgerufen bei geänderten Input-Feldern,
	 * noch bevor die Ergebnisse berechnet werden. Zweck der 
	 * Methode: Aktualisierung von gegenseitig abhängigen 
	 * Feldern vor Neuberechnungen. Defaultimplementierung
	 * ist leer. Änderungen im Modell während der Ausführung
	 * dieser Methode folgen nicht automatisch zu einer 
	 * Neuberechnung des Ergebnisses.
	 */
	protected void onInputChangedBeforeCalculation(Object eventSource) {
	}
	
	
	private void onInputChanged(Object eventSource) {
//		System.out.println("onInputChanged()");
		if (reactOnInputChange == true) {
			
			//Vorberechnungen
			reactOnInputChange = false;
			onInputChangedBeforeCalculation(eventSource);
			reactOnInputChange = true;
			
			//Kalkulation
			displayErgebnis();
		}
	}
	
	
	
	/**
	 * Initializes the panels, loads the model instance 
	 * to the gui, and finally makes this window visible.
	 * All operations are done in the event dispatcher thread.
	 * 
	 * This class must be called by the concrete subclass to 
	 * complete the initialization process.
	 * 
	 * This method is separated from the constructor since a 
	 * constructor should not call abstract methods.
	 */
	protected final void initalize() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {

					//init content
					pnlFaktoren = new FaktorenPanel(HeProMoWindow.this); //Listener müssen vor Initialisierung des FaktorenPanel initialisiert sein, das GUI danach!
					initContent();
					initPanelArbeitsobjekt(pnlArbeitsObjekt);
					initPanelArbeitssystem(pnlArbeitsSystem);
					initData();

					loadModelToGUI( getModel() );

					//show window
					HeProMoWindow.this.setVisible(true);
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	private final void initContent() {
		//remove all
		this.getContentPane().removeAll();
		

		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		//label Arbeitsort
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.weightx = 20;
		c.insets = new Insets(5,10,0,10);
		lblArbeitsort = new JLabel(GuiStrings.getString("HeProMoWindow.ArbeitsortHolzschlag")); //$NON-NLS-1$
		this.getContentPane().add(lblArbeitsort, c);
		
		//text Arbeitsort
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,10,0,0);
		txtArbeitsort = new JTextField() {
			private static final long serialVersionUID = 1L;
			private int limit = 80;
			
			@Override
			protected Document createDefaultModel() {
				return new PlainDocument(){
					private static final long serialVersionUID = 1L;
					
					@Override
					public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
						if (str == null) return;
						
						if ((getLength() + str.length()) <= limit) {
							super.insertString(offset, str, attr);
						}
						else {
							int maxLength = limit - getLength();
							super.insertString(offset, str.substring(0, maxLength), attr);
						}
					}
				};
			}
		};
		addDefaultDocumentListener(txtArbeitsort);
		this.getContentPane().add(txtArbeitsort, c);

		//info Arbeitsort
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(7,5,0,12);
		JLabel lblArbeitsort_Info = GuiStrings.getInfoButtonBlue(GuiStrings.getString("HeProMoWindow.InfoButtonArbeitsort")); //$NON-NLS-1$
		this.getContentPane().add(lblArbeitsort_Info, c);
		
		
		//panel input
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
		c.insets = new Insets(10,5,5,5);
		JPanel pnlInput = initPanelInput();
		this.getContentPane().add(pnlInput, c);
		
		//panel output
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
//		c.weighty = 100;
		c.insets = new Insets(15,5,5,5);
		ergebnisPanel = initErgebnisPanel();
		this.getContentPane().add(ergebnisPanel, c);
		
		//panel buttons
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		c.insets = new Insets(5,5,5,5);
		JPanel pnlButtons = initPanelButtons();
		this.getContentPane().add(pnlButtons, c);
	}
	
	
	
	
	private final JPanel initPanelInput() {
		JPanel pnlInput = new JPanel();
		pnlInput.setBorder( BorderFactory.createTitledBorder(GuiStrings.getString("HeProMoWindow.TitleEingaben"))); //$NON-NLS-1$
		
		//set title font to bold
		TitledBorder border = (TitledBorder) pnlInput.getBorder();
		Font titleFont = border.getTitleFont();
		if (titleFont == null) { //needed since java 7, see http://bugs.java.com/view_bug.do?bug_id=7022041
//			titleFont = UIManager.getDefaults().getFont("TitledBorder.font");
			titleFont = UIManager.getFont("TitledBorder.font"); //$NON-NLS-1$
		}
		border.setTitleFont( titleFont.deriveFont(Font.BOLD) );
		
		
		//set layout
		pnlInput.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//tabbed pane
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
//		c.anchor = GridBagConstraints.EAST;
//		c.insets = new Insets(5,5,5,5);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(GuiStrings.getString("HeProMoWindow.TabTitleArbeitsobjekt"), pnlArbeitsObjekt); //$NON-NLS-1$
		tabbedPane.addTab(GuiStrings.getString("HeProMoWindow.TabTitleArbeitssystem"), pnlArbeitsSystem); //$NON-NLS-1$
		tabbedPane.addTab(GuiStrings.getString("HeProMoWindow.TabTitleFaktoren"), pnlFaktoren); //$NON-NLS-1$
		pnlInput.add(tabbedPane, c);
		
		
		return pnlInput;
	}
	
	
	
	
	private final JPanel initPanelButtons() {
		JPanel pnlButtons = new JPanel();
		
		//set layout
		pnlButtons.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		//label anzahl nachkommastellen
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,0,0);
		JLabel lblAnzahlNachkommastellen = new JLabel(GuiStrings.getString("HeProMoWindow.Nachkommastellen")); //$NON-NLS-1$
		lblAnzahlNachkommastellen.setToolTipText(GuiStrings.getString("HeProMoWindow.TooltipNachkommastellen")); //$NON-NLS-1$
		pnlButtons.add(lblAnzahlNachkommastellen, c);
		
		//spinner anzahl nachkommastellen
        c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(5,2,0,5);
		txtAnzahlNachkommastellen = new JSpinner(new SpinnerNumberModel(2, 0, 5, 1));
		txtAnzahlNachkommastellen.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				ergebnisPanel.setDecimalFormat( createDecimalFormat() );
				displayErgebnis();
			}
		});
		pnlButtons.add(txtAnzahlNachkommastellen, c);
		
		//placeholder
        c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 100;
		JPanel pnlPlaceholder = new JPanel();
		pnlButtons.add(pnlPlaceholder, c);
		
		
		//button dokumentation
		if (DocumentationBroker.exists(getModel())) {
			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = 0;
			c.insets = new Insets(5,0,0,0);
			final JButton btnDokumentation = new JButton(GuiStrings.getString("HeProMoWindow.btnGrundlagen")); //$NON-NLS-1$
			btnDokumentation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DocumentationBroker.showDocumentation(getModel(), HeProMoWindow.this, btnDokumentation);
				}			
			});
			pnlButtons.add(btnDokumentation, c);
		}
		
		
		//button datenblatt
        c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		btnDatenblatt = new JButton(GuiStrings.getString("HeProMoWindow.btnDatenblatt")); //$NON-NLS-1$
		btnDatenblatt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				DatenblattCreator.preInitializeInSeparateThread();
				
				//get filename
				final File pdfFile = getDatenblattFile();
				if (pdfFile == null) {
					return;
				}
				
				btnDatenblatt.setEnabled(false);
				btnDatenblatt.setText(GuiStrings.getString("HeProMoWindow.btnBitteWarten")); //$NON-NLS-1$
				
				//update model
				loadGUIToModel();
				
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
					@Override
					protected Void doInBackground() throws Exception {
						//create Datenblatt
						createAndOpenDatenblattPdf(pdfFile);
						return null;
					}
					@Override
					protected void done() {
						btnDatenblatt.setEnabled(true);	
						btnDatenblatt.setText(GuiStrings.getString("HeProMoWindow.btnDatenblatt"));;	 //$NON-NLS-1$
					}
				};
				worker.execute();
			}			
		});
		pnlButtons.add(btnDatenblatt, c);
		
		
		//button laden
        c = new GridBagConstraints();
		c.gridx = 5;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		JButton btnLoad = new JButton(GuiStrings.getString("HeProMoWindow.btnLaden")); //$NON-NLS-1$
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}			
		});
		pnlButtons.add(btnLoad, c);
		
		
		//button speichern
        c = new GridBagConstraints();
		c.gridx = 6;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,0);
		JButton btnSave = new JButton(GuiStrings.getString("HeProMoWindow.btnSpeichern")); //$NON-NLS-1$
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}			
		});
		pnlButtons.add(btnSave, c);
		
		
		//button calculate
//        c = new GridBagConstraints();
//		c.gridx = 6;
//		c.gridy = 0;
//		c.insets = new Insets(5,0,0,0);
//		JButton btnCalculate = new JButton("Berechnen");
//		btnCalculate.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				displayErgebnis();
//			}			
//		});
//		pnlButtons.add(btnCalculate, c);
		
		//button close
        c = new GridBagConstraints();
		c.gridx = 7;
		c.gridy = 0;
		c.insets = new Insets(5,0,0,5);
		JButton btnClose = new JButton(GuiStrings.getString("HeProMoWindow.btnBeenden")); //$NON-NLS-1$
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HeProMoWindow.this.dispose();
			}			
		});
		pnlButtons.add(btnClose, c);
		
		
		return pnlButtons;
	}
	
	
	
	private DecimalFormat createDecimalFormat() {
		int anzahlNachkommastellen = (Integer) txtAnzahlNachkommastellen.getValue();
		StringBuilder sb = new StringBuilder(",##0"); //$NON-NLS-1$
		
		if (anzahlNachkommastellen > 0) {
			sb.append("."); //$NON-NLS-1$
		}
		
		for (int i=0; i<anzahlNachkommastellen; i++) {
			sb.append("0"); //$NON-NLS-1$
		}
		
		DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
		
		return decimalFormat;
	}
	
	
	
	protected abstract void initPanelArbeitsobjekt(JPanel panel);
	
	protected abstract void initPanelArbeitssystem(JPanel panel);
	
	protected AbstractErgebnisPanel initErgebnisPanel() {
		return new ErgebnisPanel();
	}
	
	protected abstract void initData();
	
	
	
	private File getDatenblattFile() {
		
		//open file chooser
		String defaultfileName = GuiStrings.getString("HeProMoWindow.DefaultDatenblattFilename"); //$NON-NLS-1$
		JFileChooser fileChooser = new JFileChooser(defaultDir){
			private static final long serialVersionUID = 1L;
			@Override
		    public void approveSelection(){
				
				//check file extension
				File file = getSelectedFile();
				if ( !file.getName().toLowerCase().endsWith(".pdf") ) { //$NON-NLS-1$
					file = new File(file.getPath() + ".pdf"); //$NON-NLS-1$
					setSelectedFile(file);
				}
				
				//check if already exists
		        if(file.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this, GuiStrings.getString("HeProMoWindow.WarnungDateiUeberschreiben"), GuiStrings.getString("HeProMoWindow.DialogTitleSpeichern"), JOptionPane.YES_NO_CANCEL_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                case JOptionPane.NO_OPTION:
		                    return;
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
		    }        
		};
		fileChooser.setSelectedFile(new File(defaultfileName));
		
		
		//check if 'save' pressed
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		
		return fileChooser.getSelectedFile();
	}
	
	
	
	private void createAndOpenDatenblattPdf(File pdfFile) {
		//create pdf
		DatenblattCreator pdfCreator = new DatenblattCreator();
		pdfCreator.create(getModel(), pdfFile, createDecimalFormat());
		
		
		//try to open file
		try {
			Desktop.getDesktop().open(pdfFile);
		} catch (IOException e) {
			HeProMoExceptionHandler.handle(e);
		}
	}
	
	
	
	/**
	 * Speichert die Inputdaten des aktuellen Modells in eine Datei
	 */
	private void save() {
		
		//open file chooser
		String defaultfileName = "data.hpm"; //$NON-NLS-1$
		JFileChooser fileChooser = new JFileChooser(defaultDir){
			private static final long serialVersionUID = 1L;
			@Override
		    public void approveSelection(){
				
				//check file extension
				File file = getSelectedFile();
				if ( !file.getName().toLowerCase().endsWith(".hpm") ) { //$NON-NLS-1$
					file = new File(file.getPath() + ".hpm"); //$NON-NLS-1$
					setSelectedFile(file);
				}
				
				//check if already exists
		        if(file.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this, GuiStrings.getString("HeProMoWindow.WarnungDateiUeberschreiben"), GuiStrings.getString("HeProMoWindow.DialogTitleSpeichern"), JOptionPane.YES_NO_CANCEL_OPTION); //$NON-NLS-1$ //$NON-NLS-2$
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                case JOptionPane.NO_OPTION:
		                    return;
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
		    }        
		};
		fileChooser.setSelectedFile(new File(defaultfileName));
		
		
		//check if 'save' pressed
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		

		//create data instance to persist
		loadGUIToModel();
		PersistentInputData data = new PersistentInputData(getModel());
		
		//write file
		try{
			FileOutputStream fout = new FileOutputStream(fileChooser.getSelectedFile());
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(data);
			oos.close();
			System.out.println(GuiStrings.getString("HeProMoWindow.BestaetigungDatenGespeichert")); //$NON-NLS-1$

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Lädt die Inputdaten für das aktuelle Modell aus einer Datei
	 */
	private void load() {
		
		//open file chooser
		String defaultfileName = "data.hpm"; //$NON-NLS-1$
		JFileChooser fileChooser = new JFileChooser(defaultDir);
		fileChooser.setSelectedFile(new File(defaultfileName));
		
		//check if 'open' pressed
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		
		//read file
		File file = fileChooser.getSelectedFile();
		PersistentInputData data = null;
		try{
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			data = (PersistentInputData) ois.readObject();
			ois.close();
			
		}
		catch (InvalidClassException e) {
			//Dieser Fehler tritt auch dann auf, wenn z.B. in Radharvester2014 eine Datei geöffnet wird,
			// welche mit Schlepper2014 in einer früheren Version gespeichert wurde. D.h. zuerst wird
			// die serialVersionUID der zu öffnenden Datei geprüft, und erst anschliessend, ob es überhaupt
			// die gewünschte Klasse ist.
			JOptionPane.showMessageDialog(this, GuiStrings.getString("HeProMoWindow.FehlerBeimLesenDerDateiFalscheVersion"), GuiStrings.getString("HeProMoWindow.DialogTitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		}
        catch(FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getLocalizedMessage(), GuiStrings.getString("HeProMoWindow.DialogTitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
            return;
        }
		catch (Exception e) { //Catcht auch, falls eingelesene Datei keine PersistentInputData-Instanz
//			e.printStackTrace();
			JOptionPane.showMessageDialog(this, GuiStrings.getString("HeProMoWindow.FehlerBeimLesenDerDatei"), GuiStrings.getString("HeProMoWindow.DialogTitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			return;
		} 
		
		//load data
		Class<? extends AbstractModel> loadedModelClass = data.getModelClass();
		if (loadedModelClass != getModel().getClass()) {
			JOptionPane.showMessageDialog(this, 
					GuiStrings.getString("HeProMoWindow.FehlerDateiMitDatenAnderemModellZeile1") + //$NON-NLS-1$
					GuiStrings.getString("HeProMoWindow.FehlerDateiMitDatenAnderemModellZeile2") + //$NON-NLS-1$
					loadedModelClass.getSimpleName(),
					GuiStrings.getString("HeProMoWindow.DialogTitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			return;
		}
		loadModelToGUI(data);
		onSuccessfullyLoaded();
		System.out.println(GuiStrings.getString("HeProMoWindow.BestaetigungDatenGeladen")); //$NON-NLS-1$
	}
	
	
	protected void onSuccessfullyLoaded() {
		//can be overridden in subclasses
	}
	
	
	
	/**
	 * JSpinner wird so angepasst dass bei ungültigen Eingaben gewarnt wird (farblich und mit Tooltip).
	 * Zusätzlich wird im Formatter der übergebenen JSpinner-Instanz die Methode setCommitsOnValidEdit(true) aufgerufen.
	 *  
	 * Achtung: nach Aufruf von JSpinner.setModel() muss diese Methode erneut aufgerufen werden.
	 */
	public static final void adjustJSpinnerFormatter(final JSpinner spinner, final boolean isPercentageField) {
		//Formatter-Methoden aufrufen
		final JFormattedTextField textField = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
		DefaultFormatter formatter = (DefaultFormatter) textField.getFormatter();
		formatter.setCommitsOnValidEdit(true);
//		formatter.setAllowsInvalid(false);
		
		//User-Warnung bei ungültigem Eingabewert
		textField.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("editValid".equals(evt.getPropertyName())) { //$NON-NLS-1$
					if (Boolean.FALSE.equals(evt.getNewValue())) {
						DecimalFormat df = isPercentageField ? new DecimalFormat("#.##%") :  new DecimalFormat("#.##") ; //$NON-NLS-1$ //$NON-NLS-2$
						SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();  
						textField.setBackground(Color.YELLOW);
						textField.setToolTipText(GuiStrings.getString("HeProMoWindow.ToolTipUngueltigeEingabeA") + df.format(model.getMinimum()) + GuiStrings.getString("HeProMoWindow.ToolTipUngueltigeEingabeB") + df.format(model.getMaximum()) + GuiStrings.getString("HeProMoWindow.ToolTipUngueltigeEingabeC")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
					else{
						textField.setBackground(Color.WHITE);
						textField.setToolTipText(null);
					}
				}
			}
		});
	}
	
	
	/**
	 * Registriert den defaultChangeListener in der übergebenen
	 *  JSpinner-Instanz, falls dieser dort noch nicht registriert ist.
	 *  Zusätzlich wird im Formatter der übergebenen JSpinner-Instanz die  
	 *  Methode setCommitsOnValidEdit(true) aufgerufen.
	 *  
	 * Achtung: nach Aufruf von JSpinner.setModel() muss diese Methode 
	 *  erneut aufgerufen werden.
	 * 
	 * @param spinner
	 */
	public void addDefaultChangeListenerAndAdjustJSpinnerFormatter(final JSpinner spinner, final boolean isPercentageField) {
		adjustJSpinnerFormatter(spinner, isPercentageField);
		
		
		//Adds the default change listener to the given spinner.
		// It is tried to remove the default change listener prior
		// to adding it. This ensures that if this method is called 
		// multiple times, there will still be only one instance of
		// the default listener registred in the spinner.
		spinner.removeChangeListener(defaultChangeListner);
		spinner.addChangeListener(defaultChangeListner);
	}
	
	
	/**
	 * Registriert den defaultChangeListener in der übergebenen
	 *  JSpinner-Instanz, falls dieser dort noch nicht registriert ist.
	 *  Zusätzlich wird im Formatter der übergebenen JSpinner-Instanz die  
	 *  Methode setCommitsOnValidEdit(true) aufgerufen.
	 *  
	 * Achtung: nach Aufruf von JSpinner.setModel() muss diese Methode 
	 *  erneut aufgerufen werden.
	 * 
	 * @param spinner
	 */
	public void addDefaultChangeListenerAndAdjustJSpinnerFormatter(final JSpinner spinner) {
		addDefaultChangeListenerAndAdjustJSpinnerFormatter(spinner, false);
	}
	
	
	/**
	 * Registriert den defaultActionListener in der übergebenen
	 *  JComboBox-Instanz, falls dieser dort noch nicht registriert ist.
	 * 
	 * @param combobox
	 */
	public void addDefaultActionListener(JComboBox<?> combobox) {
		combobox.removeActionListener(defaultActionListener);
		combobox.addActionListener(defaultActionListener);
	}
	
	
	/**
	 * Registriert den defaultItemListener in der übergebenen
	 *  JCheckBox-Instanz, falls dieser dort noch nicht registriert ist.
	 * 
	 * @param checkbox
	 */
	public void addDefaultItemListener(JCheckBox checkbox) {
		checkbox.removeItemListener(defaultItemListener);
		checkbox.addItemListener(defaultItemListener);
	}
	
	
	/**
	 * Registriert den defaultItemListener in der übergebenen
	 *  JRadioButton-Instanz, falls dieser dort noch nicht registriert ist.
	 * 
	 * @param radiobutton
	 */
	public void addDefaultItemListener(JRadioButton radiobutton) {
		radiobutton.removeItemListener(defaultItemListener);
		radiobutton.addItemListener(defaultItemListener);
	}
	
	
	/**
	 * Registriert den defaultDocumentListener in der übergebenen
	 *  JTextField-Instanz, falls dieser dort noch nicht registriert ist.
	 * 
	 * @param textfield
	 */
	public void addDefaultDocumentListener(JTextField textfield) {
		textfield.getDocument().removeDocumentListener(defaultDocumentListener);
		textfield.getDocument().addDocumentListener(defaultDocumentListener);
	}
	
	
	
	
	/**
	 * Aktualisiert die zugehörige Modell-Instanz mit den Werten aus dem GUI
	 */
	public void loadGUIToModel() {
		
		getModel().setArbeitsort(txtArbeitsort.getText());
		
		//Faktoren
		pnlFaktoren.loadGUIToModel(getModel());
	}
	
	
	/**
	 * Gibt die zugehörige Modell-Instanz zurück. Um sicher zu gehen, dass die
	 *  Modell-Instanz die richtigen Werte enthält, sollte vorher die Methode
	 *  loadGUIToModel() aufgerufen werden.
	 */
	public abstract AbstractModel getModel();
	
	
	/**
	 * Berechnet das Ergebnis aus den Eingabewerten und zeigt es an
	 */
	protected void displayErgebnis() {
//		System.out.println("displayErgebnis()");
		
		//Modell-Instanz aktualisieren
		loadGUIToModel();
		
		//Read-Only Faktoren anzeigen
		pnlFaktoren.updateFaktorenPanel(getModel());
		
		//Währungskürzel aktualisieren
		updateWaehrungskuerzel(getModel().getFaktoren().getWaehrungskuerzel());
		
		//Ergebnis berechnen und anzeigen
		Ergebnis ergebnis = getModel().getErgebnis();
		this.ergebnisPanel.setErgebnis(ergebnis);
		this.validate();
	}
	
	
	/**
	 * Aktualisiert das Währungskürzel in allen GUI's.
	 * 
	 * Wird diese Methode in Subklassen überschrieben, muss immer 
	 * zuerst die Methode in der Superklasse aufgerufen werden, 
	 * damit alle notwendigen Felder aktualisiert werden können.
	 */
	protected void updateWaehrungskuerzel(String newValue) {
		
		//Währung in ErgebnisPanel aktualisieren
		ergebnisPanel.updateWaehrung(newValue);
	}
	
	
	
	/**
	 * Lädt die übergebenen Daten in die Eingabefelder des GUI
	 * @param data
	 */
	public abstract void loadModelToGUI(HeProMoInputData data);
	
	

	
	protected final void loadFaktoren(HeProMoInputData data) {
		txtArbeitsort.setText(data.getArbeitsort());
		pnlFaktoren.loadFaktoren(data);
	}
	
	
	protected FaktorenPanel getFaktorenPanel() {
		return pnlFaktoren;
	}
	
	
	public static String wrap(String text, final int charsPerLine) {
		if (text.startsWith("<html>") || text.length() < charsPerLine) {
			return text;
		}
		else {
			final String BREAK = "<br>";
			int startPos = 0;
			int latestCutPosition = startPos + charsPerLine;
			
			//handle possible extra breaks
			while (text.lastIndexOf(BREAK, latestCutPosition) > startPos) {
				startPos = text.lastIndexOf(BREAK, latestCutPosition) + BREAK.length();
				latestCutPosition = startPos + charsPerLine;
			}
			
			while (latestCutPosition < text.length()) {
				int indexOfLastSpace = text.lastIndexOf(" ", latestCutPosition);
				if (indexOfLastSpace == -1) {
					text = text.substring(0, latestCutPosition) + "-" + BREAK + text.substring(latestCutPosition);
					startPos = latestCutPosition + 1 + BREAK.length();
					latestCutPosition = startPos + charsPerLine;
				}
				else {
					text = text.substring(0, indexOfLastSpace) + BREAK + text.substring(indexOfLastSpace + 1);
					startPos = indexOfLastSpace + BREAK.length();
					latestCutPosition = startPos + charsPerLine;
				}
				
				//handle possible extra breaks
				while (text.lastIndexOf(BREAK, latestCutPosition) > startPos) {
					startPos = text.lastIndexOf(BREAK, latestCutPosition) + BREAK.length();
					latestCutPosition = startPos + charsPerLine;
				}
			}
			text = "<html>" + text + "</html>";
			return text;
		}
	}
	
}
