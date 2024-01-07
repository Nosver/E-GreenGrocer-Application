package application.controller;

import application.SceneSwitch;
import application.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CourrierHomeScreenController {

    @FXML
    private Text CustomerAdressText;

    @FXML
    private Text CustomerNameText;

    @FXML
    private Button MyProfileButton;

    @FXML
    private Button OrdersButton;

    @FXML
    private Text WelcomeUserText;
    
    private User user;

    @FXML
    void OrdersButtonClicked(MouseEvent event) {
    	SceneSwitch.switchScene("CourrierOrderScreen.fxml", event, user);
    }

    @FXML
    void SeeMyProfileClicked(MouseEvent event) {
    	SceneSwitch.switchScene("myProfileScreen.fxml", event, user);
    }
    public void setUser(User user) {
        this.user = user;
        WelcomeUserText.setText("Welcome, " + user.getName());
    }


}
