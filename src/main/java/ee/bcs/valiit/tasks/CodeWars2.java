package ee.bcs.valiit.tasks;

import java.util.HashMap;

public class CodeWars2 {
    public static void main(String[] args) {
        // int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // snail(array);
        // int[] array2 = {20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5};
        // findIt(array2);
    }

    /**
     * https://www.codewars.com/kata/521c2db8ddc89b9b7a0000c1
     * Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.
     */

    public static int[] snail(int[][] array) {
        int lines = 4;

        for (int i = 0; i < lines; i++) {
            for (int j = 1; j <= lines; j++) {
                if (j <= (lines - i))
                    System.out.print(j);
            }
            System.out.println();
        }
        return null;
    }
// ---------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/54da5a58ea159efa38000836
     * Given an array of integers, find the one that appears an odd number of times.
     *
     * There will always be only one integer that appears an odd number of times.
     */

    public static int findIt(int[] a) {
        int answer = 0;
        HashMap<Integer, Integer> elementCount = new HashMap<Integer, Integer>();
        for (int element : a) {
            int count = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == element) {
                    count++;
                }
            }
            if (!elementCount.containsKey(element)) {
                elementCount.put(element, count);
            }
        }

        for (Integer element : elementCount.keySet()) {
            if (elementCount.get(element) %2 != 0 ) {
                answer = element;
            }

        }

        return answer;
    }
// ---------------------------------------------------------------------------------------------------------------------
    /**
     * https://www.codewars.com/kata/55f8a9c06c018a0d6e000132/java
     * ATM machines allow 4 or 6 digit PIN codes and PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits.
     *
     * If the function is passed a valid PIN string, return true, else return false.
     * "1234"   -->  true
     * "12345"  -->  false
     * "a234"   -->  false
     */

    public static boolean validatePin(String pin) {
        String[] splitPin= pin.split("");
        if (splitPin.length > 4 || splitPin.length < 4) {
            return false;
        } else {
            return true;
        }
    }
}