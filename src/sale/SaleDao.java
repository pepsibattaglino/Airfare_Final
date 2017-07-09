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
        String sql = "INSERT INTO sale (salecustomer, saleflight, timeofpurchase) VALUES (?, ?, ?)";
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

    private Sale locateSale(String parameter, String value) {
        String sql;
        PreparedStatement ppst = null;
        ResultSet rset = null;
        Sale sale = new Sale();

        try {
            switch (parameter) {
                case "saleID":
                    sql = "SELECT * FROM sale WHERE sale_id = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "salecustomer":
                    sql = "SELECT * FROM sale WHERE salecustomer = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "saleflight":
                    sql = "SELECT * FROM sale WHERE saleflight = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "timeofpurechase":
                    sql = "SELECT * FROM sale WHERE timeofpurchase = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                default:
                    System.out.println("Invalid search parameter.");
                    break;
            }
            rset = ppst.executeQuery();
            rset.next();
            sale.setSaleID(rset.getInt("sale_id"));
            sale.setSaleCustomer(rset.getInt("salecustomer"));
            sale.setSaleFlight(rset.getInt("saleflight"));
            sale.setTimeOfPurchase(rset.getTimestamp("timeofpurchase").toLocalDateTime());
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
        return sale;
    }

    public Sale locateSaleById(String value) {
        Sale sale = locateSale("saleID", value);
        return sale;
    }

    public Sale locateSaleBySaleCustomer(String value) {
        Sale sale = locateSale("salecustomer", value);
        return sale;
    }

    public Sale locateSaleBySaleFlight(String value) {
        Sale sale = locateSale("saleflight", value);
        return sale;
    }

    public Sale locateSaleByTimeOfPurchase(String value) {
        Sale sale = locateSale("timeofpurechase", value);
        return sale;
    }

    public static boolean isNotEmpty(Sale sale) {
        if (sale.getSaleID() == 0 && sale.getSaleCustomer() == 0 && sale.getSaleFlight() == 0 && sale.getTimeOfPurchase() == null) {
            return false;
        } else {
            return true;
        }
    }

}
