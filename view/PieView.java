package view;

import java.awt.Dimension;
import java.awt.Image;

import logic.Model;

public class PieView extends AbstractView{
	
	private Dimension size;
    private Image pieViewImage; 

	public PieView(Model model) {
		super(model);
		size = new Dimension(0,0);
	}

}
