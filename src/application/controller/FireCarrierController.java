package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class FireCarrierController implements Initializable{

    @FXML
    private Button backButton;

    @FXML
    private TableView<User> carrierTable;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Button fireButton;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private Label selectedCarrierLabel;

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

    @FXML
    void carrierTablePressed(MouseEvent event) {
    	User carrier = carrierTable.getSelectionModel().getSelectedItem();
    	if(carrier!= null)
    		selectedCarrierLabel.setText(carrier.getName());
    }
    @FXML
    void fireButtonClicked(MouseEvent event) throws SQLException {
    	User carrier = carrierTable.getSelectionModel().getSelectedItem();
    	if(carrier== null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("please select a carrier to fire");
			Optional<ButtonType>	result = alert.showAndWait();
    		
    		return;
    	}
    	DatabaseAdapter db= new DatabaseAdapter();
    	db.deleteUser(carrier);
    	
    	
    	selectedCarrierLabel.setText(null);
    	refresh();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DatabaseAdapter db= new DatabaseAdapter();
		ArrayList<User> users = null;
		try {
			users = db.getAllCarriers();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		ObservableList<User> carriers = FXCollections.observableArrayList(users);
	 	
		
		  nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		  emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		  carrierTable.setItems(carriers);  
	}
	
	public void refresh() {
		DatabaseAdapter db= new DatabaseAdapter();
		ArrayList<User> users = null;
		try {
			users = db.getAllCarriers();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		ObservableList<User> carriers = FXCollections.observableArrayList(users);
	 	
		
		  nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		  emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		  carrierTable.setItems(carriers);  
	}

}
