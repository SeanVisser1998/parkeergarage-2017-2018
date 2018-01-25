package view;

import java.awt.GridLayout;

import javax.swing.JLabel;

import logic.Model;

@SuppressWarnings("serial")
public class Profits extends AbstractView{
	
	JLabel omzet;
	JLabel normaalOmzet;
	JLabel reserveerOmzet;
	JLabel elecOmzet;

	public Profits(Model model) {
		super(model);
		
		omzet = new JLabel();
		model.omzet = omzet;
		
		normaalOmzet = new JLabel();
		model.normaalOmzet = normaalOmzet;
		
		reserveerOmzet = new JLabel();
		model.reserveerOmzet = reserveerOmzet;
		
		elecOmzet = new JLabel();
		model.elecOmzet = elecOmzet;
		
    	omzet.setText("Totale omzet: €" + model.getTotaalOmzet());;
    	normaalOmzet.setText("Omzet normale auto's: €" + model.getNormaalOmzet());;
    	reserveerOmzet.setText("Omzet gereserveerde auto's: €" + model.getReserveerOmzet());
    	elecOmzet.setText("Omzet electrische auto's: €" + model.getElecOmzet());
    	
    	setLayout(new GridLayout(4,0));
    	
    	add(normaalOmzet);
    	add(reserveerOmzet);
    	add(elecOmzet);
    	add(omzet);
		
	}

}
