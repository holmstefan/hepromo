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

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisHackschnitzelTransport2018 extends Ergebnis {
	
	private double zeitLaden_h;
	private double zeitLastfahrt_h;
	private double zeitEntladen_h;
	private double zeitLeerfahrt_h;
	private int anzahlZyklen;
	
	
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
		double gesamtZeit_h = getZeitLaden_h() + getZeitLastfahrt_h() + getZeitEntladen_h() + getZeitLeerfahrt_h();
		return gesamtZeit_h;
	}
	
	public int getAnzahlZyklen() {
		return anzahlZyklen;
	}

	public void setAnzahlZyklen(int anzahlZyklen) {
		this.anzahlZyklen = anzahlZyklen;
	}
	
	
	@Override
	protected String[][] getErgebnisStrings(DecimalFormat df, String waehrung) {
		String[][] ergebnisStringsSuperClass = super.getErgebnisStrings(df, waehrung);
		String[][] stringsNew = Arrays.copyOf(ergebnisStringsSuperClass, ergebnisStringsSuperClass.length + 7);
		
		stringsNew[ergebnisStringsSuperClass.length + 0] = new String[]{};
		stringsNew[ergebnisStringsSuperClass.length + 1] = new String[]{ModelStrings.getString("ErgebnisHackschnitzelTransport2018.ZeitLaden_h"), 			 df.format(getZeitLaden_h())		, "", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		stringsNew[ergebnisStringsSuperClass.length + 2] = new String[]{ModelStrings.getString("ErgebnisHackschnitzelTransport2018.ZeitLastfahrt_h"),		 df.format(getZeitLastfahrt_h())	, "", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		stringsNew[ergebnisStringsSuperClass.length + 3] = new String[]{ModelStrings.getString("ErgebnisHackschnitzelTransport2018.ZeitEntladen_h"), 		 df.format(getZeitEntladen_h())		, "", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		stringsNew[ergebnisStringsSuperClass.length + 4] = new String[]{ModelStrings.getString("ErgebnisHackschnitzelTransport2018.ZeitLeerfahrt_h"), 		 df.format(getZeitLeerfahrt_h())	, "", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		stringsNew[ergebnisStringsSuperClass.length + 5] = new String[]{ModelStrings.getString("ErgebnisHackschnitzelTransport2018.GesamtzeitProZyklus_h"), df.format(getZeitProZyklus_h())	, "", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		stringsNew[ergebnisStringsSuperClass.length + 6] = new String[]{ModelStrings.getString("ErgebnisHackschnitzelTransport2018.AnzahlZyklen"), 			 "" + getAnzahlZyklen()				, "", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		
		return stringsNew;
	}
}
