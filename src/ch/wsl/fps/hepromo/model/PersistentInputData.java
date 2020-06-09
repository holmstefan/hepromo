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

import java.io.Serializable;

import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.modelle.AbstractModel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class PersistentInputData implements HeProMoInputData, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String arbeitsort;
	private final Class<? extends AbstractModel> modelClass;
	private Arbeitsobjekt arbeitsobjekt;
	private Arbeitssystem arbeitssystem;
	private Faktoren faktoren;
	
	
	public PersistentInputData(AbstractModel model) {
		this.modelClass = model.getClass();
		this.arbeitsort = model.getArbeitsort();
		this.arbeitsobjekt = model.getArbeitsobjekt();
		this.arbeitssystem = model.getArbeitssystem();
		this.faktoren = model.getFaktoren();
	}
	

	@Override
	public Arbeitsobjekt getArbeitsobjekt() {
		return arbeitsobjekt;
	}

	@Override
	public Arbeitssystem getArbeitssystem() {
		return arbeitssystem;
	}

	@Override
	public Faktoren getFaktoren() {
		return faktoren;
	}


	public Class<? extends AbstractModel> getModelClass() {
		return modelClass;
	}


	@Override
	public String getArbeitsort() {
		return arbeitsort;
	}

}
