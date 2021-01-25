package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MyBankServices {

    @Autowired
    private MyBankRepository myBankRepository;

    public static String menu() {
        String answer = "Please enter a command: " + "<br>" +
                "1. createAccount" + "<br>" +
                "2. getBalance" + "<br>" +
                "3. depositMoney" + "<br>" +
                "4. withdrawMoney" + "<br>" +
                "5. transfer" + "<br>" +
                "6. exit";
        return answer;
    }

    public String createAccount(MyBankCustomer myBankCustomer) {
        int validation = myBankRepository.validateCustomer(myBankCustomer.getSocialNumber());
        String accountNr = buildAccountNumber();
        if (validation == 0) {
            myBankRepository.createCustomer(myBankCustomer);
            int customerID = myBankRepository.getCustomerID(myBankCustomer.getSocialNumber());
            myBankRepository.createAccount(accountNr, customerID);
            return "Client and customer created";
        } else {
            int customerID = myBankRepository.getCustomerID(myBankCustomer.getSocialNumber());
            myBankRepository.createAccount(accountNr, customerID);
            return "Account created ";
        }
    }

    public String getBalance(String accountNr) {
        int accountValidation = myBankRepository.validateAccount(accountNr);
        if (accountValidation == 1) {
            BigDecimal balance = myBankRepository.getBalance(accountNr);
            return "Balance for account: " + accountNr + " : " + balance;
        } else {
            return "No such account";
        }
    }

    public String depositMoney(MyBankTransaction myBankTransaction) {
        int accountValidation = myBankRepository.validateAccount(myBankTransaction.getAccountTo());
        boolean isPositive = isPositive(myBankTransaction.getSum());
        if (accountValidation > 0 && isPositive) {
            BigDecimal balance = myBankRepository.getBalance(myBankTransaction.getAccountTo());
            BigDecimal newBalance = balance.add(myBankTransaction.getSum());
            myBankRepository.updateBalance(myBankTransaction.getAccountTo(), newBalance);
            myBankRepository.saveTransaction(myBankTransaction);
            return "Transaction completed";
        } else if (accountValidation > 0 && !isPositive) {
            return "Sum have to be positive";
        } else {
            return "No such account";
        }
    }

    public String withdrawMoney(MyBankTransaction myBankTransaction) {
        int accountValidation = myBankRepository.validateAccount(myBankTransaction.getAccountFrom());
        boolean isSumPositive = isPositive(myBankTransaction.getSum());
        if (accountValidation > 0 && isSumPositive) {
            boolean isEnoughMoney = isEnoughMoney(myBankTransaction.getAccountFrom(), myBankTransaction.getSum());
            if (isEnoughMoney) {
                BigDecimal balance = myBankRepository.getBalance(myBankTransaction.getAccountFrom());
                BigDecimal newBalance = balance.subtract(myBankTransaction.getSum());
                myBankRepository.updateBalance(myBankTransaction.getAccountFrom(), newBalance);
                myBankRepository.saveTransaction(myBankTransaction);
                return "Transaction completed";
            } else
                return "Not enough money";
        } else if (!isSumPositive) {
            return "Sum have to be positive";
        } else {
            return "No such account";
        }
    }

    public String buildAccountNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append("EE");
        String lastAccountNr = myBankRepository.getLastAccountNr();
        int length = lastAccountNr.length();
        String newString = lastAccountNr.substring(2, length);
        int newAccountNr = 1 + Integer.parseInt(newString);
        String newNr = String.valueOf(newAccountNr);
        return sb.append(newNr).toString();
    }

    public boolean isPositive(BigDecimal sum) {
        return sum.compareTo(BigDecimal.ZERO) > 0;
    }

    public Boolean isEnoughMoney(String accountNr, BigDecimal sum) {
        BigDecimal balance = myBankRepository.getBalance(accountNr);
        return balance.compareTo(sum) >= 0;
    }

    public String transferMoney(MyBankTransaction myBankTransaction) {
        int accountFromValidation = myBankRepository.validateAccount(myBankTransaction.getAccountFrom());
        int accountToValidation = myBankRepository.validateAccount(myBankTransaction.getAccountTo());
        boolean isSumPositive = isPositive(myBankTransaction.getSum());
        if (accountFromValidation > 0 && accountToValidation > 0 && isSumPositive) {
            Boolean isEnoughMoney = isEnoughMoney(myBankTransaction.getAccountFrom(), myBankTransaction.getSum());
            if (isEnoughMoney) {
                BigDecimal balanceFrom = myBankRepository.getBalance(myBankTransaction.getAccountFrom());
                BigDecimal newBalanceFrom = balanceFrom.subtract(myBankTransaction.getSum());
                BigDecimal balanceTo = myBankRepository.getBalance(myBankTransaction.getAccountTo());
                BigDecimal newBalanceTo = balanceTo.add(myBankTransaction.getSum());
                myBankRepository.updateBalance(myBankTransaction.getAccountFrom(), newBalanceFrom);
                myBankRepository.updateBalance(myBankTransaction.getAccountTo(), newBalanceTo);
                myBankRepository.saveTransaction(myBankTransaction);
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


    public String printAccounts() {
        List<MyBankAccount> allAccounts = myBankRepository.allAccount();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1 cellspacing=1 cellpadding=2 style='font-family:Arial;font-size:12'>" +
                "<tr>" +
                "<td><b>Account Owner</b></td>" +
                "<td><b>Account Number</b></td>" +
                "<td><b>Balance</b></td>" +
                "</tr>");
        for (MyBankAccount account : allAccounts) {
            String row = "<tr>" +
                    "<td>" + account.getOwner() + "</td>" +
                    "<td>" + account.getAccountNumber() + "</td>" +
                    "<td>" + account.getAccountBalance() + "</td>" +
                    "</tr>";
            sb.append(row);
        }
        return sb.toString();
    }

    public String printAllTransfers() {
        List<MyBankTransaction> allTransactions = myBankRepository.allTransactions();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1 cellspacing=1 cellpadding=2 style='font-family:Arial;font-size:12'>" +
                "<tr>" +
                "<td><b>Transacation ID</b></td>" +
                "<td><b>Date</b></td>" +
                "<td><b>Account From</b></td>" +
                "<td><b>Account To</b></td>" +
                "<td><b>Sum</b></td>" +
                "</tr>");
        for (MyBankTransaction transaction : allTransactions) {
            String row = "<tr>" +
                    "<td>" + transaction.getTransactionID() + "</td>" +
                    "<td>" + transaction.getDatetime() + "</td>" +
                    "<td>" + transaction.getAccountFrom() + "</td>" +
                    "<td>" + transaction.getAccountTo() + "</td>" +
                    "<td>" + transaction.getSum() + "</td>" +
                    "</tr>";
            sb.append(row);
        }
        return sb.toString();
    }

}




