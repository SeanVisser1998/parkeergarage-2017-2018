package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import controller.Controller;
import logic.Model;
import view.AbstractView;
import view.CarParkView;


@SuppressWarnings("serial")
public class Simulator extends JFrame{

	private Model model;
	private AbstractView carParkView;
	private Controller controller;
	
	public Simulator() {
		super("City Park - Groningenr");
		model = new Model(3, 6, 30);
		controller = new Controller(model);
		carParkView = new CarParkView(model);
		setUpFrame();

	}
	
	public void setUpFrame() {
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		getContentPane().add(carParkView, BorderLayout.CENTER);
		getContentPane().add(controller, BorderLayout.SOUTH);
		setSize(1280, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void addComponents() {
	
		
	}
}
