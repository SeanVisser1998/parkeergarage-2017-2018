package controller;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Color;

import logic.Model;

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener{

	JButton startButton;
	JButton stopButton;
	JButton resetButton;
	JButton closeButton;
	JButton plusOne;
//	JButton minusOne;
	JSlider timeScale;
	JLabel timeText;
	
	public Controller(Model model) {
		super(model);
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		resetButton = new JButton("Reset");
		closeButton = new JButton("Close");
		plusOne = new JButton ("+1");
		timeScale = new JSlider(JSlider.HORIZONTAL,-50, 200, 100);
		timeText = new JLabel();
		timeText.setForeground(Color.white);
		
		model.timeText = timeText;

		setUpPanel();
		
	}
	
	private void setUpPanel(){
		setLayout(new FlowLayout());
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		resetButton.addActionListener(this);
		closeButton.addActionListener(this);
		plusOne.addActionListener(this);
		timeScale.addChangeListener(e -> sliderChanged() );
		timeScale.setMajorTickSpacing(10);
		timeScale.setMinorTickSpacing(5);
		add(startButton);
		add(stopButton);
		add(resetButton);
		add(closeButton);
		add(plusOne);
		add(timeScale);
		add(timeText);
		
	}
	
	
	public void sliderChanged()
	{
		model.sliderChanged(timeScale.getValue());
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
			model.reset(); 
		}
		else if (e.getSource() == closeButton)	{
			model.close(); //sluit het scherm xx Daan
		}
		else if (e.getSource() == plusOne) {
			model.plusOne(); //1 stap verder in de simulatie
			//bug(?): er gebeuren meerdere dingen in 1 stap vd simulatie ipv 1 ding tegelijk
		}
	}
}