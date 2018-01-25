package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;

import logic.Model;

public class BarView extends AbstractView{
	
	public BarView(Model model) {
		super(model);

	}
	
	public Dimension getPrefferedSize() {
		return new Dimension(200,262);
	}
	
	private int getSize(int totaalAuto) {
		int totaal = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
		double size = 1.0 * (double) totaalAuto;
		return (int) size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int sizeNormaal = getSize(model.getCountCar());
		int sizePass = getSize(model.getCountPass());
		int sizeRes = getSize(model.getCountRes());
		int sizeElec = getSize(model.getCountElec());
		
		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		
		//Normaal
		if(sizeNormaal < 240) {
			g.setColor(Color.RED);
			g.fillRect(25, 256-sizeNormaal, 35, sizeNormaal);
			g.setColor(Color.DARK_GRAY);
			g.drawString("" + model.getCountCar(), 35, 253-sizeNormaal);
		}
		else {
			g.setColor(Color.RED);
			g.fillRect(25, 16, 35, 240);
			g.setColor(Color.DARK_GRAY);
			g.drawString("" + model.getCountCar(), 35, 13);
		}
		
		
		//Pas
		g.setColor(Color.BLUE);
		g.fillRect(60, 256-sizePass, 35, sizePass);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + model.getCountPass(), 70, 253-sizePass);
		
		//Reserveer
		g.setColor(Color.YELLOW);
		g.fillRect(95, 256-sizeRes, 35, sizeRes);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + model.getCountRes(), 105,253-sizeRes);
		
		//Electrisch
		g.setColor(Color.CYAN);
		g.fillRect(130, 256-sizeElec, 35,sizeElec);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + model.getCountElec(), 140, 253-sizeElec);
		
	}
	
	

}
