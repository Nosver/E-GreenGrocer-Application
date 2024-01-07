package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CustomerScreenController   {

    @FXML
    private Button MyProfileButton;

    @FXML
    private Text WelcomeUserText;
    
    @FXML
    private Button startShoppingButton;
    
    private User user;

    @FXML
    void SeeMyProfileClicked(MouseEvent event) {
    	SceneSwitch.switchScene("myProfileScreen.fxml", event, user);
    }

    public void setUser(User user) {
        this.user = user;
        WelcomeUserText.setText("Welcome, " + user.getName());
    }

    @FXML
    void startShoppingButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("newProductScreen.fxml", event, user);
    }
	

}
