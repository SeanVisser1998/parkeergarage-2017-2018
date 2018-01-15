package Parkeersimulator;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Label;
import javax.swing.ImageIcon;


public class Main extends JFrame{
	
	
	private final JLabel lblCityParking = new JLabel("City Parking - Groningen");
	
	/*
	 * Fix voor Eclipse:
	 * Run > Run Configurations > Java Application > Main > Parkeersimulator.Main
	 */
	
	
	public static void main(String args[]) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					
						Main frame = new Main();
						frame.setVisible(true);

					
			}
			
		});
		
		
		
		/*
		 * Opmerking: dit voert ALLEEN de bovenste uit, ik ben momenteel met een fix hiervoor bezig :)
		 */	
		
		
		/*
		 * Sean Visser
		 * Simulator openen voor gereserveerde plekken
		 */
		//SimulatorReserved simRes = new SimulatorReserved();
		//simRes.runRes();
		
		/*
		 * Sean Visser
		 * Simulator openen voor NIET gereserveerde plekken
		 */
		//Simulator sim = new Simulator();
		//sim.run();
		
	
	}
	
	public Main() {
		setResizable(false);
		setTitle("City parking - Groningen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(740,420);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 370, 391);
		getContentPane().add(panel);
		panel.add(lblCityParking);
		lblCityParking.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCityParking.setForeground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/afbeeldingen/image_preview.jpg")));
		panel.add(lblNewLabel);
		
		JLabel lblDfdf = new JLabel("Gemaakt door:");
		lblDfdf.setForeground(Color.WHITE);
		panel.add(lblDfdf);
		
		Label label = new Label("Daan Aalders, Pyter van der Leij, Rutger Rozendal, Bram ... & Sean Visser");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 9));
		panel.add(label);
		
		JButton btnNewButton = new JButton("Reserveringen statistiek");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StatisticReservedView STRV = new StatisticReservedView();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(380, 316, 334, 64);
		getContentPane().add(btnNewButton);
		
		JButton btnOpenParkeergarage = new JButton("Open parkeergarage");
		btnOpenParkeergarage.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				
				Simulator sim = new Simulator();
			
			}
		});
		btnOpenParkeergarage.setForeground(Color.WHITE);
		btnOpenParkeergarage.setBackground(SystemColor.textHighlight);
		btnOpenParkeergarage.setBounds(380, 91, 334, 64);
		getContentPane().add(btnOpenParkeergarage);
		
		JButton btnParkeergarageStatistiek = new JButton("Parkeergarage statistiek");
		btnParkeergarageStatistiek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StatisticView STV = new StatisticView();
				
			}
		});
		btnParkeergarageStatistiek.setForeground(Color.WHITE);
		btnParkeergarageStatistiek.setBackground(SystemColor.textHighlight);
		btnParkeergarageStatistiek.setBounds(380, 166, 334, 64);
		getContentPane().add(btnParkeergarageStatistiek);
		
		JButton btnOpenReserveringen = new JButton("Open reserveringen");
		btnOpenReserveringen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimulatorReserved simRes = new SimulatorReserved();
				
			}
		});
		btnOpenReserveringen.setForeground(Color.WHITE);
		btnOpenReserveringen.setBackground(SystemColor.textHighlight);
		btnOpenReserveringen.setBounds(380, 241, 334, 64);
		getContentPane().add(btnOpenReserveringen);
		
		JLabel lblGroningen = new JLabel("Groningen");
		lblGroningen.setForeground(SystemColor.textHighlight);
		lblGroningen.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblGroningen.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroningen.setBounds(380, 11, 344, 69);
		getContentPane().add(lblGroningen);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/afbeeldingen/Parking-Logo.jpg")));
	}
}
