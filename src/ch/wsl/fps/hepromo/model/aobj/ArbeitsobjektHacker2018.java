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
public class ArbeitsobjektHacker2018 extends Arbeitsobjekt {

	private static final long serialVersionUID = 1L;
	private Zielsortiment zielsortiment;
	
	
	public Zielsortiment getZielsortiment() {
		return zielsortiment;
	}

	public void setZielsortiment(Zielsortiment zielsortiment) {
		this.zielsortiment = zielsortiment;
	}
	
	
	
	public enum Zielsortiment {
		Waldrestholz,
		Energierundholz;
		
		@Override
		public String toString() {
			switch(this) {
			case Waldrestholz:
				return ModelStrings.getString("ArbeitsobjektHacker2018.enumWaldrestholz"); //$NON-NLS-1$
				
			case Energierundholz:
				return ModelStrings.getString("ArbeitsobjektHacker2018.enumEnergierundholz"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}

	

	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add(ModelStrings.getString("ArbeitsobjektHacker2018.Poltersortiment"),  zielsortiment); //$NON-NLS-1$
		
		return list;
	}
	
	
	@Override
	protected String getPdfLabelHolzmenge() {
		return ModelStrings.getString("ArbeitsobjektHacker2018.Hackgutmenge_Srm");
	}
}
