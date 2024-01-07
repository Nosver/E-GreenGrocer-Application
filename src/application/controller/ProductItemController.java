package application.controller;

import java.sql.SQLException;

import application.DatabaseAdapter;
import application.model.Chart;
import application.model.Product;
import application.model.User;
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
    
    @FXML
    private Text PRODUCT_PRICE;
        
    private User user = null;
    
    public void initialize() throws SQLException {
    	DatabaseAdapter databaseAdapter = new DatabaseAdapter();
         user = databaseAdapter.getUserByEmail("example1@mail.com"); // User must come from previous screen through scene switch
    }
    
    private void handlePurchaseButtonClicked(Product product) {
    	
		DatabaseAdapter databaseAdapter = new DatabaseAdapter();
		Chart chart = databaseAdapter.getChart(user);
		System.out.println(chart.getState());
		System.out.println(chart.getTotalPrice());
		System.out.println(chart.getDate());
		System.out.println(chart.getUserId());
    }
    
    public void setProductItem(Product product) {
    	PRODUCT_NAME.setText(product.getName());
    	PRODUCT_AMOUNT.setPromptText("Enter the amount");
    	Image image = new Image(product.getImagePath());
    	PRODUCT_IMG.setImage(image);
    	PRODUCT_PRICE.setText(Double.toString(product.getPrice()));
    	PRODUCT_BUTTON.setOnMouseClicked(event -> handlePurchaseButtonClicked(product));
    }

}
