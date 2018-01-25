package logic;

import java.util.Random;
import java.awt.*;

public class ParkingPassCar extends Car {
	private static final Color COLOR=Color.decode("#0085FF");
	
    public ParkingPassCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public Color getColor(){
    	return COLOR;
    }
    
    public static Color returnColor() {
    	return COLOR;
    }
}
