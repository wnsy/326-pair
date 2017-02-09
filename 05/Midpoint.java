//Java 8
import java.lang.Math;

public strictfp class Midpoint {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
//        System.out.println(midpoint(Integer.MAX_VALUE, Integer.MAX_VALUE+1000));

        //01.symmetric midpoint. midpoint(x, y) = midpoint(y, x)
        System.out.println("01.symmetric midpoint: " + midpoint(Integer.MAX_VALUE, Integer.MIN_VALUE));
        System.out.println("01.symmetric midpoint: " + midpoint(Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println("01.symmetric midpoint: " + midpoint(Integer.MAX_VALUE, Integer.MAX_VALUE+20000));
        System.out.println("01.symmetric midpoint: " + midpoint(Integer.MAX_VALUE+20000, Integer.MAX_VALUE));

//        System.out.println(midpoint(139, 150));
//        System.out.println(midpoint(150, 139));

        //02. x ≤ y then x ≤ midpoint(x, y) ≤ y
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE+1000;
        System.out.println("02. x ≤ y then x ≤ midpoint(x, y) ≤ y " +
                midpoint(x, y));
        System.out.println("x: " + Math.min(x, y) + " y: " + Math.min(x, y));

        //03. min(x, y) ≤ midpoint(x, y) ≤ max(x, y)
//        System.out.println(Math.min(100, 25));

    }

    private static int midpoint(int x, int y) {
        if (Math.min(x, y) < 0 && Math.min(x, y) > 0) {
            return (Math.max(x, y) + Math.min(x, y));
        } else {
            return (Math.min(x, y) + Math.max(x, y));
        }
    }


}