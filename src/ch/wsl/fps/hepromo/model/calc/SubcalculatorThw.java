/*******************************************************************************
 * Copyright 2024 Stefan Holm
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
package ch.wsl.fps.hepromo.model.calc;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SubcalculatorThw {

	public static final int ANZAHL_AN_ABHAENGE_PROZESSE_PRO_RUECKEGASSE_RADHARVESTER = 1;
	public static final int ANZAHL_AN_ABHAENGE_PROZESSE_PRO_RUECKEGASSE_FORWARDER = 6;
	
	private final boolean einsatzThw;
	private final int anzahlRueckegassen;
	private final int anzahlAnAbhaengeProzesseProRueckegasse;
	
	private SubcalculatorThw(boolean einsatzThw, int anzahlRueckegassen, int anzahlAnAbhaengeProzesseProRueckegasse) {
		this.einsatzThw = einsatzThw;
		this.anzahlRueckegassen = anzahlRueckegassen;
		this.anzahlAnAbhaengeProzesseProRueckegasse = anzahlAnAbhaengeProzesseProRueckegasse;
	}
	
	public double calcISH_THW() {
		if (einsatzThw == false) {
			return 0;
		}
		
		double zeitInstallation_s = 1382;
		double zeitVerschieben_s = zeitInstallation_s;
		double zeitAttachDetach_s = 73;
		
		double result_s = 2 * zeitInstallation_s 
				+ (anzahlRueckegassen - 1) * zeitVerschieben_s 
				+ anzahlAnAbhaengeProzesseProRueckegasse * anzahlRueckegassen * zeitAttachDetach_s;
		double result_h = result_s / 3_600;
		
		return result_h;
	}
	
	public static SubcalculatorThw getInstanceRadharvester(boolean einsatzThw, int anzahlRueckegassen) {
		return new SubcalculatorThw(einsatzThw, anzahlRueckegassen, ANZAHL_AN_ABHAENGE_PROZESSE_PRO_RUECKEGASSE_RADHARVESTER);
	}
	
	public static SubcalculatorThw getInstanceForwarder(boolean einsatzThw, int anzahlRueckegassen) {
		return new SubcalculatorThw(einsatzThw, anzahlRueckegassen, ANZAHL_AN_ABHAENGE_PROZESSE_PRO_RUECKEGASSE_FORWARDER);
	}

}
