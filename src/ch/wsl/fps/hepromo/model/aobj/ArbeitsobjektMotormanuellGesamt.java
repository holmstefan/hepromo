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
package ch.wsl.fps.hepromo.model.aobj;

import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntastenMotormanuell.KronenLaengenKlasse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektEntrinden.AnteilShUndIh;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektIhLangAufarbeiten.IndustrieholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektSchichtholzAufarbeiten.SchichtholzStuecklaengen;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektStammholzAufarbeiten.StammholzStuecklaengen;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektMotormanuellGesamt extends ArbeitsobjektMotormanuell {
	
	private static final long serialVersionUID = 1L;

	//Bestand
	private int anteilFaellenMitHandseilzug_Prz;
	private int anteilEntrindenVonHand_Prz;
	private KronenLaengenKlasse kronenLaengenKlasse;
	
	//Sortimentsvorgaben
//	private int anteilStammholz;
	private int anteilIndustrieholz_Prz;
	private int anteilSchichtholz_Prz;
	private int anteilSpalten_Prz;
	private StammholzStuecklaengen stammholzStuecklaengen;
	private boolean kantenBrechen;
	private IndustrieholzStuecklaengen industrieholzStuecklaengen;
	private SchichtholzStuecklaengen schichtholzStuecklaengen;
	
	

	
	public ArbeitsobjektFaellenMotormanuell getTeilobjektFaellen() {
		ArbeitsobjektFaellenMotormanuell ao = new ArbeitsobjektFaellenMotormanuell();
		
		ao.setBaumartenGruppe(				super.getBaumartenGruppe()					);
		ao.setHangneigung(					super.getHangneigung()						);
		ao.setHindernisse(					super.getHindernisse()						);
		ao.setMassenmittelstamm_m3iR(		super.getMassenmittelstamm_m3iR()			);
		ao.setHolzmenge_m3(					super.getHolzmenge_m3()						);
		ao.setAnteilFaellenHandseilzug_Prz(	this.getAnteilFaellenMitHandseilzug_Prz()	);
		
		return ao;
	}
	
	
	
	public ArbeitsobjektEntastenMotormanuell getTeilobjektEntasten() {
		ArbeitsobjektEntastenMotormanuell ao = new ArbeitsobjektEntastenMotormanuell();
		
		ao.setBaumartenGruppe(				super.getBaumartenGruppe()					);
		ao.setHangneigung(					super.getHangneigung()						);
		ao.setHindernisse(					super.getHindernisse()						);
		ao.setMassenmittelstamm_m3iR(		super.getMassenmittelstamm_m3iR()			);
		ao.setHolzmenge_m3(					super.getHolzmenge_m3()						);
		ao.setKronenLaengenKlasse(			this.getKronenLaengenKlasse()				);
		
		return ao;
	}
	
	
	
	public ArbeitsobjektEntrinden getTeilobjektEntrinden() {
		ArbeitsobjektEntrinden ao = new ArbeitsobjektEntrinden();
		
		ao.setBaumartenGruppe(				super.getBaumartenGruppe()					);
		ao.setHangneigung(					super.getHangneigung()						);
		ao.setHindernisse(					super.getHindernisse()						);
		ao.setMassenmittelstamm_m3iR(		super.getMassenmittelstamm_m3iR()			);
		ao.setHolzmenge_m3(					
				super.getHolzmenge_m3() 
				* (this.getAnteilStammholz_Prz() / 100.0) //Entrinden nur beim Stammholz
				* (this.anteilEntrindenVonHand_Prz / 100.0) 
				);
		
		//Bugfix: im alten HeProMo wurde hier fix mit Anteil_0bis10 gerechnet, unabhängig von den effektiv eingestellten Werten.
		// -> siehe Klasse C_Evh.cls:88 (EntrindenVonHand/Step3): Standardwert ist eashihk0bis10Prz', wenn vom Gesamtmodell auf dieses Teilmodell zugegriffen wird.
//		ao.setAnteilShUndIhLang(AnteilShUndIh.Anteil_0bis10);
		int anteilShUndIhLang =
				this.getAnteilIndustrieholz_Prz() +
				this.getAnteilSchichtholz_Prz();
		ao.setAnteilShUndIhLang(AnteilShUndIh.getValue(anteilShUndIhLang));
		
		return ao;
	}
	
	
	
	public ArbeitsobjektIhLangAufarbeiten getTeilobjektIndustrieholzAufarbeiten() {
		ArbeitsobjektIhLangAufarbeiten ao = new ArbeitsobjektIhLangAufarbeiten();
		
		ao.setBaumartenGruppe(				super.getBaumartenGruppe()					);
		ao.setHangneigung(					super.getHangneigung()						);
		ao.setHindernisse(					super.getHindernisse()						);
		ao.setMassenmittelstamm_m3iR(		super.getMassenmittelstamm_m3iR()			);
		ao.setHolzmenge_m3(					super.getHolzmenge_m3()						);
		ao.setAnteilIndustrieholz_Prz(		this.getAnteilIndustrieholz_Prz()			);
		ao.setAnteilSchichtholz_Prz(		this.getAnteilSchichtholz_Prz()				);
		
		//Bugfix: im alten HeProMo wurde hier fix mit Anteil_11bis20 gerechnet, unabhängig von den effektiv eingestellten Werten.
		// -> siehe Klasse C_Ihla.cls:87 (IHLangAbfertigen/Step3): Standardwert ist eashihk11bis20Prz', wenn vom Gesamtmodell auf dieses Teilmodell zugegriffen wird.
//		ao.setAnteilShUndIh(AnteilShUndIh.Anteil_11bis20);

		ao.setStuecklaengen(this.getIndustrieholzStuecklaengen());
		
		return ao;
	}
	
	
	
	public ArbeitsobjektSchichtholzAufarbeiten getTeilobjektSchichtholzAufarbeiten() {
		ArbeitsobjektSchichtholzAufarbeiten ao = new ArbeitsobjektSchichtholzAufarbeiten();
		
		ao.setBaumartenGruppe(				super.getBaumartenGruppe()					);
		ao.setHangneigung(					super.getHangneigung()						);
		ao.setHindernisse(					super.getHindernisse()						);
		ao.setMassenmittelstamm_m3iR(		super.getMassenmittelstamm_m3iR()			);
		ao.setHolzmenge_m3(					super.getHolzmenge_m3()						);
		ao.setAnteilIndustrieholz_Prz(		this.getAnteilIndustrieholz_Prz()			);
		ao.setAnteilSchichtholz_Prz(		this.getAnteilSchichtholz_Prz()				);
		
		//Bugfix: im alten HeProMo wurde hier fix mit Anteil_11bis20 gerechnet, unabhängig von den effektiv eingestellten Werten.
		// -> siehe Klasse C_Scha.cls:87 (SchichtholzAbfertigen/Step3): Standardwert ist eashihk11bis20Prz', wenn vom Gesamtmodell auf dieses Teilmodell zugegriffen wird.
//		ao.setAnteilShUndIh(AnteilShUndIh.Anteil_11bis20);

		ao.setStuecklaengen(this.getSchichtholzStuecklaengen());
		ao.setAnteilSpalten_Prz(this.getAnteilSpalten_Prz());
		
		return ao;
	}
	
	
	
	public ArbeitsobjektStammholzAufarbeiten getTeilobjektStammholzAufarbeiten() {
		ArbeitsobjektStammholzAufarbeiten ao = new ArbeitsobjektStammholzAufarbeiten();
		
		ao.setBaumartenGruppe(				super.getBaumartenGruppe()					);
		ao.setHangneigung(					super.getHangneigung()						);
		ao.setHindernisse(					super.getHindernisse()						);
		ao.setMassenmittelstamm_m3iR(		super.getMassenmittelstamm_m3iR()			);
		ao.setStuecklaengen(				this.getStammholzStuecklaengen()			);
		ao.setKantenBrechen(				this.isKantenBrechen()						);
		
		ao.setHolzmenge_m3(					
				super.getHolzmenge_m3()
				* this.getAnteilStammholz_Prz() / 100.0	
				);
		
		return ao;
	}
	
	
	
	
	
	public int getAnteilFaellenMitHandseilzug_Prz() {
		return anteilFaellenMitHandseilzug_Prz;
	}
	
	public void setAnteilFaellenMitHandseilzug_Prz(int anteilFaellenMitHandseilzug_Prz) {
		this.anteilFaellenMitHandseilzug_Prz = anteilFaellenMitHandseilzug_Prz;
	}
	
	
	public int getAnteilEntrindenVonHand_Prz() {
		return anteilEntrindenVonHand_Prz;
	}
	
	
	public void setAnteilEntrindenVonHand_Prz(int anteilEntrindenVonHand_Prz) {
		this.anteilEntrindenVonHand_Prz = anteilEntrindenVonHand_Prz;
	}
	
	
	public KronenLaengenKlasse getKronenLaengenKlasse() {
		return kronenLaengenKlasse;
	}
	
	
	public void setKronenLaengenKlasse(KronenLaengenKlasse kronenLaengenKlasse) {
		this.kronenLaengenKlasse = kronenLaengenKlasse;
	}
	
	
	public int getAnteilStammholz_Prz() {
		return 100 - anteilIndustrieholz_Prz - anteilSchichtholz_Prz;
	}
	
	
	public int getAnteilIndustrieholz_Prz() {
		return anteilIndustrieholz_Prz;
	}
	
	
	public void setAnteilIndustrieholz_Prz(int anteilIndustrieholz_Prz) {
		this.anteilIndustrieholz_Prz = anteilIndustrieholz_Prz;
	}
	
	
	public int getAnteilSchichtholz_Prz() {
		return anteilSchichtholz_Prz;
	}
	
	
	public void setAnteilSchichtholz_Prz(int anteilSchichtholz_Prz) {
		this.anteilSchichtholz_Prz = anteilSchichtholz_Prz;
	}
	
	
	public int getAnteilSpalten_Prz() {
		return anteilSpalten_Prz;
	}
	
	
	public void setAnteilSpalten_Prz(int anteilSpalten_Prz) {
		this.anteilSpalten_Prz = anteilSpalten_Prz;
	}
	
	
	public StammholzStuecklaengen getStammholzStuecklaengen() {
		return stammholzStuecklaengen;
	}
	
	
	public void setStammholzStuecklaengen(
			StammholzStuecklaengen stammholzStuecklaengen) {
		this.stammholzStuecklaengen = stammholzStuecklaengen;
	}
	
	
	public boolean isKantenBrechen() {
		return kantenBrechen;
	}
	
	
	public void setKantenBrechen(boolean kantenBrechen) {
		this.kantenBrechen = kantenBrechen;
	}
	
	
	public IndustrieholzStuecklaengen getIndustrieholzStuecklaengen() {
		return industrieholzStuecklaengen;
	}
	
	
	public void setIndustrieholzStuecklaengen(IndustrieholzStuecklaengen industrieholzStuecklaengen) {
		this.industrieholzStuecklaengen = industrieholzStuecklaengen;
	}
	
	
	public SchichtholzStuecklaengen getSchichtholzStuecklaengen() {
		return schichtholzStuecklaengen;
	}
	
	
	public void setSchichtholzStuecklaengen(SchichtholzStuecklaengen schichtholzStuecklaengen) {
		this.schichtholzStuecklaengen = schichtholzStuecklaengen;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Anteil Fällen mit Handseilzug (%)", anteilFaellenMitHandseilzug_Prz);
		list.add("Anteil Entrinden von Hand (%)", anteilEntrindenVonHand_Prz);
		list.add("Kronenlängenklasse", kronenLaengenKlasse);
		list.add("Anteil Industrieholz (%)", anteilIndustrieholz_Prz);
		list.add("Anteil Schichtholz (%)", anteilSchichtholz_Prz);
		list.add("Anteil Spalten (%)", anteilSpalten_Prz);
		list.add("Stammholzstücklängen", stammholzStuecklaengen);
		list.add("Kanten brechen", kantenBrechen);
		list.add("Industrieholzstücklängen", industrieholzStuecklaengen);
		list.add("Schichtholzstücklängen", schichtholzStuecklaengen);
		
		return list;
	}
	
}
