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

	private boolean risikoVerwaltungGewinnMerged = true;
	private double margin; //Risiko, Verwaltung, Gewinn (Prozentsatz)
	private double risiko;
	private double verwaltung;
	private double gewinn;
	private double mehrwertsteuer = 0;
	private String waehrungskuerzel;
	private double korrekturFaktor; //Betriebsspezifischer Korrekturfaktor
	
	
	/*
	 * GETTERS AND SETTERS
	 */

	public boolean isRisikoVerwaltungGewinnMerged() {
		return risikoVerwaltungGewinnMerged;
	}

	public void setRisikoVerwaltungGewinnMerged(boolean risikoVerwaltungGewinnMerged) {
		this.risikoVerwaltungGewinnMerged = risikoVerwaltungGewinnMerged;
	}

	public double getMargin() {
		if (isRisikoVerwaltungGewinnMerged()) {
			return margin;
		}
		else {
			return (((1 + risiko/100d) * (1 + verwaltung/100d) * (1 + gewinn/100d)) - 1) *100d;
		}
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public double getRisiko() {
		return risiko;
	}

	public void setRisiko(double risiko) {
		this.risiko = risiko;
	}

	public double getVerwaltung() {
		return verwaltung;
	}

	public void setVerwaltung(double verwaltung) {
		this.verwaltung = verwaltung;
	}

	public double getGewinn() {
		return gewinn;
	}

	public void setGewinn(double gewinn) {
		this.gewinn = gewinn;
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

		if (isRisikoVerwaltungGewinnMerged()) {
			list.add(PdfLabels.Faktoren_RisikoVerwaltungGewinn, margin);
		}
		else {
			list.add(PdfLabels.Faktoren_Risiko, risiko);
			list.add(PdfLabels.Faktoren_Verwaltung, verwaltung);
			list.add(PdfLabels.Faktoren_Gewinn, gewinn);
		}
		list.add(PdfLabels.Faktoren_Mehrwertsteuer, mehrwertsteuer);
		list.add(PdfLabels.Faktoren_Waehrungskuerzel, waehrungskuerzel);
		list.add(PdfLabels.Faktoren_Korrekturfaktor, korrekturFaktor);
		
		return list;
	}
}
