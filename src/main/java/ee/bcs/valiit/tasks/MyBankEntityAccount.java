package ee.bcs.valiit.tasks;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account")
public class MyBankEntityAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int Id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private MyBankEntityCustomer customer;

    @Column(name = "account_nr")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal accountBalance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "accountFrom")
    private List<MyBankEntityTransaction> accountFrom;

    @OneToMany(mappedBy = "accountTo")
    private List<MyBankEntityTransaction> accountTo;

    public int getId() {
        return Id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setId(int id) {
        Id = id;
    }

    public MyBankEntityCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(MyBankEntityCustomer customer) {
        this.customer = customer;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<MyBankEntityTransaction> getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(List<MyBankEntityTransaction> accountFrom) {
        this.accountFrom = accountFrom;
    }

    public List<MyBankEntityTransaction> getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(List<MyBankEntityTransaction> accountTo) {
        this.accountTo = accountTo;
    }

}
