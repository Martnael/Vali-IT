package ee.bcs.valiit.tasks;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBankTransactionRowMapper implements RowMapper<MyBankTransaction>{
    @Override
    public MyBankTransaction mapRow(ResultSet resultSet, int i) throws SQLException {
        MyBankTransaction transaction = new MyBankTransaction();
        transaction.setAccountFrom(resultSet.getString("account_nr_from"));
        transaction.setAccountTo(resultSet.getString("account_nr_to"));
        transaction.setDatetime(resultSet.getString("date_time"));
        transaction.setSum(resultSet.getBigDecimal("sum"));
        transaction.setType(resultSet.getString("type"));
        transaction.setTransactionID(resultSet.getInt("transfer_id"));
        return transaction;
    }
}
