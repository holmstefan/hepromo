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

import ch.wsl.fps.hepromo.api.SimpleForwarderEnergieholz2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleForwarderEnergyWood2018 extends AbstractSimpleForwarder2018<SimpleForwarderEnergieholz2018> {

	// Work item
	public static final double DEFAULT_ENERGY_WOOD_VOLUME_M3_OB = SimpleForwarderEnergieholz2018.DEFAULT_ENERGIEHOLZMENGE_M3_IR;
	public static final double DEFAULT_TOP_DIAMTER_ROUNDWOOD_CM = SimpleForwarderEnergieholz2018.DEFAULT_ZOPFDURCHMESSER_RUNDHOLZ_CM;
	public static final double DEFAULT_ENERGY_WOOD_VOLUME_M3_OB_PER_HA = SimpleForwarderEnergieholz2018.DEFAULT_ENERGIEHOLZANFALL_M3_IR_PRO_HA;
	
	
	public SimpleForwarderEnergyWood2018() {
		super(new SimpleForwarderEnergieholz2018());
	}
	
	public void setEnergyWoodVolume_m3ob(double value) {
		adaptee.setEnergieholzmenge_m3iR(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setTopDiameterRoundwood_cm(double value) {
		adaptee.setZopfdurchmesser_Rundholz_cm(value);
	}
	
	/**
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setEnergyWoodVolume_m3obPerHa(double value) {
		adaptee.setEnergieholzanfall_m3iRproHa(value);
	}
	

	public double getCostsMachineOperator_perM3ob() {
		return adaptee.getKostenMaschinist_proM3iR();
	}

	public double getCostsForwarder_perM3ob() {
		return adaptee.getKostenForwarder_proM3iR();
	}

	public double getCostsTransfer_perM3ob() {
		return adaptee.getKostenUmsetzen_proM3iR();
	}

	public double getCostsFurtherWork_perM3ob() {
		return adaptee.getKostenWeitereAufwaende_proM3iR();
	}
	
	public double getCostsTotal_perM3ob() {
		return adaptee.getKostenTotal_proM3iR();
	}

}
