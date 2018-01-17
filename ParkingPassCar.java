package Parkeersimulator;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	
	/*
	 * Class voor auto's met een parkeerpas
	 */
	private static final Color COLOR=Color.blue;
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
        this.setHasToPayFee(false); //toegevoegd
    }
    
    public Color getColor(){
    	return COLOR;
    }
}
