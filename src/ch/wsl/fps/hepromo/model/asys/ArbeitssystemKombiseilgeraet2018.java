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
public class ArbeitssystemKombiseilgeraet2018 extends Arbeitssystem {
	
	private EquipeAnzahlPersonen equipeAnzahlPersonen;
	private int anteilEinsatzzeitVerzugsfahrzeug_Prz;
	private double kostensatzVerzugsfahrzeug_proH; //FIXME: Arbeitssystem-Klasse auf drei Maschinen erweitern, analog Ergebnis-Klasse?
	
	
	public EquipeAnzahlPersonen getEquipeAnzahlPersonen() {
		return equipeAnzahlPersonen;
	}


	public void setEquipeAnzahlPersonen(EquipeAnzahlPersonen equipeAnzahlPersonen) {
		this.equipeAnzahlPersonen = equipeAnzahlPersonen;
	}


	public int getAnteilEinsatzzeitVerzugsfahrzeug_Prz() {
		return anteilEinsatzzeitVerzugsfahrzeug_Prz;
	}
	

	public void setAnteilEinsatzzeitVerzugsfahrzeug_Prz(int anteilEinsatzzeitVerzugsfahrzeug_Prz) {
		this.anteilEinsatzzeitVerzugsfahrzeug_Prz = anteilEinsatzzeitVerzugsfahrzeug_Prz;
	}


	public double getKostensatzVerzugsfahrzeug_proH() {
		return kostensatzVerzugsfahrzeug_proH;
	}


	public void setKostensatzVerzugsfahrzeug_proH(double kostensatzVerzugsfahrzeug_proH) {
		this.kostensatzVerzugsfahrzeug_proH = kostensatzVerzugsfahrzeug_proH;
	}


	public enum EquipeAnzahlPersonen {
		Anzahl3,
		Anzahl3einhalb,
		Anzahl4;
		
		public double asDouble() {
			switch(this) {
			case Anzahl3:
				return 3;
				
			case Anzahl3einhalb:
				return 3.5;
				
			case Anzahl4:
				return 4;
				
			default:
				throw new IllegalStateException();
			}
		}
		
		@Override
		public String toString() {
			switch(this) {
			case Anzahl3:
				return "3"; //$NON-NLS-1$
				
			case Anzahl3einhalb:
				return "3.5"; //$NON-NLS-1$
				
			case Anzahl4:
				return "4"; //$NON-NLS-1$
				
			default:
				throw new IllegalStateException();
			}
		}
		
		public static EquipeAnzahlPersonen parseFromDouble(double value) {
			if (value == 3) {
				return Anzahl3;
			}
			else if (value == 3.5) {
				return Anzahl3einhalb;
			}
			else if (value == 4) {
				return Anzahl4;
			}
			else {
				throw new IllegalArgumentException("" + value); //$NON-NLS-1$
			}
		}
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.addOnTop(ModelStrings.getString("ArbeitssystemKombiseilgeraet2018.EquipeAnzahlArbeiter"),  equipeAnzahlPersonen); //$NON-NLS-1$
		
		return list;
	}

}
