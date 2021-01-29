package ee.bcs.valiit.tasks;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "type")
public class MyBankEntityType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int typeId;

    private String typeName;

    @OneToMany(mappedBy = "type")
    private List<MyBankEntityTransaction> transactions;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public List<MyBankEntityTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<MyBankEntityTransaction> transactions) {
        this.transactions = transactions;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
