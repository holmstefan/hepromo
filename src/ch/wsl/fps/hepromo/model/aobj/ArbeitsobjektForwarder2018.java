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

import java.io.Serializable;
import java.text.DecimalFormat;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektForwarder2018 extends Arbeitsobjekt {
	
	private double energieholzmenge_m3iR;
	private double energieholzAusSchaftholz_m3iR;
	private double energieholzAusAstderbholzUndReisig_m3iR;
	private double verkaufRundholz_m3oR;
	private boolean holzmengenUnveraendertSeitBerechnungEnergieholzmengen; //TODO: ins pdf?
	
	private int bhdMit_cm;
	private Rueckeentfernung rueckeentfernung;
	private Hangneigung hangneigung;
	private AnzahlSortimente anzahlSortimente;
	private Erschwernisse erschwernisse;
	private AbstandRueckegasse abstandRueckegasse;
	
	private int zopfdurchmesser_cm;
	private Energieholzanfall energieholzanfall_m3iRproHa;
	
	private Energieholzanfall[] allEnergieholzanfall = {
			new Energieholzanfall(ModelStrings.getString("ArbeitsobjektForwarder2018.enumKlein"), 			 15, false), //$NON-NLS-1$
			new Energieholzanfall(ModelStrings.getString("ArbeitsobjektForwarder2018.enumMittel"), 			 70, false), //$NON-NLS-1$
			new Energieholzanfall(ModelStrings.getString("ArbeitsobjektForwarder2018.enumGross"), 	     	130, false), //$NON-NLS-1$
			new Energieholzanfall(ModelStrings.getString("ArbeitsobjektForwarder2018.enumBenutzerdefiniert"),70, true) //$NON-NLS-1$
	};

	private boolean einsatzThw;
	private int anzahlRueckegassen;


	public double getEnergieholzmenge_m3iR() {
		return energieholzmenge_m3iR;
	}

	public void setEnergieholzmenge_m3iR(double energieholzmenge_m3iR) {
		this.energieholzmenge_m3iR = energieholzmenge_m3iR;
	}

	public double getEnergieholzAusSchaftholz_m3iR() {
		return energieholzAusSchaftholz_m3iR;
	}

	public void setEnergieholzAusSchaftholz_m3iR(double energieholzAusSchaftholz_m3iR) {
		this.energieholzAusSchaftholz_m3iR = energieholzAusSchaftholz_m3iR;
	}

	public double getEnergieholzAusAstderbholzUndReisig_m3iR() {
		return energieholzAusAstderbholzUndReisig_m3iR;
	}

	public void setEnergieholzAusAstderbholzUndReisig_m3iR(double energieholzAusAstderbholzUndReisig_m3iR) {
		this.energieholzAusAstderbholzUndReisig_m3iR = energieholzAusAstderbholzUndReisig_m3iR;
	}

	public double getVerkaufRundholz_m3oR() {
		return verkaufRundholz_m3oR;
	}

	public void setVerkaufRundholz_m3oR(double verkaufRundholz_m3oR) {
		this.verkaufRundholz_m3oR = verkaufRundholz_m3oR;
	}

	public boolean isHolzmengenUnveraendertSeitBerechnungEnergieholzmengen() {
		return holzmengenUnveraendertSeitBerechnungEnergieholzmengen;
	}

	public void setHolzmengenUnveraendertSeitBerechnungEnergieholzmengen(boolean holzmengenUnveraendertSeitBerechnungEnergieholzmengen) {
		this.holzmengenUnveraendertSeitBerechnungEnergieholzmengen = holzmengenUnveraendertSeitBerechnungEnergieholzmengen;
	}

	public int getBhdMit_cm() {
		return bhdMit_cm;
	}

	public void setBhdMit_cm(int bhdMit_cm) {
		this.bhdMit_cm = bhdMit_cm;
	}

	public Rueckeentfernung getRueckeentfernung() {
		return rueckeentfernung;
	}

	public void setRueckeentfernung(Rueckeentfernung rueckeentfernung) {
		this.rueckeentfernung = rueckeentfernung;
	}

	public Hangneigung getHangneigung() {
		return hangneigung;
	}

	public void setHangneigung(Hangneigung hangneigung) {
		this.hangneigung = hangneigung;
	}

	public AnzahlSortimente getAnzahlSortimente() {
		return anzahlSortimente;
	}

	public void setAnzahlSortimente(AnzahlSortimente anzahlSortimente) {
		this.anzahlSortimente = anzahlSortimente;
	}

	public Erschwernisse getErschwernisse() {
		return erschwernisse;
	}

	public void setErschwernisse(Erschwernisse erschwernisse) {
		this.erschwernisse = erschwernisse;
	}

	public AbstandRueckegasse getAbstandRueckegasse() {
		return abstandRueckegasse;
	}

	public void setAbstandRueckegasse(AbstandRueckegasse abstandRueckegasse) {
		this.abstandRueckegasse = abstandRueckegasse;
	}

	public int getZopfdurchmesser_cm() {
		return zopfdurchmesser_cm;
	}

	public void setZopfdurchmesser_cm(int zopfdurchmesser_cm) {
		this.zopfdurchmesser_cm = zopfdurchmesser_cm;
	}

	public Energieholzanfall getEnergieholzanfall_m3iRproHa() {
		return energieholzanfall_m3iRproHa;
	}

	public void setEnergieholzanfall_m3iRproHa(Energieholzanfall energieholzanfall_m3iRproHa) {
		this.energieholzanfall_m3iRproHa = energieholzanfall_m3iRproHa;
	}
	
	public Energieholzanfall[] getAllEnergieholzanfall() {
		return allEnergieholzanfall;
	}
	
	public void setAllEnergieholzanfall(Energieholzanfall[] allEnergieholzanfall) {
		this.allEnergieholzanfall = allEnergieholzanfall;
	}

	public boolean isEinsatzThw() {
		return einsatzThw;
	}

	public void setEinsatzThw(boolean einsatzThw) {
		this.einsatzThw = einsatzThw;
	}

	public int getAnzahlRueckegassen() {
		return anzahlRueckegassen;
	}

	public void setAnzahlRueckegassen(int anzahlRueckegassen) {
		this.anzahlRueckegassen = anzahlRueckegassen;
	}
	

	public enum Rueckeentfernung {
		EntfKleiner200m,
		Entf201bis400m,
		Entf401bis600m,
		Entf601bis900m,
		Entf901bis1200m,
		EntfGroesser1200m;
		
		@Override
		public String toString() {
			switch(this) {
			case EntfKleiner200m:
				return "<200 m"; //$NON-NLS-1$
				
			case Entf201bis400m:
				return "201-400 m"; //$NON-NLS-1$
				
			case Entf401bis600m:
				return "401-600 m"; //$NON-NLS-1$
				
			case Entf601bis900m:
				return "601-900 m"; //$NON-NLS-1$
				
			case Entf901bis1200m:
				return "901-1200 m"; //$NON-NLS-1$
				
			case EntfGroesser1200m:
				return ">1200 m"; //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
		
		
		public static Rueckeentfernung getFromDistanceInMeters(double value) {
			if (value <= 200) {
				return EntfKleiner200m;
			}
			else if (value <= 400) {
				return Entf201bis400m;
			}
			else if (value <= 600) {
				return Entf401bis600m;
			}
			else if (value <= 900) {
				return Entf601bis900m;
			}
			else if (value <= 1200) {
				return Entf901bis1200m;
			}
			else {
				return EntfGroesser1200m;
			}
		}
	}
	
	
	public enum Hangneigung {
		NeigungBis15Prozent,
		Neigung15Bis25Prozent,
		Neigung25Bis35Prozent,
		Neigung35Bis45Prozent,
		NeigungGroesser45Prozent;
		
		@Override
		public String toString() {
			switch(this) {
			case NeigungBis15Prozent:
				return "<15 %"; //$NON-NLS-1$
				
			case Neigung15Bis25Prozent:
				return "15-24 %"; //$NON-NLS-1$
				
			case Neigung25Bis35Prozent:
				return "25-34 %"; //$NON-NLS-1$
				
			case Neigung35Bis45Prozent:
				return "35-44 %"; //$NON-NLS-1$
				
			case NeigungGroesser45Prozent:
				return ">45 %"; //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	public enum AnzahlSortimente {
		AnzahlBis3,
		Anzahl4Bis6,
		Anzahl7UndMehr;
		
		@Override
		public String toString() {
			switch(this) {
			case AnzahlBis3:
				return "1-3"; //$NON-NLS-1$
				
			case Anzahl4Bis6:
				return "4-6"; //$NON-NLS-1$
				
			case Anzahl7UndMehr:
				return ModelStrings.getString("ArbeitsobjektForwarder2018.enum7undMehr"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	public enum Erschwernisse {
		Keine,
		Wenige,
		Viele;
		
		@Override
		public String toString() {
			switch(this) {
			case Keine:
				return ModelStrings.getString("ArbeitsobjektForwarder2018.enumKeine"); //$NON-NLS-1$
				
			case Wenige:
				return ModelStrings.getString("ArbeitsobjektForwarder2018.enumWenige"); //$NON-NLS-1$
				
			case Viele:
				return ModelStrings.getString("ArbeitsobjektForwarder2018.enumViele"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	public enum AbstandRueckegasse {
		Circa40m,
		Circa30m,
		Circa20m;
		
		@Override
		public String toString() {
			switch(this) {
			case Circa40m:
				return "40 m"; //$NON-NLS-1$
				
			case Circa30m:
				return "30 m"; //$NON-NLS-1$
				
			case Circa20m:
				return "20 m"; //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	public static class Energieholzanfall implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public final String name;
		private int energieholzanfall_m3ProHa;
		private boolean wasChanged = false;
		private final boolean benutzerdefiniert;
		
		private Energieholzanfall(String name, int energieholzanfall_m3ProHa, boolean benutzerdefiniert) {
			this.name = name;
			this.energieholzanfall_m3ProHa = energieholzanfall_m3ProHa;
			this.benutzerdefiniert = benutzerdefiniert;
		}
		
		@Override
		public String toString() {
			if (benutzerdefiniert && ! wasChanged) {
				return "<html><nobr>" + name + "</nobr></html>"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			else {
				return "<html><nobr>" + name + " (" + energieholzanfall_m3ProHa + " m<sup>3</sup>)</nobr></html>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
		}
		
		public String toStringNoHtml() {
			return name + " (" + energieholzanfall_m3ProHa + " m3)"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		public int getEnergieholzanfall_m3ProHa() {
			return this.energieholzanfall_m3ProHa;
		}
		
		public boolean isBenutzerdefiniert() {
			return benutzerdefiniert;
		}
		
		public void setEnergieholzanfall_m3ProHa(int value) {
			if (this.benutzerdefiniert == true) {
				this.wasChanged = true;
				this.energieholzanfall_m3ProHa = value;
			}
			else {
				throw new RuntimeException("Wert kann nur bei benutzerdefiniertem Typ verändert werden."); //$NON-NLS-1$
			}
		}
	}
	
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.Energieholz_iR"),  		energieholzmenge_m3iR); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.EnergieholzAusSchaftholz_m3iR"),  			energieholzAusSchaftholz_m3iR); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.EnergieholzAusAstderbholzUndReisig_m3iR"),  energieholzAusAstderbholzUndReisig_m3iR); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.VerkaufRundholz_m3oR"), verkaufRundholz_m3oR); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.MittlererBhd_cm"),  	bhdMit_cm); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.RueckeEntfernung"),  	rueckeentfernung); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.Hangneigung"),  		hangneigung); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.AnzahlSortimente"),  	anzahlSortimente); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.Erschwernisse"),  		erschwernisse); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.AbstandRueckegasse"),  	abstandRueckegasse); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.Zopfdurchmesser_cm"),  	zopfdurchmesser_cm); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektForwarder2018.Energieholzanfall"),  	energieholzanfall_m3iRproHa.toStringNoHtml()); //$NON-NLS-1$
		list.add(ModelStrings.getString("Thw.EinsatzTraktionshilfswinde"),  				einsatzThw); //$NON-NLS-1$
		if (einsatzThw) {
			list.add(ModelStrings.getString("Thw.AnzahlRueckegassen"),  					anzahlRueckegassen); //$NON-NLS-1$
		}
		
		return list;
	}
	
	
	@Override
	protected String getPdfLabelHolzmenge() {
		return ModelStrings.getString("ArbeitsobjektForwarder2018.Schaftholz_m3iR");
	}
}
