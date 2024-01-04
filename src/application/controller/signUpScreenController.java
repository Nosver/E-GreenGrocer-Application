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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class signUpScreenController {

    @FXML
    private TextField EMailBox;

    @FXML
    private PasswordField PasswordBox;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField nameBox;


    @FXML
    void RegisterButtonClicked(MouseEvent event) throws SQLException {
    	
    	if(nameBox.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Name-Surname can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(EMailBox.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Email can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(PasswordBox.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(!nameBox.getText().isBlank() && !EMailBox.getText().isBlank() && !PasswordBox.getText().isBlank()) {
    		
    		User user = new User(nameBox.getText(), EMailBox.getText(), PasswordBox.getText(), null, null);
        	user.setRole("customer");
        	DatabaseAdapter db= new DatabaseAdapter();
        	db.insertUser(user);
            
            SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    		
    	}
    	
    	
    	
    }

}
