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
package ch.wsl.fps.hepromo.model.modelle;

import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemSeilkranGesamt;
import ch.wsl.fps.hepromo.model.calc.CalculatorKonventionellerSeilkranGesamt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelKonventionellerSeilkranGesamt extends AbstractModel2014 {

	/**
	 * Constructor
	 */
	public ModelKonventionellerSeilkranGesamt() {
		super.arbeitsobjekt = new ArbeitsobjektKonventionellerSeilkranGesamt();
		getArbeitsobjekt().setMittleresStueckvolumen_m3(0.4);
		getArbeitsobjekt().setHolzmenge_m3(600);
		getArbeitsobjekt().setStueckLaenge_m(5);
		getArbeitsobjekt().setEingriffsart(Eingriffsart.Durchforstung);
		getArbeitsobjekt().setHolzseilort(HolzSeilOrt.AusSchlagflaeche);
		getArbeitsobjekt().setHangneigung_Prz(50);
		getArbeitsobjekt().setHindernisse(Hindernisse.Normal);
		getArbeitsobjekt().setLinieAbsteckenOhneProjekt(false);
		getArbeitsobjekt().setLinienLaenge_m(800);
		getArbeitsobjekt().setFahrtrichtung(Fahrtrichtung.Bergab);
		getArbeitsobjekt().setMittlereFahrdistanz_m(350);
		getArbeitsobjekt().setMittlereDistanzSeitlicherZuzug_m(20);
		getArbeitsobjekt().setAnzahlStuetzen(2);
		getArbeitsobjekt().setAnzahlEndmasten(1);
		getArbeitsobjekt().setTragseilhoeheBestand_m(12);
		getArbeitsobjekt().setTragseilhoeheLagerplatz_m(10);
		getArbeitsobjekt().setMontageIstSeilverlegung(false);
		getArbeitsobjekt().setMontageWindenTransportart(WindenTransport.KeinWindenTransport);
		getArbeitsobjekt().setMontageWindenStandort(WindenStandort.Bleibt);
		getArbeitsobjekt().setMontageDistanzWindenselbstfahrt_m(200);
		getArbeitsobjekt().setDemontageIstSeilverlegung(false);
		getArbeitsobjekt().setDemontageWindenTransportart(WindenTransport.KeinWindenTransport);
		getArbeitsobjekt().setDemontageWindenStandort(WindenStandort.Bleibt);
		getArbeitsobjekt().setDemontageDistanzWindenselbstfahrt_m(200);
		
		super.arbeitssystem = new ArbeitssystemSeilkranGesamt();
		getArbeitssystem().setKostensatzPersonal1_proH(70);
		getArbeitssystem().setKostensatzMaschine1_proH(110);
		getArbeitssystem().setKostensatzMaschine2_proH(80);
		getArbeitssystem().setLabelPersonal1(PdfLabels.ModelKonventionellerSeilkranGesamt_LabelPersonal.toString());
		getArbeitssystem().setLabelMaschine1(PdfLabels.ModelKonventionellerSeilkranGesamt_LabelSeilkrananlage.toString());
		getArbeitssystem().setLabelMaschine2(PdfLabels.ModelKonventionellerSeilkranGesamt_LabelKranfahrzeug.toString());
		getArbeitssystem().setLaufzeitKranfahrzeug_Prz(75);
		getArbeitssystem().setAnzahlPersonenInstallation(3);
		getArbeitssystem().setAnzahlPersonenSeilen(2);
		getArbeitssystem().setAnzahlPersonenLagerplatz(1);
		getArbeitssystem().setEinsatzzeitPersonenLagerplatz_Prz(100);
		getArbeitssystem().setTaeglicheArbeitszeit_Min(540);
		getArbeitssystem().setWegzeitenUndPausen_Min(60);
		
		super.faktoren = new Faktoren();
		getFaktoren().setMargin(0);
		getFaktoren().setWaehrungskuerzel(defaultWaehrungskuerzel);
		getFaktoren().setKorrekturFaktor(1);
		
		super.calculator = new CalculatorKonventionellerSeilkranGesamt(this);
	}
	
	
	/**
	 * Constructor
	 */
	public ModelKonventionellerSeilkranGesamt(HeProMoInputData inputData) {
		this();
		if (inputData != null) {
			super.setInputData(inputData);
			super.calculator = new CalculatorKonventionellerSeilkranGesamt(this);
		}
	}
	
	
	@Override
	public ArbeitsobjektKonventionellerSeilkranGesamt getArbeitsobjekt() {
		return (ArbeitsobjektKonventionellerSeilkranGesamt) arbeitsobjekt;
	}
	
	
	@Override
	public ArbeitssystemSeilkranGesamt getArbeitssystem() {
		return (ArbeitssystemSeilkranGesamt) arbeitssystem;
	}


	@Override
	protected String getModelName() {
		return PdfLabels.ModelKonventionellerSeilkranGesamt_ModelName.toString();
	}

}
