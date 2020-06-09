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
package ch.wsl.fps.hepromo.api.en;

import ch.wsl.fps.hepromo.api.SimpleHackschnitzeltransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleWoodchipsTransport2018 extends AbstractSimpleModel<SimpleHackschnitzeltransport2018> {

	// Work item
	public static final double DEFAULT_VOLUME_OF_WOODCHIPS_BCM = SimpleHackschnitzeltransport2018.DEFAULT_HACKSCHNITZELMENGE_SRM;
	public static final double DEFAULT_LIFTING_OF_LOADED_CONTAINERS_CATEGORY = SimpleHackschnitzeltransport2018.DEFAULT_AUFNEHMEN_BELADENER_MULDEN_KATEGORIE;
	public static final double DEFAULT_KIND_OF_WOOD_ON_PILE_CATEGORY = SimpleHackschnitzeltransport2018.DEFAULT_POLTERSORTIMENT_KATEGORIE;
	public static final double DEFAULT_ENGINE_POWER_CHIPPER_KW = SimpleHackschnitzeltransport2018.DEFAULT_MOTORLEISTUNG_HACKER_KW;
	public static final double DEFAULT_DRIVING_DISTANCE_ON_FOREST_ROAD_KM = SimpleHackschnitzeltransport2018.DEFAULT_FAHRSTRECKE_WALDSTRASSE_KM;
	public static final double DEFAULT_DRIVING_DISTANCE_INSIDE_AND_OUTSIDE_OF_LOCALITIES_KM = SimpleHackschnitzeltransport2018.DEFAULT_FAHRSTRECKE_INNERORTS_UND_AUSSERORTS_KM;
	public static final double DEFAULT_DRIVING_DISTANCE_ON_MOTORWAY_KM = SimpleHackschnitzeltransport2018.DEFAULT_FAHRSTRECKE_AUTOBAHN_KM;

	// Work system
	public static final double DEFAULT_CONTAINER_CAPACITY_BCM = SimpleHackschnitzeltransport2018.DEFAULT_MULDENINHALT_SRM;
	public static final double DEFAULT_COSTS_TRANSPORT_VEHICLE_INCL_DRIVER_PER_H = SimpleHackschnitzeltransport2018.DEFAULT_KOSTEN_TRANSPORTFAHRZEUG_INKL_FAHRER_PRO_H;
	
	/** Break times are included in the costs of the transport vehicle incl. driver, therefore this value is 0 here */
	public static final double DEFAULT_TRAVEL_AND_BREAK_TIMES_MIN = SimpleHackschnitzeltransport2018.DEFAULT_WEGZEITEN_UND_PAUSEN_MIN;
	

	public SimpleWoodchipsTransport2018() {
		super(new SimpleHackschnitzeltransport2018());
	}
	
	
	public void setVolumeOfWoodchips_bcm(double value) {
		adaptee.setHackschnitzelmenge_Srm(value);
	}
	
	/**
	 * 
	 * @param value yes (lifting of loaded containers)=1, no (chipping in container mounted on transport vehicle)=2
	 */
	public void setLiftingOfLoadedContainers_category(double value) {
		adaptee.setAufnehmenBeladenerMulden_Kategorie(value);
	}
	
	/**
	 * This parameter is only relevant if liftingOfLoadedContainers = no
	 * 
	 * @param value forest residues=1, energy round wood=2
	 */
	public void setKindOfWoodOnPile_category(double value) {
		adaptee.setPoltersortiment_Kategorie(value);
	}
	
	/**
	 * This parameter is only relevant if liftingOfLoadedContainers = no
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setEnginePowerChipper_kW(double value) {
		adaptee.setMotorleistungHacker_kW(value);
	}
	
	public void setDrivingDistanceForestRoad_km(double value) {
		adaptee.setFahrstreckeWaldstrasse_km(value);
	}
	
	public void setDrivingDistanceInsideAndOutsideOfLocalities_km(double value) {
		adaptee.setFahrstreckeInnerortsUndAusserorts_km(value);
	}
	
	public void setDrivingDistanceMotorway_km(double value) {
		adaptee.setFahrstreckeAutobahn_km(value);
	}
	
	public void setContainerCapacity_bcm(double value) {
		adaptee.setMuldeninhalt_Srm(value);
	}
	
	public void setCostsTransportVehicleInclDriver_perH(double value) {
		adaptee.setKostenTransportfahrzeugInklFahrer_proH(value);
	}
	
	
	public double getTimeTransportVehicleInclDriver_PMH15() {
		return adaptee.getZeitTransportfahrzeugInklFahrer_PMH15();
	}
	

	public double getCostsTransportVehicleInclDriver_perBcm() {
		return adaptee.getKostenTransportfahrzeugInklFahrer_proSrm();
	}

	public double getCostsFurtherWork_perBcm() {
		return adaptee.getKostenWeitereAufwaende_proSrm();
	}

	public double getCostsTotal_perBcm() {
		return adaptee.getKostenTotal_proSrm();
	}
	

	public double getCostsTransportVehicleInclDriver_total() {
		return adaptee.getKostenTransportfahrzeugInklFahrer_total();
	}

	public double getCostsFurtherWork_total() {
		return adaptee.getKostenWeitereAufwaende_total();
	}


	public double getProductivity_bcmPerPMH15() {
		return adaptee.getProduktivitaet_SrmProPMH15();
	}

}
