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
package ch.wsl.fps.hepromo.model;

/**
 * 
 * @author Stefan Holm
 *
 */
public class MockErgebnisKombiseilgeraet2018 extends MockErgebnis {

	private double zeitVerzugsfahrzeug;
	private double kostenVerzugsfahrzeug_proM3;
	private double kostenVerzugsfahrzeug_total;
	
	private double produktivitaet_m3oRproWSH;
	
	
	public double getZeitVerzugsfahrzeug() {
		return zeitVerzugsfahrzeug;
	}
	
	public void setZeitVerzugsfahrzeug(double zeitVerzugsfahrzeug) {
		this.zeitVerzugsfahrzeug = zeitVerzugsfahrzeug;
	}
	
	public double getKostenVerzugsfahrzeug_proM3() {
		return kostenVerzugsfahrzeug_proM3;
	}
	
	public void setKostenVerzugsfahrzeug_proM3(double kostenVerzugsfahrzeug_proM3) {
		this.kostenVerzugsfahrzeug_proM3 = kostenVerzugsfahrzeug_proM3;
	}
	
	public double getKostenVerzugsfahrzeug_total() {
		return kostenVerzugsfahrzeug_total;
	}
	
	public void setKostenVerzugsfahrzeug_total(double kostenVerzugsfahrzeug_total) {
		this.kostenVerzugsfahrzeug_total = kostenVerzugsfahrzeug_total;
	}

	public double getProduktivitaet_m3oRproWSH() {
		return produktivitaet_m3oRproWSH;
	}

	public void setProduktivitaet_m3oRproWSH(double produktivitaet_m3oRproWSH) {
		this.produktivitaet_m3oRproWSH = produktivitaet_m3oRproWSH;
	}
}
