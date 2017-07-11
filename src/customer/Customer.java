package customer;

/**
 * Created by Bernardo on 09/06/2017.
 */
public class Customer {
    private int customerID;
    private String identification;
    private String customerName;
    private String phone;

    public Customer(String identification, String customerName, String phone) {
        this.identification = identification;
        this.customerName = customerName;
        this.phone = phone;
    }

    public Customer(int customerID, String identification, String customerName, String phone) {
        this.customerID = customerID;
        this.identification = identification;
        this.customerName = customerName;
        this.phone = phone;
    }

    public Customer() {

    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}