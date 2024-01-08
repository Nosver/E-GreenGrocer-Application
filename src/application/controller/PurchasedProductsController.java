package application.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import application.DatabaseAdapter;
import application.model.Chart;
import application.model.Product;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PurchasedProductsController {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<Chart, Double> PriceField;

    @FXML
    private TableColumn<Chart, String> ProductNameField;

    @FXML
    private TableView<Chart> ProductTable;

    @FXML
    private TableColumn<Chart, Double> QuantityField;
    
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
    	 
    	 
    	 
    }
    
    
    
    
    

}
