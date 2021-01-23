package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.solution.SolutionEmployee;
import ee.bcs.valiit.tasks.solution.controller.SolutionEmployeeController;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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

    public static boolean isPositive(BigDecimal sum) {
        return sum.compareTo(BigDecimal.ZERO) > 0;
    }

    public static int validateAccount (String accountNr, NamedParameterJdbcTemplate template){
        String sql = "SELECT COUNT(*) FROM account WHERE account_nr = :account_nr";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public static int validateCustomer (String social_nr, NamedParameterJdbcTemplate template){
        String sql = "SELECT COUNT(*) FROM customer WHERE social_number = :social_number";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("social_number", social_nr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public static int getCustomerID (String social_nr, NamedParameterJdbcTemplate template) {
        String sql = "SELECT user_id FROM customer WHERE social_number = :social_number";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("social_number", social_nr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public static int getAccountID (String accountNr, NamedParameterJdbcTemplate template) {
        String sql = "SELECT account_id FROM account WHERE account_nr = :account_nr";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public static Boolean isEnoughMoney (String accountNr, BigDecimal sum, NamedParameterJdbcTemplate template) {
        BigDecimal balance = getBalance(accountNr, template);
        Map<String, String> paraMap = new HashMap<>();
        return balance.compareTo(sum) >= 0;
    }
    ///////// DOES NOT WORK //////
    public static int getLastAccountNr (NamedParameterJdbcTemplate template) {
        String sql = "SELECT MAX(:account_id) FROM account";
        Map<String, Object> paraMap = new HashMap<>();
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public static String createCustomer(Customer customer, NamedParameterJdbcTemplate template) {
        String sql = "INSERT INTO customer (name, social_number) " + "VALUES (:name,:social_number)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", customer.getCustomerName());
        paraMap.put("social_number", customer.getSocialNumber());
        template.update(sql, paraMap);
        return "customer created";
    }

    public static String createAccount(String accountNr, int customerID, NamedParameterJdbcTemplate template) {
        String sql = "INSERT INTO account (account_nr, owner_id, balance) " + "VALUES (:account_nr,:owner_id, :balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        paraMap.put("owner_id", customerID);
        paraMap.put("balance", 0);
        template.update(sql, paraMap);
        return "account created";
    }

    public static BigDecimal getBalance(String accountNr, NamedParameterJdbcTemplate template ) {
        String sql = "SELECT balance FROM account WHERE account_nr = :account_nr";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        BigDecimal balance = template.queryForObject(sql, paraMap, BigDecimal.class);
        return balance;
    }

    public static void depositMoney (Transfer transfer, NamedParameterJdbcTemplate template) {
        BigDecimal balance = getBalance(transfer.getAccountTo(), template);
        BigDecimal newBalance = balance.add(transfer.getSum());
        updateBalance(transfer.getAccountTo(), newBalance, template);
    }

    public static void withdrawMoney (Transfer transfer, NamedParameterJdbcTemplate template) {
        BigDecimal balance = getBalance(transfer.getAccountFrom(), template);
        BigDecimal newBalance = balance.subtract(transfer.getSum());
        updateBalance(transfer.getAccountFrom(), newBalance, template);
    }

    public static void transferMoney (Transfer transfer, NamedParameterJdbcTemplate template) {
        BigDecimal balanceFrom = getBalance(transfer.getAccountFrom(), template);
        BigDecimal newBalanceFrom = balanceFrom.subtract(transfer.getSum());
        BigDecimal balanceTo = getBalance(transfer.getAccountTo(), template);
        BigDecimal newBalanceTo = balanceTo.add(transfer.getSum());
        updateBalance(transfer.getAccountFrom(), newBalanceFrom, template);
        updateBalance(transfer.getAccountTo(), newBalanceTo, template);
    }

    public static void updateBalance (String accountNr, BigDecimal newBalance, NamedParameterJdbcTemplate template) {
        String sql = "UPDATE account SET balance = :balance WHERE account_nr= :account_nr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", newBalance);
        template.update(sql, paramMap);
    }

    public static void saveTransaction (Transfer transfer, NamedParameterJdbcTemplate template ) {
        String sql = "INSERT INTO transaction (account_from, account_to, sum, date_time) VALUES (:account_from,:account_to, :sum, :date_time)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from", getAccountID(transfer.getAccountFrom(), template));
        paramMap.put("account_to", getAccountID(transfer.getAccountTo(), template));
        paramMap.put("sum", transfer.getSum());
        paramMap.put("date_time", transfer.getDatetime());
        template.update(sql, paramMap);
    }

    public static List<Account> allAccount(NamedParameterJdbcTemplate template) {
        String sql ="SELECT account.account_nr, account.balance, customer.name  FROM account JOIN customer on account.owner_id = customer.user_id";
        List<Account> result = template.query(sql, new HashMap<>(), new AccountRowMapper());
        return result;
    }

    public static String printAccounts (List<Account> allaccounts) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1 cellspacing=1 cellpadding=2 style='font-family:Arial;font-size:12'>" +
                "<tr>" +
                "<td><b>Account Owner</b></td>" +
                "<td><b>Account Number</b></td>" +
                "<td><b>Balance</b></td>" +
                "</tr>");
        for (Account account : allaccounts) {
            String row =    "<tr>" +
                            "<td>" + account.getOwner() + "</td>" +
                            "<td>" + account.getAccountNumber() + "</td>" +
                            "<td>" + account.getAccountBalance() + "</td>" +
                            "</tr>";
            sb.append(row);
        }
        return sb.toString();
    }

}




