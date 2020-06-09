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
public class ArbeitsobjektIhLangAufarbeiten extends ArbeitsobjektIhundSchichtholzAufarbeiten {
	
	private static final long serialVersionUID = 1L;
	
	private IndustrieholzStuecklaengen stuecklaengen;
	
	
	public enum IndustrieholzStuecklaengen implements Stuecklaengen {
		Bis7m,
		Ueber7m;
		
		@Override
		public String toString() {
			switch (this) {
				case Bis7m:
					return "bis 7m"; 
					
				case Ueber7m:
					return "über 7m"; 
					
			}
			throw new RuntimeException();
		}
	}


	
	public IndustrieholzStuecklaengen getStuecklaengen() {
		return stuecklaengen;
	}


	public void setStuecklaengen(IndustrieholzStuecklaengen stuecklaengen) {
		this.stuecklaengen = stuecklaengen;
	}


	public double getMengeIndustrieholzLang_m3iR() {
		return getHolzmenge_m3() * getAnteilIndustrieholz_Prz() / 100.0;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Stücklängen", stuecklaengen);
		list.add("Menge Industrieholz lang (m3 i.R.)", getMengeIndustrieholzLang_m3iR());
		
		return list;
	}

}
