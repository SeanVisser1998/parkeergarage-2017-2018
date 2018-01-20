package logic;

import java.awt.Color;

public class PassPlace extends Car{

    public static final Color COLOR = Color.MAGENTA;

    
    public PassPlace() {
        int stayMinutes = (int) Double.POSITIVE_INFINITY;
        this.setMinutesLeft(stayMinutes);
        this.setHasToPay(false);
    }
    
    public java.awt.Color getColor(){
        return COLOR;
    }
}


