package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;

import logic.Model;

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
				
		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.WHITE);
		g.fillArc(0, 0, 180, 180, 360, 540);
		g.setColor(Color.RED);
		g.fillArc(0, 0, 180, 180, 360, normaal);
		g.setColor(Color.BLUE);
		g.fillArc(0, 0, 180, 180, 360, pass);
		g.setColor(Color.YELLOW);
		g.fillArc(0, 0, 180, 180, 360, reserveer);
		
		
	}

}
