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
	
	public Controller(Model model) {
		super(model);
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		resetButton = new JButton("Reset");
		setUpPanel();
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		resetButton.addActionListener(this);
		
		add(startButton);
		add(stopButton);
		add(resetButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			
			if(Model.run) {
				
			}else {
				model.start();
			}
		
		}
		
		else if (e.getSource() == stopButton) {
			model.stop();
		}
		else if (e.getSource() == resetButton) {
			model.reset(); //Deze mag jij maken, Rutger xD
		}
		
	}
}