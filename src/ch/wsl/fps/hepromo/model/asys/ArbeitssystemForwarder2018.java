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
import ch.wsl.fps.hepromo.model.ModelStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemForwarder2018 extends Arbeitssystem {
	
	private Forwardertyp forwardertyp;
	
	
	public Forwardertyp getForwardertyp() {
		return forwardertyp;
	}
	
	public void setForwardertyp(Forwardertyp forwardertyp) {
		this.forwardertyp = forwardertyp;
	}


	public enum Forwardertyp {
		Mittel,
		Gross;
		
		@Override
		public String toString() {
			switch(this) {
			case Mittel:
				return ModelStrings.getString("ArbeitssystemForwarder2018.enumMittel"); //$NON-NLS-1$
				
			case Gross:
				return ModelStrings.getString("ArbeitssystemForwarder2018.enumGross"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.addOnTop(ModelStrings.getString("ArbeitssystemForwarder2018.Forwardertyp"), forwardertyp); //$NON-NLS-1$
		
		return list;
	}

}
