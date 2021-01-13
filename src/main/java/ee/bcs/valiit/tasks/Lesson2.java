package ee.bcs.valiit.tasks;

import javassist.expr.NewArray;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Lesson2 {

    public static void main(String[] args) {
        // exercise1(20);
        // exercise2(20);
        // exercise3( 4, 5);
        // System.out.println(exercise4(7));
        // System.out.println(nthPower(new int[] {3,1,2}, 1));
        // System.out.println(toCamelCase("the-Stealth-Warrior"));
        // System.out.println(cycleLength(22));
        // exercise5(900,1000);
        // deleteNth(new int[]{1, 1, 3, 3, 7, 2, 2, 2, 2}, 3);
        // System.out.println(maxMultiple(37, 200));
        System.out.println(buddy(381, 4318));
    }

    /**
     * loo 10 elemendile täisarvude massiv
     * loe sisse konsoolist 10 täisarvu
     * loe sisse konsoolist 10 täisarvu
     */

    public static void exercise1() {
        System.out.println("Exercise 1");
        int massiv[] = new int[10];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int x = i + 1;
            System.out.println("Please insert " + x + " of 10 integer");
            int number = scanner.nextInt();
            massiv[i] = number;
        }

        System.out.println("Numbers in opposite order are: ");

        for (int i = 9; i > -1; i--) {
            System.out.println(massiv[i]);
        }
    }

    /**
     * prindi välja x esimest paaris arvu
     * SISEND 5
     * Väljund 2 4 6 8 10
     *
     * @param x INTEGER
     */

    public static void exercise2(int x) {
        int count = 0;
        for (int i = 1; count < x; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                count++;
            }
        }
    }

    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee
    public static void exercise3(int x, int y) {
        int[][] table = new int[x + 1][y + 1];
        for (int i = 1; i < x + 1; i++) {
            for (int j = 1; j < y + 1; j++) {
                table[i][j] = i * j;
                System.out.printf("%4d", table[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
     *
     * @param n mitmendat liiget vaja
     * @return Tagasta fibonacci jada n element
     */

    public static int exercise4(int n) {
        int[] fibList = new int[n];
        fibList[0] = 0;
        fibList[1] = 1;
        for (int i = 2; i < n; i++) {
            fibList[i] = fibList[i - 1] + fibList[i - 2];
        }
        return fibList[n - 1];
    }

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

    /**
     * mis leiab 3n+1 sequenci pikkuse, kui on paaris / 2 kui on paaritu *3+1
     *
     * @param n integer
     * @return integer what is the length
     */
    public static int cycleLength(int n) {
        int count = 0;
        while (n > 0) {
            if (n == 1) {
                count++;
                break;
            } else if (n % 2 == 0) {
                n = n / 2;
                count++;
            } else {
                n = 3 * n + 1;
                count++;
            }
        }
        return count;
    }

    /**
     * https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
     * TODO 1 (tee alamfunktsioon) mis leiab 3n+1 sequenci pikkuse
     * kui on paaris / 2 kui on paaritu *3+1
     * TODO 2 tee tsükkel mis leiab i -> j kõige suurema tsükkli pikkuse
     *
     * @param i
     * @param j
     */

    public static void exercise5(int i, int j) {
        int start = i;
        int fin = j;
        ArrayList<Integer> cycleLengths = new ArrayList<Integer>();
        for (int x = i; i <= j; i++) {
            int length = cycleLength(i);
            cycleLengths.add(length);
        }
        int answer = Collections.max(cycleLengths);
        System.out.printf("%-7d" + "%-7d" + "%-7d", start, fin, answer);
    }

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

    /**
     *https://www.codewars.com/kata/59ccf051dcc4050f7800008f
     * @param start
     * @param limit
     * @return
     */

    public static String buddy(long start, long limit) {
        long start2 = start;
        HashMap<Long, Long> values= new HashMap<>();
        while (start <= limit) {
            long sum = countSum(start);
            values.put(start, sum);
            start++;
        }

        // Start to compare what is the max pair where (key, value) are a pair of buddy if s(key) = value + 1 and s(value) = key + 1
        for (long i = limit; i > start2; i--) {
            Long currentValue = values.get(i);
            Long comparableKey = currentValue - 1;
            Long comparableValue = countSum(comparableKey);
            if (comparableValue-1 == i) {
                System.out.println(i);
            }



        }
        return "Nothing";
    }

    public static Long countSum (Long number) {
        long sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }
}
