package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping ("test/{name}")
    public String getHelloWorld(@PathVariable("name") String name, @RequestParam("name") String lastName){
        return "Hello " + name + " " + lastName;
    }
}
