package controller;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.Model;

@SuppressWarnings("serial")
public class TopController extends AbstractController{
	
	JLabel datum;

	public TopController(Model model) {
		super(model);


		datum = new JLabel();
		model.datum = datum;
		model.setTimeText();
		datum.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		
		setLayout(new GridLayout(2,0));
		add(datum);
	}

}
