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
package ch.wsl.fps.hepromo.model.calc;

import java.util.HashMap;

import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorKompartimente2018.Standort;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseCalculatorSchaftholztarif2018 {
	
	public static void main(String[] args) {
//		System.out.println(1.0E-05);
//		System.out.println(1.0E-5);
		
		System.out.println(getSchaftholzVolumen_m3iR(Tarifnummer.T201, 35, Gesamtwuchsleistung.Gut, Entwicklungsstufe.Baumholz2_41bis50cm, 800));
	}
	
	
	private static final HashMap<Tarifnummer, Double[]> modelKoeffizienten;
	
	static {
		modelKoeffizienten = new HashMap<>();
		
		modelKoeffizienten.put(Tarifnummer.T201, new Double[] {
				-9.6939329,
				 2.8757162,
				-0.00360841,
				 2.38E-05,
				 0.006454553,
				-0.35423996,
				-0.00019064
		});
		
		modelKoeffizienten.put(Tarifnummer.T202, new Double[] {
				-10.190717,
				  3.01181565,
				 -0.00436003,
				  5.66E-05,
				  0.005186263,
				  0.0,
				 -5.09E-05
		});
		
		modelKoeffizienten.put(Tarifnummer.T203, new Double[] {
				-10.40762,
				  3.14895427,
				 -0.00476514,
				  3.67E-05,
				  0.005617423,
				 -0.29285027,
				 -0.00020783
		});
		
		modelKoeffizienten.put(Tarifnummer.T204, new Double[] {
				-11.225599,
				  3.43239299,
				 -0.0058899,
				  3.39E-05,
				  0.005502126,
				 -0.28350633,
				 -0.00022606
		});
		
		modelKoeffizienten.put(Tarifnummer.T205, new Double[] {
				-11.024619,
				  3.20871603,
				 -0.0050543,
				  1.15564E-04,
				  0.003814261,
				 -0.25367643,
				 -4.37E-05
		});
		
		modelKoeffizienten.put(Tarifnummer.T216, new Double[] {
				-9.7605762,
				 2.83855622,
				-0.00324786,
				 4.15E-05,
				 0.006981857,
				-0.19001432,
				-0.00015251
		});
		
		modelKoeffizienten.put(Tarifnummer.T217, new Double[] {
				-10.869359,
				  3.20963764,
				 -0.00453908,
				  5.83E-05,
				  0.00326154,
				 -0.08886847,
				  0.0
		});
		
		modelKoeffizienten.put(Tarifnummer.T218, new Double[] {
				-10.596355,
				  3.11284073,
				 -0.00462775,
				  4.70E-05,
				  0.008531597,
				 -0.28987184,
				 -0.00015084
		});
		
		modelKoeffizienten.put(Tarifnummer.T219, new Double[] {
				-11.036856,
				  3.27767482,
				 -0.00587506,
				  3.35E-05,
				  0.014177976,
				 -0.12590653,
				 -0.00039528
		});
		
		modelKoeffizienten.put(Tarifnummer.T220, new Double[] {
				-8.1151843,
				 2.17166411,
				-0.00086928,
				 0.0,
				 0.007345411,
				-0.22094684,
				 0.0
		});
	}
	
	
	public static double getSchaftholzVolumen_m3iR(Tarifnummer tarif, int bhd_cm, Gesamtwuchsleistung gwl, Entwicklungsstufe es, int hoeheUeberMeer_m) {
		Double[] b = modelKoeffizienten.get(tarif);
		
		double vk = Math.exp(b[0]
				+ b[1] * Math.log(bhd_cm)
				+ b[2] * Math.pow(Math.log(bhd_cm), 4)
				+ b[3] * gwl.getValue()
				+ b[4] * es.getValue()
				+ b[6] * hoeheUeberMeer_m
				);
		
		return vk;
	}
	
	
	public enum Tarifnummer {
		T201, T202, T203, T204, T205,
		T216, T217, T218, T219, T220;
		
		public static Tarifnummer[] values(Boolean isNadelholz, Standort standort) {
			if (isNadelholz == null || standort == null) {
				return Tarifnummer.values();
			}
			if (isNadelholz == true) {
				switch (standort) {
				case Allg_Alpen_Hoehe1000bis1400m:
				case Allg_Alpen_Hoehe1401bis3000m:
				case Nadel_Alpen_Hoehe1000bis1500m:
				case Nadel_Alpen_Hoehe1501bis3000m:
					return new Tarifnummer[]{T204};

				case Allg_Jura_Hoehe0bis1250m:
				case Allg_Jura_Hoehe1251bis3000m:
				case Nadel_Jura_Hoehe0bis1250m:
				case Nadel_Jura_Hoehe1251bis3000m:
					return new Tarifnummer[]{T201};

				case Allg_Mittelland_Hoehe0bis1250m:
				case Allg_Mittelland_Hoehe1251bis3000m:
				case Nadel_Mittelland_Hoehe0bis1250m:
				case Nadel_Mittelland_Hoehe1251bis3000m:
					return new Tarifnummer[]{T202};

				case Allg_Voralpen_Hoehe0bis1250m:
				case Allg_Voralpen_Hoehe1251bis3000m:
				case Nadel_Voralpen_Hoehe0bis1250m:
				case Nadel_Voralpen_Hoehe1251bis3000m:
					return new Tarifnummer[]{T203};

				case Allg_AlpenSuedseite_Hoehe0bis1250m:
				case Allg_AlpenSuedseite_Hoehe1251bis3000m:
				case Nadel_AlpenSuedseite_Hoehe0bis1250m:
				case Nadel_AlpenSuedseite_Hoehe1251bis3000m:
					return new Tarifnummer[]{T205};
					
					//$CASES-OMITTED$
				default:
					throw new IllegalArgumentException(standort.toString());		
				}
			}
			if (isNadelholz == false) {
				switch (standort) {

				case Allg_Alpen_Hoehe1000bis1400m:
				case Allg_Alpen_Hoehe1401bis3000m:
					return new Tarifnummer[]{T219};

				case Allg_Jura_Hoehe0bis1250m:
				case Allg_Jura_Hoehe1251bis3000m:
				case Laub_Jura_Hoehe0bis3000m:
					return new Tarifnummer[]{T216};

				case Allg_Mittelland_Hoehe0bis1250m:
				case Allg_Mittelland_Hoehe1251bis3000m:
				case Laub_Mittelland_Hoehe0bis3000m:
					return new Tarifnummer[]{T217};

				case Allg_Voralpen_Hoehe0bis1250m:
				case Allg_Voralpen_Hoehe1251bis3000m:
				case Laub_Voralpen_Hoehe0bis1250m:
				case Laub_Voralpen_Hoehe1251bis3000m:
					return new Tarifnummer[]{T218};

				case Allg_AlpenSuedseite_Hoehe0bis1250m:
				case Allg_AlpenSuedseite_Hoehe1251bis3000m:
				case Laub_AlpenSuedseite_Hoehe0bis1250m:
				case Laub_AlpenSuedseite_Hoehe1251bis3000m:
					return new Tarifnummer[]{T220};
					
					//$CASES-OMITTED$
				default:
					throw new IllegalArgumentException(standort.toString());		
				}
			}
			throw new IllegalStateException("this should never happen"); //$NON-NLS-1$
		}
		
		public static Tarifnummer[] values(Standort standort) {
			if (standort == null) {
				return Tarifnummer.values();
			}
			switch (standort) {
			case Allg_Alpen_Hoehe1000bis1400m:
			case Allg_Alpen_Hoehe1401bis3000m:
				return new Tarifnummer[]{T204, T219};

			case Allg_Jura_Hoehe0bis1250m:
			case Allg_Jura_Hoehe1251bis3000m:
				return new Tarifnummer[]{T201, T216};

			case Allg_Mittelland_Hoehe0bis1250m:
			case Allg_Mittelland_Hoehe1251bis3000m:
				return new Tarifnummer[]{T202, T217};

			case Allg_Voralpen_Hoehe0bis1250m:
			case Allg_Voralpen_Hoehe1251bis3000m:
				return new Tarifnummer[]{T203, T218};

			case Allg_AlpenSuedseite_Hoehe0bis1250m:
			case Allg_AlpenSuedseite_Hoehe1251bis3000m:
				return new Tarifnummer[]{T205, T220};

				//$CASES-OMITTED$
			default:
				throw new IllegalArgumentException(standort.toString());		
			}
		}

		@Override
		public String toString() {
			switch(this) {
			case T201:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum201"); //$NON-NLS-1$
				
			case T202:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum202"); //$NON-NLS-1$
				
			case T203:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum203"); //$NON-NLS-1$
				
			case T204:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum204"); //$NON-NLS-1$
				
			case T205:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum205"); //$NON-NLS-1$
				
			case T216:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum216"); //$NON-NLS-1$
				
			case T217:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum217"); //$NON-NLS-1$
				
			case T218:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum218"); //$NON-NLS-1$
				
			case T219:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum219"); //$NON-NLS-1$
				
			case T220:
				return ModelStrings.getString("BiomasseCalculatorSchaftholztarif2018.enum220"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
		
		public int nr() {
			return Integer.valueOf(this.name().replaceAll("T", "")); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	
	public enum Gesamtwuchsleistung {
		Gering("BiomasseCalculatorSchaftholztarif2018.enumGering", 1500), //$NON-NLS-1$
		Maessig("BiomasseCalculatorSchaftholztarif2018.enumMaessig", 2300), //$NON-NLS-1$
		Gut("BiomasseCalculatorSchaftholztarif2018.enumGut", 3700), //$NON-NLS-1$
		SehrGut("BiomasseCalculatorSchaftholztarif2018.enumSehrGut", 5000); //$NON-NLS-1$
		
		private final String title;
		private final int value;
		
		private Gesamtwuchsleistung(String title, int value) {
			this.title = title;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return ModelStrings.getString(this.title);
		}

		public int getValue() {
			return this.value;
		}
	}
	
	
	public enum Entwicklungsstufe {
		Stangenholz_12bis30cm("BiomasseCalculatorSchaftholztarif2018.enumStangenholz", 21), //$NON-NLS-1$
		Baumholz1_31bis40cm("BiomasseCalculatorSchaftholztarif2018.enumBaumholz1", 35), //$NON-NLS-1$
		Baumholz2_41bis50cm("BiomasseCalculatorSchaftholztarif2018.enumBaumholz2", 45), //$NON-NLS-1$
		Baumholz3_ueber50cm("BiomasseCalculatorSchaftholztarif2018.enumBaumholz3", 55); //$NON-NLS-1$
		
		private final String title;
		private final int value;
		
		private Entwicklungsstufe(String title, int value) {
			this.title = title;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return ModelStrings.getString(this.title);
		}

		public int getValue() {
			return this.value;
		}
	}
	
}
