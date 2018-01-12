package Parkeersimulator;

import java.awt.Color;
import java.util.Random;

/*
 * Sean Visser
 * 11-01-2018
 */
public class ReservedCar extends Car{
	private static final Color COLOR=Color.CYAN;

	public ReservedCar() {
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasToPayFee(true);
		
	}

	
	public Color getColor() {
		return COLOR;
	}

}
