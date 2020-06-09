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
package ch.wsl.fps.hepromo.model.asys;

import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemMobilerHacker extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private HackerTyp hackerTyp;
	private double kippContainerVolumen_m3;
	
	

	public HackerTyp getHackerTyp() {
		return hackerTyp;
	}

	public void setHackerTyp(HackerTyp hackerTyp) {
		this.hackerTyp = hackerTyp;
	}
	

	public double getKippContainerVolumen_m3() {
		return kippContainerVolumen_m3;
	}


	public void setKippContainerVolumen_m3(double kippContainerVolumen_m3) {
		this.kippContainerVolumen_m3 = kippContainerVolumen_m3;
	}




	public enum HackerTyp {
		AufForwarder,
		AufLastwagen;
		
		@Override
		public String toString() {
			switch(this) {
			case AufForwarder:
				return "auf Forwarder"; 
				
			case AufLastwagen:
				return "auf Lastwagen"; 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add("Hackertyp", hackerTyp); 
		list.add("Kippcontainervolumen (m3)", kippContainerVolumen_m3); 
		
		return list;
	}

}
