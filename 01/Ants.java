// Java 8
import java.awt.Point;
import java.util.Scanner;
import java.util.HashMap;

public class Ants {
    static HashMap<Point, Character> locationStateMap = new HashMap<Point, Character>();
    static HashMap<Character, char[]> stateChange = new HashMap<Character, char[]>();
    static HashMap<Character, int[]> directionOut = new HashMap<Character, int[]>();
    private static char blank;

    //SHOULD do some documentation or something
    //yes I agree
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = 0;
        int noSteps = 0;
        boolean scenarioCompleted = false;

        while (scan.hasNextLine()) {
            locationStateMap.clear();
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
                System.out.println(dnaLine); //print line when see it
                //System.out.println("length: " + dnaLine.length()); //debug
                if(dnaLine.isEmpty() || dnaLine.charAt(0) == '#'){
                    System.out.println(dnaLine);
                    break;
                }
                if (i == 0) {
                    blank = dnaLine.charAt(0);
                }
                i++;
                if (dnaLine.length() == 11) { //TODO: this assumes all numbers are single digit
                    firstTemp = dnaLine.charAt(0);
                    String temp = dnaLine.substring(2, 6); //cardinal directions

                    for (int j = 0; j < 4; j++) { //would enumerated be better??
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
                    System.out.print(firstTemp + "| ");
                    for (int j = 0; j < 4; j++){
                        tempCharArray[j] = temp.charAt(j);
                        System.out.print(tempCharArray[j]);
                    }
                    System.out.println();
                    stateChange.put(firstTemp, tempCharArray);
                } else if (stepScanner.hasNextInt()) {
                    System.out.println("char at 0: " + dnaLine.charAt(0)); //debug
                    System.out.println();
                    noSteps = Integer.parseInt(dnaLine.substring(0, dnaLine.length())); //SHOULD HAVE A CATCH BLOCK HERE
                    //System.out.println("NO STEPS: " + noSteps); //debug
                    System.out.println("Let's go ant Joe!");
                    System.out.println(walk(noSteps).toString());
                    scenarioCompleted = true;
                } else {
                    System.out.println("Sorry that input is incorrect");
                    System.out.println("Enter DNA lines in the format x NNNN xxxx\n followed by a number up to 10^9");
                    scenarioCompleted = true;
                }
            }
        }
    }

    private static Point walk(int noSteps) {
        int incomingDirection = 0; //0 = N, 1 = E, 2 = S, 3 = W
        char pointState = blank;
        Point currentPoint = new Point(0, 0);
        char outState = blank;
        char[] debugChar;
        int[] directions; //debugs
        for (int i = 0; i < noSteps; i++) {
            System.out.println("incoming direction: " + incomingDirection);
            System.out.println("# " + currentPoint.x + " " + currentPoint.y + " noSteps: " + i);
            if (locationStateMap.containsKey(currentPoint)) { //determines current tile state
                //another method to look up
                pointState = locationStateMap.get(currentPoint); //DOUBLE LOOK UP, potential inefficiency
            } else {
                pointState = blank;
                //System.out.println("new coordinate so space is: " + blank);
            }
            System.out.print("State: " + pointState + " ");
            debugChar = stateChange.get(pointState);

        /*
        for(char a : debugChar){
            System.out.print(a);
        }
        System.out.println();
        */
            outState = stateChange.get(pointState)[incomingDirection]; //determine state change based on incoming
            System.out.println("out: " + outState);
            locationStateMap.remove(currentPoint);
            if (outState != blank) { //Doesn't store things that can be generated
                locationStateMap.put(currentPoint, outState); //change the state of the current point based on incoming
            }
            //debugs

            directions = directionOut.get(pointState);
            for(int a: directions){
                System.out.print(a);
            }
            System.out.println(); //debug

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
            incomingDirection = directionOut.get(pointState)[incomingDirection]; //set incoming direction

        }
        return currentPoint.getLocation();
    }
}