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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemSchlepper2014 extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private Maschinenkategorie maschinenkategorie;
	

	public Maschinenkategorie getMaschinenkategorie() {
		return maschinenkategorie;
	}
	

	public void setMaschinenkategorie(Maschinenkategorie maschinenkategorie) {
		this.maschinenkategorie = maschinenkategorie;
	}
	
	
	public enum Maschinenkategorie {
		Kranschlepper,
		Seilschlepper,
		Klemmbankschlepper;
		
		@Override
		public String toString() {
			switch(this) {
			case Klemmbankschlepper:
				return PdfLabels.ArbeitssystemSchlepper2014_Klemmbankschlepper.toString(); 
				
			case Kranschlepper:
				return PdfLabels.ArbeitssystemSchlepper2014_Kranschlepper.toString(); 
				
			case Seilschlepper:
				return PdfLabels.ArbeitssystemSchlepper2014_Seilschlepper.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.addOnTop(PdfLabels.ArbeitssystemSchlepper2014_Maschinenkategorie, maschinenkategorie);
		
		return list;
	}

}
