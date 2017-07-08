package customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;

/**
 * Created by G.Battaglino on 08/07/2017.
 */
public class CustomerController {

   /* @FXML
    private VBox formCustomer;*/

    @FXML
    private TextField customerIdentificationField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerPhoneField;

   /* @FXML
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
                notEmptyField(customerIdentificationField) &&
                notEmptyField(customerNameField) &&
                notEmptyField(customerPhoneField)) {

            CustomerBusiness cb = new CustomerBusiness();
            if (
                    cb.customerIdentificationChecker(customerIdentificationField.getText()) &&
                    cb.customerNameChecker(customerNameField.getText()) &&
                    cb.customerPhoneChecker(customerPhoneField.getText())) {

                registerCustomer(customerIdentificationField, customerNameField, customerPhoneField);
                clearFields();
                JOptionPane.showMessageDialog(null, "Customer saved with success!");
            } else {
                System.out.println("Failed to verify the customer.");
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "One or more required fields are empty.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void registerCustomer (TextField identification, TextField name, TextField phone) {
        Customer c = new Customer(
                identification.getText(),
                name.getText(),
                phone.getText());
        CustomerDao cDao = new CustomerDao();
        cDao.createCustomer(c);
    }

    private void clearFields () {
        customerIdentificationField.setText("");
        customerNameField.setText("");
        customerPhoneField.setText("");
    }

    private boolean notEmptyField (TextField toTest) {
        if (toTest.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}