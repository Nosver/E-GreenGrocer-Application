package application.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ViewMyOrdersController {

    @FXML
    private Button backButton;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private TableColumn<Chart, Integer> chartId;

    @FXML
    private TableView<Chart> chartTable;

    @FXML
    private TableColumn<Chart, LocalDateTime> date;

    @FXML
    private TableColumn<ActiveProductTable, String> name;

    @FXML
    private TableColumn<ActiveProductTable, Double> price;

    @FXML
    private TableView<ActiveProductTable> productTable;

    @FXML
    private TableColumn<ActiveProductTable, Double> quantity;

    @FXML
    private TableColumn<Chart, String> state;

    @FXML
    private TableColumn<Chart, Double> totalPrice;
    
    @FXML
    private Label selectedOrder;
    
    private DatabaseAdapter db=new DatabaseAdapter();
    
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setScreen() throws SQLException {
		chartTable.setItems(null);
		ArrayList<Chart> charts= db.getPurchasedActiveAndDeliveredCharts(user);
		//System.out.println(charts);
		 ObservableList<Chart> chartList=FXCollections.observableArrayList(charts);
		 chartId.setCellValueFactory(new PropertyValueFactory<Chart,Integer>("chartId"));
		 totalPrice.setCellValueFactory(new PropertyValueFactory<Chart,Double>("totalPrice"));
		 date.setCellValueFactory(new PropertyValueFactory<Chart,LocalDateTime>("date"));
		 state.setCellValueFactory(new PropertyValueFactory<Chart,String>("state"));
		 chartTable.setItems(chartList);
	}

	@FXML
    void backButtonClicked(MouseEvent event) {
		SceneSwitch.switchScene("Customer.fxml", event, user);
    }

    @FXML
    void cancelOrderClicked(MouseEvent event) throws SQLException {
    	Chart chart = chartTable.getSelectionModel().getSelectedItem();
    	if(chart==null)
    		return;
    	System.out.println(chart.getState());
    	if(chart.getState().equals("active")||chart.getState().equals("delivered")) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Active and delivered orders can not be cancelled");
			Optional<ButtonType>result = alert.showAndWait();
			return;
    	}
    	
    	db.deleteChartByChartId(chart.getChartId());
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("successful");
		alert.setContentText("Order is cancelled");
		Optional<ButtonType>result = alert.showAndWait();
		setScreen();
		
    }

    @FXML
    void chartTableSelected(MouseEvent event) throws SQLException {
    	Chart chart = chartTable.getSelectionModel().getSelectedItem();
    	if(chart==null)
    		return;
    	selectedOrder.setText(Integer.toString(chart.getChartId()));
    	ArrayList<Pair<Product, Double>> products = db.getProductIdByChartId(chart.getChartId());
    	ArrayList<ActiveProductTable> activeProductTableList = new ArrayList();
      	 
    	Double totalTotalPrice = 0.0;
    	
    	
   	 	for(int i=0; i<products.size(); i++) {
   	 		double totalPrice = products.get(i).left.getPrice() * products.get(i).right;
   	 		totalTotalPrice += totalPrice;
   	 		ActiveProductTable activeProductTable = new ActiveProductTable(totalPrice,products.get(i).left.getName(),products.get(i).right);
   	 		activeProductTableList.add(activeProductTable);
   	 	}
   	 	ObservableList<ActiveProductTable> productList= FXCollections.observableArrayList(activeProductTableList);
   	 
   	 	price.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("price"));
	 	name.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,String>("name"));
	 	quantity.setCellValueFactory(new PropertyValueFactory<ActiveProductTable,Double>("quantitiy"));
	 	productTable.setItems(productList);
	 	
    }

}
