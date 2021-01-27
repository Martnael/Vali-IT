package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.*;
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
    public void createAccount(@RequestBody MyBankCustomer myBankCustomer) {
        myBankServices.createAccount(myBankCustomer);
    }

    @GetMapping("getbalance")
    public void getBalance(@RequestParam("accountnr") String accountNr) {
        myBankServices.getBalance(accountNr);
    }

    @PostMapping("depositmoney")
    public void depositMoney(@RequestBody MyBankTransaction myBankTransaction) {
        myBankServices.depositMoney(myBankTransaction);
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
        return myBankServices.buildAccountNumber();
    }

    @GetMapping("/alltransactions")
    public String allTransactions () {
        return myBankServices.printAllTransfers();
    }

    @GetMapping("/oneaccount")
    public String getAccounts(@RequestParam("nr") int nr) {
        return myBankServices.getAccount(nr);
    }

    @GetMapping("/oneowner")
    public String oneOwner(@RequestParam("nr") int nr) {
        return myBankServices.ownerAccounts(nr);
    }
}
