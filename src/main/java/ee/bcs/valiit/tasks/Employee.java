package ee.bcs.valiit.tasks;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Employee {

    private String name;
    private int age;

    public HashMap<LocalDateTime, String[]> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(HashMap<LocalDateTime, String[]> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    private HashMap<LocalDateTime, String[]> transactionHistory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
