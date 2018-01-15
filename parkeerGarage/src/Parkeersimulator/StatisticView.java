package Parkeersimulator;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class StatisticView extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public StatisticView() {
		
		/*
		 * Weergave voor de statistiek van de 'normale' auto's etc.
		 */
		
		setTitle("Statistiek Parkeergarage - Groningen");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatisticView.class.getResource("/afbeeldingen/Parking-Logo.jpg")));
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		
		JLabel lblStatistiekParkeergarage = new JLabel("Statistiek Parkeergarage");
		lblStatistiekParkeergarage.setForeground(Color.WHITE);
		lblStatistiekParkeergarage.setBounds(10, 0, 454, 61);
		lblStatistiekParkeergarage.setFont(new Font("Tahoma", Font.BOLD, 36));
		getContentPane().add(lblStatistiekParkeergarage);

		
		setSize(860,560);
		setVisible(true);
		
	}

}
