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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemHelikopterGesamt extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private double helikopterKosten_proMin;
	private double anflugPauschale;
	private HelikopterKlasse helikopterKlasse;
	private double lastVolumen;
	private boolean lastVolumenAutomatischBerechnen;
	private int anzahlPersonalBeimHolzFliegen;	
	
	private int anzahlPersonalNachHolzFliegen;
	private int anzahlMotorsaegen;
	private int anzahlKranfahrzeuge;
	
	private boolean kalkulationInklLagerplatzarbeit;
	

	
	public ArbeitssystemHelikopterFliegen getTeilsystemFliegen() {
		ArbeitssystemHelikopterFliegen arbeitsSystem = new ArbeitssystemHelikopterFliegen();
		
		arbeitsSystem.setKostensatzMaschine1_proMin(this.getHelikopterKosten_proMin()		);
		arbeitsSystem.setAnflugPauschale(			this.getAnflugPauschale()				);
		arbeitsSystem.setHelikopterKlasse(			this.getHelikopterKlasse()				);
		arbeitsSystem.setLastVolumen(				this.getLastVolumen()					);
		
		arbeitsSystem.setAnzahlPersonal(			this.getAnzahlPersonalBeimHolzFliegen()	);
		arbeitsSystem.setKostensatzPersonal1_proH(	super.getKostensatzPersonal1_proH()		);

		arbeitsSystem.setTaeglicheArbeitszeit_Min(	super.getTaeglicheArbeitszeit_Min()		);
		arbeitsSystem.setWegzeitenUndPausen_Min( 	super.getWegzeitenUndPausen_Min()		);
		
		arbeitsSystem.setWeitereAufwaendeBetrag_CHF(super.getWeitereAufwaendeBetrag_CHF()	);
		arbeitsSystem.setWeitereAufwaendeZeit_h( 	super.getWeitereAufwaendeZeit_h()		);
		
		return arbeitsSystem;
	}
	
	
	
	
	public ArbeitssystemHelikopterAufarbeiten getTeilsystemAufarbeiten() {
		ArbeitssystemHelikopterAufarbeiten arbeitsSystem = new ArbeitssystemHelikopterAufarbeiten();

		arbeitsSystem.setAnzahlPersonal1(			this.getAnzahlPersonalNachHolzFliegen()	);
		arbeitsSystem.setKostensatzPersonal1_proH(	super.getKostensatzPersonal1_proH()		);
		arbeitsSystem.setAnzahlMaschine1(			this.getAnzahlMotorsaegen()				);
		arbeitsSystem.setKostensatzMaschine1_proH(	super.getKostensatzMaschine1_proH()		);
		arbeitsSystem.setAnzahlMaschine2(			this.getAnzahlKranfahrzeuge()			);
		arbeitsSystem.setKostensatzMaschine2_proH(	super.getKostensatzMaschine2_proH()		);

		arbeitsSystem.setTaeglicheArbeitszeit_Min(	super.getTaeglicheArbeitszeit_Min()		);
		arbeitsSystem.setWegzeitenUndPausen_Min( 	super.getWegzeitenUndPausen_Min()		);

		arbeitsSystem.setUmsetzenBetrag_CHF(		super.getUmsetzenBetrag_CHF()			);
		arbeitsSystem.setUmsetzenZeit_h( 			super.getUmsetzenZeit_h()				);
		arbeitsSystem.setWeitereAufwaendeBetrag_CHF(super.getWeitereAufwaendeBetrag_CHF()	);
		arbeitsSystem.setWeitereAufwaendeZeit_h( 	super.getWeitereAufwaendeZeit_h()		);
		
		return arbeitsSystem;
	}
	
	
	
	
	
	
	public double getHelikopterKosten_proMin() {
		return helikopterKosten_proMin;
	}

	public void setHelikopterKosten_proMin(double value) {
		this.helikopterKosten_proMin = value;
	}

	public double getAnflugPauschale() {
		return anflugPauschale;
	}
	
	public void setAnflugPauschale(double anflugPauschale) {
		this.anflugPauschale = anflugPauschale;
	}
	
	public HelikopterKlasse getHelikopterKlasse() {
		return helikopterKlasse;
	}
	
	public void setHelikopterKlasse(HelikopterKlasse helikopterKlasse) {
		this.helikopterKlasse = helikopterKlasse;
	}
	
	public double getLastVolumen() {
		return lastVolumen;
	}
	
	public void setLastVolumen(double lastVolumen) {
		this.lastVolumen = lastVolumen;
	}
	
	public boolean isLastVolumenAutomatischBerechnen() {
		return lastVolumenAutomatischBerechnen;
	}
	
	public void setLastVolumenAutomatischBerechnen(boolean flag) {
		this.lastVolumenAutomatischBerechnen = flag;
	}
	
	public int getAnzahlPersonalBeimHolzFliegen() {
		return anzahlPersonalBeimHolzFliegen;
	}
	
	public void setAnzahlPersonalBeimHolzFliegen(int anzahlPersonalBeimHolzFliegen) {
		this.anzahlPersonalBeimHolzFliegen = anzahlPersonalBeimHolzFliegen;
	}
	
	public int getAnzahlPersonalNachHolzFliegen() {
		return anzahlPersonalNachHolzFliegen;
	}
	
	public void setAnzahlPersonalNachHolzFliegen(int anzahlPersonalNachHolzFliegen) {
		this.anzahlPersonalNachHolzFliegen = anzahlPersonalNachHolzFliegen;
	}
	
	public int getAnzahlMotorsaegen() {
		return anzahlMotorsaegen;
	}
	
	public void setAnzahlMotorsaegen(int anzahlMotorsaegen) {
		this.anzahlMotorsaegen = anzahlMotorsaegen;
	}
	
	public int getAnzahlKranfahrzeuge() {
		return anzahlKranfahrzeuge;
	}
	
	public void setAnzahlKranfahrzeuge(int anzahlKranfahrzeuge) {
		this.anzahlKranfahrzeuge = anzahlKranfahrzeuge;
	}

	public boolean isKalkulationInklLagerplatzarbeit() {
		return kalkulationInklLagerplatzarbeit;
	}

	public void setKalkulationInklLagerplatzarbeit(boolean kalkulationInklLagerplatzarbeit) {
		this.kalkulationInklLagerplatzarbeit = kalkulationInklLagerplatzarbeit;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_AnzahlPersonenBeimHolzFliegen, anzahlPersonalBeimHolzFliegen);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_AnzahlPersonenNachHolzFliegen, anzahlPersonalNachHolzFliegen);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_AnzahlMotorsaegen, anzahlMotorsaegen);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_AnzahlKranfahrzeuge, anzahlKranfahrzeuge);

		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_Helikopterkosten + " (" + waehrung + PdfLabels.ArbeitssystemHelikopterGesamt_proBMin + ")", helikopterKosten_proMin);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_Anflugpauschale + " (" + waehrung + ")", anflugPauschale);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_Helikopterklasse, helikopterKlasse);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_Lastvolumen, lastVolumen);
		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_LastvolumenAutomatischBerechnen, lastVolumenAutomatischBerechnen);

		list.add(PdfLabels.ArbeitssystemHelikopterGesamt_KalkulationInklLagerplatzarbeit, kalkulationInklLagerplatzarbeit);
		
		return list;
	}


}
