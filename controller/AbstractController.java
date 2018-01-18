package controller;

import logic.Model;

import java.awt.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel{

	protected Model model;
	
	public AbstractController(Model model) {
		this.model = model;
		this.setBackground(Color.DARK_GRAY);
	}
}
