package application.controller;

import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
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
    
    public User user; // inhertied by product screen
    
    public void setUser(User user) {
        this.user = user;
        System.out.print("Welcome User: ");
		System.out.println(user.getName());
        WelcomeUserText.setText("Welcome, " + user.getName());
    }

    @FXML
    void SeeMyProfileClicked(MouseEvent event) {
    	SceneSwitch.switchScene("myProfileScreen.fxml", event, user);
    }

    @FXML
    void startShoppingButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("newProductScreen.fxml", event, user);
    }
    
    @FXML
    void logOutButtonClicked(MouseEvent event) {
    	
    	SceneSwitch.switchScene("LoginScreen.fxml", event, null);
    }
    
    @FXML
    void viewMyOrdersClicked(MouseEvent event) {
    	SceneSwitch.switchScene("viewMyOrdersScreen.fxml", event, user);
    }
	

}
