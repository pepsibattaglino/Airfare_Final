package customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by G.Battaglino on 09/07/2017.
 */
public class CustomerTableController implements Initializable {

    //-------------- Table ------------------------------------
    @FXML
    private Label winTitle;

    @FXML
    private TableView<Customer> tableCustomer;

    @FXML
    private TableColumn<Customer, String> clnIdentification;

    @FXML
    private TableColumn<Customer, String> clnName;

    @FXML
    private TableColumn<Customer, String> clnPhone;

    @FXML
    private Button btnTableReload;

    @FXML
    private Button btnTableClear;



    //-------------- Form ------------------------------------
    @FXML
    private VBox form;

    @FXML
    private Label formTitle;

    @FXML
    private TextField fieldIdentification;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldPhone;

    @FXML
    private Button btnFormSave;

    @FXML
    private Button btnFormClear;

    @FXML
    void treatBtnFormClear(ActionEvent event) {
        System.out.println("The Clear button has been pressed.");
        clearFields();
    }

    @FXML
    void treatBtnFromSave(ActionEvent event) {
        System.out.println("The Save button has been pressed.");
        if (notEmptyField(fieldIdentification) &&
                notEmptyField(fieldName) &&
                notEmptyField(fieldPhone)) {
            CustomerTableBusiness ctb = new CustomerTableBusiness();
            if (ctb.customerIdentificationChecker(fieldIdentification.getText()) &&
                    ctb.customerNameChecker(fieldName.getText()) &&
                    ctb.customerPhoneChecker(fieldPhone.getText())) {
                registerCustomer(fieldIdentification, fieldName, fieldPhone);
                clearFields();
                JOptionPane.showMessageDialog(null,
                        "Customer saved with success!");
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
        CustomerTableDao cTDao = new CustomerTableDao();
        cTDao.createCustomer(c);
    }

    private void clearFields () {
        fieldIdentification.setText("");
        fieldName.setText("");
        fieldPhone.setText("");
    }

    private boolean notEmptyField (TextField toTest) {
        if (toTest.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private List<Customer> listCustomers;
    private ObservableList<Customer> observableListCustomers;
    private CustomerTableDao cTDao = new CustomerTableDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listCustomers = new ArrayList<>();
            listCustomers = cTDao.listAllCustomers();
            loadTableViewCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableViewCustomers() {
        clnIdentification.setCellValueFactory(new PropertyValueFactory<>("identification"));
        clnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        clnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        observableListCustomers = FXCollections.observableArrayList(listCustomers);
        tableCustomer.setItems(observableListCustomers);
    }

}