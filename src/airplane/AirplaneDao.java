package airplane;

import java.sql.*;
import java.util.*;

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

    /**
     * UPDATE
     *
     * @param airplane
     * @return
     */
    public boolean updateAirplane(Airplane airplane) {
        String sql = "UPDATE airplane SET code = ?, model = ?, qntSeats = ? WHERE airplane_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setString(1, airplane.getCode());
            ppst.setString(2, airplane.getModel());
            ppst.setInt(3, airplane.getQntSeats());
            ppst.setInt(4, airplane.getAirplaneID());
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
     * @param airplane
     * @return
     */
    public boolean deleteAirplane(Airplane airplane) {
        String sql = "DELETE FROM airplane WHERE airplane_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setInt(1, airplane.getAirplaneID());
            ppst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error: " + e);
            return false;
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
    }

//    public Airplane findAirplaneById(int id) {
//        String sql = "SELECT * FROM airplane WHERE airplane_id = ?";
//        PreparedStatement ppst = null;
//        ResultSet rset = null;
//        Airplane airplane = new Airplane();
//        try {
//            ppst = con.prepareStatement(sql);
//            ppst.setInt(1, id);
//            rset = ppst.executeQuery();
//            rset.next();
//            airplane.setAirplaneID(rset.getInt("airplane_id"));
//            airplane.setCode(rset.getString("code"));
//            airplane.setModel(rset.getString("model"));
//            airplane.setQntSeats(rset.getInt("qntseats"));
//        } catch (SQLException e) {
//            System.err.println("Error: " + e);
//        } finally {
//            DBConnector.closeConnection(con, ppst);
//        }
//        return airplane;
//    }

    private Airplane locateAirplane(String parameter, String value) {
        String sql;
        PreparedStatement ppst = null;
        ResultSet rset = null;
        Airplane airplane = new Airplane();

        try {
            switch (parameter) {
                case "airplaneID":
                    sql = "SELECT * FROM airplane WHERE airplane_id = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "code":
                    sql = "SELECT * FROM airplane WHERE code = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                case "model":
                    sql = "SELECT * FROM airplane WHERE model = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                case "qntSeats":
                    sql = "SELECT * FROM airplane WHERE qntseats = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                default:
                    System.out.println("Invalid search parameter.");
                    break;
            }
            rset = ppst.executeQuery();
            rset.next();
            airplane.setAirplaneID(rset.getInt("airplane_id"));
            airplane.setCode(rset.getString("code"));
            airplane.setModel(rset.getString("model"));
            airplane.setQntSeats(rset.getInt("qntseats"));
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
        return airplane;
    }

    public Airplane locateAirplaneById(String value) {
        Airplane airplane = locateAirplane("airplaneID", value);
        return airplane;
    }

    public Airplane locateAirplaneByCode(String value) {
        Airplane airplane = locateAirplane("code", value);
        return airplane;
    }

    public Airplane locateAirplaneByModel(String value) {
        Airplane airplane = locateAirplane("model", value);
        return airplane;
    }

    public Airplane locateAirplaneByQntSeats(String value) {
        Airplane airplane = locateAirplane("qntSeats", value);
        return airplane;
    }

    public static boolean isNotEmpty(Airplane airplane) {
        if (airplane.getAirplaneID() == 0 && airplane.getCode() == null && airplane.getModel() == null && airplane.getQntSeats() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
