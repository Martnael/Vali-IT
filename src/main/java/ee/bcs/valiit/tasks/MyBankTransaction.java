package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MyBankTransaction {
    String accountFrom =" ";
    String accountTo =" ";
    BigDecimal sum = BigDecimal.ZERO;
    String type = " ";
    String Datetime = LocalDateTime.now().toString();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
