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

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * @author Stefan Holm
 *
 */
public class GuiStrings {
	
	private static final String BUNDLE_NAME = "ch.wsl.fps.hepromo.gui.guistrings"; //$NON-NLS-1$
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("de")); //$NON-NLS-1$

	private static ImageIcon imageIconBlue = null;
	private static ImageIcon imageIconGrey = null;
	private static ImageIcon imageIconRed = null;
	
	
	public static JLabel getInfoButtonBlue(String text) {
		JLabel label = new JLabel(getInfoIconBlue());
		label.setToolTipText(text);
		return label;
	}
	
	
	public static JLabel getInfoButtonGrey(String text) {
		JLabel label = new JLabel(getInfoIconGrey());
		label.setToolTipText(text);
		return label;
	}
	
	
	public static JLabel getInfoButtonRed(String text) {
		JLabel label = new JLabel(getInfoIconRed());
		label.setToolTipText(text);
		return label;
	}
	
	
	
	private static ImageIcon getInfoIconBlue() {
		if (imageIconBlue != null) {
			return imageIconBlue; //Datei wird nur beim ersten Zugriff gelesen
		}
		
		String filePath = "data/info.png"; //$NON-NLS-1$
		
		//try to open from jar
		URL imgURL = GuiStrings.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			imageIconBlue = new ImageIcon(imgURL);
			return imageIconBlue;
		}
		
		//otherwise, try to open from file system
		imageIconBlue = new ImageIcon(filePath);
		return imageIconBlue;
	}
	
	
	
	private static ImageIcon getInfoIconGrey() {
		if (imageIconGrey != null) {
			return imageIconGrey; //Datei wird nur beim ersten Zugriff gelesen
		}
		
		String filePath = "data/infoGrey.png"; //$NON-NLS-1$
		
		//try to open from jar
		URL imgURL = GuiStrings.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			imageIconGrey = new ImageIcon(imgURL);
			return imageIconGrey;
		}
		
		//otherwise, try to open from file system
		imageIconGrey = new ImageIcon(filePath);
		return imageIconGrey;
	}
	
	
	
	private static ImageIcon getInfoIconRed() {
		if (imageIconRed != null) {
			return imageIconRed; //Datei wird nur beim ersten Zugriff gelesen
		}
		
		String filePath = "data/info2.png"; //$NON-NLS-1$
		
		//try to open from jar
		URL imgURL = GuiStrings.class.getClassLoader().getResource(filePath);	
		if (imgURL != null) {
			imageIconRed = new ImageIcon(imgURL);
			return imageIconRed;
		}
		
		//otherwise, try to open from file system
		imageIconRed = new ImageIcon(filePath);
		return imageIconRed;
	}



	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			System.err.println("key not found: " + key); //$NON-NLS-1$
			return "XXXXXXXX"; //$NON-NLS-1$
		}
	}
	
	
	public static void setLocale(Locale locale) {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}
}
