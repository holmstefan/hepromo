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

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektKonventionellerSeilkranSeilen extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;

	private double mittleresStueckvolumen_m3;
//	private int holzmengeAnSeillinie_m3;
	private double stuecklaenge_m;
	private Eingriffsart eingriffsart;
	private HolzSeilOrt holzSeilOrt;

	private int hangneigung_Prz;
	private Hindernisse hindernisse;
	
	private Fahrtrichtung fahrtrichtung;
	private int mittlereFahrdistanz_m;
	private int mittlereDistanzSeitlicherZuzug_m;
	private int tragseilHoeheLagerplatz_m;
	private int tragseilHoeheBestand_m;
	
	
	public enum Fahrtrichtung {
		Bergab,
		Bergauf;
		
		@Override
		public String toString() {
			switch(this) {
			case Bergab:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_FahrtrichtungBergab.toString(); 
				
			case Bergauf:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_FahrtrichtungBergauf.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	public enum Hindernisse {
		Normal,
		Erschwert,
		Extrem;
		
		@Override
		public String toString() {
			switch(this) {
			case Normal:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_HindernisKatNormal.toString(); 
				
			case Extrem:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_HindernisKatExtrem.toString(); 
				
			case Erschwert:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_HindernisKatErschwert.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	public enum Eingriffsart {
		Durchforstung,
		Lichtung,
		Raeumung;
		
		@Override
		public String toString() {
			switch(this) {
			case Durchforstung:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_EingriffsartDurchforstung.toString(); 
				
			case Lichtung:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_EingriffsartLichtung.toString(); 
				
			case Raeumung:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_EingriffsartRaeumung.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	public enum HolzSeilOrt {
		AusSchlagflaeche,
		AbHaufen;
		
		@Override
		public String toString() {
			switch(this) {
			case AusSchlagflaeche:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_SeilenAusSchlagflaeche.toString(); 
				
			case AbHaufen:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranSeilen_SeilenAbHaufen.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}


	public double getMittleresStueckvolumen_m3() {
		return mittleresStueckvolumen_m3;
	}


	public void setMittleresStueckvolumen_m3(double mittleresStueckvolumen_m3) {
		this.mittleresStueckvolumen_m3 = mittleresStueckvolumen_m3;
	}


	public double getStuecklaenge_m() {
		return stuecklaenge_m;
	}


	public void setStuecklaenge_m(double stuecklaenge_m) {
		this.stuecklaenge_m = stuecklaenge_m;
	}


	public Eingriffsart getEingriffsart() {
		return eingriffsart;
	}


	public void setEingriffsart(Eingriffsart eingriffsart) {
		this.eingriffsart = eingriffsart;
	}


	public HolzSeilOrt getHolzSeilOrt() {
		return holzSeilOrt;
	}


	public void setHolzSeilOrt(HolzSeilOrt holzSeilOrt) {
		this.holzSeilOrt = holzSeilOrt;
	}


	public int getHangneigung_Prz() {
		return hangneigung_Prz;
	}


	public void setHangneigung_Prz(int hangneigung_Prz) {
		this.hangneigung_Prz = hangneigung_Prz;
	}


	public Hindernisse getHindernisse() {
		return hindernisse;
	}


	public void setHindernisse(Hindernisse hindernisse) {
		this.hindernisse = hindernisse;
	}


	public Fahrtrichtung getFahrtrichtung() {
		return fahrtrichtung;
	}


	public void setFahrtrichtung(Fahrtrichtung fahrtrichtung) {
		this.fahrtrichtung = fahrtrichtung;
	}


	public int getMittlereFahrdistanz_m() {
		return mittlereFahrdistanz_m;
	}


	public void setMittlereFahrdistanz_m(int mittlereFahrdistanz_m) {
		this.mittlereFahrdistanz_m = mittlereFahrdistanz_m;
	}


	public int getMittlereDistanzSeitlicherZuzug_m() {
		return mittlereDistanzSeitlicherZuzug_m;
	}


	public void setMittlereDistanzSeitlicherZuzug_m(int mittlereDistanzSeitlicherZuzug_m) {
		this.mittlereDistanzSeitlicherZuzug_m = mittlereDistanzSeitlicherZuzug_m;
	}


	public int getTragseilHoeheLagerplatz_m() {
		return tragseilHoeheLagerplatz_m;
	}


	public void setTragseilHoeheLagerplatz_m(int tragseilHoeheLagerplatz_m) {
		this.tragseilHoeheLagerplatz_m = tragseilHoeheLagerplatz_m;
	}


	public int getTragseilHoeheBestand_m() {
		return tragseilHoeheBestand_m;
	}


	public void setTragseilHoeheBestand_m(int tragseilHoeheBestand_m) {
		this.tragseilHoeheBestand_m = tragseilHoeheBestand_m;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Mittleres Stückvolumen (m3)", mittleresStueckvolumen_m3);
		list.add("Stücklängen (m)", stuecklaenge_m);
		list.add("Eingriffsort", eingriffsart);
		list.add("Holzseilort", holzSeilOrt);
		list.add("Hangneigung (%)", hangneigung_Prz);
		list.add("Hindernisse", hindernisse);
		
		list.add("Fahrtrichtung", fahrtrichtung);
		list.add("Mittlere Fahrdistanz (m)", mittlereFahrdistanz_m);
		list.add("Mittlere Distanz seitlicher Zuzug (m)", mittlereDistanzSeitlicherZuzug_m);
		list.add("Tragseilhöhe Lagerplatz (m)", tragseilHoeheLagerplatz_m);
		list.add("Tragseilhöhe Bestand (m)", tragseilHoeheBestand_m);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}
}
