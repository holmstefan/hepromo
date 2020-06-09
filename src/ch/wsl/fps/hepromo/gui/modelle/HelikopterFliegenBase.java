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
package ch.wsl.fps.hepromo.gui.modelle;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

import ch.wsl.fps.hepromo.gui.HeProMoWindow;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.ArbeitswegePausenPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.HelikopterPanel;
import ch.wsl.fps.hepromo.gui.modelle.panel.asys.WeitereAufwaendePanel;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHelikopterFliegen.HelikopterKlasse;

public abstract class HelikopterFliegenBase extends HeProMoWindow {

	private static final long serialVersionUID = 1L;

	protected JSpinner txtHolzmenge_m3iR;
	protected JComboBox<Holztyp> cmbHolztyp;
	protected JSpinner txtHorizontalDistanz_m;
	protected JSpinner txtVertikalDistanz_m;
	
	protected HelikopterPanel panelHelikopter;
	protected ArbeitswegePausenPanel panelArbeitswegePausen;
	protected WeitereAufwaendePanel panelWeitereAufwaende;
	
	
	
	
	public void adaptLastvolumen() {
		if (panelHelikopter != null && panelHelikopter.isLastvolumenAutomatischBerechnen()) {
			double lastVolumen = calcLastvolumen_m3();
			panelHelikopter.setLastvolumen_m3(lastVolumen);
		}
	}
	
	
	
	
	private double calcLastvolumen_m3() { //TODO: Berechnung gehört eigentlich ins Model, nicht ins GUI -> Flag lastVolumenAutomatischBerechnen ist dort bereits vorhanden
		Holztyp holztyp = (Holztyp) cmbHolztyp.getSelectedItem();
		HelikopterKlasse heliklasse = panelHelikopter.getHelikopterKlasse();
		
		double spezGewicht = 0.0;
		switch(holztyp) {
		case Laubholz_frisch:
			spezGewicht = 10.0;
			break;
			
		case Laubholz_angetrocknet:
			spezGewicht = 9.0;
			break;
			
		case Nadelholz_frisch:
			spezGewicht = 8.4;
			break;
			
		case Nadelholz_angetrocknet:
			spezGewicht = 7.5;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		double lastKapaziaet = 0.0;
		switch (heliklasse) {
		case Leicht:
			lastKapaziaet = 11.0;
			break;
			
		case Mittel:
			lastKapaziaet = 30.0;
			break;
			
		case Schwer:
			lastKapaziaet = 49.0;
			break;
			
		default:
			throw new RuntimeException();
		}
		
		double ausnutzungsgrad = 0.8;
		double lastVolumen = lastKapaziaet / spezGewicht;
		lastVolumen *= ausnutzungsgrad;
		
		return lastVolumen;
	}
	
	
	
	@Override
	protected void updateWaehrungskuerzel(String newValue) {
		super.updateWaehrungskuerzel(newValue);
		panelHelikopter.updateWaehrungskuerzel(newValue);
		panelWeitereAufwaende.updateWaehrungskuerzel(newValue);
	}
}
