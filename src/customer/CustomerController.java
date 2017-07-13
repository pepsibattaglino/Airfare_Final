package customer;

/**
 * Created by Bernardo on 13/07/2017.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerController {

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
    private TableView<?> customerTab;

    @FXML
    private TableColumn<?, ?> customerTabClnId;

    @FXML
    private TableColumn<?, ?> customerTabClnIdentification;

    @FXML
    private TableColumn<?, ?> customerTabClnName;

    @FXML
    private TableColumn<?, ?> customerTabClnPhone;

    @FXML
    private Button customerTabBtnRefresh;

}

