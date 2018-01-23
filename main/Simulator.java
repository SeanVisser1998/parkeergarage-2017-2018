package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.SideBar;


@SuppressWarnings("serial")
public class Simulator extends JFrame{

	private Model model;
	private AbstractView carParkView;
	private Controller controller;
	private SideBar sidebar;
	
	public Simulator() {
		super("City Park - Groningen");
		model = new Model(3, 6, 30);
		controller = new Controller(model);
		sidebar = new SideBar(model);
		carParkView = new CarParkView(model);
		setUpFrame();
	}
	
	public void setUpFrame() {
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		getContentPane().add(sidebar, BorderLayout.EAST);
		setSize(1280, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addComponents() {
	
		
	}
}
