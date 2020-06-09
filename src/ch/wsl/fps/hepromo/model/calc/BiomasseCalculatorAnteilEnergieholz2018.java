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
package ch.wsl.fps.hepromo.model.calc;

import ch.wsl.fps.hepromo.util.HashMap3D;
import static ch.wsl.fps.hepromo.model.calc.BiomasseCalculatorAnteilEnergieholz2018.Zopfklasse.*;

import ch.wsl.fps.hepromo.model.ModelStrings;

/**
 * 
 * @author Stefan Holm
 *
 */
public class BiomasseCalculatorAnteilEnergieholz2018 {	
	
	private static final HashMap3D<Baumtyp, Zopfklasse, Integer, Double> lookupTable;
	
	static {
		lookupTable = new HashMap3D<BiomasseCalculatorAnteilEnergieholz2018.Baumtyp, Zopfklasse, Integer, Double>();

		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf0,  15, 0.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf7,  15, 0.056);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf10, 15, 0.235);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf15, 15, 0.611);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf20, 15, 1.00);

		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf0,  25, 0.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf7,  25, 0.019);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf10, 25, 0.038);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf15, 25, 0.148);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf20, 25, 0.383);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf25, 25, 0.720);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf30, 25, 0.967);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf35, 25, 1.00);

		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf0,  35, 0.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf7,  35, 0.009);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf10, 35, 0.015);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf15, 35, 0.061);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf20, 35, 0.137);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf25, 35, 0.314);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf30, 35, 0.613);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf35, 35, 0.854);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf40, 35, 0.979);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf45, 35, 1.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf50, 35, 1.00);

		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf0,  45, 0.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf7,  45, 0.004);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf10, 45, 0.007);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf15, 45, 0.032);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf20, 45, 0.072);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf25, 45, 0.161);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf30, 45, 0.332);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf35, 45, 0.564);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf40, 45, 0.803);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf45, 45, 0.935);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf50, 45, 0.985);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf55, 45, 1.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf60, 45, 1.00);

		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf0,  55, 0.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf7,  55, 0.002);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf10, 55, 0.004);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf15, 55, 0.019);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf20, 55, 0.041);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf25, 55, 0.086);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf30, 55, 0.173);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf35, 55, 0.299);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf40, 55, 0.502);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf45, 55, 0.742);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf50, 55, 0.894);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf55, 55, 0.932);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf60, 55, 0.989);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf65, 55, 1.00);
		lookupTable.add(Baumtyp.FichteRundholzBis100ProzentDerBaumhoehe, Zopf70, 55, 1.00);


		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf0,  15, 0.00);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf7,  15, 0.056);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf10, 15, 0.247);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf15, 15, 0.572);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf20, 15, 1.00);

		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf0,  25, 0.00);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf7,  25, 0.011);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf10, 25, 0.045);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf15, 25, 0.127);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf20, 25, 0.399);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf25, 25, 0.694);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf30, 25, 0.913);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf35, 25, 1.00);

		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf0,  35, 0.00);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf7,  35, 0.006);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf10, 35, 0.014);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf15, 35, 0.053);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf20, 35, 0.162);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf25, 35, 0.284);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf30, 35, 0.519);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf35, 35, 0.780);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf40, 35, 0.928);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf45, 35, 0.987);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf50, 35, 1.00);

		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf0,  45, 0.00);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf7,  45, 0.005);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf10, 45, 0.007);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf15, 45, 0.027);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf20, 45, 0.075);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf25, 45, 0.143);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf30, 45, 0.270);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf35, 45, 0.447);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf40, 45, 0.645);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf45, 45, 0.821);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf50, 45, 0.945);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf55, 45, 0.982);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf60, 45, 1.00);

		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf0,  55, 0.00);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf7,  55, 0.003);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf10, 55, 0.004);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf15, 55, 0.015);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf20, 55, 0.038);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf25, 55, 0.077);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf30, 55, 0.140);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf35, 55, 0.229);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf40, 55, 0.363);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf45, 55, 0.505);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf50, 55, 0.673);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf55, 55, 0.806);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf60, 55, 0.915);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf65, 55, 0.983);
		lookupTable.add(Baumtyp.BucheRundholzBis100ProzentDerBaumhoehe, Zopf70, 55, 1.00);
	}
	
	
	public static double calculateAnteilEnergieholz(Baumtyp baumtyp, Zopfklasse zopf_cm, int bhd_cm) {
		double result = -1;
		
		if (isInterpolationNecessary(bhd_cm)) {
			int[] pairOfClosestBhd = getPairOfClosestBhd_cm(bhd_cm);
			double anteli1 = getAnteilEnergieholz(baumtyp, zopf_cm, pairOfClosestBhd[0]);
			double anteil2 = getAnteilEnergieholz(baumtyp, zopf_cm, pairOfClosestBhd[1]);
			result = getInterpolation(bhd_cm, pairOfClosestBhd[0], pairOfClosestBhd[1], anteli1, anteil2);
		} 
		else {
			result = getAnteilEnergieholz(baumtyp, zopf_cm, bhd_cm);
		}	
		
		return result;
	}
	
	
	private static boolean isInterpolationNecessary(int bhd_cm) {
		if (bhd_cm < 15) {
			throw new IllegalArgumentException("BHD muss mindestens 15 cm sein!"); //$NON-NLS-1$
		}
		if (bhd_cm > 55) {
			throw new IllegalArgumentException("BHD darf maximal 55 cm sein!"); //$NON-NLS-1$
		}
		
		boolean isDurch5teilbarOhneRestUndHintersteZiffer5 = bhd_cm%5 == 0 && bhd_cm/5%2 == 1;
		return isDurch5teilbarOhneRestUndHintersteZiffer5 == false;
	}
	
	
	private static int[] getPairOfClosestBhd_cm(int bhd_cm) {
		int[] result = null;
		
		if (bhd_cm%5 == 0 && bhd_cm/5%2 == 1) {
			//Zahl durch 5 teilbar ohne Rest, hintere Ziffer 5
			result = new int[]{bhd_cm, bhd_cm};
		}
		else if (bhd_cm%5 == 0 && bhd_cm/5%2 == 0) {
			//Zahl durch 5 teilbar ohne Rest, hintere Ziffer 0
			result = new int[]{bhd_cm - 5, bhd_cm + 5};
		}
		else {
			int obererWert = (int) (Math.ceil((0f + bhd_cm) / 5f) * 5);
			int untererWert = (int) (Math.floor((0f + bhd_cm) / 5f) * 5);

			if (untererWert/5%2 == 0) {
				//hinterste Ziffer ist 0
				untererWert -= 5;
			}
			
			if (obererWert/5%2 == 0) {
				//hinterste Ziffer ist 0
				obererWert += 5;
			}

			result = new int[] {untererWert, obererWert};
		}
		
		return result;
	}
	
	
	/**
	 * zopf_cm und bhd_cm müssen in der Tabelle vorhanden sein!
	 */
	private static double getAnteilEnergieholz(Baumtyp baumtyp, Zopfklasse zopf_cm, int bhd_cm) {
		Double result = lookupTable.get(baumtyp, zopf_cm, bhd_cm);
		
		if (result == null) {
			throw new IllegalArgumentException(baumtyp + "/" + zopf_cm + "/" + bhd_cm); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		return result; 
	}
	
	
	/**
	 * 
	 * @param bhd BHD, für welchen der interpolierte Wert gesucht ist
	 * @param bhd1 Unterer BHD, für den ein Wert bekannt ist
	 * @param bhd2 Oberer BHD, für den ein Wert bekannt ist
	 * @param value1 Wert für unteren BHD
	 * @param value2 Wert für oberen BHD
	 */
	private static double getInterpolation(double bhd, double bhd1, double bhd2, double value1, double value2) {
		
		double x = (bhd - bhd1) / (bhd2 - bhd1);
		
		double result = value1 + x * (value2 - value1);
		
		return result; //FIXME: testen!!
	}

	
	public enum Baumtyp {
		FichteRundholzBis100ProzentDerBaumhoehe,
		BucheRundholzBis100ProzentDerBaumhoehe;
		
		@Override
		public String toString() {
			switch(this) {
			case FichteRundholzBis100ProzentDerBaumhoehe:
				return ModelStrings.getString("BiomasseCalculatorAnteilEnergieholz2018.enumNadelholz"); //$NON-NLS-1$
				
			case BucheRundholzBis100ProzentDerBaumhoehe:
				return ModelStrings.getString("BiomasseCalculatorAnteilEnergieholz2018.enumLaubholz"); //$NON-NLS-1$
				
			default:
				throw new RuntimeException(this.name());
			}
		}
	}
	
	
	public enum Zopfklasse {
		Zopf0,
		Zopf7,
		Zopf10,
		Zopf15,
		Zopf20,
		Zopf25,
		Zopf30,
		Zopf35,
		Zopf40,
		Zopf45,
		Zopf50,
		Zopf55,
		Zopf60,
		Zopf65,
		Zopf70;
		
		public static Zopfklasse getNaechstKleinere(int zopf_cm) {
			if (zopf_cm >= 70) {
				return Zopf70;
			}
			else if (zopf_cm >= 65) {
				return Zopf65;
			}
			else if (zopf_cm >= 60) {
				return Zopf60;
			}
			else if (zopf_cm >= 55) {
				return Zopf55;
			}
			else if (zopf_cm >= 50) {
				return Zopf50;
			}
			else if (zopf_cm >= 45) {
				return Zopf45;
			}
			else if (zopf_cm >= 40) {
				return Zopf40;
			}
			else if (zopf_cm >= 35) {
				return Zopf35;
			}
			else if (zopf_cm >= 30) {
				return Zopf30;
			}
			else if (zopf_cm >= 25) {
				return Zopf25;
			}
			else if (zopf_cm >= 20) {
				return Zopf20;
			}
			else if (zopf_cm >= 15) {
				return Zopf15;
			}
			else if (zopf_cm >= 10) {
				return Zopf10;
			}
			else if (zopf_cm >= 7) {
				return Zopf7;
			}
			else {
				return Zopf0;
			}
		}
		
		public int getValue() {
			switch(this) {
			case Zopf0:
				return 0;
				
			case Zopf7:
				return 7;
				
			case Zopf10:
				return 10;
				
			case Zopf15:
				return 15;
				
			case Zopf20:
				return 20;
				
			case Zopf25:
				return 25;
				
			case Zopf30:
				return 30;
				
			case Zopf35:
				return 35;
				
			case Zopf40:
				return 40;
				
			case Zopf45:
				return 45;
				
			case Zopf50:
				return 50;
				
			case Zopf55:
				return 55;
				
			case Zopf60:
				return 60;
				
			case Zopf65:
				return 65;
				
			case Zopf70:
				return 70;
				
			default:
				throw new RuntimeException(this.name());
			}
		}
		
		@Override
		public String toString() {
			return this.name().replace("Zopf", "").concat(" cm"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

}
