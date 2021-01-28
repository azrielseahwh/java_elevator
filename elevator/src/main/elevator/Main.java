package main.elevator;

import java.util.*;

import static main.elevator.ElevatorController.totalElevators;
import static main.elevator.ElevatorInput.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> configFile;
        configFile = initConfig();
        askForInput(configFile);

        Scanner input = new Scanner(System.in);

        while(true) {
            String userInput = input.nextLine();
            ArrayList floorInput = userInput(configFile, userInput);
            for (Object part : floorInput) {
//                System.out.println(Arrays.deepToString((Object[]) part));
                Integer[] floor = (Integer[]) part;
                int currentFloor = floor[0];
                int finalFloor = floor[1];
                Elevator elevator = new Elevator();
                elevator.setCurrentFloor(currentFloor);
                elevator.setFinalFloor(finalFloor);
                logger.info("Current floor = " + currentFloor);
                logger.info("Final floor = " + finalFloor);
            }

            List<Thread> elevatorList = totalElevators(configFile);
            elevatorList.forEach(thread -> thread.start());

            logger.info("Input current floor and desired floor");
        }
    }
}
