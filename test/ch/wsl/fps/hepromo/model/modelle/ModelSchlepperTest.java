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
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.AnzahlSortimente;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Fahrentfernung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckeart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Rueckebedingungen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.RueckenImSaft;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Schlagordnung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchlepper.Zuzugentfernung;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.RueckgehilfeEinsatzanteil;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSchlepper.SchlepperTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelSchlepperTest extends AbstractModelTest {

	@Override
	protected String getCsvPath() {
		return "testcases/Schlepper.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelSchlepper(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektSchlepper ao = new ArbeitsobjektSchlepper();
		ao.setMittlererStueckInhalt(Double.valueOf(testcase[0]));
		ao.setHolzmenge_m3(Double.valueOf(testcase[1]));
		ao.setSchlagordnung(Schlagordnung.valueOf(testcase[2]));
		ao.setAnzahlSortimente(AnzahlSortimente.valueOf(testcase[3]));
		ao.setFahrentfernung(Fahrentfernung.valueOf(testcase[4]));
		ao.setZuzugentfernung(Zuzugentfernung.valueOf(testcase[5]));
		ao.setRueckeart(Rueckeart.valueOf(testcase[6]));
		ao.setRueckenImSaft(RueckenImSaft.valueOf(testcase[7]));
		ao.setRueckeBedingungen(Rueckebedingungen.valueOf(testcase[8]));
		return ao;
	}
	
	
	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemSchlepper as = new ArbeitssystemSchlepper();
		as.setKostensatzPersonal1_proH(Double.valueOf(testcase[9]));
		as.setRueckegehilfe(Boolean.valueOf(testcase[10]));
		as.setKostensatzRueckegehilfe(Double.valueOf(testcase[11]));
		as.setRueckgehilfeEinsatzanteil(RueckgehilfeEinsatzanteil.valueOf(testcase[12]));
		as.setKostensatzMaschine1_proH(Double.valueOf(testcase[13]));
		as.setSchlepperTyp(SchlepperTyp.valueOf(testcase[14]));
		as.setTaeglicheArbeitszeit_Min(Integer.valueOf(testcase[15]));
		as.setWegzeitenUndPausen_Min(Integer.valueOf(testcase[16]));
		as.setUmsetzenBetrag_CHF(Double.valueOf(testcase[17]));
		as.setUmsetzenZeit_h(Double.valueOf(testcase[18]));
		as.setWeitereAufwaendeBetrag_CHF(Double.valueOf(testcase[19]));
		as.setWeitereAufwaendeZeit_h(Double.valueOf(testcase[20]));
		return as;
	}	

	@Override
	protected int getFirstOutputField() {
		return 24;
	}

}
