package sale;

import flight.Flight;
import flight.FlightDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class SaleControllerOlder {

    /*@FXML
    private VBox formSale;*/

    @FXML
    private TextField saleCustomerField;

    @FXML
    private TextField saleFlightField;

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

        if (notEmptyField(saleCustomerField) &&
            notEmptyField(saleFlightField)) {

            SaleBusiness sb = new SaleBusiness();

            if (sb.saleCustomerChecker(saleCustomerField.getText()) &&
                sb.saleFlightChecker(saleFlightField.getText())) {

                registerSale(saleCustomerField, saleFlightField);
                clearFields();
                JOptionPane.showMessageDialog(null,
                        "Sale saved with success!");

            } else {

                System.out.println("Failed to verify the Sale.");

            }

        } else {

            JOptionPane.showMessageDialog(null,
                    "One or more required fields are empty.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

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

    private void clearFields () {

        saleCustomerField.setText("");

        saleFlightField.setText("");

    }

    private boolean notEmptyField (TextField toTest) {

        if (toTest.getText().isEmpty()) {

            return false;

        } else {

            return true;

        }
    }

}