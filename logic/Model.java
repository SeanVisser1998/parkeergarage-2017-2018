package logic;

import java.awt.Graphics;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import view.BarView;

public class Model extends AbstractModel implements Runnable{
	
	public static boolean run;
	
	public JMenuBar menuBar;
	
	private int minuteOld = -1;
	
	public boolean switchGraphs = true;
	
	public JLabel normalCar;
	public JLabel resCar;
	public JLabel passCar;
	public JLabel elecCar;
	public JLabel totalCar;
	
	public JLabel omzet;
	public JLabel normaalOmzet;
	public JLabel elecOmzet;
	public JLabel reserveerOmzet;
	
	//line graph
	public LinkedList<Integer> lineDataNormal;
	public LinkedList<Integer> lineDataPass;
	public LinkedList<Integer> lineDataReserved;
	public LinkedList<Integer> lineDataElectric;
	public LinkedList<Integer> lineDataAll;
	public int lineGraphStepSize;
	
	public JLabel datum;
	
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	private static final String RESERVE = "3";
	private static final String ELECTRIC = "4";
	
	private int countCar;
	private int countPass;
	private int countRes;
	private int countElec;
		
	private int gemTicketPrijs;
	private int fee;
	private int totaalOmzet;
	private int normaalIntOmzet;
	private int reserveerIntOmzet;
	private int elecIntOmzet;
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    
    private Car[][][] cars;
    
    String dayString;
    
    Calendar calendar;
    
    private int dayPref = 2;
    private int day = 2;
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
    
    int weekResArrivals = 30;
    int weekendResArrivals = 20;
    
    int weekElecArrivals = 10;
    int weekendElecArrivals = 20;

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
        
        this.countCar = 0;
        this.countPass = 0;
        this.countRes = 0;
        this.countElec = 0;
        
        this.gemTicketPrijs = 10;
        this.fee = 2;
        this.totaalOmzet = 0;
        this.normaalIntOmzet = 0;
        this.reserveerIntOmzet = 0;
        this.elecIntOmzet = 0;
        
        //line graph
        lineDataNormal = new LinkedList<Integer>();
        lineDataPass = new LinkedList<Integer>();
        lineDataReserved = new LinkedList<Integer>();
        lineDataElectric = new LinkedList<Integer>();
        lineDataAll = new LinkedList<Integer>();
        lineGraphStepSize = 10;
        
        calendar = Calendar.getInstance();
       
//        day = calendar.get(Calendar.DAY_OF_WEEK);
        DateFormatSymbols dfs = new DateFormatSymbols();
        dayString = dfs.getWeekdays()[day];
        
        calendar.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        
        String datumString = dayString + ": "+ format1.format(calendar.getTime());
        System.out.println(datumString);
        
        System.out.println(entranceCarQueue);

	}
	
	public String returnDayString() {
		if(dayString == ""){
				DateFormatSymbols dfs = new DateFormatSymbols();
				return dfs.getWeekdays()[Calendar.SATURDAY];
		}
		return dayString;
	}
	
	public int getCountCar() {
		return countCar;
	}
	
	public int getCountPass() {
		return countPass;
	}
	
	public int getCountElec() {
		return countElec;
	}
	
	public int getCountRes() {
		return countRes;
	}
	
	public int getCountTotalCar() {
		return countCar+countPass+countRes+countElec;
	}
	
	
	public int getGemTicketPrijs() {
		return gemTicketPrijs;
	}
	
	public int getNormaalOmzet() {
		return normaalIntOmzet * gemTicketPrijs;
	}
	
	public int getReserveerOmzet() {
		return reserveerIntOmzet * gemTicketPrijs + reserveerIntOmzet * fee;
	}
	
	public int getElecOmzet() {
		return elecIntOmzet * gemTicketPrijs;
	}
	
	public int getTotaalOmzet() {
		return totaalOmzet;
	}
	
	public boolean isRunning() {
		return run;
	}

	public boolean switchGraphs() {
		if(switchGraphs == true) {
			switchGraphs = false;
			return switchGraphs;
		}
		else {
			switchGraphs = true;
			return switchGraphs;
		}
	}
	
	public void reset() {
		/*
		 * Alleen het legen van CarParkView moet nog gedaan worden :)
		 * 
		 */
		stop();
		for(int floor = 0; floor < getNumberOfFloors(); floor++) {
			  for(int row = 0; row < getNumberOfRows(); row++) {
				  for(int place = 0; place < getNumberOfPlaces(); place++) {
					  Location location = new Location(floor, row, place);
	                  removeCarAt(location);
	                  
	                	
	                }
	            }
		}
				
        this.countCar = 0;
        this.countPass = 0;
        this.countRes = 0;
        this.countElec = 0;
        
        this.gemTicketPrijs = 10;
        this.fee = 2;
        this.totaalOmzet = 0;
        this.normaalIntOmzet = 0;
        this.reserveerIntOmzet = 0;
        this.elecIntOmzet = 0;
        
        day = dayPref;
        hour = 0;
        minute = 0;
        
        //niet tick(); gebruiken want dan gaat de tijd 1 minuut vooruit als je
        //op de reset knop klikt.
    	notifyViews();
    	emptyQueues();
    	timeHandling();
    	handleJLabel();
                
	}
	
	public void sliderChanged(int sliderValue) {
		timeScale = sliderValue;
	}
	
	public void emptyQueues() {

		entranceCarQueue.emptyQueue();
	    entrancePassQueue.emptyQueue();
	    paymentCarQueue.emptyQueue();
	    exitCarQueue.emptyQueue();
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
	
	public void setDayPopUp() {
		String[] options = new String[] {"Maandag","Dinsdag","Woensdag","Donderdag","Vrijdag","Zaterdag","Zondag"}; 
		int option = JOptionPane.showOptionDialog(null,
				"Kies de dag waarop je de simulatie wilt starten \n (De simulatie word gereset)",
				"Dag kiezer",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null, options, null);
		if(option == -1) {
			//als de gebruiker het venster sluit is option == -1
			//dus dan niks doen
		}
		if(option == 6) {
			dayPref = 1;
			System.out.println(day);
			reset();
		}
		else {
			//als option iets anders is dan -1 heeft de gebruiker een keuze gemaakt
			//dagen zijn 1-indexed en beginnen bij zondag
			//de array met dagen is 0-indexed en begint bij maandag
			//dus 2 optellen bij de optie en je zit op de goede plek in de array
			dayPref = (option+2);
			System.out.println(day);
			reset();
		}
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
		System.out.println("Stopped!");
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
    	
    	timeHandling();
    	handleJLabel();
    }
    
    private void handleJLabel() {
    	normalCar.setText("Aantal normale auto's: " + getCountCar());
    	resCar.setText("Aantal gereserveerde auto's: " + getCountRes());
    	passCar.setText("Aantal auto's met een pas: " + getCountPass());
    	totalCar.setText("Totaal aantal auto's: " + getCountTotalCar());
    	elecCar.setText("Aantal electrische auto's: " + getCountElec());
    	
    	omzet.setText("Totale omzet: €" + getTotaalOmzet());;
    	normaalOmzet.setText("Omzet normale auto's: €" + getNormaalOmzet());;
    	reserveerOmzet.setText("Omzet gereserveerde auto's: €" + getReserveerOmzet());    
    	elecOmzet.setText("Omzet electrische auto's: €" + getElecOmzet());
    	
    }
    
    public int returnOldMinute() {
    	return minuteOld;
    }
    
    private void lineGraph(int stepSize) {
    	if(minute%stepSize == 0) {
	        lineDataNormal.add(getCountCar());
	        lineDataPass.add(getCountPass());
	        lineDataReserved.add(getCountRes());
	    	lineDataElectric.add(getCountElec());
	    	int total = getCountCar() + getCountPass() + getCountRes() + getCountElec();
	    	lineDataAll.add(total);
        }
    }
    
    public int returnLineGraphStepSize() {
    	return lineGraphStepSize;
    }

    private void advanceTime(){
        // Advance the time by one minute.    	
    	
        minute++;
        
        lineGraph(lineGraphStepSize);

        //        System.out.println(lineDataNormal);
        
        tickPause = timeScale;
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
    
    public int returnMinute() {
    	return minute;
    }
    
    public int returnHour() {
    	return hour;
    }
    
    private void timeHandling() {
    	if(hour >= 7 && hour < 16) {
    		weekDayArrivals = 200;
    		weekDayPassArrivals = 50;
    		
    		weekendArrivals = 100;
    		weekendPassArrivals = 55;
    		
    		weekResArrivals = 30;
    		weekendResArrivals = 20;
    		
    	    weekElecArrivals = 30;
    	    weekendElecArrivals = 20;
    	}
    	else {
    		weekDayArrivals = 20;
    		weekDayPassArrivals = 10;
    		
    		weekResArrivals = 3;
    		weekendResArrivals = 2;
    		
    		weekendArrivals = 20;
    		weekendPassArrivals = 15;
    		
    	    weekElecArrivals = 5;
    	    weekendElecArrivals = 15;
    	}
    	
    	if(day == 5 && hour >= 18 && hour < 23|| day == 6 && hour >= 18 && hour < 23 || day == 7 && hour >= 18 && hour < 23 || day == 0 && hour > 13 && hour < 18) {
    		weekDayArrivals = 350;
    		weekDayPassArrivals = 20;
    		
    		weekResArrivals = 20;
    		weekendResArrivals = 15;
    		
    		weekendArrivals = 350;
    		weekendPassArrivals = 20;
    		
    		weekElecArrivals = 10;
    	    weekendElecArrivals = 10;
    	}
    	else if(day == 5 && hour >= 23 || day == 6 && hour >= 23 || day == 7 && hour >= 23) {
    		weekDayArrivals = 20;
    		weekDayPassArrivals = 10;
    		
    		weekResArrivals = 30;
    		weekendResArrivals = 20;
    		
    		weekendArrivals = 20;
    		weekendPassArrivals = 10;
    		
    		weekElecArrivals = 10;
    	    weekendElecArrivals = 20;
    	}
//    	
    	setTimeText();

    }
    
    public LinkedList<Integer> getLineDataNormal() {
    	return lineDataNormal;
    }
    
    public LinkedList<Integer> getLineDataPass(){
    	return lineDataPass;
    }
    
    public LinkedList<Integer> getLineDataReserved(){
    	return lineDataReserved;
    }
    
    public LinkedList<Integer> getLineDataElectric(){
    	return lineDataElectric;
    }
    
    public LinkedList<Integer> getLineDataAll(){
    	return lineDataAll;
    }
    
    public void setTimeText() {
    	 // Get weekday name
        DateFormatSymbols dfs = new DateFormatSymbols();
        dayString =  dfs.getWeekdays()[day];
        
        //set the day + time
        //leading zeroes toevoegen aan de tijd (bijv 1:5 word 01:05)
        String timeHour = String.format("%02d", hour);
        String timeMinute = String.format("%02d", minute);
        String time = timeHour + ":" + timeMinute;
        
        
        String timeString = dayString + "  " + time;
        datum.setText(timeString);
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
        numberOfCars = getNumberOfCars(weekResArrivals, weekendResArrivals);
        addArrivingCars(numberOfCars, RESERVE);
        numberOfCars = getNumberOfCars(weekElecArrivals, weekendElecArrivals);
        addArrivingCars(numberOfCars, ELECTRIC);
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            
            if(car instanceof ParkingPassCar) {
            	 Location freePassLocation = getFirstFreePassLocation();
                 setCarAt(freePassLocation, car);
                 countPass++;                 
            }else if(car instanceof ReservedCar){
            	 Location freeLocation = getFirstFreeResLocation();
                 setCarAt(freeLocation, car);
                 countRes++;
            }else if(car instanceof AdHocCar) {
            	Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);
                countCar++;
            }
            else if(car instanceof ElecCar) {
            	Location freeLocation = getFirstFreeLocation();
                setCarAt(freeLocation, car);
                countElec++;
            }

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
            if(car instanceof ParkingPassCar) {
           	                
           }else if(car instanceof ReservedCar){
        	   totaalOmzet += gemTicketPrijs;
        	   totaalOmzet += fee;
        	   reserveerIntOmzet++;
           	 
           }else if(car instanceof AdHocCar) {
        	   totaalOmzet += gemTicketPrijs;
        	   normaalIntOmzet++;
           }
           else if(car instanceof ElecCar) {
        	   totaalOmzet += gemTicketPrijs;
        	   elecIntOmzet++;
           }
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
        //Do you know da wae!? 
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
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new ReservedCar());
            }
            break;	  
    	case ELECTRIC:
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new ElecCar());
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
        
        if(car instanceof ParkingPassCar) {
        	countPass--;
        }else if(car instanceof ReservedCar) {
        	countRes--;
        }else if(car instanceof AdHocCar){
        	countCar--;
        }else if(car instanceof ElecCar) {
        	countElec--;
        }
        
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
    
    public Location getFirstFreePassLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor--) {
            for (int row = 5; row < getNumberOfRows(); row--) {
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
    
    public Location getFirstFreeResLocation() {
        for (int floor = 2; floor < getNumberOfFloors(); floor--) {
            for (int row = 1; row < getNumberOfRows(); row--) {
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

    public boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
}
