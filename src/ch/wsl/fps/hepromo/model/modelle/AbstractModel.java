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
package ch.wsl.fps.hepromo.model.modelle;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import ch.wsl.fps.hepromo.model.Ergebnis;
import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.calc.AbstractCalculator;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class AbstractModel implements HeProMoInputData {
	
	protected String arbeitsort;
	
	protected Arbeitsobjekt arbeitsobjekt;
	protected Arbeitssystem arbeitssystem;
	protected Faktoren faktoren;
	protected AbstractCalculator calculator;
	
	protected static final String defaultWaehrungskuerzel = "Fr.";
	
	public static final double KOSTENSATZ_MOTORSAEGE_CHF_PRO_PMH15 = 18;
	

	
	protected final void setInputData(HeProMoInputData inputData) {
		this.arbeitsobjekt = inputData.getArbeitsobjekt();
		this.arbeitssystem = inputData.getArbeitssystem();
		this.faktoren      = inputData.getFaktoren();
	}
	
	
	public Ergebnis getErgebnis() {
		return calculator.calculate();
	}
	
	
	@Override
	public Arbeitsobjekt getArbeitsobjekt() {
		return arbeitsobjekt;
	}
	
	
	@Override
	public Arbeitssystem getArbeitssystem() {
		return arbeitssystem;
	}


	@Override
	public Faktoren getFaktoren() {
		return faktoren;
	}
	
	
	public AbstractCalculator getCalculator() {
		return calculator;
	}
	
	
	@Override
	public String getArbeitsort() {
		return arbeitsort;
	}
	
	
	public void setArbeitsort(String arbeitsort) {
		this.arbeitsort = arbeitsort;
	}
	
	
	
	public String getModelAsXmlString(DecimalFormat decimalFormat) {
		String xmlModelName = "	<modellname>" + getModelName() + "</modellname>";
		String xmlInfo = getMetaInformationAsXmlString();
		String xmlPdfTitles = getPdfTitlesAsXmlString();
		String waehrung = getFaktoren().getWaehrungskuerzel();
		
		String xmlFaktoren = getFaktoren().getLabelValuePairList(decimalFormat).getAsXmlString("faktoren");
		String xmlAo =  getArbeitsobjekt().getLabelValuePairList(decimalFormat).getAsXmlString("arbeitsobjekt");
		String xmlAs =  getArbeitssystem().getLabelValuePairList(decimalFormat, waehrung).getAsXmlString("arbeitssystem");
		String xmlErgebnis = getErgebnis().getErgebnisAsXmlString(decimalFormat, waehrung);
		
		String result = xmlModelName + xmlInfo + xmlPdfTitles + xmlFaktoren + xmlAo + xmlAs + xmlErgebnis;
		
		return result;
	}
	
	
	private String getMetaInformationAsXmlString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<info>\n");
		
		if (arbeitsort.length() > 0) {
			sb.append("\t<arbeitsort>");
				sb.append(arbeitsort);
			sb.append("\t</arbeitsort>\n");
		}
		
			sb.append("\t<creationdate>");
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				sb.append( sdf.format(new GregorianCalendar().getTime() ));
			sb.append("\t</creationdate>\n");

		sb.append("</info>\n");
		
		return sb.toString();
	}
	
	
	private String getPdfTitlesAsXmlString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<pdftitles>\n");
		
			sb.append("\t<haupttitel>");
				sb.append(PdfLabels.AbstractModel_PdfTitles_Haupttitel);
			sb.append("\t</haupttitel>\n");
		
			sb.append("\t<arbeitsort>");
				sb.append(PdfLabels.AbstractModel_PdfTitles_ArbeitsortHolzschlag);
			sb.append("\t</arbeitsort>\n");
		
			sb.append("\t<arbeitsobjekt>");
				sb.append(PdfLabels.AbstractModel_PdfTitles_Arbeitsobjekt);
			sb.append("\t</arbeitsobjekt>\n");
		
			sb.append("\t<arbeitssystem>");
				sb.append(PdfLabels.AbstractModel_PdfTitles_Arbeitssystem);
			sb.append("\t</arbeitssystem>\n");
		
			sb.append("\t<faktoren>");
				sb.append(PdfLabels.AbstractModel_PdfTitles_Faktoren);
			sb.append("\t</faktoren>\n");
		
			sb.append("\t<ergebnis>");
				sb.append(PdfLabels.AbstractModel_PdfTitles_Ergebnis);
			sb.append("\t</ergebnis>\n");
		
			sb.append("\t<ergebnisSuffix>");
				sb.append(getErgebnisTitleSuffix());
			sb.append("\t</ergebnisSuffix>\n");

		sb.append("</pdftitles>\n");
		
		return sb.toString();
	}
	
	
	
	/**
	 * Returns a readable name of the model
	 */
	protected abstract String getModelName();
	
	
	protected String getErgebnisTitleSuffix() {
		return "";
	}
	

}
