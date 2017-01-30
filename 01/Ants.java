//Java 8

import java.awt.Point;
import java.util.Scanner;
import java.util.HashMap;

/**
 *  Langton's ant simulation
 *
 */
public class Ants {

    /**
     * locationStateMap stores the position of the ant and the its state
     * stateChange stores the current state and the changed state
     * directionOut stores the state of the ant and its index direction (NSEW)
     */
    static HashMap<Point, Character> locationStateMap = new HashMap<Point, Character>();
    static HashMap<Character, char[]> stateChange = new HashMap<Character, char[]>();
    static HashMap<Character, int[]> directionOut = new HashMap<Character, int[]>();
    private static char blank;


    /**
     * Reads input from stdin and prints output.
     * Main method checking if the scenario has been completed, and check if there are rules in the
     * map. If there is no rules, user input will be saved to the map.
     *
     * @param args Command line arguments are not used
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = 0;
        int noSteps = 0;
        boolean scenarioCompleted = false;
        while (scan.hasNextLine()) {
            locationStateMap.clear(); //clears the maps for the next scenario
            stateChange.clear();
            directionOut.clear();
            i = 0;
            scenarioCompleted = false;
            while (!scenarioCompleted) {
                char[] tempCharArray = new char[4];
                int[] tempIntArray = new int[4];
                char firstTemp;
                String dnaLine = scan.nextLine();
                Scanner stepScanner = new Scanner(dnaLine);
                if (dnaLine.isEmpty()) {
                    System.out.println();
                    break;
                }
                if (dnaLine.charAt(0) == '#') { //comment line is not repeated
                    break; //needs to just be ignored
                }
                System.out.println(dnaLine); //print line when see it

                if (i == 0) {
                    blank = dnaLine.charAt(0); //first char of first DNA line is taken as the default tile state
                }
                i++;
                if (dnaLine.length() == 11) { //is a DNA line/rule
                    firstTemp = dnaLine.charAt(0);
                    String temp = dnaLine.substring(2, 6); //parse the cardinal directions

                    // N = 0, E = 1, S = 2, W = 3 this makes indexing into DNA arrays easier
                    for (int j = 0; j < 4; j++) {
                        char direction = temp.charAt(j);
                        if (direction == 'N') {
                            tempIntArray[j] = 0;
                        } else if (direction == 'E') {
                            tempIntArray[j] = 1;
                        } else if (direction == 'S') {
                            tempIntArray[j] = 2;
                        } else {
                            tempIntArray[j] = 3; //West
                        }
                    }

                    directionOut.put(firstTemp, tempIntArray);
                    temp = dnaLine.substring(7, 11);
                    for (int j = 0; j < 4; j++) {
                        tempCharArray[j] = temp.charAt(j);
                    }

                    stateChange.put(firstTemp, tempCharArray);

                } else if (stepScanner.hasNextInt()) { //Scans for steps
                    noSteps = Integer.parseInt(dnaLine.substring(0, dnaLine.length()));
                    Point p = walk(noSteps);
                    System.out.println("# " + p.x + " " + p.y);
                    scenarioCompleted = true;
                } else { //unexpected input
                    System.out.println("Sorry that input is incorrect");
                    System.out.println("Enter DNA lines in the format x NNNN xxxx\n followed by a number up to 10^9");
                    scenarioCompleted = true;
                }
            }
        }
    }


    /**
     * Walk method for the ant, and check if the rules exist in the map.
     * Changes the current direction of the ant
     * @param noSteps the number of steps of the ant
     * @return currentPoint of the ant (x, y)
     */
    private static Point walk(int noSteps) {
        int incomingDirection = 0; //0 = N, 1 = E, 2 = S, 3 = W
        char pointState = blank; //state of current tile
        Point currentPoint = new Point(0, 0); //the location of the current tile
        char outState = blank; //what the tile will change too after being visited
        for (int i = 0; i < noSteps; i++) {
            if (locationStateMap.containsKey(currentPoint)) { //determine the state of tile ant is on
                pointState = locationStateMap.get(currentPoint);
            } else {
                pointState = blank;
            }
            outState = stateChange.get(pointState)[incomingDirection]; //find new tile state from direction and DNA

            locationStateMap.remove(currentPoint);
            if (outState != blank) { //Doesn't store things that can be generated
                locationStateMap.put(new Point(currentPoint), outState); //change the tile state
            }

            //set incoming direction based on DNA and direction
            incomingDirection = directionOut.get(pointState)[incomingDirection];

            //change current position
            if (incomingDirection == 0) { //N
                currentPoint.y = currentPoint.y + 1;
            } else if (incomingDirection == 1) { //E
                currentPoint.x = currentPoint.x + 1;
            } else if (incomingDirection == 2) { //S
                currentPoint.y = currentPoint.y - 1;
            } else {
                currentPoint.x = currentPoint.x - 1; //W = 3
            }
            //incomingDirection = directionOut.get(pointState)[incomingDirection]; //set incoming direction
        }
        return currentPoint.getLocation();
    }
}
