package application.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.Chart;
import application.model.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ViewActiveOrderController {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<Chart, String> State;

    @FXML
    private TableView<Chart> activeOrdersTable;

    @FXML
    private TableColumn<Chart, String> customerName;

    @FXML
    private TableColumn<Chart, LocalDateTime> orderDate;

    @FXML
    private TableColumn<Chart, Double> totalPrice;
    
    private DatabaseAdapter db = new DatabaseAdapter();
    
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
    void backButtonClicked(MouseEvent event) {
		SceneSwitch.switchScene("OwnerScreen.fxml", event, user);
    }
    
    private void loadPurchasedCharts() throws SQLException {
        List<Chart> purchasedCharts = db.getPurchasedAndActiveCharts();

        activeOrdersTable.getItems().setAll(purchasedCharts);
    }

    
    
    @FXML
    public void initialize() {
    	
    	
    	customerName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chart, String>, ObservableValue<String>>() {
    	    @Override
    	    public ObservableValue<String> call(TableColumn.CellDataFeatures<Chart, String> param) {
    	        return new SimpleObjectProperty<>(getUserName(param.getValue().getUserId()));
    	    }
    	});
    	
    	
    	
        
    	totalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    	orderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    	State.setCellValueFactory(new PropertyValueFactory<>("state"));
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
    
}
