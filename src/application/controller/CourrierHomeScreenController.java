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
    
    private User user;
    
    private DatabaseAdapter db = new DatabaseAdapter();
    private Chart chart;
   
    
    
    @FXML
    private void loadActiveCharts() throws SQLException {
        ArrayList<Chart> activeCharts = db.getActiveChart();
        
        this.chart = activeCharts.get(0);
       
    }

    @FXML
    public void initialize() {
        
    	try {
    		if(!db.getActiveChart().isEmpty()) {
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
    	SceneSwitch.switchScene("myProfileScreen.fxml", event, user);
    }
    public void setUser(User user) {
        this.user = user;
        WelcomeUserText.setText("Welcome, " + user.getName());
    }
    
    
    @FXML
    void OrderDeliveredClicked(MouseEvent event) throws SQLException {
    	 ArrayList<Chart> activeCharts = db.getActiveChart(); 
    	 activeCharts.get(0).setState("delivered");
    	 db.UpdateChartState(activeCharts.get(0));
    	 
    	 try {
     		if(!db.getActiveChart().isEmpty()) {
 	            loadActiveCharts();
 	            CustomerNameText.setText(getUserName(chart.getUserId())); 
 	            CustomerAdressText.setText(getCustomerAddress(chart.getUserId())); 
     		}
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	
    }


}
