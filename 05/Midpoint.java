//Java 8
import java.lang.Math;

public strictfp class Midpoint {
    public static void main(String[] args) {
        //symmetric midpoint
        System.out.println(midpoint(Integer.MAX_VALUE, Integer.MAX_VALUE+1000));
        System.out.println(midpoint(139, 150));
        System.out.println(mid(150, 139));

        // x ≤ y then x ≤ midpoint(x, y) ≤ y
        System.out.println(mid(100, 25));

        // min(x, y) ≤ midpoint(x, y) ≤ max(x, y)
        System.out.println(Math.min(100, 25));
    }

    private static int midpoint(int x, int y) {
        if (Math.min(x, y) < 0 && Math.min(x, y) > 0) {
            return (Math.max(x, y) + Math.min(x, y));
        } else {
            return (Math.min(x, y) + Math.max(x, y));
        }

    }
}