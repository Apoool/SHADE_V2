package Controller;

import Core.Product;
import Core.ProductData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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

    private List<Product> checkList = new ArrayList<>();
    private Stage stage;
    private Scene scene;
    private Integer count = 0;

    public void switchToCartScene(ActionEvent event) throws IOException {
        toCartButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/CartScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProdInfoScene(ActionEvent event) throws IOException{
        toProdInfoButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ProdInfoScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMapScene(ActionEvent event) throws IOException{
        toMapButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/MapScene.fxml"));
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

    private void homeSlide(){
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("D:\\Coding\\01_Java\\SHADE_V2\\resources\\Images\\Cart1.png"));
        images.add(new Image("D:\\Coding\\01_Java\\SHADE_V2\\resources\\Images\\Cart2.png"));
        images.add(new Image("D:\\Coding\\01_Java\\SHADE_V2\\resources\\Images\\Cart3.png"));
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e ->{

            imageViewer.setImage(images.get(count));
            count++;
            if (count == 3){
                count = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            checkList = ProductData.fillObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(checkList.size() < 1){
            toCartButton.setDisable(true);
        }
        imageViewer.setImage(new Image("D:\\Coding\\01_Java\\SHADE_V2\\resources\\Images\\Cart1.png"));
        homeSlide();
    }
}
