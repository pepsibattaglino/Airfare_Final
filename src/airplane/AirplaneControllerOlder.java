package airplane;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.*;
import javax.swing.*;
import airplane.AirplaneBusiness;


/**
 * Created by Bernardo on 06/07/2017.
 */
public class AirplaneControllerOlder {

    @FXML
    private TextField AirplaneCodeField;

    @FXML
    private TextField AirplaneModelField;

    @FXML
    private TextField AirplaneQntSeatsField;

    public void treatSaveButton(ActionEvent event) {
        System.out.println("The Save button has been pressed.");
        if (notEmptyField(AirplaneCodeField) && notEmptyField(AirplaneModelField) && notEmptyField(AirplaneQntSeatsField)) {
            AirplaneBusiness ab = new AirplaneBusiness();
            if (ab.airplaneCodeChecker(AirplaneCodeField.getText()) &&
                    ab.airplaneModelChecker(AirplaneModelField.getText()) &&
                    ab.airplaneQntSeatsChecker(AirplaneQntSeatsField.getText())) {
                registerAirplane(AirplaneCodeField, AirplaneModelField, AirplaneQntSeatsField);
                clearFields();
                JOptionPane.showMessageDialog(null,
                        "Plane saved with success!");
            } else {
                System.out.println("Failed to verify the airplane.");
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "One or more required fields are empty.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void treatClearButton(ActionEvent event) {
        System.out.println("The Clear button has been pressed.");
        clearFields();
    }

    private void registerAirplane (TextField code, TextField model, TextField qntSeats) {
        Airplane a = new Airplane(
                code.getText(),
                model.getText(),
                Integer.parseInt(qntSeats.getText()));
        AirplaneDao aDao = new AirplaneDao();
        aDao.createAirplane(a);
    }

    private void clearFields () {
        AirplaneCodeField.setText("");
        AirplaneModelField.setText("");
        AirplaneQntSeatsField.setText("");
    }

    private boolean notEmptyField (TextField toTest) {
        if (toTest.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
