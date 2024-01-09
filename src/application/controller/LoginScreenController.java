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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginScreenController {

    @FXML
    private Button createuser_BUTTON;
    @FXML
    private TextField PasswordText;
    
    @FXML
    private TextField EMailBox;
    
    @FXML
    private PasswordField PasswordBox;

    @FXML
    private CheckBox showPasswordButton;

    @FXML
    private Button forgotpassword_BUTTON;

    @FXML
    private Button loginButton;
    
    @FXML
    void createUserButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("signUpScreen.fxml", event, null);
    }

    @FXML
    void loginButtonClicked(MouseEvent event) throws SQLException {
    	if(PasswordBox.getText().isBlank()) {
    		PasswordBox.setText(PasswordText.getText());
    	}
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
    		
    		DatabaseAdapter databaseAdapter=  new DatabaseAdapter();
    		User user= databaseAdapter.getUserByEmail(EMailBox.getText());
    		if(user== null) {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("wrong Email");
    			Optional<ButtonType>	result = alert.showAndWait();
    			return;
    		}
    		String enteredPassword =PasswordBox.getText();
    		
    		if(user.getPassword().equals(enteredPassword)) {
    			System.out.println(user.getRole());
    			if(user.getRole().equals("customer"))
    				SceneSwitch.switchScene("Customer.fxml", event, user);
    			else if(user.getRole().equals("owner"))
    				SceneSwitch.switchScene("OwnerScreen.fxml", event, user);
    			else if(user.getRole().equals("carrier"))
    				SceneSwitch.switchScene("CourrierHomeScreen.fxml", event, user);
    		}
    		else{
    			System.out.println("Password is incorrect!");
				System.out.println("Correct Password:\t");
				System.out.print(user.getPassword());
				System.out.println("Entered Password:\t");
				System.out.print(enteredPassword); 
				Alert alert = new Alert(Alert.AlertType.WARNING);
    			alert.setTitle("error");
    			alert.setContentText("Wrong Password");
    			Optional<ButtonType> result = alert.showAndWait();
    			return;
			}
    		
    }
    @FXML
    void resetPasswordButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("ForgotPassword1.fxml", event, null);
    }

    @FXML
    void changeVisibilty(MouseEvent event) {
    	if(showPasswordButton.isSelected()) {
    		PasswordText.setText(PasswordBox.getText());
    		PasswordText.setVisible(true);	
    		PasswordBox.setVisible(false);		
    		return;

    	}
    	PasswordBox.setText(PasswordText.getText());
    	PasswordBox.setVisible(true);
    	PasswordText.setVisible(false);	
    	
    }
   

}