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
                    depositMoney(scanner, accountBalanceMap);
                    break;
                case "withdrawMoney":
                    withdrawMoney(scanner, accountBalanceMap);
                    break;
                case "transfer":
                    transferMoney(scanner);
                    break;
                case "exit":
                    System.out.println("exit");
                    status = false;
                    break;
                default:
                    System.out.println("Unknown command");
            }

            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction

        }
    }

    /**
     * check account balance
     */
    public static BigDecimal accountBalance(String accountNumber) {
        return accountBalanceMap.get(accountNumber);
    }

    /**
     * Validate is there a existing bankAccount or not
     */
    public static boolean existBankAccount (String bankAccount) {
        boolean status = accountBalanceMap.containsKey(bankAccount);
        return status;
    }

    /**
     * Check if amount is positive or not
     */

    public static boolean isPositive (String value) {
        BigDecimal number = new BigDecimal(value);
        boolean answer = number.compareTo(BigDecimal.ZERO) > 0;
        return answer;
    }

    /** TODO LOPETA ARA
     * Validate bank account format EE 90 10 10 1234 5678 9012
     * two letters + 18 numbers
     */

    public static boolean validateBankAccountFormat (String bankAccount) {
        String[] splitAccount = bankAccount.split("");


        return true;
    }

    /**
     * create an account and compare maybe there is already that account
     * @param scanner
     * @param accountBalanceMap
     * @return
     */
    public static void createAccount (Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
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
    }

    /**
     * check account balance
     * @param scanner
     */

    public static void getBalance (Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
        System.out.println("Please enter an Account number:");
        boolean session = true;
        while (session) {
            String line = scanner.nextLine();
            if (accountBalanceMap.containsKey(line)) {
                String balance = accountBalance(line).toString();
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

    /**
     * get current balance for account.
     * @param scanner
     * @param accountBalanceMap
     * @return
     */

    public static void depositMoney(Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
        boolean session = true;
        while (session) {
            System.out.println("Please insert account number:");
            String line = scanner.nextLine();
            if (existBankAccount(line)) {
                System.out.println("Please enter amount: ");
                String toAdd = scanner.nextLine();
                boolean positive = isPositive(toAdd);
                while (!positive) {
                    System.out.println("Please enter correct amount or type \"back\" ");
                    toAdd = scanner.nextLine();
                    positive = isPositive(toAdd);
                }
                BigDecimal add = new BigDecimal(toAdd);
                BigDecimal newBalance = accountBalanceMap.get(line).add(add);
                accountBalanceMap.put(line, newBalance);
                System.out.println("Balance: " + accountBalanceMap.get(line).toString());
                session = false;
            } else if (line.equals("back")) {
                session = false;
            } else {
                System.out.println("Incorrect account number");
                System.out.println("Please try again or type \"back\" ");
            }
        }
    }

    /**
     * subtract from account
     * @param scanner
     * @param accountBalanceMap
     */

    public static void withdrawMoney (Scanner scanner, HashMap<String, BigDecimal> accountBalanceMap) {
        boolean session = true;
        while (session) {
            System.out.println("Please insert account number:");
            String line = scanner.nextLine();
            if (existBankAccount(line)) {
                System.out.println("Please enter amount to withdraw: ");
                String toSubtract = scanner.nextLine();
                BigDecimal balance = accountBalance(line);
                Boolean status = isPositive(toSubtract);
                while (!status) {
                    System.out.println("Please enter positive number to withdraw:");
                    toSubtract = scanner.nextLine();
                    status = isPositive(toSubtract);
                }
                BigDecimal subtract = new BigDecimal(toSubtract);
                if (balance.compareTo(subtract) > 0) {
                    BigDecimal newBalance = accountBalanceMap.get(line).subtract(subtract);
                    accountBalanceMap.put(line, newBalance);
                    System.out.println("Balance: " + accountBalance(line).toString());
                    session = false;
                } else {
                    System.out.println("current balance is only " + balance.toString());
                }
            } else if (line.equals("back")) {
                session = false;
            } else {
                System.out.println("Incorrect account number");
                System.out.println("Please try again or type \"back\" ");
            }
        }
    }



    public static void transferMoney (Scanner scanner){
        boolean session = true;
        while (session) {
            System.out.println("Transfer from account:");
            String transferFromAccount = scanner.nextLine();
            if (existBankAccount(transferFromAccount)) {
                System.out.println("Transfer to account:");
                String transferToAccount = scanner.nextLine();
                if (existBankAccount(transferToAccount)) {
                    System.out.println("Enter amount to transfer");
                    String sumToTransfer = scanner.nextLine();
                    if (isPositive(sumToTransfer)) {
                        BigDecimal transfer = new BigDecimal(sumToTransfer);
                        if( accountBalance(transferFromAccount).compareTo(transfer) > 0) {
                            BigDecimal newBalance = accountBalanceMap.get(transferFromAccount).subtract(transfer);
                            accountBalanceMap.put(transferFromAccount, newBalance);
                            BigDecimal newBalance2 = accountBalanceMap.get(transferToAccount).add(transfer);
                            accountBalanceMap.put(transferToAccount, newBalance2);
                            session = false;
                        } else {
                            System.out.println("Ha HA you dont have enough money");
                        }
                    } else {
                        System.out.println("Amount is Negative");
                    }
                    System.out.println("Incorrect account number");
                }
            } else if (transferFromAccount.equals("back")) {
                session = false;
            } else {
                System.out.println("Incorrect account number");
                System.out.println("Please try again or type \"back\" ");
            }
        }
    }
}
