package logic;

import java.awt.Color;
import java.util.Random;

public class ElecCar extends Car{
	
	private static final Color COLOR=Color.decode("#00DF3C");
	private static final Color TEXTCOLOR = Color.decode("#009f2b");
	
	public ElecCar() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(true);
	}
	
	public Color getColor() {
		return COLOR;
	}
	
	public static Color returnColor() {
		return COLOR;
	}
	
	public static Color returnTextColor() {
		return TEXTCOLOR;
	}

}
