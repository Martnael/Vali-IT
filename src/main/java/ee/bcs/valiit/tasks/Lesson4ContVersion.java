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

    public static String depositMoney(String account, String sum, HashMap<String, BigDecimal> accountBalanceMap) {
        if (existBankAccount(account, accountBalanceMap) && isPositive(sum)) {
            BigDecimal add = new BigDecimal(sum);
            BigDecimal newBalance = accountBalanceMap.get(account).add(add);
            accountBalanceMap.put(account, newBalance);
            String answer = "Balance for account: " + account + "<br>" + accountBalanceMap.get(account).toString();
            return answer;
        } else {
            String answer = "We dont have such account or you try to insert negative amount of money to account";
            return answer;
        }
    }




}