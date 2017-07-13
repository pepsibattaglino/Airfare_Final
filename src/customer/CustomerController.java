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

    @FXML
    void treatTabBtnRefresh(ActionEvent event) {
        System.out.println("The Refresh button has been pressed.");
        customerTab.refresh();
    }
}