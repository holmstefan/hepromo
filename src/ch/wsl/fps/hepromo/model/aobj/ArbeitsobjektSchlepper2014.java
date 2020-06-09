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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektSchlepper2014 extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private double mittlererStueckinhalt;
	private MittlereFahrentfernung mittlereFahrentfernung;
	
	private BeizugsdistanzArrayWithSelection beizugsdistanzArrayWithSelection = new BeizugsdistanzArrayWithSelection();

	

	public double getMittlererStueckinhalt() {
		return mittlererStueckinhalt;
	}
	

	public void setMittlererStueckinhalt(double mittlererStueckinhalt) {
		this.mittlererStueckinhalt = mittlererStueckinhalt;
	}
	

	public MittlereFahrentfernung getMittlereFahrentfernung() {
		return mittlereFahrentfernung;
	}
	

	public void setMittlereFahrentfernung(MittlereFahrentfernung mittlereFahrentfernung) {
		this.mittlereFahrentfernung = mittlereFahrentfernung;
	}
	

	public BeizugsdistanzArrayWithSelection getBeizugsdistanzArrayWithSelection() {
		return beizugsdistanzArrayWithSelection;
	}


	public void setBeizugsdistanzArrayWithSelection(BeizugsdistanzArrayWithSelection beizugsdistanzArrayWithSelection) {
		this.beizugsdistanzArrayWithSelection = beizugsdistanzArrayWithSelection;
	}




	public enum MittlereFahrentfernung {
		EntfBis300m,
		Entf301bis500m,
		Entf501bis700m,
		Entf701bis900m,
		EntfUeber900m;
		
		@Override
		public String toString(){
			switch(this) {
			case EntfBis300m:
				return PdfLabels.ArbeitsobjektSchlepper2014_EntfernungBis300m.toString(); 
				
			case Entf301bis500m:
				return PdfLabels.ArbeitsobjektSchlepper2014_Entfernung301bis500m.toString(); 
				
			case Entf501bis700m:
				return PdfLabels.ArbeitsobjektSchlepper2014_Entfernung501bis700m.toString(); 
				
			case Entf701bis900m:
				return PdfLabels.ArbeitsobjektSchlepper2014_Entfernung701bis900m.toString(); 
				
			case EntfUeber900m:
				return PdfLabels.ArbeitsobjektSchlepper2014_EntfernungUeber900m.toString(); 
				
			default:
				throw new RuntimeException(); //this should never happen
			}
		}
		
		
		public int value() {
			switch(this) {
			case EntfBis300m:
				return 1;
				
			case Entf301bis500m:
				return 2;
				
			case Entf501bis700m:
				return 3;
				
			case Entf701bis900m:
				return 4;
				
			case EntfUeber900m:
				return 5;
				
			default:
				throw new RuntimeException(); //this should never happen
			}
		}
		
		
		public static MittlereFahrentfernung parseDouble(double value) {
			
			if (value <= 300) {
				return EntfBis300m;
			}
			else if (value <= 500) {
				return Entf301bis500m;
			}
			else if (value <= 700) {
				return Entf501bis700m;
			}
			else if (value <= 900) {
				return Entf701bis900m;
			}
			else {
				return EntfUeber900m;
			}
		}
		
		
	}
	
	
	
	
	
	
	public static class BeizugsdistanzArrayWithSelection implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public final Beizugsdistanz[] allValues = Beizugsdistanz.getNewArrayWithAllValues();
		private Beizugsdistanz selection = allValues[ Beizugsdistanz.Bis20m ];

		public Beizugsdistanz getSelection() {
			return selection;
		}

		public void setSelection(Beizugsdistanz selection) {
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
	public static class Beizugsdistanz implements Serializable {
		private static final long serialVersionUID = 1L;

		private final DecimalFormat df = new DecimalFormat("+#.## %;-#.## %"); //$NON-NLS-1$
		
		//int-Werte entsprechen der Position im Array
		private static final int Bis20m = 0;
		private static final int Von20bis40m = 1;
		private static final int Ueber40m = 2;
		private static final int Benutzerdefiniert = 3;
		
		private int name;
		private boolean wasChanged = false;
		private double wert = 0;
		
		public static Beizugsdistanz[] getNewArrayWithAllValues() {
			return new Beizugsdistanz[]{
					new Beizugsdistanz(Beizugsdistanz.Bis20m, 		 	 0.0 ),
					new Beizugsdistanz(Beizugsdistanz.Von20bis40m, 		-0.1 ),
					new Beizugsdistanz(Beizugsdistanz.Ueber40m, 		-0.2 ),
					new Beizugsdistanz(Beizugsdistanz.Benutzerdefiniert, 0.0 )};
		}
		
		private Beizugsdistanz(int name, double wert) {
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
			if ( ! isBenutzerdefiniert() || wert < -0.50001 || wert > 0.00001) {
				throw new RuntimeException();
			}
			this.wasChanged = true;
			this.wert = wert;
		}
		

		@Override
		public String toString() {
			switch(this.name) {
			case Bis20m:
				return PdfLabels.ArbeitsobjektSchlepper2014_BeizugBis20m + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Von20bis40m:
				return PdfLabels.ArbeitsobjektSchlepper2014_Beizug20bis40m + " (" + df.format(getWert()) + ")".toString(); //$NON-NLS-1$ //$NON-NLS-2$ 

			case Ueber40m:
				return PdfLabels.ArbeitsobjektSchlepper2014_BeizugUeber40m + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 

			case Benutzerdefiniert:
				if (wasChanged) {
					return PdfLabels.ArbeitsobjektSchlepper2014_benutzerdefiniert + " (" + df.format(getWert()) + ")".toString();  //$NON-NLS-1$//$NON-NLS-2$ 
				}
				else {
					return PdfLabels.ArbeitsobjektSchlepper2014_benutzerdefiniert.toString();
				}

			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add(PdfLabels.ArbeitsobjektSchlepper2014_MittlererStueckinhalt, mittlererStueckinhalt); 
		list.add(PdfLabels.ArbeitsobjektSchlepper2014_MittlereFahrentfernung, mittlereFahrentfernung); 
		list.add(PdfLabels.ArbeitsobjektSchlepper2014_ZuschlagBeizugsdistanz, beizugsdistanzArrayWithSelection.getSelection().toString()); 
		
		return list;
	}
	
	
	
	public double getGesamtfaktorAllerZuschlaege() {
		double result = 1 + beizugsdistanzArrayWithSelection.getSelection().getWert();
		
		return result;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}
}
