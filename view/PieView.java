package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;

import logic.ElecCar;
import logic.Model;
import logic.ParkingPassCar;
import logic.ReservedCar;

@SuppressWarnings("serial")
public class PieView extends AbstractView{

	public PieView(Model model) {
		super(model);
		setSize(200,200);
	}
	
	public void paintComponent(Graphics g) {
		int normaal = getModel().getCountCar();
		int pass = getModel().getCountPass();
		int reserveer = getModel().getCountRes();
		int electric = getModel().getCountElec();
				
		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.WHITE);
		g.fillArc(10, 10, 180, 180, 360, 540);
		g.setColor(Color.RED);
		g.fillArc(10, 10, 180, 180, 360, normaal);
		g.setColor(ParkingPassCar.returnColor());
		g.fillArc(10, 10, 180, 180, 360, pass);
		g.setColor(ReservedCar.returnColor());
		g.fillArc(10, 10, 180, 180, 360, reserveer);
		g.setColor(ElecCar.returnColor());
		g.fillArc(10, 10, 180, 180, 360, electric);
		
		
	}

}
