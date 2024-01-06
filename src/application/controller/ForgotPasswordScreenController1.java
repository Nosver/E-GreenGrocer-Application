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



public class ForgotPasswordScreenController1{
	
	private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    @FXML
    private TextField EMailBox;

    @FXML
    private Button loginButton;
    
	@FXML
    void continueButtonClicked(MouseEvent event) throws SQLException {
    			
    	if(EMailBox.getText().isBlank()){
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("E-mail field cannot be empty !");
			Optional<ButtonType>result = alert.showAndWait();
			return;
    	}
    	DatabaseAdapter db= new DatabaseAdapter();
    	User user = db.getUserByEmail(EMailBox.getText());
    	
    	if(user == null) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("User doesn't exist on the database !");
			Optional<ButtonType>result = alert.showAndWait();
			
			return;
    	}else {
    		SceneSwitch.switchScene("ForgotPassword2.fxml", event, user);
    	}
    	
    	
    }
    
}
