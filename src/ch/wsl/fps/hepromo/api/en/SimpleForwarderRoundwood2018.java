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

import ch.wsl.fps.hepromo.api.SimpleForwarderRundholz2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleForwarderRoundwood2018 extends AbstractSimpleForwarder2018<SimpleForwarderRundholz2018> {

	// Work item
	public static final double DEFAULT_ROUNDWOOD_VOLUME_M3_OB = SimpleForwarderRundholz2018.DEFAULT_RUNDHOLZMENGE_M3_IR;
	
	
	public SimpleForwarderRoundwood2018() {
		super(new SimpleForwarderRundholz2018());
	}
	
	
	public void setRoundwoodVolume_m3ob(double value) {
		adaptee.setRundholzmenge_m3iR(value);
	}
	

	public double getCostsMachineOperator_perM3ub() {
		return adaptee.getKostenMaschinist_proM3oR();
	}

	public double getCostsForwarder_perM3ub() {
		return adaptee.getKostenForwarder_proM3oR();
	}

	public double getCostsTransfer_perM3ub() {
		return adaptee.getKostenUmsetzen_proM3oR();
	}

	public double getCostsFurtherWork_perM3ub() {
		return adaptee.getKostenWeitereAufwaende_proM3oR();
	}
	
	public double getCostsTotal_perM3ub() {
		return adaptee.getKostenTotal_proM3oR();
	}

}
