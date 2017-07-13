package flight;

/**
 * Created by Bernardo on 13/07/2017.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class FlightController {

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
    private TableView<?> flightTab;

    @FXML
    private TableColumn<?, ?> flightTabClnID;

    @FXML
    private TableColumn<?, ?> flightTabClnOrigin;

    @FXML
    private TableColumn<?, ?> flightTabClnDestination;

    @FXML
    private TableColumn<?, ?> flightTabClnDepartureTime;

    @FXML
    private TableColumn<?, ?> flightTabClnDesignatedAirplane;

    @FXML
    private TableColumn<?, ?> flightTabClnAvailableSeats;

    @FXML
    private Button flightTabBtnRefresh;

}
