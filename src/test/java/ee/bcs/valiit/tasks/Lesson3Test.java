package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class Lesson3Test {

    @Test
    void sum() {
        int[] test = new int[] {20,37,20,21};
        int actualSum = Lesson3.sum(test);
        int expectedSum = 98;
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void sumPerformanceTest() {
        int[] test = new int[] {20,37,20,21};
        assertTimeout(Duration.ofSeconds(1), () -> {
                    for (int i = 0; i < 100000; i++) {
                        int j = Lesson3.sum(test);
                    }

                }

        );
    }


    @Test
    void factorial() {
        int test = 10;
        int actualFactorial = Lesson3.factorial(10);
        int expected = 3628800;
        assertEquals(expected, actualFactorial);

    }

    @Test
    void sort() {
        int[] test = {20, 37, 15, 21};
        int[] actual = Lesson3.sort(test);
        int[] expected = {15, 20, 21, 37};
        assertArrayEquals(expected, actual);
    }

    @Test
    void reverseString() {
        String test = "Test";
        String actualReverseString = Lesson3.reverseString(test);
        String expectedString = "tseT";
        assertEquals(expectedString, actualReverseString);
    }

    @Test
    void isPrime() {
        boolean[] actual = {true, true, false, false};
        boolean[] expected = new boolean[4];
        int[] test = {2,11, 6, 10};
        for( int i = 0; i < actual.length; i++) {
            expected[i] = Lesson3.isPrime(test[i]) ;
        }
        assertArrayEquals(actual, expected);
    }
}