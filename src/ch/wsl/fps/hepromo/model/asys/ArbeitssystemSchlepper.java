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
public class ArbeitssystemSchlepper extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private boolean rueckegehilfe;
	private double kostensatzRueckegehilfe;
	private RueckgehilfeEinsatzanteil rueckgehilfeEinsatzanteil;
	private SchlepperTyp schlepperTyp;

	
	public boolean isRueckegehilfe() {
		return rueckegehilfe;
	}
	
	public void setRueckegehilfe(boolean rueckegehilfe) {
		this.rueckegehilfe = rueckegehilfe;
	}
	
	public double getKostensatzRueckegehilfe() {
		return kostensatzRueckegehilfe;
	}

	public void setKostensatzRueckegehilfe(double kostensatzRueckegehilfe) {
		this.kostensatzRueckegehilfe = kostensatzRueckegehilfe;
	}

	public RueckgehilfeEinsatzanteil getRueckgehilfeEinsatzanteil() {
		return rueckgehilfeEinsatzanteil;
	}
	
	public void setRueckgehilfeEinsatzanteil(RueckgehilfeEinsatzanteil rueckgehilfeEinsatzanteil) {
		this.rueckgehilfeEinsatzanteil = rueckgehilfeEinsatzanteil;
	}
	
	public SchlepperTyp getSchlepperTyp() {
		return schlepperTyp;
	}
	
	public void setSchlepperTyp(SchlepperTyp schlepperTyp) {
		this.schlepperTyp = schlepperTyp;
	}
	
	
	
	public enum RueckgehilfeEinsatzanteil {
		Unbekannt,
		Prz_0,
		Prz_1bis25,
		Prz_26bis50,
		Prz_51bis75,
		Prz_76bis100;	
		
		
		public double getRueckgehilfeEinsatzZeitFaktor() {
			switch (this) {
			case Unbekannt:
			case Prz_0:
				return 0.0;
				
			case Prz_1bis25:
				return 0.25;
				
			case Prz_26bis50:
				return 0.50;
				
			case Prz_51bis75:
				return 0.75;
				
			case Prz_76bis100:
				return 1.00;
				
			}
			throw new RuntimeException();
		}
		
		
		
		@Override
		public String toString() {
			switch (this) {
			case Unbekannt:
				return "unbekannt"; 
				
			case Prz_0:
				return "0%"; //$NON-NLS-1$
				
			case Prz_1bis25:
				return "1 - 25%"; //$NON-NLS-1$
				
			case Prz_26bis50:
				return "26 - 50%"; //$NON-NLS-1$
				
			case Prz_51bis75:
				return "51 - 75%"; //$NON-NLS-1$
				
			case Prz_76bis100:
				return "76 - 100%"; //$NON-NLS-1$
			}
			throw new RuntimeException();
		}
		
		
		public static RueckgehilfeEinsatzanteil parseDouble_Prz(double value) {
			
			if (value <= 0) {
				return Prz_0;
			}
			else if (value <= 25) {
				return Prz_1bis25;
			}
			else if (value <= 50) {
				return Prz_26bis50;
			}
			else if (value <= 75) {
				return Prz_51bis75;
			}
			else {
				return Prz_76bis100;
			}
		}
	}
	
	
	
	public enum SchlepperTyp {
		Forstspezialschlepper,
		AndereSchleppertypen;
		
		@Override
		public String toString() {
			switch (this) {
			case Forstspezialschlepper:
				return "Forstspezialschlepper"; 
				
			case AndereSchleppertypen:
				return "andere Schleppertypen"; 
			}
			throw new RuntimeException();
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add("Rückegehilfe", rueckegehilfe); 
		list.add("Rückegehilfe Einsatzanteil", rueckgehilfeEinsatzanteil); 
		list.add("Schleppertyp", schlepperTyp); 
		
		return list;
	}
	
	
	
	@Override
	public double getAnsatzPersonalKombiniertGewichtet() {

		double ansatz1 = getKostensatzPersonal1_proH() * 1.0;
		double ansatz2 = getKostensatzRueckegehilfe() * getRueckgehilfeEinsatzanteil().getRueckgehilfeEinsatzZeitFaktor();
		double fte = 1.0 + getRueckgehilfeEinsatzanteil().getRueckgehilfeEinsatzZeitFaktor();
		
		double gesamtkostensatz = (ansatz1 + ansatz2) / fte;
		
		return gesamtkostensatz;
	}

}
