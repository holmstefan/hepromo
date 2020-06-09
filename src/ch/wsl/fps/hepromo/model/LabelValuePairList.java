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
package ch.wsl.fps.hepromo.model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class LabelValuePairList {
	
	private ArrayList<LabelValuePair> list = new ArrayList<LabelValuePair>();
	private final DecimalFormat decimalFormat;
	
	public LabelValuePairList(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
	}
	
	
	
	public class LabelValuePair {
		public final String label;
		public final String value;
		
		public LabelValuePair(String label, String value) {
			this.label = label;
			this.value = value;
		}
	}
	
	
	public void add(String label, String value) {
		list.add(new LabelValuePair(label, value));
	}
	
	
	public void addOnTop(String label, String value) {
		list.add(0, new LabelValuePair(label, value));
	}
	
	
	public void add(String label, int value) {
		add(label, String.valueOf(value));
	}
	
	
	public void add(String label, double value) {
		add(label, decimalFormat.format(value));
	}
	
	
	public void addOnTop(String label, double value) {
		addOnTop(label, decimalFormat.format(value));
	}
	
	
	public void add(String label, boolean value) {
		String text = value == true ? PdfLabels.LabelValuePairList_ja.toString() : PdfLabels.LabelValuePairList_nein.toString();
		add(label, text);
	}
	
	
	public void add(String label, Enum<?> value) {
		add(label, String.valueOf(value));
	}
	
	
	public void addOnTop(String label, Enum<?> value) {
		addOnTop(label, String.valueOf(value));
	}
	
	public void add(Enum<PdfLabels> label, String value) {
		list.add(new LabelValuePair(label.toString(), value));
	}
	
	
	public void addOnTop(Enum<PdfLabels> label, String value) {
		list.add(0, new LabelValuePair(label.toString(), value));
	}
	
	
	public void add(Enum<PdfLabels> label, int value) {
		add(label, String.valueOf(value));
	}
	
	
	public void add(Enum<PdfLabels> label, double value) {
		add(label, decimalFormat.format(value));
	}
	
	
	public void add(Enum<PdfLabels> label, boolean value) {
		String text = value == true ? PdfLabels.LabelValuePairList_ja.toString() : PdfLabels.LabelValuePairList_nein.toString();
		add(label, text);
	}
	
	
	public void add(Enum<PdfLabels> label, Enum<?> value) {
		add(label, String.valueOf(value));
	}
	
	
	public void addOnTop(Enum<PdfLabels> label, Enum<?> value) {
		addOnTop(label, String.valueOf(value));
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (LabelValuePair element : list) {
			sb.append(element.label + ": " + element.value);
		}
		
		return sb.toString();
	}
	
	
	public String getAsXmlString(String enclosingTag) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<" + enclosingTag + ">\n");
		
		for (LabelValuePair element : list) {
			sb.append("\t<entry>\n");
				sb.append("\t\t<label>");
					sb.append(escapeXmlField(element.label));
				sb.append("</label>\n");
				sb.append("\t\t<value>");
					sb.append(escapeXmlField(element.value));
				sb.append("</value>\n");
			sb.append("\t</entry>\n");
		}
		
		sb.append("</" + enclosingTag + ">\n");
		
		return sb.toString();
	}
	
	
	private String escapeXmlField(String field) {
		
		//aktuelle Implementierung funktioniert nur,
		// falls eckige Klammern höchstens einmal auftauchen
		if (field.contains("<")) {
			return field.replace("<", "<![CDATA[<]]>");
		}
		if (field.contains(">")) {
			return field.replace(">", "<![CDATA[>]]>");
		}
		return field;
		
//		return field
//				.replaceAll("<", "<![CDATA[<]]>")
//				.replaceAll(">", "<![CDATA[>]]>")
//				.replaceAll("<", "&lt;")
//				.replaceAll(">", "&gt;")
//				.replaceAll("\"", "&quot;")
//				.replaceAll("'", "&apos;")
//				.replaceAll("&", "&amp;")
//				;
	}

}
