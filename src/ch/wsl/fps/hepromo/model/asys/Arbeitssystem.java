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

import java.io.Serializable;
import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Arbeitssystem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int taeglicheArbeitszeit_Min;
	private int wegzeitenUndPausen_Min;
	
	private double umsetzenBetrag_CHF;
	private double umsetzenZeit_h;
	private double weitereAufwaendeBetrag_CHF;
	private double weitereAufwaendeZeit_h;
	
	private double kostensatzPersonal1_proH;
	private double kostensatzMaschine1_proH;
	private double kostensatzMaschine2_proH;
	
	private String labelPersonal1 = null;
	private String labelMaschine1 = null;
	private String labelMaschine2 = null;
	
	
	
	public double getFaktorWegzeitenUndPausen() {
		double arbeit = taeglicheArbeitszeit_Min;
		double pausen = wegzeitenUndPausen_Min;
		double f_wegzeitenUndPausen = arbeit / (arbeit - pausen);
		
		return f_wegzeitenUndPausen;
	}
	
	
	
	/*
	 * 	GETTERS AND SETTERS
	 */

	public int getTaeglicheArbeitszeit_Min() {
		return taeglicheArbeitszeit_Min;
	}
	
	public void setTaeglicheArbeitszeit_Min(int taeglicheArbeitszeit_Min) {
		this.taeglicheArbeitszeit_Min = taeglicheArbeitszeit_Min;
	}

	public int getWegzeitenUndPausen_Min() {
		return wegzeitenUndPausen_Min;
	}
	
	public void setWegzeitenUndPausen_Min(int wegzeitenUndPausen_Min) {
		this.wegzeitenUndPausen_Min = wegzeitenUndPausen_Min;
	}

	public double getUmsetzenBetrag_CHF() {
		return umsetzenBetrag_CHF;
	}
	
	public void setUmsetzenBetrag_CHF(double umsetzenBetrag_CHF) {
		this.umsetzenBetrag_CHF = umsetzenBetrag_CHF;
	}

	public double getUmsetzenZeit_h() {
		return umsetzenZeit_h;
	}
	
	public void setUmsetzenZeit_h(double umsetzenZeit_h) {
		this.umsetzenZeit_h = umsetzenZeit_h;
	}

	public double getWeitereAufwaendeBetrag_CHF() {
		return weitereAufwaendeBetrag_CHF;
	}
	
	public void setWeitereAufwaendeBetrag_CHF(double weitereAufwaendeBetrag_CHF) {
		this.weitereAufwaendeBetrag_CHF = weitereAufwaendeBetrag_CHF;
	}

	public double getWeitereAufwaendeZeit_h() {
		return weitereAufwaendeZeit_h;
	}
	
	public void setWeitereAufwaendeZeit_h(double weitereAufwaendeZeit_h) {
		this.weitereAufwaendeZeit_h = weitereAufwaendeZeit_h;
	}

	public double getKostensatzPersonal1_proH() {
		return kostensatzPersonal1_proH;
	}

	public void setKostensatzPersonal1_proH(double value) {
		this.kostensatzPersonal1_proH = value;
	}

	public double getKostensatzMaschine1_proH() {
		return kostensatzMaschine1_proH;
	}

	public void setKostensatzMaschine1_proH(double value) {
		this.kostensatzMaschine1_proH = value;
	}

	public double getKostensatzMaschine2_proH() {
		return kostensatzMaschine2_proH;
	}

	public void setKostensatzMaschine2_proH(double value) {
		this.kostensatzMaschine2_proH = value;
	}


	
	public void setLabelPersonal1(String label) {
		this.labelPersonal1 = label;
	}
	
	public void setLabelMaschine1(String label) {
		this.labelMaschine1 = label;
	}
	
	public void setLabelMaschine2(String label) {
		this.labelMaschine2 = label;
	}
	
	
	
	public String getLabelPersonal1() {
		return labelPersonal1;
	}

	public String getLabelMaschine1() {
		return labelMaschine1;
	}

	public String getLabelMaschine2() {
		return labelMaschine2;
	}
	



	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = new LabelValuePairList(decimalFormat);

		list.add(PdfLabels.Arbeitssystem_TaeglicheArbeitszeit_min, 					taeglicheArbeitszeit_Min);
		list.add(PdfLabels.Arbeitssystem_WegzeitenPausen_min, 						wegzeitenUndPausen_Min);
		list.add(PdfLabels.Arbeitssystem_Umsetzen + " (" + waehrung + ")",			umsetzenBetrag_CHF);
		list.add(PdfLabels.Arbeitssystem_Umsetzten_Std, 							umsetzenZeit_h);
		list.add(PdfLabels.Arbeitssystem_WeitereAufwaende + " (" + waehrung + ")", 	weitereAufwaendeBetrag_CHF);
		list.add(PdfLabels.Arbeitssystem_WeitereAufwaende_Std, 						weitereAufwaendeZeit_h);
		
		if (labelPersonal1 != null) {
			list.add(PdfLabels.Arbeitssystem_Kostensatz + ": " + labelPersonal1 + " (" + waehrung + PdfLabels.Arbeitssystem_proStd + ")", 	kostensatzPersonal1_proH);
		}
		
		if (labelMaschine1 != null) {
			list.add(PdfLabels.Arbeitssystem_Kostensatz + ": " + labelMaschine1 + " (" + waehrung + PdfLabels.Arbeitssystem_proBStd + ")", 	kostensatzMaschine1_proH);
		}
		
		if (labelMaschine2 != null) {
			list.add(PdfLabels.Arbeitssystem_Kostensatz + ": " + labelMaschine2 + " (" + waehrung + PdfLabels.Arbeitssystem_proBStd + ")", 	kostensatzMaschine2_proH);
		}

		return list;
	}
	
	
	/**
	 * Gibt den Kostensatz des Personals zurück. Falls mehrere 
	 * Personen mit unterschiedlichen Kostensätzen vorhanden sind, 
	 * gibt diese Methode den gemittelten kombinierten Kostensatz 
	 * zurück, d.h. die Summe der einzelnen Kostensätze, multipliziert 
	 * mit den zugehörigen Einsatzanteilen, wird geteilt durch die 
	 * Anzahl Vollzeit-Aequivalente (FTE).
	 */
	public double getAnsatzPersonalKombiniertGewichtet() {
		return getKostensatzPersonal1_proH() / 1.0;
	}
	
}
