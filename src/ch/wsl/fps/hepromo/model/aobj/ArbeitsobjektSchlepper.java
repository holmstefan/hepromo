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

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektSchlepper extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private double mittlererStueckInhalt;
	private Schlagordnung schlagordnung;
	private AnzahlSortimente anzahlSortimente;
	private Fahrentfernung fahrentfernung;
	private Zuzugentfernung zuzugentfernung;
	private Rueckeart rueckeart;
	private RueckenImSaft rueckenImSaft;
	private Rueckebedingungen rueckeBedingungen;
	
	
	public double getMittlererStueckInhalt() {
		return mittlererStueckInhalt;
	}
	
	public void setMittlererStueckInhalt(double mittlererStueckInhalt) {
		this.mittlererStueckInhalt = mittlererStueckInhalt;
	}
	
	public Schlagordnung getSchlagordnung() {
		return schlagordnung;
	}
	
	public void setSchlagordnung(Schlagordnung schlagordnung) {
		this.schlagordnung = schlagordnung;
	}
	
	public AnzahlSortimente getAnzahlSortimente() {
		return anzahlSortimente;
	}
	
	public void setAnzahlSortimente(AnzahlSortimente anzahlSortimente) {
		this.anzahlSortimente = anzahlSortimente;
	}
	
	public Fahrentfernung getFahrentfernung() {
		return fahrentfernung;
	}
	
	public void setFahrentfernung(Fahrentfernung fahrentfernung) {
		this.fahrentfernung = fahrentfernung;
	}
	
	public Zuzugentfernung getZuzugentfernung() {
		return zuzugentfernung;
	}
	
	public void setZuzugentfernung(Zuzugentfernung zuzugentfernung) {
		this.zuzugentfernung = zuzugentfernung;
	}
	
	public Rueckeart getRueckeart() {
		return rueckeart;
	}
	
	public void setRueckeart(Rueckeart rueckeart) {
		this.rueckeart = rueckeart;
	}
	
	public RueckenImSaft getRueckenImSaft() {
		return rueckenImSaft;
	}
	
	public void setRueckenImSaft(RueckenImSaft rueckenImSaft) {
		this.rueckenImSaft = rueckenImSaft;
	}
	
	public Rueckebedingungen getRueckeBedingungen() {
		return rueckeBedingungen;
	}
	
	public void setRueckeBedingungen(Rueckebedingungen rueckeBedingungen) {
		this.rueckeBedingungen = rueckeBedingungen;
	}
	


	public enum Schlagordnung{
		WenigerAlsEinViertelNichtEingehalten,
		MehrAlsEinViertelNichtEingehalten;
		
		@Override
		public String toString() {
			switch (this) {
			case WenigerAlsEinViertelNichtEingehalten:
				return "weniger als 1/4 der Stücke nicht eingehalten"; 
			case MehrAlsEinViertelNichtEingehalten:
				return "mehr als 1/4 der Stücke nicht eingehalten"; 
			}
			throw new RuntimeException();
		}
	}
	
	
	
	public enum AnzahlSortimente {
		WenigerAlsVier,
		VierBisSechs,
		MehrAlsSechs;
		
		@Override
		public String toString() {
			switch (this) {
			case WenigerAlsVier:
				return "< 4"; //$NON-NLS-1$
			case VierBisSechs:
				return "4 - 6"; //$NON-NLS-1$
			case MehrAlsSechs:
				return "> 6"; //$NON-NLS-1$
			}
			throw new RuntimeException();
		}
	}
	
	
	
	public enum Fahrentfernung {
		Bis200m,
		Ab200bis400m,
		Ueber400m;
		
		@Override
		public String toString() {
			switch (this) {
			case Bis200m:
				return "< 200 m"; //$NON-NLS-1$
			case Ab200bis400m:
				return "200 - 400 m"; //$NON-NLS-1$
			case Ueber400m:
				return "> 400 m"; //$NON-NLS-1$
			}
			throw new RuntimeException();
		}
	}
	
	
	
	public enum Zuzugentfernung {
		Bis20m,
		Ab20bis30m,
		Ab30bis40m,
		Ueber40m;
		
		@Override
		public String toString() {
			switch (this) {
			case Bis20m:
				return "< 20 m"; //$NON-NLS-1$
			case Ab20bis30m:
				return "20 - 30 m"; //$NON-NLS-1$
			case Ab30bis40m:
				return "30 - 40 m"; //$NON-NLS-1$
			case Ueber40m:
				return "> 40 m"; //$NON-NLS-1$
			}
			throw new RuntimeException();
		}
	}
	
	
	
	public enum Rueckeart {
		NurVorruecken,
		NurFertigruecken,
		GesamtesRuecken;
		
		@Override
		public String toString() {
			switch (this) {
			case NurVorruecken:
				return "nur vorrücken"; 
			case NurFertigruecken:
				return "nur fertigrücken"; 
			case GesamtesRuecken:
				return "gesamtes Rücken"; 
			}
			throw new RuntimeException();
		}
	}
	
	
	
	public enum RueckenImSaft {
		Ja,
		Nein;
		
		@Override
		public String toString() {
			switch (this) {
			case Ja:
				return "ja"; 
			case Nein:
				return "nein"; 
			}
			throw new RuntimeException();
		}
	}
	
	
	
	public enum Rueckebedingungen {
		SehrEinfach,
		Einfach,
		Mittel,
		Schwierig,
		SehrSchwierig;
		
		@Override
		public String toString() {
			switch (this) {
			case SehrEinfach:
				return "sehr einfach"; 
			case Einfach:
				return "einfach"; 
			case Mittel:
				return "mittel"; 
			case Schwierig:
				return "schwierig"; 
			case SehrSchwierig:
				return "sehr schwierig"; 
			}
			throw new RuntimeException();
		}
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add("Mittlerer Stückinhalt", mittlererStueckInhalt); 
		list.add("Schlagordnung", schlagordnung); 
		list.add("Anzahl Sortimente", anzahlSortimente); 
		list.add("Fahrentfernung", fahrentfernung); 
		list.add("Zuzugentfernung", zuzugentfernung); 
		list.add("Rückeart", rueckeart); 
		list.add("Rücken im Saft", rueckenImSaft); 
		list.add("Rückebedingungen", rueckeBedingungen); 
		
		return list;
	}
	

}
