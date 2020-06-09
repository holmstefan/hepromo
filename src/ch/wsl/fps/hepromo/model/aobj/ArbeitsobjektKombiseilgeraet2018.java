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
import ch.wsl.fps.hepromo.model.ModelStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektKombiseilgeraet2018 extends Arbeitsobjekt {

	private static final long serialVersionUID = 1L;
	
	private Erschwernisse erschwernisse;
	private int laengeProAufstellung_m;
	private int anzahlStuetzenProAufstellung;
	private int anzahlAufstellungen;
	
	private int hangneigung_Prz;
	private int anteilLaubholz_Prz;
	
	
	
	public Erschwernisse getErschwernisse() {
		return erschwernisse;
	}

	public void setErschwernisse(Erschwernisse erschwernisse) {
		this.erschwernisse = erschwernisse;
	}

	public int getLaengeProAufstellung_m() {
		return laengeProAufstellung_m;
	}

	public void setLaengeProAufstellung_m(int laengeProAufstellung_m) {
		this.laengeProAufstellung_m = laengeProAufstellung_m;
	}

	public int getAnzahlStuetzenProAufstellung() {
		return anzahlStuetzenProAufstellung;
	}

	public void setAnzahlStuetzenProAufstellung(int anzahlStuetzenProAufstellung) {
		this.anzahlStuetzenProAufstellung = anzahlStuetzenProAufstellung;
	}

	public int getAnzahlAufstellungen() {
		return anzahlAufstellungen;
	}

	public void setAnzahlAufstellungen(int anzahlAufstellungen) {
		this.anzahlAufstellungen = anzahlAufstellungen;
	}

	public int getHangneigung_Prz() {
		return hangneigung_Prz;
	}

	public void setHangneigung_Prz(int hangneigung_Prz) {
		this.hangneigung_Prz = hangneigung_Prz;
	}

	public int getAnteilLaubholz_Prz() {
		return anteilLaubholz_Prz;
	}

	public void setAnteilLaubholz_Prz(int anteilLaubholz_Prz) {
		this.anteilLaubholz_Prz = anteilLaubholz_Prz;
	}




	public static enum Erschwernisse {
		Keine,
		Vorhanden;
		
		@Override
		public String toString() {
			switch(this) {
			case Keine:
				return ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.enumKeineErschwernisse"); //$NON-NLS-1$
				
			case Vorhanden:
				return ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.enumErschwernisseVorhanden"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add(ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.Erschwernisse"),  erschwernisse); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.LaengeProAufstellung_m"),  laengeProAufstellung_m); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.AnzahlStuetzen"),  anzahlStuetzenProAufstellung); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.AnzahlAufstellungen"),  anzahlAufstellungen); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.Hangneigung_Prz"),  hangneigung_Prz); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektKombiseilgeraet2018.AnteilLaubholz_Prz"),  anteilLaubholz_Prz); //$NON-NLS-1$
		
		return list;
	}
}
