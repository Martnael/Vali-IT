package ee.bcs.valiit.controller;


import ee.bcs.valiit.tasks.Lesson2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("solution/lesson2")
@RestController

public class Lesson2Controller {

        @GetMapping("fib")
        public int fib (@RequestParam("a") int a ) {
            return ee.bcs.valiit.tasks.Lesson2.exercise4(a);
        }

        @GetMapping("table")
        @ResponseStatus(value = HttpStatus.OK)
        public void exercise2 (@RequestParam("a") int a){
            Lesson2.exercise2(a);

        }

        @GetMapping("cyclelength")
        public void cycleLength(@RequestParam("a") int a) {
            Lesson2.exercise2(a);

        }

}
