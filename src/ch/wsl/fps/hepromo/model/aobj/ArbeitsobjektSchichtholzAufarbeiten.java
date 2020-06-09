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

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektSchichtholzAufarbeiten extends ArbeitsobjektIhundSchichtholzAufarbeiten {
	
	private static final long serialVersionUID = 1L;
	
	private SchichtholzStuecklaengen stuecklaengen;
	private int anteilSpalten_Prz;
	
	
	public enum SchichtholzStuecklaengen implements Stuecklaengen {
		Laenge1m,
		Laenge2m;
		
		@Override
		public String toString() {
			switch (this) {
				case Laenge1m:
					return "1m";
					
				case Laenge2m:
					return "2m";
					
			}
			throw new RuntimeException();
		}
	}


	
	public SchichtholzStuecklaengen getStuecklaengen() {
		return stuecklaengen;
	}


	public void setStuecklaengen(SchichtholzStuecklaengen stuecklaengen) {
		this.stuecklaengen = stuecklaengen;
	}


	public double getMengeSchichtholz_m3iR() {
		return getHolzmenge_m3() * getAnteilSchichtholz_Prz() / 100.0;
	}


	public int getAnteilSpalten_Prz() {
		return anteilSpalten_Prz;
	}


	public void setAnteilSpalten_Prz(int anteilSpalten_Prz) {
		this.anteilSpalten_Prz = anteilSpalten_Prz;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Stücklängen", stuecklaengen);
		list.add("Anteil Spalten (%)", anteilSpalten_Prz);
		list.add("Menge Schichtholz (m3 i.R.)", getMengeSchichtholz_m3iR());
		
		return list;
	}
	

}
