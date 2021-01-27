package ee.bcs.valiit.tasks;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class MyBankAccountTwo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int Id;

    @Column(name = "owner_id")
    private int ownerNr;

    @Column(name = "account_nr")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal accountBalance = BigDecimal.ZERO;

    public int getId() {
        return Id;
    }

    public int getOwnerNr() {
        return ownerNr;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }
}
