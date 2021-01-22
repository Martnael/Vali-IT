package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Account;
import ee.bcs.valiit.tasks.Customer;
import ee.bcs.valiit.tasks.Lesson4ContVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("solutions/bank")
@RestController

public class Lesson4controller {

    @Autowired
    private NamedParameterJdbcTemplate template;


    @GetMapping("/{menu}")
    public String menu(@PathVariable("menu") String text) {
        return Lesson4ContVersion.menu();
    }

    @PostMapping ("createaccount")
    public String createAccount(@RequestBody Customer customer, @RequestParam("accountnr") String accountNr) {
        int validation = Lesson4ContVersion.validateCustomer(customer.getSocialNumber(), template);
        if (validation == 0) {
            Lesson4ContVersion.createCustomer(customer, template);
            int customerID = Lesson4ContVersion.getCustomerID(customer.getSocialNumber(), template);
            Lesson4ContVersion.createAccount(accountNr, customerID, template);
            return "Client and customer created";
        } else {

            return "Account created";
        }
    }

    @GetMapping("getbalance")
    public String getBalance(@RequestParam("accountnr") String accountNr) {
        int accountValidation = Lesson4ContVersion.validateAccount(accountNr, template);
        if (accountValidation == 1) {
            BigDecimal balance = Lesson4ContVersion.getBalance(accountNr, template);
            return "Balance for account: " + accountNr + " : " + balance;
        } else {
            return "No such account";
        }
    }

//    @GetMapping ("all")
//    public String showAll() {
//        return accountMap.toString();
//    }
//
//    @GetMapping ("getbalance")
//    public String getBalance(@RequestParam("accountnr") String accountNr) {
//    return Lesson4ContVersion.getBalance(accountNr, accountMap);
//    }
//
//    @PostMapping ("depositmoney")
//    public String depositMoney(@RequestBody String accountNr, String depositSum) {
//        return Lesson4ContVersion.depositMoney(accountNr, depositSum, accountMap);
//    }
//
//    @PostMapping ("withdrawmoney")
//    public String withdrawMoney (@RequestBody String accountNr, String depositSum) {
//        return Lesson4ContVersion.withdrawMoney(accountNr, depositSum, accountMap);
//    }
//
//    @GetMapping ("transfermoney")
//    public String transferMoney ( @RequestParam("accountto") String accountTo, @RequestParam("accountfrom") String accountFrom,@RequestParam("sum") String sum) {
//        return Lesson4ContVersion.transferMoney(accountFrom, accountTo, sum, accountBalanceMap);
//    }

}
