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

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemRadharvester extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private MaschinenKategorie maschinenKategorie;
	private MaschinenTyp maschinenTyp;
	
	
	public MaschinenKategorie getMaschinenKategorie() {
		return maschinenKategorie;
	}

	public void setMaschinenKategorie(MaschinenKategorie maschinenKategorie) {
		this.maschinenKategorie = maschinenKategorie;
	}

	public MaschinenTyp getMaschinenTyp() {
		return maschinenTyp;
	}

	public void setMaschinenTyp(MaschinenTyp maschinenTyp) {
		this.maschinenTyp = maschinenTyp;
	}

	
	
	public enum MaschinenKategorie{
		Mittel,
		Gross;
		
		@Override
		public String toString() {
			switch (this) {
			case Mittel:
				return "mittel"; 
			case Gross:
				return "gross"; 
			}
			throw new RuntimeException();
		}
	}
	
	
	public enum MaschinenTyp {
		
		//Kategorie 'mittel'
		Standard_mittel	(MaschinenKategorie.Mittel, 45, "Standardwert - mittel"), 
		Oesa			(MaschinenKategorie.Mittel, 45, "Ösa SuperEVA - FMG 746"), //$NON-NLS-1$
		Silvatec854		(MaschinenKategorie.Mittel, 51, "Silvatec 854 TH - Silvatec 235"), //$NON-NLS-1$
		Timberjack870A	(MaschinenKategorie.Mittel, 45, "Timberjack 870 - TJ 743"), //$NON-NLS-1$
		Timberjack870B	(MaschinenKategorie.Mittel, 45, "Timberjack 870B - TJ 743C"), //$NON-NLS-1$
		Valmet9014		(MaschinenKategorie.Mittel, 52, "Valmet 901/4 - Valmet 984"), //$NON-NLS-1$
		Valmet9016		(MaschinenKategorie.Mittel, 44, "Valmet 901/6 - Valmet 942"), //$NON-NLS-1$
		
		//Kategorie 'gross'
		Standard_gross	(MaschinenKategorie.Gross, 60, "Standardwert - gross"), 
		FMG				(MaschinenKategorie.Gross, 45, "FMG Lokomo 990 - FMG 746"), //$NON-NLS-1$
		Hemek			(MaschinenKategorie.Gross, 55, "Hemek 880 - Woodking 550"), //$NON-NLS-1$
		Herma			(MaschinenKategorie.Gross, 46, "Herma 2010 - SP 550"), //$NON-NLS-1$
		Ponsse			(MaschinenKategorie.Gross, 60, "Ponsse Ergo HS 16 - Ponsse H60"), //$NON-NLS-1$
		Silvatec866		(MaschinenKategorie.Gross, 56, "Silvatec 866 TH - Silvatec 445 MD 5"), //$NON-NLS-1$
		Skogsjan		(MaschinenKategorie.Gross, 55, "Skogsjan 687 XL - Skogsjan 601 XL"), //$NON-NLS-1$
		Timberjack1270A	(MaschinenKategorie.Gross, 50, "Timberjack 1270 - TJ 746C"), //$NON-NLS-1$
		Timberjack1270B	(MaschinenKategorie.Gross, 60, "Timberjack 1270 - TJ 755"), //$NON-NLS-1$
		Valmet911		(MaschinenKategorie.Gross, 45, "Valmet 911 - PAN 728"); //$NON-NLS-1$
		

		private final MaschinenKategorie mKat;
		private final int maxFaellDrm_cm;
		private final String fullName;
		
		
		private MaschinenTyp(MaschinenKategorie mKat, int maxFaellDrm_cm, String fullName) {
			this.mKat = mKat;
			this.maxFaellDrm_cm = maxFaellDrm_cm;
			this.fullName = fullName;
		}
		
		
		public MaschinenKategorie getMaschinenKategorie() {
			return this.mKat;
		}
		

		public int getMaxFaellDrm_cm() {
			return this.maxFaellDrm_cm;
		}
		
		
		@Override
		public String toString() {
			return this.fullName;
		}
		
		
		
		
		/**
		 * returns a new double[] {ML, KR, HM, SM, FD, EK} (Maschinentyp-spezfisch)
		 */
		public double[] getTechnologieFaktoren() {
			//ML, KR, HM, SM, FD, EK

			switch(this) {
			case FMG:
				return new double[]{114, 10.2, 155, 30.0, 45, 21};
			case Hemek:
				return new double[]{147, 10.2, 100, 38, 55, 19};
			case Herma:
				return new double[]{150, 10.7, 170, 30.0, 46, 21};
			case Oesa:
//				return new double[]{99.5, 9.5, 125, 29.0, 48, 20}; //version doku
				return new double[]{99.5, 9.5, 125, 29.0, 45, 21}; //version code
			case Ponsse:
				return new double[]{157, 10.0, 190, 27.0, 60, 24};
			case Silvatec854:
				return new double[]{119, 9.4, 72, 20.6, 51, 18};
			case Silvatec866:
				return new double[]{160, 10.0, 168, 28.9, 56, 11};
			case Skogsjan:
				return new double[]{158, 10.0, 180, 28.9, 55, 22};
				

			case Standard_gross:
				return new double[]{156, 10.0, 168, 39.3, 60, 24}; //entspricht Timberjack 1270B mit TJ 755
			case Standard_mittel:
				return new double[]{113, 10.0, 125, 38.4, 45, 17}; //entspricht Timberjack 870B mit TJ 743C
				
				
				
			case Timberjack1270A:
				return new double[]{114, 10.3, 147, 31.0, 50, 21};
			case Timberjack1270B:
				return new double[]{156, 10.0, 168, 39.3, 60, 24};
			case Timberjack870A:
				return new double[]{112, 10.1, 102, 26.0, 45, 16};
			case Timberjack870B:
				return new double[]{113, 10.0, 125, 38.4, 45, 17};
			case Valmet9014:
				return new double[]{83, 9.4, 98, 22.6, 52, 20};
			case Valmet9016:
				return new double[]{83, 9.5, 81, 30.2, 44, 15};
			case Valmet911:
				return new double[]{130, 9.2, 145, 32, 45, 21};
			}
			throw new RuntimeException();
		}
		
	} //end enum MaschinenTyp
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add("Maschinenkategorie", maschinenKategorie);
		list.add("Maschinentyp", maschinenTyp);
		
		return list;
	}

}
