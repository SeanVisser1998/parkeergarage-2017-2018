package logic;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Model extends AbstractModel implements Runnable{
	
	public static boolean run;
	
	public JLabel timeText;
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    private Car[][][] cars;
    
    String dayString;
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private int timeScale = 100;
    private int tickPause = 100;
    
    int numberOfFloors;
    int numberOfRows;
    int numberOfPlaces;
    int numberOfOpenSpots;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
	
	public Model(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		run = false;
		entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        
        
     // Create a calendar with year and day of year.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 180);
        
        // Get the weekday
        day = calendar.get(Calendar.DAY_OF_WEEK);

        // Get weekday name
        DateFormatSymbols dfs = new DateFormatSymbols();
        dayString =  dfs.getWeekdays()[day];
        
	}
	
	public void run() {
		run = true;
		while(run) {
			tick();
			try {
				Thread.sleep(tickPause);
			} catch (Exception e) {} 
		}
		
	}
	
	public void start() {
		if(!run) {
			run = true;
			new Thread(this).start();
		}
	}
	
	public void stop() {
		run = false;
	}
	
	public void reset() {
		/*
		 * Deze methode moet door de array cars lopen en checken of er een car in de array zit en als die er inzit dan moet je die verwijdert worden.
		 */
		
		for(int f = 0; f < numberOfFloors; f++){ //aantal floors
			for(int r = 0; r < numberOfRows; r++){ //aantal rows
				for(int p = 0; p < cars[f][r].length; p++){  //aantal plaatsen
					if(cars[f][r][p] != null)					//checkt of de plaats leeg is of niet
					removeCarAt(cars[f][r][p].getLocation());	//verwijdert de auto
				}
			}
		}
		run = false;
		tick();
		day = 0;
		hour = 0;
		minute = 0;
	}
	
	public void plusOne() {
		tick();
	}
	
//	public void minusOne() {
//		
//	}
	
	public void sliderChanged(int sliderValue) {
		timeScale = sliderValue;
	}
	
	public void close() {
		int option = JOptionPane.showConfirmDialog( 
								
                null, "Are you sure you want to close the application?",
                "Close Confirmation", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
        }
	}
	
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    private void tick() {
    	oldTick();
    	advanceTime();
    	handleExit();
    	notifyViews();
    	handleEntrance();
    }

    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        tickPause = timeScale;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 7) {
            day -= 7;
        }
        
     // Get weekday name
        DateFormatSymbols dfs = new DateFormatSymbols();
        dayString =  dfs.getWeekdays()[day];
        
        //set the day + time
        String timeString = dayString + " : " + hour + ":" + minute;
		timeText.setText(timeString);
    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
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
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    
    private void carLeavesSpot(Car car){
		removeCarAt(car.getLocation());
		exitCarQueue.addCar(car);
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    public void oldTick() {
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
    		for (int row = 0; row < getNumberOfRows(); row++) {
    			for (int place = 0; place < getNumberOfPlaces(); place++) {
    				Location location = new Location(floor, row, place);
    				Car car = getCarAt(location);
    				if (car != null) {
    					car.tick();
					}
				}           
    		}
		}
 	}

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
}
