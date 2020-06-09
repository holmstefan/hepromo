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
import ch.wsl.fps.hepromo.model.ModelStrings;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHacker2018.Zielsortiment;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018;
import ch.wsl.fps.hepromo.model.asys.ArbeitssystemHacker2018.HackerMotorleistung;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektHackschnitzelTransport2018 extends Arbeitsobjekt {

	private static final long serialVersionUID = 1L;
	
	private boolean aufnehmenBeladenerMulde;
	private Zielsortiment zielsortiment;
	private HackerMotorleistung hackerMotorleistung;
	private HackerMotorleistung[] allHackerMotorleistung = (new ArbeitssystemHacker2018()).getAllHackerMotorleistung();
	
	private double distanzWaldstrasse_km;
	private double distanzInnerAusserorts_km;
	private double distanzAutobahn_km;
	

	public boolean isAufnehmenBeladenerMulde() {
		return aufnehmenBeladenerMulde;
	}

	public void setAufnehmenBeladenerMulde(boolean aufnehmenBeladenerMulde) {
		this.aufnehmenBeladenerMulde = aufnehmenBeladenerMulde;
	}

	public Zielsortiment getZielsortiment() {
		return zielsortiment;
	}

	public void setZielsortiment(Zielsortiment zielsortiment) {
		this.zielsortiment = zielsortiment;
	}

	public HackerMotorleistung getHackerMotorleistung() {
		return hackerMotorleistung;
	}

	public void setHackerMotorleistung(HackerMotorleistung hackerMotorleistung) {
		this.hackerMotorleistung = hackerMotorleistung;
	}
	
	
	

	public double getDistanzWaldstrasse_km() {
		return distanzWaldstrasse_km;
	}
	
	public void setDistanzWaldstrasse_km(double distanzWaldstrasse_km) {
		this.distanzWaldstrasse_km = distanzWaldstrasse_km;
	}

	public double getDistanzInnerAusserorts_km() {
		return distanzInnerAusserorts_km;
	}
	
	public void setDistanzInnerAusserorts_km(double distanzInnerAusserorts_km) {
		this.distanzInnerAusserorts_km = distanzInnerAusserorts_km;
	}
	
	public double getDistanzAutobahn_km() {
		return distanzAutobahn_km;
	}
	
	public void setDistanzAutobahn_km(double distanzAutobahn_km) {
		this.distanzAutobahn_km = distanzAutobahn_km;
	}
	
	
	
	public HackerMotorleistung[] getAllHackerMotorleistung() {
		return allHackerMotorleistung;
	}
	
	public void setAllHackerMotorleistung(HackerMotorleistung[] allHackerMotorleistung) {
		this.allHackerMotorleistung = allHackerMotorleistung;
	}
	
	// for testing purposes only
	public static HackerMotorleistung getBenutzerdefinierteHackerMotorleistung(int motorleistung_kW) {
		return ArbeitssystemHacker2018.getBenutzerdefinierteHackerMotorleistung(motorleistung_kW);
	}


	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		if (aufnehmenBeladenerMulde) {
			list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.ArtDesBeladens"), ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.AufnehmenBeladenerMulde")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.ArtDesBeladens"), ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.HackenInMulde")); //$NON-NLS-1$ //$NON-NLS-2$
			list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.Poltersortiment"), zielsortiment); //$NON-NLS-1$
			list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.HackerMotorleistung"), hackerMotorleistung.toString()); //$NON-NLS-1$
		}
		
		list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.DistanzWaldstrasse_km"),  distanzWaldstrasse_km); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.DistanzInnerAusserorts_km"),  distanzInnerAusserorts_km); //$NON-NLS-1$
		list.add(ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.DistanzAutobahn_km"),  distanzAutobahn_km); //$NON-NLS-1$
		
		return list;
	}
	
	
	@Override
	protected String getPdfLabelHolzmenge() {
		return ModelStrings.getString("ArbeitsobjektHackschnitzelTransport2018.Hackgutmenge_Srm");
	}
}
