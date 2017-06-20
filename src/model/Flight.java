package model;

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

    public Flight(String origin, String destination, LocalDateTime departure, int designatedPlane) {

        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.designatedPlane = designatedPlane;
    }

    public Flight(int flightID, String origin, String destination, LocalDateTime departure, int designatedPlane, int availableSeats) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.designatedPlane = designatedPlane;
        this.availableSeats = availableSeats;
    }
}
