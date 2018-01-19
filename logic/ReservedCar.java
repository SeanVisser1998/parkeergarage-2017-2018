package logic;
import java.awt.Color;
import java.util.Random;

public class ReservedCar extends Car {

	//Kleur bepalen
	private static final Color COLOR=Color.yellow;
	
	//gereserveerde locatie
	private Location reservedLocation;
	
	//Constructor
	public ReservedCar(){
		Random random = new Random();
		int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
		this.setMinutesLeft(stayMinutes);
		this.setHasToPay(true);
//		this.reservedLocation = location;
	};
	
	//Return color functie
	public Color getColor() {
		return COLOR;
	}
	
	//return gereserveerde locatie van deze auto
	public Location getReservedSpot() {
		return this.reservedLocation;
	}
}
