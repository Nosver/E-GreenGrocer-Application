package application.controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.util.Pair;

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
    
    public void setUser(User user) {
    	this.user = user;
    }    
    
    public void initialize() throws SQLException {
    	DatabaseAdapter databaseAdapter = new DatabaseAdapter();
         user = databaseAdapter.getUserByEmail("example1@mail.com"); // User must come from previous screen through scene switch
    }
    
    @SuppressWarnings("unchecked")
	private void handlePurchaseButtonClicked(Product product) throws NumberFormatException, SQLException {
    	
		DatabaseAdapter databaseAdapter = new DatabaseAdapter();
		Chart chart = databaseAdapter.getChart(user); // Also controls if chart does exist
		
		Pair<Product,Double> pair = new Pair(product, Double.parseDouble(PRODUCT_AMOUNT.getText()));
		
		chart.pushToArray(pair);
		
		databaseAdapter.insertChartItem(product, Double.parseDouble(PRODUCT_AMOUNT.getText()), chart);
		
    }
    
    public void setProductItem(Product product) {
    	PRODUCT_NAME.setText(product.getName());
    	PRODUCT_AMOUNT.setPromptText("Enter the amount");
    	Image image = new Image(product.getImagePath());
    	PRODUCT_IMG.setImage(image);
    	PRODUCT_PRICE.setText(Double.toString(product.getPrice()));
    	PRODUCT_BUTTON.setOnMouseClicked(event -> {
			try {
				handlePurchaseButtonClicked(product);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

}
