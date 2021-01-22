package ee.bcs.valiit.tasks;

public class Customer {

    private String customerName;
    private String socialNumber;

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", socialnumber=" + socialNumber +
                '}';
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }
}
