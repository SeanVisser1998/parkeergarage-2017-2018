package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.Model;

public class Legenda extends AbstractView {
	
	JLabel rood;
	JLabel blauw;
	JLabel geel;

	public Legenda(Model model) {
		super(model);
		
		rood = new JLabel();
		rood.setText("Normale Auto");
		rood.setFont(new Font("Tahoma", Font.BOLD, 12));
		rood.setForeground(Color.RED);
		
		blauw = new JLabel();
		blauw.setText("Pass Houder");
		blauw.setFont(new Font("Tahoma", Font.BOLD, 12));
		blauw.setForeground(Color.BLUE);
		
		geel = new JLabel();
		geel.setText("Gereserveerde Auto");
		geel.setFont(new Font("Tahoma", Font.BOLD, 12));
		geel.setForeground(Color.YELLOW);
		
		setLayout(new GridLayout(3,0));
		setBackground(Color.LIGHT_GRAY);
		
		add(rood);
		add(blauw);
		add(geel);
		
	}

}
