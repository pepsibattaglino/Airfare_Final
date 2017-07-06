package airplane;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bernardo on 29/06/2017.
 */
public class AirplaneController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AirplaneDao airplaneDao = new AirplaneDao();
    }

    @FXML
    private AnchorPane registerAirplanePane;
    @FXML
    private TextField fieldCode;
    @FXML
    private TextField fieldModel;
    @FXML
    private TextField fieldQntSeats;

    @FXML
    protected void treatSaveButton() {
        System.out.println("The Save button has been pressed.");
    }




/*
    @FXML
    public void treatSaveButton(ActionEvent event) throws Exception {
        Stage stage = (Stage) registerAirplanePane.getScene().getWindow();

        try {
            () -> airplaneDao
            AirplaneDao.(new Paciente(
                    textFieldRg.getText(),
                    textFieldNome.getText(),
                    datePickerDataNascimento.getValue()
            ));
            PrintUtil.printMessageSucesso("Cadastro realizado com sucesso!");
            limparCampos();
        } catch (NegocioException ex) {
            PrintUtil.printMessageError(ex.getMessage());
        }

    }

    @FXML
    public void tratarBotaoCancelar(ActionEvent event) throws Exception {
        Stage stage = (Stage) paneAirplaneRegister.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clearForm(ActionEvent event) throws Exception {
        fieldCode.clear();
        fieldModel.clear();
        fieldQntSeats.clear();
    }*/

}