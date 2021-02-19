package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MyBankRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public void createCustomer(MyBankCustomer myBankCustomer) {
        String sql = "INSERT INTO customer (name, social_number, user_name, password) " + "VALUES (:name, :social_number, :user_name, :password)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("name", myBankCustomer.getCustomerName());
        paraMap.put("user_name", myBankCustomer.getUserName());
        paraMap.put("social_number", myBankCustomer.getSocialNumber());
        paraMap.put("password", myBankCustomer.getPassword());
        template.update(sql, paraMap);
    }

    public int validateAccount (String accountNr){
        String sql = "SELECT COUNT(*) FROM account WHERE account_nr = :account_nr";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public int getCustomerID (String socialNr) {
        String sql = "SELECT user_id FROM customer WHERE social_number = :social_number";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("social_number", socialNr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public void createAccount(String accountNr, int customerID) {
        String sql = "INSERT INTO account (account_nr, owner_id, balance) VALUES (:account_nr,:owner_id, :balance)";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        paraMap.put("owner_id", customerID);
        paraMap.put("balance", 0);
        template.update(sql, paraMap);
    }

    public int validateCustomer (String socialNr){
        String sql = "SELECT COUNT(*) FROM customer WHERE social_number = :social_number";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("social_number", socialNr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public int validateCustomerById (int customerId){
        String sql = "SELECT COUNT(*) FROM customer WHERE user_id = :user_id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("user_id", customerId);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public int validateUserName (String userName){
        String sql = "SELECT COUNT(*) FROM customer WHERE user_name = :user_name";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("user_name", userName);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public int getAccountID (String accountNr) {
        String sql = "SELECT account_id FROM account WHERE account_nr = :account_nr";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        return template.queryForObject(sql, paraMap, Integer.class);
    }

    public String getLastAccountNr () {
        String sql = "SELECT MAX(account_id) FROM account";
        int lastId = template.queryForObject(sql, new HashMap<>(), Integer.class);
        String sql2 = "SELECT account_nr FROM account WHERE account_id = :account_id";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("account_id", lastId);
        return template.queryForObject(sql2, paraMap, String.class);
    }

    public BigDecimal getBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_nr = :account_nr";
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("account_nr", accountNr);
        try {
            return template.queryForObject(sql, paraMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateBalance (String accountNr, BigDecimal newBalance) {
        String sql = "UPDATE account SET balance = :balance WHERE account_nr= :account_nr";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_nr", accountNr);
        paramMap.put("balance", newBalance);
        template.update(sql, paramMap);
    }

    public void saveTransaction (MyBankTransaction myBankTransaction) {
        String sql = "INSERT INTO transaction (account_from, account_to, sum, type, date_time) VALUES (:account_from,:account_to, :sum, :type, :date_time)";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("account_from", getAccountID(myBankTransaction.getAccountFrom()));
        paramMap.put("account_to", getAccountID(myBankTransaction.getAccountTo()));
        paramMap.put("sum", myBankTransaction.getSum());
        paramMap.put("type", myBankTransaction.getType());
        paramMap.put("date_time", myBankTransaction.getDatetime());
        template.update(sql, paramMap);
    }

    public List<MyBankAccount> allAccount() {
        String sql ="SELECT account.account_id, account.account_nr, account.balance, customer.name FROM account JOIN customer on account.owner_id = customer.user_id ORDER BY account_id";
        return template.query(sql, new HashMap<>(), new MyBankAccountRowMapper());
    }


    public List<MyBankTransaction> allTransactions () {
        String  sql = "SELECT t.transfer_id,\n" +
                "       t.sum,\n" +
                "       t.date_time,\n" +
                "       t2.type_name,\n" +
                "       a1.account_nr AS account_nr_to,\n" +
                "       a2.account_nr AS account_nr_from\n" +
                "FROM transaction t\n" +
                "    INNER JOIN account a1 ON t.account_to = a1.account_id\n" +
                "    INNER JOIN account a2 ON t.account_from = a2.account_id\n" +
                "    INNER JOIN type t2 on t.type = t2.type_id\n" +
                "ORDER BY transfer_id";
        return template.query(sql, new HashMap<>(), new MyBankTransactionRowMapper());
    }

     public List<MyBankTransaction> transactionsByAccountNr (String accountNr) {
        String sql = "SELECT t.transfer_id,\n" +
                "       t.sum,\n" +
                "       t.date_time,\n" +
                "       t2.type_name,\n" +
                "       a1.account_nr AS account_nr_to,\n" +
                "       a2.account_nr AS account_nr_from\n" +
                "FROM transaction t\n" +
                "    INNER JOIN account a1 ON t.account_to = a1.account_id\n" +
                "    INNER JOIN account a2 ON t.account_from = a2.account_id\n" +
                "    INNER JOIN type t2 on t.type = t2.type_id\n" +
                "WHERE a1.account_nr = :account_nr_from OR a2.account_nr = :account_nr_to\n" +
                "ORDER BY transfer_id";
         Map<String, String> paramMap = new HashMap();
         paramMap.put("account_nr_to", accountNr);
         paramMap.put("account_nr_from", accountNr);
        return template.query(sql, paramMap, new MyBankTransactionRowMapper());
     }


}
