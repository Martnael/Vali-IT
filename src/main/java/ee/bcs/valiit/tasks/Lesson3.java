package ee.bcs.valiit.tasks;

import java.util.ArrayList;

public class Lesson3 {
    public static void main(String[] args) {
        // System.out.println(sum(new int[] {20,37,20,21}));
        // System.out.println(factorial(6));
        // System.out.println(sort(new int[]{20, 37, 15, 21}));
        // System.out.println(reverseString("Mart on"));
        // System.out.println(isPrime(7));
        // Lesson3Hard.randomGame();
        System.out.println(Lesson3Hard.morseCode("sos"));
    }

    /**
     * return sum of the list
     *
     * @param x List with integers
     * @return sum of the list
     */
    public static int sum(int[] x) {
        int sum = 0;
        for (int el : x) {
            sum = sum + el;
        }
        return sum;
    }

    /**
     * Return factorial of input
     *
     * @param x int
     * @return y int
     */
    public static int factorial(int x) {
        int sum = x;
        for (int i = x - 1; i > 0; i--) {
            sum = sum * i;
        }
        return sum;
    }

    /**
     * Sort array smaller to bigger number
     * @param a
     * @return
     */
    public static int[] sort(int[] a) {
        int arrayLength = a.length;
        for (int i = 0; i < arrayLength; i++) {        // votan esimese liikme
            for (int j = i + 1; j < arrayLength; j++) {    // votan teise liikme
                if (a[i] > a[j]) {                          // vordlen neid
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;                               // et ma ei kaotaks ara vaadeldavat elementi siis kirjutan elemendi asemele kust votsin elemendi
                }
            }
        }
        return a;
    }

    /**
     * Return reverse String
     *
     * @param a String
     * @return reverse string
     */
    public static String reverseString(String a) {
        StringBuilder sb = new StringBuilder();
        for (int i = a.length()-1; i >= 0; i--) {
            sb.append(a.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Is integer primary number or not
     *
     * @param x
     * @return boolean
     */

    public static boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        for (int i = 2; i < x; i ++) {
            if ( x % i == 0 ) {
               return false;
            }
        }
        return true;
    }
}
