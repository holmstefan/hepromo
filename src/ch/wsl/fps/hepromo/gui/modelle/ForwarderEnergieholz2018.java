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

import ch.wsl.fps.hepromo.gui.GuiStrings;
import ch.wsl.fps.hepromo.gui.MainWindow;

/**
 * 
 * @author Stefan Holm
 *
 */
public class ForwarderEnergieholz2018 extends Forwarder2018 {
	

	public ForwarderEnergieholz2018() {
		super.setTitle(GuiStrings.getString("ForwarderEnergieholz2018.Title")); //$NON-NLS-1$
		super.setSize((int) (740 * MainWindow.SIZE * MainWindow.WIDTH_FACTOR), (int) (850 * MainWindow.SIZE));
		
		super.initalize();
	}

}
