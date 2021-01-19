package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson4ContVersion;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RequestMapping("solution/lesson4")
@RestController

public class Lesson4controller {

    static HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();

    @GetMapping("/{menu}")
    public String morseCode(@PathVariable("menu") String text) {
        return Lesson4ContVersion.menu();
    }

    @GetMapping ("createaccount")
    public String createAccount(@RequestParam("x") String x) {
        return Lesson4ContVersion.createAccount(x, accountBalanceMap);
    }

    @GetMapping ("getbalance")
    public String getBalance(@RequestParam("x") String x) {
        return Lesson4ContVersion.getBalance(x, accountBalanceMap);
    }

    @GetMapping ("depositmoney")
    public String depositMoney(@RequestParam("account") String account, @RequestParam("sum") String sum) {
        return Lesson4ContVersion.depositMoney(account, sum, accountBalanceMap);
    }
}
