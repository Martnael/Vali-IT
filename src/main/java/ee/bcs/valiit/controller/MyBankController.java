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

    @CrossOrigin
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

    @CrossOrigin
    @PostMapping ("createaccount")
    public MyBankResponse createAccount(@RequestParam("id") int customerId) {
        return myBankServices.createAccount(customerId);
    }

    @CrossOrigin
    @GetMapping ("allcustomers")
    public List<MyBankCustomer> allCustomers() {
        return myBankServices.allCustomers();
    }

    @CrossOrigin
    @PutMapping("depositmoney")
    public MyBankResponse depositMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.depositMoney(myBankTransaction);
    }

    @CrossOrigin
    @PutMapping("withdrawmoney")
    public MyBankResponse withdrawMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.withdrawMoney(myBankTransaction);
    }

    @CrossOrigin
    @PostMapping("transfermoney")
    public MyBankResponse transferMoney(@RequestBody MyBankTransaction myBankTransaction) {
        return myBankServices.transferMoney(myBankTransaction);
    }

    @CrossOrigin
    @GetMapping("customeraccounts")
    public List<MyBankAccount> ownerAccount(@RequestParam("id") int customerId) {
        return myBankServices.ownerAccounts(customerId);
    }

    @CrossOrigin
    @GetMapping("/allaccounts")
    public List<MyBankAccount> allAccount() {
        return myBankServices.allAccounts();
    }

    @GetMapping("/lastaccount")
    public String lastAccount () {
        return myBankServices.buildAccountNumber();
    }

    @CrossOrigin
    @GetMapping("/alltransactions")
    public List<MyBankTransaction> allTransactions () {
        return myBankServices.allTransactions();
    }

    @CrossOrigin
    @GetMapping("/transactionsbyaccount")
    public List<MyBankTransaction> transactionsByAccounts (@RequestParam("accountnr") String accountNr) {
        return myBankServices.transactionsByAccount(accountNr);
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
