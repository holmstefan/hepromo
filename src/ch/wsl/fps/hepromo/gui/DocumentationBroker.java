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

import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import ch.wsl.fps.hepromo.model.modelle.AbstractModel;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder;
import ch.wsl.fps.hepromo.model.modelle.ModelForwarder2018;
import ch.wsl.fps.hepromo.model.modelle.ModelHacker2018;
import ch.wsl.fps.hepromo.model.modelle.ModelHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterAufarbeiten;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterFliegen;
import ch.wsl.fps.hepromo.model.modelle.ModelHelikopterGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranDemontage;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranMontage;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranPlanung;
import ch.wsl.fps.hepromo.model.modelle.ModelKonventionellerSeilkranSeilen;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilerHacker;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranInstallation;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranLagerplatz;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranPlanung;
import ch.wsl.fps.hepromo.model.modelle.ModelMobilseilkranSeilen;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellEntasten;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellEntrindenMitBiber;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellEntrindenVonHand;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellFaellen;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellGesamt;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellIhLangAufarbeiten;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellSchichtholzAufarbeiten;
import ch.wsl.fps.hepromo.model.modelle.ModelMotormanuellStammholzAufarbeiten;
import ch.wsl.fps.hepromo.model.modelle.ModelRadharvester;
import ch.wsl.fps.hepromo.model.modelle.ModelRadharvester2014;
import ch.wsl.fps.hepromo.model.modelle.ModelSchlepper;
import ch.wsl.fps.hepromo.model.modelle.ModelSchlepper2014;
import ch.wsl.fps.hepromo.model.modelle.ModelVorruecken2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class DocumentationBroker {
	
	public static final String DOKU_DIR = "Dokumentation/"; //$NON-NLS-1$

//	protected static final HashMap<Class<? extends AbstractModel>, String[]> filenames = new HashMap<Class<? extends AbstractModel>, String[]>();
	protected static final HashMap<Object, String[]> filenames = new HashMap<>();
	
	
	/**
	 * Die Dokumentation für die Modelle, für welche hier eine enum-Konstante
	 * definiert ist, kann mit der Methode showDocumentation() direkt geladen
	 * werden, ohne dass vorher eine Überprüfung mittels exists() gemacht wird.
	 */
	public static enum Documentation {
		Biomasseschaetzer2018,
		Energieholzschaetzer2018,
		Schaftholzschaetzer2018;
	}
	
	
	private DocumentationBroker() {} //Der Zugriff auf diese Klasse erfolgt ausschliesslich über statische Methoden.
	
	
	static {
		filenames.put(ModelMotormanuellGesamt2014.class, 	new String[]{"Motormanuell2014A.pdf", "Motormanuell2014B.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelRadharvester2014.class, 			new String[]{"Radharvester2014A.pdf", "Radharvester2014B.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelSchlepper2014.class, 			new String[]{"Schlepper2014A.pdf", "Schlepper2014B.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$

		filenames.put(ModelMotormanuellGesamt.class, 					new String[]{"MotormanuellA.pdf", "MotormanuellGesamtB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellFaellen.class, 					new String[]{"MotormanuellA.pdf", "MotormanuellFaellenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellEntasten.class, 					new String[]{"MotormanuellA.pdf", "MotormanuellEntastenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellEntrindenVonHand.class, 			new String[]{"MotormanuellA.pdf", "MotormanuellEntrindenVonHandB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellEntrindenMitBiber.class, 		new String[]{"MotormanuellA.pdf", "MotormanuellEntrindenMitBiberB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellIhLangAufarbeiten.class, 		new String[]{"MotormanuellA.pdf", "MotormanuellIhLangAufarbeitenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellSchichtholzAufarbeiten.class, 	new String[]{"MotormanuellA.pdf", "MotormanuellSchichtholzAufarbeitenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMotormanuellStammholzAufarbeiten.class, 		new String[]{"MotormanuellA.pdf", "MotormanuellStammholzAufarbeitenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		
		filenames.put(ModelRadharvester.class, 			new String[]{"RadharvesterA.pdf", "RadharvesterB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelSchlepper.class, 			new String[]{"SchlepperA.pdf", "SchlepperB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMobilerHacker.class, 		new String[]{"MobilerHackerA.pdf", "MobilerHackerB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelForwarder.class, 			new String[]{"ForwarderA.pdf", "ForwarderB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		
		filenames.put(ModelHelikopterAufarbeiten.class, new String[]{"HelikopterA.pdf", "HelikopterGesamtB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelHelikopterFliegen.class, 	new String[]{"HelikopterA.pdf", "HelikopterFliegenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelHelikopterGesamt.class, 		new String[]{"HelikopterA.pdf", "HelikopterGesamtB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$

		filenames.put(ModelKonventionellerSeilkranGesamt.class, 	new String[]{"KonvSeilkranA.pdf", "KonvSeilkranGesamtB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelKonventionellerSeilkranPlanung.class, 	new String[]{"KonvSeilkranA.pdf", "SeilkranPlanungB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelKonventionellerSeilkranMontage.class, 	new String[]{"KonvSeilkranA.pdf", "KonvSeilkranMontageB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelKonventionellerSeilkranSeilen.class, 	new String[]{"KonvSeilkranA.pdf", "KonvSeilkranSeilenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelKonventionellerSeilkranDemontage.class, 	new String[]{"KonvSeilkranA.pdf", "KonvSeilkranDemontageB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelKonventionellerSeilkranLagerplatz.class, new String[]{"KonvSeilkranA.pdf", "SeilkranLagerplatzB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		
		filenames.put(ModelMobilseilkranGesamt.class, 				new String[]{"MobilSeilkranA.pdf", "MobilSeilkranGesamtB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMobilseilkranPlanung.class, 				new String[]{"MobilSeilkranA.pdf", "SeilkranPlanungB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMobilseilkranInstallation.class, 		new String[]{"MobilSeilkranA.pdf", "MobilSeilkranInstallationB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMobilseilkranSeilen.class, 				new String[]{"MobilSeilkranA.pdf", "MobilSeilkranSeilenB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelMobilseilkranLagerplatz.class, 			new String[]{"MobilSeilkranA.pdf", "SeilkranLagerplatzB.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$

		filenames.put(ModelHacker2018.class, 						new String[]{"Hacker2018A.pdf"}); //$NON-NLS-1$
		filenames.put(ModelHackschnitzelTransport2018.class, 		new String[]{"HackschnitzelTransport2018A.pdf"}); //$NON-NLS-1$
		filenames.put(ModelKombiseilgeraet2018.class, 				new String[]{"Kombiseilgeraet2018A.pdf", "Kombiseilgeraet2018B.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelVorruecken2018.class, 					new String[]{"Vorruecken2018A.pdf", "Vorruecken2018B.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$
		filenames.put(ModelForwarder2018.class, 					new String[]{"Forwarder2018A.pdf", "Forwarder2018B.pdf"}); //$NON-NLS-1$ //$NON-NLS-2$

		filenames.put(Documentation.Biomasseschaetzer2018, 				new String[]{"Biomasseschaetzer2018A.pdf"}); //$NON-NLS-1$
		filenames.put(Documentation.Energieholzschaetzer2018, 			new String[]{"Energieholzschaetzer2018A.pdf"}); //$NON-NLS-1$
		filenames.put(Documentation.Schaftholzschaetzer2018, 			new String[]{"Schaftholzschaetzer2018A.pdf"}); //$NON-NLS-1$
	}
	

	
	/**
	 * Opens a documentation pdf file that is stored inside the jar.
	 * Since files inside the jar file can not be read directly by the system,
	 * the pdf is first copied from the jar to the filesystem and then opened.
	 * 
	 * @param modelKey model key associated with documentation
	 * @param index index of the documentation file if multiple files are avaiable. '-1' directly returns
	 */
	private static void openDocumentationPdfFile(Object modelKey, int index) {
		if (index < 0) {
			return; //somit kann als index auch 'JOptionPane.CLOSED_OPTION' übergeben werden -> keine Aktion
		}
		
		if (exists(modelKey, index) == false) {
			SwingUtilities.invokeLater(() -> {
				String message = "Dokumentation nicht verfügbar!";
				JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
			});
			return;
		}
		
		String filename = DOKU_DIR + filenames.get(modelKey)[index];
		openDocumentationPdfFile(filename);
	}
	
	

	/**
	 * Checks if the documentation file for the given model is available
	 */
	public static boolean exists(AbstractModel model) {
		if (filenames.get(model.getClass()) != null) {
			if (filenames.get(model.getClass()).length > 0) {
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * Checks if the documentation file for the given model and index is available
	 */
	private static boolean exists(Object modelKey, int index) {
		if (filenames.get(modelKey) != null) {
			if (index >=0 && filenames.get(modelKey).length > index) {
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Opens a documentation pdf file that is stored inside the jar.
	 * Since files inside the jar file can not be read directly by the system,
	 * the pdf is first copied from the jar to the filesystem and then opened.
	 */
	private static void openDocumentationPdfFile(String path) {
		//copy files
		copyFileFromJarToFilesystemIfNecessary(path);
		generateReadMeFile();
		
		//try to open file
		File file = new File(path);
		try {
			Desktop.getDesktop().open(file);
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Fehler beim Öffnen der Datei", JOptionPane.ERROR_MESSAGE);			
		}
	}
	
	
	private static void generateReadMeFile() {
		File file = new File(DOKU_DIR + "README.txt"); //$NON-NLS-1$	
		if (file.exists() == false) {
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(file);
				writer.println(GuiStrings.getString("DocumentationBroker.ReadMeText"));
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static void copyFileFromJarToFilesystemIfNecessary(String path) {
		if (isStartedFromJar() == false) {
			return;
		}
		
		File fileInFilesystem = new File(path);
		
		//check if file already exists in file system
		if (fileInFilesystem.exists() == false) {
			System.out.println("File in file system does not exist. File will be copied from jar.");
			copyFileFromJarToFilesystem(path);
		}
		else if (hasFilesystemFileDifferentDateThanFileInJar(path)) {
			System.out.println("File in file system has different last-modified date than file in jar. File in file system will be replaced.");
			copyFileFromJarToFilesystem(path);
		}
		else {
			return; //no copy needed
		}
	}
	
	
	private static boolean isStartedFromJar() {
		URL url = DocumentationBroker.class.getResource("DocumentationBroker.class");
		boolean isStartedFromJar = url.toString().startsWith("jar") || url.toString().startsWith("rsrc");
		return isStartedFromJar;
	}
	
	
	private static boolean hasFilesystemFileDifferentDateThanFileInJar(String path) {
		File fileInFilesystem = new File(path);	

		long fileInFilesystemLastModified = -2;
		long fileInJarLastModified = -2;
		try {
			fileInFilesystemLastModified = fileInFilesystem.lastModified();
			fileInJarLastModified = getJarFileLastModified(path);	
//			System.out.println("filesystem: " + new Date(fileInFilesystemLastModified) + " / jar: " + new Date(fileInJarLastModified) );
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		if (fileInFilesystemLastModified <= 0 || fileInJarLastModified <=0) {
			return true;
		}
		
		return fileInFilesystemLastModified != fileInJarLastModified;
	}

	
	private static long getJarFileLastModified(String filePath) {
		filePath = "/" + filePath; //classFilePath muss mit einem slash starten
		
		try (JarFile jar = getJarFile()) {
			if (jar != null) {
				Enumeration<JarEntry> enumEntries = jar.entries();
				while (enumEntries.hasMoreElements()) {
					JarEntry file = (JarEntry) enumEntries.nextElement();
					if (file.getName().equals(filePath.substring(1))) {
						long time=file.getTime();
						return time;
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return -1;
	}
	
	
	private static JarFile getJarFile() {
		JarFile jar = null;
		
		// Versuch 1: funktioniert, falls sich HeProMo.jar im working-directory befindet
		try {
			jar = new JarFile("HeProMo.jar");
			return jar;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		// Versuch 2: funktioniert, falls sich HeProMo.jar ausserhalb working-directory befindet,
		// und das jar-File mit der Option "Package required libraries into generated JAR" erstellt wurde.
		try {
			String jarFilePath = ClassLoader.getSystemClassLoader().getResource(".")
					.toURI()
					.getPath()
					.replaceFirst("/", "");
			jar = new JarFile(jarFilePath + File.separator + "HeProMo.jar");
			return jar;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		return null;
	}
	
	
	private static void copyFileFromJarToFilesystem(String path) {
		File fileInFilesystem = new File(path);
		
		//try to open file in jar
		try (InputStream in = DocumentationBroker.class.getClassLoader().getResourceAsStream(path)) {
			if (in == null) {
				System.err.println("file '" + path + "' not found in jar");
				return;
			}

			//create target directory if it does not exist
			String[] dirs = path.split("\\\\|/");
			String dir = "";
			for (int i=0; i<dirs.length-1; i++) {
				dir += dirs[i] + "/";
			}
			File f = new File(dir);
			f.mkdirs();

			//copy file to filesystem
			int readBytes;
			byte[] buffer = new byte[4096];
			try (FileOutputStream out = new FileOutputStream(fileInFilesystem)) {
				while ((readBytes = in.read(buffer)) > 0) {
					out.write(buffer, 0, readBytes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    //set lastModified
	    long lastModified = getJarFileLastModified(path);
	    if (lastModified >= 0) {
	    	fileInFilesystem.setLastModified(lastModified);
	    }
	}
	
	
	public static void showDocumentation(final Documentation documentation, final Component parentComponent, final JButton btnDokumentation) {
		showDocumentationGeneric(documentation, false, parentComponent, btnDokumentation);
	}
	
	
	public static void showDocumentation(final AbstractModel model, final Component parentComponent, final JButton btnDokumentation) {
		boolean is2014Text = parentComponent instanceof HeProMoWindow2014;
		showDocumentationGeneric(model.getClass(), is2014Text, parentComponent, btnDokumentation);
	}
	
	
	private static void showDocumentationGeneric(final Object modelKey, final boolean is2014Text, final Component parentComponent, final JButton btnDokumentation) {
		if (SwingUtilities.isEventDispatchThread() == false) {
			throw new RuntimeException("not in EDT");
		}
		btnDokumentation.setEnabled(false);
		btnDokumentation.setText(GuiStrings.getString("HeProMoWindow.btnBitteWarten")); //$NON-NLS-1$
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
			@Override
			protected Void doInBackground() throws Exception {
				showDocumetation(modelKey, is2014Text, parentComponent);
				return null;
			}
			@Override
			protected void done() {
				btnDokumentation.setEnabled(true);	
				btnDokumentation.setText(GuiStrings.getString("HeProMoWindow.btnGrundlagen"));	 //$NON-NLS-1$
				btnDokumentation.requestFocusInWindow();
			}
		};
		worker.execute();
	}
	
	private static void showDocumetation(Object modelKey, boolean is2014Text, Component parentComponent) {
		String teilA_lang = is2014Text ? GuiStrings.getString("DocumentationBroker.TeilA_lang_2014") : GuiStrings.getString("DocumentationBroker.TeilA_lang");
		String teilB_lang = is2014Text ? GuiStrings.getString("DocumentationBroker.TeilB_lang_2014") : GuiStrings.getString("DocumentationBroker.TeilB_lang");	
		
		if (exists(modelKey, 1) == true) {
			//Teile A und B vorhanden (Standardfall)
			
			Object[] options = {GuiStrings.getString("DocumentationBroker.TeilA"), GuiStrings.getString("DocumentationBroker.TeilB")}; //$NON-NLS-1$ //$NON-NLS-2$
			
			int selection = JOptionPane.showOptionDialog(parentComponent,
					"<html>" //$NON-NLS-1$
					+ GuiStrings.getString("DocumentationBroker.WelchenDokuTeilOeffnen") //$NON-NLS-1$
					+ "<br>" //$NON-NLS-1$
					+ 	"<li>" //$NON-NLS-1$
					+ 		teilA_lang
					+ 	"</li>" //$NON-NLS-1$
					+ 	"<li>" //$NON-NLS-1$
					+ 		teilB_lang
					+ 	"</li>" //$NON-NLS-1$
					+ "</html", //$NON-NLS-1$
					GuiStrings.getString("DocumentationBroker.DialogTitleDokuOeffnen"), //$NON-NLS-1$
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]);

			openDocumentationPdfFile(modelKey, selection);
		}
		else {
			//nur Teil A vorhanden
			openDocumentationPdfFile(modelKey, 0);
		}
	}
}
