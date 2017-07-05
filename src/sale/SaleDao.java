package sale;

import java.sql.*;
import java.util.*;
import connection.DBConnector;

/**
 * Created by G.Battaglino on 05/07/2017.
 */
public class SaleDao {

    private Connection con = null;

    public SaleDao() {
        con = DBConnector.getConnection();
    }

    /**
     * CREATE
     *
     * Creates an sale on database.
     *
     * @param sale
     * @return
     */
    public boolean createSale(Sale sale) {
        String sql = "INSERT INTO sale (salecustomer, saleflight, timeofpurechase) VALUES (?, ?, ?)";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setInt(1, sale.getSaleCustomer());
            ppst.setInt(2, sale.getSaleFlight());
            Timestamp date = Timestamp.valueOf(sale.getTimeOfPurchase());
            ppst.setTimestamp(3, date);//?????
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
     * List all from the table sale.
     *
     * @return
     */
    public List<Sale> listAllSales() {
        String sql = "SELECT * FROM sale";
        PreparedStatement ppst = null;
        ResultSet rset = null;
        List<Sale> sales = new ArrayList<>();

        try {
            ppst = con.prepareStatement(sql);
            rset = ppst.executeQuery();
            while (rset.next()) {
                Sale sale = new Sale(
                        rset.getInt("sale_id"),
                        rset.getInt("salecustomer"),
                        rset.getInt("saleflight"),
                        rset.getTimestamp("timeofpurchase").toLocalDateTime()
                );
                sales.add(sale);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst, rset);
        }
        return sales;
    }

    /**
     * UPDATE
     *
     * Update a sale in database.
     *
     * @param sale
     * @return
     */
    public boolean updateSale(Sale sale) {
        String sql = "UPDATE sale SET salecustomer = ?, saleflight = ?, timeofpurchase = ? WHERE sale_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setInt(1, sale.getSaleCustomer());
            ppst.setInt(2, sale.getSaleFlight());
            Timestamp date = Timestamp.valueOf(sale.getTimeOfPurchase());
            ppst.setTimestamp(3, date);
            ppst.setInt(4, sale.getSaleID());
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
     * Delete a sale from database.
     *
     * @param sale
     * @return
     */
    public boolean deleteSale(Sale sale) {
        String sql = "DELETE FROM sale WHERE sale_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setInt(1, sale.getSaleID());
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
