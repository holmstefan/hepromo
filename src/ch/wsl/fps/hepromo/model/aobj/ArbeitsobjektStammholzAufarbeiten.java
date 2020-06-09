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
public class ArbeitsobjektStammholzAufarbeiten extends ArbeitsobjektMotormanuell {
	
	private static final long serialVersionUID = 1L;
	
	private StammholzStuecklaengen stuecklaengen;
	private boolean kantenBrechen;
	
	
	public enum StammholzStuecklaengen implements Stuecklaengen {
		WenigerAls4m,
		Von4Bis6m,
		Von6Bis10m,
		MehrAls10m;
		
		@Override
		public String toString() {
			switch (this) {
				case WenigerAls4m:
					return "< 4m";
					
				case Von4Bis6m:
					return "4 - 6m";
					
				case Von6Bis10m:
					return "6 - 10m";
					
				case MehrAls10m:
					return "> 10m";
					
			}
			throw new RuntimeException();
		}
	}


	
	public StammholzStuecklaengen getStuecklaengen() {
		return stuecklaengen;
	}


	public void setStuecklaengen(StammholzStuecklaengen stuecklaengen) {
		this.stuecklaengen = stuecklaengen;
	}


	public boolean isKantenBrechen() {
		return kantenBrechen;
	}


	public void setKantenBrechen(boolean kantenBrechen) {
		this.kantenBrechen = kantenBrechen;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Stücklängen", stuecklaengen);
		list.add("Kanten brechen", kantenBrechen);
		
		return list;
	}

}
