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
public class ArbeitsobjektRadharvester extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private Baumartgruppe baumartGruppe;
	private double volumenMittelstamm_m3;
	
		
	
	public Baumartgruppe getBaumartGruppe() {
		return baumartGruppe;
	}

	public void setBaumartGruppe(Baumartgruppe baumartGruppe) {
		this.baumartGruppe = baumartGruppe;
	}

	public double getVolumenMittelstamm_m3() {
		return volumenMittelstamm_m3;
	}

	public void setVolumenMittelstamm_m3(double volumenMittelstamm_m3) {
		this.volumenMittelstamm_m3 = volumenMittelstamm_m3;
	}




	public enum Baumartgruppe {
		Fichte, Foehre_Laerche;
		
		@Override
		public String toString() {
			switch (this) {
			case Fichte:
				return "Fichte"; 
				
			case Foehre_Laerche:
				return "Föhre / Lärche"; 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Baumartengruppe", baumartGruppe);
		list.add("Volumen-Mittelstamm (m3)", volumenMittelstamm_m3);
		
		return list;
	}

}
