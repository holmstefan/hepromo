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
package ch.wsl.fps.hepromo.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoExceptionHandler;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class DatenblattCreator {
	
	private static final Object classLevelLock = new Object();
	private static volatile boolean isAlreadyPreinitialized = false;
	private static final String CHARSET = "UTF-8";
	
	public static void preInitializeInSeparateThread() {
		synchronized(classLevelLock) {
			if (isAlreadyPreinitialized == false) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						DatenblattCreator instance = new DatenblattCreator();
						String emptyXMLString = "<?xml version=\"1.0\" encoding=\"" + CHARSET + "\" standalone=\"yes\"?><kalkulation></kalkulation>";
						instance.generatePdf(emptyXMLString, null);
						isAlreadyPreinitialized = true;
					}
				});
				t.start();
			}
		}
	}
	
	public ExportMethodResult create(AbstractModel model, File pdfFile, DecimalFormat decimalFormat) {
		//Setze XML-String zusammen
		String finalXMLString = 
				"<?xml version=\"1.0\" encoding=\"" + CHARSET + "\" standalone=\"yes\"?>\n" +
				"<kalkulation>\n" +
				model.getModelAsXmlString(decimalFormat) +
				"</kalkulation>";
		
		//Generiere das Pdf
		ExportMethodResult exportMethodResult = generatePdf(finalXMLString, pdfFile);
		return exportMethodResult;
	}
	
	
	private ExportMethodResult generatePdf(String xmlString, File pdfFile) {
		synchronized(classLevelLock) {
			Logger fopLogger = Logger.getLogger("org.apache.fop");
			fopLogger.setLevel(Level.OFF);

			try {
				// Step 1: Construct a FopFactory
				// (reuse if you plan to render multiple documents!)
				FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());

				// Step 2: Set up output stream.
				// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
				OutputStream out = getOutputStream(pdfFile);

				try {
					// Step 3: Construct fop with desired output format
					Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

					// Step 4: Setup XSLT
					String xsltFilePath = "data/kalkulation2fo.xsl";
					TransformerFactory factory = TransformerFactory.newInstance();
					Transformer transformer = factory.newTransformer(new StreamSource(openFile(xsltFilePath)));
//			        transformer.setOutputProperty("encoding", CHARSET);

					// Step 5: Setup input for XSLT transformation
					Source src = new StreamSource(new ByteArrayInputStream(xmlString.getBytes(CHARSET))); 

					// Resulting SAX events (the generated FO) must be piped through to FOP
					Result res = new SAXResult(fop.getDefaultHandler());

					// Step 6: Start XSLT transformation and FOP processing
					transformer.transform(src, res);
					
					if (pdfFile != null) {
						System.out.println("Datenblatt erfolgreich erstellt");
						return new ExportMethodResult(true, false);
					}
				} catch (FOPException e) {
					HeProMoExceptionHandler.handle(e);

				} catch (TransformerException e) {
					HeProMoExceptionHandler.handle(e);

				} catch (TransformerFactoryConfigurationError e) {
					HeProMoExceptionHandler.handle(e);

				} finally {
					//Clean-up
					out.close();
				}
			} catch (FileNotFoundException e) {
				String msg = GuiStrings.getString("HeProMoWindow.FehlerBeimErstellenDerDatei") + "\n" + e.getLocalizedMessage(); //$NON-NLS-1$
				JOptionPane.showMessageDialog(null, msg, GuiStrings.getString("HeProMoWindow.DialogTitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
				return new ExportMethodResult(false, true);

			} catch (IOException e) {
				HeProMoExceptionHandler.handle(e);
			}
		}
		return new ExportMethodResult(false, false);
	}
	
	
	/**
	 * @param pdfFile if null, a NullOutputStream is returned.
	 * @throws FileNotFoundException 
	 */
	private OutputStream getOutputStream(File pdfFile) throws FileNotFoundException {
		if (pdfFile == null) {
			return new NullOutputStream();
		}
		else {
			// Set up output stream.
			// Note: Using BufferedOutputStream for performance reasons (helpful with FileOutputStreams).
			OutputStream out = new BufferedOutputStream(new FileOutputStream( pdfFile ));
			return out;
		}
	}
	
	
	private class NullOutputStream extends OutputStream {
		@Override
		public void write(int b) throws IOException {
			//do nothing
		}
	}
	
	
	private BufferedReader openFile(String filePath) {
		//try to open from jar
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);	
		if (is != null) {
			try {
				return new BufferedReader(new InputStreamReader(is, CHARSET));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new BufferedReader(new InputStreamReader(is));
			}
		}
		
		//otherwise, try to open from file system
		File file = new File(filePath);
		try {
			//open file
			try {
				return new BufferedReader( new InputStreamReader(new FileInputStream(file), CHARSET));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new BufferedReader( new InputStreamReader(new FileInputStream(file)));
			}
			
		} catch (FileNotFoundException e) {
			HeProMoExceptionHandler.handle(e);
		}
		
		return null;
	}

}
