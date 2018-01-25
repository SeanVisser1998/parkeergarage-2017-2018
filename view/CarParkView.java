package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import logic.Car;
import logic.Location;
import logic.Model;

@SuppressWarnings("serial")
public class CarParkView extends AbstractView{
	
	private Dimension size;
    private Image carParkImage; 

	public CarParkView(Model model) {
		super(model);
		size = new Dimension(800, 500);
	}
	
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    public void paintComponent(Graphics g) {
    	
    	
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }
    
    /*
     * Normaal: Places 0 t/m 360 = wit
     * Res: Places 360 t/m 420 = geel
     * Pas: Places 420 t/m 540 = blauw
     */

    public void updateView() {
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < model.getNumberOfFloors(); floor++) {
            for(int row = 0; row < model.getNumberOfRows(); row++) {
                for(int place = 0; place < model.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = model.getCarAt(location);
                    
                    
                    Color color = car == null ? Color.white : car.getColor();
                    if(model.getCarAt(location) == null) { 
                    	//als de plek een plek is voor abbonementhouders maak de plek lichtblauw
                    	if(floor == 2 && row == 5 || floor == 2 && row == 4 || floor == 2 && row == 3 ||floor == 2 && row == 2) {
	                    	
	                    	color =  Color.decode("#B7EDFF");
                    	}
                    	//als de plek een plek is voor reserveringen maak de plek licht geel
                    	if(floor == 2 && row == 0 || floor == 2 && row == 1) {
                    		color = Color.decode("#FFFFDB");
                    	}
                    }
                    drawPlace(graphics, location, color);
                    
                }
            }
        }
        repaint();
    }

    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); 
    }
}
