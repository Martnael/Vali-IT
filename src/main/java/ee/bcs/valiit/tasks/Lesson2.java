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
        // exercise5(900,1000);
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

}
