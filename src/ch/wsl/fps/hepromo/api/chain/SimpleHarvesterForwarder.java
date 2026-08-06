/*******************************************************************************
 * Copyright 2026 Stefan Holm
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
package ch.wsl.fps.hepromo.api.chain;

import ch.wsl.fps.hepromo.api.SimpleForwarderRundholz2018;
import ch.wsl.fps.hepromo.api.SimpleRadharvester2014;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHarvesterForwarder extends AbstractSimpleChain {

	// Work item
	public static final double DEFAULT_TIMBER_VOLUME_M3_OB = SimpleRadharvester2014.DEFAULT_HOLZMENGE_M3_IR;
	public static final double DEFAULT_MEAN_DBH_CM = SimpleRadharvester2014.DEFAULT_MITTLERER_BHD_CM;
	public static final double DEFAULT_STEM_FORM_CATEGORY = SimpleRadharvester2014.DEFAULT_FOERMIGKEIT_KATEGORIE;
	public static final double DEFAULT_PERCENTAGE_OF_BROADLEAVES_CATEGORY = SimpleRadharvester2014.DEFAULT_ANTEIL_LAUBHOLZ_KATEGORIE;
	public static final double DEFAULT_PREFELLED_TREES_CATEGORY = SimpleRadharvester2014.DEFAULT_LIEGENDES_HOLZ_KATEGORIE;

	@SuppressWarnings("unused")
	private static final double DEFAULT_ROUNDWOOD_VOLUME_M3_OB = SimpleForwarderRundholz2018.DEFAULT_RUNDHOLZMENGE_M3_IR; //this constant and the corresponding setter are not used in this process chain
//	public static final double DEFAULT_MEAN_DBH_CM = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_MITTLERER_BHD_CM; // we use the default dbh of the harvester model here
	public static final double DEFAULT_SKIDDING_DISTANCE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_RUECKEENTFERNUNG_KATEGORIE;
	public static final double DEFAULT_SLOPE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_HANGNEIGUNG_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_ASSORTMENTS_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ANZAHL_SORTIMENTE_KATEGORIE;
	public static final double DEFAULT_DIFFICULTIES_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ERSCHWERNISSE_KATEGORIE;
	public static final double DEFAULT_DISTANCE_BETWEEN_SKID_TRAILS_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_ABSTAND_RUECKEGASSEN_KATEGORIE;

	public static final double DEFAULT_MEAN_SLOPE_CATEGORY = SimpleRadharvester2014.DEFAULT_MITTLERE_HANGNEIGUNG_KATEGORIE;
	public static final double DEFAULT_USE_OF_WINCH_ASSISTED_HARVESTING_CATEGORY = SimpleRadharvester2014.DEFAULT_EINSATZ_TRAKTIONSHILFSWINDE_KATEGORIE;
	public static final double DEFAULT_NUMBER_OF_SKID_TRAILS_WITH_WINCH_ASSISTED_HARVESTING_NUMBER = SimpleRadharvester2014.DEFAULT_ANZAHL_RUECKEGASSEN_MIT_THW_ANZAHL;
	public static final double DEFAULT_HARVESTER_TYPE_CATEGORY = SimpleRadharvester2014.DEFAULT_MASCHINENTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_ENGINE_DRIVER_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_HARVESTER_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_RADHARVESTER_PRO_H;
	public static final double DEFAULT_COSTS_WINCH_ASSISTED_HARVESTING_PER_H = SimpleRadharvester2014.DEFAULT_KOSTEN_TRAKTIONSHILFSWINDE_PRO_H;

	public static final double DEFAULT_FORWARDER_TYPE_CATEGORY = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_FORWARDERTYP_KATEGORIE;
	public static final double DEFAULT_COSTS_MACHINE_OPERATOR_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_MASCHINIST_PRO_H;
	public static final double DEFAULT_COSTS_FORWARDER_PER_H = ch.wsl.fps.hepromo.api.AbstractSimpleForwarder2018.DEFAULT_KOSTEN_FORWARDER_PRO_H;

	private final SimpleRadharvester2014 harvester;
	private final SimpleForwarderRundholz2018 forwarder;



	public SimpleHarvesterForwarder() {
		this(new SimpleRadharvester2014(), new SimpleForwarderRundholz2018());
	}

	private SimpleHarvesterForwarder(SimpleRadharvester2014 harvester, SimpleForwarderRundholz2018 forwarder) {
		super(harvester, forwarder);
		this.harvester = harvester;
		this.forwarder = forwarder;
		this.setTimberVolume_m3ob(DEFAULT_TIMBER_VOLUME_M3_OB); // to ensure both models use the same default timber volume
		this.setMeanDbh_cm(DEFAULT_MEAN_DBH_CM); // to ensure both models use the same default dbh
	}
	
	
	public void setTimberVolume_m3ob(double value) {
		harvester.setHolzmenge_m3iR(value);
		forwarder.setRundholzmenge_m3iR(value);
	}
	
	
	@SuppressWarnings("unused") // instead of this method, setTimberVolume_m3ob() should be used for both models 
	private void setRoundwoodVolume_m3ob(double value) {
		forwarder.setRundholzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMeanDbh_cm(double value) {
		harvester.setMittlererBhd_cm(value);
		forwarder.setMittlererBhd_cm(value);
	}
	
	/**
	 * 
	 * @param value slightly tapering=1, normally tapering=2, tapering=3
	 */
	public void setStemForm_category(double value) {
		harvester.setFoermigkeit_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 0%=1, up to 25%=2, up to 50%=3, up to 75%=4, up to 100%=5
	 */
	public void setPercentageOfBroadleaves_category(double value) {
		harvester.setAnteilLaubholz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value none%=1, pre-delivered trees up to 25%=2, predelivered trees up to 50%=3, trees felled towards the skid trail up to 25%=4, trees felled towards the skid trails up to 50%=5
	 */
	public void setPrefelledTrees_category(double value) {
		harvester.setLiegendesHolz_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value up to 30%=1, 30-39%=2, 40-49%=3, 50-60%=4
	 */
	public void setMeanSlope_category(double value) {
		harvester.setMittlereHangneigung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value yes=1, no=2
	 */
	public void setUseOfWinchAssistedHarvesting_category(double value) {
		harvester.setEinsatzTraktionshilfswinde_Kategorie(value);
		forwarder.setEinsatzTraktionshilfswinde_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setNumberOfSkidTrailsWithWinchAssistedHarvesting_number(double value) {
		harvester.setAnzahlRueckegassenMitThw_Anzahl(value);
		forwarder.setAnzahlRueckegassenMitThw_Anzahl(value);
	}
	
	/**
	 * 
	 * @param value &lt;200m=1, 201-400m=2, 401-600m=3, 601-900m=4, 901-1200m=5, &gt;1200m=6
	 */
	public void setSkiddingDistance_category(double value) {
		forwarder.setRueckeentfernung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value &lt;15%=1, 15-25%=2, 25-35%=3, 35-45%=4, &gt;45%=5
	 */
	public void setSlope_category(double value) {
		forwarder.setHangneigung_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 1-3=1, 4-6=2, &ge;7=3
	 */
	public void setNumberOfAssortments_category(double value) {
		forwarder.setAnzahlSortimente_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value none=1, few=2, many=3
	 */
	public void setDifficulties_category(double value) {
		forwarder.setErschwernisse_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value 40m=1, 30m=2, 20m=3
	 */
	public void setDistanceBetweenSkidTrails_category(double value) {
		forwarder.setAbstandRueckegassen_Kategorie(value);
	}
	
	/**
	 * 
	 * @param value medium=1, big=2
	 */
	public void setHarvesterType_category(double value) {
		harvester.setMaschinentyp_Kategorie(value);
	}
	
	/**
	 * This method belongs to the harvester model
	 */
	public void setCostsEngineDriver_perH(double value) {
		harvester.setKostenMaschinist_proH(value);
	}
	
	public void setCostsHarvester_perH(double value) {
		harvester.setKostenRadharvester_proH(value);
	}
	
	public void setCostsWinchAssistedHarvesting_perH(double value) {
		harvester.setKostenTraktionshilfswinde_proH(value);
		forwarder.setKostenTraktionshilfswinde_proH(value);
	}
	
	/**
	 * 
	 * @param value medium=1, big=2
	 */
	public void setForwarderType_category(double value) {
		forwarder.setForwardertyp_Kategorie(value);
	}
	
	/**
	 * This method belongs to the forwarder model
	 */
	public void setCostsMachineOperator_perH(double value) {
		forwarder.setKostenMaschinist_proH(value);
	}
	
	public void setCostsForwarder_perH(double value) {
		forwarder.setKostenForwarder_proH(value);
	}
	
	/**
	 * This method belongs to the harvester model
	 */
	public double getTimeEngineDriver_WPPH() {
		return harvester.getZeitMaschinist_WPPH();
	}

	public double getTimeHarvester_PMH15() {
		return harvester.getZeitRadharvester_PMH15();
	}

	
	/**
	 * This method belongs to the forwarder model
	 */
	public double getTimeMachineOperator_WPPH() {
		return forwarder.getZeitMaschinist_WPPH();
	}

	public double getTimeForwarder_PMH15() {
		return forwarder.getZeitForwarder_PMH15();
	}

	public double getTimeWinchAssistedHarvesting_ISH() {
		return harvester.getZeitTraktionshilfswinde_ISH()
				+ forwarder.getZeitTraktionshilfswinde_ISH();
	}
	

	/**
	 * This method belongs to the harvester model
	 */
	public double getCostsEngineDriver_perM3ub() {
		return harvester.getKostenMaschinist_proM3oR();
	}

	public double getCostsHarvester_perM3ub() {
		return harvester.getKostenRadharvester_proM3oR();
	}
	
	/**
	 * This method belongs to the forwarder model
	 */
	public double getCostsMachineOperator_perM3ub() {
		return forwarder.getKostenMaschinist_proM3oR();
	}

	public double getCostsForwarder_perM3ub() {
		return forwarder.getKostenForwarder_proM3oR();
	}

	public double getCostsWinchAssistedHarvesting_perM3ub() {
		return harvester.getKostenTraktionshilfswinde_proM3oR()
				+ forwarder.getKostenTraktionshilfswinde_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return harvester.getKostenUmsetzen_proM3oR()
				+ forwarder.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return harvester.getKostenWeitereAufwaende_proM3oR()
				+ forwarder.getKostenWeitereAufwaende_proM3oR();
	}
	
	public double getCostsTotal_perM3ub() {
		return harvester.getKostenTotal_proM3oR()
				+ forwarder.getKostenTotal_proM3oR();
	}
	

	/**
	 * This method belongs to the harvester model
	 */
	public double getCostsEngineDriver_total() {
		return harvester.getKostenMaschinist_total();
	}

	public double getCostsHarvester_total() {
		return harvester.getKostenRadharvester_total();
	}
	
	/**
	 * This method belongs to the forwarder model
	 */
	public double getCostsMachineOperator_total() {
		return forwarder.getKostenMaschinist_total();
	}

	public double getCostsForwarder_total() {
		return forwarder.getKostenForwarder_total();
	}

	public double getCostsWinchAssistedHarvesting_total() {
		return harvester.getKostenTraktionshilfswinde_total()
				+ forwarder.getKostenTraktionshilfswinde_total();
	}

	public double getCostsTransfer_total() {
		return harvester.getKostenUmsetzen_total()
				+ forwarder.getKostenUmsetzen_total();
	}

	public double getCostsFurtherWork_total() {
		return harvester.getKostenWeitereAufwaende_total()
				+ forwarder.getKostenWeitereAufwaende_total();
	}
	
	public double getProductivityHarvester_m3obPerPMH15() {
		return harvester.getProduktivitaet_m3iRproPMH15();
	}
	
	
	public double getProductivityForwarder_m3obPerPMH15() {
		return forwarder.getProduktivitaet_m3iRproPMH15();
	}
	
}
