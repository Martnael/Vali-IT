package ee.bcs.valiit.tasks;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

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

    public static String buildAccountNumber (int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("EE");
        sb.append(i);
        return sb.toString();

    }

    public static String createCustomer(Customer customer, NamedParameterJdbcTemplate template) {
        String sql = "INSERT INTO customer (name, social_number) " + "VALUES (:name,:social_number)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", customer.getCustomerName());
        paraMap.put("social_number", customer.getSocialNumber());
        template.update(sql, paraMap);
        return "customer created";
    }

    public static String createAccount(String accountNr,NamedParameterJdbcTemplate template) {
        String sql = "INSERT INTO account (account_nr, owner_id, balance) " + "VALUES (:account_nr,:owner_id, :balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        paraMap.put("owner_id", 2);
        paraMap.put("balance", 0);
        template.update(sql, paraMap);
        return "account created";
    }


//    public static String createAccount(String accountNr, Account account) {
//        String answer = "Account has been created:<br>" + accountNr + "<br>" + account.getAccountHolderName();
//        return answer;
//    }
//
//    public static boolean validateAccount (String accountNr, HashMap<String, Account> accountMap) {
//        return accountMap.containsKey(accountNr);
//    }

//    public static String getBalance(String accountNr, HashMap<String, Account> accountMap) {
//
//        if (validateAccount(accountNr, accountMap)) {
//            Account account = accountMap.get(accountNr);
//            BigDecimal balance = account.getAccountBalance();
//            String answer = "Current balance for account: " + accountNr + "<br>" + balance + "  EUR";
//            return answer;
//        } else {
//            String answer = "We dont have such account please!" + "<br>" + "Please try again";
//            return answer;
//        }
//    }
//
//    public static boolean isPositive(String value) {
//        BigDecimal number = new BigDecimal(value);
//        boolean answer = number.compareTo(BigDecimal.ZERO) > 0;
//        return answer;
//    }
//
//    public static BigDecimal accountBalance(String accountnr, HashMap<String, Account> accountMap) {
//        return accountMap.get(accountnr).getAccountBalance();
//    }
//
//    public static String depositMoney(String accountNr, String depositSum, HashMap<String, Account> accountMap) {
//        if (validateAccount(accountNr,accountMap) && isPositive(depositSum)) {
//            BigDecimal add = new BigDecimal(depositSum);
//            Account account = accountMap.get(accountNr);
//            BigDecimal newBalance = account.getAccountBalance().add(add);
//            account.setAccountBalance(newBalance);
//            accountMap.put(accountNr, account);
//            String answer = "Transfer complete. <br> Balance for account: " + accountNr + "<br>" + account.getAccountBalance();
//            return answer;
//        } else {
//            String answer = "We dont have such account or you try to insert negative amount of money to account";
//            return answer;
//        }
//    }
//
//    public static String withdrawMoney(String accountNr, String sum, HashMap<String, Account> accountMap) {
//        Account account = accountMap.get(accountNr);
//        BigDecimal withdrawSum = new BigDecimal(sum);
//        BigDecimal balance = accountBalance(accountNr, accountMap);
//        Boolean isPositive = isPositive(sum);
//        Boolean existBankAccount = validateAccount(accountNr, accountMap);
//
//        if (existBankAccount && isPositive && balance.compareTo(withdrawSum)>0) {
//            account.setAccountBalance(balance.subtract(withdrawSum));
//            accountMap.put(accountNr, account);
//            String answer = "Withdraw transaction completed.<br>Current balance for account: " + accountNr + "        " + account.getAccountBalance();
//            return answer;
//        } else if (!isPositive(sum)) {
//            String answer = "You can not withdraw negative amount from your account";
//            return answer;
//        } else if (balance.compareTo(withdrawSum)<0) {
//            String answer = "You dont have so much money on your account!";
//            return answer;
//        } else {
//            String answer = "Account number does not exist";
//            return answer;
//        }
//    }

 //   public static String transferMoney (String accountFrom, String accountTo, String sum, HashMap<String, BigDecimal> accountBalanceMap ){
  //      BigDecimal accountBalanceFrom = accountBalance(accountFrom, accountBalanceMap);
  //      BigDecimal transferSum = new BigDecimal(sum);
  //      if (existBankAccount(accountFrom, accountBalanceMap) && existBankAccount(accountTo, accountBalanceMap) && isPositive(sum) && accountBalanceFrom.compareTo(transferSum)>0) {
  //          BigDecimal newBalanceFrom = accountBalanceMap.get(accountFrom).subtract(transferSum);
 //           BigDecimal newBalanceTo = accountBalanceMap.get(accountTo).add(transferSum);
 //           accountBalanceMap.put(accountTo, newBalanceTo);
 //           String answer = "Transfer completed.<br>New balance for account " + accountFrom + " :  " + accountBalanceMap.get(accountFrom).toString() + "<br>" +
 //                   "New balance for account: " + accountTo + " :  " + accountBalanceMap.get(accountTo).toString();
 //           return answer;
 //       } else if (!existBankAccount(accountFrom, accountBalanceMap)) {
 //           String answer = "Account from is wrong";
 //           return answer;
 //       } else if (!existBankAccount(accountTo, accountBalanceMap)) {
 //           String answer = "Account to is wrong";
 //           return answer;
 //       } else if (!isPositive(sum)) {
 //           String answer = "You can not transfer negative amount";
 //           return answer;
 //       } else if (accountBalanceFrom.compareTo(transferSum)<0) {
 //           String answer = "You dont have money to transfer";
 //           return answer;
 //       } else {
 //           String answer = "Something went terribly wrong";
 //           return answer;
//        }
 //   }
}




