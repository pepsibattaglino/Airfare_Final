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

    Stage window;
    Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Register Airplane");

        //Form
        Label codeLabel = new Label("Code:");
        TextField codeInput = new TextField();
        Label modelLabel = new Label("Model:");
        TextField modelInput = new TextField();
        Label seatsLabel = new Label("Seats:");
        TextField seatsInput = new TextField();

        /*Button clearButton = new Button("Clear");
        clearButton.setOnAction( e -> isInt(seatsInput));*/

        Button saveButton = new Button("Save");
        saveButton.setOnAction( e -> planeCreator(codeInput, modelInput, seatsInput));

        //Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(
                codeLabel,
                codeInput,
                modelLabel,
                modelInput,
                seatsLabel,
                seatsInput,
                //clearButton,
                saveButton
        );

        scene = new Scene(layout, 300, 290);
        window.setScene(scene);
        window.show();
    }

    private boolean isInt(TextField input){
        try{
            int test = Integer.parseInt(input.getText());
            System.out.println("Seats: " + test);
            return true;
        }catch(NumberFormatException e){
            System.out.println("Error: " + input.getText() + " is not a number.");
            return false;
        }
    }

    private void planeCreator(TextField cI, TextField mI, TextField sI){
        if (isInt(sI)){
            int sInt = Integer.parseInt(sI.getText());
            Airplane a = new Airplane(cI.getText(), mI.getText(), sInt);
            System.out.println("Plane saved successfully. Code: " + a.getCode() +" Model: "+ a.getModel() +" Seats: "+ a.getQntSeats());
            AirplaneDao aD = new AirplaneDao();
            aD.createAirplane(a);
            JOptionPane.showMessageDialog(null,
                    "Plane saved successfully");
        } else {
            System.out.println("The plane could not be saved.");
            JOptionPane.showMessageDialog(null,
                    "The plane could not be saved.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
