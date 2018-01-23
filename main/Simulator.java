package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.PieView;
import view.SideBar;
import java.awt.SystemColor;


@SuppressWarnings("serial")
public class Simulator{

	private JFrame screen;
	
	private Model model;
	private AbstractView carParkView;
	private Controller controller;
	private AbstractView pieView;
	private AbstractView sidebar;
	
	public Simulator() {
		
		screen = new JFrame("City Parking - Groningen");
	
		model = new Model(3, 6, 30, 60);
		
		controller = new Controller(model);
		controller.setBackground(SystemColor.menu);
		controller.setLocation(0, 561);
		
		
		sidebar = new SideBar(model);
		
		
		carParkView = new CarParkView(model);
		carParkView.setBounds(239, 53, 800, 500);
		
		pieView = new PieView(model);
		pieView.setLocation(0, 53);
		
		screen.setSize(1280, 640);
		screen.setResizable(false);
		screen.getContentPane().setLayout(null);
		
		carParkView.setLayout(null);
		
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(pieView);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(sidebar);
				
		carParkView.setBounds(239, 53, 800, 500);
		controller.setBounds(0, 561, 1274, 50);
		sidebar.setBounds(1049, 53, 215, 500);
		
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	
	}
	
	/*public void setUpFrame() {
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		getContentPane().add(sidebar, BorderLayout.EAST);
		getContentPane().add(pieView, BorderLayout.WEST);
		setSize(1280, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} */
	
	
	public void addComponents() {
	
		
	}
}
