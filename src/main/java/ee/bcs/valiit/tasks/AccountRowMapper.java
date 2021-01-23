package ee.bcs.valiit.tasks;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setAccountNumber(resultSet.getString("account_nr"));
        account.setAccountBalance(resultSet.getBigDecimal("balance"));
        account.setOwner(resultSet.getString("name"));
        return account;
    }
}
