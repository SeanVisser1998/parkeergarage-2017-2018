package logic;

import java.awt.Color;

public class PassPlace extends Car{
	
public static final Color COLOR = Color.decode("#88a2ff");      //The color of the car

    
    public PassPlace() {
        int stayMinutes = Integer.MAX_VALUE;
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
