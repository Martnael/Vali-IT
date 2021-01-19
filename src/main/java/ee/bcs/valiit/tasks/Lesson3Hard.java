package ee.bcs.valiit.tasks;

import ee.bcs.valiit.controller.Lesson3HardController;

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
    public static String randomGame( int x, int randomNr, int tries) {

        if (x < randomNr) {
            String answer = "Number is bigger and you have " + tries + " left." + randomNr;
            return answer;
        } else if (x > randomNr) {
            String answer = "Number is smaller and you have " + tries + " left." + randomNr;
            return answer;
        }  else {
            String answer = " You have won! Correct number is " + x + " Total " + tries;
            return answer;
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
