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
public class ArbeitsobjektSeilkranLagerplatz extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private double systemzeitSeilen_psh0proM;

	public double getSystemzeitSeilen_psh0proM() {
		return systemzeitSeilen_psh0proM;
	}

	public void setSystemzeitSeilen_psh0proM(double systemzeitSeilen_psh0proM) {
		this.systemzeitSeilen_psh0proM = systemzeitSeilen_psh0proM;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add("Systemzeit seilen (psh0 pro m3)", systemzeitSeilen_psh0proM);
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}

}
