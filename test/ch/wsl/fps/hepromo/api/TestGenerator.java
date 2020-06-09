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
package ch.wsl.fps.hepromo.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Erstellt aufgrund der Klassen im package hepromo.api die benötigten Testfiles, 
 * d.h. für jedes Modell einerseits die Testklasse, andererseits das CSV. Das CSV 
 * wird nur vorbereitet und muss manuell ergänzt werden.
 * 
 * @author Stefan Holm
 *
 */
public class TestGenerator {

	public static void main(String[] args) throws IOException {
		List<Path> allFiles = getAllFiles();
		
		for (Path path : allFiles) {
			TestFileData tfd = new TestFileData(path);
			System.out.println("Preparing tests for " + tfd.className + ".");
			printJavaFile(tfd);
			printCsvFile(tfd);
		}
	}
	
	
	private static DynamicWriter getNewDynamicWriterJavaFile(TestFileData tfd) throws IOException {
//		return new DirectSystemOutWriter();
//		return new BufferedSystemOutWriter();
		return new TestFileWriter(tfd.getTestFilePath());
//		return new SilentWriter();
	}
	
	
	private static DynamicWriter getNewDynamicWriterCsvFile(TestFileData tfd) throws IOException {
//		return new DirectSystemOutWriter();
//		return new BufferedSystemOutWriter();
		return new TestFileWriter(tfd.getTestCsvPath());
//		return new SilentWriter();
	}

	

	
	private static List<Path> getAllFiles() throws IOException {
		final List<Path> allFiles = new ArrayList<>();
		
		Path dir = Paths.get("src/ch/wsl/fps/hepromo/api");
		
		for (Path p : Files.newDirectoryStream(dir)) {
			String fileName = p.getFileName().toString();
			
			if (fileName.startsWith("Simple") && fileName.endsWith(".java")) {
				allFiles.add(p);
			}
		}
		
		return allFiles;
	}

	
	private static List<Double> getDefaultValues(Path file) throws IOException {
		List<Double> allDefaultValues = new ArrayList<>();
		for (String line : Files.readAllLines(file, Charset.defaultCharset())) {
			if (line.matches("\\s*public static final double DEFAULT_.*")) {
				line = line.substring(line.indexOf("=") + 1);
				line = line.substring(0, line.length() - 1);
				line = line.trim();
//				System.out.println("'" + line + "'");
				allDefaultValues.add(Double.valueOf(line));
			}
		}
		
		allDefaultValues.add(540d);
		allDefaultValues.add(60d);
		allDefaultValues.add(0d);
		allDefaultValues.add(0d);
		allDefaultValues.add(1d);
		allDefaultValues.add(0d);
		allDefaultValues.add(0d);
		
		return allDefaultValues;
	}
	
	private static List<String> getSetterShortnames(Path file) throws IOException {
		List<String> allSettersShortname = new ArrayList<>();
		for (String line : Files.readAllLines(file, Charset.defaultCharset())) {
			if (line.matches("\\s*public void set.*")) {
				line = line.replace("public void set", "");
				line = line.trim();
				line = line.substring(0, line.indexOf("("));
//				System.out.println(line);
				allSettersShortname.add(line);
			}
		}
		
		allSettersShortname.add("TaeglicheArbeitszeit_min");
		allSettersShortname.add("WegzeitenUndPausen_min");
		allSettersShortname.add("KostenUmsetzen_Betrag");
		allSettersShortname.add("KostenWeitereAufwaende_Betrag");
		allSettersShortname.add("Korrekturfaktor_Faktor");
		allSettersShortname.add("RisikoVerwaltungGewinn_Prz");
		allSettersShortname.add("Mehrwertsteuer_Prz");
		
		return allSettersShortname;
	}
	
	private static List<String> getGetterShortnames(Path file) throws IOException {
		List<String> allSettersShortname = new ArrayList<>();
		for (String line : Files.readAllLines(file, Charset.defaultCharset())) {
			if (line.matches("\\s*public double get.*")) {
				line = line.replace("public double get", "");
				line = line.trim();
				line = line.substring(0, line.indexOf("("));
//				System.out.println(line);
				allSettersShortname.add(line);
			}
		}
		
		// Insert KostenTotal_total just before the first productivity method.
		int posToInsertKostenTotal = allSettersShortname.size();
		while (allSettersShortname.get(posToInsertKostenTotal-1).startsWith("Produktivitaet")) {
			posToInsertKostenTotal--;
		}
		allSettersShortname.add(posToInsertKostenTotal, "KostenTotal_total");
		
		return allSettersShortname;
	}
	
	
	private static void printJavaFile(TestFileData tfd) throws IOException {
		DynamicWriter dw = getNewDynamicWriterJavaFile(tfd);
		
		dw.writeLine("package ch.wsl.fps.hepromo.api;");
		dw.writeLine("");
		dw.writeLine("import org.testng.annotations.Test;");
		dw.writeLine("");
		dw.writeLine("/**");
		dw.writeLine(" * ");
		dw.writeLine(" * @author Stefan Holm");
		dw.writeLine(" *");
		dw.writeLine(" */");
		dw.writeLine("public class " + tfd.className + "Test extends AbstractSimpleModelTest {");
		dw.writeLine("");
		printAllTestMethods(dw, tfd);
		dw.writeLine("");
		dw.writeLine("	@Override");
		dw.writeLine("	protected double[] getErgebnis(double[] input) {");
		dw.writeLine("		//Inputvariablen setzen");
		dw.writeLine("		" + tfd.className + " model = new " + tfd.className + "();");
		dw.writeLine("		int currentLineIndex = -1;");
		for (String setterShortname : tfd.allSetterShortnames) {
			dw.writeLine("		if (input[++currentLineIndex] != -1) model.set" + setterShortname + "(" + getFillingTabs(setterShortname) + "input[currentLineIndex]	);");
		}
		dw.writeLine("		");
		dw.writeLine("		");
		dw.writeLine("		//Outputvariablen auslesen");
		dw.writeLine("		double[] result = new double[" + tfd.allGetterShortnames.size() + "];");
		dw.writeLine("		currentLineIndex = -1;");
		for (String getterShortname : tfd.allGetterShortnames) {
			dw.writeLine("		result[++currentLineIndex] = model.get" + getterShortname + "();");
		}
		dw.writeLine("		");
		dw.writeLine("		return result;");
		dw.writeLine("	}");
		dw.writeLine("");
		dw.writeLine("");
		dw.writeLine("	@Override");
		dw.writeLine("	protected int getFirstOutputField() {");
		dw.writeLine("		return " + tfd.firstOutputField + ";");
		dw.writeLine("	}");
		dw.writeLine("");
		dw.writeLine("	@Override");
		dw.writeLine("	protected int getLastOutputField() {");
		dw.writeLine("		return " + tfd.lastOutputField + ";");
		dw.writeLine("	}");
		dw.writeLine("");
		dw.writeLine("}");
		
		dw.flushAndClose();
	}
	
	
	private static void printAllTestMethods(DynamicWriter dw, TestFileData tfd) {
		for (int i=0; i<tfd.allGetterShortnames.size(); i++) {
			String getterShortname = tfd.allGetterShortnames.get(i);
			printTestMethod(dw, getterShortname, i);
		}
	}
	
	
	private static void printTestMethod(DynamicWriter dw, String methodShortname, int index) {
		dw.writeLine("	@SuppressWarnings(\"unused\")");
		dw.writeLine("	@Test(dataProvider=\"csvData\")");
		dw.writeLine("	public void check" + methodShortname + "(String testcaseName, double[] ergebnis, double[] expectedValues) {");
		dw.writeLine("		assertEqualsDynamicDelta(ergebnis[" + index + "], expectedValues[" + index + "]);");
		dw.writeLine("	}");
		dw.writeLine("");
	}
	
	
	private static String getFillingTabs(String str) {
		final int SPACES_PER_TAB = 4;
		int fillingSpaces = 32 - str.length() - 1;
		fillingSpaces = Math.max(fillingSpaces, 1);
		int fillingTabs = (int) Math.round(Math.ceil( (0d + fillingSpaces) / SPACES_PER_TAB));
		
		String result = "";
		for (int i=0; i<fillingTabs; i++) {
			result += "\t";
		}
		return result;
	}
	
	
	private static void printCsvFile(TestFileData tfd) throws IOException {
		DynamicWriter dw = getNewDynamicWriterCsvFile(tfd);
		
		// print header (best guess; probably needs to be adapted manually in file)
		int nrOfInputVariables = tfd.allSetterShortnames.size();
		int guessedNrOfFaktoren = 3;
		int guessedNrOfAsTitles = 6;
		int guessedNrOfAoTitles = Math.max(1, nrOfInputVariables - guessedNrOfFaktoren - guessedNrOfAsTitles);
		
		dw.write("#Arbeitsobjekt");
		for (int i=0; i<guessedNrOfAoTitles; i++) {
			dw.write(";");
		}
		
		dw.write("Arbeitssystem");
		for (int i=0; i<guessedNrOfAsTitles; i++) {
			dw.write(";");
		}
		
		dw.write("Faktoren");
		for (int i=0; i<guessedNrOfFaktoren; i++) {
			dw.write(";");
		}
		
		dw.write("Ergebnis");
		for (int i=0; i<tfd.allGetterShortnames.size(); i++) {
			dw.write(";");
		}
		dw.writeLine("");
		
		
		// print input var titles
		dw.write("#");
		for (String setterShortname : tfd.allSetterShortnames) {
			dw.write(convertMethodShortnameToCsvTitle(setterShortname) + ";");
		}

		// print output var titles
		for (String getterShortname : tfd.allGetterShortnames) {
			dw.write(convertMethodShortnameToCsvTitle(getterShortname) + ";");
		}
		dw.writeLine("");
		

		// two base testcases and one addtional testcase for each setter
		for (int i=0; i<tfd.allSetterShortnames.size()+tfd.allGetterShortnames.size(); i++) {
			dw.write("-1;");
		}
		dw.writeLine("");
		for (int iLine=0; iLine<tfd.allSetterShortnames.size()+1; iLine++) {

			for (int i=0; i<tfd.allSetterShortnames.size(); i++) {
				String defaultValue = String.valueOf(tfd.allDefaultValues.get(i));
				if (defaultValue.endsWith(".0")) {
					defaultValue = defaultValue.replace(".0", "");
				}
				dw.write(defaultValue + ";");
			}
			for (int i=0; i<tfd.allGetterShortnames.size(); i++) {
				dw.write("-1;");
			}
			dw.writeLine("");
		}
		
		dw.flushAndClose();
	}
	
	
	private static String convertMethodShortnameToCsvTitle(String shortname) {
		// convert camels to spaces
		for (int i=1; i<shortname.indexOf("_"); i++) {
			if (Character.isUpperCase(shortname.charAt(i))) {
				shortname = shortname.substring(0, i) + " " + shortname.substring(i);
				i++;
			}
		}
		
		// replace _ with [
		int iUnderscore = shortname.indexOf("_");
		shortname = shortname.substring(0, iUnderscore) + " [" + shortname.substring(iUnderscore + 1);
		
		// close unit bracket ]
		shortname = shortname + "]";
		
		// further enhancements
		shortname = shortname.replace("[pro", "[pro ");
		shortname = shortname.replace("[pro M3", "[pro m3");
		shortname = shortname.replace("[pro H", "[pro h");
		shortname = shortname.replace("[Prz]", "[%]");
		shortname = shortname.replace("m3oR", "m3 oR");
		shortname = shortname.replace("m3iR", "m3 iR");
		
		return shortname;
	}

	
	private static class TestFileData {
		private final String classToTest;
		private final String className;

		private final List<Double> allDefaultValues;
		private final List<String> allGetterShortnames;
		private final List<String> allSetterShortnames;
		
		private final int firstOutputField;
		private final int lastOutputField;
		
		public TestFileData(Path classToTest) throws IOException {
			this.classToTest = classToTest.toString();
			className = classToTest.getFileName().toString().replace(".java", "");

			allDefaultValues = getDefaultValues(classToTest);
			allGetterShortnames = getGetterShortnames(classToTest);
			allSetterShortnames = getSetterShortnames(classToTest);
			
			int nrOfGetters = allGetterShortnames.size();
			int nrOfSetters = allSetterShortnames.size();
			
			firstOutputField = nrOfSetters;
			lastOutputField = nrOfSetters + nrOfGetters - 1;
		}
		
		public String getTestFilePath() {
			String testFilePath = classToTest.replace("src", "test").replace(".java", "Test.java");
			return testFilePath;
		}
		
		public String getTestCsvPath() {
			String testCsvPath = "testcases/api/" + className + ".csv";
			return testCsvPath;
		}
	}
	
	
	private static interface DynamicWriter {
		void write(String text);
		void writeLine(String text);
		void flushAndClose();
	}
	
	@SuppressWarnings("unused")
	private static class DirectSystemOutWriter implements DynamicWriter {
		@Override
		public void write(String text) {
			System.out.print(text);
		}

		@Override
		public void writeLine(String text) {
			System.out.println(text);
		}

		@Override
		public void flushAndClose() {
			// nothing to do here
		}
	}
	
	@SuppressWarnings("unused")
	private static class BufferedSystemOutWriter implements DynamicWriter {
		private final StringBuilder sb = new StringBuilder();

		@Override
		public void write(String text) {
			sb.append(text);
		}

		@Override
		public void writeLine(String text) {
			sb.append(text);
			sb.append("\n");
		}

		@Override
		public void flushAndClose() {
			System.out.println(sb.toString());
		}
	}
	
	private static boolean overwriteAll = false;
	
	
	@SuppressWarnings("unused")
	private static class TestFileWriter implements DynamicWriter {
		private final PrintWriter writer;
		
		public TestFileWriter(String fileName) throws IOException {
			File file = new File(fileName);
			if (file.exists() && overwriteAll == false) {
				System.out.println("File \"" + fileName + "\" already exists. Overwrite (y/n/yall)?");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String answer = in.readLine();
				
				if (answer.equalsIgnoreCase("yall")) {
					overwriteAll = true;
				}
				if (answer.equalsIgnoreCase("yall") == false && answer.equalsIgnoreCase("y") == false) {
					writer = null; 
					return;
				}
			}
			writer = new PrintWriter(fileName);
		}
		
		@Override
		public void write(String text) {
			if (writer != null) {
				writer.write(text);
			}
		}
		
		@Override
		public void writeLine(String text) {
			if (writer != null) {
				writer.write(text);
				writer.append("\r\n");
			}
		}

		@Override
		public void flushAndClose() {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static class SilentWriter implements DynamicWriter {

		@Override
		public void write(String text) {
			// no-op
		}

		@Override
		public void writeLine(String text) {
			// no-op
		}

		@Override
		public void flushAndClose() {
			// no-op
		}
	}
}
