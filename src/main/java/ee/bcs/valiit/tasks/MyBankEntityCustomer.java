package ee.bcs.valiit.tasks;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class MyBankEntityCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    private String socialNumber;
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<MyBankEntityAccount> accounts;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public List<MyBankEntityAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<MyBankEntityAccount> accounts) {
        this.accounts = accounts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
