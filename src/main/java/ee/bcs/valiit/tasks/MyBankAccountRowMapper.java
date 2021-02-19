package ee.bcs.valiit.tasks;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBankAccountRowMapper implements RowMapper<MyBankAccount> {
    @Override
    public MyBankAccount mapRow(ResultSet resultSet, int i) throws SQLException {
        MyBankAccount account = new MyBankAccount();
        account.setId(resultSet.getInt("account_id"));
        account.setAccountNumber(resultSet.getString("account_nr"));
        account.setAccountBalance(resultSet.getBigDecimal("balance"));
        account.setOwner(resultSet.getString("name"));
        return account;
    }
}
