package application.controller;


import java.lang.ModuleLayer.Controller;
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

public class ForgotPasswordScreenController2 {

	private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
    @FXML
    private TextField PasswordBox;

    @FXML
    private TextField PasswordBoxAuth;

    @FXML
    private Button ResetButton;

    @FXML
    void resetButtonClicked(MouseEvent event) throws SQLException {
    	if(PasswordBox.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password Field cannot me empty !");
			Optional<ButtonType>result = alert.showAndWait();
			return;
    	}
    	
    	if(PasswordBoxAuth.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password Auth Field cannot be empty !");
			Optional<ButtonType>result = alert.showAndWait();
			return;
    	}
    	String pw = PasswordBox.getText();
    	if(!isStrongPassword(pw)) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("Password is not strong. It should have at least one upper,one lower, one non-alphabetic letter and one number");
			Optional<ButtonType>	result = alert.showAndWait();
			return;
    	}
    	
    	if(PasswordBox.getText().equals(PasswordBoxAuth.getText()) ) {
    		DatabaseAdapter db = new DatabaseAdapter();
    		db.resetPassword(user, PasswordBox.getText());
    		System.out.println(user.getName());
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

}
