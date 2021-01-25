package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.MyBankCustomer;
import ee.bcs.valiit.tasks.MyBankServices;
import ee.bcs.valiit.tasks.MyBankTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("solutions/bank")
@RestController

public class MyBankController {

    @Autowired
    private MyBankServices myBankServices;

    @GetMapping("/menu")
    public String menu() {
        return MyBankServices.menu();
    }

    @PostMapping("createaccount")
    public String createAccount(@RequestBody MyBankCustomer myBankCustomer, @RequestParam("accountnr") String accountNr) {
        return myBankServices.createAccount(myBankCustomer, accountNr);
    }

    @GetMapping("getbalance")
    public String getBalance(@RequestParam("accountnr") String accountNr) {
        return myBankServices.getBalance(accountNr);
    }

    @PostMapping("depositmoney")
    public String depositMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.depositMoney(myBankTransaction);
    }

    @PostMapping("withdrawmoney")
    public String withdrawMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.withdrawMoney(myBankTransaction);
    }

    @PostMapping("transfermoney")
    public String transferMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.transferMoney(myBankTransaction);
    }

    @GetMapping("/allaccounts")
    public String allAccount() {
        return myBankServices.printAccounts();
    }

    @GetMapping("/lastaccount")
    public String lastAccount () {
        return myBankServices.getLastAccountNr();
    }
}
