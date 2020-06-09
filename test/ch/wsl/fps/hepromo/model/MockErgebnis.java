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
public class MockErgebnis {
	
	private double zeitPersonal;
	private double zeitMaschine1;
	private double zeitMaschine2;
	private double zeitUmsetzen;
	private double zeitWeitereAufwaende;
	private double zeitTotal;
	
	private double kostenPersonal_proM3;
	private double kostenMaschine1_proM3;
	private double kostenMaschine2_proM3;
	private double kostenUmsetzen_proM3;
	private double kostenWeitereAufwaende_proM3;
	private double kostenTotal_proM3;
	
	private double kostenPersonal_total;
	private double kostenMaschine1_total;
	private double kostenMaschine2_total;
	private double kostenUmsetzen_total;
	private double kostenWeitereAufwaende_total;
	private double kostenTotal_total;

	private double produktivitaet_m3ProPsh15;
	private double produktivitaet_fmORproWPPH;
	private double rotationszeit;
	
	

	/*
	 * SETTERS
	 */

	public void setZeitPersonal(double zeitPersonal) {
		this.zeitPersonal = zeitPersonal;
	}

	public void setZeitMaschine1(double zeitMaschine1) {
		this.zeitMaschine1 = zeitMaschine1;
	}

	public void setZeitMaschine2(double zeitMaschine2) {
		this.zeitMaschine2 = zeitMaschine2;
	}

	public void setZeitUmsetzen(double zeitUmsetzen) {
		this.zeitUmsetzen = zeitUmsetzen;
	}

	public void setZeitWeitereAufwaende(double zeitWeitereAufwaende) {
		this.zeitWeitereAufwaende = zeitWeitereAufwaende;
	}

	public void setZeitTotal(double zeitTotal) {
		this.zeitTotal = zeitTotal;
	}

	public void setKostenPersonal_proM3(double kostenPersonal_proM3) {
		this.kostenPersonal_proM3 = kostenPersonal_proM3;
	}

	public void setKostenMaschine1_proM3(double kostenMaschine1_proM3) {
		this.kostenMaschine1_proM3 = kostenMaschine1_proM3;
	}

	public void setKostenMaschine2_proM3(double kostenMaschine2_proM3) {
		this.kostenMaschine2_proM3 = kostenMaschine2_proM3;
	}

	public void setKostenUmsetzen_proM3(double kostenUmsetzen_proM3) {
		this.kostenUmsetzen_proM3 = kostenUmsetzen_proM3;
	}

	public void setKostenWeitereAufwaende_proM3(double kostenWeitereAufwaende_proM3) {
		this.kostenWeitereAufwaende_proM3 = kostenWeitereAufwaende_proM3;
	}

	public void setKostenTotal_proM3(double kostenTotal_proM3) {
		this.kostenTotal_proM3 = kostenTotal_proM3;
	}

	public void setKostenPersonal_total(double kostenPersonal_total) {
		this.kostenPersonal_total = kostenPersonal_total;
	}

	public void setKostenMaschine1_total(double kostenMaschine1_total) {
		this.kostenMaschine1_total = kostenMaschine1_total;
	}

	public void setKostenMaschine2_total(double kostenMaschine2_total) {
		this.kostenMaschine2_total = kostenMaschine2_total;
	}

	public void setKostenUmsetzen_total(double kostenUmsetzen_total) {
		this.kostenUmsetzen_total = kostenUmsetzen_total;
	}

	public void setKostenWeitereAufwaende_total(double kostenWeitereAufwaende_total) {
		this.kostenWeitereAufwaende_total = kostenWeitereAufwaende_total;
	}

	public void setKostenTotal_total(double kostenTotal_total) {
		this.kostenTotal_total = kostenTotal_total;
	}

	public void setProduktivitaet_m3ProPsh15(double produktivitaet_m3ProPsh15) {
		this.produktivitaet_m3ProPsh15 = produktivitaet_m3ProPsh15;
	}

	public void setProduktivitaet_fmORproWPPH(double produktivitaet_fmORproWPPH) {
		this.produktivitaet_fmORproWPPH = produktivitaet_fmORproWPPH;
	}

	public void setRotationszeit(double rotationszeit) {
		this.rotationszeit = rotationszeit;
	}
	
	
	
	
	
	
	/*
	 * GETTERS
	 */

	public double getZeitPersonal() {
		return zeitPersonal;
	}

	public double getZeitMaschine1() {
		return zeitMaschine1;
	}

	public double getZeitMaschine2() {
		return zeitMaschine2;
	}
	
	public double getZeitUmsetzen() {
		return zeitUmsetzen;
	}
	
	public double getZeitWeitereAufwaende() {
		return zeitWeitereAufwaende;
	}
	
	public double getZeitTotal() {
		return zeitTotal;
	}
	


	
	public double getKostenPersonal_proM3() {
		return kostenPersonal_proM3;
	}
	
	public double getKostenMaschine1_proM3() {
		return kostenMaschine1_proM3;
	}
	
	public double getKostenMaschine2_proM3() {
		return kostenMaschine2_proM3;
	}
	
	public double getKostenUmsetzen_proM3() {
		return kostenUmsetzen_proM3;
	}
	
	public double getKostenWeitereAufwaende_proM3() {
		return kostenWeitereAufwaende_proM3;
	}
	
	public double getKostenTotal_proM3() {
		return kostenTotal_proM3;
	}
	

	

	
	public double getKostenPersonal_total() {
		return kostenPersonal_total;
	}
	
	public double getKostenMaschine1_total() {
		return kostenMaschine1_total;
	}
	
	public double getKostenMaschine2_total() {
		return kostenMaschine2_total;
	}
	
	public double getKostenUmsetzen_total() {
		return kostenUmsetzen_total;
	}
	
	public double getKostenWeitereAufwaende_total() {
		return kostenWeitereAufwaende_total;
	}
	
	public double getKostenTotal_total() {
		return kostenTotal_total;
	}

	
	

	public double getProduktivitaet_m3ProPsh15() {
		return produktivitaet_m3ProPsh15;
	}
	
	public double getProduktivitaet_fmORproWPPH() {
		return produktivitaet_fmORproWPPH;
	}
	
	public double getRotationszeit() {
		return rotationszeit;
	}
}
