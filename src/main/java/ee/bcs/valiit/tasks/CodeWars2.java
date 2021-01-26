package ee.bcs.valiit.tasks;

import javassist.expr.NewArray;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class CodeWars2 {
    public static void main(String[] args) {
        stat();
        //String[] test = {"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"};
        //dirReduc(test);
        //String proov = "01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17";
        //System.out.println(stat(proov));
        // int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // snail(array);
        // int[] array2 = {20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5};
        // findIt(array2);
        // System.out.println(validatePin("0000"));
        //System.out.println(high("snpb mx heq rznlxr m opy fvmbzyup hrvwdnnjxd cms f iuygbkqp fntamvx bmolibqnfoym yo cpt"));
    }

    /**
     * https://www.codewars.com/kata/521c2db8ddc89b9b7a0000c1
     * Given an n x n array, return the array elements arranged from outermost elements to the middle element, traveling clockwise.
     */

    public static int[] snail(int[][] array) {
        int lines = 4;
        int startrow = 0;           // k
        int endingrow =  4;         // m number of rows
        int startingcolm = 0;       // l
        int endcolumn = 4;          // n number of columns

        int[][] matrix = new int[lines][lines];
        int i = 0;

        while ( startrow < endingrow && startingcolm < endcolumn) {
            for ( i = 1; i < endcolumn; i++) {
                System.out.printf("%3d",matrix[startrow][i]);
            }
            startrow++;

            for (i = startrow; i < endingrow; i++) {
                System.out.printf("%3d",matrix[i][endcolumn-1]);
            }
            endcolumn--;


        }

        System.out.println();

        for ( i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++) {
                System.out.printf("%3d",matrix[i][j]);
            }
            System.out.println();
        }
        return null;
    }
// ---------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/54da5a58ea159efa38000836
     * Given an array of integers, find the one that appears an odd number of times.
     * <p>
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
            if (elementCount.get(element) % 2 != 0) {
                answer = element;
            }

        }

        return answer;
    }
// ---------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/55f8a9c06c018a0d6e000132/java
     * ATM machines allow 4 or 6 digit PIN codes and PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits.
     * <p>
     * If the function is passed a valid PIN string, return true, else return false.
     * "1234"   -->  true
     * "12345"  -->  false
     * "a234"   -->  false
     */

    public static boolean validatePin(String pin) {
        String[] splitPin = pin.split("");
        List<Boolean> values = new ArrayList<Boolean>();
        for (int i = 0; i < splitPin.length; i++) {
            if (splitPin[i].equals("1") || splitPin[i].equals("2") || splitPin[i].equals("3") || splitPin[i].equals("4") || splitPin[i].equals("5") ||
                    splitPin[i].equals("6") || splitPin[i].equals("7") || splitPin[i].equals("8") || splitPin[i].equals("9") || splitPin[i].equals("0")) {
                values.add(true);
            } else {
                values.add(false);
            }
        }
        if (values.size() == 4 || values.size() == 6) {
            if (!values.contains(false)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
// ---------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/57eb8fcdf670e99d9b000272/java
     * Given a string of words, you need to find the highest scoring word.
     */
    public static String high(String s) {
        HashMap<String, Integer> values = new HashMap<>();
        HashMap<String, Integer> sums = new HashMap<>();
        values.put("a", 1);
        values.put("b", 2);
        values.put("c", 3);
        values.put("d", 4);
        values.put("e", 5);
        values.put("f", 6);
        values.put("g", 7);
        values.put("h", 8);
        values.put("i", 9);
        values.put("j", 10);
        values.put("k", 11);
        values.put("l", 12);
        values.put("m", 13);
        values.put("n", 14);
        values.put("o", 15);
        values.put("p", 16);
        values.put("q", 17);
        values.put("r", 18);
        values.put("s", 19);
        values.put("t", 20);
        values.put("u", 21);
        values.put("v", 22);
        values.put("w", 23);
        values.put("x", 24);
        values.put("y", 25);
        values.put("z", 26);

        String[] splitString = s.split(" ");

        for (int i = 0; i < splitString.length; i++) {
            int sum = 0;
            String[] splitSplitString = splitString[i].split("");
            for (int j = 0; j < splitSplitString.length; j++) {
                sum = sum + values.get(splitSplitString[j]);
            }
            sums.put(splitString[i], sum);
        }

        System.out.println(sums.toString());
        String answer = "";
        int answerInt = 0;

        for (String el : splitString) {
            if (sums.get(el) > answerInt) {
                answerInt = sums.get(el);
                answer = el;
            }
        }
        return answer;
    }

// ---------------------------------------------------------------------------------------------------------------------

/**
 * https://www.codewars.com/kata/55b3425df71c1201a800009c
 * String proov = "01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17";
 * FORMAT HH|MM|SS
 * Statistics for an Athletic Association
  */

    public static String stat() {
        String proov = "01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17";
        String[] split = proov.split(", ");

        for (String s : split) {
            System.out.println(s);
        }
    return "valmis";

    }

    public static int calculateSeconds(String time) {
        String[] split = time.split("|");
        int hoursToSec = Integer.parseInt(split[0]) * 360;
        int minToSec = Integer.parseInt(split[1]) * 60;
        int sec = Integer.parseInt(split[2]);
        return hoursToSec + minToSec + sec;
    }

// ---------------------------------------------------------------------------------------------------------------------
/**
 * https://www.codewars.com/kata/550f22f4d758534c1100025a/java
 * Directions Reduction
 */
    public static String[] dirReduc(String[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (("NORTH".equals(arr[i]) && "SOUTH".equals(arr[i + 1])) ||
                    ("SOUTH".equals(arr[i]) && "NORTH".equals(arr[i + 1])) ||
                    ("EAST".equals(arr[i]) && "WEST".equals(arr[i + 1])) ||
                    ("WEST".equals(arr[i]) && "EAST".equals(arr[i + 1]))) {
                arr[i] = null;
                arr[i + 1] = null;
            }
        }

        final String[] newArr = Arrays.stream(arr).filter(s -> s != null).toArray(String[]::new);

        if (newArr.length == arr.length) {
            return arr;
        } else {
            return dirReduc(newArr);
        }
    }

}
//        String[] mystery = {"NORTH", "WEST", "SOUTH", "EAST"};
//        if (arr == mystery) {
//            return arr;
//        }
//        int directionNS = 0;
//        int directionWE = 0;
//        for (int i=0; i<arr.length; i++) {
//            if (arr[i].equals("NORTH")) {
//                directionNS++;
//            } else if (arr[i].equals("SOUTH")){
//                directionNS--;
//            } else if (arr[i].equals("EAST")){
//                directionWE++;
//            } else {
//                directionWE--;
//            }
//        }
//
//        System.out.println(directionNS);
//        System.out.println(directionWE);
//
//        List<String> list = new ArrayList<>();
//
//        for (int i = 0; i<arr.length; i++) {
//            if (arr[i].equals("NORTH") && directionNS > 0) {
//                list.add("NORTH");
//                directionNS--;
//            } else if ((arr[i].equals("SOUTH") && directionNS < 0)) {
//                list.add("SOUTH");
//                directionNS++;
//            } else if ((arr[i].equals("EAST") && directionWE > 0)) {
//                list.add("EAST");
//                directionWE--;
//            } else if ((arr[i].equals("WEST") && directionWE < 0)) {
//                list.add("WEST");
//                directionWE++;
//            } else {
//                continue;
//            }
//        }
//        String[] answer = list.toArray(String[]::new);
//
//        for (String s : answer) {
//            System.out.println(s);
//        }
//        return answer;
//
//        if (arr == null || arr.length <= 1) {
//            return arr;
//        }
//
//        for (int i = 0; i < arr.length - 1; i++) {
//            if (("NORTH".equals(arr[i]) && "SOUTH".equals(arr[i + 1])) ||
//                    ("SOUTH".equals(arr[i]) && "NORTH".equals(arr[i + 1])) ||
//                    ("EAST".equals(arr[i]) && "WEST".equals(arr[i + 1])) ||
//                    ("WEST".equals(arr[i]) && "EAST".equals(arr[i + 1]))) {
//                arr[i] = null;
//                arr[i + 1] = null;
//            }
//        }
//        final String[] newArr = Arrays.stream(arr)
//                .filter(s -> s != null)
//                .toArray(String[]::new);
//
//        if (newArr.length == arr.length) {
//            return arr;
//        } else {
//            return dirReduc(newArr);
//        }
//    }
//}
//    }
//        List<String> list = new ArrayList<>();
//        for(int i= 0; i<arr.length;i++) {
//            if (i != arr.length - 1 && ((arr[i].equals("NORTH") && arr[i + 1].equals("SOUTH")) || (arr[i].equals("SOUTH") && arr[i + 1].equals("NORTH")))) {
//                i++;
//            } else if (i != arr.length - 1 && ((arr[i].equals("WEST") && arr[i + 1].equals("EAST")) || (arr[i].equals("EAST") && arr[i + 1].equals("WEST")))) {
//                i++;
//            } else {
//                list.add(arr[i]);
//            }
//        }
//        System.out.println(list.toString());



    // TOOTAV LAHENDUS AGA EI SOBI !!!!
//        if (arr == null || arr.length <= 1) {
//            return arr;
//        }
//
//        List<String> almostAnswer = new ArrayList<>();
//        HashMap<String, Integer> elementCount = new HashMap();
//        for (String element : arr) {
//            int count = 0;
//            for (int i = 0; i < arr.length; i++) {
//                if (arr[i] == element) {
//                    count++;
//                }
//            }
//            if (!elementCount.containsKey(element)) {
//                elementCount.put(element, count);
//            }
//        }
//
//        int northSouthRate = elementCount.get("NORTH") - elementCount.get("SOUTH");
//        int westEastRate = elementCount.get("EAST") - elementCount.get("WEST");
//
//        if (northSouthRate < 0) {
//            while (northSouthRate < 0) {
//                almostAnswer.add("SOUTH");
//                northSouthRate++;
//            }
//        } else {
//            while (northSouthRate > 0) {
//                almostAnswer.add("NORTH");
//                northSouthRate--;
//            }
//        }
//
//        if (westEastRate < 0) {
//            while (westEastRate < 0) {
//                almostAnswer.add("WEST");
//                westEastRate++;
//            }
//        } else {
//            while (westEastRate > 0) {
//                almostAnswer.add("WEST");
//                westEastRate--;
//            }
//        }
//        String[] strings = almostAnswer.toArray(String[]::new);
//        return strings;
//
//    }



