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

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

/**
 * 
 * @author Stefan Holm
 *
 */
public class HeProMoExceptionHandler {
	
	private static final boolean PRINT = true;
	private static volatile boolean DIALOG = false;
	private static volatile boolean STACKTRACE = false;

	public static void handle(Throwable e) {
		handle(e, null);
	}

	public static void handle(Throwable e, String msgPrefix) {
		if (DIALOG && STACKTRACE) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			JOptionPane.showMessageDialog(null, (msgPrefix != null ? msgPrefix : "") + sw.toString());
		}
		else if (DIALOG) {
			JOptionPane.showMessageDialog(null, (msgPrefix != null ? msgPrefix : "") + e);
		}
		
		if (PRINT) {
			if (msgPrefix != null) {
				System.err.print(msgPrefix);
			}
			e.printStackTrace();
		}
	}
	
	public static void setLogDialog(boolean flag) {
		DIALOG = flag;
	}
	
	public static void setLogStackTrace(boolean flag) {
		STACKTRACE = flag;
	}
}
