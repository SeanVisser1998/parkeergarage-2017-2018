package view;

import javax.swing.*;
import java.awt.*;

import logic.Model;

@SuppressWarnings("serial")
public class SideBar extends AbstractView {
	JLabel openSpots;
	JLabel timeText;
	
	String spots;
		
	public SideBar(Model model) {
		super(model);
		spots = String.valueOf(model.getNumberOfOpenSpots());
		
		
	
		
		//new JLabel(); -> NODIG ANDERS NULLPOINTEREXCEPTION
		openSpots = new JLabel();
		model.openSpots = openSpots;
	
		
		//new JLabel(); -> NODIG ANDERS NULLPOINTEREXCEPTION
		timeText = new JLabel();
		model.timeText = timeText;
		
		setUpSideBar();
	}
	
	private void setUpSideBar() {
		setLayout(new GridLayout(2,0));
		
		//Voeg JLabels toe aan sidebar
		add(openSpots);
		add(timeText);
	}
}
