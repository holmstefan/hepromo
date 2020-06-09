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
import ch.wsl.fps.hepromo.model.ModelStrings.PdfLabels;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.ArbeitsVerfahren;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterAufarbeiten.BaumartenGruppe;
import ch.wsl.fps.hepromo.model.aobj.ArbeitsobjektHelikopterFliegen.Holztyp;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ArbeitsobjektHelikopterGesamt extends Arbeitsobjekt {
	
	private static final long serialVersionUID = 1L;

	private Holztyp holztyp;
	private int horizontalDistanz_m;
	private int vertikalDistanz_m;
	
	private ArbeitsVerfahren arbeitsVerfahren;
	private BaumartenGruppe baumartenGruppe;
	

	
	public ArbeitsobjektHelikopterFliegen getTeilobjektFliegen() {
		ArbeitsobjektHelikopterFliegen arbeitsObjekt = new ArbeitsobjektHelikopterFliegen();
		
		arbeitsObjekt.setHolzmenge_m3(			super.getHolzmenge_m3()			);
		arbeitsObjekt.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		arbeitsObjekt.setRindenabzugsfaktorManuell(super.getRindenAbzugFaktor());
		arbeitsObjekt.setHolztyp(				this.getHolztyp()				);
		arbeitsObjekt.setHorizontalDistanz_m(	this.getHorizontalDistanz_m()	);
		arbeitsObjekt.setVertikalDistanz_m(		this.getVertikalDistanz_m()		);
		
		return arbeitsObjekt;
	}
	
	
	
	
	public ArbeitsobjektHelikopterAufarbeiten getTeilobjektAufarbeiten() {
		ArbeitsobjektHelikopterAufarbeiten arbeitsObjekt = new ArbeitsobjektHelikopterAufarbeiten();

		arbeitsObjekt.setHolzmenge_m3(		super.getHolzmenge_m3()			);
		arbeitsObjekt.setRindenabzugsfaktorManuellGesetzt(super.isRindenabzugsfaktorManuellGesetzt());
		arbeitsObjekt.setRindenabzugsfaktorManuell(super.getRindenAbzugFaktor());
		arbeitsObjekt.setBaumartenGruppe(	this.getBaumartenGruppe()		);
		arbeitsObjekt.setArbeitsVerfahren(	this.getArbeitsVerfahren()		);
		
		return arbeitsObjekt;
	}


	
	
	
	public Holztyp getHolztyp() {
		return holztyp;
	}
	
	public void setHolztyp(Holztyp holztyp) {
		this.holztyp = holztyp;
	}
	
	public int getHorizontalDistanz_m() {
		return horizontalDistanz_m;
	}
	
	public void setHorizontalDistanz_m(int horizontalDistanz_m) {
		this.horizontalDistanz_m = horizontalDistanz_m;
	}
	
	public int getVertikalDistanz_m() {
		return vertikalDistanz_m;
	}
	
	public void setVertikalDistanz_m(int vertikalDistanz_m) {
		this.vertikalDistanz_m = vertikalDistanz_m;
	}
	
	public ArbeitsVerfahren getArbeitsVerfahren() {
		return arbeitsVerfahren;
	}
	
	public void setArbeitsVerfahren(ArbeitsVerfahren arbeitsVerfahren) {
		this.arbeitsVerfahren = arbeitsVerfahren;
	}

	public BaumartenGruppe getBaumartenGruppe() {
		return baumartenGruppe;
	}

	public void setBaumartenGruppe(BaumartenGruppe baumartenGruppe) {
		this.baumartenGruppe = baumartenGruppe;
	}
	
	
	
	@Override
	public LabelValuePairList getLabelValuePairList(DecimalFormat decimalFormat) {
		LabelValuePairList list = super.getLabelValuePairList(decimalFormat);

		list.add(PdfLabels.ArbeitsobjektHelikopterGesamt_Holztyp, holztyp); 
		list.add(PdfLabels.ArbeitsobjektHelikopterGesamt_Horizontaldistanz_m, horizontalDistanz_m); 
		list.add(PdfLabels.ArbeitsobjektHelikopterGesamt_Vertikaldistanz_m, vertikalDistanz_m);
		if (arbeitsVerfahren == ArbeitsVerfahren.Vollbaumverfahren && holztyp.isNadelholz()) {
			list.add(PdfLabels.ArbeitsobjektHelikopterGesamt_Baumartengruppe, baumartenGruppe);
		}
		list.add(PdfLabels.ArbeitsobjektHelikopterGesamt_Arbeitsverfahren, arbeitsVerfahren); 
		
		return list;
	}
	
	
	@Override
	protected double getRindenAbzugFaktorBerechnet() {
		return 0.89;
	}
	
}
