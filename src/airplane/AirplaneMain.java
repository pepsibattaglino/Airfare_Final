package airplane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

import static javafx.application.Application.launch;

/**
 * Created by Bernardo on 29/06/2017.
 */
public class AirplaneMain extends Application{
    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("AirplaneViewOlder.fxml"));
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Register Airplane");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AirplaneTable.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Airplane Log");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
