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

import java.io.Serializable;
import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Faktoren implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private double margin; //Risiko, Verwaltung, Gewinn (Prozentsatz)
	private double mehrwertsteuer = 0;
	private String waehrungskuerzel;
	private double korrekturFaktor; //Betriebsspezifischer Korrekturfaktor
	
	
	/*
	 * GETTERS AND SETTERS
	 */

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public double getMehrwertsteuer() {
		return mehrwertsteuer;
	}

	public void setMehrwertsteuer(double mehrwertsteuer) {
		this.mehrwertsteuer = mehrwertsteuer;
	}

	public String getWaehrungskuerzel() {
		return waehrungskuerzel;
	}

	public void setWaehrungskuerzel(String waehrungskuerzel) {
		this.waehrungskuerzel = waehrungskuerzel;
	}


	public double getKorrekturFaktor() {
		return korrekturFaktor;
	}

	public void setKorrekturFaktor(double korrekturFaktor) {
		this.korrekturFaktor = korrekturFaktor;
	}
	
	
	
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = new LabelValuePairList(decimalFormat);

		list.add(PdfLabels.Faktoren_RisikoVerwaltungGewinn, margin);
		list.add(PdfLabels.Faktoren_Mehrwertsteuer, mehrwertsteuer);
		list.add(PdfLabels.Faktoren_Waehrungskuerzel, waehrungskuerzel);
		list.add(PdfLabels.Faktoren_Korrekturfaktor, korrekturFaktor);
		
		return list;
	}
}
