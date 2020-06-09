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

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ErgebnisColorListCellRenderer extends DefaultListCellRenderer {
	
	private boolean overwriteBackgroundColor = false;
	
	@Override
	public Component getListCellRendererComponent(JList<?> list,Object value, int index,boolean isSelected,boolean cellHasFocus) {
		
		overwriteBackgroundColor = index == -1; //overwrite bgColor of selected item
		overwriteBackgroundColor |= isSelected == false; //overwrite bgColor of dropdown-list
		
//		overwriteBackgroundColor = index == -1 || isSelected == false;
		
		return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
	
    @Override
    public void paint(Graphics g) {
    	if (overwriteBackgroundColor) {
			setBackground(ErgebnisPanel.ERGEBNIS_PANEL_BACKGROUND_COLOR);
    	}
        super.paint(g);
    }
}
