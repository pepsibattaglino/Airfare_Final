package connection;

import java.sql.*;

/**
 * Created by Bernardo on 23/06/2017.
 */

public class DBConnector {

    /**
     * The DBConnector attributtes are used to create a connection.
     */
    private static String driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/AirfareDB";
    private static String user = "postgres";
    private static String pass = "123456";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Connection error: ", e);
        }
    }

    public static void closeConnection (Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection (Connection c, PreparedStatement ps) {
        closeConnection(c);
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection (Connection c, PreparedStatement ps, ResultSet rs) {
        closeConnection(c, ps);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}