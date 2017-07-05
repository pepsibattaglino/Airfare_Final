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
    }

    @Test
    public void deleteFlights() throws Exception {
    }

/*    @Test
    void createAirplane() throws Exception {
        Airplane airplane = new Airplane("IPX-7432", "AirFit", 5);
        AirplaneDao dao = new AirplaneDao();

        if(dao.createAirplane(airplane)) {
            System.out.println("Plane created with success.");
        } else {
            fail("Failed to create a plane.");
        }
    }

    @Test
    void listAllAirplanes() {
        AirplaneDao dao = new AirplaneDao();

        for(Airplane a: dao.listAllAirplanes()){
            System.out.println("Plane model: " + a.getModel());
        }
    }

    @Test
    void updateAirplane() {
        AirplaneDao dao = new AirplaneDao();
        Airplane a = new Airplane(99, "DSS-6593", "Songbird", 5);

        if(dao.updateAirplane(a)){
            System.out.println("Plane updated with success.");
        } else {
            fail("Failed to update a plane.");
        }
    }

    @Test
    void deleteAirplane() {
        AirplaneDao dao = new AirplaneDao();
        Airplane a = new Airplane();
        a.setAirplaneID(3);

        if(dao.deleteAirplane(a)){
            System.out.println("Plane deleted with success.");
        } else {
            fail("Failed to delete a plane.");
        }
    }*/

}