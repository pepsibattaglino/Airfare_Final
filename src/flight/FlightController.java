package flight;

/**
 * Created by Bernardo on 13/07/2017.
 */
import airplane.Airplane;
import airplane.AirplaneBusiness;
import airplane.AirplaneDao;
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

    /**
     * UTILITIES
     */

    // Specific
    private TextField[] registerFields = new TextField[]{flightManRegTfOrigin, flightManRegTfDestination, flightManRegTfDepartureTime, flightManRegTfDesignatedPlaneId};
    private TextField[] editFields = new TextField[]{flightManEditTfOrigin, flightManEditTfDestination, flightManEditTfDepartureTime, flightManEditTfDesignatedPlane};
    private TextField editSurvey = flightManEditTfId;
    private TextField deleteSurvey = flightManDelTfId;
    private Label[] deleteLabels = new Label[]{flightManDelLabOrigin, flightManDelLabDestination, flightManDelLabDepartureTime, flightManDelLabDesignatedAirplane};

    private void refresh(){
        registerFields = new TextField[]{flightManRegTfOrigin, flightManRegTfDestination, flightManRegTfDepartureTime, flightManRegTfDesignatedPlaneId};
        editFields = new TextField[]{flightManEditTfOrigin, flightManEditTfDestination, flightManEditTfDepartureTime, flightManEditTfDesignatedPlane};
        editSurvey = flightManEditTfId;
        deleteSurvey = flightManDelTfId;
        deleteLabels = new Label[]{flightManDelLabOrigin, flightManDelLabDestination, flightManDelLabDepartureTime, flightManDelLabDesignatedAirplane};
    }

    private boolean allRulesComplied(TextField[] listField) {
        FlightBusiness fb = new FlightBusiness();
        if (
                fb.flightOriginChecker(listField[0].getText()) &&
                fb.flightDestinationChecker(listField[1].getText(), listField[0].getText()) &&
                fb.flightDepartureChecker(listField[2].getText()) &&
                fb.flightDesignatedAirplaneChecker(listField[3].getText()))
        {
            return true;
        } else {
            return false;
        }
    }

    private void registerObject(TextField[] listField) {
        FlightBusiness fb = new FlightBusiness();
        Flight f = new Flight(
                listField[0].getText(),
                listField[1].getText(),
                fb.timeParser(listField[2].getText()),
                Integer.parseInt(listField[3].getText()));

        FlightDao fDao = new FlightDao();
        fDao.createFlight(f);
    }

    private void editObject(TextField[] listField) {    ////////////////////////////////
        FlightBusiness fb = new FlightBusiness();
        Flight f = new Flight(
                listField[0].getText(),
                listField[1].getText(),
                fb.timeParser(listField[2].getText()),
                Integer.parseInt(listField[3].getText()));

        FlightDao fDao = new FlightDao();
        fDao.updateFlights(f);
    }

    private void delete(Flight flight) {
        FlightDao fDao = new FlightDao();
        fDao.deleteFlights(flight);
    }

//    private boolean existsOnDbArray(TextField[] listField) {
//        AirplaneDao aDao = new AirplaneDao();
//        String idTest = listField[0].getText();
//        Airplane test = aDao.locateAirplaneByCode(idTest);
//        if (aDao.isNotEmpty(test)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    private boolean existsOnDb(TextField listField) {
        FlightDao fDao = new FlightDao();
        String idTest = listField.getText();
        Flight test = fDao.locateFlightById(idTest);
        if (fDao.isNotEmpty(test)) {
            return true;
        } else {
            return false;
        }
    }

    private Flight findById(String id) {
        FlightDao fDao = new FlightDao();
        return fDao.locateFlightById(id);
    }

    private void fillFields(Flight flight) {
        editFields[0].setText(flight.getOrigin()); // origin
        editFields[1].setText(flight.getDestination()); // destination
        editFields[2].setText(String.valueOf(flight.getDeparture())); // departure  <<<<<<<<<<<<<<<<<<<<<<
        editFields[3].setText(String.valueOf(flight.getDesignatedPlane())); // plane
    }

    private void fillLabels(Flight flight) {
        deleteLabels[0].setText(flight.getOrigin()); // origin
        deleteLabels[1].setText(flight.getDestination()); // destination
        deleteLabels[2].setText(String.valueOf(flight.getDeparture())); // departure  <<<<<<<<<<<<<<<<<<<<<<
        deleteLabels[3].setText(String.valueOf(flight.getDesignatedPlane())); // plane
    }

    private void clearLabels() {
        deleteLabels[0].setText("");
        deleteLabels[1].setText("");
        deleteLabels[2].setText("");
        deleteLabels[3].setText("");
    }

    // Generic

    private TextField[] toAry(TextField tf) { // <<<<<<< Check if needed
        TextField[] atf = new TextField[]{tf};
        return atf;
    }

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
        refresh();
        if(allFieldsFilled(registerFields)) {           // Check for empty fields
            if (allRulesComplied(registerFields)) {     // Check for rule compliance
                registerObject(registerFields);
                clearFields(registerFields);
            }
        } else {
            eMes("Could not save", "There is at least one empty required field.");
        }
    }

    public void btnClearFieldsRegister(ActionEvent event) {
        refresh();
        clearFields(registerFields);
    }

    /**
     * EDIT
     */

    public void btnActionSurveyEdit(ActionEvent event) {
        refresh();

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
        refresh();
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
        refresh();
        clearFields(editFields);
        clearField(editSurvey);
    }

    /**
     * DELETE
     */

    public void btnActionSurveyDelete(ActionEvent event) {
        refresh();
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
        refresh();
        delete(findById(deleteSurvey.getText()));
        clearField(deleteSurvey);
        clearLabels();
    }

}