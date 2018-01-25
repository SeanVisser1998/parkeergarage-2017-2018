package view;

import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.Model;

@SuppressWarnings("serial")
public class SideBar extends AbstractView{
	
	JLabel normalCar;
	JLabel resCar;
	JLabel passCar;
	JLabel totalCar;
	
	public SideBar(Model model) {
		super(model);
	
		normalCar = new JLabel();
		model.normalCar = normalCar;
		normalCar.setText("Aantal normale auto's: " + model.getCountCar());
		
		resCar = new JLabel();
		model.resCar = resCar;
		resCar.setText("Aantal gereserveerde auto's: " + model.getCountRes());
		
		passCar = new JLabel();
		model.passCar = passCar;
		passCar.setText("Aantal pas auto's: " + model.getCountPass());
		
		totalCar = new JLabel();
		model.totalCar = totalCar;
		totalCar.setText("Totaal aantal auto's: " + model.getCountTotalCar());
		
		setupSideBar();
	}
	
	private void setupSideBar() {
		setLayout(new GridLayout(4,0));
	
		add(normalCar);
		add(resCar);
		add(passCar);
		add(totalCar);
	}



}
