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
public class ArbeitsobjektEntastenMotormanuell extends ArbeitsobjektMotormanuell {
	
	private static final long serialVersionUID = 1L;

	private KronenLaengenKlasse kronenLaengenKlasse;
	
	public enum KronenLaengenKlasse{ 
		Klasse_0bis33,
		Klasse_33bis50,
		Klasse_51bis66,
		Klasse_67bis90,
		Klasse_91bis100;
		
		@Override
		public String toString() {
			switch (this) {
				case Klasse_0bis33:
					return "< 33%";
					
				case Klasse_33bis50:
					return "33 - 50%";
					
				case Klasse_51bis66:
					return "51 - 66%";
					
				case Klasse_67bis90:
					return "67 - 90%";
					
				case Klasse_91bis100:
					return "> 90%";					
			}
			throw new RuntimeException();
		}
		
	}


	public KronenLaengenKlasse getKronenLaengenKlasse() {
		return kronenLaengenKlasse;
	}


	public void setKronenLaengenKlasse(KronenLaengenKlasse kronenLaengenKlasse) {
		this.kronenLaengenKlasse = kronenLaengenKlasse;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add("Kronenlängenklasse", kronenLaengenKlasse);
		
		return list;
	}
}
