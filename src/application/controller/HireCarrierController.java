package application.controller;

import java.sql.SQLException;
import java.util.Optional;

import application.DatabaseAdapter;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HireCarrierController {

    @FXML
    private Button backButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button hireCarrierButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;
    
    private User user;
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@FXML
    void backButtonClicked(MouseEvent event) {

    }

    @FXML
    void hireCarrierButtonClicked(MouseEvent event) throws SQLException {
    	if(nameField.getText().isBlank() || emailField.getText().isBlank() || passwordField.getText().isBlank()) {
    		Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("error");
			alert.setContentText("please fill all the fields");
			Optional<ButtonType>	result = alert.showAndWait();
    		return;
    	}
    	User carrier = new User(nameField.getText(),passwordField.getText(),emailField.getText(),"carrier",null);
    	
    	DatabaseAdapter db= new DatabaseAdapter();
    	
    	db.insertUser(carrier);
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setContentText("Carrier added succesfully");
		Optional<ButtonType>	result = alert.showAndWait();
    	
    }

}
