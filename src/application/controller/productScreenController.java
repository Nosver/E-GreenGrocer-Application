package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.model.Product;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class productScreenController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private FlowPane PRODUCTS_FLOW;

	@FXML
	private TextField PRODUCT_AMOUNT;

	@FXML
	private VBox PRODUCT_BOX;

	@FXML
	private Group PRODUCT_GROUP;

	@FXML
	private ImageView PRODUCT_IMAGE;

	@FXML
	private Text PRODUCT_NAME;

	@FXML
	private Button PRODUCT_PURCHASE;

	public void initialize() {

		DatabaseAdapter db = new DatabaseAdapter();
		ArrayList<Product> products = null;
		System.out.println("dcsd");

		try {
			products = db.getAllProducts();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (Product product : products) {
	        // Create a new Group for each product
	        Group group = new Group(PRODUCT_GROUP);

	        // Add the Group to the FlowPane
	        PRODUCTS_FLOW.getChildren().add(group);
	    }

	}

}
