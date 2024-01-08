package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.model.Product;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class productScreenController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private FlowPane PRODUCTS_FLOW;
	
    @FXML
    private Button CART_BUTTON;

    @FXML
    private ImageView CHART_IMG;

    private User user = null;
    
	public void setUser(User user) {
		this.user = user;
	}
	
	
	@FXML
	public void initialize() throws IOException {
		
		// this.user = user;
		Image image = new Image("images/cartA.png");
		CHART_IMG.setImage(image);
		
		
		DatabaseAdapter db = new DatabaseAdapter();
		ArrayList<Product> products = null;
		// System.out.println(user.getId());

		try {
			products = db.getAllProducts();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		for (Product product : products) {
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/application/ProductItem.fxml"));
			
			VBox vbox = fxmlLoader.load();
			
	        ProductItemController productitemcontroller = fxmlLoader.getController();
	        productitemcontroller.setProductItem(product);
        	productitemcontroller.setUser(user);
        	// System.out.println(user.getId());
        	// System.out.println(user.getName());

	        
	        PRODUCTS_FLOW.getChildren().add(vbox);
	    }
	}
	
	public void initializeWithData(User user) {
        this.user = user;
        // Additional initialization logic based on the user if needed
    }



}
