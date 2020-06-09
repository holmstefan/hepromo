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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranMontageDemontage.WindenTransport;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Eingriffsart;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Fahrtrichtung;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.Hindernisse;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektKonventionellerSeilkranSeilen.HolzSeilOrt;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektKonventionellerSeilkranGesamt extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;

	private double mittleresStueckvolumen_m3;
	private double stueckLaenge_m;
	private Eingriffsart eingriffsart;
	private HolzSeilOrt holzseilort;
	
	private int hangneigung_Prz;
	private Hindernisse hindernisse;
	
	private boolean linieAbsteckenOhneProjekt;
	private int linienLaenge_m;
	private Fahrtrichtung fahrtrichtung;
	private int mittlereFahrdistanz_m;
	private int mittlereDistanzSeitlicherZuzug_m;
	
	private int anzahlStuezten;
	private int anzahlEndmasten;
	private int tragseilhoeheBestand_m;
	private int tragseilhoeheLagerplatz_m;
	
	private boolean montageIstSeilverlegung;
	private boolean demontageIstSeilverlegung;
	private WindenTransport montageWindenTransportart;
	private WindenTransport demontageWindenTransportart;
	private WindenStandort montageWindenStandort;
	private WindenStandort demontageWindenStandort;
	private int montageDistanzWindenselbstfahrt_m;
	private int demontageDistanzWindenselbstfahrt_m;
	

	public ArbeitsobjektSeilkranPlanung getTeilobjektPlanung() {
		ArbeitsobjektSeilkranPlanung ao = new ArbeitsobjektSeilkranPlanung();

		ao.setHolzmenge_m3(					super.getHolzmenge_m3()					);
		ao.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		ao.setRindenabzugsfaktorManuell(	super.getRindenAbzugFaktor()			);
		ao.setLinienlaenge_m(				this.getLinienLaenge_m()				);
		ao.setLinieAbsteckenOhneProjekt(	this.isLinieAbsteckenOhneProjekt()		);
		
		return ao;
	}
	
	
	public ArbeitsobjektKonventionellerSeilkranMontage getTeilobjektMontage() {
		ArbeitsobjektKonventionellerSeilkranMontage ao = new ArbeitsobjektKonventionellerSeilkranMontage();

		ao.setHolzmenge_m3(				super.getHolzmenge_m3()						);
		ao.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		ao.setRindenabzugsfaktorManuell(super.getRindenAbzugFaktor()				);
		ao.setLinienLaenge_m(			this.getLinienLaenge_m()					);
		ao.setAnzahlStuetzen(			this.getAnzahlStuezten()					);
		ao.setAnzahlEndmasten(			this.getAnzahlEndmasten()					);
		ao.setMontageIstSeilverlegung(	this.isMontageIstSeilverlegung()			);
		ao.setWindenTransport(			this.getMontageWindenTransportart()			);
		ao.setWindenStandort(			this.getMontageWindenStandort()				);
		ao.setDistanzWindenSelbstfahrt(	this.getMontageDistanzWindenselbstfahrt_m()	);
		
		return ao;
	}
	
	
	public ArbeitsobjektKonventionellerSeilkranSeilen getTeilobjektSeilen() {
		ArbeitsobjektKonventionellerSeilkranSeilen ao = new ArbeitsobjektKonventionellerSeilkranSeilen();
		
		ao.setMittleresStueckvolumen_m3(this.getMittleresStueckvolumen_m3()	);
		ao.setHolzmenge_m3(				super.getHolzmenge_m3()				);
		ao.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		ao.setRindenabzugsfaktorManuell(super.getRindenAbzugFaktor()		);
		ao.setStuecklaenge_m(			this.getStueckLaenge_m()			);
		ao.setEingriffsart(				this.getEingriffsart()				);
		ao.setHolzSeilOrt(				this.getHolzseilort()				);
		
		ao.setHangneigung_Prz(			this.getHangneigung_Prz()			);
		ao.setHindernisse(				this.getHindernisse()				);
		
		ao.setFahrtrichtung(			 		this.getFahrtrichtung() 					);
		ao.setMittlereFahrdistanz_m( 			this.getMittlereFahrdistanz_m()				);
		ao.setMittlereDistanzSeitlicherZuzug_m(	this.getMittlereDistanzSeitlicherZuzug_m()	);
		ao.setTragseilHoeheLagerplatz_m(		this.getTragseilhoeheLagerplatz_m()			);
		ao.setTragseilHoeheBestand_m(			this.getTragseilhoeheBestand_m()			);
		
		return ao;
	}
	
	
	public ArbeitsobjektKonventionellerSeilkranDemontage getTeilobjektDemontage() {
		ArbeitsobjektKonventionellerSeilkranDemontage ao = new ArbeitsobjektKonventionellerSeilkranDemontage();

		ao.setHolzmenge_m3(				super.getHolzmenge_m3()						);
		ao.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		ao.setRindenabzugsfaktorManuell(super.getRindenAbzugFaktor()				);
		ao.setLinienLaenge_m(			this.getLinienLaenge_m()					);
		ao.setAnzahlStuetzen(			this.getAnzahlStuezten()					);
		ao.setAnzahlEndmasten(			this.getAnzahlEndmasten()					);
		ao.setDemontageIstSeilverlegung(this.isDemontageIstSeilverlegung()			);
		ao.setWindenTransport(			this.getDemontageWindenTransportart()			);
		ao.setWindenStandort(			this.getDemontageWindenStandort()				);
		ao.setDistanzWindenSelbstfahrt(	this.getDemontageDistanzWindenselbstfahrt_m()	);
		
		return ao;
	}
	
	
	public ArbeitsobjektSeilkranLagerplatz getTeilobjektLagerplatz(double systemzeitSeilen_psh0proM) {
		ArbeitsobjektSeilkranLagerplatz ao = new ArbeitsobjektSeilkranLagerplatz();
		
		ao.setSystemzeitSeilen_psh0proM(		systemzeitSeilen_psh0proM		);
		ao.setHolzmenge_m3(						super.getHolzmenge_m3()			);
		ao.setRindenabzugsfaktorManuellGesetzt(	super.isRindenabzugsfaktorManuellGesetzt()	);
		ao.setRindenabzugsfaktorManuell(		super.getRindenAbzugFaktor()				);
		
		return ao;
	}
	
	


	public double getMittleresStueckvolumen_m3() {
		return mittleresStueckvolumen_m3;
	}


	public void setMittleresStueckvolumen_m3(double mittleresStueckvolumen_m3) {
		this.mittleresStueckvolumen_m3 = mittleresStueckvolumen_m3;
	}


	public double getStueckLaenge_m() {
		return stueckLaenge_m;
	}


	public void setStueckLaenge_m(double stueckLaenge_m) {
		this.stueckLaenge_m = stueckLaenge_m;
	}


	public Eingriffsart getEingriffsart() {
		return eingriffsart;
	}


	public void setEingriffsart(Eingriffsart eingriffsart) {
		this.eingriffsart = eingriffsart;
	}


	public HolzSeilOrt getHolzseilort() {
		return holzseilort;
	}


	public void setHolzseilort(HolzSeilOrt holzseilort) {
		this.holzseilort = holzseilort;
	}


	public int getHangneigung_Prz() {
		return hangneigung_Prz;
	}


	public void setHangneigung_Prz(int hangneigung_Prz) {
		this.hangneigung_Prz = hangneigung_Prz;
	}


	public Hindernisse getHindernisse() {
		return hindernisse;
	}


	public void setHindernisse(Hindernisse hindernisse) {
		this.hindernisse = hindernisse;
	}


	public boolean isLinieAbsteckenOhneProjekt() {
		return linieAbsteckenOhneProjekt;
	}


	public void setLinieAbsteckenOhneProjekt(boolean linieAbsteckenOhneProjekt) {
		this.linieAbsteckenOhneProjekt = linieAbsteckenOhneProjekt;
	}


	public int getLinienLaenge_m() {
		return linienLaenge_m;
	}


	public void setLinienLaenge_m(int linienLaenge_m) {
		this.linienLaenge_m = linienLaenge_m;
	}


	public Fahrtrichtung getFahrtrichtung() {
		return fahrtrichtung;
	}


	public void setFahrtrichtung(Fahrtrichtung fahrtrichtung) {
		this.fahrtrichtung = fahrtrichtung;
	}


	public int getMittlereFahrdistanz_m() {
		return mittlereFahrdistanz_m;
	}


	public void setMittlereFahrdistanz_m(int mittlereFahrdistanz_m) {
		this.mittlereFahrdistanz_m = mittlereFahrdistanz_m;
	}


	public int getMittlereDistanzSeitlicherZuzug_m() {
		return mittlereDistanzSeitlicherZuzug_m;
	}


	public void setMittlereDistanzSeitlicherZuzug_m(int mittlereDistanzSeitlicherZuzug_m) {
		this.mittlereDistanzSeitlicherZuzug_m = mittlereDistanzSeitlicherZuzug_m;
	}


	public int getAnzahlStuezten() {
		return anzahlStuezten;
	}


	public void setAnzahlStuetzen(int anzahlStuezten) {
		this.anzahlStuezten = anzahlStuezten;
	}


	public int getAnzahlEndmasten() {
		return anzahlEndmasten;
	}


	public void setAnzahlEndmasten(int anzahlEndmasten) {
		this.anzahlEndmasten = anzahlEndmasten;
	}


	public int getTragseilhoeheBestand_m() {
		return tragseilhoeheBestand_m;
	}


	public void setTragseilhoeheBestand_m(int tragseilhoeheBestand_m) {
		this.tragseilhoeheBestand_m = tragseilhoeheBestand_m;
	}


	public int getTragseilhoeheLagerplatz_m() {
		return tragseilhoeheLagerplatz_m;
	}


	public void setTragseilhoeheLagerplatz_m(int tragseilhoeheLagerplatz_m) {
		this.tragseilhoeheLagerplatz_m = tragseilhoeheLagerplatz_m;
	}


	public boolean isMontageIstSeilverlegung() {
		return montageIstSeilverlegung;
	}


	public void setMontageIstSeilverlegung(boolean montageIstSeilverlegung) {
		this.montageIstSeilverlegung = montageIstSeilverlegung;
	}


	public boolean isDemontageIstSeilverlegung() {
		return demontageIstSeilverlegung;
	}


	public void setDemontageIstSeilverlegung(boolean demontageIstSeilverlegung) {
		this.demontageIstSeilverlegung = demontageIstSeilverlegung;
	}


	public WindenTransport getMontageWindenTransportart() {
		return montageWindenTransportart;
	}


	public void setMontageWindenTransportart(WindenTransport montageWindenTransportart) {
		this.montageWindenTransportart = montageWindenTransportart;
	}


	public WindenTransport getDemontageWindenTransportart() {
		return demontageWindenTransportart;
	}


	public void setDemontageWindenTransportart(WindenTransport demontageWindenTransportart) {
		this.demontageWindenTransportart = demontageWindenTransportart;
	}


	public WindenStandort getMontageWindenStandort() {
		return montageWindenStandort;
	}


	public void setMontageWindenStandort(WindenStandort montageWindenStandort) {
		this.montageWindenStandort = montageWindenStandort;
	}


	public WindenStandort getDemontageWindenStandort() {
		return demontageWindenStandort;
	}


	public void setDemontageWindenStandort(WindenStandort demontageWindenStandort) {
		this.demontageWindenStandort = demontageWindenStandort;
	}


	public int getMontageDistanzWindenselbstfahrt_m() {
		return montageDistanzWindenselbstfahrt_m;
	}


	public void setMontageDistanzWindenselbstfahrt_m(int montageDistanzWindenselbstfahrt_m) {
		this.montageDistanzWindenselbstfahrt_m = montageDistanzWindenselbstfahrt_m;
	}


	public int getDemontageDistanzWindenselbstfahrt_m() {
		return demontageDistanzWindenselbstfahrt_m;
	}


	public void setDemontageDistanzWindenselbstfahrt_m(int demontageDistanzWindenselbstfahrt_m) {
		this.demontageDistanzWindenselbstfahrt_m = demontageDistanzWindenselbstfahrt_m;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_MittleresStueckvolumen_m3, mittleresStueckvolumen_m3); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Stuecklaengen_m,  stueckLaenge_m); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Eingriffsart,  eingriffsart); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Holzseilort,  holzseilort); 
		
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Hangneigung_Prz,  hangneigung_Prz); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Hindernisse,  hindernisse); 
		
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Linienlaenge_m,  linienLaenge_m); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_Fahrtrichtung,  fahrtrichtung); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_MittlereFahrdistanz,  mittlereFahrdistanz_m); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_MittlereDistanzSeitlicherZuzug,  mittlereDistanzSeitlicherZuzug_m); 
		
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_AnzahlStuetzen,  anzahlStuezten); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_AnzahlEndmasten,  anzahlEndmasten); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_TragseilhoeheBestand_m,  tragseilhoeheBestand_m); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_TragseilhoeheLagerplatz_m,  tragseilhoeheLagerplatz_m); 
		
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_MontageIstSeilverlegung,  montageIstSeilverlegung); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_DemontageIstSeilverlegung,  demontageIstSeilverlegung); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_WindentransportartMontage,  montageWindenTransportart); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_WindentransportartDemontage,  demontageWindenTransportart); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_WindenstandortMontage,  montageWindenStandort); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_WindenstandortDemontage,  demontageWindenStandort); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_DistanzWindenselbstfahrtMontage_m,  montageDistanzWindenselbstfahrt_m); 
		list.add(PdfLabels.ArbeitsobjektKonventionellerSeilkranGesamt_DistanzWindenselbstfahrtDemontage_m,  demontageDistanzWindenselbstfahrt_m); 
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}
	
}
