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
package ch.wsl.fps.hepromo.model.aobj;

import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektForwarder extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	//Nutzung
	private int laubholzAnteil_Prz;
	private int durchschnittlicherBhdAushieb_cm;
	private double durchschnittlicheHolzlaenge_m;
	private double durchschnittlicheAnzahlSortimente;
	private int anzahlVerschiedeneSortimente;
	private int anteilKrumm_Prz;
	
	//Erschliessung
	private int fahrstreckeAufStrasse;
	private FahrstreckenArt fahrstreckenArtStrasse;
	private int fahrstreckeAufFeinerschliessung;
	private FahrstreckenArt fahrstreckenArtFeinerschliessung;
	
	//Erschliessung Details
	private int erschliessungsLaengeEinseitig_m;
	private int erschliessungsLaengeEinseitigAnteilStrasse_Prz;
	private int erschliessungsLaengeBeidseitig_m;
	private int erschliessungsLaengeBeidseitigAnteilStrasse_Prz;
	private HindernisKategorie hindernisKategorieFeinerschliessung;
	private NeigungsKategorie neigungsKategorieFeinerschliessung;
	
	//Fahrstreckenanteile
	private int fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz;
	private int fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz;
	private int fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz;
	private int fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz;
	private int fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz;
	private int fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz;
	private int fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz;
	private int fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz;
	private int fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz;
	private int fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz;


	public int getLaubholzAnteil_Prz() {
		return laubholzAnteil_Prz;
	}
	
	public int getNadelholzAnteil_Prz() {
		return (100 - laubholzAnteil_Prz);
	}


	public void setLaubholzAnteil_Prz(int laubholzAnteil_Prz) {
		this.laubholzAnteil_Prz = laubholzAnteil_Prz;
	}


	public int getDurchschnittlicherBhdAushieb_cm() {
		return durchschnittlicherBhdAushieb_cm;
	}


	public void setDurchschnittlicherBhdAushieb_cm(int durchschnittlicherBhdAushieb_cm) {
		this.durchschnittlicherBhdAushieb_cm = durchschnittlicherBhdAushieb_cm;
	}


	public double getDurchschnittlicheHolzlaenge_m() {
		return durchschnittlicheHolzlaenge_m;
	}


	public void setDurchschnittlicheHolzlaenge_m(double durchschnittlicheHolzlaenge_m) {
		this.durchschnittlicheHolzlaenge_m = durchschnittlicheHolzlaenge_m;
	}


	public double getDurchschnittlicheAnzahlSortimente() {
		return durchschnittlicheAnzahlSortimente;
	}


	public void setDurchschnittlicheAnzahlSortimente(double durchschnittlicheAnzahlSortimente) {
		this.durchschnittlicheAnzahlSortimente = durchschnittlicheAnzahlSortimente;
	}


	public int getAnzahlVerschiedeneSortimente() {
		return anzahlVerschiedeneSortimente;
	}


	public void setAnzahlVerschiedeneSortimente(int anzahlVerschiedeneSortimente) {
		this.anzahlVerschiedeneSortimente = anzahlVerschiedeneSortimente;
	}


	public int getAnteilKrumm_Prz() {
		return anteilKrumm_Prz;
	}


	public void setAnteilKrumm_Prz(int anteilKrumm_Prz) {
		this.anteilKrumm_Prz = anteilKrumm_Prz;
	}


	public int getFahrstreckeAufStrasse() {
		return fahrstreckeAufStrasse;
	}


	public void setFahrstreckeAufStrasse(int fahrstreckeAufStrasse) {
		this.fahrstreckeAufStrasse = fahrstreckeAufStrasse;
	}


	public FahrstreckenArt getFahrstreckenArtStrasse() {
		return fahrstreckenArtStrasse;
	}


	/**
	 * <b>Achtung:</b> beim Aufruf dieser Methode werden die prozentualen 
	 * Fahrstreckenanteile automatisch neu gesetzt. Die Verteilung 
	 * der prozentualen Fahrstreckenanteile ist abhängig vom Argument 
	 * Fahrstreckenart und in der Dokumentation ersichtlich.
	 */
	public void setFahrstreckenArtStrasse(FahrstreckenArt fahrstreckenArtStrasse) {
		this.fahrstreckenArtStrasse = fahrstreckenArtStrasse;
		
		switch(fahrstreckenArtStrasse) {
		case LastfahrtVorwaertsBergauf:
			fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz = 75;
			fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz  =  0;
			fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz =  0;
			fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz  = 75;
			break;
			
		case LastfahrtVorwaertsBergab:
			fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz =  0;
			fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz  = 75;
			fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz = 75;
			fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz  =  0;
			break;
			
		case LastfahrtVorwaertsEben:
			fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz = 10;
			fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz  = 10;
			fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz = 10;
			fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz  = 10;
			break;
			
		case GleichmaessigVerteilt:
			fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz = 33;
			fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz  = 33;
			fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz = 33;
			fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz  = 33;
			break;

		default:
			throw new RuntimeException();
		}
	}


	public int getFahrstreckeAufFeinerschliessung() {
		return fahrstreckeAufFeinerschliessung;
	}


	public void setFahrstreckeAufFeinerschliessung(int fahrstreckeAufFeinerschliessung) {
		this.fahrstreckeAufFeinerschliessung = fahrstreckeAufFeinerschliessung;
	}


	public FahrstreckenArt getFahrstreckenArtFeinerschliessung() {
		return fahrstreckenArtFeinerschliessung;
	}


	/**
	 * <b>Achtung:</b> beim Aufruf dieser Methode werden die prozentualen 
	 * Fahrstreckenanteile automatisch neu gesetzt. Die Verteilung 
	 * der prozentualen Fahrstreckenanteile ist abhängig vom Argument 
	 * Fahrstreckenart und in der Dokumentation ersichtlich.
	 */
	public void setFahrstreckenArtFeinerschliessung(FahrstreckenArt fahrstreckenArtFeinerschliessung) {
		this.fahrstreckenArtFeinerschliessung = fahrstreckenArtFeinerschliessung;
		
		switch(fahrstreckenArtFeinerschliessung) {
		case LastfahrtVorwaertsBergauf:
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz = 75;
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz  =  0;
			fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz       = 75;
			fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz        =  0;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz       =  0;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz        = 75;
			break;
			
		case LastfahrtVorwaertsBergab:
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz =  0;
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz  = 75;
			fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz       =  0;
			fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz        = 75;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz       = 75;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz        =  0;
			break;
			
		case LastfahrtVorwaertsEben:
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz = 10;
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz  = 10;
			fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz       = 10;
			fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz        = 10;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz       = 10;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz        = 10;
			break;
			
		case GleichmaessigVerteilt:
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz = 33;
			fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz  = 33;
			fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz       = 33;
			fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz        = 33;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz       = 33;
			fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz        = 33;
			break;

		default:
			throw new RuntimeException();
		}
	}


	public int getErschliessungsLaengeEinseitig_m() {
		return erschliessungsLaengeEinseitig_m;
	}


	public void setErschliessungsLaengeEinseitig_m(int erschliessungsLaengeEinseitig_m) {
		this.erschliessungsLaengeEinseitig_m = erschliessungsLaengeEinseitig_m;
	}


	public int getErschliessungsLaengeEinseitigAnteilStrasse_Prz() {
		return erschliessungsLaengeEinseitigAnteilStrasse_Prz;
	}


	public void setErschliessungsLaengeEinseitigAnteilStrasse_Prz(int erschliessungsLaengeEinseitigAnteilStrasse_Prz) {
		this.erschliessungsLaengeEinseitigAnteilStrasse_Prz = erschliessungsLaengeEinseitigAnteilStrasse_Prz;
	}


	public int getErschliessungsLaengeBeidseitig_m() {
		return erschliessungsLaengeBeidseitig_m;
	}


	public void setErschliessungsLaengeBeidseitig_m(int erschliessungsLaengeBeidseitig_m) {
		this.erschliessungsLaengeBeidseitig_m = erschliessungsLaengeBeidseitig_m;
	}


	public int getErschliessungsLaengeBeidseitigAnteilStrasse_Prz() {
		return erschliessungsLaengeBeidseitigAnteilStrasse_Prz;
	}


	public void setErschliessungsLaengeBeidseitigAnteilStrasse_Prz(int erschliessungsLaengeBeidseitigAnteilStrasse_Prz) {
		this.erschliessungsLaengeBeidseitigAnteilStrasse_Prz = erschliessungsLaengeBeidseitigAnteilStrasse_Prz;
	}


	public HindernisKategorie getHindernisKategorieFeinerschliessung() {
		return hindernisKategorieFeinerschliessung;
	}


	public void setHindernisKategorieFeinerschliessung(HindernisKategorie hindernisKategorieFeinerschliessung) {
		this.hindernisKategorieFeinerschliessung = hindernisKategorieFeinerschliessung;
	}


	public NeigungsKategorie getNeigungsKategorieFeinerschliessung() {
		return neigungsKategorieFeinerschliessung;
	}


	public void setNeigungsKategorieFeinerschliessung(NeigungsKategorie neigungsKategorieFeinerschliessung) {
		this.neigungsKategorieFeinerschliessung = neigungsKategorieFeinerschliessung;
	}


	public int getFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz() {
		return fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz;
	}


	public void setFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz(int fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz) {
		this.fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz = fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz;
	}


	public int getFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz() {
		return fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz;
	}


	public void setFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz(int fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz) {
		this.fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz = fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz;
	}


	public int getFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz() {
		return fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz;
	}


	public void setFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz(int fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz) {
		this.fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz = fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz;
	}


	public int getFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz() {
		return fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz;
	}


	public void setFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz(int fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz) {
		this.fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz = fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz;
	}


	public int getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz() {
		return fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz;
	}


	public void setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz(int fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz) {
		this.fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz = fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz;
	}


	public int getFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz() {
		return fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz;
	}


	public void setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz(int fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz) {
		this.fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz = fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz;
	}


	public int getFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz() {
		return fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz;
	}


	public void setFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz) {
		this.fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz = fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz;
	}


	public int getFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz() {
		return fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz;
	}


	public void setFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz) {
		this.fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz = fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz;
	}


	public int getFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz() {
		return fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz;
	}


	public void setFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz) {
		this.fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz = fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz;
	}


	public int getFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz() {
		return fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz;
	}


	public void setFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz(int fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz) {
		this.fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz = fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz;
	}


	public enum FahrstreckenArt {
		LastfahrtVorwaertsBergauf,
		LastfahrtVorwaertsBergab,
		LastfahrtVorwaertsEben,
		GleichmaessigVerteilt;
		
		@Override
		public String toString() {
			switch (this) {
			case LastfahrtVorwaertsBergauf:
				return PdfLabels.ArbeitsobjektForwarder_LastfahrtVorwaertsBergauf.toString(); 
				
			case LastfahrtVorwaertsBergab:
				return PdfLabels.ArbeitsobjektForwarder_LastfahrtVorwaertsBergab.toString(); 
				
			case LastfahrtVorwaertsEben:
				return PdfLabels.ArbeitsobjektForwarder_LastfahrtVorwaertsEben.toString(); 
				
			case GleichmaessigVerteilt:
				return PdfLabels.ArbeitsobjektForwarder_gleichmaessigVerteilt.toString(); 
			
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		//Nutzung
		list.add(PdfLabels.ArbeitsobjektForwarder_Laubholzanteil_Prz,  laubholzAnteil_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_BHD_cm, durchschnittlicherBhdAushieb_cm); 
		list.add(PdfLabels.ArbeitsobjektForwarder_Holzlaenge_m,  durchschnittlicheHolzlaenge_m); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnzahlSortimente,  durchschnittlicheAnzahlSortimente); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnzahlVerschiedeneSortimente,  anzahlVerschiedeneSortimente); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilKrumm_Prz,  anteilKrumm_Prz); 
		
		//Erschliessung
		list.add(PdfLabels.ArbeitsobjektForwarder_FahrstreckeAufStrasse,  fahrstreckeAufStrasse); 
		list.add(PdfLabels.ArbeitsobjektForwarder_Fahrstreckenart,  fahrstreckenArtStrasse); 
		list.add(PdfLabels.ArbeitsobjektForwarder_FahrstreckeAufFeinerschliessung,  fahrstreckeAufFeinerschliessung); 
		list.add(PdfLabels.ArbeitsobjektForwarder_Fahrstreckenart,  fahrstreckenArtFeinerschliessung); 
		
		//Erschliessung Details
		list.add(PdfLabels.ArbeitsobjektForwarder_ErschliessungslaengeEinseitig_m,  erschliessungsLaengeEinseitig_m); 
		list.add(PdfLabels.ArbeitsobjektForwarder_ErschliessungslaengeEinseitigAnteilStrasse_Prz,  erschliessungsLaengeEinseitigAnteilStrasse_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_ErschliessungslaengeBeidseitig_m,  erschliessungsLaengeBeidseitig_m); 
		list.add(PdfLabels.ArbeitsobjektForwarder_ErschliessungslaengeBeidseitigAnteilStrasse_Prz,  erschliessungsLaengeBeidseitigAnteilStrasse_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_HinderniskategorieFeinerschliessung,  hindernisKategorieFeinerschliessung); 
		list.add(PdfLabels.ArbeitsobjektForwarder_NeigungskategorieFeinerschliessung,  neigungsKategorieFeinerschliessung); 
		
		//Fahrstreckenanteile
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilStrasseLastfahrtAuf_Prz,  fahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilStrasseLastfahrtAb_Prz,  fahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilStrasseLeerfahrtAuf_Prz,  fahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilStrasseLeerfahrtAb_Prz,  fahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilFeinerschliessungFahrenBeimLadenAuf_Prz,  fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilFeinerschliessungFahrenBeimLadenAb_Prz,  fahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilFeinerschliessungLastfahrtAuf_Prz,  fahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilFeinerschliessungLastfahrtAb_Prz,  fahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilFeinerschliessungLeerfahrtAuf_Prz,  fahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz); 
		list.add(PdfLabels.ArbeitsobjektForwarder_AnteilFeinerschliessungLeerfahrtAb_Prz,  fahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz); 
		
		return list;
	}
	
}
