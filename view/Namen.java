package view;

import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.Model;

public class Namen extends AbstractView {

	JLabel Bram;
	JLabel Daan;
	JLabel Pyter;
	JLabel Rutger;
	JLabel Sean;
	
	JLabel Klas;
	
	public Namen(Model model) {
		super(model);
		
		Bram = new JLabel();
		Bram.setText("Bram Rustenhoven");
		
		Daan = new JLabel();
		Daan.setText("Daan Aalders");
		
		Pyter = new JLabel();
		Pyter.setText("Pyter vd Leij");
		
		Rutger = new JLabel();
		Rutger.setText("Rutger Rozendal");
		
		Sean = new JLabel();
		Sean.setText("Sean Visser");
		
		Klas = new JLabel();
		Klas.setText("ITV1A - 2017/2018");
		
		setLayout(new GridLayout(6,0));
		
		add(Bram);
		add(Daan);
		add(Pyter);
		add(Rutger);
		add(Sean);
		add(Klas);
		
	}
	
	

}
