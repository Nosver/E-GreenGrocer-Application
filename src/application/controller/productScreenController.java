package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class productScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField PRODUCT_AMOUNT;

    @FXML
    private VBox PRODUCT_BOX;

    @FXML
    private ImageView PRODUCT_IMAGE;

    @FXML
    private Text PRODUCT_NAME;

    @FXML
    private Button PRODUCT_PURCHASE;

    @FXML
    void initialize() throws SQLException {
        assert PRODUCT_AMOUNT != null : "fx:id=\"PRODUCT_AMOUNT\" was not injected: check your FXML file 'newProductScreen.fxml'.";
        assert PRODUCT_BOX != null : "fx:id=\"PRODUCT_BOX\" was not injected: check your FXML file 'newProductScreen.fxml'.";
        assert PRODUCT_IMAGE != null : "fx:id=\"PRODUCT_IMAGE\" was not injected: check your FXML file 'newProductScreen.fxml'.";
        assert PRODUCT_NAME != null : "fx:id=\"PRODUCT_NAME\" was not injected: check your FXML file 'newProductScreen.fxml'.";
        assert PRODUCT_PURCHASE != null : "fx:id=\"PRODUCT_PURCHASE\" was not injected: check your FXML file 'newProductScreen.fxml'.";
        
        DatabaseAdapter db = new DatabaseAdapter();
        
        ArrayList<Product> products = db.getAllProducts();
        
        for()
        
    }
    

}
