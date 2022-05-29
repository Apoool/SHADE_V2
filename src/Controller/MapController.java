package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private ImageView imageViewer;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button toCartButton;

    @FXML
    private Button toHomeButton;

    @FXML
    private Button toMapButton;

    @FXML
    private Button toProdInfoButton;

    @FXML
    private Button toScanButton;

    private Stage stage;
    private Scene scene;

    public void switchToHomeScene(ActionEvent event) throws IOException {
        toHomeButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCartScene(ActionEvent event) throws IOException{
        toCartButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/CartScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProdInfoScene(ActionEvent event) throws IOException{
        toMapButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ProdInfoScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCamScene(ActionEvent event) throws IOException{
        toScanButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/CamScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageViewer.setImage(new Image("D:\\Coding\\01_Java\\SHADE_V2\\resources\\Images\\map.png"));
    }
}
