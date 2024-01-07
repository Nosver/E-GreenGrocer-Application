package application.controller;

import application.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProductItemController {

    @FXML
    private TextField PRODUCT_AMOUNT;

    @FXML
    private Button PRODUCT_BUTTON;

    @FXML
    private ImageView PRODUCT_IMG;

    @FXML
    private Text PRODUCT_NAME;
        
    private void handlePurchaseButtonClicked(Product product) {
        
    }
    
    public void setProductItem(Product product) {
    	PRODUCT_NAME.setText(product.getName());
    	PRODUCT_AMOUNT.setPromptText("Enter the amount");
    	System.out.println(product.getImagePath());
    	Image image = new Image(product.getImagePath());
    	PRODUCT_IMG.setImage(image);
    	PRODUCT_BUTTON.setOnMouseClicked(event -> handlePurchaseButtonClicked(product));
    }

}
