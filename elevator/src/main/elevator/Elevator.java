package main.elevator;

import java.util.Scanner;

public class Elevator implements Runnable{

    volatile int elevatorNumber;
    volatile int currentFloor;
    volatile int finalFloor;
    volatile boolean available;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getElevatorNumber() {
        return elevatorNumber;
    }

    public void setElevatorNumber(int elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getFinalFloor() {
        return finalFloor;
    }

    public void setFinalFloor(int finalFloor) {
        this.finalFloor = finalFloor;
    }

    @Override
    public void run() {
        try {
            synchronized (this){
                int elevatorNum = getElevatorNumber();
                boolean available = isAvailable();
                System.out.println(Thread.currentThread().getName());
                System.out.println(elevatorNum);
                wait();
                if (available) {
                    Scanner input = new Scanner(System.in);
                    System.out.println(Thread.currentThread().getName());
                }
                System.out.println("Outside wait");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
