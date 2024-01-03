package application.controller;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    		User user= DatabaseAdapter.getUserByEmail(EMailBox.getText());
    		if(user== null) {
    			System.out.println("user not found");
    		}
    		String enteredPassword =PasswordBox.getText();
    		
    		if(user.getPassword().equals(enteredPassword)) {
    			SceneSwitch.switchScene("customer.fxml", event, user);
    		}
    		else
    			System.out.println("password yanlış aq");
    		
    }

}