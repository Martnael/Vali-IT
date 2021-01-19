package ee.bcs.valiit.controller;
import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequestMapping("solution/lesson3hard")
@RestController

public class Lesson3HardController {
    @GetMapping("evenFib")
    public int evenFib (@RequestParam("x") int x) {
        return ee.bcs.valiit.tasks.Lesson3Hard.evenFibonacci(x);
    }

    @GetMapping("morse/{text}")
    public String morseCode(@PathVariable("text") String text) {
        return Lesson3Hard.morseCode(text);
    }

    Random random = new Random();
    public int i = random.nextInt(100);
    public int count = 10;

    @GetMapping ("random")
    public String random (@RequestParam("x") int x) {
        count--;
        if (x < i) {
            String answer = "Number is bigger and you have " + count + " left.";
            return answer;
        } else if (x > i) {
            String answer = "Number is smaller and you have " + count + " left.";
            return answer;
        }  else {
            String answer = " You have won! Correct number is " + x + " Total " + count + "\n Game will start again with new guess.";
            count = 10;
            i = random.nextInt(100);
            return answer;
        }
    }
}
