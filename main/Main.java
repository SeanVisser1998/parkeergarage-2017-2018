package main;

import javax.swing.JFrame;

import controller.Controller;
import model.CarPark;
import view.View;
import view.CarParkView;


public class Main extends JFrame{
	
	private CarPark carParkModel;
	private JFrame screen;
	private Controller controller;
	
	 public static int simulationSpeed = 1000;
	 public static boolean running;
	
	private View carParkView;
	
	
	public Main() {
		
		carParkModel = new CarPark(3, 6, 30);
		
		carParkView = new CarParkView(carParkModel);
		
		controller = new Controller(carParkModel);
		
		screen = new JFrame("City Parking Groningen");
		screen.setSize(1200, 750);
		screen.setResizable(false);
		screen.setLayout(null);
	
		screen.getContentPane().add(carParkView);
		carParkView.setBounds(260,30,680,330);
		
		screen.getContentPane().add(controller);
		controller.setBounds(0, 210, 450, 50);	
		
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		
		carParkView.updateView();
		
		running = true;
		
		while(true) {
			if(running) {
				carParkModel.tick();
			}
			try {
				Thread.sleep(simulationSpeed);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}
