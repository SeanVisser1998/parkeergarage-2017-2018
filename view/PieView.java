package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;

import logic.ElecCar;
import logic.Model;
import logic.ParkingPassCar;
import logic.ReservedCar;

@SuppressWarnings("serial")
public class PieView extends AbstractView{

	public PieView(Model model) {
		super(model);
		setSize(200,200);
	}
	
	public void paintComponent(Graphics g) {
		int normaal = getModel().getCountCar();
		int pass = getModel().getCountPass();
		int reserveer = getModel().getCountRes();
		int electric = getModel().getCountElec();
		
		int normaalArc = berekenHoek(normaal);
		int passArc = berekenHoek(pass);
		int reserveerArc = berekenHoek(reserveer);
		int electricArc = berekenHoek(electric);
				
		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		g.setColor(Color.WHITE);
		g.fillArc(10, 10, 180, 180, 0, 360);
		g.setColor(Color.RED);
		
		//begint op 0 graden
		g.fillArc(10, 10, 180, 180, 0, normaalArc);
		g.setColor(ParkingPassCar.returnColor());
		
		//begint op de som van:
		//0 graden
		//de hoek van de normale autos
		g.fillArc(10, 10, 180, 180, normaalArc, passArc);
		g.setColor(ReservedCar.returnColor());
		
		//begint op de som van:
		//0 graden
		//de hoek van de normale autos 
		//de hoek van de pass autos
		g.fillArc(10, 10, 180, 180, passArc+normaalArc, reserveerArc);
		g.setColor(ElecCar.returnColor());
		
		//begint op de som van:
		//0 graden
		//hoek van de pass autos
		//hoek van de normale autos
		//hoek van de gereserveerde autos
		g.fillArc(10, 10, 180, 180, passArc+normaalArc+reserveerArc, electricArc);
		
		
	}
	
	private int berekenHoek(int aantal) {
		//360 graden
		//540 plekken
		//540 / 360 = 1.5
		//aantal / 1.5 = hoek
		
		return (int)(aantal/1.5);
	}

}
