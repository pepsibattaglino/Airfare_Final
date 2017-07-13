package sale;

/**
 * Created by Bernardo on 13/07/2017.
 */
import flight.Flight;
import flight.FlightDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;

import java.time.LocalDateTime;

import static messages.MessageController.eMes;

public class SaleController {

    @FXML
    private TextField saleClientInput;

    @FXML
    private TextField saleFlightInput;

    @FXML
    private Button saleBtnClear;

    @FXML
    private Button saleBtnSave;

    @FXML
    private Button saleBtnRefresh;



    public void treatSaveButton(ActionEvent event) {
        System.out.println("The Save button has been pressed.");
        if (notEmptyField(saleClientInput) && notEmptyField(saleFlightInput)) {
            SaleBusiness sb = new SaleBusiness();
            if (sb.saleCustomerChecker(saleClientInput.getText()) && sb.saleFlightChecker(saleFlightInput.getText())) {

                registerSale(saleClientInput, saleFlightInput);
                clearFields();
                eMes("Success!", "Sale saved with success!");
            } else {
                System.out.println("Failed to verify the Sale.");
            }
        } else {
            eMes("Error", "One or more required fields are empty.");
        }
    }

    private void registerSale (TextField saleCustomer, TextField saleFlight) {
        SaleDao sDao = new SaleDao();
        Sale s = new Sale(
                Integer.parseInt(saleCustomer.getText()),
                Integer.parseInt(saleFlight.getText()),
                LocalDateTime.now());

        sDao.createSale(s);
        FlightDao fDao = new FlightDao();
        Flight sFlight = fDao.locateFlightById(saleFlight.getText());
        sFlight.setAvailableSeats(sFlight.getAvailableSeats()-1);
        fDao.updateFlights(sFlight);
    }

    private boolean notEmptyField (TextField toTest) {
        if (toTest.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void treatClearButton(ActionEvent event) {
        System.out.println("The Clear button has been pressed.");
        clearFields();
    }

    private void clearFields() {
        saleClientInput.setText("");
        saleFlightInput.setText("");
    }

}
