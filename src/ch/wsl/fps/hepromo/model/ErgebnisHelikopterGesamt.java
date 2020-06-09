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

import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisHelikopterGesamt extends Ergebnis {
	
	private double zeitHelifirma;
	private double zeitTotalFliegen;
	private double zeitTotalAbsenkplatz;
	
	private double kostenHeli_total;
	
	protected double rotationszeit;
	
	
	
	/*
	 * SETTERS
	 */
	
	public void setZeitHelifirma(double zeitHelifirma) {
		this.zeitHelifirma = zeitHelifirma;
	}

	public void setZeitTotalFliegen(double zeitTotalFliegen) {
		this.zeitTotalFliegen = zeitTotalFliegen;
	}

	public void setZeitTotalAbsenkplatz(double zeitTotalAbsenkplatz) {
		this.zeitTotalAbsenkplatz = zeitTotalAbsenkplatz;
	}
	
	public void setKostenHeli_total(double kostenHeli_total) {
		this.kostenHeli_total = kostenHeli_total;
	}

	public void setRotationszeit(double rotationszeit) {
		this.rotationszeit = rotationszeit;
	}
	
	
	

	
	/*
	 * GETTERS
	 */
	
	public double getZeitHelifirma() {
		return zeitHelifirma;
	}
	
	public double getZeitTotalFliegen() {
		return zeitTotalFliegen;
	}

	public double getZeitTotalAbsenkplatz() {
		return zeitTotalAbsenkplatz;
	}
	


	public double getKostenHeli_total() {
		return kostenHeli_total;
	}

	public double getKostenHeli_proM3() {
		return kostenHeli_total / getAnzahl_m3();
	}

	
	public double getRotationszeit() {
		return rotationszeit;
	}
	
	
	
	
	@Override
	public double getKostenTotal_total() {
		double sum =  
				super.getKostenTotal_total() + 
				getKostenHeli_total() * super.faktorRisikoVerwaltungGewinn * super.faktorMehrwertsteuer;
		
		return sum;
	}
	
	

	@Override
	protected String[][] getErgebnisStrings(DecimalFormat df, String waehrung) {
		
		String[][] strings = new String[12][];

		strings[ 0] = new String[]{"", 																		PdfLabels.Ergebnis_Zeitaufwand_Std.toString(), 		"", 		PdfLabels.Ergebnis_Kosten.toString() + " (" + waehrung + ")", 	""							};
		strings[ 1] = new String[]{PdfLabels.ErgebnisHelikopterGesamt_DauerDerArbeitFliegen.toString(),		df.format(getZeitTotalFliegen()), 					"", 		"", 											""											};
		strings[ 2] = new String[]{PdfLabels.ErgebnisHelikopterGesamt_DauerDerArbeitAbsenkplatz.toString(),	df.format(getZeitTotalAbsenkplatz()),				"", 		PdfLabels.Ergebnis_proM3.toString(),			PdfLabels.Ergebnis_total.toString()			};
		strings[ 3] = new String[]{PdfLabels.ErgebnisHelikopterGesamt_Helifirma.toString(),					df.format(getZeitHelifirma()), 						"PMH15", 	df.format(getKostenHeli_proM3()), 				df.format(getKostenHeli_total()) 			};
		strings[ 4] = new String[]{labelPersonal1,															df.format(getZeitPersonal()), 						"WPPH", 	df.format(getKostenPersonal_proM3()), 			df.format(getKostenPersonal_total()) 			};
		strings[ 5] = new String[]{labelMaschine1, 															df.format(getZeitMaschine1()), 						"PMH15", 	df.format(getKostenMaschine1_proM3()),	 		df.format(getKostenMaschine1_total()) 			};
		strings[ 6] = new String[]{labelMaschine2, 															df.format(getZeitMaschine2()), 						"PMH15", 	df.format(getKostenMaschine2_proM3()),	 		df.format(getKostenMaschine2_total()) 			};
		strings[ 7] = new String[]{PdfLabels.Ergebnis_Umsetzen.toString(), 									df.format(getZeitUmsetzen()), 						"", 		df.format(getKostenUmsetzen_proM3()), 			df.format(getKostenUmsetzen_total()) 			};
		strings[ 8] = new String[]{PdfLabels.Ergebnis_WeitereAufwaende.toString(), 							df.format(getZeitWeitereAufwaende()), 				"", 		df.format(getKostenWeitereAufwaende_proM3()), 	df.format(getKostenWeitereAufwaende_total()) 	};
		strings[ 9] = new String[]{PdfLabels.Ergebnis_Total.toString(), 									"", 												"", 		df.format(getKostenTotal_proM3()), 				df.format(getKostenTotal_total()) 				};
		strings[10] = new String[]{};
		strings[11] = new String[]{PdfLabels.ErgebnisHelikopterGesamt_Rotationszeit_Min_pro_Rot.toString(), df.format(getRotationszeit()),						"", 		"", 											""												};
		
		
		return strings;
	}

}
