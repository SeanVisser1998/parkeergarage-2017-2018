package Parkeersimulator;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class MainView extends JFrame{
	
	public MainView() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 370, 381);
		getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Reserveringen statistiek");
		btnNewButton.setBounds(380, 306, 334, 64);
		getContentPane().add(btnNewButton);
		
		JButton btnOpenParkeergarage = new JButton("Open parkeergarage");
		btnOpenParkeergarage.setBounds(380, 81, 334, 64);
		getContentPane().add(btnOpenParkeergarage);
		
		JButton btnParkeergarageStatistiek = new JButton("Parkeergarage statistiek");
		btnParkeergarageStatistiek.setBounds(380, 156, 334, 64);
		getContentPane().add(btnParkeergarageStatistiek);
		
		JButton btnOpenReserveringen = new JButton("Open reserveringen");
		btnOpenReserveringen.setBounds(380, 231, 334, 64);
		getContentPane().add(btnOpenReserveringen);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));
	}
}
