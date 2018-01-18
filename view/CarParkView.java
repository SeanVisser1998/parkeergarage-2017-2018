package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import model.Car;
import model.CarPark;
import model.Location;

@SuppressWarnings("serial")
public class CarParkView extends View{
	
	private Dimension size;
    private Image carParkImage;    

    /**
     * Constructor for objects of class CarPark
     */
    public CarParkView(CarPark model) {
    	super(model);
    	this.size = new Dimension(680,330);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    public void updateView() {
    	CarPark carPark = (CarPark) super.model;
    	
        // Create a new car park image if the size has changed.

            carParkImage = createImage(size.width, size.height);
            
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < CarPark.getNumberOfFloors(); floor++) {
            for(int row = 0; row < CarPark.getNumberOfRows(); row++) {
                for(int place = 0; place < CarPark.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = CarPark.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }

    /**
     * Paint a place on this car park view in a given color.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }
}

