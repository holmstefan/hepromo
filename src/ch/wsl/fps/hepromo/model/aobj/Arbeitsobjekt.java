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

import java.io.Serializable;
import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class Arbeitsobjekt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private double holzmenge_m3;
	
	private boolean rindenabzugsfaktorManuellGesetzt = false;
	private double rindenabzugsFaktorManuell = 1.0;
	
	


	public double getHolzmenge_m3() {
		return holzmenge_m3;
	}


	public void setHolzmenge_m3(double holzmenge_m3) {
		this.holzmenge_m3 = holzmenge_m3;
	}
	
	

	/**
	 * Gibt den Abzugsfaktor für die Rinde an bei der
	 * Umrechnung von m3 i.R. -> m3 o.R.
	 */
	public final double getRindenAbzugFaktor() {
		return rindenabzugsfaktorManuellGesetzt ? rindenabzugsFaktorManuell : getRindenAbzugFaktorBerechnet();
	}
	
	

	/**
	 * Gibt den berechneten Abzugsfaktor für die Rinde an
	 * bei der Umrechnung von m3 i.R. -> m3 o.R.
	 */
	protected double getRindenAbzugFaktorBerechnet() {
		return 1;
	}
	
	
	/**
	 * Setzt den manuellen Rindenabzugsfaktor. Dieser wird nur
	 * berücksichtigt falls rindenabzugsfaktorManuellGesetzt = true
	 */
	public final void setRindenabzugsfaktorManuell(double value) {
		this.rindenabzugsFaktorManuell = value;
	}
	
	
	/**
	 * Gibt an, ob der Rindenabzugsfaktor automatisch
	 * berechnet wird, oder manuell gesetzt wird.
	 */
	public final boolean isRindenabzugsfaktorManuellGesetzt() {
		return rindenabzugsfaktorManuellGesetzt;
	}
	

	
	/**
	 * Bestimmt, ob der Rindenabzugsfaktor automatisch
	 * berechnet wird, oder manuell gesetzt wird.
	 */
	public final void setRindenabzugsfaktorManuellGesetzt(boolean flag) {
		this.rindenabzugsfaktorManuellGesetzt = flag;
	}

	
	
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = new LabelValuePairList(decimalFormat);
		
		list.add(getPdfLabelHolzmenge(), holzmenge_m3);
		list.add(PdfLabels.Arbeitsobjekt_Rindenabzugsfaktor, getRindenAbzugFaktor());
		
		return list;
	}
	
	
	protected String getPdfLabelHolzmenge() {
		return PdfLabels.Arbeitsobjekt_Holzmenge_m3.toString();
	}
}
