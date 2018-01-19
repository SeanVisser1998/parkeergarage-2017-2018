package controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import logic.Model;

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener{

	JButton startButton;
	JButton stopButton;
	JButton resetButton;
	JButton closeButton;
	JButton plusOne;
//	JButton minusOne;
	
	public Controller(Model model) {
		super(model);
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		resetButton = new JButton("Reset");
		closeButton = new JButton("Close");
		plusOne = new JButton ("+1");
//		minusOne = new JButton ("-1");
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		resetButton.addActionListener(this);
		closeButton.addActionListener(this);
		plusOne.addActionListener(this);
//		minusOne.addActionListener(this);
		
		add(startButton);
		add(stopButton);
		add(resetButton);
		add(closeButton);
		add(plusOne);
//		add(minusOne);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			
			if(model.run) {
				
			}else {
				model.start();
			}
		
		}
		
		//dit moet beter kunnen dan allemaal else if statements, toch? ^^" -bram
		else if (e.getSource() == stopButton) {
			model.stop();
		}
		else if (e.getSource() == resetButton) {
			model.reset(); //Deze mag jij maken, Rutger xD
		}
		else if (e.getSource() == closeButton)	{
			model.close(); //sluit het scherm xx Daan
		}
		else if (e.getSource() == plusOne) {
			model.plusOne(); //1 stap verder in de simulatie
			//bug(?): er gebeuren meerdere dingen in 1 stap vd simulatie ipv 1 ding tegelijk
		}
//		else if (e.getSource() == minusOne) {
//			model.minusOne(); //TODO 1 stap terug in de simulatie
//		}
	}
}