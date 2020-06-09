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
public class ArbeitsobjektEntrinden extends ArbeitsobjektMotormanuell {
	
	private static final long serialVersionUID = 1L;
	

	private AnteilShUndIh anteilShUndIhLang;

	
	public enum AnteilShUndIh{ 
		Anteil_0bis10,
		Anteil_11bis20,
		Anteil_21bis35,
		Anteil_36bis60,
		Anteil_61bis100;
		
		@Override
		public String toString() {
			switch (this) {
				case Anteil_0bis10:
					return "0 - 10%";
					
				case Anteil_11bis20:
					return "11 - 20%";
					
				case Anteil_21bis35:
					return "21 - 35%";
					
				case Anteil_36bis60:
					return "36 - 60%";
					
				case Anteil_61bis100:
					return "61 - 100%";					
			}
			throw new RuntimeException();
		}
		
//		public static AnteilShUndIh getValue(double value) {
//			if (value < 0) {
//				return null;
//			}
//			else if (value <= 0.1) {
//				return Anteil_0bis10;
//			}
//			else if (value <= 0.2) {
//				return Anteil_11bis20;
//			}
//			else if (value <= 0.35) {
//				return Anteil_21bis35;
//			}
//			else if (value <= 0.60) {
//				return Anteil_36bis60;
//			}
//			else if (value <= 1.0) {
//				return Anteil_61bis100;
//			}
//			else {
//				return null;
//			}
//		}
		
		public static AnteilShUndIh getValue(int value) {
			if (value < 0) {
				return null;
			}
			else if (value <= 10) {
				return Anteil_0bis10;
			}
			else if (value <= 20) {
				return Anteil_11bis20;
			}
			else if (value <= 35) {
				return Anteil_21bis35;
			}
			else if (value <= 60) {
				return Anteil_36bis60;
			}
			else if (value <= 100) {
				return Anteil_61bis100;
			}
			else {
				return null;
			}
		}
		
	}



	public AnteilShUndIh getAnteilShUndIhLang() {
		return anteilShUndIhLang;
	}


	public void setAnteilShUndIhLang(AnteilShUndIh anteilShUndIhLang) {
		this.anteilShUndIhLang = anteilShUndIhLang;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add("Anteil Schichtholz und Industrieholz lang", anteilShUndIhLang);
		
		return list;
	}
}
