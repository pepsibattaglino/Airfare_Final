package sale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by G.Battaglino on 06/07/2017.
 */
public class SaleMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SaleViewOlder.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Register Sale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}