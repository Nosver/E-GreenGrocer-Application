package application.controller;

import application.DatabaseAdapter;
import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void RegisterButtonClicked(MouseEvent event) {
    	
    	String name = nameBox.getText();
    	String email = EMailBox.getText();
    	String password = PasswordBox.getText();
    
    	
    	User user = new User(name, email, password, null, null);
    	user.setRole("customer");
    	
        DatabaseAdapter.insertUser(user);
        
        SceneSwitch.switchScene("LoginScreen.fxml", event, user);
    	
    }

}
