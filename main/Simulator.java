package main;

import javax.swing.JFrame;

import controller.Controller;
import controller.TopController;
import logic.Model;
import view.AbstractView;
import view.CarParkView;
import view.Legenda;
import view.Namen;
import view.PieView;
import view.Profits;
import view.SideBar;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * @author Bram Rustenhoven, Daan Aalders, Pyter vd Leij, Rutger Rozendal & Sean Visser
 * @klas ITV1A
 * @schooljaar 2017-2018
 * @version 1.0.0 
 */

public class Simulator{

	private JFrame screen;
	private Model model;
	private AbstractView carParkView;
	private AbstractView pieView;
	private AbstractView sideBar;
	private AbstractView profits;
	private AbstractView legenda;
	private AbstractView namen;
	private TopController topController;
	private Controller controller;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	
	public Simulator() {
		screen = new JFrame("City parking - Groningen");
		screen.setIconImage(Toolkit.getDefaultToolkit().getImage(Simulator.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")));
		
		model = new Model(3, 6, 30);
		
		controller = new Controller(model);
		controller.setSize(1324, 50);
		controller.setLocation(0, 561);
		
		topController = new TopController(model);
		topController.setSize(215, 43);
		topController.setLocation(1099, 12);
		
		carParkView = new CarParkView(model);
		carParkView.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		pieView = new PieView(model);
		pieView.setBorder(new LineBorder(new Color(0, 0, 0)));
		pieView.setLocation(10, 80);
		
		namen = new Namen(model);
		namen.setBorder(new LineBorder(new Color(0, 0, 0)));
		namen.setBounds(1099, 428, 215, 125);
		
		profits = new Profits(model);
		profits.setBorder(new LineBorder(new Color(0, 0, 0)));
		profits.setBounds(1099, 211, 215, 79);
		
		legenda = new Legenda(model);
		legenda.setBorder(new LineBorder(new Color(0, 0, 0)));
		legenda.setBounds(1099, 320, 215, 79);
		
		sideBar = new SideBar(model);
		sideBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		sideBar.setBounds(1099, 68, 215, 110);

		
		screen.setSize(1330, 640);
		screen.setResizable(false);
		screen.getContentPane().setLayout(null);
		
		carParkView.setLayout(null);
		
		screen.getContentPane().add(carParkView);
		screen.getContentPane().add(pieView);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(sideBar);
		screen.getContentPane().add(topController);
		screen.getContentPane().add(profits);
		screen.getContentPane().add(namen);
		screen.getContentPane().add(legenda);
		
		carParkView.setBounds(239, 53, 850, 500);
		
		JLabel lblAutos = new JLabel("Auto's");
		lblAutos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutos.setBounds(1099, 53, 46, 14);
		screen.getContentPane().add(lblAutos);
		
		JLabel lblNewLabel = new JLabel("Auto's");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 66, 62, 14);
		screen.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CarParkView");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(239, 36, 100, 14);
		screen.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Omzet");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(1099, 196, 46, 14);
		screen.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Legenda");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(1099, 301, 93, 18);
		screen.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Namen");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(1099, 410, 62, 14);
		screen.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Hanze Hogeschool");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_5.setForeground(Color.ORANGE);
		lblNewLabel_5.setBounds(10, 12, 219, 38);
		screen.getContentPane().add(lblNewLabel_5);
		
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);

	}
}
