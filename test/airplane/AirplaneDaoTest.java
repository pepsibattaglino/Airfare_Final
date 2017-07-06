package airplane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Bernardo on 25/06/2017.
 */
class AirplaneDaoTest {
    @Test
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
    }

//    @Test
//    void findAirplaneByID() {
//        AirplaneDao dao = new AirplaneDao();
//        Airplane a = dao.findAirplaneById(4);
//        System.out.println("Plane model: " + a.getModel());
//    }

//    @Test
//    void findAirplane() {
//        AirplaneDao dao = new AirplaneDao();
//        Airplane a1 = dao.locateAirplane("airplaneID", "4");
//        System.out.println("Plane model: " + a1.getModel());
//        Airplane a2 = dao.locateAirplane("code", "IPX-7432");
//        System.out.println("Plane model: " + a2.getModel());
//        Airplane a3 = dao.locateAirplane("model", "Boeing");
//        System.out.println("Plane model: " + a3.getModel());
//        Airplane a4 = dao.locateAirplane("qntSeats", "28");
//        System.out.println("Plane model: " + a4.getModel());
//    }

    @Test
    void findAirplaneByValue() {
        AirplaneDao dao = new AirplaneDao();
//        Airplane a = dao.locateAirplaneById("4");
//        Airplane a = dao.locateAirplaneByCode("IPX-7432");
//        Airplane a = dao.locateAirplaneByModel("Boeing");
        Airplane a = dao.locateAirplaneByQntSeats("28");
        System.out.println("Plane model: " + a.getModel());
    }

    @Test
    void testExists() {
        AirplaneDao dao = new AirplaneDao();
        Airplane a = dao.locateAirplaneById("48");
        System.out.println("Plane model: " + a.getAirplaneID());
        dao.checkIfAirplaneExists(a);
    }
}