package Controller;

import Core.Listener;
import Core.Main;
import Core.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductContController {

    @FXML
    private Label typeLabel;

    @FXML
    private ImageView imageviewer;

    @FXML
    private Label priceLabel;

    @FXML
    private void click(MouseEvent mouseEvent){
        listener.onCLickListener(product);
    }

    private Product product;
    private Listener listener;

    public void setData(Product product, Listener listener){
        this.listener = listener;
        this.product = product;
        typeLabel.setText(product.getType());
        priceLabel.setText(String.valueOf(Main.currency + String.format("%.2f",product.getPrice())));
        Image image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        imageviewer.setImage(image);

    }

}
