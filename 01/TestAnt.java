// Java 8
/* TODO: add warnings, expected input type, use etc */

import java.awt.*;
import java.util.Scanner;
import java.util.HashMap;


class TestAnt {
    private static HashMap<Point, Character> locationStateMap = new HashMap<Point, Character>();
    private static HashMap<Character, char[]> stateChange = new HashMap<Character, char[]>();
    private static HashMap<Character, int[]> directionOut = new HashMap<Character, int[]>();
    private static char blank;
    private int antSteps = 8; //ant will take a certain sequence of steps //DOUBLE UP WITH noSteps variable


    //SHOULD do some documentation or something
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] tempCharArray = new char[4];
        int[] tempIntArray = new int[4];
        int noSteps = 0;
        boolean scenarioCompleted = false;

        while (scan.hasNextLine()) {
            String dnaLine = scan.nextLine();
            if (dnaLine.isEmpty()) {
                //walk method before we clear. :D
                System.out.println("Clearing.. ");
                locationStateMap.clear();
                stateChange.clear();
                directionOut.clear();
                scenarioCompleted = false;
            } else if (!scenarioCompleted) {
                System.out.println(dnaLine); //print line when see it
                System.out.println("Length: " + dnaLine.length()); //debug
                char firstTemp = dnaLine.charAt(0);
                String storeTempDirections = dnaLine.substring(2, 6);
                directionOut.put(firstTemp, tempIntArray);
                storeTempDirections = dnaLine.substring(7,11);
            }
        }
    }
}