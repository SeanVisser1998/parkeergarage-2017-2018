package view;

import javax.swing.JPanel;

import model.CarPark;

@SuppressWarnings("serial")
public class View extends JPanel{
	
	protected CarPark model;
	
	public View(CarPark model) {
		this.model = model;
		model.addView(this);
		setSize(200,200);
		setVisible(true);
	}
	
	public void setModel(CarPark model) {
		this.model = model;
	}
	
	public CarPark getModel() {
		return model;
	}
	
	public void updateView() {
		repaint();
	}
	

}
