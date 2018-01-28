/*
 * LineGraph Class
 * Wordt niet gebruikt en is puur voor overzicht

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import logic.AdHocCar;
import logic.ElecCar;
import logic.Model;
import logic.ParkingPassCar;
import logic.ReservedCar;

//

@SuppressWarnings("serial")
public class LineGraph extends AbstractView{
	
	LinkedList<Integer> dataNormal;
	LinkedList<Integer> dataPass;
	LinkedList<Integer> dataReserved;
	LinkedList<Integer> dataElectric;
	LinkedList<Integer> dataAll;
	
	int counter = 0;
	int x_counter = 0;
	
	int i;
	int y1;
	int y2;
	
	public LineGraph(Model model) {
		super(model);

	}
	
	public Dimension getPrefferedSize() {
		return new Dimension(200,262);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 200, 262);
		
		updateLinkedLists();
		
		g.setColor(Color.BLUE);
		if(model.returnOldMinute() != model.returnMinute()) {

		drawGraph(g);
		}		
	}
	//----------------------------------------
	public void drawGraph(Graphics g){
		counter ++;

		drawLines(g);

		drawLineGraph(g, dataAll, Color.CYAN);
		drawLineGraph(g, dataNormal, AdHocCar.returnColor());
		drawLineGraph(g, dataPass, ParkingPassCar.returnColor());
		drawLineGraph(g, dataReserved, ReservedCar.returnTextColor());
		drawLineGraph(g, dataElectric, ElecCar.returnTextColor());
		
		
		String filename = model.returnDayString()+".png";

		if(model.returnHour() == 23 && model.returnMinute() == 59) {
			try {
				BufferedImage image = createImage(this);
		
				//kijk of het bestand al bestaat, en zo ja, verwijder het bestand
				//path naar het bestand
			    Path path = FileSystems.getDefault().getPath(null, filename);
			    
			    //if file exists = delete
				Files.deleteIfExists(path);
				
				//nieuwe bestand opslaan
				save(image, filename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Er is iets mis gegaan! - "+e);
			}
			clearLinkedLists();
		}
	}
	
	private void drawLines(Graphics g) {
		//horizontal lines
		for(int i = 262; i > 0; i-=10) {
			int x1 = 0;
			int x2 = 200;
			
			int y = i;
			
			g.setColor(Color.decode("#4f4f4f"));
//			g.drawLine(0, 54, 200, 54);
			g.drawLine(x1, y, x2, y);
			
			g.setColor(Color.GRAY);
			g.drawLine(x1, y, 5, y);
			
		}
		
		for(float i = 0; i < 200; i+=8.33333) {
			
			int y1 = 262;
			int y2 = 0;
			
			int x = (int)i;
			
			g.setColor(Color.decode("#4f4f4f"));
			g.drawLine(x, y1, x, y2);
			
			g.setColor(Color.GRAY);
			g.drawLine(x, y1, x, 257);
		}
	}
	
	private void drawLineGraph(Graphics g, LinkedList<Integer> list, Color color) {
		g.setColor(color);
		if(list.size() > 1) {
			for(i = 1; i < list.size(); i++) {
				int stepSize = model.returnLineGraphStepSize();
				
				//hack groter = meer naar links
				int hack = 75;
				
				int x1 = ((i-1)	* 10) / (hack / stepSize);
				int x2 = ((i)	* 10) / (hack / stepSize);
				
				y1 = list.get(i-1)	  ;
				y2 = list.get(i	 ) + 1;
				
				g.drawLine(x1, 262+(-y1/2), x2, 262+(-y2/2));
			}
		}
	}
	
	private void updateLinkedLists() {
		 dataNormal 	= 	model.getLineDataNormal();
		 dataPass 		= 	model.getLineDataPass();
		 dataReserved 	= 	model.getLineDataReserved();
		 dataElectric 	=	model.getLineDataElectric();
		 dataAll		=	model.getLineDataAll();
	}
	
	public void clearLinkedLists() {
		dataNormal.clear();
		dataPass.clear();
		dataReserved.clear();
		dataElectric.clear();
		dataAll.clear();
	}
	
	public void clearLineGraph() {
		clearLinkedLists();
	}
	
	private BufferedImage createImage(JPanel panel) {    
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return bi;
    }
	
	public void save(BufferedImage image, String filename) throws IOException{
        ImageIO.write(image, "PNG", new File(filename));
    }
}
*/


/*
 * BarView graph
 * wordt niet gebruikt en is puur voor overzicht
 *
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;

import logic.ElecCar;
import logic.Model;
import logic.ParkingPassCar;
import logic.ReservedCar;

public class BarView extends AbstractView{
	
	public BarView(Model model) {
		super(model);

	}
	
	public Dimension getPrefferedSize() {
		return new Dimension(200,262);
	}
	
	private int getSize(int totaalAuto) {
		int totaal = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
		double size = 1.0 * (double) totaalAuto;
		return (int) size;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int sizeNormaal = getSize(model.getCountCar());
		int sizePass = getSize(model.getCountPass());
		int sizeRes = getSize(model.getCountRes());
		int sizeElec = getSize(model.getCountElec());
		
		g.setColor(SystemColor.menu);
		g.fillRect(0, 0, 200, 200);
		
		//Normaal
		if(sizeNormaal < 240) {
			g.setColor(Color.RED);
			g.fillRect(25, 256-sizeNormaal, 35, sizeNormaal);
			g.setColor(Color.DARK_GRAY);
			g.drawString("" + model.getCountCar(), 35, 253-sizeNormaal);
		}
		else {
			g.setColor(Color.RED);
			g.fillRect(25, 16, 35, 240);
			g.setColor(Color.DARK_GRAY);
			g.drawString("" + model.getCountCar(), 35, 13);
		}
		
		
		//Pas
		g.setColor(ParkingPassCar.returnColor());
		g.fillRect(60, 256-sizePass, 35, sizePass);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + model.getCountPass(), 70, 253-sizePass);
		
		//Reserveer
		g.setColor(ReservedCar.returnColor());
		g.fillRect(95, 256-sizeRes, 35, sizeRes);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + model.getCountRes(), 105, 253-sizeRes);
		
		//Electrisch
		g.setColor(ElecCar.returnColor());
		g.fillRect(130, 256-sizeElec, 35,sizeElec);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + model.getCountElec(), 140, 253-sizeElec);
		
	}
}
*/
package view;

import logic.Model;

@SuppressWarnings("serial")
public class LineGraph extends AbstractView {
	public LineGraph(Model model){
		super(model);
	}
}
