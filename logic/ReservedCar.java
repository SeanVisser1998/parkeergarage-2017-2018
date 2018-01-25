package logic;

import java.awt.Color;
import java.util.Random;

public class ReservedCar extends Car{
	
	//Kleur bepalen
		private static final Color COLOR=Color.decode("#EAEA00");
				
		//Constructor
		public ReservedCar(){
			Random random = new Random();
			int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
			this.setMinutesLeft(stayMinutes);
			this.setHasToPay(true);
		};
		
		//Return color functie
		public Color getColor() {
			return COLOR;
		}
		
		public static Color returnColor() {
			return COLOR;
		}
}
