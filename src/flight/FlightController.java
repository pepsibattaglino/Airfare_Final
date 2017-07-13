package flight;

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

public class FlightController implements Initializable {

    @FXML
    private TextField flightManRegTfOrigin;

    @FXML
    private TextField flightManRegTfDestination;

    @FXML
    private TextField flightManRegTfDepartureTime;

    @FXML
    private TextField flightManRegTfDesignatedPlaneId;

    @FXML
    private Button flightManRegBtnClear;

    @FXML
    private Button flightManRegBtnSave;

    @FXML
    private TextField flightManEditTfId;

    @FXML
    private Button flightManEditBtnSurvey;

    @FXML
    private TextField flightManEditTfOrigin;

    @FXML
    private TextField flightManEditTfDestination;

    @FXML
    private TextField flightManEditTfDepartureTime;

    @FXML
    private TextField flightManEditTfDesignatedPlane;

    @FXML
    private Button flightManEditBtnClear;

    @FXML
    private Button flightManEditBtnSave;

    @FXML
    private TextField flightManDelTfId;

    @FXML
    private Button flightManDelBtnSurvey;

    @FXML
    private Label flightManDelLabOrigin;

    @FXML
    private Label flightManDelLabDestination;

    @FXML
    private Label flightManDelLabDepartureTime;

    @FXML
    private Label flightManDelLabDesignatedAirplane;

    @FXML
    private Button flightManDelBtnDelete;

    @FXML
    private TableView<Flight> flightTab;

    @FXML
    private TableColumn<Flight, String> flightTabClnID;

    @FXML
    private TableColumn<Flight, String> flightTabClnOrigin;

    @FXML
    private TableColumn<Flight, String> flightTabClnDestination;

    @FXML
    private TableColumn<Flight, String> flightTabClnDepartureTime;

    @FXML
    private TableColumn<Flight, String> flightTabClnDesignatedAirplane;

    @FXML
    private TableColumn<Flight, String> flightTabClnAvailableSeats;

    @FXML
    private Button flightTabBtnRefresh;

    private List<Flight> listFlighs;
    private ObservableList<Flight> observableListFlights;
    private FlightDao fDao = new FlightDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listFlighs = new ArrayList<>();
            listFlighs = fDao.listAllFlights();
            loadTableViewFlights();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableViewFlights() {
        flightTabClnID.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        flightTabClnOrigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
        flightTabClnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        flightTabClnDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departure"));
        flightTabClnDesignatedAirplane.setCellValueFactory(new PropertyValueFactory<>("designatedPlane"));
        flightTabClnAvailableSeats.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));

        observableListFlights = FXCollections.observableArrayList(listFlighs);
        flightTab.setItems(observableListFlights);
    }

    @FXML
    void treatTabBtnRefresh(ActionEvent event) {
        System.out.println("The Refresh button has been pressed.");
        flightTab.refresh();
    }

}