package customer;

import java.sql.*;
import java.util.*;

import connection.DBConnector;

/**
 * Created by G.Battaglino on 04/07/2017.
 */
public class CustomerDao {

    private Connection con = null;

    public CustomerDao() {
        con = DBConnector.getConnection();
    }


    /**
     * CREATE
     * 
     * Creates an customer on database.
     *
     * @param customer
     * @return
     */
    public boolean createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (identification, customername, phone) VALUES (?, ?, ?)";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setString(1, customer.getIdentification());
            ppst.setString(2, customer.getCustomerName());
            ppst.setString(3, customer.getPhone());
            ppst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            return false;
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
    }


    /**
     * READ
     *
     * List all from the table customer.
     *
     * @return
     */
    public List<Customer> listAllCustomers() {
        String sql = "SELECT * FROM customer";
        PreparedStatement ppst = null;
        ResultSet rset = null;
        List<Customer> customers = new ArrayList<>();

        try {
            ppst = con.prepareStatement(sql);
            rset = ppst.executeQuery();
            while (rset.next()) {
                Customer customer = new Customer(
                        rset.getInt("customer_id"),
                        rset.getString("identification"),
                        rset.getString("customername"),
                        rset.getString("phone")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst, rset);
        }
        return customers;
    }


    /**
     * UPDATE
     *
     * @param customer
     * @return
     */
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET identification = ?, customername = ?, phone = ? WHERE customer_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setString(1, customer.getIdentification());
            ppst.setString(2, customer.getCustomerName());
            ppst.setString(3, customer.getPhone());
            ppst.setInt(4, customer.getCustomerID());
            ppst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            return false;
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
    }

}