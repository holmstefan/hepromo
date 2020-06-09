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
public class ArbeitsobjektMobilseilkranSeilen extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;

	private double mittleresStueckvolumen_m3;
	private int mittlereFahrdistanz_m;
	private int mittlereDistanzSeitlicherZuzug_m;
	private SchwierigkeitSeitlicherZuzug schwierigkeitSeitlicherZuzug;
	
	
	public enum SchwierigkeitSeitlicherZuzug {
		Einfach,
		Erschwert;
		
		@Override
		public String toString() {
			switch (this) {
			case Einfach:
				return PdfLabels.ArbeitsobjektMobilseilkranSeilen_ZuzugEinfach.toString(); 

			case Erschwert:
				return PdfLabels.ArbeitsobjektMobilseilkranSeilen_ZuzugErschwert.toString(); 

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

	public SchwierigkeitSeitlicherZuzug getSchwierigkeitSeitlicherZuzug() {
		return schwierigkeitSeitlicherZuzug;
	}

	public void setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug schwierigkeitSeitlicherZuzug) {
		this.schwierigkeitSeitlicherZuzug = schwierigkeitSeitlicherZuzug;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Mittleres Stückvolumen (m3)", mittleresStueckvolumen_m3);
		list.add("Mittlere Fahrdistanz (m)", mittlereFahrdistanz_m);
		list.add("Mittlere Distanz seitlicher Zuzug (m)", mittlereDistanzSeitlicherZuzug_m);
		list.add("Schwierigkeit seitlicher Zuzug", schwierigkeitSeitlicherZuzug);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}
	
}
