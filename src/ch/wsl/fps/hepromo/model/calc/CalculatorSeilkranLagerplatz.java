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
package ch.wsl.fps.hepromo.model.calc;

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSeilkranLagerplatz;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranLagerplatz;

/**
 * 
 * @author Stefan Holm
 *
 */
public class CalculatorSeilkranLagerplatz extends AbstractCalculatorSingleModel {
	
	
	
	public CalculatorSeilkranLagerplatz(HeProMoInputData inputData) {
		super(inputData);
	}

	

	@Override
	public ArbeitsobjektSeilkranLagerplatz getArbeitsobjekt() {
		return (ArbeitsobjektSeilkranLagerplatz) super.getArbeitsobjekt();
	}
	
	@Override
	public ArbeitssystemSeilkranLagerplatz getArbeitssystem() {
		return (ArbeitssystemSeilkranLagerplatz) super.getArbeitssystem();
	}
	
	
	
	
	@Override
	protected double calcPsh0_hProM3() {
		double psh0_Lagerplatz = getArbeitsobjekt().getSystemzeitSeilen_psh0proM();// * (getArbeitssystem().getLaufzeitKranfahrzeug_Prz() / 100.0); //gemäss doku sollte hier noch mit anteil_psh0Seilen multipliziert werden

		// WICHTIG: Der Rindenabzugsfaktor wird bereits in getSystemzeitSeilen_psh0proM() berücksichtigt!
//		if (super.rindenAbzugBeruecksichtigen) {
//			psh0_Lagerplatz /= getArbeitsobjekt().getRindenAbzugFaktor();
//		}
		
		return psh0_Lagerplatz;
	}
	
	
	
	@Override
	protected double getAnzahlPersonalVollzeitAequivalente() {
		return getArbeitssystem().getAnzahlPersonal() * getArbeitssystem().getEinsatzzeitPersonal_Prz() / 100.0;
	}

	
	
	@Override
	protected double getLaufzeitAnteilMaschine() {
		return getArbeitssystem().getLaufzeitKranfahrzeug_Prz() / 100.0;
	}

}
