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
package ch.wsl.fps.hepromo.model.asys;

import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemHelikopterAufarbeiten extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private int anzahlPersonal1;
	private int anzahlMaschine1;
	private int anzahlMaschine2;
	
	
	
	/*
	 * 	GETTERS AND SETTERS
	 */
	
	public int getAnzahlPersonal1() {
		return anzahlPersonal1;
	}
	
	public void setAnzahlPersonal1(int anzahlPersonal1) {
		this.anzahlPersonal1 = anzahlPersonal1;
	}
	
	public int getAnzahlMaschine1() {
		return anzahlMaschine1;
	}
	
	public void setAnzahlMaschine1(int anzahlMaschine1) {
		this.anzahlMaschine1 = anzahlMaschine1;
	}
	
	public int getAnzahlMaschine2() {
		return anzahlMaschine2;
	}
	
	public void setAnzahlMaschine2(int anzahlMaschine2) {
		this.anzahlMaschine2 = anzahlMaschine2;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add("Anzahl Personen", anzahlPersonal1);
		list.add("Anzahl Motorsägen", anzahlMaschine1);
		list.add("Anzahl Kranfahrzeuge", anzahlMaschine2);
		
		return list;
	}
	
	

}
