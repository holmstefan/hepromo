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
public class MockErgebnisHelikopterGesamt extends MockErgebnis {
	
	private double zeitHelifirma;
	private double zeitTotalFliegen;
	private double zeitTotalAbsenkplatz;

	private double kostenHeli_proM3;
	private double kostenHeli_total;
	
	
	
	
	public double getZeitHelifirma() {
		return zeitHelifirma;
	}
	
	public void setZeitHelifirma(double zeitHelifirma) {
		this.zeitHelifirma = zeitHelifirma;
	}
	
	public double getZeitTotalFliegen() {
		return zeitTotalFliegen;
	}
	
	public void setZeitTotalFliegen(double zeitTotalFliegen) {
		this.zeitTotalFliegen = zeitTotalFliegen;
	}
	
	public double getZeitTotalAbsenkplatz() {
		return zeitTotalAbsenkplatz;
	}
	
	public void setZeitTotalAbsenkplatz(double zeitTotalAbsenkplatz) {
		this.zeitTotalAbsenkplatz = zeitTotalAbsenkplatz;
	}
	
	public double getKostenHeli_proM3() {
		return kostenHeli_proM3;
	}
	
	public void setKostenHeli_proM3(double kostenHeli_proM3) {
		this.kostenHeli_proM3 = kostenHeli_proM3;
	}
	
	public double getKostenHeli_total() {
		return kostenHeli_total;
	}
	
	public void setKostenHeli_total(double kostenHeli_total) {
		this.kostenHeli_total = kostenHeli_total;
	}

	
}
