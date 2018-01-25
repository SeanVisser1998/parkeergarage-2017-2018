package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.ElecCar;
import logic.Model;
import logic.ParkingPassCar;
import logic.ReservedCar;

@SuppressWarnings("serial")
public class Legenda extends AbstractView {
	
	JLabel normalCar;
	JLabel passCar;
	JLabel elecCar;
	JLabel reservedCar;

	public Legenda(Model model) {
		super(model);
		
		normalCar = new JLabel();
		normalCar.setText("Normale Auto");
		normalCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		normalCar.setForeground(Color.RED);
		
		passCar = new JLabel();
		passCar.setText("Pass Houder");
		passCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		passCar.setForeground(ParkingPassCar.returnColor());
		
		reservedCar = new JLabel();
		reservedCar.setText("Gereserveerde Auto");
		reservedCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		reservedCar.setForeground(ReservedCar.returnTextColor());
		
		elecCar = new JLabel();
		elecCar.setText("Electrische Auto");
		elecCar.setFont(new Font("Tahoma", Font.BOLD, 12));
		elecCar.setForeground(ElecCar.returnTextColor());
		
		setLayout(new GridLayout(4,0));
		setBackground(Color.LIGHT_GRAY);
		
		add(normalCar);
		add(passCar);
		add(elecCar);
		add(reservedCar);
		
	}

}
