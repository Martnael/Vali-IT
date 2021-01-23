package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Account;
import ee.bcs.valiit.tasks.Customer;
import ee.bcs.valiit.tasks.Lesson4ContVersion;
import ee.bcs.valiit.tasks.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("solutions/bank")
@RestController

public class Lesson4controller {

    @Autowired
    private NamedParameterJdbcTemplate template;


    @GetMapping("/menu")
    public String menu() {
        return Lesson4ContVersion.menu();
    }

    @PostMapping("createaccount")
    public String createAccount(@RequestBody Customer customer, @RequestParam("accountnr") String accountNr) {
        int validation = Lesson4ContVersion.validateCustomer(customer.getSocialNumber(), template);
        if (validation == 0) {
            Lesson4ContVersion.createCustomer(customer, template);
            int customerID = Lesson4ContVersion.getCustomerID(customer.getSocialNumber(), template);
            Lesson4ContVersion.createAccount(accountNr, customerID, template);
            return "Client and customer created";
        } else {
            int customerID = Lesson4ContVersion.getCustomerID(customer.getSocialNumber(), template);
            Lesson4ContVersion.createAccount(accountNr, customerID, template);
            return "Account created ";
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

    @PostMapping("depositmoney")
    public String depositMoney(@RequestBody Transfer transfer) {
        int accountValidation = Lesson4ContVersion.validateAccount(transfer.getAccountTo(), template);
        boolean isPositive = Lesson4ContVersion.isPositive(transfer.getSum());
        if (accountValidation > 0 && isPositive) {
            Lesson4ContVersion.depositMoney(transfer, template);
            Lesson4ContVersion.saveTransaction(transfer, template);
            return "Transaction completed";
        } else if (accountValidation > 0 && !isPositive) {
            return "Sum have to be positive";
        } else {
            return "No such account";
        }
    }

    @PostMapping("withdrawmoney")
    public String withdrawMoney(@RequestBody Transfer transfer) {
        int accountValidation = Lesson4ContVersion.validateAccount(transfer.getAccountFrom(), template);
        boolean isSumPositive = Lesson4ContVersion.isPositive(transfer.getSum());
        if (accountValidation > 0 && isSumPositive) {
            boolean isEnoughMoney = Lesson4ContVersion.isEnoughMoney(transfer.getAccountFrom(), transfer.getSum(), template);
            if (isEnoughMoney) {
                Lesson4ContVersion.withdrawMoney(transfer, template);
                Lesson4ContVersion.saveTransaction(transfer, template);
                return "Transaction completed";
            } else
                return "Not enough money";
        } else if (!isSumPositive) {
            return "Sum have to be positive";
        } else {
            return "No such account";
        }

    }

    @PostMapping("transfermoney")
    public String transferMoney(@RequestBody Transfer transfer) {
        int accountFromValidation = Lesson4ContVersion.validateAccount(transfer.getAccountFrom(), template);
        int accountToValidation = Lesson4ContVersion.validateAccount(transfer.getAccountTo(), template);
        boolean isSumPositive = Lesson4ContVersion.isPositive(transfer.getSum());
        if (accountFromValidation > 0 && accountToValidation > 0 && isSumPositive) {
            Boolean isEnoughMoney = Lesson4ContVersion.isEnoughMoney(transfer.getAccountFrom(), transfer.getSum(), template);
            if (isEnoughMoney) {
                Lesson4ContVersion.transferMoney(transfer, template);
                Lesson4ContVersion.saveTransaction(transfer, template);
                return "Transfer Completed";
            } else {
                return "Not enough money";
            }
        } else if (!isSumPositive) {
                return "Sum have to be positive";
        } else {
                return "Account number wrong";
        }
    }

    @GetMapping("/allaccounts")
    public String allAccount() {
        List<Account> result = Lesson4ContVersion.allAccount(template);
        return Lesson4ContVersion.printAccounts(result);
    }

}
