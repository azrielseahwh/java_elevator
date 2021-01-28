package main.elevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static main.elevator.ElevatorInput.initConfig;

public class ElevatorController {

    public static List<Thread> totalElevators(HashMap<String, String> configFile) {

        List<Thread> elevatorList = new ArrayList<>();
        int delimiter = Integer.parseInt(configFile.get("num_elevator"));
        for (int i = 1; i != delimiter + 1; i++) {
            Elevator elevatorRunnable = new Elevator();
            elevatorRunnable.setElevatorNumber(i);
            elevatorRunnable.setAvailable(true);
            Thread elevatorThread = new Thread(elevatorRunnable,String.format("Elevator %s thread",i));

            synchronized (elevatorRunnable){
                elevatorRunnable.notify();
            }
            elevatorList.add(elevatorThread);
        }
//        System.out.println(elevatorList.get(0).getElevatorNumber());
        return elevatorList;
    }

    public static void main(String[] args) {
        HashMap<String, String> configFile;
        configFile = initConfig();
        List<Thread> elevatorList = totalElevators(configFile);
        elevatorList.forEach(thread -> thread.start());
    }


}
