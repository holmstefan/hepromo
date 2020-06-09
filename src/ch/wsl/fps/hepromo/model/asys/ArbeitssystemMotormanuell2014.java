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
package ch.wsl.fps.hepromo.model.asys;

import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemMotormanuell2014 extends Arbeitssystem {

	private static final long serialVersionUID = 1L;
	
	private double kostensatzPersonal2_proH;
	private double kostensatzPersonal3_proH;
	private double kostensatzPersonal4_proH;

	private int einsatzanteilPersonal1_Prz = 100;
	private int einsatzanteilPersonal2_Prz;
	private int einsatzanteilPersonal3_Prz;
	private int einsatzanteilPersonal4_Prz;
	
	
	
	public double getKostensatzPersonal2_proH() {
		return kostensatzPersonal2_proH;
	}
	
	
	public void setKostensatzPersonal2_proH(double value) {
		this.kostensatzPersonal2_proH = value;
	}
	
	
	public double getKostensatzPersonal3_proH() {
		return kostensatzPersonal3_proH;
	}
	
	
	public void setKostensatzPersonal3_proH(double value) {
		this.kostensatzPersonal3_proH = value;
	}
	
	
	public double getKostensatzPersonal4_proH() {
		return kostensatzPersonal4_proH;
	}
	
	
	public void setKostensatzPersonal4_proH(double value) {
		this.kostensatzPersonal4_proH = value;
	}
	
	
	public int getEinsatzanteilPersonal1_Prz() {
		return einsatzanteilPersonal1_Prz;
	}


	public void setEinsatzanteilPersonal1_Prz(int einsatzanteilPersonal1_Prz) {
		this.einsatzanteilPersonal1_Prz = einsatzanteilPersonal1_Prz;
	}


	public int getEinsatzanteilPersonal2_Prz() {
		return einsatzanteilPersonal2_Prz;
	}


	public void setEinsatzanteilPersonal2_Prz(int einsatzanteilPersonal2_Prz) {
		this.einsatzanteilPersonal2_Prz = einsatzanteilPersonal2_Prz;
	}


	public int getEinsatzanteilPersonal3_Prz() {
		return einsatzanteilPersonal3_Prz;
	}


	public void setEinsatzanteilPersonal3_Prz(int einsatzanteilPersonal3_Prz) {
		this.einsatzanteilPersonal3_Prz = einsatzanteilPersonal3_Prz;
	}


	public int getEinsatzanteilPersonal4_Prz() {
		return einsatzanteilPersonal4_Prz;
	}


	public void setEinsatzanteilPersonal4_Prz(int einsatzanteilPersonal4_Prz) {
		this.einsatzanteilPersonal4_Prz = einsatzanteilPersonal4_Prz;
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = new LabelValuePairList(decimalFormat);

		list.add(PdfLabels.Arbeitssystem_TaeglicheArbeitszeit_min, 					getTaeglicheArbeitszeit_Min());
		list.add(PdfLabels.Arbeitssystem_WegzeitenPausen_min, 						getWegzeitenUndPausen_Min());
		list.add(PdfLabels.Arbeitssystem_Umsetzen + " (" + waehrung + ")", 			getUmsetzenBetrag_CHF());
		list.add(PdfLabels.Arbeitssystem_Umsetzten_Std, 							getUmsetzenZeit_h());
		list.add(PdfLabels.Arbeitssystem_WeitereAufwaende + " (" + waehrung + ")", 	getWeitereAufwaendeBetrag_CHF());
		list.add(PdfLabels.Arbeitssystem_WeitereAufwaende_Std, 						getWeitereAufwaendeZeit_h());

		list.add(PdfLabels.ArbeitssystemMotormanuell2014_KostensatzArbeitskraft + " 1 (" + einsatzanteilPersonal1_Prz + "%, " + waehrung + ")", getKostensatzPersonal1_proH());
		list.add(PdfLabels.ArbeitssystemMotormanuell2014_KostensatzArbeitskraft + " 2 (" + einsatzanteilPersonal2_Prz + "%, " + waehrung + ")", getKostensatzPersonal2_proH());
		list.add(PdfLabels.ArbeitssystemMotormanuell2014_KostensatzArbeitskraft + " 3 (" + einsatzanteilPersonal3_Prz + "%, " + waehrung + ")", getKostensatzPersonal3_proH());
		list.add(PdfLabels.ArbeitssystemMotormanuell2014_KostensatzArbeitskraft + " 4 (" + einsatzanteilPersonal4_Prz + "%, " + waehrung + ")", getKostensatzPersonal4_proH());

		return list;
	}
	
	
	
	
	@Override
	public double getAnsatzPersonalKombiniertGewichtet() {

		double anteil1 = (getEinsatzanteilPersonal1_Prz() / 100.0) * getKostensatzPersonal1_proH();
		double anteil2 = (getEinsatzanteilPersonal2_Prz() / 100.0) * getKostensatzPersonal2_proH();
		double anteil3 = (getEinsatzanteilPersonal3_Prz() / 100.0) * getKostensatzPersonal3_proH();
		double anteil4 = (getEinsatzanteilPersonal4_Prz() / 100.0) * getKostensatzPersonal4_proH();
		double fte = getVollzeitAequivalent();
		
		double gesamtkostensatz = (anteil1 + anteil2 + anteil3 + anteil4) / fte;
		
		return gesamtkostensatz;
	}
	
	
	
	public double getVollzeitAequivalent() {
		double fte = 
				getEinsatzanteilPersonal1_Prz() +
				getEinsatzanteilPersonal2_Prz() +
				getEinsatzanteilPersonal3_Prz() +
				getEinsatzanteilPersonal4_Prz();
		fte /= 100.0;
		
		return fte;
	}

}
