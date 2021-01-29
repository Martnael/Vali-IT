package ee.bcs.valiit.tasks;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "transaction")
public class MyBankEntityTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transferId;

    @ManyToOne
    @JoinColumn(name = "account_from")
    private MyBankEntityAccount accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to")
    private MyBankEntityAccount accountTo;

    @ManyToOne
    @JoinColumn(name = "type")
    private MyBankEntityType type;

    private BigDecimal sum;

    private String dateTime;

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public MyBankEntityAccount getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(MyBankEntityAccount accountFrom) {
        this.accountFrom = accountFrom;
    }

    public MyBankEntityAccount getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(MyBankEntityAccount accountTo) {
        this.accountTo = accountTo;
    }

    public MyBankEntityType getType() {
        return type;
    }

    public void setType(MyBankEntityType type) {
        this.type = type;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
