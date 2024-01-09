package application.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import application.DatabaseAdapter;
import application.model.ActiveProductTable;
import application.model.Chart;
import application.model.Product;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    }

    @FXML
    void deleteItemButtonClicked(MouseEvent event) {

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

    @FXML
    void updaeQuantityClicked(MouseEvent event) throws SQLException {
    	ActiveProductTable carrier = chartTable.getSelectionModel().getSelectedItem();
    	int row= chartTable.getSelectionModel().getSelectedIndex();
    	System.out.println(row);
    	if(carrier==null) {
    		return;
    	}
    	
    	double quantity;	
    	if(updateQuantity.getText().isBlank() || !containsNonAlphabetic(updateQuantity.getText())) {
    		return;
    	}
    	
    	try {
    		quantity = Double.parseDouble(updateQuantity.getText());
    		System.out.println(quantity);
        } catch (NumberFormatException e) {
        	return;
        }
    	
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
    void initialize() throws SQLException {
    	 userChart=db.getChartByUserId(1);//user.getId()
    	System.out.println(userChart.getChartId());
    	ArrayList<Pair<Product, Double>> products = db.getProductIdByChartId(userChart.getChartId());
    	System.out.println(products.get(0).left.getId());
    	
    	
    	ArrayList<ActiveProductTable> activeProductTableList = new ArrayList();
   	 
   	 for(int i=0; i<products.size(); i++) {
   		 double totalPrice = products.get(i).left.getPrice() * products.get(i).right;
   		 ActiveProductTable activeProductTable = new ActiveProductTable(totalPrice,products.get(i).left.getName(),products.get(i).right);
   		 activeProductTableList.add(activeProductTable);
   	 }
   	 ObservableList<ActiveProductTable> productList= FXCollections.observableArrayList(activeProductTableList);
   	 
   	priceCollumn.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("price"));
   	nameCollumn.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,String>("name"));
   	quantityCollumn.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("quantitiy"));
   	chartTable.setItems(productList);
    }
    
    @FXML
    void chartClicked(MouseEvent event) {
    	ActiveProductTable selected = chartTable.getSelectionModel().getSelectedItem();
    	selectedItemLabel.setText(selected.getName());
    	quantityInput.setText(String.valueOf(selected.getQuantitiy()));
    
    }
    

}
