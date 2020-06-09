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
public class ArbeitssystemRadharvester2014 extends Arbeitssystem {

	private static final long serialVersionUID = 1L;
	
	private Maschinentyp maschinentyp;

	

	public Maschinentyp getMaschinentyp() {
		return maschinentyp;
	}


	public void setMaschinentyp(Maschinentyp maschinentyp) {
		this.maschinentyp = maschinentyp;
	}


	public enum Maschinentyp {
		Mittel,
		Gross;
		
		@Override
		public String toString() {
			switch(this) {
			case Mittel:
				return PdfLabels.ArbeitssystemRadharvester2014_TypMittel.toString(); 
				
			case Gross:
				return PdfLabels.ArbeitssystemRadharvester2014_TypGross.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.addOnTop(PdfLabels.ArbeitssystemRadharvester2014_Maschinentyp, maschinentyp);
		
		return list;
	}

}
