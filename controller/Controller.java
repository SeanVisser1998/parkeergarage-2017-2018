package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;
import model.CarPark;

@SuppressWarnings("serial")
public class Controller extends JPanel implements ActionListener{
	
	private CarPark carPark;
	private JButton start;
	private JButton stop;

	public Controller(CarPark carPark) {
		
		this.carPark = carPark;
		
		start=new JButton("Start");
		start.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		
		setSize(450,50);
		
		this.setLayout(null);
		add(start);
		add(stop);
		start.setBounds(229, 10, 70, 30);
		stop.setBounds(319, 10, 70, 30);
		
		setVisible(true);
	}
	
	private void startPressed() {
		Main.running = true;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		 if(actionEvent.getSource() == start){
			 this.startPressed();
		 }
	            
	}
		
	}


