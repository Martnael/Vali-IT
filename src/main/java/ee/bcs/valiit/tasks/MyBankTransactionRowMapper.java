package ee.bcs.valiit.tasks;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyBankTransactionRowMapper implements RowMapper<MyBankTransaction>{
    @Override
    public MyBankTransaction mapRow(ResultSet resultSet, int i) throws SQLException {
        MyBankTransaction transaction = new MyBankTransaction();
        if (resultSet.getString("account_nr_from").equals("EE1")) {
            transaction.setAccountFrom("");
        } else {
            transaction.setAccountFrom(resultSet.getString("account_nr_from"));
        }
        if (resultSet.getString("account_nr_to").equals("EE1")) {
            transaction.setAccountTo("");
        } else {
            transaction.setAccountTo(resultSet.getString("account_nr_to"));
        }
        transaction.setDatetime(resultSet.getString("date_time"));
        transaction.setSum(resultSet.getBigDecimal("sum"));
        transaction.setTypeName(resultSet.getString("type_name"));
        transaction.setTransactionID(resultSet.getInt("transfer_id"));
        return transaction;
    }
}
