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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import ch.wsl.fps.hepromo.gui.modelle.Biomasseschaetzer2018;
import ch.wsl.fps.hepromo.gui.modelle.BiomasseschaetzerAbTarif2018;
import ch.wsl.fps.hepromo.gui.modelle.BiomasseschaetzerEnergie2018;
import ch.wsl.fps.hepromo.gui.modelle.FaellenMotormanuell;
import ch.wsl.fps.hepromo.gui.modelle.Forwarder;
import ch.wsl.fps.hepromo.gui.modelle.ForwarderEnergieholz2018;
import ch.wsl.fps.hepromo.gui.modelle.ForwarderRundholz2018;
import ch.wsl.fps.hepromo.gui.modelle.Hacker2018;
import ch.wsl.fps.hepromo.gui.modelle.HackschnitzelTransport2018;
import ch.wsl.fps.hepromo.gui.modelle.HelikopterGesamt;
import ch.wsl.fps.hepromo.gui.modelle.Kombiseilgeraet2018;
import ch.wsl.fps.hepromo.gui.modelle.KonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.MobilseilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.MotormanuellGesamt;
import ch.wsl.fps.hepromo.gui.modelle.MotormanuellGesamt2014;
import ch.wsl.fps.hepromo.gui.modelle.Radharvester2014;
import ch.wsl.fps.hepromo.gui.modelle.Schlepper;
import ch.wsl.fps.hepromo.gui.modelle.Schlepper2014;
import ch.wsl.fps.hepromo.gui.modelle.Vorruecken2018;
import ch.wsl.fps.hepromo.model.ModelStrings;


/**
 * 
 * @author Stefan Holm
 *
 */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTree tree = initJTree();
	private JLabel lblWait;
	private static final String betaSuffix = " <font size=6 color=red>BETA</font>"; //$NON-NLS-1$
	private static final boolean isBeta = false;
	private static final String hepromoVersion = "2.6" + (isBeta ? betaSuffix : ""); //$NON-NLS-1$ //$NON-NLS-2$
	private static final int hepromoMonth = Calendar.AUGUST;
	private static boolean LOG_TO_FILE = false;
	private static final int hepromoYear = 2024;
	private static Locale locale = new Locale("de"); //$NON-NLS-1$
	
	public static float SIZE = 1;
	public static float WIDTH_FACTOR = 1;
	
	public static void main(String[] args) {
		//parse settings args		
		for (int i=0; i<args.length; i++) {
			String arg = args[i];			
			System.out.println("arg" + i + ": " + arg); //$NON-NLS-1$ //$NON-NLS-2$

			if (arg.equalsIgnoreCase("-logtofile") || arg.equalsIgnoreCase("logtofile") ) { //$NON-NLS-1$ //$NON-NLS-2$
				LOG_TO_FILE = true;
			}
			else if (arg.equalsIgnoreCase("-logdialog") || arg.equalsIgnoreCase("logdialog") ) { //$NON-NLS-1$ //$NON-NLS-2$
				HeProMoExceptionHandler.setLogDialog(true);
			}
			else if (arg.equalsIgnoreCase("-stacktrace") || arg.equalsIgnoreCase("stacktrace") ) { //$NON-NLS-1$ //$NON-NLS-2$
				HeProMoExceptionHandler.setLogStackTrace(true);
			}
		}
		
		
		if (LOG_TO_FILE) {
			try {
				OutputStream os = new FileOutputStream("hepromo.log", true);
				PrintStream ps = new PrintStream(os);
				System.setOut(ps);
				System.setErr(ps);
			} catch (FileNotFoundException e) {
				HeProMoExceptionHandler.handle(e);
			}
		}
		
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {	
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				HeProMoExceptionHandler.handle(e, "Uncaught Exception in Thread " + t + "\n"); //$NON-NLS-1$ $NON-NLS-2$
			}
		});
		
		System.out.println("Default Locale: " + Locale.getDefault()); //$NON-NLS-1$
		if (Locale.getDefault().getLanguage().equals(Locale.FRENCH.getLanguage())) {
			locale = new Locale("fr"); //$NON-NLS-1$
		}
		if (Locale.getDefault().getLanguage().equals(Locale.ITALIAN.getLanguage())) {
			locale = new Locale("it"); //$NON-NLS-1$
		}
		
		//parse lang args		
		for (int i=0; i<args.length; i++) {
			String arg = args[i];			
			System.out.println("arg" + i + ": " + arg); //$NON-NLS-1$ //$NON-NLS-2$

			if (arg.equalsIgnoreCase("-fr") || arg.equalsIgnoreCase("fr") ) { //$NON-NLS-1$ //$NON-NLS-2$
				locale = new Locale("fr"); //$NON-NLS-1$
			}
			else if (arg.equalsIgnoreCase("-de") || arg.equalsIgnoreCase("de") ) { //$NON-NLS-1$ //$NON-NLS-2$
				locale = new Locale("de"); //$NON-NLS-1$
			}
			else if (arg.equalsIgnoreCase("-en") || arg.equalsIgnoreCase("en") ) { //$NON-NLS-1$ //$NON-NLS-2$
				locale = new Locale("en"); //$NON-NLS-1$
			}
			else if (arg.equalsIgnoreCase("-it") || arg.equalsIgnoreCase("it") ) { //$NON-NLS-1$ //$NON-NLS-2$
				locale = new Locale("it"); //$NON-NLS-1$
			}
		}
		
		

		//create gui
		SwingUtilities.invokeLater(() -> {
			//load look & feel
			try {
				// Set System L&F
				UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

				//adapt GUIs based on default font
				Font defaultFont = new JLabel().getFont();	
				SIZE = (float)defaultFont.getSize() / 11; //Für Schriftgrösse 11 wurde die Applikation ursprünglich entwickelt.
				SIZE = Math.max(1, SIZE);

				//work-around for windows 10  -> funktioniert, macht aber GUI sehr klein
				//					if (defaultFont.getFontName().startsWith("Tahoma")) {
				//						if (defaultFont.getSize() != 11) {
				//							setDefaultFont(new FontUIResource("Tahoma", Font.PLAIN, 11));
				//						}
				//					}
				if (System.getProperty("os.name") != null && System.getProperty("os.name").indexOf("Mac") >= 0) {
					WIDTH_FACTOR = 1.15f;
				}
			} 
			catch (UnsupportedLookAndFeelException 
					| ClassNotFoundException
					| InstantiationException
					| IllegalAccessException e) {
				HeProMoExceptionHandler.handle(e);
			}

			//tooltip settings
			ToolTipManager.sharedInstance().setInitialDelay(0);
			ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);


			//create main window
			setAllLocales(locale);
			new MainWindow();
		});
	}

	
	@SuppressWarnings("unused")
	private static void setDefaultFont(FontUIResource font){
		for(Object key : Collections.list(UIManager.getDefaults().keys())) {
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, font);
			}
		}
	}
	
	
	public MainWindow() {
		//window properties
		this.setSize((int) (420 * Math.pow(MainWindow.SIZE, 0.6) * WIDTH_FACTOR), (int) (580 * Math.pow(MainWindow.SIZE, 0.6)));
		this.setMinimumSize(new Dimension(350, 300));
		this.setLocationByPlatform(true);
		this.setTitle("HeProMo"); //$NON-NLS-1$
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//menu
		createMenu();

		//content
		initContent();
		
		//taskbar icon
		this.setIconImage(getWslLogo().getImage());

		//show window
		this.setVisible(true);
	}

	
	private void createMenu() {
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		this.setJMenuBar(menuBar);
		
		//Top-level entries
		JMenu menuFile = new JMenu(GuiStrings.getString("MainWindow.menuDatei")); //$NON-NLS-1$
		menuBar.add(menuFile);
		
		JMenu menuLanguage = new JMenu(GuiStrings.getString("MainWindow.menuSprache")); //$NON-NLS-1$
		menuBar.add(menuLanguage);
		
//		JMenu menuTools = new JMenu("Tools");
//		menuBar.add(menuTools);
		
		JMenu menuKontakt = new JMenu(GuiStrings.getString("MainWindow.menuKontakt")); //$NON-NLS-1$
		menuBar.add(menuKontakt);
		
		JMenu menuHelp = new JMenu(GuiStrings.getString("MainWindow.menuInfo")); //$NON-NLS-1$
		menuBar.add(menuHelp);
		
		
		
		//separator
		menuFile.addSeparator();
		
		//menu action: close
		Action aMenuClose = new AbstractAction(GuiStrings.getString("MainWindow.menuBeenden")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}			
		};
		menuFile.add(aMenuClose);
		
		
		
		

		//menu action: languages
		ButtonGroup btnGroupLang = new ButtonGroup();
		ArrayList<Locale> listLanguages = new ArrayList<>();
		listLanguages.add(new Locale("de")); //$NON-NLS-1$
		listLanguages.add(new Locale("fr")); //$NON-NLS-1$
		listLanguages.add(new Locale("it")); //$NON-NLS-1$
		listLanguages.add(new Locale("en")); //$NON-NLS-1$
		for (final Locale lang : listLanguages) {
			//create action
			Action aMenuLanguageX = new AbstractAction(lang.toString()) {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeLanguage(lang);
				}
			};
			//create button
			JRadioButtonMenuItem menuLanguageX = new JRadioButtonMenuItem();
			menuLanguageX.setAction(aMenuLanguageX);
			menuLanguageX.setSelected( lang.equals(MainWindow.locale) );
			menuLanguageX.setText( lang.getDisplayLanguage(MainWindow.locale) );
			menuLanguageX.setName(lang.getLanguage());
			
			//add to menu and group
			menuLanguage.add(menuLanguageX);
			btnGroupLang.add(menuLanguageX);
		}
		
		
		
		
		
		//menu action: kontakt
		Action aMenuKontakt = new AbstractAction(GuiStrings.getString("MainWindow.menuKontakt")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				sb.append("<html>"); //$NON-NLS-1$
				sb.append(GuiStrings.getString("MainWindow.InfoDialog.EidgForschungsanstaltWSL") + "<br>"); //$NON-NLS-1$
				sb.append("Zürcherstrasse 111<br>"); //$NON-NLS-1$
				sb.append("CH-8903 Birmensdorf<br>"); //$NON-NLS-1$
				sb.append("<br>"); //$NON-NLS-1$
				sb.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleKontakt") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append("Janine Schweier / janine.schweier@wsl.ch / +41 44 739 24 78<br>"); //$NON-NLS-1$
				sb.append("<br>"); //$NON-NLS-1$
				sb.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleProgrammierung") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb.append("\tStefan Holm / stefan.holm@wsl.ch / +41 44 739 22 63<br>"); //$NON-NLS-1$
				
				if (locale.equals( new Locale("fr") )) { //$NON-NLS-1$
					sb.append("<br>"); //$NON-NLS-1$
					sb.append("<b>" + "Traduction:" + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					sb.append("\tFrançois Fahrni / fahrni@bzwlyss.ch / +41 32 387 49 21<br>"); //$NON-NLS-1$
				}
				
				if (locale.equals( new Locale("it") )) { //$NON-NLS-1$
					sb.append("<br>"); //$NON-NLS-1$
					sb.append("<b>" + "Traduzione:" + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					sb.append("\tFulvio Giudici / fulvio.guidici@gmail.com<br>"); //$NON-NLS-1$
				}
				
				if (locale.equals( new Locale("en") )) { //$NON-NLS-1$
					sb.append("<br>"); //$NON-NLS-1$
					sb.append("<b>" + "Translation:" + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					sb.append("\tFritz Frutig<br>"); //$NON-NLS-1$
				}
				
				sb.append("</html>"); //$NON-NLS-1$
				
				JOptionPane.showMessageDialog(MainWindow.this, sb.toString(), GuiStrings.getString("MainWindow.menuKontakt"), JOptionPane.NO_OPTION); //$NON-NLS-1$
			}
		};
		menuKontakt.add(aMenuKontakt);
		
		
		
		

		//menu action: info
		Action aMenuInfo = new AbstractAction(GuiStrings.getString("MainWindow.menuInfo")) { //$NON-NLS-1$
			@Override
			public void actionPerformed(ActionEvent e) {
				int fontSize = SIZE > 1.3 ? 6 : 5;
				StringBuilder sb1 = new StringBuilder();
				sb1.append("<html>"); //$NON-NLS-1$
				sb1.append("<b><font size=" + fontSize + " color=#006666>HeProMo v" + hepromoVersion + " / " + getHeProMoDate() + "</font></b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleBereitgestelltDurch") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append(GuiStrings.getString("MainWindow.InfoDialog.EidgForschungsanstaltWSL") + "<br>"); //$NON-NLS-1$
				sb1.append("Zürcherstrasse 111<br>"); //$NON-NLS-1$
				sb1.append("CH-8903 Birmensdorf<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleNeueModelle2024") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("Marc Werder und Janine Schweier<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleNeueModelle2014") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("D. Pedolin, F. Frutig, R. Lemm, O. Thees<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleAlteModelle") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("V. Erni, R. Lemm, F. Frutig, M. Breitenstein, D. Riechsteiner, K. Oswald, O. Thees<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleProgrammierung") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("\tStefan Holm<br>"); //$NON-NLS-1$
				sb1.append("<br>"); //$NON-NLS-1$
				sb1.append("<b>" + GuiStrings.getString("MainWindow.InfoDialog.TitleZitierung") + "</b><br>"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				sb1.append("\t- " + GuiStrings.getString("MainWindow.InfoDialog.EidgForschungsanstaltWSL") + ", " + hepromoYear + ": " + GuiStrings.getString("MainWindow.InfoDialog.HolzernteProduktivitaetsmodelle") + " HeProMo, " + GuiStrings.getString("MainWindow.Version") + " " + hepromoVersion + ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
				sb1.append("</html>"); //$NON-NLS-1$
				JLabel label1 = new JLabel(sb1.toString());


				StringBuilder sb2 = new StringBuilder();
				sb2.append("<html>\t- <u style=\"color:blue;\">Holm, S., Frutig, F., Lemm, R., Thees, O., & Schweier, J. (2020). HeProMo: A decision</u><br>&nbsp;&nbsp;<u style=\"color:blue;\">support tool to estimate wood harvesting productivities. PLoS One, 15(12), e0244289.</u><br><br>"); //$NON-NLS-1$ //$NON-NLS-2$
				sb2.append("</html>"); //$NON-NLS-1$
				JLabel label2 = new JLabel(sb2.toString());
				label2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				label2.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				            try {
				            	URI uri = new URI("https://doi.org/10.1371/journal.pone.0244289");
				                desktop.browse(uri);
				            } catch (Exception e1) {
				                e1.printStackTrace();
				            }
				        }
				    }
				});

				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.add(label1);
				panel.add(label2);
				
				JOptionPane.showMessageDialog(MainWindow.this, panel, GuiStrings.getString("MainWindow.menuInfo"), JOptionPane.NO_OPTION); //$NON-NLS-1$
			}			
		};
		menuHelp.add(aMenuInfo);	
	}
	
	
	
	
	private void initContent() {

		//remove all
		this.getContentPane().removeAll();
		

		//set layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints c;
		
		
		//WSL label
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,10,10,10);
//		c.weightx = 100;
//		c.weighty = 100;
		ImageIcon wslLogo = getWslLogo();
		int fontSize = SIZE > 1.3 ? 6 : 5;
		String infoText = "<html><font size=" + fontSize + " color=#006666><b>" //$NON-NLS-1$ //$NON-NLS-2$
				+ GuiStrings.getString("MainWindow.InfoDialog.HolzernteProduktivitaetsmodelle") + "<font size=0><br><br></font>"  //$NON-NLS-1$//$NON-NLS-2$
				+ GuiStrings.getString("MainWindow.Version") + " " + hepromoVersion  + " / " + getHeProMoDate()  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
				+ "</b></font></html>"; //$NON-NLS-1$
		JLabel lblWslInfo = new JLabel(infoText, wslLogo, SwingConstants.LEFT);
		lblWslInfo.setIconTextGap(12);
		this.getContentPane().add( lblWslInfo , c);
		
		
		//JTree && waitLabel
        c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
//		c.gridheight = 99;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 100;
		c.weighty = 100;
		JScrollPane treeView = new JScrollPane(tree);
		tree.setBorder(new EmptyBorder(5,5,5,5));
	
        lblWait = new JLabel(getWaitImage());
        lblWait.setAlignmentX(0.5f);
        lblWait.setAlignmentY(0.5f);
		lblWait.setVisible(false);
        
        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new OverlayLayout(overlayPanel));
        overlayPanel.add(lblWait);
        overlayPanel.add(treeView);
        
		this.getContentPane().add( overlayPanel , c);
	}
	
	
	
	private JTree initJTree() {
		final JTree tree = new JTree(getRootNode());
		tree.setRootVisible(false);
		tree.setShowsRootHandles(true);
		tree.expandRow(2);
		tree.expandRow(0);
		
		//correct text and selection colors
		tree.setCellRenderer(new DefaultTreeCellRenderer(){
			private static final long serialVersionUID = 1L;
			private final JLabel infoButtonBlue = GuiStrings.getInfoButtonBlue("");
			private final JLabel infoButtonGrey = GuiStrings.getInfoButtonGrey("");
			private final JPanel panel = new JPanel(new BorderLayout());
			
			{
        		panel.setOpaque(false);
				panel.add(this, BorderLayout.WEST);
				
				this.setBackgroundSelectionColor(null);
				this.setTextSelectionColor(null);
				this.setBorderSelectionColor(Color.LIGHT_GRAY);
			}
			
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
				JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
				label.setFont(tree.getFont());
				panel.removeAll();
				panel.add(this, BorderLayout.WEST);
				if (value instanceof InfoTreeNode) {
					if (lblWait != null && lblWait.isVisible() == true) {
						panel.add(infoButtonGrey, BorderLayout.EAST);
					}
					else {
						panel.add(infoButtonBlue, BorderLayout.EAST);
					}
					panel.setToolTipText(((InfoTreeNode)value).getToolTip());
				}
				else {
					panel.setToolTipText(null);
				}

//				//mark leafs as hyperlinks
//				if (leaf) {
//					//set underline
//					Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
//					fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
//					setFont(getFont().deriveFont(fontAttributes));
//
//					//set color to blue
//					Map<TextAttribute, Paint> fontAttributesColor = new HashMap<TextAttribute, Paint>();
//					fontAttributesColor.put(TextAttribute.FOREGROUND, Color.BLUE);
//					setFont(getFont().deriveFont(fontAttributesColor));
//
//				} else {
//					//unset underline
//					Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
//					fontAttributes.put(TextAttribute.UNDERLINE, -1);
//					setFont(getFont().deriveFont(fontAttributes));
//
//					//set color to std color
//					Map<TextAttribute, Paint> fontAttributesColor = new HashMap<TextAttribute, Paint>();
//					fontAttributesColor.put(TextAttribute.FOREGROUND, null);
//					setFont(getFont().deriveFont(fontAttributesColor));
//				}

				return panel;
		    }
		});

		
		//correct mouse pointer
		tree.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseMoved(MouseEvent e) {
		    	if (tree.isEnabled() == false) {
		    		return;
		    	}
		        int x = (int) e.getPoint().getX();
		        int y = (int) e.getPoint().getY();
		        TreePath path = tree.getPathForLocation(x, y);
		        
		        //set cursor
		        if (path != null && path.getLastPathComponent() instanceof HeProMoApplicationTreeNode) {
		        	tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		        } else {
		        	tree.setCursor(Cursor.getDefaultCursor());
		        }
		    }
		});
		
		
		//run correct application
		tree.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (tree.isEnabled() == false) {
		    		return;
		    	}
		        int x = (int) e.getPoint().getX();
		        int y = (int) e.getPoint().getY();
		        TreePath path = tree.getPathForLocation(x, y);
		        if (path != null) {
		        	if (path.getLastPathComponent() instanceof HeProMoApplicationTreeNode) {
		        		HeProMoApplicationTreeNode selectedNode = (HeProMoApplicationTreeNode) path.getLastPathComponent();
		        		selectedNode.run();
		        	}
		        }
		    }
		});

		tree.setFont(tree.getFont().deriveFont(tree.getFont().getSize() + 1f));
//		tree.setFont(tree.getFont().deriveFont(Font.BOLD));
		tree.setRowHeight(20);
		ToolTipManager.sharedInstance().registerComponent(tree);
		
		return tree;
	}
	
	
	
	private class InfoTreeNode extends DefaultMutableTreeNode {
		private static final long serialVersionUID = 1L;
		private final String label;
		private final String tooltip;
		
		public InfoTreeNode(String label, String tooltip) {
			this.label = label;
			this.tooltip = tooltip;
		}
		
		public String getToolTip() {
			return tooltip;
		}
		
		@Override
		public String toString() {
			
			//if waiting screen is visible, do not render labels as html, so that labels are grayed out correctly
			if (lblWait != null && lblWait.isVisible() == true) {
				return label;
			}
			
			return "<html><nobr>" + label + "</nobr></html>"; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	
	
	private class HeProMoApplicationTreeNode extends DefaultMutableTreeNode {
		private static final long serialVersionUID = 1L;
		private Class<?> clazz;
		private String label;
		private static final boolean labelAsHtmlLink = true;
		private boolean bold = false;
		
		public HeProMoApplicationTreeNode(Class<?> clazz, String label, boolean bold) {
			this.clazz = clazz;
			this.label = label;
			this.bold = bold;
		}
		
//		public HeProMoApplicationTreeNode(Class<? extends HeProMoWindow> clazz, String label) {
//			this(clazz, label, false);
//		}
		
		public void run() {
			lblWait.setVisible(true);
			tree.setEnabled(false);

			SwingWorker<String, Integer> worker = new SwingWorker<String, Integer>() {
				@Override
				public String doInBackground() throws Exception {
	    			try {
	    				//this is not done in the event dispatcher thread since it is time-consuming.
	    				// after the constructor is called, concrete HeProMoWindow-classes do all 
	    				// initialization stuff in the event dispatcher thread.
	    				clazz.newInstance();
	    			} 
	    			catch (InstantiationException e) {
	    				HeProMoExceptionHandler.handle(e);
	    			} 
	    			catch (IllegalAccessException e) {
	    				HeProMoExceptionHandler.handle(e);
	    			}
	    			catch (Exception e) {
	    				HeProMoExceptionHandler.handle(e);
	    			}
					return null;
				}

				@Override
				public void done() {
	    			lblWait.setVisible(false);
	    			tree.setEnabled(true);
				}
			};
			worker.execute();
		}
		
		@Override
		public String toString() {
			
			//if waiting screen is visible, do not render labels as html, so that labels are grayed out correctly
			if (lblWait != null && lblWait.isVisible() == true) {
				return label;
			}
			
			if (labelAsHtmlLink && !bold) {
				return "<html><a href=\"\">" + label + "</a></html>"; //$NON-NLS-1$ //$NON-NLS-2$
			} 
			else if (labelAsHtmlLink && bold) {
				return "<html><a href=\"\"><b>" + label + "</b></a></html>"; //$NON-NLS-1$ //$NON-NLS-2$
			} 
			else {
				return label;
			}
		}
	}
	

	
	private TreeNode getRootNode() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Einzelprozesse"); //$NON-NLS-1$
        
        String tooltipAktuelleModelle = 
        		"<html>" //$NON-NLS-1$
        		+ GuiStrings.getString("MainWindow.TooltipAktuelleModelleZeile1") + "<br><br>"  //$NON-NLS-1$//$NON-NLS-2$
        		+ "&nbsp;&nbsp;&nbsp;" + GuiStrings.getString("MainWindow.TooltipAktuelleModelleZeile2") + "<br><br>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        		+ "&nbsp;&nbsp;&nbsp;" + GuiStrings.getString("MainWindow.TooltipAktuelleModelleZeile3") + "<br>" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" //$NON-NLS-1$
        		+ GuiStrings.getString("MainWindow.TooltipAktuelleModelleZeile4") //$NON-NLS-1$
        		+ "<font size=0><br><br></font>" //$NON-NLS-1$
        		+ "</html>"; //$NON-NLS-1$
        
        String tooltipAlteModelle = 
        		"<html>" //$NON-NLS-1$
        		+ GuiStrings.getString("MainWindow.TooltipAlteModelleZeile1") + "<br>"  //$NON-NLS-1$//$NON-NLS-2$
        		+ GuiStrings.getString("MainWindow.TooltipAlteModelleZeile2") + "<br>"  //$NON-NLS-1$//$NON-NLS-2$
        		+ GuiStrings.getString("MainWindow.TooltipAlteModelleZeile3") + "<br>"  //$NON-NLS-1$//$NON-NLS-2$
        		+ GuiStrings.getString("MainWindow.TooltipAlteModelleZeile4") + "<br>"  //$NON-NLS-1$//$NON-NLS-2$
        		+ GuiStrings.getString("MainWindow.TooltipAlteModelleZeile5") //$NON-NLS-1$
//        		+ "<font size=0><br><br></font>"
        		+ "</html>"; //$NON-NLS-1$
        
        DefaultMutableTreeNode rootNodeAktuelleModelle = new InfoTreeNode(GuiStrings.getString("MainWindow.menuAktuelleModelle"), tooltipAktuelleModelle); //$NON-NLS-1$
        DefaultMutableTreeNode rootNodeAlteModelle = new InfoTreeNode(GuiStrings.getString("MainWindow.menuAlteModelle"), tooltipAlteModelle); //$NON-NLS-1$
        DefaultMutableTreeNode rootNodeBiomasse = new DefaultMutableTreeNode(GuiStrings.getString("MainWindow.menuVolumenberechnungen")); //$NON-NLS-1$
        rootNode.add(rootNodeAktuelleModelle);
    	rootNode.add(rootNodeAlteModelle);
    	rootNode.add(rootNodeBiomasse);

        //Motormanuell
//        DefaultMutableTreeNode nodeMotormanuellRoot = new DefaultMutableTreeNode("Holzhauerei motormanuell");
//        rootNodeAlteModelle.add(nodeMotormanuellRoot);
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(MotormanuellGesamt.class, "Gesamtmodell", true) );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(FaellenMotormanuell.class, "Fällen") );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(EntastenMotormanuell.class, "Entasten") );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(EntrindenVonHand.class, "Entrinden von Hand") );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(EntrindenMitBiber.class, "Entrinden mit Handmaschine") );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(IhLangAufarbeiten.class, "Industrieholz lang aufarbeiten") );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(SchichtholzAufarbeiten.class, "Schichtholz aufarbeiten") );
//        nodeMotormanuellRoot.add( new HeProMoApplicationTreeNode(StammholzAufarbeiten.class, "Stammholz aufarbeiten") );
        rootNodeAlteModelle.add( new HeProMoApplicationTreeNode(MotormanuellGesamt.class, "Holzhauerei motormanuell 1978", true) ); //$NON-NLS-1$
        rootNodeAlteModelle.add( new HeProMoApplicationTreeNode(FaellenMotormanuell.class, "Fällen motormanuell 1978", true) ); //$NON-NLS-1$

        //Motormanuell 2014
        DefaultMutableTreeNode nodeMotormanuell2014Root = new HeProMoApplicationTreeNode(MotormanuellGesamt2014.class, GuiStrings.getString("MainWindow.menuMotormanuell2014"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeMotormanuell2014Root);
        
        //Radharvester
//        DefaultMutableTreeNode nodeRadharvesterRoot = new HeProMoApplicationTreeNode(Radharvester.class, "Radharvester 1998", true); //$NON-NLS-1$
//        rootNodeAlteModelle.add(nodeRadharvesterRoot);
        
        //Radharvester 2014
        DefaultMutableTreeNode nodeRadharvester2014Root = new HeProMoApplicationTreeNode(Radharvester2014.class, GuiStrings.getString("MainWindow.menuRadharvester2014"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeRadharvester2014Root);
        
        //Hacker
        DefaultMutableTreeNode nodeHackerRoot = new HeProMoApplicationTreeNode(Hacker2018.class, GuiStrings.getString("MainWindow.menuHacker"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeHackerRoot);
        
        //Hackschnitzel-Transport
        DefaultMutableTreeNode nodeHackschnitzelTransportRoot = new HeProMoApplicationTreeNode(HackschnitzelTransport2018.class, GuiStrings.getString("MainWindow.menuWaldhackschnitzelTransport"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeHackschnitzelTransportRoot);
        
        //Fällen und Vorruecken
        DefaultMutableTreeNode nodeVorrueckenRoot = new HeProMoApplicationTreeNode(Vorruecken2018.class, GuiStrings.getString("MainWindow.menuFaellenUndVorruecken"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeVorrueckenRoot);
        
        //Schlepper
        DefaultMutableTreeNode nodeSchlepperRoot = new HeProMoApplicationTreeNode(Schlepper.class, "Schlepper 1980/1992", true); //$NON-NLS-1$
        rootNodeAlteModelle.add(nodeSchlepperRoot);
        
        //Schlepper 2014
        DefaultMutableTreeNode nodeSchlepper2014Root = new HeProMoApplicationTreeNode(Schlepper2014.class, GuiStrings.getString("MainWindow.menuSchlepper2014"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeSchlepper2014Root);
        
        //Forwarder
        DefaultMutableTreeNode nodeForwarderRoot = new HeProMoApplicationTreeNode(Forwarder.class, GuiStrings.getString("MainWindow.menuForwarder"), true); //$NON-NLS-1$
        rootNodeAlteModelle.add(nodeForwarderRoot);
        
        //Forwarder Rundholz 2018
        DefaultMutableTreeNode nodeForwarderRundholz2018Root = new HeProMoApplicationTreeNode(ForwarderRundholz2018.class, GuiStrings.getString("MainWindow.menuForwarderNeuRundholz"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeForwarderRundholz2018Root);
        
        //Forwarder Energieholz 2018
        DefaultMutableTreeNode nodeForwarderEnergieholz2018Root = new HeProMoApplicationTreeNode(ForwarderEnergieholz2018.class, GuiStrings.getString("MainWindow.menuForwarderNeuEnergieholz"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeForwarderEnergieholz2018Root);
        
        //Konventioneller Seilkran
//        DefaultMutableTreeNode nodeKonventionellerSeilkranRoot = new DefaultMutableTreeNode("Konventioneller Seilkran");
//        rootNodeAktuelleModelle.add(nodeKonventionellerSeilkranRoot);
//        nodeKonventionellerSeilkranRoot.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranGesamt.class, "Gesamtmodell", true) );
//        nodeKonventionellerSeilkranRoot.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranPlanung.class, "Planung") );
//        nodeKonventionellerSeilkranRoot.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranMontage.class, "Montage") );
//        nodeKonventionellerSeilkranRoot.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranSeilen.class, "Seilen") );
//        nodeKonventionellerSeilkranRoot.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranDemontage.class, "Demontage") );
//        nodeKonventionellerSeilkranRoot.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranLagerplatz.class, "Lagerplatzarbeiten") );
        rootNodeAktuelleModelle.add( new HeProMoApplicationTreeNode(KonventionellerSeilkranGesamt.class, GuiStrings.getString("MainWindow.menuKonventionellerSeilkran"), true) ); //$NON-NLS-1$
        
        //Mobilseilkran
//        DefaultMutableTreeNode nodeMobilseilkranRoot = new DefaultMutableTreeNode("Mobilseilkran");
//        rootNodeAktuelleModelle.add(nodeMobilseilkranRoot);
//        nodeMobilseilkranRoot.add( new HeProMoApplicationTreeNode(MobilseilkranGesamt.class, "Gesamtmodell", true) );
//        nodeMobilseilkranRoot.add( new HeProMoApplicationTreeNode(MobilseilkranPlanung.class, "Planung") );
//        nodeMobilseilkranRoot.add( new HeProMoApplicationTreeNode(MobilseilkranInstallation.class, "Installation") );
//        nodeMobilseilkranRoot.add( new HeProMoApplicationTreeNode(MobilseilkranSeilen.class, "Seilen") );
//        nodeMobilseilkranRoot.add( new HeProMoApplicationTreeNode(MobilseilkranLagerplatz.class, "Lagerplatzarbeiten") );
        rootNodeAktuelleModelle.add( new HeProMoApplicationTreeNode(MobilseilkranGesamt.class, GuiStrings.getString("MainWindow.menuMobilseilkran"), true) ); //$NON-NLS-1$
        
        //Kombiseilgerät
        DefaultMutableTreeNode nodeKombiseilgeraetRoot = new HeProMoApplicationTreeNode(Kombiseilgeraet2018.class, GuiStrings.getString("MainWindow.menuKombiseilgeraet"), true); //$NON-NLS-1$
        rootNodeAktuelleModelle.add(nodeKombiseilgeraetRoot);
        
        //Helikopter
//        DefaultMutableTreeNode nodeHelikopterRoot = new DefaultMutableTreeNode("Helikopter");
//        rootNodeAktuelleModelle.add(nodeHelikopterRoot);
//        nodeHelikopterRoot.add( new HeProMoApplicationTreeNode(HelikopterGesamt.class, "Gesamtmodell", true) );
//        nodeHelikopterRoot.add( new HeProMoApplicationTreeNode(HelikopterFliegen.class, "Holz fliegen") );
//        nodeHelikopterRoot.add( new HeProMoApplicationTreeNode(HelikopterAufarbeiten.class, "Aufarbeiten, sortieren, lagern") );
        rootNodeAktuelleModelle.add( new HeProMoApplicationTreeNode(HelikopterGesamt.class, GuiStrings.getString("MainWindow.menuHelikopter"), true) ); //$NON-NLS-1$

        //Mobiler Hacker
//        DefaultMutableTreeNode nodeMobilerHackerRoot = new HeProMoApplicationTreeNode(MobilerHacker.class, "Mobiler Hacker", true); //$NON-NLS-1$
//        rootNodeAlteModelle.add(nodeMobilerHackerRoot);
        
        //Biomasseschätzer
        rootNodeBiomasse.add(new HeProMoApplicationTreeNode(Biomasseschaetzer2018.class, GuiStrings.getString("MainWindow.menuBiomasseschaetzer"), true)); //$NON-NLS-1$
        rootNodeBiomasse.add(new HeProMoApplicationTreeNode(BiomasseschaetzerEnergie2018.class, GuiStrings.getString("MainWindow.menuEnergieholzschaetzer"), true)); //$NON-NLS-1$
        rootNodeBiomasse.add(new HeProMoApplicationTreeNode(BiomasseschaetzerAbTarif2018.class, GuiStrings.getString("MainWindow.menuSchaftholzschaetzer"), true)); //$NON-NLS-1$
        

        
        if (locale.equals(new Locale("fr")) == true) {  //$NON-NLS-1$
        	rootNodeAlteModelle.removeAllChildren(); //Die alten Modelle existeren nur auf deutsch
        	
        	//create and add info node
        	DefaultMutableTreeNode infoNode = new DefaultMutableTreeNode("<html><font color=\"gray\">modèles seulement disponsible en allemand</font><html>"); //$NON-NLS-1$
        	rootNodeAlteModelle.add(infoNode);
        }
        
        if (locale.equals(new Locale("it")) == true) {  //$NON-NLS-1$
        	rootNodeAlteModelle.removeAllChildren(); //Die alten Modelle existeren nur auf deutsch
        	
        	//create and add info node
        	DefaultMutableTreeNode infoNode = new DefaultMutableTreeNode("<html><font color=\"gray\">Questi modelli sono disponibili solo in tedesco</font><html>"); //$NON-NLS-1$
        	rootNodeAlteModelle.add(infoNode);
        }
        
        if (locale.equals(new Locale("en")) == true) {  //$NON-NLS-1$
        	rootNodeAlteModelle.removeAllChildren(); //Die alten Modelle existeren nur auf deutsch
        	
        	//create and add info node
        	DefaultMutableTreeNode infoNode = new DefaultMutableTreeNode("<html><font color=\"gray\">these models are available in German only</font><html>"); //$NON-NLS-1$
        	rootNodeAlteModelle.add(infoNode);
        }
        
        
        return rootNode;
	}
	
	
    private ImageIcon getWaitImage() {
		return getImageIcon("data/loadinganimation.gif"); //$NON-NLS-1$
	}
	
	
	public static ImageIcon getWslLogo() {
		return getImageIcon("data/WSL64.png"); //$NON-NLS-1$
	}
	
	
	
	private static ImageIcon getImageIcon(String filePath) {
		//try to open from jar
		URL imgURL = MainWindow.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		
		//otherwise, try to open from file system
		return new ImageIcon(filePath);
	}

	
	
	private void changeLanguage(Locale locale) {

		//Check ob neue Sprache gewählt
		if (locale.equals(MainWindow.locale) == false ) {

			//Locale überall anpassen
			setAllLocales(locale);
			
			//Neues Fenster öffnen / altes schliessen
			MainWindow mainWindow = new MainWindow();
			mainWindow.setLocation(this.getLocation());
			this.dispose();
		}
	}
	
	
	private static void setAllLocales(Locale newLocale){
		MainWindow.locale = newLocale;
//		Locale.setDefault(locale);
		JComponent.setDefaultLocale(locale);
		GuiStrings.setLocale(locale);
		ModelStrings.setLocale(locale);
	}
	
	
	
	private String getHeProMoDate() {
		GregorianCalendar cal = new GregorianCalendar(hepromoYear, hepromoMonth, 15);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", MainWindow.locale); //$NON-NLS-1$
		String hepromoDate = sdf.format(cal.getTime());
		return hepromoDate;
	}
	
	
	public static Locale getCurrentLocale() {
		return locale;
	}
	
}
