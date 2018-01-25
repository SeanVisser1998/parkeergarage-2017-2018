package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSlider;

import logic.Model;

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener{

	JButton startButton;
	JButton stopButton;
	JButton resetButton;
	JButton closeButton;
	
	JSlider timeScale;
	
	
	public Controller(Model model) {
		super(model);
		
		setSize(1280,50);
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		startButton.setBackground(Color.WHITE);
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(this);
		stopButton.setBackground(Color.white);
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetButton.setBackground(Color.white);
	
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		closeButton.setBackground(Color.white);
		
		timeScale = new JSlider(JSlider.HORIZONTAL, 25, 200, 100);
		
		timeScale.addChangeListener(e -> sliderChanged() );
		timeScale.setMajorTickSpacing(10);
		timeScale.setMinorTickSpacing(5);
		
		this.setLayout(new FlowLayout());
		add(startButton);
		add(stopButton);
		add(resetButton);
		add(closeButton);
		add(timeScale);
		
		
		//setVisible(true);
	
	}

	public void sliderChanged()
	{
		model.sliderChanged(timeScale.getValue());
	}
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == startButton) {
			
			if(model.run) {
				
			}else {
				model.start();
			}
		
		}
	
		else if (e.getSource() == stopButton) {
			model.stop();
		}
			
		else if(e.getSource() == closeButton) {
			model.close();
		}
			
		else if(e.getSource() == resetButton) {
			model.reset();
		}
		
			
		
	}
}