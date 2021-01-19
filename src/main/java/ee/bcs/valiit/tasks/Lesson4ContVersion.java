package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Lesson4ContVersion {

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

    public static String createAccount(String account, HashMap<String, BigDecimal> accountBalanceMap) {
        BigDecimal balance = new BigDecimal(0);
        boolean isAccountAlready = true;
        if (accountBalanceMap.containsKey(account)) {
            isAccountAlready = accountBalanceMap.containsKey(account);
            String answer = "This account number already exist. Please try again";
            return answer;
        } else {
            accountBalanceMap.put(account, balance);
            String answer = "Account created";
            return answer;
        }
    }

    public static String getBalance(String account, HashMap<String, BigDecimal> accountBalanceMap) {
        if (accountBalanceMap.containsKey(account)) {
            String balance = accountBalanceMap.get(account).toString();
            String answer = "Current balance for account: " + balance + " EUR";
            return answer;
        } else {
            String answer = "We dont have such account please!" + "<br>" + "Please try again";
            return answer;
        }
    }

    public static boolean existBankAccount(String bankAccount, HashMap<String, BigDecimal> accountBalanceMap) {
        boolean status = accountBalanceMap.containsKey(bankAccount);
        return status;
    }

    public static boolean isPositive(String value) {
        BigDecimal number = new BigDecimal(value);
        boolean answer = number.compareTo(BigDecimal.ZERO) > 0;
        return answer;
    }

    public static BigDecimal accountBalance(String account, HashMap<String, BigDecimal> accountBalanceMap) {
        return accountBalanceMap.get(account);
    }

    public static String depositMoney(String account, String sum, HashMap<String, BigDecimal> accountBalanceMap) {
        if (existBankAccount(account, accountBalanceMap) && isPositive(sum)) {
            BigDecimal add = new BigDecimal(sum);
            BigDecimal newBalance = accountBalanceMap.get(account).add(add);
            accountBalanceMap.put(account, newBalance);
            String answer = "Transfer complete. <br> Balance for account: " + account + "<br>" + accountBalanceMap.get(account).toString();
            return answer;
        } else {
            String answer = "We dont have such account or you try to insert negative amount of money to account";
            return answer;
        }
    }

    public static String withdrawMoney(String account, String sum, HashMap<String, BigDecimal> accountBalanceMap) {
        BigDecimal balance = accountBalance(account, accountBalanceMap);
        BigDecimal withdrawSum = new BigDecimal(sum);
        if (existBankAccount(account, accountBalanceMap) && isPositive(sum) && balance.compareTo(withdrawSum)>0) {
            BigDecimal newBalance = accountBalanceMap.get(account).subtract(withdrawSum);
            accountBalanceMap.put(account, newBalance);
            String answer = "Withdraw transaction completed.<br>Current balance for account: " + account + "        " + accountBalanceMap.get(account).toString();
            return answer;
        } else if (!isPositive(sum)) {
            String answer = "You can not withdraw negative amount from your account";
            return answer;
        } else if (balance.compareTo(withdrawSum)<0) {
            String answer = "You dont have so much money on your account!";
            return answer;
        } else {
            String answer = "Account number does not exist";
            return answer;
        }
    }

    public static String transferMoney (String accountFrom, String accountTo, String sum, HashMap<String, BigDecimal> accountBalanceMap ){
        BigDecimal accountBalanceFrom = accountBalance(accountFrom, accountBalanceMap);
        BigDecimal transferSum = new BigDecimal(sum);
        if (existBankAccount(accountFrom, accountBalanceMap) && existBankAccount(accountTo, accountBalanceMap) && isPositive(sum) && accountBalanceFrom.compareTo(transferSum)>0) {
            BigDecimal newBalanceFrom = accountBalanceMap.get(accountFrom).subtract(transferSum);
            accountBalanceMap.put(accountFrom, newBalanceFrom);
            BigDecimal newBalanceTo = accountBalanceMap.get(accountTo).add(transferSum);
            accountBalanceMap.put(accountTo, newBalanceTo);
            String answer = "Transfer completed.<br>New balance for account " + accountFrom + " :  " + accountBalanceMap.get(accountFrom).toString() + "<br>" +
                    "New balance for account: " + accountTo + " :  " + accountBalanceMap.get(accountTo).toString();
            return answer;
        } else if (!existBankAccount(accountFrom, accountBalanceMap)) {
            String answer = "Account from is wrong";
            return answer;
        } else if (!existBankAccount(accountTo, accountBalanceMap)) {
            String answer = "Account to is wrong";
            return answer;
        } else if (!isPositive(sum)) {
            String answer = "You can not transfer negative amount";
            return answer;
        } else if (accountBalanceFrom.compareTo(transferSum)<0) {
            String answer = "You dont have money to transfer";
            return answer;
        } else {
            String answer = "Something went terribly wrong";
            return answer;
        }
    }
}




