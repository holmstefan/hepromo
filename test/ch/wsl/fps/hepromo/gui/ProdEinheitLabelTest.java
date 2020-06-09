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
import java.util.regex.Pattern;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.FrameFixture;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ch.wsl.fps.hepromo.gui.modelle.ForwarderEnergieholz2018;
import ch.wsl.fps.hepromo.gui.modelle.ForwarderRundholz2018;
import ch.wsl.fps.hepromo.gui.modelle.Hacker2018;
import ch.wsl.fps.hepromo.gui.modelle.HackschnitzelTransport2018;
import ch.wsl.fps.hepromo.gui.modelle.Kombiseilgeraet2018;
import ch.wsl.fps.hepromo.gui.modelle.KonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.MobilseilkranGesamt;
import ch.wsl.fps.hepromo.gui.modelle.MotormanuellGesamt2014;
import ch.wsl.fps.hepromo.gui.modelle.Radharvester2014;
import ch.wsl.fps.hepromo.gui.modelle.Schlepper2014;
import ch.wsl.fps.hepromo.gui.modelle.Vorruecken2018;
import ch.wsl.fps.hepromo.model.Ergebnis.ProdEinheit;
import ch.wsl.fps.hepromo.model.Faktoren;
import ch.wsl.fps.hepromo.model.HeProMoInputData;
import ch.wsl.fps.hepromo.model.aobj.Arbeitsobjekt;
import ch.wsl.fps.hepromo.model.asys.Arbeitssystem;
import ch.wsl.fps.hepromo.model.calc.CalculatorForwarder2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorHacker2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorHackschnitzelTransport2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorKombiseilgeraet2018;
import ch.wsl.fps.hepromo.model.calc.CalculatorKonventionellerSeilkranGesamt;
import ch.wsl.fps.hepromo.model.calc.CalculatorMobilseilkranGesamt;
import ch.wsl.fps.hepromo.model.calc.CalculatorMotormanuellGesamt2014;
import ch.wsl.fps.hepromo.model.calc.CalculatorRadharvester2014;
import ch.wsl.fps.hepromo.model.calc.CalculatorSchlepper2014;
import ch.wsl.fps.hepromo.model.calc.CalculatorVorruecken2018;

/**
 * 
 * Überprüft, ob die im GUI angezeigten Produktivitäts-Einheiten mit den 
 * in den Calculator-Klassen definierten Einheiten übereinstimmen.
 * 
 * @author Stefan Holm
 *
 */
public class ProdEinheitLabelTest {

	private Robot robot = BasicRobot.robotWithNewAwtHierarchy();
	private FrameFixture frameHeProMo;
	private FrameFixture frameCurrentModel;
	
	@BeforeClass
	public void setup() {
		robot.settings().delayBetweenEvents(0);
		robot.settings().eventPostingDelay(0);
		initMainWindow();
	}
	
	
	private void initMainWindow() {
		application(MainWindow.class).start();
		findHeProMoMainWindow();
	}
	
	
	private void findHeProMoMainWindow() {
		frameHeProMo = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
			@Override
			protected boolean isMatching(Frame frame) {
				return "HeProMo".equals(frame.getTitle()) && frame.isShowing();
			}
		}).using(robot);
		
	}
	
	
	private static enum ModelToTest {
		MotormanuellGesamt2014,
		Radharvester2014,
		Schlepper2014,
		Hacker2018,
		HackschnitzelTransport2018,
		Kombiseilgeraet2018_MontageDemontage,
		Kombiseilgeraet2018_FaellenRuecken,
		Kombiseilgeraet2018_VortransportierenLagern,
		Kombiseilgeraet2018_Gesamt,
		Vorruecken2018,
		Forwarder2018_Rundholz,
		Forwarder2018_Energieholz,
		KonventionellerSeilkranGesamt,
		MobilseilkranGesamt;
	}
	
	
	@Test
	private void testMotormanuellGesamt2014_DE() {
		testModel(ModelToTest.MotormanuellGesamt2014, Language.DE);
	}
	
	
	@Test
	private void testMotormanuellGesamt2014_FR() {
		testModel(ModelToTest.MotormanuellGesamt2014, Language.FR);
	}
	
	
	@Test
	private void testMotormanuellGesamt2014_IT() {
		testModel(ModelToTest.MotormanuellGesamt2014, Language.IT);
	}
	
	
	@Test
	private void testMotormanuellGesamt2014_EN() {
		testModel(ModelToTest.MotormanuellGesamt2014, Language.EN);
	}
	
	
	@Test
	private void testRadharvester2014_DE() {
		testModel(ModelToTest.Radharvester2014, Language.DE);
	}
	
	
	@Test
	private void testRadharvester2014_FR() {
		testModel(ModelToTest.Radharvester2014, Language.FR);
	}
	
	
	@Test
	private void testRadharvester2014_IT() {
		testModel(ModelToTest.Radharvester2014, Language.IT);
	}
	
	
	@Test
	private void testRadharvester2014_EN() {
		testModel(ModelToTest.Radharvester2014, Language.EN);
	}
	
	
	@Test
	private void testSchlepper2014_DE() {
		testModel(ModelToTest.Schlepper2014, Language.DE);
	}
	
	
	@Test
	private void testSchlepper2014_FR() {
		testModel(ModelToTest.Schlepper2014, Language.FR);
	}
	
	
	@Test
	private void testSchlepper2014_IT() {
		testModel(ModelToTest.Schlepper2014, Language.IT);
	}
	
	
	@Test
	private void testSchlepper2014_EN() {
		testModel(ModelToTest.Schlepper2014, Language.EN);
	}
	
	
	@Test
	private void testHacker2018_DE() {
		testModel(ModelToTest.Hacker2018, Language.DE);
	}
	
	
	@Test
	private void testHacker2018_FR() {
		testModel(ModelToTest.Hacker2018, Language.FR);
	}
	
	
	@Test
	private void testHacker2018_IT() {
		testModel(ModelToTest.Hacker2018, Language.IT);
	}
	
	
	@Test
	private void testHacker2018_EN() {
		testModel(ModelToTest.Hacker2018, Language.EN);
	}
	
	
	@Test
	private void testHackschnitzelTransport2018_DE() {
		testModel(ModelToTest.HackschnitzelTransport2018, Language.DE);
	}
	
	
	@Test
	private void testHackschnitzelTransport2018_FR() {
		testModel(ModelToTest.HackschnitzelTransport2018, Language.FR);
	}
	
	
	@Test
	private void testHackschnitzelTransport2018_IT() {
		testModel(ModelToTest.HackschnitzelTransport2018, Language.IT);
	}
	
	
	@Test
	private void testHackschnitzelTransport2018_EN() {
		testModel(ModelToTest.HackschnitzelTransport2018, Language.EN);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_MontageDemontage_DE() {
		testModel(ModelToTest.Kombiseilgeraet2018_MontageDemontage, Language.DE, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_MontageDemontage_FR() {
		testModel(ModelToTest.Kombiseilgeraet2018_MontageDemontage, Language.FR, false);;
	}
	
	
	@Test
	private void testKombiseilgeraet2018_MontageDemontage_IT() {
		testModel(ModelToTest.Kombiseilgeraet2018_MontageDemontage, Language.IT, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_MontageDemontage_EN() {
		testModel(ModelToTest.Kombiseilgeraet2018_MontageDemontage, Language.EN, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_FaellenRuecken_DE() {
		testModel(ModelToTest.Kombiseilgeraet2018_FaellenRuecken, Language.DE);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_FaellenRuecken_FR() {
		testModel(ModelToTest.Kombiseilgeraet2018_FaellenRuecken, Language.FR);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_FaellenRuecken_IT() {
		testModel(ModelToTest.Kombiseilgeraet2018_FaellenRuecken, Language.IT);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_FaellenRuecken_EN() {
		testModel(ModelToTest.Kombiseilgeraet2018_FaellenRuecken, Language.EN);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_VortransportierenLagern_DE() {
		testModel(ModelToTest.Kombiseilgeraet2018_VortransportierenLagern, Language.DE, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_VortransportierenLagern_FR() {
		testModel(ModelToTest.Kombiseilgeraet2018_VortransportierenLagern, Language.FR, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_VortransportierenLagern_IT() {
		testModel(ModelToTest.Kombiseilgeraet2018_VortransportierenLagern, Language.IT, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_VortransportierenLagern_EN() {
		testModel(ModelToTest.Kombiseilgeraet2018_VortransportierenLagern, Language.EN, false);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_Gesamt_DE() {
		testModel(ModelToTest.Kombiseilgeraet2018_Gesamt, Language.DE);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_Gesamt_FR() {
		testModel(ModelToTest.Kombiseilgeraet2018_Gesamt, Language.FR);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_Gesamt_IT() {
		testModel(ModelToTest.Kombiseilgeraet2018_Gesamt, Language.IT);
	}
	
	
	@Test
	private void testKombiseilgeraet2018_Gesamt_EN() {
		testModel(ModelToTest.Kombiseilgeraet2018_Gesamt, Language.EN);
	}
	
	
	@Test
	private void testVorruecken2018_DE() {
		testModel(ModelToTest.Vorruecken2018, Language.DE);
	}
	
	
	@Test
	private void testVorruecken2018_FR() {
		testModel(ModelToTest.Vorruecken2018, Language.FR);
	}
	
	
	@Test
	private void testVorruecken2018_IT() {
		testModel(ModelToTest.Vorruecken2018, Language.IT);
	}
	
	
	@Test
	private void testVorruecken2018_EN() {
		testModel(ModelToTest.Vorruecken2018, Language.EN);
	}
	
	
	@Test
	private void testForwarder2018_Rundholz_DE() {
		testModel(ModelToTest.Forwarder2018_Rundholz, Language.DE);
	}
	
	
	@Test
	private void testForwarder2018_Rundholz_FR() {
		testModel(ModelToTest.Forwarder2018_Rundholz, Language.FR);
	}
	
	
	@Test
	private void testForwarder2018_Rundholz_IT() {
		testModel(ModelToTest.Forwarder2018_Rundholz, Language.IT);
	}
	
	
	@Test
	private void testForwarder2018_Rundholz_EN() {
		testModel(ModelToTest.Forwarder2018_Rundholz, Language.EN);
	}
	
	
	@Test
	private void testForwarder2018_Energieholz_DE() {
		testModel(ModelToTest.Forwarder2018_Energieholz, Language.DE);
	}
	
	
	@Test
	private void testForwarder2018_Energieholz_FR() {
		testModel(ModelToTest.Forwarder2018_Energieholz, Language.FR);
	}
	
	
	@Test
	private void testForwarder2018_Energieholz_IT() {
		testModel(ModelToTest.Forwarder2018_Energieholz, Language.IT);
	}
	
	
	@Test
	private void testForwarder2018_Energieholz_EN() {
		testModel(ModelToTest.Forwarder2018_Energieholz, Language.EN);
	}
	
	
	@Test
	private void testKonventionellerSeilkranGesamt_DE() {
		testModel(ModelToTest.KonventionellerSeilkranGesamt, Language.DE);
	}
	
	
	@Test
	private void testKonventionellerSeilkranGesamt_FR() {
		testModel(ModelToTest.KonventionellerSeilkranGesamt, Language.FR);
	}
	
	
	@Test
	private void testKonventionellerSeilkranGesamt_IT() {
		testModel(ModelToTest.KonventionellerSeilkranGesamt, Language.IT);
	}
	
	
	@Test
	private void testKonventionellerSeilkranGesamt_EN() {
		testModel(ModelToTest.KonventionellerSeilkranGesamt, Language.EN);
	}
	
	
	@Test
	private void testMobilseilkranGesamt_DE() {
		testModel(ModelToTest.MobilseilkranGesamt, Language.DE);
	}
	
	
	@Test
	private void testMobilseilkranGesamt_FR() {
		testModel(ModelToTest.MobilseilkranGesamt, Language.FR);
	}
	
	
	@Test
	private void testMobilseilkranGesamt_IT() {
		testModel(ModelToTest.MobilseilkranGesamt, Language.IT);
	}
	
	
	@Test
	private void testMobilseilkranGesamt_EN() {
		testModel(ModelToTest.MobilseilkranGesamt, Language.EN);
	}
	
	
	private void testModel(ModelToTest modelToTest, Language language) {
		testModel(modelToTest, language, true);
	}
	
	
	private void testModel(ModelToTest modelToTest, Language language, boolean testFirst) {
		startModel(modelToTest, language);
		ProdEinheit[] prodEinheiten = getProdEinheiten(modelToTest);

		Pattern pattern1 = getPattern(prodEinheiten[0], language);
		Pattern pattern2 = getPattern(prodEinheiten[1], language);

		//Einheiten der Produktivitäten prüfen
		if (testFirst == true) {
//			System.out.println(pattern1);
			frameCurrentModel.label("lblProduktivitaet").requireText(pattern1);
		}
//		System.out.println(pattern2);
		frameCurrentModel.label("lblProduktivitaet2").requireText(pattern2);
	}
	
	
	private void startModel(ModelToTest model, Language language) {
		// close old model and move mainwindow to front
		if (frameCurrentModel != null) {
			frameCurrentModel.close();
		}	
		frameHeProMo.moveToFront();
		
		// change language
		frameHeProMo.menuItem(language.name().toLowerCase()).click();
		findHeProMoMainWindow();
		
		// load model
		frameHeProMo.tree().node(getRowNr(model)).click();
		frameCurrentModel = findFrame(getFrameClass(model)).using(robot);
		
		if (model == ModelToTest.Forwarder2018_Energieholz) {
			frameCurrentModel.comboBox("ergebnisanzeige").selectItem(1);
		}
		else if (model == ModelToTest.Kombiseilgeraet2018_MontageDemontage) {
			frameCurrentModel.comboBox("ergebnisanzeige").selectItem(0);
		}
		else if (model == ModelToTest.Kombiseilgeraet2018_FaellenRuecken) {
			frameCurrentModel.comboBox("ergebnisanzeige").selectItem(1);
		}
		else if (model == ModelToTest.Kombiseilgeraet2018_VortransportierenLagern) {
			frameCurrentModel.comboBox("ergebnisanzeige").selectItem(2);
		}
	}
	
	
	private static int getRowNr(ModelToTest model) {
		switch (model) {
		case MotormanuellGesamt2014:
			return 1;
			
		case Radharvester2014:
			return 2;
			
		case Schlepper2014:
			return 3;
			
		case Hacker2018:
			return 4;
			
		case HackschnitzelTransport2018:
			return 5;

		case Kombiseilgeraet2018_MontageDemontage:
		case Kombiseilgeraet2018_FaellenRuecken:
		case Kombiseilgeraet2018_VortransportierenLagern:
		case Kombiseilgeraet2018_Gesamt:
			return 6;
			
		case Vorruecken2018:
			return 7;

		case Forwarder2018_Rundholz:
			return 8;
			
		case Forwarder2018_Energieholz:
			return 9;
			
		case KonventionellerSeilkranGesamt:
			return 10;
			
		case MobilseilkranGesamt:
			return 11;
			
		default:
			throw new RuntimeException(model.name());
		}
	}
	
	
	private static Class<? extends HeProMoWindow> getFrameClass(ModelToTest model) {
		switch (model) {
		case MotormanuellGesamt2014:
			return MotormanuellGesamt2014.class;
			
		case Radharvester2014:
			return Radharvester2014.class;
			
		case Schlepper2014:
			return Schlepper2014.class;
			
		case Hacker2018:
			return Hacker2018.class;
			
		case HackschnitzelTransport2018:
			return HackschnitzelTransport2018.class;
			
		case Kombiseilgeraet2018_MontageDemontage:
		case Kombiseilgeraet2018_FaellenRuecken:
		case Kombiseilgeraet2018_VortransportierenLagern:
		case Kombiseilgeraet2018_Gesamt:
			return Kombiseilgeraet2018.class;
			
		case Vorruecken2018:
			return Vorruecken2018.class;

		case Forwarder2018_Rundholz:
			return ForwarderRundholz2018.class;
			
		case Forwarder2018_Energieholz:
			return ForwarderEnergieholz2018.class;
			
		case KonventionellerSeilkranGesamt:
			return KonventionellerSeilkranGesamt.class;
			
		case MobilseilkranGesamt:
			return MobilseilkranGesamt.class;
			
		default:
			throw new RuntimeException(model.name());
		}
		
	}
	
	
	private static ProdEinheit[] getProdEinheiten(ModelToTest modelToTest) {
		CalculatorWithPublicProdEinheitGetters calculator = getCalculator(modelToTest);
		ProdEinheit prodEinheit1 = calculator.getProdEinheit1();
		ProdEinheit prodEinheit2 = calculator.getProdEinheit2();
		return new ProdEinheit[]{prodEinheit1, prodEinheit2};
	}
	
	
	private static CalculatorWithPublicProdEinheitGetters getCalculator(ModelToTest modelToTest) {
		switch (modelToTest) {
		case MotormanuellGesamt2014:
			return new CalculatorMotormanuellGesamt2014_Hack();
			
		case Radharvester2014:
			return new CalculatorRadharvester2014_Hack();
			
		case Schlepper2014:
			return new CalculatorSchlepper2014_Hack();
			
		case Hacker2018:
			return new CalculatorHacker2018_Hack();
			
		case HackschnitzelTransport2018:
			return new CalculatorHackschnitzelTransport2018_Hack();
			
		case Kombiseilgeraet2018_MontageDemontage:
			return new CalculatorKombiseilgeraet2018_MontageDemontage_Hack();
			
		case Kombiseilgeraet2018_FaellenRuecken:
			return new CalculatorKombiseilgeraet2018_FaellenRuecken_Hack();
			
		case Kombiseilgeraet2018_VortransportierenLagern:
			return new CalculatorKombiseilgeraet2018_VortransportierenLagern_Hack();
			
		case Kombiseilgeraet2018_Gesamt:
			return new CalculatorKombiseilgeraet2018_Gesamt_Hack();
			
		case Vorruecken2018:
			return new CalculatorVorruecken2018_Hack();
			
		case Forwarder2018_Rundholz:
			return new CalculatorForwarder2018_Rundholz_Hack();
			
		case Forwarder2018_Energieholz:
			return new CalculatorForwarder2018_Energieholz_Hack();
			
		case KonventionellerSeilkranGesamt:
			return new CalculatorKonventionellerSeilkranGesamt_Hack();
			
		case MobilseilkranGesamt:
			return new CalculatorMobilseilkranGesamt_Hack();
			
		default:
			throw new RuntimeException(modelToTest.name());
		}
	}
	
	
	private static interface CalculatorWithPublicProdEinheitGetters {
		public ProdEinheit getProdEinheit1();
		public ProdEinheit getProdEinheit2();
	}
	
	
	private static class CalculatorMotormanuellGesamt2014_Hack extends CalculatorMotormanuellGesamt2014 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorMotormanuellGesamt2014_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorRadharvester2014_Hack extends CalculatorRadharvester2014 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorRadharvester2014_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorSchlepper2014_Hack extends CalculatorSchlepper2014 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorSchlepper2014_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorHacker2018_Hack extends CalculatorHacker2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorHacker2018_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorHackschnitzelTransport2018_Hack extends CalculatorHackschnitzelTransport2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorHackschnitzelTransport2018_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorKombiseilgeraet2018_MontageDemontage_Hack extends CalculatorKombiseilgeraet2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorKombiseilgeraet2018_MontageDemontage_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
		@Override
		public ErgebnisAnzeige getErgebnisAnzeige() {
			return ErgebnisAnzeige.NurMontageDemontage;
		}
	}
	
	
	private static class CalculatorKombiseilgeraet2018_FaellenRuecken_Hack extends CalculatorKombiseilgeraet2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorKombiseilgeraet2018_FaellenRuecken_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
		@Override
		public ErgebnisAnzeige getErgebnisAnzeige() {
			return ErgebnisAnzeige.NurFaellenRuecken;
		}
	}
	
	
	private static class CalculatorKombiseilgeraet2018_VortransportierenLagern_Hack extends CalculatorKombiseilgeraet2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorKombiseilgeraet2018_VortransportierenLagern_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
		@Override
		public ErgebnisAnzeige getErgebnisAnzeige() {
			return ErgebnisAnzeige.NurVerziehen;
		}
	}
	
	
	private static class CalculatorKombiseilgeraet2018_Gesamt_Hack extends CalculatorKombiseilgeraet2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorKombiseilgeraet2018_Gesamt_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
		@Override
		public ErgebnisAnzeige getErgebnisAnzeige() {
			return ErgebnisAnzeige.AlleArbeitsschritte;
		}
	}
	
	
	private static class CalculatorVorruecken2018_Hack extends CalculatorVorruecken2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorVorruecken2018_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorForwarder2018_Rundholz_Hack extends CalculatorForwarder2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorForwarder2018_Rundholz_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
		@Override
		public ErgebnisAnzeige getErgebnisAnzeige() {
			return ErgebnisAnzeige.Rundholz;
		}
	}
	
	
	private static class CalculatorForwarder2018_Energieholz_Hack extends CalculatorForwarder2018 implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorForwarder2018_Energieholz_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
		@Override
		public ErgebnisAnzeige getErgebnisAnzeige() {
			return ErgebnisAnzeige.Energieholz;
		}
	}
	
	
	private static class CalculatorKonventionellerSeilkranGesamt_Hack extends CalculatorKonventionellerSeilkranGesamt implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorKonventionellerSeilkranGesamt_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static class CalculatorMobilseilkranGesamt_Hack extends CalculatorMobilseilkranGesamt implements CalculatorWithPublicProdEinheitGetters {
		public CalculatorMobilseilkranGesamt_Hack() {
			super(getEmptyHeProMoInputData());
		}
		@Override
		public ProdEinheit getProdEinheit1() {
			return super.getProdEinheit1();
		}
		@Override
		public ProdEinheit getProdEinheit2() {
			return super.getProdEinheit2();
		}
	}
	
	
	private static HeProMoInputData getEmptyHeProMoInputData() {
		final HeProMoInputData emptyInputData = new HeProMoInputData() {
			@Override
			public Faktoren getFaktoren() {
				return null;
			}
			@Override
			public Arbeitssystem getArbeitssystem() {
				return null;
			}
			@Override
			public String getArbeitsort() {
				return null;
			}
			@Override
			public Arbeitsobjekt getArbeitsobjekt() {
				return null;
			}
		};
		
		return emptyInputData;
	}
	
	
	private static Pattern getPattern(ProdEinheit prodEinheit, Language language) {
		final String prefix_iR = getPrefix_iR(language);
		final String prefix_oR = getPrefix_oR(language);
		
		switch (prodEinheit) {
		case EMPTY:
			return Pattern.compile("^$");
			
		case FM_OR_PRO_WPPH:
			throw new RuntimeException(); //wird im Moment nur intern verwendet

		case M3_PRO_PMH15:
			return Pattern.compile(".*PMH15.*");

		case M3_IR_PRO_PMH15_BEIM_SEILEN:
			return Pattern.compile(prefix_iR + "PMH15.*"); 

		case M3_OR_PRO_PMH15_BEIM_SEILEN:
			return Pattern.compile(prefix_oR + "PMH15.*"); 
			
		case M3_PRO_PSH15:
			return Pattern.compile(".*PSH15.*");

		case M3_IR_PRO_PMH15:
			return Pattern.compile(prefix_iR + "PMH15.*"); 

		case M3_OR_PRO_PMH15:
			return Pattern.compile(prefix_oR + "PMH15.*"); 
			
		case M3_IR_PRO_PSH15:
			return Pattern.compile(prefix_iR + "PSH15.*"); 
			
		case M3_OR_PRO_PSH15:
			return Pattern.compile(prefix_oR + "PSH15.*");
			
		case M3_IR_PRO_WSH:
			return Pattern.compile(prefix_iR + "WSH.*");
			
		case M3_OR_PRO_WSH:
			return Pattern.compile(prefix_oR + "WSH.*");

		case M3_IR_PRO_WPSH:
			return Pattern.compile(prefix_iR + "WPSH.*");

		case M3_OR_PRO_WPSH:
			return Pattern.compile(prefix_oR + "WPSH.*");

		case SRM_PRO_PMH15:
			return Pattern.compile(".*Srm.*PMH15.*");
			
		default:
			throw new RuntimeException("unhandled case: " + prodEinheit.name());
		}
	}
	
	
	private static String getPrefix_iR(Language language) {
		switch(language) {
		case DE:
			return ".*i\\.R\\..*";
			
		case FR:
			return ".*n\\.éc\\..*";
			
		case IT:
			return "((.*c\\.cort\\..*)|(.*c\\.C\\..*))";
			
		case EN:
			return ".*o\\.b\\..*";
			
		default:
			throw new RuntimeException("unhandled case: " + language.name());
		}
	}
	
	
	private static String getPrefix_oR(Language language) {
		switch(language) {
		case DE:
			return ".*o\\.R\\..*";
			
		case FR:
			return ".*[^(n\\.)]éc\\..*";
			
		case IT:
			return "((.*s\\.cort\\..*)|(.*s\\.C\\..*))";
			
		case EN:
			return ".*u\\.b\\..*";
			
		default:
			throw new RuntimeException("unhandled case: " + language.name());
		}
	}
	
	
	private static enum Language {
		DE, FR, IT, EN;
	}
}
