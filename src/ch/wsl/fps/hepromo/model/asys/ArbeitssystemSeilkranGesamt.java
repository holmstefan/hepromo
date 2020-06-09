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

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitssystemSeilkranGesamt extends Arbeitssystem {
	
	private static final long serialVersionUID = 1L;
	
	private int laufzeitKranfahrzeug_Prz;
	private int anzahlPersonenInstallation;
	private int anzahlPersonenSeilen;
	private int anzahlPersonenLagerplatz;
	private int einsatzzeitPersonenLagerplatz_Prz;
	
	
	
	public Arbeitssystem getTeilsystemPlanung() {
		Arbeitssystem as = new Arbeitssystem();
		
		as.setKostensatzPersonal1_proH( 	super.getKostensatzPersonal1_proH() 	);
		
		as.setTaeglicheArbeitszeit_Min( 	super.getTaeglicheArbeitszeit_Min() 	);
		as.setWegzeitenUndPausen_Min( 		super.getWegzeitenUndPausen_Min()		);
		as.setWeitereAufwaendeBetrag_CHF( 	super.getWeitereAufwaendeBetrag_CHF() 	);
		as.setWeitereAufwaendeZeit_h(		super.getWeitereAufwaendeZeit_h()		);
		
		return as;
	}
	
	
	public ArbeitssystemSeilkran getTeilsystemInstallation() {
		ArbeitssystemSeilkran as = new ArbeitssystemSeilkran();

		as.setKostensatzPersonal1_proH( 	super.getKostensatzPersonal1_proH() 	);
		as.setAnzahlPersonal(		 		this.getAnzahlPersonenInstallation()	);
		as.setKostensatzMaschine1_proH(		super.getKostensatzMaschine1_proH() 	);
		as.setAnteilSeilkranLaufzeit_Prz(	20	 									);	
		
		as.setTaeglicheArbeitszeit_Min( 	super.getTaeglicheArbeitszeit_Min() 	);
		as.setWegzeitenUndPausen_Min( 		super.getWegzeitenUndPausen_Min()		);
		as.setWeitereAufwaendeBetrag_CHF( 	super.getWeitereAufwaendeBetrag_CHF() 	);
		as.setWeitereAufwaendeZeit_h(		super.getWeitereAufwaendeZeit_h()		);
		
		return as;
	}
	
	
	public ArbeitssystemSeilkran getTeilsystemSeilen() {
		ArbeitssystemSeilkran as = new ArbeitssystemSeilkran();

		as.setKostensatzPersonal1_proH( 	super.getKostensatzPersonal1_proH() 	);
		as.setAnzahlPersonal(		 		this.getAnzahlPersonenSeilen()			);
		as.setKostensatzMaschine1_proH(		super.getKostensatzMaschine1_proH()		);
		as.setAnteilSeilkranLaufzeit_Prz(	90									 	);
		
		as.setTaeglicheArbeitszeit_Min( 	super.getTaeglicheArbeitszeit_Min() 	);
		as.setWegzeitenUndPausen_Min( 		super.getWegzeitenUndPausen_Min()		);
		as.setWeitereAufwaendeBetrag_CHF( 	super.getWeitereAufwaendeBetrag_CHF() 	);
		as.setWeitereAufwaendeZeit_h(		super.getWeitereAufwaendeZeit_h()		);
		
		return as;
	}
	
	
	public ArbeitssystemSeilkranLagerplatz getTeilsystemLagerplatz() {
		ArbeitssystemSeilkranLagerplatz as = new ArbeitssystemSeilkranLagerplatz();
		
		as.setKostensatzPersonal1_proH( 	super.getKostensatzPersonal1_proH() 	);
		as.setAnzahlPersonal(		 		this.getAnzahlPersonenLagerplatz()		);
		as.setEinsatzzeitPersonal_Prz( 		this.getEinsatzzeitPersonenLagerplatz_Prz()	);
		as.setKostensatzMaschine1_proH(		super.getKostensatzMaschine2_proH() 	); //Maschine2 = Kranfahrzeug
		as.setLaufzeitKranfahrzeug_Prz(		this.getLaufzeitKranfahrzeug_Prz()	 	);
		
		as.setTaeglicheArbeitszeit_Min( 	super.getTaeglicheArbeitszeit_Min() 	);
		as.setWegzeitenUndPausen_Min( 		super.getWegzeitenUndPausen_Min()		);
		as.setUmsetzenBetrag_CHF( 			super.getUmsetzenBetrag_CHF() 			);
		as.setUmsetzenZeit_h(				super.getUmsetzenZeit_h()				);
		as.setWeitereAufwaendeBetrag_CHF( 	super.getWeitereAufwaendeBetrag_CHF() 	);
		as.setWeitereAufwaendeZeit_h(		super.getWeitereAufwaendeZeit_h()		);
		
		return as;
	}
	
	
	
	
	public int getLaufzeitKranfahrzeug_Prz() {
		return laufzeitKranfahrzeug_Prz;
	}
	
	
	public void setLaufzeitKranfahrzeug_Prz(int laufzeitKranfahrzeug_Prz) {
		this.laufzeitKranfahrzeug_Prz = laufzeitKranfahrzeug_Prz;
	}
	
	
	public int getAnzahlPersonenInstallation() {
		return anzahlPersonenInstallation;
	}
	
	
	public void setAnzahlPersonenInstallation(int anzahlPersonenInstallation) {
		this.anzahlPersonenInstallation = anzahlPersonenInstallation;
	}
	
	
	public int getAnzahlPersonenSeilen() {
		return anzahlPersonenSeilen;
	}
	
	
	public void setAnzahlPersonenSeilen(int anzahlPersonenSeilen) {
		this.anzahlPersonenSeilen = anzahlPersonenSeilen;
	}
	
	
	public int getAnzahlPersonenLagerplatz() {
		return anzahlPersonenLagerplatz;
	}
	
	
	public void setAnzahlPersonenLagerplatz(int anzahlPersonenLagerplatz) {
		this.anzahlPersonenLagerplatz = anzahlPersonenLagerplatz;
	}
	
	
	public int getEinsatzzeitPersonenLagerplatz_Prz() {
		return einsatzzeitPersonenLagerplatz_Prz;
	}
	
	
	public void setEinsatzzeitPersonenLagerplatz_Prz(int einsatzzeitPersonenLagerplatz_Prz) {
		this.einsatzzeitPersonenLagerplatz_Prz = einsatzzeitPersonenLagerplatz_Prz;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat, String waehrung) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat, waehrung);

		list.add(PdfLabels.ArbeitssystemSeilkranGesamt_LaufzeitKranfahrzeug_Prz, laufzeitKranfahrzeug_Prz);
		
		list.add(PdfLabels.ArbeitssystemSeilkranGesamt_AnzahlPersonenInstallation, anzahlPersonenInstallation);
		list.add(PdfLabels.ArbeitssystemSeilkranGesamt_AnzahlPersonenSeilen, anzahlPersonenSeilen);
		list.add(PdfLabels.ArbeitssystemSeilkranGesamt_AnzahlPersonenLagerplatz, anzahlPersonenLagerplatz);
		
		list.add(PdfLabels.ArbeitssystemSeilkranGesamt_EinsatzzeitPersonenLagerplatz_Prz, einsatzzeitPersonenLagerplatz_Prz);
		
		return list;
	}


}
