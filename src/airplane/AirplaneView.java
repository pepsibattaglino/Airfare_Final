package airplane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Bernardo on 29/06/2017.
 */
public class AirplaneView  extends Application {

    Stage window;
    Scene scene;
    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Register Airplane");

        //Form
        TextField codeInput = new TextField();

        button = new Button("Print it");
        button.setOnAction( e -> System.out.println(codeInput.getText()));

    }


}