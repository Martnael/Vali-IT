package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution/lesson3")
@RestController

public class Lesson3Controller {

    @GetMapping("sum")
    public int sum(@RequestParam("array") int[] a) {
        return ee.bcs.valiit.tasks.Lesson3.sum(a);
    }

    @GetMapping("factorial/{x}")
    public int factorial(@PathVariable("x") int x ) {
        return Lesson3.factorial(x);
    }

    @GetMapping("sort")
    public int[] sort(@RequestParam("array") int[] a) {
        return ee.bcs.valiit.tasks.Lesson3.sort(a);
    }

    @GetMapping("reversestring")
    public String reverseString(@RequestParam("x") String x) {
        return Lesson3.reverseString(x);
    }

    @GetMapping ("isprime/{x}")
    public boolean isPrime(@PathVariable("x") int x) {
        return Lesson3.isPrime(x);
    }

}
