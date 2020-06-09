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

import java.io.Serializable;
import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemHacker2018 extends Arbeitssystem { //TODO: kann das Problem mit einzelnen benutzerdefinierten Typen generisch gelöst werden? Problem taucht innerhalb HeProMo an verschiedenen Stellen auf!
	
	public static final HackerMotorleistung HACKER_KW_100_BIS_199 = new HackerMotorleistung(100, 150, 199, false);
	public static final HackerMotorleistung HACKER_KW_200_BIS_299 = new HackerMotorleistung(200, 250, 299, false);
	public static final HackerMotorleistung HACKER_KW_300_BIS_399 = new HackerMotorleistung(300, 350, 399, false);
	public static final HackerMotorleistung HACKER_KW_400_BIS_499 = new HackerMotorleistung(400, 450, 499, false);
	
	//WICHTIG: der benutzerdefinierte Typ ist NICHT static, da sonst jede Instanz die selbe kW-Zahl hätte!
	public final HackerMotorleistung HACKER_BENUTZERDEFINIERT = new HackerMotorleistung(-1, 100, -1, true);

	private static final long serialVersionUID = 1L;
	
	private HackerMotorleistung hackerMotorleistung;
	private boolean displayHackerKostenProPMH15;
	
	private HackerMotorleistung[] allHackerMotorleistung = {
			HACKER_KW_100_BIS_199,
			HACKER_KW_200_BIS_299,
			HACKER_KW_300_BIS_399,
			HACKER_KW_400_BIS_499,
			HACKER_BENUTZERDEFINIERT
	};
	
	
	public HackerMotorleistung getHackerMotorleistung() {
		return hackerMotorleistung;
	}

	public void setHackerMotorleistung(HackerMotorleistung hackerMotorleistung) {
		this.hackerMotorleistung = hackerMotorleistung;
	}

	
	public boolean isDisplayHackerKostenProPMH15() {
		return displayHackerKostenProPMH15;
	}

	public void setDisplayHackerKostenProPMH15(boolean displayHackerKostenProPMH15) {
		this.displayHackerKostenProPMH15 = displayHackerKostenProPMH15;
	}
	
	
	public HackerMotorleistung[] getAllHackerMotorleistung() {
		return allHackerMotorleistung;
	}
	
	
	public void setAllHackerMotorleistung(HackerMotorleistung[] allHackerMotorleistung) {
		this.allHackerMotorleistung = allHackerMotorleistung;
	}
	
	
	
	public static class HackerMotorleistung implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private int motorleistungMin_kW;
		private int motorleistungCalc_kW;
		private int motorleistungMax_kW;
		private boolean wasChanged = false;
		private final boolean benutzerdefiniert;
		
		private HackerMotorleistung(int motorleistungMin_kW, int motorleistungCalc_kW, int motorleistungMax_kW, boolean benutzerdefiniert) {
			this.motorleistungMin_kW = motorleistungMin_kW;
			this.motorleistungCalc_kW = motorleistungCalc_kW;
			this.motorleistungMax_kW = motorleistungMax_kW;
			this.benutzerdefiniert = benutzerdefiniert;
		}
		
		@Override
		public String toString() {
			if (isBenutzerdefiniert()) {
				if (wasChanged) {
					return ModelStrings.getString("ArbeitssystemHacker2018.enumBenutzerdefiniert") + " (" + motorleistungCalc_kW + " kW)"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3
				}
				else {
					return ModelStrings.getString("ArbeitssystemHacker2018.enumBenutzerdefiniert"); //$NON-NLS-1$
				}
			}
			else {
				return motorleistungMin_kW + " - " + motorleistungMax_kW + " kW"; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		
		public int getMotorleistungCalc_Kw() {
			return this.motorleistungCalc_kW;
		}
		
		
		public boolean isBenutzerdefiniert() {
			return benutzerdefiniert;
		}
		
		public void setMotorleistungCalc_Kw(int value) {
			if (this.benutzerdefiniert == true) {
				this.wasChanged = true;
				this.motorleistungCalc_kW = value;
			}
			else {
				throw new RuntimeException("Leistung kann nicht verändert werden."); //$NON-NLS-1$
			}
		}
	}
	
	// for testing purposes only
	public static HackerMotorleistung getBenutzerdefinierteHackerMotorleistung(int motorleistung_kW) {
		return new HackerMotorleistung(-1, motorleistung_kW, -1, true);
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		//TODO: hier noch ein zusätzlicher Eintrag der Betriebskosten pro Srm? -> dazu müsste das AS auch das AO und den CALC kennen (bzw. das zugehörige Modell)!
		list.addOnTop(ModelStrings.getString("ArbeitssystemHacker2018.HackerMotorleistung"), hackerMotorleistung.toString()); //$NON-NLS-1$
		
		return list;
	}

}
