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
public class ArbeitsobjektHelikopterFliegen extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private Holztyp holztyp;
	private int horizontalDistanz_m;
	private int vertikalDistanz_m;
	
	
	public Holztyp getHolztyp() {
		return holztyp;
	}


	public void setHolztyp(Holztyp holztyp) {
		this.holztyp = holztyp;
	}


	public int getHorizontalDistanz_m() {
		return horizontalDistanz_m;
	}


	public void setHorizontalDistanz_m(int horizontalDistanz_m) {
		this.horizontalDistanz_m = horizontalDistanz_m;
	}


	public int getVertikalDistanz_m() {
		return vertikalDistanz_m;
	}


	public void setVertikalDistanz_m(int vertikalDistanz_m) {
		this.vertikalDistanz_m = vertikalDistanz_m;
	}


	public enum Holztyp {
		Laubholz_frisch,
		Laubholz_angetrocknet,
		Nadelholz_frisch,
		Nadelholz_angetrocknet;
		
		@Override
		public String toString() {
			switch (this) {
			case Laubholz_frisch:
				return PdfLabels.ArbeitsobjektHelikopterFliegen_LaubholzFrisch.toString(); 
				
			case Laubholz_angetrocknet:
				return PdfLabels.ArbeitsobjektHelikopterFliegen_LaubholzAngetrocknet.toString(); 
				
			case Nadelholz_frisch:
				return PdfLabels.ArbeitsobjektHelikopterFliegen_NadelholzFrisch.toString(); 
				
			case Nadelholz_angetrocknet:
				return PdfLabels.ArbeitsobjektHelikopterFliegen_NadelholzAngetrocknet.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
		
		public boolean isNadelholz() {
			return this == Nadelholz_frisch || this == Nadelholz_angetrocknet;
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Holztyp", holztyp);
		list.add("Horizontaldistanz (m)", horizontalDistanz_m);
		list.add("Vertikaldistanz (m)", vertikalDistanz_m);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}

}
