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

import java.text.DecimalFormat;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisHelikopterFliegen extends Ergebnis {
	
	protected double rotationszeit;


	public void setRotationszeit(double rotationszeit) {
		this.rotationszeit = rotationszeit;
	}

	public double getRotationszeit() {
		return rotationszeit;
	}
	
	
	
	@Override
	protected String[][] getErgebnisStrings(DecimalFormat df, String waehrung) {
		
		String[][] strings = new String[11][];

		strings[ 0] = 							new String[]{"", 												"Zeitaufwand (Std.)", 					"", 		"Kosten (" + waehrung + ")", 					""											};
		strings[ 1] = 							new String[]{"Dauer der Arbeit",								df.format(getZeitTotal()), 				"", 		"pro m3", 										"total"										};
		strings[ 2] = labelPersonal1 != null ? 	new String[]{labelPersonal1,									df.format(getZeitPersonal()), 			"WPPH", 	df.format(getKostenPersonal_proM3()), 			df.format(getKostenPersonal_total()) 			} : null;
		strings[ 3] = labelMaschine1 != null ? 	new String[]{labelMaschine1, 									df.format(getZeitMaschine1()), 			"PMH15", 	df.format(getKostenMaschine1_proM3()),	 		df.format(getKostenMaschine1_total()) 			} : null;
		strings[ 4] = labelMaschine2 != null ? 	new String[]{labelMaschine2, 									df.format(getZeitMaschine2()), 			"PMH15", 	df.format(getKostenMaschine2_proM3()),	 		df.format(getKostenMaschine2_total()) 			} : null;
//		strings[ 5] = 							new String[]{"Umsetzen", 										df.format(getZeitUmsetzen()), 			"", 		df.format(getKostenUmsetzen_proM3()), 			df.format(getKostenUmsetzen_total()) 			};
		strings[ 6] = 							new String[]{"Weitere Aufwände", 								df.format(getZeitWeitereAufwaende()), 	"", 		df.format(getKostenWeitereAufwaende_proM3()), 	df.format(getKostenWeitereAufwaende_total()) 	};
		strings[ 7] = 							new String[]{"Total", 											"", 									"", 		df.format(getKostenTotal_proM3()), 				df.format(getKostenTotal_total()) 				};
		strings[ 8] = 							new String[]{};
		strings[ 9] = 							new String[]{"Produktivität (" + prodEinheit + ")", 	df.format(getProduktivitaet()),			"", 		"", 											""											};
		strings[10] = 							new String[]{"Rotationszeit (Min./Rot.)", 						df.format(getRotationszeit()),			"", 		"", 											""											};
		
		
		return strings;
	}
}
