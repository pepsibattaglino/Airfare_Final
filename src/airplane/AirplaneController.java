package airplane;

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
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static messages.MessageController.eMes;

public class AirplaneController implements Initializable{

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
    private TableView<Airplane> airplaneTab;

    @FXML
    private TableColumn<Airplane, String> airplaneTabClnId;

    @FXML
    private TableColumn<Airplane, String> airplaneTabClnCode;

    @FXML
    private TableColumn<Airplane, String> airplaneTabClnModel;

    @FXML
    private TableColumn<Airplane, String> airplaneTabClnQntSeats;

    @FXML
    private Button airplaneTabBtnRefresh;

    /**
     * UTILITIES
     */

    // Specific
    private TextField[] registerFields = new TextField[]{airplaneManRegTfCode, airplaneManRegTfModel, airplaneManRegTfQntSeat};
    private TextField[] editFields = new TextField[]{airplaneManEditTfCode, airplaneManEditTfModel, airplaneManEditTfQntSeats};
    private TextField editSurvey = airplaneManEditTfId;
    private TextField deleteSurvey = airplaneManDelTfId;
    private Label[] deleteLabels = new Label[]{airplaneManDelLabCode, airplaneManDelLabModel, airplaneManDelLabQntSeats};

    private void refresh(){
        registerFields = new TextField[]{airplaneManRegTfCode, airplaneManRegTfModel, airplaneManRegTfQntSeat};
        editFields = new TextField[]{airplaneManEditTfCode, airplaneManEditTfModel, airplaneManEditTfQntSeats};
        editSurvey = airplaneManEditTfId;
        deleteSurvey = airplaneManDelTfId;
        deleteLabels = new Label[]{airplaneManDelLabCode, airplaneManDelLabModel, airplaneManDelLabQntSeats};
    }

    private boolean allRulesComplied(TextField[] listField) {
        AirplaneBusiness ab = new AirplaneBusiness();
        if (
                ab.airplaneCodeChecker(listField[0].getText()) &&
                ab.airplaneModelChecker(listField[1].getText()) &&
                ab.airplaneQntSeatsChecker(listField[2].getText()))
        {
            return true;
        } else {
            return false;
        }
    }

    private void registerObject(TextField[] listField) {
        Airplane a = new Airplane(
                listField[0].getText(),
                listField[1].getText(),
                Integer.parseInt(listField[2].getText()));
        AirplaneDao aDao = new AirplaneDao();

        System.out.println(a.getAirplaneID() + " - " + a.getCode() + " - " + a.getModel() + " - " + a.getQntSeats());

        aDao.createAirplane(a);
    }

    private void editObject(TextField[] listField) {    ////////////////////////////////
        Airplane a = new Airplane(
                Integer.parseInt(editSurvey.getText()),
                listField[0].getText(),
                listField[1].getText(),
                Integer.parseInt(listField[2].getText()));
        AirplaneDao aDao = new AirplaneDao();
        aDao.updateAirplane(a);
    }

    private void delete(Airplane airplane) {
        AirplaneDao aDao = new AirplaneDao();
        aDao.deleteAirplane(airplane);
    }

    private boolean existsOnDbArray(TextField[] listField) {
        AirplaneDao aDao = new AirplaneDao();
        String idTest = listField[0].getText();
        Airplane test = aDao.locateAirplaneByCode(idTest);
        if (aDao.isNotEmpty(test)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean existsOnDb(TextField listField) {
        AirplaneDao aDao = new AirplaneDao();
        String idTest = listField.getText();
        Airplane test = aDao.locateAirplaneById(idTest);
        if (aDao.isNotEmpty(test)) {
            return true;
        } else {
            return false;
        }
    }

    private Airplane findById(String id) {
        AirplaneDao aDao = new AirplaneDao();
        return aDao.locateAirplaneById(id);
    }

    private void fillFields(Airplane airplane) {
        editFields[0].setText(airplane.getCode());
        editFields[1].setText(airplane.getModel());
        editFields[2].setText(String.valueOf(airplane.getQntSeats()));
    }

    private void fillLabels(Airplane airplane) {
        deleteLabels[0].setText(airplane.getCode());
        deleteLabels[1].setText(airplane.getModel());
        deleteLabels[2].setText(String.valueOf(airplane.getQntSeats()));
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
        refresh();
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


    public void btnActionRefresh(ActionEvent event) {
        System.out.println("The Refresh button has been pressed.");
    }


    private List<Airplane> listAirplanes;
    private ObservableList<Airplane> observableListAirplanes;
    private AirplaneDao aDao = new AirplaneDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listAirplanes = new ArrayList<>();
            listAirplanes = aDao.listAllAirplanes();
            loadTableViewAirplanes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTableViewAirplanes() {
        airplaneTabClnId.setCellValueFactory(new PropertyValueFactory<>("airplaneID"));
        airplaneTabClnCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        airplaneTabClnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        airplaneTabClnQntSeats.setCellValueFactory(new PropertyValueFactory<>("qntSeats"));

        observableListAirplanes = FXCollections.observableArrayList(listAirplanes);
        airplaneTab.setItems(observableListAirplanes);
    }

//    @FXML
//    void treatTabBtnRefresh(ActionEvent event) {
//        System.out.println("The Refresh button has been pressed.");
//        airplaneTab.refresh();
//    }


}