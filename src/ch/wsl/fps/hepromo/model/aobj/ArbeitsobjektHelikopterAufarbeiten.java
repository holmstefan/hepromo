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
public class ArbeitsobjektHelikopterAufarbeiten extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private BaumartenGruppe baumartenGruppe;
	private ArbeitsVerfahren arbeitsVerfahren;


	public BaumartenGruppe getBaumartenGruppe() {
		return baumartenGruppe;
	}


	public void setBaumartenGruppe(BaumartenGruppe baumartenGruppe) {
		this.baumartenGruppe = baumartenGruppe;
	}


	public ArbeitsVerfahren getArbeitsVerfahren() {
		return arbeitsVerfahren;
	}


	public void setArbeitsVerfahren(ArbeitsVerfahren arbeitsVerfahren) {
		this.arbeitsVerfahren = arbeitsVerfahren;
	}


	public enum BaumartenGruppe {
		Fichte,
		Tanne,
		Foehre_Laerche,
		Laubholz;
		
		@Override
		public String toString() {
			switch (this) {
			case Fichte:
				return PdfLabels.ArbeitsobjektHelikopterAufarbeiten_Fichte.toString(); 
				
			case Tanne:
				return PdfLabels.ArbeitsobjektHelikopterAufarbeiten_Tanne.toString(); 
				
			case Foehre_Laerche:
				return PdfLabels.ArbeitsobjektHelikopterAufarbeiten_FoehreLaerche.toString(); 
				
			case Laubholz:
				return PdfLabels.ArbeitsobjektHelikopterAufarbeiten_Laubholz.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}


	public enum ArbeitsVerfahren {
		Sortimentsverfahren,
		Vollbaumverfahren;
		
		@Override
		public String toString() {
			switch (this) {
			case Sortimentsverfahren:
				return PdfLabels.ArbeitsobjektHelikopterAufarbeiten_Sortimentsverfahren.toString(); 
				
			case Vollbaumverfahren:
				return PdfLabels.ArbeitsobjektHelikopterAufarbeiten_Vollbaumverfahren.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Baumartengruppe", baumartenGruppe);
		list.add("Arbeitsverfahren", arbeitsVerfahren);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}

}
