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

import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;

/**
 * 
 * @author Stefan Holm
 *
 */
public class FeinerschliessungGelaendebedingungen {
	
	public enum HindernisKategorie {
		Keine,
		Wenige,
		Viele,
		SehrViele;
		
		@Override
		public String toString() {
			switch(this) {
			case Keine:
				return PdfLabels.FeinerschliessungGelaendebedingungen_HindernisKatKeine.toString(); 
				
			case Wenige:
				return PdfLabels.FeinerschliessungGelaendebedingungen_HindernisKatWenige.toString(); 
				
			case Viele:
				return PdfLabels.FeinerschliessungGelaendebedingungen_HindernisKatViele.toString(); 
				
			case SehrViele:
				return PdfLabels.FeinerschliessungGelaendebedingungen_HindernisKatSehrViele.toString(); 
			
			default:
				throw new RuntimeException();
			}
		}


		
		public double value() {
			switch(this) {
			case Keine:
				return 1;
				
			case Wenige:
				return 2;
				
			case Viele:
				return 3;
				
			case SehrViele:
				return 4;
			
			default:
				throw new RuntimeException();
			}
		}
	}
	
	
	
	public enum NeigungsKategorie {
		NK_bis10,
		NK_10bis20,
		NK_ueber20;
		
		@Override
		public String toString() {
			switch(this) {
			case NK_bis10:
				return PdfLabels.FeinerschliessungGelaendebedingungen_NeigungsKatBis10Prz.toString(); 
				
			case NK_10bis20:
				return PdfLabels.FeinerschliessungGelaendebedingungen_NeigungsKat10Bis20Prz.toString(); 
				
			case NK_ueber20:
				return PdfLabels.FeinerschliessungGelaendebedingungen_NeigungsKatUeber20Prz.toString(); 
			
			default:
				throw new RuntimeException();
			}
		}


		
		public double value() {
			switch(this) {
			case NK_bis10:
				return 1;
				
			case NK_10bis20:
				return 2;
				
			case NK_ueber20:
				return 3;
			
			default:
				throw new RuntimeException();
			}
		}
	}
}
