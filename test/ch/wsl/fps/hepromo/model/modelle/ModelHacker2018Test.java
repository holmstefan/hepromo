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
package ch.wsl.fps.hepromo.model.modelle;

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorHacker2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelHacker2018Test extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/Hacker2018.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelHacker2018(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektHacker2018 ao = new ArbeitsobjektHacker2018();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setZielsortiment(					Zielsortiment.valueOf(testcase[1]));
		
		return ao;
	}

	@Override
	protected ArbeitssystemHacker2018 parseArbeitssystemData(String[] testcase) {
		ArbeitssystemHacker2018 as = new ArbeitssystemHacker2018();
		as.setHackerMotorleistung(ArbeitssystemHacker2018.getBenutzerdefinierteHackerMotorleistung(Integer.valueOf(testcase[ 2])));
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[ 3]));

		Double kostensatzMaschine1_proPMH15 = Double.valueOf(testcase[ 4]);
		Double kostensatzMaschine1_proSrm   = Double.valueOf(testcase[ 5]);
		if (kostensatzMaschine1_proPMH15 > 0 && kostensatzMaschine1_proSrm < 0) {
			as.setKostensatzMaschine1_proH(kostensatzMaschine1_proPMH15);
		}
		else if (kostensatzMaschine1_proPMH15 < 0 && kostensatzMaschine1_proSrm > 0) {
			Zielsortiment sortiment = Zielsortiment.valueOf(testcase[1]);
			int motorleistung_kw = Integer.valueOf(testcase[ 2]);
			double convertedValue = CalculatorHacker2018.convertFromCHFproSrmToCHFproPMH15(kostensatzMaschine1_proSrm, sortiment, motorleistung_kw);
			as.setKostensatzMaschine1_proH(convertedValue);
		}
		else {
			throw new RuntimeException("Der Stundensatz für den Hacker muss entweder in Fr./PMH15 oder Fr./Srm angegeben werden!");
		}
	
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[ 6]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[ 7]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[ 8]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[ 9]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[10]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[11]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 15;
	}
	
	@Override
	protected int getLastOutputField() {
		return 30;
	}

}
