package logic;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class Model extends AbstractModel implements Runnable{
	
	public static boolean run;
	
	public JLabel timeText;
	public JLabel openSpots;
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RESERVE = "3";
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    private Car[][][] cars;
    
    String dayString;
    
    Calendar calendar;
    
    CarQueue queue;
    
    private int day = 1;
    private int hour = 0;
    private int minute = 0;
    private int timeScale = 100;
    private int tickPause = 100;
        
    int numberOfFloors;
    int numberOfRows;
    int numberOfPlaces;
    int numberOfOpenSpots;
    int numberOfPassCars;
    int numberOfAdHoc;
    int numberOfPassPlaces;
    int numberOfOpenPassPlaces;

    int numberOfReserve;
    
    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 500; // average number of arriving cars per hour
    int weekendPassArrivals = 500; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
	
	public Model(int numberOfFloors, int numberOfRows, int numberOfPlaces, int numberOfPassPlaces) {
		run = false;
		entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenPassPlaces = numberOfPassPlaces;
        this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        makePassPlaces(numberOfPassPlaces);
        
     // Create a calendar with year and day of year.
        calendar = Calendar.getInstance();
        
        // Get the weekday
        day = calendar.get(Calendar.DAY_OF_WEEK);

        // Get weekday name
        DateFormatSymbols dfs = new DateFormatSymbols();
        dayString =  dfs.getWeekdays()[day];
        
	}
	public int getNumberOfReserved() {
		return numberOfReserve;
	}
	
	public void addReserved() {
		numberOfReserve ++;
	}
	
	public void removeReserved() {
		numberOfReserve --;
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
		
		for(int f = 0; f < numberOfFloors; f++){ 						//aantal floors
			for(int r = 0; r < numberOfRows; r++){ 						//aantal rows
				for(int p = 0; p < cars[f][r].length; p++){  			//aantal plaatsen
					if(cars[f][r][p] != null){							//checkt of de plaats leeg is of niet
						Object color = cars[f][r][p].getColor();
						Color passCar = Color.blue;
						if(color == passCar){
							removeCarAt(cars[f][r][p].getLocation());	//verwijdert de auto
							remakePassLocation();
						}
						Color passPlace = Color.magenta;
						if(color == passPlace){
							removeCarAt(cars[f][r][p].getLocation());	//verwijdert de auto
							remakePassLocation();
						}else{
						removeCarAt(cars[f][r][p].getLocation());		//verwijdert de auto
						}	
					}
				}
			}
		}
		run = false;
		tick();
		day = calendar.get(Calendar.DAY_OF_WEEK);
		hour = 0;
		minute = 0;
		weekDayArrivals = 10;
		weekDayPassArrivals = 5;
	}
	
	public void plusOne() {
		tick();
	}
	
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
    
    public int getNumberOfOpenPassPlaces(){
    	return numberOfOpenPassPlaces;
    }
    
    private void tick() {
    	oldTick();
    	advanceTime();
    	handleExit();
    	notifyViews();
    	handleEntrance();
    	timeHandling();
    	if(numberOfReserve > 0) {
    		removeReserved();
    	}
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
    }
    
    private void timeHandling() {
    	if(hour >= 7 && hour < 16) {
    		weekDayArrivals = 200;
    		weekDayPassArrivals = 50;
    		weekendArrivals = 100;
    		weekendPassArrivals = 55;
    	}
    	else {
    		weekDayArrivals = 20;
    		weekDayPassArrivals = 10;
    		weekendArrivals = 20;
    		weekendPassArrivals = 15;
    	}
    	
    	if(day == 5 && hour >= 18 && hour < 23|| day == 6 && hour >= 18 && hour < 23 || day == 7 && hour >= 18 && hour < 23 || day == 0 && hour > 13 && hour < 18) {
    		weekDayArrivals = 350;
    		weekDayPassArrivals = 20;
    		weekendArrivals = 350;
    		weekendPassArrivals = 20;
    	}
    	else if(day == 5 && hour >= 23 || day == 6 && hour >= 23 || day == 7 && hour >= 23) {
    		weekDayArrivals = 20;
    		weekDayPassArrivals = 10;
    		weekendArrivals = 20;
    		weekendPassArrivals = 10;
    	}
    	
    	
    	 // Get weekday name
        DateFormatSymbols dfs = new DateFormatSymbols();
        dayString =  dfs.getWeekdays()[day];
        
        //set the day + time
        //leading zeroes toevoegen aan de tijd (bijv 1:5 word 01:05)
        String timeHour = String.format("%02d", hour);
        String timeMinute = String.format("%02d", minute);
        String time = timeHour + ":" + timeMinute;
        
        
        String timeString = dayString + "  " + time;
		timeText.setText(timeString);
		
		String spots = String.valueOf(this.numberOfOpenSpots);
		openSpots.setText("Open spots: " + spots);
		
		if(queue.carsInQueue() > 10) {
			queue.removeCar();
		}
    }
    
    public void makePassPlaces(int numberOfSpots) {
        numberOfPassPlaces = numberOfSpots;

        int x = 0,
            z = 0,
            y = 0;

        for (int i=0; i<numberOfSpots; i++) {
            if (z == numberOfPlaces) {
                if (x == numberOfRows - 1) {
                    y++;
                    x = 0;
                    z = 0;
                }else {
                    x += 1;
                    z = 0;
                	}
            	}
            setPassHolderSpace(new Location(y, x, z), new PassPlace());
            z++;
        	}
        }
    
        public boolean setPassHolderSpace(Location loc, PassPlace phs) {
            if (!locationIsValid(loc))
                return false;

            Car oldCar = getCarAt(loc);
            if(oldCar == null) {
                cars[2][0][loc.getPlace()] = phs;
                cars[2][1][loc.getPlace()] = phs;
                cars[2][2][loc.getPlace()] = phs;
                cars[2][3][loc.getPlace()] = phs;
                phs.setLocation(loc);
                return true;
            }

            return false;
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
    	int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        
    	numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        
        numberOfCars = getNumberOfReserveCars();
        addArrivingCars(numberOfCars, RESERVE);
    }
    
    private int getNumberOfReserveCars() {
    	return numberOfReserve;
    }

    private void carsEntering(CarQueue queue){
    	this.queue = queue;
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 &&
    			getNumberOfOpenPassPlaces()>0 &&
    			i<enterSpeed) {
            Car car = queue.removeCar();
            if(queue == entrancePassQueue){
            	Location freePassLocation = getFirstPassLocation();
            	setCarAt(freePassLocation, car);
            	i++;
            }else{
            	Location freeLocation = getFirstFreeLocation();
            	setCarAt(freeLocation, car);
            	i++;
            }
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
        		remakePassLocation();
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
    	case RESERVE:
    		for (int i = 0; i <numberOfCars; i++) {
    			entranceCarQueue.addCar(new ReservedCar());
    		}
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
            if(car.getHasToPay()==false){
            	numberOfOpenPassPlaces--;
            }else{
            numberOfOpenSpots--;
            }
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
        if(car.getHasToPay() == true){
        	numberOfOpenSpots++;
        }else{
        	numberOfOpenPassPlaces++;
        }
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
    
    public Location getFirstPassLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if(getCarAt(location) != null) {
                        if(getCarAt(location).getColor() == PassPlace.COLOR) {
                            removeCarAt(location);
                            return location;
                        }
                    }
                }
            }
        }
        return null;
    }

    public void remakePassLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows() && row < 4; row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if(getCarAt(location) == null) {
                        setPassHolderSpace(location, new PassPlace());
                        }
                    }
                }
            }
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
