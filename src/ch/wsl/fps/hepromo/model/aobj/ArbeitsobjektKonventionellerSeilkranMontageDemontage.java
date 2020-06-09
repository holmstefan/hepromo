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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public abstract class ArbeitsobjektKonventionellerSeilkranMontageDemontage extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private int linienLaenge_m;
	private int anzahlStuetzen;
	private int anzahlEndmasten;
	private WindenTransport windenTransport;
	private WindenStandort windenStandort;
	private int distanzWindenSelbstfahrt;
	
	
	
	public enum WindenTransport {
		KeinWindenTransport,
		SelbstfahrtBergauf,
		SelbstfahrtBergab,
		MitHeli;
		
		@Override
		public String toString() {
			switch(this) {
			case KeinWindenTransport:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTKeinWindentransport.toString(); 
				
			case SelbstfahrtBergauf:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTSelbstfahrtBergauf.toString(); 
				
			case SelbstfahrtBergab:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTSelbstfahrtBergab.toString(); 
				
			case MitHeli:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTmitHeli.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	public enum WindenStandort {
		Bleibt,
		Wechselt;
		
		@Override
		public String toString() {
			switch(this) {
			case Bleibt:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranMontageDemontage_WSbleibt.toString(); 
				
			case Wechselt:
				return PdfLabels.ArbeitsobjektKonventionellerSeilkranMontageDemontage_WSwechselt.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	
	public int getLinienLaenge_m() {
		return linienLaenge_m;
	}
	
	public void setLinienLaenge_m(int linienLaenge_m) {
		this.linienLaenge_m = linienLaenge_m;
	}
	
	public int getAnzahlStuetzen() {
		return anzahlStuetzen;
	}
	
	public void setAnzahlStuetzen(int anzahlStuetzen) {
		this.anzahlStuetzen = anzahlStuetzen;
	}
	
	public int getAnzahlEndmasten() {
		return anzahlEndmasten;
	}
	
	public void setAnzahlEndmasten(int anzahlEndmasten) {
		this.anzahlEndmasten = anzahlEndmasten;
	}
	
	public WindenTransport getWindenTransport() {
		return windenTransport;
	}
	
	public void setWindenTransport(WindenTransport windenTransport) {
		this.windenTransport = windenTransport;
	}
	
	public WindenStandort getWindenStandort() {
		return windenStandort;
	}
	
	public void setWindenStandort(WindenStandort windenStandort) {
		this.windenStandort = windenStandort;
	}
	
	public int getDistanzWindenSelbstfahrt() {
		return distanzWindenSelbstfahrt;
	}
	
	public void setDistanzWindenSelbstfahrt(int distanzWindenSelbstfahrt) {
		this.distanzWindenSelbstfahrt = distanzWindenSelbstfahrt;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Linienlänge (m)", linienLaenge_m);
		list.add("Anzahl Stützen", anzahlStuetzen);
		list.add("Anzahl Endmasten", anzahlEndmasten);
		list.add("WindenTransport", windenTransport);
		list.add("WindenStandort", windenStandort);
		list.add("Distanz Windenselbstfahrt", distanzWindenSelbstfahrt);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}

}
