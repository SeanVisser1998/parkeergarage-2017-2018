package view;

import javax.swing.*;
import java.awt.*;

import logic.Model;

@SuppressWarnings("serial")
public class SideBar extends AbstractView {
	JLabel openSpots;
	String spots;
		
	public SideBar(Model model) {
		super(model);
		spots = String.valueOf(model.getNumberOfOpenSpots());
		
		openSpots = new JLabel();
		model.openSpots = openSpots;
		
		
		setUpSideBar();
	}
	
	private void setUpSideBar() {
		setLayout(new GridLayout());
		add(openSpots);
	}
}
