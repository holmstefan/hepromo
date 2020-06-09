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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden.AnteilShUndIh;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektIhundSchichtholzAufarbeiten extends ArbeitsobjektMotormanuell {
	
	private static final long serialVersionUID = 1L;
	
	private int anteilIndustrieholz_Prz;
	private int anteilSchichtholz_Prz;


	public int getAnteilIndustrieholz_Prz() {
		return anteilIndustrieholz_Prz;
	}

	public void setAnteilIndustrieholz_Prz(int anteilIndustrieholz_Prz) {
		this.anteilIndustrieholz_Prz = anteilIndustrieholz_Prz;
	}

	public int getAnteilSchichtholz_Prz() {
		return anteilSchichtholz_Prz;
	}

	public void setAnteilSchichtholz_Prz(int anteilSchichtholz_Prz) {
		this.anteilSchichtholz_Prz = anteilSchichtholz_Prz;
	}


	public AnteilShUndIh getAnteilShUndIh() {
		AnteilShUndIh anteilShUndIh = AnteilShUndIh.getValue(anteilIndustrieholz_Prz + anteilSchichtholz_Prz);
		return anteilShUndIh;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Anteil Schichtholz (%)", anteilSchichtholz_Prz);
		list.add("Anteil Industrieholz lang (%)", anteilIndustrieholz_Prz);
		
		return list;
	}

}
