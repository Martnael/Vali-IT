package ee.bcs.valiit.tasks;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class CodeWars {
    public static void main(String[] args) {
        // System.out.println(buddy(1071625, 1103735));
        // System.out.println(findUniq(new double[]{ 1, 1, 1, 2, 1, 1 }));
        // System.out.println(decode("Romani ite domumasasas"));
        // System.out.println(toBinary(11));
        // System.out.println(solve(new int[]{15,7,12,10,11}));
        // PawnGame.redKnight(1,6);
        // System.out.println(sumOfDifferences(new int[]{-17, 17}));
        // Get(200000);
    }

//---------------------------------------------------------------------------------------------------------------------------------------


    /**
     * https://www.codewars.com/kata/5a24a35a837545ab04001614
     * In this kata, your task is to implement what I call Interlaced Spiral Cipher (ISC).
     * @Param String
     */

    public static String encode(String s) {
        // Your code here!
        return "";
    }

    public static String decode(String s) {
        int length = s.length();
        StringBuilder sb = new StringBuilder();
        int boxSize = 0;
        sb.append(s);

        //////  find closest and make such string //////////
        if (checkPerfectSqrt(s.length())) {
            boxSize = (int) Math.sqrt(s.length());
        } else {
            while (!checkPerfectSqrt(s.length())) {
                length++;
                sb.append(" ");
            }
            boxSize = length;
        }

        ///// Start decoding ///////
        String[][] matrix = new String[boxSize][boxSize];
        int howManyCircles = (boxSize + 1) /2;
        String[] splitVersion = sb.toString().split("");

        return sb.toString() + boxSize;
    }


    public static Boolean checkPerfectSqrt (int number) {
        double sq = Math.sqrt(number);
        return (sq - Math.floor(sq)) == 0;
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/5819f1c3c6ab1b2b28000624
     * The Padovan sequence is the sequence of integers P(n) defined by the initial values
     *
     * P(0)=P(1)=P(2)=1
     * @param power
     * @return
     */

    public static BigInteger Get (long power) {
        BigInteger[] series = new BigInteger[(int) power];
        series[0]=BigInteger.ONE;
        series[1]=BigInteger.ONE;
        series[2]=BigInteger.ONE;
        series[3]=BigInteger.TWO;
        series[4]=BigInteger.TWO;

        for (int i = 5; i < power; i++) {
            series[i] = series[i-2].add(series[i-3]);
        }

        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < power; i++) {
            sum = sum.add(series[i]);
        }

        System.out.println(sum.toString());
        return BigInteger.ZERO;
    }





//---------------------------------------------------------------------------------------------------------------------------------------
    /**
     * !!!! FAILING TIMEOUT TEST MAKE IT MORE EFFICIENT !!!!!!!!!
     * https://www.codewars.com/kata/585d7d5adb20cf33cb000235/train/java
     * There is an array with some numbers. All numbers are equal except for one. Try to find it!
     * @param arr
     * @return
     */

    public static double findUniq(double arr[]) {
        HashMap<Double, Integer> elementCount = new HashMap<Double, Integer>();
        Arrays.sort(arr);

        double temp = 0;

        for (int i = 0 ; i < 0; i++) {
            if (arr[i] == arr[i+1]) {

            }
        }



        /// a loop to count how many of each elements is represented in the list elements and limit it with maxOccurrences
        Arrays.sort(arr);
        for (double element : arr) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == element) {
                    count++;
                }
            }
            if (!elementCount.containsKey(element)) {
                elementCount.put(element, count);
            }
        }
        double answer = 0;
        for (Double aDouble : elementCount.keySet()) {
            if (elementCount.get(aDouble) == 1) {
                answer = aDouble;
            }
        }
        return answer;
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/5a090c4e697598d0b9000004
     * @param arr
     * @return
     */
    public static int[] solve (int[] arr){
        int [] answer = new int[arr.length];
        int [] sorted = sort(arr);

        int pos = 0;
        for (int i = 0 ; i < sorted.length ; i = i + 2) {
            answer[i] = sorted[pos];
            pos++;
        }

        int pos2 = arr.length-1;
        for (int i = 1 ; i < sorted.length ; i = i + 2) {
            answer[i] = sorted[pos2];
            pos2--;
        }

        for (int i : answer) {
            System.out.println(i);
        }
        return answer;
    }

    public static int[] sort(int[] a) {
        int arrayLength = a.length;
        for (int i = 0; i < arrayLength; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (a[i] < a[j]) {
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
        return a;
    }


//---------------------------------------------------------------------------------------------------------------------------------------


    /**
     * https://www.codewars.com/kata/59fca81a5712f9fa4700159a
     * @param n
     * @return
     */

    public static int toBinary(int n) {
        List<String> order = new ArrayList<>();
        while (n > 0) {
            int j = n % 2;
            order.add(String.valueOf(j));
            n = n / 2;
        }
        System.out.println(order.toString());
        StringBuilder sb = new StringBuilder();
        for (int i = order.size()-1; i >= 0; i--) {
            sb.append(order.get(i));
        }
        int answer = Integer.parseInt(sb.toString());
        return answer;
    }



//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/557cd6882bfa3c8a9f0000c1
     * Failing if person is older than 9 years. :)
     * @param herOld
     * @return
     */

    public static int howOld(final String herOld) {
    char first = herOld.charAt(0);
    return Character.getNumericValue(first);
    }




// ---------------------------------------------------------------------------------------------------------------------------------------
    /**
     *https://www.codewars.com/kata/59ccf051dcc4050f7800008f
     * @param start
     * @param limit
     * @return
     */

    public static String buddy(long start, long limit) {

        // Calculate all possible sum of divisors between start and limits.
        long start2 = start;
        HashMap<Long, Long> values= new HashMap<>();
        while (start <= limit) {
            long sum = countSum(start);
            values.put(start, sum);
            start++;
        }

        // Start to compare what is the max pair where (key, value) are a pair of buddy if s(key) = value + 1 and s(value) = key + 1
        for (long i = start2; i < limit; i++) {
            Long currentValue = values.get(i);
            Long comparableKey = currentValue - 1;
            Long comparableValue = countSum(comparableKey);
            if (comparableValue-1 == i) {
                if (i < comparableKey) {
                    String answer = "(" + i + " " + comparableKey +")";
                    return answer;
                } else {
                    continue;
                }
            }
        }
        return "Nothing";
    }
//---------------------------------------------------------------------------------------------------------------------------------------


    /**
     * calculate sum of divisors for one element
     * @param number
     * @return
     */

    public static Long countSum (Long number) {
        long result = 0;
        for (long i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                if (i == (number / i))
                    result += i;
                else
                    result += (i + number / i);
            }
        }
        return (result + 1);
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Given a Divisor and a Bound , Find the largest integer N , Such That
     * Explanation:
     * (50) is divisible by (10) , (50) is less than or equal to bound (50) , and (50) is > 0 .*
     * @param divisor
     * @param bound
     * @return
     */
    public static int maxMultiple(int divisor, int bound) {
        while (bound % divisor != 0) {
            bound--;
        }
        return bound;
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Given a list lst and a number N, create a new list that contains each number of lst at most N times without reordering.
     * For example if N = 2, and the input is [1,2,3,1,2,1,2,3], you take [1,2,3,1,2],
     * drop the next [1,2] since this would lead to 1 and 2 being in the result 3 times, and then take 3, which leads to [1,2,3,1,2,3].
     *
     * @param elements
     * @param maxOccurrences
     * @return
     */
    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        List<Integer> correctLst = new ArrayList<Integer>();
        HashMap<Integer, Integer> elementCount = new HashMap<Integer, Integer>();

        /// a loop to count how many of each elements is represented in the list elements and limit it with maxOccurrences
        for (int element : elements) {
            int count = 0;
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == element) {
                    if (count < maxOccurrences) {
                        count++;
                    }
                }
            }
            if (!elementCount.containsKey(element)) {
                elementCount.put(element, count);
            }
        }

        // looping second time and adding number to new list if hashmap key value is bigger than 0.
        for (int element : elements) {
            if (elementCount.get(element) > 0) {
                correctLst.add(element);
                elementCount.replace(element, elementCount.get(element) - 1);
            }
        }
        // converting to correct return type Integer to int. ????????
        int [] answer = correctLst.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Complete the method/function so that it converts dash/underscore delimited words into camel casing.
     * The first word within the output should be capitalized only if the original word was capitalized
     * (known as Upper Camel Case, also often referred to as Pascal case).
     *
     * @param s
     * @return
     */
    static String toCamelCase(String s) {
        if (s.length() == 0) {
            return s;
        }
        String[] newPhrase = s.split("[_-]");
        ArrayList<String> finalPhrase = new ArrayList<>();
        char first = newPhrase[0].charAt(0);
        int length = newPhrase.length;
        if (Character.isLowerCase(first)) {
            finalPhrase.add(newPhrase[0]);
            for (int i = 1; i < length; i++) {
                finalPhrase.add(newPhrase[i].substring(0, 1).toUpperCase() + newPhrase[i].substring(1, newPhrase[i].length()));
            }
        } else {
            for (String word : newPhrase) {
                finalPhrase.add(word.substring(0, 1).toUpperCase() + word.substring(1, word.length()));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String word : finalPhrase) {
            sb = sb.append(word);
        }
        return sb.toString();
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Capitalize each word first letter
     *
     * @param phrase string
     * @return string where each word is capitalized
     */
    public String toJadenCase(String phrase) {
        if (phrase == null) {
            return null;
        } else if (phrase == "") {
            return null;
        } else {
            ArrayList<String> currentPhrase = new ArrayList<>();
            String[] splits = phrase.split(" ");
            for (String word : splits) {
                int length = word.length();
                currentPhrase.add(word.substring(0, 1).toUpperCase() + word.substring(1, length));
            }
            StringBuilder sb = new StringBuilder();
            for (String capWord : currentPhrase) {
                sb.append(capWord).append(" ");
            }
            String phraseWithSpace = sb.toString();
            String correctPhrase = phraseWithSpace.substring(0, phraseWithSpace.length() - 1);
            return correctPhrase;
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * You are given an array with positive numbers and a non-negative number N.
     * You should find the N-th power of the element in the array with the index N.
     * If N is outside of the array, then return -1. Don't forget that the first element has the index 0.
     *
     * @param array of numbers
     * @param n     what number and power number
     * @return
     */
    public static int nthPower(int[] array, int n) {
        int length = array.length;
        if (n > length - 1) {
            return -1;
        } else {
            double a = array[n];
            double b = n;
            int answer = (int) Math.pow(a, b);
            return answer;
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * https://www.codewars.com/kata/5b73fe9fb3d9776fbf00009e
     * Your task is to sum the differences between consecutive pairs in the array in descending order.
     * @param arr
     * @return
     */
    public static int sumOfDifferences(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }
        int[] sorted = sortMaxToMin(arr);
        int sum = 0;
        for (int i = 0; i < arr.length-1; i++) {
            sum = sum + (sorted[i] -sorted[i+1]);
        }

        return sum;
    }

    public static int[] sortMaxToMin(int[] a) {
        int arrayLength = a.length;
        for (int i = 0; i < arrayLength; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (a[i] < a[j]) {
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
        return a;
    }

}
