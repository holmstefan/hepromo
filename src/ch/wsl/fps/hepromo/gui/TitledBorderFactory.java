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

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Stefan Holm
 *
 */
public class TitledBorderFactory {
	
	public static TitledBorder createTitledBorder(String title) {
		return BorderFactory.createTitledBorder(getBaseBorder(), title);
	}
	
	public static TitledBorder createTitledBorderBold(String title) {
		TitledBorder result =  BorderFactory.createTitledBorder(getBaseBorder(), title);

		//set title font to bold
		Font titleFont = result.getTitleFont();
		if (titleFont == null) { //needed since java 7, see http://bugs.java.com/view_bug.do?bug_id=7022041
//			titleFont = UIManager.getDefaults().getFont("TitledBorder.font");
			titleFont = UIManager.getFont("TitledBorder.font"); //$NON-NLS-1$
		}
		result.setTitleFont( titleFont.deriveFont(Font.BOLD) );
		
		return result;
	}
	
	public static Border getBaseBorder() {
		if (isWindowsAndJava9OrHigher()) {
			// Create a border that looks similar to the one in Java 8
	        Border baseBorderOuter = BorderFactory.createLineBorder(new Color(213, 223, 229), 1, true);
	        Border baseBorderInner = BorderFactory.createLineBorder(Color.WHITE, 1, true);
	        Border baseBorder = BorderFactory.createCompoundBorder(baseBorderOuter, baseBorderInner);
	        return baseBorder;
		}
		else {
			return null;
		}
	}
	
	
	/**
	 * Starting with Java 9, appearance of TitledBorder is different than before (border is darker and doesn't have round corners).
	 * -> see https://stackoverflow.com/questions/68532836/changed-appearance-of-swing-titledborder-since-java-9
	 */
	private static boolean isWindowsAndJava9OrHigher() {
		return isWindows() && isJava9OrHigher();
	}
	
	
	private static boolean isWindows() {
		try {
			String osName = System.getProperty("os.name");
			if (osName.startsWith("Windows")) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false; // saftey net if anything fails in the code above, e.g. due to new java or OS versions.
		}
	}
	
	
	private static boolean isJava9OrHigher() {
		try {
			String javaVersion = System.getProperty("java.version");
			boolean isJava9OrHigher;
			if (javaVersion.startsWith("1.")) { // e.g. 1.5 or 1.8, (1.9 does not exist!)
				// version is <= Java 8
				isJava9OrHigher = false;
			}
			else {
//				int majorVersion = Integer.parseInt(javaVersion.split("\\.")[0]);
//				isJava9OrHigher = majorVersion >= 9;
				isJava9OrHigher = true;
			}
			return isJava9OrHigher;
		} catch (Exception e) {
			e.printStackTrace();
			return true; // saftey net if anything fails in the code above, e.g. due to new java versions.
		}
	}
}
