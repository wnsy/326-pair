// Java 8
/* TODO: add warnings, expected input type, use etc */

import java.awt.*;
import java.util.Scanner;
import java.util.HashMap;

class AntsCopy{
    enum Direction {
        //NORTH(0), EAST(1), SOUTH(2), WEST(3);
        NORTH, EAST, SOUTH, WEST;
        private static Direction[] val = values();

        /**
         * Credit:
         * http://stackoverflow.com/questions/17006239/
         * whats-the-best-way-to-implement-next-and-previous-on-an-enum-type/17006263#17006263
         */
    }

    //data struct
    /**
     * Hashmap for Point & State(s). Char for white/black tiles (states)
     * <p>
     * |-----------|----------|
     * |  Point    |  State   |
     * |-----------|----------|
     * |    0,0    |  ,       |
     * |-----------|----------|
     */
    private static HashMap<Point, Character> locationStateMap;
    private static HashMap<Character, String> stateChange;
    private static HashMap<Character, int[]> directionOut = new HashMap<Character, int[]>();
    private static char blank;
    private int antSteps = 8; //ant will take a certain sequence of steps //DOUBLE UP WITH noSteps variable


    //SHOULD do some documentation or something
    public static void main(String[] args) {
        locationStateMap = new HashMap<Point, Character>();
        stateChange = new HashMap<Character, String>();
        Scanner scan = new Scanner(System.in);
        int i = 0;

        char[] tempCharArray = new char[4];
        int[] tempIntArray = new int[4];
        int noSteps = 8;
        boolean scenarioCompleted = false;


        while (scan.hasNextLine()) {
            String dnaLine = scan.nextLine();
            if(dnaLine.isEmpty()){
                walk(noSteps,'N');
                System.out.println("Clearing.. ");
                locationStateMap.clear();
                stateChange.clear();
                directionOut.clear();
            }else{
                //System.out.println("DNA Line: " + dnaLine); //print line when see it
                //System.out.println("length: " + dnaLine.length()); //debug
                char firstTemp = dnaLine.charAt(0);
                String temp = dnaLine.substring(2); //cardinal directions
                stateChange.put(firstTemp, temp);
                Point d = new Point(0,0);
                locationStateMap.put(d, firstTemp);
            }
            //System.out.println("Temp: " + temp);
        }

    }

    private static void walk(int steps, char dir){
        Point current = new Point(0,0);
        char currd = dir;
        while(steps >= 0){
            if(!locationStateMap.containsKey(current)){
                String DNA = stateChange.get(currd);
                if(dir == 'N'){
                    current.y = current.y+1;
                    locationStateMap.put(current, DNA.charAt(4));
                    steps--;
                    currd = DNA.charAt(0);
                }else if(dir=='E'){
                    current.x = current.x+1;
                    locationStateMap.put(current, DNA.charAt(5));
                    steps--;
                    currd = DNA.charAt(1);
                }else if(dir=='S'){
                    current.y = current.y-1;
                    locationStateMap.put(current, DNA.charAt(6));
                    steps--;
                    currd = DNA.charAt(2);
                }else if(dir=='W'){
                    current.x = current.x-1;
                    locationStateMap.put(current, DNA.charAt(7));
                    steps--;
                    currd = DNA.charAt(3);
                }
            }else{
                char outState = locationStateMap.get(current);
                String DNA = stateChange.get(outState);
                //System.out.println(DNA.length() + " " + DNA.substring(0,4));
                //String in = DNA.substring(0,4);
                //String out = DNA.substring(4);
                if(currd == 'N'){
                    System.out.println(DNA.charAt(4));
                    current.y = current.y+1;
                    locationStateMap.put(current, DNA.charAt(4));
                    steps--;
                    currd = DNA.charAt(0);
                }else if(currd=='E'){
                    current.x = current.x+1;
                    locationStateMap.put(current, DNA.charAt(5));
                    steps--;
                    currd = DNA.charAt(1);
                }else if(dir=='S'){
                    current.y = current.y-1;
                    locationStateMap.put(current, DNA.charAt(6));
                    steps--;
                    currd = DNA.charAt(2);
                }else if(dir=='W'){
                    current.x = current.x-1;
                    steps--;
                    currd = DNA.charAt(3);
                }
            }
        }
    }
}








/**
 private static Point walk(int noSteps) {
 int incomingDirection = 0; //0 = N, 1 = E, 2 = S, 3 = W
 char pointState = blank;
 Point currentPoint = new Point(0, 0);
 char outState = blank;
 for (int i = 0; i < noSteps; i++) {
 System.out.println("incoming direction: " + incomingDirection);
 System.out.println("@ " + currentPoint.toString() + "noSteps left: " + (noSteps - i));
 if (locationStateMap.get(currentPoint) != null) { //determines current tile state
 pointState = locationStateMap.get(currentPoint); //DOUBLE LOOK UP, potential inefficiency
 } else {
 pointState = blank;
 //System.out.println("new coordinate so space is: " + blank);
 }
 System.out.println("State: " + pointState);
 outState = stateChange.get(pointState)[incomingDirection]; //determine state change based on incoming
 locationStateMap.remove(currentPoint);
 if (outState != blank) { //Doesn't store things that can be generated
 locationStateMap.put(currentPoint, outState); //change the state of the current point based on incoming
 }
 incomingDirection = directionOut.get(pointState)[incomingDirection]; //set incoming direction

 //change current position
 if (incomingDirection == 0) {
 currentPoint.setLocation(currentPoint.getX(), currentPoint.getY() + 1);
 } else if (incomingDirection == 1) {
 currentPoint.setLocation(currentPoint.getX() + 1, currentPoint.getY());
 } else if (incomingDirection == 2) {
 currentPoint.setLocation(currentPoint.getX(), currentPoint.getY() - 1);
 } else {
 currentPoint.setLocation(currentPoint.getX() - 1, currentPoint.getY()); //W, W = 3
 }
 }
 return currentPoint;
 }


 private static boolean isAlpha(String userInput) {
 char[] chars = userInput.toCharArray();
 for (char character : chars) {
 if (!Character.isLetter(character)) {
 return false;
 }
 }
 return true;
 }*/

