/*******************************************************************************
 * Copyright 2021 Stefan Holm
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.HeProMoExceptionHandler;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CsvCreator {
	
	private static final String CHARSET = "UTF-8";
	
	public ExportMethodResult create(AbstractModel model, File csvFile, DecimalFormat decimalFormat) {
		//Setze XML-String zusammen
		String finalXMLString = 
				"<?xml version=\"1.0\" encoding=\"" + CHARSET + "\" standalone=\"yes\"?>\n" +
				"<kalkulation>\n" +
				model.getModelAsXmlString(decimalFormat) +
				"</kalkulation>";
		
//		System.out.println(finalXMLString);
		
		//Generate csv-file
		ExportMethodResult exportMethodResult = generateCsv(finalXMLString, csvFile);
		return exportMethodResult;
	}
	
	
	private ExportMethodResult generateCsv(String xmlString, File csvFile) {
		try {
			Element rootElement = getRootElement(xmlString);
			StringBuilder sbCsv = new StringBuilder();
			
			// first row: titles
			String ergebnisSuffix = getErgebnisSuffix(rootElement);
			sbCsv.append("Modellname;Arbeitsort;Erstelldatum;Arbeitsobjekt;;Arbeitssystem;;Faktoren;;Ergebnis" + ergebnisSuffix);
			sbCsv.append("\n");

			// second row++: data
			sbCsv.append(rootElement.getElementsByTagName("modellname").item(0).getTextContent());
			sbCsv.append(";");


			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			
			Element elementArbeitsort = (Element) xpath.evaluate("info/arbeitsort", rootElement, XPathConstants.NODE);
			if (elementArbeitsort != null) {
				sbCsv.append(elementArbeitsort.getTextContent());
			}

			sbCsv.append(";");
			sbCsv.append(rootElement.getElementsByTagName("creationdate").item(0).getTextContent());
			sbCsv.append(";");

			Element elementArbeitsobjekt = (Element) xpath.evaluate("arbeitsobjekt", rootElement, XPathConstants.NODE);
			Element elementArbeitssystem = (Element) xpath.evaluate("arbeitssystem", rootElement, XPathConstants.NODE);
			Element elementFaktoren = (Element) xpath.evaluate("faktoren", rootElement, XPathConstants.NODE);
			Element elementErgebnis = (Element) xpath.evaluate("ergebnis", rootElement, XPathConstants.NODE);

			List<String> entriesArbeitsobjekt = getInputEntries(elementArbeitsobjekt);
			List<String> entriesArbeitssystem = getInputEntries(elementArbeitssystem);
			List<String> entriesFaktoren = getInputEntries(elementFaktoren);
			List<String> entriesErgebnis = getOutputEntries(elementErgebnis);


			List<String> concatedList = concatLists(";;;", entriesArbeitsobjekt, entriesArbeitssystem, entriesFaktoren, entriesErgebnis);
			for (String str : concatedList) {
				sbCsv.append(str);
				sbCsv.append("\n");
			}
//			System.out.println(sbCsv);

			ExportMethodResult exportMethodResult = saveCsv(sbCsv.toString(), csvFile);
			if (exportMethodResult.success) {
				System.out.println("CSV erfolgreich erstellt");
			}
			return exportMethodResult;
		}
		catch (Exception e) {
			HeProMoExceptionHandler.handle(e);
			return new ExportMethodResult(false, false);
		}
	}
	
	
	private static String getErgebnisSuffix(Element rootElement) {
		NodeList nodeListErgebnisSuffix = rootElement.getElementsByTagName("ergebnisSuffix");
		if (nodeListErgebnisSuffix.getLength() == 0) {
			return "";
		}
		else {
			return nodeListErgebnisSuffix.item(0).getTextContent();
		}
	}


	private static Element getRootElement(String xmlString) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xmlString));
		Document doc = builder.parse(is);
		Element rootElement = doc.getDocumentElement();
		rootElement.normalize();
		return rootElement;
	}
	
	
	private static List<String> getInputEntries(Element parent) {
		List<String> result = new ArrayList<>();
		for (int i=0; i<parent.getChildNodes().getLength(); i++) {
			if (parent.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element entry = (Element) parent.getChildNodes().item(i);

				String label = entry.getElementsByTagName("label").item(0).getTextContent();
				String value = entry.getElementsByTagName("value").item(0).getTextContent();
				result.add(label + ";" + value);
			}
		}
		return result;
	}
	
	
	private static List<String> getOutputEntries(Element parent) {
		List<String> result = new ArrayList<>();
		for (int i=0; i<parent.getChildNodes().getLength(); i++) {
			if (parent.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element ergebniszeile = (Element) parent.getChildNodes().item(i);
				
				String zeile = "";
				for (int spalte=0; spalte<ergebniszeile.getChildNodes().getLength(); spalte++) {
					Node item = ergebniszeile.getChildNodes().item(spalte);
					if (item.getNodeType() == Node.ELEMENT_NODE) {
						zeile += item.getTextContent() + ";";
					}
				}
				result.add(zeile);
			}
		}
		return result;
	}
	
	
	private static List<String> concatLists(String linePrefix, List<?>...lists) {
		List<String> result = new ArrayList<>();
		
		for (int row=0; row<getMaxSize(lists); row++) {
			String line = row==0 ? "" : linePrefix;
			for (List<?> list : lists) {
				if (row < list.size()) {
					line += list.get(row) + ";";
				}
				else {
					line += ";;";
				}
			}
			result.add(line);
		}
		return result;
	}
	
	
	/**
	 * returns the size of the largest list.
	 */
	private static int getMaxSize(List<?>...lists) {
		int max = 0;
		for (List<?> list : lists) {
			if (list.size() > max) {
				max = list.size();
			}
		}
		return max;
	}
	
	
	private static ExportMethodResult saveCsv(String content, File file) {
		try (PrintWriter printWriter = new PrintWriter(file)) { // we do NOT use "our" charset here, but the default charset of the target system.
			printWriter.write(content);
			return new ExportMethodResult(true, false);
		}
		catch (FileNotFoundException e) {
			String msg = GuiStrings.getString("HeProMoWindow.FehlerBeimErstellenDerDatei") + "\n" + e.getLocalizedMessage(); //$NON-NLS-1$
			JOptionPane.showMessageDialog(null, msg, GuiStrings.getString("HeProMoWindow.DialogTitleFehler"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			return new ExportMethodResult(false, true);
		}
		catch (Exception e) {
			HeProMoExceptionHandler.handle(e);
			return new ExportMethodResult(false, false);
		}
	}
}
