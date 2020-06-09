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

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;

import java.awt.Frame;
import java.awt.Point;
import java.util.regex.Pattern;

import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.testng.testcase.AssertJSwingTestngTestCase;
import org.testng.annotations.Test;

/**
 * 
 * @author Stefan Holm
 *
 */
public class TestGuiTest extends AssertJSwingTestngTestCase {

	private FrameFixture frame;
	
	@Override
	public void onSetUp() {
//		robot().settings().delayBetweenEvents(400);
		
		application(MainWindow.class).start();
		
		frame = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
			@Override
			protected boolean isMatching(Frame frame) {
				return "HeProMo".equals(frame.getTitle()) && frame.isShowing();
			}
		}).using(robot());
	}


	@Test
	public void exemplaryTest() {
		frame.moveTo(new Point(0, 0));
		
		FailOnThreadViolationRepaintManager.uninstall();	
		frame.tree().node(2).click();
		FailOnThreadViolationRepaintManager.install();
		
		FrameFixture frame2 = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
			@Override
			protected boolean isMatching(Frame frame) {
				return "Radharvester".equals(frame.getTitle()) && frame.isShowing();
			}
		}).using(robot());
		frame2.moveTo(new Point(0, 0));

		frame2.textBox("txtProduktivitaet").requireEnabled();
		frame2.textBox("txtProduktivitaet").requireNotEditable();
		frame2.textBox("txtProduktivitaet").requireVisible();
		
		
		frame2.textBox("txtProduktivitaet").requireText("19.79");
		
		//Einheiten der Produktivitäten prüfen
		frame2.label("lblProduktivitaet").requireText(Pattern.compile(".*i\\.R\\..*PMH15.*"));
		frame2.label("lblProduktivitaet2").requireText(Pattern.compile(".*o\\.R\\..*PMH15.*"));
		
		frame2.spinner("txtHolzmenge_m3iR").select(1234d);
		frame2.textBox("txtProduktivitaet").requireText("19.79");
		
		frame2.spinner("txtBhd_cm").select(40);
		frame2.textBox("txtProduktivitaet").requireText("21.97");
		
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}	
	}

}
