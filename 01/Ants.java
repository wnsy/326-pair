// Java 8

import java.awt.Point;
import java.util.Scanner;
import java.util.HashMap;

class Ants {
    enum Direction {
        //NORTH(0), EAST(1), SOUTH(2), WEST(3);
        NORTH, EAST, SOUTH, WEST;
        private static Direction[] val = values();

        /** Credit:
         * http://stackoverflow.com/questions/17006239/
         * whats-the-best-way-to-implement-next-and-previous-on-an-enum-type/17006263#17006263
         */
        public Direction right() { //next
            return val[(this.ordinal() + 1) % val.length];
        }

        public Direction left() { //previous
            return val[(this.ordinal() - 1) % val.length];
        }
    }

    //data struct
    private int antSteps; //ant will take a certain sequence of steps

    
    public static void main(String[] args) {
        /**
         * First hashmap for Point & States. string for white/black tile
         *
         * |-----------|----------|
         * |  Point    |  State   |
         * |-----------|----------|
         * |  0,0      |    w     | 
         * |-----------|----------|
         *
         */
        HashMap<Point, Character> locationPoint = new HashMap<Point, Character>();      
        Point antJoe = new Point();
        char storeState = 'w';

        locationPoint.put(antJoe, storeState);
        locationPoint.put(new Point(1,1), storeState);

        //Iterate over all of the keys: 
        for (Point key : locationPoint.keySet()) {
            System.out.println(key);
        }
        

        System.out.println("Ant Joe is located at: " + "[" +  antJoe.x + "," + antJoe.y + "]");


        // System.out.println("Test user input: "); //user prompt
        
        // Scanner userInputScanner = new Scanner(System.in);
        // while (userInputScanner.hasNext()) {
        //     // antsLocation. = userInputScanner.nextInt(antsLocation); //Point, x, y
        //     //TODO: Store x, y from java.awt.Point
        //     // antsLocation.xy = userInputScanner.nextInt(); // store delete 
        //     storeState = userInputScanner.nextLine();
        //     location.put(storeLocation, storeState);
        // }
        
        


        //TO DELETE
        //        System.out.println("All directions: ");
        //        Direction directions[] = Direction.values();
        //        for (Direction dir : Direction.values()) {
        //            System.out.println(dir);
        //        }
        //        System.out.println();

    }
}

