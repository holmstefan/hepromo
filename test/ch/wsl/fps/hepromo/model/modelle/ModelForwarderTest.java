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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektForwarder.FahrstreckenArt;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.HindernisKategorie;
import ch.wsl.fps.hepromo.model.aobj.FeinerschliessungGelaendebedingungen.NeigungsKategorie;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemForwarder.ForwarderTyp;

/**
 * 
 * @author Stefan Holm
 *
 */
@Test
public class ModelForwarderTest extends AbstractModelTest {
	
	//Modell "Forwarder" konnte nicht exakt nachgebaut werden, deshalb wird
	// hier mit einer grösseren Toleranz getestet.
	@Override
	protected void assertEqualsDynamicDelta(double actual, double expected) {
		double relativeDelta = 1.008;
		double absoluteDelta = expected * (relativeDelta - 1.0);
		
		assertEquals(actual, expected, absoluteDelta);
	}

	@Override
	protected String getCsvPath() {
		return "testcases/Forwarder.csv";
	}

	@Override
	protected AbstractModel getModel(HeProMoInputData inputData) {
		return new ModelForwarder(inputData);
	}

	@Override
	protected Arbeitsobjekt parseArbeitsobjektData(String[] testcase) {
		ArbeitsobjektForwarder ao = new ArbeitsobjektForwarder();
		ao.setLaubholzAnteil_Prz(				Integer.valueOf(testcase[0]));
		ao.setHolzmenge_m3(						Double.valueOf(testcase[1]));
		ao.setDurchschnittlicherBhdAushieb_cm(	Integer.valueOf(testcase[2]));
		ao.setDurchschnittlicheHolzlaenge_m(	Integer.valueOf(testcase[3]));
		ao.setDurchschnittlicheAnzahlSortimente(Double.valueOf(testcase[4]));
		ao.setAnzahlVerschiedeneSortimente(		Integer.valueOf(testcase[5]));
		ao.setAnteilKrumm_Prz(					Integer.valueOf(testcase[6]));
		ao.setFahrstreckeAufStrasse(			Integer.valueOf(testcase[7]));
		ao.setFahrstreckenArtStrasse(			FahrstreckenArt.valueOf(testcase[8]));
		ao.setFahrstreckeAufFeinerschliessung(	Integer.valueOf(testcase[9]));
		ao.setFahrstreckenArtFeinerschliessung(	FahrstreckenArt.valueOf(testcase[10]));
		
		ao.setErschliessungsLaengeEinseitig_m(					Integer.valueOf(testcase[11]));
		ao.setErschliessungsLaengeEinseitigAnteilStrasse_Prz(	Integer.valueOf(testcase[12]));
		ao.setErschliessungsLaengeBeidseitig_m(					Integer.valueOf(testcase[13]));
		ao.setErschliessungsLaengeBeidseitigAnteilStrasse_Prz(	Integer.valueOf(testcase[14]));
		ao.setHindernisKategorieFeinerschliessung(				HindernisKategorie.valueOf(testcase[15]));
		ao.setNeigungsKategorieFeinerschliessung(				NeigungsKategorie.valueOf(testcase[16]));
		ao.setFahrstreckenAnteilStrasseLastfahrtAufwaerts_Prz(	Integer.valueOf(testcase[17]));
		ao.setFahrstreckenAnteilStrasseLastfahrtAbwaerts_Prz(	Integer.valueOf(testcase[18]));
		ao.setFahrstreckenAnteilStrasseLeerfahrtAufwaerts_Prz(	Integer.valueOf(testcase[19]));
		ao.setFahrstreckenAnteilStrasseLeerfahrtAbwaerts_Prz(	Integer.valueOf(testcase[20]));
		ao.setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAufwaerts_Prz(	Integer.valueOf(testcase[21]));
		ao.setFahrstreckenAnteilFeinerschliessungFahrenBeimLadenAbwaerts_Prz(	Integer.valueOf(testcase[22]));
		ao.setFahrstreckenAnteilFeinerschliessungLastfahrtAufwaerts_Prz(		Integer.valueOf(testcase[23]));
		ao.setFahrstreckenAnteilFeinerschliessungLastfahrtAbwaerts_Prz(			Integer.valueOf(testcase[24]));
		ao.setFahrstreckenAnteilFeinerschliessungLeerfahrtAufwaerts_Prz(		Integer.valueOf(testcase[25]));
		ao.setFahrstreckenAnteilFeinerschliessungLeerfahrtAbwaerts_Prz(			Integer.valueOf(testcase[26]));
		return ao;
	}

	@Override
	protected Arbeitssystem parseArbeitssystemData(String[] testcase) {
		ArbeitssystemForwarder as = new ArbeitssystemForwarder();
		as.setKostensatzPersonal1_proH(		Double.valueOf(testcase[27]));
		as.setKostensatzMaschine1_proH(		Double.valueOf(testcase[28]));
		as.setForwarderTyp(					ForwarderTyp.valueOf(testcase[29]));
		as.setLadeQuerschnittsFlaeche_m2(	Double.valueOf(testcase[30]));
		as.setTaeglicheArbeitszeit_Min(		Integer.valueOf(testcase[31]));
		as.setWegzeitenUndPausen_Min(		Integer.valueOf(testcase[32]));
		as.setUmsetzenBetrag_CHF(			Double.valueOf(testcase[33]));
		as.setUmsetzenZeit_h(				Double.valueOf(testcase[34]));
		as.setWeitereAufwaendeBetrag_CHF(	Double.valueOf(testcase[35]));
		as.setWeitereAufwaendeZeit_h(		Double.valueOf(testcase[36]));
		return as;
	}

	@Override
	protected int getFirstOutputField() {
		return 40;
	}
	
	@Override
	protected int getLastOutputField() {
		return 55;
	}
	
}
