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
import ch.wsl.fps.hepromo.model.ModelStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemVorruecken2018 extends Arbeitssystem {

	private static final long serialVersionUID = 1L;
	
	private Maschinentyp maschinentyp;
	private double produktivtaetssteigerungRueckeraupe;
	
	
	public Maschinentyp getMaschinentyp() {
		return maschinentyp;
	}

	public void setMaschinentyp(Maschinentyp maschinentyp) {
		this.maschinentyp = maschinentyp;
	}

	public double getProduktivtaetssteigerungRueckeraupe() {
		return produktivtaetssteigerungRueckeraupe;
	}

	public void setProduktivtaetssteigerungRueckeraupe(double produktivtaetssteigerungRueckeraupe) {
		this.produktivtaetssteigerungRueckeraupe = produktivtaetssteigerungRueckeraupe;
	}


	public enum Maschinentyp {
		Schlepper,
		Rueckeraupe;
		
		@Override
		public String toString() {
			switch(this) {
			case Schlepper:
				return ModelStrings.getString("ArbeitssystemVorruecken2018.enumSchlepper");  //$NON-NLS-1$
				
			case Rueckeraupe:
				return ModelStrings.getString("ArbeitssystemVorruecken2018.enumRueckeraupe");  //$NON-NLS-1$
				
			default:
				throw new RuntimeException();
			}
		}
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.addOnTop(ModelStrings.getString("ArbeitssystemVorruecken2018.Maschinentyp"),  maschinentyp); //$NON-NLS-1$
		
		if (maschinentyp == Maschinentyp.Rueckeraupe) {
			list.add(ModelStrings.getString("ArbeitssystemVorruecken2018.ProduktivtaetssteigerungRueckeraupe"),  maschinentyp); //$NON-NLS-1$
		}
		
		return list;
	}

}
