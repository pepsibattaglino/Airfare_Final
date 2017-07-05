package flight;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import connection.DBConnector;

/**
 * Created by G.Battaglino on 04/07/2017.
 */

public class FlightDao {

    private Connection con = null;

    public FlightDao() {
        con = DBConnector.getConnection();
    }

    /**
     * CREATE
     *
     * Creates an flight on database.
     *
     * @param flight
     * @return
     */
    public boolean createFlight(Flight flight) {
        String sql = "INSERT INTO flight (origin, destination, departure, designatedplane, availableseats) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setString(1, flight.getOrigin());
            ppst.setString(2, flight.getDestination());
            Timestamp date = Timestamp.valueOf(flight.getDeparture());
            ppst.setTimestamp(3, date);
            ppst.setInt(4, flight.getDesignatedPlane());
            ppst.setInt(1, flight.getAvailableSeats());
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
     * List all from the table flight.
     *
     * @return
     */
    public List<Flight> listAllFlights() {
        String sql = "SELECT * FROM flight";
        PreparedStatement ppst = null;
        ResultSet rset = null;
        List<Flight> flights = new ArrayList<>();

        try {
            ppst = con.prepareStatement(sql);
            rset = ppst.executeQuery();
            while (rset.next()) {
                Flight flight = new Flight(
                        rset.getInt("flight_id"),
                        rset.getString("origin"),
                        rset.getString("destination"),
                        rset.getTimestamp("departure").toLocalDateTime(),
                        rset.getInt("designatedplane"),
                        rset.getInt("availableseats")
                );
                flights.add(flight);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst, rset);
        }
        return flights;
    }

    /**
     * UPDATE
     *
     * Update a flight in database.
     *
     * @param flight
     * @return
     */
    public boolean updateFlights(Flight flight) {
        String sql = "UPDATE flight SET origin = ?, destination = ?, departure = ?, designatedplane = ?, availableseats = ? WHERE flight_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setString(1, flight.getOrigin());
            ppst.setString(2, flight.getDestination());
            Timestamp date = Timestamp.valueOf(flight.getDeparture());
            ppst.setTimestamp(3, date);
            ppst.setInt(4, flight.getDesignatedPlane());
            ppst.setInt(5, flight.getAvailableSeats());
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
     * Delete a flight from database.
     *
     * @param flight
     * @return
     */
    public boolean deleteFlights(Flight flight) {
        String sql = "DELETE FROM flight WHERE flight_id = ?";
        PreparedStatement ppst = null;

        try {
            ppst = con.prepareStatement(sql);
            ppst.setInt(1, flight.getFlightID());
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
