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

import java.awt.event.ActionEvent;

import static messages.MessageController.eMes;

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

    /**
     * UTILITIES
     */

    // Specific
    private TextField[] registerFields = new TextField[]{airplaneManRegTfCode, airplaneManRegTfModel, airplaneManRegTfQntSeat};
    private TextField[] editFields = new TextField[]{airplaneManEditTfCode, airplaneManEditTfModel, airplaneManEditTfQntSeats};
    private TextField editSurvey = airplaneManEditTfId;
    private TextField deleteSurvey = airplaneManDelTfId;

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
        aDao.createAirplane(a);
    }

    private boolean existsOnDbArray(TextField[] listField) {
        return existsOnDb(listField[0]);
    }

    private boolean existsOnDb(TextField listField) {
        AirplaneDao aDao = new AirplaneDao();
        Airplane test = aDao.locateAirplaneByCode(listField.getText());
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

    /**
     * REGISTER
     */

    public void btnActionSave(ActionEvent event) {
        if(allFieldsFilled(registerFields)) {           // Check for empty fields
            if (allRulesComplied(registerFields)) {     // Check for rule compliance
                if (!existsOnDbArray(registerFields)) { // Check for repetition on DB
                    registerObject(registerFields);
                } else {
                    eMes("Could not save", "This register already exixts on the database.");
                }
            }
        } else {
            eMes("Could not save", "There is at least one empty required field.");
        }
    }

    public void btnClearFields(ActionEvent event) {
        clearFields(registerFields);
    }

    /**
     * EDIT
     */

    public void btnActionSurvey(ActionEvent event) {
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

    }

}