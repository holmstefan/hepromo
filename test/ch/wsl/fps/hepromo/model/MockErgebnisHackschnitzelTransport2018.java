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
public class MockErgebnisHackschnitzelTransport2018 extends MockErgebnis {
	
	private double zeitLaden_h;
	private double zeitLastfahrt_h;
	private double zeitEntladen_h;
	private double zeitLeerfahrt_h;
	private double zeitProZyklus_h;
	private int anzahlZyklen;

	private double produktivitaet_SrmProPsh15;
	
	
	public double getZeitLaden_h() {
		return zeitLaden_h;
	}
	
	public void setZeitLaden_h(double zeitLaden_h) {
		this.zeitLaden_h = zeitLaden_h;
	}
	
	public double getZeitLastfahrt_h() {
		return zeitLastfahrt_h;
	}
	
	public void setZeitLastfahrt_h(double zeitLastfahrt_h) {
		this.zeitLastfahrt_h = zeitLastfahrt_h;
	}
	
	public double getZeitEntladen_h() {
		return zeitEntladen_h;
	}
	
	public void setZeitEntladen_h(double zeitEntladen_h) {
		this.zeitEntladen_h = zeitEntladen_h;
	}
	
	public double getZeitLeerfahrt_h() {
		return zeitLeerfahrt_h;
	}
	
	public void setZeitLeerfahrt_h(double zeitLeerfahrt_h) {
		this.zeitLeerfahrt_h = zeitLeerfahrt_h;
	}
	
	public double getZeitProZyklus_h() {
		return zeitProZyklus_h;
	}
	
	public void setZeitProZyklus_h(double zeitProZyklus_h) {
		this.zeitProZyklus_h = zeitProZyklus_h;
	}
	
	public int getAnzahlZyklen() {
		return anzahlZyklen;
	}

	public void setAnzahlZyklen(int anzahlZyklen) {
		this.anzahlZyklen = anzahlZyklen;
	}

	public double getProduktivitaet_SrmProPsh15() {
		return produktivitaet_SrmProPsh15;
	}

	public void setProduktivitaet_SrmProPsh15(double produktivitaet_SrmProPsh15) {
		this.produktivitaet_SrmProPsh15 = produktivitaet_SrmProPsh15;
	}

}
