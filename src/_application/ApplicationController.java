package _application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Bernardo on 12/07/2017.
 */
public class ApplicationController {

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnSales;

    @FXML
    private Button btnFlights;

    @FXML
    private Button btnAirplanes;

    @FXML
    private Button btnTest;

    @FXML
    private Pane paneForLoad;

    @FXML
    private void callOtherPane(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("TestView.fxml"));
        paneForLoad.getChildren().setAll(pane);
        //paneForLoad.getChildren().add(pane);
    }

}