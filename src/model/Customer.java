package model;

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
}
