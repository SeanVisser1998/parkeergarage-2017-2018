package logic;

import java.awt.Color;

public class PassPlace extends Car{
	public static final Color COLOR = Color.MAGENTA;      //The color of the car

    
    public PassPlace() {
        double stayMinutes = Double.POSITIVE_INFINITY;
        this.setMinutesLeft((int) stayMinutes);
        this.setHasToPay(false);
    }
    
    /**
     * Gets the color of the car
     * @Return The color of the car/
     */
    public Color getColor(){
        return COLOR;
    }
}