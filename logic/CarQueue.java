package logic;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }
    
    public Car peekCar() {
    	return queue.peek();
    }

    public int carsInQueue(){
    	return queue.size();
    }
    
    public void emptyQueue() {
    	queue.clear();
    }
}
