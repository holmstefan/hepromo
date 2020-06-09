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
import java.util.List;

import ch.wsl.fps.hepromo.model.LabelValuePairList;
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.MaschinenStandort;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranInstallation.Seilsystem;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektMobilseilkranSeilen.SchwierigkeitSeitlicherZuzug;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektMobilseilkranGesamt extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;
	
	private Seilsystem seilsystem;
	private MaschinenStandort maschinenStandort;
	private int linienLaenge_m;
	
	private boolean linieAbsteckenOhneProjekt;
	
	private List<Integer> listStuetzenTragseilHoehen;
	
	private boolean endmast;
	private int tragseilHoeheEndmast;
	
	
	private double mittleresStueckvolumen_m3;
	private int mittlereFahrdistanz_m;
	private int mittlereDistanzSeitlicherZuzug_m;
	private SchwierigkeitSeitlicherZuzug schwierigkeitSeitlicherZuzug;
	
	
	
	
	public ArbeitsobjektSeilkranPlanung getTeilobjektPlanung() {
		ArbeitsobjektSeilkranPlanung ao = new ArbeitsobjektSeilkranPlanung();

		ao.setHolzmenge_m3(					super.getHolzmenge_m3()					);
		ao.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		ao.setRindenabzugsfaktorManuell(	super.getRindenAbzugFaktor()			);
		ao.setLinienlaenge_m(				this.getLinienLaenge_m()				);
		ao.setLinieAbsteckenOhneProjekt(	this.linieAbsteckenOhneProjekt			);
		
		return ao;
	}
	
	
	public ArbeitsobjektMobilseilkranInstallation getTeilobjektInstallation() {
		ArbeitsobjektMobilseilkranInstallation ao = new ArbeitsobjektMobilseilkranInstallation();

		ao.setHolzmenge_m3(				super.getHolzmenge_m3()				);
		ao.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		ao.setRindenabzugsfaktorManuell(super.getRindenAbzugFaktor()		);
		ao.setSeilsystem(				this.getSeilsystem()				);
		ao.setMaschinenStandort(		this.getMaschinenStandort()			);
		ao.setLinienLaenge_m(			this.getLinienLaenge_m()			);
		ao.setStuetzenTragseilHoehen(	this.getStuetzenTragseilHoehen()	);
		ao.setEndmast(					this.isEndmast()					);
		ao.setTragseilHoeheEndmast(		this.getTragseilHoeheEndmast()		);
		
		return ao;
	}
	
	
	public ArbeitsobjektMobilseilkranSeilen getTeilobjektSeilen() {
		ArbeitsobjektMobilseilkranSeilen ao = new ArbeitsobjektMobilseilkranSeilen();
		
		ao.setMittleresStueckvolumen_m3(		this.getMittleresStueckvolumen_m3()			);
		ao.setHolzmenge_m3(						super.getHolzmenge_m3()						);
		ao.setRindenabzugsfaktorManuellGesetzt(	super.isRindenabzugsfaktorManuellGesetzt()	);
		ao.setRindenabzugsfaktorManuell(		super.getRindenAbzugFaktor()				);
		ao.setMittlereFahrdistanz_m(			this.getMittlereFahrdistanz_m()				);
		ao.setMittlereDistanzSeitlicherZuzug_m(	this.getMittlereDistanzSeitlicherZuzug_m()	);
		ao.setSchwierigkeitSeitlicherZuzug(		this.getSchwierigkeitSeitlicherZuzug()		);
		
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
	
	
	
	
	public Seilsystem getSeilsystem() {
		return seilsystem;
	}
	
	
	public void setSeilsystem(Seilsystem seilsystem) {
		this.seilsystem = seilsystem;
	}
	
	
	public MaschinenStandort getMaschinenStandort() {
		return maschinenStandort;
	}
	
	
	public void setMaschinenStandort(MaschinenStandort maschinenStandort) {
		this.maschinenStandort = maschinenStandort;
	}
	
	
	public int getLinienLaenge_m() {
		return linienLaenge_m;
	}
	
	
	public void setLinienLaenge_m(int linienLaenge_m) {
		this.linienLaenge_m = linienLaenge_m;
	}
	
	
	public boolean isLinieAbsteckenOhneProjekt() {
		return linieAbsteckenOhneProjekt;
	}

	
	public void setLinieAbsteckenOhneProjekt(boolean linieAbsteckenOhneProjekt) {
		this.linieAbsteckenOhneProjekt = linieAbsteckenOhneProjekt;
	}
	
	
	public List<Integer> getStuetzenTragseilHoehen() {
		return listStuetzenTragseilHoehen;
	}
	
	
	public void setStuetzenTragseilHoehen(List<Integer> listStuetzenTragseilHoehen) {
		this.listStuetzenTragseilHoehen = listStuetzenTragseilHoehen;
	}
	
	
	public boolean isEndmast() {
		return endmast;
	}
	
	
	public void setEndmast(boolean endmast) {
		this.endmast = endmast;
	}
	
	
	public int getTragseilHoeheEndmast() {
		return tragseilHoeheEndmast;
	}
	
	
	public void setTragseilHoeheEndmast(int tragseilHoeheEndmast) {
		this.tragseilHoeheEndmast = tragseilHoeheEndmast;
	}
	
	
	public double getMittleresStueckvolumen_m3() {
		return mittleresStueckvolumen_m3;
	}
	
	
	public void setMittleresStueckvolumen_m3(double mittleresStueckvolumen_m3) {
		this.mittleresStueckvolumen_m3 = mittleresStueckvolumen_m3;
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
	
	
	public SchwierigkeitSeitlicherZuzug getSchwierigkeitSeitlicherZuzug() {
		return schwierigkeitSeitlicherZuzug;
	}
	
	
	public void setSchwierigkeitSeitlicherZuzug(SchwierigkeitSeitlicherZuzug schwierigkeitSeitlicherZuzug) {
		this.schwierigkeitSeitlicherZuzug = schwierigkeitSeitlicherZuzug;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);
		
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_Seilsystem,  seilsystem); 
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_Maschinenstandort,  maschinenStandort); 
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_Linienlaenge_m,  linienLaenge_m);
		
		for (int i=0; i<listStuetzenTragseilHoehen.size(); i++) {
			int stuetzenNummer = i + 1;
			list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_TragseilhoeheStuetze + " " +  stuetzenNummer, listStuetzenTragseilHoehen.get(i)); //$NON-NLS-1$ 
		}
		
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_Endmast,  endmast); 
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_TragseilhoeheEndmast,  tragseilHoeheEndmast); 
		
		
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_MittleresStueckvolumen_m3,  mittleresStueckvolumen_m3); 
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_MittlereFahrdistanz_m,  mittlereFahrdistanz_m); 
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_MittlereDistanzSeitlicherZuzug_m,  mittlereDistanzSeitlicherZuzug_m); 
		list.add(PdfLabels.ArbeitsobjektMobilseilkranGesamt_SchwierigkeitSeitlicherZuzug,  schwierigkeitSeitlicherZuzug); 
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}
	
}
