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

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektMotormanuell extends Arbeitsobjekt{
	
	private static final long serialVersionUID = 1L;

	// z.B. Bestand (Baumart), Gelände (Hangneigung), Anzahl Kubik (beim Heli), Fahrdistanz etc
	private Baumartgruppe baumartenGruppe;
	private double massenmittelstamm_m3iR;
	private Hangneigung hangneigung;
	private Hindernisse hindernisse;
	
	
	
	
	/*
	 * ENUM's
	 */
	
	public enum Baumartgruppe {
		Fichte, Tanne, Foehre_Laerche, Laubholz;
		
		@Override
		public String toString() {
			switch (this) {
			case Fichte:
				return "Fichte"; 
				
			case Tanne:
				return "Tanne"; 
				
			case Foehre_Laerche:
				return "Föhre / Lärche"; 
				
			case Laubholz:
				return "Laubholz"; 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	public enum Hangneigung {
		Hangneigung_0bis30,
		Hangneigung_31bis50,
		Hangneigung_51bis70,
		Hangneigung_71bis100;
		
		@Override
		public String toString() {
			switch (this) {
			case Hangneigung_0bis30:
				return "0 - 30%"; //$NON-NLS-1$
				
			case Hangneigung_31bis50:
				return "31 - 50%"; //$NON-NLS-1$
				
			case Hangneigung_51bis70:
				return "51 - 70%"; //$NON-NLS-1$
				
			case Hangneigung_71bis100:
				return "> 70%"; //$NON-NLS-1$
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	public enum Hindernisse {
		Keine, Gering, Maessig, Stark;
		
		@Override
		public String toString() {
			switch (this) {
			case Keine:
				return "keine"; 
				
			case Gering:
				return "gering"; 
				
			case Maessig:
				return "mässig"; 
				
			case Stark:
				return "stark"; 
				
			default:
				throw new RuntimeException();
			}
		}
	}

	
	
	
	/*
	 * GETTERS AND SETTERS
	 */

	public Baumartgruppe getBaumartenGruppe() {
		return baumartenGruppe;
	}


	public void setBaumartenGruppe(Baumartgruppe baumartenGruppe) {
		this.baumartenGruppe = baumartenGruppe;
	}


	public double getMassenmittelstamm_m3iR() {
		return massenmittelstamm_m3iR;
	}


	public void setMassenmittelstamm_m3iR(double massenmittelstamm_m3iR) {
		this.massenmittelstamm_m3iR = massenmittelstamm_m3iR;
	}


	public Hangneigung getHangneigung() {
		return hangneigung;
	}


	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}


	public Hindernisse getHindernisse() {
		return hindernisse;
	}


	public void setHindernisse(Hindernisse hindernisse) {
		this.hindernisse = hindernisse;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Baumartengruppe", baumartenGruppe);
		list.add("Massenmittelstamm (m3 i.R.)", massenmittelstamm_m3iR);
		list.add("Hangneigung", hangneigung);
		list.add("Hindernisse", hindernisse);
		
		return list;
	}
	
	public static interface Stuecklaengen {
		//emtpy
	}
	
	
}
