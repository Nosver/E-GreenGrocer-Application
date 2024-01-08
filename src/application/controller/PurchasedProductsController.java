package application.controller;

import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PurchasedProductsController {

    @FXML
    private Button BackButton;
    

    @FXML
    private TableColumn<ActiveProductTable, Double> PriceField;

    @FXML
    private TableColumn<ActiveProductTable, String> ProductNameField;

    @FXML
    private TableView<ActiveProductTable> ProductTable;

    @FXML
    private TableColumn<ActiveProductTable, Double> QuantityField;
    
    private User user;
    
    private Chart chart;
    
    private DatabaseAdapter db = new DatabaseAdapter();
    
    
    public void setUser(User user) {
		this.user = user;
		
	}
    
    

    
    
    @FXML
    void initialize() throws SQLException {
    	 ArrayList<Chart> activeCharts = db.getActiveChart();
    	 this.chart = activeCharts.get(0);
    	 
    	 int ChartId = chart.getChartId();
    	 ArrayList<Pair<Product, Double>> products = db.getProductIdByChartId(ChartId);
    	 
    	 System.out.println(products);
    	 
    	 ArrayList<ActiveProductTable> activeProductTableList = new ArrayList();
    	 
    	 for(int i=0; i<products.size(); i++) {
    		 ActiveProductTable activeProductTable = new ActiveProductTable(products.get(i).left.getPrice(),products.get(i).left.getName(),products.get(i).right);
    		 activeProductTableList.add(activeProductTable);
    	 }
    	 ObservableList<ActiveProductTable> productList= FXCollections.observableArrayList(activeProductTableList);
    	 
    	 PriceField.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("price"));
    	 ProductNameField.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,String>("name"));
    	 QuantityField.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("quantitiy"));
    	 ProductTable.setItems(productList);
    }
    
    @FXML
    void backButtonClicked(MouseEvent event) {
    		SceneSwitch.switchScene("CourrierHomeScreen.fxml", event, user);
    }
    
    
    
    

}
