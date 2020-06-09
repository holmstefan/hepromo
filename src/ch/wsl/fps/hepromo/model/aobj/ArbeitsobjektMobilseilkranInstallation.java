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
import java.util.List;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektMobilseilkranInstallation extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private Seilsystem seilsystem;
	private MaschinenStandort maschinenStandort;
	private int linienLaenge_m;
	
	private List<Integer> listStuetzenTragseilHoehen;
	
	private boolean endmast;
	private int tragseilHoeheEndmast;
	
	
	
	
	public enum MaschinenStandort {
		Unten,
		Oben;
		
		@Override
		public String toString() {
			switch(this) {
			case Unten:
				return PdfLabels.ArbeitsobjektMobilseilkranInstallation_MaschinenstandortUnten.toString(); 
				
			case Oben:
				return PdfLabels.ArbeitsobjektMobilseilkranInstallation_MaschinenstandortOben.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	public enum Seilsystem {
		ZweiseilSystem,
		MehrseilSystem;
		
		@Override
		public String toString() {
			switch(this) {
			case ZweiseilSystem:
				return PdfLabels.ArbeitsobjektMobilseilkranInstallation_Zweiseilsystem.toString(); 
				
			case MehrseilSystem:
				return PdfLabels.ArbeitsobjektMobilseilkranInstallation_Mehrseilsystem.toString(); 
				
			default:
				throw new RuntimeException();
			}
		}
	}




	public Seilsystem getSeilsystem() {
		return seilsystem;
	}

	public void setSeilsystem(Seilsystem seilsystem) {
		this.seilsystem = seilsystem;
	}

	public MaschinenStandort getMaschinenStandort() {
		return maschinenStandort;
	}

	public void setMaschinenStandort(MaschinenStandort maschinenStandort) {
		this.maschinenStandort = maschinenStandort;
	}

	public int getLinienLaenge_m() {
		return linienLaenge_m;
	}
	
	public void setLinienLaenge_m(int linienLaenge_m) {
		this.linienLaenge_m = linienLaenge_m;
	}
	
	public List<Integer> getStuetzenTragseilHoehen() {
		return listStuetzenTragseilHoehen;
	}

	public void setStuetzenTragseilHoehen(List<Integer> listStuetzenTragseilHoehen) {
		this.listStuetzenTragseilHoehen = listStuetzenTragseilHoehen;
	}

	public boolean isEndmast() {
		return endmast;
	}

	public void setEndmast(boolean endmast) {
		this.endmast = endmast;
	}

	public int getTragseilHoeheEndmast() {
		return tragseilHoeheEndmast;
	}

	public void setTragseilHoeheEndmast(int tragseilHoeheEndmast) {
		this.tragseilHoeheEndmast = tragseilHoeheEndmast;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
//		LabelValuePairList list = super.getLabelValuePairList(decimalFormat); //Variablen aus Superklasse (Holzmenge etc.) werden hier nicht benötigt
		LabelValuePairList list = new LabelValuePairList(decimalFormat);
		
		list.add("Seilsystem",  seilsystem);
		list.add("Maschinenstandort",  maschinenStandort);
		list.add("Linienlänge (m)",  linienLaenge_m);
		
		for (int i : listStuetzenTragseilHoehen) {
			list.add("Tragseilhöhe Stütze " + i, i);
		}
		
		list.add("Endmast",  endmast);
		list.add("Tragseilhöhe Endmast",  tragseilHoeheEndmast);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}

}
