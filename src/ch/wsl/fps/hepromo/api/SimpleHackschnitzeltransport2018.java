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
package ch.wsl.fps.hepromo.api;

import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.modelle.ModelHackschnitzelTransport2018;

/**
 * 
 * @author Stefan Holm
 *
 */
public class SimpleHackschnitzeltransport2018 extends AbstractSimpleModel<ModelHackschnitzelTransport2018> {

	// Arbeitsobjekt
	public static final double DEFAULT_HACKSCHNITZELMENGE_SRM = 100;
	public static final double DEFAULT_AUFNEHMEN_BELADENER_MULDEN_KATEGORIE = 1;
	public static final double DEFAULT_POLTERSORTIMENT_KATEGORIE = 1;
	public static final double DEFAULT_MOTORLEISTUNG_HACKER_KW = 350;
	public static final double DEFAULT_FAHRSTRECKE_WALDSTRASSE_KM = 4;
	public static final double DEFAULT_FAHRSTRECKE_INNERORTS_UND_AUSSERORTS_KM = 8;
	public static final double DEFAULT_FAHRSTRECKE_AUTOBAHN_KM = 0;

	// Arbeitssystem
	public static final double DEFAULT_MULDENINHALT_SRM = 40;
	public static final double DEFAULT_KOSTEN_TRANSPORTFAHRZEUG_INKL_FAHRER_PRO_H = 200;
	
	/** Pausen sind in den Kosten Transportfahrzeug inkl. Fahrer eingerechnet, deshalb ist der Wert hier 0. */
	public static final double DEFAULT_WEGZEITEN_UND_PAUSEN_MIN = 0;
	

	public SimpleHackschnitzeltransport2018() {
		super(new ModelHackschnitzelTransport2018());
		
		// Arbeitsobjekt
		this.setHackschnitzelmenge_Srm(DEFAULT_HACKSCHNITZELMENGE_SRM);
		this.setAufnehmenBeladenerMulden_Kategorie(DEFAULT_AUFNEHMEN_BELADENER_MULDEN_KATEGORIE);
		this.setPoltersortiment_Kategorie(DEFAULT_POLTERSORTIMENT_KATEGORIE);
		this.setMotorleistungHacker_kW(DEFAULT_MOTORLEISTUNG_HACKER_KW);
		this.setFahrstreckeWaldstrasse_km(DEFAULT_FAHRSTRECKE_WALDSTRASSE_KM);
		this.setFahrstreckeInnerortsUndAusserorts_km(DEFAULT_FAHRSTRECKE_INNERORTS_UND_AUSSERORTS_KM);
		this.setFahrstreckeAutobahn_km(DEFAULT_FAHRSTRECKE_AUTOBAHN_KM);

		// Arbeitssystem
		this.setMuldeninhalt_Srm(DEFAULT_MULDENINHALT_SRM);
		this.setKostenTransportfahrzeugInklFahrer_proH(DEFAULT_KOSTEN_TRANSPORTFAHRZEUG_INKL_FAHRER_PRO_H);
		this.setWegzeitenUndPausen_min(DEFAULT_WEGZEITEN_UND_PAUSEN_MIN);
	}
	
	
	public void setHackschnitzelmenge_Srm(double value) {
		dirty = true;
		model.getArbeitsobjekt().setHolzmenge_m3(value);
	}
	
	/**
	 * 
	 * @param value Ja (Aufnehmen beladener Mulden)=1, Nein (Hacken in Mulden auf Fahrzeug)=2
	 */
	public void setAufnehmenBeladenerMulden_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setAufnehmenBeladenerMulde(true);
			return;
		case 2:
			model.getArbeitsobjekt().setAufnehmenBeladenerMulde(false);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * This parameter is only relevant if AufnehmenBeladenerMulden = Nein
	 * 
	 * @param value Waldrestholz=1, Energierundholz=2
	 */
	public void setPoltersortiment_Kategorie(double value) {
		int intValue = round(value);
		
		dirty = true;
		switch(intValue) {
		case 1:
			model.getArbeitsobjekt().setZielsortiment(Zielsortiment.Waldrestholz);
			return;
		case 2:
			model.getArbeitsobjekt().setZielsortiment(Zielsortiment.Energierundholz);
			return;
		default:
			throw new IllegalArgumentException("value must be in [1,2]");
		}
	}
	
	/**
	 * This parameter is only relevant if AufnehmenBeladenerMulden = Nein
	 * 
	 * @param value Value is rounded to the closest <code>int</code>.
	 */
	public void setMotorleistungHacker_kW(double value) {
		int intValue = round(value);
		
		dirty = true;
		model.getArbeitsobjekt().setHackerMotorleistung(ArbeitssystemHacker2018.getBenutzerdefinierteHackerMotorleistung(intValue));
	}
	
	public void setFahrstreckeWaldstrasse_km(double value) {
		dirty = true;
		model.getArbeitsobjekt().setDistanzWaldstrasse_km(value);
	}
	
	public void setFahrstreckeInnerortsUndAusserorts_km(double value) {
		dirty = true;
		model.getArbeitsobjekt().setDistanzInnerAusserorts_km(value);
	}
	
	public void setFahrstreckeAutobahn_km(double value) {
		dirty = true;
		model.getArbeitsobjekt().setDistanzAutobahn_km(value);
	}
	
	public void setMuldeninhalt_Srm(double value) {
		dirty = true;
		model.getArbeitssystem().setMuldeninhalt_Srm(value);
	}
	
	public void setKostenTransportfahrzeugInklFahrer_proH(double value) {
		dirty = true;
		model.getArbeitssystem().setKostensatzMaschine1_proH(value);
	}
	
	
	public double getZeitTransportfahrzeugInklFahrer_PMH15() {
		recalc();
		return ergebnis.getZeitMaschine1();
	}
	

	public double getKostenTransportfahrzeugInklFahrer_proSrm() {
		recalc();
		return ergebnis.getKostenMaschine1_proM3();
	}

	public double getKostenWeitereAufwaende_proSrm() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_proM3();
	}

	public double getKostenTotal_proSrm() {
		recalc();
		return ergebnis.getKostenTotal_proM3();
	}
	

	public double getKostenTransportfahrzeugInklFahrer_total() {
		recalc();
		return ergebnis.getKostenMaschine1_total();
	}

	public double getKostenWeitereAufwaende_total() {
		recalc();
		return ergebnis.getKostenWeitereAufwaende_total();
	}


	public double getProduktivitaet_SrmProPMH15() {
		recalc();
		return ergebnis.getProduktivitaet(ProdEinheit.SRM_PRO_PMH15);
	}

}
