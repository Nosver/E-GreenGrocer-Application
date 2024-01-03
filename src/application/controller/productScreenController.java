
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class productScreenController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="CHART"
    private TableView<?> CHART; // Value injected by FXMLLoader

    @FXML // fx:id="CHART_NAME"
    private TableColumn<?, ?> CHART_NAME; // Value injected by FXMLLoader

    @FXML // fx:id="CHART_PRICE"
    private TableColumn<?, ?> CHART_PRICE; // Value injected by FXMLLoader

    @FXML // fx:id="CHART_QUANTITY"
    private TableColumn<?, ?> CHART_QUANTITY; // Value injected by FXMLLoader

    @FXML // fx:id="IMAGE"
    private ImageView IMAGE; // Value injected by FXMLLoader

    @FXML // fx:id="PRODUCTS"
    private TableView<?> PRODUCTS; // Value injected by FXMLLoader

    @FXML // fx:id="PRODUCT_AMOUNT"
    private TableColumn<?, ?> PRODUCT_AMOUNT; // Value injected by FXMLLoader

    @FXML // fx:id="PRODUCT_ID"
    private TableColumn<?, ?> PRODUCT_ID; // Value injected by FXMLLoader

    @FXML // fx:id="PRODUCT_PRICE"
    private TableColumn<?, ?> PRODUCT_PRICE; // Value injected by FXMLLoader

    @FXML // fx:id="PRODUCT_STOCK"
    private TableColumn<?, ?> PRODUCT_STOCK; // Value injected by FXMLLoader

    @FXML // fx:id="PURCHASE"
    private Button PURCHASE; // Value injected by FXMLLoader

    @FXML // fx:id="RESETCHART"
    private Button RESETCHART; // Value injected by FXMLLoader

    @FXML // fx:id="SEEPROFILE"
    private Button SEEPROFILE; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert CHART != null : "fx:id=\"CHART\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert CHART_NAME != null : "fx:id=\"CHART_NAME\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert CHART_PRICE != null : "fx:id=\"CHART_PRICE\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert CHART_QUANTITY != null : "fx:id=\"CHART_QUANTITY\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert IMAGE != null : "fx:id=\"IMAGE\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert PRODUCTS != null : "fx:id=\"PRODUCTS\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert PRODUCT_AMOUNT != null : "fx:id=\"PRODUCT_AMOUNT\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert PRODUCT_ID != null : "fx:id=\"PRODUCT_ID\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert PRODUCT_PRICE != null : "fx:id=\"PRODUCT_PRICE\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert PRODUCT_STOCK != null : "fx:id=\"PRODUCT_STOCK\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert PURCHASE != null : "fx:id=\"PURCHASE\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert RESETCHART != null : "fx:id=\"RESETCHART\" was not injected: check your FXML file 'productScreen.fxml'.";
        assert SEEPROFILE != null : "fx:id=\"SEEPROFILE\" was not injected: check your FXML file 'productScreen.fxml'.";
        		
        // DO SOMETHING HERE ...
        
        //ArrayList<Product> productList = DatabaseController.

    }

}