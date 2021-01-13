package ee.bcs.valiit.tasks;

public class Lesson1MathUtil {
    private String test;

    // TODO kirjuta ise (if/else) ära kasuta java.lang.Math
    public Lesson1MathUtil(String test) {
        this.test = test;
    }

    public static void main(String[] args) {
        System.out.println(min(5,10));
        System.out.println(max(5,10));
        System.out.println(abs(100));
        System.out.println(isEven(10));
        System.out.println(min(3,4,5));
        System.out.println(max(5,8,7));
    }

    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
       if (a > b) {
           return a;
       } else {
           return b;
       }
    }

    public static int abs(int a) {
        // TODO tagasta a absoluut arv
       if ( a >= 0) {
           return a;
       } else {
           return 0-a;
       }
    }

    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        // tagasta false kui a on paaritu arv
        if (a % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int min(int a, int b, int c) {
        int min = min(a, b);
        return min(min, c);
    }

    public static int max(int a, int b, int c) {
        int max = max(a,b);
        return max(max, c);
    }
}
