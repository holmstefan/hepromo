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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ModelStrings {
	
	private static final String BUNDLE_NAME = "ch.wsl.fps.hepromo.model.modelstrings"; //$NON-NLS-1$
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("de"));	
	
	public static void setLocale(Locale locale) {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}
	
	
	public static enum PdfLabels {
		AbstractCalculatorSingleModel2014_m3_pro_PMH15,
		AbstractCalculatorSingleModel2014_m3iR_pro_PMH15,
		AbstractCalculatorSingleModel2014_m3oR_pro_PMH15,
		AbstractCalculatorSingleModel2014_m3oR_pro_WPPH,
		AbstractModel_PdfTitles_Arbeitsobjekt,
		AbstractModel_PdfTitles_ArbeitsortHolzschlag,
		AbstractModel_PdfTitles_Arbeitssystem,
		AbstractModel_PdfTitles_Ergebnis,
		AbstractModel_PdfTitles_Faktoren,
		AbstractModel_PdfTitles_Haupttitel,
		Arbeitsobjekt_Holzmenge_m3,
		Arbeitsobjekt_Rindenabzugsfaktor,
		ArbeitsobjektForwarder_AnteilFeinerschliessungFahrenBeimLadenAb_Prz,
		ArbeitsobjektForwarder_AnteilFeinerschliessungFahrenBeimLadenAuf_Prz,
		ArbeitsobjektForwarder_AnteilFeinerschliessungLastfahrtAb_Prz,
		ArbeitsobjektForwarder_AnteilFeinerschliessungLastfahrtAuf_Prz,
		ArbeitsobjektForwarder_AnteilFeinerschliessungLeerfahrtAb_Prz,
		ArbeitsobjektForwarder_AnteilFeinerschliessungLeerfahrtAuf_Prz,
		ArbeitsobjektForwarder_AnteilKrumm_Prz,
		ArbeitsobjektForwarder_AnteilStrasseLastfahrtAb_Prz,
		ArbeitsobjektForwarder_AnteilStrasseLastfahrtAuf_Prz,
		ArbeitsobjektForwarder_AnteilStrasseLeerfahrtAb_Prz,
		ArbeitsobjektForwarder_AnteilStrasseLeerfahrtAuf_Prz,
		ArbeitsobjektForwarder_AnzahlSortimente,
		ArbeitsobjektForwarder_AnzahlVerschiedeneSortimente,
		ArbeitsobjektForwarder_BHD_cm,
		ArbeitsobjektForwarder_ErschliessungslaengeBeidseitig_m,
		ArbeitsobjektForwarder_ErschliessungslaengeBeidseitigAnteilStrasse_Prz,
		ArbeitsobjektForwarder_ErschliessungslaengeEinseitig_m,
		ArbeitsobjektForwarder_ErschliessungslaengeEinseitigAnteilStrasse_Prz,
		ArbeitsobjektForwarder_FahrstreckeAufFeinerschliessung,
		ArbeitsobjektForwarder_FahrstreckeAufStrasse,
		ArbeitsobjektForwarder_Fahrstreckenart,
		ArbeitsobjektForwarder_gleichmaessigVerteilt,
		ArbeitsobjektForwarder_HinderniskategorieFeinerschliessung,
		ArbeitsobjektForwarder_Holzlaenge_m,
		ArbeitsobjektForwarder_LastfahrtVorwaertsBergab,
		ArbeitsobjektForwarder_LastfahrtVorwaertsBergauf,
		ArbeitsobjektForwarder_LastfahrtVorwaertsEben,
		ArbeitsobjektForwarder_Laubholzanteil_Prz,
		ArbeitsobjektForwarder_NeigungskategorieFeinerschliessung,
		ArbeitsobjektHelikopterAufarbeiten_Fichte,
		ArbeitsobjektHelikopterAufarbeiten_FoehreLaerche,
		ArbeitsobjektHelikopterAufarbeiten_Laubholz,
		ArbeitsobjektHelikopterAufarbeiten_Sortimentsverfahren,
		ArbeitsobjektHelikopterAufarbeiten_Tanne,
		ArbeitsobjektHelikopterAufarbeiten_Vollbaumverfahren,
		ArbeitsobjektHelikopterFliegen_LaubholzAngetrocknet,
		ArbeitsobjektHelikopterFliegen_LaubholzFrisch,
		ArbeitsobjektHelikopterFliegen_NadelholzAngetrocknet,
		ArbeitsobjektHelikopterFliegen_NadelholzFrisch,
		ArbeitsobjektHelikopterGesamt_Arbeitsverfahren,
		ArbeitsobjektHelikopterGesamt_Baumartengruppe,
		ArbeitsobjektHelikopterGesamt_Holztyp,
		ArbeitsobjektHelikopterGesamt_Horizontaldistanz_m,
		ArbeitsobjektHelikopterGesamt_Vertikaldistanz_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_AnzahlEndmasten,
		ArbeitsobjektKonventionellerSeilkranGesamt_AnzahlStuetzen,
		ArbeitsobjektKonventionellerSeilkranGesamt_DemontageIstSeilverlegung,
		ArbeitsobjektKonventionellerSeilkranGesamt_DistanzWindenselbstfahrtDemontage_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_DistanzWindenselbstfahrtMontage_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_Eingriffsart,
		ArbeitsobjektKonventionellerSeilkranGesamt_Fahrtrichtung,
		ArbeitsobjektKonventionellerSeilkranGesamt_Hangneigung_Prz,
		ArbeitsobjektKonventionellerSeilkranGesamt_Hindernisse,
		ArbeitsobjektKonventionellerSeilkranGesamt_Holzseilort,
		ArbeitsobjektKonventionellerSeilkranGesamt_Linienlaenge_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_MittlereDistanzSeitlicherZuzug,
		ArbeitsobjektKonventionellerSeilkranGesamt_MittlereFahrdistanz,
		ArbeitsobjektKonventionellerSeilkranGesamt_MittleresStueckvolumen_m3,
		ArbeitsobjektKonventionellerSeilkranGesamt_MontageIstSeilverlegung,
		ArbeitsobjektKonventionellerSeilkranGesamt_Stuecklaengen_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_TragseilhoeheBestand_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_TragseilhoeheLagerplatz_m,
		ArbeitsobjektKonventionellerSeilkranGesamt_WindenstandortDemontage,
		ArbeitsobjektKonventionellerSeilkranGesamt_WindenstandortMontage,
		ArbeitsobjektKonventionellerSeilkranGesamt_WindentransportartDemontage,
		ArbeitsobjektKonventionellerSeilkranGesamt_WindentransportartMontage,
		ArbeitsobjektKonventionellerSeilkranMontageDemontage_WSbleibt,
		ArbeitsobjektKonventionellerSeilkranMontageDemontage_WSwechselt,
		ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTKeinWindentransport,
		ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTmitHeli,
		ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTSelbstfahrtBergab,
		ArbeitsobjektKonventionellerSeilkranMontageDemontage_WTSelbstfahrtBergauf,
		ArbeitsobjektKonventionellerSeilkranSeilen_EingriffsartDurchforstung,
		ArbeitsobjektKonventionellerSeilkranSeilen_EingriffsartLichtung,
		ArbeitsobjektKonventionellerSeilkranSeilen_EingriffsartRaeumung,
		ArbeitsobjektKonventionellerSeilkranSeilen_FahrtrichtungBergab,
		ArbeitsobjektKonventionellerSeilkranSeilen_FahrtrichtungBergauf,
		ArbeitsobjektKonventionellerSeilkranSeilen_HindernisKatErschwert,
		ArbeitsobjektKonventionellerSeilkranSeilen_HindernisKatExtrem,
		ArbeitsobjektKonventionellerSeilkranSeilen_HindernisKatNormal,
		ArbeitsobjektKonventionellerSeilkranSeilen_SeilenAbHaufen,
		ArbeitsobjektKonventionellerSeilkranSeilen_SeilenAusSchlagflaeche,
		ArbeitsobjektMobilseilkranGesamt_Endmast,
		ArbeitsobjektMobilseilkranGesamt_Linienlaenge_m,
		ArbeitsobjektMobilseilkranGesamt_Maschinenstandort,
		ArbeitsobjektMobilseilkranGesamt_MittlereDistanzSeitlicherZuzug_m,
		ArbeitsobjektMobilseilkranGesamt_MittlereFahrdistanz_m,
		ArbeitsobjektMobilseilkranGesamt_MittleresStueckvolumen_m3,
		ArbeitsobjektMobilseilkranGesamt_SchwierigkeitSeitlicherZuzug,
		ArbeitsobjektMobilseilkranGesamt_Seilsystem,
		ArbeitsobjektMobilseilkranGesamt_TragseilhoeheEndmast,
		ArbeitsobjektMobilseilkranGesamt_TragseilhoeheStuetze,
		ArbeitsobjektMobilseilkranInstallation_MaschinenstandortOben,
		ArbeitsobjektMobilseilkranInstallation_MaschinenstandortUnten,
		ArbeitsobjektMobilseilkranInstallation_Mehrseilsystem,
		ArbeitsobjektMobilseilkranInstallation_Zweiseilsystem,
		ArbeitsobjektMobilseilkranSeilen_ZuzugEinfach,
		ArbeitsobjektMobilseilkranSeilen_ZuzugErschwert,
		ArbeitsobjektMotormanuellGesamt2014_AnteilKiefer_Prz,
		ArbeitsobjektMotormanuellGesamt2014_AnteilLaubholz_Prz,
		ArbeitsobjektMotormanuellGesamt2014_BHD_cm,
		ArbeitsobjektMotormanuellGesamt2014_FlachHuegelland,
		ArbeitsobjektMotormanuellGesamt2014_Gebirge,
		ArbeitsobjektMotormanuellGesamt2014_Region,
		ArbeitsobjektRadharvester2014_0Prz,
		ArbeitsobjektRadharvester2014_abholzig,
		ArbeitsobjektRadharvester2014_benutzerdefiniert,
		ArbeitsobjektRadharvester2014_BHD_cm,
		ArbeitsobjektRadharvester2014_bis100Prz,
		ArbeitsobjektRadharvester2014_bis25Prz,
		ArbeitsobjektRadharvester2014_bis50Prz,
		ArbeitsobjektRadharvester2014_bis75Prz,
		ArbeitsobjektRadharvester2014_Keines,
		ArbeitsobjektRadharvester2014_normalholzig,
		ArbeitsobjektRadharvester2014_vollholzig,
		ArbeitsobjektRadharvester2014_VorgeruecktesHolzBis25Prz,
		ArbeitsobjektRadharvester2014_VorgeruecktesHolzBis50Prz,
		ArbeitsobjektRadharvester2014_ZugefaelltesHolzBis25Prz,
		ArbeitsobjektRadharvester2014_ZugefaelltesHolzBis50Prz,
		ArbeitsobjektRadharvester2014_ZuschlagAnteilLaubholz,
		ArbeitsobjektRadharvester2014_ZuschlagFoermigkeit,
		ArbeitsobjektRadharvester2014_ZuschlagLiegendesHolz,
		ArbeitsobjektSchlepper2014_Beizug20bis40m,
		ArbeitsobjektSchlepper2014_BeizugBis20m,
		ArbeitsobjektSchlepper2014_BeizugUeber40m,
		ArbeitsobjektSchlepper2014_benutzerdefiniert,
		ArbeitsobjektSchlepper2014_Entfernung301bis500m,
		ArbeitsobjektSchlepper2014_Entfernung501bis700m,
		ArbeitsobjektSchlepper2014_Entfernung701bis900m,
		ArbeitsobjektSchlepper2014_EntfernungBis300m,
		ArbeitsobjektSchlepper2014_EntfernungUeber900m,
		ArbeitsobjektSchlepper2014_MittlereFahrentfernung,
		ArbeitsobjektSchlepper2014_MittlererStueckinhalt,
		ArbeitsobjektSchlepper2014_ZuschlagBeizugsdistanz,
		Arbeitssystem_Kostensatz,
		Arbeitssystem_proBStd,
		Arbeitssystem_proStd,
		Arbeitssystem_TaeglicheArbeitszeit_min,
		Arbeitssystem_Umsetzen,
		Arbeitssystem_Umsetzten_Std,
		Arbeitssystem_WegzeitenPausen_min,
		Arbeitssystem_WeitereAufwaende,
		Arbeitssystem_WeitereAufwaende_Std,
		ArbeitssystemForwarder_Forwardertyp,
		ArbeitssystemForwarder_klein,
		ArbeitssystemForwarder_Ladequerschnittsflaeche_m2,
		ArbeitssystemForwarder_mittel,
		ArbeitssystemHelikopterFliegen_HeliklasseLeicht,
		ArbeitssystemHelikopterFliegen_HeliklasseMittel,
		ArbeitssystemHelikopterFliegen_HeliklasseSchwer,
		ArbeitssystemHelikopterGesamt_Anflugpauschale,
		ArbeitssystemHelikopterGesamt_AnzahlKranfahrzeuge,
		ArbeitssystemHelikopterGesamt_AnzahlMotorsaegen,
		ArbeitssystemHelikopterGesamt_AnzahlPersonenBeimHolzFliegen,
		ArbeitssystemHelikopterGesamt_AnzahlPersonenNachHolzFliegen,
		ArbeitssystemHelikopterGesamt_Helikopterklasse,
		ArbeitssystemHelikopterGesamt_Helikopterkosten,
		ArbeitssystemHelikopterGesamt_KalkulationInklLagerplatzarbeit,
		ArbeitssystemHelikopterGesamt_Lastvolumen,
		ArbeitssystemHelikopterGesamt_LastvolumenAutomatischBerechnen,
		ArbeitssystemHelikopterGesamt_proBMin,
		ArbeitssystemMotormanuell2014_KostensatzArbeitskraft,
		ArbeitssystemRadharvester2014_Maschinentyp,
		ArbeitssystemRadharvester2014_TypGross,
		ArbeitssystemRadharvester2014_TypMittel,
		ArbeitssystemSchlepper2014_Klemmbankschlepper,
		ArbeitssystemSchlepper2014_Kranschlepper,
		ArbeitssystemSchlepper2014_Maschinenkategorie,
		ArbeitssystemSchlepper2014_Seilschlepper,
		ArbeitssystemSeilkranGesamt_AnzahlPersonenInstallation,
		ArbeitssystemSeilkranGesamt_AnzahlPersonenLagerplatz,
		ArbeitssystemSeilkranGesamt_AnzahlPersonenSeilen,
		ArbeitssystemSeilkranGesamt_EinsatzzeitPersonenLagerplatz_Prz,
		ArbeitssystemSeilkranGesamt_LaufzeitKranfahrzeug_Prz,
		CalculatorMotormanuellGesamt2014_m3iR_pro_WPSH,
		CalculatorMotormanuellGesamt2014_m3oR_pro_WPSH,
		CalculatorSeilkranGesamt_ProduktivitaetBeimSeilen_m3iRproPMH15,
		CalculatorSeilkranGesamt_ProduktivitaetBeimSeilen_m3oRproPMH15,
		Ergebnis_DauerDerArbeit,
		Ergebnis_Kosten,
		Ergebnis_m3_pro_PSH15,
		Ergebnis_Produktivitaet,
		Ergebnis_proM3,
		Ergebnis_total,
		Ergebnis_Total,
		Ergebnis_Umsetzen,
		Ergebnis_WeitereAufwaende,
		Ergebnis_Zeitaufwand_Std,
		ErgebnisHelikopterGesamt_DauerDerArbeitAbsenkplatz,
		ErgebnisHelikopterGesamt_DauerDerArbeitFliegen,
		ErgebnisHelikopterGesamt_Helifirma,
		ErgebnisHelikopterGesamt_Rotationszeit_Min_pro_Rot,
		Faktoren_Gewinn,
		Faktoren_Korrekturfaktor,
		Faktoren_Mehrwertsteuer,
		Faktoren_Risiko,
		Faktoren_RisikoVerwaltungGewinn,
		Faktoren_Verwaltung,
		Faktoren_Waehrungskuerzel,
		FeinerschliessungGelaendebedingungen_HindernisKatKeine,
		FeinerschliessungGelaendebedingungen_HindernisKatSehrViele,
		FeinerschliessungGelaendebedingungen_HindernisKatViele,
		FeinerschliessungGelaendebedingungen_HindernisKatWenige,
		FeinerschliessungGelaendebedingungen_NeigungsKat10Bis20Prz,
		FeinerschliessungGelaendebedingungen_NeigungsKatBis10Prz,
		FeinerschliessungGelaendebedingungen_NeigungsKatUeber20Prz,
		LabelValuePairList_ja,
		LabelValuePairList_nein,
		ModelForwarder_LabelForwarder,
		ModelForwarder_LabelPersonal,
		ModelForwarder_ModelName,
		ModelHelikopterGesamt_LabelKranfahrzeug,
		ModelHelikopterGesamt_LabelMotorsaege,
		ModelHelikopterGesamt_LabelPersonalForstbetrieb,
		ModelHelikopterGesamt_ModelName,
		ModelKonventionellerSeilkranGesamt_LabelKranfahrzeug,
		ModelKonventionellerSeilkranGesamt_LabelPersonal,
		ModelKonventionellerSeilkranGesamt_LabelSeilkrananlage,
		ModelKonventionellerSeilkranGesamt_ModelName,
		ModelMobilseilkranGesamt_LabelKranfahrzeug,
		ModelMobilseilkranGesamt_LabelMobilseilkran,
		ModelMobilseilkranGesamt_LabelPersonal,
		ModelMobilseilkranGesamt_ModelName,
		ModelMotormanuellGesamt2014_ModelName,
		ModelMotormanuellGesamt2014_LabelMotorsaege,
		ModelMotormanuellGesamt2014_LabelPersonal,
		ModelRadharvester2014_LabelRadharvester,
		ModelRadharvester2014_ModelName,
		ModelRadharvester2014_LabelMaschinist,
		ModelSchlepper2014_LabelMaschinist,
		ModelSchlepper2014_LabelSchlepper,
		ModelSchlepper2014_ModelName;
		
		
		@Override
		public String toString() {
			try {
				return RESOURCE_BUNDLE.getString( this.name() );
			} catch (MissingResourceException e) {
				System.err.println("key not found: " + this.name());
				return "XXXXXXXX";
			}
		}
		
	}



	public static String getString(String key) { //TODO: Diese  Methode wird für die Modelle ab 2015 gebraucht, vereinheitlichen?
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			System.err.println("key not found: " + key); //$NON-NLS-1$
			return "XXXXXXXX"; //$NON-NLS-1$
		}
	}

}
