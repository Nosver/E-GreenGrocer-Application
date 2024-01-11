package application.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class CourrierHomeScreenController {

    @FXML
    private Text CustomerAdressText;

    @FXML
    private Text CustomerNameText;

    @FXML
    private Button MyProfileButton;

    @FXML
    private Button OrdersButton;

    @FXML
    private Text WelcomeUserText;
    
    @FXML
    private Button OrderDeliveredButton;
    
    @FXML
    private Button ShowDetailsButton;
    
    private User user;
    
    private DatabaseAdapter db = new DatabaseAdapter();
    private Chart chart;
    private int chartId;
    
    
    @FXML
    private void loadActiveCharts() throws SQLException {
    	chartId = db.getActiveChartIdByUser(user);
    	System.out.println(chartId);
    	this.chart = db.getChartByChartId(chartId);
         
       
    }

    @FXML
    public void initialize() {
        
    	
    }
    
    @FXML
    public void setScreen() {

    	try {
    		if(db.getActiveChartIdByUser(user) != -1) {
    	    	System.out.println(db.getActiveChartIdByUser(user));

	            loadActiveCharts();
	            CustomerNameText.setText(getUserName(chart.getUserId())); 
	            CustomerAdressText.setText(getCustomerAddress(chart.getUserId())); 
    		}
    		
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

    @FXML
    void OrdersButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("CourrierOrderScreen.fxml", event, user);
    }

    @FXML
    void SeeMyProfileClicked(MouseEvent event) {
    	SceneSwitch.switchScene("CarrierProfileScreen.fxml", event, user);
    }
    public void setUser(User user) {
        this.user = user;
        WelcomeUserText.setText("Welcome, " + user.getName());
    }
    
    
    @FXML
    void OrderDeliveredClicked(MouseEvent event) throws SQLException {
    	 chartId = db.getActiveChartIdByUser(user);
    	 this.chart = db.getChartByChartId(chartId);
    	 chart.setState("delivered");
    	 db.UpdateChartState(chart);
    	 
    	
    	 CustomerNameText.setText("NO ACTIVE ORDER"); 
	     CustomerAdressText.setText(null); 
	     this.user.setChartId(-1);
	     db.UpdateUser(user);
         
    	
    }
    

    @FXML
    void ShowDetailsButtonClicked(MouseEvent event) throws SQLException {
    	if(db.getActiveChartIdByUser(user) != -1) {
    		 SceneSwitch.switchScene("PurchasedProducts.fxml", event, user);
    		 
		}

    }
    
    @FXML
    void logOutButtonPressed(MouseEvent event) {
    		SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    }
    
    
    


}
