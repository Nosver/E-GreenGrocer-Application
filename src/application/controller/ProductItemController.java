package application.controller;

import java.sql.SQLException;
import java.util.Optional;

import application.DatabaseAdapter;
import application.model.Chart;
import application.model.Product;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class ProductItemController{

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
    
    public User user = null;
    
	public void setUser(User user) {this.user = user;}
	    
    public void printUser() {
    	System.out.print("Product Screen Item Controller User: ");
		System.out.println(user.getName());
    }
    
    @SuppressWarnings("unchecked")
	private void handlePurchaseButtonClicked(Product product) throws Exception {
    	
		DatabaseAdapter databaseAdapter = new DatabaseAdapter();
		Chart chart = databaseAdapter.getChart(user); // Also controls if chart does exist
		chart.print();
		
		double amount = Double.parseDouble(PRODUCT_AMOUNT.getText());
		
		Pair<Product,Double> pair = new Pair(product, amount);
		
		chart.pushToArray(pair);
		
		// Stock is not sufficient
		if(!databaseAdapter.isStockSufficient(product, amount)) {
			Alert stockAlert = new Alert(Alert.AlertType.WARNING);
			stockAlert.setTitle("Stock is not enough!");
			stockAlert.setContentText("Please try again later, or lesser amount.");
			Optional<ButtonType>result = stockAlert.showAndWait();
			return;
		}
		
		databaseAdapter.updateChart(product, amount, chart);		
		
		Alert added2Chart = new Alert(Alert.AlertType.CONFIRMATION);
		added2Chart.setTitle("Product is added to your chart!");
		Optional<ButtonType>result = added2Chart.showAndWait();
		
		setProductItem(product);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		});
    }

}
