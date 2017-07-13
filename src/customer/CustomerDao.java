package customer;

import java.sql.*;
import java.util.*;
import connection.DBConnector;

/**
 * Created by G.Battaglino on 04/07/2017.
 */
public class CustomerDao {

    private Connection con = null;

    /*public CustomerDao() {
        con = DBConnector.getConnection();
    }*/

    /**
     * CREATE
     *
     * Creates an customer on database.
     *
     * @param customer
     * @return
     */
    public boolean createCustomer(Customer customer) {
        if (!isNotEmpty(locateCustomerByIdentification(customer.getIdentification()))) {
            con = DBConnector.getConnection();
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
        } else {
            System.out.println("Error. Repeated customer identification number.");
            return false;
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
        con = DBConnector.getConnection();
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
     * Update a customer in database.
     *
     * @param customer
     * @return
     */
    public boolean updateCustomer(Customer customer) {
        con = DBConnector.getConnection();
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

    /**
     * DELETE
     *
     * Delete a customer from database.
     *
     * @param customer
     * @return
     */
    public boolean deleteCustomer(Customer customer) {
        con = DBConnector.getConnection();
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setInt(1, customer.getCustomerID());
            ppst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            return false;
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
    }

    private Customer locateCustomer(String parameter, String value) {
        con = DBConnector.getConnection();
        String sql;
        PreparedStatement ppst = null;
        ResultSet rset = null;
        Customer customer = new Customer();

        try {
            switch (parameter) {
                case "customerID":
                    sql = "SELECT * FROM customer WHERE customer_id = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "identification":
                    sql = "SELECT * FROM customer WHERE identification = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                case "customername":
                    sql = "SELECT * FROM customer WHERE customername = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                case "phone":
                    sql = "SELECT * FROM customer WHERE phone = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                default:
                    System.out.println("Invalid search parameter.");
                    break;
            }
            rset = ppst.executeQuery();
            rset.next();
            customer.setCustomerID(rset.getInt("customer_id"));
            customer.setIdentification(rset.getString("identification"));
            customer.setCustomerName(rset.getString("customername"));
            customer.setPhone(rset.getString("phone"));
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
        return customer;
    }

    public Customer locateCustomerById(String value) {
        Customer customer = locateCustomer("customerID", value);
        return customer;
    }

    public Customer locateCustomerByIdentification(String value) {
        Customer customer = locateCustomer("identification", value);
        return customer;
    }

    public Customer locateCustomerByCustomerName(String value) {
        Customer customer = locateCustomer("customername", value);
        return customer;
    }

    public Customer locateCustomerByPhone(String value) {
        Customer customer = locateCustomer("phone", value);
        return customer;
    }

    public static boolean isNotEmpty(Customer customer) {
        if (customer.getCustomerID() == 0 && customer.getIdentification() == null && customer.getCustomerName() == null && customer.getPhone() == null) {
            return false;
        } else {
            return true;
        }
    }

}