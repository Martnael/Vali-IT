package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("solutions/bank")
@RestController

public class MyBankController {

    @Autowired
    private MyBankServices myBankServices;

    @PostMapping("createcustomer")
    public MyBankResponse createCustomer(@RequestBody MyBankCustomer myBankCustomer) {
        return myBankServices.createCustomer(myBankCustomer);
    }

    @PostMapping("login")
    public MyBankResponse logIn (@RequestBody MyBankCustomer myBankCustomer) {
        return myBankServices.logIn(myBankCustomer);
    }

    @GetMapping("getname")
    public MyBankResponse getName(@RequestParam("id") int customerId) {
        return myBankServices.getName(customerId);
    }

    @GetMapping("getbalance")
    public String getBalance(@RequestParam("accountnr") String accountNr) {
        return myBankServices.getBalance(accountNr);
    }

    @PostMapping ("createaccount")
    public MyBankResponse createAccount(@RequestParam("id") int customerId) {
        return myBankServices.createAccount(customerId);
    }

    @GetMapping ("allcustomers")
    public List<MyBankCustomer> allCustomers() {
        return myBankServices.allCustomers();
    }







    @PutMapping("depositmoney")
    public void depositMoney(@RequestBody MyBankTransaction myBankTransaction) {
        myBankServices.depositMoney(myBankTransaction);
    }

    @PutMapping("withdrawmoney")
    public String withdrawMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.withdrawMoney(myBankTransaction);
    }

    @PostMapping("transfermoney")
    public String transferMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.transferMoney(myBankTransaction);
    }

    @GetMapping("customeraccounts")
    public List<MyBankAccount> ownerAccount(@RequestParam("id") int customerId) {
        return myBankServices.ownerAccounts(customerId);
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


    @GetMapping("/onetransaction")
    public String oneTransaction(@RequestParam("nr") int nr) {
        return myBankServices.oneTransaction(nr);
    }

}
