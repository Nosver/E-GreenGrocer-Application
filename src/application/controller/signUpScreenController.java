package application.controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

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
    private Button backButton;	

    @FXML
    private PasswordField PasswordBox;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField nameBox;
    
    public static boolean isValidEmail(String email) {
        // Check if the email contains '@' and '.'
        return email != null && email.contains("@") && email.contains(".");
    }

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
    	// Check if email is valid
    	if(!(EMailBox.getText().contains("@") && EMailBox.getText().contains("."))) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Given mail is not vaild!");
			Optional<ButtonType> result = alert.showAndWait();
			return;
    	}
    	
    	if(PasswordBox.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	if(!isStrongPassword(PasswordBox.getText())) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password is not strong. It should have at least one upper,one lower, one non-alphabetic letter and one number");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(!nameBox.getText().isBlank() && !EMailBox.getText().isBlank() && !PasswordBox.getText().isBlank()) {
    		
    		User user = new User(nameBox.getText(), PasswordBox.getText(),EMailBox.getText(),"customer" ,null);
        	
        	DatabaseAdapter db= new DatabaseAdapter();
        	
        	User u =db.getUserByEmail(EMailBox.getText());
        	if(u!= null) {
        		Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("Customer already exists");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
        	}
        	
        	db.insertUser(user);
            
            SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    		
    	}
    	
    	
    	
    }
    private static boolean containsUpperCase(String password) {
        return !password.equals(password.toLowerCase());
    }
    private static boolean containsLowerCase(String password) {
        return !password.equals(password.toUpperCase());
    }

    private static boolean containsNonAlphabetic(String password) {
        Pattern pattern = Pattern.compile(".*[^a-zA-Z].*");
        return pattern.matcher(password).matches();
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }
    
    public static boolean isStrongPassword(String password) {
        if (!containsUpperCase(password)) {
            return false;
        }
        
        if (!containsLowerCase(password)) {
            return false;
        }

        if (!containsNonAlphabetic(password)) {
            return false;
        }

        if (!containsDigit(password)) {
            return false;
        }

        return true;
    }

    @FXML
    void backButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    }
  
}


