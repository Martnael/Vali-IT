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
        Lesson4ContVersion.createCustomer(customer, template);
        Lesson4ContVersion.createAccount(accountNr, template);
        return" Account created";
    }


//    @PostMapping ("createaccounttest")
//    public String createAccountTest(@RequestBody Account account) {
//        String accountNr = Lesson4ContVersion.buildAccountNumber(id);
//        String sql = "INSERT INTO bankaccounts (account_nr, holder_name, balance) " + "VALUES (:account_nr,:holder_name, :balance)";
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("account_nr", accountNr );
//        paramMap.put("holder_name", account.getAccountHolderName());
//        paramMap.put("balance", "500" );
//        template.update(sql, paramMap);
//        accountMap.put(accountNr, account);
//        id++;
//        return Lesson4ContVersion.createAccount(accountNr, account);
//    }
//
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
