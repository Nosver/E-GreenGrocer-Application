package application.controller;

import java.sql.SQLException;
import java.util.Optional;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UpdateUserInfoController  {

    @FXML
    private TextArea addressField;

    @FXML
    private TextField emailField;
    
    @FXML
    private Button BackButton;

    @FXML
    private TextField nameUpdateField;

    @FXML
    private TextField passwordUpdateField;

    @FXML
    private Button updateButton;
    
    private User user;
    
    public void setUser(User user) {
    	this.user= user;
    	addressField.setPromptText(user.getAddress());
		emailField.setPromptText(user.getEmail());
		nameUpdateField.setPromptText(user.getName());
		passwordUpdateField.setPromptText(user.getPassword());
    }

    @FXML
    void updateButtonClicked(MouseEvent event) throws SQLException {
    
    		if(!addressField.getText().isBlank()) {
    			user.setAddress(addressField.getText());
    		}
    		if(!emailField.getText().isBlank()) {
    			user.setEmail(emailField.getText());
    		}
    		if(!nameUpdateField.getText().isBlank()) {
    			user.setName(nameUpdateField.getText());
    		}
    		if(!passwordUpdateField.getText().isBlank()) {
    			user.setPassword(passwordUpdateField.getText());
    		}
    		
    		if(addressField.getText().isBlank() && emailField.getText().isBlank() && nameUpdateField.getText().isBlank() && passwordUpdateField.getText().isBlank()) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("please provide some field names");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
    		}
    		DatabaseAdapter db= new DatabaseAdapter();
    		db.UpdateUser(user);    		
    		
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Info");
			alert.setContentText("Updated succesfully");
			Optional<ButtonType>	result = alert.showAndWait();
			
    		
			
    		
    }
    @FXML
    void BackButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("myProfileScreen.fxml",event , user);
    }
    

}
