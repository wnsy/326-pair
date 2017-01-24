// Java 8
/* TODO: add warnings, expected input type, use etc */
import java.awt.Point;
import java.util.Scanner;
import java.util.HashMap;

class Ants {
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
    private int antSteps; //ant will take a certain sequence of steps //DOUBLE UP WITH noSteps variable

    /**
     * Hashmap for Point & State(s). Char for white/black tiles (states)
     *
     * |-----------|----------|
     * |  Point    |  State   |
     * |-----------|----------|
     * |    0,0    |  ,       |
     * |-----------|----------|
     */
    static HashMap<Point, Character> locationStateMap = new HashMap<Point, Character>();
    static HashMap<Character, char[]> stateChange = new HashMap<Character, char[]>();
    static HashMap<Character, int[]> directionOut = new HashMap<Character, int[]>();
    private static char blank;
   
//SHOULD do some documentation or something
    public static void main(String[] args) {
      
      Scanner scan = new Scanner(System.in);
      int i = 0;

      char[] tempCharArray = new char[4];
      int[] tempIntArray = new int[4];
      int noSteps = 0;
      boolean scenarioCompleted = false;
      
      while(scan.hasNextLine()){
        System.out.println("Clearing.. ");
        locationStateMap.clear();
        stateChange.clear();
        directionOut.clear();
        i = 0;
        scenarioCompleted = false;
        while(!scenarioCompleted){
          char firstTemp;
          String dnaLine = scan.nextLine();
          System.out.println(dnaLine); //print line when see it
          System.out.println("length: " + dnaLine.length()); //debug
          if(i == 0){
            blank = dnaLine.charAt(0);
          }
          i++;
          if(dnaLine.length() > 1){ //TODO: this assumes all numbers are single digit
            firstTemp = dnaLine.charAt(0);
            String temp = dnaLine.substring(2,6); //cardinal directions
            
            for(int j = 0; j < 4; j++){ //would enumerated be better??
              char direction = temp.charAt(j);
              if(direction == 'N'){
                tempIntArray[j] = 0;
              } else if(direction == 'E'){
                tempIntArray[j] = 1;
              } else if(direction == 'S'){
                tempIntArray[j] = 2;
              } else {
                tempIntArray[j] = 3; //West
              }
            }
            directionOut.put(firstTemp, tempIntArray);
            temp = dnaLine.substring(7,11);
            for(int j = 0; j < 4; j++){
              tempCharArray[j] = temp.charAt(j);
            }
            stateChange.put(firstTemp, tempCharArray);
          } else {
            //System.out.println("char at 0: " + dnaLine.charAt(0)); //debug
            noSteps = Integer.parseInt(dnaLine.substring(0,1)); //SHOULD HAVE A CATCH METHOD HERE
            //System.out.println("NO STEPS: " + noSteps); //debug
            System.out.println("Let's go ant Joe!");
            System.out.println(walk(noSteps).toString());

            scenarioCompleted = true;
          }
        }
      }
    }
    private static Point walk(int noSteps){
      int incomingDirection = 0; //0 = N, 1 = E, 2 = S, 3 = W
      char pointState = blank;
      Point currentPoint = new Point(0,0);
      char outState = blank;
      for(int i = 0; i < noSteps; i++){
        System.out.println("incoming direction: " + incomingDirection);
        System.out.println("@ " + currentPoint.toString() + "noSteps left: " + (noSteps - i));
        if(locationStateMap.get(currentPoint) != null){ //determines current tile state
          pointState = locationStateMap.get(currentPoint); //DOUBLE LOOK UP, potential inefficiency
        } else {
          pointState = blank;
          //System.out.println("new coordinate so space is: " + blank);
        }
        System.out.println("State: " + pointState);
        outState = stateChange.get(pointState)[incomingDirection]; //determine state change based on incoming
        locationStateMap.remove(currentPoint);
        if(outState != blank){ //Doesn't store things that can be generated
            locationStateMap.put(currentPoint, outState); //change the state of the current point based on incoming
        }
        incomingDirection = directionOut.get(pointState)[incomingDirection]; //set incoming direction
        
        //change current position
        if(incomingDirection == 0){
          currentPoint.setLocation(currentPoint.getX(), currentPoint.getY() + 1);
        } else if(incomingDirection == 1){
          currentPoint.setLocation(currentPoint.getX() + 1, currentPoint.getY());
        } else if(incomingDirection == 2){
          currentPoint.setLocation(currentPoint.getX(), currentPoint.getY() - 1);
        } else {
          currentPoint.setLocation(currentPoint.getX() - 1, currentPoint.getY()); //W, W = 3
        }
      }
      return currentPoint;
    }
}
