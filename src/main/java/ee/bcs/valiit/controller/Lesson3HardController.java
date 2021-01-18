package ee.bcs.valiit.controller;
import ee.bcs.valiit.tasks.Lesson3;
import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.web.bind.annotation.*;

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

}
