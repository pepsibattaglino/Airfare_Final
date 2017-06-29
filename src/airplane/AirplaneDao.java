package airplane;

import connection.NotFoundException;

import java.sql.*;
import java.util.*;
import java.math.*;

import connection.DBConnector;


/**
 * Created by Bernardo on 23/06/2017.
 */
public class AirplaneDao {

    private Connection con = null;

    public AirplaneDao() {
        con = DBConnector.getConnection();
    }

    /**
     * CREATE
     *
     * Creates an airplane on the database.
     * @param airplane
     * @return success
     */
    public boolean createAirplane(Airplane airplane) {
        String sql = "INSERT INTO airplane (code, model, qntSeats) VALUES (?, ?, ?)";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setString(1, airplane.getCode());
            ppst.setString(2, airplane.getModel());
            ppst.setInt(3, airplane.getQntSeats());
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
     * List all from the table.
     * @return
     */
    public List<Airplane> listAllAirplanes() {
        String sql = "SELECT * FROM airplane";
        PreparedStatement ppst = null;
        ResultSet rset = null;
        List<Airplane> airplanes = new ArrayList<>();

        try {
            ppst = con.prepareStatement(sql);
            rset = ppst.executeQuery();
            while (rset.next()) {
                Airplane airplane = new Airplane(
                        rset.getInt("airplane_id"),
                        rset.getString("code"),
                        rset.getString("model"),
                        rset.getInt("qntseats")
                );
                airplanes.add(airplane);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst, rset);
        }
        return airplanes;
    }

}
