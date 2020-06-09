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
public class ArbeitsobjektMotormanuellGesamt2014 extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private int bhd_cm;
	private int anteilLaubholz_Prz;
	private int anteilKiefer_Prz;
	private Region region;
	
	
	
	public int getBhd_cm() {
		return bhd_cm;
	}


	public void setBhd_cm(int bhd_cm) {
		this.bhd_cm = bhd_cm;
	}


	public int getAnteilLaubholz_Prz() {
		return anteilLaubholz_Prz;
	}


	public void setAnteilLaubholz_Prz(int anteilLaubholz_Prz) {
		this.anteilLaubholz_Prz = anteilLaubholz_Prz;
	}


	public int getAnteilKiefer_Prz() {
		return anteilKiefer_Prz;
	}


	public void setAnteilKiefer_Prz(int anteilKiefer_Prz) {
		this.anteilKiefer_Prz = anteilKiefer_Prz;
	}


	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}
	
	


	public enum Region {
		Huegelland,
		Gebirge;
		
		@Override
		public String toString() {
			switch (this) {
			case Gebirge:
				return PdfLabels.ArbeitsobjektMotormanuellGesamt2014_Gebirge.toString(); 
			case Huegelland:
				return PdfLabels.ArbeitsobjektMotormanuellGesamt2014_FlachHuegelland.toString(); 
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add(PdfLabels.ArbeitsobjektMotormanuellGesamt2014_BHD_cm, bhd_cm);
		list.add(PdfLabels.ArbeitsobjektMotormanuellGesamt2014_AnteilLaubholz_Prz, anteilLaubholz_Prz);
		list.add(PdfLabels.ArbeitsobjektMotormanuellGesamt2014_AnteilKiefer_Prz, anteilKiefer_Prz);
		list.add(PdfLabels.ArbeitsobjektMotormanuellGesamt2014_Region, region);
		
		return list;
	}
	
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		double faktorRinde = 
				0.88 * (100.0 - getAnteilLaubholz_Prz() - getAnteilKiefer_Prz()) / 100.0+ 
				getAnteilLaubholz_Prz() / 100.0 * 0.90 + 
				getAnteilKiefer_Prz() / 100.0 * 0.87;
		
		return faktorRinde;
	}

}
