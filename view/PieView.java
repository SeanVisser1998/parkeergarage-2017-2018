package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import logic.Model;

public class PieView extends AbstractView{
	
	public PieView(Model model) {
		super(model);
		setSize(200,200);
	}
	
	public void paintComponent(Graphics g) {
		int aantal=getModel().getNumberOfOpenSpots();
				
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.BLUE);
		g.fillArc(10, 10, 180, 180, 360, aantal);
		
		
	}

}
