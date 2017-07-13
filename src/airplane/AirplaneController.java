package airplane;

/**
 * Created by Bernardo on 13/07/2017.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AirplaneController {

    @FXML
    private TextField airplaneManRegTfCode;

    @FXML
    private TextField airplaneManRegTfModel;

    @FXML
    private TextField airplaneManRegTfQntSeat;

    @FXML
    private Button airplaneManRegBtnClear;

    @FXML
    private Button airplaneManRegBtnSave;

    @FXML
    private TextField airplaneManEditTfId;

    @FXML
    private Button airplaneManEditBtnSurvey;

    @FXML
    private TextField airplaneManEditTfCode;

    @FXML
    private TextField airplaneManEditTfModel;

    @FXML
    private TextField airplaneManEditTfQntSeats;

    @FXML
    private Button airplaneManEditBtnClear;

    @FXML
    private Button airplaneManEditBtnSave;

    @FXML
    private TextField airplaneManDelTfId;

    @FXML
    private Button airplaneManDelBtnSurvey;

    @FXML
    private Label airplaneManDelLabCode;

    @FXML
    private Label airplaneManDelLabModel;

    @FXML
    private Label airplaneManDelLabQntSeats;

    @FXML
    private Button airplaneManDelBtnDel;

    @FXML
    private TableView<?> airplaneTab;

    @FXML
    private TableColumn<?, ?> airplaneTabClnId;

    @FXML
    private TableColumn<?, ?> airplaneTabClnCode;

    @FXML
    private TableColumn<?, ?> airplaneTabClnModel;

    @FXML
    private TableColumn<?, ?> airplaneTabClnQntSeats;

    @FXML
    private Button airplaneTabBtnRefresh;

}