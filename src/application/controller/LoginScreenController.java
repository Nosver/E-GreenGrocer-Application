package application.controller;

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

public class LoginScreenController {

    @FXML
    private Button createuser_BUTTON;
    
    @FXML
    private TextField EMailBox;
    
    @FXML
    private PasswordField PasswordBox;



    @FXML
    private Button forgotpassword_BUTTON;

    @FXML
    private Button loginButton;

    @FXML
    void loginButtonClicked(MouseEvent event) {
    		if(EMailBox.getText().isBlank()) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("E-mail field can not be empty");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
    		}
    		else if(PasswordBox.getText().isBlank()) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("Password field can not be empty");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
    		}
    		
    	
    		User user= DatabaseAdapter.getUserByEmail(EMailBox.getText());
    		if(user== null) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("wrong Email or Password");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
    		}
    		String enteredPassword =PasswordBox.getText();
    		
    		if(user.getPassword().equals(enteredPassword)) {
    			SceneSwitch.switchScene("customer.fxml", event, user);
    		}
    		else
    			System.out.println("password yanlış aq");
    		
    }

}