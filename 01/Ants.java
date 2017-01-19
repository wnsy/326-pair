// Java 8

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
        public Direction right() { //next
            return val[(this.ordinal() + 1) % val.length];
        }

        public Direction left() { //previous
            return val[(this.ordinal() - 1) % val.length];
        }
    }

    //data struct
    private int antSteps; //ant will take a certain sequence of steps

    /**
     * Hashmap for Point & State(s). Char for white/black tiles (states)
     *
     * |-----------|----------|
     * |  State    |  Point   |
     * |-----------|----------|
     * |    .      |  0,0     |
     * |-----------|----------|
     */
    static HashMap<Character, Point> locationStateMap = new HashMap<Character, Point>();

    public static void main(String[] args) {
        char storeState = 'w'; //Key: State
        Point antJoe = new Point(); //Value: position
        locationStateMap.put(storeState, new Point(1, 1));

        //TODO: while loop, hasNext() blablabla
        System.out.println("The story of your average Ant Joe :)");
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println("Enter x position: ");
        antJoe.x = userInputScanner.nextInt();
        System.out.println("Enter y position: ");
        antJoe.y = userInputScanner.nextInt();
        System.out.println("Enter the initial character state: ");
        storeState = userInputScanner.next().charAt(0); //FIXME: accept a blank space, don't accept number as state?
        locationStateMap.put(storeState, antJoe);
        //TODO: check toString method from Point class for formatting
        System.out.println("Ant Joe is located at: " + "[" + antJoe.x + "," + antJoe.y + "]" + " and his state is: "
                           + locationStateMap.get(storeState) + "\n");

        //Iterate over all of the keys:
        System.out.println("Iterate keys:");
        for (Character key : locationStateMap.keySet()) {
            System.out.println(key);
        }


        //TO DELETE
//        System.out.println("All directions: ");
//        Direction directions[] = Direction.values();
//        for (Direction dir : Direction.values()) {
//            System.out.println(dir);
//        }
//        System.out.println();

    }
}

