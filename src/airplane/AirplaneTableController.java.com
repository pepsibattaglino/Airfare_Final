package airplane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import messages.MessageController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Bernardo on 08/07/2017.
 */

public class AirplaneTableController implements Initializable {

    @FXML
    private TableView<Airplane> AirplaneTableView;

    @FXML
    private TableColumn<Airplane, String> AirplaneKeyColumn;

    @FXML
    private TableColumn<Airplane, String> AirplaneCodeColumn;

    @FXML
    private TableColumn<Airplane, String> AirplaneModelColumn;

    @FXML
    private TableColumn<Airplane, String> AirplaneQntSeatsColumn;

    @FXML
    private Button AirplaneRefreshButton;

    private AirplaneDao aDao = new AirplaneDao();
    private List<Airplane> airplanes = aDao.listAllAirplanes();
    private ObservableList<Airplane> observableAirplanes;
    MessageController mc = new MessageController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadAirplaneTableView();
        } catch (Exception e) {
            mc.eMes("Error", "Could not load the Airplanes list.");
        }
    }

    private void loadAirplaneTableView(){
//        AirplaneKeyColumn.setCellFactory(
//                (Callback<TableColumn<Airplane, String>, ObservableValue<String>>) line -> {
//                    final Airplane airplane = line.getValue();
//                    final SimpleObjectProperty<String> simpleObject =
//                            new SimpleObjectProperty(
//                                    Integer.toString(airplane.getAirplaneID())
//                            );
//                    return simpleObject;
//                });
        AirplaneCodeColumn.setCellFactory(new PropertyValueFactory<>("code"));
        AirplaneModelColumn.setCellFactory(new PropertyValueFactory<>("model"));
//        AirplaneQntSeatsColumn.setCellFactory(
//            new Callback<TableColumn.CellDataFeatures<Airplane,String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Airplane, String> linha) {
//                final Airplane airplane = linha.getValue();
//                final SimpleObjectProperty<String> simpleObject =
//                        new SimpleObjectProperty(
//                                Integer.toString(airplane.getAirplaneID())
//                        );
//                return simpleObject;
//            }
//        });

        AirplaneTableView.setItems(observableAirplanes);
    }
}
