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
import java.util.HashMap;

import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Ergebnis {

	protected double psh0_hProM3;
	protected double psh0_total;
	
	protected double zeitPersonal;
	protected double zeitMaschine1;
	protected double zeitMaschine2;
	protected double zeitMaschine3;
	protected double zeitUmsetzen;
	protected double zeitWeitereAufwaende;
	protected double zeitTotal;
	
	protected double kostenPersonal_total;
	protected double kostenMaschine1_total;
	protected double kostenMaschine2_total;
	protected double kostenMaschine3_total;
	protected double kostenUmsetzen_total;
	protected double kostenWeitereAufwaende_total;
//	protected double kostenTotal_total;

	private final HashMap<ProdEinheit, Double> produktivitaeten = new HashMap<ProdEinheit, Double>();
	{
		produktivitaeten.put(ProdEinheit.EMPTY, -1.0);
	}
	protected ProdEinheit prodEinheit = ProdEinheit.M3_PRO_PSH15;;
	protected ProdEinheit prodEinheit2 = ProdEinheit.EMPTY;
	
	private double anzahl_m3;

	protected double faktorRisikoVerwaltungGewinn = 1;
	protected double faktorMehrwertsteuer = 1;
	
	protected String labelPersonal1 = null;
	protected String labelMaschine1 = null;
	protected String labelMaschine2 = null;
	protected String labelMaschine3 = null;
	
	protected boolean showDauerDerArbeit = true;
	protected boolean showProduktivitaet = true;
	
	
	
	/*
	 * SETTERS
	 */
	
	public void setPsh0_hProM3(double psh0_hProM3) {
		this.psh0_hProM3 = psh0_hProM3;
	}
	
	public void setPsh0_total(double psh0_total) {
		this.psh0_total = psh0_total;
	}
	
	
	
	public void setZeitPersonal(double zeitPersonal) {
		this.zeitPersonal = zeitPersonal;
	}

	public void setZeitMaschine1(double zeitMaschine1) {
		this.zeitMaschine1 = zeitMaschine1;
	}

	public void setZeitMaschine2(double zeitMaschine2) {
		this.zeitMaschine2 = zeitMaschine2;
	}

	public void setZeitMaschine3(double zeitMaschine3) {
		this.zeitMaschine3 = zeitMaschine3;
	}

	public void setZeitUmsetzen(double zeitUmsetzen) {
		this.zeitUmsetzen = zeitUmsetzen;
	}

	public void setZeitWeitereAufwaende(double zeitWeitereAufwaende) {
		this.zeitWeitereAufwaende = zeitWeitereAufwaende;
	}

	public void setZeitTotal(double zeitTotal) {
		this.zeitTotal = zeitTotal;
	}
	
	
	

	public void setKostenPersonal_total(double kostenPersonal_total) {
		this.kostenPersonal_total = kostenPersonal_total;
	}

	public void setKostenMaschine1_total(double kostenMaschine1_total) {
		this.kostenMaschine1_total = kostenMaschine1_total;
	}

	public void setKostenMaschine2_total(double kostenMaschine2_total) {
		this.kostenMaschine2_total = kostenMaschine2_total;
	}

	public void setKostenMaschine3_total(double kostenMaschine3_total) {
		this.kostenMaschine3_total = kostenMaschine3_total;
	}

	public void setKostenUmsetzen_total(double kostenUmsetzen_total) {
		this.kostenUmsetzen_total = kostenUmsetzen_total;
	}

	public void setKostenWeitereAufwaende_total(double kostenWeitereAufwaende_total) {
		this.kostenWeitereAufwaende_total = kostenWeitereAufwaende_total;
	}

//	public void setKostenTotal_total(double kostenTotal_total) {
//		this.kostenTotal_total = kostenTotal_total;
//	}
	
	
	public void setProduktivitaet(Purpose purpose, ProdEinheit prodEinheit, double produktivitaet) {
		if (purpose == Purpose.FIRST) {
			this.prodEinheit = prodEinheit;
		}
		else if (purpose == Purpose.SECOND) {
			this.prodEinheit2 = prodEinheit;
		}
		produktivitaeten.put(prodEinheit, produktivitaet);
	}
	
	

	public void setAnzahl_m3(double anzahl_m3) {
		this.anzahl_m3 = anzahl_m3;
	}


	public void setRisikoVerwaltungGewinn_Prz(double value) {
		faktorRisikoVerwaltungGewinn = (value / 100.0) + 1.0;
	}


	public void setMehrwertsteuer_Prz(double value) {
		faktorMehrwertsteuer = (value / 100.0) + 1.0;
	}
	
	
	
	/*
	 * GETTERS
	 */
	
	public double getPsh0_hProM3() {
		return psh0_hProM3;
	}
	
	public double getPsh0_total() {
		return psh0_total;
	}
	
	
	
	public double getZeitPersonal() {
		return zeitPersonal;
	}

	public double getZeitMaschine1() {
		return zeitMaschine1;
	}

	public double getZeitMaschine2() {
		return zeitMaschine2;
	}

	public double getZeitMaschine3() {
		return zeitMaschine3;
	}
	
	public double getZeitUmsetzen() {
		return zeitUmsetzen;
	}
	
	public double getZeitWeitereAufwaende() {
		return zeitWeitereAufwaende;
	}
	
	public double getZeitTotal() {
		return zeitTotal;
	}
	


	
	public double getKostenPersonal_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenPersonal_total() / getAnzahl_m3();
	}
	
	public double getKostenMaschine1_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenMaschine1_total() / getAnzahl_m3();
	}
	
	public double getKostenMaschine2_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenMaschine2_total() / getAnzahl_m3();
	}
	
	public double getKostenMaschine3_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenMaschine3_total() / getAnzahl_m3();
	}
	
	public double getKostenUmsetzen_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenUmsetzen_total() / getAnzahl_m3();
	}
	
	public double getKostenWeitereAufwaende_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenWeitereAufwaende_total() / getAnzahl_m3();
	}
	
	public double getKostenTotal_proM3() {
		if (getAnzahl_m3() <= 0) {
			return 0;
		}
		return getKostenTotal_total() / getAnzahl_m3();
	}
	

	

	
	public double getKostenPersonal_total() {
		return kostenPersonal_total;
	}
	
	public double getKostenMaschine1_total() {
		return kostenMaschine1_total;
	}
	
	public double getKostenMaschine2_total() {
		return kostenMaschine2_total;
	}
	
	public double getKostenMaschine3_total() {
		return kostenMaschine3_total;
	}
	
	public double getKostenUmsetzen_total() {
		return kostenUmsetzen_total;
	}
	
	public double getKostenWeitereAufwaende_total() {
		return kostenWeitereAufwaende_total;
	}
	
	public double getKostenTotal_total() {
		double sum =  
				getKostenPersonal_total() + 
				getKostenMaschine1_total() +
				getKostenMaschine2_total() +
				getKostenMaschine3_total() +
				getKostenUmsetzen_total() +
				getKostenWeitereAufwaende_total();
		
		//Faktor "Risiko, Verwaltung, Gewinn" hinzu-multiplizieren
		sum *= faktorRisikoVerwaltungGewinn;
		sum *= faktorMehrwertsteuer;
		
		return sum;
	}

	
	public double getProduktivitaet() {
		return getProduktivitaet(prodEinheit);
	}
	
	public double getProduktivitaet2() {
		return getProduktivitaet(prodEinheit2);
	}
	
	public double getProduktivitaet_m3ProPsh15() { //TODO: methode entfernen?
		return getProduktivitaet(ProdEinheit.M3_PRO_PSH15);
	}
	
	public double getProduktivitaet_m3ProPmh15() { //TODO: methode entfernen?
		return getProduktivitaet(ProdEinheit.M3_PRO_PMH15);
	}
	
	public double getProduktivitaet_fmORproWPPH() { //TODO: methode entfernen?
		return getProduktivitaet(ProdEinheit.FM_OR_PRO_WPPH);
	}

	public double getProduktivitaet(ProdEinheit prodEinheit) {
		if (prodEinheit != null && produktivitaeten.containsKey(prodEinheit)) {
			return produktivitaeten.get(prodEinheit);
		}
		else {
			return -1;
		}
	}
	
	
	
	public double getAnzahl_m3() {
		return anzahl_m3;
	}


	public double getRisikoVerwaltungGewinn_Prz() {
		double value = (faktorRisikoVerwaltungGewinn - 1.0) * 100.0;
		return value;
	}


	public double getMehrwertsteuer_Prz() {
		double value = (faktorMehrwertsteuer - 1.0) * 100.0;
		return value;
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
	
	public void setLabelMaschine3(String label) {
		this.labelMaschine3 = label;
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

	public String getLabelMaschine3() {
		return labelMaschine3;
	}
	
	
	public void hideDauerDerArbeit() {
		this.showDauerDerArbeit = false;
	}
	
	
	public void hideProduktivitaet() {
		this.showProduktivitaet = false;
	}
	
	
	
	

	public String getErgebnisAsXmlString(DecimalFormat decimalFormat, String waehrung) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ergebnis>\n");
		
		String[][] strings = getErgebnisStrings(decimalFormat, waehrung);
		for (String[] element : strings) {

			if (element == null) {
				//do nothing
			}
			else if ( element.length == 0 ) {
				sb.append("\t<leerzeile/>\n");
			}
			else {
				sb.append("\t<ergebniszeile>\n");

					sb.append("\t\t<spalte1>");
						sb.append(element[0]);
					sb.append("</spalte1>\n");

					sb.append("\t\t<spalte2>");
						sb.append(element[1]);
					sb.append("</spalte2>\n");

					sb.append("\t\t<spalte3>");
						sb.append(element[2]);
					sb.append("</spalte3>\n");

					sb.append("\t\t<spalte4>");
						sb.append(element[3]);
					sb.append("</spalte4>\n");

					sb.append("\t\t<spalte5>");
						sb.append(element[4]);
					sb.append("</spalte5>\n");

				sb.append("\t</ergebniszeile>\n");
			}
		}
		
		sb.append("</ergebnis>\n");
		
		return sb.toString();
	}
	
	
	
	protected String[][] getErgebnisStrings(DecimalFormat df, String waehrung) {
		
		if (getProduktivitaet() < 0) {
			hideProduktivitaet();
		}
		
		String[][] strings = new String[11][];
		
		if (getAnzahl_m3() > 1) {
			strings[ 0] = 							new String[]{"", 												PdfLabels.Ergebnis_Zeitaufwand_Std.toString(), 	"", 		PdfLabels.Ergebnis_Kosten + " (" + waehrung + ")", 	""												};
			strings[ 1] = showDauerDerArbeit ?		new String[]{PdfLabels.Ergebnis_DauerDerArbeit.toString(),		df.format(getZeitTotal()), 						"", 		PdfLabels.Ergebnis_proM3.toString(), 				PdfLabels.Ergebnis_total.toString()				} : new String[]{"", "", "", PdfLabels.Ergebnis_proM3.toString(), PdfLabels.Ergebnis_total.toString()}	;
			strings[ 2] = labelPersonal1 != null ? 	new String[]{labelPersonal1,									df.format(getZeitPersonal()), 					"WPPH", 	df.format(getKostenPersonal_proM3()), 				df.format(getKostenPersonal_total()) 			} : null;
			strings[ 3] = labelMaschine1 != null ? 	new String[]{labelMaschine1, 									df.format(getZeitMaschine1()), 					"PMH15", 	df.format(getKostenMaschine1_proM3()),	 			df.format(getKostenMaschine1_total()) 			} : null;
			strings[ 4] = labelMaschine2 != null ? 	new String[]{labelMaschine2, 									df.format(getZeitMaschine2()), 					"PMH15", 	df.format(getKostenMaschine2_proM3()),	 			df.format(getKostenMaschine2_total()) 			} : null;
			strings[ 5] = labelMaschine3 != null ? 	new String[]{labelMaschine3, 									df.format(getZeitMaschine3()), 					"PMH15", 	df.format(getKostenMaschine3_proM3()),	 			df.format(getKostenMaschine3_total()) 			} : null;
			strings[ 6] = 							new String[]{PdfLabels.Ergebnis_Umsetzen.toString(), 			df.format(getZeitUmsetzen()), 					"", 		df.format(getKostenUmsetzen_proM3()), 				df.format(getKostenUmsetzen_total()) 			};
			strings[ 7] = 							new String[]{PdfLabels.Ergebnis_WeitereAufwaende.toString(), 	df.format(getZeitWeitereAufwaende()), 			"", 		df.format(getKostenWeitereAufwaende_proM3()), 		df.format(getKostenWeitereAufwaende_total()) 	};
			strings[ 8] = 							new String[]{PdfLabels.Ergebnis_Total.toString(), 				"", 											"", 		"<b>" + df.format(getKostenTotal_proM3()) + "</b>", df.format(getKostenTotal_total()) 				};
			strings[ 9] = 							new String[]{};
			strings[10] = showProduktivitaet ?  	new String[]{PdfLabels.Ergebnis_Produktivitaet + " (" + prodEinheit + ")", 	df.format(getProduktivitaet()),	"", "", 												""												} : null;
		}
		else {
			strings[ 0] = 							new String[]{"", 												PdfLabels.Ergebnis_Zeitaufwand_Std.toString(), 	"", 		PdfLabels.Ergebnis_Kosten + " (" + waehrung + ")",	"", 		};
			strings[ 1] = showDauerDerArbeit ?		new String[]{PdfLabels.Ergebnis_DauerDerArbeit.toString(),		df.format(getZeitTotal()), 						"", 		PdfLabels.Ergebnis_total.toString(),				"", 		} : new String[]{"", "", "", PdfLabels.Ergebnis_total.toString(),"", };
			strings[ 2] = labelPersonal1 != null ? 	new String[]{labelPersonal1,									df.format(getZeitPersonal()), 					"WPPH", 	df.format(getKostenPersonal_total()), 				"", 		} : null;
			strings[ 3] = labelMaschine1 != null ? 	new String[]{labelMaschine1, 									df.format(getZeitMaschine1()), 					"PMH15", 	df.format(getKostenMaschine1_total()), 				"", 		} : null;
			strings[ 4] = labelMaschine2 != null ? 	new String[]{labelMaschine2, 									df.format(getZeitMaschine2()), 					"PMH15", 	df.format(getKostenMaschine2_total()), 				"", 		} : null;
			strings[ 5] = labelMaschine3 != null ? 	new String[]{labelMaschine3, 									df.format(getZeitMaschine3()), 					"PMH15", 	df.format(getKostenMaschine3_total()), 				"", 		} : null;
			strings[ 6] = 							new String[]{PdfLabels.Ergebnis_Umsetzen.toString(), 			df.format(getZeitUmsetzen()), 					"", 		df.format(getKostenUmsetzen_total()), 				"", 		};
			strings[ 7] = 							new String[]{PdfLabels.Ergebnis_WeitereAufwaende.toString(), 	df.format(getZeitWeitereAufwaende()), 			"", 		df.format(getKostenWeitereAufwaende_total()), 		"", 		};
			strings[ 8] = 							new String[]{PdfLabels.Ergebnis_Total.toString(), 				"", 											"", 		"<b>" + df.format(getKostenTotal_total()) + "</b>",	"", 		};
			strings[ 9] = 							new String[]{};
			strings[10] = showProduktivitaet ?  	new String[]{PdfLabels.Ergebnis_Produktivitaet + " (" + prodEinheit + ")", 	df.format(getProduktivitaet()),	"", "", 												""			} : null;
		}
		
		
		return strings;
	}

	
	
	/** Einheit der Produktivität */
	public static enum ProdEinheit { //TODO: alle entweder iR oder oR?
		
		EMPTY,

		FM_OR_PRO_WPPH,
		
		M3_PRO_PMH15,
		
		M3_IR_PRO_PMH15_BEIM_SEILEN,
		M3_OR_PRO_PMH15_BEIM_SEILEN,
		
		M3_PRO_PSH15,

		M3_IR_PRO_PMH15,
		M3_OR_PRO_PMH15,

		M3_IR_PRO_PSH15,
		M3_OR_PRO_PSH15,

		M3_IR_PRO_WSH,
		M3_OR_PRO_WSH,
		
		M3_IR_PRO_WPSH,
		M3_OR_PRO_WPSH,
		
		SRM_PRO_PMH15;
		
		
		
		@Override
		public String toString() {
			switch (this) {
			case EMPTY:
				return "";
				
			case FM_OR_PRO_WPPH:
				throw new RuntimeException(); //wird im Moment nur intern verwendet

			case M3_PRO_PMH15:
				return PdfLabels.AbstractCalculatorSingleModel2014_m3_pro_PMH15.toString();

			case M3_IR_PRO_PMH15_BEIM_SEILEN:
				return PdfLabels.CalculatorSeilkranGesamt_ProduktivitaetBeimSeilen_m3iRproPMH15.toString();

			case M3_OR_PRO_PMH15_BEIM_SEILEN:
				return PdfLabels.CalculatorSeilkranGesamt_ProduktivitaetBeimSeilen_m3oRproPMH15.toString();
				
			case M3_PRO_PSH15:
				return PdfLabels.Ergebnis_m3_pro_PSH15.toString();

			case M3_IR_PRO_PMH15:
				return PdfLabels.AbstractCalculatorSingleModel2014_m3iR_pro_PMH15.toString();

			case M3_OR_PRO_PMH15:
				return PdfLabels.AbstractCalculatorSingleModel2014_m3oR_pro_PMH15.toString();
				
			case M3_IR_PRO_PSH15:
				return ModelStrings.getString("CalculatorKombiseilgeraet2018.m3iRProPSH15");
				
			case M3_OR_PRO_PSH15:
				return ModelStrings.getString("CalculatorKombiseilgeraet2018.m3oRProPSH15");
				
			case M3_IR_PRO_WSH:
				return ModelStrings.getString("CalculatorKombiseilgeraet2018.m3iRProWSH");
				
			case M3_OR_PRO_WSH:
				return ModelStrings.getString("CalculatorKombiseilgeraet2018.m3oRProWSH");

			case M3_IR_PRO_WPSH:
				return PdfLabels.CalculatorMotormanuellGesamt2014_m3iR_pro_WPSH.toString();

			case M3_OR_PRO_WPSH:
				return PdfLabels.CalculatorMotormanuellGesamt2014_m3oR_pro_WPSH.toString();

			case SRM_PRO_PMH15:
				return ModelStrings.getString("CalculatorHacker2018.SrmProPMH15");
				
			default:
				throw new RuntimeException("unhandled case: " + this.name());
			}
		}
	}
	
	/** Zweck einer Produktivitäts-Einheit */
	public static enum Purpose {
		/** Wird im GUI als erstes angezeigt */
		FIRST, 

		/** Wird im GUI als zweites angezeigt */
		SECOND, 

		/** Wird nicht im GUI angezeigt, nur interne Verwendung */
		ADDITIONAL;
	}
	
}
