package Controller;

import Core.Listener;
import Core.Main;
import Core.Product;
import Core.ProductData;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProdInfoController implements Initializable {

    @FXML
    private Button addToCartButton;

    @FXML
    private TextField searchBarTextField;

    @FXML
    private Label selectedTypeLabel;

    @FXML
    private Label selectedPriceLabel;

    @FXML
    private Button toCartButton;

    @FXML
    private Button toHomeButton;

    @FXML
    private Button toMapButton;

    @FXML
    private Button toScanButton;

    @FXML
    private Button viewButton;

    @FXML
    private GridPane grid;

    @FXML
    private ImageView selectedImage;

    @FXML
    private AnchorPane pane;

    /**
     * THIS CLASS WILL SERVE AS THE MAIN DATA MANIPULATION HENCE THE INITIALIZATON OF THE FF
     */
    private List<Product> products = new ArrayList<>();
    private Image image;
    private Listener listener;
    private Stage stage;
    private Scene scene;
    private Product selectedProduct;

    private double xOffset = 0;
    private double yOffset = 0;


    File file = new File("D:\\Coding\\01_Java\\SHADE_V2\\src\\Core\\data.txt");


    public void switchToHomeScene(ActionEvent event) throws IOException{
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

    private void setSelected(Product product){
        selectedTypeLabel.setText(product.getType());
        selectedPriceLabel.setText(Main.currency + String.format("%.2f",product.getPrice()));
        image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        selectedImage.setImage(image);
    }

    public void search(ActionEvent event) throws IOException{
        List<Product> productSearch = new ArrayList<>();
        String x = searchBarTextField.getText();
        if (searchBarTextField.getCharacters().isEmpty()){
            System.out.println(products.isEmpty());
            showALl(products);
        }
        else {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getType().toLowerCase().indexOf(x) > -1) {
                    productSearch.add(products.get(i));
                }
            }
            showALl(productSearch);
        }

    }

    public void viewButton(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/ProductCardScene.fxml"));
        Parent root = fxmlLoader.load();
        ProductCardController productCard = fxmlLoader.getController();
        productCard.setCard(selectedProduct);
        scene = new Scene(root);
        stage = new Stage();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.WINDOW_MODAL);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        stage.initOwner(pane.getScene().getWindow());
        stage.show();

    }

    private void showALl(List<Product> products){
        grid.getChildren().clear();
        int col=0;
        int row=1;
        try {
            for(int i=0; i<products.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/FXML/ProductCont.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProductContController productContController = fxmlLoader.getController();
                productContController.setData(products.get(i), listener);

                if (col == 3){
                    col = 0;
                    row++;
                }

                grid.add(anchorPane, col++, row);
                //WIDTH
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //HEIGHT
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(20));

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToCart(ActionEvent event) throws IOException{
        ProductData.addToCartDB(selectedProduct.getId(), 1);
        products = ProductData.fillObject();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!file.exists()){
            System.out.println("exist");
            ProductData.test();
        }
        try {
            products = ProductData.fillObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(products.size());
        if(products.size() == 0){
            try {
                ProductData.test();
                products = ProductData.fillObject();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(products.size() > 0){
            setSelected(products.get(0));
            selectedProduct = products.get(0);
            listener = new Listener(){
                @Override
                public void onCLickListener(Product product) {
                    selectedProduct = product;
                    setSelected(product);
                }
            };
            System.out.println(products.size());
        showALl(products);
        }
    }

}


