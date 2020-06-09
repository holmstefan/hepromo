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

import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.FoermigkeitArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LaubholzAnteilArrayWithSelection;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektRadharvester2014.LiegendesHolzArrayWithSelection;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemRadharvester2014.Maschinentyp;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelRadharvester2014Test extends AbstractModel2014Test {

	@Override
	protected String getCsvPath() {
		return "testcases/Radharvester2014.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelRadharvester2014(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektRadharvester2014 ao = new ArbeitsobjektRadharvester2014();
		ao.setHolzmenge_m3(						Double.valueOf(testcase[0]));
		ao.setRindenabzugsfaktorManuellGesetzt(	Boolean.valueOf(testcase[1]));
		ao.setRindenabzugsfaktorManuell(		Double.valueOf(testcase[2]));
		ao.setBhd_cm(							Integer.valueOf(testcase[3]));
		
		FoermigkeitArrayWithSelection foermigkeit = ao.getFoermigkeitArrayWithSelection();
		foermigkeit.setSelection( foermigkeit.allValues[		Integer.valueOf(testcase[4])	]);
		foermigkeit.allValues[3].setWert(						Double.valueOf(testcase[5])		);
		
		LaubholzAnteilArrayWithSelection laubholzAnteil = ao.getLaubholzAnteilArrayWithSelection();
		laubholzAnteil.setSelection( laubholzAnteil.allValues[	Integer.valueOf(testcase[6])	]);
		laubholzAnteil.allValues[5].setWert(					Double.valueOf(testcase[7])		);
		
		LiegendesHolzArrayWithSelection liegendesHolz = ao.getLiegendesHolzArrayWithSelection();
		liegendesHolz.setSelection( liegendesHolz.allValues[	Integer.valueOf(testcase[8])	]);
		liegendesHolz.allValues[5].setWert(						Double.valueOf(testcase[9])	);
		
		return ao;
	}

	@Override
	protected ArbeitssystemRadharvester2014 parseArbeitssystemData(String[] testcase) {
		ArbeitssystemRadharvester2014 as = new ArbeitssystemRadharvester2014();
		as.setMaschinentyp(					Maschinentyp.valueOf(testcase[10]));
		as.setKostensatzPersonal1_proH(			Double.valueOf(testcase[11]));
		as.setKostensatzMaschine1_proH(			Double.valueOf(testcase[12]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[13]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[14]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[15]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[16]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[17]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[18]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 22;
	}
	
	@Override
	protected int getLastOutputField() {
		return 38;
	}
	
}
