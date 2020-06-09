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
public class ArbeitssystemHelikopterFliegen extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private double anflugPauschale;
	private HelikopterKlasse helikopterKlasse;
	private double lastVolumen;
	private boolean lastVolumenAutomatischBerechnen;
	private int anzahlPersonal;
	

	public double getKostensatzMaschine1_proMin() {
		return super.getKostensatzMaschine1_proH() / 60.0;
	}


	public void setKostensatzMaschine1_proMin(double value) {
		super.setKostensatzMaschine1_proH(value * 60.0);
	}
	
	
	
	public double getAnflugPauschale() {
		return anflugPauschale;
	}


	public void setAnflugPauschale(double anflugPauschale) {
		this.anflugPauschale = anflugPauschale;
	}


	public HelikopterKlasse getHelikopterKlasse() {
		return helikopterKlasse;
	}


	public void setHelikopterKlasse(HelikopterKlasse helikopterKlasse) {
		this.helikopterKlasse = helikopterKlasse;
	}


	public double getLastVolumen() {
		return lastVolumen;
	}


	public void setLastVolumen(double lastVolumen) {
		this.lastVolumen = lastVolumen;
	}
	
	
	public boolean isLastVolumenAutomatischBerechnen() {
		return lastVolumenAutomatischBerechnen;
	}
	
	
	public void setLastVolumenAutomatischBerechnen(boolean flag) {
		this.lastVolumenAutomatischBerechnen = flag;
	}


	public int getAnzahlPersonal() {
		return anzahlPersonal;
	}


	public void setAnzahlPersonal(int anzahlPersonal) {
		this.anzahlPersonal = anzahlPersonal;
	}


	public enum HelikopterKlasse {
		Leicht,
		Mittel,
		Schwer;
		
		@Override
		public String toString() {
			switch(this) {
			case Leicht:
				return PdfLabels.ArbeitssystemHelikopterFliegen_HeliklasseLeicht.toString(); 
				
			case Mittel:
				return PdfLabels.ArbeitssystemHelikopterFliegen_HeliklasseMittel.toString(); 
				
			case Schwer:
				return PdfLabels.ArbeitssystemHelikopterFliegen_HeliklasseSchwer.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = new LabelValuePairList(decimalFormat);
		
		list.add("Kostensatz " + getLabelPersonal1() + " (" + waehrung + "/Std.)", 		getKostensatzPersonal1_proH());
		list.add("Kostensatz " + getLabelMaschine1() + " (" + waehrung + "/B'Min.)", 	getKostensatzMaschine1_proMin());

		list.add("Anflugpauschale (" + waehrung + ")", anflugPauschale);
		list.add("Helikopterklasse", helikopterKlasse);
		list.add("Lastvolumen", lastVolumen);
		list.add("Lastvolumen automatisch berechnen", lastVolumenAutomatischBerechnen);
		list.add("Anzahl Personen", anzahlPersonal);

		list.add("Tägliche Arbeitszeit (Min.)", 				getTaeglicheArbeitszeit_Min());
		list.add("davon bezahlte Wegzeiten und Pausen (Min.)", 	getWegzeitenUndPausen_Min());
		list.add("Umsetzen (" + waehrung + ")",					getUmsetzenBetrag_CHF());
		list.add("Umsetzen (Std.)", 							getUmsetzenZeit_h());
		list.add("Weitere Aufwände (" + waehrung + ")", 		getWeitereAufwaendeBetrag_CHF());
		list.add("Weitere Aufwände (Std.)", 					getWeitereAufwaendeZeit_h());

		return list;
	}

}
