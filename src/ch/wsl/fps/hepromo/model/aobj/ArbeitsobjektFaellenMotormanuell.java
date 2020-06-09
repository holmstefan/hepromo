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
public class ArbeitsobjektFaellenMotormanuell extends ArbeitsobjektMotormanuell  {
	
	private static final long serialVersionUID = 1L;

	private double anteilFaellenHandseilzug_Prz; 



	public double getAnteilFaellenHandseilzug_Prz() {
		return anteilFaellenHandseilzug_Prz;
	}


	public void setAnteilFaellenHandseilzug_Prz(double anteilFaellenHandseilzug_Prz) {
		this.anteilFaellenHandseilzug_Prz = anteilFaellenHandseilzug_Prz;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add("Anteil Fällen mit Handseilzug (%)", anteilFaellenHandseilzug_Prz);
		
		return list;
	}
}
