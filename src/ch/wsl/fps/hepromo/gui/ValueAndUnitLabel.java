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
package ch.wsl.fps.hepromo.gui;

import java.text.DecimalFormat;

import javax.swing.JLabel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ValueAndUnitLabel extends JLabel {
	
	private Double value = null;
	private String unit = "";
	private DecimalFormat decimalFormat = AbstractErgebnisPanel.DEFAULT_DECIMAL_FORMAT;
	
	
	public void setValue(Double value) {
		this.value = value;
		updateLabel();
	}
	
	
	public void setUnit(String unit) {
		this.unit = unit;
		updateLabel();
	}
	
	
	public void setDecimalFormat(DecimalFormat decimalFormat) {
		this.decimalFormat = decimalFormat;
		updateLabel();
	}
	
	
	private void updateLabel() {
		if (value != null && unit != null && unit.isEmpty() == false) {
			super.setText("<html>(" + decimalFormat.format(value) + " " + unit + ")</html>)");
		}
		else {
			super.setText("");
		}
	}
	
	
	@Override
	public void setText(String text) {
		if (Thread.currentThread().getStackTrace()[2].getClassName().equals("javax.swing.JLabel")) {
			// Methode wurde von Konstruktor der Superklasse aufgerufen
			super.setText(text);
		}
		else {
			throw new UnsupportedOperationException("please use methods defined in " + this.getClass().getSimpleName());
		}
	}

}
