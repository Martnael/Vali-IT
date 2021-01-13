package ee.bcs.valiit.tasks;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Lesson3Hard {

    /**
     * // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
     * @param x
     * @return
     */
    public static int evenFibonacci(int x) {
        int[] fibList = new int[x];
        int sum = 0;
        fibList[0] = 0;
        fibList[1] = 1;
        for (int i = 2; i < x; i++) {
            fibList[i] = fibList[i - 1] + fibList[i - 2];
        }
        for (int i : fibList) {
            if ( i % 2 == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }

    /**
     * TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
     * iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
     * ja kasutaja peab saama uuesti arvata
     * numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
     */
    public static void randomGame() {
        Random random = new Random();
        int i = random.nextInt(100);
        Scanner scanner = new Scanner(System.in);
        int count = 10;
        while (count > 0) {
            System.out.println("You have " + count + " tries left");
            System.out.println("Please enter a number: ");
            int guess = scanner.nextInt();
            if (guess > i) {
                System.out.println("Number is smaller");
                count--;
            } else if (guess < i) {
                System.out.println("Number is bigger");
                count--;
            } else {
                System.out.println("Great! You have guessed number correctly.");
                break;
            }
        }
        if (count == 0) {
            System.out.println("Game over");
            System.out.println("Correct number: " + i);
        }
    }


    /**
     * Translate to Morse
     *
     * @param text
     * @return
     */
    public static String morseCode(String text) {
        HashMap<String, String> pattern = new HashMap<>();
        pattern.put("a", ".-");
        pattern.put("b", "-...");
        pattern.put("c", "-.-.");
        pattern.put("d", "-..");
        pattern.put("e", ".");
        pattern.put("f", "..-.");
        pattern.put("g", "--.");
        pattern.put("h", "....");
        pattern.put("i", "..");
        pattern.put("j", ".---");
        pattern.put("k", "-.-");
        pattern.put("l", ".-..");
        pattern.put("m", "--");
        pattern.put("n", "-.");
        pattern.put("o", "---");
        pattern.put("p", ".--.");
        pattern.put("q", "--.-");
        pattern.put("r", ".-.");
        pattern.put("s", "...");
        pattern.put("t", "-");
        pattern.put("u", "..-");
        pattern.put("v", "...-");
        pattern.put("w", ".--");
        pattern.put("x", "-..-");
        pattern.put("y", "-.--");
        pattern.put("z", "--..");
        pattern.put(" ", " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(pattern.get(String.valueOf(text.charAt(i))));
        }
        return sb.toString();
    }
}
