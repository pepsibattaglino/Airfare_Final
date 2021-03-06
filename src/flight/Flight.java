package flight;

import airplane.AirplaneDao;

import java.time.LocalDateTime;

/**
 * Created by Bernardo on 09/06/2017.
 */
public class Flight {
    private int flightID;
    private String origin;
    private String destination;
    private java.time.LocalDateTime departure;
    private int designatedPlane;
    private int availableSeats;

    public Flight() {}

    public Flight(String origin, String destination, LocalDateTime departure, int designatedPlane) {
        AirplaneDao aDao = new AirplaneDao();

        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.designatedPlane = designatedPlane;
        this.availableSeats = aDao.locateAirplaneById(Integer.toString(designatedPlane)).getQntSeats();
    }

    public Flight(int flightID, String origin, String destination, LocalDateTime departure, int designatedPlane, int availableSeats) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.designatedPlane = designatedPlane;
        this.availableSeats = availableSeats;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public int getDesignatedPlane() {
        return designatedPlane;
    }

    public void setDesignatedPlane(int designatedPlane) {
        this.designatedPlane = designatedPlane;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
