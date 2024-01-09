package application.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.ActiveProductTable;
import application.model.Chart;
import application.model.Product;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ChartController {

    @FXML
    private Button backButton;

    @FXML
    private TableView<ActiveProductTable> chartTable;

    @FXML
    private Button deleteItemButton;

    @FXML
    private TableColumn<ActiveProductTable, String> nameCollumn;

    @FXML
    private Button payButton;

    @FXML
    private TableColumn<ActiveProductTable, Double> priceCollumn;

    @FXML
    private TableColumn<ActiveProductTable, Double> quantityCollumn;

    @FXML
    private TextField quantityInput;

    @FXML
    private Label selectedItemLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button updateQuantity;
    
    private User user;
    
    Chart userChart;
        
    private DatabaseAdapter db = new DatabaseAdapter();

    @FXML
    void backButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("newProductScreen.fxml", event, user);
    }

    public void deleteItem() throws SQLException {
    	ActiveProductTable carrier = chartTable.getSelectionModel().getSelectedItem();
    	
    	if(carrier == null) {
    		return;
    	}
    	
    	Product product = db.getProductByProductName(carrier.getName());

    	
    	db.deleteItem(userChart.getChartId(), product.getId());
    	
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Successful");
		alert.setContentText("The product is deleted successfully !");
		Optional<ButtonType>result = alert.showAndWait();

		product.setStock(product.getStock() + carrier.getQuantitiy());
    	db.UpdateProductByIdNew(product);

		    	
    	setScreen();
		
    	quantityInput.setText(null);
    	
    }
    
    
    @FXML
    void deleteItemButtonClicked(MouseEvent event) throws SQLException {
    	
    	deleteItem();

    }

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
    void payButton(MouseEvent event) {

    }

	
	public void setScreen() throws SQLException {
		
		userChart=db.getChartByUserId(user.getId());//user.getId()
    	System.out.println(userChart.getChartId());
    	ArrayList<Pair<Product, Double>> products = db.getProductIdByChartId(userChart.getChartId());
    	
    	
    	ArrayList<ActiveProductTable> activeProductTableList = new ArrayList();
   	 
    	Double totalTotalPrice = 0.0;
    	
    	
   	 	for(int i=0; i<products.size(); i++) {
   	 		double totalPrice = products.get(i).left.getPrice() * products.get(i).right;
   	 		totalTotalPrice += totalPrice;
   	 		ActiveProductTable activeProductTable = new ActiveProductTable(totalPrice,products.get(i).left.getName(),products.get(i).right);
   	 		activeProductTableList.add(activeProductTable);
   	 	}
   	 	
   	 	ObservableList<ActiveProductTable> productList= FXCollections.observableArrayList(activeProductTableList);
   	 
   	 	priceCollumn.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("price"));
   	 	nameCollumn.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,String>("name"));
   	 	quantityCollumn.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("quantitiy"));
   	 	chartTable.setItems(productList);
   	 	totalPriceLabel.setText(totalTotalPrice.toString());
		
	}
	
    @FXML
    void updateQuantityClicked(MouseEvent event) throws SQLException {
    	
    	ActiveProductTable carrier = chartTable.getSelectionModel().getSelectedItem();
    	
    	if(carrier==null) {
    		return;
    	}
    	
    	double quantity;	
    	
    	if(updateQuantity.getText().isBlank() || !containsNonAlphabetic(updateQuantity.getText())) {
    		return;
    	}
    	
    	try {
    		quantity = Double.parseDouble(quantityInput.getText());
    		if(quantity < 0) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("invalid quantity!");
    			Optional<ButtonType>result = alert.showAndWait();
    			return;
    		}
    			
        } catch (NumberFormatException e) {
        	return;
        }
    	
    	if(quantity == 0) {
    		deleteItem();
    		return;
    	}
    	
    	Product product = db.getProductByProductName(carrier.getName());
    	
    	Double tempV = carrier.getQuantitiy() + product.getStock();
    	
    	if(quantity > tempV) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("No available stock !");
			Optional<ButtonType>result = alert.showAndWait();
			return;
    	}
    	
    	product.setStock(tempV - quantity);
    	
    	
    	
    	carrier.setQuantitiy(quantity);
    	
    	db.UpdateProductByIdNew(product);
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setContentText("The quantity is updated !");
		Optional<ButtonType>result = alert.showAndWait();
		
    	
		db.UpdateQuantityByChartAndProductId(userChart.getChartId(), product.getId(), quantity);
		quantityInput.setText(null);
		
		
		
		setScreen();
    	//stok check
    	//boolean available= db.stockCheck();
    	
    	//sepete ekle
    	//render yap
    	}
    
    private static boolean containsNonAlphabetic(String password) {
        Pattern pattern = Pattern.compile(".*[^a-zA-Z].*");
        return pattern.matcher(password).matches();
    }
    
    
    @FXML
    void chartClicked(MouseEvent event) {
    	ActiveProductTable selected = chartTable.getSelectionModel().getSelectedItem();
    	selectedItemLabel.setText(selected.getName());
    	quantityInput.setText(String.valueOf(selected.getQuantitiy()));
    
    }
    

}
