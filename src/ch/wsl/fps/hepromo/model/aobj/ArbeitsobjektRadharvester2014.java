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

import java.io.Serializable;
import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektRadharvester2014 extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private int bhd_cm;
	
	private FoermigkeitArrayWithSelection foermigkeitArrayWithSelection = new FoermigkeitArrayWithSelection();
	private LaubholzAnteilArrayWithSelection laubholzAnteilArrayWithSelection = new LaubholzAnteilArrayWithSelection();
	private HangneigungArrayWithSelection hangneigungArrayWithSelection = new HangneigungArrayWithSelection();
	private LiegendesHolzArrayWithSelection liegendesHolzArrayWithSelection = new LiegendesHolzArrayWithSelection();

	private boolean einsatzThw;
	private int anzahlRueckegassen;
	
	
	public int getBhd_cm() {
		return bhd_cm;
	}


	public void setBhd_cm(int bhd_cm) {
		this.bhd_cm = bhd_cm;
	}


	public FoermigkeitArrayWithSelection getFoermigkeitArrayWithSelection() {
		return foermigkeitArrayWithSelection;
	}


	public void setFoermigkeitArrayWithSelection(FoermigkeitArrayWithSelection foermigkeitArrayWithSelection) {
		this.foermigkeitArrayWithSelection = foermigkeitArrayWithSelection;
	}
	
	
	public LaubholzAnteilArrayWithSelection getLaubholzAnteilArrayWithSelection() {
		return laubholzAnteilArrayWithSelection;
	}


	public void setLaubholzAnteilArrayWithSelection(LaubholzAnteilArrayWithSelection laubholzAnteilArrayWithSelection) {
		this.laubholzAnteilArrayWithSelection = laubholzAnteilArrayWithSelection;
	}


	public LiegendesHolzArrayWithSelection getLiegendesHolzArrayWithSelection() {
		return liegendesHolzArrayWithSelection;
	}


	public void setLiegendesHolzArrayWithSelection(LiegendesHolzArrayWithSelection liegendesHolzArrayWithSelection) {
		this.liegendesHolzArrayWithSelection = liegendesHolzArrayWithSelection;
	}


	public HangneigungArrayWithSelection getHangneigungArrayWithSelection() {
		return hangneigungArrayWithSelection;
	}


	public void setHangneigungArrayWithSelection(HangneigungArrayWithSelection hangneigungArrayWithSelection) {
		this.hangneigungArrayWithSelection = hangneigungArrayWithSelection;
	}

	public boolean isEinsatzThw() {
		return einsatzThw;
	}

	public void setEinsatzThw(boolean einsatzThw) {
		this.einsatzThw = einsatzThw;
	}

	public int getAnzahlRueckegassen() {
		return anzahlRueckegassen;
	}

	public void setAnzahlRueckegassen(int anzahlRueckegassen) {
		this.anzahlRueckegassen = anzahlRueckegassen;
	}
	
	
	
	public static class FoermigkeitArrayWithSelection implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public final Foermigkeit[] allValues = Foermigkeit.getNewArrayWithAllValues();
		private Foermigkeit selection = allValues[ Foermigkeit.Normalholzig ];

		public Foermigkeit getSelection() {
			return selection;
		}

		public void setSelection(Foermigkeit selection) {
			if ( 
					allValues[0] != selection &&
					allValues[1] != selection &&
					allValues[2] != selection &&
					allValues[3] != selection ) 
			{
				throw new RuntimeException();
			}
			this.selection = selection;
		}
	}
	
	
	
	/*  Diese enum-ähnliche Klasse ist nötig, da es bei 
	 *  einer echten enum immer nur eine Instanz gibt,
	 *  und somit auch der benutzerdefinierte Wert 
	 *  programmweit der selbe wäre.
	 */
	public static class Foermigkeit implements Serializable {
		private static final long serialVersionUID = 1L;

		private final DecimalFormat df = new DecimalFormat("+#.## %;-#.## %"); //$NON-NLS-1$
		
		//int-Werte entsprechen der Position im Array
		private static final int Vollholzig = 0;
		private static final int Normalholzig = 1;
		private static final int Abholzig = 2;
		private static final int Benutzerdefiniert = 3;
		
		private int name;
		private boolean wasChanged = false;
		private double wert = 0;
		
		public static Foermigkeit[] getNewArrayWithAllValues() {
			return new Foermigkeit[]{
					new Foermigkeit(Foermigkeit.Vollholzig, 		 0.05 ),
					new Foermigkeit(Foermigkeit.Normalholzig, 		 0.0  ),
					new Foermigkeit(Foermigkeit.Abholzig, 			-0.05 ),
					new Foermigkeit(Foermigkeit.Benutzerdefiniert,	 0.0  )};
		}
		
		private Foermigkeit(int name, double wert) {
			this.name = name;
			this.wert = wert;
		}
		
		public boolean isBenutzerdefiniert() {
			return name == Benutzerdefiniert;
		}
		
		public double getWert() {
			return wert;
		}
		
		public void setWert(double wert) {
			if ( ! isBenutzerdefiniert() || Math.abs(wert) > 0.1001) {
				throw new RuntimeException();
			}
			this.wasChanged = true;
			this.wert = wert;
		}
		

		@Override
		public String toString() {
			switch(this.name) {
			case Vollholzig:
				return PdfLabels.ArbeitsobjektRadharvester2014_vollholzig + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Normalholzig:
				return PdfLabels.ArbeitsobjektRadharvester2014_normalholzig + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Abholzig:
				return PdfLabels.ArbeitsobjektRadharvester2014_abholzig + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Benutzerdefiniert:
				if (wasChanged) {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 
				}
				else {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert.toString();  //$NON-NLS-1$//$NON-NLS-2$ 
				}

			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	
	
	public static class LaubholzAnteilArrayWithSelection implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public final LaubholzAnteil[] allValues = LaubholzAnteil.getNewArrayWithAllValues();
		private LaubholzAnteil selection = allValues[ LaubholzAnteil.Null ];

		public LaubholzAnteil getSelection() {
			return selection;
		}

		public void setSelection(LaubholzAnteil selection) {
			if ( 
					allValues[0] != selection &&
					allValues[1] != selection &&
					allValues[2] != selection &&
					allValues[3] != selection &&
					allValues[4] != selection &&
					allValues[5] != selection ) 
			{
				throw new RuntimeException();
			}
			this.selection = selection;
		}
	}
	
	
	
	/*  Diese enum-ähnliche Klasse ist nötig, da es bei 
	 *  einer echten enum immer nur eine Instanz gibt,
	 *  und somit auch der benutzerdefinierte Wert 
	 *  programmweit der selbe wäre.
	 */
	public static class LaubholzAnteil implements Serializable {
		private static final long serialVersionUID = 1L;

		private final DecimalFormat df = new DecimalFormat("+#.## %;-#.## %"); //$NON-NLS-1$
		
		//int-Werte entsprechen der Position im Array
		private static final int Null = 0;
		private static final int Bis25 = 1;
		private static final int Bis50 = 2;
		private static final int Bis75 = 3;
		private static final int Bis100 = 4;
		private static final int Benutzerdefiniert = 5;		
		
		private int name;
		private boolean wasChanged = false;
		private double wert = 0;
		
		public static LaubholzAnteil[] getNewArrayWithAllValues() {
			return new LaubholzAnteil[]{
					new LaubholzAnteil(LaubholzAnteil.Null, 		 	 0.00  ),
					new LaubholzAnteil(LaubholzAnteil.Bis25, 		 	-0.025 ),
					new LaubholzAnteil(LaubholzAnteil.Bis50, 			-0.05  ),
					new LaubholzAnteil(LaubholzAnteil.Bis75,	 		-0.075 ),
					new LaubholzAnteil(LaubholzAnteil.Bis100, 			-0.10  ),
					new LaubholzAnteil(LaubholzAnteil.Benutzerdefiniert, 0.0   )};
		}
		
		private LaubholzAnteil(int name, double wert) {
			this.name = name;
			this.wert = wert;
		}
		
		public boolean isBenutzerdefiniert() {
			return name == Benutzerdefiniert;
		}
		
		public double getWert() {
			return wert;
		}
		
		public void setWert(double wert) {
			if ( ! isBenutzerdefiniert() || Math.abs(wert) > 0.15001 ) {
				throw new RuntimeException();
			}
			this.wasChanged = true;
			this.wert = wert;
		}
		

		@Override
		public String toString() {
			switch(this.name) {
			case Null:
				return PdfLabels.ArbeitsobjektRadharvester2014_0Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis25:
				return PdfLabels.ArbeitsobjektRadharvester2014_bis25Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis50:
				return PdfLabels.ArbeitsobjektRadharvester2014_bis50Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis75:
				return PdfLabels.ArbeitsobjektRadharvester2014_bis75Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis100:
				return PdfLabels.ArbeitsobjektRadharvester2014_bis100Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Benutzerdefiniert:
				if (wasChanged) {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$
				}
				else {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert.toString();
				} 

			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	
	
	public static class LiegendesHolzArrayWithSelection implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public final LiegendesHolz[] allValues = LiegendesHolz.getNewArrayWithAllValues();
		private LiegendesHolz selection = allValues[ LiegendesHolz.Keines ];

		public LiegendesHolz getSelection() {
			return selection;
		}

		public void setSelection(LiegendesHolz selection) {
			if ( 
					allValues[0] != selection &&
					allValues[1] != selection &&
					allValues[2] != selection &&
					allValues[3] != selection &&
					allValues[4] != selection &&
					allValues[5] != selection ) 
			{
				throw new RuntimeException();
			}
			this.selection = selection;
		}
	}
	
	
	
	/*  Diese enum-ähnliche Klasse ist nötig, da es bei 
	 *  einer echten enum immer nur eine Instanz gibt,
	 *  und somit auch der benutzerdefinierte Wert 
	 *  programmweit der selbe wäre.
	 */
	public static class LiegendesHolz implements Serializable {
		private static final long serialVersionUID = 1L;

		private final DecimalFormat df = new DecimalFormat("+#.## %;-#.## %"); //$NON-NLS-1$
		
		//int-Werte entsprechen der Position im Array
		private static final int Keines = 0;
		private static final int VorgeruecktBis25 = 1;
		private static final int VorgeruecktBis50 = 2;
		private static final int ZugefaelttBis25 = 3;
		private static final int ZugefaelltBis50 = 4;
		private static final int Benutzerdefiniert = 5;
		
		private int name;
		private boolean wasChanged = false;
		private double wert = 0;
		
		public static LiegendesHolz[] getNewArrayWithAllValues() {
			return new LiegendesHolz[]{
					new LiegendesHolz(LiegendesHolz.Keines, 		 	0.00   ),
					new LiegendesHolz(LiegendesHolz.VorgeruecktBis25, 	0.125  ),
					new LiegendesHolz(LiegendesHolz.VorgeruecktBis50,   0.25   ),
					new LiegendesHolz(LiegendesHolz.ZugefaelttBis25,   -0.0625 ),
					new LiegendesHolz(LiegendesHolz.ZugefaelltBis50,   -0.125  ),
					new LiegendesHolz(LiegendesHolz.Benutzerdefiniert,	0.0    )};
		}
		
		private LiegendesHolz(int name, double wert) {
			this.name = name;
			this.wert = wert;
		}
		
		public boolean isBenutzerdefiniert() {
			return name == Benutzerdefiniert;
		}
		
		public double getWert() {
			return wert;
		}
		
		public void setWert(double wert) {
			if ( ! isBenutzerdefiniert() || Math.abs(wert) > 0.4001 ) {
				throw new RuntimeException();
			}
			this.wasChanged = true;
			this.wert = wert;
		}
		

		@Override
		public String toString() {
			switch(this.name) {
			case Keines:
				return PdfLabels.ArbeitsobjektRadharvester2014_Keines + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case VorgeruecktBis25:
				return PdfLabels.ArbeitsobjektRadharvester2014_VorgeruecktesHolzBis25Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case VorgeruecktBis50:
				return PdfLabels.ArbeitsobjektRadharvester2014_VorgeruecktesHolzBis50Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case ZugefaelttBis25:
				return PdfLabels.ArbeitsobjektRadharvester2014_ZugefaelltesHolzBis25Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case ZugefaelltBis50:
				return PdfLabels.ArbeitsobjektRadharvester2014_ZugefaelltesHolzBis50Prz + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Benutzerdefiniert:
				if (wasChanged) {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 
				}
				else {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert.toString();
				}

			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	
	
	public static class HangneigungArrayWithSelection implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public final Hangneigung[] allValues = Hangneigung.getNewArrayWithAllValues();
		private Hangneigung selection = allValues[ Hangneigung.Bis30 ];

		public Hangneigung getSelection() {
			return selection;
		}

		public void setSelection(Hangneigung selection) {
			if ( 
					allValues[0] != selection &&
					allValues[1] != selection &&
					allValues[2] != selection &&
					allValues[3] != selection &&
					allValues[4] != selection ) 
			{
				throw new RuntimeException();
			}
			this.selection = selection;
		}
	}
	
	
	
	/*  Diese enum-ähnliche Klasse ist nötig, da es bei 
	 *  einer echten enum immer nur eine Instanz gibt,
	 *  und somit auch der benutzerdefinierte Wert 
	 *  programmweit der selbe wäre.
	 */
	public static class Hangneigung implements Serializable {
		private static final long serialVersionUID = 1L;

		private final DecimalFormat df = new DecimalFormat("+#.## %;-#.## %"); //$NON-NLS-1$
		
		//int-Werte entsprechen der Position im Array
		private static final int Bis30 = 0;
		private static final int Bis40 = 1;
		private static final int Bis50 = 2;
		private static final int Bis60 = 3;
		private static final int Benutzerdefiniert = 4;		
		
		private int name;
		private boolean wasChanged = false;
		private double wert = 0;
		
		public static Hangneigung[] getNewArrayWithAllValues() {
			return new Hangneigung[]{
					new Hangneigung(Hangneigung.Bis30, 		 	   0.00 ),
					new Hangneigung(Hangneigung.Bis40, 		 	  -0.05 ),
					new Hangneigung(Hangneigung.Bis50, 			  -0.10 ),
					new Hangneigung(Hangneigung.Bis60,	 		  -0.15 ),
					new Hangneigung(Hangneigung.Benutzerdefiniert, 0.0 )};
		}
		
		private Hangneigung(int name, double wert) {
			this.name = name;
			this.wert = wert;
		}
		
		public boolean isBenutzerdefiniert() {
			return name == Benutzerdefiniert;
		}
		
		public double getWert() {
			return wert;
		}
		
		public void setWert(double wert) {
			if ( ! isBenutzerdefiniert() || Math.abs(wert) > 0.20001 ) {
				throw new RuntimeException();
			}
			this.wasChanged = true;
			this.wert = wert;
		}
		

		@Override
		public String toString() {
			switch(this.name) {
			case Bis30:
				return "< 30% (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis40:
				return "30% - 39% (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis50:
				return "40% - 49% (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Bis60:
				return "50% - 60% (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Benutzerdefiniert:
				if (wasChanged) {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$
				}
				else {
					return PdfLabels.ArbeitsobjektRadharvester2014_benutzerdefiniert.toString();
				} 

			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add(PdfLabels.ArbeitsobjektRadharvester2014_BHD_cm, bhd_cm); 
		list.add(PdfLabels.ArbeitsobjektRadharvester2014_ZuschlagFoermigkeit, foermigkeitArrayWithSelection.getSelection().toString() ); 
		list.add(PdfLabels.ArbeitsobjektRadharvester2014_ZuschlagAnteilLaubholz, laubholzAnteilArrayWithSelection.getSelection().toString()); 
		list.add(PdfLabels.ArbeitsobjektRadharvester2014_ZuschlagLiegendesHolz, liegendesHolzArrayWithSelection.getSelection().toString()); 
		list.add(PdfLabels.ArbeitsobjektRadharvester2014_ZuschlagHangneigung, 	hangneigungArrayWithSelection.getSelection().toString());
		list.add(ModelStrings.getString("Thw.EinsatzTraktionshilfswinde"),  	einsatzThw); //$NON-NLS-1$
		if (einsatzThw) {
			list.add(ModelStrings.getString("Thw.AnzahlRueckegassen"),  		anzahlRueckegassen); //$NON-NLS-1$
		}
		
		return list;
	}
	
	
	
	public double getGesamtfaktorAllerZuschlaege() {
		double result = 
				1 +
				foermigkeitArrayWithSelection.getSelection().getWert() +
				laubholzAnteilArrayWithSelection.getSelection().getWert() +
				liegendesHolzArrayWithSelection.getSelection().getWert() +
				hangneigungArrayWithSelection.getSelection().getWert();
		
		return result;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}

}
