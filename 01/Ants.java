import java.awt.Point;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Ants {
    
    static HashMap<Point, Character> locationStateMap = new HashMap<Point, Character>();
    static HashMap<Character, char[]> stateChange = new HashMap<Character, char[]>();
    static HashMap<Character, int[]> directionOut = new HashMap<Character, int[]>();
    private static char blank;

    //still should do some documentation or something
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

                if(dnaLine.isEmpty()){
                    System.out.println();
                    break;
                }        
                if(dnaLine.charAt(0) == '#'){ //comment line is not repeated
                    break; //TODO: comment line currently kills scenario. Needs to just be ignored
                }
                System.out.println(dnaLine); //print line when see it
                if (i == 0) {
                    blank = dnaLine.charAt(0); //first char of first DNA line is taken as the default tile state
                }
                i++;
                if (dnaLine.length() == 11) { //is a DNA line/rule
                    firstTemp = dnaLine.charAt(0);
                    String temp = dnaLine.substring(2, 6); //parse the cardinal directions

                    for (int j = 0; j < 4; j++) { // N = 0, E = 1, S = 2, W = 3 this makes indexing into DNA arrays easier
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
                    for (int j = 0; j < 4; j++){
                        tempCharArray[j] = temp.charAt(j);
                    }
                    stateChange.put(firstTemp, tempCharArray);
                
                } else if (dnaLine.length() < 11) { //less than 11 characters is assumed to be a number
                    noSteps = Integer.parseInt(dnaLine.substring(0, dnaLine.length())); //SHOULD HAVE A CATCH METHOD HERE
                    Point p = walk(noSteps);
                    System.out.println("# " + (int) p.x + " " + (int) p.y);
                    scenarioCompleted = true;
                } else { //unexpected input
                    System.out.println("Sorry that input is incorrect");
                    System.out.println("Enter DNA lines in the format x NNNN xxxx\n followed by a number up to 10^9");
                    scenarioCompleted = true;
                }
            }
        }
    }

    private static Point walk(int noSteps) {
        int incomingDirection = 0; //0 = N, 1 = E, 2 = S, 3 = W
        char pointState = blank; //state of current tile
        Point currentPoint = new Point(0, 0); //the location of the current tile
        char outState = blank; //what the tile will change too after being visited
        for (int i = 0; i < noSteps; i++) {
            if (locationStateMap.get(currentPoint) != null) { //determine the state of tile ant is on
                pointState = locationStateMap.get(currentPoint);
            } else {
                pointState = blank;
            }
            outState = stateChange.get(pointState)[incomingDirection]; //find new tile state from direction and DNA
            locationStateMap.remove(currentPoint);
            if (outState != blank) { //Doesn't store things that can be generated
                locationStateMap.put(new Point(currentPoint), outState); //change the tile state
            }
            incomingDirection = directionOut.get(pointState)[incomingDirection]; //set incoming direction based on DNA and direction

            //change current position
            if (incomingDirection == 0) {
                currentPoint.setLocation(currentPoint.x, currentPoint.y + 1);
            } else if (incomingDirection == 1) {
                currentPoint.setLocation(currentPoint.x + 1, currentPoint.y);
            } else if (incomingDirection == 2) {
                currentPoint.setLocation(currentPoint.x, currentPoint.y - 1);
            } else {
                currentPoint.setLocation(currentPoint.x - 1, currentPoint.y); //W, W = 3
            }
        }
        return currentPoint;
    }
}
