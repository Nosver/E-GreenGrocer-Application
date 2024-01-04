package application.controller;


import java.lang.ModuleLayer.Controller;
import java.sql.SQLException;
import java.util.Optional;

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
    	
    	
    	if(PasswordBox.getText().equals(PasswordBoxAuth.getText()) ) {
    		DatabaseAdapter db= new DatabaseAdapter();
    		db.resetPassword(user, PasswordBox.getText());
    		SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    	}
    	
    }

}
