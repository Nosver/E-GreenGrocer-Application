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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UpdateCarrierInfoController {

    @FXML
    private Button BackButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameUpdateField;

    @FXML
    private TextField passwordUpdateField;

    @FXML
    private Button updateButton;
  
	private User user;
    
	 public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

    @FXML
    void BackButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("CourrierHomeScreen.fxml",event , user);
    }

    @FXML
    void updateButtonClicked(MouseEvent event) throws SQLException {
    	if(nameUpdateField.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Name-Surname can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(emailField.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Email can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(passwordUpdateField.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password can not be blank");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	if(!isStrongPassword(passwordUpdateField.getText())) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password is not strong. It should have at least one upper,one lower, one non-alphabetic letter and one number");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(!nameUpdateField.getText().isBlank() && !emailField.getText().isBlank() && !passwordUpdateField.getText().isBlank()) {
    		
    		User user = new User(nameUpdateField.getText(), passwordUpdateField.getText(),emailField.getText(),"customer" ,null);
        	
        	DatabaseAdapter db= new DatabaseAdapter();
        	db.insertUser(user);
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

}
