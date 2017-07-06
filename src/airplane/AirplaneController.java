package airplane;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.*;


/**
 * Created by Bernardo on 06/07/2017.
 */
public class AirplaneController {

    @FXML
    private TextField AirplaneCodeField;

    @FXML
    private TextField AirplaneModelField;

    @FXML
    private TextField AirplaneQntSeatsField;

    public void treatSaveButton(ActionEvent event) {
        System.out.println("The Save button has been pressed.");
        registerAirplane(AirplaneCodeField, AirplaneModelField, AirplaneQntSeatsField);
    }

    private void registerAirplane (TextField code, TextField model, TextField qntSeats) {
        Airplane a = new Airplane(
                code.getText(),
                model.getText(),
                Integer.parseInt(qntSeats.getText()));
        AirplaneDao aDao = new AirplaneDao();
        aDao.createAirplane(a);
    }
}
