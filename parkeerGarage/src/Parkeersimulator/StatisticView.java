package Parkeersimulator;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

public class StatisticView extends JFrame{
	
	public StatisticView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatisticView.class.getResource("/afbeeldingen/Parking-Logo.jpg")));
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		
		JLabel lblStatistiekParkeergarage = new JLabel("Statistiek Parkeergarage");
		lblStatistiekParkeergarage.setForeground(Color.WHITE);
		lblStatistiekParkeergarage.setBounds(10, 0, 454, 61);
		lblStatistiekParkeergarage.setFont(new Font("Tahoma", Font.BOLD, 36));
		getContentPane().add(lblStatistiekParkeergarage);
		setTitle("Statistiek Parkeergarage - Groningen");
		
		setSize(860,560);
		setVisible(true);
		
	}

}
