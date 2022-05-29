package Controller;

import Core.Listener;
import Core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;


public class ProductCardController {

    @FXML
    private Label brandLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label informationLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private ImageView imageViewer;

    @FXML
    private Label priceLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private AnchorPane pane;

    DecimalFormat df = new DecimalFormat("0.00");

    private Product product;
    private Stage stage;

    public void setCard(Product product){
        this.product = product;
        brandLabel.setText(product.getBrand());
        idLabel.setText(String.valueOf(product.getId()));
        informationLabel.setText(product.getInformation());
        locationLabel.setText(product.getLocation());
        priceLabel.setText(String.valueOf(df.format(product.getPrice())));
        typeLabel.setText(product.getType());
        Image image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        imageViewer.setImage(image);
    }

    public void exitButton(ActionEvent event) throws IOException{
        stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
}
