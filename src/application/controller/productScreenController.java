package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.Product;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    
    public User user = null;
    
    public void setUser(User user) {this.user = user;}
    
    public void printUser() {
    	System.out.print("Product Screen Controller User: ");
		System.out.println(user.getName());
    }
    
    public void setScreen() throws IOException, SQLException {
    	
    	DatabaseAdapter db = new DatabaseAdapter();
	    ArrayList<Product> products = db.getAllProducts();

	    for (Product product : products) {
	        FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitch.class.getResource("ProductItem.fxml"));

	        // Load the FXML file and set the controller in one step
	        fxmlLoader.setController(new ProductItemController());
	        Parent root = fxmlLoader.load();

	        // Get the controller after the FXML file is loaded
	        ProductItemController item_controller = fxmlLoader.getController();
	        item_controller.setUser(user); // Pass the user to the controller
	        item_controller.setProductItem(product);

	        VBox vbox = (VBox) root; // Assuming the root is a VBox, adjust if needed

	        PRODUCTS_FLOW.getChildren().add(vbox);
	    }
    }
    
	@FXML
	public void initialize() throws IOException, SQLException {
		
		Image image = new Image("images/cartA.png");
		CHART_IMG.setImage(image);
		
		
	}

}
