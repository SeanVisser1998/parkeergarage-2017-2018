package controller;

import logic.Model;
import javax.swing.JPanel;

public abstract class AbstractController extends JPanel{
	
	protected Model model;
	
	public AbstractController(Model model) {
		this.model = model;
	}
}
