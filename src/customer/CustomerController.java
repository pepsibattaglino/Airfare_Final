package customer;

/**
 * Created by Bernardo on 13/07/2017.
 */
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static messages.MessageController.eMes;

public class CustomerController implements Initializable {

    @FXML
    private TextField customerManRegTfIdentification;

    @FXML
    private TextField customerManRegTfName;

    @FXML
    private TextField customerManRegTfPhone;

    @FXML
    private Button customerManRegBtnClear;

    @FXML
    private Button customerManRegBtnSave;

    @FXML
    private TextField customerManEditTfId;

    @FXML
    private Button customerManEditBtnSurvey;

    @FXML
    private TextField customerManEditTfIdentification;

    @FXML
    private TextField customerManEditTfName;

    @FXML
    private TextField customerManEditTfPhone;

    @FXML
    private Button customerManEditBtnClear;

    @FXML
    private Button customerManEditBtnSave;

    @FXML
    private TextField customerManDelTfId;

    @FXML
    private Button customerManDelBtnSurvey;

    @FXML
    private Label customerManDelLabIdentification;

    @FXML
    private Label customerManDelLabName;

    @FXML
    private Label customerManDelLabPhone;

    @FXML
    private Button customerManDelBtnDel;

    @FXML
    private TableView<Customer> customerTab;

    @FXML
    private TableColumn<Customer, String> customerTabClnId;

    @FXML
    private TableColumn<Customer, String> customerTabClnIdentification;

    @FXML
    private TableColumn<Customer, String> customerTabClnName;

    @FXML
    private TableColumn<Customer, String> customerTabClnPhone;

    @FXML
    private Button customerTabBtnRefresh;

    @FXML
    void treatTabBtnRefresh(ActionEvent event) {
        System.out.println("The Refresh button has been pressed.");
        customerTab.refresh();
    }

    //==================================================
    /**
     * UTILITIES
     */

    // Specific
    private TextField[] registerFields = new TextField[]{customerManRegTfIdentification, customerManRegTfName, customerManRegTfPhone};
    private TextField[] editFields = new TextField[]{customerManEditTfIdentification, customerManEditTfName, customerManEditTfPhone};
    private TextField editSurvey = customerManEditTfId;
    private TextField deleteSurvey = customerManDelTfId;
    private Label[] deleteLabels = new Label[]{customerManDelLabIdentification, customerManDelLabName, customerManDelLabPhone};

    private void refreshBernardo(){
        registerFields = new TextField[]{customerManRegTfIdentification, customerManRegTfName, customerManRegTfPhone};
        editFields = new TextField[]{customerManEditTfIdentification, customerManEditTfName, customerManEditTfPhone};
        editSurvey = customerManEditTfId;
        deleteSurvey = customerManDelTfId;
        deleteLabels = new Label[]{customerManDelLabIdentification, customerManDelLabName, customerManDelLabPhone};
    }

    private boolean allRulesComplied(TextField[] listField) {
        CustomerBusiness cb = new CustomerBusiness();
        if (
                cb.customerIdentificationChecker(listField[0].getText()) &&
                        cb.customerNameChecker(listField[1].getText()) &&
                        cb.customerPhoneChecker(listField[2].getText()))
        {
            return true;
        } else {
            return false;
        }
    }

    private void registerObject(TextField[] listField) {
        Customer c = new Customer(
                listField[0].getText(),
                listField[1].getText(),
                listField[2].getText());
        CustomerDao cDao = new CustomerDao();

        System.out.println(c.getCustomerID() + " - " + c.getIdentification() + " - " + c.getCustomerName() + " - " + c.getPhone());

        cDao.createCustomer(c);
    }

    private void editObject(TextField[] listField) {    ////////////////////////////////
        Customer c = new Customer(
                Integer.parseInt(editSurvey.getText()),
                listField[0].getText(),
                listField[1].getText(),
                listField[2].getText());
        CustomerDao cDao = new CustomerDao();
        cDao.updateCustomer(c);
    }

    private void delete(Customer customer) {
        CustomerDao cDao = new CustomerDao();
        cDao.deleteCustomer(customer);
    }

    private boolean existsOnDbArray(TextField[] listField) {
        CustomerDao cDao = new CustomerDao();
        String idTest = listField[0].getText();
        Customer test = cDao.locateCustomerByIdentification(idTest);
        if (cDao.isNotEmpty(test)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean existsOnDb(TextField listField) {
        CustomerDao cDao = new CustomerDao();
        String idTest = listField.getText();
        Customer test = cDao.locateCustomerById(idTest);
        if (cDao.isNotEmpty(test)) {
            return true;
        } else {
            return false;
        }
    }

    private Customer findById(String id) {
        CustomerDao cDao = new CustomerDao();
        return cDao.locateCustomerById(id);
    }

    private void fillFields(Customer customer) {
        editFields[0].setText(customer.getIdentification());
        editFields[1].setText(customer.getCustomerName());
        editFields[2].setText(customer.getPhone());
    }

    private void fillLabels(Customer customer) {
        deleteLabels[0].setText(customer.getIdentification());
        deleteLabels[1].setText(customer.getCustomerName());
        deleteLabels[2].setText(customer.getPhone());
    }

    private void clearLabels() {
        deleteLabels[0].setText("");
        deleteLabels[1].setText("");
        deleteLabels[2].setText("");
    }

    private TextField[] toAry(TextField tf) { // <<<<<<< Check if needed
        TextField[] atf = new TextField[]{tf};
        return atf;
    }

    // Generic
    private boolean fieldIsEmpty(TextField toTest) {
        if (toTest.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean allFieldsFilled(TextField[] listField) {
        boolean result = true;
        for (TextField t : listField) {
            if (fieldIsEmpty(t)){
                result = false;
            }
        }
        if (result == false) {
            return false;
        } else {
            return true;
        }
    }

    private void clearFields(TextField[] listField) {
        for (TextField t : listField) {
            t.setText("");
        }
    }

    private void clearField(TextField field) {
        field.setText("");
    }

    /**
     * REGISTER
     */

    public void btnActionSaveRegister(ActionEvent event) {
        refreshBernardo();
        if(allFieldsFilled(registerFields)) {           // Check for empty fields
            if (allRulesComplied(registerFields)) {     // Check for rule compliance
                if (!existsOnDbArray(registerFields)) { // Check for repetition on DB
                    registerObject(registerFields);
                    clearFields(registerFields);
                } else {
                    eMes("Could not save", "This register already exixts on the database.");
                }
            }
        } else {
            eMes("Could not save", "There is at least one empty required field.");
        }
    }

    public void btnClearFieldsRegister(ActionEvent event) {
        refreshBernardo();
        clearFields(registerFields);
    }

    /**
     * EDIT
     */

    public void btnActionSurveyEdit(ActionEvent event) {
        refreshBernardo();

        if (allFieldsFilled(toAry(editSurvey))) {
            if (existsOnDb(editSurvey)) {
                fillFields(findById(editSurvey.getText()));
            } else {
                eMes("Survey error", "The provided ID was not found on the database.");
            }
        } else {
            eMes("Empty field", "Please inform an ID on the survey field.");
        }
    }

    public void btnActionSaveEdit(ActionEvent event) {
        refreshBernardo();
        if(allFieldsFilled(editFields)) {           // Check for empty fields
            if (allRulesComplied(editFields)) {     // Check for rule compliance
                editObject(editFields);
                clearFields(editFields);
                clearField(editSurvey);
            }
        } else {
            eMes("Could not save", "There is at least one empty required field.");
        }
    }

    public void btnClearFieldsEdit(ActionEvent event) {
        refreshBernardo();
        clearFields(editFields);
        clearField(editSurvey);
    }

    /**
     * DELETE
     */

    public void btnActionSurveyDelete(ActionEvent event) {
        refreshBernardo();
        if (allFieldsFilled(toAry(deleteSurvey))) {
            if (existsOnDb(deleteSurvey)) {
                fillLabels(findById(deleteSurvey.getText()));
            } else {
                eMes("Survey error", "The provided ID was not found on the database.");
            }
        } else {
            eMes("Empty field", "Please inform an ID on the survey field.");
        }
    }

    public void btnActionDelete(ActionEvent event) {
        refreshBernardo();
        delete(findById(deleteSurvey.getText()));
        clearField(deleteSurvey);
        clearLabels();
    }

    private List<Customer> listCustomers;
    private ObservableList<Customer> observableListCustomers;
    private CustomerDao cDao = new CustomerDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listCustomers = new ArrayList<>();
            listCustomers = cDao.listAllCustomers();
            loadTableViewCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableViewCustomers() {
        customerTabClnId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerTabClnIdentification.setCellValueFactory(new PropertyValueFactory<>("identification"));
        customerTabClnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTabClnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        observableListCustomers = FXCollections.observableArrayList(listCustomers);
        customerTab.setItems(observableListCustomers);
    }

}