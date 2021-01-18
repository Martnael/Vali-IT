package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("solution/mathutil")
@RestController

public class Lesson1MathUtilController {
    @GetMapping("min")
    public int min(@RequestParam("a") int a, @RequestParam("b") int b) {
        return ee.bcs.valiit.tasks.Lesson1MathUtil.min(a,b);
    }

    @GetMapping("max")
    public int max(@RequestParam("a") int a, @RequestParam("b") int b) {
        return ee.bcs.valiit.tasks.Lesson1MathUtil.max(a,b);
    }

    @GetMapping("abs")
    public int abs(@RequestParam("a") int a) {
        return ee.bcs.valiit.tasks.Lesson1MathUtil.abs(a);
    }

    @GetMapping("iseven/{a}")
    public boolean isEven(@PathVariable("a") int a) {
        return ee.bcs.valiit.tasks.Lesson1MathUtil.isEven(a);
    }

    @GetMapping("minmin")
    public int minMin(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        return ee.bcs.valiit.tasks.Lesson1MathUtil.min(a, b, c);
    }

    @GetMapping("maxmax")
    public int maxMax(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c) {
        return ee.bcs.valiit.tasks.Lesson1MathUtil.max(a, b, c);
    }

}
