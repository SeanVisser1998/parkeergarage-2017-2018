package Parkeersimulator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;

public class StatisticReservedView extends JFrame{

	private static final long serialVersionUID = 1L;

	public StatisticReservedView() {
		
		/*
		 * Weergave voor de statistiek van de gereserveerde auto's etc.
		 */
		
		setTitle("Statistiek Reserveringen - Groningen");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatisticReservedView.class.getResource("/afbeeldingen/Parking-Logo.jpg")));
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		
		JLabel lblStatistiekParkeergarage = new JLabel("Statistiek Reserveringen");
		lblStatistiekParkeergarage.setForeground(Color.WHITE);
		lblStatistiekParkeergarage.setBounds(10, 0, 454, 61);
		lblStatistiekParkeergarage.setFont(new Font("Tahoma", Font.BOLD, 36));
		getContentPane().add(lblStatistiekParkeergarage);

		setSize(860,560);
		setVisible(true);
	}

}
