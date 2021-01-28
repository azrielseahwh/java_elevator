package main.elevator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ElevatorInput {

    public static final Logger logger = LogManager.getLogger(ElevatorInput.class);

    public static HashMap<String, String> initConfig(){
        BufferedReader configReader;
        HashMap<String, String> configMap = new HashMap<>();
        String[] parts;
        logger.trace("Loading configuration from config.txt..");
        try {
            configReader = new BufferedReader(new FileReader(
                    System.getProperty("user.dir") + "\\src\\main\\resources\\config.txt"));
            String line = configReader.readLine();
            while (line != null) {
                parts = line.split("=");
                configMap.put(parts[0],parts[1]);
                line = configReader.readLine();
            }
            configReader.close();
        } catch (IOException e) {
            logger.error(e);
        }
//        System.out.println(configMap);
        return configMap;
    }

    public static void askForInput(HashMap<String, String> configFile) {
        String separator = configFile.get("separator");
        String delimiter = configFile.get("delimiter");
        logger.trace("Input current floor and desired floor, separated by '{}' and delimited by '{}'",separator, delimiter);
        logger.trace("e.g. src1{}dest1{}src2{}dest2",separator,delimiter,separator);
    }

    public static ArrayList userInput(HashMap<String, String> configFile, String userInput) {
        String[] parts;
        String separator = configFile.get("separator");
        String delimiter = configFile.get("delimiter");
        parts = userInput.replaceAll("\\s+", "").split(delimiter);
        ArrayList<Integer[]> test = new ArrayList<>();

        try {
            int currentFloor;
            int destinationFloor;

            logger.info("User Input - {}", userInput);

            for (String part : parts) {
                if (!part.contains(separator)) {
                    logger.error("Input does not contain separator '{}'",separator);
                } else {
                    Integer[] current_destination = new Integer[2];
                    String[] floors = part.split(separator);
                    currentFloor = Integer.parseInt(floors[0]);
                    destinationFloor = Integer.parseInt(floors[1]);
                    current_destination[0] = currentFloor;
                    current_destination[1] = destinationFloor;
                    test.add(current_destination);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }

        return test;
    }
}