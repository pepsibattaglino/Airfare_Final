package flight;

import airplane.AirplaneDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class FlightControllerOlder {

   /* @FXML
    private VBox formFlight;*/

    @FXML
    private TextField flightOriginField;

    @FXML
    private TextField flightDestinationField;

    @FXML
    private TextField flightDepartureField;

    @FXML
    private TextField flightDesignatedAirplaneField;


    /*@FXML
    private Button btnClear;

    @FXML
    private Button btnSave;*/

    @FXML
    void treatClearButton(ActionEvent event) {
        System.out.println("The Clear button has been pressed.");
        clearFields();
    }

    @FXML
    void treatSaveButton(ActionEvent event) {
        System.out.println("The Save button has been pressed.");
        if (
                notEmptyField(flightOriginField) &&
                notEmptyField(flightDestinationField) &&
                notEmptyField(flightDepartureField) &&
                notEmptyField(flightDesignatedAirplaneField)) {
            FlightBusiness fb = new FlightBusiness();
            if (
                    fb.flightOriginChecker(flightOriginField.getText()) &&
                    fb.flightDestinationChecker(flightDestinationField.getText(), flightOriginField.getText()) &&
                    fb.flightDepartureChecker(flightDepartureField.getText()) &&
                    fb.flightDesignatedAirplaneChecker(flightDesignatedAirplaneField.getText())) {

                registerFlight(flightOriginField, flightDestinationField, flightDepartureField, flightDesignatedAirplaneField);
                clearFields();
                JOptionPane.showMessageDialog(null, "Flight saved with success!");
            } else {
                System.out.println("Failed to verify the flight.");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "One or more required fields are empty.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void registerFlight (TextField origin, TextField destination, TextField departure, TextField designatedAirplane) {
        FlightBusiness fb = new FlightBusiness();


        Flight f = new Flight(
                origin.getText(),
                destination.getText(),
                fb.timeParser(departure.getText()),
                Integer.parseInt(designatedAirplane.getText()));
        FlightDao fDao = new FlightDao();
        fDao.createFlight(f);
    }

    private void clearFields () {
        flightOriginField.setText("");
        flightDestinationField.setText("");
        flightDepartureField.setText("");
        flightDesignatedAirplaneField.setText("");
    }

    private boolean notEmptyField (TextField toTest) {
        if (toTest.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
