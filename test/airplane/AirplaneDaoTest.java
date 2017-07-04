package airplane;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Bernardo on 25/06/2017.
 */
class AirplaneDaoTest {
    @Test
    public void createAirplane() throws Exception {
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

}