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
            ppst.setInt(6, flight.getFlightID());
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

    private Flight locateFlight(String parameter, String value) {
        String sql;
        PreparedStatement ppst = null;
        ResultSet rset = null;
        Flight flight = new Flight();

        try {
            switch (parameter) {
                case "flightID":
                    sql = "SELECT * FROM flight WHERE flight_id = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "origin":
                    sql = "SELECT * FROM flight WHERE origin = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                case "destination":
                    sql = "SELECT * FROM flight WHERE destination = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setString(1, value);
                    break;
                case "departure":
                    sql = "SELECT * FROM flight WHERE departure = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "designatedplane":
                    sql = "SELECT * FROM flight WHERE designatedplane = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                case "availableseats":
                    sql = "SELECT * FROM flight WHERE availableseats = ?";
                    ppst = con.prepareStatement(sql);
                    ppst.setInt(1, Integer.parseInt(value));
                    break;
                default:
                    System.out.println("Invalid search parameter.");
                    break;
            }
            rset = ppst.executeQuery();
            rset.next();
            flight.setFlightID(rset.getInt("flight_id"));
            flight.setOrigin(rset.getString("origin"));
            flight.setDestination(rset.getString("destination"));
            flight.setDeparture(rset.getTimestamp("departure").toLocalDateTime());
            flight.setDesignatedPlane(rset.getInt("designatedplane"));
            flight.setAvailableSeats(rset.getInt("availableseats"));
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        } finally {
            DBConnector.closeConnection(con, ppst);
        }
        return flight;
    }

    public Flight locateFlightById(String value) {
        Flight flight = locateFlight("flightID", value);
        return flight;
    }

    public Flight locateFlightByOrigin(String value) {
        Flight flight = locateFlight("origin", value);
        return flight;
    }
    public Flight locateFlightByDestination(String value) {
        Flight flight = locateFlight("destination", value);
        return flight;
    }

    public Flight locateFlightByDeparture(String value) {
        Flight flight = locateFlight("departure", value);
        return flight;
    }

    public Flight locateFlightByDesignatedPlane(String value) {
        Flight flight = locateFlight("designatedPlane", value);
        return flight;
    }

    public Flight locateFlightByAvailableSeats(String value) {
        Flight flight = locateFlight("availableseats", value);
        return flight;
    }

    public static boolean isNotEmpty(Flight flight) {
        if (flight.getFlightID() == 0 && flight.getOrigin() == null && flight.getDestination() == null && flight.getDeparture() == null && flight.getDesignatedPlane() == 0 && flight.getAvailableSeats() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
