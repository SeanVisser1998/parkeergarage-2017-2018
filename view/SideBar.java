package view;

import javax.swing.*;
import java.awt.*;

import logic.Model;

@SuppressWarnings("serial")
public class SideBar extends AbstractView {
	JLabel openSpots;
	JLabel timeText;
	JLabel totalOpbrengst;
	
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
		
		totalOpbrengst = new JLabel();
		model.totalOpbrengst = totalOpbrengst;
		
		openSpots.setText("Open spots:");
		timeText.setText("Datum");
        totalOpbrengst.setText("Totale opbrengst: 0");

		setUpSideBar();
	}
	
	private void setUpSideBar() {
		setLayout(new GridLayout(6,0));
		
		//Voeg JLabels toe aan sidebar
		add(openSpots);
		add(timeText);
		add(totalOpbrengst);
	}
}
