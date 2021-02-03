package ee.bcs.valiit.tasks;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyBankServices {

    String noSuchAccount = "No such account in system";
    String haveToPositive = "Amount have to be positive";
    String notEnoughMoney = "Not enough money on account";

    @Autowired
    private MyBankRepository myBankRepository;

    public static String menu() {
        return "Please enter a command: " + "<br>" +
                "1. createAccount" + "<br>" +
                "2. getBalance" + "<br>" +
                "3. depositMoney" + "<br>" +
                "4. withdrawMoney" + "<br>" +
                "5. transfer" + "<br>" +
                "6. exit";

    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyBankResponse createCustomer(MyBankCustomer myBankCustomer) {
        int validation = myBankRepository.validateCustomer(myBankCustomer.getSocialNumber());
        MyBankResponse response = new MyBankResponse();
        if (validation == 0) {
            myBankCustomer.setPassword(passwordEncoder.encode(myBankCustomer.getPassword()));
            myBankRepository.createCustomer(myBankCustomer);
            response.setAnswer("Customer created");
            return response;
        } else {
            response.setAnswer("Customer have already account!");
            return response;
        }
    }

    public MyBankResponse createAccount(int customerId) {
        String accountNr = buildAccountNumber();
        MyBankResponse response = new MyBankResponse();
        myBankRepository.createAccount(accountNr, customerId);
        response.setAnswer("Account number: " + accountNr + " is created");
        response.setCustomerId(customerId);
        return response;
    }

    public String getBalance(String accountNr) {
        BigDecimal balance = myBankRepository.getBalance(accountNr);
        if (balance == null) {
            throw new MyBankException("No such account number");
        }
        return "Balance for account: " + accountNr + " : " + balance;
    }

    public void depositMoney(MyBankTransaction myBankTransaction) {
        boolean isPositive = isPositive(myBankTransaction.getSum());
        if (!isPositive) {
            throw new MyBankException(haveToPositive);
        }
        int accountValidation = myBankRepository.validateAccount(myBankTransaction.getAccountTo());
        if (accountValidation ==  0) {
            throw new MyBankException(noSuchAccount);
        }
        myBankTransaction.setType(1);
        BigDecimal balance = myBankRepository.getBalance(myBankTransaction.getAccountTo());
        BigDecimal newBalance = balance.add(myBankTransaction.getSum());
        myBankRepository.updateBalance(myBankTransaction.getAccountTo(), newBalance);
        myBankRepository.saveTransaction(myBankTransaction);
        System.out.println("Transaction complete");
    }

    public String withdrawMoney(MyBankTransaction myBankTransaction) {
        boolean isSumPositive = isPositive(myBankTransaction.getSum());
        if(!isSumPositive) {
            throw new MyBankException(haveToPositive);
        }
        int accountValidation = myBankRepository.validateAccount(myBankTransaction.getAccountFrom());
        if (accountValidation == 0) {
            throw new MyBankException(noSuchAccount);
        }
        boolean isEnoughMoney = isEnoughMoney(myBankTransaction.getAccountFrom(), myBankTransaction.getSum());
        if (!isEnoughMoney) {
            throw new MyBankException(notEnoughMoney);
        }
        myBankTransaction.setType(2);
        BigDecimal balance = myBankRepository.getBalance(myBankTransaction.getAccountFrom());
        BigDecimal newBalance = balance.subtract(myBankTransaction.getSum());
        myBankRepository.updateBalance(myBankTransaction.getAccountFrom(), newBalance);
        myBankRepository.saveTransaction(myBankTransaction);
        return "Transaction completed";
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

    @Transactional
    public String transferMoney(MyBankTransaction myBankTransaction) {
        boolean isSumPositive = isPositive(myBankTransaction.getSum());
        if (!isSumPositive ) {
            throw new MyBankException(haveToPositive);
        }
        int accountFromValidation = myBankRepository.validateAccount(myBankTransaction.getAccountFrom());
        int accountToValidation = myBankRepository.validateAccount(myBankTransaction.getAccountTo());
        if (accountFromValidation == 0 || accountToValidation == 0) {
            throw new MyBankException(noSuchAccount);
        }
        Boolean isEnoughMoney = isEnoughMoney(myBankTransaction.getAccountFrom(), myBankTransaction.getSum());
        if (!isEnoughMoney) {
            throw new MyBankException(notEnoughMoney);
        }
        myBankTransaction.setType(3);
        BigDecimal balanceFrom = myBankRepository.getBalance(myBankTransaction.getAccountFrom());
        BigDecimal newBalanceFrom = balanceFrom.subtract(myBankTransaction.getSum());
        BigDecimal balanceTo = myBankRepository.getBalance(myBankTransaction.getAccountTo());
        BigDecimal newBalanceTo = balanceTo.add(myBankTransaction.getSum());
        myBankRepository.updateBalance(myBankTransaction.getAccountFrom(), newBalanceFrom);
        myBankRepository.updateBalance(myBankTransaction.getAccountTo(), newBalanceTo);
        myBankRepository.saveTransaction(myBankTransaction);
        return " Transaction compete";
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
                "<td><b>Type</b></td>" +
                "<td><b>Sum</b></td>" +
                "</tr>");
        for (MyBankTransaction transaction : allTransactions) {
            String row = "<tr>" +
                    "<td>" + transaction.getTransactionID() + "</td>" +
                    "<td>" + transaction.getDatetime() + "</td>" +
                    "<td>" + replaceBankAccount(transaction.getAccountFrom()) + "</td>" +
                    "<td>" + replaceBankAccount(transaction.getAccountTo()) + "</td>" +
                    "<td>" + transaction.getType() + "</td>" +
                    "<td>" + transaction.getSum() + "</td>" +
                    "</tr>";
            sb.append(row);
        }
        return sb.toString();
    }

    /**
     * replace bank account with blank string on all transactions. Like deposit and withdraw.
     */
    public String replaceBankAccount(String account) {
        if (account.equals("EE1")) {
            account = "";
            return account;
        } else {
            return account;
        }
    }

    @Autowired
    private MyBankEntityAccountRepository hibernateRepository;

//  TODO: Throws exceptions puua kinni

    public String getAccount (int nr) {
        MyBankEntityAccount account = hibernateRepository.getOne(nr);
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1 cellspacing=1 cellpadding=2 style='font-family:Arial;font-size:12'>" +
                "<tr>" +
                "<td><b>Account ID</b></td>" +
                "<td>"+ account.getId() +"</td>" +
                "</tr>" +
                "<td><b>Account NR</b></td>" +
                "<td>"+ account.getAccountNumber() +"</td>" +
                "</tr>" +
                "<td><b>Account owner</b></td>" +
                "<td>"+ account.getCustomer().getName() +"</td>" +
                "</tr>" +
                "<td><b>Balance</b></td>" +
                "<td>"+ account.getAccountBalance() +"</td>" +
                "</tr>");
        return sb.toString();
    }

    @Autowired
    private MyBankEntityAccountRepository hibernateAccountRepository;

    public List<MyBankAccount> ownerAccounts(int customerId) {
        List<MyBankAccount> customerAccounts = new ArrayList<>();
        List<MyBankEntityAccount> accounts = hibernateAccountRepository.findByCustomer_UserId(customerId);
        for (MyBankEntityAccount account : accounts) {
            MyBankAccount customerAccount = new MyBankAccount();
            customerAccount.setAccountNumber(account.getAccountNumber());
            customerAccount.setAccountBalance(account.getAccountBalance());
            customerAccounts.add(customerAccount);
        }
        return customerAccounts;
    }

    @Autowired
    private MyBankEntityTransactionRepository hibernateTransactionRepository;

    public MyBankTransaction mapTransaction (MyBankEntityTransaction transaction) {
        MyBankTransaction mappedTransaction = new MyBankTransaction();
        mappedTransaction.setTransactionID(transaction.getTransferId());
        mappedTransaction.setAccountFrom(transaction.getAccountFrom().getAccountNumber());
        mappedTransaction.setAccountTo(transaction.getAccountTo().getAccountNumber());
        mappedTransaction.setTypeName(transaction.getType().getTypeName());
        mappedTransaction.setSum(transaction.getSum());
        mappedTransaction.setDatetime(transaction.getDateTime());
        return mappedTransaction;
    }



    public String oneTransaction (int transactionId) {
        MyBankEntityTransaction transaction = hibernateTransactionRepository.getOne(transactionId);
        MyBankTransaction mapTransaction = mapTransaction(transaction);
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1 cellspacing=1 cellpadding=2 style='font-family:Arial;font-size:12'>" +
                "<tr>" +
                "<td><b>Transaction ID</b></td>" +
                "<td><b>Account From</b></td>" +
                "<td><b>Account To</b></td>" +
                "<td><b>Sum</b></td>" +
                "<td><b>Type</b></td>" +
                "<td><b>Date Time</b></td>" +
                "</tr>" +
                "<td>" + mapTransaction.getTransactionID() + "</td>" +
                "<td>" + mapTransaction.getAccountFrom() + "</td>" +
                "<td>" + mapTransaction.getAccountTo() + "</td>" +
                "<td>" + mapTransaction.getSum() + "</td>" +
                "<td>" + mapTransaction.getTypeName() + "</td>" +
                "<td>" + mapTransaction.getDatetime() + "</td>");
        return sb.toString();
    }

    @Autowired
    private MyBankEntityCustomerRespository hibernateCustomerResporitory;

    private MyBankCustomer mapCustomer(MyBankEntityCustomer entityCustomer) {
        MyBankCustomer customer = new MyBankCustomer();
        customer.setId(entityCustomer.getUserId());
        customer.setCustomerName(entityCustomer.getName());
        customer.setPassword(entityCustomer.getPassword());
        customer.setSocialNumber(entityCustomer.getSocialNumber());
        return customer;
    }

    public MyBankResponse logIn(MyBankCustomer myBankCustomerLogIn) {
        MyBankResponse myBankResponse = new MyBankResponse();
        try {
            MyBankEntityCustomer customer = hibernateCustomerResporitory.findByName(myBankCustomerLogIn.getCustomerName());
            MyBankCustomer fromDbCustomer = mapCustomer(customer);
            if (fromDbCustomer.getPassword().equals(myBankCustomerLogIn.getPassword())) {
                myBankResponse.setAnswer("Login success");
                myBankResponse.setCustomerId(fromDbCustomer.getId());
            } else {
                myBankResponse.setAnswer("Password or Username wrong");
            }
            return myBankResponse;
        }   catch (Exception e) {
                myBankResponse.setAnswer("Password or Username wrong");
        }
        return myBankResponse;
    }

    public MyBankResponse getName(int customerId) {
        MyBankResponse answer = new MyBankResponse();
        try {
            MyBankEntityCustomer customer = hibernateCustomerResporitory.getOne(customerId);
            MyBankCustomer actualcustomer = mapCustomer(customer);
            answer.setAnswer(actualcustomer.getCustomerName());
            return answer;
        } catch (Exception e) {
            answer.setAnswer("Something went wrong");
        }
        return answer;
    }

    public List<MyBankCustomer> allCustomers() {
        List<MyBankEntityCustomer> customersFromEntity = hibernateCustomerResporitory.findAll();;
        List<MyBankCustomer> customers = new ArrayList<>();
        for (MyBankEntityCustomer myBankEntityCustomer : customersFromEntity) {
            MyBankCustomer customer = new MyBankCustomer();
            customer.setUserName(myBankEntityCustomer.getUserName());
            customer.setId(myBankEntityCustomer.getUserId());
            customers.add(customer);
        }
        return customers;
    }
}



