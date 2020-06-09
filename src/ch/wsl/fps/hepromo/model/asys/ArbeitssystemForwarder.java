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
public class ArbeitssystemForwarder extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private ForwarderTyp forwarderTyp;
	private double ladeQuerschnittsFlaeche_m2;
	
	

	public ForwarderTyp getForwarderTyp() {
		return forwarderTyp;
	}
	
	
	/**
	 * <b>Achtung:</b> beim Aufruf dieser Methode wird die 
	 * Ladequerschnittsfläche automatisch neu gesetzt. Der 
	 * gesetzte Wert entspricht dem Standardwert des gewählten 
	 * Forwardertyps und ist in der Dokumentation ersichtlich.
	 */
	public void setForwarderTyp(ForwarderTyp forwarderTyp) {
		this.forwarderTyp = forwarderTyp;
		
		switch(forwarderTyp) {
		case Klein:
			ladeQuerschnittsFlaeche_m2 = 3.3;
			break;
			
		case Mittel:
			ladeQuerschnittsFlaeche_m2 = 4.1;
			break;

		default:
			throw new RuntimeException();
		}
	}
	

	public double getLadeQuerschnittsFlaeche_m2() {
		return ladeQuerschnittsFlaeche_m2;
	}


	public void setLadeQuerschnittsFlaeche_m2(double ladeQuerschnittsFlaeche_m2) {
		this.ladeQuerschnittsFlaeche_m2 = ladeQuerschnittsFlaeche_m2;
	}




	public enum ForwarderTyp {
		Klein,
		Mittel;
		
		@Override
		public String toString() {
			switch(this) {
			case Klein:
				return PdfLabels.ArbeitssystemForwarder_klein.toString(); 
				
			case Mittel:
				return PdfLabels.ArbeitssystemForwarder_mittel.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
		
		/**
		 * Korrekturfaktor
		 */
		public double getKF() {
			switch(this) {
			case Klein:
				return 1.178;
				
			case Mittel:
				return 1.418;
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add(PdfLabels.ArbeitssystemForwarder_Forwardertyp, forwarderTyp); 
		list.add(PdfLabels.ArbeitssystemForwarder_Ladequerschnittsflaeche_m2, ladeQuerschnittsFlaeche_m2); 
		
		return list;
	}

}
