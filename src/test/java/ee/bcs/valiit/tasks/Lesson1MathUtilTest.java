package ee.bcs.valiit.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Lesson1MathUtilTest {

    @Test
    void min() {
        assertEquals(3, Lesson1MathUtil.min(3,5));
        assertEquals(-5, Lesson1MathUtil.min(3,-5));
        assertEquals(0, Lesson1MathUtil.min(0,0));
    }

    @Test
    void max() {
        assertEquals(3, Lesson1MathUtil.max(1, 3));
        assertEquals(5, Lesson1MathUtil.max(5, 3));
        assertEquals(5, Lesson1MathUtil.max(5, 5));
    }

    @Test
    void abs() {
        assertEquals(4, Lesson1MathUtil.abs(4));
        assertEquals(4, Lesson1MathUtil.abs(-4));
        assertEquals(0, Lesson1MathUtil.abs(0));
    }

    @Test
    void isEven() {
       assertTrue(Lesson1MathUtil.isEven(6));
       assertFalse(Lesson1MathUtil.isEven(5));
       assertTrue(Lesson1MathUtil.isEven(0));
    }

    @Test
    void testMin() {
        assertEquals(3, Lesson1MathUtil.min(3,5,9));
        assertEquals(3, Lesson1MathUtil.min(5,3,9));
        assertEquals(3, Lesson1MathUtil.min(5,10,3));
        assertEquals(-10, Lesson1MathUtil.min(5,-10,3));
        assertEquals(3, Lesson1MathUtil.min(3,3,3));
    }

    @Test
    void testMax() {
        assertEquals(5, Lesson1MathUtil.max(5,1,3));
        assertEquals(5, Lesson1MathUtil.max(1,5,3));
        assertEquals(5, Lesson1MathUtil.max(3,1,5));
    }
}