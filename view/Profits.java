package view;

import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.Model;

public class Profits extends AbstractView{
	
	JLabel omzet;
	JLabel normaalOmzet;
	JLabel reserveerOmzet;

	public Profits(Model model) {
		super(model);
		
		omzet = new JLabel();
		model.omzet = omzet;
		
		normaalOmzet = new JLabel();
		model.normaalOmzet = normaalOmzet;
		
		reserveerOmzet = new JLabel();
		model.reserveerOmzet = reserveerOmzet;
		
    	omzet.setText("Totale omzet: €" + model.getTotaalOmzet());;
    	normaalOmzet.setText("Omzet normale auto's: €" + model.getNormaalOmzet());;
    	reserveerOmzet.setText("Omzet gereserveerde auto's: €" + model.getReserveerOmzet());
    	
    	setLayout(new GridLayout(3,0));
    	
    	add(normaalOmzet);
    	add(reserveerOmzet);
    	add(omzet);
		
	}

}
