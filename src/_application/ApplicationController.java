package _application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private void callCustomer(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/customer/CustomerView.fxml"));
        paneForLoad.getChildren().setAll(pane);
    }

    @FXML
    private void callSales(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/sale/SaleView.fxml"));
        paneForLoad.getChildren().setAll(pane);
    }

    @FXML
    private void callFlight(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/flight/FlightView.fxml"));
        paneForLoad.getChildren().setAll(pane);
    }

    @FXML
    private void callAirplane(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("/airplane/AirplaneView.fxml"));
        paneForLoad.getChildren().setAll(pane);
    }

}