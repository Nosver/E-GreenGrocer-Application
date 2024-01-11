package application.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.Chart;
import application.model.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


public class CourrierOrderScreenController {
	
    @FXML
    private Button BackButton1;

    @FXML
    private TableColumn<Chart, String> userNameColumn;
    
    @FXML
    private TableColumn<Chart, String> CustomerAdressColumn;
    
    @FXML
    private TableColumn<Chart, LocalDateTime> DateColumn;

    @FXML
    private TableColumn<Chart, Double> TotalPriceColumn;

    @FXML
    private TableView<Chart> orderTable;
    
    @FXML
    private Button SetActiveButton;
    
    private DatabaseAdapter db = new DatabaseAdapter();
    
    private User user;
    
    
    
    
    private void loadPurchasedCharts() throws SQLException {
        List<Chart> purchasedCharts = db.getPurchasedCharts();

        orderTable.getItems().setAll(purchasedCharts);
    }

    
    
    @FXML
    public void initialize() {
    	
    	
    	userNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chart, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Chart, String> param) {
    	        return new SimpleObjectProperty<>(getUserName(param.getValue().getUserId()));
    	    }
    	});
       
    	CustomerAdressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chart, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chart, String> param) {
                return new SimpleObjectProperty<>(getCustomerAddress(param.getValue().getUserId()));
            }
        });
    	
    	
    	
        
        TotalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadPurchasedCharts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    private String getUserName(Integer userId) {
        try {
            return db.getUserNameByID(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "UnknownUser";
        }
    }
    
    private String getCustomerAddress(Integer userId) {
        try {
            return db.getCustomerAddressByID(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "UnknownAddress";
        }
    }
   
    public void setUser(User user) {
		this.user = user;
		
	}
    @FXML
    void BackButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("CourrierHomeScreen.fxml", event, user);
    }

    @FXML
    void SetActiveButtonClicked(MouseEvent event) throws SQLException {
    	if(db.getPurchasedCharts().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("There is no order to be delivered ");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	if(!db.getActiveChart().isEmpty()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("There is already active order!! ");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	Chart selectedOrder = orderTable.getSelectionModel().getSelectedItem();
    	selectedOrder.setState("active");
    	
    	this.user.setChartId(selectedOrder.getChartId()); // newUpdate
   
    	db.UpdateChartState(selectedOrder);
    	db.UpdateUser(user);
    	
    	userNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chart, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Chart, String> param) {
    	        return new SimpleObjectProperty<>(getUserName(param.getValue().getUserId()));
    	    }
    	});
       
    	CustomerAdressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chart, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chart, String> param) {
                return new SimpleObjectProperty<>(getCustomerAddress(param.getValue().getUserId()));
            }
        });
    	
    	
    	
        
        TotalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            loadPurchasedCharts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	
    }
    
    @FXML
    void ShowDetailsButtonClicked(MouseEvent event) {

    }

   
}
