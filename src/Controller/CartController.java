package Controller;

import Core.Listener;
import Core.Main;
import Core.Product;
import Core.ProductData;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView selectedImage;

    @FXML
    private TextField budgetTextField;

    @FXML
    private Label inCartLabel;

    @FXML
    private Label selectedPriceLabel;

    @FXML
    private Label selectedTypeLabel;

    @FXML
    private Label budgetLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Button decreaseButton;

    @FXML
    private Button budgetButton;

    @FXML
    private Button increaseButton;

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

    DecimalFormat df = new DecimalFormat("0.00");

    private List<Product> checkList = new ArrayList<>();
    private Product product;
    private Listener listener;
    private Stage stage;
    private Scene scene;
    private Product selectedProduct;
    private Image image;
    private Double budget = 0.00;
    private Double total = 0.00;


    public void switchToHomeScene(ActionEvent event) throws IOException{
        toHomeButton.setDisable(true);
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToProdInfoScene(ActionEvent event) throws IOException{
        toProdInfoButton.setDisable(true);
        toCartButton.setDisable(false);
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

    public void getBudget(ActionEvent event) throws IOException{
        try{
            budget = Double.valueOf(budgetTextField.getText());
            budgetLabel.setText("Budget: " + Main.currency + String.valueOf(df.format(budget)));
        }catch (NumberFormatException e){
            budgetLabel.setText("Error!");
            budgetTextField.setText("0.00");
            e.printStackTrace();
        }

        checkStatus();
    }

    public void getProduct(List<Product> products) {
        checkList = products;
        System.out.println("ADDED " + checkList.size());
        for(int i = 0; i<checkList.size(); i++){
            System.out.println(checkList.get(i).getId());
        }
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

    public void refresh (ActionEvent event) throws IOException{
        showALl(checkList = ProductData.fillCart());
    }

    public int getID(){
        int i = 0;
        if(checkList.size() > 1 ){
            for(i = 0 ; i < checkList.size() ; i++){
                if(checkList.get(i).getId() == selectedProduct.getId()){
                    return  i;
                }
            }
        }else{
            return 0;
        }
        return 0;
    }

    public void getTotal(){
        total = 0.00;
        for(int i = 0 ; i < checkList.size() ; i++){
            total = total + ((double) checkList.get(i).getInCart() * checkList.get(i).getPrice());
        }
        totalLabel.setText("Total: " + Main.currency + String.valueOf(df.format(total)));
    }

    public void increase(ActionEvent event) throws IOException {
        int i = getID();
        System.out.println(checkList.get(i).getStock());
        if(checkList.get(i).getStock() > checkList.get(i).getInCart()){
            ProductData.addToCartDB(checkList.get(i).getId(), 1);
            checkList = ProductData.fillCart();
            inCartLabel.setText(String.valueOf(checkList.get(i).getInCart()));
            getTotal();
        }else{
            System.out.println("Lready at max");
            //put osome error message popup
        }

        checkStatus();

    }

    public void decrease(ActionEvent event) throws IOException {
        int i = getID();

        if(checkList.get(i).getInCart() > 1 ){
            ProductData.addToCartDB(checkList.get(i).getId(), -1);
            System.out.println("ID:" + checkList.get(i).getId());
            System.out.println("Before:" + checkList.get(i).getInCart());
            checkList = ProductData.fillCart();
            inCartLabel.setText(String.valueOf(checkList.get(i).getInCart()));
        }else {
            ProductData.addToCartDB(checkList.get(i).getId(), -1);
            refresh(event);
            if (checkList.size() == 0){
                System.out.println("SD");
                decreaseButton.setDisable(true);
                increaseButton.setDisable(true);
            }else {
                refresh(event);
                setSelected(checkList.get(0));
            }//make an empty placeholder
            refresh(event);
        }
        getTotal();
        checkStatus();

    }

    private void setSelected(Product product){
        selectedTypeLabel.setText(product.getType());
        selectedPriceLabel.setText(Main.currency + String.format("%.2f",product.getPrice()));
        inCartLabel.setText(String.valueOf(product.getInCart()));
        image = new Image(getClass().getResourceAsStream(product.getImagePath()));
        selectedImage.setImage(image);
    }

    private void checkStatus(){
        if(budget > total){
            statusLabel.setText("Status: " + Main.currency + df.format((budget-total)) + " Fine");
        }else if(budget < total){
            statusLabel.setText("Status: " + "- " + Main.currency + df.format(-1 * (budget-total)) + " Exceed");
        }else{
            statusLabel.setText("Status: " + Main.currency + df.format((budget-total)) + " Exact");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        budgetLabel.setText("Budget: " + Main.currency + "0.00");
        budgetTextField.setText("0.00");
        checkStatus();
        try {
            checkList = ProductData.fillCart();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(checkList.size() > 0){
            setSelected(checkList.get(0));
            selectedProduct = checkList.get(0);
            listener = new Listener(){
                @Override
                public void onCLickListener(Product product) {
                    selectedProduct = product;
                    setSelected(product);
                }
            };
            inCartLabel.setText(String.valueOf(selectedProduct.getInCart()));
            showALl(checkList);
        }

        getTotal();

    }
}

