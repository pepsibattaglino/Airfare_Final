package sale;

/**
 * Created by Bernardo on 13/07/2017.
 */
import flight.Flight;
import flight.FlightDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static messages.MessageController.eMes;

public class SaleController implements Initializable {

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

    @FXML
    private TableView<Sale> saleTab;

    @FXML
    private TableColumn<Sale, String> saleTabClnId;

    @FXML
    private TableColumn<Sale, String> saleTabClnCustomerName;

    @FXML
    private TableColumn<Sale, String> saleTabClnPlaneModel;

    @FXML
    private TableColumn<Sale, String> saleTabClnTimeOfPurchase;



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

    private List<Sale> listSales;
    private ObservableList<Sale> observableListSales;
    private SaleDao sDao = new SaleDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listSales = new ArrayList<>();
            listSales = sDao.listAllSales();
            loadTableViewSales();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableViewSales() {
        saleTabClnId.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        saleTabClnCustomerName.setCellValueFactory(new PropertyValueFactory<>("saleCustomer"));
        saleTabClnPlaneModel.setCellValueFactory(new PropertyValueFactory<>("saleFlight"));
        saleTabClnTimeOfPurchase.setCellValueFactory(new PropertyValueFactory<>("timeOfPurchase"));

        observableListSales = FXCollections.observableArrayList(listSales);
        saleTab.setItems(observableListSales);
    }


}
