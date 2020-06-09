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
import ch.wsl.fps.hepromo.model.calc.CalculatorVorruecken2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektVorruecken2018 extends Arbeitsobjekt {

	private static final long serialVersionUID = 1L;
	
	private int bhd_cm;
	private Standortguete standortguete;
	private Hangneigung hangneigung;
	private boolean abzopfenDerKrone;

	
	public int getBhd_cm() {
		return bhd_cm;
	}

	public void setBhd_cm(int bhd_cm) {
		if (bhd_cm < 10  || bhd_cm > 30) {
			throw new IllegalArgumentException("BHD ausserhalb Wertebreich [10,30]!");
		}
		this.bhd_cm = bhd_cm;
	}

	public Standortguete getStandortguete() {
		return standortguete;
	}

	public void setStandortguete(Standortguete standortguete) {
		this.standortguete = standortguete;
	}

	public Hangneigung getHangneigung() {
		return hangneigung;
	}

	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}

	public boolean isAbzopfenDerKrone() {
		return abzopfenDerKrone;
	}

	public void setAbzopfenDerKrone(boolean abzopfenDerKrone) {
		this.abzopfenDerKrone = abzopfenDerKrone;
	}

	public double getMittelstammvolumen() {
		return CalculatorVorruecken2018.getVMit(standortguete, bhd_cm);
	}
	
	
	public static enum Standortguete { //FIXME: alle enums im Projekt uppercase!
		SEHR_GUT,
		GUT,
		MITTEL,
		WENIGER_GUT,
		ARM;
		
		@Override
		public String toString() {
			switch(this) {
			case SEHR_GUT:
				return ModelStrings.getString("ArbeitsobjektVorruecken2018.EnumStandortgueteSehrGut"); //$NON-NLS-1$
				
			case GUT:
				return ModelStrings.getString("ArbeitsobjektVorruecken2018.EnumStandortgueteGut"); //$NON-NLS-1$
				
			case MITTEL:
				return ModelStrings.getString("ArbeitsobjektVorruecken2018.EnumStandortgueteMittel"); //$NON-NLS-1$
				
			case WENIGER_GUT:
				return ModelStrings.getString("ArbeitsobjektVorruecken2018.EnumStandortgueteWenigerGut"); //$NON-NLS-1$
				
			case ARM:
				return ModelStrings.getString("ArbeitsobjektVorruecken2018.EnumStandortgueteArm"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			
			}
		}
	}
	
	
	public static enum Hangneigung {
		HN_BIS_15_PRZ,
		HN_GROESSER_15_BIS_25_PRZ,
		HN_GROESSER_25_BIS_35_PRZ,
		HN_GROESSER_35_BIS_45_PRZ,
		HN_GROESSER_45_BIS_55_PRZ,
		HN_GROESSER_55_PRZ;
		
		@Override
		public String toString() {
			switch(this) {
			case HN_BIS_15_PRZ:
				return "0 - 15 %"; //$NON-NLS-1$
				
			case HN_GROESSER_15_BIS_25_PRZ:
				return "16 - 25 %"; //$NON-NLS-1$
				
			case HN_GROESSER_25_BIS_35_PRZ:
				return "26 - 35 %"; //$NON-NLS-1$
				
			case HN_GROESSER_35_BIS_45_PRZ:
				return "36 - 45 %"; //$NON-NLS-1$
				
			case HN_GROESSER_45_BIS_55_PRZ:
				return "46 - 55 %"; //$NON-NLS-1$
				
			case HN_GROESSER_55_PRZ:
				return "> 55 %"; //$NON-NLS-1$
				
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

		list.add(ModelStrings.getString("ArbeitsobjektVorruecken2018.Bhd_cm"),  bhd_cm); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektVorruecken2018.Standortguete"),  standortguete); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektVorruecken2018.Mittelstammvolumen"),  getMittelstammvolumen()); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektVorruecken2018.Hangneigung"),  hangneigung); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektVorruecken2018.AbzopfenDerKrone"),  abzopfenDerKrone); //$NON-NLS-1$
		
		return list;
	}

}
