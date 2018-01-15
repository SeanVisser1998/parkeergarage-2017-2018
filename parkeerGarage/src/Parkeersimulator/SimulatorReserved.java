package Parkeersimulator;
/*
 * Sean Visser
 * Simulator die de simulatie's afhandelt van de gereserveerde auto's
 */

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;

public class SimulatorReserved {
	
	private static final String RES = "1"; 

    private CarQueue entranceResQueue; 
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    private SimulatorReservedView simulatorReservedView; //toegevoegd Sean Visser
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;
    
    /*
     * Auto's die gereserveerd hebben
     */
    int weekReservedArrivals = 30; //toegevoegd Sean Visser
    int weekendReservedArrivals = 5; //Toegevoegd Sean Visser

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    
    public SimulatorReserved() {
    	
    	entranceResQueue = new CarQueue();
    	paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        
        /*
         * Sean Visser
         * 12-01-2018
         * Code blok voor WEL gereserveerde plekken (toegevoegd) VIEW
         */
        simulatorReservedView = new SimulatorReservedView(1, 6, 30);
        simulatorReservedView.setIconImage(Toolkit.getDefaultToolkit().getImage(Simulator.class.getResource("/afbeeldingen/Parking-Logo.jpg")));
        simulatorReservedView.setResizable(false);
        simulatorReservedView.setBackground(Color.DARK_GRAY);
        simulatorReservedView.getContentPane().setBackground(Color.DARK_GRAY);
        //Einde
    }
    
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }
    
    private void tick() {
    	advanceTime();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }
    
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }
    
    private void handleEntrance(){
    	carsArriving(); 
    	carsEntering(entranceResQueue);
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void updateViews(){
    	simulatorReservedView.tick();
        // Update the car park view.
        simulatorReservedView.updateView();	
    }
    
    private void carsArriving(){   	
        int numberOfCars=getNumberOfCars(weekReservedArrivals, weekendReservedArrivals); //toegevoegd
        addArrivingCars(numberOfCars, RES); //toegevoegd
    }
    
    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			simulatorReservedView.getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = simulatorReservedView.getFirstFreeLocation();
            simulatorReservedView.setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = simulatorReservedView.getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = simulatorReservedView.getFirstLeavingCar();
        }
    }
    
    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case RES:
            for (int i = 0; i < numberOfCars; i++) {
            	entranceResQueue.addCar(new ReservedCar());
            }
            break;
    	}
    }
    
    private void carLeavesSpot(Car car){
    	simulatorReservedView.removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
}
