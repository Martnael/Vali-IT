package ee.bcs.valiit.tasks;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    static HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean status = true;
        while (status){
            System.out.println("Please enter a command: ");
            System.out.printf("%-3d createAccount\n", 1);
            System.out.printf("%-3d getBalance\n", 2);
            System.out.printf("%-3d depositMoney\n", 3);
            System.out.printf("%-3d withdrawMoney\n", 4);
            System.out.printf("%-3d transfer\n", 5);
            System.out.printf("%-3d exit\n", 6);

            String line = scanner.nextLine();

            switch (line) {
                case "createAccount":
                    createAccount(scanner, accountBalanceMap);
                    break;
                case "getBalance":
                        getBalance(scanner, accountBalanceMap);
                    break;
                case "depositMoney":
                    System.out.println("depositMoney");
                    break;
                case "withdrawMoney":
                    System.out.println("withdrawMoney");
                    break;
                case "transfer":
                    System.out.println("transfer");
                    break;
                case "exit":
                    System.out.println("exit");
                    status = false;
                    break;
                default:
                    System.out.println("Unknown command");
            }

            // TODO 1
            // Add command: "createAccount ${accountNr}"
            // this has to store accountNr with 0 balance
            // TODO 2
            // Add command: "getBalance ${accountNr}"
            // this has to display account balance of specific acount
            // TODO 3
            // Add command: "depositMoney ${accountNr} ${amount}
            // this has to add specified amount of money to account
            // You have to check that amount is positive number
            // TODO 4
            // Add command: "withdrawMoney ${accountNr} ${amount}
            // This has to remove specified amount of money from account
            // You have to check that amount is positive number
            // You may not allow this transaction if account balance would become negative
            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction

        }
    }

    /**
     * create an account and compare maybe there is already that account
     * @param scanner
     * @param accountBalanceMap
     * @return
     */
    public static HashMap<String, BigDecimal> createAccount (Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
        System.out.println("Please insert account number");
        String line = scanner.nextLine();
        BigDecimal balance = new BigDecimal(0);
        boolean isAccountAlready = true;
        while (isAccountAlready) {
            if (accountBalanceMap.containsKey(line)) {
                isAccountAlready = accountBalanceMap.containsKey(line);
                System.out.println("This account number we have already. Please try again");
            } else {
                accountBalanceMap.put(line, balance);
                System.out.println("Account created");
                isAccountAlready = false;
            }
        }
        return accountBalanceMap;
    }

    /**
     * Validate bank account format EE 90 10 10 1234 5678 9012
     * two letters + 18 numbers
     */

    public static boolean validateBankAccountFormat (String bankAccount) {
            String[] splitAccount = bankAccount.split("");


        return true;
    }

    /**
     * Validate is there a existing bankAccount or not
     */
    public static boolean existBankAccount (String bankAccount, HashMap<String, BigDecimal> accountBalanceMap ) {
        boolean status = accountBalanceMap.containsKey(bankAccount);
        return status;
    }

    /**
     * check account
     * @param scanner
     */

    public static void getBalance (Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
        System.out.println("Please enter an Account number:");
        boolean session = true;
        while (session) {
            String line = scanner.nextLine();
            if (accountBalanceMap.containsKey(line)) {
                String balance = accountBalanceMap.get(line).toString();
                System.out.println("Current balance for account: " + balance + " EUR");
                session = false;
            } else if (line.equals("back")) {
                session = false;
            } else {
                System.out.println("Incorrect account number");
                System.out.println("Please try again or type \"back\" ");
            }

        }
    }

    public static HashMap<String, BigDecimal> depositMoney (Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
        boolean session = true;
        while (session) {
            System.out.println("Please insert account number:");
        }
        return null;
    }






}
