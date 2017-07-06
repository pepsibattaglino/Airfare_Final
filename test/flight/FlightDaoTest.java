package flight;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Created by G.Battaglino on 05/07/2017.
 */


public class FlightDaoTest {

    @Test
    public void createFlight() throws Exception {
//        LocalDateTime ldt = LocalDateTime.now();

        Flight f = new Flight("Foggia", "Los Angeles", LocalDateTime.now(), 1);
        FlightDao dao = new FlightDao();

        if(dao.createFlight(f)) {
            System.out.println("Flight created with success.");
        } else {
            fail("Failed to create a flight.");
        }
    }

    @Test
    public void listAllFlights() throws Exception {
        FlightDao dao = new FlightDao();

        for(Flight f: dao.listAllFlights()){
            System.out.println("Flight destination: " + f.getDestination());
        }
    }

    @Test
    public void updateFlights() throws Exception {
        FlightDao dao = new FlightDao();
        Flight f = new Flight("Foggia", "Los Angeles", LocalDateTime.now(), 1);

        if(dao.updateFlights(f)){
            System.out.println("Flight updated with success.");
        } else {
            fail("Failed to update a Flight.");
        }
    }

    @Test
    public void deleteFlights() throws Exception {
        FlightDao dao = new FlightDao();
        Flight f = new Flight();
        f.setFlightID(3);

        if(dao.deleteFlights(f)){
            System.out.println("Flight deleted with success.");
        } else {
            fail("Failed to delete a Flight.");
        }
    }

}